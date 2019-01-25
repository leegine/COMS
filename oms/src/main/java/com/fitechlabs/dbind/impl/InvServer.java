// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvServer.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.xtrade.kernel.util.PropertiesFinder;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.*;
import java.sql.SQLException;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            InvWorker, InvSQL, InvHeartbeats

public class InvServer
    implements Runnable
{

    public static InvServer getInstance()
        throws SQLException
    {
        if(theInstance == null)
            synchronized(com.fitechlabs.dbind.impl.InvServer.class)
            {
                if(theInstance == null)
                {
                    theInstance = new InvServer();
                    theInstance.start();
                    Runtime.getRuntime().addShutdownHook(new Thread() {

                        public void run()
                        {
                            InvServer.stopAll();
                        }

                    }
);
                }
            }
        return theInstance;
    }

    private InvServer()
        throws SQLException
    {
        running = true;
        serverId = InvSQL.selectServerId();
        port = 2121;
        do
        {
            if(port >= 2221)
                break;
            try
            {
                serverSocket = new ServerSocket(port);
                break;
            }
            catch(Exception e)
            {
                port++;
            }
        } while(true);
        if(serverSocket == null)
            throw new RuntimeException("No server port available between 2121 and 2121");
        try
        {
            serverSocket.setSoTimeout(SERVER_TIMEOUT_MILLIS);
            address = InetAddress.getLocalHost().getHostName();
        }
        catch(SocketException se)
        {
            throw new RuntimeException("Server socket timeout setting failed: " + se);
        }
        catch(UnknownHostException uhe)
        {
            throw new RuntimeException("Address lookup failed: " + uhe);
        }
        thread = new Thread(this, "InvServer-" + serverId);
        thread.setDaemon(true);
        thread.setPriority(9);
    }

    void start()
    {
        thread.start();
    }

    private void stop()
    {
        running = false;
        synchronized(thread)
        {
            thread.interrupt();
        }
    }

    public static void stopAll()
    {
        synchronized(com.fitechlabs.dbind.impl.InvServer.class)
        {
            if(theInstance != null)
            {
                theInstance.stop();
                theInstance = null;
                InvWorker.stopAll();
                InvHeartbeats.stopAll();
            }
        }
    }

    public void run()
    {
        log.info(thread.getName() + " has started at " + address + ":" + port + ".");
        do
        {
            if(!running)
                break;
            try
            {
                java.net.Socket socket = serverSocket.accept();
                InvWorker worker = new InvWorker(socket);
                worker.start();
            }
            catch(InterruptedIOException iioe) { }
            catch(Throwable t)
            {
                if(running)
                    log.warn("caught and ignoring " + t);
            }
        } while(true);
        try
        {
            serverSocket.close();
            log.info(thread.getName() + " has closed listerning port at " + address + ":" + port + ".");
        }
        catch(IOException e)
        {
            log.warn("IOException thrown when closing listening port at " + address + ":" + port + ".", e);
        }
        log.info(thread.getName() + " has stopped.");
    }

    public long getServerId()
    {
        return serverId;
    }

    public String getAddress()
    {
        return address;
    }

    public int getPort()
    {
        return port;
    }

    private static final Logit log;
    private static final boolean DBG;
    private static final int PORT_MIN = 2121;
    private static final int PORT_MAX = 2221;
    private static final int SERVER_TIMEOUT_MILLIS = PropertiesFinder.getProperty("dbind.properties", "invalidator.serverTimeoutMillis", 20000);
    private static InvServer theInstance;
    private ServerSocket serverSocket;
    private long serverId;
    private String address;
    private int port;
    private Thread thread;
    private boolean running;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.InvServer.class);
        DBG = log.ison();
    }
}
