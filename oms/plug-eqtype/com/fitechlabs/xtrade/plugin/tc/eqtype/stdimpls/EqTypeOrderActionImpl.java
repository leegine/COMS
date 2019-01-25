// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderActionImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.sql.Timestamp;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            PersistenceManagerImpl

public class EqTypeOrderActionImpl
    implements EqTypeOrderAction
{

    protected EqTypeOrderActionImpl(PersistenceManagerImpl pm, long order_action_id)
        throws DataQueryException, DataNetworkException
    {
        m_Row = EqtypeOrderActionDao.findRowByPk(order_action_id);
    }

    protected EqTypeOrderActionImpl(PersistenceManagerImpl pm, EqtypeOrderActionRow row)
    {
        m_Row = row;
    }

    public Object getDataSourceObject()
    {
        return m_Row;
    }

    public long getAccountId()
    {
        return m_Row.getAccountId();
    }

    public long getOrderId()
    {
        return m_Row.getOrderId();
    }

    public long getOrderActionId()
    {
        return m_Row.getOrderActionId();
    }

    public Timestamp getOrderActionTimestamp()
    {
        return m_Row.getCreatedTimestamp();
    }

    public long getOrderUnitId()
    {
        return m_Row.getOrderUnitId();
    }

    public Date getCreatedTimestamp()
    {
        return m_Row.getCreatedTimestamp();
    }

    public EqTypeExecutionConditionType getExecutionConditionType()
    {
        return m_Row.getExecutionConditionType();
    }

    public OrderExpirationStatusEnum getExpirationStatus()
    {
        return m_Row.getExpirationStatus();
    }

    public boolean isExpired()
    {
        return !getExpirationStatus().equals(OrderExpirationStatusEnum.OPEN);
    }

    public boolean isFullyExecuted()
    {
        if(m_Row.getExecutedQuantityIsNull() || m_Row.getConfirmedQuantityIsNull())
            return false;
        else
            return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isEqual(m_Row.getConfirmedQuantity(), m_Row.getExecutedQuantity());
    }

    public boolean isPartiallyExecuted()
    {
        if(m_Row.getExecutedQuantityIsNull() || m_Row.getConfirmedQuantityIsNull())
            return false;
        else
            return m_Row.getConfirmedQuantity() > m_Row.getExecutedQuantity();
    }

    public boolean isUnexecuted()
    {
        return m_Row.getExecutedQuantityIsNull();
    }

    public long getMarketId()
    {
        return m_Row.getMarketId();
    }

    public int getOrderActionSerialNo()
    {
        return m_Row.getOrderActionSerialNo();
    }

    public OrderEventTypeEnum getOrderEventType()
    {
        return m_Row.getOrderEventType();
    }

    public OrderStatusEnum getOrderStatus()
    {
        return m_Row.getOrderStatus();
    }

    public double getPrice()
    {
        if(m_Row.getPriceIsNull())
            return (0.0D / 0.0D);
        else
            return m_Row.getPrice();
    }

    public boolean isMarketOrder()
    {
        OrderTypeEnum ot = getOrderType();
        if(OrderTypeEnum.SWAP_MARGIN_LONG.equals(ot) || OrderTypeEnum.SWAP_MARGIN_SHORT.equals(ot))
        {
            return false;
        } else
        {
            double price = getPrice();
            return java.lang.Double.isNaN(price) || com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(price);
        }
    }

    public long getProductId()
    {
        return m_Row.getProductId();
    }

    public long getSubAccountId()
    {
        return m_Row.getSubAccountId();
    }

    public double getQuantity()
    {
        return m_Row.getQuantity();
    }

    public double getConfirmedPrice()
    {
        if(m_Row.getConfirmedPriceIsNull())
            return (0.0D / 0.0D);
        else
            return m_Row.getConfirmedPrice();
    }

    public double getConfirmedQuantity()
    {
        if(m_Row.getConfirmedQuantityIsNull())
            return (0.0D / 0.0D);
        else
            return m_Row.getConfirmedQuantity();
    }

    public double getExecutedQuantity()
    {
        return getExecutionQuantity();
    }

    public double getExecutionQuantity()
    {
        if(m_Row.getExecutedQuantityIsNull())
            return (0.0D / 0.0D);
        else
            return m_Row.getExecutedQuantity();
    }

    public double getExecutionPrice()
    {
        if(m_Row.getExecutedPriceIsNull())
            return (0.0D / 0.0D);
        else
            return m_Row.getExecutedPrice();
    }

    public OrderTypeEnum getOrderType()
    {
        return m_Row.getOrderType();
    }

    public SideEnum getSide()
    {
        SideEnum side = SideEnum.getSide(getOrderType());
        if(side.equals(SideEnum.UNDEFINED))
            throw new RuntimeSystemException("Unsupported OrderType:" + getOrderType() + " for OrderAction:" + getOrderActionId());
        else
            return side;
    }

    public TradedProduct getTradedProduct()
    {
        if(m_tradedProduct == null)
            try
            {
                m_tradedProduct = GtlUtils.getTradingModule(m_Row.getProductType()).getProductManager().getTradedProduct(m_Row.getProductId(), m_Row.getMarketId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "TradedProduct could not be found for order action row : " + m_Row.toString();
                m_log.error(msg);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_tradedProduct;
    }

    public Product getProduct()
    {
        if(m_product == null)
            try
            {
                m_product = GtlUtils.getTradingModule(m_Row.getProductType()).getProductManager().getProduct(m_Row.getProductId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "Product not found for Id : " + m_Row.getProductId() + ", for orderUnitId:" + getOrderUnitId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_product;
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
                String msg = "MainAccount not found accountId : " + getAccountId() + ", for orderUnitId:" + getOrderUnitId();
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
    protected EqtypeOrderActionRow m_Row;
    private TradedProduct m_tradedProduct;
    private Product m_product;
    private MainAccount m_mainAccount;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderActionImpl.class);
        DBG = m_log.ison();
    }
}
