// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeSwapContractOrderMarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeMarketRequestMarginSwapOrderUnitSpec, EqTypeSwapContractOrderMarketRequestMessage

public class DefaultEqTypeSwapContractOrderMarketRequestMessage
    implements EqTypeSwapContractOrderMarketRequestMessage
{

    public DefaultEqTypeSwapContractOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeMarketRequestMarginSwapOrderUnitSpec orderUnitSpecs[])
    {
        m_subAccount = subAccount;
        m_orderId = orderId;
        m_orderUnitSpecs = orderUnitSpecs;
    }

    public DefaultEqTypeSwapContractOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeMarketRequestMarginSwapOrderUnitSpec orderUnitSpec)
    {
        this(subAccount, orderId, new EqTypeMarketRequestMarginSwapOrderUnitSpec[] {
            orderUnitSpec
        });
    }

    public long getOrderId()
    {
        return m_orderId;
    }

    public SubAccount getSubAccount()
    {
        return m_subAccount;
    }

    public EqTypeMarketRequestMarginSwapOrderUnitSpec[] getOrderUnitSpecs()
    {
        return m_orderUnitSpecs;
    }

    private final SubAccount m_subAccount;
    private final long m_orderId;
    private final EqTypeMarketRequestMarginSwapOrderUnitSpec m_orderUnitSpecs[];
}
