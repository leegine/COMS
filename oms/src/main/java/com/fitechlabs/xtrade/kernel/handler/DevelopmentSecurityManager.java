// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DevelopmentSecurityManager.java

package com.fitechlabs.xtrade.kernel.handler;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.handler:
//            MessageSecurityManager, MessageHandlerException, MessageHandlingContext

public class DevelopmentSecurityManager
    implements MessageSecurityManager
{
    private class DevelopmentHandlingContext
        implements MessageHandlingContext
    {

        public String getSessionProperty(String name)
            throws MessageHandlerException
        {
            return (String)map.get(name);
        }

        public void setSessionProperty(String name, String value)
            throws MessageHandlerException
        {
            map.put(name, value);
        }

        public long getAccountId()
            throws MessageHandlerException
        {
            return accountId;
        }

        private Map map;

        private DevelopmentHandlingContext()
        {
            map = new HashMap();
        }

    }


    public DevelopmentSecurityManager()
    {
        accountId = 1L;
        context = new DevelopmentHandlingContext();
    }

    public void setAccountId(long accountId)
    {
        this.accountId = accountId;
    }

    public boolean allow(Message message)
        throws MessageHandlerException
    {
        return true;
    }

    public MessageHandlingContext getHandlingContext()
        throws MessageHandlerException
    {
        return context;
    }

    private long accountId;
    private DevelopmentHandlingContext context;

}
