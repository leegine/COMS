// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultChangeOrderRejectedMarketResponseMessage.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages:
//            AbstractMarketResponseMessage, ChangeOrderRejectedMarketResponseMessage

public class DefaultChangeOrderRejectedMarketResponseMessage extends AbstractMarketResponseMessage
    implements ChangeOrderRejectedMarketResponseMessage
{

    public DefaultChangeOrderRejectedMarketResponseMessage(long orderId)
    {
        super(orderId);
    }
}
