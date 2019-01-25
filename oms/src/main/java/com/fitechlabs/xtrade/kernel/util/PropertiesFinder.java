// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PropertiesFinder.java

package com.fitechlabs.xtrade.kernel.util;

import java.io.*;
import java.net.InetAddress;
import java.util.*;

public final class PropertiesFinder
{

    private static void debug(String s1, Object obj)
    {
    }

    private static void debug(String s1)
    {
    }

    private static void info(String s)
    {
        out(s);
    }

    private static void out(String s)
    {
        System.out.println("||||||||||||| PropertiesFileFinder: " + s);
    }

    private PropertiesFinder()
    {
    }

    public static Properties getProperties(String propertyFileName)
        throws FileNotFoundException
    {
        Object obj[] = null;
        synchronized(m_cache)
        {
            obj = (Object[])m_cache.get(propertyFileName);
            if(obj == null)
                m_cache.put(propertyFileName, ((Object) (obj = new Object[1])));
        }
        if(obj[0] == null)
            synchronized(obj)
            {
                if(obj[0] == null)
                    try
                    {
                        debug("loading properties file " + propertyFileName);
                        obj[0] = doLoad(propertyFileName);
                    }
                    catch(FileNotFoundException fnfe)
                    {
                        info("not found: " + propertyFileName);
                        obj[0] = fnfe;
                    }
                    catch(Exception e)
                    {
                        info("loading " + propertyFileName + " - " + e);
                        obj[0] = e;
                    }
            }
        if(obj[0] instanceof FileNotFoundException)
            throw (FileNotFoundException)obj[0];
        else
            return (Properties)obj[0];
    }

    private static Properties doLoad(String name)
        throws FileNotFoundException
    {
        Properties props;
        java.io.InputStream stream;
        props = new Properties();
        debug("attempting to load as resource using /" + name);
        stream = (com.fitechlabs.xtrade.kernel.util.PropertiesFinder.class).getResourceAsStream("/" + name);
        if(stream == null)
            break MISSING_BLOCK_LABEL_139;
        props.load(stream);
        info("loaded as resource: /" + name);
        return props;
        IOException ioe;
        ioe;
        info("exception loading as resource: /" + name + ", " + ioe);
        int i = 0;
label0:
        do
        {
label1:
            {
                if(i >= m_classPathSearchDirs.length)
                    break label0;
                FileInputStream fis = null;
                try
                {
                    Properties properties;
                    try
                    {
                        File file = new File(m_classPathSearchDirs[i], name);
                        fis = new FileInputStream(file);
                        props.load(fis);
                        debug("--- succeeded");
                        info("loaded: " + file.getAbsolutePath());
                        properties = props;
                    }
                    catch(Exception ignore)
                    {
                        break label1;
                    }
                    return properties;
                }
                finally
                {
                    if(fis != null)
                        try
                        {
                            fis.close();
                        }
                        catch(Exception ignore) { }
                }
            }
            i++;
        } while(true);
        debug("------- not found");
        throw new FileNotFoundException(name + " not found");
    }

    public static Properties getProperties(String propertyFileName, Properties onNotFound)
    {
        return getProperties(propertyFileName);
        FileNotFoundException fe;
        fe;
        debug("Properties file not found: " + propertyFileName);
        return onNotFound;
    }

    public static int getProperty(String fileName, String propName, int defaultValue)
    {
        Properties p = getProperties(fileName, null);
        if(p != null)
        {
            String s = p.getProperty(propName);
            if(s != null)
                return Integer.parseInt(s);
        }
        return defaultValue;
    }

    public static long getProperty(String fileName, String propName, long defaultValue)
    {
        Properties p = getProperties(fileName, null);
        if(p != null)
        {
            String s = p.getProperty(propName);
            if(s != null)
                return Long.parseLong(s);
        }
        return defaultValue;
    }

    public static String getProperty(String fileName, String propName, String defaultValue)
    {
        Properties p = getProperties(fileName, null);
        if(p != null)
        {
            String s = p.getProperty(propName);
            if(s != null)
                return s;
        }
        return defaultValue;
    }

    private static final boolean DEBUG = false;
    private static final String banner = "||||||||||||| PropertiesFileFinder: ";
    private static final String SYSTEM_CLASS_PATH_PROPERTY_NAME = "java.class.path";
    private static final String SYSTEM_PATH_SEP_PROPERTY_NAME = "path.separator";
    private static String m_classPathSearchDirs[] = null;
    private static Map m_cache = new HashMap();

    static 
    {
        debug("initializing");
        String machineName = null;
        try
        {
            machineName = InetAddress.getLocalHost().getHostName();
            debug("hostname is " + machineName);
        }
        catch(Exception e)
        {
            debug("can't degermin host name for this machine, threw " + e);
        }
        String cp = System.getProperty("java.class.path");
        String delim = System.getProperty("path.separator");
        debug("java.class.path is ", cp);
        debug("path.separator  is ", delim);
        debug("default directory is", (new File(".")).getAbsolutePath());
        if(delim == null)
        {
            delim = ";:";
            debug("changing null delimiter to \";:\"");
        }
        StringTokenizer st = new StringTokenizer(cp, delim);
        List dirs = new ArrayList();
        dirs.add(".");
        for(int i = 0; st.hasMoreTokens(); i++)
        {
            String dir = st.nextToken();
            if(machineName != null && dir.endsWith("properties"))
            {
                String machineDir = dir + "/" + machineName;
                debug("adding path " + machineDir);
                dirs.add(machineDir);
            }
            if(!dir.endsWith(".jar"))
            {
                debug("adding path " + dir);
                dirs.add(dir);
            }
        }

        m_classPathSearchDirs = (String[])dirs.toArray(new String[dirs.size()]);
    }
}
