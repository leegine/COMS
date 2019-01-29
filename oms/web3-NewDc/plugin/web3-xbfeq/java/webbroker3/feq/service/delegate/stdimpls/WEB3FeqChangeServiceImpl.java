head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式訂正サービスImpl(WEB3FeqChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成       
                 : 2005/08/03 鄭海良(中訊) レビュー  
                 : 2006/09/20 齊珂(中訊) レビュー   
                 : 2006/10/12 徐宏偉(中訊) バグ3072の対応 
Revesion History : 2008/01/21 柴双紅(中訊) モデルNo.381、モデルNo.372
Revesion History : 2010/01/12 張騰宇(中訊) モデルNo.532
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
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
import webbroker3.feq.WEB3FeqChangeOrderSpec;
import webbroker3.feq.WEB3FeqChangeUpdateInterceptor;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqProductQuote;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.message.WEB3FeqChangeCompleteRequest;
import webbroker3.feq.message.WEB3FeqChangeCompleteResponse;
import webbroker3.feq.message.WEB3FeqChangeConfirmRequest;
import webbroker3.feq.message.WEB3FeqChangeConfirmResponse;
import webbroker3.feq.message.WEB3FeqChangeInputRequest;
import webbroker3.feq.message.WEB3FeqChangeInputResponse;
import webbroker3.feq.service.delegate.WEB3FeqChangeService;
import webbroker3.feq.util.WEB3FeqOrderUtility;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式訂正サービスImpl)<BR>
 * 外国株式訂正サービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqChangeServiceImpl extends WEB3FeqClientRequestService 
    implements WEB3FeqChangeService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeServiceImpl.class);  
    
    /**
     * @@roseuid 42CE39F70280
     */
    public WEB3FeqChangeServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式訂正サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *    get入力画面()<BR>
     *    validate注文()<BR>
     *    submit注文()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4295F0E703E7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response;
        
        //リクエストデータの型により、以下のメソッドをコールする。 
        // get入力画面()
        // validate注文() 
        // submit注文() 
        if (l_request instanceof WEB3FeqChangeInputRequest)
        {
            l_response = 
                getInputScreen((WEB3FeqChangeInputRequest)l_request);   
        }        
        else if (l_request instanceof WEB3FeqChangeConfirmRequest)
        {
            l_response = 
                validateOrder((WEB3FeqChangeConfirmRequest)l_request);   
        }        
        else if (l_request instanceof WEB3FeqChangeCompleteRequest)
        {
            l_response =
                submitOrder((WEB3FeqChangeCompleteRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
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
     * シーケンス図<BR>
     * 「（外株訂正）get入力画面」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3FeqChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4295F1E102BE
     */
    protected WEB3FeqChangeInputResponse getInputScreen(
        WEB3FeqChangeInputRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 補助口座オブジェクトを取得する。
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)
            this.getSubAccount();
        
        //1.3 validate注文(補助口座)(外国株式注文マネージャ::validate注文)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        l_feqOrderManager.validateOrder(l_subAccount);
        
        //1.4 validate訂正可能市場(long)(外国株式注文マネージャ::validate訂正可能市場)
        //訂正可能な市場かどうかをチェックする。 
        //[引数] 
        //注文ID： リクエスト.注文ID 
        l_feqOrderManager.validateChangePossMarket(Long.parseLong(l_request.orderId));
        
        //1.5 validate注文訂正可能状態(long)
        //訂正対象の注文が訂正可能かどうかをチェックする。 
        //[引数] 
        //注文ID： リクエスト.注文ID 
        l_feqOrderManager.validateOrderChangePossibleStatus(
            Long.parseLong(l_request.orderId));
        
        //1.6 get注文単位ByOrderId(long)(外国株式注文マネージャ::get注文単位ByOrderId)
        //注文単位オブジェクトを取得する。 
        //[引数] 
        //注文ID： リクエスト.注文ID 
        FeqOrderUnit l_feqOrderUnit = l_feqOrderManager.getOrderUnitByOrderId(
                Long.parseLong(l_request.orderId));        
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();        

        WEB3FeqProduct l_feqProduct = null;
        try
        {
            //1.7 get外国株式銘柄(long)(外国株式プロダクトマネージャ::get外国株式銘柄)
            //外国株式銘柄オブジェクトを取得する。 
            //[引数] 
            //銘柄ID： 注文単位.銘柄ID 
            l_feqProduct =
                (WEB3FeqProduct) l_feqProductManager.getProduct(
                        l_feqOrderUnitParams.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when "
                + "l_feqProductManager.getProduct(ProductId) with "
                + "ProductId = "
                + l_feqOrderUnitParams.getProductId(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_feqProduct == null)
        {
            log.debug("Error in 外国株式銘柄オブジェクトを取得する");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.8 validate外株銘柄(証券会社, String)
        //銘柄のチェックを行う。 
        //[引数] 
        //証券会社： 補助口座.getInstitutuin()の戻り値 
        //銘柄コード： 外国株式銘柄.getProductCode()の戻り値 
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        
        l_feqOrderManager.validateFeqProduct(
            l_institution,
            l_feqProduct.getProductCode());
        
        //1.9 get市場( )(外国株式銘柄::get市場)
        //市場オブジェクトを取得する。
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.10 validate市場(市場)(外国株式注文マネージャ::validate市場)
        //市場のチェックを行う。 
        l_feqOrderManager.validateMarket(l_market);
        
        //1.11 validate取引銘柄(外国株式銘柄, 市場, boolean)
        //取引銘柄のチェックを行う。 
        //[引数] 
        //外国株式銘柄： 外国株式銘柄オブジェクト 
        //市場： get市場()の戻り値 
        //is買注文： 注文単位.is買付()の戻り値
        
        WEB3FeqOrderUnit l_web3FeqOrderUnit = (WEB3FeqOrderUnit) l_feqOrderUnit;
        boolean l_blnIsBuy = l_web3FeqOrderUnit.isBuy();
        
        l_feqOrderManager.validateTradedProduct(
            l_feqProduct, 
            l_market, 
            l_blnIsBuy);
        
        //1.12 validate顧客銘柄別取引停止(SubAccount, long, OrderTypeEnum)
        //顧客銘柄別取引訂正チェックを行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //銘柄ID： 外国株式銘柄.銘柄ID 
        //注文種別： 注文単位.注文種別
        l_feqOrderManager.validateAccountProductTradedStop(
            l_subAccount, 
            l_feqProduct.getProductId(), 
            l_feqOrderUnitParams.getOrderType());
        
        //1.13 取扱可能注文条件()
        //取扱可能注文条件インスタンスを取得する。 
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //銘柄タイプ： ”外国株式” 
        //先物／オプション区分： ”DEFAULT”(固定) 
        //信用取引区分： ”DEFAULT”(固定) 
        //市場コード： 市場.市場コード 
        String l_strMarketCode = null;
        if (l_market != null)
        {
            l_strMarketCode = l_market.getMarketCode();
        }
        
        WEB3GentradeHandlingOrderCond l_handingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(), 
                ProductTypeEnum.FOREIGN_EQUITY, 
                WEB3FuturesOptionDivDef.DEFAULT, 
                WEB3MarginTradingDivDef.DEFAULT, 
                l_strMarketCode);
        
        //1.14 取扱可能執行条件取得() 取扱可能な執行条件を取得する。 
        String[] l_strHandingOrderCond = 
            l_handingOrderCond.getHandlingPossibleExecCond();
        
        //1.15 is出来るまで注文取扱可能( )
        //出来るまで注文が取扱可能かを判定する。 
        boolean l_blnPossHanding = 
            l_handingOrderCond.isOrderUntilDeadLinePossibleHandling();
        
        //1.16 is出来るまで注文単位(FeqOrderUnit)
        //訂正対象の注文が出来るまで注文かどうかを判定する。 
        //[引数] 
        //注文単位： 注文単位オブジェクト 
        boolean l_blnCarriedOrderUnit = 
            l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnit);
        
        Date l_datOrderUntilDeadLineStartDay = null;
        Date l_datOrderUntilDeadLineEndDay = null;
        Date[] l_datExpirationDateHolidays = null; 
            
        log.debug("is出来るまで注文取扱可能()の戻り値 == " + l_blnPossHanding);
        log.debug("is出来るまで注文単位()の戻り値 == " + l_blnCarriedOrderUnit);
        
        //1.17 is出来るまで注文取扱可能()の戻り値 == true and
        //     is出来るまで注文単位()の戻り値 == true の場合、実施
        if (l_blnPossHanding && l_blnCarriedOrderUnit)
        {
            //1.17.1 get初回注文の注文単位(FeqOrderUnit)(外国株式注文マネージャ)
            //初回注文の注文単位を取得する。 
            //[引数] 
            //注文単位： 注文単位オブジェクト 
            FeqOrderUnit l_firstOrderUnit = 
                l_feqOrderManager.getFirstOrderUnit(l_feqOrderUnit);
            
            //1.17.2 get出来るまで注文開始日(出来るまで注文from日付 : Date)
            //出来るまで注文が可能な開始日を取得する。 
            //[引数] 
            //出来るまで注文from日付： get初回注文の注文単位()で取得した注文単位.発注日 
            FeqOrderUnitParams l_firstOrderUnitParams = 
                new FeqOrderUnitParams(
                    (FeqOrderUnitRow) l_firstOrderUnit.getDataSourceObject());
            
            log.debug("get初回注文の注文単位() = " + l_firstOrderUnitParams);
            
            l_datOrderUntilDeadLineStartDay = 
                l_handingOrderCond.getOrderUntilDeadLineStartDay(
                    WEB3DateUtility.getDate(
                        l_firstOrderUnitParams.getBizDate(), "yyyyMMdd"));
                
            log.debug("get出来るまで注文開始日 = " + l_datOrderUntilDeadLineStartDay);
            
            //1.17.3 get出来るまで注文最終日(出来るまで注文from日付 : Date)
            //出来るまで注文が可能な最終日を取得する。 
            //[引数] 
            //出来るまで注文from日付： get初回注文の注文単位()で取得した注文単位.発注日 
            l_datOrderUntilDeadLineEndDay = 
                l_handingOrderCond.getOrderUntilDeadLineEndDay(
                    WEB3DateUtility.getDate(
                        l_firstOrderUnitParams.getBizDate(), "yyyyMMdd"));
            
            log.debug("get出来るまで注文最終日 = " + l_datOrderUntilDeadLineEndDay);
            
            //1.17.4 get注文期限内祝日一覧(出来るまで注文from日付 : Date)
            //出来るまで注文の有効期限内の祝日の一覧を取得する。 
            //[引数] 
            //出来るまで注文from日付： get初回注文の注文単位()で取得した注文単位.発注日
            l_datExpirationDateHolidays = 
                l_handingOrderCond.getExpirationDateHoliday(
                    WEB3DateUtility.getDate(
                        l_firstOrderUnitParams.getBizDate(), "yyyyMMdd"));
            
            if (l_datExpirationDateHolidays != null)
            {
                for (int i = 0; i < l_datExpirationDateHolidays.length; i++)
                {
                    log.debug("get注文期限内祝日一覧 = " + l_datExpirationDateHolidays[i]);
                }                
            }
        }
        //1.18  is市場開局時間帯( )
        boolean l_blnIsTradeOpenTimeZone = 
            WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        
        //1.19 is市場開局時間帯()の戻り値 == false and
        //注文単位.市場から確認済みの数量 != NaN の場合、実施
        
        if (!l_blnIsTradeOpenTimeZone && !Double.isNaN(
            l_feqOrderUnit.getConfirmedQuantity()))
        {
            log.debug("is市場開局時間帯()の戻り値 == false and " + 
                    "注文単位.市場から確認済みの数量 != NaN の場合");
            //1.19.1 validate閉局後訂正取消受付可能(銘柄タイプ : ProductTypeEnum)
            //閉局後に注文の訂正／取消の受付が可能かどうか判定する。  
            //[引数] 
            //銘柄タイプ： ”外国株式” 
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                ProductTypeEnum.FOREIGN_EQUITY);
        }
        
        //1.20 get表示用時価情報(外国株式取引銘柄, 補助口座)
        //画面表示用の時価情報を取得する。 
        //[引数] 
        //取引銘柄： 外国株式取引銘柄オブジェクト 
        //補助口座： 補助口座オブジェクト 
        WEB3FeqTradedProduct l_feqTradedProduct = 
            l_feqProduct.getFeqTradedProduct();
        
        WEB3FeqProductQuote l_feqProductQuote =
            l_feqProductManager.getIndicationCurrentPriceUnit(
                l_feqTradedProduct, 
                l_subAccount);
        
        //1.29 レスポンスデータを生成する。
        WEB3FeqChangeInputResponse l_response = 
            (WEB3FeqChangeInputResponse) l_request.createResponse();
        
        if (l_feqProductQuote != null)
        {        
            //1.21 get時価取得区分( )(外国株式時価情報::get時価取得区分)
            //時価取得区分を取得する。 
            String l_strCurrentPriceGetDiv = 
                l_feqProductQuote.getCurrentPriceGetDiv();
            
            //1.22 時価を取得する。 
            double l_dblCurrentPrice = l_feqProductQuote.getCurrentPrice();
            
            //1.23 前日比を取得する。
            double l_dblComparedPreviousDay = 
                l_feqProductQuote.getComparedPreviousDay();
            
            //1.24 時価発表時間を取得する。 
            Date l_datCurrentPricePublicTime = 
                l_feqProductQuote.getCurrentPricePublicTime();
            
            //時価取得区分： get時価取得区分()の戻り値
            l_response.currentPriceGetDiv = l_strCurrentPriceGetDiv;
            
            //時価： get時価()の戻り値
            l_response.currentPrice = 
                WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
            
            //前日比： get前日比()の戻り値
            l_response.comparedPreviousDay = 
                WEB3StringTypeUtility.formatNumber(l_dblComparedPreviousDay);
            
            //取引時間： get時価発表時間()の戻り値
            l_response.currentPriceTime = l_datCurrentPricePublicTime;
        }
        //1.25 get執行条件() SONARの執行条件を取得する。 
        //執行条件： 注文単位.執行条件 
        String l_strExecutionConditionSonar =    
            WEB3FeqOrderUtility.getExecutionConditionTypeSonar(                          
                l_feqOrderUnitParams.getExecutionConditionType().intValue() + "");        
            
        double l_dblOtherTradingPower = 0.0D;
        BigDecimal l_bdCalcForeignCCYAmount = new BigDecimal("0");
        
        //1.26 (*1)注文単位.is買付()の戻り値 == true の場合
        if (l_blnIsBuy)
        {
            log.debug("注文単位.is買付()の戻り値 == true の場合");
            
            //1.26.1 getその他商品買付可能額(補助口座 : 補助口座, 受渡日 : Date)
            //買付可能額を取得する。 
            //[引数] 
            //補助口座： 補助口座オブジェクト 
            //受渡日： 取引銘柄.get受渡日()の戻り値
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            
            l_dblOtherTradingPower =
                l_tpTradingPowerService.getOtherTradingPower(
                    l_subAccount, 
                    l_feqTradedProduct.getDailyDeliveryDate());
        }
        //1.27 (*2)注文単位.is買付()の戻り値 == false の場合
        else
        {
            log.debug("注文単位.is買付()の戻り値 == false の場合");
            
            //1.27.1 calc概算簿価単価(補助口座, long, TaxTypeEnum)
            //概算簿価単価を計算する。 
            //[引数] 
            //補助口座： 補助口座オブジェクト 
            //銘柄ID： 注文単位.銘柄ID 
            //税区分： 注文単位.税区分 
            WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
                (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
            BigDecimal l_bdEstimatedBookValuePrice = 
                l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                    l_subAccount, 
                    l_feqOrderUnitParams.getProductId(), 
                    l_feqOrderUnitParams.getTaxType());
            
            //1.27.2 通貨オブジェクトを取得する。
            WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
                        
            //1.27.3 calc外貨換算(BigDecimal, double, int, String)
            //概算簿価単価を外貨換算する。 
            //[引数] 
            //金額（円貨）： calc概算簿価単価()の戻り値 
            //レート： 通貨.get売付基準為替レート()の戻り値 
            //小数部桁数： 通貨.get小数部桁数()の戻り値 
            //外貨換算丸め方式： 通貨.get外貨換算丸め方式()の戻り値
            l_bdCalcForeignCCYAmount = 
                l_feqBizLogicProvider.calcForeignCCYAmount(
                    l_bdEstimatedBookValuePrice,
                    l_currency.getSellBaseRate(), 
                    l_currency.getScale(), 
                    l_currency.getChangeFCcyRoundDiv());
            log.debug("calc外貨換算の戻り値 = " + l_bdCalcForeignCCYAmount);
        }
        
        //1.28 get市場閉局警告外株市場(部店 : 部店)
        //閉局間近の市場コードの一覧を取得する。 
        //[引数] 
        //部店：　@補助口座.get取引店()
        String[] l_strTradeCloseFeqMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch());
                      
        //1.30  (*)プロパティセット       
        //(*3) 以下のとおりに、プロパティをセットする。

        //注文単価区分一覧： ”成行”と”指値”の区分の配列
        String[] l_strPriceDivs = {
            WEB3OrderPriceDivDef.MARKET_PRICE, 
            WEB3OrderPriceDivDef.LIMIT_PRICE}; 
        
        l_response.orderPriceDivList = l_strPriceDivs;
        
        //執行条件一覧： 取扱可能執行条件取得()の戻り値
        l_response.execCondList = l_strHandingOrderCond;
        
        //注文期限区分一覧： null
        l_response.expirationDateTypeList = null;
            
        //有効期限開始日： get出来るまで注文開始日()の戻り値
        l_response.expirationStartDate = l_datOrderUntilDeadLineStartDay;
        
        //有効期限終了日： get出来るまで注文最終日()の戻り値
        l_response.expirationEndDate = l_datOrderUntilDeadLineEndDay;
        
        //有効期限内祝日一覧： get有効期限内祝日一覧()の戻り値
        l_response.holidayList = l_datExpirationDateHolidays;
        
        //発注条件一覧： null
        l_response.orderCondTypeList = null;        
        
        //取引終了警告市場コード一覧： get市場閉局警告外株市場()の戻り値
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;
            
        //市場コード： 外国株式銘柄.get市場コード()の戻り値
        l_response.marketCode = l_feqProduct.getMarketCode();
            
        //銘柄コード： 外国株式銘柄.getProductCode()の戻り値
        l_response.productCode = l_feqProduct.getProductCode();
        
        //現地銘柄コード： 外国株式銘柄.get現地銘柄コード()の戻り値
        l_response.localProductCode = l_feqProduct.getOffshoreProductCode();
        
        //銘柄名： 外国株式銘柄.get表示銘柄名()の戻り値
        l_response.productName = l_feqProduct.getDisplayProductName();
        
        //特定口座区分：（以下のとおり） 
        //      注文単位.税区分 == "一般"の場合、"一般"をセット。
        //      注文単位.税区分 == "特定"の場合、"特定"をセット。
        
        if (TaxTypeEnum.NORMAL.equals(l_feqOrderUnitParams.getTaxType()))
        {
            l_response.taxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitParams.getTaxType()))
        {
            l_response.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }        
        
        //売買区分： （以下のとおり）
        //   注文単位.注文種別 == ”外株買い” の場合、”買付”
        //   注文単位.注文種別 == ”外株売り” の場合、”売付”
        String l_strDealingType = null;
        OrderTypeEnum l_orderType = l_feqOrderUnitParams.getOrderType();
        
        if (OrderTypeEnum.FEQ_BUY.equals(l_orderType))
        {
            log.debug("注文単位.注文種別 == ”外株買い” の場合");
            l_strDealingType = WEB3BuySellTypeDef.BUY;
        }
        else if (OrderTypeEnum.FEQ_SELL.equals(l_orderType))
        {
            log.debug("注文単位.注文種別 == ”外株売り” の場合");
            l_strDealingType = WEB3BuySellTypeDef.SELL;
        }
        l_response.dealingType = l_strDealingType;        
        
        //決済区分： 注文単位.決済区分
        l_response.settleDiv = l_feqOrderUnitParams.getSettleDiv();
        
        //注文数量： 注文単位.注文数量
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitParams.getQuantity());
        
        //内出来数量： 注文単位.約定数量
        l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitParams.getExecutedQuantity());
        
        //注文単価区分： （以下のとおり）
        //   注文単位.isMarketOrder()の戻り値 == true の場合、”成行”
        //   注文単位.isMarketOrder()の戻り値 == false の場合、”指値”
        String l_strOrderPriceDiv = null;
        if (l_feqOrderUnit.isMarketOrder())
        {
            log.debug("注文単位.isMarketOrder()の戻り値 == true の場合");
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            log.debug("注文単位.isMarketOrder()の戻り値 == false の場合");
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        l_response.orderPriceDiv = l_strOrderPriceDiv;
        
        //注文単価： 注文単位.指値
        l_response.limitPrice = WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitParams.getLimitPrice());
        
        //通貨コード: 外国株式銘柄.get通貨コード()の戻り値
        l_response.currencyCode = l_feqProduct.getCurrencyCode();
        
        //執行条件： get執行条件（SONAR）()の戻り値
        l_response.execCondType = l_strExecutionConditionSonar;
        
        //注文期限区分： （以下のとおり）
        //   is出来るまで注文単位()の戻り値 == true の場合、”出来るまで注文”
        //   is出来るまで注文単位()の戻り値 == false の場合、”当日限り”
        String l_strCarriedOrderUnit = null;
        if (l_blnCarriedOrderUnit)
        {
            log.debug("is出来るまで注文単位()の戻り値 == true の場合");
            l_strCarriedOrderUnit = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }
        else
        {
            log.debug("is出来るまで注文単位()の戻り値 == false の場合");
            l_strCarriedOrderUnit = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }
        l_response.expirationDateType = l_strCarriedOrderUnit;
        
        //注文有効期限： （以下のとおり）
        //   is出来るまで注文単位()の戻り値 == true の場合、注文単位.注文失効日
        //   is出来るまで注文単位()の戻り値 == false の場合、null
        if (l_blnCarriedOrderUnit)
        {
            log.debug("is出来るまで注文単位()の戻り値 == true の場合");
            l_response.expirationDate = WEB3DateUtility.toDay(
                l_feqOrderUnitParams.getExpirationDate());
        }
        else
        {
            log.debug("is出来るまで注文単位()の戻り値 == false の場合");
            l_response.expirationDate = null;
        }
        
        //発注条件： 注文単位.発注条件
        l_response.orderCondType = l_feqOrderUnitParams.getOrderConditionType();
        
        //概算受渡代金： 注文単位.概算受渡代金
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitParams.getEstimatedPrice());
                
        //(*A)発注条件 == ”逆指値” の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
            l_feqOrderUnitParams.getOrderConditionType()))
        {
            //逆指値用発注条件単価： 注文単位.逆指値基準値
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getStopOrderPrice());
            
            //逆指値用発注条件演算子： 注文単位.発注条件演算子
            l_response.stopOrderCondOperator = 
                l_feqOrderUnitParams.getOrderCondOperator();
        }        
        //(*B)発注条件 == ”W指値” の場合
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
            l_feqOrderUnitParams.getOrderConditionType()))
        {
            //W指値用発注条件単価： 注文単位.逆指値基準値
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getStopOrderPrice());
            
            //W指値用発注条件演算子： 注文単位.発注条件演算子
            l_response.wlimitOrderCondOperator = 
                l_feqOrderUnitParams.getOrderCondOperator();
            
            //W指値用注文単価区分： （以下のとおり）
            //   注文単位.（W指値）訂正指値 == 0 の場合、”成行”
            //   注文単位.（W指値）訂正指値 != 0 の場合、”指値”
            //W指値用注文単価： （以下のとおり）
            //   注文単位.（W指値）訂正指値 == 0 の場合、null
            //   注文単位.（W指値）訂正指値 != 0 の場合、注文単位.（W指値）訂正指値
            String l_strWLimitOrderPriceDiv = null;
            String l_strWLimitPrice = null;
            if (l_feqOrderUnitParams.getWLimitPrice() == 0)
            {
                l_strWLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_strWLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_strWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_feqOrderUnitParams.getWLimitPrice());
            }
            l_response.wLimitOrderPriceDiv = l_strWLimitOrderPriceDiv;
            l_response.wLimitPrice = l_strWLimitPrice;
        }
        
        //(*C)注文単位.is買付()の戻り値 == true の場合
        //買付可能額： getその他買付可能額()の戻り値
        if (l_blnIsBuy)
        {
            l_response.tradingPower = WEB3StringTypeUtility.formatNumber(
                l_dblOtherTradingPower);
        }        
        //(*D)注文単位.is買付()の戻り値 == false の場合
        //概算簿価単価： calc外貨換算()の戻り値
        else
        {
            l_response.estimatedBookPrice = WEB3StringTypeUtility.formatNumber(
                l_bdCalcForeignCCYAmount.doubleValue());
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 訂正注文の確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株訂正）validate注文」 参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@外株訂正」(外株訂正）validate注文)<BR>
     * 　@　@:   1.17.2 validate取引余力<BR> 
     * 　@　@戻り値の取引余力結果.判定フラグ == false の場合、<BR>
     * 　@　@例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3FeqChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4295F1E102DD
     */
    protected WEB3FeqChangeConfirmResponse validateOrder(
        WEB3FeqChangeConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 補助口座オブジェクトを取得する。
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.3 get代理入力者( )
        //代理入力者オブジェクトを取得する。 
        Trader l_trader = this.getTrader();

        //1.4 get注文単位ByOrderId(long)
        //注文単位オブジェクトを取得する。 
        //[引数] 
        //注文ID： リクエスト.注文ID 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        FeqOrderUnit l_feqOrderUnit = l_feqOrderManager.getOrderUnitByOrderId(
            Long.parseLong(l_request.orderId));        
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();        

        WEB3FeqProduct l_feqProduct = null;
        try
        {
            //1.5 get外国株式銘柄(long)
            //外国株式銘柄オブジェクトを取得する。 
            //[引数] 
            //銘柄ID： 注文単位.銘柄ID 
            l_feqProduct =
                (WEB3FeqProduct) l_feqProductManager.getProduct(
                    l_feqOrderUnitParams.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when "
                + "l_feqProductManager.getProduct(ProductId) with "
                + "ProductId = "
                + l_feqOrderUnitParams.getProductId());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_feqProduct == null)
        {
            log.debug("Error in 外国株式銘柄オブジェクトを取得する");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6 get発注日( )
        //発注日を取得する。
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.7 外国株式訂正注文内容インスタンスを生成する。 
        //[引数] 
        //注文ID： 注文単位.注文ID 
        //注文単位ID： 注文単位.注文単位ID 
        //訂正数量： リクエスト.注文数量 
        //訂正単価： リクエスト.注文単価 
        WEB3FeqChangeOrderSpec l_feqChangeOrderSpec = 
            new WEB3FeqChangeOrderSpec(
                l_feqOrderUnitParams.getOrderId(), 
                l_feqOrderUnitParams.getOrderUnitId(), 
                Double.parseDouble(l_request.orderQuantity), 
                Double.parseDouble(l_request.limitPrice));
        
        //1.8 (*1) 以下のとおりに、プロパティをセットする。
        
        //発注日： get発注日()の戻り値
        l_feqChangeOrderSpec.setBizDate(l_datBizDate);
        
        //訂正執行条件： リクエスト.執行条件
        FeqExecutionConditionType l_feqExecutionConditionType = 
            WEB3FeqOrderUtility.getExecutionCondition(l_request.execCondType);
        
        l_feqChangeOrderSpec.setChangeExecutionCondition(
            l_feqExecutionConditionType);
        
        //訂正注文有効期限： リクエスト.注文有効期限
        //※リクエスト.注文有効期限==nullの場合は、get発注日()の戻り値
        Date l_datExpirationDate = null; 
        if (l_request.expirationDate == null)
        {
            l_datExpirationDate = l_datBizDate;
        }
        else
        {
            l_datExpirationDate = l_request.expirationDate;
        }
        l_feqChangeOrderSpec.setChangeOrderExpirationDate(
            l_datExpirationDate);
        
        //訂正注文期限区分： リクエスト.注文期限区分
        l_feqChangeOrderSpec.setChangeOrderExpirationDiv(
            l_request.expirationDateType);
        
        //発注条件： リクエスト.発注条件
        l_feqChangeOrderSpec.setOrderConditionType(l_request.orderCondType);
        
        //１）発注条件 == ”逆指値” の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
            l_request.orderCondType))
        {
            log.debug("発注条件 == ”逆指値” の場合");
            
            //訂正発注条件演算子： リクエスト.逆指値用発注条件演算子
            l_feqChangeOrderSpec.setChangeOrderCondOperator(
                l_request.stopOrderCondOperator);
            
            log.debug("外国株式訂正注文内容.訂正発注条件演算子： = " + 
                l_request.stopOrderCondOperator);
            
            //訂正発注条件単価： リクエスト.逆指値用発注条件単価
            l_feqChangeOrderSpec.setChangeOrderCondPrice(
                Double.parseDouble(l_request.stopOrderCondPrice));
            
            log.debug("外国株式訂正注文内容.訂正発注条件単価： = " + 
                l_request.stopOrderCondPrice);
        }        
        //２）発注条件 == ”W指値” の場合
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                l_request.orderCondType))
        {
            log.debug("発注条件 == ”W指値” の場合");
            
            //訂正発注条件演算子： リクエスト.W指値用発注条件演算子
            l_feqChangeOrderSpec.setChangeOrderCondOperator(
                l_request.wlimitOrderCondOperator);
            
            log.debug("外国株式訂正注文内容.訂正発注条件演算子： = " + 
                l_request.wlimitOrderCondOperator);
            
            //訂正発注条件単価： リクエスト.W指値用発注条件単価
            l_feqChangeOrderSpec.setChangeOrderCondPrice(
                Double.parseDouble(l_request.wlimitOrderCondPrice));
            
            log.debug("外国株式訂正注文内容.訂正発注条件単価： = " + 
                l_request.wlimitOrderCondPrice);
            
            //訂正（W指値）訂正指値： リクエスト.W指値用注文単価
            double l_dblWlimitPrice = 0.0D;
            if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
            {
                l_dblWlimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }
            l_feqChangeOrderSpec.setChangeWLimitPrice(l_dblWlimitPrice);
            
            log.debug("外国株式訂正注文内容.訂正（W指値）訂正指値： = " + 
                l_request.wLimitPrice);
        }
        
        //1.9 訂正注文の発注審査を行う。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //注文内容： 外国株式訂正注文内容 
        OrderValidationResult l_orderValidationResult = 
        l_feqOrderManager.validateChangeOrder(
            l_subAccount,
            l_feqChangeOrderSpec);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("発注審査チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注審査チェックエラー");
        }
        
        //1.10 get計算用注文単価(外国株式取引銘柄, String, double, double, boolean)
        //計算用注文単価を取得する。 
        //[引数] 
        //取引銘柄： 外国株式銘柄.get取引銘柄()の戻り値 
        //部店： 補助口座.get取引店()の戻り値 
        //注文単価区分： リクエスト.注文単価区分 
        //注文単価： リクエスト.注文単価 
        //訂正単価： リクエスト.W指値用注文単価 
        //is買付： 注文単位.is買付()の戻り値 
        WEB3FeqOrderUnit l_web3FeqOrderUnit = (WEB3FeqOrderUnit) l_feqOrderUnit;
        boolean l_blnIsBuy = l_web3FeqOrderUnit.isBuy();
        
        double l_dblLimitPrice = 0.0D;
        double l_dblWlimitPrice = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_request.limitPrice))
        {
            l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
        }
        if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
        {
            l_dblWlimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        
        double l_dblUnitPrice = 
            l_feqOrderManager.getUnitPrice(
                l_feqProduct.getFeqTradedProduct(), 
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(), 
                l_request.orderPriceDiv, 
                l_dblLimitPrice, 
                l_dblWlimitPrice, 
                l_blnIsBuy);
        
        //1.11 calc売買代金(double, double) 売買代金を計算する。 
        //[引数] 
        //株数： リクエスト.注文数量 - 注文単位.約定数量 
        //単価： get計算用注文単価()の戻り値 
        WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
       
        double l_lngQuantity = 
            Double.parseDouble(l_request.orderQuantity) - 
            l_feqOrderUnitParams.getExecutedQuantity();
                    
        double l_dblExecutionAmount = 
            l_feqBizLogicProvider.calcExecutionAmount(
                l_lngQuantity, 
                l_dblUnitPrice);
        
        //1.12 市場オブジェクトを取得する。
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.13 通貨オブジェクトを取得する。
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
        
        //calc売買代金(double, double)の結果を、当該通貨の小数点桁数で丸める
        BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblExecutionAmount);
        int l_intDecimalPlace = l_currency.getScale();
        l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblExecutionAmount = l_bdTradePriceFc.doubleValue();
        log.debug("売買代金の丸めた結果 ＝ "+ l_dblExecutionAmount);
        
        //1.14 get為替レート(boolean, boolean, double)(通貨::get為替レート)
        //基準為替レートを取得する。 
        //[引数] 
        //is買付： 注文単位.is買付()の戻り値 
        //is約定計算： false 
        //入力為替レート： 0
        double l_dblFxRate = l_currency.getExchangeRate(
            l_blnIsBuy, false, 0);
        
        //1.15 calc訂正外国株式金額() 訂正後の各種金額の計算を行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //外国株式銘柄： get外国株式銘柄()の戻り値 
        //市場： get市場()の戻り値 
        //基準日： get発注日()の戻り値 
        //売買代金（外貨）： calc売買代金()の戻り値 
        //為替レート： get為替レート()の戻り値 
        //is買付： 注文単位.is買付()の戻り値 
        //is約定計算： false 
        //is指値： （以下のとおり） 
        //   リクエスト.注文単価区分 == ”指値” の場合、true 
        //   リクエスト.注文単価区分 == ”成行” の場合、false 
        //注文単位： 注文単位オブジェクト 
        boolean l_blnIsLimitPrice = false;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
        {
            l_blnIsLimitPrice = true;
        }
        else if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(
            l_request.orderPriceDiv)) 
        {
            l_blnIsLimitPrice = false;
        }        
        WEB3FeqAmountCalcResult l_feqAmountCalcResult = 
            l_feqBizLogicProvider.calcChangeFeqAmount(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_feqProduct, 
                l_market, 
                l_datBizDate, 
                l_dblExecutionAmount, 
                l_dblFxRate, 
                l_blnIsBuy, 
                false,
                l_blnIsLimitPrice,
                l_feqOrderUnit);
        
        BigDecimal l_dbForeignCCYAmount = new BigDecimal("0");   //概算簿価単価を外貨換算

        //1.16.1 外国株式訂正更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //注文内容： 外国株式訂正注文内容オブジェクト 
        //計算結果： 外国株式金額計算結果オブジェクト
        //計算単価： get計算用注文単価()の戻り値 
        //代理入力者： get代理入力者()の戻り値 
        
        WEB3FeqChangeUpdateInterceptor l_feqChangeUpdateInterceptor = 
            new WEB3FeqChangeUpdateInterceptor(
                l_feqChangeOrderSpec, 
                l_feqAmountCalcResult,
                l_dblUnitPrice, 
                l_trader);

        // is日計り取引採用( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
        boolean l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();

        //is日計り市場( )
        boolean l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

        //注文単位.is買付()の戻り値 == true または　@(注文単位.is買付()の戻り値 ==false
        //且つ is日計り取引採用()＝true　@且つ　@is日計り市場()　@＝　@true)の場合
        if (l_blnIsBuy || (!l_blnIsBuy && l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket))
        {
            log.debug("注文単位.is買付()の戻り値 == true または　@(注文単位.is買付()の戻り値 ==false" +
                "　@且つ is日計り取引採用()＝true　@且つ　@is日計り市場()　@＝　@true)の場合");
            //1.16.2 取引余力のチェックを行う。 
            //[引数] 
            //補助口座： get補助口座()の戻り値 
            //注文内容インタセプタ： 外国株式訂正更新インタセプタを要素とした配列 
            //注文内容： 外国株式訂正注文内容を要素とした配列 
            //注文種別： 注文単位.注文種別 
            //余力更新フラグ： false 
            
            //注文内容インタセプタの配列            
            WEB3FeqChangeUpdateInterceptor[] l_feqChangeUpdateInterceptors = 
                {l_feqChangeUpdateInterceptor};
                
            //注文内容の配列
            WEB3FeqChangeOrderSpec[] l_feqChangeOrderSpecs = 
                {l_feqChangeOrderSpec}; 
            
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            
            WEB3TPTradingPowerResult l_tPTradingPowerResult = 
                l_tpTradingPowerService.validateTradingPower(
                    (WEB3GentradeSubAccount)l_subAccount, 
                    l_feqChangeUpdateInterceptors, 
                    l_feqChangeOrderSpecs, 
                    l_feqOrderUnitParams.getOrderType(), 
                    false);
            
            //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローする。
            log.debug("戻り値の取引余力結果.判定フラグ == " + l_tPTradingPowerResult.isResultFlg());
            
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                log.debug("取引余力チェックエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引余力チェックエラー。");
            }
        }        
        //1.17 (*3) 注文単位.is買付()の戻り値 == false の場合
        else
        {
            log.debug("注文単位.is買付()の戻り値 == false の場合");
            
            //1.17.1 calc概算簿価単価() 概算簿価単価を計算する。 
            //[引数] 
            //補助口座： 補助口座オブジェクト 
            //銘柄ID： 注文単位.銘柄ID 
            //税区分： 注文単位.税区分 
            BigDecimal l_bdEstimatedBookValuePrice = 
                l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                    (WEB3GentradeSubAccount)l_subAccount, 
                    l_feqOrderUnitParams.getProductId(),
                    l_feqOrderUnitParams.getTaxType());
            
            //1.17.2  calc外貨換算(double, double, int, String)
            //概算簿価単価を外貨換算する。 
            //[引数] 
            //金額（円貨）： calc概算簿価単価()の戻り値 
            //レート： get為替レート()の戻り値 
            //小数部桁数： 通貨.get小数部桁数()の戻り値 
            //外貨換算丸め方式： 通貨.get外貨換算丸め方式()の戻り値 
            l_dbForeignCCYAmount = 
                l_feqBizLogicProvider.calcForeignCCYAmount(
                    l_bdEstimatedBookValuePrice, 
                    l_dblFxRate, 
                    l_currency.getScale(),
                    l_currency.getChangeFCcyRoundDiv());
        }
        
        //1.18 get市場閉局警告外株市場(部店 : 部店)
        //市場閉局警告市場を取得する。 
        //[引数] 
        //部店： 補助口座.get取引店()の戻り値 
        String[] l_strTradeCloseFeqMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch());
        
        //1.19 レスポンスデータを生成する。         
        WEB3FeqChangeConfirmResponse l_response = 
            (WEB3FeqChangeConfirmResponse) l_request.createResponse();
        
        //1.20 (*4)プロパティセット
        //(*4) 以下のとおりに、プロパティをセットする。
        //内出来数量： 注文単位.約定数量
        l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitParams.getExecutedQuantity());
        
        //(*A)注文単位.決済区分 == ”円貨” の場合、円貨換算したもの
        //    注文単位.決済区分 == ”外貨” の場合、外貨換算したもの をセット
        
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(
            l_feqOrderUnitParams.getSettleDiv()))
        {
            log.debug("注文単位.決済区分 == ”円貨” の場合");
            //概算受渡代金： 外国株式金額計算結果.受渡代金(*A)
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
                l_feqAmountCalcResult.getNetAmount());
            
            //手数料： 外国株式金額計算結果.委託手数料(*A)
            l_response.commission = WEB3StringTypeUtility.formatNumber(
                l_feqAmountCalcResult.getCommissionFee());
            
            //手数料消費税： 外国株式金額計算結果.委託手数料消費税(*A)
            l_response.commissionConsumptionTax = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommisionFeeTax());        
        }
        else
        {
            log.debug("注文単位.決済区分 == ”外貨” の場合");
            //概算受渡代金： 外国株式金額計算結果.受渡代金（外貨）(*A)
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
                l_feqAmountCalcResult.getNetAmountFc());
            
            //手数料： 外国株式金額計算結果.委託手数料（外貨）(*A)
            l_response.commission = WEB3StringTypeUtility.formatNumber(
                l_feqAmountCalcResult.getCommissionFeeFc());
            
            //手数料消費税： 外国株式金額計算結果.委託手数料消費税（外貨）(*A)
            l_response.commissionConsumptionTax = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommisionFeeTaxFc());
        }
        //(*B) 注文単位.is買付()の戻り値 == false の場合
        if (!l_blnIsBuy)
        {  
            //概算簿価単価： calc外貨換算()の戻り値
            l_response.estimatedBookPrice = 
                WEB3StringTypeUtility.formatNumber(l_dbForeignCCYAmount.doubleValue());
            
            log.debug("概算簿価単価：= " + l_response.estimatedBookPrice);
        }
        
        //確認時単価： get計算用注文単価()の戻り値
        l_response.checkPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
        
        //確認時発注日： get発注日()の戻り値
        l_response.checkDate = l_datBizDate;            
        
        //取引終了警告市場コード一覧： get市場閉局警告外株市場()の戻り値
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;            
                
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * 訂正注文の更新処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株訂正）submit注文」 参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@外株訂正」(外株訂正）submit注文)<BR>
     * 　@　@:   1.17.1 validate取引余力<BR> 
     * 　@　@戻り値の取引余力結果.判定フラグ == false の場合、<BR>
     * 　@　@例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3FeqChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4295F1E102ED
     */
    protected WEB3FeqChangeCompleteResponse submitOrder(
        WEB3FeqChangeCompleteRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 補助口座オブジェクトを取得する。
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.3 get代理入力者( )
        //代理入力者オブジェクトを取得する。 
        Trader l_trader = this.getTrader();

        //1.4 get注文単位ByOrderId(long)
        //注文単位オブジェクトを取得する。 
        //[引数] 
        //注文ID： リクエスト.注文ID 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        FeqOrderUnit l_feqOrderUnit = l_feqOrderManager.getOrderUnitByOrderId(
            Long.parseLong(l_request.orderId));
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();        

        WEB3FeqProduct l_feqProduct = null;
        try
        {
            //1.5 get外国株式銘柄(long)
            //外国株式銘柄オブジェクトを取得する。 
            //[引数] 
            //銘柄ID： 注文単位.銘柄ID 
            l_feqProduct =
                (WEB3FeqProduct) l_feqProductManager.getProduct(
                    l_feqOrderUnitParams.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_feqProduct == null)
        {
            log.debug("Error in 外国株式銘柄オブジェクトを取得する");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6 get発注日( )
        //発注日を取得する。
        Date l_datBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.7 外国株式訂正注文内容インスタンスを生成する。 
        //[引数] 
        //注文ID： 注文単位.注文ID 
        //注文単位ID： 注文単位.注文単位ID 
        //訂正数量： リクエスト.注文数量 
        //訂正単価： リクエスト.注文単価 
        WEB3FeqChangeOrderSpec l_feqChangeOrderSpec = 
            new WEB3FeqChangeOrderSpec(
                l_feqOrderUnitParams.getOrderId(), 
                l_feqOrderUnitParams.getOrderUnitId(), 
                Double.parseDouble(l_request.orderQuantity), 
                Double.parseDouble(l_request.limitPrice));
        
        //1.8 (*1) 以下のとおりに、プロパティをセットする。
        
        //発注日： get発注日()の戻り値
        l_feqChangeOrderSpec.setBizDate(l_datBizDate);
        
        //訂正執行条件： リクエスト.執行条件

        FeqExecutionConditionType l_feqExecutionConditionType = 
            WEB3FeqOrderUtility.getExecutionCondition(l_request.execCondType);

        l_feqChangeOrderSpec.setChangeExecutionCondition(l_feqExecutionConditionType);
        
        //訂正注文有効期限： リクエスト.注文有効期限
        //※リクエスト.注文有効期限==nullの場合は、get発注日()の戻り値
        Date l_datExpirationDate = null;
        if (l_request.expirationDate == null)
        {
            l_datExpirationDate = l_datBizDate;
        }
        else
        {
            l_datExpirationDate = l_request.expirationDate;
        }
        l_feqChangeOrderSpec.setChangeOrderExpirationDate(l_datExpirationDate);
        
        //訂正注文期限区分： リクエスト.注文期限区分
        l_feqChangeOrderSpec.setChangeOrderExpirationDiv(l_request.expirationDateType);
        
        //発注条件： リクエスト.発注条件
        l_feqChangeOrderSpec.setOrderConditionType(l_request.orderCondType);
       
        //１）発注条件 == ”逆指値” の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
            l_request.orderCondType))
        {
            log.debug("発注条件 == ”逆指値” の場合");
            //訂正発注条件演算子： リクエスト.逆指値用発注条件演算子
            l_feqChangeOrderSpec.setChangeOrderCondOperator(
                l_request.stopOrderCondOperator);
            
            //訂正発注条件単価： リクエスト.逆指値用発注条件単価
            l_feqChangeOrderSpec.setChangeOrderCondPrice(
                Double.parseDouble(l_request.stopOrderCondPrice));
        }        
        //２）発注条件 == ”W指値” の場合
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                l_request.orderCondType))
        {
            log.debug("発注条件 == ”W指値” の場合");
            //訂正発注条件演算子： リクエスト.W指値用発注条件演算子
            l_feqChangeOrderSpec.setChangeOrderCondOperator(
                l_request.wlimitOrderCondOperator);
            
            double l_dblWlimitOrderCondPrice = 0.0D;
            double l_dblWlimitPrice = 0.0D;
            if (!WEB3StringTypeUtility.isEmpty(l_request.wlimitOrderCondPrice))
            {
                l_dblWlimitOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
            if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
            {
                l_dblWlimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }
            
            //訂正発注条件単価： リクエスト.W指値用発注条件単価
            l_feqChangeOrderSpec.setChangeOrderCondPrice(l_dblWlimitOrderCondPrice);
            
            //訂正（W指値）訂正指値： リクエスト.W指値用注文単価
            l_feqChangeOrderSpec.setChangeWLimitPrice(l_dblWlimitPrice);
        }
        
        //1.9 訂正注文の発注審査を行う。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //注文内容： 外国株式訂正注文内容 
        OrderValidationResult l_orderValidationResult = 
            l_feqOrderManager.validateChangeOrder(
                l_subAccount,
                l_feqChangeOrderSpec);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("発注審査チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注審査チェックエラー");
        }
        
        //1.10 calc売買代金(double, double) 売買代金を計算する。 
        //[引数] 
        //株数： 株数： リクエスト.注文数量 - 注文単位.約定数量 
        //単価： リクエスト.確認時単価  
        WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
             
        double l_lngQuantity = 
            Double.parseDouble(l_request.orderQuantity) - 
                l_feqOrderUnitParams.getExecutedQuantity();
        
        double l_dblExecutionAmount = 
            l_feqBizLogicProvider.calcExecutionAmount(
                l_lngQuantity, 
                Double.parseDouble(l_request.checkPrice));
        
        //1.11 市場オブジェクトを取得する。
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.12 通貨オブジェクトを取得する。
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
        
        //calc売買代金(double, double)の結果を、当該通貨の小数点桁数で丸める
        BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblExecutionAmount);
        int l_intDecimalPlace = l_currency.getScale();
        l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblExecutionAmount = l_bdTradePriceFc.doubleValue();
        log.debug("売買代金の丸めた結果 ＝ "+ l_dblExecutionAmount);
        
        //1.13 get為替レート(boolean, boolean, double)(通貨::get為替レート)
        //基準為替レートを取得する。 
        //[引数] 
        //is買付： 注文単位.is買付()の戻り値 
        //is約定計算： false 
        //入力為替レート： 0 

        WEB3FeqOrderUnit l_web3FeqOrderUnit = (WEB3FeqOrderUnit) l_feqOrderUnit;
        boolean l_blnIsBuy = l_web3FeqOrderUnit.isBuy();
        
        double l_dblFxRate = l_currency.getExchangeRate(
            l_blnIsBuy, 
            false,
            0);
        
        //1.14 calc訂正外国株式金額() 訂正後の各種金額の計算を行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //外国株式銘柄： get外国株式銘柄()の戻り値 
        //市場： get市場()の戻り値 
        //基準日： get発注日()の戻り値 
        //売買代金（外貨）： calc売買代金()の戻り値 
        //為替レート： get為替レート()の戻り値 
        //is買付： 注文単位.is買付()の戻り値 
        //is約定計算： false 
        //is指値： （以下のとおり） 
        //   リクエスト.注文単価区分 == ”指値” の場合、true 
        //   リクエスト.注文単価区分 == ”成行” の場合、false 
        //注文単位： 注文単位オブジェクト 
        boolean l_blnIsLimitPrice = false;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
        {
            l_blnIsLimitPrice = true;
        }
        else if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv)) 
        {
            l_blnIsLimitPrice = false;
        }
        
        WEB3FeqAmountCalcResult l_feqAmountCalcResult = 
            l_feqBizLogicProvider.calcChangeFeqAmount(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_feqProduct, 
                l_market, 
                l_datBizDate, 
                l_dblExecutionAmount, 
                l_dblFxRate, 
                l_blnIsBuy, 
                false,
                l_blnIsLimitPrice,
                l_feqOrderUnit);        
        
        //1.15 外国株式訂正更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //注文内容： 外国株式訂正注文内容オブジェクト 
        //計算結果： 外国株式金額計算結果オブジェクト
        //計算単価： リクエスト.確認時単価  
        //代理入力者： get代理入力者()の戻り値 
        
        WEB3FeqChangeUpdateInterceptor l_feqChangeUpdateInterceptor = 
            new WEB3FeqChangeUpdateInterceptor(
                l_feqChangeOrderSpec, 
                l_feqAmountCalcResult, 
                Double.parseDouble(l_request.checkPrice), 
                l_trader);

        // is日計り取引採用( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
        boolean l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();

        //is日計り市場( )
        boolean l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

        //注文単位.is買付()の戻り値 == true または　@(注文単位.is買付()の戻り値 ==false
        //且つ is日計り取引採用()＝true　@且つ　@is日計り市場()　@＝　@true)の場合
        if (l_blnIsBuy || (!l_blnIsBuy && l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket))
        {
            //1.16.1 取引余力のチェックを行う。 
            //[引数] 
            //補助口座： get補助口座()の戻り値 
            //注文内容インタセプタ： 外国株式訂正更新インタセプタを要素とした配列 
            //注文内容： 外国株式訂正注文内容を要素とした配列 
            //注文種別： 注文単位.注文種別 
            //余力更新フラグ： false 
            
            //注文内容インタセプタの配列            
            WEB3FeqChangeUpdateInterceptor[] l_feqChangeUpdateInterceptors = 
                {l_feqChangeUpdateInterceptor};
                
            //注文内容の配列
            WEB3FeqChangeOrderSpec[] l_feqChangeOrderSpecs = 
                {l_feqChangeOrderSpec}; 
            
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            
            WEB3TPTradingPowerResult l_tPTradingPowerResult = 
                l_tpTradingPowerService.validateTradingPower(
                    (WEB3GentradeSubAccount)l_subAccount, 
                    l_feqChangeUpdateInterceptors, 
                    l_feqChangeOrderSpecs, 
                    l_feqOrderUnitParams.getOrderType(), 
                    false);
            
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
        }
        
        //1.17 setThreadLocalPersistenceEventInterceptor
        l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(
        	l_feqChangeUpdateInterceptor);
        
        //1.18 submitChangeOrder
        OrderSubmissionResult l_orderSubmissionResult = l_feqOrderManager.submitChangeOrder(
            l_subAccount,
        	l_feqChangeOrderSpec,
        	l_request.password,
        	true);
        
        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitChangeOrder" +
                l_orderSubmissionResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }
        
        //1.19 余力再計算
        //余力再計算
        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        l_tradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        
        //1.20 getOrder
        Order l_order = null;
        try
        {
        	l_order = l_feqOrderManager.getOrder(l_feqChangeOrderSpec.getOrderId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.21 レスポンスデータを生成する。  
        
        WEB3FeqChangeCompleteResponse l_response = 
            (WEB3FeqChangeCompleteResponse) l_request.createResponse();
        
        FeqOrderRow l_feqOrderRow = (FeqOrderRow) l_order.getDataSourceObject();
     
        //1.22 (*3) 以下のとおりに、プロパティをセットする。
        //更新時間： 注文.更新日時
        l_response.lastUpdatedTimestamp = l_feqOrderRow.getLastUpdatedTimestamp();
        
        //注文ID： 注文.注文ID
        l_response.orderId = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderRow.getOrderId());
      
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
