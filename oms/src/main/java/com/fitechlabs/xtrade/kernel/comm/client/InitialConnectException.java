// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InitialConnectException.java

package com.fitechlabs.xtrade.kernel.comm.client;


// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            CommunicationException

public class InitialConnectException extends CommunicationException
{

    public InitialConnectException()
    {
    }

    public InitialConnectException(String message)
    {
        super(message);
    }

    public InitialConnectException(String message, Throwable rootThrowable)
    {
        super(message, rootThrowable);
    }
}
