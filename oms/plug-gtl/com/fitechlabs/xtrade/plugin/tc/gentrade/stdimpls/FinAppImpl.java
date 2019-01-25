// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FinAppImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            AccountManagerImpl, TradingSystemImpl, GeneralizedFinTransactionManagerImpl, FinObjectManagerImpl, 
//            DefaultGlobalBizLogicProviderImpl, CommonOrderValidatorImpl

public class FinAppImpl
    implements FinApp
{

    public FinAppImpl()
    {
        m_tradingModulesByName = new HashMap();
        m_tradingModulesByProducType = new HashMap();
        m_acctMgr = new AccountManagerImpl();
        m_tradingSys = new TradingSystemImpl();
        m_genericFinTranMgr = new GeneralizedFinTransactionManagerImpl();
        m_finObjectMgr = new FinObjectManagerImpl();
        m_globalBizLogicProvider = new DefaultGlobalBizLogicProviderImpl();
        m_commonOrderValidator = new CommonOrderValidatorImpl();
    }

    public AccountManager getAccountManager()
    {
        return m_acctMgr;
    }

    public TradingModule getTradingModule(String name)
        throws NotInstalledException
    {
        Object mod = m_tradingModulesByName.get(name);
        if(mod == null)
            throw new NotInstalledException("TradingModule with name : " + name + " is not installed.");
        else
            return (TradingModule)mod;
    }

    public TradingModule getTradingModule(ProductTypeEnum productType)
        throws NotInstalledException
    {
        Object mod = m_tradingModulesByProducType.get(productType);
        if(mod == null)
            throw new NotInstalledException("No TradingModule is installed to handle productType : " + productType);
        else
            return (TradingModule)mod;
    }

    public void installTradingModule(TradingModule module)
        throws AlreadyInstalledException
    {
        String name = module.getTradingModuleName();
        m_log.info("Installing trading module : " + name);
        synchronized(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class)
        {
            Object mod = m_tradingModulesByName.get(name);
            if(mod != null)
                throw new AlreadyInstalledException("trading module with name : " + name + " Already installed.");
            ProductTypeEnum productTypes[] = module.getProductTypes();
            for(int i = 0; i < productTypes.length; i++)
            {
                ProductTypeEnum productTypeEnum = productTypes[i];
                TradingModule tm = (TradingModule)m_tradingModulesByProducType.get(productTypeEnum);
                if(tm != null)
                {
                    String msg = tm.getTradingModuleName() + "- already handles ProductType : " + productTypeEnum;
                    m_log.error(msg);
                    throw new AlreadyInstalledException(msg);
                }
            }

            m_tradingModulesByName.put(name, module);
            for(int i = 0; i < productTypes.length; i++)
            {
                ProductTypeEnum productTypeEnum = productTypes[i];
                m_tradingModulesByProducType.put(productTypeEnum, module);
            }

        }
        StringBuffer sb = new StringBuffer();
        ProductTypeEnum productTypes[] = module.getProductTypes();
        for(int i = 0; i < productTypes.length; i++)
        {
            ProductTypeEnum productTypeEnum = productTypes[i];
            sb.append(productTypeEnum.stringValue());
            if(i + 1 < productTypes.length)
                sb.append(",");
        }

        m_log.info("Installed trading module : " + name);
        m_log.info("Trading module : " + name + " registered to handle product types : " + sb.toString());
    }

    public void uninstallTradingModule(String tradingModuleName)
        throws NotInstalledException
    {
        m_log.info("::::::: uninstalling trading module with name : " + tradingModuleName);
        synchronized(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class)
        {
            TradingModule tm = getTradingModule(tradingModuleName);
            ProductTypeEnum productTypesHandled[] = tm.getProductTypes();
            for(int i = 0; i < productTypesHandled.length; i++)
                m_tradingModulesByProducType.remove(productTypesHandled[i]);

            m_tradingModulesByName.remove(tradingModuleName);
        }
    }

    public GeneralizedFinTransactionManager getGeneralizedFinTransactionManager()
    {
        return m_genericFinTranMgr;
    }

    public TradingSystem getTradingSystem()
    {
        return m_tradingSys;
    }

    public FinObjectManager getFinObjectManager()
    {
        return m_finObjectMgr;
    }

    public void overrideAccountManager(AccountManager newAcctMgr)
    {
        m_log.warn("******* Overriding account manager");
        m_acctMgr = newAcctMgr;
    }

    public void overrideTradingSystem(TradingSystem newTradingSys)
    {
        m_log.info("***** Overriding Trading System....");
        m_tradingSys = newTradingSys;
    }

    public void overrideFinObjectManager(FinObjectManager newFinObjectMgr)
    {
        m_log.info("**** Overriding FinObjectManager");
        m_finObjectMgr = newFinObjectMgr;
    }

    public void overrideGeneralizedFinTransactionManager(GeneralizedFinTransactionManager newGenFinTranMgr)
    {
        m_genericFinTranMgr = newGenFinTranMgr;
    }

    public GlobalBizLogicProvider getGlobalBizLogicProvider()
    {
        return m_globalBizLogicProvider;
    }

    public void overrideGlobalBizLogicProvider(GlobalBizLogicProvider newBizLogic)
    {
        m_log.info("**** Overriding GlobalBizLogicProvider with class:" + newBizLogic.getClass());
        m_globalBizLogicProvider = newBizLogic;
    }

    public CommonOrderValidator getCommonOrderValidator()
    {
        return m_commonOrderValidator;
    }

    public void overrideCommonOrderValidator(CommonOrderValidator newCommonOrderValidator)
    {
        m_commonOrderValidator = newCommonOrderValidator;
    }

    private static final Logit m_log;
    private AccountManager m_acctMgr;
    private TradingSystem m_tradingSys;
    private GeneralizedFinTransactionManager m_genericFinTranMgr;
    private FinObjectManager m_finObjectMgr;
    private GlobalBizLogicProvider m_globalBizLogicProvider;
    private CommonOrderValidator m_commonOrderValidator;
    private Map m_tradingModulesByName;
    private Map m_tradingModulesByProducType;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.FinAppImpl.class);
    }
}
