head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashTransferAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金受付UnitService実装クラス(WEB3AioCashTransferAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 周勇 (中訊) 新規作成  
                   2004/10/26 黄建 (中訊) レビュー    
                   2004/12/09 周勇 (中訊) 残対応
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.aio.WEB3AioCashTransAcceptUpdateInterceptor;
import webbroker3.aio.service.delegate.WEB3AioCashTransferAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (入出金受付UnitServiceImpl)<BR>
 * 入出金受付UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * を指定する。<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashTransferAcceptUnitServiceImpl implements WEB3AioCashTransferAcceptUnitService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransferAcceptUnitServiceImpl.class);
    
    /**
     * 入出金受付DB更新処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金受付）入出金受付DB更新」  参照<BR>
     * @@param c - (注文単位オブジェクト)
     * @@param l_strErrorCode - (エラーコード)
     * @@param l_strAcceptNoticeDiv - (受付通知区分)
     * @@roseuid 40FF5EDE0196
     */
    public void execute(AioOrderUnit l_orderUnit, String l_strErrorCode, String l_strAcceptNoticeDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "executeAioOrderUnit l_orderUnit, " +
                "String l_strErrorCode," +
                " String l_strAcceptNoticeDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)インタセプタを生成する。 
        //[コンストラクタの引数]
        //エラーコード：　@引数.エラーコード
        WEB3AioCashTransAcceptUpdateInterceptor l_aioCashTransAcceptUpdateInterceptor =
            new WEB3AioCashTransAcceptUpdateInterceptor(l_strErrorCode);
        
        // 1.2)インタセプタをセットする。
        //[引数] 
        //入出金受付更新インタセプタ：　@（生成した入出金受付更新インタセプタ）
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AioOrderManager l_aioOrderManager =
            (AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_aioCashTransAcceptUpdateInterceptor);
        
        // 1.3)受付完了の場合
        //(*1) 引数.受付通知区分 = "1"（受付完了）の場合
        if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(l_strAcceptNoticeDiv))
        {
            // 1.3.1)受付結果（受付成功）オブジェクトを生成する。
            //[引数] 
            //注文ＩＤ： 引数.注文ID
            DefaultNewOrderAcceptedMarketResponseMessage l_newOrderMessage = 
                new DefaultNewOrderAcceptedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            
            // 1.3.2)受付完了を注文に更新する。
            //[引数] 
            //受付結果： （生成した受付結果オブジェクト)
            MarketAdapter l_marketAdapter = l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
            
            AioMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
            
            l_marketResponseReceiverCallbackService.process(l_newOrderMessage);
        }
        
        // 1.4)受付エラーの場合
        //(*2) 引数.受付通知区分 = "2"（受付エラー）の場合
        if(WEB3AcceptDivDef.ERROR.equals(l_strAcceptNoticeDiv))
        {
            // 1.4.1)受付結果（受付エラー）オブジェクトを生成する。
            //[引数] 
            //注文ＩＤ： 引数.注文ID 
            DefaultNewOrderRejectedMarketResponseMessage l_defaultResponseMessage = 
                new DefaultNewOrderRejectedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            
            // 1.4.2)受付エラーを注文に更新する。
            //[引数] 
            //受付結果： （生成した受付結果（エラー）オブジェクト）
            MarketAdapter l_marketAdapter = l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
            
            AioMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
            
            l_marketResponseReceiverCallbackService.process(l_defaultResponseMessage);
            
            
            //===========remain zhou-yong NO.1 begin=========
            
            //余力再計算(補助口座 : 補助口座)
            //アイテムの定義
            //余力の更新をする。
            //[引数] 
            //補助口座： 注文.補助口座IDから取得した補助口座オブジェクト
			WEB3TPTradingPowerReCalcService l_service =
                (WEB3TPTradingPowerReCalcService) Services.getService(
			WEB3TPTradingPowerReCalcService.class);
            
            WEB3GentradeAccountManager l_accManage = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                
            WEB3GentradeSubAccount l_gentradeSubAccount = null;
            try
            {
                l_gentradeSubAccount = (WEB3GentradeSubAccount)l_accManage.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {                
                log.error("___NotFoundException___" , l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            l_service.reCalcTradingPower(l_gentradeSubAccount);
            
            //===========remain zhou-yong NO.1 end=========            

        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
