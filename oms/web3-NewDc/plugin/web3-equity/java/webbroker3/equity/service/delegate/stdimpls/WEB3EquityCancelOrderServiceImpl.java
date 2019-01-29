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
filename	WEB3EquityCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文取消サービスImpl (WEB3EquityCancelOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 中尾寿彦(SRA) 新規作成
Revesion History : 2006/08/29 柴雙紅(中訊) 仕様変更モデル970
Revesion History : 2006/11/03 唐性峰　@(中訊)モデルNo.1017
Revesion History : 2006/12/14 柴双紅(中訊) モデル1083
Revesion History : 2007/01/31 柴双紅(中訊) モデル1120
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityCancelOrderInterceptor;
import webbroker3.equity.WEB3EquityCancelOrderSpec;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquityCancelCompleteResponse;
import webbroker3.equity.message.WEB3EquityCancelConfirmRequest;
import webbroker3.equity.message.WEB3EquityCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityCancelOrderService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

/**
 * （株式注文取消サービスImpl）。<BR>
 * <BR>
 * 株式注文取消サービス実装クラス
 * @@version 1.0  
 */
public class WEB3EquityCancelOrderServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityCancelOrderService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityCancelOrderServiceImpl.class);

   /**
    * @@roseuid 40AC7467029D
    */
   public WEB3EquityCancelOrderServiceImpl()
   {

   }

   /**
    * (validate注文取消)<BR>
    * 「シーケンス図（注文取消サービス）注文取消確認」参照。<BR>
    * @@param l_request - (入力データ)<BR>
    * <BR>
    * クライアントからの入力データ<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 403097C202AA
    */
    public WEB3GenResponse validateCancelOrder(WEB3EquityCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancelOrder(WEB3EquityCancelOrderConfirmRequest)";
        log.entering(STR_METHOD_NAME);
    
        //1.1. validate()
        l_request.validate();
        
        //1.2. get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
                
        //1.3. get代理入力者()
        Trader l_trader = this.getTrader();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
    
        //1.4. validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
    
        //1.5. 株式注文取消内容()
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, l_trader);
        
        //1.6. validate現物株式取消()                
        EqTypeOrderValidationResult l_res =
            (EqTypeOrderValidationResult)l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);  
        if (l_res.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(
                l_res.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.7. get市場閉局警告市場()
        String[] l_strTradeCloseMarket =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);
    
        //1.8. createResponse()
        WEB3EquityCancelConfirmResponse l_confirmResponse =
            (WEB3EquityCancelConfirmResponse)l_request.createResponse();
            
        //1.9. getOrderUnits()
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();    
        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(), l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }   
            
        //1.10. 売り注文の場合のみ実行
        double l_dblEstimatedBookPrice = 0.0D;
        OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
        if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            l_dblEstimatedBookPrice = 
                l_bizLogicProvider.calcEstimatedBookPrice(
                    l_orderUnitRow.getProductId(),
                    l_subAccount,
                    l_orderUnit.getTaxType());
        }
        
        //1.11. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct = null;
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_subAccount.getInstitution(),
                    l_product.getProductCode(),
                    l_market.getMarketCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(), l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }        
       
        //1.12. get表示用時価情報()
        WEB3EquityProductQuote l_productQuote = null;
        l_productQuote =
            l_productManager.getDisplayEquityProductQuote(
                l_tradedProduct,
                l_subAccount);
        
        //プロパティセット
        //レスポンス.確認時発注日
        Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_confirmResponse.checkDate = WEB3DateUtility.toDay(l_bizDate);
        //レスポンス.概算受渡代金
        l_confirmResponse.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
        //レスポンス.取引終了警告市場コード一覧
        l_confirmResponse.messageSuspension = l_strTradeCloseMarket;
        //レスポンス.銘柄コード
        l_confirmResponse.productCode = l_product.getProductCode();
        //レスポンス.銘柄名
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_confirmResponse.productName = l_productRow.getStandardName();
        //レスポンス.市場コード
        l_confirmResponse.marketCode = l_market.getMarketCode();
        //レスポンス.口座区分
        l_confirmResponse.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
        //レスポンス.取引区分
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                l_confirmResponse.tradingType = WEB3TradingTypeDef.PRESENCE_ORDER;
            }
            else
            {
                l_confirmResponse.tradingType = WEB3TradingTypeDef.BUY_ORDER;
            }
        }
        else
        {
            l_confirmResponse.tradingType = WEB3TradingTypeDef.SELL_ORDER;
        }
        //レスポンス.注文株数
        l_confirmResponse.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        //レスポンス.内出来株数
        double l_dblExecutedQuantity = l_orderUnitRow.getExecutedQuantity();
        if (l_dblExecutedQuantity == 0.0D)
        {
            l_confirmResponse.partContQuantity = null;
        }
        else
        {
            l_confirmResponse.partContQuantity =
                WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
        }
        //レスポンス.注文単価区分
        if (l_orderUnit.isMarketOrder())
        {
            l_confirmResponse.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_confirmResponse.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            //レスポンス.注文単価
            l_confirmResponse.limitPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }

        //レスポンス.概算簿価単価
        if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            l_confirmResponse.estimatedBookPrice = 
                WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        }
        //レスポンス.値段条件
        l_confirmResponse.priceCondType = l_orderUnitRow.getPriceConditionType();
        //レスポンス.執行条件
        l_confirmResponse.execCondType =
            l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());
        //レスポンス.注文期限区分
        boolean l_isCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnit);
        if (!l_isCarriedOrderUnit)
        {
            l_confirmResponse.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }
        else
        {
            l_confirmResponse.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }   
        //レスポンス.注文有効期限
        if (l_isCarriedOrderUnit)
        {
            l_confirmResponse.expirationDate =
                l_orderUnitRow.getExpirationDate();
        }
        //レスポンス.発注条件区分
        l_confirmResponse.orderCondType =
            l_orderUnitRow.getOrderConditionType();
        //レスポンス.逆指値用発注条件単価
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.stopOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
        }
        //レスポンス.逆指値用発注条件演算子
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.stopOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
        }
        //レスポンス.W指値用発注条件単価
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.wlimitOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
        }
        //レスポンス.W指値用発注条件演算子
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.wlimitOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
        }
        //レスポンス.W指値用注文単価区分
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            if (l_orderUnitRow.getWLimitPrice() == 0.0D)
            {
                l_confirmResponse.wLimitOrderPriceDiv = WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
            }
            else
            {
                l_confirmResponse.wLimitOrderPriceDiv = WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
                //レスポンス.W指値用注文単価
                l_confirmResponse.wLimitPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
            }
        }

        //　@W指値用執行条件：　@注文単位.発注条件＝2（W指値）の場合のみセット。
        //　@　@　@拡張株式注文マネージャ.get執行条件（SONAR）(注文単位.Ｗ指値用執行条件)の戻り値をセット。
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.wlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getWLimitExecCondType());
        }

        //　@W指値用有効状態区分：　@株式データアダプタ.getＷ指値用有効状態区分(注文単位)の戻り値をセット。
        l_confirmResponse.wlimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //　@W指値用切替前注文単価：　@株式データアダプタ.getＷ指値用切替前注文単価(注文単位)の戻り値をセット。
        l_confirmResponse.wlimitBefChgLimitPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        //　@W指値用切替前執行条件：　@株式データアダプタ.getＷ指値用切替前執行条件(注文単位)の戻り値をセット。
        l_confirmResponse.wlimitBefChgExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //　@元発注条件区分：　@注文単位.元発注条件
        l_confirmResponse.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //　@元発注条件単価：　@注文単位.元逆指値基準値
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_confirmResponse.orgOrderCondPrice = null;
        }
        else
        {
            l_confirmResponse.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }

        //　@元発注条件演算子：　@注文単位.元発注条件演算子
        l_confirmResponse.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //　@元Ｗ指値用注文単価区分：　@株式データアダプタ.get元Ｗ指値用注文単価区分(注文単位)の戻り値をセット。
        String l_strOrgWlimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);
        l_confirmResponse.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

        //　@元Ｗ指値用注文単価：元W指値用注文単価区分が"指値"の場合のみ、
        //   株式データアダプタ.get元Ｗ指値用注文単価(注文単位)の戻り値をセット。
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
        {
            l_confirmResponse.orgWlimitPrice =
                WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }

        //　@元Ｗ指値用執行条件：　@株式データアダプタ.get元Ｗ指値用執行条件(注文単位)の戻り値をセット。
        l_confirmResponse.orgWlimitExecCondType =
            WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //レスポンス.時価区分
        l_confirmResponse.currentPriceDiv = l_productQuote.getQuoteTypeDiv();
        //レスポンス.時価（現在値）
        l_confirmResponse.currentPrice =
            WEB3StringTypeUtility.formatNumber(l_productQuote.getQuote());
        //レスポンス.前日比
        l_confirmResponse.comparedPreviousDay =
            WEB3StringTypeUtility.formatNumber(l_productQuote.getComparedPreviousDay());
        //レスポンス.取引時間（時価発表時間）
        l_confirmResponse.currentPriceTime = l_productQuote.getQuoteTime();

        //レスポンス.現在値
        l_confirmResponse.boardCurrentPrice = l_productQuote.getBoardCurrentPrice();

        //レスポンス.現在値時刻
        l_confirmResponse.boardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime();

        //レスポンス.現在値区分
        l_confirmResponse.boardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();

        //レスポンス.現在値前日比
        l_confirmResponse.boardChange = l_productQuote.getBoardChange();

        //レスポンス.出来高
        l_confirmResponse.volume = l_productQuote.getVolume();

        //レスポンス.出来高時刻
        l_confirmResponse.volumeTime = l_productQuote.getVolumeTime();

        //レスポンス.買気配値タイトル区分
        l_confirmResponse.askPriceTitle = l_productQuote.getAskPriceTitle();

        //レスポンス.買気配値
        l_confirmResponse.askPrice = l_productQuote.getAskPrice();

        //レスポンス.買気配値時刻
        l_confirmResponse.askPriceTime = l_productQuote.getAskPriceTime();

        //レスポンス.売気配値タイトル区分
        l_confirmResponse.bidPriceTitle = l_productQuote.getBidPriceTitle();

        //レスポンス.売気配値
        l_confirmResponse.bidPrice = l_productQuote.getBidPrice();

        //レスポンス.売気配値時刻
        l_confirmResponse.bidPriceTime = l_productQuote.getBidPriceTime();

        //レスポンス.基準値段
        l_confirmResponse.basePrice = l_productQuote.getBasePrice();

        log.exiting(STR_METHOD_NAME);
        return l_confirmResponse;
    }

   /**
    * (submit注文取消)<BR>
    * 「シーケンス図（注文取消サービス）注文取消更新」参照<BR>
    * @@param l_request - (入力データ)<BR>
    * <BR>
    * クライアントからの入力データ<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 403097C202BA
    */
    public WEB3GenResponse submitCancelOrder(WEB3EquityCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitCancelOrder(WEB3EquityCancelOrderCompleteRequest)";
        log.entering(STR_METHOD_NAME);
    
        //1.1. validate()
        l_request.validate();
        
        //1.2. get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
                
        //1.3. get代理入力者()
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        
        //1.4. validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
    
        //1.5. 株式注文取消内容()
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, l_trader);
        
        //1.6. get発注日()
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.7. validate現物株式取消()               
        EqTypeOrderValidationResult l_res =
            (EqTypeOrderValidationResult)l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);  
        if (l_res.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(
                l_res.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.8. getOrderUnits()
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.9. 約定がある場合（注文単位.isUnexecuted( )==false）
        double l_dblEstimatedPrice;
        if (!l_orderUnit.isUnexecuted())
        {
            //1.9.1. get失効時受渡金額()
			l_dblEstimatedPrice = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
        }
        else
        {
            l_dblEstimatedPrice = l_orderUnitRow.getEstimatedPrice();
        }
        
        //1.10. 株式注文取消インタセプタ()
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityCancelOrderInterceptor l_cancelInterceptor =
            new WEB3EquityCancelOrderInterceptor(
                l_dblEstimatedPrice,
                l_strOrderRootDiv,
                l_trader);
        
        //1.11. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_cancelInterceptor);
    
        //1.12. submit現物株式注文取消()
        EqTypeOrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            ProcessingResult l_processingResult = l_orderSubmissionResult.getProcessingResult();
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }
        
        boolean l_blnIsCancelAllOrderUnit = false;
        if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
        {
            WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationEqTypeOrderUpdateService.class);
            l_blnIsCancelAllOrderUnit =
                l_updateService.cancelAllOrderUnit(l_orderUnit.getOrderId());
        }
        
        //1.13. 余力再計算()
        WEB3TPTradingPowerService l_tradingpowerService =
        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        l_tradingpowerService.reCalcTradingPower(l_subAccount);
        
        //1.14. createResponse()
        WEB3EquityCancelCompleteResponse l_completeResponse =
            (WEB3EquityCancelCompleteResponse)l_request.createResponse();
        
        //プロパティセット
        // レスポンス.更新時間
        l_completeResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        // レスポンス.識別番号
        l_completeResponse.orderActionId = String.valueOf(l_orderUnitRow.getOrderId()); 
        l_completeResponse.succSettingFlag = l_blnIsCancelAllOrderUnit;
    
        log.exiting(STR_METHOD_NAME);
        return l_completeResponse;
    }

   /**
    * 株式注文取消処理を実行する。<BR>
    * <BR>
    * （システム実装方針ガイド 4.4.業務ロジック　@参照）<BR>
    * <BR>
    * １）　@実施メソッド判定<BR>
    * 　@－リクエストデータの具象データ型が「現物株式注文取消確認リクエスト」の場合は、<BR>
    * this.注文取消確認( )をコールする。<BR>
    * 　@－リクエストデータの具象データ型が「現物株式注文取消完了リクエスト」の場<BR>
    * 合は、this.注文取消更新( )をコールする。<BR>
    * <BR>
    * ２）　@メソッドの戻り値を返却する。<BR>
    * @@param l_request
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 403097C202BC
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3EquityCancelConfirmRequest)
        {
            l_response = this.validateCancelOrder(
                (WEB3EquityCancelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3EquityCancelCompleteRequest)
        {
            l_response = this.submitCancelOrder(
                (WEB3EquityCancelCompleteRequest)l_request);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "request !=現物株式注文取消確認リクエスト and request !=現物株式注文取消完了リクエスト");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
