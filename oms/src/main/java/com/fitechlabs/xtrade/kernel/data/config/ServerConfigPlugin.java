// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerConfigPlugin.java

package com.fitechlabs.xtrade.kernel.data.config;

import com.fitechlabs.dbind.impl.CacheSizes;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.data.db.KernelDBExtensions;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.util.Properties;

public final class ServerConfigPlugin extends Plugin
{

    private ServerConfigPlugin()
    {
    }

    public static void plug()
        throws Exception
    {
        Plugin.plug(com.fitechlabs.xtrade.kernel.data.config.ServerConfigPlugin.class);
    }

    public static void onPlug()
        throws Exception
    {
        int serverConfigRowsSize = CacheSizes.getActualRowCacheSize("server_config");
        int serverConfigEnumsSize = CacheSizes.getActualEnumCacheSize("server_config");
        Plugin.regProcessor("config", com.fitechlabs.xtrade.kernel.data.impl.QPFCachingImpl.class);
        KernelDBExtensions.plug();
    }

    private static int getIntProperty(Properties p, String name, int defaultValue)
    {
        if(p == null)
            return defaultValue;
        String value = p.getProperty(name);
        if(value == null)
            return defaultValue;
        else
            return Integer.parseInt(value);
    }

    private static final Logit log;
    private static final boolean DBG;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.data.config.ServerConfigPlugin.class);
        DBG = log.ison();
    }
}
