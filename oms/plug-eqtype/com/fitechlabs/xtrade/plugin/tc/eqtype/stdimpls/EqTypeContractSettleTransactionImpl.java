// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeContractSettleTransactionImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleTransaction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeMarketTradedOrderTransactionImpl, PersistenceManagerImpl

public class EqTypeContractSettleTransactionImpl extends EqTypeMarketTradedOrderTransactionImpl
    implements EqTypeContractSettleTransaction
{

    public EqTypeContractSettleTransactionImpl(long fin_transaction_id)
        throws DataQueryException, DataNetworkException
    {
        super(fin_transaction_id);
    }

    public EqTypeContractSettleTransactionImpl(EqtypeFinTransactionRow row)
    {
        super(row);
    }

    /**
     * @deprecated Method EqTypeContractSettleTransactionImpl is deprecated
     */

    EqTypeContractSettleTransactionImpl(PersistenceManagerImpl pm, long fin_transaction_id)
        throws DataQueryException, DataNetworkException
    {
        super(pm, fin_transaction_id);
    }

    /**
     * @deprecated Method EqTypeContractSettleTransactionImpl is deprecated
     */

    EqTypeContractSettleTransactionImpl(PersistenceManagerImpl pm, EqtypeFinTransactionRow row)
    {
        super(pm, row);
    }

    public double getCapitalGain()
    {
        return m_Row.getCapitalGain();
    }

    public double getCapitalGainTax()
    {
        return m_Row.getCapitalGainTax();
    }

    public long getContractId()
    {
        return m_Row.getContractId();
    }

    public double getImportedInterestFee()
    {
        return m_Row.getImportedInterestFee();
    }

    public double getImportedInterestFeeTax()
    {
        return m_Row.getImportedInterestFeeTax();
    }

    public double getImportedManagementFee()
    {
        return m_Row.getImportedManagementFee();
    }

    public double getImportedManagementFeeTax()
    {
        return m_Row.getImportedManagementFeeTax();
    }

    public double getImportedPaidValue()
    {
        return m_Row.getImportedPaidValue();
    }

    public double getImportedSetupFee()
    {
        return m_Row.getImportedSetupFee();
    }

    public double getImportedSetupFeeTax()
    {
        return m_Row.getImportedSetupFeeTax();
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
