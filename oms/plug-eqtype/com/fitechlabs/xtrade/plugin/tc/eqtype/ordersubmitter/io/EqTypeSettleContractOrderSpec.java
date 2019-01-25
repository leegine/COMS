// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeSettleContractOrderSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io:
//            EqTypeNewOrderSpec, EqTypeSettleContractOrderEntry

public class EqTypeSettleContractOrderSpec extends EqTypeNewOrderSpec
{

    protected EqTypeSettleContractOrderSpec(Trader trader, EqTypeSettleContractOrderEntry entries[], double price, EqTypeExecutionConditionType execType, Date orderExpDate, TaxTypeEnum taxType)
    {
        super(trader, taxType, execType, orderExpDate);
        m_price = price;
        m_entries = entries;
    }

    public static EqTypeSettleContractOrderSpec createMarketOrderSpec(Trader trader, EqTypeSettleContractOrderEntry entries[], EqTypeExecutionConditionType execType, Date orderExpDate, TaxTypeEnum taxType)
    {
        return new EqTypeSettleContractOrderSpec(trader, entries, 0.0D, execType, orderExpDate, taxType);
    }

    public static EqTypeSettleContractOrderSpec createLimitOrderSpec(Trader trader, EqTypeSettleContractOrderEntry entries[], double price, EqTypeExecutionConditionType execType, Date orderExpDate, TaxTypeEnum taxType)
    {
        return new EqTypeSettleContractOrderSpec(trader, entries, price, execType, orderExpDate, taxType);
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

    public double getTotalQuantity()
    {
        if(m_totalQty == null)
        {
            double totalQty = 0.0D;
            for(int i = 0; i < m_entries.length; i++)
                totalQty += m_entries[i].getQuantity();

            m_totalQty = new Double(totalQty);
        }
        return m_totalQty.doubleValue();
    }

    public EqTypeSettleContractOrderEntry[] getSettleContractOrderEntries()
    {
        return m_entries;
    }

    private final EqTypeSettleContractOrderEntry m_entries[];
    private final double m_price;
    private Double m_totalQty;
}
