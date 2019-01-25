// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeNewCashBasedOrderMarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeOrderBaseMarketRequestMessage, EqTypeMarketRequestCashBasedOrderUnitSpec

public interface EqTypeNewCashBasedOrderMarketRequestMessage
    extends EqTypeOrderBaseMarketRequestMessage
{

    public abstract long getOrderId();

    public abstract EqTypeMarketRequestCashBasedOrderUnitSpec[] getOrderUnitSpecs();
}
