// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultCancelOrderMarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages:
//            CancelOrderMarketRequestMessage

public class DefaultCancelOrderMarketRequestMessage
    implements CancelOrderMarketRequestMessage
{

    public DefaultCancelOrderMarketRequestMessage(SubAccount subAccount, long orderId)
    {
        m_subAccount = subAccount;
        m_orderId = orderId;
    }

    public long getOrderId()
    {
        return m_orderId;
    }

    public SubAccount getSubAccount()
    {
        return m_subAccount;
    }

    private final SubAccount m_subAccount;
    private final long m_orderId;
}
