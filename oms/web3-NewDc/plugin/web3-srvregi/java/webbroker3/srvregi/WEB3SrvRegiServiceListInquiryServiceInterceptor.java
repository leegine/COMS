head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceListInquiryServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用サービス一覧照会サービスインタセプタ(WEB3SrvRegiServiceListInquiryServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 李頴淵 新規作成
*/

package webbroker3.srvregi;

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
 * (サービス利用サービス一覧照会サービスインタセプタ)<BR>
 * サービス利用サービス一覧照会サービスインタセプタクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiServiceListInquiryServiceInterceptor implements Interceptor 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiServiceListInquiryServiceInterceptor.class);
    
    /**
     * @@roseuid 416F51C70167
     */
    public WEB3SrvRegiServiceListInquiryServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR> 
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”27：サービス利用”<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”21：サービス利用”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07:照会”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。  <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * @@return Object
     * @@roseuid 40F7834C00DC
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        //取引カレンダコンテキストを生成する
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        try
        {
            //FinAppサービスを取得
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_opLoginSec.getAccountId());    //throw NotFoundException
            
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode()); 
            
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 
            l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
            
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.受付時間区分 = ”27：サービス利用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SRVREGI);
            
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = ”21：サービス利用”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.SRV_REGI);
            
            //取引カレンダコンテキスト.注文受付トランザクション = ”07:照会”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            
            //取引時間コンテキストをセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context
            );
            
            //受付日時、日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();   //WEB3BaseException
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,  
                STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(), 
                STR_METHOD_NAME); 
        }
              
        log.exiting(STR_METHOD_NAME);
        return l_context;       
     
    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCallリターン値)<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * @@roseuid 40F7834C00EC
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //取引カレンダコンテキスト、および時価のクリア処理。

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
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * @@roseuid 40F7834C00FB
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //取引カレンダコンテキスト、および時価のクリア処理。

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
