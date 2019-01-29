head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.30.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSpsecTransferForceUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定口座振替強制UnitServiceインタセプタ(WEB3AioSpsecTransferForceUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/06 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
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
 * (特定口座振替強制UnitServiceインタセプタ)<BR>
 * 特定口座振替強制UnitServiceインタセプタクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceUnitServiceInterceptor implements Interceptor 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSpsecTransferForceUnitServiceInterceptor.class);
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     *（xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     *１）　@取引カレンダコンテキストに内容をセットする。 <BR>
     *　@－サービスの引数[0]を特定口座強制振替キューParamsオブジェクトにキャストする。<BR>
     *　@－注文単位の内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     *　@取引カレンダコンテキスト.証券会社コード = <BR>
     *		特定口座強制振替キューParams.証券会社コード <BR>
     *　@取引カレンダコンテキスト.部店コード = 特定口座強制振替キューParams.部店コード <BR>
     *　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     *　@取引カレンダコンテキスト.受付時間区分 = ”20：証券振替” <BR>
     *　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     *　@取引カレンダコンテキスト.注文受付商品 = ”11：証券振替” <BR>
     *　@取引カレンダコンテキスト.注文受付トランザクション = ”09：振替” <BR>
     * <BR>
     *　@－ThreadLocalSystemAttributesRegistry.setAttribute( ) <BR>
     *	  にて取引時間コンテキストをセットする。 <BR>
     *	設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     *２）　@受付日時、日付ロールをセットする。 <BR>
     *　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     *３）　@口座をロックする。 <BR>
     *　@－拡張アカウントマネージャ.lock口座( <BR>
     *		証券会社コード, 部店コード, 口座コード)をコールする。 <BR>
     *  ※引数は特定口座強制振替キューParamsより編集。 <BR>
     *  <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * @@return Object
     * @@roseuid 415797AA00CC
     */
    public Object onCall(Method l_method, Object[] l_obj) 
    {
        String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_obj)";
        log.entering(STR_METHOD_NAME);
        
        if (l_obj == null || l_obj.length == 0)
        {
            log.debug("該当パラメータにNull値は設定できません。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);         
        }
        
        
        HostSpsecTransNotifyParams l_hostSpsecTransNotifyParams = null;
        if (l_obj[0] instanceof HostSpsecTransNotifyParams)
        {
            l_hostSpsecTransNotifyParams =
                (HostSpsecTransNotifyParams)l_obj[0];               
        }
        else 
        {
            log.debug("error in get necessory request");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        //注文単位の内容より取引時間コンテキストのプロパティをセットする。
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
            
        //証券会社コード = 特定口座強制振替キューParams.証券会社コード
        String l_strInstitutionCode = l_hostSpsecTransNotifyParams.getInstitutionCode();
        
        //部店コード = 特定口座強制振替キューParams.部店コード
        String l_strBranchCode = l_hostSpsecTransNotifyParams.getBranchCode();
            
        //取引カレンダコンテキスト.set証券会社コード
        l_context.setInstitutionCode(l_strInstitutionCode);
        //取引カレンダコンテキスト.set部店コード
        l_context.setBranchCode(l_strBranchCode);
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //取引カレンダコンテキスト.受付時間区分 = ”20：証券振替” 
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SECURITY_TRANSFER);
        //取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        //取引カレンダコンテキスト.注文受付商品 = ”11：証券振替” 
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.TRASUT_SUBSTITUTION_CHANGE);
        //取引カレンダコンテキスト.注文受付トランザクション = ”09：振替”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.TRANSFER);
    
        //ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする 
        //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
        //２）受付日時、日付ロールをセットする
        //取引時間管理.setTimestamp()をコールする 
        //throw SystemLayerException  
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("__error in setTimestamp__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }   
        
        //３）口座をロックする。 
        //　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
        //  ※引数は特定口座強制振替キューParamsより編集。
        
        //a> FinApp, GenTradeAccountManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
        //b> 口座コード
        String l_strAccountCode = l_hostSpsecTransNotifyParams.getAccountCode();
            
        try
        {
            l_accountManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in lockAccount__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
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
        String STR_METHOD_NAME = "onThrowable(" +
                "Object l_objOnCall, Throwable l_throwable)";
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
