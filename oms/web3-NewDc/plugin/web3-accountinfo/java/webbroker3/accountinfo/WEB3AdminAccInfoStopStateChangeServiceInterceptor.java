head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoStopStateChangeServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報停止状況変更サービスインタセプタ(WEB3AdminAccInfoStopStateChangeServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 情野(SRA) 新規作成
*/

package webbroker3.accountinfo;

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
 * (管理者お客様情報停止状況変更サービスインタセプタ)<BR>
 * 管理者お客様情報停止状況変更サービスインタセプタ<BR>
 *
 * @@version 1.0
 */
public class WEB3AdminAccInfoStopStateChangeServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoStopStateChangeServiceInterceptor.class);

    /**
     * @@roseuid 
     */
    public WEB3AdminAccInfoStopStateChangeServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－ログイン情報から管理者オブジェクトを取得する。 <BR>
     * 　@－管理者オブジェクトの内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 管理者.get証券会社コード()の戻り値 <BR>
     * 　@取引カレンダコンテキスト.部店コード = 管理者.get部店コード()の戻り値 <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”29：SONAR連携（管理者）” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07:照会” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     * @@roseuid 
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

        try
        {
			WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

            //リクエストデータの内容と、
            //管理者オブジェクトの内容より取引時間コンテキストのプロパティをセットする。
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();

            //取引カレンダコンテキスト.証券会社コード = 管理者.get証券会社コード()の戻り値
            l_context.setInstitutionCode(l_administrator.getInstitutionCode());

            //取引カレンダコンテキスト.部店コード = 管理者.get部店コード()の戻り値
            l_context.setBranchCode(l_administrator.getBranchCode());

            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //取引カレンダコンテキスト.受付時間区分 = ”29：SONAR連携（管理者）”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SONAR_CON);

            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);

            //取引カレンダコンテキスト.注文受付トランザクション = ”07:照会”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

            //取引時間コンテキストをセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context
            );

            //受付日時、日付ロールをセットする
            WEB3GentradeTradingTimeManagement.setTimestamp();
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
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG <BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

        //取引カレンダコンテキスト、および時価のクリア処理。

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
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG <BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //取引カレンダコンテキスト、および時価のクリア処理。

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
