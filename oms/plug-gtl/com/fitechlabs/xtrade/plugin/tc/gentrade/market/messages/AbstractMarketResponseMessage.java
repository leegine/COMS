// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractMarketResponseMessage.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages:
//            MarketResponseMessage

public abstract class AbstractMarketResponseMessage
    implements MarketResponseMessage
{

    public AbstractMarketResponseMessage(long orderId)
    {
        m_orderId = orderId;
    }

    public long getOrderId()
    {
        return m_orderId;
    }

    private final long m_orderId;
}
