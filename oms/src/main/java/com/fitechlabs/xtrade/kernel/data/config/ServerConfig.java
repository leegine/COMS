// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerConfig.java

package com.fitechlabs.xtrade.kernel.data.config;

import com.fitechlabs.dbind.Database;
import com.fitechlabs.dbind.Table;
import com.fitechlabs.dbind.impl.NonCachingDatabase;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigRow;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.SQLException;
import java.util.*;

public final class ServerConfig
{

    private ServerConfig()
    {
    }

    public static Map getConfig()
        throws DataNetworkException
    {
        return fetchConfig();
    }

    public static Properties getConfigCategory(String category)
        throws DataNetworkException
    {
        Map map = getConfig();
        if(map == null)
            return null;
        else
            return (Properties)map.get(category);
    }

    public static String getConfigValue(String category, String name)
        throws DataNetworkException
    {
        return getConfigValue(category, name, null);
    }

    public static String getConfigValue(String category, String name, String defaultValue)
        throws DataNetworkException
    {
        Properties props = getConfigCategory(category);
        if(props == null)
        {
            if(DBG)
                log.info("category=" + category + ", name=" + name + ": no category, using defaultValue=" + defaultValue);
            return defaultValue;
        }
        String value = (String)props.get(name);
        if(value == null)
        {
            if(DBG)
                log.info("category=" + category + " name=" + name + ": no value found, using defaultValue=" + defaultValue);
            return defaultValue;
        }
        if(DBG)
            log.info("category=" + category + ", name=" + name + ": found value=" + value);
        return value;
    }

    public static long getConfigValueAsLong(String category, String name, long defaultValue)
        throws DataNetworkException
    {
        Properties props = getConfigCategory(category);
        if(props == null)
        {
            if(DBG)
                log.info("category=" + category + ", name=" + name + ": no category, using defaultValue=" + defaultValue);
            return defaultValue;
        }
        String value = (String)props.get(name);
        if(value == null)
        {
            if(DBG)
                log.info("category=" + category + " name=" + name + ": no value found, using defaultValue=" + defaultValue);
            return defaultValue;
        }
        if(DBG)
            log.info("category=" + category + ", name=" + name + ": found value=" + value);
        return Long.parseLong(value);
    }

    public static int getConfigValueAsInt(String category, String name, int defaultValue)
        throws DataNetworkException
    {
        Properties props = getConfigCategory(category);
        if(props == null)
        {
            if(DBG)
                log.info("category=" + category + ", name=" + name + ": no category, using defaultValue=" + defaultValue);
            return defaultValue;
        }
        String value = (String)props.get(name);
        if(value == null)
        {
            if(DBG)
                log.info("category=" + category + " name=" + name + ": no value found, using defaultValue=" + defaultValue);
            return defaultValue;
        }
        if(DBG)
            log.info("category=" + category + ", name=" + name + ": found value=" + value);
        return Integer.parseInt(value);
    }

    private static Map fetchConfig()
        throws DataNetworkException
    {
        List rowList;
        rowList = null;
        QueryProcessor qp = null;
        try
        {
            qp = Processors.getProcessor("config");
            log.debug("query processor found.");
        }
        catch(DataQueryException fe)
        {
            log.debug("failed to find QueryProcessor", fe);
        }
        if(qp != null)
            try
            {
                rowList = (List)qp.doQuery(2, FINDALL_QUERY);
                log.debug("row list found.");
            }
            catch(DataException e)
            {
                log.debug("found QP, but failed to query for list", e);
            }
        if(rowList == null)
        {
            if(priorConfig != null)
            {
                log.debug("no list selected, returning prior config value.");
                return priorConfig;
            }
            if(serverConfigTable == null)
            {
                log.debug("neither config processor nor local table found, using null configuration.");
                return null;
            }
            log.debug("checking local non-caching table.");
            try
            {
                rowList = serverConfigTable.selectAll("config_title in (select config_value from server_config where config_title ='meta_config' and config_categ ='xtrade' and config_name ='current')", null, null, null);
            }
            catch(Exception e)
            {
                log.debug("failed to use local non-caching table, using null configuration", e);
                return null;
            }
        }
        Class class1 = com.fitechlabs.xtrade.kernel.data.config.ServerConfig.class;
        JVM INSTR monitorenter ;
        if(rowList == priorList)
            return priorConfig;
        priorList = rowList;
        priorConfig = toMap(rowList);
        priorConfig;
        class1;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static Map toMap(List rowList)
    {
        Map map = new HashMap();
        Iterator it = rowList.iterator();
        do
        {
            if(!it.hasNext())
                break;
            ServerConfigRow row = (ServerConfigRow)it.next();
            Properties p = (Properties)map.get(row.getConfigCateg());
            if(p == null)
                map.put(row.getConfigCateg(), p = new Properties());
            String value = row.getConfigValue();
            if(value != null)
                p.put(row.getConfigName(), value);
        } while(true);
        return map;
    }

    private static final Logit log;
    private static final boolean DBG;
    private static final String FINDALL_WHERE = "config_title in (select config_value from server_config where config_title ='meta_config' and config_categ ='xtrade' and config_name ='current')";
    private static final BatchedQuery FINDALL_QUERY;
    private static List priorList;
    private static Map priorConfig;
    private static Table serverConfigTable;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.data.config.ServerConfig.class);
        DBG = log.ison();
        FINDALL_QUERY = BatchedQuery.createFindAllQuery(ServerConfigRow.TYPE, "config_title in (select config_value from server_config where config_title ='meta_config' and config_categ ='xtrade' and config_name ='current')", null, null, null);
        try
        {
            Database ncd = new NonCachingDatabase(DataSources.getDefaultDataSource());
            serverConfigTable = ncd.createTable("server_config", com.fitechlabs.xtrade.kernel.data.db.ServerConfigPK.class, com.fitechlabs.xtrade.kernel.data.db.ServerConfigParams.class);
        }
        catch(SQLException e)
        {
            log.warn("non-caching server config table could not be created.");
        }
    }
}
