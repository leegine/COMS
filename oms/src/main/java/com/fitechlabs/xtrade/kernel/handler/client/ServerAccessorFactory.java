// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerAccessorFactory.java

package com.fitechlabs.xtrade.kernel.handler.client;

import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;

public interface ServerAccessorFactory
{

    public abstract ServerAccessor createAccessor(ServerAccessor serveraccessor);
}
