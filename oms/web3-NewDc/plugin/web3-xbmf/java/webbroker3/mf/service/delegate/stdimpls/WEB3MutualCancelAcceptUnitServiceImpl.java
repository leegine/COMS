head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信取消受付１件サービス実装クラス(WEB3MutualCancelAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/23 韋念瓊 (中訊) レビュー    
                   2004/12/11 于美麗 (中訊) 残対応
*/

package webbroker3.mf.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信取消受付UnitServiceImpl)<BR>
 * 投信取消受付１件サービス実装クラス<BR>
 * <BR>
 * 注文取消１件ごとの受付処理を実施する。<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualCancelAcceptUnitServiceImpl
    implements WEB3MutualCancelAcceptUnitService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelAcceptUnitServiceImpl.class);

    /**
     * (notify取消受付完了)<BR>
     * 投信取消受付完了処理をおこなう。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（投信取消受付）notify取消受付完了」参照。 <BR>
     *  <BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位
     * @@param l_mutualFundAcceptConfirmInterceptor - 投信受付確定インタセプタ
     * @@roseuid 40C40EFA012B
     */
    public void notifyCancelAcceptComplete(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor l_mutualFundAcceptConfirmInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancelAcceptComplete(MutualFundOrderUnit l_mutualFundOrderUnit,"
                + " WEB3MutualFundAcceptConfirmInterceptor "
                + " l_mutualFundAcceptConfirmInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFundOrderUnit == null
            || l_mutualFundAcceptConfirmInterceptor == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // １）　@拡張投信注文マネージャ.setThreadLocalPersistenceEventInterceptor()
        //  拡張投信注文マネージャの取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                  ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //setThreadLocalPersistenceEventInterceptor()をコール
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_mutualFundAcceptConfirmInterceptor);

        // ２）　@MutualFundMarketResponseReceiverCallbackServiceを取得する
        MarketAdapter l_marketAdapter =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getMarketAdapter();
        MutualFundMarketResponseReceiverCallbackService l_marketCallbackService =
            (MutualFundMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();

        // ３）　@DefaultCancelOrderAcceptedMarketResponseMessage を生成
        DefaultCancelOrderAcceptedMarketResponseMessage l_defaultMarketResponseMessage =
            new DefaultCancelOrderAcceptedMarketResponseMessage(
                l_mutualFundOrderUnit.getOrderId());
     
        // ４）　@MutualFundMarketResponseReceiverCallbackService.process()をコール
        ProcessingResult l_processingResylt = l_marketCallbackService.process(
                                                   l_defaultMarketResponseMessage);
        
        // ５）　@process()メソッドの戻り値.isFailedResult()の値がtrueの場合
        if (l_processingResylt.isFailedResult())
        {
            log.debug("注文受付処理失敗である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00395,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "注文受付処理失敗である");   
        }   
        
        
        // ６）　@余力残高更新処理を行う。 
        // －拡張アカウントマネージャ.get補助口座()から補助口座オブジェクトを取得する。 
        // [get補助口座に渡すパラメタ] 
        // 　@口座ID：引数.投信注文単位.get口座ID()の戻り値 
        // 　@補助口座ID：引数.投信注文単位.get補助口座ID()の戻り値
        WEB3GentradeAccountManager l_genAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = 
                (WEB3GentradeSubAccount) l_genAccountManager.getSubAccount(
                        l_mutualFundOrderUnit.getAccountId(),
                        l_mutualFundOrderUnit.getSubAccountId()
                        );   
        }
        catch(NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        // －余力再計算サービスを取得し、余力再計算()をコールする。 
        // [余力再計算に渡すパラメタ] 
        // 　@補助口座：取得した補助口座オブジェクト
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
		l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);           
    }

    /**
     * (notify取消受付失敗)<BR>
     * 投信取消受付失敗処理をおこなう。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（投信取消受付）notify取消受付失敗」参照。 <BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位
     * @@param l_mutualFundAcceptConfirmInterceptor - 投信受付確定インタセプタ
     * @@roseuid 40C40EFA012E
     */
    public void notifyCancelAcceptFail(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor l_mutualFundAcceptConfirmInterceptor) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancelAcceptFail(MutualFundOrderUnit l_mutualFundOrderUnit,"
                + " WEB3MutualFundAcceptConfirmInterceptor "
                + " l_mutualFundAcceptConfirmInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFundOrderUnit == null
            || l_mutualFundAcceptConfirmInterceptor == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）　@拡張投信注文マネージャ.setThreadLocalPersistenceEventInterceptor()
        //  拡張投信注文マネージャの取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                   ProductTypeEnum.MUTUAL_FUND).getOrderManager();
                
        //setThreadLocalPersistenceEventInterceptor()をコール
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_mutualFundAcceptConfirmInterceptor);
            
        // ２）　@MutualFundMarketResponseReceiverCallbackServiceを取得する
        MarketAdapter l_marketAdapter =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getMarketAdapter();
        MutualFundMarketResponseReceiverCallbackService l_marketCallbackService =
            (MutualFundMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();
                
        // ３）　@DefaultCancelOrderRejectedMarketResponseMessage を生成
        DefaultCancelOrderRejectedMarketResponseMessage l_defaultMarketResponseMessage =
            new DefaultCancelOrderRejectedMarketResponseMessage(
                l_mutualFundOrderUnit.getOrderId());
                
        // ４）　@MutualFundMarketResponseReceiverCallbackService.process()をコール
        ProcessingResult l_processResult = l_marketCallbackService.process(
            l_defaultMarketResponseMessage);
        // ５）　@process()メソッドの戻り値.isFailedResult()の値がtrueの場合
        if (l_processResult.isFailedResult())
        {
            log.debug("注文受付処理失敗である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00395,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "注文受付処理失敗である");   
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
