head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeOpenContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正新規建入力サービスImpl(WEB3OptionChangeOpenContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/17 李頴淵 (中訊) 新規作成
              001: 2004/07/22 王暁傑 (中訊) WEB3OrderPriceDivDefでWEB3IfoOrderPriceDivDefを差し替える
              002: 2004/07/23 王暁傑 (中訊) OrderTypeEnumでWEB3IfoTradingDivDefを差し替える
              003: 2004/07/26 王暁傑 (中訊) 対応バッグ IFO_UT-000020 execute()を修正
              004: 2004/07/26 王暁傑 (中訊) 対応バッグ IFO_UT-000023 execute()を修正
              005: 2004/07/26 王暁傑 (中訊) 対応バッグ IFO_UT-000024 execute()を修正
              006: 2004/07/30 王暁傑 (中訊) 対応バッグ IFO_UT-000077 execute()を修正
              007: 2004/08/04 王暁傑 (中訊) 対応バッグ IFO_UT-000110 execute()を修正
              008: 2004/08/04 王暁傑 (中訊) 対応バッグ BUG60
              009: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
              010: 2004/08/14 王暁傑 対応 【株価指数オプション】ソースコードチェック指摘事項(JP)20040802
              011: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000083)を対応
              012: 2006/7/13 徐宏偉 (中訊) 仕様変更　@モデル469
              013: 2006/07/28 唐性峰  (中訊)   モデルNo.534対応
              014: 2006/10/13 唐性峰　@(中訊)   モデルNo.566対応
