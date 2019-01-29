head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募サービスImpl(WEB3BondDomesticApplyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.226
Revision History : 2009/08/12 武波 (中訊) 実装の問題No.005
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;

import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondDomesticOrderUpdateInterceptor;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondNewOrderSpec;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderUnitIntroduceDiv;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * (国内債券応募サービスImpl)<BR>
 * 国内債券応募サービスImpl<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyServiceImpl
    extends WEB3BondClientRequestService
    implements WEB3BondDomesticApplyService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyServiceImpl.class);

    /**
     * @@roseuid 46A473FC031C
     */
    public WEB3BondDomesticApplyServiceImpl()
    {

    }

    /**
     * 国内債券応募サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * validate国内債券応募注文、submit国内債券応募注文<BR>
     * のいずれかのメソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 466CD702033F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3BondDomesticApplyConfirmRequest)
        {
            //国内債券応募確認リクエスト
            l_response = this.validateBondDomesticApplyOrder(
                (WEB3BondDomesticApplyConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3BondDomesticApplyCompleteRequest)
        {
            //国内債券応募完了リクエスト
            l_response = this.submitBondDomesticApplyOrder(
                (WEB3BondDomesticApplyCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータタイプ不正");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate国内債券応募注文)<BR>
     * 国内債券応募注文発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validate国内債券応募注文」参照。<BR>
     * ==========================================================<BR>
     * 　@シーケンス図　@:validate国内債券応募注文<BR>
     * 　@具体位置　@　@　@:is判定フラグ()<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@false の場合、例外をスローする。<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag   : BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3BondDomesticApplyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 466CD70A011C
     */
    protected WEB3BondDomesticApplyConfirmResponse validateBondDomesticApplyOrder(
        WEB3BondDomesticApplyConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateBondDomesticApplyOrder(WEB3BondDomesticApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate注文受付可能
        WEB3BondTradingTimeManagement.validateOrderAccept();

        //get補助口座()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

        //validate取引可能顧客(補助口座 : SubAccount)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引可能顧客チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "取引可能顧客チェックエラー");
        }

        //get債券銘柄(long)
        //[get債券銘柄()に渡す引数]
        //銘柄ID：　@リクエストデータ.銘柄ID
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_productManager.getBondProduct(Long.parseLong(l_request.productId));

        //validate国内債券応募注文(SubAccount, 債券銘柄, double)
        //[validate国内債券応募注文(）に渡す引数]
        //　@補助口座：　@get補助口座()の戻り値
        //　@債券銘柄：　@get債券銘柄()の戻り値
        //注文数量：　@リクエストデータ.申込金額
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        l_bondOrderManager.validateBondDomesticApplyOrder(
            l_subAccount, l_bondProduct, Double.parseDouble(l_request.applyAmount));

        // create国内債券約定日情報(債券銘柄)
        //[create国内債券約定日情報()に渡す引数]
        //債券銘柄：　@get債券銘柄()の戻り値
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo =
            l_bondOrderManager.createBondDomesticExecutionDateInfo(l_bondProduct);

        //債券注文種別判定(注文種別, String)
        //コンストラクタに渡す引数]
        //注文種別：　@OrderTypeEnum.国内債券応募
        //取引：　@35：募集取引
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.DOMESTIC_BOND_RECRUIT, WEB3DealTypeDef.RECRUIT_TRADING);

        //calc国内債券受渡代金(債券注文種別判定, BigDecimal, 債券銘柄, 債券約定日情報)
        //[calc国内債券受渡代金()に渡す引数]
        //債券注文種別判定：　@作成した債券注文種別判定
        //数量：　@リクエストデータ.申込金額
        //債券銘柄：　@取得した債券銘柄
        //債券約定日情報：　@create国内債券約定日情報()の戻り値
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();

        BigDecimal l_bdApplyAmount = new BigDecimal(l_request.applyAmount);

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bizLogicProvider.calcBondDomesticEstimatedPrice(
                    l_bondOrderTypeJudge, l_bdApplyAmount, l_bondProduct, l_bondExecuteDateInfo);

        //get代理入力者( )
        Trader l_trader = this.getTrader();

        //create拡張債券新規注文内容<国内債券>(Trader, 債券注文種別判定, 債券銘柄, BigDecimal)
        //create拡張債券新規注文内容<国内債券>()に渡す引数]
        //オペレータ：　@get代理入力者()の戻り値
        //債券注文種別判定：　@作成した債券注文種別判定
        //債券銘柄：　@取得した債券銘柄
        //数量：　@リクエストデータ.申込金額
        WEB3BondNewOrderSpec l_bondNewOrderSpec =
            WEB3BondNewOrderSpec.createBondNewOrderSpecDomesticBond(
                l_trader, l_bondOrderTypeJudge, l_bondProduct, l_bdApplyAmount);

        //国内債券注文更新インタセプタ()
        WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
            new WEB3BondDomesticOrderUpdateInterceptor();

        //メッセージ プロパティ・セット
        //拡張債券新規注文内容：　@create拡張債券新規注文内容<国内債券>()の戻り値
        l_bondDomesticOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

        //債券約定日情報：　@create国内債券約定日情報()の戻り値
        l_bondDomesticOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);

        //債券受渡代金計算結果：　@calc国内債券受渡代金()の戻り値
        l_bondDomesticOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_bondEstimatedPriceCalcResult);
