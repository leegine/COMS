head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力サービスインタセプタ(WEB3TPTradingPowerAfterRepayServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/13 nakazato(ACT) 新規作成
Revesion History : 2008/10/22 孟亞南 (中訊) 仕様変更モデル311
Revesion History : 2008/10/31 張少傑 (中訊) 仕様変更モデル353
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
 * (余力サービスインタセプタ)<BR>
 * 余力サービスインタセプタ。<BR>
 */
public class WEB3TPTradingPowerServiceInterceptor implements Interceptor
{

    /**
     * ログ
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerServiceInterceptor.class);

    /**
     * コンストラクタ
     */
    public WEB3TPTradingPowerServiceInterceptor()
    {

    }

    /**
     * (onCall)<BR>
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 　@・取引余力サービス<BR>
     * 　@・返済後余力サービス<BR>
     * 　@・差金決済取引余力サービス<BR>
     * 　@・債券シンプレクス連携サービス<BR>
     * 　@・入金請求管理サービス<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）スレッドローカルより、取引時間コンテキストオブジェクトを取得<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * <BR>
     * 　@(*)取得した取引時間コンテキスト==nullの場合 <BR>
     * 　@　@サービスの引数[0]のデータ型を判断する <BR>
     * 　@　@　@(*)サービスの引数[0]のデータ型が補助口座の場合、補助口座オブジェクトにキャストする。<BR>
     * 　@　@　@　@－サービスの引数[0]を補助口座オブジェクトにキャストする。 <BR>
     * 　@　@　@　@－キャストした補助口座オブジェクトより、顧客オブジェクトを取得する。 <BR>
     * 　@　@　@　@※補助口座オブジェクト.getMainAccount()をコール <BR>
     * 　@　@　@(*)サービスの引数[0]のデータ型が顧客の場合、顧客オブジェクトにキャストする。<BR>
     * <BR>
     * 　@　@取引カレンダコンテキスト.証券会社コード = 顧客オブジェクト.getInstitution().getInstitutionCode()<BR>
     * 　@　@取引カレンダコンテキスト.部店コード = 顧客オブジェクト.getBranch().getBranchCode()<BR>
     * 　@　@取引カレンダコンテキスト.市場コード =取得した取引時間コンテキストオブジェクト.市場コード <BR>
     * 　@　@取引カレンダコンテキスト.受付時間区分 = 01：株式・信用 <BR>
     * 　@　@取引カレンダコンテキスト.商品コード = 0：DEFAULT<BR>
     * <BR>
     * 　@(*)取得した取引時間コンテキスト≠nullの場合<BR>
     * 　@　@取引カレンダコンテキスト = １）で取得した取引時間コンテキストオブジェクト<BR>
     * <BR>
     * －ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする。<BR>
     * （※）設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）スレッドローカルに取引時間コンテキストオブジェクトが設定されていない場合、タイムスタンプを設定する。<BR>
     * 　@（取得した取引時間コンテキストオブジェクト==null)<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param method
     * @@param arguments
     */
    public java.lang.Object onCall(Method method, Object[] arguments)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        /*
         * スレッドローカルより、オリジナル取引時間コンテキストオブジェクトを取得
         */
        WEB3GentradeTradingClendarContext l_originalContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        /*
         * 取引時間コンテキストに内容をセットする
         */
        // 取引時間コンテキストを生成
        WEB3GentradeTradingClendarContext l_context = null;

        //オリジナル取引時間コンテキスト==null
        if(l_originalContext == null)
        {
            l_context = new WEB3GentradeTradingClendarContext();

            MainAccount l_mainAccount = null;

            //(*)サービスの引数[0]のデータ型が補助口座の場合、補助口座オブジェクトにキャストする。
            if (arguments[0] instanceof WEB3GentradeSubAccount)
            {
                WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)arguments[0];
                //顧客オブジェクトを取得
                l_mainAccount = l_subAccount.getMainAccount();
            }
            else if (arguments[0] instanceof MainAccount)
            {
                //(*)サービスの引数[0]のデータ型が顧客の場合、顧客オブジェクトにキャストする。
                l_mainAccount = (MainAccount)arguments[0];
            }

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
        }
        //以外の場合
        else
        {
            l_context = l_originalContext;
        }


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

        //オリジナル取引時間コンテキスト==null
        if(l_originalContext == null)
        {
            //xTradeが利用する業務日時をセットする
            try 
            {
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
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * サービスメソッド終了時にコールされる。<BR>
     * 
     * @@param context
     * @@param returnValue
     */
    public void onReturn(Object context, Object returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * 
     * @@param context
     * @@param thrownObject
     */
    public void onThrowable(Object context, Throwable thrownObject)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
    }

}
@
