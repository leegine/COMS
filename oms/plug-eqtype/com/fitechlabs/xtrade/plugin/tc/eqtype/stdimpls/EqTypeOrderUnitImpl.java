// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderUnitImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            BaseEqTypeOrderUnitImpl, PersistenceManagerImpl

public class EqTypeOrderUnitImpl extends BaseEqTypeOrderUnitImpl
    implements EqTypeOrderUnit
{

    public EqTypeOrderUnitImpl(PersistenceManagerImpl pm, long order_unit_id)
        throws DataQueryException, DataNetworkException
    {
        super(order_unit_id);
    }

    public EqTypeOrderUnitImpl(PersistenceManagerImpl pm, EqtypeOrderUnitRow row)
    {
        super(row);
    }

    public EqTypeOrder getEqTypeOrder()
    {
        com.fitechlabs.xtrade.plugin.tc.gentrade.Order order = getOrder();
        if(order instanceof EqTypeOrder)
            return (EqTypeOrder)order;
        else
            throw new RuntimeSystemException("getEqTypeOrder called for non-eqtype order. orderId:" + getOrderId());
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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl.class);
        DBG = m_log.ison();
    }
}
