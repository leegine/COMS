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
filename	WEB3EquityChangeOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文訂正入力サービスImpl(WEB3EquityChangeOrderInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/13 李綱 (中訊) 新規作成
                   2006/07/04 周捷 (中訊) 仕様変更管理No.937
                   2006/08/29 柴雙紅 (中訊) 仕様変更モデル970
                   2006/11/06 唐性峰 (中訊) 仕様変更モデル1002
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
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
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.message.WEB3EquityChangeInputRequest;
import webbroker3.equity.message.WEB3EquityChangeInputResponse;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderInputService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （現物株式注文訂正入力サービスImpl）。<BR>
 * <BR>
 * 現物株式注文訂正入力サービス実装クラス
 * @@version 1.0
 */
public class WEB3EquityChangeOrderInputServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityChangeOrderInputService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderInputServiceImpl.class);

    /**
     * @@roseuid 40A076E601E7
     */
    public WEB3EquityChangeOrderInputServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * 現物株式注文訂正入力サービスを実施する。<BR>
     * <BR>
     * this.get訂正入力画面()をコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response =
            this.getChangeInputScreen(
                (WEB3EquityChangeInputRequest)l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get訂正入力画面)。<BR>
     * 現物株式注文訂正入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図「（現物株式注文訂正入力）get訂正入力画面」参照。<BR>
     * @@param l_chgInRequest - (リクエストデータ)<BR>
     * リクエストデータ。
     * @@return WEB3EquityChangeInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3EquityChangeInputResponse getChangeInputScreen(
        WEB3EquityChangeInputRequest l_chgInRequest)
        throws WEB3BaseException
    {
         
        final String STR_METHOD_NAME = "getChangeOrderInputScreen(WEB3EquityChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
            = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager
            = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //--------------------
        //リクエスト.validate
        //--------------------
        l_chgInRequest.validate();

        //--------------------
        //get訂正対象注文単位
        //--------------------
        EqTypeOrderUnit l_orderUnit = this.getChangeOrderUnit(l_chgInRequest);
        EqtypeOrderUnitRow l_orderUnitRow
            = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        //--------------------
        //reset市場コード
        //--------------------
        WEB3GentradeMarket l_market = null;
        try {
            l_market = (WEB3GentradeMarket) l_finObjectManager
                .getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "市場が見つかりません。市場ID->[" + l_orderUnitRow.getMarketId() + "]");
        }
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

        //--------------------
        //注文受付可能かをチェックする
        //--------------------
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //--------------------
        //注文チェックの生成
        //--------------------
        WEB3GentradeOrderValidator l_gentradeOrderValidator
            = (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();

        //--------------------
        //get補助口座
        //--------------------
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        log.debug("get補助口座（補助口座ID=[" + l_subAccount.getSubAccountId() + "]）");

        //--------------------
        //validate取引可能顧客
        //--------------------
        OrderValidationResult l_validationResult
            = l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if (l_validationResult.getProcessingResult().isFailedResult() )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //--------------------
        //get市場閉局警告市場
        //--------------------
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        String[] l_tradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_branch,
            ProductTypeEnum.EQUITY,
            WEB3MarginTradingDivDef.DEFAULT);

        //--------------------
        //get買付可能額
        //--------------------
        Double l_dblEquityTradingPower = this.getEquityTradingPower(l_orderUnit, l_subAccount);

        EqTypeProduct l_product = (EqTypeProduct)l_orderUnit.getProduct();
        String l_strProductCode = l_product.getProductCode();
        String l_strInstitutionCode = l_market.getInstitution().getInstitutionCode();
        
        //--------------------
        //validate取引銘柄
        //--------------------
        WEB3EquityTradedProduct l_tradedProduct
            = (WEB3EquityTradedProduct) l_orderManager.validateTradedProduct(
                l_product, l_market);
        
        //--------------------
        //validate取扱可能市場
        //--------------------
        l_orderManager.validateHandlingMarket(l_branch, l_tradedProduct);
        
        //--------------------
        //get注文単価区分
        //--------------------
        String[] l_orderPriceDivList = null;
        l_orderPriceDivList = l_orderManager.getOrderPriceDivs(l_branch, l_tradedProduct);

        //--------------------
        //取扱可能注文条件の生成
        //--------------------
        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond    = null;
        String l_strMarketCode = l_market.getMarketCode();

        l_gentradeHandingOrderCond = new WEB3GentradeHandlingOrderCond(
            l_strInstitutionCode,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);

        //--------------------
        //is出来るまで注文単位
        //--------------------
        boolean l_blnIsCarriedOrder
            = l_orderManager.isCarriedOrderUnit(l_orderUnit);

        //--------------------
        //is出来るまで注文取扱可能
        //--------------------
        boolean l_blnIsHandingCarriedOrderPossible = false;
        if (l_blnIsCarriedOrder)
        {
            l_blnIsHandingCarriedOrderPossible
                = l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleHandling();
        }

        //--------------------
        //分岐フロー：出来るまで注文取扱可能 かつ is出来るまで注文単位の場合
        //--------------------
        Date l_datOrderUntilDeadLineStartDate       = null;     //出来るまで注文開始日
        Date l_datOrderUntilDeadLineEndDate         = null;     //出来るまで注文終了日
        Date[] l_datOrderUntilDeadLineHolidayList   = null;     //注文期限内祝日一覧
        if (l_blnIsCarriedOrder && l_blnIsHandingCarriedOrderPossible)
        {
            // get出来るまで注文from日付
            Date l_datFirstOrderUnitBizDate =
                this.getCarriedOrderFromDate(l_orderUnit);
            // get出来るまで注文最終日
			l_datOrderUntilDeadLineEndDate = l_gentradeHandingOrderCond.
				getOrderUntilDeadLineEndDay(l_datFirstOrderUnitBizDate);
			// get出来るまで注文開始日
            l_datOrderUntilDeadLineStartDate = l_gentradeHandingOrderCond
                .getOrderUntilDeadLineStartDay(null, l_datOrderUntilDeadLineEndDate);
            // get注文期限内祝日一覧
            l_datOrderUntilDeadLineHolidayList = l_gentradeHandingOrderCond
                .getExpirationDateHoliday(l_datOrderUntilDeadLineStartDate);
        }

        //--------------------
        //validate注文訂正可能
        //--------------------
        this.validateOrderForChangeability(l_orderUnit);

        //--------------------
        //isインサイダー警告表示
        //--------------------
        long l_lngProductId = l_orderUnit.getProduct().getProductId();
        boolean l_blnViewInsiderWarningMessage
            = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_lngProductId);

        //--------------------
        //validate顧客銘柄別取引停止
        //--------------------
        l_orderManager.validateAccountProductOrderStop(
            l_subAccount, l_lngProductId, l_orderUnit.getOrderType());
        
		//--------------------
		//validate閉局後訂正取消受付可能
		//取引時間管理．get発注日 ＞ 注文単位．発注日の場合のみコールする。
		//--------------------    
		Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
		Date l_datOrderBizDate = 
			WEB3DateUtility.getDate(
				l_orderUnitRow.getBizDate(), 
				WEB3GentradeTimeDef.DATE_FORMAT_YMD);

		if (WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
		{
			WEB3GentradeTradingTimeManagement
				.validateTradeCloseChangeOrCancel(ProductTypeEnum.EQUITY);
		}

        //--------------------
        //取扱可能執行条件取得
        //--------------------
        String[] l_handingPossibleExecCond = null;
        l_handingPossibleExecCond = l_gentradeHandingOrderCond.getHandlingPossibleExecCond();
        
        //--------------------
        //取扱可能値段条件取得
        //--------------------
        String[] l_handingPossiblePriceCond = null;
        l_handingPossiblePriceCond = l_gentradeHandingOrderCond.getHandlingPriceCond();
        
        //getＷ指値用執行条件一覧(String[], EqTypeOrderUnit)
        //Ｗ指値用の執行条件一覧を取得する。  
        //[getＷ指値用執行条件一覧()に指定する引数]  
        //執行条件一覧：　@取扱可能執行条件取得()の戻り値  
        //注文単位：　@注文単位
        String[] l_strGetWLimitExecutionConditionTypeList =
            WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                l_handingPossibleExecCond,
                l_orderUnit);

        //--------------------
        //get概算簿価単価
        //--------------------
        String l_strEstimatedBookPrice =
            this.getEstimatedBookPrice(l_orderUnit, l_subAccount);
        
        //--------------------
        //時価情報取得
        //--------------------
        double l_dblCurrentPrice = 0;
        Timestamp l_tsCurrentPriceTime = null;
        double l_dblChange = 0;
        String l_strCurrentPriceDiv = null;
        
        String l_strBoardCurrentPrice = null;                      
        Timestamp l_tsBoardCurrentPriceTime = null;               
        String l_strBoardCurrentPriceDiv = null;                   
        String l_strBoardChange = null;                      
        String l_strVolume = null;                              
        Timestamp l_tsVolumeTime = null;                          
        String l_strAskPriceTitle = null;                          
        String l_strAskPrice = null;                            
        Timestamp l_tsAskPriceTime = null;                         
        String l_strBidPriceTitle = null;                    
        String l_strbBidPrice = null;        
        Timestamp l_tsBidPriceTime = null;            
        String l_strBasePrice = null;            
        
        //時価、現在値時刻、前日比を取得
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityProductQuote l_productQuote =
            l_productManager.getDisplayEquityProductQuote(
                l_tradedProduct,
                l_subAccount);
        l_dblCurrentPrice = l_productQuote.getQuote();
        l_tsCurrentPriceTime = l_productQuote.getQuoteTime();
        l_dblChange = l_productQuote.getComparedPreviousDay();
        l_strCurrentPriceDiv = l_productQuote.getQuoteTypeDiv();
        l_strBoardCurrentPrice = l_productQuote.getBoardCurrentPrice(); 
        l_tsBoardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime(); 
        l_strBoardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv(); 
        l_strBoardChange = l_productQuote.getBoardChange(); 
        l_strVolume = l_productQuote.getVolume();  
        l_tsVolumeTime = l_productQuote.getVolumeTime();  
        l_strAskPriceTitle = l_productQuote.getAskPriceTitle(); 
        l_strAskPrice = l_productQuote.getAskPrice(); 
        l_tsAskPriceTime = l_productQuote.getAskPriceTime(); 
        l_strBidPriceTitle = l_productQuote.getBidPriceTitle();
        l_strbBidPrice = l_productQuote.getBidPrice();  
        l_tsBidPriceTime = l_productQuote.getBidPriceTime(); 
        l_strBasePrice = l_productQuote.getBasePrice(); 
        
        //--------------------
        //createResponse
        //--------------------
        WEB3EquityChangeInputResponse l_response
            = (WEB3EquityChangeInputResponse) l_chgInRequest.createResponse();
        
        //--------------------
        //プロパティセット
        //--------------------
        
        //＜現物株式入力共通レスポンス＞
        
        l_response.orderPriceDivList = l_orderPriceDivList;
        if (!l_blnIsCarriedOrder &&
            WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.priceCondList = l_handingPossiblePriceCond;
        }
        else
        {
            l_response.priceCondList = new String[]{WEB3PriceConditionDef.DEFAULT};
        }

        //(*1)
        //訂正対象注文＝逆指値注文の場合、「1:条件なし」をセット。
        //訂正対象注文＝W指値注文の場合、getＷ指値用執行条件一覧（)の戻り値をセット。
        //訂正対象注文＝出来るまで注文(*2)の場合、拡張株式注文マネージャ.get執行条件
        //（SONAR）(注文単位.執行条件)をセット。（他の執行条件への訂正は不可。）
        //訂正対象注文≠出来るまで注文(*2)の場合、取扱可能注文条件.取扱可能執行条件取得( )の戻り値Listをセット。

        //(*2)
        //訂正対象注文＝出来るまで注文かどうかの判定方法@：
        //拡張株式注文マネージャ.is出来るまで注文単位(注文単位)＝trueの場合は、出来るまで注文。
        //falseの場合は、出来るまで注文ではない。

        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
        	l_orderUnitRow.getOrderConditionType()))
        {
            //執行条件一覧：　@訂正可能な候補のみ(*1)をセット。
			l_response.execCondList = 
				new String[]{
					WEB3ExecutionConditionDef.NO_CONDITION};
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
            l_orderUnitRow.getOrderConditionType()))
        {
            //執行条件一覧：　@訂正可能な候補のみ(*1)をセット。
            l_response.execCondList = l_strGetWLimitExecutionConditionTypeList;
        }
        else if (l_blnIsCarriedOrder)
        {
            //執行条件一覧：　@訂正可能な候補のみ(*1)をセット。
            String[] l_wkExecCond = {
                l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnitRow.getExecutionConditionType())};
            l_response.execCondList = l_wkExecCond;
        }
        else
        {
            //執行条件一覧：　@訂正可能な候補のみ(*1)をセット。
            l_response.execCondList = l_handingPossibleExecCond;
        }

        //W指値用執行条件一覧：　@getW指値用執行条件一覧()の戻り値をセット。
        l_response.wlimitExecCondList = l_strGetWLimitExecutionConditionTypeList;

        if (l_blnIsCarriedOrder && l_blnIsHandingCarriedOrderPossible)
        {

            l_response.expirationStartDate = l_datOrderUntilDeadLineStartDate;
            l_response.expirationEndDate   = l_datOrderUntilDeadLineEndDate;
            l_response.holidayList         = l_datOrderUntilDeadLineHolidayList;
        }
        else if (l_blnIsCarriedOrder && !l_blnIsHandingCarriedOrderPossible)
        { 
            l_response.expirationStartDate = l_orderUnitRow.getExpirationDate();
            l_response.expirationEndDate   = l_orderUnitRow.getExpirationDate();
            l_response.holidayList         = null;
        }
        else
        {
            l_response.expirationStartDate = null;
            l_response.expirationEndDate   = null;
            l_response.holidayList         = null;
        }
        
        l_response.messageSuspension   = l_tradeCloseMarkets;
        l_response.insiderWarningFlag  = l_blnViewInsiderWarningMessage;

        l_response.currentPriceDiv     = l_strCurrentPriceDiv;
        l_response.currentPrice        = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
        l_response.currentPriceTime    = l_tsCurrentPriceTime;
        
        //現在値：　@取得した株式銘柄時価情報.get現在値( )の戻り値をセット
        l_response.boardCurrentPrice = l_strBoardCurrentPrice;
        
        //現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻( )の戻り値をセット
        l_response.boardCurrentPriceTime = l_tsBoardCurrentPriceTime; 
        
        //現在値区分：　@取得した株式銘柄時価情報.get現在値区分( )の戻り値をセット           
        l_response.boardCurrentPriceDiv = l_strBoardCurrentPriceDiv; 
        
        //現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比( )の戻り値をセット
        l_response.boardChange = l_strBoardChange;
        
        //出来高：　@取得した株式銘柄時価情報.get出来高( )の戻り値をセット
        l_response.volume = l_strVolume;
        
        //出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻( )の戻り値をセット
        l_response.volumeTime = l_tsVolumeTime;
        
        //買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分( )の戻り値をセット
        l_response.askPriceTitle = l_strAskPriceTitle;
        
        //買気配値：　@取得した株式銘柄時価情報.get買気配値( )の戻り値をセット
        l_response.askPrice = l_strAskPrice; 
        
        //買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻( )の戻り値をセット
        l_response.askPriceTime = l_tsAskPriceTime;
        
        //売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分( )の戻り値をセット
        l_response.bidPriceTitle = l_strBidPriceTitle;
        
        //売気配値：　@取得した株式銘柄時価情報.get売気配値( )の戻り値をセット
        l_response.bidPrice = l_strbBidPrice;
        
        //売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻( )の戻り値をセット
        l_response.bidPriceTime = l_tsBidPriceTime;
        
        //基準値段：　@取得した株式銘柄時価情報.get基準値段( )の戻り値をセット
        l_response.basePrice = l_strBasePrice;

        //＜現物株式注文訂正入力レスポンス＞
        
        //買付可能額の設定
        if (l_dblEquityTradingPower != null)
        {
            l_response.tradingPower =
                WEB3StringTypeUtility.formatNumber(l_dblEquityTradingPower.doubleValue());
        }
        else
        {
            l_response.tradingPower = null;
        }
        l_response.productCode      = l_strProductCode;
        EqtypeProductRow l_productRow = (EqtypeProductRow)l_tradedProduct.getProduct().getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        l_response.marketCode       = l_strMarketCode;

        //税区分の設定
        l_response.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
        
        //売買区分の設定
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnit.getOrderType()))
        {
            l_response.dealingType = WEB3BuySellTypeDef.BUY;
        }
        else if  (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType()))
        {
            l_response.dealingType = WEB3BuySellTypeDef.SELL;
        }
        else{
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        l_response.orderQuantity    = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());

        //概算簿価単価の設定
        l_response.estimatedBookPrice = l_strEstimatedBookPrice;
        
        //注文単価区分の設定
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv    = WEB3OrderPriceDivDef.MARKET_PRICE; 
        }
        else
        {
            l_response.orderPriceDiv    = WEB3OrderPriceDivDef.LIMIT_PRICE; 
        }
        
        if (!l_orderUnit.isMarketOrder())
        {
            l_response.limitPrice   = WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }
        l_response.priceCondType    = l_orderUnitRow.getPriceConditionType();
        l_response.execCondType     = l_orderManager
            .getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());
        
        if (l_blnIsCarriedOrder)
        { 
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_response.expirationDate     = WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());
        }
        else
        {
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_response.expirationDate     = null;
        }
        
        String l_strOrderCondType = l_orderUnitRow.getOrderConditionType();
        l_response.orderCondType                    = l_strOrderCondType;
        
        //逆指値の場合のみセット
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            l_response.stopOrderCondPrice       = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            l_response.stopOrderCondOperator    = l_orderUnitRow.getOrderCondOperator();
        }
        
        //Ｗ指値の場合のみセット
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            l_response.wlimitOrderCondPrice     = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            l_response.wlimitOrderCondOperator  = l_orderUnitRow.getOrderCondOperator();

            //W指値用執行条件：　@注文単位.発注条件＝2（W指値）の場合のみ、拡張株式注文マネージャ.get執行条件
            //（SONAR）(注文単位.（W指値）執行条件）の戻り値。
            l_response.wlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(
                    l_orderUnitRow.getWLimitExecCondType());

            double l_dblWLimitPrice = l_orderUnitRow.getWLimitPrice();
            if (Double.isNaN(l_dblWLimitPrice) || l_dblWLimitPrice == 0)
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.wLimitPrice = null;
            }
            else
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_dblWLimitPrice);
            }
        }

        //W指値用有効状態区分：　@株式データアダプタ.getW指値用有効状態区分(注文単位)の戻り値。
        l_response.wlimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //W指値用切替前注文単価：　@株式データアダプタ.getW指値用切替前注文単価(注文単位)の戻り値。
        l_response.wlimitBefChgLimitPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        //W指値用切替前執行条件：　@株式データアダプタ.getW指値用切替前執行条件(注文単位)の戻り値。
        l_response.wlimitBefChgExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //元発注条件区分：　@注文単位.元発注条件
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //元発注条件単価：　@注文単位.元逆指値基準値
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = null;
        }
        else
        {
            l_response.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }

        //元発注条件演算子：　@注文単位.元発注条件演算子
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //元W指値用注文単価区分：　@株式データアダプタ.get元W指値用注文単価区分（注文単位）の戻り値。
        l_response.orgWlimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        //元W指値用注文単価：　@元W指値用注文単価区分が"指値"の場合のみ、
        //株式データアダプタ.get元W指値用注文単価（注文単位）の戻り値。
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orgWlimitOrderPriceDiv))
        {
            l_response.orgWlimitPrice =
                WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }

        //元W指値用執行条件：　@株式データアダプタ.get元W指値用執行条件（注文単位）の戻り値。
        l_response.orgWlimitExecCondType =
            WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //概算受渡代金：　@注文単位.概算受渡代金
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get訂正対象注文単位)<BR>
     * 訂正対象の注文単位オブジェクトを取得する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.getOrderUnits(リクエスト.ID)で取得した注文単位オブジェクトのうち、<BR>
     * 最初の要素を返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     */
    protected EqTypeOrderUnit getChangeOrderUnit(
        WEB3EquityChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3EquityChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }
    
    /**
     * (get買付可能額)<BR>
     * 買付可能額を取得する。<BR>
     * <BR>
     * １）　@引数の注文単位.getSide()≠"買"の場合は、nullを返却する。<BR>
     * 　@　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@取引余力サービス.get株式買付可能額(引数の補助口座)にdelegateする。<BR>
     * @@param l_orderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@return Double
     * @@throws WEB3BaseException
     */
    protected Double getEquityTradingPower(
        EqTypeOrderUnit l_orderUnit,
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEquityTradingPower(EqTypeOrderUnit, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (!SideEnum.BUY.equals(l_orderUnit.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            double l_dblEquityTradingPower =
                l_tradingPowerService.getEquityTradingPower(l_subAccount);
            
            log.exiting(STR_METHOD_NAME);
            return new Double(l_dblEquityTradingPower);
        }
    }
    
    /**
     * (get出来るまで注文from日付)<BR>
     * 注文有効期限取得に使用する、出来るまで注文from日付を返却する。<BR>
     * <BR>
     * １）　@拡張株式注文マネージャ.get初回注文の注文単位(引数の注文単位)をコールする。<BR>
     * <BR>
     * ２）　@取得した注文単位.発注日を返却する。<BR>
     * @@param l_orderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。
     * @@return Date
     * @@throws WEB3BaseException
     */
    protected Date getCarriedOrderFromDate(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCarriedOrderFromDate(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_firstOrderUnit =
            l_orderManager.getFirstOrderUnit(l_orderUnit);
        EqtypeOrderUnitRow l_firstOrderUnitRow =
            (EqtypeOrderUnitRow)l_firstOrderUnit.getDataSourceObject();
        Date l_datBizDate = null;
        try
        {
            l_datBizDate =
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().parse(
                    l_firstOrderUnitRow.getBizDate());
        }
        catch (ParseException l_pex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_pex.getMessage(),
                l_pex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_datBizDate;
    }
    
    /**
     * (validate注文訂正可能)<BR>
     * 訂正対象注文が、訂正可能な状態かどうかをチェックする。<BR>
     * <BR>
     * １）　@引数の注文単位.getOrder()により、注文オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.validate注文訂正可能状態(取得した注文オブジェクト)にdelegateする。<BR>
     * @@param l_orderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。
     * @@throws WEB3BaseException
     */
    protected void validateOrderForChangeability(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.validateOrderForChangeability(l_orderUnit.getOrder());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get概算簿価単価)<BR>
     * 概算簿価単価を返却する。<BR>
     * <BR>
     * １）　@引数の注文単位.getSide()≠"売"の場合は、nullを返却する。<BR>
     * 　@　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@株式計算サービス.calc概算簿価単価(引数の注文単位.銘柄ID, 引数の補助口座,<BR>
     * 　@　@　@引数の注文単位.税区分)をコールし、<BR>
     * 　@　@　@戻り値を返却する。<BR>
     * @@param l_orderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getEstimatedBookPrice(
        EqTypeOrderUnit l_orderUnit,
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimatedBookPrice(EqTypeOrderUnit, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (!SideEnum.SELL.equals(l_orderUnit.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblEstimatedBookPrice =
                l_bizLogicProvider.calcEstimatedBookPrice(
                    l_orderUnit.getProduct().getProductId(),
                    l_subAccount,
                    l_orderUnit.getTaxType());
            String l_strEstimatedBookPrice =
                WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
            
            log.exiting(STR_METHOD_NAME);
            return l_strEstimatedBookPrice;
        }
    }
}
@
