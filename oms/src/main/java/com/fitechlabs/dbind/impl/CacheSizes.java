// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CacheSizes.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.license.LicenseService;
import com.fitechlabs.xtrade.kernel.license.Licensing;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.io.IOException;
import java.util.*;

public final class CacheSizes
{
    private static class Impl
    {

        private int getActualSize(String name)
        {
            return getActualSize(name, defaultSize);
        }

        private int getActualSize(String name, int requestedDefaultSize)
        {
            Object o = sizes.get(name);
            if(o != null)
            {
                return ((Integer)o).intValue();
            } else
            {
                int s = computeActualSize(name, defaultSize);
                sizes.put(name, new Integer(s));
                return s;
            }
        }

        private int computeActualSize(String name, int requestedDefaultSize)
        {
            String configName = name + "." + type;
            int configSize = CacheSizes.getConfigValue(configName, requestedDefaultSize);
            String source;
            if(configSize == requestedDefaultSize)
                source = "set to " + configSize + " from default value";
            else
                source = "set to " + configSize + " from server config";
            int cacheLimit = CacheSizes.cacheImpl.computeLimit();
            int typeLimit = computeLimit();
            int limit = Math.max(cacheLimit, typeLimit);
            String limits = null;
            if(configSize > limit)
            {
                configSize = limit;
                limits = " but limited by licensing to " + configSize;
            }
            String message = "'" + configName + "' " + source;
            if(limits != null)
                CacheSizes.log.info(message + limits);
            else
                CacheSizes.log.debug(message);
            return configSize;
        }

        private int computeLimit()
        {
            com.fitechlabs.xtrade.kernel.license.Key publicKey;
            try
            {
                publicKey = KernelPlugin.getPublicKey();
            }
            catch(IOException e)
            {
                CacheSizes.log.warn("can't get kernel public key, using default for '" + limitName + "' of " + unlicensedLimit);
                return unlicensedLimit;
            }
            LicenseService ls = Licensing.getLicenseServiceInstance();
            Collection c = ls.getValidAttributeValues("xtrade.dbind", publicKey, limitName);
            int limit = unlicensedLimit;
            for(Iterator it = c.iterator(); it.hasNext();)
            {
                String valu = (String)it.next();
                try
                {
                    int i = Integer.parseInt(valu);
                    limit = Math.max(i, limit);
                }
                catch(NumberFormatException nfe)
                {
                    CacheSizes.log.warn("limit value '" + limitName + "' unparsable: '" + valu + "'");
                }
            }

            return limit;
        }

        private final String type;
        private final String limitName;
        private final int unlicensedLimit;
        private final Map sizes;
        private final int defaultSize;




        private Impl(String type, int defaultSize, int unlicensedLimit)
        {
            sizes = new HashMap();
            this.type = type;
            limitName = "limit." + type;
            this.unlicensedLimit = unlicensedLimit;
            this.defaultSize = CacheSizes.getConfigValue("default." + type, defaultSize);
        }

    }


    static int getConfigValue(String name, int defValue)
    {
        int value;
        value = ServerConfig.getConfigValueAsInt("cache", name, defValue);
        if(DBG)
            log.debug("for cache|" + name + " using ServerConfig " + (value != defValue ? "looked up value " : "default value ") + value);
        return value;
        Exception e;
        e;
        log.warn("non-caching server config lookup failed, for cache|" + name + " using default value " + defValue + ", e=" + e);
        return defValue;
    }

    private CacheSizes()
    {
    }

    public static int getDefaultRowCacheSize()
    {
        return rowsImpl.defaultSize;
    }

    public static int getDefaultEnumCacheSize()
    {
        return enumsImpl.defaultSize;
    }

    public static int getDefaultBeanCount()
    {
        return beansImpl.defaultSize;
    }

    public static int getActualRowCacheSize(String name)
    {
        return rowsImpl.getActualSize(name);
    }

    public static int getActualEnumCacheSize(String name)
    {
        return enumsImpl.getActualSize(name);
    }

    public static int getActualBeanCount(String name)
    {
        return beansImpl.getActualSize(name);
    }

    public static int getActualRowCacheSize(String name, int defaultRowCacheSize)
    {
        return rowsImpl.getActualSize(name, defaultRowCacheSize);
    }

    public static int getActualEnumCacheSize(String name, int defaultEnumCacheSize)
    {
        return enumsImpl.getActualSize(name, defaultEnumCacheSize);
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
    private static final String COMPONENT_XTRADE_DBIND = "xtrade.dbind";
    private static final String SERVER_CONFIG_CATEG_CACHE = "cache";
    private static Impl cacheImpl = new Impl("cache", 4, 4);
    private static Impl beansImpl = new Impl("beans", 10, 10);
    private static Impl rowsImpl = new Impl("rows", 10, 10);
    private static Impl enumsImpl = new Impl("enums", 4, 4);

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.CacheSizes.class);
        DBG = log.ison();
    }


}
