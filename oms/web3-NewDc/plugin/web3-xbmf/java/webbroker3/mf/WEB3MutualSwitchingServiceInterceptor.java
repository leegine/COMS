head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託乗換サービスインタセプタクラス(WEB3MutualSwitchingServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/24 韋念瓊 (中訊) レビュー    
*/

package webbroker3.mf;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * 投資信託乗換サービスインタセプタクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwitchingServiceInterceptor implements Interceptor 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingServiceInterceptor.class);
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストの<BR>
     * 　@　@プロパティをセットする。<BR>
     * <BR>
     * 　@　@　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = WEB3TradingTimeTypeDef.投資信託<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付商品 = ”07：投資信託”<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = ”02：売付（新規建売）”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする。<BR>
     * 　@　@［setAttributeに渡すパラメタ］<BR>
     * 　@　@　@設定キー： 取引時間コンテキスト.TRADING_CALENDAR_TAG<BR>
     * 　@　@　@設定値： 取引時間コンテキスト<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）　@リクエストデータの型が「投信乗換完了リクエスト」の場合のみ、口座をロックする。 <BR>
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 <BR>
     * 　@　@※引数はOpLoginSecurityServiceより編集。<BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 40557E6002FC
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParam == null)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (l_serviceParam.length == 0)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenRequest l_request = null;
        if(l_serviceParam[0] instanceof WEB3GenRequest)
        {
            l_request = (WEB3GenRequest) l_serviceParam[0];
        }
        else
        {
            log.debug(" パラメータError ");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //拡張アカウントマネージャを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_manager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        //証券会社コード取得用
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);                
        String l_strInstitutionCode = "";
        String l_strBranchCode = "";
        
        try
        {
            //証券会社コード： OpLoginSecurityServiceより編集
            MainAccount l_mainAccount = l_manager.getMainAccount(l_opLoginSec.getAccountId());
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            
            //部店コード = OpLoginSecurityServiceより編集
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
              
            //－リクエストデータの内容と、OpLoginSecurityServiceの内容より
            //取引時間コンテキストのプロパティをセットする
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();  
                
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.受付時間区分 = WEB3TradingTimeTypeDef.投資信託
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            
            //取引カレンダコンテキスト.注文受付商品 = ”07：投資信託”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MUTUAL_FUND);
            
            //取引カレンダコンテキスト.注文受付トランザクション = ”02：売付（新規建売）”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
          
            //-ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセット
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
            
            WEB3GentradeTradingTimeManagement.setTimestamp(); 
            
            //３）　@リクエストデータの型が「投信乗換完了リクエスト」の場合のみ、口座をロックする
            if(l_request instanceof WEB3MutualSwitchingCompleteRequest)
            {
                String l_strAccountCode = l_mainAccount.getAccountCode();                
                //口座をロックする。 
                l_manager.lockAccount(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);                
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__an unexpected error__ when "
                    + " l_manager.getInstitution(l_strInstitutionCode)"
                    + " with l_strInstitutionCode = "
                    + l_strInstitutionCode,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()",l_ex);
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
     * サービスメソッド終了時にコールされる。
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 40557E60031B
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
            
        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 40557E60032B
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
        log.exiting(STR_METHOD_NAME);      
    }
}
@
