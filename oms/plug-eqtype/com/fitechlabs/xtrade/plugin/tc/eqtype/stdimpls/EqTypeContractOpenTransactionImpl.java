// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeContractOpenTransactionImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractOpenTransaction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeMarketTradedOrderTransactionImpl, PersistenceManagerImpl

public class EqTypeContractOpenTransactionImpl extends EqTypeMarketTradedOrderTransactionImpl
    implements EqTypeContractOpenTransaction
{

    public EqTypeContractOpenTransactionImpl(long fin_transaction_id)
        throws DataQueryException, DataNetworkException
    {
        super(fin_transaction_id);
    }

    public EqTypeContractOpenTransactionImpl(EqtypeFinTransactionRow row)
    {
        super(row);
    }

    /**
     * @deprecated Method EqTypeContractOpenTransactionImpl is deprecated
     */

    EqTypeContractOpenTransactionImpl(PersistenceManagerImpl pm, long fin_transaction_id)
        throws DataQueryException, DataNetworkException
    {
        super(pm, fin_transaction_id);
    }

    /**
     * @deprecated Method EqTypeContractOpenTransactionImpl is deprecated
     */

    EqTypeContractOpenTransactionImpl(PersistenceManagerImpl pm, EqtypeFinTransactionRow row)
    {
        super(pm, row);
    }

    public long getContractId()
    {
        return m_Row.getContractId();
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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractOpenTransactionImpl.class);
        DBG = m_log.ison();
    }
}
