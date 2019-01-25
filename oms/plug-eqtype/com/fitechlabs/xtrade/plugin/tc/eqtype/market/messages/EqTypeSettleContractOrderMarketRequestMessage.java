// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeSettleContractOrderMarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeOrderBaseMarketRequestMessage, EqTypeMarketRequestMarginSettleOrderUnitSpec

public interface EqTypeSettleContractOrderMarketRequestMessage
    extends EqTypeOrderBaseMarketRequestMessage
{

    public abstract long getOrderId();

    public abstract EqTypeMarketRequestMarginSettleOrderUnitSpec[] getOrderUnitSpecs();
}
