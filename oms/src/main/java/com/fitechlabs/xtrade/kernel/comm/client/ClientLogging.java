// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClientLogging.java

package com.fitechlabs.xtrade.kernel.comm.client;

import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            ClientLog

public final class ClientLogging
{
    private static class DefaultClientLog
        implements ClientLog
    {

        private void out(String level, String message)
        {
            System.err.println((new Timestamp((new Date()).getTime())).toString() + " - (" + Thread.currentThread().getName() + ") - ClientLogging." + level + " - " + message);
        }

        public boolean ison()
        {
            return true;
        }

        public void debug(String message)
        {
            if(isDebug)
                out("DEBUG", message);
        }

        public void debug(String message, Throwable throwable)
        {
            if(isDebug)
                out("DEBUG", message + ", " + throwable);
        }

        public void info(String message)
        {
            out("INFO", message);
        }

        public void info(String message, Throwable throwable)
        {
            out("INFO", message + ", " + throwable);
        }

        public void warn(String message)
        {
            out("WARN", message);
        }

        public void warn(String message, Throwable throwable)
        {
            out("WARN", message);
            throwable.printStackTrace();
        }

        static boolean isDebug = "on".equalsIgnoreCase(System.getProperty("xtrade.client.debug"));


        private DefaultClientLog()
        {
        }

    }


    private ClientLogging()
    {
    }

    public static void setClientLog(ClientLog log)
    {
        log = log;
    }

    public static boolean ison()
    {
        ClientLog theLog = log;
        if(theLog != null)
            return theLog.ison();
        else
            return false;
    }

    public static void debug(String message)
    {
        ClientLog theLog = log;
        if(theLog != null && theLog.ison())
            theLog.debug(message);
    }

    public static void debug(String message, Throwable throwable)
    {
        ClientLog theLog = log;
        if(theLog != null && theLog.ison())
            theLog.debug(message, throwable);
    }

    public static void info(String message)
    {
        ClientLog theLog = log;
        if(theLog != null)
            theLog.info(message);
    }

    public static void info(String message, Throwable throwable)
    {
        ClientLog theLog = log;
        if(theLog != null)
            theLog.info(message, throwable);
    }

    public static void warn(String message)
    {
        ClientLog theLog = log;
        if(theLog != null)
            theLog.warn(message);
    }

    public static void warn(String message, Throwable throwable)
    {
        ClientLog theLog = log;
        if(theLog != null)
            theLog.warn(message, throwable);
    }

    private static ClientLog log = new DefaultClientLog();

}
