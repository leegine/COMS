head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondRecruitBuyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付サービスImpl(WEB3BondRecruitBuyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/7 郭英 (中訊) 新規作成
                      : 2006/09/29 張騰宇 (中訊) モデル 094,098
                      : 2006/10/09 張騰宇 (中訊) モデル 105
                        2006/10/12 柴雙紅 (中訊)  WEBⅢ開発標準の見直しの対応（new BigDecimal部分）
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondNewOrderSpec;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondOrderUpdateInterceptor;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.message.WEB3BondApplyBuyCompleteRequest;
import webbroker3.bd.message.WEB3BondApplyBuyCompleteResponse;
import webbroker3.bd.message.WEB3BondApplyBuyConfirmRequest;
import webbroker3.bd.message.WEB3BondApplyBuyConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderUnitIntroduceDiv;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券応募/買付サービスImpl)<BR>
 * 債券応募/買付サービスImpl<BR>
 *
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3BondRecruitBuyServiceImpl
    extends WEB3BondClientRequestService implements WEB3BondRecruitBuyService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyServiceImpl.class);

    /**
     * @@roseuid 44FBFD3A01D4
     */
    public WEB3BondRecruitBuyServiceImpl()
    {

    }

    /**
     * 債券応募/買付サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * validate応募/買付注文、submit応募/買付注文<BR>
     * のいずれかのメソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C6C25402C6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3BondApplyBuyConfirmRequest)
        {
            //validate応募/買付注文
            l_response =
                this.validateRecruitBuyOrder((WEB3BondApplyBuyConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3BondApplyBuyCompleteRequest)
        {
            //submit応募/買付注文
            l_response =
                this.submitRecruitBuyOrder((WEB3BondApplyBuyCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメーターのタイプが不正です。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate応募/買付注文)<BR>
     * 債券応募/買付注文発注審査を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「validate応募/買付注文」参照。 <BR>
     * ========================================================<BR>
     *  シーケンス図(「validate応募/買付注文」)<BR>
     * 　@　@:  1.17.3 ＜分岐処理＞is判定フラグ()の戻り値 == false の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.bd.message.WEB3BondApplyBuyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C9BF8200DA
     */
    protected WEB3BondApplyBuyConfirmResponse validateRecruitBuyOrder(
        WEB3BondApplyBuyConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRecruitBuyOrder(WEB3BondApplyBuyConfirmRequest) ";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストが未指定(null)です。");
        }

        //1.1: validate( )
        l_request.validate();

        //1.2: get債券銘柄(long)
        FinApp l_finapp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finapp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productMgr =
            (WEB3BondProductManager) l_tradingModule.getProductManager();

        WEB3BondProduct l_product =
            (WEB3BondProduct) l_productMgr.getBondProduct(Long.parseLong(l_request.productId));

        //1.3: validate注文受付可能(String)
        WEB3BondTradingTimeManagement.validateOrderAccept(l_product);

        //1.4: get補助口座( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.5:validate取引可能顧客(補助口座 : SubAccount)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();

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

        //1.6:validate（応募/買付）注文(SubAccount, 債券銘柄, String, String, double)
        WEB3BondOrderManager l_orderMgr = (WEB3BondOrderManager) l_tradingModule.getOrderManager();

        String l_strTradeDiv = l_request.tradeDiv;
        double l_dblFaceAmount = Double.parseDouble(l_request.faceAmount);
        l_orderMgr.validateRecruitOrBuyOrder(
            l_subAccount,
            l_product,
            l_strTradeDiv,
            l_request.settleDiv,
            l_dblFaceAmount);

        //1.7: 債券注文種別判定(注文種別, String)
        //　@取引：　@リクエストデータ.取引区分 == "買付"の場合、92：国内仕切取引。
        //　@　@　@　@　@　@リクエストデータ.取引区分 == "応募"の場合、35：募集取引。
        String l_strTrade = null;
        if (WEB3BondDealDivDef.BUY.equals(l_strTradeDiv))
        {
            l_strTrade = WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING;
        }
        else if (WEB3BondDealDivDef.RECRUIT.equals(l_strTradeDiv))
        {
            l_strTrade = WEB3DealTypeDef.RECRUIT_TRADING;
        }
        WEB3BondOrderTypeJudge l_orderTypeJudge = new WEB3BondOrderTypeJudge(
            OrderTypeEnum.BOND_BUY,
            l_strTrade);

        //1.8:is外貨建( )
        boolean l_blnIsForeignCurrency = l_product.isForeignCurrency();

        //1.9:＜分岐処理＞is外貨建()の戻り値 == true の場合
        BigDecimal l_bdFxRate = null;
        if (l_blnIsForeignCurrency)
        {
            BigDecimal l_bigDecimal = new BigDecimal("0");
            ////1.9.1:get為替レート(債券銘柄, 債券注文種別判定, String, BigDecimal, boolean)
            l_bdFxRate = l_orderMgr.getFxRate(l_product, l_orderTypeJudge, l_request.settleDiv, l_bigDecimal, false);
        }

        //1.10: get発注日( )
        Date l_datOrderBizDate = WEB3BondTradingTimeManagement.getOrderBizDate();



        //1.11: create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄,String, Branch)
        WEB3BondExecuteDateInfo l_execDateInfo = l_orderMgr.createBondExecutionDateInfo(
            l_datOrderBizDate,
            l_orderTypeJudge,
            l_product,
            l_request.settleDiv,
            l_subAccount.getMainAccount().getBranch());

        //1.12: calc受渡代金(債券注文種別判定, BigDecimal, BigDecimal, BigDecimal, 債券銘柄, 債券約定日情報)
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_tradingModule.getBizLogicProvider();

        //　@数量：　@リクエストデータ.額面金額
        BigDecimal l_bdFaceAmt = new BigDecimal(l_request.faceAmount);

        //　@注文単価：　@債券銘柄.買付単価
        BigDecimal l_bdBuyPrice = new BigDecimal(String.valueOf(l_product.getBuyPrice()));

        //　@為替レート：　@get為替レート()の戻り値
        //（※is外貨建()の戻り値 == falseの場合、nullをセットする。）
        WEB3BondEstimatedPriceCalcResult l_estimatedPriceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_orderTypeJudge,
                l_bdFaceAmt,
                l_bdBuyPrice,
                l_bdFxRate,
                l_product,
                l_execDateInfo);

        //1.13:  get代理入力者( )
        Trader l_trader = this.getTrader();

        //1.14:  create拡張債券新規注文内容(Trader, 債券注文種別判定, String,
        //double, double, TaxTypeEnum, java.util.Date, String)
        WEB3BondNewOrderSpec l_orderSpec = WEB3BondNewOrderSpec.createBondNewOrderSpec(
            l_trader,
            l_orderTypeJudge,
            l_product.getProductCode(),
            l_dblFaceAmount,
            l_product.getBuyPrice(),
            TaxTypeEnum.NORMAL,
            l_execDateInfo.getDeliveryDate(),
            l_request.settleDiv);

        //1.15: 債券注文更新インタセプタ( )
        WEB3BondOrderUpdateInterceptor l_updateInterceptor = new WEB3BondOrderUpdateInterceptor();

        //1.16: プロパティ・セット
        l_updateInterceptor.setBondExecuteDateInfo(l_execDateInfo);
        l_updateInterceptor.setBondEstimatedPriceCalcResult(l_estimatedPriceCalcResult);
        l_updateInterceptor.setBondNewOrderSpec(l_orderSpec);

        //1.17:＜分岐処理＞リクエストデータ.決済区分 == ”円貨” の場合
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
        {
            //1.17.1: validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[],
            //注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);

            WEB3TPTradingPowerResult l_tpResult =
                l_tpTradingPowerService.validateTradingPower(
                    (WEB3GentradeSubAccount) l_subAccount,
                    new Object[]{l_updateInterceptor},
                    new Object[]{l_orderSpec},
                    OrderTypeEnum.BOND_BUY,
                    false);

            //1.17.2:  is判定フラグ( )
            boolean l_blnIsResultFlg = l_tpResult.isResultFlg();

            //1.17.3: ＜分岐処理＞is判定フラグ()の戻り値 == false の場合、例外をスローする。
            if (!l_blnIsResultFlg)
            {
                log.debug("取引余力チェックエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "取引余力チェックエラー。");
            }
        }

        //1.18:  createNewOrderId( )
        long l_lngOrderId = l_orderMgr.createNewOrderId();

        //1.19: createResponse( )
        WEB3BondApplyBuyConfirmResponse l_response =
            (WEB3BondApplyBuyConfirmResponse) l_request.createResponse();

        //1.20: プロパティ・セット
        //注文ID          = createNewOrderId()の戻り値
        l_response.id = WEB3StringTypeUtility.formatNumber(l_lngOrderId);

        //売買代金（外貨）      = 債券受渡代金計算結果.売買代金（外貨）
        BigDecimal l_dbForeignTradePrice = l_estimatedPriceCalcResult.getForeignTradePrice();

        if (l_dbForeignTradePrice != null)
        {
            l_response.foreignTradePrice = WEB3StringTypeUtility.formatNumber(
                l_dbForeignTradePrice.doubleValue());
        }

        //売買代金（円貨）      = 債券受渡代金計算結果.売買代金（円貨）
        BigDecimal l_dbTradingPrice = l_estimatedPriceCalcResult.getTradingPrice();

        if (l_dbTradingPrice != null)
        {
            l_response.yenTradePrice = WEB3StringTypeUtility.formatNumber(
                l_dbTradingPrice.doubleValue());
        }

        //経過利子（外貨）      = 債券受渡代金計算結果.経過利子（外貨）
        BigDecimal l_dbForeignAccruedInterest = l_estimatedPriceCalcResult.getForeignAccruedInterest();

        if (l_dbForeignAccruedInterest != null)
        {
            l_response.foreignAccruedInterest = WEB3StringTypeUtility.formatNumber(
                l_dbForeignAccruedInterest.doubleValue());
        }

        //経過利子（円貨）      = 債権受渡代金計算結果.経過利子（円貨）
        BigDecimal l_bdAccruedInterest = l_estimatedPriceCalcResult.getAccruedInterest();

        if (l_bdAccruedInterest != null)
        {
            l_response.yenAccruedInterest = WEB3StringTypeUtility.formatNumber(
                l_bdAccruedInterest.doubleValue());
        }

        //受渡代金（外貨）      = 債権受渡代金計算結果.受渡代金（外貨）
        BigDecimal l_bdForeignEstimatedPrice = l_estimatedPriceCalcResult.getForeignEstimatedPrice();

        if (l_bdForeignEstimatedPrice != null)
        {
            l_response.foreignDeliveryPrice = WEB3StringTypeUtility.formatNumber(
                l_bdForeignEstimatedPrice.doubleValue());
        }

        //受渡代金（外貨）      = 債権受渡代金計算結果.受渡代金（円貨）
        BigDecimal l_bdEstimatedPrice = l_estimatedPriceCalcResult.getEstimatedPrice();

        if (l_bdEstimatedPrice != null)
        {
            l_response.yenDeliveryPrice = WEB3StringTypeUtility.formatNumber(
                l_bdEstimatedPrice.doubleValue());
        }

        //確認時発注日        = get発注日()の戻り値
        l_response.checkDate = l_datOrderBizDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit応募/買付注文)<BR>
     * 債券応募/買付注文登録を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「submit応募/買付注文」参照。 <BR>
     * ========================================================<BR>
     *  シーケンス図(「submit応募/買付注文」)<BR>
     * 　@　@:  1.17.3 ＜分岐処理＞is判定フラグ()の戻り値 == false の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.bd.message.WEB3BondApplyBuyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C9BF9D0271
     */
    protected WEB3BondApplyBuyCompleteResponse submitRecruitBuyOrder(
        WEB3BondApplyBuyCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRecruitBuyOrder(WEB3BondApplyBuyCompleteRequest) ";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストが未指定(null)です。");
        }

        //1.1: validate( )
        l_request.validate();

        //1.2: get債券銘柄(long)
        FinApp l_finapp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finapp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productMgr =
            (WEB3BondProductManager) l_tradingModule.getProductManager();

        WEB3BondProduct l_product =
            (WEB3BondProduct) l_productMgr.getBondProduct(Long.parseLong(l_request.productId));

        //1.3: validate注文受付可能(String)
        WEB3BondTradingTimeManagement.validateOrderAccept(l_product);

        //1.4: get発注日( )
        Date l_datOrderBizDate =
            WEB3BondTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //1.5: get補助口座( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.6:validate取引可能顧客(補助口座 : SubAccount)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();

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

        //1.7:validate（応募/買付）注文(SubAccount, 債券銘柄, String, String, double)
        WEB3BondOrderManager l_orderMgr = (WEB3BondOrderManager) l_tradingModule.getOrderManager();

        String l_strTradeDiv = l_request.tradeDiv;
        double l_dblFaceAmount = Double.parseDouble(l_request.faceAmount);
        l_orderMgr.validateRecruitOrBuyOrder(
            l_subAccount,
            l_product,
            l_strTradeDiv,
            l_request.settleDiv,
            l_dblFaceAmount);

        //1.8: 債券注文種別判定(注文種別, String)
        //　@取引：　@リクエストデータ.取引区分 == "買付"の場合、92：国内仕切取引。
        //　@　@　@　@　@　@リクエストデータ.取引区分 == "応募"の場合、35：募集取引。
        String l_strTrade = null;
        if (WEB3BondDealDivDef.BUY.equals(l_strTradeDiv))
        {
            l_strTrade = WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING;
        }
        else if (WEB3BondDealDivDef.RECRUIT.equals(l_strTradeDiv))
        {
            l_strTrade = WEB3DealTypeDef.RECRUIT_TRADING;
        }
        WEB3BondOrderTypeJudge l_orderTypeJudge = new WEB3BondOrderTypeJudge(
            OrderTypeEnum.BOND_BUY,
            l_strTrade);

        //1.9:is外貨建( )
        boolean l_blnIsForeignCurrency = l_product.isForeignCurrency();

        //1.10:＜分岐処理＞is外貨建()の戻り値 == true の場合
        BigDecimal l_bdFxRate = null;
        if (l_blnIsForeignCurrency)
        {
            BigDecimal l_bigDecimal = new BigDecimal("0");
            //1.10.1:get為替レート(債券銘柄, 債券注文種別判定, String, BigDecimal, boolean)
            l_bdFxRate = l_orderMgr.getFxRate(l_product, l_orderTypeJudge, l_request.settleDiv, l_bigDecimal, false);
        }

        //1.11: create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄, String, Branch)
        WEB3BondExecuteDateInfo l_execDateInfo = l_orderMgr.createBondExecutionDateInfo(
            l_datOrderBizDate,
            l_orderTypeJudge,
            l_product,
            l_request.settleDiv,
            l_subAccount.getMainAccount().getBranch());

        //1.12: calc受渡代金(債券注文種別判定, BigDecimal, BigDecimal, BigDecimal, 債券銘柄, 債券約定日情報)
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_tradingModule.getBizLogicProvider();

        //　@数量：　@リクエストデータ.額面金額
        BigDecimal l_bdFaceAmt = new BigDecimal(l_request.faceAmount);

        //　@注文単価：　@債券銘柄.買付単価
        BigDecimal l_bdBuyPrice = new BigDecimal(String.valueOf(l_product.getBuyPrice()));

        WEB3BondEstimatedPriceCalcResult l_estimatedPriceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_orderTypeJudge,
                l_bdFaceAmt,
                l_bdBuyPrice,
                l_bdFxRate,
                l_product,
                l_execDateInfo);

        //1.13:  get代理入力者( )
        Trader l_trader = this.getTrader();

        //1.14:  create拡張債券新規注文内容(Trader, 債券注文種別判定, String,
        //double, double, TaxTypeEnum, java.util.Date, String)
        WEB3BondNewOrderSpec l_orderSpec = WEB3BondNewOrderSpec.createBondNewOrderSpec(
            l_trader,
            l_orderTypeJudge,
            l_product.getProductCode(),
            l_dblFaceAmount,
            l_product.getBuyPrice(),
            TaxTypeEnum.NORMAL,
            l_execDateInfo.getDeliveryDate(),
            l_request.settleDiv);

        //1.15: 債券注文更新インタセプタ( )
        WEB3BondOrderUpdateInterceptor l_updateInterceptor = new WEB3BondOrderUpdateInterceptor();

        //1.16: プロパティ・セット
        l_updateInterceptor.setBondExecuteDateInfo(l_execDateInfo);
        l_updateInterceptor.setBondEstimatedPriceCalcResult(l_estimatedPriceCalcResult);
        l_updateInterceptor.setBondNewOrderSpec(l_orderSpec);

        //1.17:＜分岐処理＞リクエストデータ.決済区分 == ”円貨” の場合
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
        {
            //1.17.1: validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[],
            //注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);

            WEB3TPTradingPowerResult l_tpResult =
                l_tpTradingPowerService.validateTradingPower(
                    (WEB3GentradeSubAccount) l_subAccount,
                    new Object[]{l_updateInterceptor},
                    new Object[]{l_orderSpec},
                    OrderTypeEnum.BOND_BUY,
                    true);

            //1.17.2:  is判定フラグ( )
            boolean l_blnIsResultFlg = l_tpResult.isResultFlg();

            //1.17.3: ＜分岐処理＞is判定フラグ()の戻り値 == false の場合、例外をスローする。
            if (!l_blnIsResultFlg)
            {
                log.debug("取引余力チェックエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "取引余力チェックエラー。");
            }
        }

        //1.18: setThreadLocalPersistenceEventInterceptor(
        //arg0 : BondOrderManagerPersistenceEventInterceptor)
        l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);

        //1.19:  submitNewOrder(arg0 : SubAccount, arg1 : ProductTypeEnum, arg2 : NewOrderSpec, arg3 : long, arg4 : String, arg5 : boolean)
        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderSubmissionResult l_submitNewOrderResult = l_orderMgr.submitNewOrder(
            l_subAccount,
            ProductTypeEnum.BOND,
            l_orderSpec,
            l_lngOrderId,
            l_request.password,
            true);

        if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitNewOrder" +
                l_submitNewOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.20: ＜分岐処理＞リクエストデータ.紹介区分 ≠ null の場合
        if (l_request.introduceStoreDiv != null)
        {
            //1.20.1: get債券注文単位By注文ID(long)
            WEB3BondOrderUnit l_orderUnit = l_orderMgr.getBondOrderUnitByOrderId(l_lngOrderId);

            //1.20.2: 注文単位紹介区分( )
            WEB3GentradeOrderUnitIntroduceDiv l_orderUnitIntroduceDiv =
                new WEB3GentradeOrderUnitIntroduceDiv();

            //1.20.3: プロパティ・セット
            //注文単位ＩＤ：　@債券注文単位.注文単位ID
            l_orderUnitIntroduceDiv.setOrderUnitId(l_orderUnit.getOrderUnitId());

            //　@口座ＩＤ：　@債券注文単位.口座ID
            l_orderUnitIntroduceDiv.setAccountId(l_orderUnit.getAccountId());

            //　@銘柄タイプ：　@債券注文単位.銘柄タイプ
            l_orderUnitIntroduceDiv.setProductType(l_orderUnit.getProductType());

            //　@紹介区分：　@リクエストデータ.紹介区分
            l_orderUnitIntroduceDiv.setIntroduceBranchDiv(l_request.introduceStoreDiv);

            //　@紹介店コード：　@リクエストデータ.紹介店コード
            l_orderUnitIntroduceDiv.setIntroduceBranchCode(l_request.introduceStoreCode);

            //　@更新者コード：　@get代理入力者()の戻り値 ≠ null の場合、扱者.扱者コード
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

            //1.20.4:  saveNew注文単位紹介区分( )
            l_orderUnitIntroduceDiv.saveNewOrderUnitIntroduceDivRow();
        }

        //1.21: createResponse( )
        WEB3BondApplyBuyCompleteResponse l_response =
            (WEB3BondApplyBuyCompleteResponse) l_request.createResponse();

        //1.22: プロパティ・セット
        //確認時発注日        = get発注日()の戻り値
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
