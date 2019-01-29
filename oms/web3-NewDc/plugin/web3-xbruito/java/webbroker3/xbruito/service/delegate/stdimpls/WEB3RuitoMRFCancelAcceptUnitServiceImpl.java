head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMRFCancelAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投MRF取消受付１件サービス実装クラス(WEB3RuitoMRFCancelAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import webbroker3.xbruito.service.delegate.WEB3RuitoMRFCancelAcceptUnitService;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * 累投MRF取消受付１件サービス実装クラス<BR>
 * <BR>
 * 注文取消１件ごとの受付処理を実施する。<BR>
 */
public class WEB3RuitoMRFCancelAcceptUnitServiceImpl implements 
    WEB3RuitoMRFCancelAcceptUnitService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFCancelAcceptUnitServiceImpl.class);

    /**
     * 累投MRF取消受付完了処理をおこなう。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（累投MRF取消受付）notify取消受付完了」参照。<BR>
     * 
     * １）　@拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()<BR>
     * 　@  をコールし、インタセプタを設定する。<BR>
     *  　@ [setThreadLocalPersistenceEventInterceptorに渡すパラメタ]<BR>
     * 　@  インタセプタ： 引数.累投受付確定インタセプタ<BR>
     * 
     * ２）　@RuitoMarketResponseReceiverCallbackService<BR>
     *       を取得する。<BR>
     * <BR>
     * ３）　@DefaultCancelOrderAcceptedMarketResponseMessage<BR>
     *      オブジェクトを生成する。<BR>
     *    　@[コンストラクタに渡すパラメタ]<BR>
     * 　@　@注文ID： 引数.累投注文単位.getOrderId()の戻り値<BR>
     * 
     * ４） RuitoMarketResponseReceiverCallbackService.process()<BR>
     *      メソッド<BR>
     *      をコールする。<BR>
     *    　@[processに渡すパラメタ]<BR>
     * 　@　@取消注文受付済市場応答メッセージ：生成した<BR>
     *      DefaultCancelOrderAcceptedMarketResponseMessage<BR>
     *      オブジェクト<BR>
     * <BR>
     * ５）　@process()メソッドの戻り値.isFailedResult()の値がtrueの場合は、<BR>
     *      例外をスローする。<BR>
     *      累投MRF取消受付エラー:<BR>
     *      classpath:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00196<BR>
     *      code:210<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - 累投受付確定インタセプタ<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40890B9B0186
     */
    public void notifyCancelAcceptComplete(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "notifyCancelAcceptComplete(RuitoOrderUnit l_ruitoOrderUnit, " +
            "WEB3RuitoAcceptedDecisionInterceptor " +
            "l_ruitoAcceptDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);
    
        if (l_ruitoOrderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);

        WEB3RuitoOrderManager l_ruitoOrderMgr = 
        (WEB3RuitoOrderManager) l_tm.getOrderManager();
        MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();


        //１）　@拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()
        l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
            l_ruitoAcceptDecisionInterceptor);
        
        RuitoMarketResponseReceiverCallbackService l_service =
           (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor
           .getMarketResponseReceiverCallbackService();
            
        log.debug("OrderId = " + l_ruitoOrderUnit.getOrderId());
        
        DefaultCancelOrderAcceptedMarketResponseMessage 
            l_defaultCancelOrderAcceptedMarketResponseMessage =
            new DefaultCancelOrderAcceptedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());

        ProcessingResult l_processingResult = null;
        l_processingResult =
            l_service.process(
                l_defaultCancelOrderAcceptedMarketResponseMessage);
                
        log.debug("l_processingResult.isFailedResult()=" + 
            l_processingResult.isFailedResult());
        
        //process()メソッドの戻り値.isFailedResult()の値がtrueの場合
        if (l_processingResult.isFailedResult())
        {
            log.debug("__累投MRF取消受付エラー__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00196,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "process()メソッドの戻り値.isFailedResult()の値がtrueの場合");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 累投MRF取消受付失敗処理をおこなう。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（累投MRF取消受付）notify取消受付失敗」参照。<BR>
     * <BR>
     * １）　@拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()<BR>
     *    　@をコールし、インタセプタを設定する。<BR>
     *    　@[setThreadLocalPersistenceEventInterceptorに渡すパラメタ]<BR>
     * 　@　@インタセプタ： 引数.累投受付確定インタセプタ<BR>
     * <BR>
     * ２）　@RuitoMarketResponseReceiverCallbackServiceを取得する。<BR>
     * <BR>
     * ３）　@DefaultCancelOrderRejectedMarketResponseMessage<BR>
     *      オブジェクトを生成する。<BR>
     *  　@  [コンストラクタに渡すパラメタ]<BR>
     * 　@　@注文ID： 引数.注文ID<BR>
     * <BR>
     * ４）　@RuitoMarketResponseReceiverCallbackService.process()メソッドをコールする。
     * <BR>
     * 　@  [processに渡すパラメタ]<BR>
     * 　@　@取消注文拒否市場応答メッセージ：<BR>
     * 　@　@生成したDefaultCancelOrderRejectedMarketResponseMessage<BR>
     *     オブジェクト<BR>
     * <BR>
     * ５）　@processメソッドの戻り値.isFailedResult()の値がtrueの場合は、<BR>
     *    例外をスローする。<BR>
     *    累投MRF取消受付エラー:<BR>
     *     classpath:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_00196<BR>
     *     code:210<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - 累投受付確定インタセプタ<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40890B9B0188
     */
    public void notifyCancelAcceptFail(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "notifyCancelAcceptComplete(RuitoOrderUnit l_ruitoOrderUnit, " +
            "WEB3RuitoAcceptedDecisionInterceptor " +
            "l_ruitoAcceptDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);
    
        if (l_ruitoOrderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);

        WEB3RuitoOrderManager l_ruitoOrderMgr = 
            (WEB3RuitoOrderManager) l_tm.getOrderManager();
        MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();
                
        //１）　@拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()
        l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
            l_ruitoAcceptDecisionInterceptor);

        //２）　@RuitoMarketResponseReceiverCallbackServiceを取得する。
        RuitoMarketResponseReceiverCallbackService l_service =
            (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor.getMarketResponseReceiverCallbackService();

        //３）　@DefaultCancelOrderRejectedMarketResponseMessage
        //オブジェクトを生成する。
        DefaultCancelOrderRejectedMarketResponseMessage 
            l_defaultCancelOrderRejectedMarketResponseMessage =
            new DefaultCancelOrderRejectedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());

        ProcessingResult l_processingResult = null;
        //４）　@RuitoMarketResponseReceiverCallbackService.process()
        //メソッドをコールする。
        l_processingResult =
            l_service.process(
                l_defaultCancelOrderRejectedMarketResponseMessage);

        //５）　@processメソッドの戻り値.isFailedResult()の値がtrueの場合
        if (l_processingResult.isFailedResult())
        {
            log.debug("__累投MRF取消受付エラー__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00196,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "processメソッドの戻り値.isFailedResult()の値がtrueの場合s");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
