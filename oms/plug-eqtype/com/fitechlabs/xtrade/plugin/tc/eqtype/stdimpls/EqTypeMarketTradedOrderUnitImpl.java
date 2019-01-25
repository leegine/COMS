// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketTradedOrderUnitImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketTradedOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeOrderUnitImpl, PersistenceManagerImpl

public class EqTypeMarketTradedOrderUnitImpl extends EqTypeOrderUnitImpl
    implements EqTypeMarketTradedOrderUnit
{

    EqTypeMarketTradedOrderUnitImpl(PersistenceManagerImpl pm, long order_unit_id)
        throws DataQueryException, DataNetworkException
    {
        super(pm, order_unit_id);
    }

    EqTypeMarketTradedOrderUnitImpl(PersistenceManagerImpl pm, EqtypeOrderUnitRow row)
    {
        super(pm, row);
    }

    public EqTypeExecutionConditionType getExecutionConditionType()
    {
        return m_Row.getExecutionConditionType();
    }

    public ProductTypeEnum getProductType()
    {
        return m_Row.getProductType();
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketTradedOrderUnitImpl.class);
        DBG = m_log.ison();
    }
}
