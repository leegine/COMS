head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeOpenMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引訂正新規建入力サービスImpl(WEB3MarginChangeOpenMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 盧法@旭(中訊) 新規作成 
                   2006/11/24 周捷(中訊) モデルNo.1009
                   2007/01/11 趙林鵬 (中訊) モデル No.1083
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

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
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeInputRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeInputResponse;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginInputService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引訂正新規建入力サービスImpl）。<BR>
 * <BR>
 * 信用取引訂正新規建入力サービス実装クラス
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3MarginChangeOpenMarginInputServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginChangeOpenMarginInputService 
{
    
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginChangeOpenMarginInputServiceImpl.class);
        
    /**
     * (コンストラクタ)。
     * @@roseuid 414006710096
     */
    public WEB3MarginChangeOpenMarginInputServiceImpl() 
    {
    }
    
    /**
     * (execute)。<BR>
     * <BR>
     * 信用取引訂正新規建入力サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータを「信用取引訂正新規建入力リクエスト」型に変換し、get訂正<BR>
     * 新規建入力画面()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407CAAE0002B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String METHOD_NAME = "execute";
        if (l_request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3MarginChangeOpenMarginInputServiceImpl" + "." + METHOD_NAME);
        }
        if (l_request instanceof WEB3MarginOpenMarginChangeInputRequest)
        {
            WEB3MarginOpenMarginChangeInputRequest l_request0 = (WEB3MarginOpenMarginChangeInputRequest)l_request;
            return this.getOpenMarginChangeInputScreen(l_request0);
        }
        else
        {
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + METHOD_NAME);          
        }        
    }
    
    /**
     * (get訂正新規建入力画面)。<BR>
     * <BR>
     * 信用取引の訂正新規建入力画面表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引訂正新規建入力サービス）get訂正新規建入力画面１」<BR>
     * 「（信用取引訂正新規建入力サービス）get訂正新規建入力画面２」<BR>
     * 参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.margin.message.WEB3MarginOpenMarginChangeInputResponse
     * @@roseuid 41046DA50010
     */
    protected WEB3MarginOpenMarginChangeInputResponse getOpenMarginChangeInputScreen(WEB3MarginOpenMarginChangeInputRequest l_request) throws WEB3BaseException
    {
        final String METHOD_NAME = "getOpenMarginChangeInputScreen";
        log.entering(METHOD_NAME);
        if (l_request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3MarginChangeOpenMarginInputServiceImpl" + "." + METHOD_NAME);
        }
        //1.1validate
        l_request.validate();
        //1.2get補助口座()
        WEB3GentradeSubAccount l_subAcc = this.getSubAccount();
        //1.3get order
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);     
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tm.getOrderManager();
        
        EqTypeOrderUnit l_orderUnit = this.getChangeOrderUnit(l_request);
        
        //1.6reset市場コード(市場コード : String)
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        long l_lngMarketId = l_orderUnitRow.getMarketId();
        Market l_market = null;
        try 
        {
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(METHOD_NAME,l_ex);
            throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + METHOD_NAME);    
        }
        String l_strMarketCode = l_market.getMarketCode();
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
        this.validateOrderForChangeability(l_orderUnit);
        
        String l_strRepaymentType = l_orderUnitRow.getRepaymentType();
        //1.7validate信用注文
        l_orderManager.validateMarginOrder((WEB3GentradeSubAccount) l_subAcc,l_strRepaymentType);
        //1.8 getTaxType
        TaxTypeEnum  l_taxType = l_orderUnitRow.getTaxType();
        //1.9getDeliveryDate
        Timestamp l_deliveryDate = l_orderUnitRow.getDeliveryDate();
        //1.10validate特定口座開設（信用）(補助口座 : 補助口座, 税区分 : TaxTypeEnum, 受渡日 : Date)
        l_orderManager.validateMarginSpecialAccountOpen(
            (WEB3GentradeSubAccount) l_subAcc,
            l_taxType,
            l_deliveryDate);            
        //1.11validate市場コード(市場コード : String, 証券会社コード : String)
        Institution l_institution = l_subAcc.getInstitution();
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        //1.12get product
        WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
        //1.13 get side 
        SideEnum l_side = l_orderUnit.getSide();
        //1.15validate取引銘柄
        Branch l_branch = l_subAcc.getMainAccount().getBranch();
        boolean l_blnShort = false;
        if (SideEnum.BUY.equals(l_side))
        {
            l_blnShort =false;    
        }
        else if (SideEnum.SELL.equals(l_side))
        {
            l_blnShort = true;
        }
        WEB3EquityTradedProduct l_tradeProduct = l_orderManager.validateTradedProductForMarginTrading(
            l_subAcc,
            l_product,
            (WEB3GentradeMarket) l_market,
            (WEB3GentradeBranch) l_branch,
            l_strRepaymentType,
            OrderCategEnum.OPEN_MARGIN,
            l_blnShort,
            false);
        //1.16validate取扱可能市場
        //弁済期限値：　@注文単位.弁済期限値
        int l_intRPN = l_orderUnitRow.getRepaymentNum();
        l_orderManager.validateHandlingMarket(
            (WEB3GentradeBranch) l_branch,
            l_tradeProduct,
            l_strMarketCode,
            l_strRepaymentType,
            l_intRPN);
            
        //1.17isインサイダー警告表示
        boolean l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(l_subAcc, l_orderUnitRow.getProductId());
        
        //1.18validate顧客銘柄別取引停止
        l_orderManager.validateAccountProductOrderStop(l_subAcc, l_orderUnitRow.getProductId(), l_orderUnitRow.getOrderType());
        
		//1.17取引時間管理.get発注日 ＞ 注文単位.発注日
		Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
		Date l_orderBizDate =
			WEB3DateUtility.getDate(
				l_orderUnitRow.getBizDate(),
				WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        
		if (WEB3DateUtility.compare(l_bizDate, l_orderBizDate) > 0)
		{
			//1.17.1validate閉局後訂正取消受付可能(銘柄タイプ : ProductTypeEnum)
			WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(ProductTypeEnum.EQUITY);
		}
        
        //get信用現引可能額
        double l_dblOpenMarginPossiblePrice = this.getMarginTradingPower(l_subAcc);
        
        log.debug("信用新規建可能額 = " + l_dblOpenMarginPossiblePrice);
        
        //1.19（信用取引訂正新規建入力サービス）get訂正新規建入力画面２（参照）
        //1.2get市場閉局警告市場
        String[] l_strCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            (WEB3GentradeBranch) l_branch,
            ProductTypeEnum.EQUITY,
            l_strRepaymentType);
        //1.3取扱可能注文条件
        WEB3GentradeHandlingOrderCond l_orderCond = new WEB3GentradeHandlingOrderCond(
            l_strInstitutionCode,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);

        //注文単価区分一覧
        String[] l_strOrderPriceDivList = l_orderManager.getOrderPriceDivs(l_branch, l_tradeProduct);
        
        //値段条件区分一覧
        String[] l_strPriceCondList = l_orderCond.getHandlingPriceCond(); 
        
        //1.4取扱可能執行条件取得( )
        String[] l_strExecConds = l_orderCond.getHandlingPossibleExecCond();

        // getＷ指値用執行条件一覧(String[], EqTypeOrderUnit)
        //Ｗ指値用の執行条件一覧を取得する。
        //[getＷ指値用執行条件一覧()に指定する引数]
        //執行条件一覧：　@取扱可能執行条件取得()の戻り値 
        //注文単位：　@注文単位
        String[] l_strWLimitExecutionConditionTypeList =
            WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                l_strExecConds,
                l_orderUnit);

        //1.5is出来るまで注文取扱可能( )
        boolean l_blnIsOrderCond = l_orderCond.isOrderUntilDeadLinePossibleHandling();
        //1.6is出来るまで注文単位(注文単位)
        boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit);
        //1.7*1)分岐フロー
        //出来るまで注文取扱可能(取扱可能注文条件.is出来るまで注文取扱可能( ) == true)、
        //かつ 訂正対象注文が出来るまで注文である（is出来るまで注文単位( )==true）の場
        Date l_datStart = null;
        Date l_datEnd = null;
        Date[] l_datHoliday = null;
        Date l_datBizDate = null;
        if (l_blnIsOrderCond && l_blnIsCarriedOrderUnit )
        {
            l_datBizDate = this.getCarriedOrderFromDate(l_orderUnit);
			//1.7.2get出来るまで注文最終日(出来るまで注文from日付 : Date)
			l_datEnd = l_orderCond.getOrderUntilDeadLineEndDay(l_datBizDate);			
            //1.7.3get出来るまで注文開始日(void)
            l_datStart = l_orderCond.getOrderUntilDeadLineStartDay(null,l_datEnd);
            //1.7.4get注文期限内祝日一覧(void)
            l_datHoliday = l_orderCond.getExpirationDateHoliday(l_datStart);
        }

        // 拡張プロダクトマネージャ取得
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            
        // 時価情報の取得
        WEB3EquityProductQuote l_productQuote =
            l_productManager.getDisplayEquityProductQuote(l_tradeProduct, l_subAcc);
        
        // 時価区分
        String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
        // 時価
        double l_dblCurrentPrice = l_productQuote.getQuote();
        // 前日比
        double l_dblChange = l_productQuote.getComparedPreviousDay();
        // 時価発表時間
        Timestamp l_timeStamp = l_productQuote.getQuoteTime();
        
        //1.12 create response
        WEB3MarginOpenMarginChangeInputResponse l_response = (WEB3MarginOpenMarginChangeInputResponse) l_request.createResponse();
        //1.13 プラパテイセット
        //信用取引訂正新規建注文入力レスポンスに下記の通りプロパティをセットする。
        //注文単価区分一覧：拡張株式注文マネージャ.get注文単価区分一覧()の戻り値Listをセット
        l_response.orderPriceDivList = l_strOrderPriceDivList;
        
        //値段条件区分一覧
        //出来るまで注文の場合は
        EqtypeOrderUnitRow l_orderUnitRow1 = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        if (l_blnIsCarriedOrderUnit || !WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitRow1.getOrderConditionType()))
        {
            String[] l_strPriceCondListOfCarriedOrderUnit = { WEB3PriceConditionDef.DEFAULT }; 
            l_strPriceCondList = l_strPriceCondListOfCarriedOrderUnit;
        }
        l_response.priceCondList = l_strPriceCondList;
        
        //執行条件一覧：　@訂正可能な候補のみ(**1)をセット
        //(**1)　@訂正対象注文が出来るまで注文で又は、逆指値注文である場合、"無条件"のみをセット。
        if (l_blnIsCarriedOrderUnit ||
			WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            String[] l_strCond = { WEB3ExecutionConditionDef.NO_CONDITION };
            l_response.execCondList = l_strCond;    
        }
        //W指値注文場合
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.execCondList = l_strWLimitExecutionConditionTypeList;
        }
        //以外、取扱可能注文条件.取扱可能執行条件取得( )の戻り値配列をセット
        else
        {
           l_response.execCondList = l_strExecConds;   
        }
        
        //Ｗ指値用執行条件一覧：　@getＷ指値用執行条件一覧の戻り値配列をセット
        l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeList;

        if (l_blnIsOrderCond && l_blnIsCarriedOrderUnit)
        {
            //有効期限開始日：　@(**2)取扱可能注文条件.get出来るまで注文開始日( )の戻り値
            l_response.expirationStartDate = l_datStart;
            //有効期限最終日：　@(**2)取扱可能注文条件.get出来るまで注文最終日( )の戻り値
            l_response.expirationEndDate = l_datEnd;
            //有効期限内祝日一覧：　@(**2)取扱可能注文条件.get注文期限内祝日一覧( )の戻り値配列
            l_response.holidayList = l_datHoliday;
        }
        else
        {
            l_response.expirationStartDate = null;
            l_response.expirationEndDate = null;
            l_response.holidayList = null;
        }
        //取引終了警告市場コード一覧：　@取引時間管理.get市場閉局警告市場( )の戻り値配列
        l_response.messageSuspension = l_strCloseMarket;
        //isインサイダー警告表示
        l_response.insiderWarningFlag = l_boolIsInsider; 

        //板情報項目の設定仕様は以下の通り。
        //現在値：　@取得した株式銘柄時価情報.get現在値()の戻り値をセット
        l_response.boardCurrentPrice = l_productQuote.getBoardCurrentPrice();

        //現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻()の戻り値をセット
        l_response.boardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime();

        //現在値区分：　@取得した株式銘柄時価情報.get現在値区分()の戻り値をセット
        l_response.boardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();

        //現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比()の戻り値をセット
        l_response.boardChange = l_productQuote.getBoardChange();

        //出来高：　@取得した株式銘柄時価情報.get出来高()の戻り値をセット
        l_response.volume = l_productQuote.getVolume();

        //出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻()の戻り値をセット
        l_response.volumeTime = l_productQuote.getVolumeTime();

        //買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分()の戻り値をセット
        l_response.askPriceTitle = l_productQuote.getAskPriceTitle();

        //買気配値：　@取得した株式銘柄時価情報.get買気配値()の戻り値をセット
        l_response.askPrice = l_productQuote.getAskPrice();

        //買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻()の戻り値をセット
        l_response.askPriceTime = l_productQuote.getAskPriceTime();

        //売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分()の戻り値をセット
        l_response.bidPriceTitle = l_productQuote.getBidPriceTitle();

        //売気配値：　@取得した株式銘柄時価情報.get売気配値()の戻り値をセット
        l_response.bidPrice = l_productQuote.getBidPrice();

        //売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻()の戻り値をセット
        l_response.bidPriceTime = l_productQuote.getBidPriceTime();

        //基準値段：　@取得した株式銘柄時価情報.get基準値段()の戻り値をセット
        l_response.basePrice = l_productQuote.getBasePrice();

        //新規建可能額：　@calc信用新規建可能額()の戻り値
        l_response.marginTradingPower = WEB3StringTypeUtility.formatNumber(l_dblOpenMarginPossiblePrice);
        //※株式銘柄はvalidate銘柄コード（信用）( )の戻り値にて取得
        //銘柄コード：　@株式銘柄.銘柄コード
        l_response.productCode = l_product.getProductCode();
        //銘柄名：　@株式銘柄.銘柄名
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        //市場コード：　@市場.市場コード
        l_response.marketCode = l_strMarketCode;
        //※市場はvalidate市場コード( )の戻り値にて取得
        //口座区分：　@
        //（注文単位.getTaxType() == TaxTypeEnum.一般）の場合、”一般”
        //（注文単位.getTaxType() == TaxTypeEnum.特定，または　@TaxTypeEnum.特定且つ源泉徴収）の場合、”特定”
        if (TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            l_response.taxType = WEB3TaxTypeDef.NORMAL;           
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_taxType)||TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            l_response.taxType = WEB3TaxTypeDef.SPECIAL; 
        }
        //取引区分：　@
        //（注文単位.getOrderType() == OrderTypeEnum.新規買建注文）の場合、新規買建注文
        //（注文単位.getOrderType() == OrderTypeEnum.新規売建注文）の場合、新規売建注文
        OrderTypeEnum l_orderTypeEnum = l_orderUnit.getOrderType();
        if (OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN;
        }
        else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderTypeEnum))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN;
        }
        //弁済：
        //信用取引弁済.弁済区分：　@注文単位.弁済区分
        WEB3MarginRepaymentUnit l_repayment = new WEB3MarginRepaymentUnit();
        l_repayment.repaymentDiv = l_orderUnitRow1.getRepaymentType();
        //信用取引弁済.弁済期限値：　@注文単位.弁済期限値
        l_repayment.repaymentTimeLimit =  String.valueOf(l_orderUnitRow.getRepaymentNum());
        l_response.repayment = l_repayment;       
        //注文株数：　@注文単位.getQuantity( )の戻り値
        double l_dblQuantity = l_orderUnitRow1.getQuantity();
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
        //内出来株数：　@注文単位.getExecutedQuantity( )の戻り値
        if (!l_orderUnitRow1.getExecutedQuantityIsNull())
        {
			double l_dblExeQuantity = l_orderUnitRow1.getExecutedQuantity();
			l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(l_dblExeQuantity);
        
        }
        //注文単価区分：　@（注文単位.isMarketOrder() == true）の場合”成行”、以外”指値”。
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_response.limitPrice = null;
        }
        else 
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            // 注文単価：注文単位.getLimitPrice( )の戻り値
            double l_dblLimitPrice = l_orderUnitRow1.getLimitPrice();
            l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_dblLimitPrice);            
        }
        //値段条件：  注文単位.値段条件
		l_response.priceCondType = l_orderUnitRow1.getPriceConditionType();
        //執行条件：　@注文単位.執行条件
        if (l_orderUnitRow1.getExecutionConditionType() == null)
        {
            l_response.execCondType = null;
        }
        else
        {
            //拡張株式マネージャ.get執行条件(SONAR)の戻り値
            l_response.execCondType = l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow1.getExecutionConditionType());
        }
        //注文期限区分：（is出来るまで注文単位( ) == false）の場合”当日限り”、以外”出来るまで注文”。
        if (! l_blnIsCarriedOrderUnit)
        {
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_response.expirationDate = null;   
        }
        else
        {
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;   
            //注文有効期限：注文単位.注文失効日付
            l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitRow1.getExpirationDate());
        }
        //発注条件区分：注文単位.発注条件
        l_response.orderCondType = l_orderUnitRow1.getOrderConditionType();
        //＜注文単位.発注条件 == ”逆指値”の場合＞※以外の場合、下記項目にnullをセットする。
        // 逆指値用発注条件単価：　@注文単位.逆指値基準値
        // 逆指値用発注条件演算子：　@注文単位.発注条件演算子
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow1.getOrderConditionType()))
        {
            double l_dblStopOrderPrice = l_orderUnitRow1.getStopOrderPrice();
            if(Double.isNaN(l_dblStopOrderPrice))
            {
                l_dblStopOrderPrice = 0;
            }
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_dblStopOrderPrice); 
            l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator(); 
        }
        else 
        {
            l_response.stopOrderCondPrice = null; 
            l_response.stopOrderCondOperator = null;             
        }
        //＜注文単位.発注条件 == ”W指値”の場合＞※以外の場合、下記項目にnullをセットする。
        //W指値用発注条件単価：　@注文単位.逆指値基準値
        //W指値用発注条件演算子：注文単位.発注条件演算子
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow1.getOrderConditionType()))
        {
            double l_dblStopOrderPrice = l_orderUnitRow1.getStopOrderPrice();
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_dblStopOrderPrice); 
            l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
            //W指値用注文単価区分：（注文単位.（W指値）訂正指値 == 0）の場合”成行”、以外”指値”
            double l_dblWLimitPrice = l_orderUnitRow1.getWLimitPrice();
            if(l_dblWLimitPrice == 0)
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.wLimitPrice = null;    
            }
            else 
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                //W指値用注文単価：　@注文単位.（W指値）訂正指値
                l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_dblWLimitPrice);     
            }

            //Ｗ指値用執行条件：株式データアダプタ.get執行条件（SONAR）(注文単位.（W指値）執行条件)
            l_response.wlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow1.getWLimitExecCondType());

        }
        else 
        {
            l_response.wlimitOrderCondPrice = null; 
            l_response.wlimitOrderCondOperator = null; 
            l_response.wLimitOrderPriceDiv = null;
            l_response.wLimitPrice = null;   
            //Ｗ指値用執行条件: null
            l_response.wlimitExecCondType = null;
        }    

        //Ｗ指値用有効状態区分：株式データアダプタ.getＷ指値用有効状態区分(注文単位)
        l_response.wlimitEnableStatusDiv = WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //Ｗ指値用切替前注文単価：株式データアダプタ.getＷ指値用切替前注文単価(注文単位)
        l_response.wlimitBefChgLimitPrice = WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        //Ｗ指値用切替前執行条件：株式データアダプタ.getＷ指値用切替前執行条件(注文単位)
        l_response.wlimitBefChgExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //元発注条件区分：注文単位.元発注条件
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //元発注条件単価：注文単位.元逆指値基準値
        if (!l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }

        //元発注条件演算子：注文単位.元発注条件演算子
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        String l_strOrgWlimitOrderPriceDiv = WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        //元Ｗ指値用注文単価区分：株式データアダプタ.get元Ｗ指値用注文単価区分(注文単位)
        l_response.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
        {
            //元Ｗ指値用注文単価：株式データアダプタ.get元Ｗ指値用注文単価(注文単位)
            l_response.orgWlimitPrice = WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }

        //元Ｗ指値用執行条件：株式データアダプタ.get元Ｗ指値用執行条件(注文単位)
        l_response.orgWlimitExecCondType = WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //概算受渡代金：注文単位.概算受渡代金
        double l_dblEstimatePrice = l_orderUnitRow1.getEstimatedPrice();
        if (l_orderUnitRow1.getEstimatedPriceIsNull())
        {
            l_response.estimatedPrice = null;
        }
        else
        {
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimatePrice);
        }

        // 時価区分
        l_response.currentPriceDiv = l_strQuoteTypeDiv;
        //時価(現在値)：
        l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
        //前日比：
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
        //取引時間(時価発表時間)：
        l_response.currentPriceTime = l_timeStamp;       
        return l_response;
    }
    
    /**
     * (get訂正対象注文単位)<BR>
     * 訂正対象の注文単位オブジェクトを取得する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)で取得した注文単位オブジェクトのうち、<BR>
     * 最初の要素を返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     */
    protected EqTypeOrderUnit getChangeOrderUnit(
        WEB3MarginOpenMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3MarginOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id));
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
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
    protected void validateOrderForChangeability(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.validateOrderForChangeability(l_orderUnit.getOrder());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get新規建可能額)<BR>
     * 新規建可能額を取得する。<BR>
     * <BR>
     * 取引余力サービス.get信用新規建可能額(補助口座)をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarginTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblOpenMarginPossiblePrice =
            l_tradingPowerService.getMarginTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblOpenMarginPossiblePrice;
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
}
@
