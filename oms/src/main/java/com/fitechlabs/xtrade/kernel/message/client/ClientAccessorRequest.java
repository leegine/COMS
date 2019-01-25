// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClientAccessorRequest.java

package com.fitechlabs.xtrade.kernel.message.client;

import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.message.Request;

public class ClientAccessorRequest extends Request
{

    public ClientAccessorRequest()
    {
    }

    public static final long serialVersionUID = 1L;
    public static final String TAGNAME = "request";
    public static final String PTYPE = "client_accessor";
    public String config_name;
    public ServerAccessor requestor_accessor;
}
