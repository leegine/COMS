head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPOブックビルディング訂正サービス実装クラス(WEB3IPOBookbuildingChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 劉江涛(中訊) 新規作成
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>047,044
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoBookbuildingChangeOrderSpec;
import webbroker3.ipo.WEB3IpoBookbuildingPriceCalcResult;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoOrderValidator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingChangeService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * IPOブックビルディング訂正サービス実装クラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IpoBookbuildingChangeServiceImpl 
    extends WEB3IpoClientRequestService implements WEB3IpoBookbuildingChangeService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingChangeServiceImpl.class);
    
    /**
     * @@roseuid 4112F18F038E
     */
    public WEB3IpoBookbuildingChangeServiceImpl() 
    {
     
    }
    
    /**
     * IPOブックビルディング訂正処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、IPOブックビルディング訂正入力リクエストの場合<BR>
     * 　@－get入力画面()をコールする。<BR>
     * ○ 引数のリクエストデータが、IPOブックビルディング訂正確認リクエストの場合<BR>
     * 　@－validateブックビルディング訂正()をコールする。<BR>
     * ○ 引数のリクエストデータが、IPOブックビルディング訂正完了リクエストの場合<BR>
     * 　@－submitブックビルディング訂正()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D96AD7029F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3IPOBookBuildingChangeInputRequest)
        {
            l_response = getInputScreen((WEB3IPOBookBuildingChangeInputRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingChangeConfirmRequest)
        {
            l_response = validateBookbuildingChange((WEB3IPOBookBuildingChangeConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingChangeCompleteRequest)
        {
            l_response = submitBookbuildingChange((WEB3IPOBookBuildingChangeCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "パラメータの類型が不正、該当するWEB3IPOBookBuildingEnterRequest," + 
                "WEB3IPOProductInfoRequest類型。";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
     
    }
    
    /**
     * (get入力画面)<BR>
     * IPOブックビルディング訂正入力画面表示データ作成処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ訂正）get入力画面」参照。
     * @@param l_request - IPOブックビルディング訂正入力リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeInputResponse
     * @@roseuid 40D969A6006D
     */
    protected WEB3IPOBookBuildingChangeInputResponse getInputScreen(WEB3IPOBookBuildingChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3IpoBookbuildingChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
                     
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
                             (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
       
        //1.2.getOrdervalidater
        WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)l_orderManagerImpl.getOrderValidator();
        
        //1.3.補助口座を取得する 
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.4.validate取引可能顧客
        OrderValidationResult l_result = l_orderValidator.validateSubAccountForTrading(l_subAccount);
        if(!l_result.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
        {
            String l_strErrorMessage = "invalid取引可能顧客.";
            log.error(l_strErrorMessage);
			//2004/11/10 障害管理票No.U00394 出力エラーの修正 坂上@@SRA START    
            throw new WEB3BaseException(
			    l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
//			throw new WEB3BaseException(
//						   WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
//						   this.getClass().getName() + STR_METHOD_NAME,
//						   l_strErrorMessage); 
			//2004/11/10 障害管理票No.U00394 出力エラーの修正 坂上@@SRA END 
        }
        
        //1.5.IPO申告
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3IpoOrderImpl l_ipoOrder;
        try 
        {
            l_ipoOrder =
                (WEB3IpoOrderImpl) l_finApp
                    .getTradingModule(ProductTypeEnum.IPO)
                    .getOrderManager()
                    .getOrderUnit(l_lngOrderId);
 
            //1.6.IPO銘柄を取得する
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl) l_ipoOrder.getProduct();
        
            //1.7.IPO注文チェック::validateブックビルディング銘柄
            l_orderValidator.validateBookbuildingProduct(l_product); 
            
            //1.8.getその他商品買付可能額()
            TradingSystem l_trdSys = l_finApp.getTradingSystem();         
            WEB3TPTradingPowerService l_tpTPS  
                = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            double l_trdPow = l_tpTPS.getOtherTradingPower((WEB3GentradeSubAccount)this.getSubAccount(),l_trdSys.getBizDate() );
        
            //1.9.IPOブックビルディング訂正入力レスポンス::IPOブックビルディング訂正入力レスポンス
            WEB3IPOBookBuildingChangeInputResponse l_bookBulidingChangeInputResponse 
                = (WEB3IPOBookBuildingChangeInputResponse) l_request.createResponse();
        
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_product.getDataSourceObject();
            IpoOrderRow l_IpoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            
            //銘柄コード
            l_bookBulidingChangeInputResponse.productCode = l_ipoProductRow.getProductCode() ;
            
            //銘柄名
            l_bookBulidingChangeInputResponse.productName = l_product.getStandardName();
            
            //公開市場コード
            l_bookBulidingChangeInputResponse.publicOfferingMarketCode = l_ipoProductRow.getPublicMarket();
            
            //仮条件区分
            l_bookBulidingChangeInputResponse.temporaryConditionDiv = l_ipoProductRow.getProvisionalValueDiv();
            
            //仮条件下限値
            if(l_ipoProductRow.getProvisionalMinValueIsNull())
            {
                l_bookBulidingChangeInputResponse.temporaryConditionLower = null;
            }
            else
            {
                l_bookBulidingChangeInputResponse.temporaryConditionLower = WEB3StringTypeUtility.formatNumber(
                    l_ipoProductRow.getProvisionalMinValue());
            }
            
            //仮条件上限値
            if(l_ipoProductRow.getProvisionalMaxValueIsNull())
            {
                l_bookBulidingChangeInputResponse.temporaryConditionUpper = null;
            }
            else
            {
                l_bookBulidingChangeInputResponse.temporaryConditionUpper = WEB3StringTypeUtility.formatNumber(
                    l_ipoProductRow.getProvisionalMaxValue());
            }
            
            //刻み
            if(l_ipoProductRow.getTickValueIsNull())
            {
                l_bookBulidingChangeInputResponse.tickValue = null;
            }
            else
            {
                l_bookBulidingChangeInputResponse.tickValue = WEB3StringTypeUtility.formatNumber(
                    l_ipoProductRow.getTickValue());
            }
            
            //表示用単位区分
            l_bookBulidingChangeInputResponse.displayUnitDiv = l_ipoProductRow.getIpoUnitDiv();
            
            //購入申込単位
            if(!l_ipoProductRow.getLotSizeIsNull())
            {
                l_bookBulidingChangeInputResponse.offerUnit = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getLotSize());
            }
            
            //成行可能
            l_bookBulidingChangeInputResponse.marketOrderFlag = l_ipoProductRow.getEnableMarketOrder();
            
            //出金余力
            l_bookBulidingChangeInputResponse.paymentPower = WEB3StringTypeUtility.formatNumber(l_trdPow);
            
            //申告数量
            l_bookBulidingChangeInputResponse.demandQuantity = 
                WEB3StringTypeUtility.formatNumber(l_ipoOrder.getQuantity());
            
            //申告価格区分
            if(l_ipoOrder.getLimitPrice() == 0)
            {
                l_bookBulidingChangeInputResponse.demandPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_bookBulidingChangeInputResponse.demandPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            
            //申告価格
            l_bookBulidingChangeInputResponse.demandPrice = 
                WEB3StringTypeUtility.formatNumber(l_ipoOrder.getLimitPrice());
            
            //申告相当額
            if(l_IpoOrderRow.getBookbuildingPriceIsNull())
            {
                l_bookBulidingChangeInputResponse.demandEquivalentPrice = null;
            }
            else
            {
                l_bookBulidingChangeInputResponse.demandEquivalentPrice = 
                    WEB3StringTypeUtility.formatNumber(l_IpoOrderRow.getBookbuildingPrice());
            }
        
                   
            log.exiting(STR_METHOD_NAME);
            return l_bookBulidingChangeInputResponse; 

        } 
        catch (NotFoundException l_ex) 
        {
            log.error("データ不整合エラー。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (validateブックビルディング訂正)<BR>
     * IPOブックビルディング訂正確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ訂正）validateブックビルディング訂正」参照。
     * @@param l_request - IPOブックビルディング訂正確認リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeConfirmResponse
     * @@roseuid 40D969A6008C
     */
    protected WEB3IPOBookBuildingChangeConfirmResponse validateBookbuildingChange(WEB3IPOBookBuildingChangeConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = "validateBookbuildingChange(WEB3IpoBookbuildingChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
              
        l_request.validate();
        
        //validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        Trader l_trader = this.getTrader();
        
        //ブックビルディング訂正内容::ブックビルディング訂正内容
        
        if (!WEB3StringTypeUtility.isNumber(l_request.id))
        {
            String l_strErrorMessage = 
                "Ipo order id類型が不正、該当するlong類型。";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage);      
        }
        long l_lngIpoOrderId = Long.parseLong(l_request.id);

        double l_dblOrderQuantity =0;
        if (WEB3StringTypeUtility.isNumber(l_request.demandQuantity))
        {
            l_dblOrderQuantity =Double.parseDouble(l_request.demandQuantity);
        }
        
        double l_orderPrice =0;
        if (WEB3StringTypeUtility.isNumber(l_request.demandPrice))
        {
            l_orderPrice =Double.parseDouble(l_request.demandPrice);
        }
        
        WEB3IpoBookbuildingChangeOrderSpec l_bookBuildingChangeSpe = 
            new WEB3IpoBookbuildingChangeOrderSpec(l_trader, l_lngIpoOrderId, l_dblOrderQuantity, l_orderPrice, 0);

        //補助口座を取得する 
        SubAccount l_subAccount = this.getSubAccount();
        
        //IPO申告マネージャ::validateブックビルディング訂正
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
                             (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        OrderValidationResult l_result = l_orderManagerImpl.validateChangeOrder(l_subAccount, l_bookBuildingChangeSpe);
        if(!l_result.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
        {
            String l_strErrorMessage = "invalidブックビルディング訂正.";
            log.error(l_strErrorMessage);
			//2004/11/10 障害管理票No.U00394 出力エラーの修正 坂上@@SRA START    
			throw new WEB3BaseException(
				l_result.getProcessingResult().getErrorInfo(),
				this.getClass().getName() + STR_METHOD_NAME,
				l_strErrorMessage);
//			throw new WEB3BaseException(
//						   WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
//						   this.getClass().getName() + STR_METHOD_NAME,
//						   l_strErrorMessage); 
			//2004/11/10 障害管理票No.U00394 出力エラーの修正 坂上@@SRA END      
        }

        
        //IPO申告
        WEB3IpoOrderImpl l_ipoOrder;
        try 
        {
            l_ipoOrder =
                (WEB3IpoOrderImpl) l_finApp
                    .getTradingModule(ProductTypeEnum.IPO)
                    .getOrderManager()
                    .getOrderUnit(l_lngIpoOrderId);

            //IPO銘柄を取得する
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl) l_ipoOrder.getProduct();        
            //MainAccountを取得する
            WEB3GentradeMainAccount l_mainAccount  = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();        
            //l_subAccount.getMainAccount();
        
            double l_dblLimitPrice = l_bookBuildingChangeSpe.getLimitPrice();
            double l_dblQuantity = l_bookBuildingChangeSpe.getQuantity();
        
            WEB3IpoBookbuildingPriceCalcResult l_priceCalcResult = 
                l_orderManagerImpl.calcBookbuildingPrice(l_mainAccount, l_product, l_dblLimitPrice, l_dblQuantity, 0);
        

            //申告相当額計算結果::get申告相当額            
            double l_dblBoolbuildingPrice = l_priceCalcResult.getBookbuildingPrice();
            
            //申告相当額計算結果::get基準値（時価）
            double l_dblBasePrice = l_priceCalcResult.getCurrentPrice();
        
            //IPOブックビルディング訂正確認リクエスト::createResponse
            WEB3IPOBookBuildingChangeConfirmResponse l_bookBulidingChangeConfirmResponse 
                = (WEB3IPOBookBuildingChangeConfirmResponse) l_request.createResponse();
                
            //申告相当額
            l_bookBulidingChangeConfirmResponse.demandEquivalentPrice = WEB3StringTypeUtility.formatNumber(l_dblBoolbuildingPrice);
            
            //確認時発注日
            l_bookBulidingChangeConfirmResponse.checkDate = l_datOrderBizDate;
            
            //確認時基準値
            if(!Double.isNaN(l_dblBasePrice))
            {
                l_bookBulidingChangeConfirmResponse.checkValue = WEB3StringTypeUtility.formatNumber(l_dblBasePrice);
            }
            else
            {
                l_bookBulidingChangeConfirmResponse.checkValue = "0";
            }
        
            log.exiting(STR_METHOD_NAME);
            return l_bookBulidingChangeConfirmResponse;
        } 
        catch (NotFoundException l_ex) 
        {
            log.error("データ不整合エラー。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }


    }
    
    /**
     * (submitブックビルディング訂正)<BR>
     * IPOブックビルディング訂正完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ訂正）submitブックビルディング訂正」参照。
     * @@param l_request - IPOブックビルディング訂正完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeCompleteResponse
     * @@roseuid 40D969A6009B
     */
    protected WEB3IPOBookBuildingChangeCompleteResponse submitBookbuildingChange(WEB3IPOBookBuildingChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitBookbuildingChange(WEB3IpoBookbuildingChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        

        l_request.validate();
        //validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        Trader l_trader = this.getTrader();

        //ブックビルディング訂正内容::ブックビルディング訂正内容
        if (!WEB3StringTypeUtility.isNumber(l_request.id))
        {
            String l_strErrorMessage = 
                "Ipo order id類型が不正、該当するlong類型。";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage);      
        }
        long l_lngIpoOrderId = Long.parseLong(l_request.id);
        
        double l_dblOrderQuantity =0;
        if (WEB3StringTypeUtility.isNumber(l_request.demandQuantity))
        {
            l_dblOrderQuantity =Double.parseDouble(l_request.demandQuantity);
        }
        
        double l_dblOrderPrice =0;
        if (WEB3StringTypeUtility.isNumber(l_request.demandPrice))
        {
            l_dblOrderPrice =Double.parseDouble(l_request.demandPrice);
        }
        
        double l_dblCheckValue =0;
        if (WEB3StringTypeUtility.isNumber(l_request.checkValue))
        {
            l_dblCheckValue =Double.parseDouble(l_request.checkValue);
        }
        
        WEB3IpoBookbuildingChangeOrderSpec l_bookBuildingChangeSpe = 
            new WEB3IpoBookbuildingChangeOrderSpec(l_trader, l_lngIpoOrderId, l_dblOrderQuantity, l_dblOrderPrice, l_dblCheckValue);     
               
        //補助口座を取得する 
        SubAccount l_subAccount = this.getSubAccount();
        
        //IPO申告マネージャ::submitブックビルディング訂正
        //l_request.password
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
                             (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        OrderSubmissionResult l_result = l_orderManagerImpl.submitChangeOrder(l_subAccount, l_bookBuildingChangeSpe, l_request.password, false);
        if(!l_result.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
        {
            String l_strErrorMessage = "submitブックビルディング訂正error!";
            log.error(l_strErrorMessage);
			//2004/11/10 障害管理票No.U00394 出力エラーの修正 坂上@@SRA START    
			throw new WEB3BaseException(
				l_result.getProcessingResult().getErrorInfo(),
				this.getClass().getName() + STR_METHOD_NAME,
				l_strErrorMessage);
//			throw new WEB3BaseException(
//						   WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
//						   this.getClass().getName() + STR_METHOD_NAME,
//						   l_strErrorMessage); 
			//2004/11/10 障害管理票No.U00394 出力エラーの修正 坂上@@SRA END  
        }
    
        //IPO申告
        WEB3IpoOrderImpl l_ipoOrder;
        try 
        {
            l_ipoOrder =
                (WEB3IpoOrderImpl) l_finApp
                    .getTradingModule(ProductTypeEnum.IPO)
                    .getOrderManager()
                    .getOrderUnit(l_lngIpoOrderId);

            long l_lastOrderAction = l_ipoOrder.getLastOrderActionId();
        
            WEB3IPOBookBuildingChangeCompleteResponse l_bookBuildingChangeCompleteResponse 
                = (WEB3IPOBookBuildingChangeCompleteResponse) l_request.createResponse();
        
            Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
            
            //更新時間
            l_bookBuildingChangeCompleteResponse.lastUpdatedTimestamp = l_tsCurrentTime;
        
            //識別番号
            l_bookBuildingChangeCompleteResponse.orderActionId = l_request.id;

            log.exiting(STR_METHOD_NAME);
            return l_bookBuildingChangeCompleteResponse;

        } 
        catch (NotFoundException l_ex) 
        {
            log.error("データ不整合エラー。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        

    }
}
@
