// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseEqTypeOrderUnitImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            Utils

public abstract class BaseEqTypeOrderUnitImpl
    implements OrderUnit
{

    protected BaseEqTypeOrderUnitImpl(long order_unit_id)
        throws DataQueryException, DataNetworkException
    {
        this(EqtypeOrderUnitDao.findRowByPk(order_unit_id));
    }

    protected BaseEqTypeOrderUnitImpl(EqtypeOrderUnitRow row)
    {
        m_Row = row;
    }

    public Object getDataSourceObject()
    {
        return m_Row;
    }

    public OrderAction[] getOrderActions()
    {
        List rows;
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();
            String where = "order_unit_id = ?";
            Object bvs[] = {
                new Long(getOrderUnitId())
            };
            String orderBy = "order_action_serial_no";
            rows = qp.doFindAllQuery(EqtypeOrderActionRow.TYPE, "order_unit_id = ?", "order_action_serial_no", null, bvs);
        }
        catch(DataException de)
        {
            String msg = "Exception while getting EqtypeOrderActionsRows from eqtype_order_action table for order_unit_id:" + getOrderUnitId();
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
        OrderManager om = GtlUtils.getTradingModule(m_Row.getProductType()).getOrderManager();
        int size = rows.size();
        OrderAction objs[] = new OrderAction[size];
        for(int i = 0; i < size; i++)
        {
            EqtypeOrderActionRow row = (EqtypeOrderActionRow)rows.get(i);
            objs[i] = om.toOrderAction(row);
        }

        return objs;
    }

    public OrderExecution[] getExecutions()
    {
        OrderExecution objs[];
        com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.Where query = new com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.Where();
        query.append("order_unit_id=?", new Object[] {
            new Long(getOrderUnitId())
        });
        query = GtlQueryUtils.addNotMarkedDeleted0(query);
        String orderBy = "exec_serial_no asc";
        List rows = GtlQueryUtils.executeQuery(EqtypeOrderExecutionRow.TYPE, query.getWhere(), "exec_serial_no asc", null, query.getBindVarArray(), -1, -1);
        OrderManager om = GtlUtils.getTradingModule(m_Row.getProductType()).getOrderManager();
        int size = rows.size();
        objs = new OrderExecution[size];
        for(int i = 0; i < size; i++)
        {
            EqtypeOrderExecutionRow row = (EqtypeOrderExecutionRow)rows.get(i);
            objs[i] = om.toOrderExecution(row);
        }

        return objs;
        DataException de;
        de;
        String msg = "Exception while getting executions for orderUnitId:" + getOrderUnitId();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
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

    public Timestamp getExpirationTimestamp()
    {
        return m_Row.getExpirationDate();
    }

    public long getOrderId()
    {
        return m_Row.getOrderId();
    }

    public long getOrderUnitId()
    {
        return m_Row.getOrderUnitId();
    }

    public Timestamp getReceivedTimestamp()
    {
        return m_Row.getCreatedTimestamp();
    }

    public long getSubAccountId()
    {
        return m_Row.getSubAccountId();
    }

    public OrderOpenStatusEnum getOrderOpenStatus()
    {
        return m_Row.getOrderOpenStatus();
    }

    public OrderTypeEnum getOrderType()
    {
        return m_Row.getOrderType();
    }

    public OrderCategEnum getOrderCateg()
    {
        return m_Row.getOrderCateg();
    }

    public TaxTypeEnum getTaxType()
    {
        return m_Row.getTaxType();
    }

    public Order getOrder()
    {
        if(m_order == null)
        {
            TradingModule tm = GtlUtils.getTradingModule(m_Row.getProductType());
            try
            {
                m_order = tm.getOrderManager().getOrder(m_Row.getOrderId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "Order not found with orderId : " + m_Row.getOrderId() + ", for OrderUnitId:" + m_Row.getOrderUnitId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        }
        return m_order;
    }

    public SideEnum getSide()
    {
        SideEnum side = SideEnum.getSide(getOrderType());
        return side;
    }

    public Product getProduct()
    {
        if(m_product != null)
            break MISSING_BLOCK_LABEL_84;
        return Utils.getProductManager().getProduct(m_Row.getProductId());
        NotFoundException nfe;
        nfe;
        String msg = "Product not found for Id : " + m_Row.getProductId() + ", for orderUnitId:" + getOrderUnitId();
        m_log.error(msg, nfe);
        throw new RuntimeSystemException(msg, nfe);
        return m_product;
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

    public Date getDeliveryDate()
    {
        return m_Row.getDeliveryDate();
    }

    public double getExecutedAmount()
    {
        if(m_Row.getExecutedAmountIsNull())
            return (0.0D / 0.0D);
        else
            return m_Row.getExecutedAmount();
    }

    public double getExecutedQuantity()
    {
        if(m_Row.getExecutedQuantityIsNull())
            return (0.0D / 0.0D);
        else
            return m_Row.getExecutedQuantity();
    }

    public OrderExpirationStatusEnum getExpirationStatus()
    {
        return m_Row.getExpirationStatus();
    }

    public double getQuantity()
    {
        return m_Row.getQuantity();
    }

    public TradedProduct getTradedProduct()
    {
        if(m_Row.getMarketIdIsNull())
            return null;
        if(m_tradedProduct == null)
            try
            {
                m_tradedProduct = GtlUtils.getTradingModule("eqtype").getProductManager().getTradedProduct(m_Row.getProductId(), m_Row.getMarketId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "TradedProduct could not be found for order unit row : " + m_Row.toString();
                m_log.error(msg);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_tradedProduct;
    }

    public boolean isConfirmedPriceMarketOrder()
    {
        double confirmed_price = getConfirmedPrice();
        return java.lang.Double.isNaN(confirmed_price) || com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(confirmed_price);
    }

    public boolean isExpired()
    {
        return !getExpirationStatus().equals(OrderExpirationStatusEnum.OPEN);
    }

    public boolean isFullyExecuted()
    {
        if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(m_Row.getExecutedQuantity()) || m_Row.getConfirmedQuantityIsNull())
            return false;
        else
            return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isEqual(m_Row.getConfirmedQuantity(), m_Row.getExecutedQuantity());
    }

    public boolean isPartiallyExecuted()
    {
        if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(m_Row.getExecutedQuantity()) || m_Row.getConfirmedQuantityIsNull())
            return false;
        else
            return m_Row.getConfirmedQuantity() > m_Row.getExecutedQuantity();
    }

    public boolean isUnexecuted()
    {
        return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(m_Row.getExecutedQuantity());
    }

    public double getLimitPrice()
    {
        if(m_Row.getLimitPriceIsNull())
            return (0.0D / 0.0D);
        else
            return m_Row.getLimitPrice();
    }

    public OrderStatusEnum getOrderStatus()
    {
        return m_Row.getOrderStatus();
    }

    public boolean isMarketOrder()
    {
        double limit_price = getLimitPrice();
        return java.lang.Double.isNaN(limit_price) || com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(limit_price);
    }

    public ProductTypeEnum getProductType()
    {
        return m_Row.getProductType();
    }

    protected MainAccount getMainAccount()
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
    protected EqtypeOrderUnitRow m_Row;
    private Order m_order;
    protected TradedProduct m_tradedProduct;
    protected Product m_product;
    protected MainAccount m_mainAccount;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.BaseEqTypeOrderUnitImpl.class);
        DBG = m_log.ison();
    }
}
