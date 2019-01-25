// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TradingModuleImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptable;
import com.fitechlabs.xtrade.kernel.interceptor.InterceptableBuilder;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AbstractTradingModuleImpl;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeMarketAdapterImpl, EqTypePositionManagerImpl, EqTypeOrderManagerImpl, EqTypeProductManagerImpl, 
//            EqTypeFinTransactionManagerImpl, EqTypeBizLogicProviderImpl, EqTypeOrderManagerInterceptor, EqTypeOrderManagerMethodCallLoggerInterceptor

public class TradingModuleImpl extends AbstractTradingModuleImpl
{

    public TradingModuleImpl()
    {
        m_marketAdapter = new EqTypeMarketAdapterImpl();
        m_positionManager = new EqTypePositionManagerImpl();
        EqTypeOrderManager orderMgrImpl = new EqTypeOrderManagerImpl();
        m_orderManager = getInterceptedEqTypeOrderManager(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager.class, orderMgrImpl);
        m_productManager = new EqTypeProductManagerImpl();
        m_finTranManager = new EqTypeFinTransactionManagerImpl();
        m_bizLogicProvider = new EqTypeBizLogicProviderImpl();
    }

    private EqTypeOrderManager getInterceptedEqTypeOrderManager(Class interfaceType, OrderManager orderMgrImpl)
    {
        Interceptable in = InterceptableBuilder.buildInterceptable(interfaceType, orderMgrImpl);
        in.addInterceptor(new EqTypeOrderManagerInterceptor(m_orderMgrMethodsToIntercept, m_orderMgrMethodsToIntercept4Serializing));
        in.addInterceptor(new EqTypeOrderManagerMethodCallLoggerInterceptor());
        return (EqTypeOrderManager)in;
    }

    public String getTradingModuleName()
    {
        return "eqtype";
    }

    public static EqTypeQuoteDataSupplierService getEqTypeQuoteDataSupplierService()
    {
        if(m_eqtypeQuoteDataSupplierService == null)
            throw new IllegalStateException("QuoteDataSupplierService is not yet installed.");
        else
            return m_eqtypeQuoteDataSupplierService;
    }

    public void overrideBizLogicProvider(BizLogicProvider newBizLogicProvider)
    {
        if(newBizLogicProvider instanceof EqTypeBizLogicProvider)
            m_bizLogicProvider = newBizLogicProvider;
        else
            throw new IllegalArgumentException("BizLogicProvider is not an instanceof EqTypeBizLogicProvider.");
    }

    public void overrideOrderManager(OrderManager newOrderManager)
    {
        overrideOrderManager(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager.class, newOrderManager);
    }

    public void overrideOrderManager(Class interfaceType, OrderManager newOrderManager)
    {
        if(newOrderManager instanceof EqTypeOrderManager)
            m_orderManager = getInterceptedEqTypeOrderManager(interfaceType, newOrderManager);
        else
            throw new IllegalArgumentException("OrderManager is not an instanceof EqTypeOrderManager.");
    }

    public void overridePositionManager(PositionManager newPositionManager)
    {
        if(newPositionManager instanceof EqTypePositionManager)
            m_positionManager = newPositionManager;
        else
            throw new IllegalArgumentException("PositionManager is not an instanceof EqTypePositionManager");
    }

    public void overrideProductManager(ProductManager newProductManager)
    {
        if(newProductManager instanceof EqTypeProductManager)
            m_productManager = newProductManager;
        else
            throw new IllegalArgumentException("ProductManager is not an instanceof EqTypeProductManager");
    }

    public void installQuoteDataSupplierService(QuoteDataSupplierService quoteDataSupplierService)
        throws AlreadyInstalledException
    {
        if(m_quoteDataSupplierService == null)
        {
            if(quoteDataSupplierService instanceof EqTypeQuoteDataSupplierService)
            {
                m_quoteDataSupplierService = quoteDataSupplierService;
                TradingModuleImpl _tmp = this;
                m_eqtypeQuoteDataSupplierService = (EqTypeQuoteDataSupplierService)quoteDataSupplierService;
                m_log.info("Installed QuoteDataSupplierService provided by " + quoteDataSupplierService.getClass().getName());
            } else
            {
                throw new IllegalArgumentException("Arguemet is not of type EqTypeQuoteDataSupplierServive.");
            }
        } else
        {
            throw new AlreadyInstalledException("QuoteDataSupplierService already installed.");
        }
    }

    public ProductTypeEnum[] getProductTypes()
    {
        return PRODUCT_TYPES;
    }

    private static final Logit m_log;
    private static final boolean DBG;
    public static final String TRADING_MODULE_NAME = "eqtype";
    public static final ProductTypeEnum PRODUCT_TYPES[];
    private static EqTypeQuoteDataSupplierService m_eqtypeQuoteDataSupplierService;
    private static final String m_orderMgrMethodsToIntercept[] = {
        "validateCancelOrder", "validateChangeOrder", "validateChangeSettleContractOrder", "validateChangeSwapContractOrder", "validateNewCashBasedOrder", "validateOpenContractOrder", "validateSettleContractOrder", "validateSwapContractOrder", "submitCancelOrder", "submitChangeOrder", 
        "submitChangeSettleContractOrder", "submitChangeSwapContractOrder", "submitNewCashBasedOrder", "submitOpenContractOrder", "submitSettleContractOrder", "submitSwapContractOrder", "validateNewOrder", "submitNewOrder", "validateNewMiniStockOrder", "submitNewMiniStockOrder"
    };
    private static final String m_orderMgrMethodsToIntercept4Serializing[] = {
        "submitCancelOrder", "submitChangeOrder", "submitChangeSettleContractOrder", "submitChangeSwapContractOrder", "submitNewCashBasedOrder", "submitOpenContractOrder", "submitSettleContractOrder", "submitSwapContractOrder", "submitNewOrder", "submitNewMiniStockOrder"
    };

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.TradingModuleImpl.class);
        DBG = m_log.ison();
        PRODUCT_TYPES = (new ProductTypeEnum[] {
            ProductTypeEnum.EQUITY, ProductTypeEnum.CASH
        });
    }
}
