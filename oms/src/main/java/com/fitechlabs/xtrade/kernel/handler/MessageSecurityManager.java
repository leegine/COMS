// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageSecurityManager.java

package com.fitechlabs.xtrade.kernel.handler;

import com.fitechlabs.xtrade.kernel.message.Message;

// Referenced classes of package com.fitechlabs.xtrade.kernel.handler:
//            MessageHandlerException, MessageHandlingContext

public interface MessageSecurityManager
{

    public abstract boolean allow(Message message)
        throws MessageHandlerException;

    public abstract MessageHandlingContext getHandlingContext()
        throws MessageHandlerException;
}
