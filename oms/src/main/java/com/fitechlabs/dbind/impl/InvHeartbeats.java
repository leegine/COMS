// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvHeartbeats.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.SQLException;
import java.util.*;
import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            InvHeartbeat, InvServer, InvSQL, CacheSizes

public class InvHeartbeats
{

    private InvHeartbeats()
    {
    }

    static InvHeartbeat checkHeartbeat(DataSource ds)
        throws SQLException
    {
        InvHeartbeat holder[];
        synchronized(indexDataSource)
        {
            holder = (InvHeartbeat[])indexDataSource.get(ds);
            if(holder == null)
            {
                holder = new InvHeartbeat[1];
                indexDataSource.put(ds, holder);
            }
        }
        if(holder[0] != null)
            return holder[0];
        InvHeartbeat ainvheartbeat[] = holder;
        JVM INSTR monitorenter ;
        if(holder[0] != null)
            return holder[0];
        long serverId;
        int heartbeatSerial;
        InvServer server = InvServer.getInstance();
        serverId = server.getServerId();
        heartbeatSerial = InvSQL.selectServerEntry(ds, serverId);
        if(heartbeatSerial <= 0) goto _L2; else goto _L1
_L1:
        holder[0] = (InvHeartbeat)indexSerial.get(new Integer(heartbeatSerial));
        if(holder[0] == null)
            throw new IllegalStateException("Heartbeat " + heartbeatSerial + " registered for this server " + serverId + " which could not be found.");
        holder[0];
        ainvheartbeat;
        JVM INSTR monitorexit ;
        return;
_L2:
        InvHeartbeat heartbeat;
        heartbeat = new InvHeartbeat(ds);
        heartbeat.start();
        heartbeatSerial = heartbeat.getSerial();
        indexSerial.put(new Integer(heartbeatSerial), heartbeat);
        holder[0] = heartbeat;
        heartbeat;
        ainvheartbeat;
        JVM INSTR monitorexit ;
        return;
        Exception exception1;
        exception1;
        throw exception1;
    }

    static void stopAll()
    {
        Map map = indexDataSource;
        JVM INSTR monitorenter ;
        InvHeartbeat heartbeat;
        for(Iterator it = indexSerial.values().iterator(); it.hasNext(); heartbeat.stop())
            heartbeat = (InvHeartbeat)it.next();

        indexDataSource.clear();
        indexSerial.clear();
        break MISSING_BLOCK_LABEL_84;
        Exception exception;
        exception;
        indexDataSource.clear();
        indexSerial.clear();
        throw exception;
        Exception exception1;
        exception1;
        throw exception1;
    }

    public static void checkHeartbeatAndReplaceHeartbeatDataSource(DataSource ds)
        throws SQLException
    {
        InvHeartbeat heartbeat = checkHeartbeat(ds);
        heartbeat.replaceDataSourceWithAnotherOneThatTargetSameDatabase(ds);
    }

    public static void dropCache(DataSource ds)
        throws SQLException
    {
        InvHeartbeat ihb = checkHeartbeat(ds);
        ihb.dropCache(System.currentTimeMillis() + (long)(1000 * SUBSCRIPTION_DURATION_SECONDS));
        log.info("All cached contents are dropped successfully.");
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
    public static final int HEARTBEAT_INTERVAL_SECONDS = CacheSizes.getConfigValue("heartbeat.interval.seconds", 6);
    public static final int SUBSCRIPTION_DURATION_SECONDS = CacheSizes.getConfigValue("subscription.duration.seconds", 20);
    private static Map indexDataSource = new HashMap();
    private static Map indexSerial = Collections.synchronizedMap(new HashMap());

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.InvHeartbeats.class);
        DBG = log.ison();
    }
}
