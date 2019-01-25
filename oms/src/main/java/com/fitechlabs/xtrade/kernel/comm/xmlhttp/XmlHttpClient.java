// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlHttpClient.java

package com.fitechlabs.xtrade.kernel.comm.xmlhttp;

import com.fitechlabs.xtrade.kernel.comm.client.HttpServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import java.io.*;

public class XmlHttpClient
{

    public XmlHttpClient()
    {
    }

    private static void out(String s)
    {
        System.out.println(s);
    }

    private static void debug(String s, Object o)
    {
        if(DEBUG)
            debug(s + o);
    }

    protected static void debug(String s)
    {
        if(DEBUG)
            out("XmlHttpClient.DEBUG: " + s);
    }

    protected static void info(String s)
    {
        out("XmlHttpClient.INFO:  " + s);
    }

    protected static void warn(String s)
    {
        out("XmlHttpClient.WARN:  " + s);
    }

    public static void main(String arg[])
    {
        try
        {
            if(arg.length < 2)
            {
                System.out.println("\nusage:");
                System.out.println("   java com.fitechlabs.xtrade.kernel.comm.xmlhttp.XmlHttpClient filename url");
                System.out.println("\narguments:");
                System.out.println("   filename  - an xml file to send to the server. ");
                System.out.println("   url       - the server url. ");
                System.out.println();
                System.exit(0);
            }
            DEBUG = true;
            String infile = arg[0];
            String url = arg[1];
            String spacer = "--------------------------------";
            info(spacer + " filename: " + infile);
            info(spacer + " url:      " + url);
            ServerAccessor accessor = new HttpServerAccessor(url);
            info(spacer + " accessor: " + accessor);
            byte request[] = getBytes(new FileInputStream(infile));
            String requestString = new String(request, "UTF8");
            info(spacer + " request:\n" + requestString);
            String responseString = accessor.doRequest(requestString);
            info(spacer + " response:\n" + responseString);
            info(spacer + " (done)");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static byte[] getBytes(InputStream input)
        throws Exception
    {
        byte buffer[] = new byte[1024];
        ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
        int count;
        while((count = input.read(buffer)) > 0) 
            output.write(buffer, 0, count);
        return output.toByteArray();
    }

    private static boolean DEBUG = false;

}
