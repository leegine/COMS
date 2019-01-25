// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeBaseChangeOrderSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io:
//            EqTypeChangeOrderUnitEntry

public abstract class EqTypeBaseChangeOrderSpec extends ChangeOrderSpec
{

    public EqTypeBaseChangeOrderSpec(long orderId, EqTypeChangeOrderUnitEntry entries[])
    {
        super(orderId);
        m_entries = entries;
    }

    public EqTypeBaseChangeOrderSpec(long orderId, EqTypeChangeOrderUnitEntry entry)
    {
        this(orderId, new EqTypeChangeOrderUnitEntry[] {
            entry
        });
    }

    public EqTypeBaseChangeOrderSpec(long orderId, long orderUnitId, double afterChangeOrigQuantity, double afterChangePrice)
    {
        this(orderId, new EqTypeChangeOrderUnitEntry(orderUnitId, afterChangeOrigQuantity, afterChangePrice));
    }

    public EqTypeChangeOrderUnitEntry[] getChangeOrderUnitEntries()
    {
        return m_entries;
    }

    private final EqTypeChangeOrderUnitEntry m_entries[];
}
