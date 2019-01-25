// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeChangeSettleContractOrderMarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeChangeSettleContractOrderMarketRequestMessage

public class DefaultEqTypeChangeSettleContractOrderMarketRequestMessage
    implements EqTypeChangeSettleContractOrderMarketRequestMessage
{

    public DefaultEqTypeChangeSettleContractOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeContractSettleChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        m_subAccount = subAccount;
        m_orderId = orderId;
        m_changeOrderUnitEntries = changeOrderUnitEntries;
    }

    public DefaultEqTypeChangeSettleContractOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeContractSettleChangeOrderUnitEntry changeOrderUnitEntry)
    {
        this(subAccount, orderId, new EqTypeContractSettleChangeOrderUnitEntry[] {
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

    public EqTypeContractSettleChangeOrderUnitEntry[] getChangeOrderUnitEntries()
    {
        return m_changeOrderUnitEntries;
    }

    private final SubAccount m_subAccount;
    private final long m_orderId;
    private final EqTypeContractSettleChangeOrderUnitEntry m_changeOrderUnitEntries[];
}
