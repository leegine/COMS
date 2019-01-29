head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替受付UnitServiceImpl(WEB3AccTransChangeAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/22 王蘭芬(中訊) レビュー  
                   2004/12/09 周勇 (中訊) 残対応                                     
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.aio.WEB3AioCashTransAcceptUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (振替受付UnitServiceImpl)<BR>
 * 振替受付UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * を指定する。<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeAcceptUnitServiceImpl
    implements WEB3AccTransChangeAcceptUnitService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeAcceptUnitServiceImpl.class);

    /**
     * 振替請求受付DB更新処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（振替請求受付）振替請求受付DB更新」  参照<BR>
     * @@param l_orderUnit - (注文単位オブジェクト)<BR>
     * @@param l_strErrorCode - (エラーコード)<BR>
     * @@param l_strAcceptDiv - (受付通知区分)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413C20F2037B
     */
    public void execute(
        AioOrderUnit l_orderUnit,
        String l_strErrorCode,
        String l_strAcceptDiv)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(" 
            + "AioOrderUnit l_orderUnit,"
            + "String l_strErrorCode," 
            + "String l_strAcceptDiv)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1) インタセプタを生成する。 
        // [コンストラクタの引数] 
        // エラーコード：　@引数.エラーコード 
        WEB3AioCashTransAcceptUpdateInterceptor l_updateInterceptor = 
            new WEB3AioCashTransAcceptUpdateInterceptor(l_strErrorCode);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
                    
        // 2) インタセプタをセットする。 
        // [引数] 
        // 入出金受付更新インタセプタ：　@（生成した入出金受付更新インタセプタ）
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        MarketAdapter l_marketAdapter = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
        
        AioMarketResponseReceiverCallbackService l_marketService =
            (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
         
        // 3) 受付完了の場合
        if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(l_strAcceptDiv))
        {
            // 3- 1 ) 受付結果（受付成功）オブジェクトを生成する。 
            // [引数] 
            // 注文ＩＤ： 引数.注文単位.注文ID
            DefaultNewOrderAcceptedMarketResponseMessage l_newOrderMessage = 
                new DefaultNewOrderAcceptedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            
            // 3 - 2) 受付完了を注文に更新する。 
            // [引数] 
            // 受付結果： （生成した受付結果オブジェクト）                
            l_marketService.process(l_newOrderMessage);
        }
        else
        {
            // 4) 受付エラーの場合
            if(WEB3AcceptDivDef.ERROR.equals(l_strAcceptDiv))
            {
                // 4- 1 ) 受付結果（受付エラー）オブジェクトを生成する。 
                // [引数] 
                // 注文ＩＤ： 引数.注文単位.注文ID
                DefaultNewOrderRejectedMarketResponseMessage l_newOrderMessage = 
                    new DefaultNewOrderRejectedMarketResponseMessage(
                        l_orderUnit.getOrderId());
            
                // 4 - 2) 受付エラーを注文に更新する。 
                // [引数] 
                // 受付結果： （生成した受付結果オブジェクト）                
                l_marketService.process(l_newOrderMessage);
                
                // 4 - 3) 補助口座オブジェクトを取得する。 
                // ［引数］ 
                // 注文単位： 引数.注文単位 
                SubAccount l_subAccount = this.getSubAccount(l_orderUnit);
                
                if(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(
                    l_subAccount.getSubAccountType()))
                {
                    //=========remain zhou-yong NO.1 begin ===========
                    
                    // 1.4.4) 余力再計算(補助口座 : 補助口座)
                    //アイテムの定義
                    ///余力の更新を行う。
                    //[引数] 
                    //補助口座： get補助口座()の戻り値 
					WEB3TPTradingPowerReCalcService l_service =
                        (WEB3TPTradingPowerReCalcService) Services.getService(
					WEB3TPTradingPowerReCalcService.class);
                    
                    WEB3GentradeSubAccount l_gentradeSubAccount = 
                        (WEB3GentradeSubAccount)l_subAccount;
                    
                    l_service.reCalcTradingPower(l_gentradeSubAccount);

                    //=========remain zhou-yong NO.1 end ===========
                    
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME); 
    }

    /**
     * (get補助口座)<BR>
     * 注文単位から、補助口座オブジェクトを取得する。<BR>
     * <BR>
     * １）補助口座オブジェクトを取得する。<BR>
     * <BR>
     *    拡張アカウントマネージャ.get補助口座()<BR>
     * <BR>
     *    ［引数］<BR>
     *    口座ID： 引数.注文単位.口座ID<BR>
     *    補助口座ID： 引数.注文単位.補助口座ID<BR>
     * <BR>
     * ２）取得した補助口座オブジェクトを返却する。<BR>
     * @@param l_orderUnit - (注文単位オブジェクト)
     * @@return SubAccount
     * @@throws WEB3BaseException
     * @@roseuid 413D12B9014F
     */
    protected SubAccount getSubAccount(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getSubAccount(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）補助口座オブジェクトを取得する。 
        // 拡張アカウントマネージャ.get補助口座() 
        // ［引数］ 
        // 口座ID： 引数.注文単位.口座ID 
        // 補助口座ID： 引数.注文単位.補助口座ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accManage = l_finApp.getAccountManager();
        
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accManage.getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__an unexpected error__when "
                     + " getSubAccount",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        // ２）取得した補助口座オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
}
@
