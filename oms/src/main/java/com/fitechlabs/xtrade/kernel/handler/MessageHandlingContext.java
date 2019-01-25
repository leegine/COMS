// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageHandlingContext.java

package com.fitechlabs.xtrade.kernel.handler;


// Referenced classes of package com.fitechlabs.xtrade.kernel.handler:
//            MessageHandlerException

public interface MessageHandlingContext
{

    public abstract String getSessionProperty(String s)
        throws MessageHandlerException;

    public abstract void setSessionProperty(String s, String s1)
        throws MessageHandlerException;

    public abstract long getAccountId()
        throws MessageHandlerException;
}
