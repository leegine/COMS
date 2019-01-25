// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataQueryException.java

package com.fitechlabs.xtrade.kernel.data;


// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            DataException

public class DataQueryException extends DataException
{

    public DataQueryException()
    {
    }

    public DataQueryException(String message)
    {
        super(message);
    }

    public DataQueryException(String message, String detail)
    {
        this(message);
        this.detail = detail;
    }

    public String getDetail()
    {
        return detail;
    }

    protected String detail;
}
