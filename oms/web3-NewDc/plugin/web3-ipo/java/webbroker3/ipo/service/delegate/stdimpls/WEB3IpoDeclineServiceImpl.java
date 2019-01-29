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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO辞退サービスクラス(WEB3IpoDeclineServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 彭巍 (中訊) 新規作成
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
* ( IPO辞退サービスクラス)<BR>
* 
* @@author 彭巍
* @@version 1.0
*/

public class WEB3IpoDeclineServiceImpl extends WEB3IpoClientRequestService implements WEB3IpoDeclineService 
{
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminIpoProductRegistrationServiceImpl.class);

    
    /**
     * IPO辞退処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、IPO辞退確認リクエストの場合<BR>
     * 　@－validate辞退()をコールする。<BR>
     * ○ 引数のリクエストデータが、IPO購入申込完了リクエストの場合<BR> 
     * 　@－submit辞退()をコールする。<BR>   
     * @@param l_request - リクエスト
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
            log.debug("validate辞退()をコールする");
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if(l_request instanceof WEB3IPODeclineCompleteRequest)
        {
            log.debug("submit辞退()をコールする");
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
     * (validate辞退)<BR>
     * IPO辞退確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（辞退）validate辞退」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO辞退確認リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IPODeclineConfirmResponse
     * @@roseuid 40DA5D450208
     */
    protected WEB3IPODeclineConfirmResponse validateDecline(WEB3IPODeclineConfirmRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validateDecline(WEB3IPODeclineConfirmRequest)";
            log.entering(STR_METHOD_NAME);
            
        //1.1validate注文受付可能( )
        log.debug("validate注文受付可能");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.2get発注日( )
        log.debug("get発注日");
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(); 
               
        //1.3get代理入力者( )
        log.debug("get代理入力者");
        Trader l_trader = this.getTrader();
        
        //1.4辞退内容(扱者, long)
        log.debug("辞退内容(扱者, long)");
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3IpoCancelOrderSpec l_declineSpec = new WEB3IpoCancelOrderSpec(l_trader,l_lngOrderId);
        
        //1.5get補助口座( )
        log.debug("get補助口座");    
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.6validate辞退(SubAccount, 辞退内容)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        log.debug("validate辞退(SubAccount, 辞退内容)");
        log.debug("l_subAccount = " + l_subAccount);
        log.debug("l_declineSpec = " + l_declineSpec);
        OrderValidationResult l_orderValidationResult = l_orderManagerImpl.validateDecline(l_subAccount, l_declineSpec);
        
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
        	
        	String l_strErrorMessage = "validate辞退エラー.";
			log.error(l_strErrorMessage); 
	        throw new WEB3BaseException(
				l_orderValidationResult.getProcessingResult().getErrorInfo(),
				this.getClass().getName() + STR_METHOD_NAME,
				l_strErrorMessage);
		 
        }
        
        //1.7IPO申告(long)
        WEB3IpoOrderImpl l_ipoOrder;
        try
        {
            l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
   
            //1.8getIPO銘柄( )
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_product.getDataSourceObject();
            
            //1.9getInstitution( )
            log.debug("getInstitution");
            InstitutionParams l_institutionParams = (InstitutionParams)l_subAccount.getInstitution().getDataSourceObject();
            
            //1.10createResponse( )
            WEB3IPODeclineConfirmResponse l_declineConfirmResponse = (WEB3IPODeclineConfirmResponse)l_request.createResponse();
            //(*1) プロパティセット 
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
     * (submit辞退)<BR>
     * IPO辞退完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（辞退）submit辞退」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO辞退完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IPODeclineResponse
     * @@roseuid 40DA5D450217
     */
    protected WEB3IPODeclineCompleteResponse submitDecline(WEB3IPODeclineCompleteRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " submitDecline(WEB3IPODeclineCompleteRequest)";
        log.entering(STR_METHOD_NAME);
                
        //1.1.validate注文受付可能( )
        log.debug("validate注文受付可能");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.2.get発注日( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.3.get代理入力者( )
        Trader l_trader = this.getTrader();
        long l_lngOrderId = Long.parseLong(l_request.id);
        
        //1.4.辞退内容(扱者, long)
        WEB3IpoCancelOrderSpec l_declineSpec = new WEB3IpoCancelOrderSpec(l_trader,l_lngOrderId);
        
        //1.5.get補助口座( )
        log.debug("get補助口座");
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.6.submit辞退(SubAccount, 辞退内容, String, boolean)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        log.debug("submit辞退(SubAccount, 辞退内容, String, boolean)");
        OrderSubmissionResult l_orderSubmissionResult = l_orderManagerImpl.submitDecline
            (l_subAccount, l_declineSpec, l_request.password, false);
        
        if(l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
        	
				String l_strErrorMessage = "submit辞退エラー.";
				log.error(l_strErrorMessage); 
				throw new WEB3BaseException(
					l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
					this.getClass().getName() + STR_METHOD_NAME,
					l_strErrorMessage);
		 
        }
        
        //1.7.余力再計算()
        WEB3TPTradingPowerService l_tpTPS  
            = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        l_tpTPS.reCalcTradingPower((WEB3GentradeSubAccount) l_subAccount);
                
        //1.8.IPO申告(long)
        WEB3IpoOrderImpl l_ipoOrder;
        try
        {
            l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);

            //getIPO申告ＩＤ( )  
            long l_lngIpoOrderId = l_ipoOrder.getOrderId();
            
            //createResponse( )    
            WEB3IPODeclineCompleteResponse l_declineResponse = (WEB3IPODeclineCompleteResponse)l_request.createResponse();
            //(*1) プロパティセット
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
