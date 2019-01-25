// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeNewOrderSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import java.util.Date;

public class EqTypeNewOrderSpec extends NewOrderSpec
{

    protected EqTypeNewOrderSpec(Trader trader, TaxTypeEnum taxType, EqTypeExecutionConditionType execType, Date orderExpDate)
    {
        super(trader, orderExpDate);
        m_taxType = taxType;
        m_execType = execType;
    }

    public TaxTypeEnum getTaxType()
    {
        return m_taxType;
    }

    public EqTypeExecutionConditionType getExecConditionType()
    {
        return m_execType;
    }

    private final TaxTypeEnum m_taxType;
    private final EqTypeExecutionConditionType m_execType;
}
