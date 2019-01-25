// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderExecutionImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.sql.Timestamp;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            PersistenceManagerImpl

public class EqTypeOrderExecutionImpl
    implements EqTypeOrderExecution
{

    public EqTypeOrderExecutionImpl(long order_execution_id)
        throws DataQueryException, DataNetworkException
    {
        this(EqtypeOrderExecutionDao.findRowByPk(order_execution_id));
    }

    public EqTypeOrderExecutionImpl(EqtypeOrderExecutionRow row)
    {
        m_Row = row;
    }

    /**
     * @deprecated Method EqTypeOrderExecutionImpl is deprecated
     */

    public EqTypeOrderExecutionImpl(PersistenceManagerImpl pm, long order_execution_id)
        throws DataQueryException, DataNetworkException
    {
        this(null, EqtypeOrderExecutionDao.findRowByPk(order_execution_id));
    }

    /**
     * @deprecated Method EqTypeOrderExecutionImpl is deprecated
     */

    public EqTypeOrderExecutionImpl(PersistenceManagerImpl pm, EqtypeOrderExecutionRow row)
    {
        m_Row = row;
    }

    public long getAccountId()
    {
        return m_Row.getAccountId();
    }

    public long getBranchId()
    {
        return m_Row.getBranchId();
    }

    public long getTraderId()
    {
        return m_Row.getTraderId();
    }

    public Date getDeliveryDate()
    {
        return m_Row.getDeliveryDate();
    }

    public double getExecutionPrice()
    {
        return m_Row.getExecPriceIsNull() ? (0.0D / 0.0D) : m_Row.getExecPrice();
    }

    public int getExecutionSerialNo()
    {
        return m_Row.getExecSerialNo();
    }

    public Timestamp getExecutionTimestamp()
    {
        return m_Row.getExecTimestamp();
    }

    public long getMarketId()
    {
        return m_Row.getMarketId();
    }

    public long getOrderExecutionId()
    {
        return m_Row.getOrderExecutionId();
    }

    public long getOrderUnitId()
    {
        return m_Row.getOrderUnitId();
    }

    public long getProductId()
    {
        return m_Row.getProductId();
    }

    public Product getProduct()
    {
        if(m_product == null)
            try
            {
                m_product = GtlUtils.getTradingModule(m_Row.getProductType()).getProductManager().getProduct(getProductId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "Could not get Product biz object for productId :" + getProductId() + ", for orderExecutionId:" + getOrderExecutionId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_product;
    }

    public TradedProduct getTradedProduct()
    {
        if(m_tradedProduct == null)
            try
            {
                m_tradedProduct = GtlUtils.getTradingModule(m_Row.getProductType()).getProductManager().getTradedProduct(m_Row.getProductId(), m_Row.getMarketId());
            }
            catch(NotFoundException ex)
            {
                m_log.error(ex.getMessage(), ex);
                throw new RuntimeSystemException("System exception while getting TradedProduct with product id, market id : " + m_Row.getProductId() + "," + m_Row.getMarketId());
            }
        return m_tradedProduct;
    }

    public ProductTypeEnum getProductType()
    {
        return m_Row.getProductType();
    }

    public double getExecutionQuantity()
    {
        return m_Row.getExecQuantity();
    }

    public long getSubAccountId()
    {
        return m_Row.getSubAccountId();
    }

    public OrderTypeEnum getOrderType()
    {
        return m_Row.getOrderType();
    }

    public Object getDataSourceObject()
    {
        return m_Row;
    }

    private MainAccount getMainAccount()
    {
        if(m_mainAccount == null)
            try
            {
                m_mainAccount = GtlUtils.getAccountManager().getMainAccount(getAccountId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "MainAccount not found accountId : " + getAccountId() + ", for orderExecutionID:" + getOrderExecutionId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_mainAccount;
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
    protected EqtypeOrderExecutionRow m_Row;
    private Product m_product;
    private TradedProduct m_tradedProduct;
    private MainAccount m_mainAccount;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderExecutionImpl.class);
        DBG = m_log.ison();
    }
}
