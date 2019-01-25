// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeContractSettleOrderUnitImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeMarketTradedOrderUnitImpl, EqTypeClosingContractSpecImpl, PersistenceManagerImpl

public class EqTypeContractSettleOrderUnitImpl extends EqTypeMarketTradedOrderUnitImpl
    implements EqTypeContractSettleOrderUnit
{

    EqTypeContractSettleOrderUnitImpl(PersistenceManagerImpl pm, long order_unit_id)
        throws DataQueryException, DataNetworkException
    {
        super(pm, order_unit_id);
    }

    EqTypeContractSettleOrderUnitImpl(PersistenceManagerImpl pm, EqtypeOrderUnitRow row)
    {
        super(pm, row);
    }

    public EqTypeClosingContractSpec[] getContractsToClose()
    {
        return getContractsToClose(getOrderUnitId());
    }

    EqTypeClosingContractSpec[] getContractsToClose(long orderUnitId)
    {
        List objs;
        String where = "order_unit_id=?";
        Object bvs[] = {
            new Long(orderUnitId)
        };
        String orderBy = "closing_serial_no";
        QueryProcessor qp = Processors.getDefaultProcessor();
        List rows = qp.doFindAllQuery(EqtypeClosingContractSpecRow.TYPE, "order_unit_id=?", "closing_serial_no", null, bvs);
        objs = new ArrayList();
        int size = rows.size();
        for(int i = 0; i < size; i++)
        {
            EqtypeClosingContractSpecRow row = (EqtypeClosingContractSpecRow)rows.get(i);
            objs.add(new EqTypeClosingContractSpecImpl(row));
        }

        return (EqTypeClosingContractSpec[])objs.toArray(new EqTypeClosingContractSpec[0]);
        DataException e;
        e;
        String msg = "Exception while getting EqTypeClosingContractSpecRows from eqtype_closing_contract_spec table for order_unit_id:" + orderUnitId;
        m_log.error(msg, e);
        throw new RuntimeSystemException(msg, e);
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
