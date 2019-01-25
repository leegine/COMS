// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeChangeSwapContractOrderSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io:
//            EqTypeContractSwapChangeOrderUnitEntry

public class EqTypeChangeSwapContractOrderSpec extends ChangeOrderSpec
{

    public EqTypeChangeSwapContractOrderSpec(long orderId, EqTypeContractSwapChangeOrderUnitEntry entries[])
    {
        super(orderId);
        m_entries = entries;
    }

    public EqTypeChangeSwapContractOrderSpec(long orderId, EqTypeContractSwapChangeOrderUnitEntry entry)
    {
        this(orderId, new EqTypeContractSwapChangeOrderUnitEntry[] {
            entry
        });
    }

    public EqTypeContractSwapChangeOrderUnitEntry[] getChangeOrderUnitEntries()
    {
        return m_entries;
    }

    private final EqTypeContractSwapChangeOrderUnitEntry m_entries[];
}
