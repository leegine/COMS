// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClientAccessorRequestHandler.java

package com.fitechlabs.xtrade.kernel.handler.client;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.message.client.ClientAccessorRequest;
import com.fitechlabs.xtrade.kernel.message.client.ClientAccessorResponse;
import com.fitechlabs.xtrade.kernel.util.ObjectPrettyPrinter;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

// Referenced classes of package com.fitechlabs.xtrade.kernel.handler.client:
//            ClientAccessorRegistry, ServerAccessorFactory

public class ClientAccessorRequestHandler
    implements MessageHandler
{

    public ClientAccessorRequestHandler()
    {
    }

    public Response handleRequest(ClientAccessorRequest request)
        throws Exception
    {
        if(DBG)
            log.info("request: " + ObjectPrettyPrinter.toString(request));
        ClientAccessorResponse response = new ClientAccessorResponse();
        ServerAccessorFactory factory = ClientAccessorRegistry.getAccessorFactory(request.config_name);
        if(factory != null)
            response.accessor = factory.createAccessor(request.requestor_accessor);
        else
        if("loopback".equals(request.config_name))
            response.accessor = request.requestor_accessor;
        if(DBG)
            log.info("response: " + ObjectPrettyPrinter.toString(response));
        return response;
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static Logit log;
    private static boolean DBG;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.handler.client.ClientAccessorRequestHandler.class);
        DBG = log.ison();
    }
}
