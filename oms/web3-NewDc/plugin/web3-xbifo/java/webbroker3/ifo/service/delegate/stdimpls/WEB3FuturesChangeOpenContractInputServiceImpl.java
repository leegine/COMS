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
filename	WEB3FuturesChangeOpenContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正新規建入力サービス実装クラス(WEB3FuturesChangeOpenContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 李強 (中訊) 新規作成
                 : 2006/07/31 肖志偉 (中訊) 仕様変更 モデルNo.492,534
                 : 2006/09/22 郭英  (中訊)   モデルNo.557
                 : 2006/10/13 唐性峰　@(中訊)   モデルNo.566
Revesion History : 2007/06/21 孫洪江  (中訊) 仕様変更モデルNo.705
Revesion History : 2007/07/24 孟亜南 (中訊) モデル777
Revesion History : 2007/11/20 周墨洋 (中訊) 仕様変更 モデル798
Revesion History : 2007/11/28 周墨洋 (中訊) 仕様変更 Javaソース（基本設計と合っていない実装）No.008
Revesion History : 2008/03/13 張騰宇(中訊) モデル834 835
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
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
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeInputResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractInputService;


/**
 * (先物訂正新規建入力サービスImpl)<BR>
 * 株価指数先物訂正新規建入力サービス実装クラス
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesChangeOpenContractInputServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesChangeOpenContractInputService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeOpenContractInputServiceImpl.class);

    /**
     * @@roseuid 40F7A2CF02EE
     */
    public WEB3FuturesChangeOpenContractInputServiceImpl()
    {

    }

    /**
     * 株価指数先物訂正新規建入力サービス処理を実施する。<BR> 
     * <BR>
     * this.create入力画面をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8ABD402D0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (!(l_request instanceof WEB3FuturesOpenMarginChangeInputRequest))
        {
            log.debug("パラメータのタイプが不正");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        WEB3FuturesOpenMarginChangeInputRequest l_openMarginChangeInputRequest =
            (WEB3FuturesOpenMarginChangeInputRequest)l_request;

        WEB3FuturesOpenMarginChangeInputResponse l_response = this.createInputScreen(l_openMarginChangeInputRequest);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get訂正対象注文単位)<BR>
     * get訂正対象注文単位 <BR>
     * <BR>
     * 訂正対象の注文単位を取得する。<BR> 
     * <BR>
     * ※処理の詳細はシーケンス図「（先物訂正新規建入力）入力画面表示データ取得」参照<BR>
     * @@param l_request - (株価指数先物訂正新規建入力画面リクエスト)<BR>
     * 株価指数先物訂正新規建入力画面リクエスト<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3FuturesOpenMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "getChangeOrderUnit(WEB3FuturesOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        //getOrderUnits(注文ID : long)
        //注文ID： リクエストデータ.ID
        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(Long.parseLong(l_request.id));
        OrderUnit l_orderUnit = null;

        if (l_orderUnits.length == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        else
        {
            l_orderUnit = l_orderUnits[0];
        }

        log.exiting(STR_METHOD_NAME);
        return (IfoOrderUnit)l_orderUnit;
    }
    
    /**
     * (validate注文訂正可能)<BR>
     * validate注文訂正可能 <BR>
     * <BR>
     * 訂正対象注文が、訂正可能な状態かどうかをチェックする。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（先物訂正新規建入力）入力画面表示データ取得」参照<BR>
     * @@param l_orderUnit - (IfoOrderUnit)<BR>
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
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (create入力画面)<BR>
     * 入力画面表示処理 <BR>
     * <BR>
     * シーケンス図「（先物訂正新規建入力）入力画面表示データ取得」参照<BR>
     * @@param l_request0 - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3FuturesOpenMarginChangeInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOpenMarginChangeInputResponse createInputScreen(
        WEB3FuturesOpenMarginChangeInputRequest l_request0) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createInputScreen(WEB3FuturesOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        try
        {
            // validate
            l_request0.validate();

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

            //get訂正対象注文単位(株価指数先物訂正新規建入力画面リクエスト)
            IfoOrderUnit l_orderUnit = getChangeOrderUnit(l_request0);
            
            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            // get補助口座( )
            WEB3GentradeSubAccount l_subAccount = null;
            l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

            // validate注文(補助口座, String)
            l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            // getInstance
            WEB3IfoOrderManagerReusableValidations l_orderManagerReusableValidations =
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

            //validate注文訂正可能(IfoOrderUnit)
            //IfoOrderUnit：　@取得したIfoOrderUnit
            validateOrderForChangeability(l_orderUnit);

            // validate市場ＩＤ(long)
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_orderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            // validate銘柄ＩＤ(long)
            WEB3IfoProductImpl l_productImpl =
                l_orderManagerReusableValidations.validateProductID(l_ifoOrderUnitRow.getProductId());

            // validate取引銘柄(先物OP銘柄, 市場, boolean, boolean)
            boolean l_blnIsBuyToOpenOrder = false;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuyToOpenOrder = true;
            }

            WEB3IfoTradedProductImpl l_tradedProduct =
                l_orderManagerReusableValidations.validateTradedProduct(
                    l_productImpl,
                    l_market,
                    l_blnIsBuyToOpenOrder,
                    true);

            //validate閉局後訂正取消受付可能(銘柄タイプ : ProductTypeEnum,
            //先物／オプション区分 : String, 部店 : 部店, 立会区分 : String, 発注日 : Date)
            Date l_datOrderBizDate =
                WEB3DateUtility.getDate(
                    l_ifoOrderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datOrderBizDate);

            // getQuote(TradedProduct, RealType)
            WEB3QuoteDataSupplierService  l_quoteSupplier =
                (WEB3QuoteDataSupplierService ) l_tradingModule.getQuoteDataSupplierService();

            WEB3GentradeMainAccount l_gentradeMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            WEB3IfoQuoteData l_ifoQuoteData = null;

            if (l_gentradeMainAccount.isRealCustomer())
            {
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote(l_tradedProduct,RealType.REAL);
            }
            else
            {
                //NotFoundException
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote(l_tradedProduct,RealType.DELAY);
            }

            // getCurrentPrice( )
            double l_dblCurrentPrice = l_ifoQuoteData.getCurrentPrice();


            // getCurrentPriceTime( )
            Timestamp l_tsCurrentPriceTime = l_ifoQuoteData.getCurrentPriceTime();

            // getChange( )
            double l_dblChange = l_ifoQuoteData.getChange();


            // 取扱可能注文条件
            String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();

            WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
                new WEB3GentradeHandlingOrderCond(
                    l_strInstitutionCode,
                    ProductTypeEnum.IFO,
                    WEB3FuturesOptionDivDef.FUTURES,
                    WEB3MarginTradingDivDef.DEFAULT);

            // 取扱可能注文単価区分取得(boolean,boolean)
            String[] l_strHandlingPossibleOrderPriceDiv = null;
            if(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderUnit.getOrderType()))
            {
                l_strHandlingPossibleOrderPriceDiv = l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(true,true);
            }
                if(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderUnit.getOrderType()))
            {
                l_strHandlingPossibleOrderPriceDiv = l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(true,false);
            }
            // 取扱可能執行条件取得( )
            String[] l_strHandlingPossibleExecCond = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();
            
            // 執行条件一覧取得()
            l_strHandlingPossibleExecCond = 
                l_orderMgr.getHandlingPossibleExecConds
                (l_strHandlingPossibleOrderPriceDiv,l_strHandlingPossibleExecCond);

            // W指値用の執行条件一覧取得()
            String[] l_strWLimitExecutionConditionTypeList = 
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(
                    l_strHandlingPossibleExecCond,
                    (IfoOrderUnit) l_orderUnit);            
            
            // 取扱可能注文期限区分取得( )
            String[] l_strHandlingPossibleExpirationDateType =
                l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();

            // 取扱可能発注条件取得( )
            String[] l_strHandlingPossibleOrderCond = null;
            l_strHandlingPossibleOrderCond = l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();

            //set取引最終日(取引最終日 : Date)
            //取引最終日をセットする。
            //［引数］
            //先物OP取引銘柄.getLastTradingDate()の戻り値
            l_gentradeHandlingOrderCond.setTradingEndDate(l_tradedProduct.getLastTradingDate());

            //is出来るまで注文単位（注文単位）
            boolean l_blnIsCarriedOrderUnit =
                l_orderMgr.isCarriedOrderUnit((IfoOrderUnit)l_orderUnit);

            // (*1)
            WEB3FuturesOpenMarginChangeInputResponse l_response = null;
            Date l_datOrderUntilDeadLineStartDay = null;
            Date l_datOrderUntilDeadLineEndDay = null;
            Date[] l_datExpirationDateHoliday = null;
            if (l_blnIsCarriedOrderUnit)
            {
                //get出来るまで注文最終日<取引最終日考慮>(原注文発注日 : Date)
                //売買最終日を考慮した出来るまで注文最終日を取得する。
                //[引数]
                //出来るまで注文開始日：　@OP注文マネージャ.get初回注文の注文単位(注文単位).発注日
                WEB3FuturesOrderManagerImpl l_futuresOrderManager =
                    (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
                IfoOrderUnit l_ifoOrderUnit =
                    l_futuresOrderManager.getFirstOrderUnit((IfoOrderUnit)l_orderUnit);
                IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
                String l_strBizDate = l_orderUnitRow.getBizDate();
                Date l_datFrom = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
                l_datOrderUntilDeadLineEndDay =
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datFrom);

                //get出来るまで注文開始日(出来るまで注文from日付 : Date, 出来るまで注文to日付 : Date)
                //売買最終日を考慮した出来るまで注文開始日を取得する。
                //[引数]
                //出来るまで注文from日付：　@null
                //出来るまで注文to日付：　@get出来るまで注文最終日<取引最終日考慮>()の戻り値
                l_datOrderUntilDeadLineStartDay = l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(null,l_datOrderUntilDeadLineEndDay);


                // get注文期限内祝日一覧( )
                l_datExpirationDateHoliday = l_gentradeHandlingOrderCond.getExpirationDateHoliday(l_datOrderUntilDeadLineStartDay);
            }

            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_gentradeMainAccount.getBranch();

            // get市場閉局警告指数(部店, String)
            String[] l_strTradeCloseSuspension =
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_branch,
                    WEB3FuturesOptionDivDef.FUTURES);

            // W指値の有効状態区分取得
            String l_strWLimitEnableStatusDiv = 
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit)l_orderUnit);
            
            // W指値の切替前注文単価取得
            String l_strWLimitBefSwitchPrice = 
                WEB3IfoDataAdapter.getWLimitBefSwitchPrice((IfoOrderUnit)l_orderUnit);
            
            // W指値の切替前執行条件取得
            String l_strWLimitBefSwitchExecCondType = 
                WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType((IfoOrderUnit)l_orderUnit);

            //is夕場まで注文(IfoOrderUnit)
            boolean l_blnIsEveningSessionOrder = l_orderMgr.isEveningSessionOrder((IfoOrderUnit)l_orderUnit);

            // createResponse( )
            l_response = (WEB3FuturesOpenMarginChangeInputResponse)l_request0.createResponse();

            // プロパティセット
            //レスポンス.注文単価区分一覧＝取扱可能注文単価区分取得の戻り値
            l_response.orderPriceDivList = l_strHandlingPossibleOrderPriceDiv;

            //レスポンス.執行条件一覧＝get執行条件一覧()の戻り値
            //(ただし、訂正注文が出来るまで注文、又は夕場まで注文(is夕場まで注文()の戻り値 == true)、
            //又は逆指値注文の場合、”１：無条件”をセット。W指値注文の場合、getＷ指値用執行条件一覧（)の戻り値をセット。）
            String[] l_strExecutionConditions =new String[1];
            l_strExecutionConditions[0]=WEB3ExecutionConditionDef.NO_CONDITION;

            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
               l_response.execCondList = l_strWLimitExecutionConditionTypeList;
            }
            else if (l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull()
                && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType())
                && !l_blnIsEveningSessionOrder)
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

            //レスポンス.有効期限最終日＝get出来るまで注文最終日<取引最終日考慮>の戻り値
            l_response.expirationEndDate = WEB3DateUtility.toDay(l_datOrderUntilDeadLineEndDay);

            //レスポンス.有効期限内祝日一覧＝get注文期限内祝日一覧の戻り値
            l_response.holidayList = l_datExpirationDateHoliday;

            //レスポンス.発注条件区分一覧＝取扱可能発注条件取得の戻り値
            l_response.orderCondTypeList = l_strHandlingPossibleOrderCond;

            //レスポンス.W指値用執行条件一覧＝getW指値用執行条件一覧()の戻り値
            l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeList;

            //レスポンス.立会区分＝注文単位.立会区分
            l_response.sessionType = l_ifoOrderUnitRow.getSessionType();

            //レスポンス.取引区分＝（以下のとおり）
            //注文単位・注文種別="601"（先物新規買建注文） => "3"
            //注文単位・注文種別="602"（先物新規売建注文） => "4"
            if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                l_response.tradingType = String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue());
            }
            else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                l_response.tradingType = String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue());
            }

            //レスポンス.取引市場＝注文単位.市場コード(SONAR)
            l_response.marketCode = l_ifoOrderUnitRow.getSonarMarketCode();

            //レスポンス.指数種別＝先物OP銘柄.原資産銘柄コード
            l_response.targetProductCode = l_productImpl.getUnderlyingProductCode();

            //レスポンス.限月＝先物OP銘柄.限月
            l_response.delivaryMonth = l_productImpl.getMonthOfDelivery();

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

            //レスポンス.取引時間(時価発表時間)＝getCurrentPriceTimeの戻り値
            l_response.currentPriceTime = l_tsCurrentPriceTime;

            //レスポン.注文数量＝注文単位.注文数量
            double l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }
            l_response.futOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);

            //レスポンス.内約定数量＝注文単位.約定数量
            double l_dblExecutedQuantity = l_ifoOrderUnitRow.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }

            l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);


            //レスポンス.注文単価区分＝(注文単位.isMarketOrder == true)の場合"成行"、以外"指値"
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
            
            //レスポンス.執行条件＝先物OPデータアダプタ.get執行条件(PR層)(注文単位.執行条件)の戻り値
            l_response.execCondType = 
                WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getExecutionConditionType());
            
            //レスポンス.注文期限区分＝先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);
            l_response.expirationDateType = l_strExpirationDateType;

            //レスポンス.注文有効期限＝先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値が
            //"出来るまで注文"の場合のみ、注文単位.注文失効日をセット。以外の場合、nullをセット。
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
            {
                l_response.expirationDate = WEB3DateUtility.toDay(l_ifoOrderUnitRow.getExpirationDate());
            }
            else
            {
                l_response.expirationDate = null;
            }

            //レスポンス.発注条件区分＝注文単位.発注条件
            l_response.orderCondType = l_ifoOrderUnitRow.getOrderConditionType();

            //＜注文単位.発注条件＝１（逆指値）の場合＞
            //レスポンス.逆指値用プレミアム/原資産価格＝注文単位.逆指値基準値タイプ
            //レスポンス.逆指値用発注条件単価＝注文単位.逆指値基準値
            //レスポンス.逆指値用発注条件演算子＝注文単位.発注条件演算子
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                double l_dblStopOrderPrice = l_ifoOrderUnitRow.getStopOrderPrice();
                if (Double.isNaN(l_dblStopOrderPrice))
                {
                    l_dblStopOrderPrice = 0D;
                }

                l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_dblStopOrderPrice);
                l_response.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                //＜注文単位.発注条件＝２（W指値）の場合＞
                //レスポンス.W指値用プレミアム/原資産価格＝注文単位.逆指値基準値タイプ
                //レスポンス.W指値用発注条件単価＝注文単位.逆指値基準値
                //レスポンス.W指値用発注条件演算子＝注文単位.発注条件演算子
                //レスポンス.W指値用注文単価区分＝if 注文単位.(W指値)訂正指値＝0 then "0"(成行) else "1"(指値)
                //レスポンス.W指値用注文単価＝注文単位.(W指値)訂正指値
                double l_dblStopOrderPrice = l_ifoOrderUnitRow.getStopOrderPrice();
                if (Double.isNaN(l_dblStopOrderPrice))
                {
                    l_dblStopOrderPrice = 0D;
                }
                l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_dblStopOrderPrice);
                l_response.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();

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
                
                //レスポンス.Ｗ指値用執行条件 = 先物OPデータアダプタ.get執行条件（PR層）(注文単位.（W指値）執行条件)
                l_response.wlimitExecCondType = 
                    WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getWLimitExecCondType());
            }

            //レスポンス.W指値用有効状態区分＝先物OPデータアダプタ.getＷ指値用有効状態区分()の戻り値
            l_response.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
            
            //レスポンス.W指値用切替前注文単価＝先物OPデータアダプタ.getＷ指値用切替前注文単価(注文単位)
            l_response.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
            
            //レスポンス.W指値用切替前執行条件＝先物OPデータアダプタ.getＷ指値用切替前執行条件(注文単位)
            l_response.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;
            
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
            
            //レスポンス.概算建代金＝注文単位.概算受渡代金
            double l_dblEstimatedPrice = l_ifoOrderUnitRow.getEstimatedPrice();
            if (Double.isNaN(l_dblEstimatedPrice))
            {
                l_dblEstimatedPrice = 0D;
            }
            l_response.estimatedContractPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimatedPrice);
            //レスポンス.取引終了警告文言＝get市場閉局警告指数の戻り値
            l_response.messageSuspension = l_strTradeCloseSuspension;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@
