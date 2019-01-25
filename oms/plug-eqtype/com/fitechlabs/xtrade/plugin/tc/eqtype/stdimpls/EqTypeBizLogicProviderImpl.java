// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeBizLogicProviderImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BizLogicProviderImpl;

public class EqTypeBizLogicProviderImpl extends BizLogicProviderImpl
    implements EqTypeBizLogicProvider
{

    public EqTypeBizLogicProviderImpl()
    {
    }

    public double calcExecutionAmount(ProductTypeEnum pType, long pId, double price, double quantity, 
            QuantityTypeEnum qType)
    {
        return price * quantity;
    }

    public double calcCommission(OrderExecution exec)
    {
        double amount = exec.getExecutionPrice() * exec.getExecutionQuantity();
        OrderTypeEnum orderType = exec.getOrderType();
        if(orderType.equals(OrderTypeEnum.EQUITY_BUY) || orderType.equals(OrderTypeEnum.EQUITY_SELL))
            return Math.floor(0.01D * amount);
        if(orderType.equals(OrderTypeEnum.MARGIN_LONG) || orderType.equals(OrderTypeEnum.MARGIN_SHORT))
            return Math.floor(0.02D * amount);
        if(orderType.equals(OrderTypeEnum.CLOSE_MARGIN_LONG) || orderType.equals(OrderTypeEnum.CLOSE_MARGIN_SHORT))
            return Math.floor(0.029999999999999999D * amount);
        if(orderType.equals(OrderTypeEnum.SWAP_MARGIN_LONG) || orderType.equals(OrderTypeEnum.SWAP_MARGIN_SHORT))
            return Math.floor(0.040000000000000001D * amount);
        else
            return 0.0D;
    }

    public double calcCapitalGainTax(SubAccount sub_account, TaxTypeEnum tax, double amount)
    {
        if(amount > 0.0D && TaxTypeEnum.SPECIAL_WITHHOLD.equals(tax))
            return Math.floor(0.14999999999999999D * amount);
        else
            return 0.0D;
    }

    protected OrderExecution finTransactionToOrderExecution(FinTransaction from)
    {
        com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BizLogicProviderImpl.SimpleOrderExecution exec;
        ProductManager productManager = getTradingModule().getProductManager();
        exec = new com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BizLogicProviderImpl.SimpleOrderExecution(this);
        EqtypeFinTransactionRow row = (EqtypeFinTransactionRow)from.getDataSourceObject();
        exec.setAccountId(row.getAccountId());
        exec.setSubAccountId(row.getSubAccountId());
        exec.setBranchId(from.getSubAccount().getMainAccount().getBranch().getBranchId());
        exec.setProduct(productManager.getProduct(row.getProductId()));
        exec.setOrderType(row.getFinTransactionType().toOrderTypeEnum());
        exec.setExecutionPrice(row.getPrice());
        exec.setExecutionQuantity(row.getQuantity());
        return exec;
        NotFoundException e;
        e;
        String message = "Exception thrown when trying to convert a FinTransaction object to a OrderExecution object: " + e.getMessage();
        m_log.error(message);
        throw new RuntimeSystemException(message);
    }

    protected TradingModule getTradingModule()
    {
        return GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeBizLogicProviderImpl.class);
        DBG = m_log.ison();
    }
}
