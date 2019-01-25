// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeContractSwapOrderUnitImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeMarketTradedOrderUnitImpl, EqTypeClosingContractSpecImpl, PersistenceManagerImpl

public class EqTypeContractSwapOrderUnitImpl extends EqTypeMarketTradedOrderUnitImpl
    implements EqTypeContractSwapOrderUnit
{

    EqTypeContractSwapOrderUnitImpl(PersistenceManagerImpl pm, long order_unit_id)
        throws DataQueryException, DataNetworkException
    {
        super(pm, order_unit_id);
    }

    EqTypeContractSwapOrderUnitImpl(PersistenceManagerImpl pm, EqtypeOrderUnitRow row)
    {
        super(pm, row);
    }

    public EqTypeClosingContractSpec[] getContractsToClose()
    {
        List rows;
        try
        {
            String where = "order_unit_id=?";
            Object bvs[] = {
                new Long(getOrderUnitId())
            };
            String orderBy = "closing_serial_no";
            QueryProcessor qp = Processors.getDefaultProcessor();
            rows = qp.doFindAllQuery(EqtypeClosingContractSpecRow.TYPE, "order_unit_id=?", "closing_serial_no", null, bvs);
        }
        catch(DataException e)
        {
            m_log.warn("Exception while getting EqTypeClosingContractSpecRows from eqtype_closing_contract_spec table for order_unit_id:" + getOrderUnitId());
            return new EqTypeClosingContractSpec[0];
        }
        List objs = new ArrayList();
        for(int i = 0; i < rows.size(); i++)
        {
            EqtypeClosingContractSpecRow row = (EqtypeClosingContractSpecRow)rows.get(i);
            objs.add(new EqTypeClosingContractSpecImpl(row));
        }

        return (EqTypeClosingContractSpec[])objs.toArray(new EqTypeClosingContractSpec[0]);
    }

    public boolean isMarketOrder()
    {
        return false;
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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractSwapOrderUnitImpl.class);
        DBG = m_log.ison();
    }
}
