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
filename	WEB3RuitoOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投注文受付UnitServiceImpl (WEB3RuitoOrderAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  周勇 (中訊) 新規作成
                   2004/12/07 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
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
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptUnitService;
/**
 * 累投注文受付１件サービス実装クラス<BR>
 * <BR>
 * 注文１件ごとの受付処理を実施する。<BR>
 */
public class WEB3RuitoOrderAcceptUnitServiceImpl
    implements WEB3RuitoOrderAcceptUnitService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderAcceptUnitServiceImpl.class);
    /**
     * 注文受付失敗処理を行う。<BR>
     * <BR>
     * シーケンス図「（累投注文受付）notify注文受付失敗」参照。 <BR>
     * <BR>
     * 　@process()メソッドの戻り値.isFailedResult()の値が<BR>
     *       trueの場合は、例外をスローする。。<BR>
     *        class    : WEB3BusinessLayerException<BR>
     *        tag      : BUSINESS_ERROR_00239<BR>
     * <BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - 累投受付確定インタセプタ<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4088F66602ED
     */
    public void notifyOrderAcceptFail(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyOrderAcceptFail(RuitoOrderUnit l_ruitoOrderUnit,"
                + "WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null
            || l_ruitoAcceptDecisionInterceptor == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //1.1 拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()をコールし、インタセプタを設定する
        WEB3RuitoOrderManager l_web3RuitoOrderManager = null; //拡張累投注文マネージャ
        l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();
        
        l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoAcceptDecisionInterceptor);
        
        //1.2 RuitoMarketResponseReceiverCallbackServiceを取得する
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        RuitoMarketResponseReceiverCallbackService l_service =
            (RuitoMarketResponseReceiverCallbackService) 
                l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        //1.3 DefaultNewOrderRejectedMarketResponseMessageオブジェクトを生成する
        long l_lnOrderId = 0;
        l_lnOrderId = l_ruitoOrderUnit.getOrderId();
        DefaultNewOrderRejectedMarketResponseMessage l_defaultNewOrderRejectedMarketResponseMessage =
            null;
        l_defaultNewOrderRejectedMarketResponseMessage =
            new DefaultNewOrderRejectedMarketResponseMessage(l_lnOrderId);
        
        //1.4 RuitoMarketResponseReceiverCallbackService.process()メソッドをコールする
        ProcessingResult l_processingResult = null;
        log.debug("begin ProcessingResult");
        l_processingResult =
            l_service.process(
                l_defaultNewOrderRejectedMarketResponseMessage);
        log.debug(" l_processingResult = " + l_processingResult);
        
        //process()メソッドの戻り値.isFailedResult()の値がtrueの場合は、例外をスローする。
        log.debug("l_processingResult.isFailedResult() = " + l_processingResult.isFailedResult());
        if (l_processingResult.isFailedResult())
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00239,
                STR_METHOD_NAME);
        }
        
        log.debug("end if (l_processingResult.isFailedResult())");
        
        //拡張アカウントマネージャを取得する。
        WEB3GentradeAccountManager l_gentradeAccountManaer = null;
        l_gentradeAccountManaer =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        log.debug("l_gentradeAccountManaer =" + l_gentradeAccountManaer);
        
        //拡張アカウントマネージャ.getSubAccount()をコールし、補助口座オブジェクトを取得する.        
        SubAccount l_subAccount = null;
        long l_lnAccountId = l_ruitoOrderUnit.getAccountId();//口座ID
        long l_lnSubAccountId = l_ruitoOrderUnit.getSubAccountId();//補助口座ID
        log.debug("l_lnAccountId  = " + l_lnAccountId); 
        log.debug("l_lnSubAccountId  = " + l_lnSubAccountId);
        try{
        l_subAccount =
            l_gentradeAccountManaer.getSubAccount(
                l_lnAccountId,
                l_lnSubAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ with 補助口座オブジェクトを取得する", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "."+ STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.debug("l_subAccount = " + l_subAccount);
        
        //引数.累投注文単位.getDataSourceObject().getMRF注文識別コード()の戻り値がnullで無い場合、以下の処理を行う
        RuitoOrderUnitRow l_ruitoOrderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
        log.debug("l_ruitoOrderUnitRow  = " + l_ruitoOrderUnitRow);
        
        // 引数.累投注文単位.getDataSourceObject().getMRF注文識別コード()
        String l_StrMrfRequstNumber = l_ruitoOrderUnitRow.getMrfOrderRequestNumber();
        log.debug("l_StrMrfRequstNumber  = " + l_StrMrfRequstNumber);
        log.debug("entry  if (l_StrMrfRequstNumber != null)");
        
        //1.5 
        if (l_StrMrfRequstNumber != null)
        {
            //1.5.1 累投注文単位オブジェクトを取得する。 
            RuitoOrderUnit l_ruitoOrderUnitEx = null;
            try
            {
                l_ruitoOrderUnitEx = 
                    l_web3RuitoOrderManager.getRuitoOrderUnit(
                        l_lnAccountId,
                        l_lnSubAccountId,
                        l_StrMrfRequstNumber);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "."+ STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //1.5.2 引数.累投受付確定インタセプタ.set注文エラー理由コード()をコースする
            l_ruitoAcceptDecisionInterceptor.setOrderErrorReasonCode(null);
            log.debug(
                "l_ruitoAcceptDecisionInterceptor.getOrderErrorReasonCode() = "
                    + l_ruitoAcceptDecisionInterceptor.getOrderErrorReasonCode());
            //1.5.3 拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()をコールし、インタセプタを設定する
            l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_ruitoAcceptDecisionInterceptor);
            log.debug(
                "l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor() = "
                    + l_web3RuitoOrderManager
                        .getThreadLocalPersistenceEventInterceptor());

            // 1.5.4 －CancelOrderSpecオブジェクトを生成する。
            // 　@　@[コンストラクタに渡すパラメタ]<BR>
            // 　@　@　@注文ID：<BR>
            //        取得した累投注文単位オブジェクト.getOrderId()の戻り値<BR>
            CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_ruitoOrderUnitEx.getOrderId());

            log.debug("l_cancelOrderSpec =" + l_cancelOrderSpec);

            MainAccount l_mainAccount = null;
            try
            {
                log.debug("l_lnAccountId = " + l_lnAccountId);
                log.debug("l_lnSubAccountId = " + l_lnSubAccountId);
                //1.5.5 顧客オブジェクトを取得する。 
                l_mainAccount =
                    l_gentradeAccountManaer.getMainAccount(l_lnAccountId);
                log.debug("l_mainAccount = " + l_mainAccount);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__ ", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "."+ STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //1.5.7 取引パスワードの取得
            String l_tradingPassword = null;
            l_tradingPassword = l_mainAccount.getTradingPassword();
            WEB3Crypt l_crypt = new WEB3Crypt();
            log.debug("l_tradingPassword =" + l_tradingPassword);
            //累投市場リクエスト送信サービスに、市場送信処理を実施するという設定を行う
            WEB3RuitoMarketRequestSubmitServiceImpl l_ruitoMarketRequestSubmitServiceImpl =
                null;
            
            //1.5.8 累投市場リクエスト送信サービス
            l_ruitoMarketRequestSubmitServiceImpl =
                (WEB3RuitoMarketRequestSubmitServiceImpl) l_marketAdapter
                    .getMarketRequestSenderServce();
            l_ruitoMarketRequestSubmitServiceImpl.setMarketSubmit(true);
            
            //1.5.9 拡張累投注文マネージャ.submitCancelOrder()をコールする
            OrderSubmissionResult l_orderSubmissionResult = null;
                l_orderSubmissionResult =
                    l_web3RuitoOrderManager.submitCancelOrder(
                        l_subAccount,
                        l_cancelOrderSpec,
                        l_crypt.decrypt(l_tradingPassword),
                        true);
            log.debug("l_orderSubmissionResult = " + l_orderSubmissionResult);
            
            //拡張累投注文マネージャ.submitCancelOrde()の戻り値判定

            if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                log.debug("取消注文失敗");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00240,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "拡張累投注文マネージャ.submitCancelOrde()の戻り値." +
                        "getProcessingResult().isSuccessfulResult()==falseの場合");
            }
        }
        // 1.6　@余力残高情報更新
        //余力再計算サービス.余力再計算()をコールする。
        WEB3TPTradingPowerReCalcService l_tPReCalcService = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                    WEB3TPTradingPowerReCalcService.class);
        l_tPReCalcService.reCalcTradingPower(
                (WEB3GentradeSubAccount)l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * 累投注文受付完了処理をおこなう。<BR>
     * <BR>
     * シーケンス図「（累投注文受付）notify注文受付完了」参照。 <BR>
     * <BR>
     * 　@process()メソッドの戻り値.isFailedResult()の値が<BR>
     *       falseの場合は、<BR>
     *       例外をスローする。<BR>
     *        class    : WEB3BusinessLayerException<BR>
     *        tag      : BUSINESS_ERROR_00239<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - 累投受付確定インタセプタ<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4088F66602EE
     */
    public void notifyOrderAcceptComplete(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyOrderAcceptComplete(RuitoOrderUnit l_ruitoOrderUnit,"
                + "WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor))";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null
            || l_ruitoAcceptDecisionInterceptor == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        //1.1 拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor() をコールし、インタセプタを設定する
        WEB3RuitoOrderManager l_web3RuitoOrderManager = null;
        //拡張累投注文マネージャ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getOrderManager();

        l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoAcceptDecisionInterceptor);
        //1.2 RuitoMarketResponseReceiverCallbackServiceを取得する
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        MarketResponseReceiverCallbackService l_service =
            l_marketAdapter.getMarketResponseReceiverCallbackService();
        //1.3 DefaultNewOrderAcceptedMarketResponseMessageオブジェクトを生成する
        DefaultNewOrderAcceptedMarketResponseMessage l_defaultNewCashBasedOrderMarketRequestMessage =
            new DefaultNewOrderAcceptedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());
        //RuitoMarketResponseReceiverCallbackService.process()メソッドをコールする
        ProcessingResult l_presessingResult = null;
        l_presessingResult =
            l_service.process(l_defaultNewCashBasedOrderMarketRequestMessage);

        //1.4 process()メソッドの戻り値.isFailedResult()の値がfalseの場合は、例外をスローする
        log.debug("l_presessingResult.isFailedResult() = " + l_presessingResult.isFailedResult());

        if (l_presessingResult.isFailedResult())
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00239,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "process()メソッドの戻り値.isFailedResult()の値がfalseの場合");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
