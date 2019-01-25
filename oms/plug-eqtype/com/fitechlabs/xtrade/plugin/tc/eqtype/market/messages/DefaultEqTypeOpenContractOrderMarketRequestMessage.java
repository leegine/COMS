// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeOpenContractOrderMarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeMarketRequestMarginOpenOrderUnitSpec, EqTypeOpenContractOrderMarketRequestMessage

public class DefaultEqTypeOpenContractOrderMarketRequestMessage
    implements EqTypeOpenContractOrderMarketRequestMessage
{

    public DefaultEqTypeOpenContractOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeMarketRequestMarginOpenOrderUnitSpec orderUnitSpecs[])
    {
        m_subAccount = subAccount;
        m_orderId = orderId;
        m_orderUnitSpecs = orderUnitSpecs;
    }

    public DefaultEqTypeOpenContractOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeMarketRequestMarginOpenOrderUnitSpec orderUnitSpec)
    {
        this(subAccount, orderId, new EqTypeMarketRequestMarginOpenOrderUnitSpec[] {
            orderUnitSpec
        });
    }

    public long getOrderId()
    {
        return m_orderId;
    }

    public EqTypeMarketRequestMarginOpenOrderUnitSpec[] getOrderUnitSpecs()
    {
        return m_orderUnitSpecs;
    }

    public SubAccount getSubAccount()
    {
        return m_subAccount;
    }

    private final SubAccount m_subAccount;
    private final long m_orderId;
    private final EqTypeMarketRequestMarginOpenOrderUnitSpec m_orderUnitSpecs[];
}
