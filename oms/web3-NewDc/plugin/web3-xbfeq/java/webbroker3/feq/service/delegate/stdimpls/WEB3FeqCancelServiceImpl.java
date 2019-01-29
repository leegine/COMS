head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式取消サービスImpl(WEB3FeqCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成       
                 : 2005/08/03 鄭海良(中訊) レビュー       
Revesion History : 2008/01/21 柴双紅(中訊) モデルNo.381、モデルNo.372
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrder;
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
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqCancelUpdateInterceptor;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.message.WEB3FeqCancelCompleteRequest;
import webbroker3.feq.message.WEB3FeqCancelCompleteResponse;
import webbroker3.feq.message.WEB3FeqCancelConfirmRequest;
import webbroker3.feq.message.WEB3FeqCancelConfirmResponse;
import webbroker3.feq.service.delegate.WEB3FeqCancelService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式取消サービスImpl)<BR>
 * 外国株式取消サービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqCancelServiceImpl extends WEB3FeqClientRequestService implements WEB3FeqCancelService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCancelServiceImpl.class);  
    
    /**
     * @@roseuid 42CE39F6008C
     */
    public WEB3FeqCancelServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式取消サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *    validate注文()<BR>
     *    submit注文()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429ADE42014F
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
        // validate注文() 
        // submit注文() 
        if (l_request instanceof WEB3FeqCancelConfirmRequest)
        {
            l_response = 
                validateOrder((WEB3FeqCancelConfirmRequest)l_request);   
        }        
        else if (l_request instanceof WEB3FeqCancelCompleteRequest)
        {
            l_response =
                submitOrder((WEB3FeqCancelCompleteRequest)l_request);
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
     * (validate注文)<BR>
     * 取消注文の確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株取消）validate注文」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return WEB3FeqCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 429ADE42017D
     */
    protected WEB3FeqCancelConfirmResponse validateOrder(
        WEB3FeqCancelConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 補助口座オブジェクトを取得する。
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount();
        
        //1.3 get注文単位ByOrderId(long)
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
        
        log.debug("get注文単位ByOrderId(long)");
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();        

        WEB3FeqProduct l_feqProduct = null;
        try
        {
            //1.4 get外国株式銘柄(long)
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
        
        //1.5 get発注日( )
        //発注日を取得する。
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.6 CancelOrderSpec(注文ID : long)
        //取消注文内容インスタンスを生成する。 
        //[引数] 
        //注文ID： 注文単位.注文ID 
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(l_feqOrderUnitParams.getOrderId());

        //1.7 validate取消注文(SubAccount, CancelOrderSpec)(外国株式注文マネージャ::validate取消注文)
        //訂正注文の発注審査を行う。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //注文内容： 取消注文内容オブジェクト 
        OrderValidationResult l_cancelValidationResult =
            l_feqOrderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_cancelValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("発注審査チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_cancelValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注審査チェックエラー");
        }
        
        WEB3FeqOrderUnit l_web3FeqOrderUnit = (WEB3FeqOrderUnit) l_feqOrderUnit;

        BigDecimal l_bdCalcForeignCCYAmount = new BigDecimal("0");
        
        log.debug("注文単位.is買付()の戻り値 = " + l_web3FeqOrderUnit.isBuy());
        
        //1.8 (*1) 注文単位.is買付()の戻り値 == false の場合
        if (!l_web3FeqOrderUnit.isBuy())
        {
            //1.8.1 calc概算簿価単価(補助口座, long, TaxTypeEnum)
            //概算簿価単価を計算する。 
            //[引数] 
            //補助口座： 補助口座オブジェクト 
            //銘柄ID： 注文単位.銘柄ID 
            //税区分： 注文単位.税区分 
            WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
                (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();

            TaxTypeEnum l_taxType = l_feqOrderUnitParams.getTaxType();
            
            BigDecimal l_bdBookValuePrice = 
                l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                    l_subAccount, 
                    l_feqOrderUnitParams.getProductId(), 
                    l_taxType);
            
            //1.8.2 通貨オブジェクトを取得する。
            WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
                        
            //1.8.3 calc外貨換算(BigDecimal, double, int, String)
            //概算簿価単価を外貨換算する。 
            //[引数] 
            //金額（円貨）： calc概算簿価単価()の戻り値 
            //レート： 通貨.get売付基準為替レート()の戻り値 
            //小数部桁数： 通貨.get小数部桁数()の戻り値 
            //外貨換算丸め方式： 通貨.get外貨換算丸め方式()の戻り値
            l_bdCalcForeignCCYAmount = 
                l_feqBizLogicProvider.calcForeignCCYAmount(
                    l_bdBookValuePrice, 
                    l_currency.getSellBaseRate(), 
                    l_currency.getScale(), 
                    l_currency.getChangeFCcyRoundDiv());
            
            log.debug("calc外貨換算の戻り値 = " + l_bdCalcForeignCCYAmount);
        }
        
        //1.9 is出来るまで注文単位(FeqOrderUnit)
        //出来るまで注文かどうかを判定する。 
        //[引数] 
        //注文単位： 注文単位オブジェクト 
        boolean l_blnCarriedOrderUnit =  
            l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnit);        
        
        //1.10 get執行条件（SONAR）(String)(外国株式注文マネージャ::get執行条件（SONAR）)
        //SONARの執行条件区分を取得する。 
        //[引数] 
        //執行条件： 注文単位.執行条件 
        String l_strExecCondType = 
            l_feqOrderUnitParams.getExecutionConditionType().intValue() + "";
        
        String l_strExecutionConditionTypeSonar = 
            l_feqOrderManager.getExecutionConditionTypeSonar(l_strExecCondType);
        
        //1.11 get市場閉局警告外株市場(部店 : 部店)        
        //市場閉局警告市場を取得する。 
        //[引数] 
        //部店： 補助口座.get取引店()の戻り値
        String[] l_strTradeCloseFeqMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                (WEB3GentradeBranch)l_subAccount.getWeb3GenBranch());
        
        //1.12 レスポンスデータを生成する。 
        WEB3FeqCancelConfirmResponse l_response = 
            (WEB3FeqCancelConfirmResponse) l_request.createResponse();
        
        //1.13 (*2)プロパティセット
        //(*2) 以下のとおりに、プロパティをセットする。

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
            log.debug("注文単位.税区分 == '一般'の場合");
            l_response.taxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitParams.getTaxType()))
        {
            log.debug("注文単位.税区分 == '特定'の場合");
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
        l_response.orderQuantity = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnitParams.getQuantity());
        
        //内出来数量： 注文単位.約定数量        
        l_response.partContQuantity = 
            WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getExecutedQuantity());        
        
        log.debug("内出来数量： = " + l_response.partContQuantity);
        
        //注文単価区分： （以下のとおり）
        //   注文単位.指値 == 0 の場合、”成行”
        //   注文単位.指値 != 0 の場合、”指値”
        String l_strOrderPriceDiv = null;
        if (l_feqOrderUnitParams.getLimitPrice() == 0)
        {
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        l_response.orderPriceDiv = l_strOrderPriceDiv;
        
        //注文単価： 注文単位.指値
        l_response.limitPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnitParams.getLimitPrice());
        
        //通貨コード: 外国株式銘柄.get通貨コード()の戻り値
        l_response.currencyCode = l_feqProduct.getCurrencyCode();
        
        //執行条件： get執行条件（SONAR）()の戻り値
        l_response.execCondType = l_strExecutionConditionTypeSonar;
        
        //注文期限区分： （以下のとおり）
        //   is出来るまで注文単位()の戻り値 == true の場合、”出来るまで注文”
        //   is出来るまで注文単位()の戻り値 == false の場合、”当日限り”
        String l_strCarriedOrderUnit = null;
        
        log.debug("is出来るまで注文単位()の戻り値 = " + l_blnCarriedOrderUnit);
        
        if (l_blnCarriedOrderUnit)
        {
            l_strCarriedOrderUnit = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }
        else
        {
            l_strCarriedOrderUnit = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }
        l_response.expirationDateType = l_strCarriedOrderUnit;
        
        //注文有効期限： （以下のとおり）
        //   is出来るまで注文単位()の戻り値 == true の場合、注文単位.注文失効日
        //   is出来るまで注文単位()の戻り値 == false の場合、null
        if (l_blnCarriedOrderUnit)
        {
            l_response.expirationDate = WEB3DateUtility.toDay(
                l_feqOrderUnitParams.getExpirationDate());
        }
        else
        {
            l_response.expirationDate = null;
        }
        
        //発注条件： 注文単位.発注条件
        l_response.orderCondType = l_feqOrderUnitParams.getOrderConditionType();
        
        log.debug("注文単位.発注条件 = " + l_feqOrderUnitParams.getOrderConditionType());
        
        String l_strConditionType = l_response.orderCondType;
        
        //(*A)発注条件 == ”逆指値” の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
            l_strConditionType))
        {
            log.debug("発注条件 == ”逆指値” の場合");
            //逆指値用発注条件単価： 注文単位.逆指値基準値
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getStopOrderPrice());
            
            //逆指値用発注条件演算子： 注文単位.発注条件演算子
            l_response.stopOrderCondOperator = 
                l_feqOrderUnitParams.getOrderCondOperator();
        }        
        //(*B)発注条件 == ”W指値” の場合
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                l_strConditionType))
        {
            log.debug("発注条件 == ”W指値” の場合");
            //W指値用発注条件単価： 注文単位.逆指値基準値
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getStopOrderPrice());
            
            //W指値用発注条件演算子： 注文単位.発注条件演算子
            l_response.wlimitOrderCondOperator = 
                l_feqOrderUnitParams.getOrderCondOperator();
            
            //W指値用注文単価区分： （以下のとおり）
            //   注文単位.（W指値）訂正指値 == 0 の場合、”成行”
            //   注文単位.（W指値）訂正指値 != 0 の場合、”指値”
            String l_strWLimitOrderPriceDiv = null;
            if (l_feqOrderUnitParams.getWLimitPrice() == 0)
            {
                log.debug("注文単位.（W指値）訂正指値 == 0 の場合");
                l_strWLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                log.debug("注文単位.（W指値）訂正指値 != 0 の場合");
                l_strWLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            l_response.wLimitOrderPriceDiv = l_strWLimitOrderPriceDiv;
            
            //W指値用注文単価： 注文単位.（W指値）訂正指値
            l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getWLimitPrice());            
        }

        //概算受渡代金： 注文単位.概算受渡代金(*C)
        //(*C)注文単位.決済区分 == ”円貨” の場合、円貨換算したもの
        //    注文単位.決済区分 == ”外貨” の場合、外貨換算したもの をセット
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(
                l_feqOrderUnitParams.getSettleDiv()))
        {            
            //QA38
            //注文単位.決済区分 == ”円貨” の場合、注文単位.概算受渡代金
            log.debug("注文単位.決済区分 == ”円貨” の場合");
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getEstimatedPrice());
        }
        else
        {
            //QA38
            //注文単位.決済区分 == ”外貨” の場合、注文単位.概算受渡代金（外貨）
            log.debug("注文単位.決済区分 == ”外貨” の場合");
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getFEstimatedPrice());
        }
        
        //(*D)注文単位.is買付()の戻り値 == false の場合
        //概算簿価単価： calc外貨換算()の戻り値
        if (!l_web3FeqOrderUnit.isBuy())
        {
            log.debug("注文単位.is買付()の戻り値 == false");
            l_response.estimatedBookPrice = 
                WEB3StringTypeUtility.formatNumber(l_bdCalcForeignCCYAmount.doubleValue());
        }
        //確認時発注日： get発注日()の戻り値
        l_response.checkDate = l_datBizDate;
        
        //時価取得区分： null
        l_response.currentPriceGetDiv = null;
        
        //時価： null
        l_response.currentPrice = null;
        
        //前日比： null
        l_response.comparedPreviousDay = null;
        
        //取引時間： null
        l_response.currentPriceTime = null;
            
        //取引終了警告市場コード一覧： get市場閉局警告外株市場()の戻り値
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)
     * 取消注文の更新処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株取消）submit注文」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return WEB3FeqCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 429ADE42019D
     */
    protected WEB3FeqCancelCompleteResponse submitOrder(
        WEB3FeqCancelCompleteRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqCancelCompleteRequest l_request)";
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
                               
        //1.5 get発注日( )
        //[引数] 
        //確認時発注日： リクエスト.確認時発注日 
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.6 CancelOrderSpec(注文ID : long)
        //取消注文内容インスタンスを生成する。 
        //[引数] 
        //注文ID： 注文単位.注文ID 
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(l_feqOrderUnitParams.getOrderId());

        //1.7 validate取消注文(SubAccount, CancelOrderSpec)(外国株式注文マネージャ::validate取消注文)
        //訂正注文の発注審査を行う。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //注文内容： 取消注文内容オブジェクト 
        OrderValidationResult l_cancelValidationResult =
            l_feqOrderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_cancelValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("発注審査チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_cancelValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注審査チェックエラー");
        }
        
        //1.8 外国株式取消更新インタセプタを生成する。 
        //[引数] 
        //代理入力者： get代理入力者()の戻り値
        WEB3FeqCancelUpdateInterceptor l_updateInterceptor = 
            new WEB3FeqCancelUpdateInterceptor(l_trader);
        
        //1.9 インタセプタをセットする。 
        // [引数] 
        // 外国株式取消更新インタセプタ ：　@（外国株式取消更新インタセプタ）
        l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //1.10 submitCancelOrder()
        //取消注文の登録を行う。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //注文内容： 取消注文内容オブジェクト 
        //パスワード： リクエスト.暗証番号 
        //isSkip発注審査： true 
        OrderSubmissionResult l_submissionResult =
            l_feqOrderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        if (l_submissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitCancelOrder" +
                l_submissionResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.11 余力再計算(補助口座 : 補助口座)
        //取引余力の更新を行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(
            (WEB3GentradeSubAccount)l_subAccount);

        //1.12 getOrder(注文ID : long)
        //注文オブジェクトを取得する。 
        //[引数] 
        //注文ID： リクエスト.注文ID 
        FeqOrderRow l_feqOrderRow = null;
        try
        {
            FeqOrder l_feqOrder = (FeqOrder) 
                l_feqOrderManager.getOrder(Long.parseLong(l_request.orderId));
            
            l_feqOrderRow = (FeqOrderRow) l_feqOrder.getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when "
                + "l_feqOrderManager.getOrder(OrderId) with "
                + "OrderId = "
                + Long.parseLong(l_request.orderId));
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //1.13 レスポンスデータを生成する。
        WEB3FeqCancelCompleteResponse l_response = 
            (WEB3FeqCancelCompleteResponse) l_request.createResponse();
        
        //1.14  (*)プロパティセット
        //(*) 以下のとおりに、プロパティをセットする。
        
        //更新時間： 注文.更新日付
        l_response.lastUpdatedTimestamp = 
            l_feqOrderRow.getLastUpdatedTimestamp();
        
        //注文ID： リクエスト.注文ID
        l_response.orderId = l_request.orderId;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
