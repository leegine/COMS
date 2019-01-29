head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.24.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXAccManagementServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者FX口座管理サービスインタセプタ(WEB3AdminFXAccManagementServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/26 王蘭芬(中訊) 新規作成
*/
package webbroker3.aio;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者FX口座管理サービスインタセプタ) <BR>
 * 管理者FX口座管理サービスインタセプタ
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AdminFXAccManagementServiceInterceptor implements Interceptor
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccManagementServiceInterceptor.class);
    
    /**
     * @@roseuid 41E7934B0242
     */
    public WEB3AdminFXAccManagementServiceInterceptor()
    {
    }

    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １） 取引カレンダコンテキストに内容をセットする。 <BR>
     * －サービスの引数[0]をリクエストデータオブジェクトにキャストする。 <BR>
     * －リクエストデータの内容と、OpLoginSecurityServiceの内容より <BR>
     * 取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 取引カレンダコンテキスト.証券会社コード =<BR>
     * OpLoginSecurityServiceより編集 <BR>
     * 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 取引カレンダコンテキスト.受付時間区分 = ”00：その他” <BR>
     * 取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 取引カレンダコンテキスト.注文受付商品 = ”23：為替保証金” <BR>
     * 取引カレンダコンテキスト.注文受付トランザクション = <BR>
     *      ”01：買付（新規建買）(サービス利用申込)(為替保証金口座開設)” <BR>
     * <BR>
     * －ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２） 受付日時、日付ロールをセットする。 <BR>
     * －取引時間管理.setTimestamp()をコールする。
     * 
     * @@param l_method - サービスメソッド
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 41BD392C0154
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
 
        try
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();  
                
            //管理者オブジェクトの内容より取引時間コンテキストのプロパティをセットする。
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
                
            // 取引カレンダコンテキスト.証券会社コード = 管理者.get証券会社コード()の戻り値
            l_context.setInstitutionCode(l_admin.getInstitutionCode());
            
            // 取引カレンダコンテキスト.部店コード = 管理者.get部店コード()の戻り値
            l_context.setBranchCode(l_admin.getBranchCode());
            
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.受付時間区分 = ”00：その他”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = ”23：為替保証金”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.EXCHANGE_GUARANTEE);
            
            //取引カレンダコンテキスト.注文受付トランザクション = ”01：買付（新規建買）(サービス利用申込)(為替保証金口座開設)” 
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()にて
            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);                
            
            //２）受付日時、日付ロールをセットする
            //WEB3BaseException
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
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
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * 
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 41BD392C0173
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
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
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * 
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 41BD392C0192
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
}@
