head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoTradingModule.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張取引モジュール(WEB3IpoTradingModule.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
              001: 2004/10/14 張威 (中訊) 対応名称  コードチェック指摘事項(IPOV1.0-20040928ベース)
*/

package webbroker3.ipo;

import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.AlreadyInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PositionManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteDataSupplierService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AbstractTradingModuleImpl;


/**
 * （拡張取引モジュール）<BR>
 *<BR>
 * xTradeのTradingModuleを拡張したクラス。<BR>
 *<BR>                                                            
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IpoTradingModule extends AbstractTradingModuleImpl
{

    /**
     * ログ出力ユーティリティ。
     */
    public static final WEB3LogUtility log;
        
    public static final ProductTypeEnum PRODUCT_TYPES[];

    //コードチェック指摘事項(IPOV1.0-20040928ベース) No. 4
    private static WEB3QuoteDataSupplierService m_eqtypeQuoteDataSupplierService;
    
    static 
    {
        log = WEB3LogUtility.getInstance(WEB3IpoTradingModule.class);
        PRODUCT_TYPES = new ProductTypeEnum[] { ProductTypeEnum.IPO };
    }
    
    /**
     * コンストラクタ。<BR>
     * トランザクションマネージャにWEB3IfoFinTransactionManagerImplを<BR>
     * 設定する。<BR>
     */
    public WEB3IpoTradingModule()
    {
        m_orderManager = new WEB3IpoOrderManagerImpl();
        m_productManager = new WEB3IpoProductManagerImpl();
    }
        
    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule#getProductTypes()
     */
    public ProductTypeEnum[] getProductTypes()
    {
        return PRODUCT_TYPES;
    }
    
    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule#getTradingModuleName()
     */
    public String getTradingModuleName()
    {        
        return "web3-ipo";
    }
    
    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule#overrideBizLogicProvider(com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider)
     */
    public void overrideBizLogicProvider(BizLogicProvider l_bizLogicProvider)
    {
        
    }
    
    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule#overrideOrderManager(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager)
     */
    public void overrideOrderManager(OrderManager l_orderManager)
    {
        if (l_orderManager instanceof WEB3IpoOrderManagerImpl)
        {
            m_orderManager = l_orderManager;
        }             
        else
        {
            throw new IllegalArgumentException("OrderManager is not an instanceof WEB3IPODemandManagerImpl");
        }
    }
    
    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule#overrideOrderManager(java.lang.Class, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager)
     */

    public void overrideOrderManager(Class l_interfaceType, OrderManager l_orderManager)
    {
            
    }
    
    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule#overridePositionManager(com.fitechlabs.xtrade.plugin.tc.gentrade.PositionManager)
     */
    public void overridePositionManager(PositionManager l_positionManager)
    {
        
    }
    
    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule#overrideProductManager(com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager)
     */
    public void overrideProductManager(ProductManager l_productManager)
    {
        if (l_productManager instanceof WEB3IpoProductManagerImpl)
        {
            m_productManager = l_productManager;
        }            
        else
        {
            throw new IllegalArgumentException("ProductManager is not an instanceof WEB3IpoProductManagerImpl");
        }            
    }
    
    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule#installQuoteDataSupplierService(com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteDataSupplierService)
     */
    public void installQuoteDataSupplierService(QuoteDataSupplierService l_quoteDataSupplierService) throws AlreadyInstalledException
    {
        if(m_quoteDataSupplierService == null)
        {
            //コードチェック指摘事項(IPOV1.0-20040928ベース) No. 4
            if(l_quoteDataSupplierService instanceof WEB3QuoteDataSupplierService)
            {
                m_quoteDataSupplierService = l_quoteDataSupplierService;
                WEB3IpoTradingModule _tmp = this;
                m_eqtypeQuoteDataSupplierService = (WEB3QuoteDataSupplierService)l_quoteDataSupplierService;
                log.debug("Installed QuoteDataSupplierService provided by " + l_quoteDataSupplierService.getClass().getName());
            } else
            {
                throw new IllegalArgumentException("Arguemet is not of type EqTypeQuoteDataSupplierServive.");
            }
        } else
        {
            throw new AlreadyInstalledException("QuoteDataSupplierService already installed.");
        }
    }
}@
