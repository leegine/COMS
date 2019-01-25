// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TradingModule.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            AlreadyInstalledException, ProductTypeEnum, BizLogicProvider, OrderManager, 
//            FinTransactionManager, MarketAdapter, PositionManager, QuoteDataSupplierService, 
//            ProductManager

public interface TradingModule
{

    public abstract ProductTypeEnum[] getProductTypes();

    public abstract boolean canHandle(ProductTypeEnum producttypeenum);

    public abstract String getTradingModuleName();

    public abstract BizLogicProvider getBizLogicProvider();

    public abstract OrderManager getOrderManager();

    public abstract FinTransactionManager getFinTransactionManager();

    public abstract MarketAdapter getMarketAdapter();

    public abstract PositionManager getPositionManager();

    public abstract QuoteDataSupplierService getQuoteDataSupplierService();

    public abstract ProductManager getProductManager();

    public abstract void overrideBizLogicProvider(BizLogicProvider bizlogicprovider);

    public abstract void overrideOrderManager(OrderManager ordermanager);

    public abstract void overrideOrderManager(Class class1, OrderManager ordermanager);

    public abstract void overridePositionManager(PositionManager positionmanager);

    public abstract void overrideProductManager(ProductManager productmanager);

    public abstract void installQuoteDataSupplierService(QuoteDataSupplierService quotedatasupplierservice)
        throws AlreadyInstalledException;
}
