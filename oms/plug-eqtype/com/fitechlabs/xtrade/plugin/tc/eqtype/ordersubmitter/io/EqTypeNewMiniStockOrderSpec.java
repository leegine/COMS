// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeNewMiniStockOrderSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io:
//            EqTypeNewOrderSpec

public class EqTypeNewMiniStockOrderSpec extends EqTypeNewOrderSpec
{

    public EqTypeNewMiniStockOrderSpec(Trader trader, boolean isBuyOrder, String productCode, String marketCode, double quantity, TaxTypeEnum taxType)
    {
        super(trader, taxType, null, NewOrderSpec.MAX_ORDER_EXP_DATE);
        m_isBuyOrder = isBuyOrder;
        m_marketCode = marketCode;
        m_productCode = productCode;
        m_quantity = quantity;
    }

    public boolean isBuyOrder()
    {
        return m_isBuyOrder;
    }

    public boolean isSellOrder()
    {
        return !m_isBuyOrder;
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
    private final boolean m_isBuyOrder;
}
