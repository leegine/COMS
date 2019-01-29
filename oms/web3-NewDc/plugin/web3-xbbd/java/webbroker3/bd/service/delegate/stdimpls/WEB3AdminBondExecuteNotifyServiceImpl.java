head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券約定通知サービスImpl(WEB3AdminBondExecuteNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 徐大方(中訊) 新規作成         
*/

package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultFillOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;

import webbroker3.bd.WEB3AdminBondDefaultInterceptor;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondFinTransactionManager;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券約定通知サービスImpl)<BR>
 * 債券約定通知サービスImplクラス
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3AdminBondExecuteNotifyServiceImpl implements WEB3AdminBondExecuteNotifyService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteNotifyServiceImpl.class);
    
    /**
     * @@roseuid 44E3362F0242
     */
    public WEB3AdminBondExecuteNotifyServiceImpl() 
    {
     
    }
    
    /**
     * (notify約定)<BR>
     * notify約定<BR>
     * <BR>
     * <BR>
     * シーケンス図「notify約定」参照 <BR>
     * ------------------------------------------------<BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位
     * @@param l_adminBondDefaultInterceptor - (債券管理者デフォルトインタセプタ)<BR>
     * 債券管理者デフォルトインタセプタ
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777025E
     */
    public void notifyExecute(BondOrderUnit l_bondOrderUnit, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "notifyExecute(BondOrderUnit, WEB3AdminBondDefaultInterceptor)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null || l_adminBondDefaultInterceptor == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //1.1 setThreadLocalPersistenceEventInterceptor(arg0 : BondOrderManagerPersistenceEventInterceptor)
        //インタセプタをセットする 
        //引数：債券管理者デフォルトインタセプタ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager ) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(l_adminBondDefaultInterceptor);
        
        //1.2 get債券受渡代金計算結果()
        //債券受渡代金計算結果を取得 
        WEB3BondEstimatedPriceCalcResult l_calcResult = 
            l_adminBondDefaultInterceptor.getBondEstimatedPriceCalcResult();
        
        //1.3 get単価()
        //単価を取得
        double l_dblPrice = 0D;
        if (l_calcResult != null)
        {
            l_dblPrice = l_calcResult.getPrice().doubleValue();
        }
        
        double l_dblQuantity = 0D;
        //1.4 get数量()
        //数量を取得
        if (l_calcResult != null)
        {
            l_dblQuantity = l_calcResult.getQuantity().doubleValue();
        }
        
        //1.5 DefaultFillOrderUnitSpec(arg0 : long, arg1 : double, arg2 : double)
        //債券注文約定内容を生成 
        //[DefaultFillOrderUnitSpec()の引数] 
        //注文単位ID：拡張債券注文単位.get注文単位ID 
        //約定数量：get単価 
        //約定単価：get数量 
        DefaultFillOrderUnitSpec l_defaultFillOrderUnitSpec =
            new DefaultFillOrderUnitSpec(
                l_bondOrderUnit.getOrderUnitId(),
                l_dblQuantity,
                l_dblPrice);
        
        //1.6 DefaultOrderFillMarketResponseMessage(arg0 : long, arg1 : FillOrderUnitSpec)
        //債券約定メッセージを生成 
        //[DefaultOrderFillMarketResponseMessage()の引数] 
        //注文ID：拡張債券注文単位.get注文ID 
        //FillOrderUnitSpec：DefaultFillOrderUnitSpec
        DefaultOrderFillMarketResponseMessage l_defaultOrderFillMarketResponseMessage =
            new DefaultOrderFillMarketResponseMessage(
                l_bondOrderUnit.getOrderId(),
                l_defaultFillOrderUnitSpec);
        
        //1.7 process(arg0 : OrderFillMarketResponseMessage)
        //約定処理を実行 
        //[process()の引数] 
        //DefaultOrderFillMarketResponseMessage
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        BondMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (BondMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
        ProcessingResult l_processingResult = 
            l_marketResponseReceiverCallbackService.process(
                l_defaultOrderFillMarketResponseMessage);     
       log.debug("l_processingResult.isSuccessfulResult() = " + l_processingResult.isSuccessfulResult());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (undo約定)<BR>
     * undo約定<BR>
     * <BR>
     * <BR>
     * シーケンス図「undo約定」参照 <BR>
     * ------------------------------------------------<BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位
     * @@param l_adminBondDefaultInterceptor - (債券管理者デフォルトインタセプタ)<BR>
     * 債券管理者デフォルトインタセプタ
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777026E
     */
    public void undoExecute(BondOrderUnit l_bondOrderUnit, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "undoExecute(BondOrderUnit, WEB3AdminBondDefaultInterceptor)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //1.1 getExecutions()
        //債券注文単位より債券注文約定配列を取得
        OrderExecution[] l_orderExcution = l_bondOrderUnit.getExecutions();
        
        //1.2 約定件数分ループ
        int l_intLength = 0;
        if (l_orderExcution != null)
        {
            l_intLength = l_orderExcution.length;
        }
        
        for (int i = 0;i < l_intLength; i++)
        {
            //1.2.1 setThreadLocalPersistenceEventInterceptor(arg0 : BondOrderManagerPersistenceEventInterceptor) 
            //インタセプタをセットする 
            //引数：債券管理者デフォルトインタセプタ
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3BondOrderManager l_bondOrderManager = 
                (WEB3BondOrderManager ) l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getOrderManager(); 
            l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(l_adminBondDefaultInterceptor);
            
            //1.2.2 DefaultUndoOrderFillMarketResponseMessage(arg0 : long, arg1 : long)
            //債券約定取消応答メッセージを生成 
            //[DefaultUndoOrderFillMarketResponseMessage()の引数] 
            //注文ID：引数.債券注文単位.get注文ID() 
            //注文約定ID：ループ中の債券約定.getOrderExecutionId()
            DefaultUndoOrderFillMarketResponseMessage l_undoOrderFillMarketResponseMessage =
                new DefaultUndoOrderFillMarketResponseMessage(
                    l_bondOrderUnit.getOrderId(),
                    l_orderExcution[i].getOrderExecutionId());
           
            //1.2.3 process(arg0 : UndoOrderFillMarketResponseMessage)
            //債券約定取消を実行。 
            //[process()の引数] 
            //DefaultUndoOrderFillMarketResponseMessage
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
            MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
            BondMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                (BondMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
            ProcessingResult l_processingResult = 
                l_marketResponseReceiverCallbackService.process(
                    l_undoOrderFillMarketResponseMessage);     
           log.debug("l_processingResult.isSuccessfulResult() = " + l_processingResult.isSuccessfulResult());
           
           //1.3 undo確定トランザクションBy注文単位ID(long)
           WEB3BondFinTransactionManager l_bondFinTransactionManager = 
               (WEB3BondFinTransactionManager) l_tradingMod.getFinTransactionManager();
           l_bondFinTransactionManager.undoTransactionByOrderUnitId(l_bondOrderUnit.getOrderUnitId());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (accept新規注文)<BR>
     * accept新規注文を実行<BR>
     * <BR>
     * <BR>
     * シーケンス図「accept新規注文」参照 <BR>
     * ------------------------------------------------<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID
     * @@param l_adminBondDefaultInterceptor - (債券管理者デフォルトインタセプタ)<BR>
     * 債券管理者デフォルトインタセプタ
     * @@throws WEB3BaseException
     * @@roseuid 44D852EA01B7
     */
    public void acceptNewOrder(long l_lngOrderId, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException
    {  
        final String STR_METHOD_NAME = "acceptNewOrder(long, WEB3AdminBondDefaultInterceptor)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 setThreadLocalPersistenceEventInterceptor(arg0 : BondOrderManagerPersistenceEventInterceptor)
        //インタセプタをセットする 
        //引数：債券管理者デフォルトインタセプタ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager ) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(l_adminBondDefaultInterceptor);
        
        //1.2 DefaultNewOrderAcceptedMarketResponseMessage(arg0 : long)
        //新規注文受付メッセージを生成 
        //[DefaultNewOrderAcceptedMarketResponseMessage()の引数] 
        //注文ID：引数.注文ID() 
        DefaultNewOrderAcceptedMarketResponseMessage l_newAcceptedResponseMessage =
            new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
        
        //1.3 process(arg0 : NewOrderAcceptedMarketResponseMessage)
        //新規注文受付処理を実行 
        //[processの引数] 
        //DefaultNewOrderAcceptedMarketResponseMessage
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        BondMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (BondMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService(); 
        ProcessingResult l_processingResult = 
            l_marketResponseReceiverCallbackService.process(
                l_newAcceptedResponseMessage);     
       log.debug("l_processingResult.isSuccessfulResult() = " + l_processingResult.isSuccessfulResult());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (accept注文取消)<BR>
     * accept注文取消処理を実行<BR>
     * <BR>
     * <BR>
     * シーケンス図「accept注文取消」参照 <BR>
     * ------------------------------------------------<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID
     * @@param l_adminBondDefaultInterceptor - (債券管理者デフォルトインタセプタ)<BR>
     * 債券管理者デフォルトインタセプタ
     * @@throws WEB3BaseException
     * @@roseuid 44D94E1F00CC
     */
    public void acceptOrderCancel(long l_lngOrderId,
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "acceptOrderCancel(long, WEB3AdminBondDefaultInterceptor)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 setThreadLocalPersistenceEventInterceptor(arg0 : BondOrderManagerPersistenceEventInterceptor)
        //インタセプタをセットする 
        //引数：債券管理者デフォルトインタセプタ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager ) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(l_adminBondDefaultInterceptor);
        
        //1.2 DefaultCancelOrderAcceptedMarketResponseMessage(arg0 : long)
        //債券取消注文受付メッセージを生成 
        //[DefaultCancelOrderAcceptedMarketResponseMessage()の引数] 
        //注文ID：引数.注文ID
        DefaultCancelOrderAcceptedMarketResponseMessage l_cancelResponseMessage =
            new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);

        //1.3 process(arg0 : CancelOrderAcceptedMarketResponseMessage)
        //注文取消受付処理を実行 
        //[processの引数] 
        //DefaultCancelOrderAcceptedMarketResponseMessage
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        BondMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (BondMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService(); 
        ProcessingResult l_processingResult = 
            l_marketResponseReceiverCallbackService.process(
                l_cancelResponseMessage);     
       log.debug("l_processingResult.isSuccessfulResult() = " + l_processingResult.isSuccessfulResult());
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
