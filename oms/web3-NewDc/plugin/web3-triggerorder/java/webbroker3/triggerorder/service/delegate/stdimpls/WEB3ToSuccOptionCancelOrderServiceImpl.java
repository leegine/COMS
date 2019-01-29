head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）OP取消注文サービImpl（WEB3ToSuccOptionCancelOrderServiceImpl.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 金傑 (中訊) 新規作成 モデルNo.280
Revision History : 2008/05/15 安陽 (中訊) 仕様変更 モデルNo.351
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.define.WEB3ToSuccOpProductTypeDef;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionCancelOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）OP取消注文サービImpl)<BR>
 * （連続）OP取消注文サービス実装クラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3ToSuccOptionCancelOrderServiceImpl extends WEB3OptionClientRequestService
    implements WEB3ToSuccOptionCancelOrderService
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionCancelOrderServiceImpl.class);

    /**
     * @@roseuid 47FDBE3F039A
     */
    public WEB3ToSuccOptionCancelOrderServiceImpl()
    {

    }

    /**
     * （連続）OP取消サービス処理を実施する。<BR>
     * <BR>
     * 　@リクエストデータの型により、以下のメソッドを呼び分ける。<BR>
     * <BR>
     * 　@　@[（連続）株価指数オプション取消注文確認リクエストの場合] <BR>
     * 　@　@　@this.validate注文()をコールする。<BR>
     * 　@　@[（連続）株価指数オプション取消注文完了リクエストの場合] <BR>
     * 　@　@　@this.submit注文()をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A911B50395
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

        //[（連続）株価指数オプション取消注文確認リクエストの場合]
        //this.validate注文()をコールする。
        if (l_request instanceof WEB3SuccOptionsCancelConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccOptionsCancelConfirmRequest)l_request);
        }
        //[（連続）株価指数オプション取消注文完了リクエストの場合] <BR>
        //this.submit注文()をコールする。
        else if (l_request instanceof WEB3SuccOptionsCancelCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccOptionsCancelCompleteRequest)l_request);
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


    /**
     * (validate注文)<BR>
     * （連続）OP取消の発注審査を行う。<BR>
     * <BR>
     * 「（（連続）OP取消サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3SuccOptionsCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A9167E00FB
     */
    protected WEB3SuccOptionsCancelConfirmResponse validateOrder(
        WEB3SuccOptionsCancelConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccOptionsCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータのチェック
        l_request.validate();

        //補助口座を取得する
        SubAccount l_subAccount = this.getSubAccount();

        //予約注文単位を取得する
        //【引数】
        //注文ID：　@リクエスト.ID
        WEB3ToSuccOrderManagerImpl l_toSuccOrderManagerImpl =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);

        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit =
            l_toSuccOrderManagerImpl.getReserveIfoOrderUnit(l_lngOrderId);

        //先物OP注文取消発注審査を行う
        //【引数】
        //補助口座　@　@　@：　@get補助口座()の戻り値
        //予約注文単位：　@get先物OP予約注文単位()の戻り値
        l_toSuccOrderManagerImpl.validateIfoCancelOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            l_ifoOrderUnit);

        //予約注文単位より建玉明細を作成する
        //【引数】
        //予約注文単位：　@get先物OP予約注文単位()の戻り値
        WEB3FuturesOptionsContractUnit[] l_futuresOptionsContractUnit =
            l_toSuccOrderManagerImpl.createIfoContractUnitByOrder(l_ifoOrderUnit);

        //市場閉局警告指数を取得する
        //【引数】
        //部店：補助口座.get取引店()
        //先物／オプション区分：　@”オプション”
        String[] l_strTradeCloseSuspension =
            WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(),
                WEB3FuturesOptionDivDef.OPTION);

        //先物OP銘柄オブジェクトを取得する
        IfoProduct l_ifoProduct = (IfoProduct)l_ifoOrderUnit.getProduct();

        //先物OP取引銘柄オブジェクトを取得する。
        TradedProduct l_tradedProduct = l_ifoOrderUnit.getTradedProduct();

        //時価情報を取得する
        //【引数】
        //tradedProduct(取引銘柄）：　@get取引銘柄()の戻り値の取引銘柄オブジェクト
        //realType：
        //顧客 = 補助口座.getMainAccount()
        //顧客.isリアル顧客()==trueの場合は”リアル”、  falseの場合は”20分ディレイ”をセット。
        WEB3IfoQuoteData l_quoteData = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3QuoteDataSupplierService l_supplierProvide =
            (WEB3QuoteDataSupplierService)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getQuoteDataSupplierService();
        RealType l_realType = null;
        WEB3GentradeMainAccount  l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        if (l_mainAccount.isRealCustomer())
        {
            l_realType = RealType.REAL;
        }
        else
        {
            l_realType = RealType.DELAY;
        }
        try
        {
            l_quoteData = (WEB3IfoQuoteData)l_supplierProvide.getQuote(
                l_tradedProduct,
                l_realType);
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

        //現在値を取得する
        double l_dblCurrentPrice = l_quoteData.getCurrentPrice();

        //前日比を取得する
        double l_dblChange = l_quoteData.getChange();

        //取引時間を取得する
        Timestamp l_tsCurrentPriceTime = l_quoteData.getCurrentPriceTime();

        //発注日を取得する
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //レスポンスデータ生成
        WEB3SuccOptionsCancelConfirmResponse l_response =
            (WEB3SuccOptionsCancelConfirmResponse)l_request.createResponse();

        IfoProductRow l_ifoProductRow = (IfoProductRow)l_ifoProduct.getDataSourceObject();

        //レスポンス.取引区分 = 先物OP予約注文単位.getメッセージ用取引区分()
        l_response.tradingType = l_ifoOrderUnit.getMsgTradingType();

        //レスポンス.取引市場 = 先物OP予約注文単位.get市場()の戻り値.市場コード(SONAR)
        MarketRow l_marketRow = (MarketRow)l_ifoOrderUnit.getMarket().getDataSourceObject();
        l_response.marketCode = l_marketRow.getSonarMarketCode();

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        //レスポンス.指数種別 = get銘柄()の戻り値.原資産銘柄コード
        l_response.targetProductCode = l_ifoProduct.getUnderlyingProductCode();

        //レスポンス.限月 = get銘柄()の戻り値.限月
        l_response.delivaryMonth = l_ifoProduct.getMonthOfDelivery();

        // レスポンス.オプション商品区分 = get銘柄()の戻り値.オプション商品区分
        if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
        {
            l_response.opProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
        }
        else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
        {
            l_response.opProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
        }

        // レスポンス.行使価格 = get銘柄()の戻り値.行使価格
        l_response.strikePrice =  WEB3StringTypeUtility.formatNumber(l_ifoProductRow.getStrikePrice());

        //レスポンス.注文数量 = 先物OP予約注文単位.注文数量
        double l_dblOrderQuantity = l_ifoOrderUnit.getQuantity();
        l_response.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);

        //レスポンス.内約定数量 = null
        l_response.partExecQuantity = null;

        //レスポンス.注文単価区分 = 先物OP予約注文単位.getメッセージ用注文単価区分()
        l_response.orderPriceDiv = l_ifoOrderUnit.getMsgOrderPriceDiv();

        //レスポンス.注文単価 = 先物OP予約注文単位.getメッセージ用注文単価()
        l_response.limitPrice = l_ifoOrderUnit.getMsgLimitPrice();

        //レスポンス.執行条件 = 先物OP予約注文単位.getメッセージ用執行条件()（※無条件が設定される）
        l_response.execCondType = l_ifoOrderUnit.getMsgExecCondType();

        //レスポンス.注文期限区分 = 先物OP予約注文単位.get注文期限区分()
        l_response.expirationDateType = l_ifoOrderUnit.getExpirationDateType();

        //レスポンス.注文有効期限 = 先物OP予約注文単位.getメッセージ用注文有効期限()
        l_response.expirationDate = l_ifoOrderUnit.getMsgExpirationDate();

        //レスポンス.発注条件区分 = 先物OP予約注文単位.getメッセージ用発注条件区分() 　@(※条件なしが設定される)
        l_response.orderCondType = l_ifoOrderUnit.getMsgOrderCondType();

        // レスポンス.逆指値プレミアム/原資産価格 = null
        l_response.stopPremium_underlyingAssets = null;
        //レスポンス.逆指値用注文条件単価  = null
        l_response.stopOrderCondPrice = null;
        //レスポンス.逆指値用注文条件演算子   = null
        l_response.stopOrderCondOperator = null;
        //レスポンス.W指値用注文条件単価    = null
        l_response.wlimitOrderCondPrice = null;
        //レスポンス.W指値用注文条件演算子   = null
        l_response.wlimitOrderCondOperator = null;
        // レスポンス.W指値用プレミアム/原資産価格
        l_response.wlimitPremium_underlyingAssets = null;
        //レスポンス.W指値用注文単価区分    = null
        l_response.wLimitOrderPriceDiv = null;
        //レスポンス.W指値用注文単価      = null
        l_response.wLimitPrice = null;
        //レスポンス.W指値用執行条件 = null
        l_response.wlimitExecCondType = null;
        //レスポンス.W指値用有効状態区分 = null
        l_response.wlimitEnableStatusDiv = null;
        //レスポンス.W指値用切替前注文単価 = null
        l_response.wlimitBefChgLimitPrice = null;
        //レスポンス.W指値用切替前執行条件 = null
        l_response.wlimitBefChgExecCondType = null;
        //レスポンス.元発注条件区分 = null
        l_response.orgOrderCondType = null;
        // レスポンス.元プレミアム/原資産価格   = null
        l_response.orgPremium_underlyingAssets = null;
        //レスポンス.元発注条件単価 = null
        l_response.orgOrderCondPrice = null;
        //レスポンス.元発注条件演算子  = null
        l_response.orgCondOperator = null;
        //レスポンス.元Ｗ指値用注文単価区分 = null
        l_response.orgWLimitOrderPriceDiv = null;
        //レスポンス.元Ｗ指値用注文単価 = null
        l_response.orgWLimitPrice = null;
        //レスポンス.元Ｗ指値用執行条件 = null
        l_response.orgWlimitExecCondType = null;

        //レスポンス.概算受渡代金 = 先物OP予約注文単位.概算受渡代金
        l_response.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_rsvIfoOrderUnitRow.getEstimatedPrice());

        //レスポンス.取引終了警告文言 = 取引時間管理.get市場閉局警告指数()の戻り値
        l_response.messageSuspension = l_strTradeCloseSuspension;

        //レスポンス.確認時発注日 = 取引時間管理.get発注日()の戻り値
        l_response.checkDate =  WEB3DateUtility.toDay(l_datOrderBizDate);

        //レスポンス.決済順序 = 先物OP予約注文単位.決済順序
        l_response.closingOrder = l_rsvIfoOrderUnitRow.getClosingOrder();

        //レスポンス.建玉明細  = （連続）create建玉明細ByOrder()の戻り値
        l_response.contractUnits = l_futuresOptionsContractUnit;

        //レスポンス.現在値  = getCurrentPrice()の戻り値
        if (GtlUtils.Double.isZero(l_dblCurrentPrice))
        {
            l_response.currentPrice = null;
        }
        else
        {
            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
        }
        //レスポンス.前日比 = getChange()の戻り値
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

        //レスポンス.取引時間 = getCurrentPriceTime()の戻り値
        l_response.currentPriceTime = l_tsCurrentPriceTime;

        //レスポンス.立会区分 = 先物OP予約注文単位.立会区分
        l_response.sessionType = l_rsvIfoOrderUnitRow.getSessionType();

        //レスポンス.単価調整値情報 = 先物OP予約注文単位.create単価調整値情報()
        l_response.priceAdjustmentValueInfo = l_ifoOrderUnit.createSuccPriceAdjustmentValueInfo();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * （連続）OP取消の注文を登録する。<BR>
     * <BR>
     * 「（（連続）OP取消サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3SuccOptionsCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A916A203A9
     */
    protected WEB3SuccOptionsCancelCompleteResponse submitOrder(
        WEB3SuccOptionsCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccOptionsCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする
        l_request.validate();

        //発注日のチェックを行う
        //【引数】
        //確認時発注日：　@リクエストデータ.確認時発注日
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //補助口座を取得する
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

        //先物OP予約注文単位を取得する
        //【引数】
        //注文ID：　@リクエストデータ.ID
        WEB3ToSuccOrderManagerImpl l_toSuccOrderManagerImpl =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);

        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            l_toSuccOrderManagerImpl.getReserveIfoOrderUnit(l_lngOrderId);

        //取消発注審査を行う
        //【引数】
        //補助口座：get補助口座()の戻り値
        //予約注文単位：　@get先物OP予約注文単位()の戻り値
        l_toSuccOrderManagerImpl.validateIfoCancelOrder(
            l_subAccount,
            l_toSuccIfoOrderUnitImpl);

        //予約注文取消処理を行う
        //【引数】
        //補助口座　@　@　@：　@get補助口座()の戻り値
        //予約注文単位：　@get先物OP予約注文単位()の戻り値
        //取引パスワード：　@リクエストデータ.暗証番号
        l_toSuccOrderManagerImpl.submitIfoCancelOrder(
            l_subAccount,
            l_toSuccIfoOrderUnitImpl,
            l_request.password);

        //レスポンスデータを生成する
        WEB3SuccOptionsCancelCompleteResponse l_response =
            (WEB3SuccOptionsCancelCompleteResponse)l_request.createResponse();

        //(*)レスポンスデータにプロパティをセットする。

        //更新時間    ＝　@現在日時（GtlUtils.getSystemTimestamp()）
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        //識別番号    ＝　@リクエスト.ID（＝先物OP予約注文単位.注文ID）
        l_response.orderActionId = l_request.id;
        //連続注文設定フラグ   ＝　@false（固定）
        l_response.succSettingFlag = false;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}


@
