head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式買付サービスImpl(WEB3FeqBuyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
Revesion History : 2009/09/23 孟亞南(中訊) モデルNo.523,No.524,No.525,No.526,No.527
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqAmountCalcResult;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqNewOrderSpec;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUpdateInterceptor;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqProductQuote;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.message.WEB3FeqBuyCompleteRequest;
import webbroker3.feq.message.WEB3FeqBuyCompleteResponse;
import webbroker3.feq.message.WEB3FeqBuyConfirmRequest;
import webbroker3.feq.message.WEB3FeqBuyConfirmResponse;
import webbroker3.feq.message.WEB3FeqBuyInputRequest;
import webbroker3.feq.message.WEB3FeqBuyInputResponse;
import webbroker3.feq.message.WEB3FeqBuyProductSelectRequest;
import webbroker3.feq.message.WEB3FeqBuyProductSelectResponse;
import webbroker3.feq.message.WEB3FeqTradingPowerUnit;
import webbroker3.feq.service.delegate.WEB3FeqBuyService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式買付サービスImpl)<BR>
 * 外国株式買付サービス実装クラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqBuyServiceImpl 
    extends WEB3FeqClientRequestService implements WEB3FeqBuyService 
{
   /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBuyServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F8000F
     */
    public WEB3FeqBuyServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式買付サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *    get入力画面()<BR>
     *    validate注文()<BR>
     *    submit注文()<BR>
     *    get銘柄選択画面()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AF0B202F6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FeqBuyInputRequest)
        {
            //get入力画面
            l_response =
                this.getInputScreen(
                    (WEB3FeqBuyInputRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqBuyConfirmRequest)
        {
            //validate注文
            l_response =
                this.validateOrder(
                    (WEB3FeqBuyConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqBuyCompleteRequest)
        {
            //submit注文
            l_response =
                this.submitOrder(
                    (WEB3FeqBuyCompleteRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqBuyProductSelectRequest)
        {
            //get銘柄選択画面
            l_response =
                this.getProductSelectScreen(
                    (WEB3FeqBuyProductSelectRequest)l_request);
        }
        else
        {
            log.debug(
                "リクエストデータが"
                    + " WEB3FeqBuyInputRequest "
                    + "と WEB3FeqBuyConfirmRequest "
                    + "と WEB3FeqBuyCompleteRequest "
                    + "と WEB3FeqBuyProductSelectRequest 以外である, but is "
                    + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * シーケンス図「（外株買付）get入力画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return webbroker3.feq.message.WEB3FeqBuyInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AF4BA018F
     */
    protected WEB3FeqBuyInputResponse getInputScreen(WEB3FeqBuyInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqBuyInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1)get補助口座()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();
            
        //外国株式注文マネージャ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        //証券会社： （get補助口座()の戻り値）.getInstitutuin()の戻り値
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution) l_subAccount.getInstitution(); 
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        
        //発注日( )
        Date l_datOrderBizDate = null;
        //取扱可能注文条件
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond = null;

        //顧客
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            
        //1.4.3 (*3)取得した発注日
        Date l_datOrderResultBizDate = null;    

        //銘柄コード
        String l_strProductCode = null;
        //現地銘柄コード
        String l_strOffshoreProductCode = null;
        //表示銘柄名
        String l_strProductName = null;

        //市場コード
        String l_strMarketCode = null;
        //外株銘柄            
        WEB3FeqProduct l_feqProduct = null;
        //外国株式取引銘柄
        WEB3FeqTradedProduct l_feqTradedProduct = null;
        
        //1.2 (*1)リクエスト.銘柄コード != null の場合
        log.debug("リクエスト.銘柄コード = " + l_request.productCode);
        if (l_request.productCode != null)
        {
            //1.3.1 validate外株銘柄(証券会社, String)
            l_feqProduct =
                (WEB3FeqProduct) l_feqOrderManager.validateFeqProduct(
                    l_institution, 
                    l_request.productCode);
                    
            //銘柄コード
            l_strProductCode = l_feqProduct.getProductCode();  
            //現地銘柄コード
            l_strOffshoreProductCode = l_feqProduct.getOffshoreProductCode();
            //表示銘柄名：
            l_strProductName = l_feqProduct.getDisplayProductName();
            
            //1.2.2 get市場( )
            WEB3GentradeMarket l_market = l_feqProduct.getMarket();
            l_strMarketCode = l_market.getMarketCode();
            log.debug("市場コード = " + l_strMarketCode);
                        
            //1.2.3 validate市場(市場)
            l_feqOrderManager.validateMarket(l_market);
            
            //1.2.4 reset市場コード(市場コード : String)
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            //1.2.5 validate注文(補助口座)
            l_feqOrderManager.validateOrder(l_subAccount); 
            
            //1.2.6 validate取引銘柄(外国株式銘柄, 市場, boolean)
            l_feqTradedProduct =
                (WEB3FeqTradedProduct) l_feqOrderManager.validateTradedProduct(
                    l_feqProduct,
                    l_market,
                    true);
                    
            //1.2.7 validate顧客銘柄別取引停止(SubAccount, long, OrderTypeEnum)
            l_feqOrderManager.validateAccountProductTradedStop(
                l_subAccount, 
                l_feqProduct.getProductId(), 
                OrderTypeEnum.FEQ_BUY);
                
            //1.2.8 get発注日( )
            l_datOrderBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        else
        {
            //1.3 (*2) リクエスト.銘柄コード == null の場合
            
            //1.3.1 validate注文(補助口座)
            l_feqOrderManager.validateOrder(l_subAccount); 
            
            //1.3.2 get取引中取扱可能市場(部店 : 部店, 銘柄タイプ : ProductTypeEnum)
            //部店： 補助口座.get取引店()の戻り値
            WEB3GentradeBranch l_branch = 
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
            String[] l_strHandlingPossibleMarkets =
                WEB3GentradeFeqBranchMarketDealtCond.getTradingHandlingPossibleMarket(
                    l_branch,
                    ProductTypeEnum.FOREIGN_EQUITY);
            if (l_strHandlingPossibleMarkets == null || 
                l_strHandlingPossibleMarkets.length == 0)
            {
                log.debug("予期しないシステムエラーが発生しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } 
            int l_intLengthTemp = l_strHandlingPossibleMarkets.length;

            //1.3.3 市場コードの配列（get取引中取扱可能市場()の戻り値）の要素分、Loop処理 
            for (int i = 0; i < l_intLengthTemp; i++)
            {
                //1.3.3.1 reset市場コード(市場コード : String)
                WEB3GentradeTradingTimeManagement.resetMarketCode(
                    l_strHandlingPossibleMarkets[i]);
                
                //1.3.3.2  get発注日( )
                l_datOrderBizDate = 
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
               
                //すべての発注日!=ローカルスレッドの現在時刻の日付部分 
                //でない場合、1番目の要素を記憶しておく。
                if (i == 0)
                {
                    l_datOrderResultBizDate = l_datOrderBizDate;
                }

                //1.3.4 (*3)
                //取得した発注日==ローカルスレッドの現在時刻の日付部分 
                //となる要素がある場合、その日付を記憶しておく。
                Date l_datCurrentDate = 
                    WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
                if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datCurrentDate) == 0)
                {
                    l_datOrderResultBizDate = l_datCurrentDate;
                }
            }
        }  
        
        //リクエスト.銘柄コード == null の場合
        //1.4 取扱可能注文条件(String, ProductTypeEnum, String, String)
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //銘柄タイプ： ”外国株式” 
        //先物／オプション区分： ”DEFAULT”(固定) 
        //信用取引区分： ”DEFAULT”(固定) 
        if (l_request.productCode == null)
        {
            l_gentradeHandlingOrderCond = 
                new WEB3GentradeHandlingOrderCond(
                    l_strInstitutionCode,
                    ProductTypeEnum.FOREIGN_EQUITY,
                    WEB3FuturesOptionDivDef.DEFAULT,
                    WEB3MarginTradingDivDef.DEFAULT);
        }
            
        //リクエスト.銘柄コード != null の場合
        //1.5 取扱可能注文条件(String, ProductTypeEnum, String, String, String)
        //取扱可能注文条件インスタンスを取得する。 
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //銘柄タイプ： ”外国株式” 
        //先物／オプション区分： ”DEFAULT”(固定) 
        //信用取引区分： ”DEFAULT”(固定) 
        //市場コード： 外国株式銘柄.get市場コードの戻り値
        else
        {
            l_gentradeHandlingOrderCond = 
                new WEB3GentradeHandlingOrderCond(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    ProductTypeEnum.FOREIGN_EQUITY,
                    WEB3FuturesOptionDivDef.DEFAULT,
                    WEB3MarginTradingDivDef.DEFAULT,
                    l_strMarketCode);
        }
        
        //1.6 取扱可能執行条件取得( )
        String[] l_strHandlingPossibleExecConds = 
            l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();
        
        //1.7 取扱可能発注条件取得( )      
        String[] l_strHandlingPossibleOrderConds = 
            l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();
            
        //1.8 取扱可能注文期限区分取得( )
        String[] l_strHandlingPossibleExpirationDateTypes =
            l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();
        
        //1.9 is出来るまで注文取扱可能( )
        boolean l_blnIsOrderUntilHandling =
            l_gentradeHandlingOrderCond.isOrderUntilDeadLinePossibleHandling();
        log.debug("is出来るまで注文取扱可能()の戻り値 = " + l_blnIsOrderUntilHandling);
        
        //1.10 (*3) is出来るまで注文取扱可能()の戻り値 == true の場合
        
        //出来るまで注文開始日
        Date l_datOrderUntilDeadLineStartDay = null;
        //出来るまで注文最終日
        Date l_datOrderUntilDeadLineEndDay = null;       
        //注文期限内祝日一覧
        Date[] l_datExpirationDateHolidays = null;
        
        if (l_blnIsOrderUntilHandling)
        {
            //1.10.1 get出来るまで注文開始日(Date)
            //[引数] 
            //出来るまで注文from日付： （以下のとおり） 
            //リクエスト.銘柄コード != null の場合、null 
            if (l_request.productCode != null)
            {
                l_datOrderUntilDeadLineStartDay = 
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(null);
            }
            else
            {
                //リクエスト.銘柄コード == null の場合、(*3)で記憶した日付
                l_datOrderUntilDeadLineStartDay = 
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(
                        l_datOrderResultBizDate);
            }

            //1.10.2 get出来るまで注文最終日(Date)
            //[引数] 
            //出来るまで注文from日付： （以下のとおり） 
            //リクエスト.銘柄コード != null の場合、null 
            if (l_request.productCode != null)
            {
                l_datOrderUntilDeadLineEndDay = 
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDay(null);
            }
            else
            {
                //リクエスト.銘柄コード == null の場合、(*3)で記憶した日付
                l_datOrderUntilDeadLineEndDay = 
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDay(
                        l_datOrderResultBizDate);
            }
             
            //1.10.3 get注文期限内祝日一覧(Date)
            //[引数] 
            //出来るまで注文from日付： （以下のとおり） 
            //リクエスト.銘柄コード != null の場合、null 
            if (l_request.productCode != null)
            {
                l_datExpirationDateHolidays = 
                    l_gentradeHandlingOrderCond.getExpirationDateHoliday(null);
            }
            else
            {
                //リクエスト.銘柄コード == null の場合、(*3)で記憶した日付
                l_datExpirationDateHolidays = 
                    l_gentradeHandlingOrderCond.getExpirationDateHoliday(
                        l_datOrderResultBizDate);
            }
        }
           
        //1.11 get市場閉局警告外株市場(部店 : 部店) 
        String[] l_strTradeCloseFeqMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                l_subAccount.getWeb3GenBranch());
                
        //時価取得区分
        String l_strCurrentPriceGetDiv = null;
        //時価
        String l_strCurrentPrice = null;
        //前日比
        String l_strComparedPreviousDay = null;
        //時価発表時間
        Date l_DatCurrentPricePublicTime = null;
        boolean l_blnfeqProductQuoteIsNull = true;
        
        //1.12 リクエストデータ.銘柄コード != nullの場合
        if (l_request.productCode != null)
        {
            l_blnfeqProductQuoteIsNull = false;
            
            //1.12.1 get表示用時価情報(外国株式取引銘柄, 補助口座)
            WEB3FeqProductManager l_feqProductManager =
                (WEB3FeqProductManager) GtlUtils.getTradingModule(
                    ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
            //外国株式時価情報   
            WEB3FeqProductQuote l_feqProductQuote =
                l_feqProductManager.getIndicationCurrentPriceUnit(
                    l_feqTradedProduct, l_subAccount);
            if (l_feqProductQuote != null)
            {
                //1.13.2 get時価取得区分( )
                l_strCurrentPriceGetDiv =
                    l_feqProductQuote.getCurrentPriceGetDiv();

                //1.12.3 get時価( )
                l_strCurrentPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqProductQuote.getCurrentPrice());
            
                //1.12.4 get前日比( )
                l_strComparedPreviousDay = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqProductQuote.getComparedPreviousDay());
        
                //1.12.5 get時価発表時間( )
                l_DatCurrentPricePublicTime = 
                    l_feqProductQuote.getCurrentPricePublicTime();
            }
        }
        
        //1.13 getその他商品買付可能額(補助口座 : 補助口座, 受渡日 : Date)
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //受渡日： （以下のとおり） 
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        //その他商品買付可能額
        double l_dblBuyPossiblePrice = 0D;

        //リクエスト.銘柄コード == null の場合、(*3)で記憶した日付の３営業日後（受渡日）     
        if (l_request.productCode == null)
        {
            WEB3GentradeBizDate l_genBizDate = 
                new WEB3GentradeBizDate(new Timestamp(l_datOrderResultBizDate.getTime()));
            Timestamp l_timeThreeBizDate = l_genBizDate.roll(3);
            l_dblBuyPossiblePrice = 
                l_tpTradingPowerService.getOtherTradingPower(
                    l_subAccount, 
                    l_timeThreeBizDate);
        }
        //リクエスト.銘柄コード != null の場合、取引銘柄.get受渡日()の戻り値 
        else
        {
            l_dblBuyPossiblePrice = 
                l_tpTradingPowerService.getOtherTradingPower(
                    l_subAccount, 
                    l_feqTradedProduct.getDailyDeliveryDate());
        }

        //getその他商品買付可能（外貨）
        WEB3FeqTradingPowerUnit[] l_feqTradingPowerUnits = null;
        if (l_request.productCode == null)
        {
            //証券会社コード： 補助口座.証券会社コード
            //外国株式銘柄： （以下のとおり）
            //リクエスト.銘柄コード == nullの場合、null
            //買付可能額（円貨）：getその他商品買付可能額（）の戻り値
            l_feqTradingPowerUnits = this.getFeqOtherTradingPowerForeignUnit(
                l_subAccount.getInstitution().getInstitutionCode(),
                null,
                l_dblBuyPossiblePrice);
        }
        else
        {
            //証券会社コード： 補助口座.証券会社コード
            //外国株式銘柄： （以下のとおり）
            //リクエスト.銘柄コード != null の場合、外国株式銘柄オブジェクト
            //買付可能額（円貨）：getその他商品買付可能額（）の戻り値
            l_feqTradingPowerUnits = this.getFeqOtherTradingPowerForeignUnit(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_feqProduct,
                l_dblBuyPossiblePrice);
        }

        //QA72
        //買付可能額（外貨）の取得ロジック
        List l_lisCurrencyRows = new ArrayList();
        //買付可能額（外貨）
        double l_dblCalcForeignCCYAmount = 0D;
                        
        //1.14 リクエスト.銘柄コード != null is特定口座開設(
        //受渡日 : Date, 補助口座 : 補助口座)
        //特定口座が開設されているかどうかを判定する。 
        //[引数] 
        //受渡日： 外国株式取引銘柄.get受渡日()の戻り値 
        //補助口座： 補助口座オブジェクト 
        
        //口座区分一覧：
        boolean l_blnIsSpecialAccountEstablished = false;
        if (l_request.productCode != null)
        {
            l_blnIsSpecialAccountEstablished = 
                l_mainAccount.isSpecialAccountEstablished(
                    l_feqTradedProduct.getDailyDeliveryDate(), 
                    l_subAccount);
        }

        //1.15 リクエスト.銘柄コード == null の場合  
        //      is特定口座開設(補助口座 : 補助口座)
        else
        {
            l_blnIsSpecialAccountEstablished = 
                l_mainAccount.isSpecialAccountEstablished(l_subAccount);    
        }
        
        //1.16 レスポンスデータを生成する。 
        WEB3FeqBuyInputResponse l_response = 
            (WEB3FeqBuyInputResponse)l_request.createResponse();
        
        //1.17(*4) 以下のとおり、プロパティをセットする。
            
        //注文単価区分一覧： ”成行”と”指値”の区分の配列
        String[] l_strPriceDivs = 
            {WEB3OrderPriceDivDef.MARKET_PRICE, 
             WEB3OrderPriceDivDef.LIMIT_PRICE};
        l_response.orderPriceDivList = l_strPriceDivs;
        
        //執行条件一覧： 取扱可能執行条件取得()の戻り値
        l_response.execCondList = l_strHandlingPossibleExecConds;
        
        //注文期限区分一覧： 取扱可能注文期限区分取得()の戻り値
        l_response.expirationDateTypeList = 
            l_strHandlingPossibleExpirationDateTypes;
        
        //有効期限開始日： get出来るまで注文開始日()の戻り値
        l_response.expirationStartDate = l_datOrderUntilDeadLineStartDay;
        
        //有効期限終了日： get出来るまで注文最終日()の戻り値
        l_response.expirationEndDate = l_datOrderUntilDeadLineEndDay;
        
        //有効期限内祝日一覧： get注文期限内祝日一覧()の戻り値
        l_response.holidayList = l_datExpirationDateHolidays;
        
        //発注条件一覧： 取扱可能発注条件取得()の戻り値
        l_response.orderCondTypeList = l_strHandlingPossibleOrderConds;
        
        //取引終了警告市場コード一覧： get市場閉局警告外株市場()の戻り値
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;
        
        //買付可能額（円貨）： getその他買付可能額()の戻り値
        l_response.tradingPowerYen = 
            WEB3StringTypeUtility.formatNumber(l_dblBuyPossiblePrice);

        //買付可能額（外貨）：getその他商品買付可能（外貨）（）の戻り値
        l_response.tradingPowerFrnList = l_feqTradingPowerUnits;

        //口座区分一覧： （以下のとおり）
        //顧客.is特定口座開設() == true の場合、”一般”と”特定”の区分の配列
        // それ以外の場合、”一般”のみの配列
        if (l_blnIsSpecialAccountEstablished)
        {
            String[] l_strTaxTypeList = 
                { WEB3TaxTypeSpecialDef.NORMAL, WEB3TaxTypeSpecialDef.SPECIAL };
            l_response.taxTypeList = l_strTaxTypeList;
        }
        else
        {
            String[] l_strTaxTypeList = { WEB3TaxTypeSpecialDef.NORMAL };
            l_response.taxTypeList = l_strTaxTypeList;
        }
            
        //決済区分一覧： （以下のとおり）
        //買付可能額（外貨）が取得できている場合、”円貨”と”外貨”の区分の配列
        String[] l_strSettleDivList = null;
        if (l_dblCalcForeignCCYAmount > 0)
        {
            l_strSettleDivList = new String[2];
            l_strSettleDivList[0] = WEB3InputOutputActionSettlementDivDef.EN_SETTLE;
            l_strSettleDivList[1] = WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE;
            l_response.settleDivList = l_strSettleDivList;
        }
        else
        {
            //買付可能額（外貨）が取得できてない or すべての要素が0の場合、”円貨”のみの配列
            if (l_dblCalcForeignCCYAmount == 0 || l_lisCurrencyRows.size() == 0)
            {
                l_strSettleDivList = new String[1];
                l_strSettleDivList[0] = WEB3InputOutputActionSettlementDivDef.EN_SETTLE;
                l_response.settleDivList = l_strSettleDivList;
            }
        }        

        //(*A)リクエスト.銘柄コード != null の場合
        if (l_request.productCode != null)
        {
            //銘柄コード： 外国株式銘柄.getProductCode()の戻り値
            l_response.productCode = l_strProductCode;
            
            //現地銘柄コード： 外国株式銘柄.get現地銘柄コード()の戻り値
            l_response.localProductCode = l_strOffshoreProductCode;
        
            //銘柄名： 外国株式銘柄.get表示銘柄名()の戻り値
            l_response.productName =  l_strProductName;   
            
            //市場コード： 外国株式銘柄.get市場コード()の戻り値
            l_response.marketCode = l_strMarketCode;
            
            if (!l_blnfeqProductQuoteIsNull)
            {
                //時価取得区分： get時価取得区分()の戻り値
                l_response.currentPriceGetDiv = l_strCurrentPriceGetDiv;
        
                //時価： get時価()の戻り値
                l_response.currentPrice = l_strCurrentPrice;
        
                //前日比： get前日比()の戻り値
                l_response.comparedPreviousDay = l_strComparedPreviousDay;
        
                //取引時間： get時価発表時間()の戻り値
                l_response.currentPriceTime = l_DatCurrentPricePublicTime;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 買付注文の確認処理を行う。<BR>
     * <BR>
     * シーケンス図「（外株買付）validate注文」 参照。<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@外株買付」(外株買付）validate注文)<BR>
     * 　@　@:  1.17 validate取引余力<BR> 
     * 　@　@戻り値の取引余力結果.判定フラグ == falseの場合、<BR>
     * 　@　@例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3FeqBuyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AF4BA01AE
     */
    protected WEB3FeqBuyConfirmResponse validateOrder(WEB3FeqBuyConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqBuyConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1)validate( )
        l_request.validate();
        
        //1.2)get補助口座( )
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3 get代理入力者( )
        WEB3GentradeTrader l_trade = (WEB3GentradeTrader)this.getTrader();
        
        //1.4 getFeqProduct(arg0 : Institution, arg1 : 論理ビュー::java::lang::String)
        //外国株式銘柄オブジェクトを取得する。 
        //[引数] 
        //証券会社： 補助口座.getInstitution()の戻り値 
        //銘柄コード： リクエスト銘柄コード 
        
        //証券会社            
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution) l_subAccount.getInstitution();
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            l_feqProduct = 
                (WEB3FeqProduct) l_feqProductManager.getFeqProduct(
                    l_institution, 
                    l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("外国株式銘柄が取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        String l_strMarketCode = l_feqProduct.getMarketCode();
        
        //1.5 reset市場コード(市場コード : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
        //1.6 get発注日()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.7 get執行条件(String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        FeqExecutionConditionType l_executionCond = 
            l_feqOrderManager.getExecutionCondition(l_request.execCondType);
        
        //1.8 create新規注文内容
        //新規注文内容オブジェクトを生成する。 
        //[引数] 
        //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 
        //扱者： get代理入力者()の戻り値 
        //is買付注文： true 
        //銘柄コード： 外国株式銘柄.銘柄コード 
        //市場コード：外国株式銘柄.市場コード 
        //注文数量： リクエスト.注文数量 
        //注文単価： リクエスト.注文単価 
        //執行条件： get執行条件()の戻り値 
        //注文失効日： リクエスト.注文有効期限 
        //税区分： リクエスト.特定口座区分 
        //通貨コード： 外国株式銘柄.通貨コード 
        //発注条件： リクエスト.発注条件 
        //（W指値）訂正指値： リクエスト.W指値用注文単価 
        //決済区分： リクエスト.決済区分 
        //初回注文の注文単位ID： （以下のとおり） 
        //   リクエスト.注文期限区分 == ”当日限り” の場合、null 
        //   リクエスト.注文期限区分 == ”出来るまで注文” の場合、0 
        String l_stringInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        Long l_lngFirstOrderUnitId = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = null;
        }
        else
        {
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
            {
                l_lngFirstOrderUnitId = new Long(0);
            }
        }
        
        //注文単価
        double l_dblLimitPrice = 0.0D;
        //W指値用注文単価
        double l_dblWLimitPrice = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_request.limitPrice))
        {
            l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
        }
        if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
        {
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        
        //特定口座区分
        TaxTypeEnum l_taxTypeEnum = null;
        //特定口座区分＝”0：一般口座” の場合
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        else 
        {
            //特定口座区分＝”1:特定口座” の場合
            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
            {
                l_taxTypeEnum = TaxTypeEnum.SPECIAL;
            }
        }
        WEB3FeqNewOrderSpec l_feqNewOrderSpec =
            WEB3FeqNewOrderSpec.createNewOrderSpec(
                l_stringInstitutionCode,
                l_trade,
                true,
                l_feqProduct.getProductCode(),
                l_strMarketCode,
                Double.parseDouble(l_request.orderQuantity),
                l_dblLimitPrice,
                l_executionCond,
                l_request.expirationDate,
                l_taxTypeEnum,
                l_feqProduct.getCurrencyCode(),
                l_request.orderCondType,
                l_dblWLimitPrice,
                l_request.settleDiv,
                l_lngFirstOrderUnitId);
               
        //1.9  validate新規注文(SubAccount, ProductTypeEnum, NewOrderSpec)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //銘柄タイプ： ”外国株式” 
        //注文内容： 外国株式新規注文内容オブジェクト
        NewOrderValidationResult  l_newOrderValidationResult =
            l_feqOrderManager.validateNewOrder(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec);
       
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("注文内容のチェックを行う Error " +
            l_newOrderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //1.10 get計算用注文単価(外国株式取引銘柄, String, double, double, boolean)
        WEB3GentradeBranch l_branch = 
            (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        //外国株式取引銘柄
        WEB3FeqTradedProduct l_feqTradedProduct = 
            l_feqProduct.getFeqTradedProduct();
            
        double l_dblUnitPrice =
            l_feqOrderManager.getUnitPrice(
                l_feqTradedProduct,
                l_branch,
                l_request.orderPriceDiv,
                l_dblLimitPrice,
                l_dblWLimitPrice,
                true);
        
        //1.11 calc売買代金(double, double)
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        //外国株式計算サービス
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblCalcExecutionAmount =
            l_feqBizLogicProvider.calcExecutionAmount(
                Double.parseDouble(l_request.orderQuantity), 
                l_dblUnitPrice);
        
        //1.12 get市場( )
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.13 get通貨( )
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
        String l_strCurrencyCode = l_feqProduct.getCurrencyCode();    
        
        //calc売買代金(double, double)の結果を、当該通貨の小数点桁数で丸める
        BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblCalcExecutionAmount);
        int l_intDecimalPlace = l_currency.getScale();
        l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblCalcExecutionAmount = l_bdTradePriceFc.doubleValue();
        log.debug("売買代金の丸めた結果 ＝ "+ l_dblCalcExecutionAmount);
        
        //1.14 get買付基準為替レート( )
        double l_dblBuyBaseFxRate = l_currency.getBuyBaseRate();
        
        //1.15 calc外国株式金額(補助口座, 外国株式銘柄, 市場, Date, 
        //      double, double, boolean, boolean, boolean)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //外国株式銘柄： getFeqProduct()の戻り値 
        //市場： get市場()の戻り値 
        //基準日： get発注日()の戻り値 
        //売買代金（外貨）： calc売買代金()の戻り値 
        //為替レート： get買付基準為替レート()の戻り値 
        //is買付： true 
        //is約定計算： false 
        //is指値： （以下のとおり） 
        //   リクエスト.注文単価区分 == ”指値” の場合、true 
        //   リクエスト.注文単価区分 == ”成行” の場合、false 
        //注文チャネル：　@this.getログインチャネル()
        
        //is指値
        boolean l_blnOrderPrice = true;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_blnOrderPrice = false;
        }
        else
        {
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
            {
                l_blnOrderPrice = true;
            }
        }
        WEB3FeqAmountCalcResult l_feqAmountCalcResult =
            l_feqBizLogicProvider.calcFeqAmount(
                l_subAccount,
                l_feqProduct,
                l_market,
                l_datOrderBizDate,
                l_datOrderBizDate,
                l_dblCalcExecutionAmount,
                l_dblBuyBaseFxRate,
                true,
                false,
                l_blnOrderPrice,
                this.getLoginChannel());

        //1.16 外国株式注文更新イベントインタセプタ
        //注文更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //注文内容： 外国株式新規注文内容オブジェクト 
        //計算結果： calc外国株式金額()の戻り値 
        //計算単価： get計算用注文単価()の戻り値 
        //発注条件： リクエスト.発注条件 
        //発注条件演算子： （以下のとおり） 
        //リクエスト.発注条件 == ”逆指値” の場合、リクエスト.逆指値用発注条件演算子 
        //リクエスト.発注条件 == ”W指値” の場合、リクエスト.W指値用発注条件演算子 
        //発注条件単価： （以下のとおり） 
        //リクエスト.発注条件 == ”逆指値” の場合、リクエスト.逆指値用発注条件単価 
        //リクエスト.発注条件 == ”W指値” の場合、リクエスト.W指値用発注条件単価     
        
        //発注条件演算子
        String l_strOrderCondOperator = null;
        //発注条件単価
        double l_dblOrderCondPrice = 0.0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.stopOrderCondOperator;
            l_dblOrderCondPrice = 
                Double.parseDouble(l_request.stopOrderCondPrice);
        }
        else
        {
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
        }
        WEB3FeqOrderUpdateInterceptor l_feqOrderUpdateInterceptor = 
            new WEB3FeqOrderUpdateInterceptor(
                l_feqNewOrderSpec,
                l_feqAmountCalcResult,
                l_dblUnitPrice,
                l_request.orderCondType,
                l_strOrderCondOperator,
                l_dblOrderCondPrice);
      
        //1.17 validate取引余力
        //取引余力の更新を行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //注文内容インタセプタ： 外国株式注文更新インタセプタを要素とした配列 
        //注文内容： 外国株式新規注文内容を要素とした配列 
        //注文種別： ”外株買” 
        //余力更新フラグ： false 
        
        //取引余力サービスインタフェース
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        //外国株式注文更新インタセプタを要素とした配列 
        WEB3FeqOrderUpdateInterceptor[] l_feqOrderUpdateInterceptors = 
            {l_feqOrderUpdateInterceptor};
            
        //外国株式新規注文内容を要素とした配列 
        WEB3FeqNewOrderSpec[] l_feqNewOrderSpecs = {l_feqNewOrderSpec}; 
                
        WEB3TPTradingPowerResult l_tPTradingPowerResult = 
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount, 
                l_feqOrderUpdateInterceptors, 
                l_feqNewOrderSpecs, 
                OrderTypeEnum.FEQ_BUY, 
                false);
        if (l_tPTradingPowerResult == null)
        {
            log.debug("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローする。
        if (!l_tPTradingPowerResult.isResultFlg())
        {
            log.debug("取引余力チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力チェックエラー。");
        }
        
        //1.18 get市場閉局警告外株市場(部店 : 部店)
        String[] l_strTradeCloseFeqMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(l_branch);
        
        //1.19 createNewOrderId( )
        //新規の注文IDを取得する。
        long l_lngNewOrderId = l_feqOrderManager.createNewOrderId();
                
        //1.20  createResponse( )
        WEB3FeqBuyConfirmResponse l_response = 
            (WEB3FeqBuyConfirmResponse)l_request.createResponse();
                    
        //1.21 (*) プロパティセット
        //(*) 以下のとおりに、プロパティをセットする。
        
        //銘柄コード： 外国株式銘柄.getProductCode()の戻り値
        l_response.productCode = l_feqProduct.getProductCode();
        
        //現地銘柄コード： 外国株式銘柄.get現地銘柄コード()の戻り値
        l_response.localProductCode= l_feqProduct.getOffshoreProductCode();
        
        //銘柄名： 外国株式銘柄.get表示銘柄名()の戻り値
        l_response.productName = l_feqProduct.getDisplayProductName();
        
        //市場コード：外国株式銘柄.get市場コード()の戻り値
        l_response.marketCode = l_strMarketCode;
        
        //買付基準為替：通貨.get買付基準為替レート()の戻り値
        l_response.buyExchange = 
            WEB3StringTypeUtility.formatNumber(l_dblBuyBaseFxRate);

        //通貨コード： 外国株式銘柄.get通貨コード()の戻り値
        l_response.currencyCode = l_strCurrencyCode;
        
        //注文ID： createNewOrderId()の戻り値
        l_response.orderId = l_lngNewOrderId + "";
        
        //確認時単価： get計算用注文単価()の戻り値
        l_response.checkPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
        
        //確認時発注日： get発注日()の戻り値
        l_response.checkDate = l_datOrderBizDate;
        
        //取引終了警告市場コード一覧：get市場閉局警告市場()の戻り値
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;
           
        //(*1) リクエスト.決済区分 == ”円貨”の場合、設定する値は円貨換算されたもの。
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(l_request.settleDiv))
        {
            //概算受渡代金： 外国株式金額計算結果.受渡代金(*1)
            l_response.estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getNetAmount());
        
            //現地精算代金： 外国株式金額計算結果.現地精算代金(*1)
            l_response.localClearUpPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getBalanceAmount());
                
            //手数料： 外国株式金額計算結果.委託手数料(*1)
            l_response.commission = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommissionFee());
            
            //手数料消費税： 外国株式金額計算結果.委託手数料消費税(*1)
            l_response.commissionConsumptionTax = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommisionFeeTax());
        }
        else
        {
            //リクエスト.決済区分 == ”外貨”の場合、設定する値は外貨換算されたもの。
            if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(l_request.settleDiv))
            {
                //概算受渡代金： 外国株式金額計算結果.受渡代金(外貨)
                l_response.estimatedPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getNetAmountFc());
        
                //現地精算代金： 外国株式金額計算結果.現地清算代金
                l_response.localClearUpPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getBalanceAmountFc());
                
                //手数料： 外国株式金額計算結果.委託手数料(外貨)
                l_response.commission = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getCommissionFeeFc());
            
                //手数料消費税： 外国株式金額計算結果.委託手数料消費税(外貨)
                l_response.commissionConsumptionTax = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getCommisionFeeTaxFc());
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * 買付注文の登録処理を行う。<BR>
     * <BR>
     * シーケンス図「（外株買付）submit注文」 参照。<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@外株買付」(外株買付）submit注文)<BR>
     * 　@　@:  1.16 validate取引余力<BR> 
     * 　@　@戻り値の取引余力結果.判定フラグ == falseの場合、<BR>
     * 　@　@例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3FeqBuyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AF4BA01CD
     */
    protected WEB3FeqBuyCompleteResponse submitOrder(WEB3FeqBuyCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqBuyCompleteRequest l_request)";
            
        //1.1) validate( )
        l_request.validate();
        
        //1.2)get補助口座()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();

        //証券会社コード
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution) l_subAccount.getInstitution();
        
        //1.3 get代理入力者( )
        WEB3GentradeTrader l_trade = (WEB3GentradeTrader) this.getTrader();
        
        //1.4 getFeqProduct(arg0 : Institution, arg1 : 論理ビュー::java::lang::String)
        //外国株式銘柄オブジェクトを取得する。 
        //[引数] 
        //証券会社： 補助口座.getInstitution()の戻り値 
        //銘柄コード： リクエスト銘柄コード 
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            l_feqProduct = 
                (WEB3FeqProduct) l_feqProductManager.getFeqProduct(
                    l_institution, 
                    l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("外国株式銘柄が取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                  
        String l_strMarketCode = l_feqProduct.getMarketCode();
        
        //1.5 get発注日(確認時発注日 : Date)
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.6 get執行条件(String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        FeqExecutionConditionType l_executionCond = 
            l_feqOrderManager.getExecutionCondition(l_request.execCondType);
        
        //1.7 create新規注文内容
        //新規注文内容オブジェクトを生成する。 
        //[引数] 
        //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 
        //扱者： get代理入力者()の戻り値 
        //is買付注文： true 
        //銘柄コード： 外国株式銘柄.銘柄コード 
        //市場コード：外国株式銘柄.市場コード 
        //注文数量： リクエスト.注文数量 
        //注文単価： リクエスト.注文単価 
        //執行条件： get執行条件()の戻り値 
        //注文失効日： リクエスト.注文有効期限 
        //税区分： リクエスト.特定口座区分 
        //通貨コード： 外国株式銘柄.通貨コード 
        //発注条件： リクエスト.発注条件 
        //（W指値）訂正指値： リクエスト.W指値用注文単価 
        //決済区分： リクエスト.決済区分 
        //初回注文の注文単位ID： （以下のとおり） 
        //   リクエスト.注文期限区分 == ”当日限り” の場合、null 
        //   リクエスト.注文期限区分 == ”出来るまで注文” の場合、0 
        String l_stringInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        Long l_lngFirstOrderUnitId = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = null;
        }
        else
        {
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
            {
                l_lngFirstOrderUnitId = new Long(0);
            }
        }
        //注文単価
        double l_dblLimitPrice = 0.0D;
        //W指値用注文単価
        double l_dblWLimitPrice = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_request.limitPrice))
        {
            l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
        }
        if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
        {
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        
        //特定口座区分
        TaxTypeEnum l_taxTypeEnum = null;
        //特定口座区分＝”0：一般口座” の場合
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        else 
        {
            //特定口座区分＝”1:特定口座” の場合
            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
            {
                l_taxTypeEnum = TaxTypeEnum.SPECIAL;
            }
        }
        WEB3FeqNewOrderSpec l_feqNewOrderSpec =
            WEB3FeqNewOrderSpec.createNewOrderSpec(
                l_stringInstitutionCode,
                l_trade,
                true,
                l_feqProduct.getProductCode(),
                l_strMarketCode,
                Double.parseDouble(l_request.orderQuantity),
                l_dblLimitPrice,
                l_executionCond,
                l_request.expirationDate,
                l_taxTypeEnum,
                l_feqProduct.getCurrencyCode(),
                l_request.orderCondType,
                l_dblWLimitPrice,
                l_request.settleDiv,
                l_lngFirstOrderUnitId);

        //1.8  validate新規注文(SubAccount, ProductTypeEnum, NewOrderSpec)
        NewOrderValidationResult  l_newOrderValidationResult =
            l_feqOrderManager.validateNewOrder(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec);
       
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("注文内容のチェックを行う Error " +
            l_newOrderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //1.9 calc売買代金(double, double)
        
        //確認時単価
        double l_dblCheckPrice = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_request.checkPrice))
        {
            l_dblCheckPrice = Double.parseDouble(l_request.checkPrice);
        }
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblCalcExecutionAmount =
            l_feqBizLogicProvider.calcExecutionAmount(
                Double.parseDouble(l_request.orderQuantity), 
                l_dblCheckPrice);
        
        //1.10 get市場( )
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.11 get通貨( )
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
        
        //calc売買代金(double, double)の結果を、当該通貨の小数点桁数で丸める
        BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblCalcExecutionAmount);
        int l_intDecimalPlace = l_currency.getScale();
        l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblCalcExecutionAmount = l_bdTradePriceFc.doubleValue();
        log.debug("売買代金の丸めた結果 ＝ "+ l_dblCalcExecutionAmount);
        
        //1.12 get買付基準為替レート( )
        double l_dblBuyBaseFxRate = l_currency.getBuyBaseRate();
        
        //1.13 calc外国株式金額(補助口座, 外国株式銘柄, 市場, Date, double, 
                //double, boolean, boolean, boolean)
        //各種金額の計算を行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //外国株式銘柄： getFeqProduct()の戻り値 
        //市場： get市場()の戻り値 
        //基準日： get発注日()の戻り値 
        //売買代金（外貨）： calc売買代金()の戻り値 
        //為替レート： get買付基準為替レート()の戻り値 
        //is買付： true 
        //is約定計算： false 
        //is指値： （以下のとおり） 
        //   リクエスト.注文単価区分 == ”指値” の場合、true 
        //   リクエスト.注文単価区分 == ”成行” の場合、false 
        //注文チャネル：　@this.getログインチャネル()
        
        //is指値
        boolean l_blnOrderPrice = true;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_blnOrderPrice = false;
        }
        else
        {
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
            {
                l_blnOrderPrice = true;
            }
        }
        WEB3FeqAmountCalcResult l_feqAmountCalcResult =
            l_feqBizLogicProvider.calcFeqAmount(
                l_subAccount,
                l_feqProduct,
                l_market,
                l_datOrderBizDate,
                l_datOrderBizDate,
                l_dblCalcExecutionAmount,
                l_dblBuyBaseFxRate,
                true,
                false,
                l_blnOrderPrice, 
                this.getLoginChannel());
        
        //1.14 外国株式注文更新イベントインタセプタ
        //注文更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //注文内容： 外国株式新規注文内容オブジェクト 
        //計算結果： calc外国株式金額()の戻り値 
        //計算単価： リクエスト.確認時単価 
        //発注条件： リクエスト.発注条件 
        //発注条件演算子： （以下のとおり） 
        //リクエスト.発注条件 == ”逆指値” の場合、リクエスト.逆指値用発注条件演算子 
        //リクエスト.発注条件 == ”W指値” の場合、リクエスト.W指値用発注条件演算子 
        //発注条件単価： （以下のとおり） 
        //リクエスト.発注条件 == ”逆指値” の場合、リクエスト.逆指値用発注条件単価 
        //リクエスト.発注条件 == ”W指値” の場合、リクエスト.W指値用発注条件単価 
        
        //発注条件演算子
        String l_strOrderCondOperator = null;
        //発注条件単価
        double l_dblOrderCondPrice = 0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.stopOrderCondOperator;
            l_dblOrderCondPrice = 
                Double.parseDouble(l_request.stopOrderCondPrice);
        }
        else
        {
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblOrderCondPrice = 
                    Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
        }
        WEB3FeqOrderUpdateInterceptor l_feqOrderUpdateInterceptor = 
            new WEB3FeqOrderUpdateInterceptor(
                l_feqNewOrderSpec,
                l_feqAmountCalcResult,
                l_dblCheckPrice,
                l_request.orderCondType,
                l_strOrderCondOperator,
                l_dblOrderCondPrice);
        
        //1.15 validate取引余力
        //取引余力の更新を行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //注文内容インタセプタ： 外国株式注文更新インタセプタを要素とした配列 
        //注文内容： 外国株式新規注文内容を要素とした配列 
        //注文種別： ”外株買” 
        //余力更新フラグ： true 
        
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        //外国株式注文更新インタセプタを要素とした配列 
        WEB3FeqOrderUpdateInterceptor[] l_feqOrderUpdateInterceptors = 
            {l_feqOrderUpdateInterceptor};
        //注文内容の配列
        WEB3FeqNewOrderSpec[] l_feqNewOrderSpecs = {l_feqNewOrderSpec}; 
        WEB3TPTradingPowerResult l_tPTradingPowerResult = 
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount, 
                l_feqOrderUpdateInterceptors, 
                l_feqNewOrderSpecs, 
                OrderTypeEnum.FEQ_BUY, 
                true);
                
        if (l_tPTradingPowerResult == null)
        {
            log.debug("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローする。
        if (!l_tPTradingPowerResult.isResultFlg())
        {
            log.debug("取引余力チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力チェックエラー。");
        }

        
        //1.16 setThreadLocalPersistenceEventInterceptor(
            //arg0 : FeqOrderManagerPersistenceEventInterceptor)
        l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(l_feqOrderUpdateInterceptor);     
        
        //1.17 submitNewOrder
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //銘柄タイプ： ”外国株式” 
        //注文内容： 外国株式注文内容オブジェクト 
        //注文ID： リクエスト.注文ID 
        //パスワード： リクエスト.暗証番号 
        //isSkip発注審査： true
        long l_lngOrderId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.orderId))
        {
            l_lngOrderId =  Long.parseLong(l_request.orderId);
        } 
        OrderSubmissionResult l_submitNewOrderResult =
            l_feqOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec,
                l_lngOrderId,
                l_request.password,
                true);
            
        if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitNewOrder" +
            l_submitNewOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  

        //1.18 getOrder(注文ID : long)
        FeqOrderRow l_orderRow = null;
        try
        {
            Order l_order = l_feqOrderManager.getOrder(l_lngOrderId);
            l_orderRow = (FeqOrderRow) l_order.getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.19  createResponse( )
        WEB3FeqBuyCompleteResponse l_response = 
            (WEB3FeqBuyCompleteResponse)l_request.createResponse();
        
        //1.20 (*) プロパティセット
        //(*) 以下のとおりに、プロパティをセットする。
        //更新時間： 注文.更新日時
        l_response.lastUpdatedTimestamp = l_orderRow.getLastUpdatedTimestamp();
        //注文ID：リクエスト.注文ID
        l_response.orderId = l_request.orderId;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get銘柄選択画面)<BR>
     * 銘柄選択画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * シーケンス図「（外株買付）get銘柄選択画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3FeqBuyProductSelectResponse
     * @@throws WEB3BaseException
     */
    protected WEB3FeqBuyProductSelectResponse getProductSelectScreen(
        WEB3FeqBuyProductSelectRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getProductSelectScreen(WEB3FeqBuyProductSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)this.getSubAccount();
        
        WEB3FeqOrderManager l_feqOrderManager =
            (WEB3FeqOrderManager)GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        l_feqOrderManager.validateOrder(l_subAccount);
        
        String[] l_strMessageSuspension =
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                l_subAccount.getWeb3GenBranch());
        
        WEB3FeqBuyProductSelectResponse l_response =
            (WEB3FeqBuyProductSelectResponse)l_request.createResponse();
        
        l_response.messageSuspension = l_strMessageSuspension;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getその他商品買付可能（外貨）)<BR>
     * 買付可能額（外貨）一覧を取得する。<BR>
     * <BR>
     * １）　@外国株式銘柄オブジェクト != nullの場合、以下の処理を行う。<BR>
     * <BR>
     * 　@　@　@１－１）　@通貨オブジェクト取得<BR>
     * 　@　@　@　@　@　@　@外国株式銘柄.get通貨()にて通貨オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@１－２）　@買付可能額（外貨）の各プロパティに以下の通り値をセットする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@買付可能額（外貨）.通貨コード　@=　@外国株式銘柄.get通貨コード()の戻り値。<BR>
     * 　@　@　@　@　@　@　@買付可能額（外貨）.買付可能額　@=　@外国株式計算サービス.calc外貨換算（）の戻り値。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@[calc外貨換算（）に指定する引数]<BR>
     * 　@　@　@　@　@　@　@　@金額（円貨）：　@引数.買付可能額（円貨）<BR>
     * 　@　@　@　@　@　@　@　@レート：　@get通貨().get買付基準為替レート ()の戻り値<BR>
     * 　@　@　@　@　@　@　@　@小数部桁数：get通貨().get小数部桁数()の戻り値<BR>
     * 　@　@　@　@　@　@　@　@外貨換算丸め方式：get通貨().get外貨換算丸め方式()の戻り値<BR>
     * <BR>
     * 　@　@　@　@　@　@　@※calc外貨換算（）の戻り値がdoubleです。<BR>
     * <BR>
     * ２）　@外国株式銘柄オブジェクト == nullの場合、以下の処理を行う。<BR>
     * <BR>
     * 　@　@　@２－１）　@外国株式プロダクトマネージャ.get通貨（）<BR>
     * 　@　@　@　@　@　@　@　@[get通貨（）に指定する引数]<BR>
     * 　@　@　@　@　@　@　@　@証券会社コード：　@引数.証券会社コード<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@通貨オブジェクトを取得できない場合、nullを返却する。<BR>
     * <BR>
     * 　@　@　@２－２）　@取得した通貨オブジェクトの要素数分Loopし、以下の処理を行う。<BR>
     * <BR>
     * 　@　@　@　@　@買付可能額（外貨）の各プロパティに以下の通り値をセットする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@買付可能額（外貨）.通貨コード　@=　@get通貨().get通貨コード()の戻り値。<BR>
     * 　@　@　@　@　@　@　@買付可能額（外貨）.買付可能額　@=　@外国株式計算サービス.calc外貨換算（）の戻り値。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@[calc外貨換算（）に指定する引数]<BR>
     * 　@　@　@　@　@　@　@　@金額（円貨）：　@引数.買付可能額（円貨）<BR>
     * 　@　@　@　@　@　@　@　@レート：　@get通貨().get買付基準為替レート ()の戻り値<BR>
     * 　@　@　@　@　@　@　@　@小数部桁数：get通貨().get小数部桁数()の戻り値<BR>
     * 　@　@　@　@　@　@　@　@外貨換算丸め方式：get通貨().get外貨換算丸め方式()の戻り値<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@※calc外貨換算（）の戻り値がdoubleです。<BR>
     * <BR>
     * ３）　@買付可能額（外貨）の配列を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * 証券会社コード
     * @@param l_web3FeqProduct - 外国株式銘柄<BR>
     * 外国株式銘柄
     * @@param l_dbTradingPowerYen - 買付可能額（円貨）<BR>
     * 買付可能額（円貨）
     * @@return WEB3FeqTradingPowerUnit[]
     */
    protected WEB3FeqTradingPowerUnit[] getFeqOtherTradingPowerForeignUnit(
        String l_strInstitutionCode, WEB3FeqProduct l_web3FeqProduct, double l_dblTradingPowerYen)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getFeqOtherTradingPowerForeignUnit(String, WEB3FeqProduct, double)";
        log.entering(STR_METHOD_NAME);
        List l_lisFeqTradingPowerUnit = new ArrayList();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        //外国株式銘柄オブジェクト != nullの場合、以下の処理を行う。 
        if (l_web3FeqProduct != null)
        {
            //通貨コード取得
            //外国株式銘柄.get通貨コード()にて通貨コードを取得する。
            String l_strCurrencyCode = l_web3FeqProduct.getCurrencyCode();
            //通貨オブジェクト取得
            //外国株式銘柄.get通貨()にて通貨オブジェクトを取得する。
            WEB3GentradeCurrency l_currency = l_web3FeqProduct.getCurrency();

            //買付可能額（外貨）の各プロパティに以下の通り値をセットする。
            //買付可能額（外貨）.通貨コード　@=　@外国株式銘柄.get通貨コード()の戻り値。
            //買付可能額（外貨）.買付可能額　@=　@外国株式計算サービス.calc外貨換算（）の戻り値。
            //[calc外貨換算（）に指定する引数] 
            //金額（円貨）：　@引数.買付可能額（円貨） 
            //レート：　@get通貨().get買付基準為替レート ()の戻り値
            //小数部桁数：get通貨().get小数部桁数()の戻り値
            //外貨換算丸め方式：get通貨().get外貨換算丸め方式()の戻り値
            //※calc外貨換算（）の戻り値がdoubleです。
            WEB3FeqTradingPowerUnit l_feqTradingPowerUnit = new WEB3FeqTradingPowerUnit();
            l_feqTradingPowerUnit.currencyCode = l_strCurrencyCode;
            double l_dblForeignCCYAmount =
                l_feqBizLogicProvider.calcForeignCCYAmount(
                    l_dblTradingPowerYen,
                    l_currency.getBuyBaseRate(),
                    l_currency.getScale(),
                    l_currency.getChangeFCcyRoundDiv());
            l_feqTradingPowerUnit.tradingPower = WEB3StringTypeUtility.formatNumber(l_dblForeignCCYAmount);
            l_lisFeqTradingPowerUnit.add(l_feqTradingPowerUnit);
        }
        else
        {
            //外国株式銘柄オブジェクト == nullの場合、以下の処理を行う。 
            //外国株式プロダクトマネージャ.get通貨（）
            //[get通貨（）に指定する引数]
            //証券会社コード：　@引数.証券会社コード
            WEB3FeqProductManager l_productMgr = (WEB3FeqProductManager)l_tradingModule.getProductManager();
            WEB3GentradeCurrency[] l_gentradeCurrencys = l_productMgr.getCurrency(l_strInstitutionCode);

            //通貨オブジェクトを取得できない場合、nullを返却する。
            if (l_gentradeCurrencys == null)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //買付可能額（外貨）の各プロパティに以下の通り値をセットする。
            //買付可能額（外貨）.通貨コード　@=　@get通貨().get通貨コード()の戻り値。
            //買付可能額（外貨）.買付可能額　@=　@外国株式計算サービス.calc外貨換算（）の戻り値。
            //[calc外貨換算（）に指定する引数]
            //金額（円貨）：　@引数.買付可能額（円貨）
            //レート：　@get通貨().get買付基準為替レート ()の戻り値
            //小数部桁数：get通貨().get小数部桁数()の戻り値
            //外貨換算丸め方式：get通貨().get外貨換算丸め方式()の戻り値
            //※calc外貨換算（）の戻り値がdoubleです。
            for (int i = 0; i < l_gentradeCurrencys.length; i++)
            {
                WEB3FeqTradingPowerUnit l_feqTradingPowerUnit = new WEB3FeqTradingPowerUnit();
                l_feqTradingPowerUnit.currencyCode = l_gentradeCurrencys[i].getCurrencyCode();
                double l_dblForeignCCYAmount =
                    l_feqBizLogicProvider.calcForeignCCYAmount(
                        l_dblTradingPowerYen,
                        l_gentradeCurrencys[i].getBuyBaseRate(),
                        l_gentradeCurrencys[i].getScale(),
                        l_gentradeCurrencys[i].getChangeFCcyRoundDiv());
                l_feqTradingPowerUnit.tradingPower = WEB3StringTypeUtility.formatNumber(l_dblForeignCCYAmount);
                l_lisFeqTradingPowerUnit.add(l_feqTradingPowerUnit);
            }
        }

        //買付可能額（外貨）の配列を返却する。
        WEB3FeqTradingPowerUnit[] l_feqTradingPowerUnits =
            new WEB3FeqTradingPowerUnit[l_lisFeqTradingPowerUnit.size()];
        if (l_lisFeqTradingPowerUnit.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            l_lisFeqTradingPowerUnit.toArray(l_feqTradingPowerUnits);
        }

        log.exiting(STR_METHOD_NAME);
    	return l_feqTradingPowerUnits;
    }
}
@
