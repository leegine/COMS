// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataRollbackException.java

package com.fitechlabs.xtrade.kernel.data;


// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            DataNetworkException

public class DataRollbackException extends DataNetworkException
{

    private DataRollbackException()
    {
    }

    public DataRollbackException(String message, Throwable reason)
    {
        super(message);
        this.reason = reason;
    }

    public Throwable getReason()
    {
        return reason;
    }

    public String toString()
    {
        if(reason != null)
            return super.toString() + ", reason=" + reason;
        else
            return super.toString();
    }

    private Throwable reason;
}
