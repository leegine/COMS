head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認一件サービスインタセプタ(WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/17 張騰宇(中訊) 新規作成 仕様変更モデルNo.180
*/
package webbroker3.eqtypeadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・強制決済仮注文承認／非承認一件サービスインタセプタ)<BR>
 * 管理者・強制決済仮注文承認／非承認一件サービスインタセプタクラス <BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor implements Interceptor
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor.class);

    /**
     * コンストラクタ<BR>
     */
    public WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor()
    {

    }

    /**
     * 取引カレンダが利用するコンテキストを生成する。  <BR>
     * （xTradeカーネルより、サービスメソッド開始時にコールされる）  <BR>
     * <BR>
     * １）　@強制決済注文Rowオブジェクトを取得する。 <BR>
     * 　@　@サービスの引数[0]を強制決済注文Rowにキャストする。 <BR>
     * <BR>
     * ２）　@管理者オブジェクトを取得する。 <BR>
     * 　@　@サービスの引数[1]を管理者にキャストする。 <BR>
     * <BR>
     * ３）　@取引カレンダコンテキストに内容をセットする。  <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 管理者.get証券会社コード()の戻り値 <BR>
     * 　@取引カレンダコンテキスト.部店コード = 強制決済注文Row.部店IDに該当する部店コード <BR>
     * 　@取引カレンダコンテキスト.市場コード = 強制決済注文Row.市場IDに該当する市場コード <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”  <BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”  <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = "信用取引"  <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = "返済" <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
     * 　@設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * ４）　@受付日時、日付ロールをセットする。  <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ５）　@強制決済注文の発注審査スキップフラグをセットする。 <BR>
     * <BR>
     * 　@　@"強制決済注文発注審査スキップフラグ"をLocalThreadにセットする。 <BR>
     * 　@　@－ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     * 　@　@　@　@WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP, <BR>
     * 　@　@　@　@BooleanEnum.True ) <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッド<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@強制決済注文Rowオブジェクトを取得する。
        //　@　@サービスの引数[0]を強制決済注文Rowにキャストする。
        AdminEqForcedSettleOrderRow l_forcedSettleOrderRow = null;
        if (l_serviceParams.length > 0 && l_serviceParams[0] instanceof AdminEqForcedSettleOrderRow)
        {
            l_forcedSettleOrderRow = (AdminEqForcedSettleOrderRow)l_serviceParams[0];
        }

        //２）　@管理者オブジェクトを取得する。
        //　@　@サービスの引数[1]を管理者にキャストする。
        WEB3Administrator l_administrator = (WEB3Administrator)l_serviceParams[1];

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        String l_strBranchCode = null;
        String l_strMarketCode = null;
        try
        {
            //部店コード = 強制決済注文Row.部店IDに該当する部店コード
            long l_lngBranchId = l_forcedSettleOrderRow.getBranchId();
            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_strBranchCode = l_accMgr.getBranch(l_lngBranchId).getBranchCode();

            //市場コード = 強制決済注文Row.市場IDに該当する市場コード
            long l_lngMarketId = l_forcedSettleOrderRow.getMarketId();
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(l_lngMarketId);
            l_strMarketCode = l_market.getMarketCode();
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //３）　@取引カレンダコンテキストに内容をセットする。
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        //取引カレンダコンテキスト.証券会社コード = 管理者.get証券会社コード()の戻り値
        l_context.setInstitutionCode(l_administrator.getInstitutionCode());
        //取引カレンダコンテキスト.部店コード = 強制決済注文Row.部店IDに該当する部店コード
        l_context.setBranchCode(l_strBranchCode);
        //取引カレンダコンテキスト.市場コード = 強制決済注文Row.市場IDに該当する市場コード
        l_context.setMarketCode(l_strMarketCode);
        //取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        //取引カレンダコンテキスト.注文受付商品 = "信用取引"
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        //取引カレンダコンテキスト.注文受付トランザクション = "返済"
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);

        //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。
        //設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //４）　@受付日時、日付ロールをセットする。
        //－取引時間管理.setTimestamp()をコールする。
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //５）　@強制決済注文の発注審査スキップフラグをセットする。
        //"強制決済注文発注審査スキップフラグ"をLocalThreadにセットする。
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
            BooleanEnum.TRUE);

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * サービスメソッド終了時にコールされる。  <BR>
     * 取引カレンダコンテキストクリア処理。  <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。  <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG  <BR>
     * 取引時間管理.OFFSET_TAG  <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        //WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP, null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。  <BR>
     * 取引カレンダコンテキストクリア処理。  <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。  <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG  <BR>
     * 取引時間管理.OFFSET_TAG  <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP <BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
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

        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        //WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
            null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
