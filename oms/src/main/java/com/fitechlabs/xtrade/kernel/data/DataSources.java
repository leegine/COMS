// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataSources.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class DataSources
{
    static class DataSourceWrapper
        implements DataSource
    {

        public String toString()
        {
            return "DataSources.DataSourceWrapper: URL=" + m_url + ";DriverClass=" + m_driverClass + ";Properties=" + m_properties + ";dataSourceJndiName=" + m_dataSourceJndiName;
        }

        void setURL(String url)
        {
            m_url = url;
            if(m_properties != null)
                if(url == null)
                    m_properties.remove("database.url");
                else
                    m_properties.put("database.url", url);
            m_delegate = null;
            m_dataSourceJndiName = null;
        }

        String getURL()
        {
            return m_url;
        }

        void setDriverClass(String driver)
        {
            m_driverClass = driver;
            if(m_properties != null)
                if(driver == null)
                    m_properties.remove("database.driver");
                else
                    m_properties.put("database.driver", driver);
            m_delegate = null;
            m_dataSourceJndiName = null;
        }

        String getDriverClass()
        {
            return m_driverClass;
        }

        void setProperties(Properties properties)
        {
            m_properties = properties != null ? (Properties)properties.clone() : null;
            if(m_properties != null)
            {
                m_driverClass = m_properties.getProperty("database.driver", m_driverClass);
                m_url = m_properties.getProperty("database.url", m_url);
            }
            m_delegate = null;
            m_dataSourceJndiName = null;
        }

        Properties getProperties()
        {
            return m_properties != null ? (Properties)m_properties.clone() : null;
        }

        void setDataSourceJndiName(String dataSourceJndiName)
        {
            m_dataSourceJndiName = dataSourceJndiName;
            m_delegate = null;
            m_driverClass = null;
            m_url = null;
            m_properties = null;
        }

        String getDataSourceJndiName()
        {
            return m_dataSourceJndiName;
        }

        void setDataSource(DataSource dataSource)
        {
            m_delegate = dataSource;
            m_driverClass = null;
            m_url = null;
            m_properties = null;
            m_dataSourceJndiName = null;
        }

        DataSource getDelegate()
        {
            return m_delegate;
        }

        void setDelegate(DataSource delegate)
        {
            m_delegate = delegate;
        }

        boolean check()
            throws SQLException
        {
            if(m_delegate != null)
                return true;
            if(m_dataSourceJndiName == null)
                break MISSING_BLOCK_LABEL_137;
            InitialContext initCtx = new InitialContext();
            DataSources.log.info("Looking up  JNDI resource:" + m_dataSourceJndiName);
            m_delegate = (DataSource)initCtx.lookup(m_dataSourceJndiName);
            DataSources.log.info("Sucessfully Obtained data source from JNDI resource:" + m_dataSourceJndiName);
            return true;
            NamingException ne;
            ne;
            DataSources.log.error("Error while obtaining Data Source from jndi resource.", ne);
            throw new SQLException("Unable obtain data source." + ne.getMessage());
            DataSourceWrapper ds = (DataSourceWrapper)DataSources.getDataSource(m_driverClass, m_url, m_properties);
            if(ds != null && ds.getDelegate() != null)
            {
                m_delegate = ds;
                return true;
            } else
            {
                return false;
            }
        }

        public Connection getConnection()
            throws SQLException
        {
            if(check())
                return m_delegate.getConnection();
            else
                throw new SQLException("Insufficient DataSource desciption:" + this);
        }

        public Connection getConnection(String username, String password)
            throws SQLException
        {
            if(check())
                return m_delegate.getConnection(username, password);
            else
                throw new SQLException("Insufficient DataSource desciption:" + this);
        }

        public int getLoginTimeout()
            throws SQLException
        {
            if(check())
                return m_delegate.getLoginTimeout();
            else
                throw new SQLException("Insufficient DataSource desciption:" + this);
        }

        public PrintWriter getLogWriter()
            throws SQLException
        {
            if(check())
                return m_delegate.getLogWriter();
            else
                throw new SQLException("Insufficient DataSource desciption:" + this);
        }

        public void setLoginTimeout(int seconds)
            throws SQLException
        {
            if(check())
                m_delegate.setLoginTimeout(seconds);
            else
                throw new SQLException("Insufficient DataSource desciption:" + this);
        }

        public void setLogWriter(PrintWriter out)
            throws SQLException
        {
            if(check())
                m_delegate.setLogWriter(out);
            else
                throw new SQLException("Insufficient DataSource desciption:" + this);
        }

        private String m_url;
        private String m_driverClass;
        private Properties m_properties;
        private String m_dataSourceJndiName;
        private DataSource m_delegate;





        DataSourceWrapper()
        {
            m_url = null;
            m_driverClass = "oracle.jdbc.driver.OracleDriver";
            m_properties = null;
            m_dataSourceJndiName = null;
            m_delegate = null;
        }
    }

    static class Listener
    {

        protected void onGetConnection(DataSource source, Connection connection)
            throws SQLException
        {
            throw new UnsupportedClassVersionError("Method onGetConnection(DataSource,Connection) is a subclass responsiblity.");
        }

        Listener()
        {
        }
    }


    public DataSources()
    {
    }

    public static void setDefaultDataSourceJndiName(String dataSourceJndiName)
    {
        defaultDataSource.setDataSourceJndiName(dataSourceJndiName);
    }

    public static String getDefaultDataSourceJndiName()
    {
        return defaultDataSource.getDataSourceJndiName();
    }

    public static void setDefaultDriver(String driver)
        throws ClassNotFoundException
    {
        defaultDataSource.setDriverClass(driver);
    }

    public static void setDefaultUrl(String url)
    {
        defaultDataSource.setURL(url);
    }

    public static void setDefaultProperties(Properties properties)
    {
        defaultDataSource.setProperties(properties);
    }

    public static void setDefaultDataSource(DataSource dataSource)
    {
        defaultDataSource.setDataSource(dataSource);
    }

    public static String getDefaultDriver()
    {
        return defaultDataSource.getDriverClass();
    }

    public static String getDefaultUrl()
    {
        return defaultDataSource.getURL();
    }

    public static Properties getDefaultProperties()
    {
        return defaultDataSource.getProperties();
    }

    public static DataSource getDefaultDataSource()
        throws SQLException
    {
        if(defaultDataSource.check())
            return defaultDataSource;
        else
            return null;
    }

    public static DataSource getDefaultDataSourceServant()
        throws SQLException
    {
        DataSourceWrapper wrapper = (DataSourceWrapper)getDefaultDataSource();
        return wrapper != null ? wrapper.getDelegate() : null;
    }

    public static DataSource getDataSource(Properties props)
        throws SQLException
    {
        if(props == null || props.isEmpty())
        {
            return getDefaultDataSource();
        } else
        {
            String url = props.getProperty("database.url", defaultDataSource.getURL());
            String driver = props.getProperty("database.driver", defaultDataSource.getDriverClass());
            return getDataSource(driver, url, props);
        }
    }

    public static DataSource getDataSource(String driverClass, String url, Properties props)
        throws SQLException
    {
        Object infos[] = formalize(driverClass, url, props);
        if(infos == null)
            return null;
        Properties props0 = (Properties)infos[2];
        String url0 = (String)infos[1];
        String driverClass0 = (String)infos[0];
        Object key = props0.toString();
        DataSourceWrapper val = (DataSourceWrapper)sources.get(key);
        if(val == null)
        {
            DataSource ds = createDataSource(driverClass0, url0, props0);
            val = new DataSourceWrapper();
            val.m_delegate = ds;
            val.m_url = url0;
            val.m_driverClass = driverClass0;
            val.m_properties = props0;
            sources.put(key, val);
        }
        return val;
    }

    private static Object[] formalize(String driverClass, String url, Properties props)
    {
        Properties props0;
        if(props != null)
            props0 = (Properties)props.clone();
        else
            props0 = new Properties();
        if(driverClass != null)
            props0.put("database.driver", driverClass);
        String driverClass0 = props0.getProperty("database.driver");
        if(url != null)
            props0.put("database.url", url);
        String url0 = props0.getProperty("database.url");
        if(driverClass0 == null)
        {
            String message = "Unknown Driver Class infomation for getDataSource method";
            log.warn(message);
            return null;
        }
        if(url0 == null)
        {
            String message = "Unknown URL infomation for getDataSource method";
            log.warn(message);
            return null;
        } else
        {
            return (new Object[] {
                driverClass0, url0, props0
            });
        }
    }

    private static DataSource createDataSource(String drclass, final String url, final Properties props)
        throws SQLException
    {
        log.info("Creating DataSource using Properties " + props);
        try
        {
            Class.forName(drclass);
        }
        catch(ClassNotFoundException cnfe)
        {
            throw new SQLException("Driver class not found: " + drclass);
        }
        final Driver driver = DriverManager.getDriver(url);
        if(DBG)
            log.info("Driver instance is " + driver);
        return new DataSource() {

            public Connection getConnection()
                throws SQLException
            {
                Connection c = driver.connect(url, props);
                Listener l;
                for(Iterator it = DataSources.listeners.iterator(); it.hasNext(); l.onGetConnection(this, c))
                    l = (Listener)it.next();

                return c;
            }

            public Connection getConnection(String parm1, String parm2)
                throws SQLException
            {
                throw new UnsupportedOperationException("Method getConnection(String,String) not implemented.");
            }

            public PrintWriter getLogWriter()
                throws SQLException
            {
                throw new UnsupportedOperationException("Method getLogWriter() not implemented.");
            }

            public int getLoginTimeout()
                throws SQLException
            {
                throw new UnsupportedOperationException("Method getLoginTimeout() not implemented.");
            }

            public void setLogWriter(PrintWriter parm1)
                throws SQLException
            {
                throw new UnsupportedOperationException("Method setLogWriter(PrintWriter) not implemented.");
            }

            public void setLoginTimeout(int parm1)
                throws SQLException
            {
                throw new UnsupportedOperationException("Method setLoginTimeout(int) not implemented.");
            }

        }
;
    }

    static void addListener(Listener l)
    {
        listeners.add(l);
    }

    static void removeListener(Listener l)
    {
        listeners.add(l);
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
    private static final String DRIVER_PROPERTY = "database.driver";
    private static final String URL_PROPERTY = "database.url";
    private static final DataSourceWrapper defaultDataSource = new DataSourceWrapper();
    private static Map sources = new HashMap();
    private static List listeners = new ArrayList();

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.data.DataSources.class);
        DBG = log.ison();
    }


}
