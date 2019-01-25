// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeChangeSwapContractOrderMarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSwapChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeChangeSwapContractOrderMarketRequestMessage

public class DefaultEqTypeChangeSwapContractOrderMarketRequestMessage
    implements EqTypeChangeSwapContractOrderMarketRequestMessage
{

    public DefaultEqTypeChangeSwapContractOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeContractSwapChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        m_subAccount = subAccount;
        m_orderId = orderId;
        m_changeOrderUnitEntries = changeOrderUnitEntries;
    }

    public DefaultEqTypeChangeSwapContractOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeContractSwapChangeOrderUnitEntry changeOrderUnitEntry)
    {
        this(subAccount, orderId, new EqTypeContractSwapChangeOrderUnitEntry[] {
            changeOrderUnitEntry
        });
    }

    public SubAccount getSubAccount()
    {
        return m_subAccount;
    }

    public long getOrderId()
    {
        return m_orderId;
    }

    public EqTypeContractSwapChangeOrderUnitEntry[] getChangeOrderUnitEntries()
    {
        return m_changeOrderUnitEntries;
    }

    private final SubAccount m_subAccount;
    private final long m_orderId;
    private final EqTypeContractSwapChangeOrderUnitEntry m_changeOrderUnitEntries[];
}
