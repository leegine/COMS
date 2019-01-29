head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinSettleCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文要求中止サービスインタセプタクラス(WEB3AioCashinSettleCancelServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 屈陽 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー    
*/

package webbroker3.aio;

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
 * (注文要求中止サービスインタセプタ)<BR>
 * 注文要求中止サービスインタセプタクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCancelServiceInterceptor implements Interceptor 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleCancelServiceInterceptor.class);       
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。 <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityService<BR>
     * の内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     * OpLoginSecurityServiceより編集<BR> 
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     * OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”14：入金” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”14：入出金” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”10：入金” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR> 
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * @@return Object
     * @@roseuid 4119A89203AB
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        String l_strMethodName = "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(l_strMethodName);                

        //１）取引カレンダコンテキストに内容をセットする
        
        //リクエストデータの内容と、OpLoginSecurityServiceの内容より
        //取引時間コンテキストのプロパティをセットする
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(
            OpLoginSecurityService.class);
        
        long l_lngAccountId = l_opLoginSecurityService.getAccountId();
        
        try
        {
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            //証券会社コードを取得する
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            //部店コードを取得する
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
    
            //取引カレンダコンテキスト.set証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.set部店コード
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”14：入金” 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.RECIEPT);
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.注文受付商品 = ”14：入出金” 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.PAYMENT);
            //取引カレンダコンテキスト.注文受付トランザクション = ”10：入金”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CASH_IN);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする 
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //２）受付日時、日付ロールをセットする
            //取引時間管理.setTimestamp()をコールする  
            WEB3GentradeTradingTimeManagement.setTimestamp();            
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getMainAccount", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
                        
        log.exiting(l_strMethodName);
        
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。  <BR>
     *  <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。  <BR>
     *  <BR>
     * 取引時間管理.TIMESTAMP_TAG  <BR>
     * 取引時間管理.OFFSET_TAG  <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCallリターン値)<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * @@roseuid 4119A89300AD
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        String l_strMethodName = "onReturn(Object l_context, Object l_returnValue)";
        log.entering(l_strMethodName);
        
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

        log.exiting(l_strMethodName);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     *  <BR>
     * 取引カレンダコンテキストクリア処理。 <BR> 
     *  <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。  <BR>
     *  <BR>
     * 取引時間管理.TIMESTAMP_TAG  <BR>
     * 取引時間管理.OFFSET_TAG  <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * @@roseuid 4119A893014A
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        String l_strMethodName = "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(l_strMethodName);
        
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

        log.exiting(l_strMethodName);
    }
}
@
