head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeClosingContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正返済入力サービス実装(WEB3OptionChangeClosingContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李海波 (中訊) 新規作成
              001: 2004/07/23 王暁傑 (中訊) OrderTypeEnumでWEB3IfoTradingDivDefを差し替える
              002: 2004/07/28 王暁傑 (中訊) 時価取得インタフェースの取得方法@を修正
              003: 2004/07/30 王暁傑 対応バッグ WEB3_IFO_UT-000085
              004: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
              005: 2004/08/13 li-qiang　@(中訊) STBUG(IFO_ST-000103)を対応
              006: 2004/08/14 王暁傑 対応 【株価指数オプション】ソースコードチェック指摘事項(JP)20040802 No014
              007: 2004/08/15 呉艶飛　@(中訊) STBUG(IFO_ST-000083)を対応
              008: 2006/07/14 李　@俊　@(中訊)　@ 仕様変更　@モデル476,491
              009: 2006/07/28 唐性峰  (中訊)   モデルNo.534対応    
              010: 2006/09/22 郭英  (中訊)   モデルNo.557
              011: 2006/10/13 唐性峰　@(中訊)   モデルNo.566
Revesion History : 2007/06/08 孟亜南 (中訊) 仕様変更モデルNo.697 No.658 
Revesion History : 2007/06/21 孟亜南 (中訊) 仕様変更モデルNo.712
Revesion History : 2007/07/24 孟亜南 (中訊) モデル777
Revesion History : 2007/11/20 トウ鋒鋼 (中訊) モデル795
Revesion History : 2008/04/10 張騰宇 (中訊) モデル864
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeInputResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeClosingContractInputService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (OP訂正返済入力サービスImpl)<BR>
 * 株価指数オプション訂正返済入力サービス実装クラス<BR>
 *
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3OptionChangeClosingContractInputServiceImpl extends WEB3OptionClientRequestService
    implements WEB3OptionChangeClosingContractInputService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3OptionChangeClosingContractInputServiceImpl.class);

    /**
     * @@roseuid 40C0BAF00271
     */
    public WEB3OptionChangeClosingContractInputServiceImpl()
    {

    }

    /**
     * 株価指数オプション訂正返済入力サービス処理を実施する。<BR>
     * <BR>
     * this.get訂正返済入力画面()メソッドをコールする。<BR>
     * @@param l_inRequest - リクエスト
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 407A47F7007C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_inRequest) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_inRequest)";
        log.entering(STR_METHOD_NAME);

        if (!(l_inRequest instanceof WEB3OptionsCloseMarginChangeInputRequest))
        {
            log.debug("パラメータタイプ不正");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }

        WEB3OptionsCloseMarginChangeInputRequest l_request =
            (WEB3OptionsCloseMarginChangeInputRequest)l_inRequest;
        WEB3OptionsCloseMarginChangeInputResponse l_response =
            this.getCloseChangeInputScreen(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get訂正返済入力画面)<BR>
     * 株価指数OPの訂正返済入力画面表示サービスを実施する。<BR>
     * <BR>
     * 「（OP訂正返済入力）入力画面表示データ取得」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3OptionsCloseMarginChangeInputResponse
     * @@throws WEB3BaseException
     */
    public WEB3OptionsCloseMarginChangeInputResponse getCloseChangeInputScreen(
        WEB3OptionsCloseMarginChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCloseChangeInputScreen(WEB3OptionsCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //1.1 validate()
            l_request.validate();

            //1.2 getOrder(注文ID : long)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

            //get訂正対象注文単位
            IfoOrderUnit l_ifoOrderUnit = this.getChangeOrderUnit(l_request);
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

            //1.4 get補助口座( )
            SubAccount l_subAccount = this.getSubAccount();

            //1.5 validate注文(補助口座, String) String = 先物／オプション区分
            l_orderManager.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);

            //1.6 getInstance()
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations =
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

            //validate注文訂正可能
            this.validateOrderForChangeability(l_ifoOrderUnit);

            //1.8 validate市場ＩＤ(long) long = 市場ＩＤ
            long l_lngMarketId = l_ifoOrderUnitRow.getMarketId();
            Market l_market = l_ifoOrderManagerReusableValidations.validateMarketID(l_lngMarketId);

            //1.9 validate銘柄ＩＤ(long) long = 銘柄ＩＤ
            long l_lngProductId = l_ifoOrderUnitRow.getProductId();
            WEB3IfoProductImpl l_productImpl = l_ifoOrderManagerReusableValidations.validateProductID(l_lngProductId);
            IfoProductRow l_ifoProductRow = (IfoProductRow)l_productImpl.getDataSourceObject();

            boolean l_blnBuySell = false;
            //is買建
            SideEnum l_sideEnum = l_ifoOrderUnit.getSide();
            if (SideEnum.BUY.equals(l_sideEnum))
            {
                //注文単位.getSide() = ”買”の場合、”false”
                l_blnBuySell = false;
            }
            else if (SideEnum.SELL.equals(l_sideEnum))
            {
                //注文単位.getSide() = ”売”の場合、”true”
                l_blnBuySell = true;
            }

            //1.14 validate取引銘柄(先物OP銘柄, 市場, boolean, boolean)
            WEB3IfoTradedProductImpl l_tradedProduct =
                l_ifoOrderManagerReusableValidations.validateTradedProduct(
                    l_productImpl,
                    (WEB3GentradeMarket)l_market,
                    l_blnBuySell,
                    false);

            //validate閉局後訂正取消受付可能(銘柄タイプ : ProductTypeEnum, 
            //先物／オプション区分 : String, 部店 : 部店, 立会区分 : String, 発注日 : Date)
            ProductTypeEnum l_productType = l_ifoOrderUnitRow.getProductType();

            //先物／オプション区分
            String l_strFutureOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();

            WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
            //部店
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_gentradeSubAccount.getWeb3GenBranch();

            //立会区分
            String l_strSessionType = l_ifoOrderUnitRow.getSessionType();

            //発注日
            String l_strBizDateOrder = l_ifoOrderUnitRow.getBizDate();

            Date l_datBizDate = WEB3DateUtility.getDate(l_strBizDateOrder, "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_productType,
                l_strFutureOptionDiv,
                l_branch,
                l_strSessionType,
                l_datBizDate);

            //1.16 getQuote(TradedProduct, RealType)
            FinApp finApp = (FinApp) Services.getService(FinApp.class);
            WEB3QuoteDataSupplierService  l_quoteSupplier =
                (WEB3QuoteDataSupplierService ) finApp.getTradingModule(
                    ProductTypeEnum.IFO).getQuoteDataSupplierService();
            WEB3IfoQuoteData l_ifoQuoteData = null;
            if (((WEB3GentradeMainAccount)l_subAccount.getMainAccount()).isRealCustomer())
            {
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote((IfoTradedProduct)l_tradedProduct, RealType.REAL);
            }
            else
            {
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote((IfoTradedProduct)l_tradedProduct, RealType.DELAY);
            }

            //1.17 getCurrentPrice( )(WEB3IfoQuoteDataImpl::getCurrentPrice)
            double l_dblCurrentPrice = l_ifoQuoteData.getCurrentPrice();

            //1.18  getCurrentPriceTime( )(WEB3IfoQuoteDataImpl::getCurrentPriceTime)
            Timestamp l_tsCurrentPriceTime = l_ifoQuoteData.getCurrentPriceTime();

            //1.19 getChange( )(WEB3IfoQuoteDataImpl::getChange)
            double l_dblChange = l_ifoQuoteData.getChange();

            //1.20 取扱可能注文条件(String, ProductTypeEnum, String, String)
            Institution l_institution = l_subAccount.getInstitution();
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond = null;
            l_gentradeHandlingOrderCond = new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                WEB3MarginTradingDivDef.DEFAULT);

            //1.21 取扱可能注文単価区分取得(boolean,boolean)
            String[] l_strHandlingPossibleOrderPriceDiv =
                l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(false, l_blnBuySell);

            //1.22 取扱可能執行条件取得( )
            String[] l_strHandlingPossibleExecCond = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();

            //1.23 get執行条件一覧(String[], String[])
            //[引数]
            // 注文単価区分一覧：取扱可能注文条件.取扱可能注文単価区分取得()
            // 執行条件一覧：取扱可能注文条件.取扱可能執行条件取得()
            l_strHandlingPossibleExecCond = 
                l_orderManager.getHandlingPossibleExecConds(l_strHandlingPossibleOrderPriceDiv, l_strHandlingPossibleExecCond);

            //1.24 getW指値用執行条件一覧(String[])
            //[引数]
            // get執行条件一覧()の戻り値
            //注文単位：　@注文単位
            String[] l_strWLimitExecutionConditionTypes =
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(
                    l_strHandlingPossibleExecCond,
                    l_ifoOrderUnit);
            
            //1.25 取扱可能注文期限区分取得( )
            String[] l_strHandlingPossibleExpirationDateType =
                l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();

            //1.26 取扱可能発注条件取得( )
            String[] l_strHandlingPossibleOrderCond = l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();

            //set取引最終日(Date)
            l_gentradeHandlingOrderCond.setTradingEndDate(l_tradedProduct.getLastTradingDate());

            //is出来るまで注文単位（注文単位）
            boolean l_blnIsCarriedOrderUnit =
                l_orderManager.isCarriedOrderUnit(l_ifoOrderUnit);
            WEB3OptionsCloseMarginChangeInputResponse l_response = null;

            //1.28 (*1)
            Date[] l_datHoliday = null;
            Date l_datLineEndDay = null;
            Date l_datStartDay = null;
            if (l_blnIsCarriedOrderUnit)
            {

                //1.28.1  get出来るまで注文最終日(出来るまで注文from日付 : Date)
                //  出来るまで注文可能な最終日付を取得する。
                //[引数]
                IfoOrderUnitRow l_orderUnitRow =
                    (IfoOrderUnitRow)l_orderManager.getFirstOrderUnit(l_ifoOrderUnit).getDataSourceObject();
                String l_strBizDate = l_orderUnitRow.getBizDate();
                Date l_datFrom = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
                l_datLineEndDay =
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datFrom);

                //1.28.2 get出来るまで注文開始日()
                l_datStartDay = l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(null,l_datLineEndDay);

                //1.26.3 出来るまで注文の有効期限までのすべての休日（祝日）を配列で返却する。
                l_datHoliday = l_gentradeHandlingOrderCond.getExpirationDateHoliday(l_datStartDay);
            }

            //1.29 get市場閉局警告指数(部店, String)
            WEB3GentradeBranch l_gentradeBranch = null;
            l_gentradeBranch =  (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
            String[] l_strWEB3GentradeTradingTimeManagement =
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_gentradeBranch,
                    l_ifoOrderUnitRow.getFutureOptionDiv());

            //create建玉明細ByOrder(IfoOrderUnit)
            WEB3FuturesOptionsContractUnit[] l_contractUnits =
                this.createContractUnitByOrder(l_ifoOrderUnit);

            //1.31 getＷ指値用有効状態区分(注文単位 : IfoOrderUnit)
            String l_strWlimitEnableStatusDiv =
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_ifoOrderUnit);

            //1.32 getＷ指値用切替前注文単価(注文単位 : IfoOrderUnit)
            String l_strWlimitBefChgLimitPrice =
                WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_ifoOrderUnit);

            //1.33 getＷ指値用切替前執行条件(注文単位 : IfoOrderUnit)
            String l_strWLimitBefChgExecCondType =
                WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_ifoOrderUnit);

            //is夕場まで注文
            boolean l_blnEveningSessionOrder = l_orderManager.isEveningSessionOrder(l_ifoOrderUnit);

            //1.34 createResponse( )
            l_response = (WEB3OptionsCloseMarginChangeInputResponse)l_request.createResponse();

            //1.35 プロパティセット
            // レスポンス.注文単価区分一覧＝取扱可能注文単価区分取得の戻り値
            l_response.orderPriceDivList = l_strHandlingPossibleOrderPriceDiv;

            //レスポンス.執行条件一覧＝get執行条件一覧()の戻り値
            //（ただし、訂正注文が出来るまで注文、又は夕場まで注文、又は逆指値注文の場合、
            //”無条件”をセット。W指値注文の場合、getＷ指値用執行条件一覧（)の戻り値をセット。）
            String[] l_strExecutionConditions =new String[1];
            l_strExecutionConditions[0]=WEB3ExecutionConditionDef.NO_CONDITION;

            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                l_response.execCondList = l_strWLimitExecutionConditionTypes;

            }
            else if (l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull()
                && !l_blnEveningSessionOrder
                && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                l_response.execCondList = l_strHandlingPossibleExecCond;
            }
            else
            {
                l_response.execCondList = l_strExecutionConditions;
            }

            // レスポンス.注文期限区分一覧＝取扱可能注文期限区分取得の戻り値
            l_response.expirationDateTypeList = l_strHandlingPossibleExpirationDateType;

            // レスポンス.有効期限開始日＝get出来るまで注文開始日の戻り値
            l_response.expirationStartDate    = WEB3DateUtility.toDay(l_datStartDay);

            // レスポンス.有効期限最終日＝get出来るまで注文最終日<取引最終日考慮>の返り値
            l_response.expirationEndDate = WEB3DateUtility.toDay(l_datLineEndDay);

            // レスポンス.有効期限内祝日一覧＝get注文期限内祝日一覧の戻り値
            l_response.holidayList            = l_datHoliday;

            // レスポンス.発注条件区分一覧＝取扱可能発注条件取得の戻り値
            l_response.orderCondTypeList      = l_strHandlingPossibleOrderCond;

            //レスポンス.W指値用執行条件一覧＝getW指値用執行条件一覧()の戻り値
            l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypes;

            //レスポンス.立会区分 = 注文単位.get立会区分()
            l_response.sessionType = l_ifoOrderUnitRow.getSessionType();

            // レスポンス.取引区分＝（以下のとおり）
            //    注文単位.注文種別="608"（OP買建返済注文（売返済）） => "5"
            //    注文単位.注文種別="607"（OP売建返済注文（買返済）） => "6"
            if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_SELL_CONTRACT;
            }
            else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_BUY_CONTRACT;
            }

            // レスポンス.取引市場＝注文単位.市場コード(SONAR)
            l_response.marketCode        = l_ifoOrderUnitRow.getSonarMarketCode();

            // レスポンス.指数種別＝先物OP銘柄.原資産銘柄コード
            l_response.targetProductCode = l_productImpl.getUnderlyingProductCode();

            // レスポンス.限月＝先物OP銘柄.限月
            l_response.delivaryMonth     = l_ifoProductRow.getMonthOfDelivery();

            // レスポンス.オプション商品区分＝（以下のとおり）
            //    先物OP銘柄.先物オプション商品="2"（コールオプション） => "C"
            //    先物OP銘柄.先物オプション商品="3"（プットオプション） => "P"
            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
            {
                l_response.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
            {
                l_response.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }

            // レスポンス.行使価格＝先物OP銘柄.行使価格
            double l_dblStrikePrice = l_ifoProductRow.getStrikePrice();
            if (Double.isNaN(l_dblStrikePrice))
            {
                l_dblStrikePrice = 0D;
            }
            l_response.strikePrice     = WEB3StringTypeUtility.formatNumber(l_dblStrikePrice);

            //getCurrentPriceの返り値
            //時価が0の場合、nullを設定する
            if (l_dblCurrentPrice == 0D)
            {
                l_response.currentPrice = null;
            }
            else
            {
                //時価が0でない場合、取得した現在値を設定する
                l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
            }

            // レスポンス.前日比＝getChange()の戻り値
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

            // レスポンス.取引時間(時価発表時間)＝getCurrentPriceTime()の戻り値
            l_response.currentPriceTime = l_tsCurrentPriceTime;

            // レスポンス.決済順序＝注文単位.決済順序
            l_response.closingOrder    = l_ifoOrderUnitRow.getClosingOrder();

            // レスポンス.建玉明細＝create建玉明細ByOrderの戻り値
            l_response.contractUnits = l_contractUnits;

            // レスポンス.注文数量＝if注文単位.決済順序 != "0"(ランダム)の場合のみ、注文単位.注文数量をセット
            double l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_ifoOrderUnitRow.getClosingOrder()))
            {
                l_response.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            }

            // レスポンス.注文単価区分＝(注文単位.isMarketOrder()＝＝true)の場合 "成行"、以外 "指値"
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            if (l_ifoOrderUnit.isMarketOrder())
            {
                l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            // レスポンス.注文単価＝注文単位.指値
            if (l_ifoOrderUnitRow.getLimitPrice() == 0)
            {
                l_response.limitPrice   = null;
            }
            else
            {
                l_response.limitPrice   = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getLimitPrice());
            }

            //レスポンス.執行条件＝（以下のとおり）
            //レスポンス.執行条件＝先物OPデータアダプタ.get執行条件（PR層）(注文単位.執行条件)の戻り値
            l_response.execCondType = 
                WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getExecutionConditionType());

            //レスポンス.注文期限区分＝先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);
            l_response.expirationDateType = l_strExpirationDateType;

            //レスポンス.注文有効期限＝先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値
            //が"出来るまで注文"の場合のみ、注文単位.注文失効日をセット。以外の場合、nullをセット。
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
            {
                l_response.expirationDate = WEB3DateUtility.toDay(l_ifoOrderUnitRow.getExpirationDate());
            }
            else
            {
                l_response.expirationDate = null;
            }


            // レスポンス.発注条件区分＝注文単位.発注条件
            l_response.orderCondType  = l_ifoOrderUnitRow.getOrderConditionType();

            //＜注文単位.発注条件＝１（逆指値）の場合＞
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_response.orderCondType))
            {
                //  レスポンス.逆指値用プレミアム/原資産価格＝注文単位.逆指値基準値タイプ
                l_response.stopPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();

                //  レスポンス.逆指値用発注条件単価＝注文単位.逆指値基準値
                l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

                //  レスポンス.逆指値用発注条件演算子＝注文単位.発注条件演算子
                l_response.stopOrderCondOperator       = l_ifoOrderUnitRow.getOrderCondOperator();
            }
            //＜注文単位.発注条件＝２（W指値）の場合＞
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_response.orderCondType))
            {
                //  レスポンス.W指値用プレミアム/原資産価格＝注文単位.逆指値基準値タイプ
                l_response.wlimitPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();

                //  レスポンス.W指値用発注条件単価＝注文単位.逆指値基準値
                l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

                //  レスポンス.W指値用発注条件演算子＝注文単位.発注条件演算子
                l_response.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();

                // レスポンス.W指値用注文単価区分＝if 注文単位.(W指値)訂正指値＝0 then "0"(成行) else "1"(指値)
                // レスポンス.W指値用注文単価＝if 注文単位.(W指値)訂正指値 != "0"(指値)の場合のみ、注文単位.(W指値)訂正指値をセット
                if (l_ifoOrderUnitRow.getWLimitPrice() == 0)
                {
                    l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    l_response.wLimitPrice = null;
                }
                else
                {
                    l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
                }

                //レスポンス.W指値用執行条件＝先物OPデータアダプタ.get執行条件（PR層）(注文単位.（W指値)執行条件)
                l_response.wlimitExecCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
                    l_ifoOrderUnitRow.getWLimitExecCondType());
            }
            
            // レスポンス.W指値用有効状態区分＝getＷ指値用有効状態区分()の戻り値
            l_response.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;
            
            //レスポンス.W指値用切替前注文単価＝getＷ指値用切替前注文単価()の戻り値
            l_response.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;
            
            //レスポンス.W指値用切替前執行条件＝getＷ指値用切替前執行条件()の戻り値
            l_response.wlimitBefChgExecCondType = l_strWLimitBefChgExecCondType;
            
            //レスポンス.元発注条件区分＝注文単位.元発注条件
            l_response.orgOrderCondType = l_ifoOrderUnitRow.getOrgOrderConditionType();
            
            //レスポンス.元プレミアム／原資産価格＝注文単位.元逆指値基準値タイプ
            l_response.orgPremium_underlyingAssets = 
                l_ifoOrderUnitRow.getOrgStopPriceType();

            //レスポンス.元発注条件単価＝注文単位.元逆指値基準値
            if (l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
            {
                l_response.orgOrderCondPrice = null;
            }
            else
            {
                l_response.orgOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getOrgStopOrderPrice());
            }
            //レスポンス.元発注条件演算子＝注文単位.元発注条件演算子
            l_response.orgCondOperator = l_ifoOrderUnitRow.getOrgOrderCondOperator();
            
            //レスポンス.元Ｗ指値用注文単価区分＝先物OPデータアダプタ.get元W指値用注文単価区分(注文単位)
            l_response.orgWLimitOrderPriceDiv = 
                WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_ifoOrderUnit);
            //レスポンス.元Ｗ指値用注文単価＝元Ｗ指値用注文単価区分が"指値"の場合のみ、
            //先物OPデータアダプタ.get元Ｗ指値用注文単価（注文単位）の返り値
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orgWLimitOrderPriceDiv))
            {
                l_response.orgWLimitPrice = 
                    WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_ifoOrderUnit);
            }
            //レスポンス.元Ｗ指値用執行条件＝先物OPデータアダプタ.get元W指値用執行条件(注文単位)
            l_response.orgWlimitExecCondType = 
                WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_ifoOrderUnit);
            // レスポンス.概算受渡代金＝注文単位.概算受渡代金
            l_response.estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());

            // レスポンス.取引終了警告文言＝get市場閉局警告指数の戻り値
            l_response.messageSuspension = l_strWEB3GentradeTradingTimeManagement;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get訂正対象注文単位)<BR>
     * 訂正対象の注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図<BR>
     * 「オプション訂正返済入力/（OP訂正返済入力）入力画面表示データ取得」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3OptionsCloseMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3OptionsCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        // getOrderUnits
        long l_lngID = Long.parseLong(l_request.id);
        OrderUnit[] l_ifoOrderUnits = l_orderManager.getOrderUnits(l_lngID);

        log.exiting(STR_METHOD_NAME);
        return (IfoOrderUnit)l_ifoOrderUnits[0];
    }

    /**
     * (validate注文訂正可能)<BR>
     * 訂正対象注文が、訂正可能な状態かどうかをチェックする。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図<BR>
     * 「オプション訂正返済入力/（OP訂正返済入力）入力画面表示データ取得」参照<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        //getInstance
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        //validate注文訂正可能状態()
        try
        {
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(l_orderUnit.getOrder());
        }
        catch (OrderValidationException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create建玉明細ByOrder)<BR>
     * 引数の注文単位に関連する建玉明細の一覧を作成する。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図<BR>
     * 「オプション訂正返済入力/（OP訂正返済入力）入力画面表示データ取得」参照<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnitByOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnitByOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //create建玉明細ByOrder(long)
        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            l_orderManager.createContractUnitByOrder(l_orderUnit.getOrderId());

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
}
@
