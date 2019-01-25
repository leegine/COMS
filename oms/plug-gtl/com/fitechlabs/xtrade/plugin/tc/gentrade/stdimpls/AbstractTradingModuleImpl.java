// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractTradingModuleImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

public abstract class AbstractTradingModuleImpl
    implements TradingModule
{

    protected AbstractTradingModuleImpl()
    {
    }

    public BizLogicProvider getBizLogicProvider()
    {
        return m_bizLogicProvider;
    }

    public OrderManager getOrderManager()
    {
        return m_orderManager;
    }

    public FinTransactionManager getFinTransactionManager()
    {
        return m_finTranManager;
    }

    public MarketAdapter getMarketAdapter()
    {
        return m_marketAdapter;
    }

    public PositionManager getPositionManager()
    {
        return m_positionManager;
    }

    public ProductManager getProductManager()
    {
        return m_productManager;
    }

    public QuoteDataSupplierService getQuoteDataSupplierService()
    {
        if(m_quoteDataSupplierService == null)
            throw new IllegalStateException("QuoteDataSupplierService is not yet installed.");
        else
            return m_quoteDataSupplierService;
    }

    public boolean canHandle(ProductTypeEnum inProductType)
    {
        ProductTypeEnum productTypes[] = getProductTypes();
        for(int i = 0; i < productTypes.length; i++)
        {
            ProductTypeEnum p = productTypes[i];
            if(p.equals(inProductType))
                return true;
        }

        return false;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj instanceof TradingModule)
        {
            TradingModule anotherTm = (TradingModule)obj;
            return getTradingModuleName().equals(anotherTm.getTradingModuleName());
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return getTradingModuleName().hashCode();
    }

    protected BizLogicProvider m_bizLogicProvider;
    protected OrderManager m_orderManager;
    protected PositionManager m_positionManager;
    protected FinTransactionManager m_finTranManager;
    protected MarketAdapter m_marketAdapter;
    protected QuoteDataSupplierService m_quoteDataSupplierService;
    protected ProductManager m_productManager;
}
