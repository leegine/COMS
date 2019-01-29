head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡入力サービスインタセプタ(WEB3InformInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/
package webbroker3.inform;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.inform.message.WEB3InformCompleteRequest;
import webbroker3.inform.message.WEB3InformConfirmRequest;
import webbroker3.inform.message.WEB3InformInputRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (連絡入力サービスインタセプタ)<BR>
 * 連絡入力サービスインタセプタクラス
 */
public class WEB3InformInputServiceInterceptor implements Interceptor
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformInputServiceInterceptor.class);

    /**
     * @@roseuid 41EE642D01B5
     */
    public WEB3InformInputServiceInterceptor()
    {

    }

    /**
     *サービスメソッド開始時にコールされる。 <BR>
     *<BR>
     *取引カレンダが利用するコンテキストを生成する。 <BR>
     *（xTradeカーネルよりサービス実行前に呼び出される） <BR>
     *<BR>
     *※ログイン前でかつ、リクエストデータが連絡入力リクエストの場合は、何もせずに処理を終了する。<BR>
     *<BR>
     *１）　@取引カレンダコンテキストに内容をセットする。 <BR>
     *　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。 <BR>
     *　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。 <BR>
     *<BR>
     *○ログイン後の処理の場合<BR>
     *　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
     *　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     *<BR>
     *○ログイン前の処理の場合<BR>
     *   ※リクエストデータが、連絡確認リクエストもしくは連絡完了リクエストの場合<BR>
     *　@取引カレンダコンテキスト.証券会社コード = リクエストデータ.連絡情報.証券会社コード<BR>
     *<BR>
     *○共通<BR>
     *　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     *　@取引カレンダコンテキスト.受付時間区分 = ”00：その他” <BR>
     *　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     *　@取引カレンダコンテキスト.注文受付商品 = ”26：連絡管理” <BR>
     *　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会” <BR>
     *<BR>
     *　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 <BR>
     *設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     *<BR>
     *２）　@受付日時、日付ロールをセットする。 <BR>
     *　@－取引時間管理.setTimestamp()をコールする。 <BR> 
     * ※ログイン後の処理の場合のみ、実施する。<BR>    
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数
     * 
     * @@return Object
     * @@roseuid 41AEDE8E0032
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        //１）　@取引カレンダコンテキストに内容をセットする。 
        //　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。 
        //　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。 
        //
        if (l_serviceParam == null)
        {
            log.error("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strInstitutionCode = "";
        String l_strBranchCode = "";
        boolean l_isLogin = true;
        //CollectionタイプのパラメータSizeは０できない
        //パラメータタイプ不正
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        if (l_serviceParam[0] instanceof  WEB3InformInputRequest)
        {
            //return null;
        }
        else if (l_serviceParam[0] instanceof  WEB3InformConfirmRequest)
        {
            WEB3InformConfirmRequest l_request = (WEB3InformConfirmRequest)l_serviceParam[0];
            l_strInstitutionCode = l_request.informInfoUnit.institutionCode;
            l_strBranchCode = l_request.informInfoUnit.branchCode;
        }
        else if (l_serviceParam[0] instanceof  WEB3InformCompleteRequest)
        {
            WEB3InformCompleteRequest l_request = (WEB3InformCompleteRequest)l_serviceParam[0];
            l_strInstitutionCode = l_request.informInfoUnit.institutionCode;
            l_strBranchCode = l_request.informInfoUnit.branchCode;
        }
        else
        {
            log.error("パラメータタイプ不正");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        AccountManager l_accMgr = l_finApp.getAccountManager();


        try
        {
            long l_lngLoginId = l_opLoginSec.getLoginId();       
                
            if (l_lngLoginId == 0)
            {
                if (l_serviceParam[0] instanceof  WEB3InformInputRequest)
                {
                    return null;
                }
                log.info("ホームページからアクセスの場合"); 
                l_isLogin = false;
                l_context.setInstitutionCode(l_strInstitutionCode);              
            } 
            else
            {      
                long l_lngAccountId = l_opLoginSec.getAccountId();
                MainAccount l_mainAccount = null;
                try
                {       
                    l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);     
                }
                catch(NotFoundException l_ex)
                {
                    log.error("予期しないシステムエラーが発生しました。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex
                        );
                }
                //　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集         
                l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
                //　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 
                l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());   
            }
        }
        catch (IllegalSessionStateException l_ex)
        {          
            if (l_serviceParam[0] instanceof  WEB3InformInputRequest)
            {
                return null;
            }
            log.info("ホームページからアクセスの場合");
            l_isLogin = false;   
            l_context.setInstitutionCode(l_strInstitutionCode);
        }


        //　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //　@取引カレンダコンテキスト.受付時間区分 = ”00：その他” 
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
        //　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        //　@取引カレンダコンテキスト.注文受付商品 = ”26：連絡管理”
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.INFORM_MANAGEMENT); 
        //　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE); 
        //
        //　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 
        //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
        //
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        //２）　@受付日時、日付ロールをセットする。 
        //　@－取引時間管理.setTimestamp()をコールする。 
        //受付日時、日付ロールをセットする
        //※ログイン後の処理の場合のみ、実施する。
        if (l_isLogin)
        {
            try
            {
                WEB3GentradeTradingTimeManagement.setTimestamp();
            }
            catch(WEB3SystemLayerException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                    );
            }
        }


        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値
     * 
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値
     * @@roseuid 41AEDE8E0051
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn";
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
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR> 
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値
     * 
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト
     * @@roseuid 41AEDE8E0070
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable";
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
