head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeClosingContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正返済入力サービス実装(WEB3FuturesChangeClosingContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 李媛 (中訊) 新規作成
                   2004/07/26 李媛 (中訊) Interfaceを変更するため、ソースを変更しました。
                   2006/07/28 肖志偉 (中訊) 仕様変更No.489,534
                   2006/09/22 郭英  (中訊)   モデルNo.557
                   2006/10/13 唐性峰　@(中訊)   モデルNo.566
Revesion History : 2007/06/21 張騰宇(中訊) モデル708
Revesion History : 2007/07/24 孟亜南(中訊) モデル777
Revesion History : 2007/11/20 周墨洋 (中訊) 仕様変更 モデル800
Revesion History : 2007/11/28 周墨洋 (中訊) 仕様変更 Javaソース（基本設計と合っていない実装）No.005
Revesion History : 2008/03/13 張騰宇(中訊) モデル834 837 838 857
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
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
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeClosingContractInputService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物訂正返済入力サービスImpl)<BR>
 * 株価指数先物訂正返済入力サービス実装クラス<BR>
 *
 * @@author 李媛
 * @@version 1.0
 */
public class WEB3FuturesChangeClosingContractInputServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesChangeClosingContractInputService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesChangeClosingContractInputServiceImpl.class);

    public WEB3FuturesChangeClosingContractInputServiceImpl()
    {

    }

    /**
     * 株価指数先物訂正返済入力サービス処理を実施する。<BR>
     * <BR>
     * this.get訂正返済入力画面()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3FuturesCloseMarginChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8BC3301CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "execute(WEB3GenRequest l_inRequest)";
        log.entering(STR_METHOD_NAME);

        if (!(l_request instanceof WEB3FuturesCloseMarginChangeInputRequest))
        {
            log.debug("パラメータタイプ不正");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3FuturesCloseMarginChangeInputRequest l_FRequest =
            (WEB3FuturesCloseMarginChangeInputRequest)l_request;

        WEB3FuturesCloseMarginChangeInputResponse l_response =
            this.getCloseChangeInputScreen(l_FRequest);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get訂正返済入力画面)<BR>
     * 株価指数先物の訂正返済入力画面表示サービスを実施する。 <BR>
     * <BR>
     * 「（先物訂正返済入力）入力画面表示データ取得」参照。<BR>
     * @@param l_FRequest - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3FuturesCloseMarginChangeInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesCloseMarginChangeInputResponse getCloseChangeInputScreen(
        WEB3FuturesCloseMarginChangeInputRequest l_FRequest) throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "getCloseChangeInputScreen(WEB3FuturesCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //1.1 validate()
            l_FRequest.validate();

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

            IfoOrderUnit l_orderUnit = getChangeOrderUnit(l_FRequest);
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //1.4 get補助口座()
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

            //1.5 validate注文(補助口座, String) String = 先物／オプション区分
            l_orderManager.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations =
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

            //validate注文訂正可能(IfoOrderUnit)
            validateOrderForChangeability(l_orderUnit);

            //1.8 validate市場ＩＤ(long) long = 市場ＩＤ
            long l_lngMarketId = l_ifoOrderUnitRow.getMarketId();
            Market l_market = l_ifoOrderManagerReusableValidations.validateMarketID(l_lngMarketId);

            //1.9 validate銘柄ＩＤ(long) long = 銘柄ＩＤ
            long l_lngProductId = l_ifoOrderUnitRow.getProductId();
            WEB3IfoProductImpl l_productImpl =
                l_ifoOrderManagerReusableValidations.validateProductID(l_lngProductId);

            //is買建：
            //注文単位.getSide() = ”買”の場合、”false”
            //注文単位.getSide() = ”売”の場合、”true”
            boolean l_blnContract = false;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnContract = false;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_blnContract = true;
            }

            //1.14 validate取引銘柄(先物OP銘柄, 市場, boolean, boolean)
            WEB3IfoTradedProductImpl l_tradedProduct =
                l_ifoOrderManagerReusableValidations.validateTradedProduct(
                    l_productImpl,
                    (WEB3GentradeMarket)l_market,
                    l_blnContract,
                    false);

            //validate閉局後訂正取消受付可能(銘柄タイプ : ProductTypeEnum,
            //  先物／オプション区分 : String, 部店 : 部店, 立会区分 : String, 発注日 : Date)
            //[指定する引数]
            //銘柄タイプ：　@注文単位.銘柄タイプ
            //先物／オプション区分：　@注文単位.先物／オプション区分
            //部店：　@補助口座.get取引店()
            //立会区分：　@注文単位.立会区分
            //発注日：　@注文単位.発注日
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd"));

            //1.16 getQuote(TradedProduct, RealType)
            WEB3QuoteDataSupplierService  l_quoteSupplier =
                (WEB3QuoteDataSupplierService )l_tradingModule.getQuoteDataSupplierService();
            WEB3GentradeMainAccount l_gentradeMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            WEB3IfoQuoteData l_ifoQuoteData = null;
            if (l_gentradeMainAccount.isRealCustomer())
            {
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote(l_tradedProduct,RealType.REAL);
            }
            else
            {
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote(l_tradedProduct,RealType.DELAY);
            }

            //1.17 時価(現在値)の取得
            double l_dblCurrentPrice = l_ifoQuoteData.getCurrentPrice();

            //1.18 取引時刻の取得
            Timestamp l_tsCurrentPriceTime = l_ifoQuoteData.getCurrentPriceTime();

            //1.19 getChange()
            double l_dblChange = l_ifoQuoteData.getChange();

            //1.20 取扱可能注文条件
            Institution l_institution = l_subAccount.getInstitution();
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
                new WEB3GentradeHandlingOrderCond(
                    l_strInstitutionCode,
                    ProductTypeEnum.IFO,
                    WEB3FuturesOptionDivDef.FUTURES,
                    WEB3MarginTradingDivDef.DEFAULT);

            //1.21 取扱可能注文単価区分を取得する。
            String[] l_strHandlingPossibleOrderPriceDivs = null;
            l_strHandlingPossibleOrderPriceDivs =
                l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(false, l_blnContract);

            //1.22 取扱可能執行条件取得
            String[] l_strHandlingPossibleExecConds =
                l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();

            //1.23 執行条件一覧を取得する。
            l_strHandlingPossibleExecConds = 
                l_orderManager.getHandlingPossibleExecConds(
                    l_strHandlingPossibleOrderPriceDivs, l_strHandlingPossibleExecConds);

            //1.24 Ｗ指値用の執行条件一覧を取得する。 
            String[] l_strWLimitExecutionConditionTypeList = 
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(
                    l_strHandlingPossibleExecConds,
                    (IfoOrderUnit) l_orderUnit);
            
            //1.25 取扱可能注文期限区分を取得する。
            String[] l_strHandlingPossibleExpirationDateTypes =
                l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();

            //1.26 取扱可能発注条件取得( )
            String[] l_strHandlingPossibleOrderConds =
                l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();

            //set取引最終日(取引最終日 : Date)
            //取引最終日をセットする。  
            //［引数］  
            //先物OP取引銘柄.getLastTradingDate()の戻り値
            l_gentradeHandlingOrderCond.setTradingEndDate(l_tradedProduct.getLastTradingDate());

            //is出来るまで注文単位（注文単位）
            boolean l_blnIsCarriedOrderUnit =
                l_orderManager.isCarriedOrderUnit((IfoOrderUnit)l_orderUnit);

            //is出来るまで注文単位（注文単位）==True時
            Date l_datOrderUntilDeadLineStartDay = null;
            Date l_datOrderUntilDeadLineEndDay = null;
            Date[] l_datExpirationDateHolidays = null;
            if (l_blnIsCarriedOrderUnit)
            {
                //get出来るまで注文最終日<取引最終日考慮>(原注文発注日 : Date)
                //売買最終日を考慮した出来るまで注文最終日を取得する。
                //[引数]
                //出来るまで注文from日付：　@OP注文マネージャ.get初回注文の注文単位(注文単位).発注日
                WEB3FuturesOrderManagerImpl l_futuresOrderManager =
                    (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
                IfoOrderUnit l_ifoOrderUnit =
                    l_futuresOrderManager.getFirstOrderUnit((IfoOrderUnit)l_orderUnit);
                IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
                String l_strBizDate = l_orderUnitRow.getBizDate();
                Date l_datFrom = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
                l_datOrderUntilDeadLineEndDay =
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datFrom);

                //1.28.2 get出来るまで注文開始日()
                //売買最終日を考慮した出来るまで注文開始日を取得する。
                //[引数]
                //出来るまで注文from日付：　@null
                //出来るまで注文to日付：　@get出来るまで注文最終日<取引最終日考慮>()の戻り値
                l_datOrderUntilDeadLineStartDay = l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(null,l_datOrderUntilDeadLineEndDay);

                //1.28.3 出来るまで注文の有効期限までのすべての休日（祝日）を配列で返却する。
                l_datExpirationDateHolidays = l_gentradeHandlingOrderCond.getExpirationDateHoliday(l_datOrderUntilDeadLineStartDay);
            }

            //1.29 get市場閉局警告指数(部店, String)
            WEB3GentradeBranch l_gentradeBranch =
                (WEB3GentradeBranch)l_gentradeMainAccount.getBranch();
            String[] l_strTradeCloseSuspensions =
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_gentradeBranch,
                    WEB3FuturesOptionDivDef.FUTURES);

            //create建玉明細ByOrder(IfoOrderUnit)
            WEB3FuturesOptionsContractUnit[] l_contractUnits = createContractUnitByOrder(l_orderUnit);

            //1.31 getＷ指値用有効状態区分()
            String l_strWLimitEnableStatusDiv = 
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit)l_orderUnit);

            //is夕場まで注文(IfoOrderUnit)
            boolean l_blnIsEveningSessionOrder = l_orderManager.isEveningSessionOrder((IfoOrderUnit)l_orderUnit);

            //1.32 createResponse()
            WEB3FuturesCloseMarginChangeInputResponse l_response =
                (WEB3FuturesCloseMarginChangeInputResponse)l_FRequest.createResponse();

            //1.33 プロパティセット
            //レスポンス.注文単価区分一覧＝取扱可能注文単価区分取得の戻り値
            l_response.orderPriceDivList = l_strHandlingPossibleOrderPriceDivs;
            
            String[] l_strExecutionConditions =new String[1];
            l_strExecutionConditions[0]=WEB3ExecutionConditionDef.NO_CONDITION;
            //レスポンス.執行条件一覧
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                l_response.execCondList = l_strWLimitExecutionConditionTypeList;
            }
            else if(l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull()
                && !l_blnIsEveningSessionOrder
                && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                l_response.execCondList = l_strHandlingPossibleExecConds;
            }
            else
            {
                l_response.execCondList = l_strExecutionConditions;
            }
 
            //レスポンス.注文期限区分一覧＝取扱可能注文期限区分取得の戻り値
            l_response.expirationDateTypeList = l_strHandlingPossibleExpirationDateTypes;

            //レスポンス.有効期限開始日＝get出来るまで注文開始日の戻り値
            l_response.expirationStartDate = WEB3DateUtility.toDay(l_datOrderUntilDeadLineStartDay);

            //レスポンス.有効期限最終日＝get出来るまで注文最終日<取引最終日考慮>の戻り値
            l_response.expirationEndDate = WEB3DateUtility.toDay(l_datOrderUntilDeadLineEndDay);

            //レスポンス.有効期限内祝日一覧＝get注文期限内祝日一覧の戻り値
            l_response.holidayList = l_datExpirationDateHolidays;

            //レスポンス.発注条件区分一覧＝取扱可能発注条件取得の戻り値
            l_response.orderCondTypeList = l_strHandlingPossibleOrderConds;

            //レスポンス.Ｗ指値用執行条件一覧＝getＷ指値用執行条件一覧（)の戻り値
            l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeList;

            //レスポンス.立会区分 = 注文単位.立会区分
            l_response.sessionType = l_ifoOrderUnitRow.getSessionType();
                
            //レスポンス.取引区分＝（以下のとおり）
            //注文単位.注文種別="604"（先物買建返済注文（売返済）） => "5"
            //注文単位.注文種別="603"（先物売建返済注文（買返済）） => "6"
            if(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderUnit.getOrderType()))
            {
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_SELL_CONTRACT;
            }
            if(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderUnit.getOrderType()))
            {
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_BUY_CONTRACT;
            }

            //レスポンス.取引市場＝注文単位.市場コード(SONAR)
            l_response.marketCode = l_ifoOrderUnitRow.getSonarMarketCode();

            //レスポンス.指数種別＝先物OP銘柄.原資産銘柄コード
            l_response.targetProductCode = l_productImpl.getUnderlyingProductCode();

            //レスポンス.限月＝先物OP銘柄.限月
            IfoProductRow l_ifoProductRow = (IfoProductRow)l_productImpl.getDataSourceObject();
            l_response.delivaryMonth = l_ifoProductRow.getMonthOfDelivery();

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

            //レスポンス.前日比＝getChange()の戻り値
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

            //レスポンス.取引時間(時価発表時間)＝getCurrentPriceTime()の戻り値
            l_response.currentPriceTime = l_tsCurrentPriceTime;

            //レスポンス.決済順序＝注文単位・決済順序
            l_response.closingOrder = l_ifoOrderUnitRow.getClosingOrder();

            //レスポンス.建玉明細＝create建玉明細ByOrderの戻り値
            l_response.contractUnits = l_contractUnits;

            //レスポンス.注文数量＝注文単位.注文数量
            //レスポンス.注文数量＝if注文単位.決済順序 != "0"(ランダム)の場合のみ、注文単位.注文数量をセット
            double l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            if(Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0;
            }
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_ifoOrderUnitRow.getClosingOrder()))
            {
                l_response.futOrderQuantity =WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            }

            //レスポンス.注文単価区分＝(注文単位.isMarketOrder == true)の場合"成行"、 以外"指値"
            if (l_orderUnit.isMarketOrder())
            {
                l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            //レスポンス.注文単価＝注文単位.指値
            if (l_ifoOrderUnitRow.getLimitPrice() == 0)
            {
                l_response.limitPrice = null;
            }
            else
            {
                l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getLimitPrice());
            }
            
            //レスポンス.執行条件＝先物OPデータアダプタ.get執行条件（PR層）(注文単位.執行条件)
            l_response.execCondType = 
                WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getExecutionConditionType());

            //レスポンス.注文期限区分＝先物OPデータアダプタ.get注文期限区分(注文単位)()の戻り値
            //レスポンス.注文有効期限＝先物OPデータアダプタ.get注文期限区分(注文単位)()の戻り値が
            //出来るまで注文の場合のみ、注文単位.注文失効日をセット。
            //以外の場合、nullをセット
            String l_strExpirationDateType =
                WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);
            l_response.expirationDateType = l_strExpirationDateType;
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
            {
                l_response.expirationDate = l_ifoOrderUnitRow.getExpirationDate();
            }
            else
            {
                l_response.expirationDate = null;
            }

            //レスポンス.発注条件区分＝注文単位.発注条件
            l_response.orderCondType = l_ifoOrderUnitRow.getOrderConditionType();

            //＜注文単位.発注条件＝１（逆指値）の場合＞
            if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                //レスポンス.逆指値用発注条件単価＝注文単位.逆指値基準値
                l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

                //レスポンス.逆指値用発注条件演算子＝注文単位.発注条件演算子
                l_response.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
            }
            //＜注文単位.発注条件＝２（W指値）の場合＞
            else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                //レスポンス.W指値用発注条件単価＝注文単位.逆指値基準値
                l_response.wlimitOrderCondPrice  = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

                //レスポンス.W指値用発注条件演算子＝注文単位.発注条件演算子
                l_response.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();

                // レスポンス.W指値用注文単価区分＝if 注文単位.(W指値)訂正指値＝0 then "0"(成行) else "1"(指値)
                // レスポンス.W指値用注文単価＝if 注文単位.(W指値)訂正指値 != "0"(指値)の場合のみ、注文単位.(W指値)訂正指値をセット
                if(l_ifoOrderUnitRow.getWLimitPrice() == 0)
                {
                    l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    l_response.wLimitPrice = null;
                }
                else
                {
                    l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
                }
                
                //レスポンス.W指値用執行条件＝先物OPデータアダプタ.get執行条件（PR層）(注文単位.（W指値）執行条件)
                l_response.wlimitExecCondType = WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getWLimitExecCondType());
            }

            //レスポンス.W指値用有効状態区分＝先物OPデータアダプタ.getＷ指値用有効状態区分()の戻り値
            l_response.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
            
            //レスポンス.W指値用切替前注文単価＝先物OPデータアダプタ.getＷ指値用切替前注文単価(注文単位)
            l_response.wlimitBefChgLimitPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice((IfoOrderUnit)l_orderUnit);
            
            //レスポンス.W指値用切替前執行条件＝先物OPデータアダプタ.getＷ指値用切替前執行条件(注文単位)
            l_response.wlimitBefChgExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType((IfoOrderUnit)l_orderUnit);
            
            //レスポンス.元発注条件区分＝注文単位.元発注条件
            l_response.orgOrderCondType = l_ifoOrderUnitRow.getOrgOrderConditionType();
            
            //レスポンス.元発注条件単価＝注文単位.元逆指値基準値
            if (!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
            {
                l_response.orgOrderCondPrice = 
                    WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getOrgStopOrderPrice());
            }
            
            //レスポンス.元発注条件演算子＝注文単位.元発注条件演算子
            l_response.orgCondOperator = l_ifoOrderUnitRow.getOrgOrderCondOperator();
            
            //レスポンス.元Ｗ指値用注文単価区分＝先物OPデータアダプタ.get元W指値用注文単価区分(注文単位)
            String l_strOrgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv((IfoOrderUnit)l_orderUnit);
            l_response.orgWLimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
            
            //レスポンス.元Ｗ指値用注文単価＝元Ｗ指値用注文単価区分が"指値"の場合のみ、先物OPデータアダプタ.get元Ｗ指値用注文単価(注文単位)
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
            {
                l_response.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice((IfoOrderUnit)l_orderUnit);
            }
            
            //レスポンス.元Ｗ指値用執行条件＝先物OPデータアダプタ.get元W指値用執行条件(注文単位)
            l_response.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType((IfoOrderUnit)l_orderUnit);
            
            //レスポンス.概算決済損益＝注文単位.概算受渡代金（概算決済損益）
            l_response.estimatedSettleIncome = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());

            //レスポンス.取引終了警告文言＝get市場閉局警告指数の戻り値
            l_response.messageSuspension = l_strTradeCloseSuspensions;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get訂正対象注文単位)<BR>
     * 訂正対象の注文単位オブジェクトを取得する。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図<BR>
     * 「先物訂正返済入力/（先物訂正返済入力）入力画面表示データ取得」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3FuturesCloseMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "getChangeOrderUnit(WEB3FuturesCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        long l_lngID = Long.parseLong(l_request.id);

        //getOrderUnits(注文ID : long)
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngID);

        log.exiting(STR_METHOD_NAME);
        return (IfoOrderUnit)l_orderUnits[0];
    }

    /**
     * (validate注文訂正可能)<BR>
     * 訂正対象注文が、訂正可能な状態かどうかをチェックする。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図<BR>
     * 「先物訂正返済入力/（先物訂正返済入力）入力画面表示データ取得」参照<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "validateOrderForChangeability(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        try
        {
            //getInstance()
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

            //validate注文訂正可能状態(Order)
            //注文 ： 注文単位.getOrder()の戻り値
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(l_orderUnit.getOrder());

            log.exiting(STR_METHOD_NAME);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (create建玉明細ByOrder)<BR>
     * 引数の注文単位に関連する建玉明細の一覧を作成する。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図<BR>
     * 「先物訂正返済入力/（先物訂正返済入力）入力画面表示データ取得」参照<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnitByOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "createContractUnitByOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        //create建玉明細ByOrder(long)
        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            l_orderManager.createContractUnitByOrder(l_orderUnit.getOrderId());

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
}
@
