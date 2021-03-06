head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.28.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSLCashOutStopReleaseServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保ローン出金停止解除サービスインターセプタ(WEB3AdminAioSLCashOutStopReleaseServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 金傑（中訊）新規作成 モデルNo.764
Revision History : 2007/09/18 金傑（中訊）モデルNo.768
Revision History : 2007/10/10 金傑（中訊）モデルNo.799
Revision History : 2007/10/10 金傑（中訊）モデルNo.803
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
 * (担保ローン出金停止解除サービスインターセプタ)<BR>
 * 担保ローン出金停止解除サービスインターセプタクラス<BR>
 *
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioSLCashOutStopReleaseServiceInterceptor implements Interceptor
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLCashOutStopReleaseServiceInterceptor.class);

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     *（xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@−サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@−リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間<BR>
     * 　@コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”00：その他”<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”22：顧客サービス”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT（すべて）”<BR>
     * <BR>
     * 　@−ThreadLocalSystemAttributesRegistry.setAttribute( )に<BR>
     * 　@て取引時間コンテキストをセットする。<BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@−取引時間管理.setTimestamp()をコールする。<BR>
     *
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッド
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * サービスメソッド引数
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        //１）　@取引カレンダコンテキストに内容をセットする。
        WEB3Administrator l_admin = null;
        try
        {
            l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //管理者オブジェクトの内容より取引時間コンテキストのプロパティをセットする。
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_admin.getInstitutionCode());
            
            // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_admin.getBranchCode());
            
            // 取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            // 取引カレンダコンテキスト.受付時間区分 = ”00：その他
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
            
            // 取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            // 取引カレンダコンテキスト.注文受付商品 = ”22：顧客サービス”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
            
            // 取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT（すべて）”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            
            //−ThreadLocalSystemAttributesRegistry.setAttribute( )にて
            //取引時間コンテキストをセットする。
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            
            // ２）　@受付日時、日付ロールをセットする。
            // 取引時間管理
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
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
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     *
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG<BR>
     *
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        log.exiting(STR_METHOD_NAME);
    }

}
@
