// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FindException.java

package com.fitechlabs.dbind;


// Referenced classes of package com.fitechlabs.dbind:
//            SelectException

public class FindException extends SelectException
{

    public FindException()
    {
    }

    public FindException(String message)
    {
        super(message);
    }

    public FindException(String message, String txt)
    {
        super(message, txt);
    }
}
