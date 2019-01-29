head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverMainServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文繰越メインサービスインタセプタ(WEB3IfoOrderCarryOverMainServiceImplInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/21 趙林鵬(中訊) 新規作成 モデルNo.669
Revision History : 2007/06/28 趙林鵬(中訊) モデルNo.758
Revision History : 2007/07/03 趙林鵬(中訊) モデルNo.772
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.message.WEB3IfoOrderCarryOverMainRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP注文繰越メインサービスインタセプタ)<BR>
 * 先物OP注文繰越メインサービスインタセプタ。<BR>
 * <BR>
 * 先物OP注文繰越メインサービスインタセプタに対して設定する<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3IfoOrderCarryOverMainServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。 <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoOrderCarryOverMainServiceInterceptor.class);

    /**
     * 先物OP注文繰越メインサービスインタセプタ<BR>
     */
    public WEB3IfoOrderCarryOverMainServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。  <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。  <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）  <BR>
     * <BR>
     * １）　@メソッド名≠"execute"の場合は、引数のサービスメソッドを返却する。（return;）<BR>
     * <BR>
     * ２）　@サービスの引数[0]を先物OP注文繰越メインリクエストにキャストする。 <BR>
     * <BR>
     * ３）　@取引カレンダコンテキストに内容をセットする。  <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 先物OP注文繰越<BR>
     * 　@　@メインリクエスト.証券会社コード  <BR>
     * 　@取引カレンダコンテキスト.部店コード = 証券会社コードに該当する<BR>
     * 　@　@証券会社.getBranches()の戻り値の <BR>
     * 　@　@0番目の要素に該当する部店.部店コード <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”  <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”  <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = null (*1) <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null (*1) <BR>
     * <BR>
     * 　@(*1)  注文受付ステイタス関連項目は使用しないため。<BR>
     * 　@　@　@　@（取引時間関連の項目のみセット）<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * 　@にて取引時間コンテキストをセットする。  <BR>
     * 　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * ４）　@RLSへの非同期通知フラグをセットする。<BR>
     * 　@　@※注文繰越は、RLSへの通知を常に非同期で行う。<BR>
     * <BR>
     * 　@"注文繰越RLS非同期通知"をLocalThreadにセットする。<BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     * 　@　@　@WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY,<BR>
     * 　@　@　@BooleanEnum.TRUE ) <BR>
     * <BR>
     * ５）　@日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッド<BR>
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //メソッド名≠"execute"の場合は、引数のサービスメソッドを返却する。（return;）
        if (!"execute".equals(l_method.getName()))
        {
            log.exiting(STR_METHOD_NAME);
            return l_method;
        }

        //サービスの引数[0]を先物OP注文繰越メインリクエストにキャストする
        String l_strInstitutionCode = null;
        if (l_serviceParam[0] instanceof WEB3IfoOrderCarryOverMainRequest)
        {
            WEB3IfoOrderCarryOverMainRequest l_request =
                (WEB3IfoOrderCarryOverMainRequest)l_serviceParam[0];
            l_strInstitutionCode = l_request.institutionCode;
        }
        else
        {
            log.error("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        Branch[] l_branchs = null;
        try
        {
            Institution l_institution =
                l_finApp.getAccountManager().getInstitution(l_strInstitutionCode);

            l_branchs = l_institution.getBranches();

        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //取引カレンダコンテキストに内容をセットする。
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        //取引カレンダコンテキスト.証券会社コード = 先物OP注文繰越メインリクエスト.証券会社コード
        l_context.setInstitutionCode(l_strInstitutionCode);

        //取引カレンダコンテキスト.部店コード = 証券会社コードに該当する証券会社.getBranches()の戻り値の
        //0番目の要素に該当する部店.部店コード
        l_context.setBranchCode(l_branchs[0].getBranchCode());

        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

        //取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

        //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        //取引カレンダコンテキスト.注文受付商品 = null (*1)
        l_context.setOrderAcceptProduct(null);

        //取引カレンダコンテキスト.注文受付トランザクション = null (*1)
        l_context.setOrderAcceptTransaction(null);

        //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。
        //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        //４）　@RLSへの非同期通知フラグをセットする。
        //※注文繰越は、RLSへの通知を常に非同期で行う。
        //"注文繰越RLS非同期通知"をLocalThreadにセットする。
        //－ThreadLocalSystemAttributesRegistry.setAttribute(
        //WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY,
        //BooleanEnum.TRUE )
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY,
            BooleanEnum.TRUE);

        try
        {
            //日付ロールをセットする。
            //－取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        return l_context;
    }

    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * 引数.onCallリターン値≠nullの場合、何もしないで処理を終了する。（return;）<BR>
     * 以外、ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param  l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object) ";
        log.entering(STR_METHOD_NAME);

        //引数.onCallリターン値≠nullの場合、何もしないで処理を終了する。（return;）
        if(l_context != null)
        {
            return;
        }

        //以外、ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * 引数.onCallリターン値≠nullの場合、何もしないで処理を終了する。（return;）<BR>
     * 以外、ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY<BR>
     * @@param  l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param  l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);

        //引数.onCallリターン値≠nullの場合、何もしないで処理を終了する。（return;）
        if(l_obj != null)
        {
            return;
        }

        //以外、ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
