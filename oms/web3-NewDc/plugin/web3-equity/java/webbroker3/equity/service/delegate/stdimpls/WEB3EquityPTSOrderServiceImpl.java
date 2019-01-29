head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文サービスImpl（WEB3EquityPTSOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 孟亞南 (中訊) 新規作成モデルNo.1215,No.1256,No.1267,No.1270,No.1272
Revision History : 2008/01/22 趙林鵬 (中訊) モデル No.1289
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManagerPersistenceEventInterceptor;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyCompleteResponse;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmResponse;
import webbroker3.equity.message.WEB3EquityCommissionInfoUnit;
import webbroker3.equity.message.WEB3EquityCommonRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteResponse;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter;
import webbroker3.equity.service.delegate.WEB3EquityPTSOrderService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ((PTS)現物株式注文サービスImpl)<BR>
 * (PTS)現物株式注文サービス実装クラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3EquityPTSOrderServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityPTSOrderService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSOrderServiceImpl.class);

    /**
     * @@roseuid 4766071F0352
     */
    public WEB3EquityPTSOrderServiceImpl()
    {

    }

    /**
     * (PTS)株式注文処理を実施する。 <BR>
     * <BR>
     * １）　@引数の株式注文リクエストのオブジェクト型より、<BR>
     * 　@　@　@サービスメソッドを判定しコールする。<BR>
     * <BR>
     * ２）　@サービスメソッドの戻り値を返却する。 <BR>
     * @@param l_request - (入力データ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4740EC250121
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
        //引数の株式注文リクエストのオブジェクト型より、
        //サービスメソッドを判定しコールする。
        if (l_request instanceof WEB3EquityBuyConfirmRequest
            || l_request instanceof WEB3EquitySellConfirmRequest)
        {
            l_response = this.validateOrder(l_request);
        }
        else if (l_request instanceof WEB3EquityBuyCompleteRequest
            || l_request instanceof WEB3EquitySellCompleteRequest)
        {
            l_response = this.submitOrder(l_request);
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
     * (validate注文)<BR>
     * (PTS)現物株式注文入力確認処理を実施する。 <BR>
     * <BR>
     * シーケンス図「（PTS）現物株式注文入力確認」参照。<BR>
     * @@param l_request - (入力データ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4740EB5D02EB
     */
    protected WEB3GenResponse validateOrder(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCommonRequest l_commonRequest = (WEB3EquityCommonRequest)l_request;
        //validate()
        l_commonRequest.validate();

        //株式注文リクエストアダプタ
        WEB3EquityOrderRequestAdapter l_requestAdaptor =
            WEB3EquityOrderRequestAdapter.create(l_request);

        //市場コード
        String l_strMarketCode = null;
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            WEB3EquityBuyConfirmRequest l_buyConfirmRequest =
                (WEB3EquityBuyConfirmRequest)l_request;
            l_strMarketCode = l_buyConfirmRequest.marketCode;
        }
        else if (l_request instanceof WEB3EquitySellConfirmRequest)
        {
            WEB3EquitySellConfirmRequest l_sellConfirmRequest =
                (WEB3EquitySellConfirmRequest)l_request;
            l_strMarketCode = l_sellConfirmRequest.marketCode;
        }

        //発注条件：　@リクエスト.発注条件区分
        String l_strOrderCondType = l_commonRequest.orderCondType;
        String l_strOrderCondOperator = null;
        double l_dblStopOrderBasePrice = 0.0D;
        double l_dblWLimitOrderChange = 0.0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = null;
            //逆指値基準値
            l_dblStopOrderBasePrice = 0.0D;
            //（W指値）訂正指値
            l_dblWLimitOrderChange = 0.0D;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = l_commonRequest.stopOrderCondOperator;
            //逆指値基準値
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.stopOrderCondPrice);
            //（W指値）訂正指値
            l_dblWLimitOrderChange = 0.0D;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = l_commonRequest.wlimitOrderCondOperator;
            //逆指値基準値
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.wlimitOrderCondPrice);
            //（W指値）訂正指値
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_commonRequest.wLimitOrderPriceDiv))
            {
                l_dblWLimitOrderChange = Double.parseDouble(l_commonRequest.wLimitPrice);
            }
            else
            {
                l_dblWLimitOrderChange = 0.0D;
            }
        }

        //get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        //注文失効日
        Timestamp l_tsExpirationDate = new Timestamp(WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime());
        //株式注文内容
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                (WEB3GentradeTrader)this.getTrader(),
                l_strMarketCode,
                l_requestAdaptor.getProductCode(),
                Double.parseDouble(l_commonRequest.orderQuantity),
                l_requestAdaptor.getPrice(),
                l_requestAdaptor.getExecCondType(),
                l_requestAdaptor.getTaxDivision(),
                l_tsExpirationDate,
                l_requestAdaptor.isSellOrder(),
                this.getLoginChannel(),
                l_commonRequest.priceCondType,
                l_strOrderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderBasePrice,
                l_dblWLimitOrderChange,
                null);

        //set手数料商品コード()
        l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);
        //create手数料()
        WEB3GentradeCommission l_commission =
            l_orderSpec.createCommission(
                l_subAccount.getWeb3GenBranch(),
                WEB3TransactionTypeSONARDef.MARKET_TRADING);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //PTS注文マネージャ
        WEB3EquityPTSOrderManager l_orderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        //validatePTS注文
        EqTypeNewOrderValidationResult l_validationResult =
            l_orderManager.validatePTSOrder(l_subAccount, l_orderSpec);
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

        //拡張プロダクトマネージャ
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        //取引銘柄
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                l_subAccount.getInstitution(),
                l_orderSpec.getProductCode(),
                l_orderSpec.getMarketCode());
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

        //calc概算受渡代金()
        //引数は以下の通りに設定する。
        //手数料 : 株式注文内容.get手数料()
        //指値 : 株式注文内容.getLimitPrice()
        //（W指値）訂正指値 : 株式注文内容.get（W指値）訂正指値()
        //逆指値基準値 : 株式注文内容.get逆指値基準値()
        //執行条件 : 株式注文内容.getExecConditionType()
        //（W指値）執行条件 : 株式注文内容.get（Ｗ指値）執行条件( )
        //値段条件 : 株式注文内容.get値段条件()
        //発注条件 : 株式注文内容.get発注条件()
        //確認時取得時価 : null
        //isストップ注文有効 : false（固定）
        //補助口座 : this.get補助口座()
        //取引銘柄 : 拡張プロダクトマネージャ.getTradedProduct()
        //株数 : 株式注文内容.getQuantity( )
        //is売注文 : 株式注文内容.isSellOrder( )
        //約定数量 : 0
        //合計約定金額 : 0
        //isSkip金額チェック : false（固定）
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_orderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                null,
                false,
                l_subAccount,
                l_tradedProduct,
                l_orderSpec.getQuantity(),
                l_orderSpec.isSellOrder(),
                0.0D,
                0.0D,
                false);

        //set注文単価()
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());
        //set概算受渡代金()
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());

        //getMarket
        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderSpec.getInstitutionCode(), l_strMarketCode);
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

        //validatePTS市場別取引可能上限金額
        //部店：　@this.get補助口座.get取引店()
        //市場：　@getMarket()の戻り値
        //拘束売買代金：　@手数料.get諸経費計算用代金()
        l_orderManager.validatePTSMarketMaxHandlingPrice(
            l_subAccount.getWeb3GenBranch(),
            l_market,
            l_commission.getExpensesCalcAmount());

        //株式注文インタセプタ
        WEB3EquityOrderManagerPersistenceEventInterceptor l_eventInterceptor =
            new WEB3EquityOrderManagerPersistenceEventInterceptor();

        //set株式注文内容(株式注文内容)
        l_eventInterceptor.setEquityOrderSpec(l_orderSpec);

        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //validate取引余力()
        Object[] l_orderSpecIntercepters = { l_eventInterceptor };
        Object[] l_orderSpecs = { l_orderSpec };
        OrderTypeEnum l_orderType;
        if (l_orderSpec.isSellOrder())
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }
        else
        {
            l_orderType = OrderTypeEnum.EQUITY_BUY;
        }
        WEB3TPTradingPowerResult l_tpResult =
            l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                false);

        //throw現物株式余力エラー詳細情報
        l_orderManager.throwEquityTpErrorDetailInfo(
            l_tpResult,
            l_orderType,
            l_orderSpec.getQuantity());

        //createNewOrderId()
        long l_lngOrderId = l_orderManager.createNewOrderId();

        //createResponse()
        WEB3GenResponse l_response = l_request.createResponse();

        //get市場閉局警告市場()
        String[] l_strMessageSuspensions =
            WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                (WEB3GentradeBranch)l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);

        //isインサイダー警告表示()
        boolean l_blnIsInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(
                l_subAccount,
                l_tradedProduct.getProduct().getProductId());

        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

        //分岐フロー：　@売り注文（株式注文リクエストアダプタ.is売注文() == true）の場合のみ実行
        String l_strEstimatedBookPrice = null;
        if (l_requestAdaptor.isSellOrder())
        {
            //getAsset
            WEB3EquitySellConfirmRequest l_sellConfirmRequest = (WEB3EquitySellConfirmRequest)l_request;
            Asset l_asset = null;
            try
            {
                l_asset = l_positionManager.getAsset(Long.parseLong(l_sellConfirmRequest.id));
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
            //calc概算簿価単価
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblEstimatedBookPrice =
                l_bizLogicProvider.calcEstimatedBookPrice(
                    l_asset.getProduct().getProductId(),
                    l_subAccount,
                    l_asset.getTaxType());
            l_strEstimatedBookPrice =
                WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        }

        //プロパティセット
        if (l_response instanceof WEB3EquityBuyConfirmResponse)
        {
            WEB3EquityBuyConfirmResponse l_buyConfirmResponse =
                (WEB3EquityBuyConfirmResponse)l_response;
            //レスポンス.確認時発注日
            l_buyConfirmResponse.checkDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            //レスポンス.概算受渡代金
            l_buyConfirmResponse.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(l_orderSpec.getEstimateDeliveryAmount());
            //レスポンス.取引終了警告市場コード一覧
            l_buyConfirmResponse.messageSuspension = l_strMessageSuspensions;
            // 手数料コース
            WEB3EquityCommissionInfoUnit l_commissionInfo = new WEB3EquityCommissionInfoUnit();
            l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
            // 手数料
            l_commissionInfo.commission =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
            // 手数料消費税
            l_commissionInfo.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
            //レスポンス.手数料情報
            l_buyConfirmResponse.commissionInfo = l_commissionInfo;
            //レスポンス.確認時単価
            //calc概算受渡代金( )の戻り値.get確認時取得時価( )の戻り値
            l_buyConfirmResponse.checkPrice = l_estimatedDeliveryPrice.getCheckGetCurrentPrice();
            //レスポンス.注文ID
            l_buyConfirmResponse.orderId = String.valueOf(l_lngOrderId);
            //レスポンス.インサイダー警告表示フラグ
            l_buyConfirmResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //レスポンス.注意文言表示区分
            //レスポンス.預り金不足額
            //取引余力結果.get注意文言表示区分 == "3：預り金不足注意文言表示" または
            //取引余力結果.get注意文言表示区分 == "1：現金不足注意文言表示"の場合
            //取引余力結果クラスの同項目をセットする
            if (l_tpResult != null)
            {
                l_buyConfirmResponse.attentionObjectionType = l_tpResult.getAttentionObjectionType();
                if (WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType())
                    || WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType()))
                {
                    l_buyConfirmResponse.accountBalanceInsufficiency =
                        WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
                }
            }
            //市場コード
            l_buyConfirmResponse.marketCode = l_strMarketCode;
            //レスポンス.銘柄名
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_tradedProduct.getProduct().getDataSourceObject();
            l_buyConfirmResponse.productName = l_productRow.getStandardName();
            // レスポンス.注文有効期限
            l_buyConfirmResponse.expirationDate = l_requestAdaptor.getExpirationDate();
        }
        else
        {
            WEB3EquitySellConfirmResponse l_sellConfirmResponse =
                (WEB3EquitySellConfirmResponse)l_response;
            //レスポンス.確認時発注日
            Date l_bizDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            l_sellConfirmResponse.checkDate = WEB3DateUtility.toDay(l_bizDate);
            //レスポンス.概算受渡代金
            l_sellConfirmResponse.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(l_orderSpec.getEstimateDeliveryAmount());
            //レスポンス.取引終了警告市場コード一覧
            l_sellConfirmResponse.messageSuspension = l_strMessageSuspensions;
            // 手数料コース
            WEB3EquityCommissionInfoUnit l_commissionInfo = new WEB3EquityCommissionInfoUnit();
            l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
            // 手数料
            l_commissionInfo.commission =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
            // 手数料消費税
            l_commissionInfo.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
            //レスポンス.手数料情報
            l_sellConfirmResponse.commissionInfo = l_commissionInfo;
            //レスポンス.確認時単価
            //calc概算受渡代金( )の戻り値.get確認時取得時価( )の戻り値
            l_sellConfirmResponse.checkPrice = l_estimatedDeliveryPrice.getCheckGetCurrentPrice();
            //レスポンス.注文ID
            l_sellConfirmResponse.orderId = String.valueOf(l_lngOrderId);
            //レスポンス.インサイダー警告表示フラグ
            l_sellConfirmResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //レスポンス.注意文言表示区分
            //レスポンス.預り金不足額
            //取引余力結果.get注意文言表示区分 == "3：預り金不足注意文言表示" または
            //取引余力結果.get注意文言表示区分 == "1：現金不足注意文言表示"の場合
            //取引余力結果クラスの同項目をセットする
            if (l_tpResult != null)
            {
                l_sellConfirmResponse.attentionObjectionType = l_tpResult.getAttentionObjectionType();
                if (WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType())
                    || WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType()))
                {
                    l_sellConfirmResponse.accountBalanceInsufficiency =
                        WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
                }
            }
            //市場コード
            l_sellConfirmResponse.marketCode = l_strMarketCode;
            //レスポンス.概算簿価単価
            l_sellConfirmResponse.estimatedBookPrice = l_strEstimatedBookPrice;
            // レスポンス.注文有効期限
            l_sellConfirmResponse.expirationDate = l_requestAdaptor.getExpirationDate();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * (PTS)現物株式注文登録更新処理を実施する。 <BR>
     * <BR>
     * シーケンス図「（PTS）現物株式注文登録更新」参照。 <BR>
     * @@param l_request - (入力データ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4743D9E300E9
     */
    protected WEB3GenResponse submitOrder(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCommonRequest l_commonRequest = (WEB3EquityCommonRequest)l_request;

        //validate()
        l_commonRequest.validate();

        //株式注文リクエストアダプタ
        WEB3EquityOrderRequestAdapter l_requestAdaptor =
            WEB3EquityOrderRequestAdapter.create(l_request);

        //get発注日
        Date l_datCheckDate = null;
        //get市場コード( )
        String l_strMarketCode = null;
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            WEB3EquityBuyCompleteRequest l_buyCompleteRequest =
                (WEB3EquityBuyCompleteRequest)l_request;
            if (l_buyCompleteRequest.checkDate == null)
            {
                l_buyCompleteRequest.checkDate =
                    WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            }
            l_datCheckDate = l_buyCompleteRequest.checkDate;
            l_strMarketCode = l_buyCompleteRequest.marketCode;
        }
        else if (l_request instanceof WEB3EquitySellCompleteRequest)
        {
            WEB3EquitySellCompleteRequest l_sellCompleteRequest =
                (WEB3EquitySellCompleteRequest)l_request;
            if (l_sellCompleteRequest.checkDate == null)
            {
                l_sellCompleteRequest.checkDate =
                    WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            }
            l_datCheckDate = l_sellCompleteRequest.checkDate;
            l_strMarketCode = l_sellCompleteRequest.marketCode;
        }
        WEB3EquityPTSTradingTimeManagement.getOrderBizDate(l_datCheckDate);

        //発注条件：　@リクエスト.発注条件区分
        String l_strOrderCondType = l_commonRequest.orderCondType;
        String l_strOrderCondOperator = null;
        double l_dblStopOrderBasePrice = 0.0D;
        double l_dblWLimitOrderChange = 0.0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = null;
            //逆指値基準値
            l_dblStopOrderBasePrice = 0.0D;
            //（W指値）訂正指値
            l_dblWLimitOrderChange = 0.0D;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = l_commonRequest.stopOrderCondOperator;
            //逆指値基準値
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.stopOrderCondPrice);
            //（W指値）訂正指値
            l_dblWLimitOrderChange = 0.0D;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = l_commonRequest.wlimitOrderCondOperator;
            //逆指値基準値
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.wlimitOrderCondPrice);
            //（W指値）訂正指値
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_commonRequest.wLimitOrderPriceDiv))
            {
                l_dblWLimitOrderChange = Double.parseDouble(l_commonRequest.wLimitPrice);
            }
            else
            {
                l_dblWLimitOrderChange = 0.0D;
            }
        }

        //get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        //注文失効日
        Timestamp l_tsExpirationDate = new Timestamp(WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime());
        //株式注文内容
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                (WEB3GentradeTrader)this.getTrader(),
                l_strMarketCode,
                l_requestAdaptor.getProductCode(),
                Double.parseDouble(l_commonRequest.orderQuantity),
                l_requestAdaptor.getPrice(),
                l_requestAdaptor.getExecCondType(),
                l_requestAdaptor.getTaxDivision(),
                l_tsExpirationDate,
                l_requestAdaptor.isSellOrder(),
                this.getLoginChannel(),
                l_commonRequest.priceCondType,
                l_strOrderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderBasePrice,
                l_dblWLimitOrderChange,
                null);

        //set手数料商品コード()
        l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);
        //create手数料()
        WEB3GentradeCommission l_commission =
            l_orderSpec.createCommission(
                l_subAccount.getWeb3GenBranch(),
                WEB3TransactionTypeSONARDef.MARKET_TRADING);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //PTS注文マネージャ
        WEB3EquityPTSOrderManager l_orderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        //validatePTS注文
        EqTypeNewOrderValidationResult l_validationResult =
            l_orderManager.validatePTSOrder(l_subAccount, l_orderSpec);
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

        //拡張プロダクトマネージャ
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        //取引銘柄
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                l_subAccount.getInstitution(),
                l_orderSpec.getProductCode(),
                l_orderSpec.getMarketCode());
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

        //calc概算受渡代金()
        //引数は以下の通りに設定する。
        //手数料 : 株式注文内容.get手数料()
        //指値 : 株式注文内容.getLimitPrice()
        //（W指値）訂正指値 : 株式注文内容.get（W指値）訂正指値()
        //逆指値基準値 : 株式注文内容.get逆指値基準値()
        //執行条件 : 株式注文内容.getExecConditionType()
        //（W指値）執行条件 : 株式注文内容.get（Ｗ指値）執行条件( )
        //値段条件 : 株式注文内容.get値段条件()
        //発注条件 : 株式注文内容.get発注条件()
        //確認時取得時価 : null
        //isストップ注文有効 : false（固定）
        //補助口座 : this.get補助口座()
        //取引銘柄 : 拡張プロダクトマネージャ.getTradedProduct()
        //株数 : 株式注文内容.getQuantity( )
        //is売注文 : 株式注文内容.isSellOrder( )
        //約定数量 : 0
        //合計約定金額 : 0
        //isSkip金額チェック : false（固定）
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_orderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                null,
                false,
                l_subAccount,
                l_tradedProduct,
                l_orderSpec.getQuantity(),
                l_orderSpec.isSellOrder(),
                0.0D,
                0.0D,
                false);

        //set注文単価()
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());
        //set概算受渡代金()
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());

        //getMarket
        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderSpec.getInstitutionCode(), l_strMarketCode);
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

        //validatePTS市場別取引可能上限金額
        //部店：　@this.get補助口座.get取引店()
        //市場：　@getMarket()の戻り値
        //拘束売買代金：　@手数料.get諸経費計算用代金()
        l_orderManager.validatePTSMarketMaxHandlingPrice(
            l_subAccount.getWeb3GenBranch(),
            l_market,
            l_commission.getExpensesCalcAmount());

        //株式注文インタセプタ
        WEB3EquityOrderManagerPersistenceEventInterceptor l_eventInterceptor =
            new WEB3EquityOrderManagerPersistenceEventInterceptor();

        //set株式注文内容(株式注文内容)
        l_eventInterceptor.setEquityOrderSpec(l_orderSpec);

        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //validate取引余力()
        Object[] l_orderSpecIntercepters = { l_eventInterceptor };
        Object[] l_orderSpecs = { l_orderSpec };
        OrderTypeEnum l_orderType;
        if (l_orderSpec.isSellOrder())
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }
        else
        {
            l_orderType = OrderTypeEnum.EQUITY_BUY;
        }
        WEB3TPTradingPowerResult l_tpResult =
            l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                true);

        //throw現物株式余力エラー詳細情報
        l_orderManager.throwEquityTpErrorDetailInfo(
            l_tpResult,
            l_orderType,
            l_orderSpec.getQuantity());

        //setThreadLocalPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_eventInterceptor);

        //submitNewCashBasedOrder
        long l_lngOrderId;
        String l_strPassward;
        if (l_commonRequest instanceof WEB3EquityBuyCompleteRequest)
        {
            if (((WEB3EquityBuyCompleteRequest)l_commonRequest).orderId == null)
            {
                ((WEB3EquityBuyCompleteRequest)l_commonRequest).orderId =
                    String.valueOf(l_orderManager.createNewOrderId());
            }
            l_lngOrderId = Long.parseLong(((WEB3EquityBuyCompleteRequest)l_commonRequest).orderId);
            l_strPassward = ((WEB3EquityBuyCompleteRequest)l_commonRequest).password;
        }
        else
        {
            if (((WEB3EquitySellCompleteRequest)l_commonRequest).orderId == null)
            {
                ((WEB3EquitySellCompleteRequest)l_commonRequest).orderId =
                    String.valueOf(l_orderManager.createNewOrderId());
            }
            l_lngOrderId = Long.parseLong(((WEB3EquitySellCompleteRequest)l_commonRequest).orderId);
            l_strPassward = ((WEB3EquitySellCompleteRequest)l_commonRequest).password;
        }
        EqTypeOrderSubmissionResult l_result =
            l_orderManager.submitNewCashBasedOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_strPassward,
                true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            ProcessingResult l_processingResult = l_result.getProcessingResult();
            log.debug("ProcessingResult() = " + l_processingResult);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }

        //createResponse()
        WEB3GenResponse l_response = l_commonRequest.createResponse();

        //isインサイダー警告表示()
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_blnIsInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(
                l_subAccount,
                l_orderUnitRow.getProductId());

        //プロパティセット
        if (l_response instanceof WEB3EquityBuyCompleteResponse)
        {
            WEB3EquityBuyCompleteResponse l_buyCompleteResponse =
                (WEB3EquityBuyCompleteResponse)l_response;
            //レスポンス.更新時間
            l_buyCompleteResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            //レスポンス.識別番号
            l_buyCompleteResponse.orderActionId = ((WEB3EquityBuyCompleteRequest)l_request).orderId;
            //レスポンス.インサイダー警告表示フラグ
            l_buyCompleteResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //市場コード
            l_buyCompleteResponse.marketCode = l_strMarketCode;
            // レスポンス.注文有効期限
            l_buyCompleteResponse.expirationDate = l_requestAdaptor.getExpirationDate();
        }
        else
        {
            WEB3EquitySellCompleteResponse l_sellCompleteResponse =
                (WEB3EquitySellCompleteResponse)l_response;
            //レスポンス.更新時間
            l_sellCompleteResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            //レスポンス.識別番号
            l_sellCompleteResponse.orderActionId = ((WEB3EquitySellCompleteRequest)l_request).orderId;
            //レスポンス.インサイダー警告表示フラグ
            l_sellCompleteResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //市場コード
            l_sellCompleteResponse.marketCode = l_strMarketCode;
            // レスポンス.注文有効期限
            l_sellCompleteResponse.expirationDate = l_requestAdaptor.getExpirationDate();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
