// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvHeartbeat.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            IHBBacking, InvServer, InvSQL, InvHeartbeats, 
//            CachingDatabase, CDBBacking

class InvHeartbeat
    implements Runnable
{

    InvHeartbeat(DataSource ds)
        throws SQLException
    {
        running = true;
        indexCount = new HashMap();
        InvServer server = InvServer.getInstance();
        serverId = server.getServerId();
        String serverAddr = server.getAddress();
        int serverPort = server.getPort();
        serial = nextSerial();
        this.ds = ds;
        thread = new Thread(this, "Heartbeat-" + serverId + "." + serial);
        thread.setDaemon(true);
        thread.setPriority(8);
        long before = System.currentTimeMillis();
        InvSQL.CreateHeartbeatResult chr = InvSQL.createHeartbeat(ds, serverId, serverAddr, serverPort, serial);
        if(DBG)
            log.debug("heartbeat created: " + chr);
        url = chr.url;
        long expiration = (before + (chr.expirationDate.getTime() - chr.createDate.getTime())) - 1000L;
        backing = new IHBBacking(expiration);
    }

    void replaceDataSourceWithAnotherOneThatTargetSameDatabase(DataSource ds)
    {
        this.ds = ds;
    }

    private static synchronized int nextSerial()
    {
        return ++serialCount;
    }

    int getSerial()
    {
        return serial;
    }

    void start()
    {
        thread.start();
        log.debug("started " + thread);
    }

    void stop()
    {
        log.debug("stopping " + thread);
        running = false;
        backing.setInvalid();
        try
        {
            thread.interrupt();
        }
        catch(Exception e)
        {
            log.warn(thread.getName() + ": " + e);
        }
        finally
        {
            try
            {
                InvSQL.deleteServerEntry(ds, serverId);
            }
            catch(Exception e)
            {
                log.warn(thread.getName() + ": " + e);
            }
        }
    }

    public void run()
    {
        long waitIntervalMillis;
        Connection conn;
        waitIntervalMillis = (long)InvHeartbeats.HEARTBEAT_INTERVAL_SECONDS * 1000L;
        log.info(thread.getName() + " has started on " + url + ", interval=" + (float)waitIntervalMillis / 1000F + ", duration=" + InvHeartbeats.SUBSCRIPTION_DURATION_SECONDS);
        conn = null;
        do
        {
            if(!running)
                break;
            try
            {
                if(DBG)
                    log.debug("ready to update heartbeat...");
                if(conn == null)
                {
                    conn = ds.getConnection();
                    log.info("Heartbeat will use new Connection object from " + ds);
                }
                long before = System.currentTimeMillis();
                InvSQL.UpdateHeartbeatResult uhr = InvSQL.updateHeartbeat(conn, serverId);
                long after = System.currentTimeMillis();
                if(DBG)
                    log.debug("...heartbeat updated: " + uhr);
                long newExpiration = after + (long)(InvHeartbeats.SUBSCRIPTION_DURATION_SECONDS * 1000);
                if(uhr.synchWasLost)
                {
                    String details = "Heartbeat started at " + uhr.updateDate + ", but it is expected to be started by " + uhr.priorExpirationDate;
                    onSynchWasLost(newExpiration, "DB heartbeat is not started in time. " + uhr);
                } else
                if(after > backing.getExpiration())
                {
                    String details = "Heartbeat finished at " + new Timestamp(after) + ", but it is expected to be finished by " + new Timestamp(backing.getExpiration());
                    onSynchWasLost(newExpiration, "DB heartbeat is not done in time." + details);
                } else
                {
                    backing.setExpiration(newExpiration);
                    if(DBG)
                        log.debug("Next heartbeat is supposed to be done by " + new Timestamp(newExpiration));
                }
            }
            catch(Throwable t)
            {
                log.warn("heartbeat failure", t);
                closeConnection(conn);
                conn = null;
            }
            try
            {
                synchronized(thread)
                {
                    thread.wait(waitIntervalMillis);
                }
                continue;
            }
            catch(InterruptedException ie)
            {
                if(DBG)
                    log.debug("heartbeat interrupted: " + ie);
                if(!running)
                    break;
            }
            catch(Throwable t)
            {
                log.warn("heartbeat wait failure", t);
            }
        } while(true);
        closeConnection(conn);
        conn = null;
        break MISSING_BLOCK_LABEL_556;
        Exception exception1;
        exception1;
        closeConnection(conn);
        conn = null;
        throw exception1;
        log.info(thread.getName() + " has stopped.");
        return;
    }

    void closeConnection(Connection conn)
    {
        if(conn != null)
            try
            {
                conn.close();
            }
            catch(Throwable e) { }
    }

    void onSynchWasLost(long newExpiration, String reason)
    {
        log.warn("Heartbeat syncronization was lost, all cached items will be dropped. Reason: " + reason);
        dropCache(newExpiration);
    }

    void dropCache(long newExpiration)
    {
        backing.setInvalid();
        if(!running)
            return;
        backing = new IHBBacking(newExpiration);
        if(!running)
            backing.setInvalid();
    }

    CDBBacking getBacking(CachingDatabase cachingDatabase, boolean create)
    {
        return backing.getBacking(cachingDatabase, create);
    }

    void addSubscription(Long accountId)
        throws SQLException
    {
        if(!running)
            return;
        int count[] = null;
        synchronized(indexCount)
        {
            count = (int[])indexCount.get(accountId);
            if(count == null)
            {
                count = new int[1];
                indexCount.put(accountId, count);
            }
        }
        synchronized(count)
        {
            if(count[0] == 0)
                InvSQL.insertSubscription(ds, serverId, accountId.longValue());
            count[0]++;
        }
    }

    void removeSubscription(Long accountId)
        throws SQLException
    {
        if(!running)
            return;
        int count[] = null;
        synchronized(indexCount)
        {
            count = (int[])indexCount.get(accountId);
        }
        if(count == null)
            throw new IllegalStateException("Subscription not found for " + accountId);
        synchronized(count)
        {
            if(--count[0] <= 0)
            {
                InvSQL.deleteSubscription(ds, serverId, accountId.longValue());
                count[0] = 0;
            }
        }
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit log;
    private static final boolean DBG;
    private DataSource ds;
    private Thread thread;
    private boolean running;
    private long serverId;
    private int serial;
    private static int serialCount;
    private String url;
    private IHBBacking backing;
    private static final long SQL_TIMESTAMP_ROUNDOFF_MILLIS = 1000L;
    private Map indexCount;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.InvHeartbeat.class);
        DBG = log.ison();
    }
}
