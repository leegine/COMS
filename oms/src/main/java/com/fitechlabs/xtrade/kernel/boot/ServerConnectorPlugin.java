// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerConnectorPlugin.java

package com.fitechlabs.xtrade.kernel.boot;

import com.fitechlabs.xtrade.kernel.comm.client.*;
import com.fitechlabs.xtrade.kernel.handler.client.ClientAccessorRegistry;
import com.fitechlabs.xtrade.kernel.handler.client.ServerAccessorFactory;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

// Referenced classes of package com.fitechlabs.xtrade.kernel.boot:
//            Plugin, ServerAccessorConfig

public class ServerConnectorPlugin extends Plugin
{
    private static class DynamicClusterServerAccessorFactory
        implements ServerAccessorFactory
    {

        public ServerAccessor createAccessor(ServerAccessor initialAccessor)
        {
            return new DynamicReloadAccessor("static-cluster", initialAccessor, ServerAccessorConfig.getConfigValueRefreshEverySeconds(), ServerAccessorConfig.getConfigValueRefreshRetrySeconds());
        }

        private DynamicClusterServerAccessorFactory()
        {
        }

    }

    private static class StaticClusterServerAccessorFactory
        implements ServerAccessorFactory
    {

        public ServerAccessor createAccessor(ServerAccessor initialAccessor)
        {
            String urls[] = ServerAccessorConfig.getConfigValueClusterUrls();
            if(urls == null || urls.length == 0)
            {
                ServerConnectorPlugin.log.warn("server_config table contains no values in category 'cluster.urls', returning supplied initial accessor instead, value=" + initialAccessor);
                return initialAccessor;
            }
            AffinityAccessor affinityAccessor = new AffinityAccessor("session_id", urls, (long)ServerAccessorConfig.getConfigValueRetryAfterSeconds() * 1000L);
            try
            {
                ServerAccessor accessors[] = affinityAccessor.getServerAccessors();
                for(int i = 0; i < accessors.length; i++)
                {
                    ServerAccessor accessor = accessors[i];
                    if(accessor instanceof SocketPoolAccessor)
                    {
                        SocketPoolAccessor socketPoolAccessor = (SocketPoolAccessor)accessor;
                        socketPoolAccessor.setMaxSize(ServerAccessorConfig.getConfigValueSocketPoolClientMaxSize());
                        socketPoolAccessor.setTimeOut(ServerAccessorConfig.getConfigValueSocketPoolTimeOut());
                        socketPoolAccessor.setBufferSize(ServerAccessorConfig.getConfigValueSocketPoolBufferSize());
                    }
                }

            }
            catch(Exception e)
            {
                ServerConnectorPlugin.log.warn("Can not config individual SocketPoolAccessor of an AffinityAccessor due to Exception: " + e);
            }
            return affinityAccessor;
        }

        private StaticClusterServerAccessorFactory()
        {
        }

    }


    private ServerConnectorPlugin()
    {
    }

    public static void plug()
        throws Exception
    {
        Plugin.plug(com.fitechlabs.xtrade.kernel.boot.ServerConnectorPlugin.class);
    }

    public static void onPlug()
        throws Exception
    {
        Plugin.regClass(com.fitechlabs.xtrade.kernel.message.client.ClientAccessorRequest.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.message.client.ClientAccessorResponse.class);
        Plugin.regHandler(com.fitechlabs.xtrade.kernel.message.client.ClientAccessorRequest.class, com.fitechlabs.xtrade.kernel.handler.client.ClientAccessorRequestHandler.class, "handleRequest");
        ClientAccessorRegistry.registerClientAccessor("static-cluster", new StaticClusterServerAccessorFactory());
        ClientAccessorRegistry.registerClientAccessor("dynamic-cluster", new DynamicClusterServerAccessorFactory());
    }

    private static final Logit log;
    private static final boolean DBG;
    private static final String RETRY_ORDER_TAG_NAME = "session_id";

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.boot.ServerConnectorPlugin.class);
        DBG = log.ison();
    }

}
