head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング取消サービス実装クラス(WEB3IpoBookbuildingCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>047
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoBookbuildingCancelOrderSpec;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingCancelService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPOブックビルディング取消サービス実装クラス
 *                                                                
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IpoBookbuildingCancelServiceImpl 
    extends WEB3IpoClientRequestService implements WEB3IpoBookbuildingCancelService 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingCancelServiceImpl.class);           
     
    /**
     * @@roseuid 4112F18F0276
     */
    public WEB3IpoBookbuildingCancelServiceImpl() 
    {
     
    }
    
    /**
     * IPOブックビルディング取消処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、IPOブックビルディング取消確認リクエストの場合<BR>
     * 　@－validateブックビルディング取消()をコールする。<BR>
     * ○ 引数のリクエストデータが、IPOブックビルディング取消完了リクエストの場合<BR>
     * 　@－submitブックビルディング取消()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D93482014F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3IPOBookBuildingCancelConfirmRequest)
        {
            //IPOブックビルディング取消確認リクエストの場合
            l_response = validateBookbuildingCancel(
                (WEB3IPOBookBuildingCancelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingCancelCompleteRequest)
        {
            //IPOブックビルディング取消完了リクエストの場合
            l_response = submitBookbuildingCancel(
                (WEB3IPOBookBuildingCancelCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "パラメータの類型が不正、該当するWEB3IPOBookBuildingCancelConfirmRequest," + 
                "WEB3IPOBookBuildingCancelCompleteRequest類型。";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validateブックビルディング取消)<BR>
     * IPOブックビルディング取消確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ取消）validateブックビルディング取消」参照。<BR>
     * @@param l_request - IPOブックビルディング取消確認リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingCancelConfirmResponse
     * @@roseuid 40D93482015F
     */
    protected WEB3IPOBookBuildingCancelConfirmResponse validateBookbuildingCancel(
        WEB3IPOBookBuildingCancelConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateBookbuildingCancel(WEB3IpoBookbuildingCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3IPOBookBuildingCancelConfirmResponse l_response= null;
        try
        {
            //1.1validate注文受付可能
            WEB3GentradeTradingTimeManagement.validateOrderAccept();//WEB3BaseException
            
            //1.2get発注日
            Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();//WEB3SystemLayerException
        
            //1.3get代理入力者
            Trader l_trader = this.getTrader();//WEB3SystemLayerException
        
            //1.4ブックビルディング取消内容
            long l_lngIpoOrderId = Long.parseLong(l_request.id);
            WEB3IpoBookbuildingCancelOrderSpec l_cancelOrderSpec = 
                new WEB3IpoBookbuildingCancelOrderSpec(l_trader, l_lngIpoOrderId);
                        
            //1.5get補助口座
            SubAccount l_subAccount = this.getSubAccount();//WEB3BaseException

            //1.6validateブックビルディング取消
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
                 
            OrderValidationResult l_result = l_ipoOrderManager.validateCancelOrder(l_subAccount,l_cancelOrderSpec);
            if(!l_result.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
            {
                String l_strErrorMessage = "invalidブックビルディング取消.";
                log.error(l_strErrorMessage);
                throw new WEB3BaseException(
                    // 2004/11/15 U00413 ビジネスエラーをレスポンスにセットするように修正 水落@@SRA START 
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                    l_result.getProcessingResult().getErrorInfo(),
                    // 2004/11/15 U00413 ビジネスエラーをレスポンスにセットするように修正 水落@@SRA START
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);  
            }

            //1.7getIPO申告
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_ipoOrderManager.getOrderUnit(l_lngIpoOrderId);//NotFoundException
            
            //1.8getIPO銘柄
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            
            //1.9createResponse
            l_response = (WEB3IPOBookBuildingCancelConfirmResponse)l_request.createResponse();
            
            //1.10プロパティセット
            
            IpoProductRow l_productRow = (IpoProductRow)l_product.getDataSourceObject();
            //銘柄コード
            l_response.productCode = l_productRow.getProductCode();
            
            //銘柄名
            l_response.productName = l_product.getStandardName();
            
            //公開市場コード
            l_response.publicOfferingMarketCode = l_product.getPublicMarket();
            
            //購入申込単位
            l_response.offerUnit = WEB3StringTypeUtility.formatNumber(
                l_product.getLotSize());
                
            //仮条件区分
            l_response.temporaryConditionDiv = l_productRow.getProvisionalValueDiv();
            
            //仮条件上限値
            l_response.temporaryConditionUpper = WEB3StringTypeUtility.formatNumber(
                l_productRow.getProvisionalMaxValue());
            //仮条件下限値
            l_response.temporaryConditionLower = WEB3StringTypeUtility.formatNumber(
                l_productRow.getProvisionalMinValue());
            
            //刻み
            l_response.tickValue = WEB3StringTypeUtility.formatNumber(
                l_productRow.getTickValue());
                
            //表示用単位区分
            l_response.displayUnitDiv = l_productRow.getIpoUnitDiv();
            
            //成行可能
            l_response.marketOrderFlag = l_productRow.getEnableMarketOrder();
            
            //申告数量
            double l_dblQuantity = l_ipoOrder.getQuantity();
            if(Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D; 
            }
            l_response.demandQuantity = WEB3StringTypeUtility.formatNumber(
                l_dblQuantity);
            
            //申告価格区分
            if(l_ipoOrder.getLimitPrice() == 0)
            {
                l_response.demandPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_response.demandPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            
            //申告価格
            l_response.demandPrice = WEB3StringTypeUtility.formatNumber(
                l_ipoOrder.getLimitPrice());
                
            //申告相当額
            IpoOrderRow l_orderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            if(l_orderRow.getBookbuildingPriceIsNull())
            {
                l_response.demandEquivalentPrice = null;
            }
            else
            {
                l_response.demandEquivalentPrice = WEB3StringTypeUtility.formatNumber(l_orderRow.getBookbuildingPrice());
            }
            
            //確認時発注日
            l_response.checkDate = l_bizDate;
        }
        catch (NotFoundException l_e)
        {
            log.error(STR_METHOD_NAME, l_e);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (submitブックビルディング取消)<BR>
     * IPOブックビルディング取消完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ取消）submitブックビルディング取消」参照。
     * @@param l_request - IPOブックビルディング取消完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingCancelCompleteResponse
     * @@roseuid 40D93482017E
     */
    protected WEB3IPOBookBuildingCancelCompleteResponse submitBookbuildingCancel(
        WEB3IPOBookBuildingCancelCompleteRequest l_request)
        throws WEB3BaseException     
    {
        final String STR_METHOD_NAME = " submitBookbuildingCancel(WEB3IpoBookbuildingCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME );
           
        WEB3IPOBookBuildingCancelCompleteResponse l_response= null;
        try
        {
            //1.1validate注文受付可能
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //1.2get発注日
            Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
           
            //1.3get代理入力者
            Trader l_trader = this.getTrader();
           
            //1.4ブックビルディング取消内容
            long l_lngIpoOrderId = Long.parseLong(l_request.id);
            WEB3IpoBookbuildingCancelOrderSpec l_cancelOrderSpec = 
                new WEB3IpoBookbuildingCancelOrderSpec(l_trader, l_lngIpoOrderId);
                           
            //1.5get補助口座
            SubAccount l_subAccount = this.getSubAccount();
      
            //1.6submitブックビルディング取消
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
                
            OrderSubmissionResult l_result = l_ipoOrderManager.submitCancelOrder(
                l_subAccount, 
                l_cancelOrderSpec, 
                l_request.password,
                false);
                
            if(!l_result.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
            {
                String l_strErrorMessage = "submitブックビルディング取消error!";
                log.error(l_strErrorMessage);
                throw new WEB3BaseException(
                    // 2004/11/15 U00413 ビジネスエラーをレスポンスにセットするように修正 水落@@SRA START
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    l_result.getProcessingResult().getErrorInfo(),
                    // 2004/11/15 U00413 ビジネスエラーをレスポンスにセットするように修正 水落@@SRA START 
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);  
            }
      
            //1.7getIPO申告
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_ipoOrderManager.getOrderUnit(l_lngIpoOrderId);
               
            //1.8get最新履歴ＩＤ
            long l_lngNewActionID = l_ipoOrder.getLastOrderActionId();            
               
            //1.9createResponse
            l_response = new WEB3IPOBookBuildingCancelCompleteResponse();
               
            //1.10プロパティセット
            
            //更新時間
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            l_response.lastUpdatedTimestamp = l_tradingSys.getSystemTimestamp();;
            
            //識別番号
            l_response.orderActionId = l_request.id;
        }
        catch (NotFoundException l_e)
        {
            log.error(STR_METHOD_NAME, l_e);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
            
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
}
@
