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
filename	WEB3MarginOrderExecNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引出来通知一件サービスImpl(WEB3MarginExecNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 凌建平 (中訊) 新規作成
                   2006/11/28 趙林鵬 (中訊) モデル No.1040 1072
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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultFillOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;

import webbroker3.equity.WEB3EquityCancelOrderConfirmInterceptor;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3MarginOrderExecNotifyUpdateInterceptor;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3MarginOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;

/**
 * （信用取引出来通知一件サービスImpl）。<BR>
 * <BR>
 * 信用取引出来通知一件サービス実装クラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginOrderExecNotifyUnitServiceImpl implements WEB3MarginOrderExecNotifyUnitService 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOrderExecNotifyUnitServiceImpl.class);
    
    /**
     * @@roseuid 4140066E01E6
     */
    public WEB3MarginOrderExecNotifyUnitServiceImpl() 
    {
     
    }
    
    /**
     * (notify約定)<BR>
     * 約定処理を実行する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引出来通知一件サービス）notify約定」参照。<BR>
     * @@param l_ordeUnit - (注文単位)<BR>
     * 出来通知キュー.識別コードに該当する、注文単位オブジェクト。<BR>
     * @@param l_equityExecNotifyQueueParams - 
     *  株式出来通知キューParamsオブジェクト。<BR>
     * @@roseuid 40CE82CF039D
     */
    public void notifyExecute(EqTypeOrderUnit l_orderUnit, 
        HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "notifyExecute(EqTypeOrderUnit,HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME); 
        
        log.debug("シーケンス図「（信用取引出来通知一件サービス）notify約定」参照");
        //信用取引出来通知 / （信用取引出来通知一件サービス）notify約定
        
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
        
        //3 validate注文状態(OrderStatusEnum)(信用取引出来通知一件サービスImpl::validate注文状態)
        //引数は以下の通りに設定する。 
        //  注文状態：　@引数の注文単位.注文状態
        this.validateOrderStatus(l_orderUnit);

        //4 validate全部約定(注文単位)(信用取引出来通知一件サービスImpl::validate全部約定)
        this.validateAllOrderExecution(l_orderUnit);
    
        //5 validate二重約定(注文単位, double)(信用取引出来通知一件サービスImpl::validate二重約定)
        //引数は以下の通りに設定する。 
        //  注文単位：　@引数の注文単位 
        //  約定株数：　@引数の株式出来通知キューParams.約定株数
        this.validateDoubleOrderExecution(l_orderUnit,l_equityExecNotifyQueueParams.getExecQuantity());
        
        // ------------------------------------------------------------------
        // 信用出来通知更新インタセプタの生成
        // ------------------------------------------------------------------
        WEB3MarginOrderExecNotifyUpdateInterceptor l_updateInterceptor = new WEB3MarginOrderExecNotifyUpdateInterceptor(
            l_equityExecNotifyQueueParams.getExecTimestamp(),
            l_equityExecNotifyQueueParams.getExecPrice(),
            l_equityExecNotifyQueueParams.getExecQuantity());
        
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //6 DefaultFillOrderUnitSpec(long, double, double)
        //  (DefaultFillOrderUnitSpec::DefaultFillOrderUnitSpec)
        //引数は以下の通りに設定する。 
        //  注文単位ID：　@引数の注文単位.注文単位ID 
        //  約定株数：　@引数の株式出来通知キューParams.約定株数 
        //  約定単価：　@引数の株式出来通知キューParams.約定単価
        DefaultFillOrderUnitSpec l_unitSpec = new DefaultFillOrderUnitSpec(
        l_orderUnit.getOrderUnitId(),
            l_equityExecNotifyQueueParams.getExecQuantity(),
            l_equityExecNotifyQueueParams.getExecPrice());
        
        //7 DefaultOrderFillMarketResponseMessage(long, FillOrderUnitSpec)
        //  (DefaultOrderFillMarketResponseMessage::DefaultOrderFillMarketResponseMessage)
        //　@引数は以下の通りに設定する。 
        //  注文ID：　@引数の注文単位.注文ID 
        //  FillOrderUnitSpec：　@生成したDefaultFillOrderUnitSpecオブジェクト
        DefaultOrderFillMarketResponseMessage l_responseMessage = 
            new DefaultOrderFillMarketResponseMessage(
                l_orderUnit.getOrderId(),
                l_unitSpec);
       
        //8 process(MarketResponseMessage)(EqTypeMarketResponseReceiverCallbackService::process)
        //　@引数は以下の通りに設定する。 
        //  OrderFillMarketResponseMessage：　@ 
        //  生成したDefaultOrderFillMarketResponseMessageオブジェクト
        EqTypeMarketResponseReceiverCallbackService l_callbackService = 
            (EqTypeMarketResponseReceiverCallbackService)l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY).getMarketAdapter().getMarketResponseReceiverCallbackService();
        log.debug("********** process(DefaultOrderFillMarketResponseMessage) START **********");
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
        log.debug("********** process(DefaultOrderFillMarketResponseMessage) EXIT **********");
        
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_finApp.getTradingModule(
                ProductTypeEnum.EQUITY).getOrderManager().getOrderUnit(
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

        boolean l_blnFullyExecuted = l_orderUnit.isFullyExecuted();

        //21 isFullyExecuted( )==trueの場合のみ
        if (l_blnFullyExecuted == true)
        {
            //22 sendMailProcess(注文単位, String)(約定メール送信サービスImpl::sendMailProcess)
            WEB3EquityExecutedMailSenderService l_mailSenderService = 
                (WEB3EquityExecutedMailSenderService)Services.getService(
                WEB3EquityExecutedMailSenderService.class);
            //l_mailSenderService.sendMailProcess(l_ordeUnit,l_equityExecNotifyQueueParams.request_code);            
            l_mailSenderService.sendMailProcess(l_orderUnit, null);

            //notifyルールエンジンサーバ(EqTypeOrderUnit, OrderManagerPersistenceContext)
            try
            {
                l_orderManager.notifyRLS(
                    l_orderUnit,
                    OrderManagerPersistenceContext.FILL_ORDER);
            }
            //notifyルールエンジンサーバ()にて業務エラーがスローされた場合
            //catchして処理を継続する。（※ロールバックしない）
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合, ロールバックしない");
            } 
        }        
        
        // 1.11. isPartiallyExecuted()
        if (l_orderUnit.isPartiallyExecuted())
        {
            // 1.12. 一部約定かつ約定対象注文単位.値段条件＝"成行残数取消"の場合のみ、注文失効処理を実行。
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            if (WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
            {
                // 1.12.1. 株式失効通知キューParamsのインスタンス生成、及びプロパティセット
                HostEqtypeCloseOrderNotifyParams l_hostCloseNotifyParams = new HostEqtypeCloseOrderNotifyParams();
                l_hostCloseNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
                l_hostCloseNotifyParams.setInstitutionCode(l_equityExecNotifyQueueParams.getInstitutionCode());
                l_hostCloseNotifyParams.setBranchCode(l_equityExecNotifyQueueParams.getBranchCode());
                AccountManager l_accountManager = l_finApp.getAccountManager();
                MainAccount l_mainAccount = null;
                try
                {
                    l_mainAccount = 
                        l_accountManager.getMainAccount(l_orderUnit.getAccountId());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(STR_METHOD_NAME, l_nfe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(), l_nfe);
                }
                l_hostCloseNotifyParams.setAccountCode(l_mainAccount.getAccountCode());
                l_hostCloseNotifyParams.setTraderCode(l_equityExecNotifyQueueParams.getTraderCode());
                l_hostCloseNotifyParams.setOrderRequestNumber(l_equityExecNotifyQueueParams.getOrderRequestNumber());
                l_hostCloseNotifyParams.setExecutedQuantity(l_orderUnitRow.getExecutedQuantity());
                l_hostCloseNotifyParams.setReasonCode(WEB3ExpirationReasonCodeDef.PARTIALLY_EXECUTE_CLOSE);
                l_hostCloseNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
                l_hostCloseNotifyParams.setErrorMessage(null);
                l_hostCloseNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

                // 1.12.2. exec失効()
                WEB3EquityReceiveCloseOrderUnitService l_unitService =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(
                        WEB3EquityReceiveCloseOrderUnitService.class);
                l_unitService.execCloseOrder(l_hostCloseNotifyParams, l_orderUnit);
            }
        }
        
        // 1.13 call失効通知処理()        
        this.callExpirationNotify(l_orderUnit, l_equityExecNotifyQueueParams);         
        
        // 1.14 call取消通知処理()
        this.callCancelNotify(l_orderUnit, l_equityExecNotifyQueueParams);
        
        // 1.15 余力再計算()
        try
        {        
            AccountManager l_accountManager = l_finApp.getAccountManager();
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = 
                    l_accountManager.getMainAccount(l_orderUnit.getAccountId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }
            //getSubAccount
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    
            log.debug("余力再計算を行う");
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify約定取消)<BR>
     * 約定取消処理を実行する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引出来通知一件サービス）notify約定取消」参照。<BR>
     * @@param l_ordeUnit - (注文単位)<BR>
     * 出来通知キュー.識別コードに該当する、注文単位オブジェクト。<BR>
     * @@param l_equityExecNotifyQueueParams - <BR>
     *   株式出来通知キューParamsオブジェクト。<BR>
     * @@roseuid 40CE82EE03DC
     */
    public void notifyExecuteCancel(EqTypeOrderUnit l_orderUnit, 
        HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "notifyExecuteCancel(EqTypeOrderUnit,HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME); 
        
        log.debug("シーケンス図「（信用取引出来通知一件サービス）notify約定取消」参照");
        //信用取引出来通知 / （信用取引出来通知一件サービス）notify約定取消
        
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
        
        //1 validate注文状態()
        validateOrderStatus(l_orderUnit);
        
        //2 get取消対象約定(注文単位, double, double)(拡張株式注文マネージャ::get取消対象約定)
        //引数は以下の通りに設定する。 
        //  注文単位：　@引数の注文単位 
        //  約定株数：　@引数の株式出来通知キューParams.約定株数 
        //  約定単価：　@引数の株式出来通知キューParams.約定単価
        log.debug("================== 取消対象約定 開始 ===================");
        log.debug("注文単位:" + l_orderUnit.getOrderUnitId());   
        log.debug("約定株数:" + l_equityExecNotifyQueueParams.getExecQuantity());
        log.debug("約定単価:" + l_equityExecNotifyQueueParams.getExecPrice());
        log.debug("================== 取消対象約定 終了 ===================");

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
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        // 1.10. 一部約定に対する約定取消かつ約定取消対象注文単位.値段条件=="成行残数取消"
        // 　@　@　@の場合のみ、失効取消処理を実行。
        if (l_orderExecution.getExecutionQuantity() < l_orderUnit.getQuantity())
        {
            if (WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
            {
                // 1.10.1. 株式失効通知キューParamsのインスタンス生成、及びプロパティセット
                HostEqtypeCloseOrderNotifyParams l_hostCloseNotifyParams = new HostEqtypeCloseOrderNotifyParams();
                l_hostCloseNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
                l_hostCloseNotifyParams.setInstitutionCode(l_equityExecNotifyQueueParams.getInstitutionCode());
                l_hostCloseNotifyParams.setBranchCode(l_equityExecNotifyQueueParams.getBranchCode());
                l_hostCloseNotifyParams.setAccountCode(l_equityExecNotifyQueueParams.getAccountCode());
                l_hostCloseNotifyParams.setTraderCode(l_equityExecNotifyQueueParams.getTraderCode());
                l_hostCloseNotifyParams.setOrderRequestNumber(l_equityExecNotifyQueueParams.getOrderRequestNumber());
                l_hostCloseNotifyParams.setExecutedQuantity(l_orderUnitRow.getExecutedQuantity());
                l_hostCloseNotifyParams.setReasonCode(WEB3ExpirationReasonCodeDef.PARTIALLY_EXECUTE_CLOSE);
                l_hostCloseNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE_CANCEL);
                l_hostCloseNotifyParams.setErrorMessage(null);
                l_hostCloseNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

                // 1.10.2. exec失効()
                WEB3EquityReceiveCloseOrderUnitService l_unitService =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(
                        WEB3EquityReceiveCloseOrderUnitService.class);
                l_unitService.execCloseOrder(l_hostCloseNotifyParams, l_orderUnit);
            }
        }
        
        // 信用取引出来通知更新インタセプタの生成
        WEB3MarginOrderExecNotifyUpdateInterceptor l_updateInterceptor = new WEB3MarginOrderExecNotifyUpdateInterceptor(
            l_equityExecNotifyQueueParams.getExecTimestamp(),
            l_equityExecNotifyQueueParams.getExecPrice(),
            l_equityExecNotifyQueueParams.getExecQuantity());
            
        // 信用取引出来通知更新インタセプタをセット。
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);

        //3 getOrderId()
        long l_lngOrderId = l_orderUnit.getOrderId();
        
        //4  DefaultUndoOrderFillMarketResponseMessage(long, long)
        //  (DefaultUndoOrderFillMarketResponseMessage::DefaultUndoOrderFillMarketResponseMessage)
        //引数は以下の通りに設定する。 
        //  注文ID：　@getOrderId( )の戻り値　@ 
        //  約定ID：get取消対象約定( )の戻り値の約定オブジェクト.getOrderExecutionId( )
        DefaultUndoOrderFillMarketResponseMessage l_responseMessage = 
            new DefaultUndoOrderFillMarketResponseMessage(
                l_lngOrderId,
                l_orderExecution.getOrderExecutionId());
            
        //5 process(MarketResponseMessage)(EqTypeMarketResponseReceiverCallbackService::process)
        //引数は以下の通りに設定する。 
        //  UndoOrderFillMarketResponseMessage：　@ 
        //  生成したDefaultUndoOrderFillMarketResponseMessageオブジェクト
        EqTypeMarketResponseReceiverCallbackService l_callbackService = 
            (EqTypeMarketResponseReceiverCallbackService)l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY).getMarketAdapter().getMarketResponseReceiverCallbackService();
        log.debug("********** process(DefaultUndoOrderFillMarketResponseMessage) START **********");
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
        log.debug("********** process(DefaultUndoOrderFillMarketResponseMessage) EXIT **********");
        
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_finApp.getTradingModule(
                ProductTypeEnum.EQUITY).getOrderManager().getOrderUnit(
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
        
        //15 update既約定データ(EqTypeOrderExecution)(株式ポジションマネージャ::update既約定データ)
        WEB3EquityPositionManager l_positionManager = 
            (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
        l_positionManager.updateExecutedData(l_orderExecution);
        
        //17 undoSendMail(EqTypeOrderUnit)(約定メール送信サービス::undoSendMail)
        //引数は以下の通りにセットする。 
        //  注文単位：　@引数の注文単位 
        WEB3EquityExecutedMailSenderService l_mailSenderService = 
            (WEB3EquityExecutedMailSenderService)Services.getService(
            WEB3EquityExecutedMailSenderService.class);
        l_mailSenderService.undoSendMail(l_orderUnit);


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
                l_nfe.getMessage(), l_nfe);
        }

        //18 exec現引現渡注文取消(注文単位)(信用取引出来通知一件サービスImpl::exec現引現渡注文取消)
        //引数は以下の通りにセットする。 
        //  注文単位：　@引数の注文単位 
        log.debug("18 exec現引現渡注文取消()を調用●開始");
        this.execSwapMarginOrderCancel(l_orderUnit);
        log.debug("18 exec現引現渡注文取消()を調用●終了");
        
        // 1.12. 余力再計算()
        try
        {        
            AccountManager l_accountManager = l_finApp.getAccountManager();
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = 
                    l_accountManager.getMainAccount(l_orderUnit.getAccountId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }
            //getSubAccount
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    
            log.debug("余力再計算を行う");
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (call失効通知処理)<BR>
     * 出来待ちの失効通知が存在するかどうかを、注文単位のプロパティより判定し、 <BR>
     * 失効処理が必要な場合は「注文失効一件サービス」をコールする。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（（株式出来通知共通）出来通知一件サービス）call失効通知処理」参照。 <BR>
     * @@param l_ordeUnit - (注文単位)<BR>
     * 出来通知キュー.識別コードに該当する、注文単位オブジェクト。<BR>
     * @@param l_equityOrderExecNotifyParams - <BR>
     *   株式出来通知キューParamsオブジェクト。<BR>
     * @@roseuid 40D0DDD002C3
     */
    protected void callExpirationNotify(EqTypeOrderUnit l_orderUnit, 
        HostEquityOrderExecNotifyParams l_equityOrderExecNotifyParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "callExpirationNotify(EqTypeOrderUnit,HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME); 

        log.debug("シーケンス図「（信用取引出来通知一件サービス）call失効通知処理」参照");

        // 1.2 （分岐フロー：注文単位．失効区分≠”失効中”の場合、何もせずにreturnする）
        if (!(l_orderUnit.getExpirationStatus().equals(OrderExpirationStatusEnum.EXPIRING)))
        {
            return;
        }

        //5 getDefaultProcessor( )(Processors::getDefaultProcessor)
        QueryProcessor l_queryProcessor = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(),l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_dfe.getMessage(), l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(),l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_dne.getMessage(), l_dne);
        }
        
        //6 信用取引call失効通知TransactionCallback( )
        //  (信用取引call失効通知TransactionCallback::信用取引call失効通知TransactionCallback)
        WEB3EquityCallCloseNotifyTransactionCallback l_transactionCallBack =
            new WEB3EquityCallCloseNotifyTransactionCallback(l_orderUnit, l_equityOrderExecNotifyParams);
        
        //7 doTransaction(int, TransactionCallback)(QueryProcessor::doTransaction)
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
     * 注文が取消中かどうかをチェックし、 <BR>
     * 取消通知処理が必要な場合は「信用取引訂正取消通知取消一件サービス」をコールする。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（（株式出来通知共通）出来通知一件サービス）call取消通知処理」参照。 <BR>
     * @@param l_ordeUnit - (注文単位)<BR>
     * 出来通知キュー.識別コードに該当する、注文単位オブジェクト。<BR>
     * @@param l_equityOrderExecNotifyParams - <BR>
     * 株式出来通知キューParamsオブジェクト。<BR>
     */
    protected void callCancelNotify(EqTypeOrderUnit l_orderUnit, 
        HostEquityOrderExecNotifyParams l_equityOrderExecNotifyParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "callCancelNotify(EqTypeOrderUnit,HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME); 

        log.debug("「（信用取引出来通知一件サービス）call取消通知処理」参照");
        //信用取引出来通知 / （信用取引出来通知一件サービス）call取消通知処理        

        // 1.2. 注文単位.注文状態≠（"受付済（取消注文）" or "発注中（取消注文）"） == 取消中でない）の場合、何もせずreturnする。
        if (!(OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnit.getOrderStatus()) ||
            OrderStatusEnum.CANCELLING.equals(l_orderUnit.getOrderStatus())))
        {
            log.debug("注文単位.注文状態≠（\"受付済（取消注文）\" or \"発注中（取消注文）\"）なので、取消処理を行わない。");
            return;
        }
        
        // 取消中の場合のみ以下の処理を行う。        
        //1.3 getDefaultProcessor( )(Processors::getDefaultProcessor)
        QueryProcessor l_QueryProcessor = null;
        try
        {
            l_QueryProcessor = Processors.getDefaultProcessor();
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
        
        //6 株式出来通知call取消通知TransactionCallback( )
        //  (株式出来通知call取消通知TransactionCallback::株式出来通知call取消通知TransactionCallback)
        WEB3EquityCallCancelNotifyTransactionCallback l_transactionCallBack = 
            new WEB3EquityCallCancelNotifyTransactionCallback(l_orderUnit, l_equityOrderExecNotifyParams);
        
        //7 doTransaction(int, TransactionCallback)(QueryProcessor::doTransaction)
        try
        {
            log.debug("株式出来通知call取消通知TransactionCallback()を開始");
            l_QueryProcessor.doTransaction(
                TransactionalInterceptor.TX_JOIN_EXISTING,
                l_transactionCallBack);
            log.debug("株式出来通知call取消通知TransactionCallback()を終了");
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
     * すでに、全部約定済みかどうかチェックする。<BR>
     * <BR>
     * １）　@引数の注文単位.isFullyExecuted( )をコールする。<BR>
     * <BR>
     * ２）　@１）でtrueが返却された場合は、<BR>
     *     「当該注文は既に全部約定済」の例外（業務エラー）をthrowする。以外、<BR>
     *      そのままreturnする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00634<BR>
     * @@param l_ordeUnit - (注文単位)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40DFC5E600B9
     */
    protected void validateAllOrderExecution(EqTypeOrderUnit l_ordeUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAllOrderExecution(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        //１）　@引数の注文単位.isFullyExecuted( )をコールする。
        boolean l_blnFullyExecuted = l_ordeUnit.isFullyExecuted();
        
        //２）　@１）でtrueが返却された場合は、<BR>
        //     「当該注文は既に全部約定済」の例外（業務エラー）をthrowする。以外、<BR>
        //      そのままreturnする。<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag:   BUSINESS_ERROR_00634<BR>
        if (l_blnFullyExecuted == true)
        {
            log.error("「当該注文は既に全部約定済」の例外（業務エラー）をthrowする");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00634,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate二重約定)<BR>
     * ・合計約定数量が注文株数を超えていないかチェックする。<BR>
     * <BR>
     * 　@引数の注文単位.市場から確認済みの数量　@＜　@合計約定数量(*1)　@の場合、<BR>
     * 　@「合計約定数量が、注文数量を超過」の例外（業務エラー）をthrowする。<BR>
     * 　@以外、そのままreturnする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00635<BR>
     * <BR>
     * (*1)<BR>
     * 合計約定数量　@＝　@注文単位.約定数量　@＋　@引数の約定株数<BR>
     * <BR>
     * @@param l_ordeUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@param l_dblExecQuantity - (約定株数)<BR>
     * 約定した株数。<BR>
     * @@roseuid 40DFC5E600CA<BR>
     */
    protected void validateDoubleOrderExecution(EqTypeOrderUnit l_ordeUnit, 
        double l_dblExecQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDoubleOrderExecution(EqTypeOrderUnit,double)";
        log.entering(STR_METHOD_NAME);
        
        //(*1) 合計約定数量　@＝　@注文単位.約定数量　@＋　@引数の約定株数
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_ordeUnit.getDataSourceObject();
        double l_dblTotalQuantity = l_orderUnitRow.getExecutedQuantity() + l_dblExecQuantity;
        
        // 引数の注文単位.市場から確認済みの数量　@＜　@合計約定数量(*1)　@の場合、<BR>
        // 「合計約定数量が、注文数量を超過」の例外（業務エラー）をthrowする。<BR>
        // 以外、そのままreturnする。<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00635<BR> 
        if (l_ordeUnit.getConfirmedQuantity() < l_dblTotalQuantity)
        {
            log.error("「合計約定数量が、注文数量を超過」の例外（業務エラー）をthrowする");
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
    
    /**
     * (exec現引現渡注文取消)<BR>
     * 現引現渡注文の取消を行う必要があるかどうかを判定し、<BR>
     * 必要時には現引現渡注文の取消を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引出来通知一件サービス）exec現引現渡注文取消」参照。<BR>
     * <BR>
     * @@param l_ordeUnit - (注文単位)<BR>
     * 出来通知キュー.識別コードに該当する、注文単位オブジェクト。<BR>
     * @@roseuid 40F2405003A8
     */
    protected void execSwapMarginOrderCancel(EqTypeOrderUnit l_ordeUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execSwapMarginOrderCancel(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        log.debug("シーケンス図「（信用取引出来通知一件サービス）exec現引現渡注文取消」参照");
        //信用取引出来通知 / （信用取引出来通知一件サービス）exec現引現渡注文取消
        //2 getOrderCateg()
        OrderCategEnum l_orderCateg = l_ordeUnit.getOrderCateg();
        
        //3 （分岐フロー：現引現渡注文以外（≠OrderCategEnum.SWAP_MARGIN）の場合は何もせずreturnする。）
        if (!OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
        {
            log.debug("現引現渡注文以外の場合は何もせずreturnする!");
            return;
        }
        
        //4 株式注文取消確定インタセプタ( )(株式注文取消確定インタセプタ::株式注文取消確定インタセプタ)
        WEB3EquityCancelOrderConfirmInterceptor l_confirmInterceptor = new WEB3EquityCancelOrderConfirmInterceptor();
        
        //5 setThreadLocalPersistenceEventInterceptor(
        //  株式注文取消確定インタセプタ : EqTypeOrderManagerPersistenceEventInterceptor)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY).getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_confirmInterceptor);
        
        //6 DefaultCancelOrderAcceptedMarketResponseMessage(long)
        //  (DefaultCancelOrderAcceptedMarketResponseMessage::DefaultCancelOrderAcceptedMarketResponseMessage)
        //引数は以下の通りに設定する。 
        //  注文ID：　@引数の注文単位.注文ID
        DefaultCancelOrderAcceptedMarketResponseMessage l_responseMessage = 
            new DefaultCancelOrderAcceptedMarketResponseMessage(l_ordeUnit.getOrderId());
        
        //7 process(CancelOrderAcceptedMarketResponseMessage)(EqTypeMarketResponseReceiverCallbackService::process)
        //引数は以下の通りに設定する。 
        //  arg0：　@生成したDefaultCancelOrderAcceptedMarketResponseMessage
        EqTypeMarketResponseReceiverCallbackService l_callbackService = 
            (EqTypeMarketResponseReceiverCallbackService)l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY).getMarketAdapter().getMarketResponseReceiverCallbackService();
        log.debug("◎◎◎◎◎◎ exec現引現渡注文取消にprocess() 開始◎◎◎◎◎◎");
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
        log.debug("◎◎◎◎◎◎ exec現引現渡注文取消にprocess() 終了◎◎◎◎◎◎");
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
