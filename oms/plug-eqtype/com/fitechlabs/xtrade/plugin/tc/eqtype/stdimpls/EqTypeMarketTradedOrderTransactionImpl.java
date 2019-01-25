// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketTradedOrderTransactionImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketTradedOrderTransaction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeFinTransactionImpl, PersistenceManagerImpl

public class EqTypeMarketTradedOrderTransactionImpl extends EqTypeFinTransactionImpl
    implements EqTypeMarketTradedOrderTransaction
{

    public EqTypeMarketTradedOrderTransactionImpl(long fin_transaction_id)
        throws DataQueryException, DataNetworkException
    {
        super(fin_transaction_id);
    }

    public EqTypeMarketTradedOrderTransactionImpl(EqtypeFinTransactionRow row)
    {
        super(row);
    }

    /**
     * @deprecated Method EqTypeMarketTradedOrderTransactionImpl is deprecated
     */

    EqTypeMarketTradedOrderTransactionImpl(PersistenceManagerImpl pm, long fin_transaction_id)
        throws DataQueryException, DataNetworkException
    {
        super(pm, fin_transaction_id);
    }

    /**
     * @deprecated Method EqTypeMarketTradedOrderTransactionImpl is deprecated
     */

    EqTypeMarketTradedOrderTransactionImpl(PersistenceManagerImpl pm, EqtypeFinTransactionRow row)
    {
        super(pm, row);
    }

    public double getCommissionFee()
    {
        return m_Row.getCommissionFee();
    }

    public double getCommissionFeeTax()
    {
        return m_Row.getCommissionFeeTax();
    }

    public long getMarketId()
    {
        return m_Row.getMarketId();
    }

    public long getOrderExecutionId()
    {
        return m_Row.getOrderExecutionId();
    }

    public long getOrderId()
    {
        return m_Row.getOrderId();
    }

    public long getOrderUnitId()
    {
        return m_Row.getOrderUnitId();
    }

    public double getPrice()
    {
        if(m_Row.getPriceIsNull())
            return (0.0D / 0.0D);
        else
            return m_Row.getPrice();
    }

    public long getProductId()
    {
        return m_Row.getProductId();
    }

    public ProductTypeEnum getProductType()
    {
        return m_Row.getProductType();
    }

    public double getQuantity()
    {
        return m_Row.getQuantity();
    }

    public TaxTypeEnum getTaxType()
    {
        return m_Row.getTaxType();
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
