// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RemoteAccessor.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteAccessor
    extends Remote
{

    public abstract Response doRequestO(String s)
        throws RemoteException;

    public abstract Response doRequest(Request request)
        throws RemoteException;
}
