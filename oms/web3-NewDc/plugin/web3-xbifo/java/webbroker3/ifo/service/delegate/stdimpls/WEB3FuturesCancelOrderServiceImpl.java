head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物取消注文サービス実装クラス(WEB3FuturesCancelOrderServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/19 盧法@旭 (中訊) 新規作成
              001: 2004/08/05 王暁傑 (中訊) Review 修正
              002: 2006/07/28 柴雙紅 (中訊) 仕様変更 モデル493
Revesion History : 2007/06/21 張騰宇 (中訊)仕様変更 モデル710
Revesion History : 2008/03/14 張騰宇 (中訊)仕様変更 モデル831 859
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
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
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoCancelUpdateInterceptor;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3FuturesCancelCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCancelCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesCancelOrderService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (先物取消注文サービスImpl)<BR>
 * 株価指数先物取消注文サービス実装クラス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3FuturesCancelOrderServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesCancelOrderService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3FuturesCancelOrderServiceImpl.class);

    /**
     * @@roseuid 40F7A2C40157
     */
    public WEB3FuturesCancelOrderServiceImpl()
    {

    }

    /**
     * (validate注文)<BR>
     * 株価指数先物の取消発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物取消サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数先物取消注文確認リクエスト<BR>
     * @@return WEB3FuturesCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8291800FC
     */
    protected WEB3FuturesCancelConfirmResponse validateOrder(WEB3FuturesCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateOrder(WEB3FuturesCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        log.debug("シーケンス図「（先物取消サービス）validate注文」参照");
        //1.1 validate()
        l_request.validate();
        
        //1.2 CancelOrderSpec(long)
        long l_lngOrderId = Long.parseLong(l_request.id);
        CancelOrderSpec l_orderSpec = new CancelOrderSpec(l_lngOrderId);

        //1.3 get補助口座()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.4  validate先物取消注文(SubAccount, CancelOrderSpec)
        //FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager = 
            (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();
        OrderValidationResult l_result = 
            l_orderManager.validateFuturesCancelOrder(l_subAccount,l_orderSpec);
        if(l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //1.5 createResponse()
        WEB3FuturesCancelConfirmResponse l_response = 
            (WEB3FuturesCancelConfirmResponse)l_request.createResponse();

        //1.6 IfoOrderImpl(long)
        IfoOrderImpl l_orderImpl = null;
        try 
        {
            l_orderImpl = new IfoOrderImpl(l_lngOrderId);
        }
        catch (DataQueryException l_ex) 
        {
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + l_ex.getMessage()); 
        } 
        catch (DataNetworkException l_ex) 
        {
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + l_ex.getMessage());
        }

        //1.7 getOrderUnits()
        OrderUnit l_orderUnit = l_orderImpl.getOrderUnits()[0];

        //1.8 getProduct()
        IfoProduct l_product = (IfoProduct)l_orderUnit.getProduct();

        //1.9 create建玉明細ByOrder(long)
        WEB3FuturesOptionsContractUnit[] l_createContractUnitByOrder = l_orderManager.createContractUnitByOrder(l_lngOrderId);

        //1.10 get市場閉局警告指数(部店, String)
        String[] l_strWarningIndex = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
            l_subAccount.getWeb3GenBranch(),
            WEB3FuturesOptionDivDef.FUTURES);
        
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
        
        //1.16 get発注日
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("get発注日:" + l_datOrderBizDate);

        //1.17  プロパティセット
        // レスポンス.取引区分 = （次の判定を行う）
        OrderTypeEnum l_strOrderType = l_orderUnit.getOrderType();
        if(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_strOrderType))
        {
            //注文単位.注文種別==601（先物新規買建注文）の場合、"3"
            l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_LONG_CONTRACT;            
        }
        else if(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_strOrderType))
        {
            //注文単位.注文種別==602（先物新規売建注文）の場合、"4"
            l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_SHORT_CONTRACT;
        }
        else if(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_strOrderType))
        {
            //注文単位.注文種別==604（先物買建返済注文）の場合、"5"
            l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_SELL_CONTRACT;
        }
        else if(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_strOrderType))
        {
            //注文単位.注文種別==603（先物売建返済注文）の場合、"6"
            l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_BUY_CONTRACT;
        }

        // レスポンス.取引市場 = 注文単位.市場コード(SONAR)
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        l_response.marketCode = l_ifoOrderUnitRow.getSonarMarketCode();

        // レスポンス.指数種別 = 先物OP銘柄.原資産コード
        l_response.targetProductCode = l_product.getUnderlyingProductCode();

        // レスポンス.限月 = 先物OP銘柄.限月
        l_response.delivaryMonth = l_product.getMonthOfDelivery();

        // レスポンス.注文数量 = 注文単位.注文数量
        double l_orderQuantity = l_orderUnit.getQuantity();
        if(Double.isNaN(l_orderQuantity))
        {
            l_orderQuantity = 0;
        }
        l_response.futOrderQuantity = WEB3StringTypeUtility.formatNumber(l_orderQuantity);

        // レスポンス.内約定数量 = 注文単位.内約定数量
        l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getExecutedQuantity());
 
        // レスポンス.注文単価区分 = （注文単位.指値==0の場合は”成行”、以外”指値”）
        // レスポンス.注文単価 = 注文単位.指値
        if(l_ifoOrderUnitRow.getLimitPrice() == 0) 
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE; 
            l_response.limitPrice = null;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_response.limitPrice =  WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getLimitPrice());
        }

        //レスポンス.執行条件   
        l_response.execCondType = 
            WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getExecutionConditionType());

        // レスポンス.注文期限区分 = 先物OPデータアダプタ.get注文期限区分(注文単位)
        // レスポンス.注文有効期限 = 先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値が
        //　@　@　@　@　@　@　@　@"出来るまで注文"の場合のみ、注文単位.注文失効日をセット。
        String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);
        l_response.expirationDateType = l_strExpirationDateType;
        if(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
        {
            l_response.expirationDate = WEB3DateUtility.toDay(l_ifoOrderUnitRow.getExpirationDate());
        }        
        else
        {
            l_response.expirationDate = null;
        }

        // レスポンス.注文条件区分 = 注文単位.発注条件
        l_response.orderCondType = l_ifoOrderUnitRow.getOrderConditionType();
        
        String l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {                             
            // レスポンス.逆指値用注文条件単価 = (**1)注文単位.逆指値基準値
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

            // レスポンス.逆指値用注文条件演算子 = (**1)注文単位.発注条件演算子
            l_response.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                
            l_response.wlimitOrderCondPrice = null;
            l_response.wlimitOrderCondOperator = null;
            l_response.wLimitOrderPriceDiv = null;
            l_response.wLimitPrice = null;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {          
            l_response.stopOrderCondPrice = null;                
            l_response.stopOrderCondOperator = null;               
           
            // レスポンス.W指値用注文条件単価 = (**2)注文単位.逆指値基準値
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());                       
                         
            // レスポンス.W指値用注文条件演算子 = (**2)注文単位.発注条件演算子
            l_response.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
            
            // レスポンス.W指値用注文単価区分 = (**2)注文単位.W指値用訂正指値==0の場合”成行”、以外”指値”
            // レスポンス.W指値用注文単価 = (**2)注文単位.W指値用訂正指値
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
            
            //Ｗ指値用執行条件
            l_response.wlimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getWLimitExecCondType()); 
        } 
        
        IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
        
        //レスポンス.Ｗ指値用有効状態区分
        l_response.wlimitEnableStatusDiv = 
            WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_ifoOrderUnit);
        
        //レスポンス.Ｗ指値用切替前注文単価
        l_response.wlimitBefChgLimitPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_ifoOrderUnit);  
        
        //レスポンス.Ｗ指値用切替前執行条件
        l_response.wlimitBefChgExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_ifoOrderUnit);
        
        //レスポンス.元発注条件区分 = 注文単位.発注条件
        l_response.orgOrderCondType = l_ifoOrderUnitRow.getOrgOrderConditionType();
  
        //レスポンス.元発注条件単価 = 注文単位.元逆指値基準値
        if(!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = 
                WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getOrgStopOrderPrice());
        }

        //レスポンス.元発注条件演算子 = 注文単位.元発注条件演算子
        l_response.orgCondOperator = l_ifoOrderUnitRow.getOrgOrderCondOperator();  
        
        //レスポンス.元W指値用注文単価区分
        String l_strOrgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_ifoOrderUnit);
        l_response.orgWLimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
               
        //レスポンス.元W指値用注文単価
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))    
        {
            l_response.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_ifoOrderUnit);
        }
               
        //レスポンス.元W指値用執行条件
        l_response.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_ifoOrderUnit);    
            
        // レスポンス.概算建代金（決済損益） = 注文単位.概算受渡代金
        l_response.estimatedContractPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());

        // レスポンス.取引終了警告文言 = 取引時間管理.get市場閉局警告指数()の戻り値
        l_response.messageSuspension = l_strWarningIndex;

        // レスポンス.確認時発注日 = 取引時間管理.get発注日()の戻り値
        l_response.checkDate =  WEB3DateUtility.toDay(l_datOrderBizDate);

        // レスポンス.決済順序 = 注文単位.決済順序
        l_response.closingOrder = l_ifoOrderUnitRow.getClosingOrder();

        // レスポンス.建玉明細 = （create建玉明細ByOrder()の戻り値）
        l_response.contractUnits = l_createContractUnitByOrder;

        // レスポンス.現在値 = （getCurrentPrice()の戻り値）
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
            
        // レスポンス.前日比 = （getChange()の戻り値）
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

        // レスポンス.取引時間 = （getCurrentPriceTime()の戻り値）
        l_response.currentPriceTime = l_currentPriceTime;

        //レスポンス.立会区分 = 注文単位.立会区分
        l_response.sessionType = l_ifoOrderUnitRow.getSessionType();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * 株価指数先物の取消注文を登録する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物取消サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数先物取消注文完了リクエスト<BR>
     * @@return WEB3FuturesCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A82918011B
     */
    protected WEB3FuturesCancelCompleteResponse submitOrder(WEB3FuturesCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitOrder(WEB3FuturesCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate
        l_request.validate();

        //1.2 get発注日()
        // リクエストデータ.確認時発注日!=nullの場合、コール。
        Date l_datOrderBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }

        //1.3 CancelOrderSpec(long)
        String l_strOrderId = l_request.id;
        long l_lngOrderId = Long.parseLong(l_strOrderId);
        CancelOrderSpec l_orderSpec = new CancelOrderSpec(l_lngOrderId);

        //1.4 get補助口座()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        //1.5 validate取消注文(SubAccount, CancelOrderSpec)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager = (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();
        OrderValidationResult l_orderResult = l_orderManager.validateFuturesCancelOrder(l_subAccount,l_orderSpec);
        if(l_orderResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_orderResult.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_orderResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //1.6 get代理入力者()
        Trader l_trader = this.getTrader();
        
        //1.7 先物OP取消更新インタセプタ
        WEB3IfoCancelUpdateInterceptor l_interceptor = new WEB3IfoCancelUpdateInterceptor();

        //1.8 インタセプタ.取引者ID = 代理入力者.getTraderId()の戻り値
        long l_lngTraderID = 0;
        if (l_trader != null)
        {
            l_lngTraderID = l_trader.getTraderId();
        }
        l_interceptor.setTraderId(l_lngTraderID);

        //1.9 setThreadLocalPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //1.10 submitCancelOrder()
        OrderSubmissionResult l_result = l_orderManager.submitCancelOrder(
            l_subAccount,
            l_orderSpec,
            l_request.password,
            true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //getOrderUnits(注文ID : long)
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id));
        IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnits[0];

        //is予約注文確認要(IfoOrderUnit)
        boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist(l_ifoOrderUnit);

        boolean l_blnCancelAllOrderUnit = false;
        //約注文確認要（is予約注文確認要()==true）の場合
        if (l_blnIsReserveOrderExist)
        {
            WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationIfoOrderUpdateService.class);
            //cancelAll予約注文単位(親注文の注文ID : long)
            l_blnCancelAllOrderUnit =
                l_ifoOrderUpdateService.cancelAllOrderUnit(l_ifoOrderUnit.getOrderId());
        }

        //1.11 createResponse()
        WEB3FuturesCancelCompleteResponse l_response = (WEB3FuturesCancelCompleteResponse)l_request.createResponse();

        if (l_result.getProcessingResult().isSuccessfulResult())
        {
            // レスポンス.更新時間 = 現在日時(GtlUtils.getSystemTimestamp())
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            
            // レスポンス.識別番号 = リクエストデータ.注文ID
            l_response.orderActionId = l_request.id;

            // レスポンス.連続注文設定フラグ = cancelAll予約注文単位の戻り値
            l_response.succSettingFlag = l_blnCancelAllOrderUnit;
        }
        else
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * 株価指数先物取消注文サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()または、submit注文()メソッドをコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A82918012B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3FuturesCancelConfirmRequest)
        {
            l_response = this.validateOrder((WEB3FuturesCancelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3FuturesCancelCompleteRequest)
        {
            l_response = this.submitOrder((WEB3FuturesCancelCompleteRequest)l_request);
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
