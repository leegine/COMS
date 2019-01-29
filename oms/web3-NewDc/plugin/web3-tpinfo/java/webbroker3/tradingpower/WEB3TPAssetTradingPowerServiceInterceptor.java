head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.13.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetTradingPowerServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 資産余力情報画面表示サービスインターセプタ(WEB3TPAssetTradingPowerServiceInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 asano(SCS) 新規作成
 */
package webbroker3.tradingpower;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (資産余力情報画面表示サービスインターセプタ)<BR>
 * 資産余力情報画面表示サービスインターセプタ。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPAssetTradingPowerServiceInterceptor
    implements Interceptor
{

    /**
     * 取引カレンダコンテキストの属性名
     */
    public static final String TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME =
        "webbroker3.tradingpower.attributes.tradingcalendarcontext";

    /**
     * 受付時間の属性名
     */
    public static final String TIMESTAMP_ATTRIBUTE_NAME =
        "webbroker3.tradingpower.attributes.timestamp";

    /**
     * オフセットの属性名
     */
    public static final String OFFSET_ATTRIBUTE_NAME =
        "webbroker3.tradingpower.attributes.offset";

    /**
     * ログ
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3TPAssetTradingPowerServiceInterceptor.class);

    /**
     * (デバッグison)
     */
    private static boolean DBG = log.ison();

    /**
     * (コンストラクタ)
     */
    public  WEB3TPAssetTradingPowerServiceInterceptor()
    {
    }

    /**
     * (onCall) <BR>
     * <BR>
     * サービスメソッド開始時にコールされる。 <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）設定されているThreadLocal属性を移動する。 <BR>
     * 　@?this.removeAttributes()をコール <BR>
     * <BR>
     * ２）取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@?サービスの引数[0]をリクエストデータオブジェクトにキャストする。 <BR>
     * 　@?リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”01：株式” <BR>
     * 　@取引カレンダコンテキスト.商品コード = "0" <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = "32：余力" <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = "07：照会" <BR>
     * <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする <BR>
     * 　@　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ３）受付日時、日付ロールをセットする。 <BR>
     * 　@?取引時間管理.setBusinessTimestamp()をコールする。 <BR>
     * <BR>
     * @@param method
     * @@param arguments
     * @@return Object
     */
    public java.lang.Object onCall(Method method, Object[] arguments)
    {

        // 設定されているThreadLocal属性を移動
        removeAttributes();

        // 取引カレンダコンテキストを生成
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        String l_strInstitutionCode = null; // 証券会社コード
        String l_strBranchCode = null; // 部店コード

        // OpLoginSecurityServiceから証券会社コード、部店コードを取得
        OpLoginSecurityService l_security =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        long l_lngAccountId = l_security.getAccountId();
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                GtlUtils.getAccountManager().getMainAccount(l_lngAccountId);
            l_strInstitutionCode =
                l_mainAccount.getInstitution().getInstitutionCode();
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        } 
        catch (NotFoundException nfe)
        {
            log.error(nfe.getMessage(), nfe);
        }

        // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
        l_context.setInstitutionCode( l_strInstitutionCode );
        // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode( l_strBranchCode );
        // 取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_context.setMarketCode( WEB3MarketCodeDef.DEFAULT );
        // 取引カレンダコンテキスト.受付時間区分 = ”01：EQUITY”
        l_context.setTradingTimeType( WEB3TradingTimeTypeDef.EQUITY );
        // 取引カレンダコンテキスト.商品コード
        l_context.setProductCode( "0" );
        // 取引カレンダコンテキスト.注文受付商品 = "32：余力"
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.TRADING_POWER);
        // 取引カレンダコンテキスト.注文受付トランザクション = "07：照会"
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

        // 取引時間コンテキストをセット
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
            
        //xTradeが利用する業務日時をセットする
        try 
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        } 
        catch (WEB3SystemLayerException e) 
        {
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "onCall");

        }
        
        if( DBG ) 
        {
            log.debug("--------------------------------------------------");
            log.debug("TradingCalendarContext.institutionCode=" + l_context.getInstitutionCode());
            log.debug("TradingCalendarContext.branchCode=" + l_context.getBranchCode());
            log.debug("TradingCalendarContext.MarketCode=" + l_context.getMarketCode());
            log.debug("TradingCalendarContext.tradingTimeType=" + l_context.getTradingTimeType());
            log.debug("TradingCalendarContext.productCode=" + l_context.getProductCode());
            log.debug("TradingCalendarContext.orderAcceptProduct=" + l_context.getOrderAcceptProduct());
            log.debug("TradingCalendarContext.orderAcceptTransaction=" + l_context.getOrderAcceptTransaction());
            log.debug("--------------------------------------------------");
        }
        
        return null;

    }

    /**
     * (onReturn) <BR>
     * <BR>
     * サービスメソッド終了時にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）ThreadLocal属性を再設定する。 <BR>
     * 　@?this.resetAttributes()をコール <BR>
     * <BR>
     * @@param context
     * @@param returnValue
     */
    public void onReturn(Object context, Object returnValue)
    {
        resetAttributes();
    }

    /**
     * (onThrowable) <BR>
     * <BR>
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）ThreadLocal属性を再設定する。 <BR>
     * ?this.resetAttributes()をコール <BR>
     * <BR>
     * @@param context
     * @@param thrownObject
     */
    public void onThrowable(Object context, Throwable thrownObject)
    {
        resetAttributes();
    }

    /**
     * (removeAttributes) <BR>
     * 設定されているThreadLocal属性を移動する <BR>
     * <BR>
     * １）設定されているThreadLocal属性を取得 <BR>
     * <BR>
     * 　@１?１）属性名（=取引時間管理.TRADING_CAL_CONTEXT_PATH)の値を取得 <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.getAttribute()にて値を取得 <BR>
     * <BR>
     * 　@１?２）属性名（=取引時間管理.TIMESTAMP_TAG)の値を取得 <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.getAttribute()にて値を取得 <BR>
     * <BR>
     * 　@１?３）属性名（=取引時間管理.OFFSET_TAG)の値を取得 <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.getAttribute()にて値を取得 <BR>
     * <BR>
     * ２）取得したThreadLocal属性を別の名前で保存 <BR>
     * <BR>
     * 　@２?１）属性名（=資産余力情報画面表示インタセプタ.TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME)に値をセット<BR> 
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute()にてセット <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@String：資産余力情報画面表示インタセプタ.TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME <BR>
     * 　@　@　@Value：１?１）の戻り値 <BR>
     * <BR>
     * 　@２?２）属性名（=資産余力情報画面表示インタセプタ.TIMESTAMP_ATTRIBUTE_NAME)に値をセット <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute()にてセット <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@String：資産余力情報画面表示インタセプタ.TIMESTAMP_ATTRIBUTE_NAME <BR>
     * 　@　@　@Value：１?２）の戻り値 <BR>
     * <BR>
     * 　@２?３）属性名（=資産余力情報画面表示インタセプタ.OFFSET_ATTRIBUTE_NAME)に値をセット <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute()にてセット <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@String：資産余力情報画面表示インタセプタ.OFFSET_ATTRIBUTE_NAME <BR>
     * 　@　@　@Value：１?３）の戻り値 <BR>
     * <BR>
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
     * (resetAttributes) <BR>
     * ThreadLocal属性を再設定する。 <BR>
     * <BR>
     * １）退避していた属性を取得 <BR>
     * <BR>
     * 　@１?１）属性名（=資産余力情報画面表示インタセプタ.TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME)の値を取得 <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.getAttribute()にて値を取得 <BR>
     * <BR>
     * 　@１?２）属性名（=資産余力情報画面表示インタセプタ.TIMESTAMP_ATTRIBUTE_NAME)の値を取得 <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.getAttribute()にて値を取得 <BR>
     * <BR>
     * 　@１?３）属性名（=資産余力情報画面表示インタセプタ.OFFSET_ATTRIBUTE_NAME)の値を取得 <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.getAttribute()にて値を取得 
     * <BR>
     * ２）取得した属性を再設定 <BR>
     * <BR>
     * 　@２?１）属性名（=取引時間管理.TRADING_CAL_CONTEXT_PATH)に値をセット <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute()にてセット <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@String：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * 　@　@　@Value：１?１）の戻り値 <BR>
     * <BR>
     * 　@２?２）属性名（=取引時間管理.TIMESTAMP_TAG)に値をセット <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute()にてセット <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@String：取引時間管理.TIMESTAMP_TAG <BR>
     * 　@　@　@Value：１?２）の戻り値 <BR>
     * <BR>
     * 　@２?３）属性名（=取引時間管理.OFFSET_TAG)に値をセット <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute()にてセット<BR> 
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@String：取引時間管理.OFFSET_TAG <BR>
     * 　@　@　@Value：１?３）の戻り値 <BR>
     * <BR>
     * ３）退避していた属性を削除 <BR>
     * <BR>
     * 　@３?１）属性名（=資産余力情報画面表示インタセプタ.TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME)に値をセット <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute()にてセット <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@String：資産余力情報画面表示インタセプタ.TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME <BR>
     * 　@　@　@Value：null <BR>
     * <BR>
     * 　@３?２）属性名（=資産余力情報画面表示インタセプタ.TIMESTAMP_ATTRIBUTE_NAME)に値をセット <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute()にてセット <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@String：資産余力情報画面表示インタセプタ.TIMESTAMP_ATTRIBUTE_NAME <BR>
     * 　@　@　@Value：null <BR>
     * <BR>
     * 　@３?３）属性名（=資産余力情報画面表示インタセプタ.OFFSET_ATTRIBUTE_NAME)に値をセット <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute()にてセット <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@String：資産余力情報画面表示インタセプタ.OFFSET_ATTRIBUTE_NAME <BR>
     * 　@　@　@Value：null <BR>
     * <BR>
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
