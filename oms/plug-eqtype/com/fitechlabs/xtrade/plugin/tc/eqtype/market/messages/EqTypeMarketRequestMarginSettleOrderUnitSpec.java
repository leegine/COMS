// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketRequestMarginSettleOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeMarketRequestMarketTradedOrderUnitSpec

public interface EqTypeMarketRequestMarginSettleOrderUnitSpec
    extends EqTypeMarketRequestMarketTradedOrderUnitSpec
{

    public abstract boolean isSettlingLongContract();

    public abstract boolean isSettlingShortContract();
}
