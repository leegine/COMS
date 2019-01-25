// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBindConfig.java

package com.fitechlabs.dbind;

import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

public class DBindConfig
{

    public DBindConfig()
    {
    }

    public static int getStringMaxSize()
    {
        if(stringMaxSize == -1)
        {
            synchronized(com.fitechlabs.dbind.DBindConfig.class)
            {
                if(stringMaxSize == -1)
                {
                    stringMaxSize = 1024;
                    try
                    {
                        stringMaxSize = ServerConfig.getConfigValueAsInt("dbind", "string.max.size", 1024);
                    }
                    catch(Exception e)
                    {
                        log.warn("Query to server_config table with config_categ = 'dbind' and config_name = 'string.max.size' failed", e);
                    }
                }
            }
            log.info("xTrade dbind layer will use PreparedStatement.setCharacterStream method for binding a String if its length is bigger than " + stringMaxSize);
        }
        return stringMaxSize;
    }

    private static final Logit log;
    private static final boolean DBG;
    public static final String COMPONENT_XTRADE_DBIND = "dbind";
    public static final String STRING_MAX_SIZE = "string.max.size";
    public static final int DEFAULT_STRING_MAX_SIZE = 1024;
    private static int stringMaxSize = -1;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.DBindConfig.class);
        DBG = log.ison();
    }
}
