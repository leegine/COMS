// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarketAdapter.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            NotInstalledException, AlreadyInstalledException, MarketRequestSenderService, MarketResponseReceiverCallbackService

public interface MarketAdapter
{

    public abstract MarketRequestSenderService getMarketRequestSenderServce()
        throws NotInstalledException;

    public abstract MarketRequestSenderService getOriginalMarketRequestSenderService()
        throws NotInstalledException;

    public abstract MarketResponseReceiverCallbackService getMarketResponseReceiverCallbackService();

    public abstract void installMarketRequestSenderService(MarketRequestSenderService marketrequestsenderservice)
        throws AlreadyInstalledException;

    public abstract void setThreadLocalMarketRequestSenderServiceProxy(MarketRequestSenderService marketrequestsenderservice);
}
