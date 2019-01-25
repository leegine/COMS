// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BizLogicProviderImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.sql.Timestamp;
import java.util.Date;

public abstract class BizLogicProviderImpl
    implements BizLogicProvider
{
    public class SimpleOrderExecution
        implements OrderExecution
    {

        public long getTraderId()
        {
            throw new UnsupportedOperationException("This method is not supported for SimpleOrderExecution used for commission calculation");
        }

        public Date getDeliveryDate()
        {
            throw new UnsupportedOperationException("This method is not supported for SimpleOrderExecution used for commission calculation");
        }

        public int getExecutionSerialNo()
        {
            throw new UnsupportedOperationException("This method is not supported for SimpleOrderExecution used for commission calculation");
        }

        public Timestamp getExecutionTimestamp()
        {
            throw new UnsupportedOperationException("This method is not supported for SimpleOrderExecution used for commission calculation");
        }

        public long getMarketId()
        {
            throw new UnsupportedOperationException("This method is not supported for SimpleOrderExecution used for commission calculation");
        }

        public long getOrderExecutionId()
        {
            throw new UnsupportedOperationException("This method is not supported for SimpleOrderExecution used for commission calculation");
        }

        public long getOrderUnitId()
        {
            throw new UnsupportedOperationException("This method is not supported for SimpleOrderExecution used for commission calculation");
        }

        public TradedProduct getTradedProduct()
        {
            throw new UnsupportedOperationException("This method is not supported for SimpleOrderExecution used for commission calculation");
        }

        public Object getDataSourceObject()
        {
            throw new UnsupportedOperationException("This method is not supported for SimpleOrderExecution used for commission calculation");
        }

        public long getAccountId()
        {
            return m_AccountId;
        }

        public long getBranchId()
        {
            return m_BranchId;
        }

        public double getExecutionPrice()
        {
            return m_ExecutionPrice;
        }

        public double getExecutionQuantity()
        {
            return m_ExecutionQuantity;
        }

        public OrderTypeEnum getOrderType()
        {
            return m_OrderType;
        }

        public Product getProduct()
        {
            return m_Product;
        }

        public long getSubAccountId()
        {
            return m_SubAccountId;
        }

        public void setAccountId(long l)
        {
            m_AccountId = l;
        }

        public void setBranchId(long l)
        {
            m_BranchId = l;
        }

        public void setExecutionPrice(double d)
        {
            m_ExecutionPrice = d;
        }

        public void setExecutionQuantity(double d)
        {
            m_ExecutionQuantity = d;
        }

        public void setOrderType(OrderTypeEnum enum)
        {
            m_OrderType = enum;
        }

        public void setProduct(Product product)
        {
            m_Product = product;
        }

        public void setSubAccountId(long l)
        {
            m_SubAccountId = l;
        }

        private long m_AccountId;
        private long m_SubAccountId;
        private long m_BranchId;
        private OrderTypeEnum m_OrderType;
        private Product m_Product;
        private double m_ExecutionPrice;
        private double m_ExecutionQuantity;

        public SimpleOrderExecution()
        {
        }
    }


    public BizLogicProviderImpl()
    {
    }

    public ConsolidatedCommissionInfo calcCommission(Object objs[])
    {
        int size = objs.length;
        double commissions[] = new double[size];
        double salesTaxes[] = new double[size];
        double totalCommission = 0.0D;
        double totalSalesTaxes = 0.0D;
        GlobalBizLogicProvider globalBizLogicProvider = GtlUtils.getGlobalBizLogicProvider();
        for(int i = 0; i < size; i++)
        {
            OrderExecution exec = toOrderExecution(objs[i]);
            double commission = calcCommission(exec);
            double salesTax = Math.floor(globalBizLogicProvider.calcSalesTax(commission));
            commissions[i] = commission;
            totalCommission += commission;
            salesTaxes[i] = salesTax;
            totalSalesTaxes += salesTax;
        }

        totalSalesTaxes = Math.round(totalSalesTaxes);
        return new ConsolidatedCommissionInfo(commissions, totalCommission, salesTaxes, totalSalesTaxes);
    }

    private OrderExecution toOrderExecution(Object obj)
    {
        if(obj instanceof OrderExecution)
            return (OrderExecution)obj;
        if(obj instanceof FinTransaction)
        {
            return finTransactionToOrderExecution((FinTransaction)obj);
        } else
        {
            String message = "Unsupported conversion form " + obj.getClass().getName() + " to an OrderExecution object for commission calculation.";
            m_log.error(message);
            throw new RuntimeSystemException(message);
        }
    }

    protected abstract TradingModule getTradingModule();

    protected abstract OrderExecution finTransactionToOrderExecution(FinTransaction fintransaction);

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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BizLogicProviderImpl.class);
        DBG = m_log.ison();
    }
}
