// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataCallbackException.java

package com.fitechlabs.xtrade.kernel.data;


// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            DataQueryException

public class DataCallbackException extends DataQueryException
{

    public DataCallbackException()
    {
    }

    public DataCallbackException(String message)
    {
        super(message);
    }

    public DataCallbackException(String message, Object details)
    {
        super(message);
        this.details = details;
    }

    public Object getDetails()
    {
        return details;
    }

    public String toString()
    {
        if(details != null)
            return super.toString() + ", details=" + details;
        else
            return super.toString();
    }

    private Object details;
}
