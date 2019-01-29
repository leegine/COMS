head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.30.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOutputNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出庫通知UnitServiceインタセプタ(WEB3AioOutputNotifyUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio;

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
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (出庫通知UnitServiceインタセプタ)<BR>
 * 出庫通知UnitServiceインタセプタクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioOutputNotifyUnitServiceInterceptor implements Interceptor 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOutputNotifyUnitServiceInterceptor.class);
    
    /**
     * サービスメソッド開始時にコールされる。<BR>  
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>  
     * （xTradeカーネルよりサービス実行前に呼び出される）  <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。  <BR>
     * 　@－引数の内容より取引時間コンテキストのプロパティをセットする。 <BR> 
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 引数の証券会社コード  <BR>
     * 　@取引カレンダコンテキスト.部店コード = 引数の部店コード  <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”  <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”20：証券振替、出庫通知”  <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”  <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”11：証券振替、出庫通知”  <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”09：振替”  <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     * 取引時間コンテキストをセットする。<BR>  
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。  <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。  <BR>
     * <BR>
     * ３）　@口座をロックする。  <BR>
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)<BR>
     * をコールする。  <BR>
     *   ※引数より編集。  <BR>
     *  <BR>
     * @@param l_method
     * @@param l_obj
     * @@return Object
     * @@roseuid 415797AA00CC
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
       
        if (l_serviceParams == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_serviceParams.length == 0)
        {
            log.debug("CollectionタイプのパラメータSizeは０できない。");
               throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@取引カレンダコンテキストに内容をセットする。          
       
        String l_strInstitutionCode = (String) l_serviceParams[0];
        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);
        
        String l_strBranchCode = (String) l_serviceParams[1];
        log.debug("l_strBranchCode = " + l_strBranchCode);
        
        //－取引カレンダコンテキストにの取得
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
        
        //取引カレンダコンテキスト.証券会社コード = 引数の証券会社コード  
        l_context.setInstitutionCode(l_strInstitutionCode);
        
        //取引カレンダコンテキスト.部店コード = 引数の部店コード    
        l_context.setBranchCode(l_strBranchCode);
        
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”  
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
        //取引カレンダコンテキスト.受付時間区分 = ”20：証券振替、出庫通知” 
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SECURITY_TRANSFER);
        
        //取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //取引カレンダコンテキスト.注文受付商品 = ”11：証券振替、出庫通知” 
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.TRASUT_SUBSTITUTION_CHANGE);

        //取引カレンダコンテキスト.注文受付トランザクション = ”09：振替”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.TRANSFER);

        //－ThreadLocalSystemAttributesRegistry.setAttribute( )
        //にて取引時間コン テキストをセットする。  
        // 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
     
        //取引カレンダコンテキストに内容をセットする明細
        
        try
        {
            // ２）　@受付日時、日付ロールをセットする。 
            // 　@－取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //３）　@口座をロックする。 
            //－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
            //※ ※引数より編集。 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            String l_strAccountCode = (String) l_serviceParams[2];
            
            l_accountManager.lockAccount(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR> 
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_objOnCall
     * @@param l_object
     * @@roseuid 415797AA00CF
     */
    public void onReturn(Object l_objOnCall, Object l_object) 
    {
        String STR_METHOD_NAME = "onReturn(Object l_objOnCall, Object l_obj)";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);        
        
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
     * @@param l_objOnCall
     * @@param l_throwable
     * @@roseuid 415797AA00D2
     */
    public void onThrowable(Object l_objOnCall, Throwable l_throwable) 
    {
        String STR_METHOD_NAME = "onThrowable(Object l_objOnCall, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);    

        log.exiting(STR_METHOD_NAME);       
    }
}
@
