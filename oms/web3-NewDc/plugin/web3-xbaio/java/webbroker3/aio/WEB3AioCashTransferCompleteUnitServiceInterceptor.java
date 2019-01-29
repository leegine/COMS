head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransferCompleteUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金完了UnitServiceインタセプタクラス(WEB3AioCashTransferCompleteUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 周勇 (中訊) 新規作成 
                   2004/10/22 黄建 (中訊) レビュー   
                   2004/12/09 周勇 (中訊) 残対応                   
*/
package webbroker3.aio;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
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
 * (入出金完了UnitServiceインタセプタ)<BR>
 * 入出金完了UnitServiceインタセプタクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashTransferCompleteUnitServiceInterceptor implements Interceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransferCompleteUnitServiceInterceptor.class);
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－引数.サービスの引数[0]を注文単位オブジェクトにキャストする。 <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの<BR>
     * 内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     *   注文単位.証券会社部店ＩＤに該当する部店の証券会社コード <BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     *   注文単位.証券会社部店ＩＤに該当する部店の部店コード <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = (*1)受付時間区分<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”14：入出金” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     * (*2)注文受付トランザクション <BR>
     * <BR>
     *   (*1)受付時間区分の取得方法@<BR>
     *     注文単位.注文種別 = 1001：出金注文 の場合、”16：出金”<BR>
     *     注文単位.注文種別 = 1002：入金注文 の場合、”14：入金”<BR>
     * <BR>
     *   (*2)注文受付トランザクションの取得方法@<BR>
     *     注文単位.注文種別 = 1001：出金注文 の場合、”08：出金”<BR>
     *     注文単位.注文種別 = 1002：入金注文 の場合、”10：入金”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）　@口座をロックする。 <BR>
　@   * －拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)<BR>
     * をコールする。 <BR>
     * ※引数は注文単位より編集。<BR>
     * <BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 41416CF0007F
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("l_serviceParams is null or l_serviceParams length is 0");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@取引カレンダコンテキストに内容をセットする。 
        //　@－引数.サービスの引数[0]を注文単位オブジェクトにキャストする。 
        //　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。 
        AioOrderUnit l_aioOrderUnit =
            (AioOrderUnit) l_serviceParams[0];
        log.debug("l_serviceParams[0].getClass().getName() = " + l_serviceParams[0].getClass().getName());
        log.debug("l_aioOrderUnit.getClass().getName() = " + l_aioOrderUnit.getClass().getName());
        
        long l_lngAccountId = l_aioOrderUnit.getAccountId();  
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            
            //取引カレンダコンテキスト.証券会社コード = 注文単位.証券会社部店ＩＤに該当する部店の証券会社コード  
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //取引カレンダコンテキスト.部店コード = 注文単位.証券会社部店ＩＤに該当する部店の部店コード
            l_context.setBranchCode(l_strBranchCode);
            
            //　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.受付時間区分 = (*1)受付時間区分 
            //(*1)受付時間区分の取得方法@ 
            //注文単位.注文種別 = 1001：出金注文 の場合、”16：出金” 
            //注文単位.注文種別 = 1002：入金注文 の場合、”14：入金” 
            if(OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnit.getOrderType()))
            {
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.PAYMENT);
            }
            else if(OrderTypeEnum.CASH_IN.equals(l_aioOrderUnit.getOrderType()))
            {
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.RECIEPT);
            }
            
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = ”14：入出金”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.PAYMENT);
            
            //取引カレンダコンテキスト.注文受付トランザクション = (*2)注文受付トランザクション
            //(*2)注文受付トランザクションの取得方法@ 
            //注文単位.注文種別 = 1001：出金注文 の場合、”08：出金” 
            //注文単位.注文種別 = 1002：入金注文 の場合、”10：入金
            if(OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.PAYMENT);
            }
            else if(OrderTypeEnum.CASH_IN.equals(l_aioOrderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CASH_IN);
            }
            
           //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 
           // 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            // ２）　@受付日時、日付ロールをセットする。 
               // 　@－取引時間管理.setTimestamp()をコールする。 
               WEB3GentradeTradingTimeManagement.setTimestamp();
               
            //============remain zhou-yong NO.1 begin ============   
               
            //３）　@口座をロックする。 
            //  　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
            //   ※引数は注文単位より編集。
               WEB3GentradeAccountManager l_accountManager =
                   (WEB3GentradeAccountManager) l_accMgr;

               l_accountManager.lockAccount(
                   l_strInstitutionCode,
                   l_strBranchCode,
                   l_mainAccount.getAccountCode());
               
            //============remain zhou-yong NO.1 begin ============
        }       
        catch(NotFoundException l_ex)
        {
            log.error("no find AccountId", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()", l_ex);
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
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 41416CF0008F
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
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
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 41416CF000AE
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
    
        log.exiting(STR_METHOD_NAME);
    }
}
@