/*
        //実装の問題No.005
        //validate取引余力
        //validate取引余力()に渡す引数]
        //補助口座：　@get補助口座()の戻り値
        //注文内容インタセプタ：　@作成した国内債券注文更新インタセプタ
        //注文内容：　@作成した拡張債券新規注文内容
        //注文種別：　@OrderTypeEnum.国内債券応募
        //余力更新フラグ：　@false
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors = {l_bondDomesticOrderUpdateInterceptor};

        Object[] l_arrNewOrderSpecs = {l_bondNewOrderSpec};

        WEB3TPTradingPowerResult l_result =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.DOMESTIC_BOND_RECRUIT,
                false);

        //is判定フラグ()＝false の場合
        //取引余力判定結果をチェックする。
        //is判定フラグ()の戻り値がfalseの場合、[取引余力チェックエラー]として例外をスローする
        if (!l_result.isResultFlg())
        {
            log.debug("取引余力チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + STR_METHOD_NAME,
                "取引余力チェックエラー");
        }
        //実装の問題No.005
*/
        //createNewOrderId( )
        long l_lngNewOrderId = l_bondOrderManager.createNewOrderId();

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3BondDomesticApplyConfirmResponse l_response =
            (WEB3BondDomesticApplyConfirmResponse)l_request.createResponse();

        //注文ID = createNewOrderId()の戻り値
        l_response.id = l_lngNewOrderId + "";

        //初回利子調整額 = 債券受渡代金計算結果.経過利子（円貨）
        l_response.initialInterestAdjustAmount = WEB3StringTypeUtility.formatNumber(
            l_bondEstimatedPriceCalcResult.getAccruedInterest().doubleValue());

        //受渡金額 = 債券受渡代金計算結果.受渡代金(円貨)
        l_response.deliveryPrice = WEB3StringTypeUtility.formatNumber(
            l_bondEstimatedPriceCalcResult.getEstimatedPrice().doubleValue());

        //約定日 = 債券約定日情報.約定日
        l_response.executionUpdateDate = l_bondExecuteDateInfo.getExecuteDate();

        //受渡日  = 債券約定日情報.受渡日
        l_response.deliveryDate = l_bondExecuteDateInfo.getDeliveryDate();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit国内債券応募注文)<BR>
     * 国内債券応募注文発注登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submit国内債券応募注文」参照。<BR>
     * ==========================================================<BR>
     * 　@シーケンス図　@:submit国内債券応募注文<BR>
     * 　@具体位置　@　@　@:is判定フラグ()<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@false の場合、例外をスローする。<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag   : BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3BondDomesticApplyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 466CD71E0216
     */
    protected WEB3BondDomesticApplyCompleteResponse submitBondDomesticApplyOrder(
        WEB3BondDomesticApplyCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " submitBondDomesticApplyOrder(WEB3BondDomesticApplyCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate注文受付可能
        WEB3BondTradingTimeManagement.validateOrderAccept();

        // get補助口座( )
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

        //validate取引可能顧客(補助口座 : SubAccount)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引可能顧客チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "取引可能顧客チェックエラー");
        }

        //get債券銘柄(long)
        //[get債券銘柄()に渡す引数]
        //銘柄ID：　@リクエストデータ.銘柄ID
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_productManager.getBondProduct(Long.parseLong(l_request.productId));

        //validate国内債券応募注文(SubAccount, 債券銘柄, double)
        //[validate国内債券応募注文(）に渡す引数]
        //　@補助口座：　@get補助口座()の戻り値
        //　@債券銘柄：　@get債券銘柄()の戻り値
        //注文数量：　@リクエストデータ.申込金額
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        l_bondOrderManager.validateBondDomesticApplyOrder(
            l_subAccount, l_bondProduct, Double.parseDouble(l_request.applyAmount));

        // create国内債券約定日情報(債券銘柄)
        //[create国内債券約定日情報()に渡す引数]
        //債券銘柄：　@get債券銘柄()の戻り値
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo =
            l_bondOrderManager.createBondDomesticExecutionDateInfo(l_bondProduct);

        //債券注文種別判定
        //コンストラクタに渡す引数]
        //注文種別：　@OrderTypeEnum.国内債券応募
        //取引：　@35：募集取引
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.DOMESTIC_BOND_RECRUIT, WEB3DealTypeDef.RECRUIT_TRADING);

        //calc国内債券受渡代金(債券注文種別判定, BigDecimal, 債券銘柄, 債券約定日情報)
        //[calc国内債券受渡代金()に渡す引数]
        //債券注文種別判定：　@作成した債券注文種別判定
        //数量：　@リクエストデータ.申込金額
        //債券銘柄：　@取得した債券銘柄
        //債券約定日情報：　@create国内債券約定日情報()の戻り値
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();

        BigDecimal l_bdApplyAmount = new BigDecimal(l_request.applyAmount);

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bizLogicProvider.calcBondDomesticEstimatedPrice(
                l_bondOrderTypeJudge, l_bdApplyAmount, l_bondProduct, l_bondExecuteDateInfo);

        //get代理入力者( )
        Trader l_trader = this.getTrader();

        //create拡張債券新規注文内容<国内債券>(Trader, 債券注文種別判定, 債券銘柄, BigDecimal)
        //create拡張債券新規注文内容<国内債券>()に渡す引数]
        //オペレータ：　@get代理入力者()の戻り値
        //債券注文種別判定：　@作成した債券注文種別判定
        //債券銘柄：　@取得した債券銘柄
        //数量：　@リクエストデータ.申込金額
        WEB3BondNewOrderSpec l_bondNewOrderSpec =
            WEB3BondNewOrderSpec.createBondNewOrderSpecDomesticBond(
                l_trader, l_bondOrderTypeJudge, l_bondProduct, l_bdApplyAmount);

        //国内債券注文更新インタセプタ()
        WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
            new WEB3BondDomesticOrderUpdateInterceptor();

        //メッセージ プロパティ・セット
        //拡張債券新規注文内容：　@create拡張債券新規注文内容<国内債券>()の戻り値
        l_bondDomesticOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

        //債券約定日情報：　@create国内債券約定日情報()の戻り値
        l_bondDomesticOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);

        //債券受渡代金計算結果：　@calc国内債券受渡代金()の戻り値
        l_bondDomesticOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_bondEstimatedPriceCalcResult);
