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
filename	WEB3EquityPTSChangeOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文訂正入力サービスImpl（WEB3EquityPTSChangeOrderInputServiceImpl.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/17 トウ鋒鋼 (中訊) 新規作成
Revision History : 2007/12/20 トウ鋒鋼 (中訊) 仕様変更　@モデル1217、1253、1265、1270
Revision History : 2008/02/04 トウ鋒鋼 (中訊) 障害対応　@U3064
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.message.WEB3EquityChangeInputRequest;
import webbroker3.equity.message.WEB3EquityChangeInputResponse;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderInputService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ((PTS)現物株式注文訂正入力サービスImpl)<BR>
 * (PTS)現物株式注文訂正入力サービス実装クラス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3EquityPTSChangeOrderInputServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityPTSChangeOrderInputService
{

    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSChangeOrderInputServiceImpl.class);

    /**
     * @@roseuid 4766072000E1
     */
    public WEB3EquityPTSChangeOrderInputServiceImpl()
    {

    }

    /**
     * (PTS)株式注文訂正入力サービスを実施する。<BR>
     * <BR>
     * this.get訂正入力画面()をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 473BD7E60078
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

        if (l_request instanceof WEB3EquityChangeInputRequest)
        {
            l_response = getChangeInputScreen((WEB3EquityChangeInputRequest)l_request);
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
     * (get訂正入力画面)<BR>
     * (PTS)株式注文訂正入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図「(PTS)注文訂正入力」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3EquityChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 473BD95A0002
     */
    protected WEB3EquityChangeInputResponse getChangeInputScreen(
        WEB3EquityChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeInputScreen(WEB3EquityChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3EquityTradingModule l_tradingModule =
            (WEB3EquityTradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityPTSOrderManager l_orderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //リクエスト.ID
        long l_lngOrderId = Long.parseLong(l_request.id);

        //訂正対象の注文単位オブジェクトを取得する。
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        //注文単位
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];

        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        WEB3GentradeMarket l_genMarket = null;
        try
        {
            l_genMarket = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }

        //reset市場コード(市場コード : String)
        WEB3EquityPTSTradingTimeManagement.resetMarketCode(l_genMarket.getMarketCode());

        //validate注文受付可能( )
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

        //getOrderValidator( )
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

        //補助口座
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //顧客 : this.get補助口座().getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //発注日 : PTS取引時間管理.get発注日( )
        Timestamp l_tsOrderBizDate =
            new Timestamp(WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime());

        //validate取引可能顧客(顧客 : 顧客, 発注日 : Timestamp)
        OrderValidationResult l_orderValidationResult =
            l_orderValidator.validateAccountForTrading(l_mainAccount, l_tsOrderBizDate);

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
        }

        //get市場閉局警告市場(部店, ProductTypeEnum, String)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        String[] l_strTradeCloseMarkets =
            WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                l_branch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);

        //株式銘柄 : 訂正対象の注文単位.getProduct( )
        WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_orderUnit.getProduct();

        //市場 : PTS注文マネージャ.validate市場コード( )
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        Market l_market = l_orderManager.validateMarket(l_genMarket.getMarketCode(), l_strInstitutionCode);

        //validate取引銘柄(株式銘柄, 市場)
        WEB3EquityTradedProduct l_tradedProduct =
            (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(l_equityProduct, l_market);

        //validate取扱可能PTS市場(部店, 取引銘柄)
        l_orderManager.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);

        //get注文単価区分一覧(部店, 取引銘柄)
        String[] l_orderPriceDivs = l_orderManager.getOrderPriceDivs(l_branch, l_tradedProduct);

        //validatePTS注文訂正可能状態(注文)
        l_orderManager.validatePTSOrderForChangeability(l_orderUnit.getOrder());

        //isインサイダー警告表示(補助口座, long)
        boolean l_blnInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(l_subAccount, l_orderUnitRow.getProductId());

        //validate顧客銘柄別取引停止（PTS）(補助口座, long, OrderTypeEnum)
        l_orderManager.validatePTSAccountProductOrderStop(
            l_subAccount, l_orderUnitRow.getProductId(), l_orderUnitRow.getOrderType());

        //取扱可能注文条件オブジェクトを生成する。
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_genMarket.getMarketCode());

        //取扱可能執行条件取得( )
        String[] l_handlingPossibleExecConds = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();

        SideEnum l_sideEnum = l_orderUnit.getSide();

        double l_dblTradingPower = 0;

        double l_dblEstimatedBookPrice = 0;

        //訂正対象の注文単位.getSide()=="買"の場合
        if (SideEnum.BUY.equals(l_sideEnum))
        {
            //取引余力サービス取得
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);

            //get株式買付可能額(補助口座 : 補助口座)
            l_dblTradingPower = l_tradingPowerService.getEquityTradingPower(l_subAccount);
        }
        //訂正対象の注文単位.getSide()=="売"の場合
        else if (SideEnum.SELL.equals(l_sideEnum))
        {
            //株式計算サービス取得
            WEB3EquityBizLogicProvider l_equityBizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();

            //calc概算簿価単価(long, SubAccount, TaxTypeEnum)
            l_dblEstimatedBookPrice = l_equityBizLogicProvider.calcEstimatedBookPrice(
                l_orderUnitRow.getProductId(),
                l_subAccount,
                l_orderUnitRow.getTaxType());
        }

        //拡張プロダクトマネージャ取得
        WEB3EquityProductManager l_equityProductManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();

        //get表示用時価情報(EqTypeTradedProduct, 補助口座)
        WEB3EquityProductQuote l_equityProductQuote =
            l_equityProductManager.getDisplayEquityProductQuote(l_tradedProduct, l_subAccount);

        //レスポンスクラスを生成する。
        WEB3EquityChangeInputResponse l_response =
            (WEB3EquityChangeInputResponse)l_request.createResponse();

        //＜現物株式入力共通レスポンス＞

        //注文単価区分一覧：　@PTS注文マネージャ.get注文単価区分一覧( )
        l_response.orderPriceDivList = l_orderPriceDivs;

        //値段条件一覧：　@"0:指定なし"
        l_response.priceCondList = new String[]{WEB3PriceConditionDef.DEFAULT};

        //執行条件一覧：　@取扱可能注文条件.取扱可能執行条件取得( )
        l_response.execCondList = l_handlingPossibleExecConds;

        //W指値用執行条件一覧：　@Null
        l_response.wlimitExecCondList = null;

        //有効期限開始日：　@Null
        l_response.expirationStartDate = null;

        //有効期限最終日：　@Null
        l_response.expirationEndDate = null;

        //有効期限内祝日一覧：　@Null
        l_response.holidayList = null;

        //取引終了警告市場コード一覧：　@PTS取引時間管理.get市場閉局警告市場( )
        l_response.messageSuspension = l_strTradeCloseMarkets;

        //インサイダー警告表示フラグ：　@PTS注文マネージャ.isインサイダー警告表示( )
        l_response.insiderWarningFlag = l_blnInsiderMessageSuspension;

        //時価区分：　@取得した株式銘柄時価情報.get時価区分( )
        l_response.currentPriceDiv = l_equityProductQuote.getQuoteTypeDiv();

        //時価（現在値）：　@取得した株式銘柄時価情報.get時価( )
        l_response.currentPrice =
            WEB3StringTypeUtility.formatNumber(l_equityProductQuote.getQuote());

        //前日比：　@取得した株式銘柄時価情報.get前日比( )
        l_response.comparedPreviousDay =
            WEB3StringTypeUtility.formatNumber(l_equityProductQuote.getComparedPreviousDay());

        //取引時間(時価発表時間)：　@取得した株式銘柄時価情報.get時価発表時間( )
        l_response.currentPriceTime = l_equityProductQuote.getQuoteTime();

        //板情報項目の設定仕様は以下の通り。
        //　@現在値：　@取得した株式銘柄時価情報.get現在値( )
        l_response.boardCurrentPrice = l_equityProductQuote.getBoardCurrentPrice();

        //　@現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻( )
        l_response.boardCurrentPriceTime = l_equityProductQuote.getBoardCurrentPriceTime();

        //　@現在値区分：　@取得した株式銘柄時価情報.get現在値区分( )
        l_response.boardCurrentPriceDiv = l_equityProductQuote.getBoardCurrentPriceDiv();

        //　@現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比( )
        l_response.boardChange = l_equityProductQuote.getBoardChange();

        //　@出来高：　@取得した株式銘柄時価情報.get出来高( )
        l_response.volume = l_equityProductQuote.getVolume();

        //　@出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻( )
        l_response.volumeTime = l_equityProductQuote.getVolumeTime();

        //　@買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分( )
        l_response.askPriceTitle = l_equityProductQuote.getAskPriceTitle();

        //　@買気配値：　@取得した株式銘柄時価情報.get買気配値( )
        l_response.askPrice = l_equityProductQuote.getAskPrice();

        //　@買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻( )
        l_response.askPriceTime = l_equityProductQuote.getAskPriceTime();

        //　@売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分( )
        l_response.bidPriceTitle = l_equityProductQuote.getBidPriceTitle();

        //　@売気配値：　@取得した株式銘柄時価情報.get売気配値( )
        l_response.bidPrice = l_equityProductQuote.getBidPrice();

        //　@売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻( )
        l_response.bidPriceTime = l_equityProductQuote.getBidPriceTime();

        //　@基準値段：　@取得した株式銘柄時価情報.get基準値段( )
        l_response.basePrice = l_equityProductQuote.getBasePrice();

        //＜現物株式注文訂正入力レスポンス＞
        //注文単位.注文種別＝”1：現物買注文”の場合
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnitRow.getOrderType()))
        {
            //買付可能金額：取引余力サービス.get株式買付可能額( )
            l_response.tradingPower = WEB3StringTypeUtility.formatNumber(l_dblTradingPower);

            //売買区分："1：買い"
            l_response.dealingType = WEB3BuySellTypeDef.BUY;
        }
        //注文単位.注文種別＝”2：現物売注文”の場合
        else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnitRow.getOrderType()))
        {
            //売買区分："2：売り"
            l_response.dealingType = WEB3BuySellTypeDef.SELL;

            //概算簿価単価：株式計算サービス.calc概算簿価単価( )
            l_response.estimatedBookPrice =
                WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        }

        //銘柄コード：　@注文単位.銘柄IDに該当する株式銘柄.銘柄コード
        l_response.productCode = l_equityProduct.getProductCode();

        EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject();

        //銘柄名：　@注文単位.銘柄IDに該当する株式銘柄.銘柄名
        l_response.productName = l_eqtypeProductRow.getStandardName();

        //市場コード：　@注文単位.市場IDに該当する市場マスタ.市場コード
        l_response.marketCode = l_genMarket.getMarketCode();

        //口座区分：　@株式データアダプタ.get口座区分(注文単位.getTaxType)
        l_response.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());

        //注文株数：　@注文単位.注文数量
        l_response.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());

        //内出来株数：　@注文単位.約定数量
        l_response.partContQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());

        //注文単位.isMarketOrder( )＝trueの場合
        if (l_orderUnit.isMarketOrder())
        {
            //注文単価区分："0：成行"
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        //注文単位.isMarketOrder( )＝falseの場合
        else
        {
            //注文単価区分："1：指値"
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

            //注文単価：注文単位.指値
            l_response.limitPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }

        //値段条件：　@注文単位.値段条件
        l_response.priceCondType = l_orderUnitRow.getPriceConditionType();

        //執行条件：　@PTS注文マネージャ.get執行条件（SONAR）(注文単位.執行条件)
        l_response.execCondType =
            l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnitRow.getExecutionConditionType());

        //PTS注文マネージャ.is出来るまで注文単位(注文単位)＝trueの場合
        if (l_orderManager.isCarriedOrderUnit(l_orderUnit))
        {
            //注文期限区分："2：出来るまで注文"
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;

            //注文有効期限：注文単位.注文失効日
            l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnit.getExpirationTimestamp());
        }
        //PTS注文マネージャ.is出来るまで注文単位(注文単位)＝falseの場合
        else
        {
            //注文期限区分："1：当日限り"
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }

        //発注条件区分：　@注文単位.発注条件
        String l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();
        l_response.orderCondType = l_strOrderConditionType;

        //注文単位.発注条件＝1（逆指値）の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //逆指値用発注条件単価：注文単位.逆指値
            if (!l_orderUnitRow.getStopOrderPriceIsNull())
            {
                l_response.stopOrderCondPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            }

            //逆指値用発注条件演算子: 注文単位.発注条件演算子
            l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
        }
        //注文単位.発注条件＝2（W指値）の場合
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //W指値用発注条件単価：注文単位.逆指値
            if (!l_orderUnitRow.getStopOrderPriceIsNull())
            {
                l_response.wlimitOrderCondPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            }
            //W指値用発注条件演算子：注文単位.発注条件演算子
            l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();

            if (l_orderUnitRow.getWLimitPrice() == 0)
            {
                //W指値用注文単価区分："0：成行"
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                //W指値用注文単価区分："1：指値"
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                //W指値用注文単価：注文単位.（W指値）訂正指値
                l_response.wLimitPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
            }

            //W指値用執行条件：拡張株式注文マネージャ.get執行条件（SONAR）(注文単位.（W指値）執行条件）
            l_response.wlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getWLimitExecCondType());
        }

        //W指値用有効状態区分：　@株式データアダプタ.getW指値用有効状態区分(注文単位)
        l_response.wlimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //W指値用切替前注文単価：　@株式データアダプタ.getW指値用切替前注文単価(注文単位)
        l_response.wlimitBefChgLimitPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        //W指値用切替前執行条件：　@株式データアダプタ.getW指値用切替前執行条件(注文単位)
        l_response.wlimitBefChgExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //元発注条件区分：　@注文単位.元発注条件
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //元発注条件単価：　@注文単位.元逆指値基準値
        if (!l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }

        //元発注条件演算子：　@注文単位.元発注条件演算子
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //元W指値用注文単価区分：　@株式データアダプタ.get元W指値用注文単価区分（注文単位）
        String l_strOrgWLimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        l_response.orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;

        //元W指値用注文単価：　@元W指値用注文単価区分が"1：指値"の場合、
        //   株式データアダプタ.get元W指値用注文単価（注文単位）
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
        {
            l_response.orgWlimitPrice = WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }

        //元W指値用執行条件：　@株式データアダプタ.get元W指値用執行条件（注文単位）
        l_response.orgWlimitExecCondType = WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //概算受渡代金：　@注文単位.概算受渡代金
        l_response.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
