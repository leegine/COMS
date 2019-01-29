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
filename	WEB3MarginCloseMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引返済入力サービスImpl(WEB3MarginCloseMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 呉艶飛 (中訊) 新規作成
                 : 2006/11/27 柴双紅(中訊) モデル1005
                 : 2007/01/11 趙林鵬 (中訊) モデル No.1083
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginContractUnitContractPriceComparator;
import webbroker3.equity.WEB3MarginContractUnitOpenDateComparator;
import webbroker3.equity.WEB3MarginContractUnitFirstOpenDateComparator;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginCloseMarginInputRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginInputResponse;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.WEB3MarginTempContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginInputService;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

/**
 * （信用取引返済入力サービスImpl）。<BR>
 * <BR>
 * 信用取引返済入力サービス実装クラス。
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3MarginCloseMarginInputServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginCloseMarginInputService
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginCloseMarginInputServiceImpl.class);

    /**
     * (execute)。<BR>
     * <BR>
     * 信用取引の返済入力画面表示サービスを実施する。<BR>
     * <BR>
     * リクエストデータを「信用取引返済注文入力リクエスト」型に変換し、<BR>
     * get返済入力画面()メソッドをコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4105D75B0341
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request == null)
        {
            log.error(STR_METHOD_NAME + "パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        if (l_request instanceof WEB3MarginCloseMarginInputRequest) //validate注文
        {
            l_response = getCloseMarginInputScreen((WEB3MarginCloseMarginInputRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get返済入力画面)。<BR>
     * <BR>
     * 信用取引の返済入力画面表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引返済入力サービス）get返済入力画面１」<BR>
     * 「（信用取引返済入力サービス）get返済入力画面２」<BR>
     * 「（信用取引返済入力サービス）get返済入力画面３」<BR>
     * 参照。<BR>
     *  ========================================================<BR>
     *   シーケンス図(「（信用取引返済入力サービス）get返済入力画面２」) : (25*)(*) 分岐フロー　@決済残高チェック<BR>   
     *   (*) 分岐フロー<BR>   
     *   決済可能残高チェック<BR>   
     *   注文株数が画面より入力された<BR>   
     *   （リクエストデータ.注文株数 != null）場合、決済可能残高チェックを行う。<BR>   
     *<BR>   
     *   －注文株数が決済可能残高より大きい<BR>   
     *   （注文株数（残数）（：BigDecimal） > 0）場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00657<BR>
     *   ==========================================================<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3MarginCloseMarginInputResponse
     * @@roseuid 4105D70303DE
     */
    protected WEB3MarginCloseMarginInputResponse getCloseMarginInputScreen(WEB3MarginCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCloseMarginInputScreen(WEB3MarginCloseMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginInputResponse l_response = null;
        try
        {
            //リクエストデータの整合性をチェックする。
            l_request.validate();
            //補助口座オブジェクトを取得する。
            WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();

            //建株オブジェクトを取得する。
            WEB3EquityContract l_contract = this.getContract(l_request);

            //市場コード：　@get建株().getMarketId()に該当する市場.市場コード
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(l_contract.getMarketId());

            //市場コードをThreadLocalにセットし直す。
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

            //信用取引注文時共通チェックを実施する。
            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
            l_orderManager.validateMarginOrder(l_subAccount, l_contractRow.getRepaymentType());

            //取引銘柄を取得する。
            WEB3EquityTradedProduct l_tradedProduct = null;
            try
            {
                l_tradedProduct = (WEB3EquityTradedProduct) l_contract.getTradedProduct();
            }
            catch (RuntimeSystemException l_rse)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "建株ID=[" + Long.toString(l_contract.getContractId()) + "]の建株に紐付く取引銘柄無し",
                    l_rse);
            }

            //受渡日を取得する。
            Date l_datDeliveryDate = l_tradedProduct.getDailyDeliveryDate();

            //特定口座開設チェックを実施する。
            l_orderManager.validateMarginSpecialAccountOpen(l_subAccount, l_contractRow.getTaxType(), l_datDeliveryDate);

            //市場オブジェクトを取得する。
            WEB3GentradeMarket l_gentradeMarket = (WEB3GentradeMarket) l_tradedProduct.getMarket();
            //市場チェックを実施する。
            WEB3GentradeMarket l_market1 = (WEB3GentradeMarket) l_orderManager.validateMarket(l_gentradeMarket.getMarketCode(), l_subAccount.getInstitution().getInstitutionCode());

            //銘柄オブジェクトを取得する。
            WEB3EquityProduct l_product = (WEB3EquityProduct) l_contract.getProduct();

            //売建かどうかを判別する。
            boolean l_isShort = l_contract.isShort();
            
            String l_strRepaymentType= l_contractRow.getRepaymentType();
            //銘柄コードの存在チェックを実施する。存在する場合は銘柄オブジェクトを返却する。
            WEB3EquityProduct l_equityProduct =
                (WEB3EquityProduct) l_orderManager.validateProductCode(l_product.getProductCode(), l_subAccount.getInstitution().getInstitutionCode(), l_contractRow.getRepaymentType());

            //取引銘柄のチェックを行う。
            WEB3EquityTradedProduct l_equityTradedProduct =
                l_orderManager.validateTradedProductForMarginTrading(
                    l_subAccount,
                    l_equityProduct,
                    l_market1,
                    l_subAccount.getWeb3GenBranch(),
                    l_strRepaymentType,
                    OrderCategEnum.CLOSE_MARGIN,
                    l_isShort);

            //取扱可能市場かをチェックする。
            l_orderManager.validateHandlingMarket(l_subAccount.getWeb3GenBranch(), l_equityTradedProduct, l_market.getMarketCode(), l_contractRow.getRepaymentType(), l_contractRow.getRepaymentNum());

            //インサイダーチェック
            boolean l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_product.getProductId());
            
			OrderTypeEnum l_orderTypeEnum = null;
            //validate顧客銘柄別取引停止
            if ( l_isShort == false )
            {
            	//買建返済注文をセット
				l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
            	
            }
            else
            {
            	//売建返済注文をセット
				l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
            }
                       
			l_orderManager.validateAccountProductOrderStop(
				l_subAccount, l_product.getProductId(), l_orderTypeEnum);
            
            //時価を取得する。
            double l_dblCurrentPrice = l_productManager.getCurrentPrice(l_tradedProduct);

            WEB3MarginContractUnit[] l_unitTemp =
                this.createMarginContractUnitList(l_request, l_dblCurrentPrice);
            
            this.sortMarginContractUnitList(l_unitTemp, l_request);

            List l_lstContractUnit = new ArrayList();
            //信用取引建株明細Temp[]から、信用取引建株明細[]を作成する。
            for (int i = 0; i < l_unitTemp.length; i++)
            {
                WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
                
                l_contractUnit.id = l_unitTemp[i].id;
                l_contractUnit.openDate = l_unitTemp[i].openDate;
                l_contractUnit.contractPrice = l_unitTemp[i].contractPrice;
                l_contractUnit.contractQuantity = l_unitTemp[i].contractQuantity;
                l_contractUnit.contractExecPrice = l_unitTemp[i].contractExecPrice;
                l_contractUnit.appraisalProfitLoss = l_unitTemp[i].appraisalProfitLoss;
                l_contractUnit.orderQuantity = l_unitTemp[i].orderQuantity;
                l_contractUnit.partContQuantity = l_unitTemp[i].partContQuantity;
                l_contractUnit.settlePriority = l_unitTemp[i].settlePriority;
                
                l_lstContractUnit.add(l_contractUnit);
            }
            WEB3MarginContractUnit[] l_unit =
                new WEB3MarginContractUnit[l_unitTemp.length];
            l_lstContractUnit.toArray(l_unit);
           
            //(*) 分岐フロー注文株数が画面より入力された（リクエストデータ.注文株数 != null）場合
            //注文株数を決済順位順に、各建株に割り当てる。
            BigDecimal l_bdQuantity = null;
            if (l_request.orderQuantity != null)
            {
                //引数設定仕様は、シーケンスのノートアンカー参照。
                l_bdQuantity = new BigDecimal(l_request.orderQuantity);
            }
            //建株明細[]（建株明細List.toArray()の戻り値）各要素毎のLOOP処理
            for (int i = 0; i < l_unit.length; i++)
            {

                //信用取引建株明細オブジェクトのプロパティに値をセットする。
                //　@（リクエストデータ.注文株数 == null）の場合、建株明細[index].建株数
                if (l_request.orderQuantity == null)
                {
                    l_unit[i].orderQuantity = l_unit[i].contractQuantity;
                }
                //　@（リクエストデータ.注文株数 != null） && （注文株数（残数） == 0）の場合、0
                else if (l_request.orderQuantity != null && (l_bdQuantity.longValue() <= 0))
                {
                    l_unit[i].orderQuantity = "0";
                }
                //　@（リクエストデータ.注文株数 != null） && （注文株数（残数） >= 建株明細[index].建株数）の場合、建株明細[index].建株数
                else if (l_request.orderQuantity != null && l_bdQuantity.compareTo(new BigDecimal(l_unit[i].contractQuantity)) >= 0)
                {
                    l_unit[i].orderQuantity = l_unit[i].contractQuantity;
                }
                //　@（リクエストデータ.注文株数 != null） && （注文株数（残数） < 建株明細[index].建株数）の場合、注文株数（残数）
                else if (l_request.orderQuantity != null && l_bdQuantity.compareTo(new BigDecimal(l_unit[i].contractQuantity)) < 0)
                {
                    l_unit[i].orderQuantity = "" + l_bdQuantity.longValue();
                }
                l_unit[i].settlePriority = "" + (i + 1);
                
                if (l_request.orderQuantity != null)
                {
					l_bdQuantity = l_bdQuantity.subtract(new BigDecimal(l_unit[i].contractQuantity));
                }
            }

            //決済可能残高チェック
            //注文株数が画面より入力された（リクエストデータ.注文株数 != null）場合、決済可能残高チェックを行う。
            if (l_request.orderQuantity != null)
            {
                //注文株数が決済可能残高より大きい（注文株数（残数）（：BigDecimal） > 0）場合、例外をスローする。
                if (l_bdQuantity.doubleValue() > 0)
                {
                    log.error("注文数量が返済可能残高数量を超えのエラー。");

                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00299, this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            //引数は以下の通り設定する。
            String[] l_strCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_contractRow.getRepaymentType());

            //取扱可能注文条件
            WEB3GentradeHandlingOrderCond l_orderCond = new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_market.getMarketCode());

            //注文単価区分一覧
            String[] l_strOrderPriceDivList = l_orderManager.getOrderPriceDivs(l_subAccount.getWeb3GenBranch(), l_tradedProduct);
            
            //値段条件区分一覧
            String[] l_strPriceCondList = l_orderCond.getHandlingPriceCond(); 


            //取扱可能執行条件を取得する。
            String[] l_strExecCond = l_orderCond.getHandlingPossibleExecCond();

            //取扱可能注文期限区分を取得する。
            String[] l_strExpirationDateType = l_orderCond.getHandlingPossibleExpirationDateType();

            //取扱可能発注条件を取得する。
            String[] l_strPossibleOrderCond = l_orderCond.getHandlingPossibleOrderCond();

            //getＷ指値用執行条件一覧(String[], String[])
            //執行条件一覧：　@取扱可能注文条件.取扱可能執行条件取得( )の戻り値
            //発注条件一覧：　@取扱可能注文条件.取扱可能発注条件取得( )の戻り値
            String[] l_strWLimitExecutionConditionTypeList =
                WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(l_strExecCond, l_strPossibleOrderCond);

            //出来まで注文が取扱可能であるかを判定する。
            boolean l_blnHanding = l_orderCond.isOrderUntilDeadLinePossibleHandling();

            //(*1)分岐フロー
            //出来るまで注文取扱可能の場合のみ、以下の処理を実施する。
            //(取扱可能注文条件.is出来るまで注文取扱可能( ) == true)

            //注文開始日
            Date l_datStartDay = null;

            //注文最終日
            Date l_datEndDay = null;

            //注文期限内の祝日一覧
            Date[] l_datDateHolidays = null;

            if (l_blnHanding)
            {
                //出来るまで注文開始日を取得する。
                l_datStartDay = l_orderCond.getOrderUntilDeadLineStartDay();
                //出来るまで注文最終日を取得する。
                l_datEndDay = l_orderCond.getOrderUntilDeadLineEndDay();
                //出来るまで注文期限内の祝日一覧を取得する。
                l_datDateHolidays = l_orderCond.getExpirationDateHoliday();
            }

            //時価情報を取得する  
            WEB3EquityProductQuote l_productQuote = l_productManager.getDisplayEquityProductQuote(l_tradedProduct, l_subAccount);

            // 時価区分を取得する。
            String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
            
            //時価（現在値）を取得する。
            double l_dblCurrentPrice1 = l_productQuote.getQuote();
            
            //前日比を取得する。
            double l_dblChange = l_productQuote.getComparedPreviousDay();

            //取引時間（時価発表時間）を取得する。
            Timestamp l_timCurrentPriceTime = l_productQuote.getQuoteTime();

            //レスポンスを作成する。
            l_response = (WEB3MarginCloseMarginInputResponse)l_request.createResponse();
            
			//注文単価区分一覧
			l_response.orderPriceDivList = l_strOrderPriceDivList;
			
			//値段条件区分一覧
			l_response.priceCondList = l_strPriceCondList;     

            //信用取引返済注文入力レスポンスに下記の通りプロパティをセットする。
            //執行条件一覧：　@取扱可能注文条件.取扱可能執行条件取得( )の戻り値配列
            l_response.execCondList = l_strExecCond;

            //Ｗ指値用執行条件一覧:株式データアダプタ.getＷ指値用執行条件一覧
            l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeList;

            //有効期限開始日：　@(**1)取扱可能注文条件.get出来るまで注文開始日( )の戻り値
            l_response.expirationStartDate = WEB3DateUtility.toDay(l_datStartDay);

            //有効期限最終日：　@(**1)取扱可能注文条件.get出来るまで注文最終日( )の戻り値
            l_response.expirationEndDate = WEB3DateUtility.toDay(l_datEndDay);

            //有効期限内祝日一覧：　@(**1)取扱可能注文条件.get注文期限内祝日一覧( )の戻り値配列
            l_response.holidayList = l_datDateHolidays;

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

            //銘柄コード：　@株式銘柄.銘柄コード
            l_response.productCode = l_equityProduct.getProductCode();

            //銘柄名：　@株式銘柄.銘柄名　@
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_equityProduct.getDataSourceObject();
            l_response.productName = l_productRow.getStandardName();

            //市場コード：　@市場.市場コード
            l_response.marketCode = l_market1.getMarketCode();

            //口座区分：
            String l_strTaxType = null;
            //（建株.税区分 == TaxTypeEnum.一般）の場合、”一般”
            //（建株.税区分 == TaxTypeEnum.特定，または　@TaxTypeEnum.特定且つ源泉徴収）の場合、”特定”
            if (TaxTypeEnum.NORMAL.equals(l_contractRow.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_contractRow.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_contractRow.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeDef.SPECIAL;
            }
            l_response.taxType = l_strTaxType;

            //取引区分：
            //　@（建株.isLong() == true）の場合、買建返済注文（売返済）
            //（建株.isLong() == false）の場合、売建返済注文（買返済）
            String l_strTradeType = null;
            if (l_contract.isLong())
            {
                l_strTradeType = WEB3MarginTradeTypeDef.CLOSE_SELL_MARGIN;
            }
            else
            {
                l_strTradeType = WEB3MarginTradeTypeDef.CLOSE_BUY_MARGIN;
            }
            l_response.tradingType = l_strTradeType;

            WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
            l_repaymentUnit.repaymentDiv = l_strRepaymentType;
            l_repaymentUnit.repaymentTimeLimit =  "" + l_contractRow.getRepaymentNum();
            //信用取引弁済.弁済区分：　@建株.弁済区分            
            //信用取引弁済.弁済期限値：　@建株.弁済期限値
            l_response.repayment = l_repaymentUnit;

            //注文期限区分一覧：　@取扱可能注文期限区分取得( )の戻り値
            l_response.expirationDateTypeList = l_strExpirationDateType;

            //発注条件区分一覧：　@取扱可能発注条件取得( )の戻り値
            l_response.orderCondTypeList = l_strPossibleOrderCond;

            // 時価区分
            l_response.currentPriceDiv = l_strQuoteTypeDiv;
            
            //時価(現在値)：
            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice1);

            //前日比：
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

            //取引時間(時価発表時間)：
            l_response.currentPriceTime = l_timCurrentPriceTime;

            //建株明細一覧：　@建株明細[]（建株明細List.toArray()の戻り値）
            l_response.contractUnits = l_unit;

        }
        catch (DataFindException l_dfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_dfe.getMessage(), l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get建株)<BR>
     * 建株を取得し返却する。<BR>
     * <BR>
     * 株式ポジションマネージャ.get建株()をコールし、<BR>
     * 戻り値を返却する。<BR>
     * <BR>
     * [get建株()に指定する引数]<BR>
     * 建株ID：　@リクエストデータ.ID[0]<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引返済注文入力リクエストオブジェクト。
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     */
    protected WEB3EquityContract getContract(
        WEB3MarginCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getContract(WEB3MarginCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3EquityContract l_contract = null;
        try
        {
            l_contract =
                (WEB3EquityContract)l_positionManager.getContract(
                    Long.parseLong(l_request.id[0]));
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }
    
    /**
     * (create建株明細一覧)<BR>
     * リクエストデータより信用取引建株明細の一覧を<BR>
     * 作成する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@パラメータ.リクエストデータ.IDの要素数分、<BR>
     * 　@以下の処理を実施する。<BR>
     * 　@２－１）　@株式ポジションマネージャ.get建株()にて<BR>
     * 　@　@建株を取得する。<BR>
     * <BR>
     * 　@　@[get建株()に指定する引数]<BR>
     * 　@　@　@建株ID：　@処理対象の要素<BR>
     * <BR>
     * 　@２－２）　@信用取引建株明細Tempインスタンスを生成する。<BR>
     * <BR>
     * 　@２－３）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@ID：　@建株.建株ID<BR>
     * 　@　@建日 = 建株.建日<BR>
     * 　@　@建単価 = 建株.建単価<BR>
     * 　@　@建株数 = 未決済株数(*1)<BR>
     * 　@　@建代金 = 建株.get建代金(未決済株数)<BR>
     * 　@　@評価損益 =<BR>
     * 　@　@　@建株.get評価損益（建株諸経費考慮）(パラメータ.時価, 未決済株数)<BR>
     * 　@　@注文株数 = 0　@※当メソッドの外でセット。<BR>
     * 　@　@内出来数量 = null<BR>
     * 　@　@当初建日 = 建株.当初建日<BR>
     * 　@　@<BR>
     * 　@　@(*1)建株.建株数 - ロック中数量(*2)<BR>
     * 　@　@(*2)建株.getLockedQuantity()にて取得。<BR>
     * <BR>
     * 　@２－４）　@ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * ３）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_request - (クエストデータ)<BR>
     * 信用取引返済注文入力リクエストオブジェクト。
     * @@param l_dblCurrentPrice - (時価)<BR>
     * 時価。
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        WEB3MarginCloseMarginInputRequest l_request,
        double l_dblCurrentPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(WEB3MarginCloseMarginInputRequest, double)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstContractUnit = new ArrayList();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        for (int i = 0;i < l_request.id.length;i++)
        {
            WEB3EquityContract l_contract = null;
            try
            {
                l_contract =
                    (WEB3EquityContract)l_positionManager.getContract(
                        Long.parseLong(l_request.id[i]));
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            WEB3MarginTempContractUnit l_contractUnit = new WEB3MarginTempContractUnit();
            EqtypeContractRow l_contractRow = (EqtypeContractRow)l_contract.getDataSourceObject();

            l_contractUnit.id = Long.toString(l_contract.getContractId());
            l_contractUnit.openDate = WEB3DateUtility.toDay(l_contract.getOpenDate());
            l_contractUnit.contractPrice =
                WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
            double l_dblContractQuantity =
                l_contract.getQuantity() - l_contract.getLockedQuantity();
            l_contractUnit.contractQuantity =
                WEB3StringTypeUtility.formatNumber(l_dblContractQuantity);
            l_contractUnit.contractExecPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_contract.getContractAmount(l_dblContractQuantity));
            double l_dblAppraisalProfitLoss =
                l_contract.getAppraisalProfitOrLossExpenses(
                    l_dblCurrentPrice,
                    l_dblContractQuantity);
            l_contractUnit.appraisalProfitLoss =
                WEB3StringTypeUtility.formatNumber(l_dblAppraisalProfitLoss);
            l_contractUnit.orderQuantity = "0";
            l_contractUnit.partContQuantity = null;
            l_contractUnit.firstOpenDate = WEB3DateUtility.toDay(l_contractRow.getFirstOpenDate());
            l_lstContractUnit.add(l_contractUnit);
        }
        
        WEB3MarginTempContractUnit[] l_contractUnits =
            new WEB3MarginTempContractUnit[l_lstContractUnit.size()];
        l_lstContractUnit.toArray(l_contractUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
    
    /**
     * (sort建株明細一覧)<BR>
     * 引数の建株明細一覧をソートする。<BR>
     * <BR>
     * 処理内容は、<BR>
     * シーケンス図<BR>
     * 「（信用取引返済入力サービス）get返済入力画面２」の<BR>
     * WEB3ArraysUtility.sort()のノートアンカーを参照。<BR>
     * @@param l_contractUnits - (建株明細一覧)<BR>
     * 信用取引建株明細の配列。（実際の型：信用取引建株明細Temp型）
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引返済注文入力リクエストオブジェクト。
     * @@throws WEB3BaseException
     */
    protected void sortMarginContractUnitList(
        WEB3MarginContractUnit[] l_contractUnits,
        WEB3MarginCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sortMarginContractUnitList(WEB3MarginContractUnit[], WEB3MarginCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        int l_intSortKeyCnt = l_request.sortKeys.length;
        int index = 0;
        List l_lstComparators = new ArrayList();
            
        for (int i = 0; i < l_intSortKeyCnt; i++)
        {
            WEB3MarginSortKey l_sortKey = l_request.sortKeys[index];
                
            if (WEB3EquityKeyItemDef.OPEN_DATE.equals(l_sortKey.keyItem))
            {
                l_lstComparators.add(
                    new WEB3MarginContractUnitOpenDateComparator(l_sortKey.ascDesc));
                l_lstComparators.add(
                    new WEB3MarginContractUnitFirstOpenDateComparator(WEB3AscDescDef.ASC));
                l_intSortKeyCnt++;
                i++;
            }
            else if (WEB3EquityKeyItemDef.CONTRACT_PRICE.equals(l_sortKey.keyItem))
            {
                l_lstComparators.add(
                    new WEB3MarginContractUnitContractPriceComparator(l_sortKey.ascDesc));
            }
            index++;
        }
        Comparator[] l_comparators = new Comparator[l_intSortKeyCnt];
        l_lstComparators.toArray(l_comparators);
        
        WEB3ArraysUtility.sort(l_contractUnits, l_comparators);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
