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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g��������W���[��(WEB3IpoTradingModule.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
              001: 2004/10/14 ���� (���u) �Ή�����  �R�[�h�`�F�b�N�w�E����(IPOV1.0-20040928�x�[�X)
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
 * �i�g��������W���[���j<BR>
 *<BR>
 * xTrade��TradingModule���g�������N���X�B<BR>
 *<BR>                                                            
 * @@author ����
 * @@version 1.0
 */
public class WEB3IpoTradingModule extends AbstractTradingModuleImpl
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    public static final WEB3LogUtility log;
        
    public static final ProductTypeEnum PRODUCT_TYPES[];

    //�R�[�h�`�F�b�N�w�E����(IPOV1.0-20040928�x�[�X) No. 4
    private static WEB3QuoteDataSupplierService m_eqtypeQuoteDataSupplierService;
    
    static 
    {
        log = WEB3LogUtility.getInstance(WEB3IpoTradingModule.class);
        PRODUCT_TYPES = new ProductTypeEnum[] { ProductTypeEnum.IPO };
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �g�����U�N�V�����}�l�[�W����WEB3IfoFinTransactionManagerImpl��<BR>
     * �ݒ肷��B<BR>
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
            //�R�[�h�`�F�b�N�w�E����(IPOV1.0-20040928�x�[�X) No. 4
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
