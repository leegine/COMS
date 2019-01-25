// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBMSPipeInvServer.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.xtrade.kernel.data.DataSources;
import com.fitechlabs.xtrade.kernel.util.PropertiesFinder;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            InvServer, InvSQL, InvRouter

public class DBMSPipeInvServer
{
    public static class DBMSPipeInvWorker extends Thread
    {

        public void stopRunning()
        {
            running = false;
            synchronized(this)
            {
                interrupt();
            }
        }

        public void run()
        {
            DBMSPipeInvServer.log.info(getName() + " has started.");
            do
            {
                if(!running)
                    break;
                CallableStatement stmt = null;
                CallableStatement stmt2 = null;
                int status = -1;
                boolean closeConnection = false;
                int seq = -1;
                try
                {
                    try
                    {
                        if(conn == null)
                            conn = DataSources.getDefaultDataSource().getConnection();
                        stmt = conn.prepareCall("{call dbms_pipe_inv.receive_request(?, ?, ?, ?, ?)}");
                        stmt.setString(1, requestPipeName);
                        stmt.registerOutParameter(2, 4);
                        stmt.registerOutParameter(3, 12);
                        stmt.registerOutParameter(4, 4);
                        stmt.registerOutParameter(5, 12);
                        stmt.execute();
                        status = stmt.getInt(2);
                        if(status == 0)
                        {
                            String responsePipeName = stmt.getString(3);
                            seq = stmt.getInt(4);
                            String message = stmt.getString(5);
                            if(DBMSPipeInvServer.DBG)
                                DBMSPipeInvServer.log.debug(getName() + " received:" + message);
                            String tokens[] = null;
                            boolean ok = true;
                            try
                            {
                                StringTokenizer st = new StringTokenizer(message, ":");
                                String name = st.nextToken();
                                String op = st.nextToken();
                                String acct = st.nextToken();
                                String pk = st.nextToken();
                                String beginInvalidationTimestampStr = st.nextToken();
                                tokens = (new String[] {
                                    name, op, acct, pk, beginInvalidationTimestampStr
                                });
                                InvRouter.onInvMessage(tokens);
                            }
                            catch(Exception e)
                            {
                                ok = false;
                            }
                            String returnCode = ok ? "ok" : "ng";
                            long echoTimestamp = tokens == null ? -1L : Long.parseLong(tokens[4]);
                            stmt2 = conn.prepareCall("{call dbms_pipe_inv.send_response(?, ?, ?)}");
                            stmt2.setString(1, responsePipeName);
                            stmt2.setInt(2, seq);
                            stmt2.setString(3, returnCode);
                            stmt2.execute();
                        }
                    }
                    catch(SQLException e)
                    {
                        String error = "Invalidation processing error:" + e.getMessage();
                        DBMSPipeInvServer.log.error("Cache invalidation error.", e);
                        closeConnection = true;
                    }
                    continue;
                }
                finally
                {
                    if(stmt != null)
                        try
                        {
                            stmt.close();
                        }
                        catch(Exception e) { }
                    if(stmt2 != null)
                        try
                        {
                            stmt2.close();
                        }
                        catch(Exception e) { }
                    if(closeConnection && conn != null)
                        try
                        {
                            Connection c = conn;
                            conn = null;
                            c.close();
                        }
                        catch(Exception e) { }
                }
            } while(true);
            if(conn != null)
                try
                {
                    conn.close();
                }
                catch(Exception e) { }
            DBMSPipeInvServer.log.info(getName() + " has stopped.");
        }

        private String requestPipeName;
        private Connection conn;
        private boolean running;

        public DBMSPipeInvWorker(String name, String requestPipeName)
        {
            super(name);
            conn = null;
            running = true;
            this.requestPipeName = requestPipeName;
        }
    }


    public static DBMSPipeInvServer getInstance()
        throws SQLException
    {
        if(theInstance == null)
            synchronized(com.fitechlabs.dbind.impl.DBMSPipeInvServer.class)
            {
                if(theInstance == null)
                {
                    theInstance = new DBMSPipeInvServer();
                    Runtime.getRuntime().addShutdownHook(new Thread() {

                        public void run()
                        {
                            DBMSPipeInvServer.stopAll();
                        }

                    }
);
                    theInstance.start();
                }
            }
        return theInstance;
    }

    private DBMSPipeInvServer()
        throws SQLException
    {
        long serverId = InvServer.getInstance().getServerId();
        int size = InvSQL.getDBMSPipeSize(DataSources.getDefaultDataSource());
        String hosts = InvSQL.getDBHostnames(DataSources.getDefaultDataSource());
        Set hostnames = new HashSet();
        if(hosts != null)
        {
            for(StringTokenizer tokens = new StringTokenizer(hosts, ","); tokens.hasMoreTokens(); hostnames.add(tokens.nextToken().trim().toUpperCase()));
        }
        if(hostnames.size() == 0)
        {
            for(int i = 0; i < size; i++)
            {
                String pipeName = "s$" + serverId + "." + i;
                Thread worker = new DBMSPipeInvWorker("DBMSPipeInvWorker-" + pipeName, pipeName);
                worker.setPriority(9);
                workers.add(new DBMSPipeInvWorker("DBMSPipeInvWorker-" + pipeName, pipeName));
            }

        } else
        {
            for(Iterator it = hostnames.iterator(); it.hasNext();)
            {
                String hostname = (String)it.next();
                int i = 0;
                while(i < size) 
                {
                    String pipeName = "s$" + hostname + "." + serverId + "." + i;
                    Thread worker = new DBMSPipeInvWorker("DBMSPipeInvWorker-" + pipeName, pipeName);
                    worker.setPriority(9);
                    workers.add(new DBMSPipeInvWorker("DBMSPipeInvWorker-" + pipeName, pipeName));
                    i++;
                }
            }

        }
    }

    void start()
    {
        for(Iterator it = workers.iterator(); it.hasNext(); ((Thread)it.next()).start());
    }

    public static void stopAll()
    {
        synchronized(com.fitechlabs.dbind.impl.DBMSPipeInvServer.class)
        {
            if(theInstance != null)
            {
                for(Iterator it = theInstance.workers.iterator(); it.hasNext(); ((DBMSPipeInvWorker)it.next()).stopRunning());
            }
        }
    }

    private static final Logit log;
    private static final boolean DBG;
    private static final int SERVER_TIMEOUT_MILLIS = PropertiesFinder.getProperty("dbind.properties", "invalidator.serverTimeoutMillis", 20000);
    private static DBMSPipeInvServer theInstance;
    private final List workers = new ArrayList();

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.DBMSPipeInvServer.class);
        DBG = log.ison();
    }


}
