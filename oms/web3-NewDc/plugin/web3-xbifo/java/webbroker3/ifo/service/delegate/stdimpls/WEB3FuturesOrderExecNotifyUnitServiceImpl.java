head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderExecNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物出来通知１件サービス実装クラス(WEB3FuturesOrderExecNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/23 艾興 (中訊) 新規作成
Revesion History : 2006/8/15 郭英 (中訊) 仕様変更 モデル501
Revesion History : 2006/11/29 周捷(中訊) 仕様変更モデルNo.580
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultFillOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoExecuteNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoPositionUpdateInterceptor;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物出来通知UnitServiceImpl)<BR>
 * 株価指数先物出来通知１件サービス実装クラス<BR>
 * <BR>
 * １件ごとの出来通知処理を実施する。<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)を指定する。<BR>
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3FuturesOrderExecNotifyUnitServiceImpl
    implements WEB3FuturesOrderExecNotifyUnitService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesOrderExecNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40F7A2C402DE
     */
    public WEB3FuturesOrderExecNotifyUnitServiceImpl()
    {

    }

    /**
     * (notify約定)<BR>
     * 約定処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物出来通知）notify約定」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_tsExecDate - 約定日時<BR>
     * @@param l_dblExecQuantity - 約定数量<BR>
     * @@param l_dblExecPrice - 約定単価<BR>
     * @@param l_strExecNotifyDiv - 出来通知区分<BR>
     * @@roseuid 40A843180021
     */
    public void notifyExecute(
        OrderUnit l_orderUnit,
        Timestamp l_tsExecDate,
        double l_dblExecQuantity,
        double l_dblExecPrice,
        String l_strExecNotifyDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyExecute()";
        log.entering(STR_METHOD_NAME);

        if ((l_orderUnit == null) || (l_tsExecDate == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if ((l_dblExecQuantity <= 0) || (l_dblExecPrice <= 0))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager = (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();

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
        //1.2 getOrderUnitId()
        long l_lngOrderUnitID = l_reOrderUnit.getOrderUnitId();

        //1.3 validate注文状態(OrderUnit)
        this.validateOrderStatus(l_reOrderUnit);

        //1.3 DefaultFillOrderUnitSpec(long, double, double)
        //  [コンストラクタの引数]
        //  注文単位ＩＤ：　@注文単位.getOuderUnitId()
        //  約定数量：　@引数.約定数量
        //  約定単価：　@引数.約定単価
        DefaultFillOrderUnitSpec l_defaultFillOrderUnitSpec =
            new DefaultFillOrderUnitSpec(
                l_lngOrderUnitID,
                l_dblExecQuantity,
                l_dblExecPrice);

        //1.4 getOrderId()
        long l_lngOrderID = l_reOrderUnit.getOrderId();

        //1.5 先物OP出来通知更新インタセプタ()
        WEB3IfoExecuteNotifyUpdateInterceptor l_interceptor = new WEB3IfoExecuteNotifyUpdateInterceptor();

        //1.7 キュー.出来通知区分をインタセプタに設定
        l_interceptor.setDealedType(l_strExecNotifyDiv);
        
        //1.8 キュー.約定日時をインタセプタに設定
        l_interceptor.setExecTimestamp(l_tsExecDate);

        //1.8 setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
        //  [引数]
        //  arg0： 生成した先物OP出来通知更新インタセプタ
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //setThreadLocalPersistenceEventInterceptor(arg0 : IfoPositionManagerPersistenceEventInterceptor)
        //  [引数] 
        //  arg0： 生成した先物OPポジション更新インタセプタ
        WEB3IfoPositionUpdateInterceptor l_pInterceptor = new WEB3IfoPositionUpdateInterceptor();
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
        l_positionManager.setThreadLocalPersistenceEventInterceptor(l_pInterceptor); 

        //1.9  DefaultOrderFillMarketResponseMessage(long, FillOrderUnitSpec)
        DefaultOrderFillMarketResponseMessage l_defaultOrderFillMarketResponseMessage =
            new DefaultOrderFillMarketResponseMessage(
                l_lngOrderID,
                l_defaultFillOrderUnitSpec);

        //1.10 process(MarketResponseMessage)
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        IfoMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (IfoMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
        l_marketResponseReceiverCallbackService.process(
            l_defaultOrderFillMarketResponseMessage);

        //1.11 概算受渡代金の更新を行う。
        //  [引数の設定]
        //  注文単位：　@注文単位
        try
        {
            l_reOrderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitID);
            l_orderManager.updateFuturesEstimateDeliveryAmount(l_reOrderUnit);
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

        //1.12  isFullyExecuted( )
        boolean l_blnisFullyExecuted = l_reOrderUnit.isFullyExecuted();
        
        //1.13 (*1)全出来（isFullyExecuted() == true）の場合
        if (l_blnisFullyExecuted)
        {
            //1.13.1 sendMailProcess(OrderUnit, String)
            WEB3IfoExecutedMailSendService l_executedMailSendServiceImpl =
                (WEB3IfoExecutedMailSendService)Services.getService(WEB3IfoExecutedMailSendService.class);
            l_executedMailSendServiceImpl.sendMailProcess(l_reOrderUnit, null);
        
            //1.13.2 notifyルールエンジンサーバ(IfoOrderUnit, OrderManagerPersistenceContext)
            try
            {
                l_orderManager.notifyRLS(
                    (IfoOrderUnit) l_reOrderUnit, 
                    OrderManagerPersistenceContext.FILL_ORDER);
            }
            //1.13.3 notifyルールエンジンサーバ()にて業務エラーがスローされた場合
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify約定取消)<BR>
     * 出来通知処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物出来通知）notify約定取消」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_tsExecDate - 約定日時<BR>
     * @@param l_dblExecQuantity - 約定数量<BR>
     * @@param l_dblExecPrice - 約定単価<BR>
     * @@roseuid 40A843180041
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

        if ((l_dblExecQuantity <= 0) || (l_dblExecPrice <= 0))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager = (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();

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

        int l_intOrderExecutionCnt = l_orderExecution.length;
        Timestamp l_tsExecutionTime = null;
        int l_intOrderIndex = -1;
        //1.3 約定オブジェクトの数分LOOP
        for (int i = 0; i < l_intOrderExecutionCnt; i++)
        {
            //1.3.1getExecutionQuantity()
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

            if ((l_dblExecPrice == l_dblExecutionPrice) && (l_dblExecQuantity == l_dblExecutionQuantity))
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

        if (l_intOrderIndex != -1)
        {
            //1.3.3.1 getOrderExecutionId()
            long l_lngOrderExecutionId = l_orderExecution[l_intOrderIndex].getOrderExecutionId();

            //1.4 先物OP出来通知更新インタセプタ()
            WEB3IfoExecuteNotifyUpdateInterceptor l_interceptor = new WEB3IfoExecuteNotifyUpdateInterceptor();

            //1.5 キュー.約定日時をインタセプタに設定
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

            //1.7 getOrderId()
            long l_lngOrderID = l_reOrderUnit.getOrderId();

            //1.8 DefaultUndoOrderFillMarketResponseMessage(long, long)
            DefaultUndoOrderFillMarketResponseMessage l_undoOrderFillMarketResponseMessage =
                new DefaultUndoOrderFillMarketResponseMessage(
                    l_lngOrderID,
                    l_lngOrderExecutionId);

            //1.9 process(MarketResponseMessage)
            MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
            IfoMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackServiceImpl =
                (IfoMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
            l_marketResponseReceiverCallbackServiceImpl.process(l_undoOrderFillMarketResponseMessage);

            //1.10 updateトランザクション(long)(先物OPポジションマネージャ::updateトランザクション)
            WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl =
                (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getPositionManager();
            l_ifoPositionManagerImpl.updateTransaction(
            l_reOrderUnit.getOrderUnitId());
            
            //1.11 概算受渡代金の更新を行う。
            //  [引数の設定]
            //  注文単位：　@注文単位
            try
            {
                l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
                l_orderManager.updateFuturesEstimateDeliveryAmount(l_reOrderUnit);
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

            //1.13 undoSendMail(OrderUnit)(先物OP約定メール送信サービスImpl::undoSendMail)
            //  [sendMainProcess()に指定する引数]
            //  注文単位：　@注文単位オブジェクト
            WEB3IfoExecutedMailSendService l_executedMailSendServiceImpl =
                (WEB3IfoExecutedMailSendService)Services.getService(
                    WEB3IfoExecutedMailSendService.class);
            l_executedMailSendServiceImpl.undoSendMail(l_reOrderUnit);
        }
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
