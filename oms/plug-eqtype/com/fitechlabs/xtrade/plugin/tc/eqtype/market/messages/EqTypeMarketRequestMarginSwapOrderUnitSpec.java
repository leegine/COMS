// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketRequestMarginSwapOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeMarketRequestBaseOrderUnitSpec

public interface EqTypeMarketRequestMarginSwapOrderUnitSpec
    extends EqTypeMarketRequestBaseOrderUnitSpec
{

    public abstract boolean isSwappingLongContract();

    public abstract boolean isSwappingShortContract();
}
