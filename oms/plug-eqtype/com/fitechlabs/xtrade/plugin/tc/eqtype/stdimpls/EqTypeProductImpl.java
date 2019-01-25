// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeProductImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AbstractProductImpl;
import java.util.Date;

public class EqTypeProductImpl extends AbstractProductImpl
    implements EqTypeProduct
{

    public EqTypeProductImpl(long productId)
        throws DataQueryException, DataNetworkException
    {
        super(productId);
        m_eqtypeProductRow = EqtypeProductDao.findRowByPk(productId);
    }

    public EqTypeProductImpl(ProductRow prow)
        throws DataQueryException, DataNetworkException
    {
        super(prow);
        m_eqtypeProductRow = EqtypeProductDao.findRowByPk(prow.getProductId());
    }

    public EqTypeProductImpl(EqtypeProductRow row)
        throws DataQueryException, DataNetworkException
    {
        super(row.getProductId());
        m_eqtypeProductRow = row;
    }

    public Date getYearlyBooksClosingDate()
    {
        return m_eqtypeProductRow.getYearlyBooksClosingDate();
    }

    public String getProductCode()
    {
        return m_eqtypeProductRow.getProductCode();
    }

    public double getMiniStockLotSize()
    {
        return m_eqtypeProductRow.getMiniStockLotSize();
    }

    public Object getDataSourceObject()
    {
        return m_eqtypeProductRow;
    }

    protected TradingModule getThisTradingModule()
    {
        return GtlUtils.getTradingModule("eqtype");
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private final EqtypeProductRow m_eqtypeProductRow;
    private static final Logit m_log;
    private static final boolean DBG;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeProductImpl.class);
        DBG = m_log.ison();
    }
}
