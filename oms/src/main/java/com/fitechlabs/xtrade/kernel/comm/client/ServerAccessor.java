// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerAccessor.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import java.io.Serializable;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            CommunicationException, ServerException

public interface ServerAccessor
    extends Serializable
{

    public abstract Response doRequestO(String s)
        throws CommunicationException, ServerException;

    public abstract Response doRequest(Request request)
        throws CommunicationException, ServerException;

    public abstract String doRequest(String s)
        throws CommunicationException, ServerException;

    public static final long serialVersionUID = 1L;
}
