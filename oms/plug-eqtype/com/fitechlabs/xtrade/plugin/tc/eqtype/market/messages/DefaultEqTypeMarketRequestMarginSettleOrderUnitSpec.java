// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            DefaultEqTypeMarketRequestMarketTradedOrderUnitSpec, EqTypeMarketRequestMarginSettleOrderUnitSpec

public class DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec extends DefaultEqTypeMarketRequestMarketTradedOrderUnitSpec
    implements EqTypeMarketRequestMarginSettleOrderUnitSpec
{

    public DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec(long orderUnitId, double qty, double limitPrice, EqTypeTradedProduct tradedProduct, 
            boolean isSettlingLong, EqTypeExecutionConditionType execType, Date orderExpDate, EqtypeOrderUnitRow dataSourceObj)
    {
        super(orderUnitId, qty, limitPrice, tradedProduct, execType, orderExpDate, dataSourceObj);
        m_isSettlingLong = isSettlingLong;
    }

    public boolean isSettlingLongContract()
    {
        return m_isSettlingLong;
    }

    public boolean isSettlingShortContract()
    {
        return !m_isSettlingLong;
    }

    private final boolean m_isSettlingLong;
}
