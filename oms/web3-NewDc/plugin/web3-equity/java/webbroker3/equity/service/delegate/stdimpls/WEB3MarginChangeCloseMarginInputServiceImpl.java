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
filename	WEB3MarginChangeCloseMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (信用取引訂正返済入力サービスImpl)
                 : 信用取引訂正返済入力サービス実装クラス
                 : (WEB3MarginChangeCloseMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/28 li-songfeng (中訊) 新規作成
Revesion History : 2006/11/27 趙林鵬 (中訊) モデル No.1021
Revesion History : 2006/12/14 唐性峰　@(中訊)　@モデルNo.1083
Revesion History : 2007/06/05 何文敏　@(中訊)　@モデルNo.1166
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

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
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputResponse;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginInputService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引訂正返済入力サービスImpl）。<BR>
 * <BR>
 * 信用取引訂正返済入力サービス実装クラス。
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginInputServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginChangeCloseMarginInputService 
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginInputServiceImpl.class);
 
    /**
     * (コンストラクタ)。<BR>
     * @@roseuid 41400671023B
     */
    public WEB3MarginChangeCloseMarginInputServiceImpl()
    {
    }
    
    /**
     * (execute)。<BR>
     * <BR>
     * 信用取引訂正返済入力サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータを「信用取引訂正返済入力リクエスト」型に変換し、<BR>
     * get訂正返済入力画面()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407CB28501E0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + "execute(WEB3GenRequest l_request)";
        WEB3GenResponse l_response = null;
        log.entering(STR_METHOD_NAME);
        if (l_request instanceof WEB3MarginCloseMarginChangeInputRequest)
        {
            l_response = this.getCloseMarginChangeInputScreen((WEB3MarginCloseMarginChangeInputRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正.");
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (get訂正返済入力画面)。<BR>
     * <BR>
     * 信用取引の訂正返済入力画面表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引訂正返済入力サービス）get訂正返済入力画面１」<BR>
     * 「（信用取引訂正返済入力サービス）get訂正返済入力画面２」<BR>
     * 参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginCloseMarginChangeInputResponse
     * @@roseuid 4104B3B00274
     */
    protected WEB3MarginCloseMarginChangeInputResponse getCloseMarginChangeInputScreen(
        WEB3MarginCloseMarginChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + "getCloseMarginChangeInputScreen(WEB3MarginCloseMarginChangeInputRequest)";
        try
        {
            l_request.validate();           
            SubAccount l_subAccount = this.getSubAccount();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = this.getChangeOrderUnit(l_request);
            
            //市場コードをThreadLocalにセットし直す。
            EqtypeOrderUnitRow l_orderUnitRow=(EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            long l_marketId = l_orderUnitRow.getMarketId();
            Market l_market =(Market)l_finApp.getFinObjectManager().getMarket(l_marketId);
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            
            //注文訂正可能な状態かをチェックする。   
            this.validateOrderForChangeability(l_orderUnit);

            //シーケンス図
            // 「（信用注文）validate信用注文」
            //  顧客別取引停止チェック
            //  信用実施会社チェック
            //  ・信用客チェック
            //  ・受付時間チェック
            //  ・システム取引停止チェック
            //信用取引注文時共通チェックを実施する。
            //引数は以下の通り指定する。
            //補助口座：　@（get補助口座()の戻り値）
            //弁済区分：　@（getOrderUnits()で取得した訂正対象注文単位.弁済区分）
            l_orderManager.validateMarginOrder((WEB3GentradeSubAccount)l_subAccount,l_orderUnitRow.getRepaymentType());
            TaxTypeEnum l_taxTypeEnum = l_orderUnit.getTaxType();
            Date l_DeliveryDate = l_orderUnit.getDeliveryDate();
            //特定口座開設チェックを実施する。
            l_orderManager.validateMarginSpecialAccountOpen((WEB3GentradeSubAccount)l_subAccount,l_taxTypeEnum,l_DeliveryDate);
            
            Product l_product = l_orderUnit.getProduct();
            SideEnum l_sideEnum = l_orderUnit.getSide();
            //validate取引銘柄（信用）(株式銘柄 : 株式銘柄, 市場 : 市場, 部店 : 部店, 弁済区分 : String, 注文カテゴリ : OrderCategEnum, is売建 : boolean)
            //取引可能銘柄チェック
            //引数設定仕様は、シーケンスのノートアンカー参照。
            //株式銘柄        ＝　@validate銘柄コード（信用）()の戻り値
            //市場      ＝　@validate市場コード()の戻り値
            //注文カテゴリ  ＝　@OrderCategEnum.返済注文（CLOSE_MARGIN）
            //is売建        ＝　@(*)
            //is売買訂正チェック　@＝　@false（＝売買停止チェックをしない）
            //(*)getSide()の戻り値により分岐する。
            //[getSide()の戻り値　@＝　@SideEnum.買い(BUY)の場合]
            //  is売建       ＝　@true
            //[getSide()の戻り値　@＝　@SideEnum.売り(SELL)の場合]
            //  is売建       ＝　@false
            boolean l_isShort;
            if (SideEnum.BUY.equals(l_sideEnum))
            {
                l_isShort = true;
            }
            else
            {
                l_isShort = false;
            }
            WEB3EquityTradedProduct l_validatedTradedProduct = 
                (WEB3EquityTradedProduct)l_orderManager.validateTradedProductForMarginTrading(
                    l_subAccount,
                    (WEB3EquityProduct)l_product,
                    (WEB3GentradeMarket)l_market,
                    (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
                    l_orderUnitRow.getRepaymentType(),
                    OrderCategEnum.CLOSE_MARGIN,l_isShort,false);
                        
            //validate取扱可能市場（信用）(部店 : 部店, 取引銘柄 : 取引銘柄, 市場コード : String, 弁済区分 :
            //（信用)取引可能市場チェック(現物)ＪＡＳＤＡＱ
            //会社部店が、信用取引の指定弁済区分・弁済期限の
            //取扱可能市場かをチェックする。
            //引数は以下の通り指定する。
            //部店：　@補助口座.get取引店()
            //取引銘柄：　@validate取引銘柄（信用）()の戻り値
            //市場コード：　@validate市場コード()の戻り値.getMarketCode()
            //弁済区分：　@注文単位.弁済区分
            //弁済期限値：　@注文単位.弁済期限値
            
            l_orderManager.validateHandlingMarket((WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
                l_validatedTradedProduct,
                l_market.getMarketCode(),
                l_orderUnitRow.getRepaymentType(),
                (double)l_orderUnitRow.getRepaymentNum());
            

            //isインサイダー警告表示
            boolean l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(
                l_subAccount, l_orderUnitRow.getProductId());
            
            //validate顧客銘柄別取引停止
            l_orderManager.validateAccountProductOrderStop(
                l_subAccount, l_orderUnitRow.getProductId(), l_orderUnitRow.getOrderType());
            
			//(*)分岐フロー
			//訂正元注文の発注日が現在日時より算出した発注日より前の場合のみ実施。
			//判定
			//取引時間管理.get発注日 ＞ 注文単位.発注日
			//validate閉局後訂正取消受付可能(銘柄タイプ : ProductTypeEnum)
			//※　@取引時間管理.get発注日 ＞ 注文単位.発注日 の場合のみコールする。
			//訂正受付停止時間でないかチェックする。
			//（市場閉局～出来終了までは訂正不可）
			Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
			Date l_orderBizDate = 
				WEB3DateUtility.getDate(
					l_orderUnitRow.getBizDate(), 
					WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                    
			if (WEB3DateUtility.compare(l_bizDate, l_orderBizDate) > 0)
			{
				WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(l_product.getProductType());   
			}
            //get市場閉局警告市場(部店 : 部店, 銘柄タイプ : ProductTypeEnum, 信用取引区分 : String)
            //引数は以下の通り設定する。
            //部店：　@補助口座.get取引店()
            //銘柄タイプ：　@ProductTypeEnum.”株式”
            //信用取引区分：　@注文単位.弁済区分
            WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
            String[] l_tradeCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_gentradeBranch,
                ProductTypeEnum.EQUITY,
                l_orderUnitRow.getRepaymentType());
            //取扱可能注文条件(証券会社コード : String, 銘柄タイプ : ProductTypeEnum, 
            //先物／オプション区分 : String, 信用取引区分 : String)
            //会社ごとの取扱可能注文条件を取得する。
            //信用取引区分：　@”DEFAULT”(固定)
            //引数は以下の通り設定する。
            //証券会社コード：　@補助口座.証券会社コード
            //銘柄タイプ：　@”株式”
            //先物／オプション区分：　@”DEFAULT”
            //信用取引区分：　@”DEFAULT”(固定)
            WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond= 
                new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_market.getMarketCode());
            //取扱可能執行条件を取得する。
            String[] l_exeCondiotions = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();

            //getＷ指値用執行条件一覧(String[], EqTypeOrderUnit)
            //Ｗ指値用の執行条件一覧を取得する。
            //[getＷ指値用執行条件一覧()に指定する引数]
            //執行条件一覧：　@取扱可能執行条件取得()の戻り値
            //注文単位：　@注文単位
            String[] l_strWLimitExecCondLists =
                WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                    l_exeCondiotions,
                    l_orderUnit);

            //出来まで注文が取扱可能であるかを判定する。
            //is出来るまで注文取扱可能( )
            boolean l_isOrderUntilDeadLinePossibleHandling = l_gentradeHandlingOrderCond.isOrderUntilDeadLinePossibleHandling();
            //is出来るまで注文単位(注文単位)
            boolean l_isCarriedOrderUnit = l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit);
            //分岐フロー
            //出来るまで注文取扱可能(取扱可能注文条件.is出来るまで注文取扱可能( ) == true)、
            //かつ 訂正対象注文が出来るまで注文である（is出来るまで注文単位( )==true）の場合
            //のみ、以下の処理を実施する。
            Date l_datStartDay = null;
            Date l_datEndDay = null;
            Date[] l_datExpirationDate = null;
            if (l_isOrderUntilDeadLinePossibleHandling && l_isCarriedOrderUnit)
            {
                Date l_datBizDate = this.getCarriedOrderFromDate(l_orderUnit);
				l_datEndDay = l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDay(l_datBizDate);
                l_datStartDay = l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(null,l_datEndDay);
                l_datExpirationDate = l_gentradeHandlingOrderCond.getExpirationDateHoliday(l_datStartDay);    
                                
            }

            // 拡張プロダクトマネージャ取得
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            
            // 時価情報の取得
            WEB3EquityProductQuote l_productQuote =
                l_productManager.getDisplayEquityProductQuote(
                    (EqTypeTradedProduct)l_validatedTradedProduct,
                    (WEB3GentradeSubAccount)l_subAccount);
        
            // 時価区分
            String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
            // 時価
            double l_dblCurrentPrice = l_productQuote.getQuote();
            // 前日比
            double l_dblChange = l_productQuote.getComparedPreviousDay();
            // 時価発表時間
            Timestamp l_tsCurrentPriceTime = l_productQuote.getQuoteTime();
            
            WEB3MarginContractUnit[] l_marginContractUnits =
                this.createContractUnitByOrder(l_orderUnit);
            WEB3MarginCloseMarginChangeInputResponse l_response = (WEB3MarginCloseMarginChangeInputResponse)l_request.createResponse();
            //信用取引訂正返済注文入力レスポンスに下記の通りプロパティをセットする。
            
            //注文単価区分一覧
            String[] l_orderPriceDivList = l_orderManager.getOrderPriceDivs(
                ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(),
                l_validatedTradedProduct);
            l_response.orderPriceDivList = l_orderPriceDivList;
            
            //値段条件区分一覧
            String[] l_strPriceCondList;
            if (l_isCarriedOrderUnit || !WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitRow.getOrderConditionType()))
            {
                String[] l_strPriceCondListOfCarriedOrderUnit = { WEB3PriceConditionDef.DEFAULT }; 
                l_strPriceCondList = l_strPriceCondListOfCarriedOrderUnit;
            }
            else
            {
                l_strPriceCondList = l_gentradeHandlingOrderCond.getHandlingPriceCond();
            }
            l_response.priceCondList = l_strPriceCondList;
            
            //執行条件一覧：　@訂正可能な候補のみ(**1)をセット
            //(**1)　@訂正対象注文が出来るまで注文又は、逆指値注文である場合、"無条件"のみをセット。
            //以外、取扱可能注文条件.取扱可能執行条件取得( )の戻り値配列をセット。
            if (l_isCarriedOrderUnit ||
				WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType())) 
            {
            	String[] l_strNoCondition = {WEB3ExecutionConditionDef.NO_CONDITION};
                l_response.execCondList = l_strNoCondition;
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
            {
                l_response.execCondList = l_strWLimitExecCondLists;
            }
            else
            {
                l_response.execCondList = l_exeCondiotions;
            }

            //W指値用執行条件一覧：　@getW指値用執行条件一覧( )の戻り値
            l_response.wlimitExecCondList = l_strWLimitExecCondLists;

            //有効期限開始日：　@(**2)取扱可能注文条件.get出来るまで注文開始日( )の戻り値
            //有効期限最終日：　@(**2)取扱可能注文条件.get出来るまで注文最終日( )の戻り値
            //有効期限内祝日一覧：　@(**2)取扱可能注文条件.get注文期限内祝日一覧( )の戻り値配列
            //(**2)　@出来るまで注文取扱可能(取扱可能注文条件.is出来るまで注文取扱可能( ) == true)、
            //        かつ　@訂正対象注文が出来るまで注文である（拡張株式注文マネージャ.is出来るまで注文単位( )==true）の場合のみ設定。
            //        以外、nullを設定。
            l_response.expirationStartDate = l_datStartDay;
            l_response.expirationEndDate = l_datEndDay;
            l_response.holidayList = l_datExpirationDate;
            //取引終了警告市場コード一覧：　@取引時間管理.get市場閉局警告市場( )の戻り値配列
            l_response.messageSuspension = l_tradeCloseMarket;
            
            //インサイダー警告表示フラグ
            l_response.insiderWarningFlag = l_boolIsInsider;
            
            //銘柄コード：　@株式銘柄.銘柄コード
            EqtypeProductRow l_validateProductRow =(EqtypeProductRow) 
                l_product.getDataSourceObject();
            l_response.productCode = l_validateProductRow.getProductCode();            
            //銘柄名：　@株式銘柄.銘柄名　@
            //      ※株式銘柄はvalidate銘柄コード（信用）( )の戻り値にて取得
            l_response.productName = l_validateProductRow.getStandardName();
            //市場コード：　@市場.市場コード
            //　@　@            ※市場はvalidate市場コード( )の戻り値にて取得
            l_response.marketCode = l_market.getMarketCode();
            //口座区分：　@
            //  （注文単位.getTaxType() == TaxTypeEnum.一般）の場合、”一般”
            //  （注文単位.getTaxType() == TaxTypeEnum.特定，または　@TaxTypeEnum.特定且つ源泉徴収）の場合、”特定”
            if (TaxTypeEnum.NORMAL.equals(l_orderUnit.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeDef.NORMAL;
            }
            if (TaxTypeEnum.SPECIAL.equals(l_orderUnit.getTaxType()) || 
                TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_orderUnit.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeDef.SPECIAL;
            }
            //取引区分：　@
            //（注文単位.getOrderType() == OrderTypeEnum.買建返済注文（売返済））の場合、買建返済注文（売返済）
            //  （注文単位.getOrderType() == OrderTypeEnum.売建返済注文（買返済））の場合、売建返済注文（買返済）
            if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
            {
                l_response.tradingType = String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue());
            }
            if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderUnit.getOrderType()))
            {
                l_response.tradingType = String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue());
            }
            //弁済：
            //  信用取引弁済.弁済区分：　@注文単位.弁済区分
            //  信用取引弁済.弁済期限値：　@注文単位.弁済期限値
            WEB3MarginRepaymentUnit l_repayment = new WEB3MarginRepaymentUnit();
            l_repayment.repaymentDiv = l_orderUnitRow.getRepaymentType();
            l_repayment.repaymentTimeLimit = String.valueOf(l_orderUnitRow.getRepaymentNum());
            
            l_response.repayment = l_repayment;
            
            //決済順序区分：　@注文単位.決済順序区分
            l_response.closingOrder = l_orderUnitRow.getClosingOrderType();
            //建株明細一覧：　@create建株明細ByOrder( )の戻り値
            l_response.contractUnits = l_marginContractUnits;
            //レスポンス.注文株数 には、訂正対象の注文単位.決済順序区分=="ランダム"以外の場合のみ、
            //注文単位.getQuantity()をセットする。（以外は、nullをセットする）
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_orderUnitRow.getClosingOrderType()))
            {
                l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());                
            }
            else
            {
                l_response.orderQuantity = null;
            }

            //内出来株数：　@注文単位.getExecutedQuantity( )の戻り値
            if(l_orderUnitRow.getExecutedQuantity() == 0)
            {
                l_response.partContQuantity = null;
            }
            else
            {
                l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getExecutedQuantity());
            }
            
			//注文単価区分：　@（注文単位.isMarketOrder() == true）の場合”成行”、以外”指値”。
			if (l_orderUnit.isMarketOrder()) 
			{
				l_response.orderPriceDiv =  WEB3OrderPriceDivDef.MARKET_PRICE;
				l_response.limitPrice = null;
			}
			else
			{
				l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
				//注文単価：　@注文単位.getLimitPrice( )の戻り値
				l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
			}    
            
            //値段条件：　@注文単位.値段条件
            l_response.priceCondType = l_orderUnitRow.getPriceConditionType();
            
            //執行条件：　@注文単位.執行条件
            l_response.execCondType = l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());
            //注文期限区分：　@（is出来るまで注文単位( ) == false）の場合”当日限り”、以外”出来るまで注文”
            if (!l_isCarriedOrderUnit)
            {
                l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            }
            else
            { 
                l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            }
            //注文有効期限：　@注文単位.注文失効日付
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_response.expirationDateType))
            {
                l_response.expirationDate = null;
            }
            else
            {
                l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());
            }
            //発注条件区分：　@注文単位.発注条件
            l_response.orderCondType = l_orderUnitRow.getOrderConditionType();
            //＜注文単位.発注条件 == ”逆指値”の場合＞※以外の場合、下記項目にnullをセットする。
            //          逆指値用発注条件単価：　@注文単位.逆指値基準値
            //          逆指値用発注条件演算子：　@注文単位.発注条件演算子
            if (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                l_orderUnitRow.getOrderConditionType()))
            {
                l_response.stopOrderCondPrice = null;
                l_response.stopOrderCondOperator = null;
            }
            else
            {
                l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
                l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();         
            }
            //＜注文単位.発注条件 == ”W指値”の場合＞※以外の場合、下記項目にnullをセットする。 
            //  W指値用発注条件単価：　@注文単位.逆指値基準値
            //  W指値用発注条件演算子：　@注文単位.発注条件演算子
            if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                l_orderUnitRow.getOrderConditionType()))
            {
                l_response.wlimitOrderCondPrice = null;
                l_response.wlimitOrderCondOperator = null;
                l_response.wLimitOrderPriceDiv = null;
                l_response.wLimitPrice = null;
                l_response.wlimitExecCondType = null;
            }
            else
            {
                l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
                l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
                //W指値用注文単価区分：　@（注文単位.（W指値）訂正指値 == 0）の場合”成行”、以外”指値”。
                //  W指値用注文単価：　@注文単位.（W指値）訂正指値
                if (l_orderUnitRow.getWLimitPrice() == 0)
                {
                    l_response.wLimitOrderPriceDiv = 
                        WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
                    l_response.wLimitPrice = null;
                }
                else
                {
                    l_response.wLimitOrderPriceDiv = 
                        WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
                    l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
                }

                //W指値用執行条件：　@拡張株式注文マネージャ.get執行条件（SONAR）(注文単位.（W指値）執行条件）の戻り値
                l_response.wlimitExecCondType =
                    l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getWLimitExecCondType());
            }

            //W指値用有効状態区分：　@株式データアダプタ.getW指値用有効状態区分(注文単位)の戻り値
            l_response.wlimitEnableStatusDiv =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

            //W指値用切替前注文単価：　@株式データアダプタ.getW指値用切替前注文単価(注文単位)の戻り値
            l_response.wlimitBefChgLimitPrice =
                WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

            //W指値用切替前執行条件：　@株式データアダプタ.getW指値用切替前執行条件(注文単位)の戻り値
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

            //元W指値用注文単価区分：　@株式データアダプタ.get元W指値用注文単価区分（注文単位）の戻り値
            String l_strOrgWlimitOrderPriceDiv =
                WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);
            l_response.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

            //元W指値用注文単価：　@元W指値用注文単価区分 ==
            //"指値"の場合のみ、株式データアダプタ.get元W指値用注文単価（注文単位）の戻り値をセット。
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
            {
                l_response.orgWlimitPrice =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
            }

            //元W指値用執行条件：　@株式データアダプタ.get元W指値用執行条件（注文単位）の戻り値
            l_response.orgWlimitExecCondType =
                WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

            //概算受渡代金：　@注文単位.概算受渡代金
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
            //強制決済理由
            l_response.forcedSettleReason = l_orderUnitRow.getForcedSettleReasonType();
            // 時価区分
            l_response.currentPriceDiv = l_strQuoteTypeDiv;
            //時価(現在値)：
            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
            //前日比：
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
            //取引時間(時価発表時間)：
            l_response.currentPriceTime = l_tsCurrentPriceTime;
            
            //板情報項目の設定仕様は、以下の通り。
            //　@現在値：　@取得した株式銘柄時価情報.get現在値()の戻り値をセット
            l_response.boardCurrentPrice = l_productQuote.getBoardCurrentPrice();

            //　@現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻()の戻り値をセット
            l_response.boardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime();

            //　@現在値区分：　@取得した株式銘柄時価情報.get現在値区分()の戻り値をセット
            l_response.boardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();

            //　@現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比()の戻り値をセット
            l_response.boardChange = l_productQuote.getBoardChange();

            //　@出来高：　@取得した株式銘柄時価情報.get出来高()の戻り値をセット
            l_response.volume = l_productQuote.getVolume();

            //　@出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻()の戻り値をセット
            l_response.volumeTime = l_productQuote.getVolumeTime();

            //　@買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分()の戻り値をセット
            l_response.askPriceTitle = l_productQuote.getAskPriceTitle();

            //　@買気配値：　@取得した株式銘柄時価情報.get買気配値()の戻り値をセット
            l_response.askPrice = l_productQuote.getAskPrice();

            //　@買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻()の戻り値をセット
            l_response.askPriceTime = l_productQuote.getAskPriceTime();

            //　@売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分()の戻り値をセット
            l_response.bidPriceTitle = l_productQuote.getBidPriceTitle();

            //　@売気配値：　@取得した株式銘柄時価情報.get売気配値()の戻り値をセット
            l_response.bidPrice = l_productQuote.getBidPrice();

            //　@売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻()の戻り値をセット
            l_response.bidPriceTime = l_productQuote.getBidPriceTime();

            //　@基準値段：　@取得した株式銘柄時価情報.get基準値段()の戻り値をセット
            l_response.basePrice = l_productQuote.getBasePrice();

            //(**1)　@訂正対象注文が出来るまで注文である場合、"無条件"のみをセット。以外、取扱可能注文条件.取扱可能執行条件取得( )の戻り値配列をセット。
            //(**2)　@出来るまで注文取扱可能(取扱可能注文条件.is出来るまで注文取扱可能( ) == true)、
            //        かつ　@訂正対象注文が出来るまで注文である（拡張株式注文マネージャ.is出来るまで注文単位( )==true）の場合のみ設定
            //        以外、nullを設定。
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
        WEB3MarginCloseMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3MarginCloseMarginChangeInputRequest)";
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
     * (create建株明細ByOrder)<BR>
     * 引数の注文単位に関連する信用取引建株明細の<BR>
     * 一覧を作成する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.create建株ByOrder(注文単位.注文単位ID)に<BR>
     * delegateする。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MarginContractUnit[] createContractUnitByOrder(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnitByOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3MarginContractUnit[] l_marginContractUnits =
            l_orderManager.createContractUnitByOrder(
                l_orderUnit.getOrderUnitId());
        
        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnits;
    }
}
@
