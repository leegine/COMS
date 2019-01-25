// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClientSideException.java

package com.fitechlabs.xtrade.kernel.comm.client;


// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            ServerException

public class ClientSideException extends ServerException
{

    public ClientSideException(Exception e)
    {
        super("11", "XT-CLIENT_SIDE_EXCEPTION", e.toString());
    }

    private Exception getClientException()
    {
        return e;
    }

    private static final String CLIENT_SIDE_EXCEPTION_CODE = "11";
    private static final String CLIENT_SIDE_EXCEPTION_TAG = "XT-CLIENT_SIDE_EXCEPTION";
    private Exception e;
}
