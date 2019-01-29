head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopIfoOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 逆指値注文先物OP発注一件サービスImpl(WEB3ToStopIfoOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/23 呉艶飛(中訊) 新規作成
Revesion History : 2006/8/24 唐性峰(中訊) モデルNo.164 對應 
Revesion History : 2007/1/30 崔遠鵬(中訊) モデルNo.212 對應
Revesion History : 2007/1/31 崔遠鵬(中訊) モデルNo.218 對應
Revesion History : 2007/1/31 崔遠鵬(中訊) DB更新仕様No.036 對應
Revesion History : 2007/6/30 孟亜南(中訊) モデルNo.239
Revesion History : 2008/4/10 趙林鵬(中訊) モデルNo.275
Revesion History : 2009/11/19 車進  (中訊) 【トリガー注文・株管理者】逆指値注文発注障害対応

*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoOpenContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToStopIfoOrderUpdateInterceptor;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopIfoOrderUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (逆指値注文先物OP発注一件サービスImpl)<BR>
 * 逆指値注文先物OP発注一件サービス実装クラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3ToStopIfoOrderUnitServiceImpl implements
        WEB3ToStopIfoOrderUnitService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopIfoOrderUnitServiceImpl.class);
    
    public WEB3ToStopIfoOrderUnitServiceImpl() 
    {
     
    }

    /**
     * (submit先物新規建逆指値注文)<BR>
     * 先物新規建逆指値注文を発注する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（逆指値注文先物OP発注一件サービス）submit先物新規建逆指値注文」参照。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void submitFuturesOpenContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitFuturesOpenContractStopOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        //引数で渡された注文単位は更新されている可能性があるので、再取得を行うこと。        
        try
        {
            l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文単位テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        try
        {
            //1.1:is処理対象(IfoOrderUnit)
            boolean l_blnIsProcessObj = this.isProcessObject(l_orderUnit);
            
            //1.2:（分岐フロー：　@is処理対象＝false（処理対象外注文）の場合）
            if (!l_blnIsProcessObj)
            {
                //1.2.1:処理対象外の場合、何もせずそのままreturnする。
                //（正常ステータスで終了）
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            //1.3:get発注日(確認時発注日 : Date,立会区分 : String)
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"),
                l_orderUnitRow.getSessionType());
                
            //1.4:getSubAccount(arg0 : long, arg1 : long)
            WEB3GentradeAccountManager l_accountMgr =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            WEB3GentradeSubAccount l_subAccount = null;            
            try
            {
                l_subAccount = (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(
                    l_orderUnit.getAccountId(), 
                    l_orderUnit.getSubAccountId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("補助口座テーブルに該当するデータがありません。", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            WEB3GentradeFinObjectManager l_finObjectMgr =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
            //1.5: （分岐フロー：注文単位.取引者ID≠nullの場合のみ）
            Trader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                //1.5.1: getTrader(arg0 : long)
                try
                {
                    l_trader = l_finObjectMgr.getTrader(l_orderUnit.getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("扱者テーブルに該当するデータがありません。", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //1.6:create新規建注文内容(証券会社コード : String, 扱者 : 扱者, 
            //is買建 : boolean, 市場コード : String, 銘柄 : 先物OP銘柄, 
            //数量 : double, 指値 : double, 執行条件 : IfoOrderExecutionConditionType,
            // 注文失効日 : Date, 発注条件 : String, (W指値)訂正指値 : double, 注文期限区分 : 
            //String, 初回注文の注文単位ID : Long, 夕場前繰越対象フラグ : boolean)
            
            //is買建：　@
            //  注文単位.getSide()＝SideEnum.BUY(買)の場合、trueをセット。
            //  注文単位.getSide()＝SideEnum.SELL(売)の場合、falseをセット。
            boolean l_blnIsBuy = false;
            
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuy = true;
            }
            
            //市場コード：　@注文単位.市場IDに該当する市場オブジェクト.市場コード
            String l_strMarketCode = null;
            
            if (!l_orderUnitRow.getMarketIdIsNull())
            {
                try
                {
                    Market l_market = l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());//NotFoundException
                    l_strMarketCode = l_market.getMarketCode();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("市場テーブルに該当するデータがありません。", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //銘柄：　@注文単位.銘柄IDに該当する先物OP銘柄オブジェクト
            WEB3IfoProductManagerImpl l_productMgr = 
                (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            WEB3IfoProductImpl l_product = null;
                
            try
            {
                l_product = (WEB3IfoProductImpl) l_productMgr.getProduct(l_orderUnitRow.getProductId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("銘柄テーブルに該当するデータがありません。", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //注文期限区分 = 先物OPデータアダプタ.get注文期限区分(注文単位)
            String l_strOrderExpirationType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);
            //初回注文の注文単位ID = 注文単位.初回注文の注文単位ID
            long l_lngFirstOrderUnitId = l_orderUnitRow.getFirstOrderUnitId();
            //夕場前繰越対象フラグ = 先物OPデータアダプタ.get夕場前繰越対象フラグ（PR層）(注文単位)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            Long l_firstOrderUnitId = null;
            if (!l_orderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_lngFirstOrderUnitId);
            }
            Institution l_institution = l_subAccount.getInstitution();
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                l_trader,
                l_blnIsBuy,
                l_strMarketCode,
                l_product,
                l_orderUnit.getQuantity(), 
                l_orderUnit.getLimitPrice(),
                l_orderUnit.getExecutionConditionType(),
                l_orderUnitRow.getExpirationDate(),
                l_orderUnitRow.getOrderConditionType(),
                0,
                0,
                null,
                l_strOrderExpirationType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryoverFlag);
                
            //1.7:validate先物新規建注文(補助口座 : SubAccount, 新規建注文内容 : IfoOpenContractOrderSpec, 注文単位 : IfoOrderUnit)                
            NewOrderValidationResult l_validationResult = l_orderMgr.validateFuturesOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                l_orderUnit);
                
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("validate先物新規建注文が失敗の場合。");
                    
                throw new WEB3BaseException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.8:create手数料(注文単位ID : long)            
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = 
                (WEB3IfoBizLogicProvider) l_tradingModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission = l_ifoBizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
            
            //1.9:get取引銘柄(証券会社 : Institution, 銘柄コード : String, 市場コード : String)
            WEB3IfoTradedProductImpl l_tradedProduct = null;
            try
            {
                l_tradedProduct = l_productMgr.getIfoTradedProduct(
                    l_institution,
                    l_product.getProductCode(),
                    l_strMarketCode);//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("取引銘柄テーブルに該当するデータがありません。", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.10:calc概算建代金(手数料 : 手数料, 指値 : double, 補助口座 : SubAccount, 先物OP取引銘柄 : 先物OP取引銘柄, 
            //数量 : double, isSkip金額チェック : boolean)
            WEB3IfoEstimateDeliveryAmountCalcResult l_caclResult = 
                l_orderMgr.calcEstimatePrice(
                    l_commission,
                    l_openContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_openContractOrderSpec.getQuantity(),
                    false);

            //1.11:create新規建訂正内容(注文単位 : IfoOrderUnit)
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = this.createOpenContractChangeSpec(l_orderUnit);

			
            //1.12:先物OP新規建訂正更新インタセプタ(新規建訂正内容 : 新規建訂正内容，注文経路区分：注文単位.注文経路区分)
            WEB3IfoOpenContractChangeUpdateInterceptor l_ifoOpenContractChangeUpdateInterceptor
                = new WEB3IfoOpenContractChangeUpdateInterceptor(l_ifoOpenContractChangeSpec);

            //1.13:プロパティセット
            //手数料をセットする
            l_ifoOpenContractChangeUpdateInterceptor.setCommision(l_commission);

            //概算受渡代金計算結果をセットする
            l_ifoOpenContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_caclResult);

            //発注条件をセットする
            l_ifoOpenContractChangeUpdateInterceptor.setOrderCond(l_orderUnitRow.getOrderConditionType());

            //発注条件演算子をセットする
            l_ifoOpenContractChangeUpdateInterceptor.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());

            //逆指値基準値タイプをセットする
            l_ifoOpenContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_orderUnitRow.getStopPriceType());

            //逆指値基準値をセットする
            l_ifoOpenContractChangeUpdateInterceptor.setStopOrderBasePrice(l_orderUnitRow.getStopOrderPrice());

            //（W指値）訂正指値
            double l_wLimitPrice = 0D;
            l_ifoOpenContractChangeUpdateInterceptor.setWLimitPriceChange(l_wLimitPrice);

            //取引者ID
            long l_traderId = 0L;
            if (l_trader != null)
            {
                l_traderId = l_trader.getTraderId();
            }
            l_ifoOpenContractChangeUpdateInterceptor.setTraderId(l_traderId);

            //注文経路区分をセットする
			l_ifoOpenContractChangeUpdateInterceptor.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());
			
            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_ifoOpenContractChangeUpdateInterceptor;

            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_ifoOpenContractChangeSpec;

            //1.14:validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            WEB3TPTradingPowerResult l_result = 
                l_tradingPowerService.validateTradingPower
                (l_subAccount,
                 l_interceptorObject,
                 l_specObject,
                 l_orderUnitRow.getOrderType(),
                 true);

            //1.15:throw余力エラー情報(取引余力結果 : 取引余力結果, 補助口座 : 補助口座)(先物注文マネージャ::throw余力エラー情報)
            l_orderMgr.throwTpErrorInfo(l_result,l_subAccount);

            //1.16:(実行結果に応じて注文系データをUPDATEする)
            //1.16.1:(*)正常終了した場合
            //1.16.1.1:update注文データ(IfoOrderUnit, double, double, String)
            WEB3IfoFrontOrderService l_ifoOrderService = (WEB3IfoFrontOrderService) Services
                .getService(WEB3IfoFrontOrderService.class);
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountMgr.getBranch(l_orderUnitRow.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());

            this.updateOrderData(
                l_orderUnit,
                l_caclResult.getCalcUnitPrice(),
                l_caclResult.getEstimateDeliveryAmount(),
                l_ifoOrderService.getSubmitOrderRouteDiv(l_branch.getInstitution().getInstitutionCode(),
                l_market.getMarketCode()));

            //1.16.1.2:insert新規建注文キュー(注文ID : long)
            l_orderMgr.insertOpenContractHostOrder(l_orderUnit.getOrderId());
        
            //1.16.1.3:sendMQトリガ(IfoOrderUnit)
            try
            {
                l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("注文単位テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        
            this.sendMQTrigger(l_orderUnit);
        }
        //1.16.2:(*)処理中に例外がスローされた場合
        catch (Exception l_ex)
        {
            //1.16.2.1:get注文エラー理由コード(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_errorReasonCode = l_orderMgr.getErrorReasonCode(l_errorInfo.getErrorCode());

            //1.16.2.2:逆指値注文先物OP発注更新インタセプタ(注文エラー理由コード : String)
            WEB3ToStopIfoOrderUpdateInterceptor l_updateInterceptor = 
                new WEB3ToStopIfoOrderUpdateInterceptor(l_errorReasonCode);

            //1.16.2.3:setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            //1.16.2.4:DefaultOrderInvalidatedMarketResponseMessage(arg0 : long)
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = 
                new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
                
            //1.16.2.5:process(arg0 : OrderInvalidatedMarketResponseMessage)
            MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                            
            IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();            
            
            try
            {
                ProcessingResult l_result = l_callBackService.process(l_responseMessage);
                if (l_result.isFailedResult())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        l_result.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "発注審査エラーとなった逆指値注文の失効処理に失敗しました。注文ID：[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }
            
            // is予約注文確認要(注文単位 : IfoOrderUnit)
            //注文単位：　@注文単位
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist(l_orderUnit);

            //予約注文確認要（is予約注文確認要() == true）の場合
            if (l_blnIsReserveOrderExist)
            {
                //invalidateAll予約注文単位(long)
                //親注文の注文ID：　@注文単位.注文ID
                WEB3ToSuccReservationIfoOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);

                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }
        }    
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit先物返済逆指値注文)<BR>
     * 先物返済逆指値注文を発注する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（逆指値注文先物OP発注一件サービス）submit先物返済逆指値注文」参照。<BR>
     *  ================================================================================ <BR>
     * 1.6.1返済可能数量チェック：　@<BR>
     * ３） 返済数量(*1) ＞ 返済可能建玉残高(*2)　@の場合、「返済可能残高数量超過エラー」<BR>
     * の例外をthrowする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00299<BR>
     *  ================================================================================ <BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void submitFuturesSettleContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitFuturesSettleContractStopOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        //引数で渡された注文単位は更新されている可能性があるので、再取得を行うこと。        
        try
        {
            l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文単位テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        try
        {
            //1.1:is処理対象(IfoOrderUnit)
            boolean l_blnIsProcessObj = this.isProcessObject(l_orderUnit);
            
            //1.2:（分岐フロー：　@is処理対象＝false（処理対象外注文）の場合）
            if (!l_blnIsProcessObj)
            {
                //1.2.1:処理対象外の場合、何もせずそのままreturnする。
                //（正常ステータスで終了）
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            //1.3:get発注日(確認時発注日 : Date,立会区分 : String)
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"),
                l_orderUnitRow.getSessionType());
             
            //1.4:getContractsToClose( )    
            if (!(l_orderUnit instanceof IfoContractSettleOrderUnit))
            {
                log.debug("先物OP注文単位のTYPEがIfoContractSettleOrderUnit以外です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "先物OP注文単位のTYPEがIfoContractSettleOrderUnit以外です。");
            }
            
            IfoContractSettleOrderUnit l_ifoContractSettleOrderUnit = 
                (IfoContractSettleOrderUnit) l_orderUnit;
            IfoClosingContractSpec[] l_contractSpecs = 
                l_ifoContractSettleOrderUnit.getContractsToClose();
            
            //1.5: ArrayList()
            List l_lisSettleContractEntries = new ArrayList();
            
            int l_intCnt = 0;
            if (l_contractSpecs != null && l_contractSpecs.length > 0)
            {
                l_intCnt = l_contractSpecs.length;
            }
            
            //1.6:getContractsToClose( )の戻り値（＝建玉返済指定情報）要素数(index)分、Loop）
            for (int i = 0; i < l_intCnt; i++)
            {            
                IfoClosingContractSpec l_contractSpec = l_contractSpecs[i];
                //1.6.1: (*)返済可能数量チェック            
                try
                {
                    //返済可能数量チェック：
                    //１）返済数量(*1)を取得する。
                    //    返済数量 ＝ 建玉返済指定情報[index].返済注文数量
                    double l_dblClosingContractQuantity = l_contractSpec.getQuantity();
                    
                    //２）返済可能建玉残高(*2)を算出する。（建玉は、建玉返済指定情報[index].建玉IDに該当する建玉オブジェクトを使用）
                    //　@　@返済可能建玉残高 ＝ 建玉.getQuantity()（＝建玉数量） － 建玉.getLockedQuantity()（＝ロック中数量） ＋ 
                    //    建玉.getロック中数量(注文単位ID)（＝当該注文ロック中数量）
                    
                    //1.6.1.1:先物OP建玉(建玉ＩＤ : long)
                    long l_lngContractId = l_contractSpec.getContractId();
                    WEB3IfoContractImpl l_contract = 
                        new WEB3IfoContractImpl(l_lngContractId);//DataNetworkException, DataQueryException
                    
                    //1.6.1.2: getQuantity( )
                    double l_dblContractQuantity = l_contract.getQuantity();
                    
                    //1.6.1.3:getLockedQuantity( )
                    double l_dblContractLockedQuantity = l_contract.getLockedQuantity();
                    
                    //1.6.1.4:getロック中数量(注文単位ＩＤ : long)
                    double l_dblContracLockedQuantityForOrderUnit = l_contract.getLockedQuantity(l_orderUnit.getOrderUnitId());
                    
                    double l_dblValue = l_dblContractQuantity - l_dblContractLockedQuantity + l_dblContracLockedQuantityForOrderUnit;
                    
                    //３） 返済数量(*1) ＞ 返済可能建玉残高(*2)　@の場合、「返済可能残高数量超過エラー」の例外をthrowする。
                    if (l_dblClosingContractQuantity > l_dblValue)
                    {
                        String l_strErrorMsg = "返済数量 ＞ 返済可能建玉残高(" + 
                            l_dblClosingContractQuantity + " > " + l_dblValue + ")";
                        log.debug(l_strErrorMsg);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strErrorMsg);
                    }                                
                    
                    //1.6.1.5: SettleContractEntry(arg0 : long, arg1 : double)
                    SettleContractEntry l_entry = new SettleContractEntry(
                        l_lngContractId, l_dblClosingContractQuantity);
                    
                    //1.6.1.6: add(arg0 : Object)
                    l_lisSettleContractEntries.add(l_entry);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            SettleContractEntry[] l_settleContractEntries = new SettleContractEntry[l_lisSettleContractEntries.size()];
            l_lisSettleContractEntries.toArray(l_settleContractEntries);
            
            //1.7:getSubAccount(arg0 : long, arg1 : long)
            WEB3GentradeAccountManager l_accountMgr =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            WEB3GentradeSubAccount l_subAccount = null;            
            try
            {
                l_subAccount = (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(
                    l_orderUnit.getAccountId(), 
                    l_orderUnit.getSubAccountId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("補助口座テーブルに該当するデータがありません。", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            WEB3GentradeFinObjectManager l_finObjectMgr =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
            //1.8: （分岐フロー：注文単位.取引者ID≠nullの場合のみ）
            Trader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                //1.8.1: getTrader(arg0 : long)
                try
                {
                    l_trader = l_finObjectMgr.getTrader(l_orderUnit.getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("扱者テーブルに該当するデータがありません。", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //1.9:create返済注文内容(証券会社コード : String, 扱者 : 扱者, 
            //指値 : double, 執行条件 : IfoOrderExecutionConditionType, 
            //注文失効日 : Date, 返済建玉エントリ : SettleContractOrderEntry[], 
            //発注条件 : String, (W指値)訂正指値 : double, 注文期限区分 : String, 
            //初回注文の注文単位ID : Long, 夕場前繰越対象フラグ : boolean)                
            
            //注文期限区分 = 先物OPデータアダプタ.get注文期限区分(注文単位)
            String l_strOrderExpirationType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);
            //初回注文の注文単位ID = 注文単位.初回注文の注文単位ID
            long l_lngFirstOrderUnitId = l_orderUnitRow.getFirstOrderUnitId();
            //夕場前繰越対象フラグ = 先物OPデータアダプタ.get夕場前繰越対象フラグ（PR層）(注文単位)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            Long l_firstOrderUnitId = null;
            if (!l_orderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_lngFirstOrderUnitId);
            }
            Institution l_institution = l_subAccount.getInstitution();
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_institution.getInstitutionCode(),
                l_trader,
                l_orderUnit.getLimitPrice(),
                l_orderUnit.getExecutionConditionType(),
                l_orderUnitRow.getExpirationDate(),
                l_settleContractEntries,
                l_orderUnitRow.getOrderConditionType(),
                0,
                0,
                null,
                l_strOrderExpirationType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryoverFlag);
                
            //1.10: validate先物返済注文(補助口座 : SubAccount, 返済注文内容 : IfoSettleContractOrderSpec)                
            NewOrderValidationResult l_validationResult = l_orderMgr.validateFuturesSettleContractOrder(
                l_subAccount,
                l_settleContractOrderSpec);
                
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("validate先物返済注文が失敗の場合。");
                    
                throw new WEB3BaseException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.11:create手数料(注文単位ID : long)
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = 
                (WEB3IfoBizLogicProvider) l_tradingModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission = l_ifoBizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
            
            //1.12:get取引銘柄(証券会社 : Institution, 銘柄コード : String, 市場コード : String)
            //銘柄：　@注文単位.銘柄IDに該当する先物OP銘柄オブジェクト
            WEB3IfoProductManagerImpl l_productMgr = 
                (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
                
            WEB3IfoProductImpl l_product = null;            
            try
            {
                l_product = (WEB3IfoProductImpl) l_productMgr.getProduct(l_orderUnitRow.getProductId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("銘柄テーブルに該当するデータがありません。", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //市場コード：　@注文単位.市場IDに該当する市場オブジェクト.市場コード
            String l_strMarketCode = null;        
            if (!l_orderUnitRow.getMarketIdIsNull())
            {
                try
                {
                    Market l_market = l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());//NotFoundException
                    l_strMarketCode = l_market.getMarketCode();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("市場テーブルに該当するデータがありません。", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }                
            
            WEB3IfoTradedProductImpl l_tradedProduct = null;
            try
            {
                l_tradedProduct = l_productMgr.getIfoTradedProduct(
                    l_institution,
                    l_product.getProductCode(),
                    l_strMarketCode);//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("取引銘柄テーブルに該当するデータがありません。", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.13:calc概算決済損益(手数料 : 手数料, 指値 : double, 補助口座 : SubAccount, 
            //先物OP取引銘柄 : 先物OP取引銘柄, 返済建玉エントリ[] : SettleContractEntry[], 
            //数量 : double, 売買 : SideEnum, isSkip金額チェック : boolean)
            
            //買売:        
            //注文単位.getSide()＝SideEnum.BUY(買)（=売建買返済）の場合、”売”をセット。
            //注文単位.getSide()＝SideEnum.SELL(売)（=買建売返済）の場合、”買”をセット。
            SideEnum l_side = null;        
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.BUY;
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_caclResult = 
                l_orderMgr.calcEstimateSettlementIncome(
                    l_commission,
                    l_settleContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_settleContractEntries,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_side,
                    false);
                    
            //1.14:(実行結果に応じて注文系データをUPDATEする)
            //1.14.1:(*)正常終了した場合
            //1.14.1.1:update注文データ(IfoOrderUnit, double, double)
            WEB3IfoFrontOrderService l_ifoOrderService = (WEB3IfoFrontOrderService) Services
                .getService(WEB3IfoFrontOrderService.class);
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_accountMgr.getBranch(l_orderUnitRow.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());

            this.updateOrderData(
                l_orderUnit,
                l_caclResult.getCalcUnitPrice(),
                l_caclResult.getEstimateDeliveryAmount(),
                l_ifoOrderService.getSubmitOrderRouteDiv(l_branch.getInstitution().getInstitutionCode(),
                l_market.getMarketCode()));

            //1.14.1.2:insert返済注文キュー(注文ID : long)
            l_orderMgr.insertSettleContractHostOrder(l_orderUnit.getOrderId());
        
            //1.14.1.3:sendMQトリガ(IfoOrderUnit)
            try
            {
                l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("注文単位テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        
            this.sendMQTrigger(l_orderUnit);
        }
        //1.14.2:(*)処理中に例外がスローされた場合
        catch (Exception l_ex)
        {
            //1.14.2.1:get注文エラー理由コード(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_errorReasonCode = l_orderMgr.getErrorReasonCode(l_errorInfo.getErrorCode());

            //1.14.2.2:逆指値注文先物OP発注更新インタセプタ(注文エラー理由コード : String)
            WEB3ToStopIfoOrderUpdateInterceptor l_updateInterceptor = 
                new WEB3ToStopIfoOrderUpdateInterceptor(l_errorReasonCode);

            //1.14.2.3:setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            //1.14.2.4:DefaultOrderInvalidatedMarketResponseMessage(arg0 : long)
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = 
                new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
                
            //1.14.2.5:process(arg0 : OrderInvalidatedMarketResponseMessage)
            MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                            
            IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();            
            
            try
            {
                ProcessingResult l_result = l_callBackService.process(l_responseMessage);
                if (l_result.isFailedResult())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        l_result.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "発注審査エラーとなった逆指値注文の失効処理に失敗しました。注文ID：[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }

            // is予約注文確認要(注文単位 : IfoOrderUnit)
            //注文単位：　@注文単位
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist(l_orderUnit);

            //予約注文確認要（is予約注文確認要() == true）の場合
            if (l_blnIsReserveOrderExist)
            {
                //invalidateAll予約注文単位(long)
                //親注文の注文ID：　@注文単位.注文ID
                WEB3ToSuccReservationIfoOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);

                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }
        }   
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP新規建逆指値注文)<BR>
     * オプション新規建逆指値注文を発注する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（逆指値注文先物OP発注一件サービス）submitOP新規建逆指値注文」参照。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void submitOptionOpenContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitOptionOpenContractStopOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
        WEB3GentradeAccountManager l_accountMgr =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeSubAccount l_subAccount = null;            

        //引数で渡された注文単位は更新されている可能性があるので、再取得を行うこと。        
        try
        {
            l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文単位テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        try
        {     
			//getSubAccount(arg0 : long, arg1 : long)
			try
			{
				l_subAccount = (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(
					l_orderUnit.getAccountId(), 
					l_orderUnit.getSubAccountId());//NotFoundException
			}
			catch (NotFoundException l_ex)
			{
				log.error("補助口座テーブルに該当するデータがありません。", l_ex);
    
				log.exiting(STR_METHOD_NAME);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					this.getClass().getName() + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}
            //1.1:is処理対象(IfoOrderUnit)
            boolean l_blnIsProcessObj = this.isProcessObject(l_orderUnit);
            
            //1.2:（分岐フロー：　@is処理対象＝false（処理対象外注文）の場合）
            if (!l_blnIsProcessObj)
            {
                //1.2.1:処理対象外の場合、何もせずそのままreturnする。
                //（正常ステータスで終了）
                return;
            }
            
            //1.3:get発注日(確認時発注日 : Date,立会区分 : String)
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"),
                l_orderUnitRow.getSessionType());
                
            
            WEB3GentradeFinObjectManager l_finObjectMgr =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
            //1.5: （分岐フロー：注文単位.取引者ID≠nullの場合のみ）
            Trader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                //1.5.1: getTrader(arg0 : long)
                try
                {
                    l_trader = l_finObjectMgr.getTrader(l_orderUnit.getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("扱者テーブルに該当するデータがありません。", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //1.6:create新規建注文内容(証券会社コード : String, 扱者 : 扱者, 
            //is買建 : boolean, 市場コード : String, 銘柄 : 先物OP銘柄, 
            //数量 : double, 指値 : double, 執行条件 : IfoOrderExecutionConditionType,
            // 注文失効日 : Date, 発注条件 : String, (W指値)訂正指値 : double, 注文期限区分 : 
            //String, 初回注文の注文単位ID : Long, 夕場前繰越対象フラグ : boolean)
            
            //is買建：　@
            //  注文単位.getSide()＝SideEnum.BUY(買)の場合、trueをセット。
            //  注文単位.getSide()＝SideEnum.SELL(売)の場合、falseをセット。
            boolean l_blnIsBuy = false;
            
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuy = true;
            }
            
            //市場コード：　@注文単位.市場IDに該当する市場オブジェクト.市場コード
            String l_strMarketCode = null;
            
            if (!l_orderUnitRow.getMarketIdIsNull())
            {
                try
                {
                    Market l_market = l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());//NotFoundException
                    l_strMarketCode = l_market.getMarketCode();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("市場テーブルに該当するデータがありません。", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //銘柄：　@注文単位.銘柄IDに該当する先物OP銘柄オブジェクト
            WEB3IfoProductManagerImpl l_productMgr = 
                (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            WEB3IfoProductImpl l_product = null;
                
            try
            {
                l_product = (WEB3IfoProductImpl) l_productMgr.getProduct(l_orderUnitRow.getProductId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("銘柄テーブルに該当するデータがありません。", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //注文期限区分 = 先物OPデータアダプタ.get注文期限区分(注文単位)
            String l_strOrderExpirationType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);
            //初回注文の注文単位ID = 注文単位.初回注文の注文単位ID
            long l_lngFirstOrderUnitId = l_orderUnitRow.getFirstOrderUnitId();
            //夕場前繰越対象フラグ = 先物OPデータアダプタ.get夕場前繰越対象フラグ（PR層）(注文単位)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            Long l_firstOrderUnitId = null;
            if (!l_orderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_lngFirstOrderUnitId);
            }
            Institution l_institution = l_subAccount.getInstitution();
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                l_trader,
                l_blnIsBuy,
                l_strMarketCode,
                l_product,
                l_orderUnit.getQuantity(), 
                l_orderUnit.getLimitPrice(),
                l_orderUnit.getExecutionConditionType(),
                l_orderUnitRow.getExpirationDate(),
                l_orderUnitRow.getOrderConditionType(),
                0,
                0,
                null,
                l_strOrderExpirationType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryoverFlag);
                
            //1.7:validate新規建注文の発注審査を行う。(補助口座 : SubAccount, 新規建注文内容 : IfoOpenContractOrderSpec, 注文単位 : IfoOrderUnit)                
            NewOrderValidationResult l_validationResult = l_orderMgr.validateOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                l_orderUnit);
                
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("validateオプション新規建注文が失敗の場合。");
                    
                throw new WEB3BaseException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.8:create手数料(注文単位ID : long)
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = 
                (WEB3IfoBizLogicProvider) l_tradingModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission = l_ifoBizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
            
            //1.9:get取引銘柄(証券会社 : Institution, 銘柄コード : String, 市場コード : String)
            WEB3IfoTradedProductImpl l_tradedProduct = null;
            try
            {
                l_tradedProduct = l_productMgr.getIfoTradedProduct(
                    l_institution,
                    l_product.getProductCode(),
                    l_strMarketCode);//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("取引銘柄テーブルに該当するデータがありません。", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.10:概算受渡代金を計算する。
            WEB3IfoEstimateDeliveryAmountCalcResult l_caclAmountCalcResult = 
                l_orderMgr.calcEstimateDeliveryAmount(
                    l_commission,
                    l_openContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_openContractOrderSpec.getQuantity(),
                    l_orderUnit.getSide(),
                    false,
                    false);

            //1.11:create新規建訂正内容(注文単位 : IfoOrderUnit)
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = this.createOpenContractChangeSpec(l_orderUnit);

            //1.12:先物OP新規建訂正更新インタセプタ(新規建訂正内容 : 新規建訂正内容)
            WEB3IfoOpenContractChangeUpdateInterceptor l_ifoOpenContractChangeUpdateInterceptor
                = new WEB3IfoOpenContractChangeUpdateInterceptor(l_ifoOpenContractChangeSpec);

            //1.13:プロパティセット
            //手数料をセットする
            l_ifoOpenContractChangeUpdateInterceptor.setCommision(l_commission);

            //概算受渡代金計算結果をセットする
            l_ifoOpenContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_caclAmountCalcResult);

            //発注条件をセットする
            l_ifoOpenContractChangeUpdateInterceptor.setOrderCond(l_orderUnitRow.getOrderConditionType());

            //発注条件演算子をセットする
            l_ifoOpenContractChangeUpdateInterceptor.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());

            //逆指値基準値タイプをセットする
            l_ifoOpenContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_orderUnitRow.getStopPriceType());

            //逆指値基準値をセットする
            l_ifoOpenContractChangeUpdateInterceptor.setStopOrderBasePrice(l_orderUnitRow.getStopOrderPrice());

            //（W指値）訂正指値
            double l_wLimitPrice = 0D;
            l_ifoOpenContractChangeUpdateInterceptor.setWLimitPriceChange(l_wLimitPrice);

            //取引者ID
            long l_traderId = 0L;
            if (l_trader != null)
            {
                l_traderId = l_trader.getTraderId();
            }
            l_ifoOpenContractChangeUpdateInterceptor.setTraderId(l_traderId);
			
			//注文経路区分をセットする
			l_ifoOpenContractChangeUpdateInterceptor.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());			

            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_ifoOpenContractChangeUpdateInterceptor;

            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_ifoOpenContractChangeSpec;

            //1.14:validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            WEB3TPTradingPowerResult l_result = 
                l_tradingPowerService.validateTradingPower
                (l_subAccount,
                 l_interceptorObject,
                 l_specObject,
                 l_orderUnitRow.getOrderType(),
                 true);

            //1.15:throw余力エラー情報(取引余力結果 : 取引余力結果, 補助口座 : 補助口座)(先物注文マネージャ::throw余力エラー情報)
            l_orderMgr.throwTpErrorInfo(l_result,l_subAccount);

            //1.16:(実行結果に応じて注文系データをUPDATEする)
            //1.16.1:(*)正常終了した場合

            // 1.16.1.1:update注文データ(IfoOrderUnit, double, double)
            WEB3IfoFrontOrderService l_ifoOrderService = (WEB3IfoFrontOrderService) Services
                .getService(WEB3IfoFrontOrderService.class);
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_accountMgr.getBranch(l_orderUnitRow.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());

            this.updateOrderData(l_orderUnit,
                l_caclAmountCalcResult.getCalcUnitPrice(),
                l_caclAmountCalcResult.getEstimateDeliveryAmount(),
                l_ifoOrderService.getSubmitOrderRouteDiv(l_branch.getInstitution()
                .getInstitutionCode(), l_market.getMarketCode()));

            // 1.16.1.2:insert新規建注文キュー(注文ID : long)
            l_orderMgr.insertOpenContractHostOrder(l_orderUnit.getOrderId());
        
            //1.16.1.3:sendMQトリガ(IfoOrderUnit)
            try
            {
                l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("注文単位テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        
            this.sendMQTrigger(l_orderUnit);
        }
        //1.16.2:(*)処理中に例外がスローされた場合
        catch (Exception l_ex)
        {
            //1.16.2.1:get注文エラー理由コード(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_errorReasonCode = l_orderMgr.getErrorReasonCode(l_errorInfo.getErrorCode());

            //1.16.2.2:逆指値注文先物OP発注更新インタセプタ(注文エラー理由コード : String) 
            WEB3ToStopIfoOrderUpdateInterceptor l_updateInterceptor = 
                new WEB3ToStopIfoOrderUpdateInterceptor(l_errorReasonCode);

            //1.16.2.3:setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            //1.16.2.4:DefaultOrderInvalidatedMarketResponseMessage(arg0 : long)
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = 
                new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
                
            //1.16.2.5:process(arg0 : OrderInvalidatedMarketResponseMessage)
            MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                            
            IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();            
            
            try
            {
                ProcessingResult l_result = l_callBackService.process(l_responseMessage);
                if (l_result.isFailedResult())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        l_result.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "発注審査エラーとなった逆指値注文の失効処理に失敗しました。注文ID：[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }

            // is予約注文確認要(注文単位 : IfoOrderUnit)
            //注文単位：　@注文単位
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist(l_orderUnit);

            //予約注文確認要（is予約注文確認要() == true）の場合
            if (l_blnIsReserveOrderExist)
            {
                //invalidateAll予約注文単位(long)
                //親注文の注文ID：　@注文単位.注文ID
                WEB3ToSuccReservationIfoOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);

                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }

            //1.16.2.6:オプション買建口座（補助口座.補助口座タイプ≠"株式オプション取引口座（先物証拠金））の場合
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                //1.16.2.6.1:余力再計算(補助口座 : 補助口座)(取引余力サービス::余力再計算)
                WEB3TPTradingPowerService l_tpTradingPowerService =
                    (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
                        l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
            }
        }  

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP返済逆指値注文)<BR>
     * オプション返済逆指値注文を発注する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（逆指値注文先物OP発注一件サービス）submitオプション返済逆指値注文」参照。<BR>
     *  ================================================================================ <BR>
     * 1.6.1返済可能数量チェック：　@<BR>
     * ３） 返済数量(*1) ＞ 返済可能建玉残高(*2)　@の場合、「返済可能残高数量超過エラー」<BR>
     * の例外をthrowする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00299<BR>
     *  ================================================================================ <BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void submitOptionSettleContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitOptionSettleContractStopOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        WEB3GentradeAccountManager l_accountMgr =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeSubAccount l_subAccount = null;            

        //引数で渡された注文単位は更新されている可能性があるので、再取得を行うこと。        
        try
        {
            l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文単位テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        try
        {
			//getSubAccount(arg0 : long, arg1 : long)
			try
			{
				l_subAccount = (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(
					l_orderUnit.getAccountId(), 
					l_orderUnit.getSubAccountId());//NotFoundException
			}
			catch (NotFoundException l_ex)
			{
				log.error("補助口座テーブルに該当するデータがありません。", l_ex);
    
				log.exiting(STR_METHOD_NAME);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					this.getClass().getName() + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}
            
            //1.1:is処理対象(IfoOrderUnit)
            boolean l_blnIsProcessObj = this.isProcessObject(l_orderUnit);
            
            //1.2:（分岐フロー：　@is処理対象＝false（処理対象外注文）の場合）
            if (!l_blnIsProcessObj)
            {
                //1.2.1:処理対象外の場合、何もせずそのままreturnする。
                //（正常ステータスで終了）
                return;
            }
            
            //1.3:get発注日(確認時発注日 : Date,立会区分 : String)
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"),
                l_orderUnitRow.getSessionType());
             
            //1.4:getContractsToClose( )   
            if (!(l_orderUnit instanceof IfoContractSettleOrderUnit))
            {
                log.debug("先物OP注文単位のTYPEがIfoContractSettleOrderUnit以外です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "先物OP注文単位のTYPEがIfoContractSettleOrderUnit以外です。");
            }
            IfoContractSettleOrderUnit l_ifoContractSettleOrderUnit = 
                (IfoContractSettleOrderUnit) l_orderUnit;
            IfoClosingContractSpec[] l_contractSpecs = 
                l_ifoContractSettleOrderUnit.getContractsToClose();
            
            //1.5: ArrayList()
            List l_lisSettleContractEntries = new ArrayList();
            
            int l_intCnt = 0;
            if (l_contractSpecs != null && l_contractSpecs.length > 0)
            {
                l_intCnt = l_contractSpecs.length;
            }
            
            //1.6:getContractsToClose( )の戻り値（＝建玉返済指定情報）要素数(index)分、Loop）
            for (int i = 0; i < l_intCnt; i++)
            {            
                IfoClosingContractSpec l_contractSpec = l_contractSpecs[i];
                //1.6.1: (*)返済可能数量チェック            
                try
                {
                    //返済可能数量チェック：
                    //１）返済数量(*1)を取得する。
                    //    返済数量 ＝ 建玉返済指定情報[index].返済注文数量
                    double l_dblClosingContractQuantity = l_contractSpec.getQuantity();
                    
                    //２）返済可能建玉残高(*2)を算出する。（建玉は、建玉返済指定情報[index].建玉IDに該当する建玉オブジェクトを使用）
                    //　@　@返済可能建玉残高 ＝ 建玉.getQuantity()（＝建玉数量） － 建玉.getLockedQuantity()（＝ロック中数量） ＋ 
                    //建玉.getロック中数量(注文単位ID)（＝当該注文ロック中数量）
                    
                    //1.6.1.1:先物OP建玉(建玉ＩＤ : long)
                    long l_lngContractId = l_contractSpec.getContractId();
                    WEB3IfoContractImpl l_contract = 
                        new WEB3IfoContractImpl(l_lngContractId);//DataNetworkException, DataQueryException
                    
                    //1.6.1.2: getQuantity( )
                    double l_dblContractQuantity = l_contract.getQuantity();
                    
                    //1.6.1.3:getLockedQuantity( )
                    double l_dblContractLockedQuantity = l_contract.getLockedQuantity();
                    
                    //1.6.1.4:getロック中数量(注文単位ＩＤ : long)
                    double l_dblContracLockedQuantityForOrderUnit = l_contract.getLockedQuantity(l_orderUnit.getOrderUnitId());
                    
                    double l_dblValue = l_dblContractQuantity - l_dblContractLockedQuantity + l_dblContracLockedQuantityForOrderUnit;
                    
                    //３） 返済数量(*1) ＞ 返済可能建玉残高(*2)　@の場合、「返済可能残高数量超過エラー」の例外をthrowする。
                    if (l_dblClosingContractQuantity > l_dblValue)
                    {
                        String l_strErrorMsg = "返済数量 ＞ 返済可能建玉残高, " + 
                            l_dblClosingContractQuantity + " > " + l_dblValue;
                        log.debug(l_strErrorMsg);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strErrorMsg);
                    }                                
                    
                    //1.6.1.5: SettleContractEntry(arg0 : long, arg1 : double)
                    SettleContractEntry l_settleContractEntry = new SettleContractEntry(
                        l_lngContractId, l_dblClosingContractQuantity);
                    
                    //1.6.1.6: add(arg0 : Object)
                    l_lisSettleContractEntries.add(l_settleContractEntry);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            SettleContractEntry[] l_settleContractEntries = new SettleContractEntry[l_lisSettleContractEntries.size()];
            l_lisSettleContractEntries.toArray(l_settleContractEntries);
            
            WEB3GentradeFinObjectManager l_finObjectMgr =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
            //1.8: （分岐フロー：注文単位.取引者ID≠nullの場合のみ）
            Trader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                //1.8.1: getTrader(arg0 : long)
                try
                {
                    l_trader = l_finObjectMgr.getTrader(l_orderUnit.getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("扱者テーブルに該当するデータがありません。", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //1.9:create返済注文内容(証券会社コード : String, 扱者 : 扱者, 
            //指値 : double, 執行条件 : IfoOrderExecutionConditionType, 
            //注文失効日 : Date, 返済建玉エントリ : SettleContractOrderEntry[], 
            //発注条件 : String, (W指値)訂正指値 : double, 注文期限区分 : String, 
            //初回注文の注文単位ID : Long, 夕場前繰越対象フラグ : boolean)                

            //注文期限区分 = 先物OPデータアダプタ.get注文期限区分(注文単位)
            String l_strOrderExpirationType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);
            //初回注文の注文単位ID = 注文単位.初回注文の注文単位ID
            long l_lngFirstOrderUnitId = l_orderUnitRow.getFirstOrderUnitId();
            //夕場前繰越対象フラグ = 先物OPデータアダプタ.get夕場前繰越対象フラグ（PR層）(注文単位)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            Long l_firstOrderUnitId = null;
            if (!l_orderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_lngFirstOrderUnitId);
            }
            Institution l_institution = l_subAccount.getInstitution();
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_institution.getInstitutionCode(),
                l_trader,
                l_orderUnit.getLimitPrice(),
                l_orderUnit.getExecutionConditionType(),
                l_orderUnitRow.getExpirationDate(),
                l_settleContractEntries,
                l_orderUnitRow.getOrderConditionType(),
                0,
                0,
                null,
                l_strOrderExpirationType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryoverFlag);
                
            //1.10: validate返済注文(補助口座 : SubAccount, 返済注文内容 : IfoSettleContractOrderSpec)                
            NewOrderValidationResult l_validationResult = l_orderMgr.validateSettleContractOrder(
                l_subAccount,
                l_settleContractOrderSpec);
                
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("validateオプション返済注文が失敗の場合。");
                    
                throw new WEB3BaseException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.11:create手数料(注文単位ID : long)
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = 
                (WEB3IfoBizLogicProvider) l_tradingModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission = l_ifoBizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
            
            //1.12:get取引銘柄(証券会社 : Institution, 銘柄コード : String, 市場コード : String)
            //市場コード：　@注文単位.市場IDに該当する市場オブジェクト.市場コード
            //銘柄：　@注文単位.銘柄IDに該当する先物OP銘柄オブジェクト
            WEB3IfoProductManagerImpl l_productMgr = 
                (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
                
            WEB3IfoProductImpl l_product = null;            
            try
            {
                l_product = (WEB3IfoProductImpl) l_productMgr.getProduct(l_orderUnitRow.getProductId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("銘柄テーブルに該当するデータがありません。", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            String l_strMarketCode = null;        
            if (!l_orderUnitRow.getMarketIdIsNull())
            {
                try
                {
                    Market l_market = l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());//NotFoundException
                    l_strMarketCode = l_market.getMarketCode();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("市場テーブルに該当するデータがありません。", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }                
            
            WEB3IfoTradedProductImpl l_tradedProduct = null;
            try
            {
                l_tradedProduct = l_productMgr.getIfoTradedProduct(
                    l_institution,
                    l_product.getProductCode(),
                    l_strMarketCode);//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("取引銘柄テーブルに該当するデータがありません。", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.13:概算受渡代金を計算する。        
    
            WEB3IfoEstimateDeliveryAmountCalcResult l_caclAmountCalcResult = 
                l_orderMgr.calcEstimateDeliveryAmount(
                    l_commission,
                    l_settleContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_orderUnit.getSide(),
                    true,
                    false);
                    
            //1.14:(実行結果に応じて注文系データをUPDATEする)
            //1.14.1:(*)正常終了した場合 
            //1.14.1.1:update注文データ(IfoOrderUnit, double, double)
            WEB3IfoFrontOrderService l_ifoOrderService = (WEB3IfoFrontOrderService) Services
                .getService(WEB3IfoFrontOrderService.class);
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_accountMgr.getBranch(l_orderUnitRow.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());

            this.updateOrderData(l_orderUnit,
                l_caclAmountCalcResult.getCalcUnitPrice(),
                l_caclAmountCalcResult.getEstimateDeliveryAmount(),
                l_ifoOrderService.getSubmitOrderRouteDiv(l_branch.getInstitution()
                .getInstitutionCode(), l_market.getMarketCode()));

            // 1.14.1.2:insert返済注文キュー(注文ID : long)
            l_orderMgr.insertSettleContractHostOrder(l_orderUnit.getOrderId());
        
            //1.11.1.3:sendMQトリガ(IfoOrderUnit)
            try
            {
                l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("注文単位テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        
            this.sendMQTrigger(l_orderUnit);
        }
        //1.14.2:(*)処理中に例外がスローされた場合
        catch (Exception l_ex)
        {
            //1.14.2.1:get注文エラー理由コード(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_errorReasonCode = l_orderMgr.getErrorReasonCode(l_errorInfo.getErrorCode());

            //1.14.2.2:逆指値注文先物OP発注更新インタセプタ(注文エラー理由コード : String) 
            WEB3ToStopIfoOrderUpdateInterceptor l_updateInterceptor = 
                new WEB3ToStopIfoOrderUpdateInterceptor(l_errorReasonCode);

            //1.14.2.3:setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            //1.14.2.4:DefaultOrderInvalidatedMarketResponseMessage(arg0 : long)
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = 
                new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
                
            //1.14.2.5:process(arg0 : OrderInvalidatedMarketResponseMessage)
            MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                            
            IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();            
            
            try
            {
                ProcessingResult l_result = l_callBackService.process(l_responseMessage);
                if (l_result.isFailedResult())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        l_result.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "発注審査エラーとなった逆指値注文の失効処理に失敗しました。注文ID：[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }

            // is予約注文確認要(注文単位 : IfoOrderUnit)
            //注文単位：　@注文単位
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist(l_orderUnit);

            //予約注文確認要（is予約注文確認要() == true）の場合
            if (l_blnIsReserveOrderExist)
            {
                //invalidateAll予約注文単位(long)
                //親注文の注文ID：　@注文単位.注文ID
                WEB3ToSuccReservationIfoOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);

                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }
        }

        //1.16.2.6:オプション買建口座（補助口座.補助口座タイプ≠"株式オプション取引口座（先物証拠金））の場合
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
            //1.16.2.6.1:余力再計算(補助口座 : 補助口座)(取引余力サービス::余力再計算)
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
                    l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update注文データ)<BR>
     * 注文系データのupdateを行う。<BR>
     * <BR>
     * １）　@パラメータ.注文単位のcloneを作成する。<BR>
     * <BR>
     * ２）　@１）にて作成したcloneに対し、更新値をセットする。<BR>
     * 　@DB更新仕様<BR>
     * 　@「逆指値注文発注（OK）_先物OP注文単位テーブル.xls」参照。<BR>
     * <BR>
     * ３）　@注文データをupdateする。<BR>
     * 　@OP注文マネージャ.update注文データ()をコールする。<BR>
     * <BR>
     * 　@[update注文データ()に指定する引数]<BR>
     * 　@　@注文単位：　@２）にて作成した注文単位<BR>
     * 　@　@is履歴作成：　@true（作成する）<BR>
     * @@param l_orderUnit - (注文単位)
     * @@param l_dblOrderPrice - (注文単価)
     * @@param l_dblEstimatedPrice - (概算受渡代金)
     * @@param l_strOrderRootDiv - (発注経路区分)
     * @@throws WEB3BaseException
     */
    protected void updateOrderData(
        IfoOrderUnit l_orderUnit, 
        double l_dblOrderPrice, 
        double l_dblEstimatedPrice,
        String l_strOrderRootDiv) throws WEB3BaseException
    {    
        final String STR_METHOD_NAME =
            "updateOrderData(IfoOrderUnit, double, double, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@パラメータ.注文単位のcloneを作成する。
        IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        IfoOrderUnitParams l_params = new IfoOrderUnitParams(l_row);
        
        // ２）　@１）にて作成したcloneに対し、更新値をセットする。
        //DB更新仕様
        // 　@「逆指値注文発注（OK）_先物OP注文単位テーブル.xls」参照。
        //注文履歴最終通番 = （既存値） + 1
        l_params.setLastOrderActionSerialNo(l_params.getLastOrderActionSerialNo() + 1);
        
        //注文状態 = 2：発注中（新規注文）
        l_params.setOrderStatus(OrderStatusEnum.ORDERING);
        
        //引数の注文単価
        //（* 概算受渡代金算出に使用した単価）
        l_params.setPrice(l_dblOrderPrice);
        
        //引数の概算受渡代金
        //（* 概算受渡代金計算結果）
        l_params.setEstimatedPrice(l_dblEstimatedPrice);
                
		//訂正・取消区分
		l_params.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

        //1：時価サーバ
        l_params.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
        
        //現在時刻
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //発注経路区分
        l_params.setSubmitOrderRouteDiv(l_strOrderRootDiv);
        
        //３）　@注文データをupdateする。
        //OP注文マネージャ.update注文データ()をコールする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = 
            (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager(); 
        IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.toOrderUnit(l_params);
        l_orderManager.updateOrderData(l_ifoOrderUnit, true);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (sendMQトリガ)<BR>
     * MQトリガを発行する必要がある場合は、<BR>
     * MQトリガを発行する。<BR>
     * <BR>
     * １）　@MQトリガを発行するかどうかを判定する。<BR>
     * 　@取引時間管理.isトリガ発行()をコールする。<BR>
     * <BR>
     * 　@[isトリガ発行()に指定する引数]<BR>
     * 　@　@発注条件：　@null（固定）<BR>
     * 　@　@※注文単位.発注条件を指定すると、<BR>
     * 　@　@　@　@逆指値時はfalseが返される為。<BR>
     * <BR>
     * ２）　@MQトリガ発行要否を取得する。<BR>
     * 　@発注先切替.isMQトリガ発行経路()をコールする。<BR>
     * <BR>
     * 　@[isMQトリガ発行経路()に指定する引数]<BR>
     * 　@　@証券会社コード：<BR>
     * 　@　@　@注文単位.証券会社IDに該当する証券会社コード<BR>
     * 　@　@銘柄タイプ：　@注文単位.銘柄タイプ<BR>
     * 　@　@市場コード：　@注文単位.市場IDに該当する市場コード<BR>
     * 　@　@発注経路区分：　@注文単位の同項目<BR>
     * 　@　@フロント発注システム区分：<BR>
     * 　@　@　@先物OP発注サービス.getフロント発注システム区分(<BR>
     * 　@　@　@　@　@注文単位.市場IDに該当する市場コード)<BR>
     * <BR>
     * ３）　@１）、２）の戻り値が両方ともtrueの場合、以降の処理を実施する。<BR>
     * <BR>
     *　@３－１）　@発注時に設定するデータコードを取得する。<BR>
     *　@　@先物OP発注サービス.get発注時MQデータコード()をコールする。<BR>
     * <BR>
     *　@　@[get発注時MQデータコード()に指定する引数]<BR>
     *　@　@　@発注経路区分：　@注文単位の同項目<BR>
     * <BR>
     *　@　@nullが返却された場合、処理を終了（return）する。<BR>
     * <BR>　@ 
     * 　@３－２）　@WEB3MQMessageSpecを生成する。<BR>
     * <BR>
     * 　@　@[コンストラクタに指定する引数]<BR>
     * 　@　@　@証券会社コード：　@<BR>
     * 　@　@　@　@注文単位.部店IDに該当する部店.証券会社コード<BR>
     * 　@　@　@データコード：　@<BR>
     * 　@　@　@　@先物OP発注サービス.get発注時MQデータコード()の戻り値<BR>
     * <BR>
     * 　@３－３）　@MQトリガを発行する。<BR>
     * 　@　@WEB3MQGatewayService.send()メソッドをコールする。<BR>
     * 
     * 　@　@[send()に指定する引数]<BR>
     * 　@　@　@MQメッセージ内容：　@３－２）にて生成したインスタンス<BR>
     * <BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    protected void sendMQTrigger(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "sendMQTrigger(IfoOrderUnit l_orderUnit)  ";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //１）　@MQトリガを発行するかどうかを判定する。
            // 　@取引時間管理.isトリガ発行()をコールする。
            boolean l_blnIsTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
            
            //２）　@MQトリガ発行要否を取得する。
            //発注先切替.isMQトリガ発行経路()をコールする。
            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountMananger.getBranch(l_row.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_row.getMarketId());

            WEB3IfoFrontOrderService l_frontOrderService = 
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            String l_strSystemCode = 
                l_frontOrderService.getFrontOrderSystemCode(
                    l_market.getMarketCode());

            boolean l_blnIsSwitching = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                l_branch.getInstitution().getInstitutionCode(),
                l_orderUnit.getProductType(),
                l_market.getMarketCode(),
                l_row.getSubmitOrderRouteDiv(),
                l_strSystemCode);
            
            //３）　@１）、２）の戻り値が両方ともtrueの場合、以降の処理を実施する。
            if (l_blnIsTrigger && l_blnIsSwitching)
            {
                //３－１）　@発注時に設定するデータコードを取得する。
                //先物OP発注サービス.get発注時MQデータコード()をコールする。
                String l_strOrderMQDataCode = l_frontOrderService.getOrderMQDataCode(l_orderUnit);
                //nullが返却された場合、処理を終了（return）する。
                if (WEB3StringTypeUtility.isEmpty(l_strOrderMQDataCode))
                {
                    return;
                }
                
                //３－２）　@WEB3MQMessageSpecを生成する。
                //データコード：　@
                //先物OP発注サービス.get発注時MQデータコード()の戻り値 
                WEB3MQMessageSpec l_spec = new WEB3MQMessageSpec(l_branch.getInstitution().getInstitutionCode(),
                    l_strOrderMQDataCode);
                
                //３－２）　@MQトリガを発行する
                //WEB3MQGatewayService.send()メソッドをコールする。
                WEB3MQGatewayService l_gatewayService = (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
                l_gatewayService.send(l_spec);
            }
                
                log.exiting(STR_METHOD_NAME);           
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
    }

    /**
     * (is処理対象)<BR>
     * 指定の注文が逆指値発注の処理対象であるかを判定する。<BR>
     * <BR>
     * 処理対象の場合、trueを、処理対象外の場合、falseを返却する。 <BR>
     * 以下の条件のいずれかに該当する場合、<BR>
     * 処理対象外とし、falseを返却する。以外、trueを返却する。<BR>
     * <BR>
     * ・パラメータ.注文単位.注文有効状態 != "オープン"<BR>
     * ・パラメータ.注文単位.発注条件 != "逆指値"<BR>
     * ・パラメータ.注文単位.リクエストタイプ == "時価サーバ"<BR>
     * @@param l_orderUnit - (先物OP注文単位)
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isProcessObject (IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isProcessObject (OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //指定の注文が逆指値発注の処理対象であるかを判定する。
        IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderingCondition = l_row.getOrderConditionType();
        String l_strRequestType = l_row.getRequestType();
        
        //以下の条件のいずれかに該当する場合、
        //処理対象外とし、falseを返却する。以外、trueを返却する
        if (!OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus())
            || !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderingCondition)
            || WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
    }

   /**
     * (create新規建訂正内容)<BR>
     * 引数で指定された注文単位データより新規建訂正内容を作成する。<BR>
     * <BR>
     * １）　@新規建訂正内容の生成<BR>
     * 　@新規建訂正内容オブジェクトを生成する。<BR>
     * 　@[コンストラクタ引数]<BR>
     * 　@注文ID：　@注文単位.注文ID<BR>
     * 　@注文単位ＩＤ：　@注文単位.注文単位ID<BR>
     * 　@訂正後数量：　@注文単位.注文数量<BR>
     * 　@訂正後指値：　@注文単位.指値<BR>
     * ２）　@プロパティセット<BR>
     * 　@１）で生成した新規建訂正内容にプロパティをセットする（setterメソッドを使用）<BR>
     * 　@訂正執行条件：　@注文単位.執行条件<BR>
     * 　@訂正失効日：　@注文単位.注文失効日<BR>
     * 　@発注日：　@注文単位.発注日<BR>
     * 　@発注条件：　@注文単位.発注条件<BR>
     * 　@発注条件演算子：　@注文単位.発注条件演算子<BR>
     * 　@逆指値基準値タイプ：　@注文単位.逆指値基準値タイプ<BR>
     * 　@逆指値基準値：　@注文単位.逆指値基準値<BR>
     * 　@（W指値）訂正指値：　@0（固定）<BR>
     * 　@訂正後注文期限区分：　@先物OPデータアダプタ.get注文期限区分(注文単位) <BR>
     * 　@夕場前繰越対象フラグ：　@先物OPデータアダプタ.<BR>
     * 　@　@　@　@get夕場前繰越対象フラグ（PR層）(注文単位) <BR>
     * <BR>
     *３）　@プロパティセットした新規建訂正内容を返却する。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * @@return WEB3IfoOpenContractChangeSpec<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3IfoOpenContractChangeSpec createOpenContractChangeSpec (IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createOpenContractChangeSpec (IfoOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //注文ID
        long l_orderUnitId = l_orderUnitRow.getOrderUnitId();
        //注文単位ID
        long l_orderId = l_orderUnitRow.getOrderId();
        //訂正後数量
        double l_quantity = l_orderUnitRow.getQuantity();
        //訂正後単価
        double l_limitPrice = l_orderUnitRow.getLimitPrice();
        //（W指値）訂正指値
        double l_wLimitPrice = 0D;

        //新規建訂正内容の生成 
        WEB3IfoOpenContractChangeSpec l_web3IfoOpenContractChangeSpec = 
            new WEB3IfoOpenContractChangeSpec(
                l_orderId,
                l_orderUnitId,
                l_quantity,
                l_limitPrice);

        //プロパティセット

        //執行条件
        l_web3IfoOpenContractChangeSpec.setChangeExecCondType(l_orderUnitRow.getExecutionConditionType());
        //失効日
        l_web3IfoOpenContractChangeSpec.setChangeExpirationDate(WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate()));
        //発注日
        l_web3IfoOpenContractChangeSpec.setOrderBizDate(WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd"));
        //発注条件
        l_web3IfoOpenContractChangeSpec.setOrderCond(l_orderUnitRow.getOrderConditionType());
        //発注条件演算子
        l_web3IfoOpenContractChangeSpec.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());
        //逆指値基準値タイプ
        l_web3IfoOpenContractChangeSpec.setStopOrderBasePriceType(l_orderUnitRow.getStopPriceType());
        //逆指値基準値
        l_web3IfoOpenContractChangeSpec.setStopOrderBasePrice(l_orderUnitRow.getStopOrderPrice());
        //（W指値）訂正指値
        l_web3IfoOpenContractChangeSpec.setWLimitPriceChange(l_wLimitPrice);
        //注文期限区分
        l_web3IfoOpenContractChangeSpec.setExpirationDateType(WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit));
        //夕場前繰越対象フラグ
        l_web3IfoOpenContractChangeSpec.setEveningSessionCarryoverFlag(
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit));

        //プロパティセットした新規建訂正内容を返却する。
        return l_web3IfoOpenContractChangeSpec;
    }
}
@
