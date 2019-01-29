head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP取消注文サービスImpl(WEB3OptionCancelOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 李強 新規作成
              001: 2004/06/24 李強 submit注文   修正
              002: 2004/07/22 王暁傑 (中訊) WEB3OrderPriceDivDefでWEB3IfoOrderPriceDivDefを差し替える
              003: 2004/08/09 王暁傑 (Sinocom) 対応名称:【WEB3-XBIFO-A-CD-0082】     
              004: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
              005: 2004/08/13 li-qiang　@(中訊) STBUG(IFO_ST-000103)を対応
              006: 2004/08/15 呉艶飛　@(中訊) BUG83を対応
              012: 2006/07/13 徐宏偉 (中訊) 仕様変更　@モデル477
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.656
Revesion History : 2007/06/21 孟亜南 (中訊) 仕様変更モデル712
Revesion History : 2008/04/14 張騰宇 (中訊) モデル 844
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoCancelUpdateInterceptor;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCancelCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCancelCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionCancelOrderService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (OP取消注文サービスImpl)<BR>
 * 株価指数オプション取消注文サービス実装クラス<BR>
 * 
 * @@author 李強
 * @@version 1.0
 */
public class WEB3OptionCancelOrderServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionCancelOrderService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionCancelOrderServiceImpl.class);    
    
    /**
     * @@roseuid 40C0BD6E02FD
     */
    public WEB3OptionCancelOrderServiceImpl() 
    {
     
    }
    
    /**
     * (validate注文)<BR>
     * 株価指数オプションの取消発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP取消サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)
     * 株価指数オプション取消注文確認リクエスト
     * @@return webbroker3.ifo.message.WEB3OptionsCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 405169280118
     */
    protected WEB3OptionsCancelConfirmResponse validateOrder(
        WEB3OptionsCancelConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3OptionsCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
          
        log.debug("シーケンス図「（OP取消サービス）validate注文」参照");
        //1.1 リクエストデータのチェックを実施する
        l_request.validate();
        
        //1.2 取消対象の注文ＩＤを指定し、取消注文内容（CancelOrderSpec）を生成する
        long l_lngOrderId = Long.parseLong(l_request.id);    
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngOrderId);
        
        //1.3 補助口座を取得する
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
            
        //1.4 validate取消注文(補助口座 : SubAccount, 取消注文内容 : CancelOrderSpec)
        //OP注文マネージャを取得する
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderValidationResult l_result = l_orderManager.validateCancelOrder(l_subAccount,l_cancelOrderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);  
        }

        try 
        {
            //1.5 レスポンスデータ生成
            WEB3OptionsCancelConfirmResponse l_response = 
                (WEB3OptionsCancelConfirmResponse)l_request.createResponse();        
            
            //1.6 注文ＩＤを指定して注文オブジェクトを取得する
            IfoOrderImpl l_ifoOrder = (IfoOrderImpl)l_orderManager.getOrder(l_lngOrderId);          
            
            //1.7 注文単位取得            
            OrderUnit[] l_orderUnits = l_ifoOrder.getOrderUnits();
            
            //1.8 先物OP銘柄オブジェクトを取得
            OrderUnit l_orderUnit = l_orderUnits[0];
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();          
            
            // プロダクトIDを取得する
            long l_lngProductId = l_ifoOrderUnitRow.getProductId();
            // 拡張プロダクトマネージャ
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_productManager.getProduct(l_lngProductId);     
            
            //1.9 建玉明細を作成する
            WEB3FuturesOptionsContractUnit[] l_web3ContractUnits = 
                l_orderManager.createContractUnitByOrder(l_lngOrderId);
            
            //1.10 市場閉局警告指数を取得する
            // [get市場閉局警告指数()に指定する引数]
            // 部店：　@補助口座.get取引店()
            // 先物／オプション区分：　@”オプション”
            String[] l_strTradeCloseSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                l_subAccount.getWeb3GenBranch(),
                WEB3FuturesOptionDivDef.OPTION);     

            //1.11 getTradedProduct()
            TradedProduct l_tradedProduct = l_orderUnit.getTradedProduct();

            //1.12 getQuote(tradedProduct : TradedProduct, realType : RealType)
            //[getQuote()に指定する引数]
            // tradedProduct(取引銘柄）：
            // getTradedProduct( )の戻り値の取引銘柄オブジェクト 
            // realType：  
            // 顧客 = 補助口座.getMainAccount()  
            // 顧客.isリアル顧客( )==trueの場合は”リアル”、falseの場合は”20分ディレイ”をセット。
            WEB3IfoQuoteData l_quoteData = null;
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
            WEB3QuoteDataSupplierService l_supplierProvide = 
                (WEB3QuoteDataSupplierService)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getQuoteDataSupplierService();
            RealType l_realType = null;
            WEB3GentradeMainAccount  l_mainAccount = (WEB3GentradeMainAccount )l_subAccount.getMainAccount();
            if (l_mainAccount.isRealCustomer())
            {
                l_realType = RealType.REAL;
            }
            else
            {
                l_realType = RealType.DELAY;
            }
            try
            {
                l_quoteData = (WEB3IfoQuoteData)l_supplierProvide.getQuote(
                    l_tradedProduct,
                    l_realType);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(),l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }

            //1.13 getCurrentPrice()
            double l_dblCurrentPrice = l_quoteData.getCurrentPrice();
            
            //1.14 getChange()
            double l_dblChange = l_quoteData.getChange();
            
            //1.15 getCurrentPriceTime()
            Timestamp l_currentPriceTime = l_quoteData.getCurrentPriceTime();

            //1.16 発注日を取得する            
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            log.debug("l_datBizDate =" + l_datBizDate);    

            //1.17.getＷ指値用有効状態区分(IfoOrderUnit)
            String l_strWlimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit) l_orderUnit);
            
            //1.18.getＷ指値用切替前注文単価(IfoOrderUnit)
            String l_strWlimitBefChgLimitPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice((IfoOrderUnit) l_orderUnit);
            
            //1.19.getＷ指値用切替前執行条件(IfoOrderUnit)
            String l_strWLimitBefChgExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType((IfoOrderUnit) l_orderUnit);
            
            //1.20 プロパティセット
            // レスポンス.取引区分 = 注文単位.注文種別         
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                // 注文単位.注文種別 = "605"（OP新規買建注文）=> "3"
                l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_LONG_CONTRACT;
            }
            else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                // 注文単位.注文種別 = "606"（OP新規売建注文）=> "4"
                l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_SHORT_CONTRACT;
            }
            else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                // 注文単位.注文種別 = "608"（OP買建返済注文）=> "5"
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_SELL_CONTRACT;
            }
            else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                // 注文単位.注文種別 = "607"（OP売建返済注文）=> "6"
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_BUY_CONTRACT;
            }

            // レスポンス.取引市場 = 先物OP銘柄
            l_response.marketCode = l_ifoProduct.getPrimaryMarket().getMarketCode();
  
            // レスポンス.指数種別 = 先物OP銘柄.原資産銘柄コード
            l_response.targetProductCode = l_ifoProduct.getUnderlyingProductCode();
            
            // レスポンス.限月 = 先物OP銘柄.限月           
            l_response.delivaryMonth = ((IfoProductRow)l_ifoProduct.getDataSourceObject()).getMonthOfDelivery();

            // レスポンス.オプション商品区分 = 先物OP銘柄
            // P：プットオプション C：コールオプション
            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(
                ((IfoProductRow) l_ifoProduct.getDataSourceObject()).getDerivativeType()))
            {
                l_response.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;                           
            }
            else
            {
                l_response.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;            
            }            
            
            // レスポンス.行使価格 = 先物OP銘柄.行使価格
            double l_dblStrikePrice = ((IfoProductRow)l_ifoProduct.getDataSourceObject()).getStrikePrice();
            if (Double.isNaN(l_dblStrikePrice))
            {
                l_dblStrikePrice = 0D;
            }
            l_response.strikePrice = WEB3StringTypeUtility.formatNumber(l_dblStrikePrice);

            // レスポンス.注文数量 = 注文単位.注文数量
            double l_dblIfoQuantity = l_ifoOrderUnitRow.getQuantity();
            if (Double.isNaN(l_dblIfoQuantity))
            {
                l_dblIfoQuantity = 0D;
            }
            l_response.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblIfoQuantity);

            // レスポンス.内約定数量 = 注文単位.約定数量
            l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getExecutedQuantity());
            
            // レスポンス.注文単価区分 = （注文単位.指値==0の場合は”成行”、以外”指値”）
            // レスポンス.注文単価 = 注文単位.指値
            if (l_ifoOrderUnitRow.getLimitPrice() == 0)
            {
                l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.limitPrice = null;             
            }
            else 
            {
                l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;         
                l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getLimitPrice());             
            }
              
        	//レスポンス.執行条件 = 先物OPデータアダプタ.get執行条件（PR層）(注文単位.執行条件)の戻り値
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();
            l_response.execCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
        		l_orderUnitRow.getExecutionConditionType());
           
            //レスポンス.注文期限区分 = 先物OPデータアダプタ.get注文期限区分(注文単位)
            l_response.expirationDateType = WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);

            //レスポンス.注文有効期限 = 先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値が"出来るまで注文"の
            // 場合のみ、注文単位.注文失効日をセット。以外の場合、nullをセット。
            if ((WEB3OrderExpirationDateTypeDef.CARRIED_ORDER).equals(
                WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit)))
            {
                l_response.expirationDate = WEB3DateUtility.toDay(l_ifoOrderUnitRow.getExpirationDate());
            }
            else
            {
                l_response.expirationDate = null;
            }
            
            //レスポンス.注文条件区分 = 注文単位.発注条件           
            String l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
            l_response.orderCondType = l_strOrderConditionType;                    
             
            //(**1) 注文単位.発注条件 == ”逆指値”の場合のみセット。以外null。
            //(**2) 注文単位.発注条件 == ”W指値”の場合のみセット。以外null。                                 
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {   
                //レスポンス.逆指値用プレミアム／原資産価格 = (**1)注文単位.逆指値注文タイプ  
                l_response.stopPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();   

                //レスポンス.逆指値用注文条件単価 = (**1)注文単位.逆指値基準値
                l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

                //レスポンス.逆指値用注文条件演算子 = (**1)注文単位.発注条件演算子
                l_response.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                
                l_response.wlimitOrderCondPrice = null;
                l_response.wlimitOrderCondOperator = null;
                l_response.wLimitOrderPriceDiv = null;
                l_response.wLimitPrice = null;
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
            {          
                l_response.stopPremium_underlyingAssets = null;
                l_response.stopOrderCondPrice = null;                
                l_response.stopOrderCondOperator = null;               
                
                //レスポンス.W指値用プレミアム／原資産価格 = (**2)注文単位.逆指値注文タイプ
                l_response.wlimitPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType(); 

                //レスポンス.W指値用注文条件単価 = (**2)注文単位.逆指値基準値
                l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());                       
                         
                //レスポンス.W指値用注文条件演算子 = (**2)注文単位.発注条件演算子
                l_response.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();

                //レスポンス.W指値用注文単価区分 = (**2)注文単位.W指値用訂正指値==0の場合”成行”、以外”指値”
                //レスポンス.W指値用注文単価 = (**2)注文単位.W指値用訂正指値
                if (l_ifoOrderUnitRow.getWLimitPrice() == 0)
                {
                    l_response.wLimitOrderPriceDiv =  WEB3OrderPriceDivDef.MARKET_PRICE;
                    l_response.wLimitPrice = null;    
                }
                else
                {
                    l_response.wLimitOrderPriceDiv =  WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());                         
                }   
                
                //レスポンス.W指値用執行条件 = 先物OPデータアダプタ.get執行条件
                //（PR層）(注文単位.（W指値）執行条件)の戻り値
                l_response.wlimitExecCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
            		l_orderUnitRow.getWLimitExecCondType());              
            }            
            
			//レスポンス.W指値用有効状態区分＝先物OPデータアダプタ.getＷ指値用有効状態区分(）の戻り値
            l_response.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;
            
			//レスポンス.W指値用切替前注文単価＝先物OPデータアダプタ.getＷ指値用切替前注文単価()の戻り値
            l_response.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;
            
			//レスポンス.W指値用切替前執行条件＝先物OPデータアダプタ.getＷ指値用切替前執行条件()の戻り値
            l_response.wlimitBefChgExecCondType = l_strWLimitBefChgExecCondType;
            
			//レスポンス.元発注条件区分＝注文単位.元発注条件
            l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
            
			//レスポンス.元プレミアム／原資産価格＝注文単位.元逆指値基準値タイプ
            l_response.orgPremium_underlyingAssets = l_orderUnitRow.getOrgStopPriceType();
            
			//レスポンス.元発注条件単価＝注文単位.元逆指値基準値
            if (!l_orderUnitRow.getOrgStopOrderPriceIsNull())
            {
                l_response.orgOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                    l_orderUnitRow.getOrgStopOrderPrice());
            }
            
			//レスポンス.元発注条件演算子＝注文単位.元発注条件演算子
            l_response.orgCondOperator = l_orderUnitRow.getOrgOrderCondOperator();
            
			//レスポンス.元Ｗ指値用注文単価区分＝先物OPデータアダプタ.get元W指値用注文単価区分(注文単位)
            l_response.orgWLimitOrderPriceDiv = 
            	WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv((IfoOrderUnit) l_orderUnit);
            
			//レスポンス.元Ｗ指値用注文単価＝元Ｗ指値用注文単価区分が"指値"の
            //場合のみ、先物OPデータアダプタ.get元Ｗ指値用注文単価(注文単位)
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orgWLimitOrderPriceDiv))
            {
            	l_response.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice((IfoOrderUnit) l_orderUnit);
            }
		
            //レスポンス.元Ｗ指値用執行条件＝先物OPデータアダプタ.get元W指値用執行条件(注文単位)
            l_response.orgWlimitExecCondType = 
            	WEB3IfoDataAdapter.getOrgWLimitExecCondType((IfoOrderUnit) l_orderUnit);
            
            //レスポンス.概算受渡代金 = 注文単位.概算受渡代金
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
        		l_ifoOrderUnitRow.getEstimatedPrice());

            //レスポンス.取引終了警告文言 = 取引時間管理.get市場閉局警告指数()の戻り値
            l_response.messageSuspension = l_strTradeCloseSuspension;

            //レスポンス.確認時発注日 = 取引時間管理.get発注日()の戻り値
            l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);

            //レスポンス.決済順序 = 注文単位.決済順序
            l_response.closingOrder = l_ifoOrderUnitRow.getClosingOrder();

            //レスポンス.建玉明細 = （create建玉明細ByOrder()の戻り値      
            l_response.contractUnits = l_web3ContractUnits;

            //getCurrentPriceの返り値                                   
            //時価が0の場合、nullを設定する                                 
            if (l_dblCurrentPrice == 0D)                                    
            {                                   
                l_response.currentPrice = null;                                       
            }                                   
            else                                    
            {                                   
                //時価が0でない場合、取得した現在値を設定する                                     
                l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);                                      
            }                                   
            
            //レスポンス.前日比 = （getChange()の戻り値）
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

            //レスポンス.取引時間 = （getCurrentPriceTime()の戻り値）
            l_response.currentPriceTime = l_currentPriceTime;

            //レスポンス.立会区分 = 注文単位.get立会区分()
            l_response.sessionType = l_ifoOrderUnitRow.getSessionType();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
    }
    
    /**
     * (submit注文)<BR>
     * 株価指数オプションの取消注文を登録する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP取消サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション取消注文完了リクエスト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 405169280137
     */
    protected WEB3OptionsCancelCompleteResponse submitOrder(
        WEB3OptionsCancelCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3OptionsCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
            
        if (l_request == null)
        {   
            throw new WEB3BaseException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
 
        //1.1 リクエストデータのチェックを実施する
        l_request.validate();

        //1.2 発注日を取得する。
        //  [get発注日()に指定する引数]
        //  確認時発注日：リクエストデータ.確認時発注日
        Date l_datBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }

        //1.3 取消対象の注文ＩＤを指定し、取消注文内容（CancelOrderSpec）を生成する
        long l_lngOrderId = Long.parseLong(l_request.id);
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngOrderId);
 
        //1.4 補助口座を取得する
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        log.debug("l_subAccount = " + l_subAccount);    

        //1.5 取消注文発注審査を行う
        //OP注文マネージャを取得する
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderValidationResult l_result = l_orderManager.validateCancelOrder(
            l_subAccount,
            l_cancelOrderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);        
        } 

        //1.6 get代理入力者()
        Trader l_trader = this.getTrader();

        //1.7 先物OP取消更新インタセプタを生成する
        WEB3IfoCancelUpdateInterceptor l_interceptor = new WEB3IfoCancelUpdateInterceptor();

        //1.8 インタセプタ.取引者ID = 代理入力者.getTraderId()の戻り値
        long l_lngTraderID = 0;
        if (l_trader != null)
        {
            l_lngTraderID = l_trader.getTraderId();
        }
        l_interceptor.setTraderId(l_lngTraderID);

        //1.9 インタセプタをセットする
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //1.10 submitCancelOrder()
        OrderSubmissionResult l_orderResult = null;
        l_orderResult = l_orderManager.submitCancelOrder(
            l_subAccount,
            l_cancelOrderSpec,       
            l_request.password,
            true);
        if (l_orderResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_orderResult.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_orderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //補助口座 != 証拠金口座の場合、取引余力サービス.余力再計算()をcallする。
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
            log.debug("口座タイプ：　@" + l_subAccount.getSubAccountType());
            //1.11 [余力再計算()に指定する引数]
            //  補助口座：　@補助口座
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            l_tradingPowerService.reCalcTradingPower(l_subAccount);
        }

        //getOrderUnits(注文ID : long)
        //注文ID：　@リクエストデータ.注文ID
        IfoOrderUnit l_ifoOrderUnit =
            (IfoOrderUnit)(l_orderManager.getOrderUnits(Long.parseLong(l_request.id)))[0];

        //is予約注文確認要(IfoOrderUnit)
        boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist(l_ifoOrderUnit);

        //予約注文確認要（is予約注文確認要() == true）の場合
        boolean l_blnCancelAllOrderUnit = false;
        if (l_blnIsReserveOrderExist)
        {
            //cancelAll予約注文単位(親注文の注文ID : long)
            WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationIfoOrderUpdateService.class);
            l_blnCancelAllOrderUnit = l_ifoOrderUpdateService.cancelAllOrderUnit(l_ifoOrderUnit.getOrderId());
        }

        //1.14 レスポンスデータ生成
        WEB3OptionsCancelCompleteResponse l_response =
            (WEB3OptionsCancelCompleteResponse)l_request.createResponse();

        //1.15 (*2)プロパティセット
        //  レスポンス.更新時間 = 現在日時(GtlUtils.getSystemTimestamp())
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        //  レスポンス.識別番号 = リクエストデータ.注文ID
        l_response.orderActionId = l_request.id;
        //  レスポンス.連続注文設定フラグ = cancelAll予約注文単位の戻り値
        l_response.succSettingFlag = l_blnCancelAllOrderUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * 株価指数オプション取消注文<BR>サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()または、<BR>submit注文()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 405172F801E3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3OptionsCancelConfirmRequest)
        {
            l_response = this.validateOrder((WEB3OptionsCancelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3OptionsCancelCompleteRequest)
        {
            l_response = this.submitOrder((WEB3OptionsCancelCompleteRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
