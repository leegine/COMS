// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeMarketRequestMarginSwapOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            DefaultEqTypeMarketRequestBaseOrderUnitSpec, EqTypeMarketRequestMarginSwapOrderUnitSpec

public class DefaultEqTypeMarketRequestMarginSwapOrderUnitSpec extends DefaultEqTypeMarketRequestBaseOrderUnitSpec
    implements EqTypeMarketRequestMarginSwapOrderUnitSpec
{

    public DefaultEqTypeMarketRequestMarginSwapOrderUnitSpec(long orderUnitId, double qty, EqTypeTradedProduct tradedProduct, boolean isSwappingLong, EqtypeOrderUnitRow dataSourceObj)
    {
        super(orderUnitId, qty, tradedProduct, dataSourceObj);
        m_isSwappingLong = isSwappingLong;
    }

    public boolean isSwappingLongContract()
    {
        return m_isSwappingLong;
    }

    public boolean isSwappingShortContract()
    {
        return !m_isSwappingLong;
    }

    private final boolean m_isSwappingLong;
}
