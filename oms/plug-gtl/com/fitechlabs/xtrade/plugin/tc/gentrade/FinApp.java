// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FinApp.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.boot.Service;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            NotInstalledException, AlreadyInstalledException, AccountManager, TradingModule, 
//            ProductTypeEnum, GeneralizedFinTransactionManager, GlobalBizLogicProvider, TradingSystem, 
//            FinObjectManager, CommonOrderValidator

public interface FinApp
    extends Service
{

    public abstract AccountManager getAccountManager();

    public abstract TradingModule getTradingModule(String s)
        throws NotInstalledException;

    public abstract TradingModule getTradingModule(ProductTypeEnum producttypeenum)
        throws NotInstalledException;

    public abstract void installTradingModule(TradingModule tradingmodule)
        throws AlreadyInstalledException;

    public abstract void uninstallTradingModule(String s)
        throws NotInstalledException;

    public abstract GeneralizedFinTransactionManager getGeneralizedFinTransactionManager();

    public abstract GlobalBizLogicProvider getGlobalBizLogicProvider();

    public abstract TradingSystem getTradingSystem();

    public abstract FinObjectManager getFinObjectManager();

    public abstract void overrideAccountManager(AccountManager accountmanager);

    public abstract void overrideTradingSystem(TradingSystem tradingsystem);

    public abstract void overrideFinObjectManager(FinObjectManager finobjectmanager);

    public abstract void overrideGeneralizedFinTransactionManager(GeneralizedFinTransactionManager generalizedfintransactionmanager);

    public abstract void overrideGlobalBizLogicProvider(GlobalBizLogicProvider globalbizlogicprovider);

    public abstract CommonOrderValidator getCommonOrderValidator();

    public abstract void overrideCommonOrderValidator(CommonOrderValidator commonordervalidator);
}
