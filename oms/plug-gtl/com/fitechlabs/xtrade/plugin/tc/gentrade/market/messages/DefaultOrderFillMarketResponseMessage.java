// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultOrderFillMarketResponseMessage.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages:
//            AbstractMarketResponseMessage, FillOrderUnitSpec, OrderFillMarketResponseMessage

public class DefaultOrderFillMarketResponseMessage extends AbstractMarketResponseMessage
    implements OrderFillMarketResponseMessage
{

    public DefaultOrderFillMarketResponseMessage(long orderId, FillOrderUnitSpec fillOrderUnitSpec)
    {
        this(orderId, new FillOrderUnitSpec[] {
            fillOrderUnitSpec
        });
    }

    public DefaultOrderFillMarketResponseMessage(long orderId, FillOrderUnitSpec fillOrderUnitSpecs[])
    {
        super(orderId);
        m_fillOrderUnitSpecs = fillOrderUnitSpecs;
    }

    public FillOrderUnitSpec[] getFillOrderUnitSpecs()
    {
        return m_fillOrderUnitSpecs;
    }

    private final FillOrderUnitSpec m_fillOrderUnitSpecs[];
}
