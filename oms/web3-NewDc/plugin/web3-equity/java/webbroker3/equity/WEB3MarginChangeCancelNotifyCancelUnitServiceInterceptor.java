head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeCancelNotifyCancelUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引訂正取消通知取消一件サービスインタセプタ(WEB3MarginChangeCancelNotifyCancelUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 艾興 (中訊) 新規作成
                   2004/12/17 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引訂正取消通知取消一件サービスインタセプタ）。<BR>
 * <BR>
 * 信用取引訂正取消通知取消一件サービスインタセプタクラス
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3MarginChangeCancelNotifyCancelUnitServiceInterceptor
    implements Interceptor
{

    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginChangeCancelNotifyChangeUnitServiceInterceptor.class);
    /**
     * @@roseuid 4142B32E03B8
     */
    public WEB3MarginChangeCancelNotifyCancelUnitServiceInterceptor()
    {

    }

    /**
     * (onCall)<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルより、[信用取引訂正取消通知訂正一件サービス]実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]を株式訂正取消通知キューParamsオブジェクトにキャストする。<BR>
     * 　@－サービスの引数[1]を株式注文単位オブジェクトにキャストする。<BR>
     * 　@－株式訂正取消通知キューParams、注文単位の内容より、取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@　@　@　@証券会社コード　@＝　@株式訂正取消通知キューParams.証券会社コード<BR>
     * 　@　@　@　@　@　@部店コード　@　@　@　@＝　@株式訂正取消通知キューParams.部店コード<BR>
     * 　@　@　@　@　@　@市場コード　@　@　@　@＝　@株式注文単位.市場ID に該当する市場オブジェクトの市場コード<BR>
     * 　@　@　@　@　@　@受付時間区分　@ ＝　@”01：株式・信用”<BR>
     * 　@　@　@　@　@　@商品コード　@　@　@　@＝　@”0：DEFAULT”<BR>
     * 　@　@　@　@　@　@注文受付商品　@ ＝　@”03：信用取引”<BR>
     * 　@　@　@　@　@　@注文受付トランザクション　@＝　@”06：取消”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
     * 　@　@設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@・取引時間管理.setTimestamp()をコールする。<BR>
     * 　@・受付日時として、株式注文単位.作成日時 を使用する為（訂正時の取引銘柄を取得するため）、<BR>
     * 　@　@取引時間管理.setTimestamp()にて設定された受付日時を上書きする。<BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて、株式注文単位.作成日時 をセットする。<BR>
     * 　@　@設定キー：　@取引時間管理.TIMESTAMP_TAG<BR>
     * <BR>
     * ３）　@当日場中の基準値取得のための属性をセットする。<BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@「下り処理で必ず場中扱い」をセットする。<BR>
     * 　@　@　@　@設定キー： 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * ４）　@口座をロックする。<BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@　@　@※引数は株式訂正取消通知キューParamsより編集。<BR>
     * <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 405905910193
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        Object l_obj0 = l_serviceParams[0];
        Object l_obj1 = l_serviceParams[1];
        HostEqtypeOrderClmdReceiptParams l_params = null;
        EqTypeOrderUnit l_orderUnit = null;
        
        // １）　@取引カレンダコンテキストに内容をセットする。
        // 　@－サービスの引数[0]を株式訂正取消通知キューParamsオブジェクトにキャストする。
        if (l_obj0 instanceof HostEqtypeOrderClmdReceiptParams)
        {
            l_params = (HostEqtypeOrderClmdReceiptParams)l_obj0;
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 　@－サービスの引数[1]を株式注文単位オブジェクトにキャストする。
        if (l_obj1 instanceof EqTypeOrderUnit)
        {
            l_orderUnit = (EqTypeOrderUnit)l_obj1;
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            // 　@－株式訂正取消通知キューParams、注文単位の内容より、
            // 　@　@取引時間コンテキストのプロパティをセットする。
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            // 証券会社コード　@＝ 株式訂正取消通知キューParams.証券会社コード
            l_context.setInstitutionCode(l_params.getInstitutionCode());
            // 部店コード　@　@　@＝　@株式訂正取消通知キューParams.部店コード
            l_context.setBranchCode(l_params.getBranchCode());
            // 市場コード　@　@　@＝　@株式注文単位.市場ID に該当する市場オブジェクトの市場コード
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(
                    l_eqtypeOrderUnitRow.getMarketId());
            l_context.setMarketCode(l_market.getMarketCode());
            // 受付時間区分　@  ＝　@”01：株式・信用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            // 商品コード　@　@　@＝　@”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            // 注文受付商品　@  ＝　@”03：信用取引”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            // 注文受付トランザクション　@＝　@”06：取消”
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CANCEL);
            // 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )
            //        にて取引時間コンテキストをセットする。
            // 　@　@設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            // ２）　@受付日時、日付ロールをセットする。
            // 　@・取引時間管理.setTimestamp()をコールする。
            // 　@・受付日時として、株式注文単位.作成日時を使用する為（訂正時の取引銘柄を取得するため）、
            // 　@　@取引時間管理.setTimestamp()にて設定された受付日時を上書きする。
            // 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて、株式注文単位.作成日時 をセットする。
            // 　@　@設定キー：　@取引時間管理.TIMESTAMP_TAG
            WEB3GentradeTradingTimeManagement.setTimestamp();
            Timestamp l_tsReceivedDateTime =
                l_eqtypeOrderUnitRow.getCreatedTimestamp();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_tsReceivedDateTime);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
                WEB3EquityBackServiceOnlineDef.ONLINE);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (WEB3BaseException l_wsle)
        {
            log.error(STR_METHOD_NAME, l_wsle);
            throw new WEB3BaseRuntimeException(
                l_wsle.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wsle.getMessage(),
                l_wsle);
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (onReturn)<BR>
     * <BR>
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 4059059101A2
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME =
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
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
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            null);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (onThrowable)<BR>
     * <BR>
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 4142B32E03E0
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);
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
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            null);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
