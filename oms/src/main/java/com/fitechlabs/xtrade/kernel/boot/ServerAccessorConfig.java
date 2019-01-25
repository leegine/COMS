// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerAccessorConfig.java

package com.fitechlabs.xtrade.kernel.boot;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.util.*;

public class ServerAccessorConfig
{

    public ServerAccessorConfig()
    {
    }

    public static String[] getConfigValueClusterUrls()
    {
        java.util.Properties values = ServerConfig.getConfigCategory("cluster.urls");
        if(values == null || values.isEmpty())
            return null;
        String urls[];
        Map tree = new TreeMap();
        java.util.Map.Entry e;
        for(Iterator it = values.entrySet().iterator(); it.hasNext(); tree.put(e.getKey(), e.getValue()))
            e = (java.util.Map.Entry)it.next();

        Collection coll = tree.values();
        urls = (String[])coll.toArray(new String[coll.size()]);
        return urls;
        DataNetworkException dne;
        dne;
        return null;
    }

    public static int getConfigValueRetryAfterSeconds()
    {
        return ServerConfig.getConfigValueAsInt("cluster.timeouts", "retry.after.seconds", 20);
        DataNetworkException e;
        e;
        log.warn("Config value categ='cluster.timeouts', name='retry.after.seconds' could not be read, using default of 20 seconds, exception was " + e);
        return 20;
    }

    public static int getConfigValueRefreshEverySeconds()
    {
        return ServerConfig.getConfigValueAsInt("cluster.timeouts", "refresh.every.seconds", 300);
        DataNetworkException e;
        e;
        log.warn("Config value categ='cluster.timeouts', name='refresh.every.seconds' could not be read, using default of 300 seconds, exception was " + e);
        return 300;
    }

    public static int getConfigValueRefreshRetrySeconds()
    {
        return ServerConfig.getConfigValueAsInt("cluster.timeouts", "refresh.retry.seconds", 60);
        DataNetworkException e;
        e;
        log.warn("Config value categ='cluster.timeouts', name='refresh.retry.seconds' could not be read, using default of 60 seconds, exception was " + e);
        return 60;
    }

    public static int getConfigValueSocketPoolClientMaxSize()
    {
        return ServerConfig.getConfigValueAsInt("socket.pool", "client.max.size", 4);
        DataNetworkException e;
        e;
        log.warn("Config value categ='socket.pool', name='client.max.size' could not be read, using default of 4 , exception was " + e);
        return 4;
    }

    public static int getConfigValueSocketPoolTimeOut()
    {
        return ServerConfig.getConfigValueAsInt("socket.pool", "socket.timeout", 60000);
        DataNetworkException e;
        e;
        log.warn("Config value categ='socket.pool', name='socket.timeout' could not be read, using default of 60000 , exception was " + e);
        return 60000;
    }

    public static int getConfigValueSocketPoolBufferSize()
    {
        return ServerConfig.getConfigValueAsInt("socket.pool", "io.buffer.size.bytes", 1024);
        DataNetworkException e;
        e;
        log.warn("Config value categ='socket.pool', name='io.buffer.size.bytes' could not be read, using default of 1024 , exception was " + e);
        return 1024;
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
    private static final String CONFIG_CATEG_CLUSTER_URLS = "cluster.urls";
    private static final String CONFIG_CATEG_CLUSTER_TIMEOUTS = "cluster.timeouts";
    private static final String CONFIG_NAME_RETRY_AFTER_SECONDS = "retry.after.seconds";
    private static final String CONFIG_NAME_REFRESH_EVERY_SECONDS = "refresh.every.seconds";
    private static final String CONFIG_NAME_REFRESH_RETRY_SECONDS = "refresh.retry.seconds";
    private static final int DEFAULT_VALUE_RETRY_AFTER_SECONDS = 20;
    private static final int DEFAULT_VALUE_REFRESH_EVERY_SECONDS = 300;
    private static final int DEFAULT_VALUE_REFRESH_RETRY_SECONDS = 60;
    private static final String SOCKET_POOL_CATEG = "socket.pool";
    private static final String SOCKET_POOL_CLIENT_MAX_SIZE = "client.max.size";
    private static final String SOCKET_POOL_SOCKET_TIMEOUT = "socket.timeout";
    private static final String SOCKET_POOL_BUFFER_SIZE = "io.buffer.size.bytes";
    private static final int DEFAULT_MAX_SIZE = 4;
    private static final int DEFAULT_TIMEOUT = 60000;
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.boot.ServerAccessorConfig.class);
        DBG = log.ison();
    }
}
