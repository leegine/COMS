// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryException.java

package com.fitechlabs.dbind;


// Referenced classes of package com.fitechlabs.dbind:
//            DbException

public class QueryException extends DbException
{

    public QueryException()
    {
    }

    public QueryException(String message)
    {
        super(message);
    }

    public QueryException(String message, String txt)
    {
        super(message, txt);
    }
}
