// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeNewCashBasedOrderSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io:
//            EqTypeNewOrderSpec

public class EqTypeNewCashBasedOrderSpec extends EqTypeNewOrderSpec
{

    protected EqTypeNewCashBasedOrderSpec(Trader trader, boolean isBuyOrder, String productCode, String marketCode, double quantity, double price, EqTypeExecutionConditionType execType, Date orderExpDate, TaxTypeEnum taxType)
    {
        super(trader, taxType, execType, orderExpDate);
        m_isBuyOrder = isBuyOrder;
        m_marketCode = marketCode;
        m_price = price;
        m_productCode = productCode;
        m_quantity = quantity;
    }

    public static EqTypeNewCashBasedOrderSpec createMarketOrderSpec(Trader trader, boolean isBuyOrder, String productCode, String marketCode, double quantity, EqTypeExecutionConditionType execType, Date orderExpDate, 
            TaxTypeEnum taxType)
    {
        return new EqTypeNewCashBasedOrderSpec(trader, isBuyOrder, productCode, marketCode, quantity, 0.0D, execType, orderExpDate, taxType);
    }

    public static EqTypeNewCashBasedOrderSpec createLimitOrderSpec(Trader trader, boolean isBuyOrder, String productCode, String marketCode, double quantity, double price, 
            EqTypeExecutionConditionType execType, Date orderExpDate, TaxTypeEnum taxType)
    {
        return new EqTypeNewCashBasedOrderSpec(trader, isBuyOrder, productCode, marketCode, quantity, price, execType, orderExpDate, taxType);
    }

    public boolean isBuyOrder()
    {
        return m_isBuyOrder;
    }

    public boolean isSellOrder()
    {
        return !m_isBuyOrder;
    }

    public boolean isMarketOrder()
    {
        return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(m_price);
    }

    public boolean isLimitOrder()
    {
        return !com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(m_price);
    }

    public double getLimitPrice()
    {
        return m_price;
    }

    public double getQuantity()
    {
        return m_quantity;
    }

    public String getMarketCode()
    {
        return m_marketCode;
    }

    public String getProductCode()
    {
        return m_productCode;
    }

    private final String m_productCode;
    private final String m_marketCode;
    private final double m_quantity;
    private final double m_price;
    private final boolean m_isBuyOrder;
}
