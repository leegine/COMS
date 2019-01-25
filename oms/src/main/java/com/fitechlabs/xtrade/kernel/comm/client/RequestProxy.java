// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestProxy.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.Request;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            CommunicationException, ServerException, ServerAccessor

public abstract class RequestProxy
{

    private RequestProxy()
    {
    }

    static RequestProxy getStringStringRequestProxy()
    {
        return stringStringInstance;
    }

    static RequestProxy getStringObjectRequestProxy()
    {
        return stringObjectInstance;
    }

    static RequestProxy getObjectObjectRequestProxy()
    {
        return objectObjectInstance;
    }

    abstract Object doRequest(ServerAccessor serveraccessor, Object obj)
        throws CommunicationException, ServerException;


    private static final RequestProxy stringObjectInstance = new RequestProxy() {

        public Object doRequest(ServerAccessor accessor, Object xmlRequest)
            throws CommunicationException, ServerException
        {
            return accessor.doRequestO((String)xmlRequest);
        }

    }
;
    private static final RequestProxy objectObjectInstance = new RequestProxy() {

        public Object doRequest(ServerAccessor accessor, Object xmlRequest)
            throws CommunicationException, ServerException
        {
            return accessor.doRequest((Request)xmlRequest);
        }

    }
;
    private static final RequestProxy stringStringInstance = new RequestProxy() {

        public Object doRequest(ServerAccessor accessor, Object xmlRequest)
            throws CommunicationException, ServerException
        {
            return accessor.doRequest((String)xmlRequest);
        }

    }
;

}
