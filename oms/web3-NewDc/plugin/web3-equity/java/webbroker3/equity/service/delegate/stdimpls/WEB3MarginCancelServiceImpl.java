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
filename	WEB3MarginCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引取消サービスImpl(WEB3MarginCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 中尾寿彦(SRA) 新規作成
Revesion History : 2006/11/27 柴双紅(中訊) モデル1019
Revesion History : 2006/12/14 唐性峰　@(中訊)　@モデルNo.1083
Revesion History : 2007/01/31 柴双紅(中訊) モデル1120
Revesion History : 2007/06/05 何文敏 (中訊) 仕様変更・モデル1166
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityCancelOrderSpec;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityFinTransactionManager;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginCancelUpdateInterceptor;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginCancelCompleteRequest;
import webbroker3.equity.message.WEB3MarginCancelCompleteResponse;
import webbroker3.equity.message.WEB3MarginCancelConfirmRequest;
import webbroker3.equity.message.WEB3MarginCancelConfirmResponse;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3MarginCancelService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引取消サービスImpl）。<BR>
 * <BR>
 * 信用取引取消サービス実装クラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCancelServiceImpl
    extends WEB3MarginClientRequestService
    implements WEB3MarginCancelService 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCancelServiceImpl.class); 
    
    /**
     * @@roseuid 4140066E00E2
     */
    public WEB3MarginCancelServiceImpl() 
    {
     
    }
    
    /**
     * 信用取引取消サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()、<BR>
     * submit注文()メソッドのいずれかをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 405807E70179
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3MarginCancelConfirmRequest)
        {
            l_response = this.validateOrder((WEB3MarginCancelConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3MarginCancelCompleteRequest)
        {
            l_response = this.submitOrder((WEB3MarginCancelCompleteRequest)l_request);
        }
        else
        {
            log.error("パラメータタイプ不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 信用取引の取消審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引取消サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引取消リクエストオブジェクト
     * @@return WEB3MarginCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058082D035D
     */
    protected WEB3MarginCancelConfirmResponse validateOrder(WEB3MarginCancelConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3MarginCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate()
        l_request.validate();
        
        //1.2. get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
                
        //1.3. get代理入力者()
        Trader l_trader = this.getTrader();
        
        //1.4. 株式注文取消内容()
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, l_trader);
        
        //1.5. getOrderUnits()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.6. getMarket()
        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //1.10. validate現物株式取消()
        OrderValidationResult l_result =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.11. getOrderType()
        OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
        
        //1.12. 取消元注文が現渡注文の場合のみ実施する
        WEB3TPTradingPowerResult l_tpResult = null;
        if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            //セキュリティサービスを取得
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class); 
            // 注文経路区分取得
            String l_strOrderRootDiv =
                l_opLoginSec.getSessionProperty(
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            
            //1.12.1. 信用取消更新インタセプタ()
            WEB3MarginCancelUpdateInterceptor l_updateInterceptor =
                new WEB3MarginCancelUpdateInterceptor(
                    l_orderUnitRow.getEstimatedPrice(),
                    l_strOrderRootDiv,
                    (WEB3GentradeTrader)this.getTrader());
            //1.12.2. validate取引余力()
            WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_orderSpecIntercepters = { l_updateInterceptor };
            Object[] l_orderSpecs = { l_cancelOrderSpec };

            l_tpResult = l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                false);
            
            if (l_tpResult.isResultFlg() == false)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        //1.13. createResponse()
        WEB3MarginCancelConfirmResponse l_response =
            (WEB3MarginCancelConfirmResponse)l_request.createResponse();

        //1.14. create建株明細ByOrder()
        WEB3MarginContractUnit[] l_byOrder =
            l_orderManager.createContractUnitByOrder(l_orderUnit.getOrderUnitId());
        
        //1.15. get市場閉局警告市場()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        String[] l_strCloseMarket =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                (WEB3GentradeBranch)l_mainAccount.getBranch(),
                ProductTypeEnum.EQUITY,
                l_orderUnitRow.getRepaymentType());
            
        //1.16. get執行条件（SONAR）()
        String l_strExecutionCondition =
            l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnitRow.getExecutionConditionType());
        
        //1.17. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct =
            (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
        
        // 拡張プロダクトマネージャ取得
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            
        //時価情報取得
        WEB3EquityProductQuote l_productQuote =
            l_productManager.getDisplayEquityProductQuote(l_tradedProduct, l_subAccount);
        
        //時価区分
        String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
        // 時価
        double l_dblCurrentPrice = l_productQuote.getQuote();
        // 前日比
        double l_dblChange = l_productQuote.getComparedPreviousDay();
        // 時価発表時間
        Timestamp l_currentPriceTime = l_productQuote.getQuoteTime();
        
        //1.22. 信用取引弁済()
        WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
        
        //1.23. プロパティセット
        l_repaymentUnit.repaymentDiv = l_orderUnitRow.getRepaymentType();
        l_repaymentUnit.repaymentTimeLimit = String.valueOf(l_orderUnitRow.getRepaymentNum());
        
        //1.24. get発注日()
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.25. getProduct( )
        WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
        
        //1.26. プロパティセット
        //レスポンス.確認時発注日
        l_response.checkDate = l_datOrderBizDate;
        //レスポンス.概算受渡代金
        if (OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
        {
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
        }
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()))
        {
            WEB3EquityFinTransactionManager l_transactionManager = 
                (WEB3EquityFinTransactionManager)l_tradingModule.getFinTransactionManager();
            l_response.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_transactionManager.getNetAmountTotal(l_orderUnit));
        }
        //レスポンス.取引終了警告市場コード一覧
        l_response.messageSuspension = l_strCloseMarket;
        //レスポンス.銘柄コード
        l_response.productCode = l_product.getProductCode();
        //レスポンス.銘柄名
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        //レスポンス.市場コード
        l_response.marketCode = l_market.getMarketCode();
        //レスポンス.口座区分
        if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
        {
            l_response.taxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_orderUnitRow.getTaxType()) ||
                  TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_orderUnitRow.getTaxType()))
        {
            l_response.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }
        //レスポンス.取引区分
        if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN;
        }
        else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN;
        }
        else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.CLOSE_SELL_MARGIN;
        }
        else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.CLOSE_BUY_MARGIN;
        }
        else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.GENBIKI_ORDER;
        }
        else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.GENWADASI_ORDER;
        }
        //レスポンス.弁済
        l_response.repayment = l_repaymentUnit;
        //レスポンス.注文株数
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        //レスポンス.内出来株数
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        if (l_dblExecutedQuantity == 0.0D)
        {
            l_response.partContQuantity = null;
        }
        else
        {
            l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
        }
        //レスポンス.注文単価区分
        if (l_orderUnit.isMarketOrder() == true)
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        //レスポンス.注文単価
        if (l_orderUnit.isMarketOrder() == true)
        {
            l_response.limitPrice = null;
        }
        else
        {
            l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
        }
        //レスポンス.決済順序区分
        l_response.closingOrder = l_orderUnitRow.getClosingOrderType();
        //レスポンス.建株明細一覧
        l_response.contractUnits = l_byOrder;
        //レスポンス.現引先現渡元口座区分：　@
        TaxTypeEnum l_swapTaxType = l_orderUnitRow.getSwapTaxType();
        if (TaxTypeEnum.UNDEFINED.equals(l_swapTaxType))
        {
            l_response.swapTaxType = null;
        }
        else if (TaxTypeEnum.NORMAL.equals(l_swapTaxType))
        {
            l_response.swapTaxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_swapTaxType) ||
                  TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_swapTaxType))
        {
            l_response.swapTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }
        //レスポンス.執行条件
        l_response.execCondType = l_strExecutionCondition;
        //レスポンス.値段条件
        l_response.priceCondType = l_orderUnitRow.getPriceConditionType();
        //レスポンス.注文期限区分
        boolean l_isCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnit);
        if (l_isCarriedOrderUnit == true)
        {
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }
        else
        {
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }
        //レスポンス.注文有効期限
        if (l_isCarriedOrderUnit == true)
        {
            l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());
        }
        else
        {
            l_response.expirationDate = null;
        }
        
        //レスポンス.発注条件区分
        l_response.orderCondType = l_orderUnitRow.getOrderConditionType();
        //レスポンス.逆指値用発注条件単価
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
        }
        else
        {
            l_response.stopOrderCondPrice = null;
        }
        //レスポンス.逆指値用発注条件演算子
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
        }
        else
        {
            l_response.stopOrderCondOperator = null;
        }
        //レスポンス.W指値用発注条件単価
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
        }
        else
        {
            l_response.wlimitOrderCondPrice = null;
        }
        //レスポンス.W指値用発注条件演算子
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
        }
        else
        {
            l_response.wlimitOrderCondOperator = null;
        }
		//レスポンス.W指値用注文単価区分
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
	        if (l_orderUnitRow.getWLimitPrice() == 0)
	        {
		        l_response.wLimitOrderPriceDiv = WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
		        l_response.wLimitPrice = null;
	        }
	        else
	        {
		        l_response.wLimitOrderPriceDiv = WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
		        //レスポンス.W指値用注文単価
		        l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
	        }
            //レスポンス.W指値用執行条件
            l_response.wlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getWLimitExecCondType());
        }
        else
        {
	        l_response.wLimitOrderPriceDiv = null;
            l_response.wlimitExecCondType = null;
        }

        //レスポンス.W指値用有効状態区
        l_response.wlimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
        //レスポンス.W指値用切替前注文単位
        l_response.wlimitBefChgLimitPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);
        //レスポンス.W指値用切替前執行条件
        l_response.wlimitBefChgExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);
        //レスポンス.元発注条件区分
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
        //レスポンス.元発注条件単価
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = null;
        }
        else
        {
            l_response.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }
        //レスポンス.元発注条件演算子
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();
        //レスポンス.元Ｗ指値用注文単価区分
        String l_strOrgWlimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);
        l_response.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;
        //レスポンス.元Ｗ指値用注文単価:元W指値用注文単価区分が"指値"の場合のみ、
        // 株式データアダプタ.get元Ｗ指値用注文単価(注文単位)の戻り値をセット。
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
        {
            l_response.orgWlimitPrice =
                WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }
        //レスポンス.元Ｗ指値用執行条件
        l_response.orgWlimitExecCondType =
            WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);
        //レスポンス.時価区分
        l_response.currentPriceDiv = l_strQuoteTypeDiv;
        //レスポンス.時価（現在値）
        l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
        //レスポンス.前日比
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
        //レスポンス.取引時間（時価発表時間）
        l_response.currentPriceTime = l_currentPriceTime;
        
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
        
        //レスポンス.注意文言表示区分
        //レスポンス.預り金不足額
        if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_response.attentionObjectionType = l_tpResult.getAttentionObjectionType();
            if (WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                l_tpResult.getAttentionObjectionType()))
            {
                l_response.accountBalanceInsufficiency =
                    WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
            }
        }

        // 強制決済理由
        l_response.forcedSettleReason = l_orderUnitRow.getForcedSettleReasonType();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * 信用取引の注文取消を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引取消サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引取消リクエストオブジェクト
     * @@return WEB3MarginCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058083503AC
     */
    protected WEB3MarginCancelCompleteResponse submitOrder(WEB3MarginCancelCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3MarginCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate()
        l_request.validate();
        
        //1.2. get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
                
        //1.3. get代理入力者()
        Trader l_trader = this.getTrader();
        
        //1.4. 株式注文取消内容()
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, l_trader);
        
        //1.5. getOrderUnits()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.6. getMarket()
        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //1.10. get発注日()
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.11. validate現物株式取消()
        OrderValidationResult l_result =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.12. getOrderType()
        OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
        
        // 受渡代金
        double l_dblNetAmount = 0D;
        // 約定がある場合
        if (l_orderUnit.isUnexecuted() == false)
        {
            // 拡張株式注文マネージャ.get失効時受渡代金
            l_dblNetAmount = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
        }
        // 約定がない場合
        else
        {
            // 注文単位.概算受渡代金
            l_dblNetAmount = l_orderUnitRow.getEstimatedPrice();
        }
        
        //セキュリティサービスを取得
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class); 
        // 注文経路区分取得
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                
        //1.13. 信用取消更新インタセプタ()
        WEB3MarginCancelUpdateInterceptor l_updateInterceptor =
            new WEB3MarginCancelUpdateInterceptor(
                l_dblNetAmount,
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());
        
        //1.14. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //1.15. submit現物株式注文取消()
        OrderSubmissionResult l_submissionResult =
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        if (l_submissionResult.getProcessingResult().isFailedResult())
        {            
            throw new WEB3BusinessLayerException(
                l_submissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
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
        
        if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
			//1.16. validate取引余力()
            WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_orderSpecIntercepters = { l_updateInterceptor };
            Object[] l_orderSpecs = { l_cancelOrderSpec };
        
            WEB3TPTradingPowerResult l_tpResult = null;
            l_tpResult = l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                true);
            
            if (l_tpResult.isResultFlg() == false)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        else
        {
            // 余力再計算()
			WEB3TPTradingPowerService l_tradingpowerService =
			    (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
			l_tradingpowerService.reCalcTradingPower(l_subAccount);
        }
        
        //1.17. createResponse()
        WEB3MarginCancelCompleteResponse l_response =
            (WEB3MarginCancelCompleteResponse)l_request.createResponse();
        
        //1.18. プロパティセット
        // レスポンス.更新時間
        l_response.lastUpdatedTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        // レスポンス.識別番号
        l_response.orderActionId = String.valueOf(l_orderUnitRow.getOrderId()); 
        l_response.succSettingFlag = l_blnIsCancelAllOrderUnit;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
