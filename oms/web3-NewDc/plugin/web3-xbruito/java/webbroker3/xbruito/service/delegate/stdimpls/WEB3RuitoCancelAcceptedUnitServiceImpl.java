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
filename	WEB3RuitoCancelAcceptedUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投取消受付１件サービス実装クラス  
                   (WEB3RuitoCancelAcceptedUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/16 王艶芳 (中訊) 新規作成
                   2004/12/07 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedUnitService;

/**
 * 累投取消受付１件サービス実装クラス<BR>
 * <BR>
 * 注文取消１件ごとの受付処理を実施する。<BR>
 */
public class WEB3RuitoCancelAcceptedUnitServiceImpl
    implements WEB3RuitoCancelAcceptedUnitService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3RuitoCancelAcceptedUnitServiceImpl.class);

    /**
     * 累投取消受付完了処理をおこなう。<BR>
     * <BR>
     * シーケンス図<BR>
     * <BR>
     * 　@process()メソッドの戻り値.isFailedResult()の値がtrueの場合は、<BR>
     *      例外をスローする。<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00305<BR>
     * <BR>
     * 　@－拡張累投注文マネージャ.submitCancelOrder()の戻り値判定<BR>
     * 　@ 　@拡張累投注文マネージャ.submitCancelOrder()の戻り値<BR>
     *           .getProcessingResult().isSuccessfulResult()==false<BR>
     * 　@   の場合、例外をスローする。<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00240<BR>
     * <BR>
     * @@param l_ruitoOrderUnit - 累投注文単位 <BR>
     * @@param l_ruitoAcceptedDecisionInterceptor - 累投受付確定インタセプタ <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408891B40241
     */
    public void notifyCancelAcceptedComplete(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptedDecisionInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancelAcceptedComplete(RuitoOrderUnit l_ruitoOrderUnit,"
                + "WEB3RuitoAcceptedDecisionInterceptor "
                + "l_ruitoAcceptDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null
            || l_ruitoAcceptedDecisionInterceptor == null)
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
        WEB3RuitoOrderManager l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_tm.getOrderManager();
        l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoAcceptedDecisionInterceptor);
      
        //1.2　@RuitoMarketResponseReceiverCallbackServiceを取得する 
        MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();
        RuitoMarketResponseReceiverCallbackService l_ruitoMarketService =
            (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor.getMarketResponseReceiverCallbackService();

        
        //1.3　@DefaultCancelOrderAcceptedMarketResponseMessageオブジェクト
        //を生成する。
        long l_lngOrderId = 0L;
        l_lngOrderId = l_ruitoOrderUnit.getOrderId();
        DefaultCancelOrderAcceptedMarketResponseMessage 
        l_defaultCancelOrderAcceptedMarketResponseMessage = null;
        l_defaultCancelOrderAcceptedMarketResponseMessage =
            new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);

        //1.4 RuitoMarketResponseReceiverCallbackService.process()
        //    メソッドをコールする。 
        log.debug("l_ruitoMarketResponseReceiverCallbackService.process()");
        ProcessingResult l_processingResult = null;
        l_processingResult =
            l_ruitoMarketService.process(
                l_defaultCancelOrderAcceptedMarketResponseMessage);
        
        //process()メソッドの戻り値.isFailedResult()の値がtrueの場合は、例外をスローする。
        boolean l_blnResult1 = l_processingResult.isFailedResult();        
        if (l_blnResult1)
        {
            log.debug("process()メソッドの戻り値.isFailedResult()の値がtrueの場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00305,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "process()メソッドの戻り値.isFailedResult()の値がtrueの場合");
        }

        // 拡張アカウントマネージャを取得する。
        MainAccount l_mainAccount = null;                
        WEB3GentradeAccountManager l_gentradeAccMgr =
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                    
        long l_accountId = 0; //口座ID
        long l_subAccountId = 0; //補助口座ID
        l_accountId = l_ruitoOrderUnit.getAccountId();
        l_subAccountId = l_ruitoOrderUnit.getSubAccountId();
        SubAccount l_subAccount = null;
        
        //拡張アカウントマネージャ.getSubAccount()をコールし、 
        //補助口座オブジェクトを取得する 
        try{  
            l_subAccount = l_gentradeAccMgr.getSubAccount(
                l_accountId ,l_subAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__",l_ex);
            throw new WEB3BaseException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80006,
               this.getClass().getName() + STR_METHOD_NAME,
               l_ex);                               
        }

        //引数.累投注文単位.getDataSourceObject().getMRF注文識別コード()
        //の戻り値がnullで無い場合、以下の処理を行う
        String l_mrfRequstNumber = null;
        
        RuitoOrderUnitRow l_ruitoOrderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
        l_mrfRequstNumber = l_ruitoOrderUnitRow.getMrfOrderRequestNumber();

        if (l_mrfRequstNumber != null)
        {
            try
            {
                RuitoOrderUnit l_ruitoOrderUnitMrf = 
                    l_web3RuitoOrderManager.getRuitoOrderUnit(
                        l_accountId,
                        l_subAccountId,
                        l_mrfRequstNumber);

                //1.6 引数.累投受付確定インタセプタ.set注文エラー理由コード()をコースする
                l_ruitoAcceptedDecisionInterceptor.setOrderErrorReasonCode(null);
        
                //1.7 拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor() 
                //をコールし、インタセプタを設定する。
                l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
                    l_ruitoAcceptedDecisionInterceptor);
        
                //1.8 CancelOrderSpecオブジェクトを生成する。
                CancelOrderSpec l_cancelOrderSpec = null;
                l_cancelOrderSpec = new CancelOrderSpec(l_ruitoOrderUnitMrf.getOrderId());
        
                //1.10 拡張アカウントマネージャ.getMainAccount()をコールし、
                //顧客オブジェクトを取得する
                l_mainAccount = l_gentradeAccMgr.getMainAccount(l_ruitoOrderUnitMrf.getAccountId());
                
                //1.11 取引パスワードの取得
                String l_tradingPassword = null;
                l_tradingPassword = l_mainAccount.getTradingPassword();
                WEB3Crypt l_crypt = new WEB3Crypt();
                
                //1.12 累投市場リクエスト送信サービスに、市場送信処理を実施するという設定を行う
                TradingModule l_tradingModule = 
                        l_finApp.getTradingModule(ProductTypeEnum.RUITO);
                MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
                WEB3RuitoMarketRequestSubmitServiceImpl l_web3RuitoMarketRequestSubmitService =
                        (WEB3RuitoMarketRequestSubmitServiceImpl)l_marketAdapter
                        .getMarketRequestSenderServce();
                
                l_web3RuitoMarketRequestSubmitService.setMarketSubmit(true);
                
                //1.13 拡張累投注文マネージャ.submitCancelOrder()をコールする
                OrderSubmissionResult l_orderSubmissionResult = null;
                l_orderSubmissionResult =
                    l_web3RuitoOrderManager.submitCancelOrder(
                        l_subAccount,
                        l_cancelOrderSpec,
                        l_crypt.decrypt(l_tradingPassword),
                        true);
    
                //拡張累投注文マネージャ.submitCancelOrde()の戻り値判定
                boolean l_blnResult = 
                    l_orderSubmissionResult.getProcessingResult().isSuccessfulResult();
                
                if (!l_blnResult)
                {
                    log.debug("取消注文失敗");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00240,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "拡張累投注文マネージャ.submitCancelOrder()の戻り値" +
                        ".getProcessingResult().isSuccessfulResult()==falseの場合");
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__",l_ex);
                throw new WEB3BaseException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                   this.getClass().getName() + STR_METHOD_NAME,
                   l_ex);                               
            }
        }
        
        //1.14 －余力残高情報更新 
        //余力再計算サービス.余力再計算()をコールする。
        WEB3TPTradingPowerReCalcService l_tPReCalcService = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                    WEB3TPTradingPowerReCalcService.class);
        l_tPReCalcService.reCalcTradingPower(
                (WEB3GentradeSubAccount)l_subAccount);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 累投取消受付失敗処理をおこなう。<BR>
     * <BR>
     * シーケンス図「（累投取消受付）notify取消受付失敗」参照。 <BR>
     * <BR>
     *    　@processメソッドの戻り値.isFailedResult()の値がtrueの場合は、<BR>
     *      例外をスローする。<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00305<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位 <BR>
     * @@param l_ruitoAcceptedDecisionInterceptor - 累投受付確定インタセプタ <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408891B50176
     */
    public void notifyCancelAcceptedFail(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptedDecisionInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancelAcceptedFail"
                + "(RuitoOrderUnit l_ruitoOrderUnit,"
                + "WEB3RuitoAcceptedDecisionInterceptor "
                + "l_ruitoAcceptedDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null
            || l_ruitoAcceptedDecisionInterceptor == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        //1.1  拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()
        // をコールし、インタセプタを設定する 
        OrderManager l_orderManager = null; //注文マネージャ
        WEB3RuitoOrderManager l_web3RuitoOrderManager = null;

        //拡張累投注文マネージャ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        l_orderManager = l_tm.getOrderManager();
        l_web3RuitoOrderManager = (WEB3RuitoOrderManager) l_orderManager;
        l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
        l_ruitoAcceptedDecisionInterceptor);

        //RuitoMarketResponseReceiverCallbackServiceを取得する 

        //1.2 RuitoMarketResponseReceiverCallbackServiceを取得する。
        MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();
        RuitoMarketResponseReceiverCallbackService l_ruitoMarketService =
            (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor.getMarketResponseReceiverCallbackService();
        
        //1.3 DefaultNewOrderAcceptedMarketResponseMessageオブジェクトを生成する    
        DefaultCancelOrderRejectedMarketResponseMessage 
        l_defaultNewOrderAcceptedMarketResponseMessage =
            new DefaultCancelOrderRejectedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());

        //1.4 RuitoMarketResponseReceiverCallbackService.process()メソッドをコールする    
        ProcessingResult l_presessingResult =
            l_ruitoMarketService.process(l_defaultNewOrderAcceptedMarketResponseMessage);

        //process()メソッドの戻り値.isFailedResult()の値がtrueの場合は、
        //例外をスローする
        boolean l_blnResult = l_presessingResult.isFailedResult();   
        log.debug("process()メソッドの戻り値.isFailedResult()の値 = " + l_blnResult);
        
        if (l_blnResult)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00305,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "累投取消受付失敗処理失敗");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
