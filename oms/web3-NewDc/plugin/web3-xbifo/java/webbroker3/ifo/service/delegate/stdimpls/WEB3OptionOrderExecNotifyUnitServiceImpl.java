head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション出来通知１件サービス実装(WEB3OptionOrderExecNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 鄒鋭 (中訊) 新規作成
Revesion History : 2004/07/22 王暁傑 (中訊) 時価の関連内容をコメント
Revesion History : 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
Revesion History : 2004/08/14 王暁傑 対応 【株価指数オプション】ソースコードチェック指摘事項(JP)20040802 
Revesion History : 2004/07/28 柴雙紅 (中訊)　@ 仕様変更　@モデル464
Revesion History : 2006/11/29 周捷(中訊) 仕様変更モデルNo.579
Revesion History : 2007/04/25 崔遠鵬 (中訊) 仕様変更モデルNo.634
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultFillOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;

import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecNotifyUnitService;
import webbroker3.ifo.WEB3IfoExecuteNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoPositionUpdateInterceptor;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;

/**
 * (OP出来通知UnitServiceImpl)<BR>
 * <BR>
 * 株価指数オプション出来通知１件サービス実装クラス<BR>
 * <BR>
 * １件ごとの出来通知処理を実施する。<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)を指定する。<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionOrderExecNotifyUnitServiceImpl implements WEB3OptionOrderExecNotifyUnitService
{

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOrderExecNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40C0752F033C
     */
    public WEB3OptionOrderExecNotifyUnitServiceImpl()
    {

    }

    /**
     * (notify約定)<BR>
     * <BR>
     * 約定処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP出来通知）notify約定」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_tsExecDate - 約定日時
     * @@param l_dblExecQuantity - 約定数量
     * @@param l_dblExecPrice - 約定単価
     * @@param l_strExecutedNotifyDivision - 出来通知区分
     * @@roseuid 40876047033B
     */
    public void notifyExecute(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice, String l_strExecutedNotifyDivision) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyExecute(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice, String l_strExecutedNotifyDivision";
        log.entering(STR_METHOD_NAME);

        if ((l_orderUnit == null) || (l_tsExecDate == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        //注文単位を再取得する。（口座ロック競合した場合を考慮）
        OrderUnit l_reOrderUnit = l_orderUnit;
        try
        {
            l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch(NotFoundException l_nfe)
        {
            log.error("DBへのアクセスに失敗しました。", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        //1.1 注文単位ＩＤを取得する
        long l_lngOrderUnitID = l_reOrderUnit.getOrderUnitId();

        //1.2 validate注文状態(OrderUnit)
        this.validateOrderStatus(l_reOrderUnit);

        //1.2 個別約定Specオブジェクトを生成する
        DefaultFillOrderUnitSpec l_defaultFillOrderUnitSpec = new DefaultFillOrderUnitSpec(l_lngOrderUnitID, l_dblExecQuantity, l_dblExecPrice);
                
        //1.3 注文ＩＤを取得する
        long l_lngOrderID = l_reOrderUnit.getOrderId();

        //1.4 約定内容オブジェクトを生成する        
        DefaultOrderFillMarketResponseMessage l_defaultOrderFillMarketResponseMessage = new DefaultOrderFillMarketResponseMessage(l_lngOrderID, l_defaultFillOrderUnitSpec);
        
        //1.5 先物OP出来通知更新インタセプタ()
        WEB3IfoExecuteNotifyUpdateInterceptor l_interceptor = new WEB3IfoExecuteNotifyUpdateInterceptor();

        //キュー.出来通知区分をインタセプタに設定。
        l_interceptor.setDealedType(l_strExecutedNotifyDivision);
        
        //キュー.約定日時をインタセプタに設定。
        l_interceptor.setExecTimestamp(l_tsExecDate);

        //1.6 setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
        //  [引数] 
        //  arg0： 生成した先物OP出来通知更新インタセプタ
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);  
        
        //setThreadLocalPersistenceEventInterceptor(arg0 : IfoPositionManagerPersistenceEventInterceptor)
        //  [引数] 
        //  arg0： 生成した先物OPポジション更新インタセプタ
        WEB3IfoPositionUpdateInterceptor l_pInterceptor = new WEB3IfoPositionUpdateInterceptor();
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
        l_positionManager.setThreadLocalPersistenceEventInterceptor(l_pInterceptor);  

        //1.7 process(MarketResponseMessage)(IfoMarketResponseReceiverCallbackServiceImpl::process)
        //  [process()に指定する引数] 
        //  約定内容：　@（生成した約定内容オブジェクト） 
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        IfoMarketResponseReceiverCallbackService l_marketResponseReceiveCallbackService = 
            (IfoMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();
        ProcessingResult l_processingResult = l_marketResponseReceiveCallbackService.process(
            l_defaultOrderFillMarketResponseMessage);
        if (l_processingResult.isFailedResult())
        {
            log.error(l_processingResult.getErrorInfo().getErrorMessage());
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                "出来通知の内容で、DBを更新エラー");
        }

        //1.8 概算受渡代金の更新を行う。
        //  [引数の設定]
        //  注文単位：　@注文単位
        try
        {
            l_reOrderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitID);                    
            l_orderManager.updateEstimateDeliveryAmount(l_reOrderUnit);
        }
        catch(NotFoundException l_nfe)
        {
            log.error("DBへのアクセスに失敗しました。", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }

        //1.9 get補助口座()(拡張アカウントマネージャ::get補助口座)
        //  [引数] 
        //  口座ID： 注文単位.口座ID 
        //  補助口座ID： 注文単位.補助口座ID 
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(
            l_reOrderUnit.getAccountId(),
            l_reOrderUnit.getSubAccountId());                
        }
        catch(NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }

        //1.10 余力再計算(補助口座 : 補助口座)
        //  [引数] 
        //  補助口座： get補助口座()の戻り値 
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        }        

        try
        {
            l_reOrderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitID);
        }
        catch(NotFoundException l_nfe)
        {
            log.error("DBへのアクセスに失敗しました。", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }
        
        try
        {
            //1.11 isFullyExecuted()
            //1.12 (*1)全出来（isFullyExecuted() == true）の場合、処理実施
            l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
            if (l_reOrderUnit.isFullyExecuted())
            {
                //1.12.1 sendMailProcess(OrderUnit, String)
                //  [sendMainProcess()に指定する引数] 
                //  注文単位：　@注文単位オブジェクト 
                //  失効理由コード：　@null 
                WEB3IfoExecutedMailSendService l_web3IfoExeMailSendService =
                    (WEB3IfoExecutedMailSendService)Services.getService(
                    WEB3IfoExecutedMailSendService.class);
                l_web3IfoExeMailSendService.sendMailProcess(l_reOrderUnit, null);
                
                //1.13 notifyルールエンジンサーバ(IfoOrderUnit, OrderManagerPersistenceContext)
                //     notifyルールエンジンサーバ()にて業務エラーがスローされた場合
                try
                {
                    l_orderManager.notifyRLS((IfoOrderUnit)l_reOrderUnit, OrderManagerPersistenceContext.FILL_ORDER);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error("データ不整合エラー。", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify約定取消)<BR>
     * <BR>
     * 出来通知処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP出来通知）notify約定取消」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_tsExecDate - 約定日時
     * @@param l_dblExecQuantity - 約定数量
     * @@param l_dblExecPrice - 約定単価
     * @@roseuid 40879DB102B8
     */
    public void notifyExecuteCancel(
        OrderUnit l_orderUnit, 
        Timestamp l_tsExecDate, 
        double l_dblExecQuantity, 
        double l_dblExecPrice) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyExecuteCancel()";
        log.entering(STR_METHOD_NAME);

        if ((l_orderUnit == null) || (l_tsExecDate == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

        //1.1 注文単位を再取得する。（口座ロック競合した場合を考慮）
        OrderUnit l_reOrderUnit = l_orderUnit;
        try
        {
            l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch(NotFoundException l_nfe)
        {
            log.error("DBへのアクセスに失敗しました。", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //1.2 validate注文状態(OrderUnit)
        this.validateOrderStatus(l_reOrderUnit);
            
        //1.2 getExecutions()
        OrderExecution[] l_orderExecution = l_reOrderUnit.getExecutions();
        int l_intOrderExecutionCnt = 0;
        if (l_orderExecution != null)
        {
            l_intOrderExecutionCnt = l_orderExecution.length;
        }
        log.debug("l_intOrderExecutionCnt = " + l_intOrderExecutionCnt);
        
        Timestamp l_tsExecutionTime = null;
        
        //1.3 約定オブジェクトの数分LOOP
        int l_intOrderIndex = -1;
        for (int i = 0; i < l_intOrderExecutionCnt; i++)
        {
            //1.3.1 getExecutionQuantity()
            double l_dblExecutionQuantity = l_orderExecution[i].getExecutionQuantity();
            if (Double.isNaN(l_dblExecutionQuantity))
            {
                l_dblExecutionQuantity = 0D;
            }

            //1.3.2 getExecutionPrice()
            double l_dblExecutionPrice = l_orderExecution[i].getExecutionPrice();
            if (Double.isNaN(l_dblExecutionPrice))
            {
                l_dblExecutionPrice = 0D;
            } 

            //1.3.3 数量／単価が一致する約定オブジェクトが見つかった場合
            if ((l_dblExecPrice == l_dblExecutionPrice) 
                && (l_dblExecQuantity == l_dblExecutionQuantity))
            {
                //同じ数量／単価の約定が複数件あった場合は、約定日が古いほうの約定を処理対象とする。
                Timestamp l_tsExecutionTimeTemp = l_orderExecution[i].getExecutionTimestamp();
                if ((l_tsExecutionTime == null) || (l_tsExecutionTimeTemp.before(l_tsExecutionTime)))
                {
                    l_tsExecutionTime = l_tsExecutionTimeTemp;
                    l_intOrderIndex = i;
                }
            }
        }

        //約定日が古いほうの約定を処理対象とする
        if (l_intOrderIndex != -1)
        {
            long l_lngOrderExecutionId = l_orderExecution[l_intOrderIndex].getOrderExecutionId();
            log.debug("l_lngOrderExecutionId = " + l_lngOrderExecutionId);

            //1.4 先物OP出来通知更新インタセプタ()
            WEB3IfoExecuteNotifyUpdateInterceptor l_interceptor = new WEB3IfoExecuteNotifyUpdateInterceptor();

            //キュー.約定日時をインタセプタに設定。
            l_interceptor.setExecTimestamp(l_tsExecDate);

            //1.5 setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            //  [引数] 
            //  arg0： 生成した先物OP出来通知更新インタセプタ
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);  

            //1.6 setThreadLocalPersistenceEventInterceptor(arg0 : IfoPositionManagerPersistenceEventInterceptor)
            //  [引数] 
            //  arg0： 生成した先物OPポジション更新インタセプタ
            WEB3IfoPositionUpdateInterceptor l_pInterceptor = new WEB3IfoPositionUpdateInterceptor();
            WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
            l_positionManager.setThreadLocalPersistenceEventInterceptor(l_pInterceptor);  
        
            //1.7  getOrderId()
            long l_lngOrderID = l_reOrderUnit.getOrderId();
            log.debug("l_lngOrderID = " + l_lngOrderID);

            //1.8 DefaultUndoOrderFillMarketResponseMessage(long, long)
            //  [コンストラクタの引数] 
            //  注文ＩＤ： 注文単位.getOuderUnitId() 
            //  約定ＩＤ：（取得した約定ＩＤ）            
            DefaultUndoOrderFillMarketResponseMessage l_undoOrderFillMarketResponseMessage = 
                new DefaultUndoOrderFillMarketResponseMessage(l_lngOrderID, l_lngOrderExecutionId);

            //1.9 process(MarketResponseMessage)
            //  [process()に指定する引数] 
            //  約定取消内容：（生成した約定取消内容オブジェクト）
            MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
            IfoMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService = 
                (IfoMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
            l_marketResponseReceiverCallbackService.process(l_undoOrderFillMarketResponseMessage);

            //1.10 updateトランザクション(long)(先物OPポジションマネージャ::updateトランザクション)
            //  [updateトランザクション()に指定する引数] 
            //  注文単位ＩＤ：　@注文単位.getOrderUnitId() 
            WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl = (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();
            l_ifoPositionManagerImpl.updateTransaction(l_reOrderUnit.getOrderUnitId());

            //1.11 概算受渡代金の更新を行う。
            //  [引数の設定]
            //  注文単位：　@注文単位
            try
            {
                l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());                    
                l_orderManager.updateEstimateDeliveryAmount(l_reOrderUnit);
            }
            catch(NotFoundException l_nfe)
            {
                log.error("DBへのアクセスに失敗しました。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), 
                    l_nfe);
            }

            //1.12 get補助口座()(拡張アカウントマネージャ::get補助口座)
            //  [引数] 
            //  口座ID： 注文単位.口座ID 
            //  補助口座ID： 注文単位.補助口座ID 
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount = l_accountManager.getSubAccount(
                l_reOrderUnit.getAccountId(),
                l_reOrderUnit.getSubAccountId());                
            }
            catch(NotFoundException l_nfe)
            {
                log.error("DBへのアクセスに失敗しました。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), 
                    l_nfe);
            }

            //1.13 余力再計算(補助口座 : 補助口座)
            //  [引数] 
            //  補助口座： get補助口座()の戻り値 
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
            }

            //1.15 undoSendMail(OrderUnit)
            //  [sendMainProcess()に指定する引数] 
            //  注文単位：　@注文単位オブジェクト 
            WEB3IfoExecutedMailSendService l_executedMailSendService = 
                (WEB3IfoExecutedMailSendService)Services.getService(WEB3IfoExecutedMailSendService.class);
            l_executedMailSendService.undoSendMail(l_reOrderUnit);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate注文状態)<BR>
     * 対象注文が、約定／約定取消による更新を行って良い状態であるかチェックする。<BR>
     * <BR>
     * （チェック内容）<BR>
     * １．市場から確認済みの数量==nullの場合、<BR>
     * 　@「該当注文は受付未済／変更の受付済／発注中の状態」の例外をthrowする。<BR>
     * <BR>
     * ２．注文状態のチェック：以下のいずれかに該当する場合、１．と同じ例外をthrowする。<BR>
     * 　@　@　@　@ACCEPTED：受付済（新規注文）<BR>
     * 　@　@　@　@MODIFY_ACCEPTED：受付済（変更注文）<BR>
     * 　@　@　@　@MODIFYING：発注中（変更注文）<BR>
     * <BR>
     * 以外、そのままreturnする。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderStatus(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateOrderStatus(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_orderUnitRow.getConfirmedQuantity() == 0.0D)
        {
            log.debug("該当注文は受付未済／変更の受付済／発注中の状態。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.MODIFYING.equals(l_orderStatus))
        {
            log.debug("該当注文は受付未済／変更の受付済／発注中の状態。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
