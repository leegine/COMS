// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteException.java

package com.fitechlabs.dbind;


// Referenced classes of package com.fitechlabs.dbind:
//            QueryException

public class DeleteException extends QueryException
{

    public DeleteException()
    {
    }

    public DeleteException(String message)
    {
        super(message);
    }

    public DeleteException(String message, String txt)
    {
        super(message, txt);
    }
}
