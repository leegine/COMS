// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeNewCashBasedOrderMarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeMarketRequestCashBasedOrderUnitSpec, EqTypeNewCashBasedOrderMarketRequestMessage

public class DefaultEqTypeNewCashBasedOrderMarketRequestMessage
    implements EqTypeNewCashBasedOrderMarketRequestMessage
{

    public DefaultEqTypeNewCashBasedOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeMarketRequestCashBasedOrderUnitSpec orderUnitSpecs[])
    {
        m_subAccount = subAccount;
        m_orderId = orderId;
        m_orderUnitSpecs = orderUnitSpecs;
    }

    public DefaultEqTypeNewCashBasedOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeMarketRequestCashBasedOrderUnitSpec orderUnitSpec)
    {
        this(subAccount, orderId, new EqTypeMarketRequestCashBasedOrderUnitSpec[] {
            orderUnitSpec
        });
    }

    public long getOrderId()
    {
        return m_orderId;
    }

    public EqTypeMarketRequestCashBasedOrderUnitSpec[] getOrderUnitSpecs()
    {
        return m_orderUnitSpecs;
    }

    public SubAccount getSubAccount()
    {
        return m_subAccount;
    }

    private final SubAccount m_subAccount;
    private final long m_orderId;
    private final EqTypeMarketRequestCashBasedOrderUnitSpec m_orderUnitSpecs[];
}
