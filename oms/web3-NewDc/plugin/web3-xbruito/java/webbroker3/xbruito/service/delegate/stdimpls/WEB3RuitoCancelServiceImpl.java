head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投取消サービスImpl (WEB3RuitoCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 李志強 (中訊) 新規作成
                   2004/12/06 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoCancelDescitionInterceptor;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelService;

/**
 * 累積投資取消サービス実装クラス<BR>
 */

public class WEB3RuitoCancelServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoCancelService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoCancelServiceImpl.class);

    /**
     * 累積投資取消サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate取消()、submit取消()<BR>
     * メソッドのいずれかをコールする。<BR>
     * @@param l_request - リクエストデータ <BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40581C5900CD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        if (l_request instanceof WEB3RuitoCancelConfirmRequest)
        {
            log.exiting(STR_METHOD_NAME);
            return this.validateCancel(
                (WEB3RuitoCancelConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3RuitoCancelCompleteRequest)
        {
            log.exiting(STR_METHOD_NAME);
            return this.submitCancel(
                (WEB3RuitoCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[入力値が不正です]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
    }

    /**
     * (validate取消)
     * 累積投資取消審査を行う。<BR>
     * <BR>
     * シーケンス図「累投取消／(累投)取消審査」参照<BR>
     * <BR>
     * @@param l_request - 累積投資取消確認リクエストオブジェクト <BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoCancelConfirmResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40581CA40050
     */
    protected WEB3RuitoCancelConfirmResponse validateCancel
        (WEB3RuitoCancelConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancel" + "(WEB3RuitoCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        //1.1　@リクエストデータチェック
        l_request.validate();

        //1.2　@補助口座取得
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.3　@顧客別取引停止属性チェック
        //－FinApp.getCommonOrderValidator()をコールし、注文チェックオブジェクトを取得する。
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        //1.4 －注文チェック.validate取引可能顧客()をコールする。
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        //－チェックエラーの場合はを例外をスローする。
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引停止顧客エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.5　@1.6 注文単位オブジェクトの取得        
        WEB3RuitoOrderManager l_ruitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getOrderManager();
        
        RuitoOrderUnitParams l_ruitoOrderUnitParams = null;
        long l_lngOrderId = Long.parseLong(l_request.id);
        log.debug("l_lngOrderId = " + l_lngOrderId);
        
       
        //累投注文単位オブジェクトの配列を取得する。
        OrderUnit l_orderUnit[] = 
            l_ruitoOrderManager.getOrderUnits(l_lngOrderId);
        //累投注文単位Paramsを取得する。
        l_ruitoOrderUnitParams =
            (RuitoOrderUnitParams) l_orderUnit[0].getDataSourceObject();
        
        //1.8　@拡張累投銘柄を取得する。 
        WEB3RuitoProduct l_ruitoProduct = null; //拡張累投銘柄
        WEB3RuitoProductManager l_ruitoProductManager =
            (WEB3RuitoProductManager)l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getProductManager();
        
        long l_lngProductId = l_ruitoOrderUnitParams.getProductId();
        log.debug("ProductId = " + l_lngProductId);
        try
        {
            l_ruitoProduct = (WEB3RuitoProduct)l_ruitoProductManager.getRuitoProduct(
                l_lngProductId); 
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        //1.9　@受付時間チェック、システム取引停止チェック
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.10　@累投取消注文内容オブジェクトを生成する。
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(l_lngOrderId);
        
        log.debug("l_cancelOrderSpec.getOrderId = " + l_cancelOrderSpec.getOrderId());
        
        //1.11　@発注審査を行う。  
        l_orderValidationResult = 
            l_ruitoOrderManager.validateCancelOrder(
                l_subAccount,
                l_cancelOrderSpec);
                                            
        boolean isSuccessfulResult = 
            l_orderValidationResult.getProcessingResult().isSuccessfulResult();
        log.debug("isSuccessfulResult = " + isSuccessfulResult);
        
        if (!isSuccessfulResult)
        {
            log.debug("__ValidateCancelOrderError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //銘柄名を取得する。 
        String l_productName = l_ruitoProduct.getProductName();
        log.debug("拡張累投銘柄.get銘柄名() = " + l_productName);
        
        //注文数量の取得 
        double l_ruitoOrderQuantity = l_ruitoOrderUnitParams.getQuantity();
        log.debug("注文数量の取得 = " + l_ruitoOrderQuantity);
        
        //注文数量区分の取得 
        String l_ruitoOrderQuantityType = null; //注文数量区分
        //注文数量タイプを取得する。
        QuantityTypeEnum l_orderQuantityType =
            l_ruitoOrderUnitParams.getQuantityType();
        log.debug("注文数量区分の取得 = " + l_orderQuantityType);
        
        //注文数量タイプを取得する。
        if (QuantityTypeEnum.QUANTITY.equals(l_orderQuantityType))
        {
            log.debug("累投注文単位.注文数量タイプ='1:口数'の場合");
            l_ruitoOrderQuantityType = WEB3SellDivDef.COUNT_DESIGNATE;
        } 
        else if (QuantityTypeEnum.AMOUNT.equals(l_orderQuantityType))
        {
            log.debug("累投注文単位.注文数量タイプ='2:金額'の場合");
            l_ruitoOrderQuantityType = WEB3SellDivDef.MONEY_DESIGNATE;
        }
        
        log.debug("注文数量タイプ = " + l_ruitoOrderQuantityType);

        //売買区分の取得
        String l_ruitoSellDiv = null;
        OrderTypeEnum l_orderType = l_ruitoOrderUnitParams.getOrderType();
        log.debug("売買区分の取得 = " + l_orderType);
        
        //注文種別を取得する
        if (OrderTypeEnum.RUITO_SELL.equals(l_orderType))
        {
            log.debug("注文種別 == OrderTypeEnum.RUITO_SELLの場合");
            //累投解約区分
            l_ruitoSellDiv = l_ruitoOrderUnitParams.getGpSellDiv();
        }
        
        log.debug("注文種別を取得 = " + l_ruitoSellDiv);

        //1.12 発注日の取得   
        Date l_orderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("発注日の取得 = " + l_orderBizDate);

        //累投取消確認レスポンスオブジェクトを生成し、リターンする。  

        WEB3RuitoCancelConfirmResponse l_response =
            (WEB3RuitoCancelConfirmResponse)l_request.createResponse();

        //銘柄名：
        l_response.ruitoProductName = l_productName;
        // 注文数量区分： 取得した注文数量区分
        l_response.ruitoOrderQuantityType = l_ruitoOrderQuantityType;

        //注文数量： 取得した注文数量
        l_response.ruitoOrderQuantity = 
            WEB3StringTypeUtility.formatNumber(l_ruitoOrderQuantity);

        //売買区分： 
        String l_ruitoDealingType = null;

        log.debug("l_orderType = " + l_orderType);
        if (OrderTypeEnum.RUITO_BUY.equals(l_orderType))
        {
            log.debug("注文種別 == OrderTypeEnum.RUITO_BUYの場合");
            l_ruitoDealingType = WEB3BuySellTypeDef.BUY;
        }
        else
        {
            log.debug("注文種別 != OrderTypeEnum.RUITO_BUYの場合");
            l_ruitoDealingType = l_ruitoSellDiv;
        }
        log.debug("l_ruitoDealingType = " + l_ruitoDealingType);
        l_response.ruitoDealingType = l_ruitoDealingType;
        
        //確認時発注日： 取得した発注日を設定する。
        l_response.checkDate = l_orderBizDate;
        
        // 解約注文取消（注文単位.注文種別＝累投売注文）の場合、
        // 取引余力チェック処理を行う。
        if (OrderTypeEnum.RUITO_SELL.equals(l_orderType))
        {
            // －累投取消確定インタセプタオブジェクトを生成する。
            WEB3RuitoCancelDescitionInterceptor l_ruitoCancelInterceptor =
                new WEB3RuitoCancelDescitionInterceptor();
        
            //－取引余力サービスを取得してvalidate取引余力( )をコールし
            //  取引余力結果オブジェクトを取得する。
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
        
            WEB3GentradeSubAccount l_gentradeSubAccount = 
                (WEB3GentradeSubAccount)l_subAccount;
        
            Object l_orderSpecIntercepters[] = new Object[1];
            l_orderSpecIntercepters[0] = l_ruitoCancelInterceptor;
        
            Object[] l_orderSpecs = new Object[1];
            l_orderSpecs[0] = l_cancelOrderSpec;
        
            WEB3TPTradingPowerResult l_tpTradingPowerResult = 
                l_tpTradingPowerService.validateTradingPower(
                    l_gentradeSubAccount,
                    l_orderSpecIntercepters,
                    l_orderSpecs,
                    OrderTypeEnum.RUITO_SELL,
                    false);
        
            //－取得した取引余力結果オブジェクト.is判定フラグ( )＝falseの場合、 
            //[取引余力チェックエラー]として例外をスローする。
            if (!l_tpTradingPowerResult.isResultFlg())
            {
                log.debug("取引余力チェックエラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    getClass().getName() + "." + STR_METHOD_NAME, 
                    "取引余力チェックエラー");
            }
        }
               
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit取消)
     * 累積投資取消登録を行う。<BR>
     * <BR>
     * シーケンス図「累投取消／(累投)取消登録」参照 <BR>
     * <BR>
     * @@param l_request - 累積投資取消完了リクエストオブジェクト <BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoCancelCompleteResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40581CE000FC
     */
    protected WEB3RuitoCancelCompleteResponse submitCancel(
        WEB3RuitoCancelCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitCancel(WEB3RuitoCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        
        //1.1　@リクエストデータチェック 
        l_request.validate();
        
        //1.2　@発注日取得 
        WEB3GentradeTradingTimeManagement.getOrderBizDate(
            l_request.checkDate);

        //1.3　@補助口座取得
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.4　@顧客別取引停止属性チェック
        //－FinApp.getCommonOrderValidator()をコールし、注文チェックオブジェクトを取得する。
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        //1.5 －注文チェック.validate取引可能顧客()をコールする。
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        //－チェックエラーの場合はを例外をスローする。
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引停止顧客エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.6 －this.get代理入力者( )をコールし、代理入力者オブジェクトを取得する。
        Trader l_trader = this.getTrader();        
        
        //1.7 －validate取引パスワード( )をコールする。
        log.debug("リクエストデータ.暗証番号 = " + l_request.password);
        l_orderValidationResult = l_orderValidator.validateTradingPassword(
                l_trader,
                l_subAccount,
                l_request.password);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引パスワード不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3RuitoOrderManager l_ruitoOrderManager =
            (WEB3RuitoOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();

        RuitoOrderUnitParams l_ruitoOrderUnitParams = null;

        long l_lngOrderId = Long.parseLong(l_request.id);
        log.debug("l_lngOrderId = " + l_lngOrderId);

        OrderUnit[] l_orderUnits = 
            l_ruitoOrderManager.getOrderUnits(l_lngOrderId);
        log.debug("l_orderUit.length = " + l_orderUnits.length);
        
        //累投注文単位Paramsを取得する。
        l_ruitoOrderUnitParams =
              (RuitoOrderUnitParams) l_orderUnits[0].getDataSourceObject();
        log.debug("l_ruitoOrderUnitParams = " + l_ruitoOrderUnitParams);
        
        //1.11　@拡張累投銘柄を取得する 
        WEB3RuitoProductManager l_ruitoProductManager =
              (WEB3RuitoProductManager) 
              l_finApp.getTradingModule(ProductTypeEnum.RUITO).getProductManager();
        log.debug("ProductId = " + l_ruitoOrderUnitParams.getProductId());
        
        try
        {                        
            l_ruitoProductManager.getRuitoProduct(l_ruitoOrderUnitParams.getProductId());

        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
        
        //　@受付時間チェック、システム取引停止チェック 
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.13　@CancelOrderSpecオブジェクトを生成する。 
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngOrderId);
            
        //1.14　@発注審査
        l_orderValidationResult =
            l_ruitoOrderManager.validateCancelOrder(
                l_subAccount,
                l_cancelOrderSpec);
                                            
        boolean isSuccessfulResult = 
            l_orderValidationResult.getProcessingResult().isSuccessfulResult();
        log.debug("isSuccessfulResult = " + isSuccessfulResult);
        
        if (!isSuccessfulResult)
        {
            log.debug("__ValidateCancelOrderError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
         
        //1.15　@累投市場リクエスト送信サービスに、市場送信処理を実施するという設定を行う。
        MarketAdapter l_marketAdapter = 
            l_finApp.getTradingModule(ProductTypeEnum.RUITO).getMarketAdapter();
        WEB3RuitoMarketRequestSubmitServiceImpl l_web3RuitoMarketRequestSubmitService =
            (WEB3RuitoMarketRequestSubmitServiceImpl)l_marketAdapter.getMarketRequestSenderServce();

        l_web3RuitoMarketRequestSubmitService.setMarketSubmit(true);

        //1.16　@累投取消確定インタセプタオブジェクトを生成する。
        WEB3RuitoCancelDescitionInterceptor l_ruitoCancelDescitionInterceptor =
            new WEB3RuitoCancelDescitionInterceptor();

        //1.17　@取消処理
        long l_orderHistoryId = 0; //注文履歴ID
        String l_mrfOrderRequestNumber = l_ruitoOrderUnitParams.getMrfOrderRequestNumber();            
        log.debug("l_mrfOrderRequestNumber = " + l_mrfOrderRequestNumber);
        
        boolean l_blnConfirmedQuantityIsNull = 
            l_ruitoOrderUnitParams.getConfirmedQuantityIsNull();
        log.debug("l_confirmedQuantityIsNull = " + l_blnConfirmedQuantityIsNull);
        
        //累投注文単位Params.getMRF注文識別コード()の値がnullではなく
        //累投注文単位Params.getConfirmedQuantityIsNull()の値がtrue場合、
        //this.取消注文処理（MRF自動解約有り：翌日注文）()をコールする。 
        if (l_mrfOrderRequestNumber != null && l_blnConfirmedQuantityIsNull)    
        {
            log.debug("l_mrfOrderRequestNumber != null && l_confirmedQuantityIsNull");
            this.cancelProcessMRFAutoSellTomorrowOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                l_ruitoCancelDescitionInterceptor);
        }
        //上記以外の場合、this.取消注文処理（MRF自動解約なし）()をコールする。 
        else 
        {
            log.debug("l_mrfOrderRequestNumber != null && l_confirmedQuantityIsNull 以外の場合");
            this.cancelProcessNotMRFAutoSell(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                l_ruitoCancelDescitionInterceptor);
        }
               
        log.debug("l_orderHistoryId = " + l_orderHistoryId);
        
        //－取引余力サービスを取得してvalidate取引余力( )をコールし
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
                
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        
        // 解約注文取消（注文単位.注文種別＝累投売注文）の場合、
        // 取引余力チェック処理を行う。
        if (OrderTypeEnum.RUITO_SELL.equals(l_ruitoOrderUnitParams.getOrderType()))
        {

            //  取引余力結果オブジェクトを取得する。
            Object l_orderSpecIntercepters[] = new Object[1];
            l_orderSpecIntercepters[0] = l_ruitoCancelDescitionInterceptor;
        
            Object[] l_orderSpecs = new Object[1];
            l_orderSpecs[0] = l_cancelOrderSpec;
        
            WEB3TPTradingPowerResult l_tpTradingPowerResult = 
                l_tpTradingPowerService.validateTradingPower(
                    l_gentradeSubAccount,
                    l_orderSpecIntercepters,
                    l_orderSpecs,
                    OrderTypeEnum.RUITO_SELL,
                    true);
        
            //－取得した取引余力結果オブジェクト.is判定フラグ( )＝falseの場合、 
            //[取引余力チェックエラー]として例外をスローする。
            if (!l_tpTradingPowerResult.isResultFlg())
            {
                log.debug("取引余力チェックエラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    getClass().getName() + "." + STR_METHOD_NAME, 
                    "取引余力チェックエラー");
            }
        }
        
        // 買付注文取消（注文単位.注文種別＝累投買注文）の場合、
        // 取引余力再計算処理を行う。
        else if (OrderTypeEnum.RUITO_BUY.equals(
                        l_ruitoOrderUnitParams.getOrderType()))
        {
            l_tpTradingPowerService.reCalcTradingPower(l_gentradeSubAccount);
        }

        //処理日時の取得 
        Date l_threadLocalSystemAttributesRegistry = 
            (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
                
        log.debug("l_threadLocalSystemAttributesRegistry = "
            + l_threadLocalSystemAttributesRegistry);

        //1.20 累投取消完了レスポンスオブジェクトを生成し、リターンする。
        WEB3RuitoCancelCompleteResponse l_response =
            (WEB3RuitoCancelCompleteResponse)l_request.createResponse();

        //更新時間： 取得した処理日時 
        l_response.lastUpdatedTimestamp = l_threadLocalSystemAttributesRegistry;
            
        //識別番号： 注文単位．注文ID 
        l_response.orderActionId = l_ruitoOrderUnitParams.getOrderId() + "";


        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * 取消処理（MRF自動解約有り：翌日注文）<BR>
     * <BR>
     * シーケンス図「累投取消／(累投)取消(MRF自動解約有り：翌日注文」参照 <BR>
     * <BR>
     * @@param l_subAccount - 補助口座 <BR>
     * @@param l_cancelOrderSpec - 取消注文内容 <BR>
     * @@param l_tradePassword - 取引パスワード <BR>
     * @@param l_ruitoCancelDecisionInterceptor - 累投取消確定インタセプタ <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40839A480318
     */
    public void cancelProcessMRFAutoSellTomorrowOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_cancelOrderSpec,
        String l_tradePassword,
        WEB3RuitoCancelDescitionInterceptor 
        l_ruitoCancelDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="cancelProcessMRFAutoSellTomorrowOrder"
            + "(SubAccount l_subAccount, "
            + "CancelOrderSpec l_cancelOrderSpec, "
            + "String l_tradePassword, "
            + "WEB3RuitoCancelDescitionInterceptor "
            + "l_ruitoCancelDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);
                                 
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
   
        //1.1　@拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()
        //をコールし、 インタセプタの設定を行う。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoOrderManager l_ruitoOrderManager =
            (WEB3RuitoOrderManager)
                l_finApp.getTradingModule(ProductTypeEnum.RUITO).getOrderManager();
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoCancelDecisionInterceptor);
 
        //1.2　@拡張累投注文マネージャ.submitCancelOrder()をコールする。
        log.debug("l_subAccount.getAccountId = " + l_subAccount.getAccountId());
        log.debug("l_cancelOrderSpec = " + l_cancelOrderSpec.getOrderId());
        log.debug("l_tradePassword = " + l_tradePassword);

        OrderSubmissionResult l_submitCancelOrder = 
            l_ruitoOrderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_tradePassword,
                true); 
 
        //拡張累投注文マネージャ.submitCancelOrder()の戻り値判定
        boolean blnSuccessfulResult = 
            l_submitCancelOrder.getProcessingResult().isSuccessfulResult();
        log.debug("isSuccessfulResult = " + blnSuccessfulResult);

        if (!blnSuccessfulResult)
        {
            log.debug("__SubmitCancelOrderError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00240,
                this.getClass().getName() + STR_METHOD_NAME, 
                "取消注文失敗。");
         }
        //　@MRF自動解約注文の取消を行う。 
        //1.3 累投注文単位オブジェクトを取得する。 
        String l_strMrfOrderRequestNumber = null;  //注文識別番号
        OrderUnit[] l_orderUnits = 
            l_ruitoOrderManager.getOrderUnits(l_cancelOrderSpec.getOrderId());
        RuitoOrderUnitParams l_ruitoOrderUnitParams = 
            (RuitoOrderUnitParams)l_orderUnits[0].getDataSourceObject();
        RuitoOrderUnit l_ruitoOrderUnit;  
        
        l_strMrfOrderRequestNumber = 
            l_ruitoOrderUnitParams.getMrfOrderRequestNumber(); 
        try
        {
            log.debug("AccountId = " + l_subAccount.getAccountId());
            log.debug("SubAccountId = " + l_subAccount.getSubAccountId());
            log.debug("l_strMrfOrderRequestNumber = " + l_strMrfOrderRequestNumber);
            
            l_ruitoOrderUnit = 
                l_ruitoOrderManager.getRuitoOrderUnit(l_subAccount.getAccountId(),
                    l_subAccount.getSubAccountId(),
                    l_strMrfOrderRequestNumber);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80006,
               this.getClass().getName() + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
        }

        //1.4　@CancelOrderSpecオブジェクトを生成する。
        CancelOrderSpec l_cancelOrderSpecNew =
            new CancelOrderSpec((l_ruitoOrderUnit.getOrderId())); 
        
        //1.5  拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()
        //をコールし、インタセプタの設定を行う
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoCancelDecisionInterceptor);

        //1.6　@拡張累投注文マネージャ.submitCancelOrder()をコールする。
        log.debug("l_subAccount.getAccountId = " + l_subAccount.getAccountId());
        log.debug("l_cancelOrderSpec.getOrderId = " + l_cancelOrderSpecNew.getOrderId());
        log.debug("l_tradePassword = " + l_tradePassword);

        OrderSubmissionResult l_orderSubmissionResult = 
            l_ruitoOrderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpecNew,
                l_tradePassword,
                true);

        //拡張累投注文マネージャ.submitCancelOrde()の戻り値判定
        boolean l_blnOrderSubmissionResult =
            l_orderSubmissionResult.getProcessingResult().isSuccessfulResult();
        log.debug("l_blnOrderSubmissionResult = " + l_blnOrderSubmissionResult);  
        if (!l_blnOrderSubmissionResult)
        {
            log.debug("__SubmitCancelOrderError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00240,
                this.getClass().getName() + STR_METHOD_NAME,
                "取消注文失敗。");
        }
        log.exiting(STR_METHOD_NAME);        
    }

    /**
     * 取消処理（MRF自動解約なし）<BR>
     * <BR>
     * シーケンス図「累投取消／（累投）取消（MRF自動解約なし）」参照 <BR>
     * <BR>
     * @@param l_subAccount - 補助口座 <BR>
     * @@param l_cancelOrderSpec - 取消注文内容 <BR>
     * @@param l_tradePassword - 取引パスワード <BR>
     * @@param l_ruitoCancelDecisionInterceptor - 累投取消確定インタセプタ <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40839A95000B
     */
    public void cancelProcessNotMRFAutoSell(
        SubAccount l_subAccount,
        CancelOrderSpec l_cancelOrderSpec,
        String l_tradePassword,
        WEB3RuitoCancelDescitionInterceptor 
        l_ruitoCancelDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="cancelProcessNotMRFAutoSell"
            + "(SubAccount l_subAccount, "
            + "CancelOrderSpec l_cancelOrderSpec, "
            + "String l_tradePassword, "
            + "WEB3RuitoCancelDescitionInterceptor "
            + "l_ruitoCancelDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);
       
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        //1.1　@拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()
        //をコールし、
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoOrderManager l_ruitoOrderManager =
            (WEB3RuitoOrderManager)
            l_finApp.getTradingModule(ProductTypeEnum.RUITO).getOrderManager();
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoCancelDecisionInterceptor);

        //1.2　@拡張累投注文マネージャ.submitCancelOrde()をコールする。
        log.debug("l_subAccount.getAccountId = " + l_subAccount.getAccountId());
        log.debug("取引パスワード = " + l_tradePassword);

        OrderSubmissionResult l_orderSubmissionResult = 
            l_ruitoOrderManager.submitCancelOrder(l_subAccount,
                                                  l_cancelOrderSpec,
                                                  l_tradePassword,
                                                  true);
        
        //拡張累投注文マネージャ.submitCancelOrder()の戻り値判定 
        if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
        {
            log.debug("__SubmitCancelOrderError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00240,
               this.getClass().getName() + "." + STR_METHOD_NAME, 
               "取消注文失敗。");
        }
    }
}
@
