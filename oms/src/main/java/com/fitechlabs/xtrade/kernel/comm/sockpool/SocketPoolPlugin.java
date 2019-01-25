// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SocketPoolPlugin.java

package com.fitechlabs.xtrade.kernel.comm.sockpool;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.comm.XmlRequestHandler;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.SocketPoolAccessor;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfigPlugin;
import com.fitechlabs.xtrade.kernel.handler.client.ClientAccessorRegistry;
import com.fitechlabs.xtrade.kernel.handler.client.ServerAccessorFactory;
import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.sockpool:
//            SocketPoolServer, SocketPoolCallback

public class SocketPoolPlugin extends Plugin
{
    private static class Callback
        implements SocketPoolCallback
    {

        public Object process(Object message)
        {
            Object m[] = (Object[])message;
            String returnType = (String)m[0];
            Object request = m[1];
            if(request instanceof String)
            {
                String xmlRequest = (String)request;
                if("S".equals(returnType))
                    return XmlRequestHandler.handleRequest(xmlRequest);
                else
                    return XmlRequestHandler.handleRequestO(xmlRequest);
            }
            Request requestObj = (Request)request;
            if("S".equals(returnType))
                throw new UnsupportedOperationException("Object->String request handling not yet implemented");
            else
                return XmlRequestHandler.handleRequest(requestObj);
        }

        private Callback()
        {
        }

    }


    public static int getPort()
    {
        return port;
    }

    public static void assignPort(int toBeSet)
    {
        if(toBeSet <= 0)
            throw new IllegalArgumentException("Invalid port number.");
        if(port != 0)
        {
            throw new IllegalStateException("It is too late to assign port. Already use " + port + ".");
        } else
        {
            assignedPort = toBeSet;
            log.info("SocketPoolPlugin is configured to use port " + assignedPort + ".");
            return;
        }
    }

    private SocketPoolPlugin()
    {
    }

    public static void plug()
        throws Exception
    {
        Plugin.plug(com.fitechlabs.xtrade.kernel.comm.sockpool.SocketPoolPlugin.class);
    }

    public static void onPlug()
        throws Exception
    {
        ServerConfigPlugin.plug();
        int portMin;
        int portMax;
        if(assignedPort > 0)
        {
            portMin = portMax = assignedPort;
        } else
        {
            int defaultPortMin = ServerConfig.getConfigValueAsInt("socket.pool", "server.port", 2999);
            int defaultPortMax = ServerConfig.getConfigValueAsInt("socket.pool", "server.port", 2999);
            portMin = ServerConfig.getConfigValueAsInt("socket.pool", "server.port.min", defaultPortMin);
            portMax = ServerConfig.getConfigValueAsInt("socket.pool", "server.port.max", defaultPortMax);
        }
        int priority = ServerConfig.getConfigValueAsInt("socket.pool", "thread.priority", 5);
        final int timeout = ServerConfig.getConfigValueAsInt("socket.pool", "socket.timeout", 60000);
        Callback callback = new Callback();
        int p = portMin;
        do
        {
            if(p > portMax)
                throw new RuntimeException("No available server ports in range " + portMin + "-" + portMax);
            try
            {
                server = SocketPoolServer.startServer(p, callback, priority, timeout);
                break;
            }
            catch(Exception e)
            {
                p++;
            }
        } while(true);
        port = p;
        final String address = server.getAddress();
        ClientAccessorRegistry.registerClientAccessor("socket-pool", new ServerAccessorFactory() {

            public ServerAccessor createAccessor(ServerAccessor requestorAccessor)
            {
                int maxsize = 4;
                try
                {
                    maxsize = ServerConfig.getConfigValueAsInt("socket.pool", "client.max.size", maxsize);
                }
                catch(DataNetworkException e)
                {
                    SocketPoolPlugin.log.warn("network failed while using socket pool server accessor factory", e);
                }
                int bufferSize = 1024;
                try
                {
                    bufferSize = ServerConfig.getConfigValueAsInt("socket.pool", "io.buffer.size.bytes", bufferSize);
                }
                catch(DataNetworkException e)
                {
                    SocketPoolPlugin.log.warn("network failed while using socket pool server accessor factory", e);
                }
                SocketPoolPlugin.log.info("SocketPoolAccessor is configured with address=" + address + ",port=" + SocketPoolPlugin.port + ",timeout=" + timeout + ",maxsize=" + maxsize + ",bufferSize=" + bufferSize + ".");
                SocketPoolAccessor accessor = new SocketPoolAccessor(address, SocketPoolPlugin.port, timeout);
                accessor.setMaxSize(maxsize);
                accessor.setBufferSize(bufferSize);
                return accessor;
            }

        }
);
    }

    public static void onUnplug()
        throws Exception
    {
        server.shutdown();
    }

    private static final Logit log;
    public static final String CATEG = "socket.pool";
    public static final String SERVER_PORT = "server.port";
    public static final String SERVER_PORT_MIN = "server.port.min";
    public static final String SERVER_PORT_MAX = "server.port.max";
    public static final String THREAD_PRIORITY = "thread.priority";
    public static final String SOCKET_TIMEOUT = "socket.timeout";
    public static final String MAX_SIZE = "client.max.size";
    public static final String BUFFER_SIZE = "io.buffer.size.bytes";
    public static final int DEFAULT_PORT_MIN = 2999;
    public static final int DEFAULT_PORT_MAX = 2999;
    public static final int DEFAULT_PRIORITY = 5;
    public static final int DEFAULT_TIMEOUT = 60000;
    public static final int DEFAULT_MAX_SIZE = 4;
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    public static final String CONFIG_NAME_SOCKET_POOL = "socket-pool";
    private static SocketPoolServer server;
    private static int port = 0;
    private static int assignedPort = -1;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.comm.sockpool.SocketPoolPlugin.class);
    }


}
