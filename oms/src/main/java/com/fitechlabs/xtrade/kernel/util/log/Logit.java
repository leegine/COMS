// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Logit.java

package com.fitechlabs.xtrade.kernel.util.log;

import com.fitechlabs.xtrade.kernel.util.PropertiesFinder;
import java.io.*;
import java.util.*;
import org.apache.log4j.*;
import org.apache.log4j.helpers.LogLog;

public final class Logit
{
    private static class CustomPriorities extends Priority
    {

        private static final int LOG_INT = 60000;
        private static final String LOG = "LOG";
        private static Priority LOG_PRIORITY;

        static 
        {
            LOG_PRIORITY = new CustomPriorities(60000, "LOG", Priority.INFO.getSyslogEquivalent());
        }


        private CustomPriorities(int level, String name, int sysLogEquivalent)
        {
            super(level, name, sysLogEquivalent);
        }
    }


    public static Logit getInstance(Class clazz)
    {
        return new Logit(clazz);
    }

    public static Logit getInstance(String packageName)
    {
        return new Logit(packageName);
    }

    private Logit(Class clazz)
    {
        isOn = false;
        currentCategories = new Hashtable();
        baseName = clazz.getName();
        int i = baseName.lastIndexOf(".");
        packageCat = i >= 0 ? Category.getInstance(baseName.substring(0, i)) : root;
        baseCat = Category.getInstance(clazz);
        isOn = baseCat.isDebugEnabled();
    }

    private Logit(String packageName)
    {
        isOn = false;
        currentCategories = new Hashtable();
        baseName = packageName;
        packageCat = Category.getInstance(baseName);
        baseCat = packageCat;
        isOn = baseCat.isDebugEnabled();
    }

    private static void msg(String s)
    {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Logit: " + s);
    }

    private static void assignEncoding()
    {
        Enumeration e = root.getAllAppenders();
        do
        {
            if(!e.hasMoreElements())
                break;
            Appender app = (Appender)e.nextElement();
            try
            {
                if(app instanceof ConsoleAppender)
                {
                    ConsoleAppender cApp = (ConsoleAppender)app;
                    PrintStream stream = cApp.getTarget().equals("System.err") ? System.err : System.out;
                    cApp.setWriter(new OutputStreamWriter(stream, encoding));
                } else
                if(app.getClass().isAssignableFrom(org.apache.log4j.FileAppender.class))
                {
                    FileAppender fApp = (FileAppender)app;
                    fApp.setWriter(new OutputStreamWriter(new FileOutputStream(fApp.getFile(), fApp.getAppend()), encoding));
                }
            }
            catch(UnsupportedEncodingException ex)
            {
                msg("Encoding '" + encoding + "' is not supported by " + app.getClass().getName() + "! Using system default!");
            }
            catch(FileNotFoundException ex)
            {
                LogLog.error("Should never happen!", ex);
            }
        } while(true);
    }

    public final void error(String message)
    {
        getCurrentCategory().error(message);
    }

    public final void error(Object message)
    {
        getCurrentCategory().error(message);
    }

    public final void error(String message, Throwable t)
    {
        getCurrentCategory().error(message, t);
    }

    public final void warn(String message)
    {
        getCurrentCategory().warn(message);
    }

    public final void warn(Object message)
    {
        getCurrentCategory().warn(message);
    }

    public final void warn(String message, Throwable t)
    {
        getCurrentCategory().warn(message, t);
    }

    public final void info(String message)
    {
        getCurrentCategory().info(message);
    }

    public final void info(String message, Throwable t)
    {
        getCurrentCategory().info(message, t);
    }

    public final void info(Object message)
    {
        getCurrentCategory().info(message);
    }

    public final void debug(String message)
    {
        getCurrentCategory().debug(message);
    }

    public final void debug(String message, Throwable t)
    {
        getCurrentCategory().debug(message, t);
    }

    public final void debug(Object message)
    {
        getCurrentCategory().debug(message);
    }

    public final void log(String message)
    {
        if(accessLogPrinting)
            getCurrentCategory().log(CustomPriorities.LOG_PRIORITY, message);
    }

    public void log(Category cat, String message)
    {
        if(accessLogPrinting)
            cat.log(CustomPriorities.LOG_PRIORITY, message);
    }

    public boolean ison()
    {
        return isOn;
    }

    public boolean ison(String subCategory)
    {
        Category cat = Category.exists(baseName + "." + subCategory);
        return cat == null ? false : cat.isDebugEnabled();
    }

    public void set(String subCategory)
    {
        StringBuffer buf = new StringBuffer(50);
        buf.append(baseName);
        buf.append(".");
        buf.append(subCategory);
        currentCategories.put(Thread.currentThread(), Category.getInstance(buf.toString()));
    }

    public void setPackage()
    {
        currentCategories.put(Thread.currentThread(), packageCat);
    }

    public void unset()
    {
        currentCategories.put(Thread.currentThread(), null);
    }

    private Category getCurrentCategory()
    {
        Category cat = (Category)currentCategories.get(Thread.currentThread());
        return cat != null ? cat : baseCat;
    }

    private static final String PROPERTIES_FILE_NAME = "log.properties";
    private static final String banner = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Logit: ";
    private static final String DOT = ".";
    private static boolean accessLogPrinting = true;
    private static boolean useDefaultEncoding;
    private static String encoding = "SJIS";
    private static final Category root;
    private final Category packageCat;
    private final Category baseCat;
    private final String baseName;
    private boolean isOn;
    private final Map currentCategories;

    static 
    {
        useDefaultEncoding = true;
        root = Category.getRoot();
        msg("starting, will look for log.properties");
        try
        {
            Properties logProp = PropertiesFinder.getProperties("log.properties");
            String catFileName;
            if((catFileName = logProp.getProperty("CategoryFile")) != null)
            {
                Properties catProp = PropertiesFinder.getProperties(catFileName);
                String hackNamePrepend = "log4j.category.";
                String name;
                for(Enumeration e = catProp.propertyNames(); e.hasMoreElements(); logProp.setProperty(hackNamePrepend + name, catProp.getProperty(name)))
                    name = (String)e.nextElement();

            }
            PropertyConfigurator.configure(logProp);
            accessLogPrinting = Boolean.valueOf(logProp.getProperty("accessLogPrinting", "true")).booleanValue();
            useDefaultEncoding = Boolean.valueOf(logProp.getProperty("useDefaultEncoding", "true")).booleanValue();
            encoding = logProp.getProperty("encoding", "SJIS");
            if(!useDefaultEncoding)
                assignEncoding();
        }
        catch(FileNotFoundException ex)
        {
            msg("!!! NO LOG FILE FOUND, USING System.out !!!");
            ConsoleAppender app = new ConsoleAppender();
            app.setTarget("System.out");
            app.setLayout(new PatternLayout("%d{ISO8601} - %t - %-5p - %c - %m%n"));
            app.setWriter(new OutputStreamWriter(System.out));
            BasicConfigurator.configure(app);
            root.setPriority(Priority.INFO);
        }
    }
}
