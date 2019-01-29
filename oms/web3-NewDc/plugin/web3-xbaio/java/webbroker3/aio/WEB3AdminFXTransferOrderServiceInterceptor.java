head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.33.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXTransferOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替注文アップロードサービスインタセプタ(WEB3AdminFXTransferOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/24 鄭徳懇(中訊) 新規作成
                 : 2006/03/07 鄭徳懇 (中訊) 仕様変更・モデル515
*/
package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
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
 * (FX振替注文アップロードサービスインタセプタ)<BR>
 * FX振替注文アップロードサービスインタセプタクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXTransferOrderServiceInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferOrderServiceInterceptor.class);
    
    /**
     * @@roseuid 43F49D5D01A5
     */
    public WEB3AdminFXTransferOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>                                                       
     * <BR>                                                                                              
     * 取引カレンダが利用するコンテキストを生成する。 <BR>                                               
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>                                           
     * <BR>                                                                                              
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>                                           
     * 　@?ログイン情報から管理者オブジェクトを取得する。 <BR>                                            
     * 　@?管理者オブジェクトの内容より取引時間コンテキストのプロパティをセットする。 <BR>                
     * <BR>                                                                                              
     * 　@取引カレンダコンテキスト.証券会社コード = 管理者.get証券会社コード()の戻り値 <BR>               
     * 　@取引カレンダコンテキスト.部店コード = 管理者.get部店コード()の戻り値 <BR>                       
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>                                       
     * 　@取引カレンダコンテキスト.受付時間区分 = ”17：アップロード（管理者）” <BR>                                     
     * 　@取引カレンダコンテキスト.商品コード = ”03：振替（入金）” <BR>                                 
     * 　@取引カレンダコンテキスト.注文受付商品 = ”23：為替保証金” <BR>                                 
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”09：振替”<BR>                            
     * <BR>                                                                                              
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 <BR> 
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>                                             
     * <BR>                                                                                              
     * ２）　@受付日時、日付ロールをセットする。 <BR>                                                     
     * 　@?取引時間管理.setTimestamp()をコールする。 <BR>                                                 
     * @@param l_method - (サービスメソッド)<BR>
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * @@return java.lang.Object
     * @@roseuid 43E03BB1024F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        //１）　@取引カレンダコンテキストに内容をセットする。 
        // 　@－ログイン情報から管理者オブジェクトを取得する。 
        // 　@－管理者オブジェクトの内容より取引時間コンテキストの
        // 　@プロパティをセットする。
        try
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            // 取引カレンダコンテキスト.証券会社コード = 管理者.get証券会社コード()の戻り値
            l_context.setInstitutionCode(l_admin.getInstitutionCode());
            
            // 取引カレンダコンテキスト.部店コード = 管理者.get部店コード()の戻り値
            l_context.setBranchCode(l_admin.getBranchCode());
            
            // 取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            // 取引カレンダコンテキスト.受付時間区分 = ”17：アップロード（管理者）”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD);
            
            // 取引カレンダコンテキスト.商品コード = ”03：振替（入金）”
            l_context.setProductCode(WEB3ProductCodeDef.TRANSFER_RECIEPT);
            
            // 取引カレンダコンテキスト.注文受付商品 = ”23：為替保証金”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.EXCHANGE_GUARANTEE);
            
            // 取引カレンダコンテキスト.注文受付トランザクション = ”09：振替”
            // (サービス利用申込)(為替保証金口座開設)”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.TRANSFER);
            
            //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて
            //取引時間コンテキストをセットする。
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            
            // ２）　@受付日時、日付ロールをセットする。 
            // 　@－取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
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
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 43E03BB1026E
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
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
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 43E03BA600F7
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
