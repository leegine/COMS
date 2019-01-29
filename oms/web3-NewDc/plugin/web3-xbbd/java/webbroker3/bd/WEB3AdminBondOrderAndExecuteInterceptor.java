head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderAndExecuteInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者新規約定入力インタセプタ(WEB3AdminBondOrderAndExecuteInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3AdminBondExecCompleteRequest;


/**
 * (管理者新規約定入力インタセプタ)<BR>
 * 管理者新規約定入力サービスインタセプタクラス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondOrderAndExecuteInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。 <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminBondOrderAndExecuteInterceptor.class);
    
    /**
     * @@roseuid 44E3510501B5
     */
    public WEB3AdminBondOrderAndExecuteInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = "0:DEFAULT"<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = "0:DEFAULT"<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = "25:債券"<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = "28:債券"<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = "00：DEFAULT"<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 <BR>
     * 設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ※以下、サービスメソッドが「債券管理者新規約定完了リクエスト」の場合のみ実施。 <BR>
     * ３）　@口座をロックする。 <BR>
     * 　@３－１）顧客オブジェクトを取得する。<BR>
     * 　@　@　@　@　@拡張アカウントマネージャ.get顧客(証券会社コード, 部店コード, 口座コード)から取得する。<BR>
     * 　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@証券会社コード：OpLoginSecurityServiceより編集 <BR>
     * 　@　@　@　@　@　@部店コード：リクエスト.顧客情報.部店コード<BR>
     * 　@　@　@　@　@　@口座コード：リクエスト.顧客情報.顧客コード<BR>
     * <BR>
     * 　@３－２）口座ロックする。<BR>
     * 　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 <BR>
     * 　@　@[引数]<BR>
     * 　@　@・証券会社コード：顧客オブジェクトから取得<BR>
     * 　@　@・部店コード：顧客オブジェクトから取得<BR>
     * 　@　@・口座コード：顧客オブジェクトから取得
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッド
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数
     * @@return Object
     * @@roseuid 44B62391028F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("CollectionタイプのパラメータSizeは０できない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
   
        try 
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            String l_strInstitutionCode = l_admin.getInstitutionCode();
            String l_strBranchCode = l_admin.getBranchCode();
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = "0:DEFAULT"
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.銘柄コード = "0:DEFAULT"
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = "25:債券"
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
            //取引カレンダコンテキスト.注文受付商品 = "28:債券"
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.BOND);
            //取引カレンダコンテキスト.注文受付トランザクション = "00：DEFAULT"
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 
            //設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_context);
            //２）　@受付日時、日付ロールをセットする。 
            //－取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException
            
            //※以下、サービスメソッドが「債券管理者新規約定完了リクエスト」の場合のみ実施。
            Object l_objRequest = l_serviceParam[0];
            if (l_objRequest instanceof WEB3AdminBondExecCompleteRequest)
            {
                WEB3AdminBondExecCompleteRequest l_request = (WEB3AdminBondExecCompleteRequest)l_objRequest;             
            
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();

                //３）　@口座をロックする。 
                //３－１）顧客オブジェクトを取得する。
                //拡張アカウントマネージャ.get顧客(証券会社コード, 部店コード, 口座コード)から取得する。
                //[引数]
                //証券会社コード：OpLoginSecurityServiceより編集 
                //部店コード：リクエスト.顧客情報.部店コード
                //口座コード：リクエスト.顧客情報.顧客コード
                WEB3GentradeMainAccount l_mainAccount = 
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                        l_strInstitutionCode, 
                        l_request.accountInfo.branchCode,
                        l_request.accountInfo.accountCode);
                
                //３－２）口座ロックする。
                //拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
                //[引数]
                //・証券会社コード：顧客オブジェクトから取得
                //・部店コード：顧客オブジェクトから取得
                //・口座コード：顧客オブジェクトから取得
                 l_accountManager.lockAccount(
                     l_mainAccount.getInstitution().getInstitutionCode(),
                     l_mainAccount.getBranch().getBranchCode(),
                     l_mainAccount.getAccountCode());
            }
            
            log.exiting(STR_METHOD_NAME);
            return null;   
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

    }
    
    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値
     * @@param  l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値
     * @@roseuid 44B6239102AF
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        
        final String STR_METHOD_NAME = " onReturn(Object, Object) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param  l_obj - (onCallリターン値)<BR>
     * onCallリターン値
     * @@param  l_throwable - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値
     * @@roseuid 44B6239102CE
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                null);

        log.exiting(STR_METHOD_NAME);
    }

}
@
