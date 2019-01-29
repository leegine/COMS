head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算通知一件サービスインタセプタ(WEB3TPReCalcNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (余力計算通知一件サービスインタセプタ)
 */
public class WEB3TPReCalcNotifyUnitServiceInterceptor implements Interceptor
{

    /**
     * ログ
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPReCalcNotifyUnitServiceInterceptor.class);

    /**
     * @@roseuid 4235480002F8
     */
    public WEB3TPReCalcNotifyUnitServiceInterceptor()
    {

    }

    /**
     * (onCall)<BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルより、サービスメソッド開始時にコールされる） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]を補助口座オブジェクトにキャストする。 <BR>
     * 　@－キャストした補助口座オブジェクトより、顧客オブジェクトを取得する。<BR>
     * 　@　@※補助口座オブジェクト.getMainAccount()をコール<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 顧客オブジェクト.getInstitution().getInstitutionCode() <BR>
     * 　@取引カレンダコンテキスト.部店コード = 顧客オブジェクト.getBranch().getBranchCode()<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”1:東京”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用” <BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = null <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     * 　@　@　@取引時間コンテキストをセットする。 <BR>
     * 　@設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param arg0
     * @@param arg1
     * @@return Object
     * @@roseuid 41F49A490084
     */
    public Object onCall(Method arg0, Object[] arg1)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        /*
         * 取引時間コンテキストに内容をセットする
         */
        // 取引時間コンテキストを生成
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        //サービスの引数[0]を補助口座オブジェクトにキャストする。
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)arg1[0];
        //顧客オブジェクトを取得
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //証券会社コード
        l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
        //部店コード
        l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
        //市場コード = 1:東京
        l_context.setMarketCode(WEB3MarketCodeDef.TOKYO);
        //受付時間区分 = 01：株式・信用
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        //商品コード = 0：DEFAULT
        l_context.setProductCode("0");

        //取引時間コンテキストをセット
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        log.debug("--------------------------------------------------");
        log.debug("TradingCalendarContext.institutionCode=" + l_context.getInstitutionCode());
        log.debug("TradingCalendarContext.branchCode=" + l_context.getBranchCode());
        log.debug("TradingCalendarContext.MarketCode=" + l_context.getMarketCode());
        log.debug("TradingCalendarContext.tradingTimeType=" + l_context.getTradingTimeType());
        log.debug("TradingCalendarContext.productCode=" + l_context.getProductCode());
        log.debug("--------------------------------------------------");

        /*
         * スレッドローカルに取引時間コンテキストオブジェクトが設定されていない場合、タイムスタンプを設定する。
         */

        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                e.getErrorInfo(),
                e.getErrorMethod(),
                e.getErrorMessage(),
                e.getException());
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (onReturn)
     * 余力再計算通知一件サービス終了時にコールされる。 
     * 
     * @@param arg0
     * @@param arg1
     * @@roseuid 41F49A490093
     */
    public void onReturn(Object arg0, Object arg1)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (onThrowable) 
     * サービスメソッドが例外をスローした場合にコールされる。
     * 
     * @@param arg0
     * @@param arg1
     * @@roseuid 41F49A4900B2
     */
    public void onThrowable(Object arg0, java.lang.Throwable arg1)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
