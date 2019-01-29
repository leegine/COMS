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
filename	WEB3EquityOrderNotifyPartServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文通知一件サービスインタセプタ(WEB3EquityOrderNotifyPartServiceIntercepter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 欒学峰 (中訊) 新規作成
                   2004/12/10 岡村和明(SRA) 残案件対応 Ｎｏ.１５４＆Ｎｏ.２３９
                   2005/01/05 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.service.delegate.WEB3EquityOrderInputNotifyAdapter;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文通知一件サービスインタセプタ）。<BR>
 * <BR>
 * 株式注文通知一件サービスに登録するインタセプタクラス。<BR>
 * 株式発注審査個別チェック．calc成行時計算単価()で使用する<BR>
 * 取引銘柄オブジェクト生成時の取引カレンダコンテキスト生成・クリアを行う。
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyPartServiceInterceptor implements Interceptor 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderNotifyPartServiceInterceptor.class);
        
    /**
     * @@roseuid 40B42272029F
     */
    public WEB3EquityOrderNotifyPartServiceInterceptor() 
    {
    
    }
   
    /**
     * （onCall）。<br>
     * <br>
     * 取引カレンダが利用するコンテキストを生成する。<br>
     * （xTradeカーネルより、[株式注文通知一件サービス]実行前に呼び出される）<br>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]を株式注文入力通知データアダプタにキャストする。 <BR>
     * 　@－株式注文入力通知データアダプタ.株式注文入力通知キューParamsの内容より <BR>
     *    取引時間コンテキストのプロパティをセットする。 <BR>
     * 　@　@　@　@　@　@○証券会社コード、部店コード <BR>
     * 　@　@　@　@　@　@　@　@　@株式注文入力通知キューParamsの同項目をセット <BR>
     * 　@　@　@　@　@　@○市場コード <BR>
     * 　@　@　@　@　@　@　@　@　@株式注文入力通知データアダプタ.get市場コード( )をセット <BR>
     * 　@　@　@　@　@　@○受付時間区分 <BR>
     * 　@　@　@　@　@　@　@　@　@01：株式・信用 をセット <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     *    取引時間コンテキストをセットする。 <BR>
     * 設定キー："web3.tradingcalendarcontext" <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * ３）　@受付日時にSONAR側での発注日時(＝発注日時(biz_datetime)) をセットする。<BR>
     * 　@　@受付日時として、SONAR側での発注日時(＝発注日時(biz_datetime))を<BR>
     * 　@　@使用する。（※注　@を参照）<BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて、株式注文入力通知キューParams.発注日時 をセットする。<BR>
     * 設定キー："xblocks.gtl.attributes.system_timestamp"<BR>
     * <BR>
     * ○ ※注 ○ <BR>
     * SONAR側から受領した受注日時(create_datetime)は <BR>
     * 登録者による手入力項目（SONAR側入力チェックほとんど無し）であるため、 <BR>
     * 取引銘柄取得のsystem_timestampとしては使用できない。 <BR>
     * しかし、SONAR側から受領した発注日時(biz_datetime)は <BR>
     * SONARシステムが発注時に自動セットする項目であり、 <BR>
     * YYYYMMDDには当日日付が、 <BR>
     * HHMMSSには当日の開局前～開局時間帯の時間が入ってくるため、 <BR>
     * このサービスにおいては発注日時(biz_datetime)を<BR>
     * 取引銘柄取得のsystem_timestampとして使用する。<BR>
     * <BR>
     * ４）　@口座をロックする。<BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@　@　@※引数は株式注文入力通知キューParamsより編集。<BR>
     * <BR>
     * @@param l_method - メソッド <BR>
     * @@param l_serviceParam - サービスのメソッドに渡される引数。 <BR>
     * 株式注文受信一件サービスの場合、 <BR>
     * 株式注文入力通知キューParamsオブジェクト。 <BR>
     * @@return Object
     * @@roseuid 4021F6C6029B
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        WEB3EquityOrderInputNotifyAdapter l_orderInputNotifyAdapter = (WEB3EquityOrderInputNotifyAdapter) l_serviceParam[0];

        HostEqtypeOrderReceiptParams l_orderReceiptParams = l_orderInputNotifyAdapter.getHostEqtypeOrderReceipt();
        
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        String l_strInstitutionCode = null; //証券会社コード
        String l_strBranchCode  = null; //部店コード

        l_strInstitutionCode = l_orderReceiptParams.getInstitutionCode();
        l_strBranchCode = l_orderReceiptParams.getBranchCode();

        //証券会社コード、部店コード株式注文入力通知キューParamsの同項目をセット
        l_context.setInstitutionCode(l_strInstitutionCode);
        l_context.setBranchCode(l_strBranchCode);
        
        //株式注文入力通知データアダプタ.get市場コード( )をセット
        WEB3EquityOrderInputNotifyAdapter l_adapter =
            WEB3EquityOrderInputNotifyAdapter.create(l_orderReceiptParams);
        String l_strMarketCode = null;
        try
        {
            l_strMarketCode = l_adapter.getMarketCode();
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_context.setMarketCode(l_strMarketCode);
        
        // 取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //銘柄コードをセットする
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        // 取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
            
        //２）受付日時、日付ロールをセットする
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(
                "__WEB3GentradeTradingTimeManagement.setTimestamp Error__", l_ex);

            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //３）　@受付日時にSONAR側での発注日時(＝発注日時(biz_datetime)) をセットする
        ThreadLocalSystemAttributesRegistry.setAttribute( 
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_orderReceiptParams.biz_datetime);

        // ４）　@口座をロックする。
        log.debug("拡張アカウントマネージャの口座をロックします。");
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        // 拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
        try {
            l_accountManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_orderReceiptParams.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
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
     * （onReturn）。<br>
     * <br>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * 株式注文通知一件サービス終了時にコールされる。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * "system_timestamp" <BR>
     * "xblocks.gtl.attributes.bizdate.offset" <BR>
     * "web3.tradingcalendarcontext" <BR>
     * <BR>
     * @@param l_onCallReturnValue - onCallリターン値 <BR>
     * @@param l_serviceMethodReturnValue - サービスメソッドリターン値 <BR>
     * @@roseuid 4021F6C602AA
     */
    public void onReturn(Object l_onCallReturnValue, Object l_serviceMethodReturnValue)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    }
    
    /**
     * （onThrowable）。<br>
     * <br>
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * @@param l_onCallReturnValue
     * @@param l_throwable - 例外オブジェクト <BR>
     * @@roseuid 40B2D17D004E
     */
    public void onThrowable(Object l_onCallReturnValue, Throwable l_throwable)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    }
}
@
