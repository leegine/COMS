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
filename	WEB3BondSellServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券売却サービスImpl(WEB3BondSellServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 唐性峰 (中訊) 新規作成
                 : 2006/09/29 趙林鵬 (中訊) モデル 094.105 ＤＢ更新仕様No.013
                   2006/10/12 柴雙紅 (中訊)  WEBⅢ開発標準の見直しの対応（new BigDecimal部分）
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondNewOrderSpec;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUpdateInterceptor;
import webbroker3.bd.WEB3BondPositionManager;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.message.WEB3BondSellCompleteRequest;
import webbroker3.bd.message.WEB3BondSellCompleteResponse;
import webbroker3.bd.message.WEB3BondSellConfirmRequest;
import webbroker3.bd.message.WEB3BondSellConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondSellService;

/**
 * (債券売却サービスImpl)<BR>
 * 債券売却サービス実装クラス<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondSellServiceImpl extends WEB3BondClientRequestService implements WEB3BondSellService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSellServiceImpl.class);

    /**
     * @@roseuid 44FBFD3A0128
     */
    public WEB3BondSellServiceImpl()
    {

    }

    /**
     * 債券売却処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * validate売却注文、submit売却付注文<BR>
     * のいずれかのメソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E93E610353
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
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        //validate売却注文
        if (l_request instanceof WEB3BondSellConfirmRequest)
        {
            l_response = this.validateSellOrder(
                (WEB3BondSellConfirmRequest) l_request);
        }
        //submit売却注文
        else if (l_request instanceof WEB3BondSellCompleteRequest)
        {
            l_response = this.submitSellOrder(
                (WEB3BondSellCompleteRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate売却注文)<BR>
     * 債券売却注文発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validate売却注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return webbroker3.bd.message.WEB3BondSellConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E9470401FC
     */
    protected WEB3BondSellConfirmResponse validateSellOrder(WEB3BondSellConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSellOrder(WEB3BondSellConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 getAsset(ID : long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondPositionManager l_bondPositionManager =
            (WEB3BondPositionManager)l_tradingModule.getPositionManager();
        Asset l_asset = null;
        try
        {
            l_asset = l_bondPositionManager.getAsset(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 get債券銘柄(long)
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_asset.getProduct().getProductId());

        //1.4 validate注文受付可能(String)
        WEB3BondTradingTimeManagement.validateOrderAccept(l_bondProduct);

        //1.5 get補助口座( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.6 validate取引可能顧客(補助口座 : SubAccount)
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引可能顧客チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "取引可能顧客チェックエラー");
        }

        //1.7 get代理入力者( )
        Trader l_trader = this.getTrader();

        //1.8 債券注文種別判定(注文種別, String)
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.BOND_SELL,
                WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);

        //1.9  get発注日( )
        Date l_datBizDate = WEB3BondTradingTimeManagement.getOrderBizDate();

        //1.10 create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄, String, Branch)
        //発注日： 取得した発注日
        //債券注文種別判定：　@作成した債券注文種別判定
        //債券銘柄： 取得した債券銘柄
        //決済区分：　@リクエスト.決済区分
        //部店：補助口座.getMainAccount().getBranch()の戻り値
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        WEB3BondExecuteDateInfo l_bondExecutDateInfo =
            l_bondOrderManager.createBondExecutionDateInfo(
                l_datBizDate,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.settleDiv,
                l_subAccount.getMainAccount().getBranch());

        //1.11 create拡張債券新規注文内容(Trader, 債券注文種別判定, String, double,
        //                              double, TaxTypeEnum, java.util.Date, String)
        //[引数]
        //オペレータ： get代理入力者()の戻り値
        //債券注文種別判定： 作成した債券注文種別判定
        //銘柄コード(WEB3)： 取得した債券銘柄.銘柄コード(WEB3)
        //数量： リクエストデータ.額面金額
        //単価： 取得した債券銘柄.売付単価
        //税区分： 取得した保有資産.税区分
        //受渡日： 生成した債券約定日情報.get受渡日()の戻り値
        //決済区分： リクエストデータ.決済区分
        WEB3BondNewOrderSpec l_bondNewOrderSpec =
            WEB3BondNewOrderSpec.createBondNewOrderSpec(
                l_trader,
                l_bondOrderTypeJudge,
                l_bondProduct.getProductCode(),
                Double.parseDouble(l_request.faceAmount),
                l_bondProduct.getSellPrice(),
                l_asset.getTaxType(),
                l_bondExecutDateInfo.getDeliveryDate(),
                l_request.settleDiv);

        //1.12 validate売却注文(SubAccount, 債券銘柄, 拡張債券新規注文内容)
        l_bondOrderManager.validateSellOrder(
            l_subAccount,
            l_bondProduct,
            l_bondNewOrderSpec);

        //1.13 is外貨建( )
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();

        //1.14  ＜分岐処理＞is外貨建()の戻り値 == true の場合
        double l_dblExchangeRate = 0.0D;
        if (l_blnIsForeignCurrency)
        {
            //1.14.1 get通貨( )
            WEB3GentradeCurrency l_gentradeCurrency = l_bondProduct.getCurrency();

            //1.14.2 get為替レート(is買付 : boolean, is約定計算 : boolean, 入力為替レート : double)
            l_dblExchangeRate = l_gentradeCurrency.getExchangeRate(false, false, 0);
        }


        //1.15  calc受渡代金(債券注文種別判定, BigDecimal, BigDecimal, BigDecimal, 債券銘柄, 債券約定日情報)
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_tradingModule.getBizLogicProvider();

        BigDecimal l_bdFxRate = null;

        if (l_blnIsForeignCurrency)
        {
            l_bdFxRate = new BigDecimal(String.valueOf(l_dblExchangeRate));
        }
        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_bondOrderTypeJudge,
                new BigDecimal(l_request.faceAmount),
                new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())),
                l_bdFxRate,
                l_bondProduct,
                l_bondExecutDateInfo);

        //1.16 createNewOrderId( )
        long l_lngnewOrderId = l_bondOrderManager.createNewOrderId();

        //1.17 createResponse( )
        WEB3BondSellConfirmResponse l_response =
            (WEB3BondSellConfirmResponse)l_request.createResponse();

        //1.18 プロパティセット
        //注文ID　@　@　@　@　@　@＝　@createNewOrderId()の戻り値
        l_response.orderId = WEB3StringTypeUtility.formatNumber(l_lngnewOrderId);

        //売買代金（外貨）　@＝　@債券受渡代金計算結果.売買代金（外貨）
        if (l_bondEstimatedPriceCalcResult.getForeignTradePrice() != null)
        {
            l_response.foreignTradePrice =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getForeignTradePrice().doubleValue());
        }

        //売買代金（円貨）　@＝　@債券受渡代金計算結果.売買代金（円貨）
        if (l_bondEstimatedPriceCalcResult.getTradingPrice() != null)
        {
            l_response.yenTradePrice =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getTradingPrice().doubleValue());
        }

        //経過利子（外貨）　@＝　@債券受渡代金計算結果.経過利子（外貨）
        if (l_bondEstimatedPriceCalcResult.getForeignAccruedInterest() != null)
        {
            l_response.foreignAccruedInterest =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getForeignAccruedInterest().doubleValue());
        }

        //経過利子（円貨）　@＝　@債権受渡代金計算結果.経過利子（円貨）
        if (l_bondEstimatedPriceCalcResult.getAccruedInterest() != null)
        {
            l_response.yenAccruedInterest =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getAccruedInterest().doubleValue());
        }

        //受渡代金（外貨）　@＝　@債権受渡代金計算結果.受渡代金（外貨）
        if (l_bondEstimatedPriceCalcResult.getForeignEstimatedPrice() != null)
        {
            l_response.foreignEstDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getForeignEstimatedPrice().doubleValue());
        }

        //受渡代金（円貨）　@＝　@債権受渡代金計算結果.受渡代金（円貨）
        if (l_bondEstimatedPriceCalcResult.getEstimatedPrice() != null)
        {
            l_response.yenEstDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getEstimatedPrice().doubleValue());
        }

        //確認時発注日　@　@　@＝　@get発注日()の戻り値
        l_response.checkDate = l_datBizDate;

        //1.19  レスポンスデータ

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit売却注文)<BR>
     * 債券売却注文登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submit売却注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return webbroker3.bd.message.WEB3BondSellCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E947700315
     */
    protected WEB3BondSellCompleteResponse submitSellOrder(WEB3BondSellCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitSellOrder(WEB3BondSellCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 getAsset(ID : long)
        //保有資産を取得する。
        //[引数]
        //ID： リクエストデータ.ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondPositionManager l_bondPositionManager =
            (WEB3BondPositionManager)l_tradingModule.getPositionManager();

        Asset l_asset = null;

        try
        {
            l_asset = l_bondPositionManager.getAsset(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 get債券銘柄(long)
        //債券銘柄を取得する。
        //[引数]
        //銘柄ID： 取得した保有資産.銘柄ID
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_asset.getProduct().getProductId());

        //1.4 validate注文受付可能(String)
        //受付時間チェック、緊急停止チェック、バッチ処理中チェックを行なう。
        //[引数]
        //債券銘柄：　@取得した債券銘柄
        WEB3BondTradingTimeManagement.validateOrderAccept(l_bondProduct);

        //1.5 get補助口座( )
        //補助口座を取得する。
        SubAccount l_subAccount = this.getSubAccount();

        //1.6 validate取引可能顧客(補助口座 : SubAccount)
        //顧客別取引停止属性チェックを行なう。
        //[引数]
        //補助口座： 取得した補助口座
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引可能顧客チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "取引可能顧客チェックエラー");
        }

        //1.7 get代理入力者( )
        //代理入力者を取得する。
        Trader l_trader = this.getTrader();

        //1.8 債券注文種別判定(注文種別, String)
        //債券注文種別判定オブジェクトを作成する。
        //[引数]
        //注文種別： OrderTypeEnum."債券売り注文"
        //取引： "国内仕切取引"
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.BOND_SELL,
                WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);

        //1.9 get発注日(確認時発注日 : Date)
        //発注日を取得する。
        //[引数]
        //確認時発注日： リクエストデータ.確認時発注日
        Date l_orderBizDate =
            WEB3BondTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //1.10 create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄, String, Branch)
        //債券約定日情報を作成する。
        //[引数]
        //発注日： 取得した発注日
        //債券注文種別判定：　@作成した債券注文種別判定
        //債券銘柄： 取得した債券銘柄
        //決済区分：　@リクエスト.決済区分
        //部店：　@補助口座.getMainAccount().getBranch()の戻り値
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        WEB3BondExecuteDateInfo l_bondExecutDateInfo =
            l_bondOrderManager.createBondExecutionDateInfo(
                l_orderBizDate,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.settleDiv,
                l_subAccount.getMainAccount().getBranch());

        //1.11 create拡張債券新規注文内容(Trader, 債券注文種別判定, String, double,
        //                               double, TaxTypeEnum, java.util.Date, String)
        //オペレータ： get代理入力者()の戻り値
        //債券注文種別判定： 作成した債券注文種別判定
        //銘柄コード(WEB3)： 取得した債券銘柄.銘柄コード(WEB3)
        //数量： リクエストデータ.額面金額
        //単価： 取得した債券銘柄.売付単価
        //税区分： 取得した保有資産.税区分
        //受渡日： 生成した債券約定日情報.get受渡日()の戻り値
        //決済区分： リクエストデータ.決済区分
        WEB3BondNewOrderSpec l_bondNewOrderSpec =
            WEB3BondNewOrderSpec.createBondNewOrderSpec(
                l_trader,
                l_bondOrderTypeJudge,
                l_bondProduct.getProductCode(),
                Double.parseDouble(l_request.faceAmount),
                l_bondProduct.getSellPrice(),
                l_asset.getTaxType(),
                l_bondExecutDateInfo.getDeliveryDate(),
                l_request.settleDiv);

        //1.12 validate売却注文(SubAccount, 債券銘柄, 拡張債券新規注文内容)
        //注文内容をチェックする。
        //[引数]
        //補助口座： 取得した補助口座
        //債券銘柄： 取得した債券銘柄
        //拡張債券新規注文内容： 生成した拡張債券新規注文内容
        l_bondOrderManager.validateSellOrder(
            l_subAccount,
            l_bondProduct,
            l_bondNewOrderSpec);

        //1.13 is外貨建( )
        //外貨建銘柄かどうかを判定する。
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();

        //1.14 ＜分岐処理＞is外貨建()の戻り値 == true の場合
        double l_dblExchangeRate = 0.0D;
        if (l_blnIsForeignCurrency)
        {
            //1.14.1  get通貨( )
            //（共通）通貨を取得する。
            WEB3GentradeCurrency l_currency = l_bondProduct.getCurrency();

            //1.14.2 get為替レート(is買付 : boolean, is約定計算 : boolean, 入力為替レート : double)
            //為替レートを取得する。
            //[引数]
            //is買付： false
            //is約定計算： false
            //入力為替レート： 0
            l_dblExchangeRate = l_currency.getExchangeRate(false, false, 0);
        }

        //1.15 calc受渡代金(債券注文種別判定, BigDecimal, BigDecimal, BigDecimal, 債券銘柄, 債券約定日情報)
        //債券受渡代金計算結果を取得する。
        //[引数]
        //債券注文種別判定： 作成した債券注文種別判定
        //注文単価： 債券銘柄.売付単価
        //為替レート： is外貨建()の戻り値 == true の場合は取得した為替レート
        //　@　@　@　@　@　@ is外貨建()の戻り値 == false の場合はnull
        //債券銘柄： 取得した債券銘柄
        //債券約定日情報： 生成した債券約定日情報
        //数量： リクエストデータ.額面金額
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_tradingModule.getBizLogicProvider();

        BigDecimal l_bdFxRate = null;

        if (l_blnIsForeignCurrency)
        {
            l_bdFxRate = new BigDecimal(String.valueOf(l_dblExchangeRate));
        }
        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_bondOrderTypeJudge,
                new BigDecimal(l_request.faceAmount),
                new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())),
                l_bdFxRate,
                l_bondProduct,
                l_bondExecutDateInfo);

        //1.16 債券注文更新インタセプタ( )
        //債券注文更新インタセプタを生成する。
        WEB3BondOrderUpdateInterceptor l_bondOrderUpdateInterceptor =
            new WEB3BondOrderUpdateInterceptor();

        //1.17 プロパティセット
        //拡張債券新規注文内容： 生成した拡張債券新規注文内容
        l_bondOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

        //債券約定日情報： 生成した債券約定日情報
        l_bondOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecutDateInfo);

        //債券受渡代金計算結果： calc受渡代金()の戻り値
        l_bondOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_bondEstimatedPriceCalcResult);

        //1.18 setThreadLocalPersistenceEventInterceptor(更新インタセプタ : BondOrderManagerPersistenceEventInterceptor)
        //更新インタセプタの設定を行う。
        //[引数]
        //更新インタセプタ： 生成した債券注文更新インタセプタ
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(l_bondOrderUpdateInterceptor);

        //1.19 submitNewOrder(補助口座 : SubAccount, 銘柄タイプ : ProductTypeEnum, 注文内容 : NewOrderSpec,
        //                    注文ID : long, 取引パスワード : String, isSkip発注審査 : boolean)
        //債券注文を登録する。
        //[引数]
        //補助口座： 取得した補助口座
        //銘柄タイプ： ProductTypeEnum.債券
        //注文内容： 生成した拡張債券新規注文内容
        //注文ID： リクエストデータ.注文ID
        //取引パスワード： リクエストデータ.暗証番号
        //isSkip発注審査： true
        OrderSubmissionResult l_submitNewOrderResult = l_bondOrderManager.submitNewOrder(
            l_subAccount,
            ProductTypeEnum.BOND,
            l_bondNewOrderSpec,
            Long.parseLong(l_request.orderId),
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

        //1.20 createResponse( )
        WEB3BondSellCompleteResponse l_response =
            (WEB3BondSellCompleteResponse)l_request.createResponse();

        //1.21  プロパティセット
        //以下の通り、プロパティをセットする。
        //更新日時　@＝　@現在日時
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        //1.22  レスポンスデータ

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
