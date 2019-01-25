// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RemoteAccessorBase.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            RemoteAccessor

public class RemoteAccessorBase extends UnicastRemoteObject
    implements RemoteAccessor
{

    public RemoteAccessorBase()
        throws RemoteException
    {
    }

    public Response doRequestO(String xmlRequest)
        throws RemoteException
    {
        throw new UnsupportedOperationException("doRequestO() is a subclass responsibility");
    }

    public Response doRequest(Request xmlRequest)
        throws RemoteException
    {
        throw new UnsupportedOperationException("doRequest() is a subclass responsibility");
    }
}
