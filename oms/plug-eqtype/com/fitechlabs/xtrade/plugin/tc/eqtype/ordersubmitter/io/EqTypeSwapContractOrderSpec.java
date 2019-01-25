// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeSwapContractOrderSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io:
//            EqTypeSettleContractOrderEntry

public class EqTypeSwapContractOrderSpec extends NewOrderSpec
{

    protected EqTypeSwapContractOrderSpec(Trader trader, EqTypeSettleContractOrderEntry entries[], TaxTypeEnum taxType)
    {
        super(trader, MAX_ORDER_EXP_DATE);
        m_taxType = taxType;
        m_entries = entries;
    }

    public static EqTypeSwapContractOrderSpec create(Trader trader, EqTypeSettleContractOrderEntry entries[], TaxTypeEnum taxType)
    {
        return new EqTypeSwapContractOrderSpec(trader, entries, taxType);
    }

    public TaxTypeEnum getTaxType()
    {
        return m_taxType;
    }

    public EqTypeSettleContractOrderEntry[] getSettleContractOrderEntries()
    {
        return m_entries;
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

    private final EqTypeSettleContractOrderEntry m_entries[];
    private Double m_totalQty;
    private final TaxTypeEnum m_taxType;
}
