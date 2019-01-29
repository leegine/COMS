head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityReceiveChangeEventServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式訂正取消通知訂正一件サービスImpl(WEB3EquityReceiveChangeEventServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/20 李綱 (中訊) 新規作成
                   2004/12/17 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村 (SRA) JavaDoc修正
                   2006/11/06 張騰宇(中訊) モデル 1032 1026
                   2006/11/28 張騰宇(中訊) モデル 1063 1078
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityReceiveChangeInterceptor;
import webbroker3.equity.WEB3EquityReceiveChangeSpec;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveChangeEventService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式訂正取消通知訂正一件サービスImpl）。<BR>
 * <BR>
 * 現物株式訂正取消通知訂正一件サービス実装クラス
 * @@version 1.0
 */
public class WEB3EquityReceiveChangeEventServiceImpl
    implements WEB3EquityReceiveChangeEventService
{

    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveChangeEventServiceImpl.class);
    
    /**
     * @@roseuid 40A435E100BF
     */
    public WEB3EquityReceiveChangeEventServiceImpl()
    {

    }

    /**
     * （notify訂正）。<BR>
     * <BR>
     * 正取消通知サービスより【株式訂正取消通知キューテーブル】の訂正データを一件受け取り、<BR>
     * 注文訂正完了／注文訂正失敗処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（訂正取消通知訂正一件サービス）notify訂正」参照
     * @@param l_params （株式訂正取消通知キューParams）<BR>
     * 　@　@　@株式訂正取消通知キューテーブルの１レコード
     * @@param l_orderUnit （注文単位）
     * @@roseuid 4036012000DB
     */
    public void notifyChange(
        HostEqtypeOrderClmdReceiptParams l_params,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {     
        final String STR_METHOD_NAME = 
            "changeNotify(HostEqtypeOrderClmdReceiptParams, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }
        
        //1.1. validate訂正結果コード()
        validateChangeResultCode(l_params);

        //1.2. create()
        WEB3EquityChangeCancelNotifyDataAdapter l_dataAdapter =  
            WEB3EquityChangeCancelNotifyDataAdapter.create(l_params);

        //1.3. 株式訂正通知内容()
        WEB3EquityReceiveChangeSpec l_receiveChangeSpec =
            new WEB3EquityReceiveChangeSpec();

        //1.4.get執行条件()
        EqTypeExecutionConditionType l_conditionType =
            l_dataAdapter.getExecCondType();
        
        //1.5. set訂正後執行条件()
        l_receiveChangeSpec.setChangeAfterExecCondType(l_conditionType);
        
        //1.6. get値段条件()
        String l_strPriceConditionType = l_dataAdapter.getPriceConditionType();
        
        //1.7. set訂正後値段条件()
        l_receiveChangeSpec.setChangeAfterPriceConditionType(l_strPriceConditionType);
        
        l_receiveChangeSpec.setChangeAfterOrderRev(l_params.getModifiedOrderRev());
        
        //1.8. create手数料()
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingMod.getBizLogicProvider();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingMod.getProductManager();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission(l_orderUnit.getOrderId());
        
        //1.9. set弁済区分()
        l_commission.setPayType(WEB3PayTypeDef.OTHER);
        
        //1.10. set注文チャネル()
        l_commission.setOrderChannel(l_orderUnitRow.getOrderChanel());
        
        //isストップ注文有効(EqTypeOrderUnit)
        boolean l_blnIsStopOrderValid = l_orderManager.isStopOrderValid(l_orderUnit);
        
        //get補助口座(口座ID : long, 補助口座ID : long)
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
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
        
        //1.13. get取引銘柄()
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            Institution l_institution =
                l_accountManager.getInstitution(l_params.getInstitutionCode());
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_productManager.getProduct(l_orderUnitRow.getProductId());
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_institution,
                    l_product.getProductCode(),
                    l_market.getMarketCode());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);            
        }
        
        //is売注文(EqTypeOrderUnit)
        boolean l_blnIsSellOrder = l_orderManager.isSellOrder(l_orderUnit);
        
        WEB3EquityChangeCancelNotifyDataAdapter l_ChangeCancelNotifyDataAdapter = 
            WEB3EquityChangeCancelNotifyDataAdapter.create(l_params);
        
        boolean l_blnStopOrderValid = false;
        if (l_orderManager.isStopOrderSwitching(l_orderUnit)
            && WEB3CanmodReceiptTypeDef.CHANGED.equals(l_params.getCanmodReceiptType()))
        {
            l_blnStopOrderValid = true;
        }
        else
        {
            l_blnStopOrderValid = l_blnIsStopOrderValid;
        }

        WEB3EquityEstimatedPrice l_estimatedDeliveryPriceResult = null;
        //通知キュー.訂正取消通知区分＝"訂正失敗"かつ拡張株式注文マネージャ.isストップ注文切替中＝true　@の場合
        if (WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_params.getCanmodReceiptType())
            && l_orderManager.isStopOrderSwitching(l_orderUnit))
        {
            // getストップ注文失効時概算代金計算結果(EqtypeOrderUnit, SubAccount)
            l_estimatedDeliveryPriceResult =
                l_orderManager.getStopOrderExpireEstimatedPrice(l_orderUnit, l_subAccount);
        }

        double l_dblLimitPrice = 0.0;
        double l_dblEstimateDeliveryAmount = 0.0;

        //分岐フロー：　@getストップ注文失効時概算代金計算結果の戻り値がnullの場合
        if (l_estimatedDeliveryPriceResult == null)
        {
            //calc概算受渡代金()
            WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
                l_orderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_params.getModifiedLimitPrice(),
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getStopOrderPrice(),
                    l_ChangeCancelNotifyDataAdapter.getExecCondType(),
                    l_orderUnitRow.getWLimitExecCondType(),
                    l_ChangeCancelNotifyDataAdapter.getPriceConditionType(),
                    l_orderUnitRow.getOrderConditionType(),
                    null,
                    l_blnStopOrderValid,
                    l_subAccount,
                    l_tradedProduct,
                    l_params.getModifiedQuantity(),
                    l_blnIsSellOrder,
                    l_orderUnitRow.getExecutedQuantity(),
                    l_orderUnitRow.getExecutedAmount(),
                    true);
            l_dblLimitPrice = l_estimatedDeliveryPrice.getCalcUnitPrice();
            l_dblEstimateDeliveryAmount = l_estimatedDeliveryPrice.getEstimateDeliveryAmount();
        }
        else
        {
            l_dblLimitPrice = l_estimatedDeliveryPriceResult.getCalcUnitPrice();
            l_dblEstimateDeliveryAmount = l_estimatedDeliveryPriceResult.getEstimateDeliveryAmount();
        }

        // set注文単価
        l_receiveChangeSpec.setLimitPrice(l_dblLimitPrice);

        //1.17. set概算受渡代金()
        l_receiveChangeSpec.setEstimateDeliveryAmount(l_dblEstimateDeliveryAmount);

        //1.18. 株式注文訂正通知インタセプタ()
        WEB3EquityReceiveChangeInterceptor l_eventServiceInterceptor =
            new WEB3EquityReceiveChangeInterceptor(l_params);

        //1.19. set株式訂正通知内容()
        l_eventServiceInterceptor.setEquityChangeNotifySpec(
            l_receiveChangeSpec);

        //1.20. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_eventServiceInterceptor);
        
        //1.21. setBusinessTimestamp()
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

        //1.22. 通知キュー.訂正取消通知区分＝"訂正完了"の場合
        MarketAdapter l_marketAdaptor = l_tradingMod.getMarketAdapter();
        MarketResponseReceiverCallbackService l_callbackService =
            l_marketAdaptor.getMarketResponseReceiverCallbackService();
        if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_params.getCanmodReceiptType()))
        {
            //1.22.1. DefaultChangeOrderAcceptedMarketResponseMessage()
            DefaultChangeOrderAcceptedMarketResponseMessage l_message =
                new DefaultChangeOrderAcceptedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.22.2. process()
            ProcessingResult l_result = l_callbackService.process(l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }
            try
            {
                l_orderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ne.getMessage(),
                    l_ne);
            }
            //1.22.3 isFullyExecuted()
            boolean l_isFullyExe = l_orderUnit.isFullyExecuted();
            //1.22.4. 全部約定（isFullyExecuted( )==true）の場合のみ
            if (l_isFullyExe)
            {
                //1.22.4.1. sendMailProcess()
                WEB3EquityExecutedMailSenderService l_mailSenderService =
                    (WEB3EquityExecutedMailSenderService)Services.getService(
                        WEB3EquityExecutedMailSenderService.class);
                l_mailSenderService.sendMailProcess(l_orderUnit, null);
                try
                {
                    //notifyルールエンジンサーバ
                    l_orderManager.notifyRLS(
                        l_orderUnit,
                        OrderManagerPersistenceContext.FILL_ORDER);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
                }
                
            }
        }
        //1.23. 通知キュー.訂正取消通知区分＝"訂正失敗"の場合
        else if (WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_params.getCanmodReceiptType()))
        {
            //1.23.1. DefaultChangeOrderRejectedMarketResponseMessage()
            DefaultChangeOrderRejectedMarketResponseMessage l_message =
                new DefaultChangeOrderRejectedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.23.2. process()
            ProcessingResult l_result = l_callbackService.process(l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }
            try
            {
                l_orderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ne.getMessage(),
                    l_ne);
            }
        }

        //1.24. 余力再計算
        try
        {
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);            
        }
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validate訂正結果コード）。<BR>
     * <BR>
     * 訂正時における、【訂正取消通知キューテーブル】の「訂正結果コード」の値の正当性チェックを行う。<BR>
     * <BR>
     * １）　@コード値が”0?”（訂正完了に該当）の場合、以下の訂正ケースのいずれかに該当するかチェックする。<BR>
     * 　@　@　@いずれにも該当しない場合は、例外をthrowする。<BR>
     * 　@　@　@　@　@　@02：全数訂正（内出来なし）<BR>
     * 　@　@　@　@　@　@03：全数訂正（内出来あり）<BR>
     * 　@　@　@　@　@　@05：一部訂正<BR>
     * 　@　@　@　@　@　@08：一部訂正不能（内出来なし）<BR>
     * 　@　@　@　@　@　@09：一部訂正不能（内出来あり）<BR>
     * <BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00129
     * @@param l_params （株式訂正取消通知キューParams）
     * @@roseuid 4039DE9A0220
     */
    public void validateChangeResultCode(
        HostEqtypeOrderClmdReceiptParams l_params)
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validateChangeResultCode(HostEqtypeOrderClmdReceiptParams)";
        log.entering(STR_METHOD_NAME);

        String l_strModifiedResult = l_params.getModifiedResult();

        String l_strFirstChar = l_strModifiedResult.substring(0, 1);
        if (l_strFirstChar.equals("0"))
        {
            if (WEB3ModifiedResultDef.ALL_CHANGED_NO_EXECUTED.equals(l_strModifiedResult) ||
                WEB3ModifiedResultDef.ALL_CHANGED_PARTIALLY_EXECUTED.equals(l_strModifiedResult) ||
                WEB3ModifiedResultDef.PARTIALLY_CHANGED.equals(l_strModifiedResult) ||
                WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_NO_EXECUTED.equals(l_strModifiedResult) ||
                WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_PARTIALLY_EXECUTED.equals(l_strModifiedResult))
            {
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00129,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
