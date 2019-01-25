// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeCashBasedOrderUnitImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeCashBasedOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeMarketTradedOrderUnitImpl, PersistenceManagerImpl

public class EqTypeCashBasedOrderUnitImpl extends EqTypeMarketTradedOrderUnitImpl
    implements EqTypeCashBasedOrderUnit
{

    EqTypeCashBasedOrderUnitImpl(PersistenceManagerImpl pm, long order_unit_id)
        throws DataQueryException, DataNetworkException
    {
        super(pm, order_unit_id);
    }

    EqTypeCashBasedOrderUnitImpl(PersistenceManagerImpl pm, EqtypeOrderUnitRow row)
    {
        super(pm, row);
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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeCashBasedOrderUnitImpl.class);
        DBG = m_log.ison();
    }
}
