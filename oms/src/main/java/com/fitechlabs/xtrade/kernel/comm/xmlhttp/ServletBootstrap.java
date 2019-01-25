// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServletBootstrap.java

package com.fitechlabs.xtrade.kernel.comm.xmlhttp;

import com.fitechlabs.dbind.impl.InvServer;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.comm.sockpool.SocketPoolPlugin;
import com.fitechlabs.xtrade.kernel.data.DataSources;
import com.fitechlabs.xtrade.kernel.license.LicensingPlugin;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public final class ServletBootstrap
{

    private ServletBootstrap()
    {
    }

    public static Properties stringToProperties(String s)
    {
        Properties p = new Properties();
        String name;
        String value;
        for(StringTokenizer nameValuePairs = new StringTokenizer(s, COMMA_OR_SEMICOLON); nameValuePairs.hasMoreTokens(); p.setProperty(name, value))
        {
            String pairs = nameValuePairs.nextToken();
            StringTokenizer sides = new StringTokenizer(pairs, EQUALS);
            if(!sides.hasMoreTokens())
                throw new IllegalArgumentException("no left-hand-side.");
            name = sides.nextToken();
            if(!sides.hasMoreTokens())
                throw new IllegalArgumentException("no right-hand-side for " + name);
            value = sides.nextToken();
        }

        return p;
    }

    static void boot(ServletConfig config)
        throws ServletException
    {
        log.info("======================== booting xtrade =========================");
        String DEFAULT_DRIVER = config.getInitParameter("DEFAULT_DRIVER");
        String DEFAULT_URL = config.getInitParameter("DEFAULT_URL");
        String DEFAULT_PROPERTIES = config.getInitParameter("DEFAULT_PROPERTIES");
        String DEFAULT_DATA_SOURCE_JNDI_NAME = config.getInitParameter("DEFAULT_DATA_SOURCE_JNDI_NAME");
        if(DEFAULT_DATA_SOURCE_JNDI_NAME != null && (DEFAULT_URL != null || DEFAULT_DRIVER != null || DEFAULT_PROPERTIES != null))
        {
            String msg = "Invalid init parameter. Either [ {DEFAULT_DATA_SOURCE_JNDI_NAME} or {DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_PROPERTIES} ] should be set. Both can't be set. ";
            log.error("Invalid init parameter. Either [ {DEFAULT_DATA_SOURCE_JNDI_NAME} or {DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_PROPERTIES} ] should be set. Both can't be set. ");
            throw new ServletException("Invalid init parameter. Either [ {DEFAULT_DATA_SOURCE_JNDI_NAME} or {DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_PROPERTIES} ] should be set. Both can't be set. ");
        }
        if(DEFAULT_DATA_SOURCE_JNDI_NAME != null)
        {
            log.info("::: default data source JNDI name is set to  " + DEFAULT_DATA_SOURCE_JNDI_NAME);
            DataSources.setDefaultDataSourceJndiName(DEFAULT_DATA_SOURCE_JNDI_NAME);
        } else
        {
            if(DEFAULT_DRIVER != null)
            {
                try
                {
                    DataSources.setDefaultDriver(DEFAULT_DRIVER);
                }
                catch(Exception e)
                {
                    throw new ServletException("can't set DEFAULT_DRIVER to " + DEFAULT_DRIVER + " - " + e);
                }
                log.info("default driver set to " + DataSources.getDefaultDriver());
            }
            if(DEFAULT_URL != null)
            {
                DataSources.setDefaultUrl(DEFAULT_URL);
                log.info("default url set to " + DataSources.getDefaultUrl());
            }
            if(DEFAULT_PROPERTIES != null)
            {
                Properties props = stringToProperties(DEFAULT_PROPERTIES);
                DataSources.setDefaultProperties(props);
                log.info("default properties set to " + DataSources.getDefaultProperties().toString());
            }
        }
        log.info("Checking the data source for validitiy.");
        try
        {
            DataSource ds = DataSources.getDefaultDataSource();
            boolean isValid = isDataSourceValid(ds);
            if(isValid)
            {
                log.info("::: Data source validity check PASSED.");
            } else
            {
                String msg = "!!!Data source validity check FAILED. Specified JDBC connection related init parameters could be invalid. ";
                log.error("!!!Data source validity check FAILED. Specified JDBC connection related init parameters could be invalid. ");
                throw new ServletException("!!!Data source validity check FAILED. Specified JDBC connection related init parameters could be invalid. ");
            }
        }
        catch(SQLException ex)
        {
            String msg = "Exception while obtaining default data source.";
            log.error("Exception while obtaining default data source.", ex);
            throw new ServletException("Exception while obtaining default data source.", ex);
        }
        String SOCKET_POOL_SERVER_PORT = config.getInitParameter("SOCKET_POOL_SERVER_PORT");
        if(SOCKET_POOL_SERVER_PORT != null)
            SocketPoolPlugin.assignPort((new Integer(SOCKET_POOL_SERVER_PORT)).intValue());
        Map pluginClasses = new HashMap();
        Enumeration e = config.getInitParameterNames();
        do
        {
            if(!e.hasMoreElements())
                break;
            String s = (String)e.nextElement();
            if(s.startsWith("PLUGIN_CLASS"))
                pluginClasses.put(s, config.getInitParameter(s));
            else
            if(s.toLowerCase().startsWith("lic"))
                LicensingPlugin.installLicense(s, config.getInitParameter(s), "init-perameter");
        } while(true);
        List pluginEntries = new ArrayList(pluginClasses.entrySet());
        java.util.Map.Entry entries[] = (java.util.Map.Entry[])pluginEntries.toArray(new java.util.Map.Entry[pluginEntries.size()]);
        Arrays.sort(entries, new Comparator() {

            public int compare(Object o1, Object o2)
            {
                String s1 = (String)((java.util.Map.Entry)o1).getKey();
                String s2 = (String)((java.util.Map.Entry)o2).getKey();
                return s1.compareTo(s2);
            }

            public boolean equals(Object obj)
            {
                return false;
            }

        }
);
        try
        {
            for(int i = 0; i < entries.length; i++)
            {
                String PLUGIN_CLASS = (String)entries[i].getValue();
                log.info("plugging in class " + PLUGIN_CLASS);
                try
                {
                    Class plugin = Class.forName(PLUGIN_CLASS);
                    if(!(com.fitechlabs.xtrade.kernel.boot.Plugin.class).isAssignableFrom(plugin))
                        throw new ServletException("not a Plugin subclass: " + PLUGIN_CLASS);
                    Plugin.plug(plugin);
                }
                catch(Exception e)
                {
                    log.error("plugin failed", e);
                    throw new ServletException("plugin failed: " + e);
                }
            }

            log.info("======================= booting complete ========================");
        }
        catch(Exception e)
        {
            log.error("=============== booting FAILED - shutting down =================");
            InvServer.stopAll();
            Plugin.unplugAll();
        }
    }

    private static boolean isDataSourceValid(DataSource ds)
    {
        try
        {
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("select sysdate from dual");
            ResultSet rs = ps.executeQuery();
            rs.next();
            java.sql.Timestamp ts = rs.getTimestamp(1);
            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException ex)
        {
            String msg = "!!! Data Source validitiy check FAILED. The speicifed DB connection related Init parameters could be invalid. Exception:" + ex;
            log.error(msg);
            return false;
        }
        return true;
    }

    private static Logit log;
    private static final boolean DBG;
    private static final String DEFAULT_BOOT_DIRECTORY = "../properties/xml";
    static String COMMA_OR_SEMICOLON = ",;";
    static String EQUALS = "=";

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.comm.xmlhttp.ServletBootstrap.class);
        DBG = log.ison();
    }
}
