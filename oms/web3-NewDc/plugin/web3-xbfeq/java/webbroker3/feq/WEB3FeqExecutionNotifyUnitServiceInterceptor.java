head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式出来通知１件サービスインタセプタ (WEB3FeqExecutionNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 齊珂 (中訊) 新規作成
Revesion History : 2010/01/25 張騰宇 (中訊) 仕様変更モデル536
Revesion History : 2010/03/05 武波 (中訊)【外国株式】仕様変更管理台帳（モデル）No.541
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (外国株式出来通知１件サービスインタセプタ)<BR>
 * 外国株式出来通知１件サービスインタセプタ<BR>
 *   
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3FeqExecutionNotifyUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecutionNotifyUnitServiceInterceptor.class);
        
    /**
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@引数.サービスの引数[0]より、注文単位を取得する。<BR>
     * <BR>
     * ２）　@引数.サービスの引数[1]より、外株出来通知キューを取得する。<BR>
     * <BR>
     * ３）　@取引カレンダコンテキストに内容をセットする。<BR>
     *   −１、２で取得した注文単位と外株出来通知キューより、<BR>
     *     以下の通り取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>    
     * 取引カレンダコンテキスト.証券会社コード = 外株出来通知キュー.get証券会社コード()の戻り値<BR>
     * 取引カレンダコンテキスト.部店コード = 外株出来通知キュー.get部店コード()の戻り値<BR>
     * 取引カレンダコンテキスト.市場コード = 注文単位.get市場().get市場コード()の戻り値<BR>
     * 取引カレンダコンテキスト.受付時間区分 = "10：外国株式"<BR>
     * 取引カレンダコンテキスト.商品コード = 0：DEFAULT <BR>
     * 取引カレンダコンテキスト.注文受付商品 = "04：外国株"<BR>
     * 取引カレンダコンテキスト.注文受付トランザクション = null<BR>
     * <BR> 
     * −ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする。<BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ４）　@日付ロールをセットする。<BR>
     * 　@−取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ５）ThreadLocalSystemAttributesRegistry.setAttribute()にてvalidate当日為替レートに"TRUE"をセットする。<BR>
     * -ThreadLocalSystemAttributesRegistry.setAttribute(<BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG, <BR>
     * 　@　@　@　@BooleanEnum.TRUE)<BR>
     * ６）ThreadLocalSystemAttributesRegistry.setAttribute()にて、<BR>
     * 当日為替登録フラグに"引数.当日為替登録フラグ"をセットする。<BR>
     * -ThreadLocalSystemAttributesRegistry.setAttribute(<BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG,new Boolean(引数.当日為替登録フラグ))<BR>
     * <BR>
     * ７）　@口座をロックする。 <BR>
     * 　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@※ 引数は外株出来通知キューより編集。 <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - (サービスの引数)<BR>
     * サービスの引数配列<BR>
     * @@return Object
     * @@roseuid 429FED39003F
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {          
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null || l_serviceParams.length != 4)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        WEB3GentradeTradingClendarContext l_context = null;
        try
		{  
            l_context = new WEB3GentradeTradingClendarContext();
            
        	//引数.サービスの引数[0]より、注文単位を取得する。
	        WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_serviceParams[0];
	        
	        //引数.サービスの引数[1]より、外株出来通知キューを取得する。
	        HostFeqOrderExecNotifyParams l_params = (HostFeqOrderExecNotifyParams)l_serviceParams[1];
	        
	        if (l_params != null)
	        {
		        //取引カレンダコンテキスト.証券会社コード = 外株出来通知キュー.get証券会社コード()の戻り値
		        l_context.setInstitutionCode(l_params.getInstitutionCode());
		        
		        //取引カレンダコンテキスト.部店コード = 外株出来通知キュー.get部店コード()の戻り値
		        l_context.setBranchCode(l_params.getBranchCode());
	        }
	        
	        //取引カレンダコンテキスト.市場コード = 注文単位.get市場().get市場コード()の戻り値
	        if (l_orderUnit != null)
	        {
				l_context.setMarketCode(l_orderUnit.getMarket().getMarketCode());
	        }
			
	        //取引カレンダコンテキスト.受付時間区分 = "10：外国株式"
	        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
	        
	        //取引カレンダコンテキスト.商品コード = 0：DEFAULT 
	        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
	        
	        //取引カレンダコンテキスト.注文受付商品 = "04：外国株"
	        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
	        
	        //取引カレンダコンテキスト.注文受付トランザクション = null
	        l_context.setOrderAcceptTransaction(null);
	        
	        // 取引時間コンテキストをセットする
	        ThreadLocalSystemAttributesRegistry.setAttribute(
	            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
	            l_context);
	    
	        //取引時間管理
	        WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

            //ThreadLocalSystemAttributesRegistry.setAttribute()にてvalidate当日為替レートに"TRUE"をセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG,
                BooleanEnum.TRUE);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG,
                l_serviceParams[3]);

	        //拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            String l_strInstitutionCode = l_params.getInstitutionCode();
            String l_strBranchCode = l_params.getBranchCode();
            String l_strAccountCode = l_params.getAccountCode();
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            if (l_accountManager == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                    "拡張アカウントマネージャが存在しない。");
            }
            l_accountManager.lockAccount(
            	l_strInstitutionCode,
            	l_strBranchCode,
            	l_strAccountCode);//WEB3BaseException
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
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * 外国株式約定経路区分 <BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG<BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG<BR>
     * @@param l_context - (onCall返却値)<BR>
     * onCall返却値<BR>
     * @@param l_returnValue - (サービスメソッド返却値)<BR>
     * サービスメソッド返却値<BR>
     * @@roseuid 429FED390042
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
            
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
        //外国株式約定経路区分
        ThreadLocalSystemAttributesRegistry.setAttribute(
        	WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
            null);

        //当日為替レートチェックフラグ
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG,
            null);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * 外国株式約定経路区分 <BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG<BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG<BR>
     * @@param l_obj - (onCall返却値)<BR>
     * onCall返却値<BR>
     * @@param l_throwable - (例外)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 429FED39004E
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
        //外国株式約定経路区分
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
            null);

        //当日為替レートチェックフラグ
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG,
            null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
