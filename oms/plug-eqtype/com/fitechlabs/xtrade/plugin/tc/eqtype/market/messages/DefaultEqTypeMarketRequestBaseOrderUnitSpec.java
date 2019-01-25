// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeMarketRequestBaseOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeMarketRequestBaseOrderUnitSpec

public class DefaultEqTypeMarketRequestBaseOrderUnitSpec
    implements EqTypeMarketRequestBaseOrderUnitSpec
{

    public DefaultEqTypeMarketRequestBaseOrderUnitSpec(long orderUnitId, double qty, EqTypeTradedProduct tradedProduct, EqtypeOrderUnitRow dataSourceObj)
    {
        m_orderUnitId = orderUnitId;
        m_qty = qty;
        m_tradedProduct = tradedProduct;
        m_dataSourceObject = dataSourceObj;
    }

    public long getOrderUnitId()
    {
        return m_orderUnitId;
    }

    public double getQuantity()
    {
        return m_qty;
    }

    public EqTypeTradedProduct getTradedProduct()
    {
        return m_tradedProduct;
    }

    public Object getDataSourceObject()
    {
        return m_dataSourceObject;
    }

    private final long m_orderUnitId;
    private final double m_qty;
    private final EqTypeTradedProduct m_tradedProduct;
    private final EqtypeOrderUnitRow m_dataSourceObject;
}
