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
filename	WEB3EquityReceiveCancelEventServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式訂正取消通知取消一件サービスImpl(WEB3EquityReceiveCancelEventServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/20 中尾寿彦(SRA) 新規作成
Revesion History : 2006/11/06 張騰宇(中訊) モデル 1032
Revesion History : 2006/11/28 張騰宇(中訊) モデル 1064
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityReceiveCancelInterceptor;
import webbroker3.equity.WEB3EquityReceiveCancelSpec;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCancelEventService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式訂正取消通知取消一件サービスImpl）。<BR>
 * <BR>
 * 現物株式訂正取消通知取消一件サービス実装クラス
 * @@version 1.0
 */
public class WEB3EquityReceiveCancelEventServiceImpl
    implements WEB3EquityReceiveCancelEventService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveCancelEventServiceImpl.class);
    
    /**
     * @@roseuid 40A435C90074
     */
    public WEB3EquityReceiveCancelEventServiceImpl()
    {
    }

    /**
     * (notify取消)<BR>
     * <BR>
     * 訂正取消通知サービスより【株式訂正取消通知キューテーブル】の取消データを一件受け取り、<BR>
     * 注文取消完了／注文取消失敗処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（訂正取消通知取消一件サービス）notify取消」参照<BR>
     * <BR>
     * 戻り値は、株式注文訂正取消通知キューテーブル更新時に、<BR>
     * 処理区分設定値として使用する。<BR>
     * <BR>
     * @@param l_params - (株式訂正取消通知キューParams)<BR>
     * 株式訂正取消通知キューテーブルの１レコード<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * @@return String
     * @@roseuid 4036022D0259
     */
    public String notifyCancel(
        HostEqtypeOrderClmdReceiptParams l_params,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "notifyCancel(HostEqtypeOrderClmdReceiptParams, EqTypeOrderUnit)";
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
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.1. validate訂正結果コード()
        validateChangeResultCode(l_params);

        //1.2. 当該注文単位に対する出来が残っている場合
        if ((WEB3CanmodReceiptTypeDef.CANCEL.equals(l_params.getCanmodReceiptType()) == true)
        && (l_params.getModifiedQuantity() > l_orderUnitRow.getExecutedQuantity()))
        {
            return WEB3StatusDef.DEALING;
        }
        
        //1.3. create()
        WEB3EquityChangeCancelNotifyDataAdapter l_dataAdapter =
            WEB3EquityChangeCancelNotifyDataAdapter.create(l_params);

        //1.4. 株式取消通知内容()
        WEB3EquityReceiveCancelSpec l_receiveCancelSpec = new WEB3EquityReceiveCancelSpec();

        //1.5. get執行条件()
        EqTypeExecutionConditionType l_conditionType =
            l_dataAdapter.getExecCondType();
            
        //1.6. set訂正後執行条件()
        l_receiveCancelSpec.setChangeAfterExecCond(l_conditionType);
        
        //1.7. get値段条件()
        String l_strPriceConditionType = l_dataAdapter.getPriceConditionType();
        
        //1.8. set訂正後値段条件()
        l_receiveCancelSpec.setChangeAfterPriceConditionType(l_strPriceConditionType);
        
        WEB3GentradeAccountManager l_accManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        double l_dblEstimatedPrice = 0.0;
        double l_dblLimitPrice = 0.0;
        WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;
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
            //市場から確認済みの注文単価
            l_dblLimitPrice = l_orderUnitRow.getConfirmedOrderPrice();
        }
        //通知キュー.訂正取消通知区分＝"取消失敗"、かつ
        //株式データアダプタ.getＷ指値用有効状態区分＝"リミット注文有効" のみ
        else if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_params.getCanmodReceiptType()))
        {
            if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit)))
            {
                //get補助口座(口座ID : long, 補助口座ID : long)
                WEB3GentradeSubAccount l_subAccount = null;
                try
                {
                    l_subAccount =
                        (WEB3GentradeSubAccount)l_accManager.getSubAccount(
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

                //getストップ注文失効時概算代金計算結果(EqtypeOrderUnit, SubAccount)
                l_equityEstimatedPrice =
                    l_orderManager.getStopOrderExpireEstimatedPrice(l_orderUnit, l_subAccount);
            }

            if (l_equityEstimatedPrice != null)
            {
                //getストップ注文失効時概算代金計算結果の戻り値がnull以外の場合
                l_dblLimitPrice = l_equityEstimatedPrice.getCalcUnitPrice();
                l_dblEstimatedPrice = l_equityEstimatedPrice.getEstimateDeliveryAmount();
            }
            else
            {
                //市場から確認済みの概算受渡代金
                l_dblEstimatedPrice = l_orderUnitRow.getConfirmedEstimatedPrice();
                //市場から確認済みの注文単価
                l_dblLimitPrice = l_orderUnitRow.getConfirmedOrderPrice();
            }
        }

        //set注文単価(Double)
        l_receiveCancelSpec.setLimitPrice(l_dblLimitPrice);

        //set受渡代金(double)
        l_receiveCancelSpec.setEstimatedPrice(l_dblEstimatedPrice);

        //1.11. 株式注文取消通知インタセプタ()
        WEB3EquityReceiveCancelInterceptor l_interceptor =
            new WEB3EquityReceiveCancelInterceptor(l_params);

        //1.12. set株式取消通知内容()
        l_interceptor.setReceiveCancelSpec(l_receiveCancelSpec);

        //1.13. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_interceptor);

        //1.14. setBusinessTimestamp()
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();
        
        //isSONAR取消(EqTypeOrderUnit)
        boolean l_blnIsSONARCancel = l_orderManager.isSONARCancel(l_orderUnit);
        
        //1.15. 通知キュー.訂正取消通知区分＝"取消完了"の場合
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        MarketResponseReceiverCallbackService l_callbackService =
            l_marketAdapter.getMarketResponseReceiverCallbackService();
        if (WEB3CanmodReceiptTypeDef.CANCEL.equals(l_params.getCanmodReceiptType()))
        {
            //1.15.1. DefaultCancelOrderAcceptedMarketResponseMessage()
            DefaultCancelOrderAcceptedMarketResponseMessage l_message =
                new DefaultCancelOrderAcceptedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            //1.15.2. process()
            ProcessingResult l_result = l_callbackService.process(l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }
            //1.15.3. getOrderUnit()
            try
            {
                l_orderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.15.4. isUnexecuted()
            boolean l_isUnexecuted = l_orderUnit.isUnexecuted();
            //1.15.5. 出来が付いている（注文単位.isUnexecuted( )==false）の場合のみ
            if (l_isUnexecuted == false)
            {
                //1.15.5.1. sendMailProcess()
                WEB3EquityExecutedMailSenderService l_mailSenderService =
                    (WEB3EquityExecutedMailSenderService)Services.getService(
                        WEB3EquityExecutedMailSenderService.class);
                l_mailSenderService.sendMailProcess(l_orderUnit, null);
            }
            
            if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
            {
                WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                l_updateService.cancelAllOrderUnit(l_orderUnit.getOrderId());
            }
            //SONAR入力の取消（isSONAR取消()の戻り値 == true）の場合
            if (l_blnIsSONARCancel)
            {
                log.debug("isSONAR取消()の戻り値 == true");
                //notifyルールエンジンサーバ(EqTypeOrderUnit, OrderManagerPersistenceContext)
                try
                {
                    l_orderManager.notifyRLS(
                        l_orderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
                }
            }
        }
        //1.16. 通知キュー.訂正取消通知区分＝"取消失敗"の場合
        else if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_params.getCanmodReceiptType()))
        {
            //getOrderUnit()
            try
            {
                l_orderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            // SONAR入力の取消でない（isSONAR取消() == true）場合
            if (l_blnIsSONARCancel)
            {
                //notifyルールエンジンサーバ(EqTypeOrderUnit, OrderManagerPersistenceContext)
                try
                {
                    l_orderManager.notifyRLS(
                        l_orderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
                }
            }

            //1.16.1. DefaultCancelOrderRejectedMarketResponseMessage()
            DefaultCancelOrderRejectedMarketResponseMessage l_message =
                new DefaultCancelOrderRejectedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            //1.16.2. process()
            ProcessingResult l_result = l_callbackService.process(l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }
        }
        
        //1.17. 余力再計算
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
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

        //1.18. 戻り値"処理済"をreturnする。
        log.exiting(STR_METHOD_NAME);
        return WEB3StatusDef.DEALT;
    }

    /**
     * (validate訂正結果コード)<BR>
     * <BR>
     * 取消時における、【訂正取消通知キューテーブル】の「訂正結果コード」の値の正当性チェックを行う。<BR>
     * <BR>
     * １）　@コード値が”0?”（取消完了に該当）の場合、以下の取消ケースのいずれかに該当するかチェックする。<BR>
     * 　@　@　@いずれにも該当しない場合は、例外をthrowする。<BR>
     * 　@　@　@　@　@　@01：全部取消<BR>
     * 　@　@　@　@　@　@04：一部取消<BR>
     * 　@　@　@　@　@　@06：一部取消不能（内出来なし）<BR>
     * 　@　@　@　@　@　@07：一部取消不能（内出来あり）<BR>
     * <BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00128<BR>
     * <BR>
     * @@param l_params - (株式訂正取消通知キューParams)
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4039DC0303B7
     */
    public void validateChangeResultCode(HostEqtypeOrderClmdReceiptParams l_params)
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validateChangeResultCode(HostEqtypeOrderClmdReceiptParams)";
        log.entering(STR_METHOD_NAME);

        String l_modifiedResult = l_params.getModifiedResult();
        String l_strFirstChar = l_modifiedResult.substring(0, 1);
        if (l_strFirstChar.equals("0"))
        {
            if (WEB3ModifiedResultDef.ALL_CANCEL.equals(l_modifiedResult) ||
                WEB3ModifiedResultDef.PARTIALLY_CANCEL.equals(l_modifiedResult) ||
                WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_NO_EXECUTED.equals(l_modifiedResult) ||
                WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_EXECUTED.equals(l_modifiedResult))
            {
            }
            else
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00128,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
