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
filename	WEB3MarginChangeCancelNotifyCancelUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引訂正取消通知取消一件サービスImpl(WEB3MarginChangeCancelNotifyCancelUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 艾興 (中訊) 新規作成
Revesion History : 2006/11/27 張騰宇 (中訊) モデルNo.1032
Revesion History : 2006/11/28 趙林鵬 (中訊) モデル No.1067
Revesion History : 2007/01/31 唐性峰(中訊) モデル 1116
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityReceiveCancelInterceptor;
import webbroker3.equity.WEB3EquityReceiveCancelSpec;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyCancelUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引訂正取消通知取消一件サービスImpl）。<BR>
 * <BR>
 * 信用取引訂正取消通知取消一件サービス実装クラス
 * @@version 1.0
 */
public class WEB3MarginChangeCancelNotifyCancelUnitServiceImpl
    implements WEB3MarginChangeCancelNotifyCancelUnitService
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginChangeCancelNotifyCancelUnitServiceImpl.class);
    /**
     * @@roseuid 414006700243
     */
    public WEB3MarginChangeCancelNotifyCancelUnitServiceImpl()
    {

    }

    /**
     * (notify取消)<BR>
     * 注文取消通知処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引訂正取消通知取消一件サービス）notify取消」参照<BR>
     * <BR>
     * 戻り値は、株式注文訂正取消通知キューテーブル更新時に、<BR>
     * 処理区分設定値として使用する。
     * @@param l_params (株式訂正取消通知キューParams)<BR>
     * 　@　@　@株式訂正取消通知キューParamsオブジェクト。
     * @@param l_orderUnit (注文単位)<BR>
     * 　@　@　@注文単位オブジェクト。
     * @@return String
     * @@roseuid 40F3DA5702CD
     */
    public String notifyCancel(
        HostEqtypeOrderClmdReceiptParams l_params,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyChange(HostEqtypeOrderClmdReceiptParams, EqTypeOrderUnit)";
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
        
        //1.2. 当該注文単位に対する出来が残っている場合
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if ((WEB3CanmodReceiptTypeDef.CANCEL.equals(l_params.getCanmodReceiptType()) == true)
        && (l_params.getModifiedQuantity() > l_orderUnitRow.getExecutedQuantity()))
        {
            return WEB3StatusDef.DEALING;
        }
        
        //1.3. create()
        WEB3EquityChangeCancelNotifyDataAdapter l_dataAdapter =
            WEB3EquityChangeCancelNotifyDataAdapter.create(l_params);
        
        //1.4. 株式取消通知内容()
        WEB3EquityReceiveCancelSpec l_receiveCancelSpec =
            new WEB3EquityReceiveCancelSpec();
            
        //1.5. get執行条件()
        EqTypeExecutionConditionType l_conditionType =
            l_dataAdapter.getExecCondType();
            
        //1.6. set訂正後執行条件()
        l_receiveCancelSpec.setChangeAfterExecCond(l_conditionType);
        
        //1.7. get値段条件()
        String l_strPriceConditionType = l_dataAdapter.getPriceConditionType();
        
        //1.8. set訂正後値段条件()
        l_receiveCancelSpec.setChangeAfterPriceConditionType(l_strPriceConditionType);
        
        WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;
        //通知キュー.訂正取消通知区分＝"取消失敗"、
        //かつ 株式データアダプタ.getＷ指値用有効状態区分＝"リミット注文有効" のみ
        if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_params.getCanmodReceiptType())
            && WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit)))
        {
            //get補助口座(口座ID : long, 補助口座ID : long)
            //リクエスト注文単位.口座ID
            //リクエスト注文単位.補助口座ID
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount =
                    (SubAccount)l_accountManager.getSubAccount(
                        l_orderUnitRow.getAccountId(),
                        l_orderUnitRow.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //getストップ注文失効時概算代金計算結果(EqtypeOrderUnit, SubAccount)
            l_equityEstimatedPrice =
                l_orderManager.getStopOrderExpireEstimatedPrice(
                    l_orderUnit,
                    l_subAccount);
        }

        //set注文単価(Double)
        if (l_equityEstimatedPrice != null)
        {
            l_receiveCancelSpec.setLimitPrice(l_equityEstimatedPrice.getCalcUnitPrice());
        }
        else
        {
            l_receiveCancelSpec.setLimitPrice(l_orderUnitRow.getConfirmedOrderPrice());
        }

        //1.10. set受渡代金()
        double l_dblEstimatedPrice;
        if (WEB3CanmodReceiptTypeDef.CANCEL.equals(l_params.getCanmodReceiptType()))
        {
            if (!l_orderUnit.isUnexecuted())
            {
                l_dblEstimatedPrice =
                    l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
            }
            else
            {
                l_dblEstimatedPrice = l_orderUnitRow.getEstimatedPrice();
            }
        }
        else
        {
            if (l_equityEstimatedPrice != null)
            {
                l_dblEstimatedPrice = l_equityEstimatedPrice.getEstimateDeliveryAmount();
            }
            else
            {
                l_dblEstimatedPrice = l_orderUnitRow.getConfirmedEstimatedPrice();
            }
        }
        l_receiveCancelSpec.setEstimatedPrice(l_dblEstimatedPrice);
        
        //1.11. 株式注文取消通知インタセプタ()
        WEB3EquityReceiveCancelInterceptor l_interceptor =
            new WEB3EquityReceiveCancelInterceptor(l_params);
            
        //1.12. set株式取消通知内容()
        l_interceptor.setReceiveCancelSpec(l_receiveCancelSpec);
        
        //1.13. setThreadLocalPersistenceEventInterceptor()

        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_interceptor);
            
        //1.14. getMarketResponseReceiverCallbackService()
        MarketAdapter l_adapter = l_tradingMod.getMarketAdapter();
        MarketResponseReceiverCallbackService l_marketCallbackService =
            l_adapter.getMarketResponseReceiverCallbackService();
        
        //1.15. setBusinessTimestamp()
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

        //isSONAR取消(EqTypeOrderUnit)
        //SONARからの取消かどうか判別する。
        //引数は以下の通りにセットする。
        //注文単位：　@引数.注文単位
        boolean l_blnIsSONARCancel = l_orderManager.isSONARCancel(l_orderUnit);

        //1.16. 取消完了の場合
        if (WEB3CanmodReceiptTypeDef.CANCEL.equals(l_params.getCanmodReceiptType()))
        {
            log.debug("取消完了の場合");
            //1.16.1 DefaultCancelOrderAcceptedMarketResponseMessage()
            DefaultCancelOrderAcceptedMarketResponseMessage l_message =
                new DefaultCancelOrderAcceptedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.16.2. process()
            ProcessingResult l_result = l_marketCallbackService.process(l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }                
            
            EqTypeOrderUnit l_newOrderUnit = null;
            try
            {
                l_newOrderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }	
            
            //1.16.3. isUnexecuted()
	        boolean l_isUnExe = l_newOrderUnit.isUnexecuted();
	            
	        //1.16.4. 約定がついている場合
	        if (l_isUnExe == false)
	        {
	            WEB3EquityExecutedMailSenderService l_mailSenderService =
	                (WEB3EquityExecutedMailSenderService)Services.getService(
	                    WEB3EquityExecutedMailSenderService.class);
	            l_mailSenderService.sendMailProcess(l_newOrderUnit, null);
	        }
            
            if (l_orderManager.isReserveOrderConfirmRequire(l_newOrderUnit))
            {
                WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                l_updateService.cancelAllOrderUnit(l_newOrderUnit.getOrderId());
            }

            //(*)SONAR入力の取消（isSONAR取消() == true）の場合
            if (l_blnIsSONARCancel)
            {
                try
                {
                    //notifyルールエンジンサーバ(EqTypeOrderUnit, OrderManagerPersistenceContext)
                    //注文単位：　@注文単位オブジェクト
                    //処理：　@ CANCEL_ORDER_CONFIRMED_BY_MKT
                    l_orderManager.notifyRLS(
                        l_newOrderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT);
                }
                //(*)notifyルールエンジンサーバ()にて業務エラーがスローされた場合
                //(*)catchして処理を続行する。　@※ロールバックしない。
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合, ※ロールバックしない。");
                }
            }
	    }
	    
        //1.17. 取消失敗の場合
        else if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_params.getCanmodReceiptType()))
        {
            log.debug("取消失敗の場合");
            //(*)SONAR入力の取消（isSONAR取消() == true）場合
            if (l_blnIsSONARCancel)
            {
                // notifyルールエンジンサーバ(EqTypeOrderUnit, OrderManagerPersistenceContext)
                //引数は以下の通りに設定する。
                //[引数の設定]
                //注文単位：　@注文単位オブジェクト
                //処理：　@ CANCEL_ORDER_REJECTED_BY_MKT
                EqTypeOrderUnit l_eqTypeOrderUnit = null;
                try
                {
                    l_eqTypeOrderUnit =
                        (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                try
                {
                    l_orderManager.notifyRLS(
                        l_eqTypeOrderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT);
                }
                //(*)notifyルールエンジンサーバ()にて業務エラーがスローされた場合
                //(*)catchして処理を続行する。　@※ロールバックしない。
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合, ※ロールバックしない。");
                }
            }

            //1.17.1 DefaultCancelOrderRejectedMarketResponseMessage()
            DefaultCancelOrderRejectedMarketResponseMessage l_message =
                new DefaultCancelOrderRejectedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            //1.17.2. process()
            ProcessingResult l_result = l_marketCallbackService.process(
            l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }

        }
        
            
        //1.18. 余力再計算
        WEB3GentradeSubAccount l_subAccount = null;
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        
        //1.19. 戻り値"処理済"をreturnする。
        log.exiting(STR_METHOD_NAME);
        return WEB3HostStatusDef.COMPLETE_PROCESS;
    }

    /**
     * (validate訂正結果コード)<BR>
     * 取消時における、【訂正取消通知キューテーブル】の<BR>
     *    「訂正結果コード」の値の正当性チェックを行う。<BR>
     * <BR>
     * １）　@コード値が”0?”（取消完了に該当）の場合、<BR>
     *    以下の取消ケースのいずれかに<BR>該当するかチェックする。<BR>
     * 　@　@　@いずれにも該当しない場合は、例外をthrowする。<BR>
     * 　@　@　@　@　@　@01：全部取消<BR>
     * 　@　@　@　@　@　@04：一部取消<BR>
     * 　@　@　@　@　@　@06：一部取消不能（内出来なし）<BR>
     * 　@　@　@　@　@　@07：一部取消不能（内出来あり）<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00128
     * @@param l_eqtypeChangeCancelNotifyQueueParams (株式訂正取消通知キューParams)<BR>
     * @@roseuid 40F3DFBD03DD
     */
    protected void validateChangeResultCode(HostEqtypeOrderClmdReceiptParams l_eqtypeChangeCancelNotifyQueueParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateChangeResultCode(HostEqtypeOrderClmdReceiptParams l_eqtypeChangeCancelNotifyQueueParams)";
        log.entering(STR_METHOD_NAME);
        String l_modifyResult =
            l_eqtypeChangeCancelNotifyQueueParams.getModifiedResult();
        if((l_modifyResult.length() == 2) && l_modifyResult.startsWith("0"))
        {        
            if (WEB3ModifiedResultDef.ALL_CANCEL.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_CANCEL.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_NO_EXECUTED.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_EXECUTED.equals(
                    l_modifyResult))
            {
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00128,
                    STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
