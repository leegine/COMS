// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeClosingContractSpecImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

public class EqTypeClosingContractSpecImpl
    implements EqTypeClosingContractSpec
{

    protected EqTypeClosingContractSpecImpl(long closing_contract_spec_id)
        throws DataQueryException, DataNetworkException
    {
        this(EqtypeClosingContractSpecDao.findRowByPk(closing_contract_spec_id));
    }

    protected EqTypeClosingContractSpecImpl(EqtypeClosingContractSpecRow row)
    {
        m_Row = row;
    }

    public long getAccountId()
    {
        return m_Row.getAccountId();
    }

    public long getClosingContractSpecId()
    {
        return m_Row.getClosingContractSpecId();
    }

    public long getContractId()
    {
        return m_Row.getContractId();
    }

    public int getClosingSerialNo()
    {
        return m_Row.getClosingSerialNo();
    }

    public double getExecutedQuantity()
    {
        if(m_Row.getExecutedQuantityIsNull())
            return (0.0D / 0.0D);
        else
            return m_Row.getExecutedQuantity();
    }

    public double getConfirmedQuantity()
    {
        if(m_Row.getConfirmedQuantityIsNull())
            return (0.0D / 0.0D);
        else
            return m_Row.getConfirmedQuantity();
    }

    public boolean isFullyExecuted()
    {
        if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(m_Row.getExecutedQuantity()) || com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(m_Row.getConfirmedQuantity()))
            return false;
        else
            return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isEqual(m_Row.getConfirmedQuantity(), m_Row.getExecutedQuantity());
    }

    public boolean isPartiallyExecuted()
    {
        if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(m_Row.getExecutedQuantity()) || com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(m_Row.getConfirmedQuantity()))
            return false;
        else
            return m_Row.getConfirmedQuantity() > m_Row.getExecutedQuantity();
    }

    public boolean isUnexecuted()
    {
        return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(m_Row.getExecutedQuantity());
    }

    public long getOrderId()
    {
        return m_Row.getOrderId();
    }

    public long getOrderUnitId()
    {
        return m_Row.getOrderUnitId();
    }

    public double getQuantity()
    {
        return m_Row.getQuantity();
    }

    public long getSubAccountId()
    {
        return m_Row.getSubAccountId();
    }

    public Object getDataSourceObject()
    {
        return m_Row;
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
    protected EqtypeClosingContractSpecRow m_Row;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderActionImpl.class);
        DBG = m_log.ison();
    }
}
