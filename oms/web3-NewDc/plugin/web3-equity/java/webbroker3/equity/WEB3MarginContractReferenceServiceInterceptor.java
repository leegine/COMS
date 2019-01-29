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
filename	WEB3MarginContractReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引建株照会サービスインタセペタクラス(WEB3MarginContractReferenceServiceInterceptor.java)
Author Name      : 2004/09/24 盧法@旭(中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity;

import java.lang.reflect.Method;
import java.util.Hashtable;

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
import webbroker3.equity.message.WEB3MarginContractReferenceRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引建株照会サービスインタセプタ）。<BR>
 * <BR>
 * 信用取引建株照会サービスインタセプタクラス
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3MarginContractReferenceServiceInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginContractReferenceServiceInterceptor.class);
    /**
     * @@roseuid 4142B32B02B9
     */
    public WEB3MarginContractReferenceServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@時価の変数をセットする。<BR>
     * ThreadLocalSystemAttributesRegistry.setAttribute()にてThreadLocalに<BR>
     * 時価の変数をセットする<BR>
     * 　@　@　@　@　@　@　@設定キー：　@CURRENT_PRICE<BR>
     * 　@　@　@　@　@　@　@値：　@Hashtable（新規に生成したHashtable）<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、
     *       OpLoginSecurityServiceの内容より取引時間コンテキストの<BR>
     * 　@　@プロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     *       OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = null(*)<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR>
     * <BR>
     * 　@(*) 市場コードは、execute( )からコールされるメソッド内で、<BR>
     * 　@　@　@建株より取得して設定する。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object<BR>
     * @@roseuid 4056A0D300D4
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
            
        log.entering(STR_METHOD_NAME);
        if (l_serviceParams[0] == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        //1
        ThreadLocalSystemAttributesRegistry.setAttribute("CURRENT_PRICE",new Hashtable());
        //１）　@取引カレンダコンテキストに内容をセットする。
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        //サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        try
        {      
            Object l_request = l_serviceParams[0];
            log.debug("OnCallの引数タイプをチェック_start");    
            if (l_request == null)
            {   
                log.error("parameter is null type");            
                throw new WEB3BaseRuntimeException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       this.getClass().getName() + "." + STR_METHOD_NAME);   
                                  
            }
                    
            if (l_request instanceof WEB3MarginContractReferenceRequest)
            {
                WEB3MarginContractReferenceRequest  l_request0 =
                        (WEB3MarginContractReferenceRequest) l_request;                 
            }
            else
            {
                log.error("パラメータタイプ不正。");
                throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        this.getClass().getName() + "." + STR_METHOD_NAME);                                               
            }            
            log.debug("OnCallの引数タイプをチェック_end");              
            // 　@－リクエストデータの内容と、OpLoginSecurityServiceの<BR>
            // 内容より取引時間コンテキストのプロパティをセットする。<BR>
            String l_strInstitutionCode = null; //証券会社コード
            String l_strBranchCode      = null; //部店コード
            long l_lngAccountId = 0;    // 口座コード
            FinApp l_finApp = null;         
            AccountManager l_accMgr = null; 
            MainAccount l_mainAccount = null;

            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                   
            //MainAccountを取得
            log.debug("MainAccountを取得");
            l_lngAccountId = l_opLoginSec.getAccountId();
            l_finApp = (FinApp) Services.getService(FinApp.class);
            l_accMgr = l_finApp.getAccountManager();
                        
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);

            log.debug("l_orderUnit取得しました。");
            //証券会社コードを取得
            log.debug("証券会社コードを取得");
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            //部店コードを取得
            log.debug("部店コードを取得");
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
               
            log.debug("取引カレンダコンテキストに内容をセットする");
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode); 
            //取引カレンダコンテキスト.市場コード = null
            l_context.setMarketCode(null);
            //取引カレンダコンテキスト.受付時間区分 =   ”01：株式・信用”）”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY); 
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);        
            //取引カレンダコンテキスト.注文受付商品 = ”03：信用取引
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);         
            //取引カレンダコンテキスト.注文受付トランザクション = ”07：照会
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            
            //取引時間コンテキストをセットする。
            log.debug("取引時間コンテキストをセットする");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);         
            
            //3）　@受付日時、日付ロールをセットする。
            log.debug("受付日時、日付ロールをセットする");
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            log.exiting(STR_METHOD_NAME);
            return l_context;
             
        }
        catch (NotFoundException l_ex)
        {
            
            log.debug("__NotFoundException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() +"." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex); 
            
        }       
        catch (WEB3BaseException l_ex)
        {   
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() +"." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);      
        }        
    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキスト、および時価のクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE<BR>
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 4056A0D300E4
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object,Object)";
            
        log.entering(STR_METHOD_NAME);
        log.debug("取引時間管理.TIMESTAMP_TAG = " + WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        log.debug("取引時間管理.OFFSET_TAG = " + WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.debug("取引時間管理.TRADING_CAL_CONTEXT_PATH = " + WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキスト、および時価のクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE<BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 4056A0D300F4
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
            
        log.entering(STR_METHOD_NAME);
        
        log.debug("取引時間管理.TIMESTAMP_TAG = " + WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        log.debug("取引時間管理.OFFSET_TAG = " + WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.debug("取引時間管理.TRADING_CAL_CONTEXT_PATH = " + WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);               
    }
}
@
