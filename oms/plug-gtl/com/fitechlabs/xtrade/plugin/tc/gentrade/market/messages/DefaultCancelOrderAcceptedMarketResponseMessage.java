// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultCancelOrderAcceptedMarketResponseMessage.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages:
//            AbstractMarketResponseMessage, CancelOrderAcceptedMarketResponseMessage

public class DefaultCancelOrderAcceptedMarketResponseMessage extends AbstractMarketResponseMessage
    implements CancelOrderAcceptedMarketResponseMessage
{

    public DefaultCancelOrderAcceptedMarketResponseMessage(long orderId)
    {
        super(orderId);
    }
}
