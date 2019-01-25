// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeMarketRequestMarketTradedOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            DefaultEqTypeMarketRequestBaseOrderUnitSpec, EqTypeMarketRequestMarketTradedOrderUnitSpec

public class DefaultEqTypeMarketRequestMarketTradedOrderUnitSpec extends DefaultEqTypeMarketRequestBaseOrderUnitSpec
    implements EqTypeMarketRequestMarketTradedOrderUnitSpec
{

    public DefaultEqTypeMarketRequestMarketTradedOrderUnitSpec(long orderUnitId, double qty, double limitPrice, EqTypeTradedProduct tradedProduct, 
            EqTypeExecutionConditionType execType, Date orderExpDate, EqtypeOrderUnitRow dataSourceObj)
    {
        super(orderUnitId, qty, tradedProduct, dataSourceObj);
        m_limitPrice = limitPrice;
        m_execCondType = execType;
        m_expDate = orderExpDate;
    }

    public double getLimitPrice()
    {
        return m_limitPrice;
    }

    public boolean isMarketOrder()
    {
        return getLimitPrice() == 0.0D;
    }

    public EqTypeExecutionConditionType getExecConditionType()
    {
        return m_execCondType;
    }

    public Date getExpirationDate()
    {
        return m_expDate;
    }

    private final double m_limitPrice;
    private final EqTypeExecutionConditionType m_execCondType;
    private final Date m_expDate;
}
