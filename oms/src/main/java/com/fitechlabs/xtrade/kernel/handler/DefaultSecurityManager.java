// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultSecurityManager.java

package com.fitechlabs.xtrade.kernel.handler;

import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.handler:
//            MessageSecurityManager, MessageHandlerException, MessageHandlingContext

public class DefaultSecurityManager
    implements MessageSecurityManager
{
    private static class DefaultHandlingContext
        implements MessageHandlingContext
    {

        public String getSessionProperty(String name)
            throws MessageHandlerException
        {
            DefaultSecurityManager.log.warn("using default security manager - setting property within session common to all accounts and local to this node");
            return (String)map.get(name);
        }

        public void setSessionProperty(String name, String value)
            throws MessageHandlerException
        {
            DefaultSecurityManager.log.warn("using default security manager - getting property from session common to all accounts and local to this node");
            map.put(name, value);
        }

        public long getAccountId()
            throws MessageHandlerException
        {
            DefaultSecurityManager.log.warn("using default security manager - returning DefaultSecurityManager.accountId. (default is 0)");
            return DefaultSecurityManager.accountId;
        }

        private Map map;

        private DefaultHandlingContext()
        {
            map = new HashMap();
        }

    }


    public static void setAccountId(long accountId)
    {
        accountId = accountId;
    }

    DefaultSecurityManager()
    {
    }

    public boolean allow(Message message)
        throws MessageHandlerException
    {
        log.warn("using default security manager - allowing all messages to pass");
        return true;
    }

    public MessageHandlingContext getHandlingContext()
        throws MessageHandlerException
    {
        return theDefaultHandlingContext;
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit log;
    private static long accountId = 0L;
    private static final MessageHandlingContext theDefaultHandlingContext = new DefaultHandlingContext();

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.handler.DefaultSecurityManager.class);
    }


}
