head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInformAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 各種連絡受付UnitServiceインタセプタ(WEB3AccOpenInformAcceptUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/05/28 柴双紅 (中訊) 新規作成 モデル No.123
*/

package webbroker3.accountopen;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (各種連絡受付UnitServiceインタセプタ)<BR>
 * 各種連絡受付1件サービスインタセプタ<BR>
 * <BR>
 * 各種連絡受付1件サービスにpluginする。<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AccOpenInformAcceptUnitServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AccOpenInformAcceptUnitServiceInterceptor.class);

    /**
     * (onCall)<BR>
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットす。<BR>
     * 　@－サービスの引数[0]を口座開設伝票登録受付キューParamsオブジェクトにキャストする。<BR>
     * 　@－口座開設伝票登録受付キューParamsオブジェクトの内容より、<BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 口座開設伝票登録の同名項目<BR>
     * 　@取引カレンダコンテキスト.部店コード = 口座開設伝票登録の同名項目<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”22：口座開設”<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07:照会”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コンテキストをセットする。<BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method
     * @@param l_objects
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_objParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_objParams == null
                || l_objParams.length == 0
                || !(l_objParams[0] instanceof HostAccOpenAcceptParams))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータ値不正。");
            }

            //サービスの引数[0]を口座開設伝票登録受付キューParamsオブジェクトにキャストする
            HostAccOpenAcceptParams l_hostAcceptParams =
                (HostAccOpenAcceptParams)l_objParams[0];

            //取引カレンダが利用するコンテキストを生成する
            WEB3GentradeTradingClendarContext l_tradingClendarContext =
                new WEB3GentradeTradingClendarContext();

            //取引カレンダコンテキスト.証券会社コード = 口座開設伝票登録の同名項目
            l_tradingClendarContext.setInstitutionCode(l_hostAcceptParams.getInstitutionCode());

            //取引カレンダコンテキスト.部店コード = 口座開設伝票登録の同名項目
            l_tradingClendarContext.setBranchCode(l_hostAcceptParams.getBranchCode());

            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_tradingClendarContext.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //取引カレンダコンテキスト.受付時間区分 = ”22：口座開設”
            l_tradingClendarContext.setTradingTimeType(WEB3TradingTimeTypeDef.ACCOUNT_OPEN);

            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_tradingClendarContext.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス”
            l_tradingClendarContext.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);

            //取引カレンダコンテキスト.注文受付トランザクション = ”07:照会”
            l_tradingClendarContext.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

            //　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_tradingClendarContext);

            //受付日時、日付ロールをセットする。
            //　@－取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();

            log.exiting(STR_METHOD_NAME);
            return l_tradingClendarContext;
        }
        catch (WEB3BaseException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_exc.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (onReturn)<BR>
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * <BR>
     * @@param l_objContext
     * @@param l_objReturn
     */
    public void onReturn(Object l_objContext, Object l_objReturn)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

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
     * (onThrowable)<BR>
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * <BR>
     * @@param l_object
     * @@param l_throwable
     */
    public void onThrowable(Object l_object, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

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
