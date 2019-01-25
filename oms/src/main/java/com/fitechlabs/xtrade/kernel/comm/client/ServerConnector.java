// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerConnector.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.client.ClientAccessorRequest;
import com.fitechlabs.xtrade.kernel.message.client.ClientAccessorResponse;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            FailoverAccessor, CommunicationException, ServerException, ClientSideException, 
//            ServerAccessor, ClientLogging

public final class ServerConnector
{

    private ServerConnector()
    {
    }

    public static ServerAccessor createAccessor(String config_name, String config_urls[])
        throws CommunicationException, ServerException
    {
        ServerAccessor bootAccessor = new FailoverAccessor(config_urls);
        return createAccessor(config_name, bootAccessor);
    }

    public static ServerAccessor createAccessor(String config_name, ServerAccessor bootAccessor)
        throws CommunicationException, ServerException
    {
        ClientAccessorResponse caResponse;
        ClientAccessorRequest request = new ClientAccessorRequest();
        request.config_name = config_name;
        request.requestor_accessor = bootAccessor;
        com.fitechlabs.xtrade.kernel.message.Response response = bootAccessor.doRequest(request);
        caResponse = (ClientAccessorResponse)response;
        return caResponse.accessor;
        CommunicationException ce;
        ce;
        throw ce;
        ServerException se;
        se;
        throw se;
        Exception excep;
        excep;
        ClientLogging.warn("ServerAccessor.createAccessor client side exception", excep);
        throw new ClientSideException(excep);
    }

    public static final String DYNAMIC_CLUSTER = "dynamic-cluster";
    public static final String STATIC_CLUSTER = "static-cluster";
}
