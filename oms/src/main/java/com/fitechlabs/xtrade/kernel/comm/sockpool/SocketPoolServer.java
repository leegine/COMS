// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SocketPoolServer.java

package com.fitechlabs.xtrade.kernel.comm.sockpool;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.io.*;
import java.net.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.sockpool:
//            SocketPoolCallback

public class SocketPoolServer extends Thread
{
    private class WorkerThread extends Thread
    {

        public void run()
        {
            if(SocketPoolServer.DBG)
                SocketPoolServer.log.debug("WorkerThread.run(): " + getName() + " started");
            try
            {
                addWorkerThread(this);
                handle(socket);
            }
            catch(Throwable t)
            {
                if(running)
                    SocketPoolServer.log.warn("run(): caught other throwable - " + t);
            }
            finally
            {
                if(SocketPoolServer.DBG)
                    SocketPoolServer.log.debug("WorkerThread.run(): " + getName() + " ending");
                removeWorkerThread(this);
            }
        }

        private void interruptAndStop()
        {
            running = false;
            synchronized(this)
            {
                interrupt();
            }
        }

        protected void handle(Socket socket)
        {
            ObjectInputStream input;
            ObjectOutputStream output;
            int count;
            input = null;
            output = null;
            count = 0;
            socket.setSoTimeout(SocketPoolServer.SOCKET_TIMEOUT_MILLIS);
            int bufferSize = 1024;
            try
            {
                bufferSize = ServerConfig.getConfigValueAsInt("socket.pool", "io.buffer.size.bytes", bufferSize);
            }
            catch(DataNetworkException e)
            {
                String message = "DataNetworkException thrown when reading configuration from server_config:" + e;
                SocketPoolServer.log.warn(message, e);
            }
            input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream(), bufferSize));
            output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream(), bufferSize));
            output.flush();
_L3:
            Object request;
            long timestampReceivedRequest;
            long timestampRequestHandled;
            boolean increased;
            if(!running || count++ >= MAX_MESSAGES)
                break; /* Loop/switch isn't completed */
            request = input.readObject();
            timestampReceivedRequest = System.currentTimeMillis();
            long timestampReadyToDispatch = -1L;
            timestampRequestHandled = -1L;
            if(SocketPoolServer.PROFILE)
                SocketPoolServer.plog.debug("Request message received on [a].");
            increased = false;
            Object reply;
            increaseCurrentMessagesCount();
            long timestampReadyToDispatch = System.currentTimeMillis();
            if(SocketPoolServer.PROFILE)
                SocketPoolServer.plog.debug("Ready to dispatch request on [b]. [b]-[a]=" + (timestampReadyToDispatch - timestampReceivedRequest) + "ms.");
            increased = true;
            reply = callback.process(request);
            timestampRequestHandled = System.currentTimeMillis();
            if(SocketPoolServer.PROFILE)
                SocketPoolServer.plog.debug("Request handled on [c]. [c]-[b]=" + (timestampRequestHandled - timestampReadyToDispatch) + "ms. [c]-[a]=" + (timestampRequestHandled - timestampReceivedRequest) + "ms.");
            if(increased)
                decreaseCurrentMessagesCount();
              goto _L1
            Throwable t;
            t;
            reply = t;
            if(increased)
                decreaseCurrentMessagesCount();
              goto _L1
            Exception exception;
            exception;
            if(increased)
                decreaseCurrentMessagesCount();
            throw exception;
_L1:
            output.writeObject(reply);
            output.flush();
            long timestampRespSent = System.currentTimeMillis();
            if(SocketPoolServer.PROFILE)
                if(timestampRequestHandled == -1L)
                    SocketPoolServer.plog.debug("Response sent on [d]. [d]-[a]=" + (timestampRespSent - timestampReceivedRequest) + "ms.");
                else
                    SocketPoolServer.plog.debug("Response sent on [d]. [d]-[a]=" + (timestampRespSent - timestampReceivedRequest) + "ms. " + "[d]-[c]=" + (timestampRespSent - timestampRequestHandled) + "ms. ");
            if(true) goto _L3; else goto _L2
