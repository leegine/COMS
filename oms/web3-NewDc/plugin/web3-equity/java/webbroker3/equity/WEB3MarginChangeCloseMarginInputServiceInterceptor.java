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
filename	WEB3MarginChangeCloseMarginInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : *信用取引訂正返済入力サービスインタセプタクラス<BR>
                 :(WEB3MarginChangeCloseMarginInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23 li-songfeng (中訊) 新規作成
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引訂正返済入力サービスインタセプタ）。<BR>
 * <BR>
 * 信用取引訂正返済入力サービスインタセプタクラス
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginInputServiceInterceptor implements Interceptor 
{ 
   /**
    * (ログユーティリティ)<BR>
    */
   private static WEB3LogUtility log =
      WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginInputServiceInterceptor.class);

    
    /**
     * @@roseuid 4142B67A02E9
     */
    public WEB3MarginChangeCloseMarginInputServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、<BR>
     *     OpLoginSecurityServiceの内容より取引時間コ<BR>
     *     ンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     *      OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = null<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”05：訂正”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *       にて取引時間コン<BR>テキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object<BR>
     * @@roseuid 407CB3390088
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {        
        final String STR_METHOD_NAME =
            getClass().getName() + ".onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);    
        //１）　@取引カレンダコンテキストに内容をセットする。
        //サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        if (!(l_serviceParams[0] instanceof WEB3MarginCloseMarginChangeInputRequest))
        {
            log.error("パラメータタイプ不正。",
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,STR_METHOD_NAME));
            return null;
        }            
        long l_lngAccountId;                    // 口座コード
        String l_strInstitutionCode = null;     // 証券会社コード
        String l_strBranchCode = null;        // 部店コード
        OpLoginSecurityService l_opLoginSec =
             (OpLoginSecurityService) Services.getService(
             OpLoginSecurityService.class);
        l_lngAccountId = l_opLoginSec.getAccountId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        try
        {
            MainAccount l_acc;
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            l_strBranchCode = l_acc.getBranch().getBranchCode();
        }
        catch(NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw  new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_nfe.getMessage(),
            l_nfe);
        }
        WEB3GentradeTradingClendarContext l_context =
                   new WEB3GentradeTradingClendarContext();
        //  * 　@取引カレンダコンテキスト.証券会社コード = <BR>
        //  *      OpLoginSecurityServiceより編集<BR>
        l_context.setInstitutionCode(l_strInstitutionCode);
        //  * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
        l_context.setBranchCode(l_strBranchCode);
        //  * 　@取引カレンダコンテキスト.市場コード = null<BR>
        l_context.setMarketCode(null);
        //  * 　@取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”<BR>
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        //  * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”<BR>
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        //  * 　@取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”<BR>
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        //  * 　@取引カレンダコンテキスト.注文受付トランザクション = ”05：訂正”<BR>
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
        // 取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
              WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
        //* ２）　@受付日時、日付ロールをセットする。<BR>
        //   * 　@－取引時間管理.setTimestamp()をコールする。<BR>
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {   
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() +"." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);      
        }        
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
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 407CB33900A8
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
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 407CB33900B7
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
