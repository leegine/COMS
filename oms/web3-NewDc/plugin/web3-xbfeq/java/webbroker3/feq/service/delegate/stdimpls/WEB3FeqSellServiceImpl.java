head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式売付サービスImpl(WEB3FeqSellServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
Revesion History : 2008/01/21 柴双紅(中訊) モデルNo.381、モデルNo.372
Revesion History : 2010/01/12 張騰宇(中訊) モデルNo.531
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
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
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqProductQuote;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.message.WEB3FeqSellCompleteRequest;
import webbroker3.feq.message.WEB3FeqSellCompleteResponse;
import webbroker3.feq.message.WEB3FeqSellConfirmRequest;
import webbroker3.feq.message.WEB3FeqSellConfirmResponse;
import webbroker3.feq.message.WEB3FeqSellInputRequest;
import webbroker3.feq.message.WEB3FeqSellInputResponse;
import webbroker3.feq.service.delegate.WEB3FeqSellService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式売付サービスImpl)<BR>
 * 外国株式売付サービス実装クラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqSellServiceImpl extends WEB3FeqClientRequestService 
    implements WEB3FeqSellService 
{
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqSellServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F80186
     */
    public WEB3FeqSellServiceImpl() 
    {
     
    }
        
    /**
     * 外国株式売付サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *    get入力画面()<BR>
     *    validate注文()<BR>
     *    submit注文()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AFE1A0354
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
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FeqSellInputRequest)
        {
            //get入力画面
            l_response =
                this.getInputScreen(
                    (WEB3FeqSellInputRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqSellConfirmRequest)
        {
            //validate注文
            l_response =
                this.validateOrder(
                    (WEB3FeqSellConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqSellCompleteRequest)
        {
            //submit注文
            l_response =
                this.submitOrder(
                    (WEB3FeqSellCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                "リクエストデータが"
                    + " WEB3FeqSellInputRequest "
                    + "と WEB3FeqSellConfirmRequest "
                    + "と WEB3FeqSellCompleteRequest以外である, but is "
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
     * シーケンス図「（外株売付）get入力画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.feq.message.WEB3FeqSellInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AFE1A0364
     */
    protected WEB3FeqSellInputResponse getInputScreen(WEB3FeqSellInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqSellInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1)validate( )
        l_request.validate();
        
        //1.2)get補助口座()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();
        
        //1.3)validate注文(補助口座)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        l_feqOrderManager.validateOrder(l_subAccount);       
        
        //1.4 getAsset(保有資産ID : long)
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        //保有資産        
        AssetRow l_asset = null;
        long l_lngAsstId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
        {
            l_lngAsstId = Long.parseLong(l_request.assetId);
        }
        try
        {
            l_asset = (AssetRow) 
                l_feqPositionManager.getAsset(l_lngAsstId).getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //銘柄ID 
        long l_lngProductId = l_asset.getProductId();
        
        //1.5 get外国株式銘柄(long)
        //外国株式銘柄オブジェクトを取得する。 
        //[引数] 
        //銘柄ID： 保有資産.銘柄ID 
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct =
            (WEB3FeqProduct) l_feqProductManager.getFeqProduct(l_lngProductId);
            
        //市場コード       
        String l_strMarketCode = l_feqProduct.getMarketCode();

        //1.6 get発注日( )                
        WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
        //1.7 get市場( )
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.8 validate外株銘柄(証券会社, String)
        //[引数] 
        //証券会社： 補助口座.getInstitutuin()の戻り値 
        //銘柄コード： 外国株式銘柄.銘柄コード 
        
        //証券会社
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution) l_subAccount.getInstitution();  
        l_feqProduct = 
            (WEB3FeqProduct) l_feqOrderManager.validateFeqProduct(
                l_institution, 
                l_feqProduct.getProductCode());
       
        //1.9 validate市場(市場)
        l_feqOrderManager.validateMarket(l_market);   

        //1.10 validate取引銘柄(外国株式銘柄, 市場, boolean)
        //取引銘柄のチェックを行う。 
        //[引数] 
        //外国株式銘柄： 外国株式銘柄オブジェクト 
        //市場： get市場()の戻り値 
        //is買注文： false 
        WEB3FeqTradedProduct l_feqTradedProduct =
            (WEB3FeqTradedProduct)l_feqOrderManager.validateTradedProduct(
                l_feqProduct,
                l_market,
                false);
                
        //1.11 validate顧客銘柄別取引停止(SubAccount, long, OrderTypeEnum)
        //顧客銘柄別取引訂正チェックを行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //銘柄ID： 外国株式銘柄.銘柄ID 
        //注文種別： ”外株売り” 
        l_feqOrderManager.validateAccountProductTradedStop(
            l_subAccount, 
            l_feqProduct.getProductId(), 
            OrderTypeEnum.FEQ_SELL);
            
        //1.12 取扱可能注文条件(String, ProductTypeEnum, String, String, String)
        //取扱可能注文条件インスタンスを取得する。 
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //銘柄タイプ： ”外国株式” 
        //先物／オプション区分： ”DEFAULT”(固定) 
        //信用取引区分： ”DEFAULT”(固定) 
        //市場コード： 市場.市場コード 
        String l_stringInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_stringInstitutionCode,
                ProductTypeEnum.FOREIGN_EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_strMarketCode);
                
        //1.13 取扱可能執行条件取得( )
        String[] l_strHandlingPossibleExecConds = 
            l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();
        
        //1.14 取扱可能発注条件取得( )      
        String[] l_strHandlingPossibleOrderConds = 
            l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();
            
        //1.15 取扱可能注文期限区分取得( )
        String[] l_strHandlingPossibleExpirationDateTypes =
            l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();
        
        //1.16 is出来るまで注文取扱可能( )
        boolean l_blnIsOrderUntilHandling =
            l_gentradeHandlingOrderCond.isOrderUntilDeadLinePossibleHandling();
        log.debug("is出来るまで注文取扱可能()の戻り値 ==" 
            + l_blnIsOrderUntilHandling);
            
        //1.17 (*3) is出来るまで注文取扱可能()の戻り値 == true の場合
        
         //出来るまで注文開始日
         Date l_datorderUntilDeadLineStartDay = null;
         //出来るまで注文最終日
         Date l_datOrderUntilDeadLineEndDay = null;       
         //注文期限内祝日一覧
         Date[] l_datExpirationDateHolidays = null;
        
         if (l_blnIsOrderUntilHandling)
         {
             //1.17.1 get出来るまで注文開始日()
             l_datorderUntilDeadLineStartDay = 
                 l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay();
                
             //1.17.2 get出来るまで注文最終日()
             l_datOrderUntilDeadLineEndDay = 
                 l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDay();
             
             //1.17.3 get注文期限内祝日一覧()
             l_datExpirationDateHolidays =
                 l_gentradeHandlingOrderCond.getExpirationDateHoliday();
         }
         
        //1.18 get市場閉局警告外株市場(部店 : 部店) 
        String[] l_strTradeCloseFeqMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                l_subAccount.getWeb3GenBranch());
                
        //1.19 get表示用時価情報(外国株式取引銘柄, 補助口座)
        //[引数] 
        //取引銘柄： 外国株式取引銘柄オブジェクト 
        //補助口座： 補助口座オブジェクト 
        WEB3FeqProductQuote l_feqProductQuote =
            l_feqProductManager.getIndicationCurrentPriceUnit(
                l_feqTradedProduct, l_subAccount);
        
        //時価取得区分
        String l_strCurrentPriceGetDiv = null;
        //時価
        String l_strCurrentPrice = null;
        //前日比
        String l_strComparedPreviousDay = null;
        //時価発表時間
        Date l_DatCurrentPricePublicTime = null;
        boolean l_blnfeqProductQuoteIsNull = true;
        
        if (l_feqProductQuote != null)
        {
            l_blnfeqProductQuoteIsNull = false;
            
            //1.20 get時価取得区分( )
            l_strCurrentPriceGetDiv =
                l_feqProductQuote.getCurrentPriceGetDiv();

            //1.21 get時価( )
            l_strCurrentPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqProductQuote.getCurrentPrice());
            
            //1.22 get前日比( )
            l_strComparedPreviousDay = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqProductQuote.getComparedPreviousDay());
        
            //1.23 get時価発表時間( )
            l_DatCurrentPricePublicTime = 
                l_feqProductQuote.getCurrentPricePublicTime();
        }       
            
        //1.24 calc概算簿価単価(補助口座, long, TaxTypeEnum)    
        //概算簿価単価を計算する。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //銘柄ID： 保有資産.銘柄ID 
        //税区分： 保有資産.税区分 
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        BigDecimal l_bdCalcEstimatedBookValuePrice = 
            l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                l_subAccount,
                l_lngProductId,
                l_asset.getTaxType());
                
        //1.25 get通貨( )        
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
            
        //1.26 calc外貨換算(BigDecimal, double, int, String)
        //概算簿価単価を外貨換算する。 
        //[引数] 
        //金額（円貨）： calc概算簿価単価()の戻り値 
        //レート： 通貨.get売付基準為替レート()の戻り値 
        //小数部桁数： 通貨.get小数部桁数()の戻り値 
        //外貨換算丸め方式： 通貨.get外貨換算丸め方式()の戻り値 
        BigDecimal l_bdCalcForeignCCYAmount = 
            l_feqBizLogicProvider.calcForeignCCYAmount(
                l_bdCalcEstimatedBookValuePrice,
                l_currency.getSellBaseRate(),
                l_currency.getScale(),
                l_currency.getChangeFCcyRoundDiv());
                
        //1.27 createResponse( )
        WEB3FeqSellInputResponse l_response = 
            (WEB3FeqSellInputResponse)l_request.createResponse();
            
        //1.28 (*3)プロパティセット
        //(*3) 以下のとおりに、プロパティをセットする。
        
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
        l_response.expirationStartDate = l_datorderUntilDeadLineStartDay;
        
        //有効期限終了日： get出来るまで注文最終日()の戻り値
        l_response.expirationEndDate = l_datOrderUntilDeadLineEndDay;
        
        //有効期限内祝日一覧： get注文期限内祝日一覧()の戻り値
        l_response.holidayList = l_datExpirationDateHolidays;
        
        //発注条件一覧： 取扱可能発注条件取得()の戻り値
        l_response.orderCondTypeList = l_strHandlingPossibleOrderConds;
        
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
        
        //取引終了警告市場コード一覧： get市場閉局警告市場()の戻り値
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;
        
        //銘柄コード： 外国株式銘柄.get銘柄コード()の戻り値
        l_response.productCode = l_feqProduct.getProductCode();
        
        //現地銘柄コード： 外国株式銘柄.get現地銘柄コード()の戻り値
        l_response.localProductCode = l_feqProduct.getOffshoreProductCode();
        
        //銘柄名： 外国株式銘柄.get表示銘柄名()の戻り値
        l_response.productName = l_feqProduct.getDisplayProductName();
        
        //市場コード： 外国株式銘柄.get市場コード()の戻り値
        l_response.marketCode = l_feqProduct.getMarketCode();
        
        //注文数量： 保有資産.数量() - 保有資産.getLockedQuantity()
        double l_lockedQuantity =
            l_feqPositionManager.getAsset(l_subAccount.getAccountId(), l_subAccount.getSubAccountId(),
                l_lngProductId, l_asset.getTaxType()).getLockedQuantity();
        
        l_response.orderQuantity = 
            WEB3StringTypeUtility.formatNumber(l_asset.getQuantity() - l_lockedQuantity);
                
        //口座区分一覧： （以下のとおり）
        String l_strTaxType = null;
        // 保有資産.税区分＝＝”一般” の場合、 ”一般”
        if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        //保有資産.税区分 ＝”特定” の場合、 ”特定”
        else if (TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }
        l_response.taxType = l_strTaxType;
        
        //通貨コード： 外国株式銘柄.get通貨コード()の戻り値
        l_response.currencyCode = l_feqProduct.getCurrencyCode();
        
        //決済区分一覧： （以下のとおり）
        //外国株式銘柄から取得した通貨の残高を保有している(*)場合、”円貨”と”外貨”の区分の配列
        //外国株式銘柄から取得した通貨の残高を保有してない場合、”円貨”のみの配列
        //(*)外貨の残高保有の判定方法@の詳細は、未定
		//暫定対応として常に円貨をセットするように修正
		String[] l_strSettleDivList = null;
		l_strSettleDivList = new String[1];
		l_strSettleDivList[0] = WEB3InputOutputActionSettlementDivDef.EN_SETTLE;
		l_response.settleDivList = l_strSettleDivList;
        
        //概算簿価単価： calc外貨換算()の戻り値
        l_response.estimatedBookPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdCalcForeignCCYAmount.doubleValue());
            
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 売付注文の確認処理を行う。<BR>
     * <BR>
     * シーケンス図「（外株売付）validate注文」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.feq.message.WEB3FeqSellConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AFE1A0383
     */
    protected WEB3FeqSellConfirmResponse validateOrder(WEB3FeqSellConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqSellConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1)validate( )
        l_request.validate();
        
        //1.2)get補助口座()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();
        
        //1.3 get代理入力者( )
        WEB3GentradeTrader l_trade = (WEB3GentradeTrader) this.getTrader();
        
        //1.4 getAsset(保有資産ID : long)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        //保有資産        
        AssetRow l_asset = null;
        long l_lngAsstId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
        {
            l_lngAsstId = Long.parseLong(l_request.assetId);
        }
        try
        {
            l_asset = (AssetRow) 
                l_feqPositionManager.getAsset(l_lngAsstId).getDataSourceObject();
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
        
        //銘柄ID 
        long l_lngProductId = l_asset.getProductId();
        
        //1.5 get外国株式銘柄(long)
        //外国株式銘柄オブジェクトを取得する。 
        //[引数] 
        //銘柄ID： 保有資産.銘柄ID 
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct =
            (WEB3FeqProduct) l_feqProductManager.getFeqProduct(l_lngProductId);
            
        //市場コード       
        String l_strMarketCode = l_feqProduct.getMarketCode();

        //1.6 get発注日( )                
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //1.7 get執行条件(String)
        //執行条件： リクエスト.執行条件 
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        FeqExecutionConditionType l_feqExecutionCond = 
            l_feqOrderManager.getExecutionCondition(l_request.execCondType);
           
         //1.8 create新規注文内容   
        //新規注文内容を生成する。 
        //[引数] 
        //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 
        //扱者： get代理入力者()の戻り値 
        //is買付注文： false 
        //銘柄コード： 外国株式銘柄.銘柄コード 
        //市場コード：外国株式銘柄.市場コード 
        //注文数量： リクエスト.注文数量 
        //注文単価： リクエスト.注文単価 
        //執行条件： get執行条件()の戻り値 
        //注文失効日： リクエスト.注文有効期限 
        //税区分： 保有資産.税区分 
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
        WEB3FeqNewOrderSpec l_feqNewOrderSpec =
            WEB3FeqNewOrderSpec.createNewOrderSpec(
                l_stringInstitutionCode,
                l_trade,
                false,
                l_feqProduct.getProductCode(),
                l_strMarketCode,
                Double.parseDouble(l_request.orderQuantity),
                l_dblLimitPrice,
                l_feqExecutionCond,
                l_request.expirationDate,
                l_asset.getTaxType(),
                l_feqProduct.getCurrencyCode(),
                l_request.orderCondType,
                l_dblWLimitPrice,
                l_request.settleDiv,
                l_lngFirstOrderUnitId);

        //1.9  validate新規注文(SubAccount, ProductTypeEnum, NewOrderSpec)        
        //新規注文の発注審査を行う。 
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
        
        //1.10 get計算用注文単価(外国株式取引銘柄, 部店, String, double, double, boolean)
        //計算用注文単価を取得する。 
        //[引数] 
        //取引銘柄： 外国株式銘柄.get取引銘柄()の戻り値 
        //部店： 補助口座.get取引店()の戻り値 
        //注文単価区分： リクエスト.注文単価区分 
        //注文単価： リクエスト.注文単価 
        //訂正単価： リクエスト.W指値用注文単価 
        //is買付： false 
        
        //部店
        WEB3GentradeBranch l_branch = 
            (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        double l_dblUnitPrice =
            l_feqOrderManager.getUnitPrice(
                l_feqProduct.getFeqTradedProduct(),
                l_branch,
                l_request.orderPriceDiv,
                l_dblLimitPrice,
                l_dblWLimitPrice,
                false);
                
        //1.11 calc売買代金(double, double)
        //売買代金（外貨）を計算する。 
        //[引数] 
        //株数： リクエスト.注文数量 
        //単価： get計算用注文単価()の戻り値 
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
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

//		calc売買代金(double, double)の結果を、当該通貨の小数点桁数で丸める
		BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblCalcExecutionAmount);
		int l_intDecimalPlace = l_currency.getScale();
		l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
		l_dblCalcExecutionAmount = l_bdTradePriceFc.doubleValue();
		log.debug("売買代金の丸めた結果 ＝ "+ l_dblCalcExecutionAmount);
                
        //1.14 get売付基準為替レート
        double l_dblSellBaseFxRate = l_currency.getSellBaseRate();   

        //1.15 calc外国株式金額(補助口座, 外国株式銘柄, 市場, Date, 
        //      double, double, boolean, boolean, boolean)
        //各種金額の計算を行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //外国株式銘柄： get外国株式銘柄()の戻り値 
        //市場： get市場()の戻り値 
        //基準日： get発注日()の戻り値 
        //売買代金（外貨）： calc売買代金()の戻り値 
        //為替レート： get売付基準為替レート()の戻り値 
        //is買付： false 
        //is約定計算： false 
        //is指値： （以下のとおり） 
        //   リクエスト.注文単価区分 == ”指値” の場合、true 
        //   リクエスト.注文単価区分 == ”成行” の場合、false 
        //注文チャネル：　@this.getログインチャネル()
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
                l_dblSellBaseFxRate,
                false,
                false,
                l_blnOrderPrice,
                this.getLoginChannel());
        
        //1.16 calc概算簿価単価(補助口座, long, TaxTypeEnum)
        //概算簿価単価を計算する。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //銘柄ID： 保有資産.銘柄ID 
        //税区分： 保有資産.税区分 
        BigDecimal l_bdCalcEstimatedBookValuePrice = 
            l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                l_subAccount,
                l_lngProductId,
                l_asset.getTaxType());
                
        //1.17 calc外貨換算(double, double, int, String)
        //概算簿価単価を外貨換算する。 
        //[引数] 
        //金額（円貨）： calc概算簿価単価()の戻り値 
        //レート： get売付基準為替レート()の戻り値 
        //小数部桁数： 通貨.get小数部桁数()の戻り値 
        //外貨換算丸め方式： 通貨.get外貨換算丸め方式()の戻り値 
        BigDecimal l_bdCalcForeignCCYAmount = 
            l_feqBizLogicProvider.calcForeignCCYAmount(
                l_bdCalcEstimatedBookValuePrice,
                l_dblSellBaseFxRate,
                l_currency.getScale(),
                l_currency.getChangeFCcyRoundDiv());
        
        //外国株式注文更新イベントインタセプタ
        //注文内容： 外国株式新規注文内容オブジェクト
        //計算結果： calc外国株式金額()の戻り値
        //計算単価： get計算用注文単価()の戻り値
        //発注条件： リクエスト.発注条件
        //発注条件演算子： （以下のとおり）
        //   リクエスト.発注条件 == ”逆指値” の場合、リクエスト.逆指値用発注条件演算子
        //   リクエスト.発注条件 == ”W指値” の場合、リクエスト.W指値用発注条件演算子
        //発注条件単価： （以下のとおり）
        //   リクエスト.発注条件 == ”逆指値” の場合、リクエスト.逆指値用発注条件単価
        //   リクエスト.発注条件 == ”W指値” の場合、リクエスト.W指値用発注条件単価
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
                l_dblOrderCondPrice =
                    Double.parseDouble(l_request.wlimitOrderCondPrice);
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

        // is日計り取引採用( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
        boolean l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();

        //is日計り市場( )
        boolean l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

        //is日計り取引採用()＝true 且つ　@is日計り市場()＝trueの場合
        if (l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket)
        {
            //validate取引余力
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            //外国株式注文更新インタセプタを要素とした配列 
            WEB3FeqOrderUpdateInterceptor[] l_feqOrderUpdateInterceptors = {l_feqOrderUpdateInterceptor};

            //外国株式新規注文内容を要素とした配列 
            WEB3FeqNewOrderSpec[] l_feqNewOrderSpecs = {l_feqNewOrderSpec}; 

            WEB3TPTradingPowerResult l_tPTradingPowerResult =
                l_tpTradingPowerService.validateTradingPower(
                    l_subAccount,
                    l_feqOrderUpdateInterceptors,
                    l_feqNewOrderSpecs,
                    OrderTypeEnum.FEQ_SELL,
                    false);
            if (l_tPTradingPowerResult == null)
            {
                log.debug("予期しないシステムエラーが発生しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "予期しないシステムエラーが発生しました。");
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
        }

        //1.18 get市場閉局警告外株市場(部店 : 部店)
        //部店： 補助口座.get取引店()の戻り値 
        String[] l_strTradeCloseFeqMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(l_branch);
            
        //1.19 createNewOrderId( )
        //新規の注文IDを取得する。 
        long l_lngNewOrderId = l_feqOrderManager.createNewOrderId();

        //1.20  createResponse( )
        WEB3FeqSellConfirmResponse l_response = 
            (WEB3FeqSellConfirmResponse)l_request.createResponse();
        
        //(*2) 以下のとおりに、プロパティをセットする。
        
        //売付基準為替： get売付基準為替レート()の戻り値
        l_response.sellExchange = 
            WEB3StringTypeUtility.formatNumber(l_dblSellBaseFxRate);
        
        //概算簿価単価： calc外貨換算の戻り値
        l_response.estimatedBookPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdCalcForeignCCYAmount.doubleValue());
            
        //注文ID： createNewOrderId()の戻り値
        l_response.orderId = l_lngNewOrderId + "";
        
        //確認時単価： get計算用注文単価()の戻り値
        l_response.checkPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
        
        //確認時発注日： get発注日()の戻り値
        l_response.checkDate = l_datOrderBizDate;
        
        //取引終了警告市場コード一覧： get市場閉局警告市場()の戻り値
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;

        //(*A)リクエスト.決済区分 == ”円貨” の場合、設定する値は円貨換算されたもの。
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(l_request.settleDiv))
        {
            //概算受渡代金： 外国株式金額計算結果.受渡代金(*A)
            l_response.estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getNetAmount());

            //手数料： 外国株式金額計算結果.委託手数料(*A)
            l_response.commission = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommissionFee());

            //手数料消費税： 外国株式金額計算結果.委託手数料消費税(*A)
            l_response.commissionConsumptionTax = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommisionFeeTax());
        }
        else
        {
            //リクエスト.決済区分 == ”外貨” の場合、設定する値は外貨換算されたもの。
            if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(l_request.settleDiv))
            {
                //概算受渡代金： 外国株式金額計算結果.受渡代金(外貨)
                l_response.estimatedPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getNetAmountFc());
        
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
     * 売付注文の登録処理を行う。<BR>
     * <BR>
     * シーケンス図「（外株売付）submit注文」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.feq.message.WEB3FeqSellCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AFE1A0392
     */
    protected WEB3FeqSellCompleteResponse submitOrder(WEB3FeqSellCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqSellCompleteRequest l_request)";
            
        //1.1) validate( )
        l_request.validate();
        
        //1.2)get補助口座()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        //1.3 get代理入力者( )
        WEB3GentradeTrader l_trade = (WEB3GentradeTrader) this.getTrader(); 
        
        //1.4 getAsset(保有資産ID : long)
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        //保有資産        
        AssetRow l_asset = null;
        long l_lngAsstId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
        {
            l_lngAsstId = Long.parseLong(l_request.assetId);
        }
        try
        {
            l_asset = (AssetRow) 
                l_feqPositionManager.getAsset(l_lngAsstId).getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //銘柄ID 
        long l_lngProductId = l_asset.getProductId();
        
        //1.5 get外国株式銘柄(long)
        //外国株式銘柄オブジェクトを取得する。 
        //[引数] 
        //銘柄ID： 保有資産.銘柄ID 
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct =
            (WEB3FeqProduct) l_feqProductManager.getFeqProduct(l_lngProductId);
        
        //市場コード       
        String l_strMarketCode = l_feqProduct.getMarketCode();

        //1.6 get発注日(Date)            
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //1.7 get執行条件(String)
        //執行条件： リクエスト.執行条件 
        FeqExecutionConditionType l_executionCond = 
            l_feqOrderManager.getExecutionCondition(l_request.execCondType);
            
        //1.8 create新規注文内容   
        //新規注文内容を生成する。 
        //[引数] 
        //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 
        //扱者： get代理入力者()の戻り値 
        //is買付注文： false 
        //銘柄コード： 外国株式銘柄.銘柄コード 
        //市場コード：外国株式銘柄.市場コード 
        //注文数量： リクエスト.注文数量 
        //注文単価： リクエスト.注文単価 
        //執行条件： get執行条件()の戻り値 
        //注文失効日： リクエスト.注文有効期限 
        //税区分： 保有資産.税区分 
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
        WEB3FeqNewOrderSpec l_feqNewOrderSpec =
            WEB3FeqNewOrderSpec.createNewOrderSpec(
                l_stringInstitutionCode,
                l_trade,
                false,
                l_feqProduct.getProductCode(),
                l_strMarketCode,
                Double.parseDouble(l_request.orderQuantity),
                l_dblLimitPrice,
                l_executionCond,
                l_request.expirationDate,
                l_asset.getTaxType(),
                l_feqProduct.getCurrencyCode(),
                l_request.orderCondType,
                l_dblWLimitPrice,
                l_request.settleDiv,
                l_lngFirstOrderUnitId);
        
        //1.9 validate新規注文
        //新規注文の発注審査を行う。 
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
        
        //1.10 calc売買代金(double, double)
        
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
        
        //1.11 get市場( )
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.12 get通貨( )
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
        
        //1.13 get売付基準為替レート( )
        double l_dblSellBaseFxRate = l_currency.getSellBaseRate();

//		calc売買代金(double, double)の結果を、当該通貨の小数点桁数で丸める
		BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblCalcExecutionAmount);
		int l_intDecimalPlace = l_currency.getScale();
		l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
		l_dblCalcExecutionAmount = l_bdTradePriceFc.doubleValue();
		log.debug("売買代金の丸めた結果 ＝ "+ l_dblCalcExecutionAmount);
        
        //1.14 calc外国株式金額(補助口座, 外国株式銘柄, 市場, Date, 
        //      double, double, boolean, boolean, boolean)
        //各種金額の計算を行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //外国株式銘柄： get外国株式銘柄()の戻り値 
        //市場： get市場()の戻り値 
        //基準日： get発注日()の戻り値 
        //売買代金（外貨）： calc売買代金()の戻り値 
        //為替レート： get売付基準為替レート()の戻り値 
        //is買付： false 
        //is約定計算： false 
        //is指値： （以下のとおり） 
        //   リクエスト.注文単価区分 == ”指値” の場合、true 
        //   リクエスト.注文単価区分 == ”成行” の場合、false
        //注文チャネル：　@this.getログインチャネル()
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
                l_dblSellBaseFxRate,
                false,
                false,
                l_blnOrderPrice,
                this.getLoginChannel());       
                
        //1.15 外国株式注文更新イベントインタセプタ
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

        //is日計り取引採用( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
        boolean l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();

        //is日計り市場( )
        boolean l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

        //is日計り取引採用()＝true 且つ　@is日計り市場()＝trueの場合
        if (l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket)
        {
            //validate取引余力
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            //外国株式注文更新インタセプタを要素とした配列 
            WEB3FeqOrderUpdateInterceptor[] l_feqOrderUpdateInterceptors = {l_feqOrderUpdateInterceptor};

            //外国株式新規注文内容を要素とした配列 
            WEB3FeqNewOrderSpec[] l_feqNewOrderSpecs = {l_feqNewOrderSpec}; 

            WEB3TPTradingPowerResult l_tPTradingPowerResult =
                l_tpTradingPowerService.validateTradingPower(
                    l_subAccount,
                    l_feqOrderUpdateInterceptors,
                    l_feqNewOrderSpecs,
                    OrderTypeEnum.FEQ_SELL,
                    true);
            if (l_tPTradingPowerResult == null)
            {
                log.debug("予期しないシステムエラーが発生しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "予期しないシステムエラーが発生しました。");
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
        }

        //1.16 setThreadLocalPersistenceEventInterceptor(
            //arg0 : FeqOrderManagerPersistenceEventInterceptor)
        l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(l_feqOrderUpdateInterceptor);     
        
        //1.17 submitNewOrder
        //注文内容をDBに登録する。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //銘柄タイプ： ”外国株式” 
        //注文内容： 外国株式注文内容オブジェクト 
        //注文ID： リクエスト.注文ID 
        //パスワード： リクエスト.暗証番号 
        //isSkip発注審査： true 
        long l_lngOrderId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.orderActionId))
        {
            l_lngOrderId =  Long.parseLong(l_request.orderActionId);
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
        
        //1.18 余力再計算(補助口座 : 補助口座)
        //余力の更新を行う。 
        //[引数] 
        //補助口座： 補助口座オブジェクト
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(l_subAccount);

        //1.19 getOrder(注文ID : long)
        FeqOrderRow l_orderRow = null;
        try
        {
            Order l_order = l_feqOrderManager.getOrder(l_lngOrderId);
            l_orderRow = (FeqOrderRow) l_order.getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.20  createResponse( )
        WEB3FeqSellCompleteResponse l_response = 
            (WEB3FeqSellCompleteResponse)l_request.createResponse();
        
        //1.21 (*) プロパティセット
        //(*) 以下のとおりに、プロパティをセットする。
        //更新時間： 注文.更新日時
        l_response.lastUpdatedTimestamp = l_orderRow.getLastUpdatedTimestamp();
        //注文ID：リクエスト.注文ID
        l_response.orderActionId = l_request.orderActionId;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
