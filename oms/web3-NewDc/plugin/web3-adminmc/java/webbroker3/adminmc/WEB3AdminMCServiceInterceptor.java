head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メニュー制御サービスインタセプタ(WEB3AdminMCServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 屈陽 (中訊) 新規作成
*/

package webbroker3.adminmc;

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
 * (メニュー制御サービスインタセプタ)<BR>
 * メニュー制御サービスインタセプタ<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0 
 */
public class WEB3AdminMCServiceInterceptor implements Interceptor 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCServiceInterceptor.class);   
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”00：その他” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07:照会” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * @@param l_method - (arg0)<BR>
     * @@param l_serviceParam - (arg1)<BR>
     * @@return Object
     * @@roseuid 417718320232
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        String l_strMethodName = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(l_strMethodName);
        
        //１）取引カレンダコンテキストに内容をセットする。
        
        //－リクエストデータの内容と、OpLoginSecurityServiceの内容より
        //取引時間コンテキストのプロパティをセットする。
        
        //取引カレンダコンテキストを生成する
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
            
        try
        {
            //administrator
            WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
            
            //a> 証券会社コード
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //b> 部店コード            
            String l_strBranchCode = l_administrator.getBranchCode();
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            
            //c> 取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //d> 取引カレンダコンテキスト.受付時間区分 = ”00：その他” 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
            
            //e> 取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //f> 取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
            
            //g> 取引カレンダコンテキスト.注文受付トランザクション = ”07:照会”        
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            
            //ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
                
            //２）受付日時、日付ロールをセットする。 
            //取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();  
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in setTimestamp__", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);            
        }
       
        log.exiting(l_strMethodName);        
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG <BR>
     * @@param l_context - (arg0)<BR>
     * @@param l_returnValue - (arg1)<BR>
     * @@roseuid 417718320251
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        String l_strMethodName = "onReturn(Object l_context, Object l_returnValue)";
        log.entering(l_strMethodName);
        
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
                   
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
        
        log.exiting(l_strMethodName);     
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG <BR>
     * @@param l_obj - (arg0)<BR>
     * @@param l_throwable - (arg1)<BR>
     * @@roseuid 417718320261
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        String l_strMethodName = "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(l_strMethodName);
        
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
                   
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
        
        log.exiting(l_strMethodName);      
    }
}
@
