head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替通知UnitService実装クラス(WEB3AioSecurityTransferNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 王蘭芬(中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.WEB3AioCashTransAcceptUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券振替通知UnitServiceImpl)<BR>
 * 証券振替通知UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)を指定する。
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferNotifyUnitServiceImpl implements WEB3AioSecurityTransferNotifyUnitService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferNotifyUnitServiceImpl.class);
    
    /**
     * @@roseuid 41B031750290
     */
    public WEB3AioSecurityTransferNotifyUnitServiceImpl() 
    {
     
    }
    
    /**
     * 証券振替通知処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券振替通知）証券振替通知」 参照
     * @@param l_aioOrderUnit - 注文単位オブジェクトの配列
     * @@param l_errorCode - エラーコード
     * @@param l_acceptNotifyDiv - 受付通知区分
     * @@throws WEB3BaseException
     * @@roseuid 4157934300CC
     */
    public void execute(AioOrderUnit[] l_aioOrderUnit, String l_errorCode, String l_acceptNotifyDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execute(AioOrderUnit[] l_aioOrderUnit, String l_errorCode, String l_acceptNotifyDiv)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 入出金受付更新インタセプタ(String)
        WEB3AioCashTransAcceptUpdateInterceptor l_updateInterceptor = 
            new WEB3AioCashTransAcceptUpdateInterceptor(l_errorCode);
        
        //1.2 setThreadLocalPersistenceEventInterceptor(AioOrderManagerPersistenceEventInterceptor)
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)GtlUtils.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //1.3 注文単位毎にLoop処理
        for (int i = 0; i < l_aioOrderUnit.length; i++)
        {
            long l_lngOrderId = l_aioOrderUnit[i].getOrderId();
            
            //1.3.1 (*1) 受付完了の場合
            if (WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(l_acceptNotifyDiv))
            {
                //1.3.1.1 DefaultNewOrderAcceptedMarketResponseMessage
                // [引数] 
                // 注文ＩＤ： 注文単位.注文ID
                DefaultNewOrderAcceptedMarketResponseMessage l_newOrderAcceptedMarketResponseMessage = 
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
                
                //1.3.1.2  process(MarketResponseMessage)
                // 受付完了状態に注文を更新する。
                //[引数] 
                // 受付結果： （生成した受付結果オブジェクト） 
                MarketAdapter l_marketAdapter =
                    GtlUtils.getTradingModule(
                        ProductTypeEnum.AIO).getMarketAdapter();
                AioMarketResponseReceiverCallbackService l_marketCallbackService = 
                    (AioMarketResponseReceiverCallbackService)
                        l_marketAdapter.getMarketResponseReceiverCallbackService();
                
                ProcessingResult l_processResult = 
                    l_marketCallbackService.process(l_newOrderAcceptedMarketResponseMessage);
                if (l_processResult.isFailedResult())
                {
                    log.debug("受付完了状態に注文を更新失敗である");
                    throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00395,
                       this.getClass().getName() + "." + STR_METHOD_NAME,
                       "受付完了状態に注文を更新失敗");   
                }
            }
            //1.3.2 (*2) 受付エラーの場合
            else if (WEB3AcceptDivDef.ERROR.equals(l_acceptNotifyDiv))
            {
                // 1.3.2.1 DefaultNewOrderRejectedMarketResponseMessage(long)
                DefaultNewOrderRejectedMarketResponseMessage l_newOrderRejectedMarketResponseMessage = 
                    new DefaultNewOrderRejectedMarketResponseMessage(l_lngOrderId);
                
                //1.3.1.2  process(MarketResponseMessage)
                // 受付エラー状態に注文を更新する。
                //[引数] 
                // 受付結果： （生成した受付結果（エラー）オブジェクト）
                MarketAdapter l_marketAdapter =
                    GtlUtils.getTradingModule(
                        ProductTypeEnum.AIO).getMarketAdapter();
                AioMarketResponseReceiverCallbackService l_marketCallbackService = 
                    (AioMarketResponseReceiverCallbackService)
                        l_marketAdapter.getMarketResponseReceiverCallbackService();
                
                ProcessingResult l_processResult = 
                    l_marketCallbackService.process(l_newOrderRejectedMarketResponseMessage);
                if (l_processResult.isFailedResult())
                {
                    log.debug("受付エラー状態に注文を更新失敗である");
                    throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00395,
                       this.getClass().getName() + "." + STR_METHOD_NAME,
                       "受付エラー状態に注文を更新失敗");   
                }
            }
        }
        
        //1.4  (*3) 受付エラーの場合
        if (WEB3AcceptDivDef.ERROR.equals(l_acceptNotifyDiv))
        {
            AccountManager l_accountManager = GtlUtils.getAccountManager();

            try 
            {
                // 1.4.1 get補助口座(, )
                // [引数] 
                // 口座ID： 引数.注文単位[0].口座ID 
                // 補助口座ID： 引数.注文単位[0].補助口座ID
                WEB3GentradeSubAccount l_subAccount = 
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                        l_aioOrderUnit[0].getAccountId(), 
                        l_aioOrderUnit[0].getSubAccountId());
                
                // 1.4.2 余力再計算(補助口座 : 補助口座)
				WEB3TPTradingPowerReCalcService l_tradingPowerService = 
                    (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tradingPowerService.reCalcTradingPower(l_subAccount);
            } 
            catch (NotFoundException l_ex) 
            {
                log.error("get補助口座失敗である", l_ex);
                throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);   
            }
        }
    }
}
@
