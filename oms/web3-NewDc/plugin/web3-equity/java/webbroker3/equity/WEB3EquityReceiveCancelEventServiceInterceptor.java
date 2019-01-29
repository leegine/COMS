head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityReceiveCancelEventServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式訂正取消通知取消一件サービスインタセプタ(WEB3EquityReceiveCancelEventServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 中尾寿彦(SRA) 新規作成
Revesion History : 2005/01/05 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import java.lang.reflect.Method;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式訂正取消通知取消一件サービスインタセプタ）。<BR>
 * <BR>
 * 現物株式訂正取消通知取消一件サービスインタセプタクラス
 * @@author 中尾寿彦
 * @@version 1.0
 */
public class WEB3EquityReceiveCancelEventServiceInterceptor
    implements Interceptor
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityReceiveCancelEventServiceInterceptor.class);

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3EquityReceiveCancelEventServiceInterceptor()
    {
    }

    /**
     * (onCall)<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルより、[訂正取消通知取消一件サービス]実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]を株式訂正取消通知キューParamsオブジェクトにキャストする。<BR>
     * 　@－サービスの引数[1]を注文単位オブジェクトにキャストする。<BR>
     * 　@－株式訂正取消通知キューParams、注文単位の内容より、取引時間コンテキストのプロパティをセットする。<BR>
     * 　@　@　@　@　@　@○証券会社コード、部店コード<BR>
     * 　@　@　@　@　@　@　@　@　@株式訂正取消通知キューParamsの同項目をセット<BR>
     * 　@　@　@　@　@　@○市場コード<BR>
     * 　@　@　@　@　@　@　@　@　@注文単位.市場ID に該当する市場オブジェクトの市場コードをセット<BR>
     * 　@　@　@　@　@　@○受付時間区分<BR>
     * 　@　@　@　@　@　@　@　@　@”株式・信用” をセット<BR>
     * 　@　@　@　@　@　@○注文受付商品<BR>
     * 　@　@　@　@　@　@　@　@　@”株式”をセット<BR>
     * 　@　@　@　@　@　@○注文受付トランザクション<BR>
     * 　@　@　@　@　@　@　@　@　@”取消”をセット<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
     * 設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）　@受付日時に株式注文単位.作成日時 をセットする。<BR>
     * 　@　@受付日時として、株式注文単位.作成日時 を使用する。<BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて、株式注文単位.作成日時 をセットする。<BR>
     * 　@　@設定キー：　@取引時間管理.TIMESTAMP_TAG<BR>
     * <BR>
     * ４）　@当日場中の基準値取得のための属性をセットする。<BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@「下り処理で必ず場中扱い」をセットする。<BR>
     * 　@　@　@　@設定キー： 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * ５）　@口座をロックする。<BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@　@　@※引数は株式訂正取消通知キューより編集。<BR>
     * <BR>
     * @@param l_method - (メソッド)<BR>
     * メソッド<BR>
     * @@param l_serviceParams - (サービスの引数)<BR>
     * サービスのメソッドに渡される引数。<BR>
     * 株式注文受信一件サービスの場合、株式注文入力通知キューParamsオブジェクト。<BR>
     * @@return Object
     * @@roseuid 403EE66F025C<BR>
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        // １）　@取引カレンダコンテキストに内容をセットする。
        // 　@－サービスの引数[0]を株式訂正取消通知キューParamsオブジェクトにキャストする。
        HostEqtypeOrderClmdReceiptParams l_params =
            (HostEqtypeOrderClmdReceiptParams)l_serviceParam[0];
        // 　@－サービスの引数[1]を注文単位オブジェクトにキャストする。<BR>
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_serviceParam[1];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        // 　@－株式訂正取消通知キューParams、注文単位の内容より、取引時間コンテキストのプロパティをセットする。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Market l_market = null;
        try
        {
            l_market =
                l_finApp.getFinObjectManager().getMarket(
                    l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        // 取引カレンダコンテキスト.証券会社コード = 株式訂正取消通知キューParamsの同項目をセット
        l_context.setInstitutionCode(
            l_params.getInstitutionCode());
        // 取引カレンダコンテキスト.部店コード = 株式訂正取消通知キューParamsの同項目をセット
        l_context.setBranchCode(
            l_params.getBranchCode());
        // 取引カレンダコンテキスト.市場コード =
        //       注文単位.市場ID に該当する市場オブジェクトの市場コード
        l_context.setMarketCode(l_market.getMarketCode());
        // 取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // 取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        // 取引カレンダコンテキスト.取引停止商品 = ”株式”
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        // 取引カレンダコンテキスト.取引停止トランザクション =”取消”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);

        // 取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
            Timestamp l_tsReceivedDateTime =
                l_orderUnitRow.getCreatedTimestamp();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_tsReceivedDateTime);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
                WEB3EquityBackServiceOnlineDef.ONLINE);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (onReturn)<BR>
     * <BR>
     * 訂正取消通知取消一件サービス終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * @@param l_returnValue - (戻り値)<BR>
     * @@roseuid 403EE66F025F<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (onThrowable)<BR>
     * <BR>
     * 訂正取消通知取消一件サービスが例外をスローした場合にコールされる<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * @@roseuid 40A436F402E1<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
