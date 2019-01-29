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
filename	WEB3EquityPTSChangeOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文訂正サービスImpl(WEB3EquityPTSChangeOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 張騰宇 (中訊) 新規作成 モデル1241 1255 1265 1269
Revision History : 2007/12/28 張騰宇 (中訊) モデル1277
Revision History : 2008/01/08 張騰宇 (中訊) モデル1278,1279
Revision History : 2008/01/22 趙林鵬 (中訊) モデル No.1289
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityChangeOrderSpec;
import webbroker3.equity.WEB3EquityChangeOrderUnitEntry;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManagerChangeOrderEventInterceptor;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeCompleteResponse;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmResponse;
import webbroker3.equity.message.WEB3EquityCommissionInfoUnit;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderRequestAdapter;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderRequestAdapter;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderService;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ((PTS)現物株式注文訂正サービスImpl)<BR>
 * (PTS)現物株式注文訂正サービス実装クラス<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3EquityPTSChangeOrderServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityPTSChangeOrderService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSChangeOrderServiceImpl.class);

    /**
     * @@roseuid 476607200064
     */
    public WEB3EquityPTSChangeOrderServiceImpl()
    {

    }

    /**
     * (validate注文訂正)<BR>
     * (PTS)注文訂正確認を行う。<BR>
     * <BR>
     * シーケンス図「(PTS)注文訂正確認」 参照<BR>
     * @@param l_request - (入力データ)<BR>
     * クライアントからの入力データ<BR>
     * @@return WEB3EquityChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 474A634400AC
     */
    protected WEB3EquityChangeConfirmResponse validateChangeOrder(
        WEB3EquityChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangeOrder(WEB3EquityChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate注文受付可能( )
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

        //create(WEB3GenRequest)
        WEB3EquityChangeOrderRequestAdapter l_changeOrderRequestAdapter =
            WEB3EquityPTSChangeOrderRequestAdapter.create(l_request);

        //create株式注文訂正値詳細(株式注文訂正リクエストアダプタ)
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            WEB3EquityChangeOrderUnitEntry.createChangeOrderUnitEntry(l_changeOrderRequestAdapter);

        //株式注文訂正内容(long, 株式注文訂正値詳細, String, String, 扱者)
        //注文ＩＤ : (PTS)現物株式注文訂正リクエストアダプタ.get注文ID( )
        //株式注文訂正値詳細 : 生成した株式注文訂正値詳細
        //証券会社コード : this.get補助口座( ).getInstitution( ).証券会社コード
        //注文チャネル : this.getログインチャネル( )
        //扱者 : this.get代理入力者( )
        WEB3EquityChangeOrderSpec l_changeOrderSpec =
            new WEB3EquityChangeOrderSpec(
                l_changeOrderRequestAdapter.getRequestOrderId(),
                l_changeOrderUnitEntry,
                this.getSubAccount().getInstitution().getInstitutionCode(),
                this.getLoginChannel(),
                this.getTrader());

        //createPTS株式注文内容
        WEB3EquityNewCashBasedOrderSpec l_orderSpec = l_changeOrderSpec.createPTSOrderSpec();

        //validatePTS訂正注文(補助口座, 株式注文訂正内容)
        //補助口座 : this.get補助口座( )
        //株式注文訂正内容 : 生成した株式注文訂正内容
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPTSOrderManager l_equityPTSOrderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderValidationResult l_validationResult =
            l_equityPTSOrderManager.validatePTSChangeOrder(
                this.getSubAccount(),
                l_changeOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_validationResult.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_validationResult.getProcessingResult().getErrorInfo().getErrorMessage());
        }

        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        //株式注文訂正内容.get訂正元注文単位( )
        OrderUnit l_orderUnit = l_changeOrderSpec.getOrgChangeOrderUnit();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        WEB3EquityTradedProduct l_tradedProduct = null;
        Market l_market = null;
        try
        {
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_productManager.getProduct(l_orderUnitRow.getProductId());
            l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            //getTradedProduct
            //証券会社 : this.get補助口座( ).getInstitution( )
            //銘柄コード : 株式注文訂正内容.get訂正元注文単位( ).銘柄IDに該当する株式銘柄.銘柄コード
            //市場コード : 株式注文訂正内容.get訂正元注文単位( ).市場IDに該当する市場マスタ.市場コード
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    this.getSubAccount().getInstitution(),
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

        //手数料 : 株式注文内容.get手数料( )
        WEB3GentradeCommission l_commission = l_orderSpec.getCommission();

        //calc概算受渡代金
        //手数料 : 株式注文内容.get手数料( )
        //指値 : 株式注文内容.getLimitPrice( )
        //（W指値）訂正指値 : 株式注文内容.get（W指値）訂正指値( )
        //逆指値基準値 : 株式注文内容.get逆指値基準値( )
        //執行条件 : 株式注文内容.getExecConditionType( )
        //（W指値）執行条件 : 株式注文内容.get（W指値）執行条件( )
        //値段条件 : 株式注文内容.get値段条件( )
        //発注条件 : 株式注文内容.get発注条件( )
        //確認時取得時価 : null
        //isストップ注文有効 : 株式注文訂正値詳細.isストップ注文有効( )
        //補助口座 : this.get補助口座( )
        //取引銘柄 : 拡張プロダクトマネージャ.getTradedProduct( )
        //株数 : 株式注文内容.getQuantity( )
        //is売注文 : 株式注文内容.isSellOrder( )
        //約定数量 : 株式注文訂正内容.get訂正元注文単位( ).約定数量
        //合計約定金額 : 株式注文訂正内容.get訂正元注文単位( ).合計約定金額
        //isSkip金額チェック : false（固定）
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_equityPTSOrderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                null,
                l_changeOrderUnitEntry.isStopOrderEnable(),
                this.getSubAccount(),
                l_tradedProduct,
                l_orderSpec.getQuantity(),
                l_orderSpec.isSellOrder(),
                l_orderUnitRow.getExecutedQuantity(),
                l_orderUnitRow.getExecutedAmount(),
                false);

        //set注文単価(double)
        //注文単価 : 概算受渡代金計算結果.get計算単価( )
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());

        //set概算受渡代金(double)
        //概算金額 : 概算受渡代金計算結果.get概算受渡代金( )
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());

        //validatePTS市場別取引可能上限金額(部店, 市場, double)
        //部店 : this.get補助口座( ).get取引店( )
        //市場 : 拡張金融オブジェクトマネージャ.getMarket( )
        //　@［getMarket( )の引数］
        //　@　@市場ID : 訂正対象の注文単位.市場ID
        //拘束売買代金 : 手数料.get諸経費計算用代金( )
        l_equityPTSOrderManager.validatePTSMarketMaxHandlingPrice(
            this.getSubAccount().getWeb3GenBranch(),
            l_market,
            l_commission.getExpensesCalcAmount());

        //株式注文訂正インタセプタ(String, 扱者)
        //注文経路区分 : ログインセッションより取得した同項目値
        //代理入力者 : this.get代理入力者( )
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityOrderManagerChangeOrderEventInterceptor l_changeOrderEventInterceptor =
            new WEB3EquityOrderManagerChangeOrderEventInterceptor(
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());

        //set株式注文内容(株式注文内容)
        //株式注文内容 : 株式注文訂正内容.createPTS株式注文内容( )
        l_changeOrderEventInterceptor.setEquityOrderSpec(l_orderSpec);

        //validate取引余力
        //補助口座 : this.get補助口座( )
        //注文内容インタセプタ : 生成した株式注文訂正インタセプタ
        //注文内容 : 株式注文訂正内容
        //注文種別 : 訂正対象の注文単位.注文種別
        //余力更新フラグ : false（確認時）
        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        Object[] l_changeOrderEventInterceptors = {l_changeOrderEventInterceptor};
        Object[] l_orderSpecs = {l_changeOrderSpec};

        WEB3TPTradingPowerResult l_tpResult =
            l_tradingpowerService.validateTradingPower(
                this.getSubAccount(),
                l_changeOrderEventInterceptors,
                l_orderSpecs,
                l_orderUnit.getOrderType(),
                false);

        //throw現物株式余力エラー詳細情報
        //取引余力結果：　@取引余力サービス.validate取引余力( )
        //注文種別：　@売注文（株式注文内容.isSellOrder() == true）の場合、”現物売注文”をセット。
        //            買注文（株式注文内容.isSellOrder() == false）の場合、”現物買注文”をセットする。
        //注文株数：　@株式注文内容.getQuantity( )
        OrderTypeEnum l_orderType = null;
        boolean l_blnIsSellOrder = l_orderSpec.isSellOrder();
        if (l_blnIsSellOrder)
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }
        else
        {
            l_orderType = OrderTypeEnum.EQUITY_BUY;
        }
        l_equityPTSOrderManager.throwEquityTpErrorDetailInfo(
            l_tpResult,
            l_orderType,
            l_orderSpec.getQuantity());

        //createResponse
        WEB3EquityChangeConfirmResponse l_changeConfirmResponse =
            (WEB3EquityChangeConfirmResponse)l_request.createResponse();

        //get市場閉局警告市場(部店, ProductTypeEnum, String)
        //部店 : this.get補助口座( ).get取引店( )
        //銘柄タイプ : "株式"
        //信用取引区分 : "DEFAULT"
        String[] l_strTradeCloseMarkets =
            WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                this.getSubAccount().getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);

        //isインサイダー警告表示(補助口座, long)
        boolean l_blnIsInsiderMessageSuspension =
            l_equityPTSOrderManager.isInsiderMessageSuspension(
            this.getSubAccount(),
            l_orderUnitRow.getProductId());

        //売り注文の場合のみ実行
        if (l_blnIsSellOrder)
        {
            //calc概算簿価単価(long, SubAccount, TaxTypeEnum)
            //銘柄ID : 訂正対象の注文単位.銘柄ID
            //補助口座 : this.get補助口座( )
            //税区分 : 訂正対象の注文単位.税区分
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblEstimatedBookPrice = l_bizLogicProvider.calcEstimatedBookPrice(
                l_orderUnitRow.getProductId(),
                this.getSubAccount(),
                l_orderUnitRow.getTaxType());

            // レスポンス.概算簿価単価
            l_changeConfirmResponse.estimatedBookPrice =
                WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        }

        // レスポンス.確認時発注日
        Date l_datOrderBizDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
        l_changeConfirmResponse.checkDate = WEB3DateUtility.toDay(l_datOrderBizDate);

        // レスポンス.概算受渡代金
        l_changeConfirmResponse.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_orderSpec.getEstimateDeliveryAmount());

        // レスポンス.取引終了警告市場コード一覧
        l_changeConfirmResponse.messageSuspension = l_strTradeCloseMarkets;

        // レスポンス.内出来株数
        double l_dblExecutedQuantity = l_orderUnitRow.getExecutedQuantity();
        if (l_dblExecutedQuantity == 0.0D)
        {
            l_changeConfirmResponse.partContQuantity = null;
        }
        else
        {
            l_changeConfirmResponse.partContQuantity =
                WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
        }

        WEB3EquityCommissionInfoUnit l_commissionInfo = new WEB3EquityCommissionInfoUnit();

        //レスポンス.手数料情報.手数料コース
        l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
        // レスポンス.手数料情報.手数料
        l_commissionInfo.commission =
            WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
        // レスポンス.手数料情報.手数料消費税
        l_commissionInfo.commissionConsumptionTax =
            WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
        l_changeConfirmResponse.commissionInfo = l_commissionInfo;

        // レスポンス.確認時単価
        l_changeConfirmResponse.checkPrice = l_estimatedDeliveryPrice.getCheckGetCurrentPrice();

        // レスポンス.インサイダー警告表示フラグ
        l_changeConfirmResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;

        //レスポンス.注意文言表示区分
        l_changeConfirmResponse.attentionObjectionType = l_tpResult.getAttentionObjectionType();

        //レスポンス.預り金不足額
        //取引余力結果.get注意文言表示区分() == "1：現金不足注意文言表示" または
        //取引余力結果.get注意文言表示区分() == "3：預り金不足注意文言表示"の場合
        //取引余力結果の同項目
        if (WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION.equals(
                l_tpResult.getAttentionObjectionType())
            || WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                l_tpResult.getAttentionObjectionType()))
        {
            l_changeConfirmResponse.accountBalanceInsufficiency =
                WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
        }

        // レスポンス.注文有効期限
        l_changeConfirmResponse.expirationDate = l_changeOrderRequestAdapter.getOrderExpirationDate();

        log.exiting(STR_METHOD_NAME);
        return l_changeConfirmResponse;
    }

    /**
     * (submit注文訂正)<BR>
     * (PTS)注文訂正の登録を行う。<BR>
     * <BR>
     * シーケンス図「(PTS)注文訂正更新」 参照<BR>
     * @@param l_request - (入力データ)<BR>
     * クライアントからの入力データ<BR>
     * @@return WEB3EquityChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 474A760001C6
     */
    protected WEB3EquityChangeCompleteResponse submitChangeOrder(
        WEB3EquityChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChangeOrder(WEB3EquityChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //get発注日(Date)
        //確認時発注日 : リクエスト.確認時発注日
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
        }
        WEB3EquityPTSTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //validate注文受付可能( )
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

        //create(WEB3GenRequest)
        WEB3EquityChangeOrderRequestAdapter l_changeOrderRequestAdapter =
            WEB3EquityPTSChangeOrderRequestAdapter.create(l_request);

        //create株式注文訂正値詳細(株式注文訂正リクエストアダプタ)
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            WEB3EquityChangeOrderUnitEntry.createChangeOrderUnitEntry(l_changeOrderRequestAdapter);

        //株式注文訂正内容(long, 株式注文訂正値詳細, String, String, 扱者)
        //注文ＩＤ : (PTS)現物株式注文訂正リクエストアダプタ.get注文ID( )
        //株式注文訂正値詳細 : 生成した株式注文訂正値詳細
        //証券会社コード : this.get補助口座( ).getInstitution( ).証券会社コード
        //注文チャネル : this.getログインチャネル( )
        //扱者 : this.get代理入力者( )
        WEB3EquityChangeOrderSpec l_changeOrderSpec =
            new WEB3EquityChangeOrderSpec(
                l_changeOrderRequestAdapter.getRequestOrderId(),
                l_changeOrderUnitEntry,
                this.getSubAccount().getInstitution().getInstitutionCode(),
                this.getLoginChannel(),
                this.getTrader());

        //createPTS株式注文内容( )
        WEB3EquityNewCashBasedOrderSpec l_orderSpec = l_changeOrderSpec.createPTSOrderSpec();

        //validatePTS訂正注文(補助口座, 株式注文訂正内容)
        //補助口座 : this.get補助口座( )
        //株式注文訂正内容 : 生成した株式注文訂正内容
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPTSOrderManager l_equityPTSOrderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderValidationResult l_validationResult =
            l_equityPTSOrderManager.validatePTSChangeOrder(
                this.getSubAccount(),
                l_changeOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_validationResult.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_validationResult.getProcessingResult().getErrorInfo().getErrorMessage());
        }

        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        //株式注文訂正内容.get訂正元注文単位( )
        OrderUnit l_orderUnit = l_changeOrderSpec.getOrgChangeOrderUnit();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        WEB3EquityTradedProduct l_tradedProduct = null;
        Market l_market = null;
        try
        {
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_productManager.getProduct(l_orderUnitRow.getProductId());
            l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            //getTradedProduct
            //証券会社 : this.get補助口座( ).getInstitution( )
            //銘柄コード : 株式注文訂正内容.get訂正元注文単位( ).銘柄IDに該当する株式銘柄.銘柄コード
            //市場コード : 株式注文訂正内容.get訂正元注文単位( ).市場IDに該当する市場マスタ.市場コード
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    this.getSubAccount().getInstitution(),
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

        //手数料 : 株式注文内容.get手数料( )
        WEB3GentradeCommission l_commission = l_orderSpec.getCommission();

        //calc概算受渡代金
        //手数料 : 株式注文内容.get手数料( )
        //指値 : 株式注文内容.getLimitPrice( )
        //（W指値）訂正指値 : 株式注文内容.get（W指値）訂正指値( )
        //逆指値基準値 : 株式注文内容.get逆指値基準値( )
        //執行条件 : 株式注文内容.getExecConditionType( )
        //（W指値）執行条件 : 株式注文内容.get（W指値）執行条件( )
        //値段条件 : 株式注文内容.get値段条件( )
        //発注条件 : 株式注文内容.get発注条件( )
        //確認時取得時価 : リクエスト.確認時単価 
        //isストップ注文有効 : 株式注文訂正値詳細.isストップ注文有効( )
        //補助口座 : this.get補助口座( )
        //取引銘柄 : 拡張プロダクトマネージャ.getTradedProduct( )
        //株数 : 株式注文内容.getQuantity( )
        //is売注文 : 株式注文内容.isSellOrder( )
        //約定数量 : 株式注文訂正内容.get訂正元注文単位( ).約定数量
        //合計約定金額 : 株式注文訂正内容.get訂正元注文単位( ).合計約定金額
        //isSkip金額チェック : false（固定）
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_equityPTSOrderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                l_request.checkPrice,
                l_changeOrderUnitEntry.isStopOrderEnable(),
                this.getSubAccount(),
                l_tradedProduct,
                l_orderSpec.getQuantity(),
                l_orderSpec.isSellOrder(),
                l_orderUnitRow.getExecutedQuantity(),
                l_orderUnitRow.getExecutedAmount(),
                false);

        //set注文単価(double)
        //注文単価 : 概算受渡代金計算結果.get計算単価( )
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());

        //set概算受渡代金(double)
        //概算金額 : 概算受渡代金計算結果.get概算受渡代金( )
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());

        //validatePTS市場別取引可能上限金額(部店, 市場, double)
        //部店 : this.get補助口座( ).get取引店( )
        //市場 : 拡張金融オブジェクトマネージャ.getMarket( )
        //　@［getMarket( )の引数］
        //　@　@市場ID : 訂正対象の注文単位.市場ID
        //拘束売買代金 : 手数料.get諸経費計算用代金( )
        l_equityPTSOrderManager.validatePTSMarketMaxHandlingPrice(
            this.getSubAccount().getWeb3GenBranch(),
            l_market,
            l_commission.getExpensesCalcAmount());

        //株式注文訂正インタセプタ(String, 扱者)
        //注文経路区分 : ログインセッションより取得した同項目値
        //代理入力者 : this.get代理入力者( )
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityOrderManagerChangeOrderEventInterceptor l_changeOrderEventInterceptor =
            new WEB3EquityOrderManagerChangeOrderEventInterceptor(
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());

        //set株式注文内容(株式注文内容)
        //株式注文内容 : 株式注文訂正内容.createPTS株式注文内容( )
        l_changeOrderEventInterceptor.setEquityOrderSpec(l_orderSpec);

        //validate取引余力
        //補助口座 : this.get補助口座( )
        //注文内容インタセプタ : 生成した株式注文訂正インタセプタ
        //注文内容 : 株式注文訂正内容
        //注文種別 : 訂正対象の注文単位.注文種別
        //余力更新フラグ : true（完了時）
        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        Object[] l_changeOrderEventInterceptors = {l_changeOrderEventInterceptor};
        Object[] l_orderSpecs = {l_changeOrderSpec};

        WEB3TPTradingPowerResult l_tpResult =
            l_tradingpowerService.validateTradingPower(
                this.getSubAccount(),
                l_changeOrderEventInterceptors,
                l_orderSpecs,
                l_orderUnit.getOrderType(),
                true);

        //throw現物株式余力エラー詳細情報
        //取引余力結果：　@取引余力サービス.validate取引余力( )
        //注文種別：　@売注文（株式注文内容.isSellOrder() == true）の場合、”現物売注文”をセット。
        //            買注文（株式注文内容.isSellOrder() == false）の場合、”現物買注文”をセットする。
        //注文株数：　@株式注文内容.getQuantity( )
        OrderTypeEnum l_orderType = null;
        boolean l_blnIsSellOrder = l_orderSpec.isSellOrder();
        if (l_blnIsSellOrder)
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }
        else
        {
            l_orderType = OrderTypeEnum.EQUITY_BUY;
        }
        l_equityPTSOrderManager.throwEquityTpErrorDetailInfo(
            l_tpResult,
            l_orderType,
            l_orderSpec.getQuantity());

        //setThreadLocalPersistenceEventInterceptor
        l_equityPTSOrderManager.setThreadLocalPersistenceEventInterceptor(l_changeOrderEventInterceptor);

        //submit現物株式注文訂正
        //補助口座 : this.get補助口座( )
        //株式注文訂正内容 : 株式注文訂正内容
        //取引パスワード : リクエスト.暗証番号
        //isSkip発注審査 : true（スキップする）
        EqTypeOrderSubmissionResult l_submitResult =
            l_equityPTSOrderManager.submitChangeOrder(
                this.getSubAccount(),
                l_changeOrderSpec,
                l_request.password,
                true);
        if (l_submitResult.getProcessingResult().isFailedResult())
        {
            log.debug(STR_METHOD_NAME + " __Error[注文訂正更新]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_submitResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_submitResult.getProcessingResult().getErrorInfo().getErrorMessage());
        }

        //createResponse
        WEB3EquityChangeCompleteResponse l_changeCompleteResponse =
            (WEB3EquityChangeCompleteResponse)l_request.createResponse();

        //isインサイダー警告表示(補助口座, long)
        //補助口座：　@this.get補助口座( )
        //銘柄ID：　@訂正対象の注文単位.銘柄ID
        boolean l_blnIsInsiderMessageSuspension =
            l_equityPTSOrderManager.isInsiderMessageSuspension(
                this.getSubAccount(),
                l_orderUnitRow.getProductId());

        //更新時間
        l_changeCompleteResponse.lastUpdatedTimestamp = l_orderUnitRow.getLastUpdatedTimestamp();

        //識別番号
        l_changeCompleteResponse.orderActionId = String.valueOf(l_orderUnitRow.getOrderId());

        //インサイダー警告表示フラグ
        l_changeCompleteResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;

        //連続注文設定フラグ
        l_changeCompleteResponse.succSettingFlag = false;

        //注文有効期限
        l_changeCompleteResponse.expirationDate = l_changeOrderRequestAdapter.getOrderExpirationDate();

        log.exiting(STR_METHOD_NAME);
        return l_changeCompleteResponse;
    }

    /**
     * PTS現物株式注文訂正処理を実行する。 <BR>
     * <BR>
     * （システム実装方針ガイド 4.4.業務ロジック　@参照） <BR>
     * <BR>
     * １）　@実施メソッド判定 <BR>
     * 　@－リクエストデータの型が「現物株式注文訂正確認リクエスト」の場合は、<BR>
     * 　@　@this.validate注文訂正( )をコールする。  <BR>
     * 　@－リクエストデータの型が「現物株式注文訂正完了リクエスト」の場合は、<BR>
     * 　@　@this.submit注文訂正( )をコールする。  <BR>
     * <BR>
     * ２）　@メソッドの戻り値を返却する。 <BR>
     * @@param l_request - (入力データ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 475D0E65034E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //リクエストデータの型が「現物株式注文訂正確認リクエスト」の場合
        if (l_request instanceof WEB3EquityChangeConfirmRequest)
        {
            l_response = this.validateChangeOrder((WEB3EquityChangeConfirmRequest)l_request);
        }
        //リクエストデータの型が「現物株式注文訂正完了リクエスト」の場合
        else if (l_request instanceof WEB3EquityChangeCompleteRequest)
        {
            l_response = this.submitChangeOrder((WEB3EquityChangeCompleteRequest)l_request);
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

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
