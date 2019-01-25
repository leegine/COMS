// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultFillOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages:
//            FillOrderUnitSpec

public class DefaultFillOrderUnitSpec
    implements FillOrderUnitSpec
{

    public DefaultFillOrderUnitSpec(long orderUnitId, double fillQty, double fillPrice)
    {
        m_orderUnitId = orderUnitId;
        m_fillQty = fillQty;
        m_fillPrice = fillPrice;
    }

    public long getOrderUnitId()
    {
        return m_orderUnitId;
    }

    public double getFillQuantity()
    {
        return m_fillQty;
    }

    public double getFillPrice()
    {
        return m_fillPrice;
    }

    private final long m_orderUnitId;
    private final double m_fillQty;
    private final double m_fillPrice;
}
