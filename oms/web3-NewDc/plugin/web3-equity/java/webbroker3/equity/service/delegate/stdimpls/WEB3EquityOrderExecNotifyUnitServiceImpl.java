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
filename	WEB3EquityOrderExecNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式出来通知一件サービスImpl(WEB3EquityOrderExecNotifyPartServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
                   2004/12/02 中尾寿彦(SRA) 残案件対応による修正
                   2004/12/29 岡村和明(SRA) コメントの修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/11/21 唐性峰　@(中訊)モデルNo.1040
                   2006/11/28 張騰宇(中訊) モデル 1071
*/

package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultFillOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3HostStatusDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityOrderExecNotifyInterceptor;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式出来通知一件サービスImpl）。<BR>
 * <BR>
 * 現物株式出来通知を一件ずつ処理するサービス
 * @@version 1.0
 */
public class WEB3EquityOrderExecNotifyUnitServiceImpl
    implements WEB3EquityOrderExecNotifyUnitService
{
    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderExecNotifyUnitServiceImpl.class);

    /**
     * コンストラクタ。<BR>
     */
    public WEB3EquityOrderExecNotifyUnitServiceImpl()
    {
    }

    /**
     * (notify約定)<BR>
     * <BR>
     * [処理概要]<BR>
     * 取得した全部約定または一部約定である出来通知キュー１レコードに対する約定処理を行う。<BR>
     * <BR>
     * シーケンス図「（出来通知一件サービス）notify約定」参照。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 出来通知キュー.識別コードに該当する、注文単位オブジェクト。<BR>
     * @@param l_equityExecNotifyQueueParams - 
     *  株式出来通知キューParamsオブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void notifyExecute(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "notifyExecute(EqTypeOrderUnit, HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
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
        
        // 1.2. validate注文状態()
        this.validateOrderStatus(l_orderUnit);

        // 1.3. validate全部約定()
        this.validateAllOrderExecution(l_orderUnit);
    
        // 1.4. validate二重約定()
        this.validateDoubleOrderExecution(
            l_orderUnit,
            l_equityExecNotifyQueueParams.getExecQuantity());

        // 株式出来通知インタセプタの生成
        WEB3EquityOrderExecNotifyInterceptor l_updateInterceptor =
            new WEB3EquityOrderExecNotifyInterceptor(
                l_equityExecNotifyQueueParams.getExecTimestamp(),
                l_equityExecNotifyQueueParams.getExecPrice(),
                l_equityExecNotifyQueueParams.getExecQuantity());

        // 1.6. setThreadLocalPersistenceEventInterceptor()
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        // 1.7. DefaultFillOrderUnitSpec()
        DefaultFillOrderUnitSpec l_unitSpec = new DefaultFillOrderUnitSpec(
            l_orderUnit.getOrderUnitId(),
            l_equityExecNotifyQueueParams.getExecQuantity(),
            l_equityExecNotifyQueueParams.getExecPrice());

        // 1.8. DefaultOrderFillMarketResponseMessage()
        DefaultOrderFillMarketResponseMessage l_responseMessage = 
            new DefaultOrderFillMarketResponseMessage(
                l_orderUnit.getOrderId(),
                l_unitSpec);

        // 1.9. process(MarketResponseMessage)(EqTypeMarketResponseReceiverCallbackService::process)
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        EqTypeMarketResponseReceiverCallbackService l_callbackService = 
            (EqTypeMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
        log.debug("◎◎◎◎◎◎ notify約定にprocess() 開始 ◎◎◎◎◎◎");
        ProcessingResult l_result = l_callbackService.process(l_responseMessage);
        if (l_result.isFailedResult())
        {
            WEB3BaseException l_baseException = new WEB3BaseException(
                l_result.getErrorInfo(), 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_result.getErrorInfo().getErrorMessage());
            log.error(STR_METHOD_NAME, l_baseException);
            throw l_baseException;
        }        
        log.debug("◎◎◎◎◎◎ notify約定にprocess() 終了 ◎◎◎◎◎◎");

        // 1.11. getOrderUnit()
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                    l_orderUnit.getOrderUnitId());                       
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }

        // 1.12. isFullyExecuted()
        if (l_orderUnit.isFullyExecuted())
        {
            // 1.13. sendMailProcess()
            WEB3EquityExecutedMailSenderService l_mailSenderService = 
                (WEB3EquityExecutedMailSenderService)Services.getService(
                WEB3EquityExecutedMailSenderService.class);        
            l_mailSenderService.sendMailProcess(l_orderUnit, null);

            // notifyルールエンジンサーバ(EqTypeOrderUnit, OrderManagerPersistenceContext)
            //該当の注文が全部約定の場合、ルールエンジンサーバに通知する。
            //引数は以下の通りにセットする。
            //
            //注文単位：　@注文単位
            //処理：　@FILL_ORDER
            try
            {
                l_orderManager.notifyRLS(
                    l_orderUnit,
                    OrderManagerPersistenceContext.FILL_ORDER);
            }
            // (*)notifyルールエンジンサーバ()にて業務エラーがスローされた場合
            //(*)catchして処理を続行する。
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合, ※ロールバックしない。");
            }
        }

        // 1.14. isPartiallyExecuted()
        if (l_orderUnit.isPartiallyExecuted())
        {
            // 1.15. 一部約定かつ約定対象注文単位.値段条件＝"成行残数取消"の場合のみ、注文失効処理を実行。
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            if (WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
            {
                // 1.15.1. 株式失効通知キューParamsのインスタンス生成、及びプロパティセット
                WEB3GentradeMainAccount l_account = null;
                try
                {
                    l_account =
                        (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                            l_orderUnitRow.getAccountId());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(),l_nfe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(),
                        l_nfe);
                }
                HostEqtypeCloseOrderNotifyParams l_hostCloseNotifyParams = new HostEqtypeCloseOrderNotifyParams();
                l_hostCloseNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
                l_hostCloseNotifyParams.setInstitutionCode(l_equityExecNotifyQueueParams.getInstitutionCode());
                l_hostCloseNotifyParams.setBranchCode(l_equityExecNotifyQueueParams.getBranchCode());
                l_hostCloseNotifyParams.setAccountCode(l_account.getAccountCode());
                l_hostCloseNotifyParams.setTraderCode(l_equityExecNotifyQueueParams.getTraderCode());
                l_hostCloseNotifyParams.setOrderRequestNumber(l_equityExecNotifyQueueParams.getOrderRequestNumber());
                l_hostCloseNotifyParams.setExecutedQuantity(l_orderUnitRow.getExecutedQuantity());
                l_hostCloseNotifyParams.setReasonCode(WEB3ExpirationReasonCodeDef.PARTIALLY_EXECUTE_CLOSE);
                l_hostCloseNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
                l_hostCloseNotifyParams.setErrorMessage(null);
                l_hostCloseNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

                // 1.15.2. exec失効()
                WEB3EquityReceiveCloseOrderUnitService l_unitService =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(WEB3EquityReceiveCloseOrderUnitService.class);
                l_unitService.execCloseOrder(l_hostCloseNotifyParams, l_orderUnit);
            }
        }

        // 1.16. call失効通知処理()
        this.callExpirationNotify(l_orderUnit, l_equityExecNotifyQueueParams);

        // 1.17. call取消通知処理()
        this.callCancelNotify(l_orderUnit, l_equityExecNotifyQueueParams);

        // 1.18. 余力再計算()
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify約定取消)<BR>
     * <BR>
     * [処理概要]<BR>
     * 取得した出来通知キューが約定取消の場合、約定取消処理を行う。<BR>
     * <BR>
     * シーケンス図「（出来通知一件サービス）notify約定取消」参照。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 出来通知キュー.識別コードに該当する、注文単位オブジェクト。<BR>
     * @@param l_equityExecNotifyQueueParams - <BR>
     *   株式出来通知キューParamsオブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void notifyExecuteCancel(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyExecuteCancel(EqTypeOrderUnit, HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
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
        
        // 1.1. validate注文状態()
        this.validateOrderStatus(l_orderUnit);
        
        // 1.2. get取消対象約定()
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        EqTypeOrderExecution l_orderExecution = l_orderManager.getCancelOrderExecution(
            l_orderUnit,
            l_equityExecNotifyQueueParams.getExecQuantity(),
            l_equityExecNotifyQueueParams.getExecPrice());
        if (l_orderExecution == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00676, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // 1.4. 一部約定に対する約定取消かつ約定取消対象注文単位.値段条件=="成行残数取消"
        // 　@　@　@の場合のみ、失効取消処理を実行。
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_orderExecution.getExecutionQuantity() < l_orderUnit.getQuantity())
        {
            if (WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
            {
                // 1.4.1. 株式失効通知キューParamsのインスタンス生成、及びプロパティセット
                WEB3GentradeMainAccount l_account = null;
                try
                {
                    l_account =
                        (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                            l_orderUnitRow.getAccountId());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(),l_nfe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(),
                        l_nfe);
                }
                HostEqtypeCloseOrderNotifyParams l_hostCloseNotifyParams = new HostEqtypeCloseOrderNotifyParams();
                l_hostCloseNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
                l_hostCloseNotifyParams.setInstitutionCode(l_equityExecNotifyQueueParams.getInstitutionCode());
                l_hostCloseNotifyParams.setBranchCode(l_equityExecNotifyQueueParams.getBranchCode());
                l_hostCloseNotifyParams.setAccountCode(l_account.getAccountCode());
                l_hostCloseNotifyParams.setTraderCode(l_equityExecNotifyQueueParams.getTraderCode());
                l_hostCloseNotifyParams.setOrderRequestNumber(l_equityExecNotifyQueueParams.getOrderRequestNumber());
                l_hostCloseNotifyParams.setExecutedQuantity(l_orderUnitRow.getExecutedQuantity());
                l_hostCloseNotifyParams.setReasonCode(WEB3ExpirationReasonCodeDef.PARTIALLY_EXECUTE_CLOSE);
                l_hostCloseNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE_CANCEL);
                l_hostCloseNotifyParams.setErrorMessage(null);
                l_hostCloseNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

                // 1.4.1.1. exec失効()
                WEB3EquityReceiveCloseOrderUnitService l_unitService =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(WEB3EquityReceiveCloseOrderUnitService.class);
                l_unitService.execCloseOrder(l_hostCloseNotifyParams, l_orderUnit);
            }
        }
        
        // 株式出来通知インタセプタの生成
        WEB3EquityOrderExecNotifyInterceptor l_updateInterceptor =
            new WEB3EquityOrderExecNotifyInterceptor(
                l_equityExecNotifyQueueParams.getExecTimestamp(),
                l_equityExecNotifyQueueParams.getExecPrice(),
                l_equityExecNotifyQueueParams.getExecQuantity());
                
        // 1.5. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        // 1.6. DefaultUndoOrderFillMarketResponseMessage(long, long)
        DefaultUndoOrderFillMarketResponseMessage l_responseMessage = 
            new DefaultUndoOrderFillMarketResponseMessage(
                l_orderUnit.getOrderId(),
                l_orderExecution.getOrderExecutionId());
            
        // 1.7. process()
        EqTypeMarketResponseReceiverCallbackService l_callbackService = 
            (EqTypeMarketResponseReceiverCallbackService)l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
        log.debug("◎◎◎◎◎◎ notify約定取消にprocess() 開始 ◎◎◎◎◎◎");
        ProcessingResult l_result = l_callbackService.process(l_responseMessage);
        if (l_result.isFailedResult())
        {
            WEB3BaseException l_baseException = new WEB3BaseException(
                l_result.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_result.getErrorInfo().getErrorMessage());
            log.error(STR_METHOD_NAME, l_baseException);
            throw l_baseException;
        } 
        log.debug("◎◎◎◎◎◎ notify約定取消にprocess() 終了 ◎◎◎◎◎◎");

        // 1.8. get保有資産
        WEB3EquityPositionManager l_positionManager = 
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        EqTypeAsset l_asset = l_positionManager.getAsset(
            l_orderUnitRow.getAccountId(),
            l_orderUnitRow.getSubAccountId(),
            l_orderUnitRow.getProductId(),
            l_orderUnitRow.getTaxType());

        // 1.9. 一口約定処理()
        l_positionManager.shareContractExecution(l_orderUnit.getOrderUnitId(), l_asset.getAssetId(), true);

        // 1.11. getOrderUnit()
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }
        
        // 1.12. undoSendMail(EqTypeOrderUnit)(約定メール送信サービス::undoSendMail)
        WEB3EquityExecutedMailSenderService l_mailSenderService =
            (WEB3EquityExecutedMailSenderService)Services.getService(
            WEB3EquityExecutedMailSenderService.class);
        l_mailSenderService.undoSendMail(l_orderUnit);

        // 1.13. 余力再計算()
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (call失効通知処理)<BR>
     * <BR>
     * 一部約定かどうかをチェックし、<BR>
     * 【株式失効通知キューテーブル】に該当する「処理中」レコードが存在する場合は<BR>
     * 注文失効一件サービス」をコールする。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 出来通知キュー.識別コードに該当する、注文単位オブジェクト。<BR>
     * @@param l_equityOrderExecNotifyParams - <BR>
     *   株式出来通知キューParamsオブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void callExpirationNotify(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_equityOrderExecNotifyParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "callExpirationNotify(EqTypeOrderUnit, HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);

        // 1.2. 注文単位.失効区分≠"失効中"の場合は、何もせずreturnする。
        if (!OrderExpirationStatusEnum.EXPIRING.equals(l_orderUnit.getExpirationStatus()))
        {
            log.debug("注文単位.失効区分≠\"失効中\"なので、失効処理を行わない。");
            return;
        }

        // 1.3. getDefaultProcessor()
        QueryProcessor l_queryProcessor = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(),l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(), l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(),l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(), l_dne);
        }
        
        // 1.4. 株式出来通知call失効通知TransactionCallback()
        WEB3EquityCallCloseNotifyTransactionCallback l_transactionCallBack =
            new WEB3EquityCallCloseNotifyTransactionCallback(l_orderUnit, l_equityOrderExecNotifyParams);
        
        // 1.5. doTransaction()
        try
        {
            l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_JOIN_EXISTING,
                l_transactionCallBack);
        }
        catch (DataCallbackException l_dce)
        {
            log.error(l_dce.getMessage(), l_dce);
            WEB3BaseException l_bex = (WEB3BaseException)l_dce.getDetails();
            throw l_bex;
        }
        catch (DataException l_dex)
        {
            log.error(l_dex.getMessage(),l_dex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80077,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (call取消通知処理)<BR>
     * <BR>
     * 一部約定かどうかをチェックし、<BR>
     * 「信用取引訂正取消通知取消一件サービス」をコールする。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引出来通知一件サービス）call取消通知処理」参照。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 出来通知キュー.識別コードに該当する、注文単位オブジェクト。<BR>
     * @@param l_equityOrderExecNotifyParams - <BR>
     *   株式出来通知キューParamsオブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void callCancelNotify(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_equityOrderExecNotifyParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "callCancelNotify(EqTypeOrderUnit, HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);

        // 1.2. 注文単位.注文状態≠（"受付済（取消注文）" or "発注中（取消注文）"） == 取消中でない）の場合、何もせずreturnする。
        if (!(OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnit.getOrderStatus()) ||
            OrderStatusEnum.CANCELLING.equals(l_orderUnit.getOrderStatus())))
        {
            log.debug("注文単位.注文状態≠（\"受付済（取消注文）\" or \"発注中（取消注文）\"）なので、取消処理を行わない。");
            return;
        }

        // 1.3. getDefaultProcessor()
        QueryProcessor l_queryProcessor = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(),l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(), l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(),l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(), l_dne);
        }
        
        // 1.4. 株式出来通知call取消通知TransactionCallback()
        WEB3EquityCallCancelNotifyTransactionCallback l_transactionCallBack =
            new WEB3EquityCallCancelNotifyTransactionCallback(l_orderUnit, l_equityOrderExecNotifyParams);
        
        // 1.5. doTransaction()
        try
        {
            l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_JOIN_EXISTING,
                l_transactionCallBack);
        }
        catch (DataCallbackException l_dce)
        {
            log.error(l_dce.getMessage(), l_dce);
            WEB3BaseException l_bex = (WEB3BaseException)l_dce.getDetails();
            throw l_bex;
        }
        catch (DataException l_dex)
        {
            log.error(l_dex.getMessage(),l_dex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80078,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate全部約定)<BR>
     * <BR>
     * [処理概要]<BR>
     * すでに、全部約定済みかどうかチェックする。<BR>
     * <BR>
     * （チェック内容）<BR>
     * １）　@引数の注文単位.isFullyExecuted( )をコールする。<BR>
     * <BR>
     * ２）　@１）でtrueが返却された場合は、「当該注文は既に全部約定済」の例外（業務エラー）を<BR>
     * 　@　@　@throwする。<BR>
     * 　@　@　@以外、そのままreturnする。<BR>
     * <BR>
     * ※trueが返却された場合、出来通知キュー.処理区分を9：データエラーに更新、<BR>
     * ※エラーログを出力し一件処理を終了する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * @@throws WEB3BaseException
     * @@throws WEB3BaseException
     */
    protected void validateAllOrderExecution(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAllOrderExecution(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit.isFullyExecuted() == true)
        {
            log.error("当該注文は既に全部約定済である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00634,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （validate二重約定）<BR>
     * <BR>
     * ・合計約定数量が注文株数を超えていないかチェックする。<BR>
     * <BR>
     * 　@引数の注文単位.市場から確認済みの数量　@＜　@合計約定数量(*1)　@の場合、<BR>
     * 　@「合計約定数量が、注文数量を超過」の例外（業務エラー）をthrowする。<BR>
     * 　@以外、そのままreturnする。<BR>
     * <BR>
     * (*1)<BR>
     * 合計約定数量　@＝　@注文単位.約定数量　@＋　@引数の約定株数<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * @@param l_dblExecQuantity - (約定株数)<BR>
     * @@throws WEB3BaseException
     */
    protected void validateDoubleOrderExecution(
        EqTypeOrderUnit l_orderUnit,
        double l_dblExecQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDoubleOrderExecution(EqTypeOrderUnit, double)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        double l_dblTotalQuantity = l_orderUnitRow.getExecutedQuantity() + l_dblExecQuantity;

        if (l_orderUnit.getConfirmedQuantity() < l_dblTotalQuantity)
        {
            log.error("合計約定数量が、注文数量を超過している。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00635,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate注文状態)<BR>
     * 対象注文が、約定／約定取消による更新を行って良い状態であるかチェックする。<BR>
     * <BR>
     * （チェック内容）<BR>
     * １．引数で渡された注文単位.市場から確認済の数量==nullの場合、<BR>
     * 　@　@「該当注文は受付未済／変更の受付済／発注中の状態」の例外をthrowする。<BR>
     * <BR>
     * ２．引数で渡された注文単位.注文状態が以下のいずれかに該当する場合、<BR>
     * 　@　@１．と同じ例外をthrowする。<BR>
     * 　@　@　@　@MODIFY_ACCEPTED：受付済（変更注文）<BR>
     * 　@　@　@　@MODIFYING：発注中（変更注文）<BR>
     * <BR>
     * ３．引数で渡された注文単位.値段条件 == （"現在値指値注文", "優先値指値注文"）の<BR>
     * 　@　@いずれかの場合のみ、 <BR>
     * 　@　@値段付けが完了していない場合(*1)は、１．と同じ例外をthrowする。<BR>
     * <BR>
     * 　@　@　@(*1)値段付けが完了していない場合<BR>
     * 　@　@　@　@　@注文単位.市場から確認済みの指値 == 0（＝値段未確定）　@かつ<BR>
     * 　@　@　@　@　@注文単位.注文状態≠（MODIFIED：発注済（変更注文） or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@NOT_MODIFIED：発注失敗（変更注文） or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@NOT_CANCELLED：発注失敗（取消注文））<BR>
     * <BR>
     * 　@　@　@　@　@※特別気配時には、値段確定の訂正取消通知が訂正後指値==0円で来るため、<BR>
     * 　@　@　@　@　@※注文状態とのAND条件で値段付けの完了有無を判定する。<BR>
     * <BR>
     * 以外、そのままreturnする。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@throws WEB3BaseException
     */
    protected void validateOrderStatus(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderStatus(OrderStatusEnum)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_orderUnitRow.getConfirmedQuantityIsNull())
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.MODIFYING.equals(l_orderStatus))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        String l_strPriceConditionType = l_orderUnitRow.getPriceConditionType();
        if (WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(l_strPriceConditionType) ||
            WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(l_strPriceConditionType))
        {
            if (l_orderUnitRow.getConfirmedPrice() == 0D &&
                !OrderStatusEnum.MODIFIED.equals(l_orderStatus) &&
                !OrderStatusEnum.NOT_MODIFIED.equals(l_orderStatus) &&
                !OrderStatusEnum.NOT_CANCELLED.equals(l_orderStatus))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
