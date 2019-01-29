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
filename	WEB3MarginOrderNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  (信用取引注文通知一件サービスインタセプタ)<BR>
                 :  信用取引注文通知一件サービスインタセプタクラス
                 :  (WEB3MarginOrderNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李松峰 (中訊) 新規作成
                   2004/12/16 水落 (SRA) 残案件対応のため修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyDataAdapter;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引注文通知一件サービスインタセプタ）。<BR>
 * <BR>
 * 信用取引注文通知一件サービスインタセプタクラス
 * @@version 1.0
 */
public class WEB3MarginOrderNotifyUnitServiceInterceptor implements Interceptor 
{ 
    /**
    * (ログユーティリティ)<BR>
    */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginOrderNotifyUnitServiceInterceptor.class);
                
    /**
     * @@roseuid 4142B32E01F5
     */
    public WEB3MarginOrderNotifyUnitServiceInterceptor() 
    {
     
    }
    
    /**
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルより、[信用取引注文通知一件サービス]実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]を信用取引注文入力通知データアダプタにキャストする。<BR>
     * <BR>
     * 　@－信用取引注文入力通知データアダプタ.株式注文入力通知キューParamsの<BR>
     *        内容より<BR>
     * 　@　@取引カレンダコンテキストのプロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@証券会社コード　@＝　@<BR>
     *                  株式注文入力通知キューParams.証券会社コード<BR>
     * 　@　@　@　@　@　@　@部店コード　@　@　@＝　@<BR>
     *                  株式注文入力通知キューParams.部店コード<BR>
     * 　@　@　@　@　@　@　@市場コード　@　@　@＝　@<BR>
     *                  信用取引注文入力通知データアダプタ.get市場コード()<BR>
     * 　@　@　@　@　@　@　@受付時間区分    ＝　@"01：株式・信用"<BR>
     * 　@　@　@　@　@　@　@銘柄コード　@　@　@＝　@"0：DEFAULT"<BR>
     * 　@　@　@　@　@　@　@注文受付商品　@  ＝　@"03：信用取引"<BR>
     * 　@　@　@　@　@　@　@注文受付トランザクション　@＝　@(*)<BR>
     * <BR>
     * 　@　@(*)注文受付トランザクション<BR>
     * 　@　@　@[信用取引注文入力通知データアダプタ.is新規建注文 == trueの場合]<BR>
     * 　@　@　@　@　@[a.信用取引注文入力通知データアダプタ.is買建 == trueの場合]<BR>
     * 　@　@　@　@　@　@　@"01：買付"をセット。<BR>
     * 　@　@　@　@　@[a.以外の場合]<BR>
     * 　@　@　@　@　@　@　@"02：売付"をセット。<BR>
     * 　@　@　@[信用取引注文入力通知データアダプタ.is返済注文 == trueの場合]<BR>
     * 　@　@　@　@　@"03：返済"をセット。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引カレンダコンテキストをセットする。<BR>
     * 設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時をセットする。<BR>
     * <BR>
     * ２－１）　@取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ２－２）　@受付日時として、SONAR側での発注日時(＝発注日時<BR>
     * (biz_datetime))を使用する為、<BR>
     * 　@　@　@　@　@取引時間管理.setTimestamp()<BR>
     *              にて設定された受付日時を上書きする。（※注　@を参照）<BR>
     * <BR>
     * 　@　@　@　@　@ThreadLocalSystemAttributesRegistry.setAttribute( )にて、<BR>
     * 　@　@　@　@　@株式注文入力通知キューParams.発注日時 をセットする。<BR>
     * 　@　@　@　@　@設定キー：　@取引時間管理.TIMESTAMP_TAG<BR>
     * <BR>
     * ○ ※注 ○<BR>
     * SONAR側から受領した受注日時(create_datetime)は<BR>
     * 登録者による手入力項目（SONAR側入力チェックほとんど無し）であるため、<BR>
     * 取引銘柄取得のsystem_timestampとしては使用できない。<BR>
     * しかし、SONAR側から受領した発注日時(biz_datetime)は<BR>
     * SONARシステムが発注時に自動セットする項目であり、<BR>
     * YYYYMMDDには当日日付が、<BR>
     * HHMMSSには当日の開局前～開局時間帯の時間が入ってくるため、<BR>
     * このサービスにおいては発注日時(biz_datetime)を<BR>
     * 取引銘柄取得のsystem_timestampとして使用する。<BR>
     * <BR>
     * ３）　@口座をロックする。 <BR>
     * <BR>
     * 拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * ※引数は株式注文入力通知キューParamsより編集。 <BR>
     * <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 4057B1EE010C
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {  
        final String STR_METHOD_NAME = getClass().getName() + ".onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);    
        //１）　@取引カレンダコンテキストに内容をセットする。
        //サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        if (!(l_serviceParams[0] instanceof WEB3MarginOrderNotifyDataAdapter))
        {
            log.error("パラメータタイプ不正。",
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,STR_METHOD_NAME));
            return null;
        } 
        WEB3MarginOrderNotifyDataAdapter l_adapter = (WEB3MarginOrderNotifyDataAdapter)l_serviceParams[0];
        HostEqtypeOrderReceiptParams l_orderReceiptParams = l_adapter.getDataSourseObject();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
      
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext(); 
        // * 　@－信用取引注文入力通知データアダプタ.株式注文入力通知キューParamsの<BR>
        // *   内容より<BR>
        // * 　@取引カレンダコンテキストのプロパティをセットする。<BR>
        // * 　@証券会社コード　@＝　@<BR>
        // *   株式注文入力通知キューParams.証券会社コード<BR>
        // * 　@部店コード　@　@　@＝　@<BR>
        // *   株式注文入力通知キューParams.部店コード<BR>
        // * 　@市場コード　@　@　@＝　@<BR>
        // *   信用取引注文入力通知データアダプタ.get市場コード()<BR>
        // * 　@受付時間区分    ＝　@"01：株式・信用"<BR>
        // * 　@銘柄コード　@　@　@＝　@"0：DEFAULT"<BR>
        // * 　@注文受付商品　@  ＝　@"03：信用取引"<BR>
        // * 　@注文受付トランザクション　@＝　@(*)<BR>  
        l_context.setInstitutionCode(l_orderReceiptParams.getInstitutionCode());
        l_context.setBranchCode(l_orderReceiptParams.getBranchCode());
        try
        {
            l_context.setMarketCode(l_adapter.getMarketCode());
        }
        catch (WEB3BaseException l_exp)
        {
            throw new WEB3BaseRuntimeException(
                l_exp.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        //* 　@(*)注文受付トランザクション<BR>
        //* 　@[信用取引注文入力通知データアダプタ.is新規建注文 == trueの場合]<BR>
        //* 　@[a.信用取引注文入力通知データアダプタ.is買建 == trueの場合]<BR>
        //* 　@"01：買付"をセット。<BR>
        //* 　@[a.以外の場合]<BR>
        //* 　@"02：売付"をセット。<BR>
        //* 　@信用取引注文入力通知データアダプタ.is返済注文 == trueの場合]<BR>
        //* 　@"03：返済"をセット。<BR>
        String l_transaction = null;

        if (l_adapter.isOpenMarginOrder())
        {
            if (l_adapter.isLong())
            {
                l_transaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            else
            {
                l_transaction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }
        }
        if (l_adapter.isCloseMarginOrder())
        {
            l_transaction = WEB3OrderAccTransactionDef.CLOSE_MARGIN; 
        }
        l_context.setOrderAcceptTransaction(l_transaction);

        //* －ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
        //* 取引カレンダコンテキストをセットする。<BR>
        //* 設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
        //* <BR>
        //* ２）　@受付日時をセットする。<BR>
        //* <BR>
        //* ２－１）　@取引時間管理.setTimestamp()をコールする。<BR>
        //* <BR>
        //* ２－２）　@受付日時として、SONAR側での発注日時(＝発注日時<BR>
        //* (biz_datetime))を使用する為、<BR>
        //* 取引時間管理.setTimestamp()<BR>
        //* にて設定された受付日時を上書きする。（※注　@を参照）<BR>
        //* <BR>
        //* ThreadLocalSystemAttributesRegistry.setAttribute( )にて、<BR>
        //* 株式注文入力通知キューParams.発注日時 をセットする。<BR>
        //* 設定キー：　@取引時間管理.TIMESTAMP_TAG<BR>
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_orderReceiptParams.biz_datetime);   
			//l_orderReceiptParams.biz_datetime);      
        }
        catch (WEB3BaseException l_wbex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_wbex);
            throw new WEB3BaseRuntimeException(
                l_wbex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbex.getMessage(),
                l_wbex);  
        }

        //* ○ ※注 ○<BR>
        //* SONAR側から受領した受注日時(create_datetime)は<BR>
        //* 登録者による手入力項目（SONAR側入力チェックほとんど無し）であるため、<BR>
        //* 取引銘柄取得のsystem_timestampとしては使用できない。<BR>
        //* しかし、SONAR側から受領した発注日時(biz_datetime)は<BR>
        //* SONARシステムが発注時に自動セットする項目であり、<BR>
        //* YYYYMMDDには当日日付が、<BR>
        //* HHMMSSには当日の開局前～開局時間帯の時間が入ってくるため、<BR>
        //* このサービスにおいては発注日時(biz_datetime)を<BR>
        //* 取引銘柄取得のsystem_timestampとして使用する。<BR>    
        
        // ３）　@口座をロックする。
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            l_accountManager.lockAccount(
                l_orderReceiptParams.getInstitutionCode(),
                l_orderReceiptParams.getBranchCode(),
                l_orderReceiptParams.getAccountCode());
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
                
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 4057B1EE011B
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {        
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
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
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 4125A8CD01D7
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
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
        log.exiting(STR_METHOD_NAME);
    }
}
@
