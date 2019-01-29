head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.34.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashoutCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消サービスインタセプタ(WEB3AioCashoutCancelServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 黄建 (中訊) 新規作成
                   2004/10/25 屈陽 (中訊) レビュー
                   2004/12/09 周勇 (中訊) 残対応
*/

package webbroker3.aio;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import webbroker3.aio.message.WEB3AioCashoutCancelCompleteRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (出金取消サービスインタセプタ)<BR>
 * (出金取消サービスインタセプタクラス)<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0   
 */

public class WEB3AioCashoutCancelServiceInterceptor implements Interceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutCancelServiceInterceptor.class);
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトに<BR>
     * キャストする。 <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     * OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     * OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”16：出金” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”14：入出金” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     * ”08：出金” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）　@リクエストデータの型が「出金取消完了リクエスト」の場合のみ、口座をロックする。<BR> 
　@   * －拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)
     * をコールする。<BR> 
     * ※引数はOpLoginSecurityServiceより編集。<BR> 
     * @@param l_method - サービスメソッド
     * @@param l_serviceParams - サービスメソッド引数
     * @@return Object
     * @@roseuid 40F73E79007E
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
       
        // １）　@取引カレンダコンテキストに内容をセットする。
        
        // 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より
        // 取引時間コンテキストのプロパティをセットする。 
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        long l_lngAccountId = l_opLoginSec.getAccountId();
        log.debug("=============AccountID==============" + l_lngAccountId);
        
        //===========================FinApp==========================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //アカウントマネージャ取得する
        AccountManager l_accMgr = l_finApp.getAccountManager();     
        
        try
        {
            //顧客を取得する
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
        
            //－取引カレンダコンテキストにの取得
            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();
      
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            //証券会社コードを取得する
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();
            l_context.setInstitutionCode(l_strInstitutionCode);    
        
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            //部店コードを取得する
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            l_context.setBranchCode(l_strBranchCode);
        
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
            //取引カレンダコンテキスト.受付時間区分 = ”16：出金”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.PAYMENT);
        
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
            //取引カレンダコンテキスト.注文受付商品 = ”14：入出金” 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.PAYMENT);
        
            //取引カレンダコンテキスト.注文受付トランザクション =  ”08：出金 
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.PAYMENT);
        
            //－ThreadLocalSystemAttributesRegistry.setAttribute()
            // にて取引時間コンテキストをセットする。 
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                  WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                  l_context);
            
            // ２）　@受付日時、日付ロールをセットする。
            //－取引時間管理.setTimestamp()をコールする。 
            WEB3GentradeTradingTimeManagement.setTimestamp();   
            
            //=========remain zhou-yong NO.1 begin ==============
            
            //３）　@リクエストデータの型が「出金取消完了リクエスト」の場合のみ、口座をロックする。 
            //－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
            //※引数はOpLoginSecurityServiceより編集。 
            
            //リクエストデータオブジェクトにキャストするを取得する
            Object l_request = l_serviceParams[0];
            
            if (l_request instanceof WEB3AioCashoutCancelCompleteRequest)
            {
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager) l_accMgr;
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_mainAccount.getAccountCode());
            }
            
            //=========remain zhou-yong NO.1 end ==============            
            
            //取引カレンダコンテキストに内容をセットする明細
       }
        catch (NotFoundException l_ex)
        {
            log.error("__an unexpected error__ when get MainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 410098230119
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME =
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //取引カレンダコンテキスト.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 41009823011C
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //１）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //取引カレンダコンテキスト.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
            
        log.exiting(STR_METHOD_NAME);   
    }
}
@
