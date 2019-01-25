// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultUndoOrderFillMarketResponseMessage.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages:
//            AbstractMarketResponseMessage, UndoOrderFillMarketResponseMessage

public class DefaultUndoOrderFillMarketResponseMessage extends AbstractMarketResponseMessage
    implements UndoOrderFillMarketResponseMessage
{

    public DefaultUndoOrderFillMarketResponseMessage(long orderId, long orderExecutionId)
    {
        super(orderId);
        m_orderExecutionId = orderExecutionId;
    }

    public long getOrderExecutionId()
    {
        return m_orderExecutionId;
    }

    private final long m_orderExecutionId;
}
