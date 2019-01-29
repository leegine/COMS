head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToEquityOrderReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・株式注文照会サービスインタセプタ(WEB3AdminToEquityOrderReferenceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06　@呉艶飛(中訊) 新規作成
*/

package webbroker3.admintriggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.admintriggerorder.define.WEB3AdminToOrderAccProductDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・株式注文照会サービスインタセプタ)<BR>
 * トリガー注文管理者・株式注文照会サービスインタセプタ<BR>
 * 
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3AdminToEquityOrderReferenceServiceInterceptor implements Interceptor
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToEquityOrderReferenceServiceInterceptor.class);

    
    /**
     * コンストラクタ<BR>
     */
    public WEB3AdminToEquityOrderReferenceServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR> 
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR> 
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－OpLoginSecurityServiceの内容より取引時間コンテキストの<BR> 
     * 　@　@　@プロパティをセットする。<BR> 
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR> 
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR> 
     * 　@取引カレンダコンテキスト.市場コード = null<BR> 
     * 　@取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”<BR> 
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR> 
     * 　@取引カレンダコンテキスト.注文受付商品 = ”00：DEFAULT”<BR> 
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR> 
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     * 　@　@　@取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        try
        {
            //管理者の場合は管理者オブジェクトから取得する
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //取引カレンダが利用するコンテキストを生成する。
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            //１）　@取引カレンダコンテキストに内容をセットする。
            //取引カレンダコンテキスト.証券会社コード = 証券会社コード
            l_context.setInstitutionCode(l_admin.getInstitutionCode());
            //取引カレンダコンテキスト.部店コード = 部店コード
            l_context.setBranchCode(l_admin.getBranchCode());
            //市場コード = null
            l_context.setMarketCode(null);
            //取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.注文受付商品 = ”00：DEFAULT”
            l_context.setOrderAcceptProduct(WEB3AdminToOrderAccProductDef.DEFAULT);
            //取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
         
            //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて
            //  取引時間コンテキストをセットする。
            //  設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);        
            //受付日時、日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }    
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
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
     * サービスメソッド終了時にコールされる。<BR> 
     * 取引カレンダコンテキストクリア処理。<BR> 
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR> 
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR> 
     * 取引時間管理.OFFSET_TAG<BR> 
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
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
     * サービスメソッド例外時にコールされる。<BR> 
     * 取引カレンダコンテキストクリア処理。<BR> 
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR> 
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR> 
     * 取引時間管理.OFFSET_TAG<BR> 
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
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
