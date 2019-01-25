// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeMarketRequestMarginOpenOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            DefaultEqTypeMarketRequestMarketTradedOrderUnitSpec, EqTypeMarketRequestMarginOpenOrderUnitSpec

public class DefaultEqTypeMarketRequestMarginOpenOrderUnitSpec extends DefaultEqTypeMarketRequestMarketTradedOrderUnitSpec
    implements EqTypeMarketRequestMarginOpenOrderUnitSpec
{

    public DefaultEqTypeMarketRequestMarginOpenOrderUnitSpec(long orderUnitId, double qty, double limitPrice, EqTypeTradedProduct tradedProduct, 
            boolean isLong, EqTypeExecutionConditionType execType, Date orderExpDate, EqtypeOrderUnitRow dataSourceObj)
    {
        super(orderUnitId, qty, limitPrice, tradedProduct, execType, orderExpDate, dataSourceObj);
        m_isLong = isLong;
    }

    public boolean isLongOrder()
    {
        return m_isLong;
    }

    public boolean isShortOrder()
    {
        return !m_isLong;
    }

    private final boolean m_isLong;
}
