// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectException.java

package com.fitechlabs.dbind;


// Referenced classes of package com.fitechlabs.dbind:
//            QueryException

public class SelectException extends QueryException
{

    public SelectException()
    {
    }

    public SelectException(String message)
    {
        super(message);
    }

    public SelectException(String message, String txt)
    {
        super(message, txt);
    }
}
