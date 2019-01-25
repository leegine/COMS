// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseMarketAdapterImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

public abstract class BaseMarketAdapterImpl
    implements MarketAdapter
{

    protected BaseMarketAdapterImpl(MarketResponseReceiverCallbackService responseCallbackService)
    {
        m_senderService = null;
        m_responseCallbackService = responseCallbackService;
    }

    public MarketRequestSenderService getMarketRequestSenderServce()
        throws NotInstalledException
    {
        MarketRequestSenderService proxyService = (MarketRequestSenderService)m_senderServiceProxies.get();
        if(proxyService != null)
        {
            if(DBG)
                m_log.debug("MarketRequestSenderService proxy is installed. Routing the request thru proxy");
            return proxyService;
        } else
        {
            return getOriginalMarketRequestSenderService();
        }
    }

    public MarketRequestSenderService getOriginalMarketRequestSenderService()
        throws NotInstalledException
    {
        if(m_senderService == null)
            throw new NotInstalledException("MarketSenderService is not yet installed.");
        else
            return m_senderService;
    }

    public MarketResponseReceiverCallbackService getMarketResponseReceiverCallbackService()
    {
        return m_responseCallbackService;
    }

    public void installMarketRequestSenderService(MarketRequestSenderService sender)
        throws AlreadyInstalledException
    {
        if(m_senderService == null)
        {
            m_log.info("Installing MarketSenderService :" + sender.getClass());
            m_senderService = sender;
        } else
        {
            m_log.error("MarketSenderService is already installed.");
            throw new AlreadyInstalledException("MarketSenderService Already installed");
        }
    }

    public void setThreadLocalMarketRequestSenderServiceProxy(MarketRequestSenderService senderServiceProxy)
    {
        m_senderServiceProxies.set(senderServiceProxy);
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private final ThreadLocal m_senderServiceProxies = new ThreadLocal();
    private MarketRequestSenderService m_senderService;
    private MarketResponseReceiverCallbackService m_responseCallbackService;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BaseMarketAdapterImpl.class);
        DBG = m_log.ison();
    }
}
