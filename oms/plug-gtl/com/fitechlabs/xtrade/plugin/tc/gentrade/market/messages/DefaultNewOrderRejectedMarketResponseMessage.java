// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultNewOrderRejectedMarketResponseMessage.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages:
//            AbstractMarketResponseMessage, NewOrderRejectedMarketResponseMessage

public class DefaultNewOrderRejectedMarketResponseMessage extends AbstractMarketResponseMessage
    implements NewOrderRejectedMarketResponseMessage
{

    public DefaultNewOrderRejectedMarketResponseMessage(long orderId)
    {
        super(orderId);
    }
}