Revesion History : 2007/06/08 孟亜南 (中訊) 仕様変更モデルNo.696 No.666
Revesion History : 2007/06/21 孟亜南 (中訊) 仕様変更モデルNo.712
Revesion History : 2007/07/24 孟亜南 (中訊) モデル777
Revesion History : 2007/11/20 トウ鋒鋼 (中訊) モデル793
Revesion History : 2008/04/10 張騰宇 (中訊) モデル851
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
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
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeOpenContractInputService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (OP訂正新規建入力サービスImpl)<BR>
 * <BR>
 * 株価指数オプション訂正新規建入力サービス実装クラス<BR>
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3OptionChangeOpenContractInputServiceImpl
    extends WEB3OptionClientRequestService
    implements WEB3OptionChangeOpenContractInputService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionChangeOpenContractInputServiceImpl.class);

    /**
     * @@roseuid 40C0BAF2031C
     */
    public WEB3OptionChangeOpenContractInputServiceImpl()
    {

    }

    /**
     * 株価指数オプション訂正新規建入力サービス処理を実施する。<BR>
     * <BR>
     * this.create入力画面をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3OptionsOpenMarginChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 407A4F8C000F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3OptionsOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        if (!(l_request instanceof WEB3OptionsOpenMarginChangeInputRequest))
        {
            log.error("パラメータのタイプが不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータのタイプが不正");
        }

        WEB3GenResponse l_response =
            this.createInputScreen((WEB3OptionsOpenMarginChangeInputRequest)l_request);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get訂正対象注文単位)<BR>
     * get訂正対象注文単位<BR>
     * <BR>
     * 訂正対象の注文単位を取得する。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（OP訂正新規建入力）入力画面表示データ取得」参照<BR>
     * @@param l_request - (株価指数オプション訂正新規建入力画面リクエスト)<BR>
     * 株価指数オプション訂正新規建入力画面リクエスト
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3OptionsOpenMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3OptionsOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager();

        // 訂正対象の注文単位を取得する。
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id));
        IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderUnits[0];

        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }

    /**
     * (validate注文訂正可能)<BR>
     * validate注文訂正可能<BR>
     * <BR>
     * 訂正対象注文が、訂正可能な状態かどうかをチェックする。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（OP訂正新規建入力）入力画面表示データ取得」参照<BR>
     * @@param l_orderUnit - (IfoOrderUnit)<BR>
     * IfoOrderUnit<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_orderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            l_orderManagerReusableValidations.validateOrderForChangeability(l_orderUnit.getOrder());
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
     * (create入力画面)<BR>
     * 入力画面表示処理<BR>
     * <BR>
     * シーケンス図「（OP訂正新規建入力）入力画面表示データ取得」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3OptionsOpenMarginChangeInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3OptionsOpenMarginChangeInputResponse createInputScreen(
        WEB3OptionsOpenMarginChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createInputScreen(WEB3OptionsOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.validate
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager();

        // get訂正対象注文単位(株価指数オプション訂正新規建入力画面リクエスト)
        IfoOrderUnit l_orderUnit = this.getChangeOrderUnit(l_request);

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //4.get補助口座( )
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)getSubAccount();

        //5.validate注文(補助口座, String)
        l_orderManager.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);

        //6.getInstance
        WEB3IfoOrderManagerReusableValidations l_orderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        //1.7validate取扱可能新規建注文(SubAccount, boolean)
        l_orderManagerReusableValidations.validateHandlingOpenContractOrder(
            l_subAccount, SideEnum.BUY.equals(l_orderUnit.getSide()));

        // validate注文訂正可能(IfoOrderUnit)
        this.validateOrderForChangeability(l_orderUnit);

        //8.validate市場ＩＤ(long)
        Market l_market = l_orderManagerReusableValidations.validateMarketID(l_orderUnitRow.getMarketId());

        //9.validate銘柄ＩＤ(long)
        WEB3IfoProductImpl l_productImpl =
            l_orderManagerReusableValidations.validateProductID(l_orderUnitRow.getProductId());

        //10.validate取引銘柄(先物OP銘柄, 市場, boolean, boolean)
        boolean l_blnContract = false;
        if (SideEnum.BUY.equals(l_orderUnit.getSide()))
        {
            l_blnContract = true;
        }

        IfoTradedProduct l_tradedProduct = null;
        l_tradedProduct =
            (IfoTradedProduct)l_orderManagerReusableValidations.validateTradedProduct(
                l_productImpl, (WEB3GentradeMarket)l_market, l_blnContract, true);

        //validate閉局後訂正取消受付可能(銘柄タイプ : ProductTypeEnum,
        //先物／オプション区分 : String, 部店 : 部店, 立会区分 : String, 発注日 : Date)
        ProductTypeEnum l_productType = l_orderUnitRow.getProductType();

        //先物／オプション区分
        String l_strFutureOptionDiv = l_orderUnitRow.getFutureOptionDiv();

        //部店
        WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_subAccount.getWeb3GenBranch();

        //立会区分
        String l_strSessionType = l_orderUnitRow.getSessionType();

        Date l_datOrderBizDate =
            WEB3DateUtility.getDate(
                l_orderUnitRow.getBizDate(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
            l_productType,
            l_strFutureOptionDiv,
            l_gentradeBranch,
            l_strSessionType,
            l_datOrderBizDate);

        //12. getQuote(TradedProduct, RealType)
        WEB3QuoteDataSupplierService  l_quoteSupplier =
            (WEB3QuoteDataSupplierService)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getQuoteDataSupplierService();
        WEB3IfoQuoteData l_ifoQuoteData = null;
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        // RealType
        RealType l_realType = null;
        if (l_gentradeMainAccount.isRealCustomer())
        {
            l_realType = RealType.REAL;
        }
        else
        {
            l_realType = RealType.DELAY;
        }

        try
        {
            l_ifoQuoteData = l_quoteSupplier.getIfoQuote(l_tradedProduct, l_realType);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //13.getCurrentPrice( )
        double l_dblCurrentPrice = l_ifoQuoteData.getCurrentPrice();

        //14.getCurrentPriceTime( )
        Timestamp l_currentPriceTime = l_ifoQuoteData.getCurrentPriceTime();

        //15.getChange( )
        double l_dblGetChange = l_ifoQuoteData.getChange();

        //16.取扱可能注文条件
        Institution l_institution = l_subAccount.getInstitution();
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                l_orderUnitRow.getProductType(),
                WEB3FuturesOptionDivDef.OPTION,
                WEB3MarginTradingDivDef.DEFAULT);

        //17.取扱可能注文単価区分取得(boolean,boolean)
        String[] l_strHandlingPossibleOrderPriceDiv = null;
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderUnitRow.getOrderType()))
        {
            l_strHandlingPossibleOrderPriceDiv =
                l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(true, true);
        }
        if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderUnitRow.getOrderType()))
        {
            l_strHandlingPossibleOrderPriceDiv =
                l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(true, false);
        }

        //18.取扱可能執行条件取得( )
        String[] l_strHandlingPossibleExecCond = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();
        l_strHandlingPossibleExecCond =
            l_orderManager.getHandlingPossibleExecConds(
                l_strHandlingPossibleOrderPriceDiv, l_strHandlingPossibleExecCond);

        //getＷ指値用執行条件一覧(String[])
        String[] l_strWLimitExecutionConditionTypeLists =
            WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(
                l_strHandlingPossibleExecCond,
                l_orderUnit);

        //19.取扱可能注文期限区分取得( )
        String[] l_strHandlingPossibleExpirationDateType =
            l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();

        //20.取扱可能発注条件取得( )
        String[] l_strHandlingPossibleOrderCond = l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();

        //set取引最終日(Date)
        l_gentradeHandlingOrderCond.setTradingEndDate(l_tradedProduct.getLastTradingDate());

        //is出来るまで注文単位（注文単位）
        boolean l_blnIsCarriedOrderUnit =
            l_orderManager.isCarriedOrderUnit(l_orderUnit);

        //22. (*1)
        WEB3OptionsOpenMarginChangeInputResponse l_response = null;
        Date l_datOrderUntilDeadLineStartDay = null;
        Date l_datOrderUntilDeadLineEndDayTradingEndDate = null;
        Date[] l_datExpirationDateHoliday = null;
        if (l_blnIsCarriedOrderUnit)
        {
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_optionOrderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit =
                l_optionOrderManager.getFirstOrderUnit((IfoOrderUnit)l_orderUnit);
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            String l_strBizDate = l_ifoOrderUnitRow.getBizDate();
            Date l_datFrom = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
            // get出来るまで注文最終日<取引最終日考慮>(Date)
            l_datOrderUntilDeadLineEndDayTradingEndDate =
                l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datFrom);

            //get出来るまで注文開始日(Date, Date)
            l_datOrderUntilDeadLineStartDay =
                l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(
                    null, l_datOrderUntilDeadLineEndDayTradingEndDate);


            //25. get注文期限内祝日一覧( )
            l_datExpirationDateHoliday =
                l_gentradeHandlingOrderCond.getExpirationDateHoliday(l_datOrderUntilDeadLineStartDay);
        }

        //26.getMainAccount(口座ID : long)
        //27.get部店(String, String)
        Branch l_branch = l_gentradeMainAccount.getBranch();

        //28.get市場閉局警告指数(部店, String)
        String[] l_strTradeCloseSuspension =
             WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                (WEB3GentradeBranch)l_branch,
                WEB3FuturesOptionDivDef.OPTION);

        //29.getＷ指値用有効状態区分(IfoOrderUnit)
        String l_strWlimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //30.getＷ指値用切替前注文単価(IfoOrderUnit)
        String l_strWlimitBefChgLimitPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        //31.getＷ指値用切替前執行条件(IfoOrderUnit)
        String l_strWLimitBefChgExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //is夕場まで注文
        boolean l_blnEveningSessionOrder = l_orderManager.isEveningSessionOrder(l_orderUnit);

        //32.createResponse( )
        l_response = (WEB3OptionsOpenMarginChangeInputResponse)l_request.createResponse();

        //33.プロパティセット
        //レスポンス.注文単価区分一覧＝取扱可能注文単価区分取得の戻り値
        l_response.orderPriceDivList = l_strHandlingPossibleOrderPriceDiv;

        //レスポンス.執行条件一覧＝get執行条件一覧()の戻り値
        //（ただし、訂正注文が出来るまで注文、又は夕場まで注文、又は逆指値注文の場合、
        //”無条件”をセット。W指値注文の場合、getＷ指値用執行条件一覧（)の戻り値をセット。）
        String[] l_strExecutionConditions = new String[1];
        l_strExecutionConditions[0] = WEB3ExecutionConditionDef.NO_CONDITION;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.execCondList = l_strWLimitExecutionConditionTypeLists;
        }
        else if (l_orderUnitRow.getFirstOrderUnitIdIsNull()
            && !l_blnEveningSessionOrder
            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.execCondList = l_strHandlingPossibleExecCond;
        }
        else
        {
            l_response.execCondList = l_strExecutionConditions;
        }

        //レスポンス.注文期限区分一覧＝取扱可能注文期限区分取得の戻り値
        l_response.expirationDateTypeList = l_strHandlingPossibleExpirationDateType;

        //レスポンス.有効期限開始日＝get出来るまで注文開始日の戻り値
        l_response.expirationStartDate = WEB3DateUtility.toDay(l_datOrderUntilDeadLineStartDay);

        //レスポンス．有効期限最終日　@＝　@get出来るまで注文最終日<取引最終日考慮>の返り値
        l_response.expirationEndDate = WEB3DateUtility.toDay(l_datOrderUntilDeadLineEndDayTradingEndDate);

        //レスポンス.有効期限内祝日一覧＝get注文期限内祝日一覧の戻り値
        l_response.holidayList = l_datExpirationDateHoliday;

        //レスポンス.発注条件区分一覧＝取扱可能発注条件取得の戻り値
        l_response.orderCondTypeList = l_strHandlingPossibleOrderCond;

        //レスポンス.Ｗ指値用執行条件一覧＝getＷ指値用執行条件一覧（)の戻り値
        l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeLists;

        //レスポンス.立会区分 = 注文単位.get立会区分()
        l_response.sessionType = l_orderUnitRow.getSessionType();

        //レスポンス.内約定数量＝注文単位.約定数量
        double l_dblExecutedQuantity = l_orderUnitRow.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0D;
        }
        l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);

        //レスポンス.取引区分＝（以下のとおり）
        //注文単位・注文種別="605"（OP新規買建注文） => "3"
        //注文単位・注文種別="606"（OP新規売建注文） => "4"
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderUnitRow.getOrderType()))
        {
            l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_LONG_CONTRACT;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderUnitRow.getOrderType()))
        {
            l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_SHORT_CONTRACT;
        }

        //レスポンス.取引市場＝注文単位.市場コード(SONAR)
        l_response.marketCode = l_orderUnitRow.getSonarMarketCode();

        //レスポンス.指数種別＝先物OP銘柄.原資産銘柄コード
        l_response.targetProductCode = l_productImpl.getUnderlyingProductCode();

        //レスポンス.限月＝先物OP銘柄.限月
        l_response.delivaryMonth = l_productImpl.getMonthOfDelivery();

        //レスポンス.オプション商品区分＝(以下のとおり)
        //先物OP銘柄.先物オプション商品="2"（コールオプション） => "C"
        //先物OP銘柄.先物オプション商品="3"（プットオプション） => "P"
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_productImpl.getDerivativeType()))
        {
            l_response.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
        }
        else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_productImpl.getDerivativeType()))
        {
            l_response.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
        }

        //レスポンス.行使価格＝先物OP銘柄.行使価格
        double l_dblStrikePrice = l_productImpl.getStrikePrice();
        if (Double.isNaN(l_dblStrikePrice))
        {
            l_dblStrikePrice = 0D;
        }
        l_response.strikePrice = WEB3StringTypeUtility.formatNumber(l_dblStrikePrice);

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
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblGetChange);

        //レスポンス.取引時間(時価発表時間)＝getCurrentPriceTimeの戻り値
        l_response.currentPriceTime = l_currentPriceTime;

        //レスポン.注文数量＝注文単位.注文数量
        double l_dblQuantity = l_orderUnitRow.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }
        l_response.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);

        //レスポンス.注文単価区分＝（注文単位.isMarketOrder() == true）の場合 "成行"、以外 "指値"
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }

        //レスポンス.注文単価＝注文単位.指値 != 0の場合、注文単位.指値
        if (l_orderUnitRow.getLimitPrice() == 0)
        {
            l_response.limitPrice = null;
        }
        else
        {
            l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
        }

        //執行条件＝先物OPデータアダプタ.get執行条件（PR層）(注文単位.執行条件)
        l_response.execCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
            l_orderUnitRow.getExecutionConditionType());

        //レスポンス.注文期限区分＝先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値
        String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);
        l_response.expirationDateType = l_strExpirationDateType;

        //レスポンス.注文有効期限＝先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値
        //が"出来るまで注文"の場合のみ、注文単位.注文失効日をセット。以外の場合、nullをセット。
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
        {
            l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());
        }
        else
        {
            l_response.expirationDate = null;
        }

        //レスポンス.発注条件区分＝注文単位.発注条件
        l_response.orderCondType = l_orderUnitRow.getOrderConditionType();

        //＜注文単位.発注条件＝１（逆指値）の場合＞
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            //レスポンス.逆指値用プレミアム/原資産価格＝注文単位.逆指値基準値タイプ
            l_response.stopPremium_underlyingAssets = l_orderUnitRow.getStopPriceType();

            //レスポンス.逆指値用発注条件単価＝注文単位.逆指値基準値
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());

            //レスポンス.逆指値用発注条件演算子＝注文単位.発注条件演算子
            l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
        }
        //＜注文単位.発注条件＝２（W指値）の場合＞
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            //レスポンス.W指値用プレミアム/原資産価格＝注文単位.逆指値基準値タイプ
            l_response.wlimitPremium_underlyingAssets = l_orderUnitRow.getStopPriceType();

            //レスポンス.W指値用発注条件単価＝注文単位.逆指値基準値
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());

            //レスポンス.W指値用発注条件演算子＝注文単位.発注条件演算子
            l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();

            //レスポンス.W指値用注文単価区分＝if 注文単位.(W指値)訂正指値＝0 then "0"(成行) else "1"(指値)
            //レスポンス.W指値用注文単価＝注文単位.(W指値)訂正指値
            if (l_orderUnitRow.getWLimitPrice() == 0)
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.wLimitPrice = null;
            }
            else
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
            }

            //レスポンス.W指値用執行条件＝先物OPデータアダプタ.get執行条件（PR層）(注文単位.（W指値)執行条件)
            l_response.wlimitExecCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
                l_orderUnitRow.getWLimitExecCondType());
        }

        //レスポンス.W指値用有効状態区分＝先物OPデータアダプタ.getＷ指値用有効状態区分(）の戻り値
        l_response.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;

        //レスポンス.W指値用切替前注文単価＝先物OPデータアダプタ.getＷ指値用切替前注文単価()の戻り値
        l_response.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;

        //レスポンス.W指値用切替前執行条件＝先物OPデータアダプタ.getＷ指値用切替前執行条件()の戻り値
        l_response.wlimitBefChgExecCondType = l_strWLimitBefChgExecCondType;

        //レスポンス.元発注条件区分＝注文単位.元発注条件
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //レスポンス.元プレミアム／原資産価格＝注文単位.元逆指値基準値タイプ
        l_response.orgPremium_underlyingAssets = l_orderUnitRow.getOrgStopPriceType();

        //レスポンス.元発注条件単価＝注文単位.元逆指値基準値
        if (!l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                l_orderUnitRow.getOrgStopOrderPrice());
        }

        //レスポンス.元発注条件演算子＝注文単位.元発注条件演算子
        l_response.orgCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //レスポンス.元Ｗ指値用注文単価区分＝先物OPデータアダプタ.get元W指値用注文単価区分(注文単位)
        l_response.orgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        //レスポンス.元Ｗ指値用注文単価＝元Ｗ指値用注文単価区分が"指値"の
        //場合のみ、先物OPデータアダプタ.get元Ｗ指値用注文単価(注文単位)
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orgWLimitOrderPriceDiv))
        {
            l_response.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }

        //レスポンス.元Ｗ指値用執行条件＝先物OPデータアダプタ.get元W指値用執行条件(注文単位)
        l_response.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //レスポンス.概算受渡代金＝注文単位.概算受渡代金
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

        //レスポンス.取引終了警告文言＝get市場閉局警告指数の戻り値
        l_response.messageSuspension = l_strTradeCloseSuspension;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
