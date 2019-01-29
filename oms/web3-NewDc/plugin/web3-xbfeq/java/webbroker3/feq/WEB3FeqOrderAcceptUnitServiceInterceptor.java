head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文受付１件サービスインタセプタ(WEB3FeqOrderAcceptUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 徐大方 (中訊) 新規作成
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文受付１件サービスインタセプタ)<BR>
 * 外国株式注文受付１件サービスインタセプタ<BR>
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptUnitServiceInterceptor.class);
        
    /**
     * @@roseuid 42D0CED203D8
     */
    public WEB3FeqOrderAcceptUnitServiceInterceptor() 
    {
     
    }

    /**
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@引数.サービスの引数[0]より、注文単位を取得する。<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－１で取得した注文単位より、<BR>
     * 　@　@以下の通り取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 注文単位.get証券会社コード()の戻り値<BR>
     * 　@取引カレンダコンテキスト.部店コード = 注文単位.get部店コード()の戻り値<BR>
     * 　@取引カレンダコンテキスト.市場コード = 注文単位.get市場().get市場コード()の戻り値<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = "10：外国株式"<BR>
     * 　@取引カレンダコンテキスト.商品コード = 0：DEFAULT<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = "04：外国株"<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする。<BR>
     * 　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@WEB3GentradeTradingTimeManagement.setBusinessTimestamp()<BR>
     * 　@をコールし、業務日時をセットする。<BR>
     * <BR>
     * ４）　@口座をロックする。<BR>
     * 　@－拡張アカウントマネージャ.lock口座()をコールする。<BR>
     * 　@　@※ 引数は取得した注文単位より編集。<BR>
     * <BR> 
     * @@param l_method - (サービスメソッド)<BR>       
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - (サービスの引数)<BR>
     *  サービスの引数配列<BR>
     * @@return Object
     * @@roseuid 429FED39003F
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１）　@引数.サービスの引数[0]より、注文単位を取得する。
            WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)l_serviceParams[0];
            
            //２）　@取引カレンダコンテキストに内容をセットする。
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            //取引カレンダコンテキスト.証券会社コード = 注文単位.get証券会社コード()の戻り値 
            l_context.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
            
            //取引カレンダコンテキスト.部店コード = 注文単位.get部店コード()の戻り値 
            l_context.setBranchCode(l_feqOrderUnit.getBranchCode());
            
            //取引カレンダコンテキスト.市場コード = 注文単位.get市場().get市場コード()の戻り値 
            l_context.setMarketCode(l_feqOrderUnit.getMarket().getMarketCode());
            
            //取引カレンダコンテキスト.受付時間区分 = "10：外国株式"
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            
            //取引カレンダコンテキスト.商品コード = 0：DEFAULT 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = "04：外国株"
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
            
            //取引カレンダコンテキスト.注文受付トランザクション = null
            l_context.setOrderAcceptTransaction(null);
            
            //取引時間コンテキストをセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(
    	            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
    	            l_context);
            
            //３） WEB3GentradeTradingTimeManagement.setBusinessTimestamp()
            //をコールし、業務日時をセットする。
            WEB3GentradeTradingTimeManagement.setBusinessTimestamp(); 
            
            //４） 口座をロックする。
            //－拡張アカウントマネージャ.lock口座()をコールする。
            //※引数は取得した注文単位より編集。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            l_accountManager.lockAccount(
                l_feqOrderUnit.getInstitutionCode(), 
                l_feqOrderUnit.getBranchCode(), 
                l_feqOrderUnit.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
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
     * <BR>
     * @@param l_context - (onCall返却値)<BR>
     * onCall返却値<BR>
     * @@param l_returnValue - (サービスメソッド返却値)<BR>
     * サービスメソッド返却値<BR>
     * @@roseuid 429FED390042
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
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
     * @@param l_obj - (onCall返却値)<BR>
     * onCall返却値<BR>
     * @@param l_throwable - (例外)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 429FED39004E
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
