head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者累投銘柄別売買停止サービスインタセプタ (WEB3AdminRuitoTradeStopInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito;

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
 * (管理者累投銘柄別売買停止サービスインタセプタ)<BR>
 * 管理者累投銘柄別売買停止サービスインタセプタクラス
 */
public class WEB3AdminRuitoTradeStopInterceptor implements Interceptor
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminRuitoTradeStopInterceptor.class);

    /**
     *サービスメソッド開始時にコールされる。<BR> 
     *<BR>
     *取引カレンダが利用するコンテキストを生成する。 <BR>
     *（xTradeカーネルよりサービス実行前に呼び出される）   <BR>
     *<BR>
     *１）　@取引カレンダコンテキストに内容をセットする。 <BR>
     *　@－OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。 <BR>
     *<BR>
     *　@　@　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
     *　@　@　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR> 
     *　@　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR> 
     *　@　@　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR> 
     *　@　@　@取引カレンダコンテキスト.受付時間区分 = 00：その他<BR> 
     *　@　@　@取引カレンダコンテキスト.注文受付商品 = ”08：累積投資”<BR> 
     *　@　@　@取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT（すべて）”<BR> 
     *<BR>
     *　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR> 
     *　@　@［setAttributeに渡すパラメタ］<BR> 
     *　@　@　@設定キー： 取引時間コンテキスト.TRADING_CALENDAR_TAG<BR> 
     *　@　@　@設定値： 取引時間コンテキスト<BR> 
     *<BR>
     *２）　@受付日時、日付ロールをセットする。<BR> 
     *　@－取引時間管理.setTimestamp()をコールする。<BR> 
     * @@param l_method - サービスメソッドオブジェクト 
     * @@param l_serviceParam - サービスメソッド引数 
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
         
        try
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();   
            // 証券会社コードを取得                                                                                                                   
            String l_strInstitutionCode = l_admin.getInstitutionCode();
            // 部店コードを取得                                                                                                                     
            String l_strBranchCode = l_admin.getBranchCode();

            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();
            //－取引時間コンテキストのプロパティをセットする。
    
            //取引カレンダコンテキスト.証券会社コード編集  
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //取引カレンダコンテキスト.部店コード編集
            l_context.setBranchCode(l_strBranchCode);
            
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.受付時間区分 = 00：その他 
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = ”08：累積投資”
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
            
            //取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT（すべて）” 
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.DEFAULT);
            
            log.debug("証券会社コード = "+l_context.getInstitutionCode());
            log.debug("部店コード = "+l_context.getBranchCode());
            log.debug("市場コード = "+l_context.getMarketCode());
            log.debug("銘柄コード = "+l_context.getProductCode());
            log.debug("受付時間区分 = "+l_context.getTradingTimeType());
            log.debug("注文受付商品 = "+l_context.getOrderAcceptProduct());
            log.debug("注文受付トランザクション = "+l_context.getOrderAcceptTransaction());
    
            //２）　@受付日時、日付ロールをセットする。
            // 取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //WEB3BaseException
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理        
        }        
        catch (WEB3BaseException l_ex)
        {
            log.debug("__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
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
     *サービスメソッド終了時にコールされる。<BR> 
     *<BR>
     *取引カレンダコンテキストクリア処理。<BR> 
     *<BR>
     *１）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR> 
     *<BR>
     *　@取引時間管理.TIMESTAMP_TAG<BR> 
     *　@取引時間管理.OFFSET_TAG<BR> 
     *　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR> 
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
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
     *サービスメソッドが例外をスローした場合にコールされる。<BR>
     *<BR>
     *取引カレンダコンテキストクリア処理。<BR> 
     *<BR>
     *１）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR> 
     *<BR>
     *　@取引時間管理.TIMESTAMP_TAG <BR>
     *　@取引時間管理.OFFSET_TAG<BR> 
     *　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR> 
     * @@param l_obj - onCallリターン値 
     * @@param l_throwable - 例外オブジェクト
     */
    public void onThrowable(Object l_context, Throwable l_throwable)
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
}
@
