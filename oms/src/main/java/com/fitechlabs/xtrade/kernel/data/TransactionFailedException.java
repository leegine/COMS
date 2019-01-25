// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransactionFailedException.java

package com.fitechlabs.xtrade.kernel.data;


// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            DataNetworkException

public class TransactionFailedException extends DataNetworkException
{

    public TransactionFailedException()
    {
    }

    public TransactionFailedException(String message)
    {
        super(message);
    }

    public TransactionFailedException(String message, Object details)
    {
        super(message);
        this.details = details;
    }

    public Object getDetails()
    {
        return details;
    }

    private Object details;
}
