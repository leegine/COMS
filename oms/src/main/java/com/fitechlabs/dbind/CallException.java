// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CallException.java

package com.fitechlabs.dbind;


// Referenced classes of package com.fitechlabs.dbind:
//            QueryException

public class CallException extends QueryException
{

    public CallException()
    {
    }

    public CallException(String message)
    {
        super(message);
    }

    public CallException(String message, String txt)
    {
        super(message, txt);
    }
}