_L2:
            break MISSING_BLOCK_LABEL_797;
            InterruptedIOException ie;
            ie;
            if(SocketPoolServer.DBG)
                SocketPoolServer.log.debug("handle():" + ie);
            break MISSING_BLOCK_LABEL_797;
            EOFException ioe;
            ioe;
            if(SocketPoolServer.DBG)
                SocketPoolServer.log.debug("handle():" + ioe);
            break MISSING_BLOCK_LABEL_797;
            SocketException se;
            se;
            if(SocketPoolServer.DBG)
                SocketPoolServer.log.debug("handle():" + se);
            break MISSING_BLOCK_LABEL_797;
            se;
            SocketPoolServer.log.warn("handle(): " + se);
            break MISSING_BLOCK_LABEL_797;
            Throwable t;
            t;
            SocketPoolServer.log.warn("handle() processing: " + t);
            break MISSING_BLOCK_LABEL_797;
            local;
            if(input != null)
                try
                {
                    input.close();
                }
                catch(IOException ioe)
                {
                    SocketPoolServer.log.warn("handle() closing  input: " + ioe);
                }
            if(output != null)
                try
                {
                    output.close();
                }
                catch(IOException ioe)
                {
                    SocketPoolServer.log.warn("handle() closing output: " + ioe);
                }
            if(socket != null)
                try
                {
                    socket.close();
                }
                catch(IOException ioe)
                {
                    SocketPoolServer.log.warn("handle() closing socket: " + ioe);
                }
            JVM INSTR ret 18;
        }

        private boolean running;
        private Socket socket;
        int MAX_MESSAGES;


        private WorkerThread(String name, Socket socket)
        {
            super(name);
            running = true;
            this.socket = null;
            this.socket = socket;
            MAX_MESSAGES = 0x5f5e100;
            try
            {
                MAX_MESSAGES = ServerConfig.getConfigValueAsInt("socket.pool", "server.thread.max.messages", MAX_MESSAGES);
            }
            catch(DataNetworkException e)
            {
                String message = "DataNetworkException thrown when reading configuration from server_config:" + e;
                SocketPoolServer.log.warn(message, e);
            }
            if(SocketPoolServer.DBG)
                SocketPoolServer.log.debug("MAX_MESSAGE of WorkerThread:" + getName() + " is " + MAX_MESSAGES);
        }

    }


    public static SocketPoolServer startServer(int port, SocketPoolCallback callback, int priority, int timeout)
        throws Exception
    {
        SocketPoolServer ss = new SocketPoolServer(port, callback, priority, timeout);
        ss.start();
        return ss;
    }

    public void shutdown()
    {
        interruptAndStop();
    }

    public int getPort()
    {
        return port;
    }

    private SocketPoolServer(int port, SocketPoolCallback callback, int priority, int timeout)
        throws IOException
    {
        super("SocketPool Server");
        queue = new LinkedList();
        running = true;
        this.port = 0;
        this.callback = null;
        address = null;
        workerThreads = Collections.synchronizedSet(new HashSet());
        serverSocket = new ServerSocket(port);
        this.port = port;
        this.callback = callback;
        setDaemon(true);
        setPriority(10);
        WORKER_THREAD_PRIORITY = priority;
        SOCKET_TIMEOUT_MILLIS = timeout;
        address = InetAddress.getLocalHost().getHostName();
        try
        {
            THREADS_SIZE = ServerConfig.getConfigValueAsInt("socket.pool", "server.threads.size", 8);
        }
        catch(DataNetworkException e)
        {
            String message = "Exception thrown when reading configuration from server_config:" + e;
            log.warn(message, e);
        }
        log.info("::: THREADS_SIZE(Maximum no. of simultaneous messages that can be processed) is set as " + THREADS_SIZE);
    }

    public String getAddress()
        throws UnknownHostException
    {
        return address;
    }

    public void run()
    {
        log.info("started at " + address + ":" + port);
        do
        {
            if(!running)
                break;
            try
            {
                Socket socket = serverSocket.accept();
                int id = serial++;
                WorkerThread worker = new WorkerThread("SocketPool-" + id, socket);
                worker.setDaemon(true);
                worker.setPriority(WORKER_THREAD_PRIORITY);
                worker.start();
            }
            catch(Throwable t)
            {
                if(running)
                    log.warn("run(): " + t);
            }
        } while(true);
        log.info("stopped on " + address + ":" + port);
    }

    public void interruptAndStop()
    {
        log.info("will stop");
        running = false;
        synchronized(this)
        {
            interrupt();
            try
            {
                serverSocket.close();
            }
            catch(IOException ioe)
            {
                log.info("server socket closure threw " + ioe);
            }
        }
        WorkerThread t;
        for(Iterator iter = workerThreads.iterator(); iter.hasNext(); t.interruptAndStop())
            t = (WorkerThread)iter.next();

        noOfMessagesBeingProcessed.notifyAll();
        log.info("stopping");
    }

    protected void finalize()
        throws Throwable
    {
        shutdown();
    }

    private void addWorkerThread(WorkerThread thread)
    {
        workerThreads.add(thread);
        if(DBG)
            log.debug("WorkerThread size after adding: " + workerThreads.size());
    }

    private void removeWorkerThread(WorkerThread thread)
    {
        workerThreads.remove(thread);
        if(DBG)
            log.debug("WorkerThread size after removing: " + workerThreads.size());
    }

    private void increaseCurrentMessagesCount()
    {
        try
        {
            THREADS_SIZE = ServerConfig.getConfigValueAsInt("socket.pool", "server.threads.size", THREADS_SIZE);
        }
        catch(DataNetworkException e)
        {
            String message = "DataNetworkException thrown when reading configuration from server_config:" + e;
            log.warn(message, e);
        }
        if(DBG)
            log.debug("Current active WorkerThread(Max simultaneous messages that can be processed) limit size:" + THREADS_SIZE);
        boolean isSuspended = false;
        int ai[] = noOfMessagesBeingProcessed;
        JVM INSTR monitorenter ;
_L1:
        if(noOfMessagesBeingProcessed[0] < THREADS_SIZE || !running)
            break MISSING_BLOCK_LABEL_201;
        isSuspended = true;
        log.info("Thread: " + Thread.currentThread().getName() + " - suspended due to the THREADS_SIZE throttle control. ");
        noOfMessagesBeingProcessed.wait();
          goto _L1
        InterruptedException e;
        e;
        String message = "InterruptedException thrown when WorkerThread:" + Thread.currentThread().getName() + "is waiting for activation:" + e;
        log.warn(message, e);
        noOfMessagesBeingProcessed[0]++;
        break MISSING_BLOCK_LABEL_223;
        Exception exception;
        exception;
        throw exception;
        if(isSuspended)
            log.info("Thread: " + Thread.currentThread().getName() + " - resumed. ");
        if(DBG)
            log.debug("Thread: " + Thread.currentThread().getName() + " activated. Current active threads size:" + noOfMessagesBeingProcessed[0]);
        return;
    }

    private void decreaseCurrentMessagesCount()
    {
        synchronized(noOfMessagesBeingProcessed)
        {
            noOfMessagesBeingProcessed[0]--;
            noOfMessagesBeingProcessed.notify();
        }
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static Logit log;
    private static final boolean DBG;
    private static Logit plog;
    private static final boolean PROFILE;
    private static final int SERVER_THREAD_PRIORITY = 10;
    private static int WORKER_THREAD_PRIORITY = 5;
    private static int SOCKET_TIMEOUT_MILLIS = 60000;
    private static final String CATEG = "socket.pool";
    private static final String THREADS_SIZE_CONFIG_NAME = "server.threads.size";
    private static final int DEFAULT_THREADS_SIZE = 8;
    private static int THREADS_SIZE = 8;
    private static final String MAX_MESSAGES_PER_THREAD_CONFIG_NAME = "server.thread.max.messages";
    private static final int DEFAULT_MAX_MESSAGES_PER_THREAD = 0x5f5e100;
    private ServerSocket serverSocket;
    private LinkedList queue;
    private boolean running;
    private int port;
    private SocketPoolCallback callback;
    private String address;
    private static int serial = 1;
    private Set workerThreads;
    private int noOfMessagesBeingProcessed[] = {
        0
    };

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.comm.sockpool.SocketPoolServer.class);
        DBG = log.ison();
        plog = Logit.getInstance((com.fitechlabs.xtrade.kernel.comm.sockpool.SocketPoolServer.class).getName() + ".profile");
        PROFILE = plog.ison();
    }










}
