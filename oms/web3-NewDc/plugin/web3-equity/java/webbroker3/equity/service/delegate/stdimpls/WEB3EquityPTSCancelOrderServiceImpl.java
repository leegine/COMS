head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文取消サービスImpl（WEB3EquityPTSCancelOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 孟亞南 (中訊) 新規作成モデルNo.1213
*/

package webbroker3.equity.service.delegate.stdimpls;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityCancelOrderInterceptor;
import webbroker3.equity.WEB3EquityCancelOrderSpec;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquityCancelCompleteResponse;
import webbroker3.equity.message.WEB3EquityCancelConfirmRequest;
import webbroker3.equity.message.WEB3EquityCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityPTSCancelOrderService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ((PTS)現物株式注文取消サービスImpl)<BR>
 * (PTS)現物株式注文取消サービス実装クラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3EquityPTSCancelOrderServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityPTSCancelOrderService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSCancelOrderServiceImpl.class);

    /**
     * @@roseuid 4766071F03CF
     */
    public WEB3EquityPTSCancelOrderServiceImpl()
    {

    }

    /**
     * (PTS)株式注文取消処理を実行する。<BR>
     * <BR>
     * （システム実装方針ガイド 4.4.業務ロジック　@参照）<BR>
     * <BR>
     * １）　@実施メソッド判定<BR>
     * 　@１－１）　@リクエストデータが「現物株式注文取消確認リクエスト」の場合<BR>
     * 　@　@this.validate注文取消()をコールする。<BR>
     * <BR>
     * 　@１－２）　@リクエストデータが「現物株式注文取消完了リクエスト」の場合<BR>
     * 　@　@this.submit注文取消()をコールする。<BR>
     * <BR>
     * ２）　@メソッドの戻り値を返却する。<BR>
     * @@param l_request - (入力データ)<BR>
     * クライアントからの入力データ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47424C0902A0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        //実施メソッド判定
        //リクエストデータが「現物株式注文取消確認リクエスト」の場合
        if (l_request instanceof WEB3EquityCancelConfirmRequest)
        {
            WEB3EquityCancelConfirmRequest l_cancelConfirmRequest =
                (WEB3EquityCancelConfirmRequest)l_request;
            //this.validate注文取消()をコールする。
            l_response = this.validateCancelOrder(l_cancelConfirmRequest);
        }
        //リクエストデータが「現物株式注文取消完了リクエスト」の場合
        else if (l_request instanceof WEB3EquityCancelCompleteRequest)
        {
            WEB3EquityCancelCompleteRequest l_cancelCompleteRequest =
                (WEB3EquityCancelCompleteRequest)l_request;
            l_response = this.submitCancelOrder(l_cancelCompleteRequest);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
        //サービスメソッドの戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文取消)<BR>
     * （PTS）現物株式注文取消を行う。<BR>
     * <BR>
     * シーケンス図「(PTS)注文取消完了」参照<BR>
     * @@param l_request - (入力データ)<BR>
     * クライアントからの入力データ<BR>
     * @@return WEB3EquityCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47396A010133
     */
    protected WEB3EquityCancelCompleteResponse submitCancelOrder(WEB3EquityCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancelOrder(WEB3EquityCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();
        //validate注文受付可能
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();
        //注文ＩＤ : リクエストデータ.ID
        long l_lngOrderId = Long.parseLong(l_request.id);
        //株式注文取消内容
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, this.getTrader());

        //get補助口座
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        //get発注日(確認時発注日 : Date)
        //確認時発注日 : リクエストデータ.確認時発注日
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
        }
        WEB3EquityPTSTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        //PTS注文マネージャ
        WEB3EquityPTSOrderManager l_orderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        //validatePTS取消注文
        EqTypeOrderValidationResult l_validationResult =
            l_orderManager.validatePTSCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            ProcessingResult l_processingResult = l_validationResult.getProcessingResult();
            log.debug("ProcessingResult() = " + l_processingResult);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }

        //getOrderUnits(注文ID : long)
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //分岐フロー：　@約定がある場合（注文単位.isUnexecuted( )==false）
        double l_dblEstimatedPrice;
        if (!l_orderUnit.isUnexecuted())
        {
            //get失効時受渡代金(注文単位)
            l_dblEstimatedPrice = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
        }
        else
        {
            //取消対象の注文単位.概算受渡代金
            l_dblEstimatedPrice = l_orderUnitRow.getEstimatedPrice();
        }

        //株式注文取消インタセプタ()
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityCancelOrderInterceptor l_cancelInterceptor =
            new WEB3EquityCancelOrderInterceptor(
                l_dblEstimatedPrice,
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());

        //setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_cancelInterceptor);

        //submit現物株式注文取消()
        EqTypeOrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            ProcessingResult l_processingResult = l_orderSubmissionResult.getProcessingResult();
            log.debug("ProcessingResult() = " + l_processingResult);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }

        //余力再計算()
        WEB3TPTradingPowerService l_tradingpowerService =
        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        l_tradingpowerService.reCalcTradingPower(l_subAccount);

        //createResponse()
        WEB3EquityCancelCompleteResponse l_completeResponse =
            (WEB3EquityCancelCompleteResponse)l_request.createResponse();

        //プロパティセット
        // レスポンス.更新時間
        l_completeResponse.lastUpdatedTimestamp = l_orderUnitRow.getLastUpdatedTimestamp();
        // レスポンス.識別番号
        l_completeResponse.orderActionId = String.valueOf(l_orderUnitRow.getOrderId());
        // レスポンス.連続注文設定フラグ
        l_completeResponse.succSettingFlag = false;

        log.exiting(STR_METHOD_NAME);
        return l_completeResponse;
    }

    /**
     * (validate注文取消)<BR>
     * （PTS）注文取消審査を行う。<BR>
     * <BR>
     * シーケンス図「(PTS)注文取消確認」参照<BR>
     * @@param l_request - (入力データ)<BR>
     * クライアントからの入力データ<BR>
     * @@return WEB3EquityCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 473963B40296
     */
    protected WEB3EquityCancelConfirmResponse validateCancelOrder(WEB3EquityCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancelOrder(WEB3EquityCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //get代理入力者()
        Trader l_trader = this.getTrader();

        //get補助口座
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPTSOrderManager l_orderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);

        //validate注文受付可能()
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

        //株式注文取消内容()
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, l_trader);

        //validatePTS取消注文(補助口座, 株式注文取消内容)
        EqTypeOrderValidationResult l_validationResult =
            (EqTypeOrderValidationResult)l_orderManager.validatePTSCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            ProcessingResult l_processingResult = l_validationResult.getProcessingResult();
            log.debug("ProcessingResult() = " + l_processingResult);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }

        //get市場閉局警告市場
        String[] l_strTradeCloseMarkets =
            WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);

        //createResponse()
        WEB3EquityCancelConfirmResponse l_confirmResponse =
            (WEB3EquityCancelConfirmResponse)l_request.createResponse();

        //getOrderUnits()
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //売り注文の場合のみ実行
        double l_dblEstimatedBookPrice = 0.0D;
        OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
        if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            l_dblEstimatedBookPrice =
                l_bizLogicProvider.calcEstimatedBookPrice(
                    l_orderUnitRow.getProductId(),
                    l_subAccount,
                    l_orderUnit.getTaxType());
        }

        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct = null;
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_subAccount.getInstitution(),
                    l_product.getProductCode(),
                    l_market.getMarketCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get表示用時価情報()
        WEB3EquityProductQuote l_productQuote = null;
        l_productQuote =
            l_productManager.getDisplayEquityProductQuote(
                l_tradedProduct,
                l_subAccount);

        //プロパティセット
        //レスポンス.確認時発注日
        l_confirmResponse.checkDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
        //レスポンス.概算受渡代金
        l_confirmResponse.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
        //レスポンス.取引終了警告市場コード一覧
        l_confirmResponse.messageSuspension = l_strTradeCloseMarkets;
        //レスポンス.銘柄コード
        l_confirmResponse.productCode = l_product.getProductCode();
        //レスポンス.銘柄名
        EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
        l_confirmResponse.productName = l_productRow.getStandardName();
        //レスポンス.市場コード
        l_confirmResponse.marketCode = l_market.getMarketCode();
        //レスポンス.口座区分
        l_confirmResponse.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
        //レスポンス.取引区分
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_confirmResponse.tradingType = WEB3TradingTypeDef.BUY_ORDER;
        }
        else
        {
            l_confirmResponse.tradingType = WEB3TradingTypeDef.SELL_ORDER;
        }
        //レスポンス.注文株数
        l_confirmResponse.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        //レスポンス.内出来株数
        if (l_orderUnitRow.getExecutedQuantityIsNull())
        {
            l_confirmResponse.partContQuantity = null;
        }
        else
        {
            l_confirmResponse.partContQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getExecutedQuantity());
        }
        //レスポンス.注文単価区分
        if (l_orderUnit.isMarketOrder())
        {
            l_confirmResponse.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            //レスポンス.注文単価
            l_confirmResponse.limitPrice = null;
        }
        else
        {
            l_confirmResponse.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            //レスポンス.注文単価
            l_confirmResponse.limitPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }

        //レスポンス.概算簿価単価
        if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            l_confirmResponse.estimatedBookPrice =
                WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        }
        //レスポンス.値段条件
        l_confirmResponse.priceCondType = l_orderUnitRow.getPriceConditionType();
        //レスポンス.執行条件
        l_confirmResponse.execCondType =
            l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());
        //レスポンス.注文期限区分
        boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnit);
        if (!l_blnIsCarriedOrderUnit)
        {
            l_confirmResponse.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }
        else
        {
            l_confirmResponse.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }
        //レスポンス.注文有効期限
        if (l_blnIsCarriedOrderUnit)
        {
            l_confirmResponse.expirationDate =
                l_orderUnitRow.getExpirationDate();
        }
        //レスポンス.発注条件区分
        l_confirmResponse.orderCondType =
            l_orderUnitRow.getOrderConditionType();
        //レスポンス.逆指値用発注条件単価
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.stopOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
        }
        //レスポンス.逆指値用発注条件演算子
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.stopOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
        }
        //レスポンス.W指値用発注条件単価
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.wlimitOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
        }
        //レスポンス.W指値用発注条件演算子
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.wlimitOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
        }
        //レスポンス.W指値用注文単価区分
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            if (l_orderUnitRow.getWLimitPrice() == 0.0D)
            {
                l_confirmResponse.wLimitOrderPriceDiv =
                    WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
            }
            else
            {
                l_confirmResponse.wLimitOrderPriceDiv =
                    WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
                //レスポンス.W指値用注文単価
                l_confirmResponse.wLimitPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
            }
        }

        //　@W指値用執行条件：　@注文単位.発注条件＝2（W指値）の場合のみセット。
        //　@　@　@拡張株式注文マネージャ.get執行条件（SONAR）(注文単位.Ｗ指値用執行条件)の戻り値をセット。
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.wlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getWLimitExecCondType());
        }

        //　@W指値用有効状態区分：　@株式データアダプタ.getＷ指値用有効状態区分(注文単位)の戻り値をセット。
        l_confirmResponse.wlimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //　@W指値用切替前注文単価：　@株式データアダプタ.getＷ指値用切替前注文単価(注文単位)の戻り値をセット。
        l_confirmResponse.wlimitBefChgLimitPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        //　@W指値用切替前執行条件：　@株式データアダプタ.getＷ指値用切替前執行条件(注文単位)の戻り値をセット。
        l_confirmResponse.wlimitBefChgExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //　@元発注条件区分：　@注文単位.元発注条件
        l_confirmResponse.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //　@元発注条件単価：　@注文単位.元逆指値基準値
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_confirmResponse.orgOrderCondPrice = null;
        }
        else
        {
            l_confirmResponse.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }

        //　@元発注条件演算子：　@注文単位.元発注条件演算子
        l_confirmResponse.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //　@元Ｗ指値用注文単価区分：　@株式データアダプタ.get元Ｗ指値用注文単価区分(注文単位)の戻り値をセット。
        String l_strOrgWlimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);
        l_confirmResponse.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

        //　@元Ｗ指値用注文単価：元W指値用注文単価区分が"指値"の場合のみ、
        //   株式データアダプタ.get元Ｗ指値用注文単価(注文単位)の戻り値をセット。
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
        {
            l_confirmResponse.orgWlimitPrice =
                WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }

        //　@元Ｗ指値用執行条件：　@株式データアダプタ.get元Ｗ指値用執行条件(注文単位)の戻り値をセット。
        l_confirmResponse.orgWlimitExecCondType =
            WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //レスポンス.時価区分
        l_confirmResponse.currentPriceDiv = l_productQuote.getQuoteTypeDiv();
        //レスポンス.時価（現在値）
        l_confirmResponse.currentPrice =
            WEB3StringTypeUtility.formatNumber(l_productQuote.getQuote());
        //レスポンス.前日比
        l_confirmResponse.comparedPreviousDay =
            WEB3StringTypeUtility.formatNumber(l_productQuote.getComparedPreviousDay());
        //レスポンス.取引時間（時価発表時間）
        l_confirmResponse.currentPriceTime = l_productQuote.getQuoteTime();

        //レスポンス.現在値
        l_confirmResponse.boardCurrentPrice = l_productQuote.getBoardCurrentPrice();

        //レスポンス.現在値時刻
        l_confirmResponse.boardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime();

        //レスポンス.現在値区分
        l_confirmResponse.boardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();

        //レスポンス.現在値前日比
        l_confirmResponse.boardChange = l_productQuote.getBoardChange();

        //レスポンス.出来高
        l_confirmResponse.volume = l_productQuote.getVolume();

        //レスポンス.出来高時刻
        l_confirmResponse.volumeTime = l_productQuote.getVolumeTime();

        //レスポンス.買気配値タイトル区分
        l_confirmResponse.askPriceTitle = l_productQuote.getAskPriceTitle();

        //レスポンス.買気配値
        l_confirmResponse.askPrice = l_productQuote.getAskPrice();

        //レスポンス.買気配値時刻
        l_confirmResponse.askPriceTime = l_productQuote.getAskPriceTime();

        //レスポンス.売気配値タイトル区分
        l_confirmResponse.bidPriceTitle = l_productQuote.getBidPriceTitle();

        //レスポンス.売気配値
        l_confirmResponse.bidPrice = l_productQuote.getBidPrice();

        //レスポンス.売気配値時刻
        l_confirmResponse.bidPriceTime = l_productQuote.getBidPriceTime();

        //レスポンス.基準値段
        l_confirmResponse.basePrice = l_productQuote.getBasePrice();

        log.exiting(STR_METHOD_NAME);
        return l_confirmResponse;
    }
}
@
