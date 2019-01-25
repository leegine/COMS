// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommunicationException.java

package com.fitechlabs.xtrade.kernel.comm.client;


// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            AccessorException

public class CommunicationException extends AccessorException
{

    public CommunicationException()
    {
    }

    public CommunicationException(String message)
    {
        super(message);
    }

    public CommunicationException(String message, Throwable rootThrowable)
    {
        super(message);
        this.rootThrowable = rootThrowable;
    }

    public Throwable getRootThrowable()
    {
        return rootThrowable;
    }

    private Throwable rootThrowable;
}
