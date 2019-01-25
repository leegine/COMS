// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeChangeSettleContractOrderSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io:
//            EqTypeContractSettleChangeOrderUnitEntry

public class EqTypeChangeSettleContractOrderSpec extends ChangeOrderSpec
{

    public EqTypeChangeSettleContractOrderSpec(long orderId, EqTypeContractSettleChangeOrderUnitEntry entries[])
    {
        super(orderId);
        m_entries = entries;
    }

    public EqTypeChangeSettleContractOrderSpec(long orderId, EqTypeContractSettleChangeOrderUnitEntry entry)
    {
        this(orderId, new EqTypeContractSettleChangeOrderUnitEntry[] {
            entry
        });
    }

    public EqTypeContractSettleChangeOrderUnitEntry[] getChangeOrderUnitEntries()
    {
        return m_entries;
    }

    private final EqTypeContractSettleChangeOrderUnitEntry m_entries[];
}
