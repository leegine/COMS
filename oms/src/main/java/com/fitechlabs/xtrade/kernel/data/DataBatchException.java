// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataBatchException.java

package com.fitechlabs.xtrade.kernel.data;


// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            DataQueryException

public class DataBatchException extends DataQueryException
{

    public DataBatchException(String message, DataQueryException queryException, Object partialResults[], int successCount)
    {
        super(message);
        this.queryException = queryException;
        this.partialResults = partialResults;
        this.successCount = successCount;
    }

    public DataQueryException getQueryException()
    {
        return queryException;
    }

    public Object[] getPartialResults()
    {
        return partialResults;
    }

    public int getSuccessCount()
    {
        return successCount;
    }

    private DataQueryException queryException;
    private Object partialResults[];
    private int successCount;
}
