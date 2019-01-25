// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvWorker.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.xtrade.kernel.util.PropertiesFinder;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.io.*;
import java.net.Socket;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            InvRouter

class InvWorker
    implements Runnable
{

    InvWorker(Socket socket)
    {
        running = true;
        this.socket = null;
        this.socket = socket;
        thread = new Thread(this, "InvWorker-" + serial++);
        thread.setDaemon(true);
        thread.setPriority(7);
    }

    void start()
    {
        thread.start();
    }

    public void run()
    {
        activeThreads.add(this);
        if(DBG)
            log.info(thread.getName() + " - starting");
        try
        {
            handle(socket);
        }
        catch(Throwable t)
        {
            if(running)
                log.warn("run(): caught other throwable - " + t);
        }
        finally
        {
            if(DBG)
                log.info(thread.getName() + " - ending");
            activeThreads.remove(this);
        }
    }

    private void stop()
    {
        running = false;
        synchronized(thread)
        {
            thread.interrupt();
        }
        activeThreads.clear();
    }

    static void stopAll()
    {
        synchronized(activeThreads)
        {
            for(int n = activeThreads.size(); n > 0; n = activeThreads.size())
            {
                InvWorker iw = (InvWorker)activeThreads.remove(n - 1);
                iw.stop();
            }

        }
    }

    protected void handle(Socket socket)
    {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        try
        {
            socket.setSoTimeout(SOCKET_TIMEOUT_MILLIS);
            input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            output.flush();
            while(running) 
            {
                String message = (String)input.readObject();
                String reply;
                try
                {
                    InvRouter.onInvMessage(message);
                    reply = "ok";
                }
                catch(Exception e)
                {
                    reply = "excep: " + e;
                }
                if(reply != null && reply.length() > LONGEST_REPLY_LENGTH)
                    reply = reply.substring(0, LONGEST_REPLY_LENGTH - 3) + "...";
                output.writeObject(reply);
                output.flush();
            }
        }
        catch(InterruptedIOException ie) { }
        catch(EOFException ioe) { }
        catch(IOException ioe)
        {
            log.warn("handle(): " + ioe);
        }
        catch(Throwable t)
        {
            log.warn("handle() processing: " + t);
        }
        finally
        {
            if(input != null)
                try
                {
                    input.close();
                }
                catch(IOException ioe)
                {
                    log.warn("handle() closing  input: " + ioe);
                }
            if(output != null)
                try
                {
                    output.close();
                }
                catch(IOException ioe)
                {
                    log.warn("handle() closing output: " + ioe);
                }
            if(socket != null)
                try
                {
                    socket.close();
                }
                catch(IOException ioe)
                {
                    log.warn("handle() closing socket: " + ioe);
                }
        }
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
    private static List activeThreads = Collections.synchronizedList(new ArrayList());
    private static int serial = 1;
    private boolean running;
    private Socket socket;
    private Thread thread;
    private static final int LONGEST_REPLY_LENGTH = PropertiesFinder.getProperty("dbind.properties", "invalidator.longestReplyLength", 80);
    private static final int SOCKET_TIMEOUT_MILLIS = PropertiesFinder.getProperty("dbind.properties", "invalidator.socketTimeoutMillis", 0x1d4c0);

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.InvWorker.class);
        DBG = log.ison();
    }
}
