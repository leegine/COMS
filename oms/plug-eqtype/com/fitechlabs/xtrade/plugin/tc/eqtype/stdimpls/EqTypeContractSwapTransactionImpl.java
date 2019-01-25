// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeContractSwapTransactionImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapTransaction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeMarketTradedOrderTransactionImpl, PersistenceManagerImpl

public class EqTypeContractSwapTransactionImpl extends EqTypeMarketTradedOrderTransactionImpl
    implements EqTypeContractSwapTransaction
{

    public EqTypeContractSwapTransactionImpl(long fin_transaction_id)
        throws DataQueryException, DataNetworkException
    {
        super(fin_transaction_id);
    }

    public EqTypeContractSwapTransactionImpl(EqtypeFinTransactionRow row)
    {
        super(row);
    }

    /**
     * @deprecated Method EqTypeContractSwapTransactionImpl is deprecated
     */

    EqTypeContractSwapTransactionImpl(PersistenceManagerImpl pm, long fin_transaction_id)
        throws DataQueryException, DataNetworkException
    {
        super(pm, fin_transaction_id);
    }

    /**
     * @deprecated Method EqTypeContractSwapTransactionImpl is deprecated
     */

    EqTypeContractSwapTransactionImpl(PersistenceManagerImpl pm, EqtypeFinTransactionRow row)
    {
        super(pm, row);
    }

    public long getAssetId()
    {
        return m_Row.getAssetId();
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

    public double getTransferedAssetBookValue()
    {
        return m_Row.getTransferedAssetBookValue();
    }

    public double getTransferedAssetMngFee()
    {
        return m_Row.getTransferedAssetMngFee();
    }

    public double getTransferedAssetMngFeeTax()
    {
        return m_Row.getTransferedAssetMngFeeTax();
    }

    public double getTransferedAssetSetupFee()
    {
        return m_Row.getTransferedAssetSetupFee();
    }

    public double getTransferedAssetSetupFeeTax()
    {
        return m_Row.getTransferedAssetSetupFeeTax();
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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractSwapTransactionImpl.class);
        DBG = m_log.ison();
    }
}
