// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClientAccessorResponse.java

package com.fitechlabs.xtrade.kernel.message.client;

import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.message.Response;

public class ClientAccessorResponse extends Response
{

    public ClientAccessorResponse()
    {
    }

    public static final long serialVersionUID = 1L;
    public static final String TAGNAME = "response";
    public static final String PTYPE = "client_accessor";
    public ServerAccessor accessor;
}
