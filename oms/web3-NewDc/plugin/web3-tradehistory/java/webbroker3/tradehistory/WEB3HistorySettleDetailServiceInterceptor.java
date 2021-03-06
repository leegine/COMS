head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySettleDetailServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済明細サービスインタセプタ(WEB3HistorySettleDetailServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  賈元春(中訊) 新規作成
*/
package webbroker3.tradehistory;

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
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (決済明細サービスインタセプタ)<BR>
 * 決済明細サービスインタセプタ<BR>
 * 
 * @@author 賈元春
 * @@version 1.0
 */
public class WEB3HistorySettleDetailServiceInterceptor implements Interceptor 
{

    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistorySettleDetailServiceInterceptor.class);
    
    /**  
     * @@roseuid 4178A55C01A5
     */
    public WEB3HistorySettleDetailServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@−サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@−リクエストデータの内容と、<BR> 
     * OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”1：東京”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”22：顧客サービス”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR>
     * <BR>
     * 　@−ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@−取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param l_method - (arg0)<BR>
     * @@param l_serviceParam - (arg1)<BR>
     * @@return Object
     * @@roseuid 413EBB0F03C9
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
       
        String l_strInstitutionCode = null; //証券会社コード
        String l_strBranchCode      = null; //部店コード 
               
        try
        {            
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
                   
            //MainAccountを取得
            long l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();       
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);            
            //証券会社コードを取得
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            //部店コードを取得
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        WEB3GentradeTradingClendarContext l_context =
                         new WEB3GentradeTradingClendarContext();    
        //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集        
        l_context.setInstitutionCode(l_strInstitutionCode);
        //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode(l_strBranchCode); 
        //取引カレンダコンテキスト.市場コード = ”1：東京”
        l_context.setMarketCode(WEB3MarketCodeDef.TOKYO);
        //取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY); 
        //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        //取引カレンダコンテキスト.注文受付商品 = ”22：顧客サービス”
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
        //取引カレンダコンテキスト.注文受付トランザクション = ”07：照会
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);             

        // 取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);            
        }
        log.exiting(STR_METHOD_NAME);
        return l_context;
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
     * <BR>
     * @@param l_context - (arg0)<BR>
     * @@param l_returnValue - (arg1)<BR>
     * @@roseuid 413EBB100000
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = getClass().getName() + " onReturn(Object,Object)";
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
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_obj - (arg0)<BR>
     * @@param l_throwable - (arg1)<BR>
     * @@roseuid 413EBB100010
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = getClass().getName() + " onThrowable(Object,Throwable)";
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
