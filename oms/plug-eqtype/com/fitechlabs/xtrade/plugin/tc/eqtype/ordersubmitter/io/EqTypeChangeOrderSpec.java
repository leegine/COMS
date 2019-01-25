// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeChangeOrderSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io:
//            EqTypeBaseChangeOrderSpec, EqTypeChangeOrderUnitEntry

public class EqTypeChangeOrderSpec extends EqTypeBaseChangeOrderSpec
{

    public EqTypeChangeOrderSpec(long orderId, EqTypeChangeOrderUnitEntry entries[])
    {
        super(orderId, entries);
    }

    public EqTypeChangeOrderSpec(long orderId, EqTypeChangeOrderUnitEntry entry)
    {
        super(orderId, entry);
    }

    public EqTypeChangeOrderSpec(long orderId, long orderUnitId, double afterChangeOrigQuantity, double afterChangePrice)
    {
        super(orderId, orderUnitId, afterChangeOrigQuantity, afterChangePrice);
    }
}
