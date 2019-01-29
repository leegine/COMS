head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金計算サービスインターセプタ(WEB3IfoDepositCalcServiceInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.ifodeposit;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (証拠金計算サービスインターセプタ)<BR>
 * 証拠金計算サービスインターセプタ。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositCalcServiceInterceptor
    implements Interceptor
{

    /**
     * 取引カレンダコンテキストの属性名
     */
    public static final String TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME =
        "webbroker3.ifodeposit.attributes.tradingcalendarcontext";

    /**
     * 受付時間の属性名
     */
    public static final String TIMESTAMP_ATTRIBUTE_NAME =
        "webbroker3.ifodeposit.attributes.timestamp";

    /**
     * オフセットの属性名
     */
    public static final String OFFSET_ATTRIBUTE_NAME =
        "webbroker3.ifodeposit.attributes.offset";

    /**
     * ログ
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoDepositCalcServiceInterceptor.class);

    /**
     * @@roseuid 41861FE0034A
     */
    public WEB3IfoDepositCalcServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@スレッドローカルより、取引カレンダコンテキストオブジェクトを取得 <BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに現在セットされている内容を保存しておく。<BR>
     * 　@呼出元サービスで取引カレンダコンテキストに内容が設定されている場合は、別途保存しておく。<BR>
     * 　@※onReturn()、および、onThrowable()の際に保存内容に戻す処理を行う<BR>
     * <BR>
     * ３）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]を補助口座オブジェクトにキャストする。  <BR>
     * 　@－キャストした補助口座オブジェクトより、顧客オブジェクトを取得する。 <BR>
     * 　@※補助口座オブジェクト.getMainAccount()をコール <BR>
     * <BR>
     * 　@取引時間コンテキストのプロパティを以下にセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 顧客.getInstitution().getInstitutionCode() <BR>
     * 　@取引カレンダコンテキスト.部店コード = 顧客.getBranch().getBranchCode() <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード = (*1)<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = (*2)<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = (*3)<BR>
     * <BR>
     * 　@(*1)　@１）で取得した取引カレンダコンテキスト.商品コード == nullの場合のみ”0:DEFAULT”をセット<BR>
     * 　@　@以外は１）の取引カレンダコンテキスト.商品コードをセット<BR>
     * <BR>
     * 　@(*2)　@１）で取得した取引カレンダコンテキスト.注文受付商品 == nullの場合のみ、”05：先物”をセット<BR>
     * 　@　@以外は１）の取引カレンダコンテキスト.注文受付商品をセット<BR>
     * <BR>
     * 　@(*3)１）で取得した取引カレンダコンテキスト.注文受付トランザクション == nullの場合のみ、”07：照会”をセット<BR>
     * 　@　@以外は１）の取引カレンダコンテキスト.注文受付トランザクションをセット<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
     * 　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ４）　@受付日時、日付ロールをセットする。<BR>
     * 　@スレッドローカルに取引時間コンテキストオブジェクトが設定されていない場合のみ、タイムスタンプを設定する。 <BR>
     * <BR>
     * 　@－１）で取得した取引時間コンテキスト == nullの場合、取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param method
     * @@param arguments
     * @@return Object
     * @@roseuid 41452B27014D
     */
    public java.lang.Object onCall(Method method, Object[] arguments)
    {
        /*
         * スレッドローカルより、オリジナル取引時間コンテキストオブジェクトを取得
         */
        WEB3GentradeTradingClendarContext l_originalContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        // 設定されているThreadLocal属性を移動
        removeAttributes();

        // 取引カレンダコンテキストを生成
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        //サービスの引数[0]を補助口座オブジェクトにキャストする
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)arguments[0];
        //顧客オブジェクトを取得
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //取引カレンダコンテキスト.証券会社コード = 顧客.getInstitution().getInstitutionCode()
        l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
        //取引カレンダコンテキスト.部店コード = 顧客.getBranch().getBranchCode()
        l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

        /*
         * 取得した取引カレンダコンテキスト.商品コード == nullの場合のみ”0:DEFAULT”をセット
         * 以外は１）の取引カレンダコンテキスト.商品コードをセット
         */
        if (l_originalContext == null || l_originalContext.getProductCode() == null)
        {
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        }
        else
        {
            l_context.setProductCode(l_originalContext.getProductCode());
        }

        /*
         * 取得した取引カレンダコンテキスト.注文受付商品 == nullの場合のみ、”05：先物”をセット
         * 以外は１）の取引カレンダコンテキスト.注文受付商品をセット
         */
        if (l_originalContext == null || l_originalContext.getOrderAcceptProduct() == null)
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
        }
        else
        {
            l_context.setOrderAcceptProduct(l_originalContext.getOrderAcceptProduct());
        } 
        
        /*
         * 取得した取引カレンダコンテキスト.注文受付トランザクション == nullの場合のみ、”07：照会”をセット
         * 以外は１）の取引カレンダコンテキスト.注文受付トランザクションをセット
         */
        if (l_originalContext == null || l_originalContext.getOrderAcceptTransaction() == null)
        {
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
        }
        else
        {
            l_context.setOrderAcceptTransaction(l_originalContext.getOrderAcceptTransaction());
        }

        // 取引時間コンテキストをセット
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        /*
         * 取得した取引時間コンテキスト == nullの場合、取引時間管理.setTimestamp()をコールする。
         */
        if (l_originalContext == null)
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

        log.debug("--------------------------------------------------");
        log.debug("TradingCalendarContext.institutionCode=" + l_context.getInstitutionCode());
        log.debug("TradingCalendarContext.branchCode=" + l_context.getBranchCode());
        log.debug("TradingCalendarContext.MarketCode=" + l_context.getMarketCode());
        log.debug("TradingCalendarContext.tradingTimeType=" + l_context.getTradingTimeType());
        log.debug("TradingCalendarContext.productCode=" + l_context.getProductCode());
        log.debug("TradingCalendarContext.orderAcceptProduct=" + l_context.getOrderAcceptProduct());
        log.debug("TradingCalendarContext.orderAcceptTransaction=" + l_context.getOrderAcceptTransaction());
        log.debug("--------------------------------------------------");

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
     * @@param context
     * @@param returnValue
     * @@roseuid 41452B27016C
     */
    public void onReturn(Object context, Object returnValue)
    {
        resetAttributes();
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param context
     * @@param thrownObject
     * @@roseuid 41452B27017C
     */
    public void onThrowable(Object context, Throwable thrownObject)
    {
        resetAttributes();
    }

    /**
     * 設定されているThreadLocal属性を移動する
     */
    private void removeAttributes()
    {
        
        // 設定されているThreadLocal属性を取得
        Object l_objTempTradingCalendarContext =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        Object l_objTempTimestamp =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        Object l_objTempOffset =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        
        // 取得したThreadLocal属性を別の名前で保存
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME,
            l_objTempTradingCalendarContext);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_ATTRIBUTE_NAME,
            l_objTempTimestamp);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            OFFSET_ATTRIBUTE_NAME,
            l_objTempOffset);
    }

    /**
     * ThreadLocal属性を再設定する。
     */
    private void resetAttributes()
    {

        // 退避していた属性を取得
        Object l_objContext =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME);
        Object l_objTimestamp =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                TIMESTAMP_ATTRIBUTE_NAME);
        Object l_objOffset =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                OFFSET_ATTRIBUTE_NAME);
        
        // 取得した属性を再設定
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_objContext);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_objTimestamp);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            l_objOffset);
        
        // 退避していた属性を削除
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_ATTRIBUTE_NAME,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            OFFSET_ATTRIBUTE_NAME,
            null);

    }

}
@
