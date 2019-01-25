// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeChangeOrderMarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeChangeOrderMarketRequestMessage

public class DefaultEqTypeChangeOrderMarketRequestMessage
    implements EqTypeChangeOrderMarketRequestMessage
{

    public DefaultEqTypeChangeOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        m_subAccount = subAccount;
        m_orderId = orderId;
        m_changeOrderUnitEntries = changeOrderUnitEntries;
    }

    public DefaultEqTypeChangeOrderMarketRequestMessage(SubAccount subAccount, long orderId, EqTypeChangeOrderUnitEntry changeOrderUnitEntry)
    {
        this(subAccount, orderId, new EqTypeChangeOrderUnitEntry[] {
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

    public EqTypeChangeOrderUnitEntry[] getChangeOrderUnitEntries()
    {
        return m_changeOrderUnitEntries;
    }

    private final SubAccount m_subAccount;
    private final long m_orderId;
    private final EqTypeChangeOrderUnitEntry m_changeOrderUnitEntries[];
}
