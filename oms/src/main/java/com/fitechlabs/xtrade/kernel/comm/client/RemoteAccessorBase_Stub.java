// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RemoteAccessorBase_Stub.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import java.lang.reflect.Method;
import java.rmi.*;
import java.rmi.server.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            RemoteAccessor

public final class RemoteAccessorBase_Stub extends RemoteStub
    implements RemoteAccessor, Remote
{

    public RemoteAccessorBase_Stub(RemoteRef ref)
    {
        super(ref);
    }

    public Response doRequest(Request $param_Request_1)
        throws RemoteException
    {
        Object $result = super.ref.invoke(this, $method_doRequest_0, new Object[] {
            $param_Request_1
        }, 0xded3ae225ee71fd6L);
        return (Response)$result;
        RuntimeException e;
        e;
        throw e;
        e;
        throw e;
        e;
        throw new UnexpectedException("undeclared checked exception", e);
    }

    public Response doRequestO(String $param_String_1)
        throws RemoteException
    {
        Object $result = super.ref.invoke(this, $method_doRequestO_1, new Object[] {
            $param_String_1
        }, 0x2bbe17f8008cc090L);
        return (Response)$result;
        RuntimeException e;
        e;
        throw e;
        e;
        throw e;
        e;
        throw new UnexpectedException("undeclared checked exception", e);
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final long serialVersionUID = 2L;
    private static Method $method_doRequest_0;
    private static Method $method_doRequestO_1;

    static 
    {
        try
        {
            $method_doRequest_0 = (com.fitechlabs.xtrade.kernel.comm.client.RemoteAccessor.class).getMethod("doRequest", new Class[] {
                com.fitechlabs.xtrade.kernel.message.Request.class
            });
            $method_doRequestO_1 = (com.fitechlabs.xtrade.kernel.comm.client.RemoteAccessor.class).getMethod("doRequestO", new Class[] {
                java.lang.String.class
            });
        }
        catch(NoSuchMethodException e)
        {
            throw new NoSuchMethodError("stub class initialization failed");
        }
    }
}
