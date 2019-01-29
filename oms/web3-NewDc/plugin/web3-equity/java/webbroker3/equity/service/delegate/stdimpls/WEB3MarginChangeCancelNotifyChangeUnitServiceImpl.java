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
filename	WEB3MarginChangeCancelNotifyChangeUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引訂正取消通知訂正一件サービスImpl(WEB3MarginChangeCancelNotifyChangeUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 艾興 (中訊) 新規作成
                   2004/12/17 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/11/27 張騰宇(中訊) （モデル）No.1032 1046
                   2006/11/28 趙林鵬 (中訊) モデル No.1066 1078
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityReceiveChangeInterceptor;
import webbroker3.equity.WEB3EquityReceiveChangeSpec;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyChangeUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引訂正取消通知訂正一件サービスImpl）。<BR>
 * <BR>
 * 信用取引訂正取消通知訂正一件サービス実装クラス
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3MarginChangeCancelNotifyChangeUnitServiceImpl
    implements WEB3MarginChangeCancelNotifyChangeUnitService
{
    /**
      * (ログ出力ユーティリティ。)
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginChangeCancelNotifyChangeUnitServiceImpl.class);

    /**
     * @@roseuid 4140067002C5
     */
    public WEB3MarginChangeCancelNotifyChangeUnitServiceImpl()
    {

    }

    /**
     * (notify訂正)<BR>
     * 注文訂正通知処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引訂正取消通知訂正一件サービス）notify訂正」参照<BR>
     * @@param l_params - (株式訂正取消通知キューParams)<BR>
     * 株式訂正取消通知キューParamsオブジェクト。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@roseuid 40F3DA5702CD
     */
    public void notifyChange(
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
        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingMod.getPositionManager();
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission(l_orderUnit.getOrderId());

        // isストップ注文有効(EqTypeOrderUnit)
        boolean l_blnIsStopOrderValid = false;
        if (l_orderManager.isStopOrderSwitching(l_orderUnit) 
            && WEB3CanmodReceiptTypeDef.CHANGED.equals(l_params.getCanmodReceiptType()))
        {
            l_blnIsStopOrderValid = true;
        }
        else
        {
            l_blnIsStopOrderValid = l_orderManager.isStopOrderValid(l_orderUnit);
        }
    
        //1.11. getInstitution()
        Institution l_institution = null;
        try
        {
            l_institution = l_accMgr.getInstitution(l_params.getInstitutionCode());
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.12. getInstitutionId()
        long l_institutionId = l_institution.getInstitutionId();
        
        MainAccount l_mainAccount = null;
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            //1.13. getMainAccount()
            l_mainAccount = l_accMgr.getMainAccount(
                l_institutionId,
                l_params.getBranchCode(),
                l_params.getAccountCode());
            //1.14. getSubAccount()
            l_subAccount =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //is売注文(EqTypeOrderUnit)
        boolean l_isSellOrder = l_orderManager.isSellOrder(l_orderUnit);

        //1.16. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct =
            (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();

        //1.18. 新規建注文の場合
        WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;
        WEB3EquityEstimatedContractPrice l_equityEstimatedContractPrice = null;
        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
        {
            //注文失効時の場合
            if (l_orderManager.isStopOrderSwitching(l_orderUnit)
                && WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_params.getCanmodReceiptType()))
            {
                //getストップ注文失効時概算代金計算結果(EqtypeOrderUnit, SubAccount)
                l_equityEstimatedPrice =
                    l_orderManager.getStopOrderExpireEstimatedPrice(
                        l_orderUnit,
                        l_subAccount);
            }

            if (l_equityEstimatedPrice == null)
            {
                //calc注文時建代金
                //手数料：　@create手数料()の戻り値
                //指値：　@株式訂正取消通知キューParams.訂正後指値
                //（W指値）訂正指値：　@注文単位.（Ｗ指値）訂正指値
                //逆指値基準値：　@注文単位.逆指値基準値
                //執行条件：　@株式訂正取消通知データアダプタ.get執行条件()の戻り値
                //（W指値）執行条件：　@注文単位.（Ｗ指値）執行条件
                //値段条件：　@株式訂正取消通知データアダプタ.get値段条件()の戻り値
                //発注条件：　@注文単位.発注条件
                //確認時取得時価：　@null（固定）
                //isストップ注文有効：
                //　@[拡張株式注文マネージャ.isストップ注文切替中() == trueかつ、
                //　@通知キュー.訂正取消通知区分 == "訂正完了"の場合]
                //　@　@true（固定）
                //　@[上記以外の場合]
                //　@　@isストップ注文有効()の戻り値
                //is売建：　@is売注文()の戻り値
                //補助口座：　@getSubAccount()の戻り値
                //取引銘柄：　@getTradedProduct()の戻り値
                //株数：　@通知キューParams.訂正後数量
                //約定数量：　@注文単位.約定数量
                //合計約定金額：　@注文単位.合計約定金額
                //isSkip金額チェック：　@true(スキップする)
                l_equityEstimatedContractPrice =
                    l_orderManager.calcContractAmountAtOrder(
                        l_commission,
                        l_params.getModifiedLimitPrice(),
                        l_orderUnitRow.getWLimitPrice(),
                        l_orderUnitRow.getStopOrderPrice(),
                        l_dataAdapter.getExecCondType(),
                        l_orderUnitRow.getWLimitExecCondType(),
                        l_dataAdapter.getPriceConditionType(),
                        l_orderUnitRow.getOrderConditionType(),
                        null,
                        l_blnIsStopOrderValid,
                        l_isSellOrder,
                        (SubAccount)l_subAccount,
                        (EqTypeTradedProduct)l_tradedProduct,
                        l_params.getModifiedQuantity(),
                        l_orderUnitRow.getExecutedQuantity(),
                        l_orderUnitRow.getExecutedAmount(),
                        true);
                // set注文単価(double)
                l_receiveChangeSpec.setLimitPrice(l_equityEstimatedContractPrice.getCalcUnitPrice());

                //set概算受渡代金()
                l_receiveChangeSpec.setEstimateDeliveryAmount(
                    l_equityEstimatedContractPrice.getEstimateDeliveryAmount());
            }
            else
            {
                //set注文単価(double)
                l_receiveChangeSpec.setLimitPrice(l_equityEstimatedPrice.getCalcUnitPrice());

                //set概算受渡代金()
                l_receiveChangeSpec.setEstimateDeliveryAmount(
                    l_equityEstimatedPrice.getEstimateDeliveryAmount());
            }
        }
        //1.19. 返済注文の場合
        else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
        {
            //1.19.1. adjust返済指定情報()
            l_positionManager.adjustClosingContractSpecInfo(
                l_mainAccount.getAccountId(),
                l_subAccount.getSubAccountId(),
                l_orderUnit.getOrderId(),
                l_orderUnit.getOrderUnitId(),
                l_orderUnit.getQuantity(),
                l_params.getModifiedQuantity());
            
            //create決済建株エントリ()
            EqTypeSettleContractOrderEntry[] l_entry =
                l_positionManager.createCloseMarginContractEntry(l_orderUnit.getOrderUnitId());

            //setIs指値(is指値 : boolean)
            if (l_params.getModifiedLimitPrice() != 0)
            {
                l_commission.setIsLimitPrice(true);
            }
            else
            {
                l_commission.setIsLimitPrice(false);
            }

            //calc概算決済損益代金()
            WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice = 
                l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_params.getModifiedLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_entry,
                    l_params.getModifiedQuantity(),
                    l_orderUnit,
                    0D,
                    0D,
                    true);            
            
            //set注文単価(double)
            l_receiveChangeSpec.setLimitPrice(l_profitAndLossPrice.getCalcUnitPrice());
            
            //1.19.5. set概算受渡代金()
            l_receiveChangeSpec.setEstimateDeliveryAmount(
                l_profitAndLossPrice.getEstimatedRealizedProfitAndLossAmount());               
        }
        
        //1.20. 株式注文訂正通知インタセプタ()
        WEB3EquityReceiveChangeInterceptor l_interceptor =
            new WEB3EquityReceiveChangeInterceptor(l_params);
        
        //1.21. set株式訂正通知内容()
        l_interceptor.setEquityChangeNotifySpec(l_receiveChangeSpec);
        
        //1.22. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //1.23. getMarketResponseReceiverCallbackService()
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        MarketResponseReceiverCallbackService l_marketCallbackService =
            l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        //1.24. setBusinessTimestamp()
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();
        
        //1.25. 訂正完了の場合
        if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_params.getCanmodReceiptType()))
        {
            //1.25.1. DefaultChangeOrderAcceptedMarketResponseMessage()
            DefaultChangeOrderAcceptedMarketResponseMessage l_message =
                new DefaultChangeOrderAcceptedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.25.2 process()
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
            
	        //1.25.3 isFullyExecuted()
	        boolean l_isFullyExe = l_newOrderUnit.isFullyExecuted();
	        
	        //1.25.4 訂正後に全部約定となった場合
	        if (l_isFullyExe)
	        {
	            WEB3EquityExecutedMailSenderService l_mailSenderService =
	                (WEB3EquityExecutedMailSenderService)Services.getService(
	                    WEB3EquityExecutedMailSenderService.class);
	            l_mailSenderService.sendMailProcess(l_newOrderUnit, null);
                
                try
                {
                    l_orderManager.notifyRLS(
                        l_newOrderUnit,
                        OrderManagerPersistenceContext.FILL_ORDER);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
                }
	        }
        }
        //1.26. 訂正失敗の場合
        else if(WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_params.getCanmodReceiptType()))
        {
            //1.26.1. DefaultChangeOrderRejectedMarketResponseMessage()
            DefaultChangeOrderRejectedMarketResponseMessage l_message =
                new DefaultChangeOrderRejectedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.26.2. process()
            ProcessingResult l_result = l_marketCallbackService.process(l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }
        }

        
        //1.27. 余力再計算
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate訂正結果コード)<BR>
     * 訂正時における、【訂正取消通知キューテーブル】の「訂正結果コード」<BR>
     *    の値の正当性チェックを行う。<BR>
     * <BR>
     * １）　@コード値が”0?”（訂正完了に該当）の場合、<BR>
     *    以下の訂正ケースのいずれかに<BR>該当するかチェックする。<BR>
     * 　@　@　@いずれにも該当しない場合は、例外をthrowする。<BR>
     * 　@　@　@　@　@　@02：全数訂正（内出来なし）<BR>
     * 　@　@　@　@　@　@03：全数訂正（内出来あり）<BR>
     * 　@　@　@　@　@　@05：一部訂正<BR>
     * 　@　@　@　@　@　@08：一部訂正不能（内出来なし）<BR>
     * 　@　@　@　@　@　@09：一部訂正不能（内出来あり）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00129<BR>
     * @@param l_eqtypeOrderClmdReceiptParams - (株式訂正取消通知キューParams)<BR>
     * @@roseuid 40F3DFF80062
     */
    protected void validateChangeResultCode(HostEqtypeOrderClmdReceiptParams l_eqtypeOrderClmdReceiptParams)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateChangeResultCode(HostEqtypeOrderClmdReceiptParams l_eqtypeOrderClmdReceiptParams)";
        log.entering(STR_METHOD_NAME);
        String l_modifyResult =l_eqtypeOrderClmdReceiptParams.getModifiedResult();
        if((l_modifyResult.length() == 2) && l_modifyResult.startsWith("0"))
        {      
            if (WEB3ModifiedResultDef.ALL_CHANGED_NO_EXECUTED.equals(l_modifyResult)
                || WEB3ModifiedResultDef.ALL_CHANGED_PARTIALLY_EXECUTED.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_CHANGED.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_NO_EXECUTED.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_PARTIALLY_EXECUTED.equals(l_modifyResult))
            {
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00129,
                        STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
