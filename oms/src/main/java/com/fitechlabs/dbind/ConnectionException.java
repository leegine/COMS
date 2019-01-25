// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnectionException.java

package com.fitechlabs.dbind;


// Referenced classes of package com.fitechlabs.dbind:
//            DbException

public class ConnectionException extends DbException
{

    public ConnectionException()
    {
    }

    public ConnectionException(String message)
    {
        super(message);
    }

    public ConnectionException(String message, String txt)
    {
        super(message, txt);
    }
}