/*
        //実装の問題No.005
        //validate取引余力
        //validate取引余力()に渡す引数]
        //補助口座：　@get補助口座()の戻り値
        //注文内容インタセプタ：　@作成した国内債券注文更新インタセプタ
        //注文内容：　@作成した拡張債券新規注文内容
        //注文種別：　@OrderTypeEnum.国内債券応募
        //余力更新フラグ：　@true
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors = {l_bondDomesticOrderUpdateInterceptor};

        Object[] l_arrNewOrderSpecs = {l_bondNewOrderSpec};

        WEB3TPTradingPowerResult l_tradingPowerResult =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.DOMESTIC_BOND_RECRUIT,
                true);

        //is判定フラグ()＝false の場合
        //取引余力判定結果をチェックする。
        //is判定フラグ()の戻り値がfalseの場合、[取引余力チェックエラー]として例外をスローする
        if (!l_tradingPowerResult.isResultFlg())
        {
            log.debug("取引余力チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + STR_METHOD_NAME,
                "取引余力チェックエラー");
        }
        //実装の問題No.005
*/
        //setThreadLocalPersistenceEventInterceptor(arg0 : BondOrderManagerPersistenceEventInterceptor)
        //[引数]
        //インタセプタ： 生成した国内債券注文更新インタセプタ
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_bondDomesticOrderUpdateInterceptor);

        //submitNewOrder(
        //arg0 : SubAccount, arg1 : ProductTypeEnum,
        //arg2 : NewOrderSpec, arg3 : long, arg4 : String, arg5 : boolean)
        //      [submitNewOrder()に渡す引数]
        //      SubAccount：　@get補助口座()の戻り値
        //      ProductTypeEnum：　@ProductTypeEnum.債券
        //      NewOrderSpec：　@create拡張債券新規注文内容<国内債券>()の戻り値
        //      注文ID：　@リクエストデータ.注文ID
        //      取引パスワード：　@リクエストデータ.暗証番号
        //      isSkip発注審査：　@true
        OrderSubmissionResult l_orderSubmissionResult = l_bondOrderManager.submitNewOrder(
            l_subAccount,
            ProductTypeEnum.BOND,
            l_bondNewOrderSpec,
            Long.parseLong(l_request.id),
            l_request.password,
            true);

        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitNewOrder"
                + l_orderSubmissionResult.getProcessingResult().getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "Error in submitNewOrder");
        }

        //＜分岐処理＞リクエストデータ.紹介区分 ≠ null の場合
        if (l_request.introduceStoreDiv != null)
        {
            //get債券注文単位By注文ID(long)
            //注文ID：　@リクエストデータ.注文ID
            WEB3BondOrderUnit l_bondOrderUnit =
                l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));

            //注文単位紹介区分()
            WEB3GentradeOrderUnitIntroduceDiv l_orderUnitIntroduceDiv =
                new WEB3GentradeOrderUnitIntroduceDiv();

            //メッセージ プロパティ・セット
            //注文単位ＩＤ：　@債券注文単位.注文単位ID
            l_orderUnitIntroduceDiv.setOrderUnitId(l_bondOrderUnit.getOrderUnitId());

            //口座ＩＤ：　@債券注文単位.口座ID
            l_orderUnitIntroduceDiv.setAccountId(l_bondOrderUnit.getAccountId());

            //銘柄タイプ：　@債券注文単位.銘柄タイプ
            l_orderUnitIntroduceDiv.setProductType(l_bondOrderUnit.getProductType());

            //紹介区分：　@リクエストデータ.紹介区分
            l_orderUnitIntroduceDiv.setIntroduceBranchDiv(l_request.introduceStoreDiv);

            //紹介店コード：　@リクエストデータ.紹介店コード
            l_orderUnitIntroduceDiv.setIntroduceBranchCode(l_request.introduceStoreCode);

            //更新者コード：　@get代理入力者()の戻り値 ≠ null の場合、扱者.扱者コード
            //それ以外の場合、顧客.顧客コード
            if (l_trader != null)
            {
                l_orderUnitIntroduceDiv.setLastUpdater(l_trader.getTraderCode());
            }
            else
            {
                l_orderUnitIntroduceDiv.setLastUpdater(
                    l_subAccount.getMainAccount().getAccountCode());
            }

            //saveNew注文単位紹介区分()
            //注文単位照会区分を登録する
            l_orderUnitIntroduceDiv.saveNewOrderUnitIntroduceDivRow();
        }

        //createResponse( )
        WEB3BondDomesticApplyCompleteResponse l_response =
            (WEB3BondDomesticApplyCompleteResponse)l_request.createResponse();

        //プロパティ・セット
        //更新日時  = 現在日時
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
