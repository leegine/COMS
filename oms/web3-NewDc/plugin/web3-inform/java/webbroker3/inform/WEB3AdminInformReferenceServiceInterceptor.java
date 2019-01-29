head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索サービスインタセプタクラス(WEB3AdminInformReferenceServiceInterceptor.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
*/

package webbroker3.inform;

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
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.inform.message.WEB3AdminInformDetailRequest;
import webbroker3.inform.message.WEB3AdminInformDownLoadRequest;
import webbroker3.inform.message.WEB3AdminInformInputRequest;
import webbroker3.inform.message.WEB3AdminInformListRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (連絡情報検索サービスインタセプタ)<BR>
 * 連絡情報検索サービスインタセプタクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformReferenceServiceInterceptor implements Interceptor 
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformReferenceServiceInterceptor.class);
            
    /**
     * @@roseuid 41EE642E005D
     */
    public WEB3AdminInformReferenceServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。 <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストの<BR>
     * プロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”00：その他” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”26：連絡管理” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッド
     * @@param l_serviceParam - (サービスメソッド引数)
     * サービスメソッド引数
     * @@return Object
     * @@roseuid 41BD7F490075
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
       
        //１）　@取引カレンダコンテキストに内容をセットする。 <BR>
        //　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。 <BR>
        if (l_serviceParam[0] instanceof WEB3AdminInformInputRequest)
        {
            WEB3AdminInformInputRequest l_request = (WEB3AdminInformInputRequest)l_serviceParam[0];
            log.debug("入力画面：" + l_request);
        }
        else if (l_serviceParam[0] instanceof WEB3AdminInformListRequest)
        {
            WEB3AdminInformListRequest l_request = (WEB3AdminInformListRequest)l_serviceParam[0];
            log.debug("一覧画面：" + l_request);
        }
        else if (l_serviceParam[0] instanceof WEB3AdminInformDetailRequest)
        {
            WEB3AdminInformDetailRequest l_request = (WEB3AdminInformDetailRequest)l_serviceParam[0];
            log.debug("詳細画面：" + l_request);
        }
        else if (l_serviceParam[0] instanceof WEB3AdminInformDownLoadRequest)
        {
            WEB3AdminInformDownLoadRequest l_request = (WEB3AdminInformDownLoadRequest)l_serviceParam[0];
            log.debug("ダウンロードファ@イル：" + l_request);
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }

        // 取引カレンダコンテキストに内容をセットする
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        try
        {
            //－リクエストデータの内容と、OpLoginSecurityServiceの内容より
            //  取引時間コンテキストのプロパティをセットする。
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSec.getAccountId();
    
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager(); 
            MainAccount l_mainAccount = null;
            
            String l_strInstitutionCode = null;
            String l_strBranchCode = null;
            
            if(l_opLoginSec.isAccountIdSet())
            {
                //管理者かそれ以外かを判定し、
                //MainAccountを取得
                l_mainAccount = l_accountManager.getMainAccount(l_opLoginSec.getAccountId());    //throw NotFoundException

                //証券会社コードを取得
                l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();

                //部店コードを取得
                l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            }
            else
            {
                //口座IDがnullかどうかで、管理者の場合
                //管理者を取得
                WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

                //証券会社コードを取得
                l_strInstitutionCode = l_administrator.getInstitutionCode();

                //部店コードを取得
                l_strBranchCode = l_administrator.getBranchCode();
            }
            log.debug("証券会社コード:" + l_strInstitutionCode);
            log.debug("Institution Code: " + l_strInstitutionCode);
            
            // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
            l_context.setInstitutionCode(l_strInstitutionCode);
    
            // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
            l_context.setBranchCode(l_strBranchCode);
    
            // 取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
    
            // 取引カレンダコンテキスト.受付時間区分 = ”00：その他” <BR>
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
    
            // 取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
    
            // 取引カレンダコンテキスト.注文受付商品 = ”26：連絡管理” <BR>
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.INFORM_MANAGEMENT);
    
            // 取引カレンダコンテキスト.注文受付トランザクション = ”07：照会” <BR>
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
    
            //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 <BR>
            //  設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);         

            //２）受付日時、日付ロールをセットする。 <BR>
            //－取引時間管理.setTimestamp()をコールする。<BR>
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
        }
        catch (WEB3BaseException l_wbExp)
        {
            log.debug(STR_METHOD_NAME, l_wbExp);
            throw new WEB3BaseRuntimeException(
                l_wbExp.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbExp.getMessage(),
                l_wbExp);                  
        }
        catch (NotFoundException l_nfExp)
        {
            log.error(l_nfExp.getMessage(),l_nfExp);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_context;
    }
    
    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR> 
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値
     * 
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値
     * @@roseuid 41BD7F490094
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
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
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * 
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト
     * @@roseuid 41BD7F4900B3
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
    }
}
@
