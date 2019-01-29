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
filename	WEB3MarginSwapMarginAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡受付一件サービスインタセプタ(WEB3MarginSwapMarginAcceptUnitServiceInterceptor.java)
Author Name      : 2004/10/08 盧法@旭(中訊) 新規作成
                   2004/12/10 岡村和明(SRA) 残案件対応 Ｎｏ.２５９
                   2004/12/21 岡村和明(SRA) JavaDoc修正
                   2004/12/29 岡村和明(SRA) onCallメソッドの変更対応
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
import webbroker3.equity.data.HostEqtypeSwapAcceptRow;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引現引現渡受付一件サービスインタセプタ）。<BR>
 * <BR>
 * 信用取引現引現渡受付一件サービスインタセプタクラス
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginSwapMarginAcceptUnitServiceInterceptor.class);

    /**
     * <p>（信用取引現引現渡受付一件サービスインタセプタ）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3MarginSwapMarginAcceptUnitServiceInterceptor() 
    {
    }
    
    /**
     * <p>（onCall）。</p>
     * <p>取引カレンダが利用するコンテキストを生成する。<br>
     * （xTradeカーネルより、サービスメソッド開始時にコールされる）<br>
     * <br>
     * １）　@取引カレンダコンテキストに内容をセットする。<br>
     * 　@－サービスの引数[0]を現引現渡受付キューParamsオブジェクトにキャストする。<br>
     * 　@－現引現渡受付キューParamsオブジェクトの内容より、<br>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。<br>
     * <br>
     * 　@取引カレンダコンテキスト.証券会社コード = <br>
     *       現引現渡受付キューParamsの同項目<br>
     * 　@取引カレンダコンテキスト.部店コード = 現引現渡受付キューParamsの同項目<br>
     * 　@取引カレンダコンテキスト.市場コード = null<br>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”19：現引・現渡” <br>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<br>
     * 　@取引カレンダコンテキスト.注文受付商品 = null<br>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null<br>
     * <br>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<br>
     * 　@　@　@取引時間コンテキストをセットする。<br>
     * 設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <br>
     * <br>
     * ２）　@受付日時、日付ロールをセットする。<br>
     * 　@－取引時間管理.setTimestamp()をコールする。<br>
     * <br>
     * ３）　@口座をロックする。<br>
     * <br>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<br>
     * 　@　@　@※引数は現引現渡受付キューParamsより編集。</p>
     * @@param l_method サービスメソッドオブジェクト
     * @@param l_serviceParams サービスメソッド引数
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        if (l_serviceParams[0] == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }               
        //１）　@取引カレンダコンテキストに内容をセットする。
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        //サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        try
        {      
            HostEqtypeSwapAcceptRow l_eqtypeSwapAcceptRow = (HostEqtypeSwapAcceptRow) l_serviceParams[0];
            log.debug("OnCallの引数タイプをチェック_start");              
            // 　@－リクエストデータの内容と、OpLoginSecurityServiceの<BR>
            // 内容より取引時間コンテキストのプロパティをセットする。<BR>

            String l_strInstitutionCode = null; //証券会社コード
            String l_strBranchCode  = null; //部店コード

            l_strInstitutionCode = l_eqtypeSwapAcceptRow.getInstitutionCode();
            l_strBranchCode = l_eqtypeSwapAcceptRow.getBranchCode();

            log.debug("取引カレンダコンテキストに内容をセットする");
            //取引カレンダコンテキスト.証券会社コード = 現引現渡受付キューParamsの同項目
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode); 
            //取引カレンダコンテキスト.市場コード = null
            l_context.setMarketCode(null);
            //取引カレンダコンテキスト.受付時間区分 =   ”19：現引・現渡” ）”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP); 
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);        
            //取引カレンダコンテキスト.注文受付商品 = null
            l_context.setOrderAcceptProduct(null);         
            //取引カレンダコンテキスト.注文受付トランザクション =null
            l_context.setOrderAcceptTransaction(null);           
            //取引時間コンテキストをセットする。
            log.debug("取引時間コンテキストをセットする");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);                     
            //2）　@受付日時、日付ロールをセットする。
            log.debug("受付日時、日付ロールをセットする");
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //3）　@口座をロックする。
            log.debug("拡張アカウントマネージャの口座をロックします。");
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            // 拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
            l_accountManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_eqtypeSwapAcceptRow.getAccountCode());
            
            log.exiting(STR_METHOD_NAME);
            return l_context;
             
        }
        catch (WEB3BaseException l_ex)
        {   
            log.error( STR_METHOD_NAME + STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() +"." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);      
        }        
    }
    
    /**
     * <p>（onReturn）。</p>
     * <p>取引カレンダコンテキストクリア処理。<br>
     * 注文受付サービス終了時にコールされる。<br>
     * <br>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<br>
     * <br>
     * 取引時間管理.TIMESTAMP_TAG <br>
     * 取引時間管理.OFFSET_TAG <br>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH</p>
     * @@param l_context onCallリターン値
     * @@param l_returnValue サービスメソッドリターン値
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object,Object)";
            
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
     * <p>（onThrowable）。</p>
     * <p>取引カレンダコンテキストクリア処理。<br>
     * サービスメソッドが例外をスローした場合にコールされる。<br>
     * <br>
     * 取引時間管理.TIMESTAMP_TAG<br>
     * 取引時間管理.OFFSET_TAG<br>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH</p>
     * @@param l_obj onCall リターン値
     * @@param l_throwable 例外オブジェクト
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
            
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
