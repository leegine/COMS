// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeMarketRequestCashBasedOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            DefaultEqTypeMarketRequestMarketTradedOrderUnitSpec, EqTypeMarketRequestCashBasedOrderUnitSpec

public class DefaultEqTypeMarketRequestCashBasedOrderUnitSpec extends DefaultEqTypeMarketRequestMarketTradedOrderUnitSpec
    implements EqTypeMarketRequestCashBasedOrderUnitSpec
{

    public DefaultEqTypeMarketRequestCashBasedOrderUnitSpec(long orderUnitId, double qty, double limitPrice, EqTypeTradedProduct tradedProduct, 
            boolean isBuy, EqTypeExecutionConditionType execType, Date orderExpDate, EqtypeOrderUnitRow dataSourceObj)
    {
        super(orderUnitId, qty, limitPrice, tradedProduct, execType, orderExpDate, dataSourceObj);
        m_isBuy = isBuy;
    }

    public boolean isBuyOrder()
    {
        return m_isBuy;
    }

    public boolean isSellOrder()
    {
        return !m_isBuy;
    }

    private final boolean m_isBuy;
}
