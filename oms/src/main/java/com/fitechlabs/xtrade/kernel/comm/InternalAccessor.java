// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InternalAccessor.java

package com.fitechlabs.xtrade.kernel.comm;

import com.fitechlabs.xtrade.kernel.comm.client.CommunicationException;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerException;
import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm:
//            XmlRequestHandler

public class InternalAccessor
    implements ServerAccessor
{

    public InternalAccessor()
    {
    }

    public Response doRequestO(String request)
        throws ServerException, CommunicationException
    {
        return XmlRequestHandler.handleRequestO(request);
    }

    public Response doRequest(Request request)
        throws ServerException, CommunicationException
    {
        return XmlRequestHandler.handleRequest(request);
    }

    public String doRequest(String request)
        throws ServerException, CommunicationException
    {
        return XmlRequestHandler.handleRequest(request);
    }

    public String doRequestFromFile(String requestFileName)
    {
        return XmlRequestHandler.handleRequestFile(requestFileName);
    }
}
