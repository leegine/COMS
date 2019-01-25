// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeSettleContractOrderMarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeMarketRequestMarginSettleOrderUnitSpec, EqTypeSettleContractOrderMarketRequestMessage

public class DefaultEqTypeSettleContractOrderMarketRequestMessage
    implements EqTypeSettleContractOrderMarketRequestMessage
{

    public DefaultEqTypeSettleContractOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeMarketRequestMarginSettleOrderUnitSpec orderUnitSpecs[])
    {
        m_subAccount = subAccount;
        m_orderId = orderId;
        m_orderUnitSpecs = orderUnitSpecs;
    }

    public DefaultEqTypeSettleContractOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeMarketRequestMarginSettleOrderUnitSpec orderUnitSpec)
    {
        this(subAccount, orderId, new EqTypeMarketRequestMarginSettleOrderUnitSpec[] {
            orderUnitSpec
        });
    }

    public long getOrderId()
    {
        return m_orderId;
    }

    public EqTypeMarketRequestMarginSettleOrderUnitSpec[] getOrderUnitSpecs()
    {
        return m_orderUnitSpecs;
    }

    public SubAccount getSubAccount()
    {
        return m_subAccount;
    }

    private final SubAccount m_subAccount;
    private final long m_orderId;
    private final EqTypeMarketRequestMarginSettleOrderUnitSpec m_orderUnitSpecs[];
}
