head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoDeclineServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO���ރT�[�r�X�N���X(WEB3IpoDeclineServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �d�� (���u) �V�K�쐬
*/
package webbroker3.ipo.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.define.WEB3EnableIpoQuantityChangeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoCancelOrderSpec;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3IPODeclineCompleteRequest;
import webbroker3.ipo.message.WEB3IPODeclineConfirmRequest;
import webbroker3.ipo.message.WEB3IPODeclineConfirmResponse;
import webbroker3.ipo.message.WEB3IPODeclineCompleteResponse;
import webbroker3.ipo.service.delegate.WEB3IpoDeclineService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
* ( IPO���ރT�[�r�X�N���X)<BR>
* 
* @@author �d��
* @@version 1.0
*/

public class WEB3IpoDeclineServiceImpl extends WEB3IpoClientRequestService implements WEB3IpoDeclineService 
{
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminIpoProductRegistrationServiceImpl.class);

    
    /**
     * IPO���ޏ��������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO���ފm�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate����()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�w���\���������N�G�X�g�̏ꍇ<BR> 
     * �@@�|submit����()���R�[������B<BR>   
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40DA5D4501F8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if(l_request instanceof WEB3IPODeclineConfirmRequest)
        {
            WEB3IPODeclineConfirmResponse l_response = validateDecline((WEB3IPODeclineConfirmRequest)l_request);
            log.debug("validate����()���R�[������");
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if(l_request instanceof WEB3IPODeclineCompleteRequest)
        {
            log.debug("submit����()���R�[������");
            WEB3IPODeclineCompleteResponse l_response = submitDecline((WEB3IPODeclineCompleteRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
        
    }

    /**
     * (validate����)<BR>
     * IPO���ފm�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i���ށjvalidate���ށv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * IPO���ފm�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IPODeclineConfirmResponse
     * @@roseuid 40DA5D450208
     */
    protected WEB3IPODeclineConfirmResponse validateDecline(WEB3IPODeclineConfirmRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validateDecline(WEB3IPODeclineConfirmRequest)";
            log.entering(STR_METHOD_NAME);
            
        //1.1validate������t�\( )
        log.debug("validate������t�\");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.2get������( )
        log.debug("get������");
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(); 
               
        //1.3get�㗝���͎�( )
        log.debug("get�㗝���͎�");
        Trader l_trader = this.getTrader();
        
        //1.4���ޓ��e(����, long)
        log.debug("���ޓ��e(����, long)");
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3IpoCancelOrderSpec l_declineSpec = new WEB3IpoCancelOrderSpec(l_trader,l_lngOrderId);
        
        //1.5get�⏕����( )
        log.debug("get�⏕����");    
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.6validate����(SubAccount, ���ޓ��e)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        log.debug("validate����(SubAccount, ���ޓ��e)");
        log.debug("l_subAccount = " + l_subAccount);
        log.debug("l_declineSpec = " + l_declineSpec);
        OrderValidationResult l_orderValidationResult = l_orderManagerImpl.validateDecline(l_subAccount, l_declineSpec);
        
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
        	
        	String l_strErrorMessage = "validate���ރG���[.";
			log.error(l_strErrorMessage); 
	        throw new WEB3BaseException(
				l_orderValidationResult.getProcessingResult().getErrorInfo(),
				this.getClass().getName() + STR_METHOD_NAME,
				l_strErrorMessage);
		 
        }
        
        //1.7IPO�\��(long)
        WEB3IpoOrderImpl l_ipoOrder;
        try
        {
            l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
   
            //1.8getIPO����( )
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_product.getDataSourceObject();
            
            //1.9getInstitution( )
            log.debug("getInstitution");
            InstitutionParams l_institutionParams = (InstitutionParams)l_subAccount.getInstitution().getDataSourceObject();
            
            //1.10createResponse( )
            WEB3IPODeclineConfirmResponse l_declineConfirmResponse = (WEB3IPODeclineConfirmResponse)l_request.createResponse();
            //(*1) �v���p�e�B�Z�b�g 
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();       
            l_declineConfirmResponse.productCode = l_ipoProductRow.getProductCode();
            l_declineConfirmResponse.productName = l_product.getStandardName();
            l_declineConfirmResponse.publicOfferingMarketCode = l_product.getPublicMarket();
            l_declineConfirmResponse.offerUnit = WEB3StringTypeUtility.formatNumber(l_product.getLotSize());
            
            long l_lngElectedQuantity = l_ipoOrderRow.getElectedQuantity();
            l_declineConfirmResponse.prizeQuantity = WEB3StringTypeUtility.formatNumber(l_lngElectedQuantity);
            
            l_declineConfirmResponse.displayUnitDiv = l_ipoProductRow.getIpoUnitDiv(); 
            
            double l_lngPublicPrice = l_ipoProductRow.getPublicPrice();       
            l_declineConfirmResponse.publicOfferingPrice = WEB3StringTypeUtility.formatNumber(l_lngPublicPrice);
            
            l_declineConfirmResponse.offerPrice = WEB3StringTypeUtility.formatNumber(l_lngElectedQuantity * l_lngPublicPrice);
            
            if(WEB3EnableIpoQuantityChangeDef.CAN_CHANGE.equals(l_institutionParams.getEnableIpoQuantityChange()))
            {
                log.debug("l_declineConfirmResponse.enableApplicationQuantityChangeFlag = true;");
                l_declineConfirmResponse.offerQuantityFlag = true;
            }
            else
            {
                log.debug("l_declineConfirmResponse.enableApplicationQuantityChangeFlag = false;");
                l_declineConfirmResponse.offerQuantityFlag = false;
            }
     
            l_declineConfirmResponse.checkDate = l_datOrderBizDate;
            log.exiting(STR_METHOD_NAME);  
            
            return l_declineConfirmResponse;
        }
        catch(NotFoundException l_ex)
        {            
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() +  STR_METHOD_NAME,
                l_ex);            
        }
    }
    
    /**
     * (submit����)<BR>
     * IPO���ފ����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i���ށjsubmit���ށv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * IPO���ފ������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IPODeclineResponse
     * @@roseuid 40DA5D450217
     */
    protected WEB3IPODeclineCompleteResponse submitDecline(WEB3IPODeclineCompleteRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " submitDecline(WEB3IPODeclineCompleteRequest)";
        log.entering(STR_METHOD_NAME);
                
        //1.1.validate������t�\( )
        log.debug("validate������t�\");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.2.get������( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.3.get�㗝���͎�( )
        Trader l_trader = this.getTrader();
        long l_lngOrderId = Long.parseLong(l_request.id);
        
        //1.4.���ޓ��e(����, long)
        WEB3IpoCancelOrderSpec l_declineSpec = new WEB3IpoCancelOrderSpec(l_trader,l_lngOrderId);
        
        //1.5.get�⏕����( )
        log.debug("get�⏕����");
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.6.submit����(SubAccount, ���ޓ��e, String, boolean)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        log.debug("submit����(SubAccount, ���ޓ��e, String, boolean)");
        OrderSubmissionResult l_orderSubmissionResult = l_orderManagerImpl.submitDecline
            (l_subAccount, l_declineSpec, l_request.password, false);
        
        if(l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
        	
				String l_strErrorMessage = "submit���ރG���[.";
				log.error(l_strErrorMessage); 
				throw new WEB3BaseException(
					l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
					this.getClass().getName() + STR_METHOD_NAME,
					l_strErrorMessage);
		 
        }
        
        //1.7.�]�͍Čv�Z()
        WEB3TPTradingPowerService l_tpTPS  
            = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        l_tpTPS.reCalcTradingPower((WEB3GentradeSubAccount) l_subAccount);
                
        //1.8.IPO�\��(long)
        WEB3IpoOrderImpl l_ipoOrder;
        try
        {
            l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);

            //getIPO�\���h�c( )  
            long l_lngIpoOrderId = l_ipoOrder.getOrderId();
            
            //createResponse( )    
            WEB3IPODeclineCompleteResponse l_declineResponse = (WEB3IPODeclineCompleteResponse)l_request.createResponse();
            //(*1) �v���p�e�B�Z�b�g
            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
            l_declineResponse.lastUpdatedTimestamp = l_tradingSystem.getSystemTimestamp();
            l_declineResponse.orderActionId = WEB3StringTypeUtility.formatNumber(l_lngIpoOrderId);
            
            log.exiting(STR_METHOD_NAME);
            return l_declineResponse;
        }
        catch(NotFoundException l_ex)
        {           
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() +  STR_METHOD_NAME,
                l_ex);
            
        }
    
    }
}

@
