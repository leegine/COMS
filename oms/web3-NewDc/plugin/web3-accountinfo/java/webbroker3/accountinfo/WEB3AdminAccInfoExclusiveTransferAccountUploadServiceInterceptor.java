head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 専用振込先口座アップロードサービスインタセプタ(WEB3AdminAccInfoExclusiveTransferAccountUploadServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
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
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (専用振込先口座アップロードサービスインタセプタ)<BR>
 * 専用振込先口座アップロードサービスインタセプタ<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadServiceInterceptor implements Interceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountUploadServiceInterceptor.class);
    
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
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”17：アップロード（管理者）”<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     * @@roseuid 415BC5540247
     */
    public java.lang.Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method l_method, Object[] l_serviceParam)";
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
            MainAccount l_mainAccount;
            String l_strInstitutionCode;
            String l_strBranchCode;
            
            if(l_opLoginSec.isAccountIdSet())
            {
                //管理者かそれ以外かを判定し、
                l_mainAccount = l_accountMgr.getMainAccount(l_opLoginSec.getAccountId());    //throw NotFoundException
                l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();

                l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            }
            else
            {
                //口座IDがnullかどうかで、管理者の場合
                WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
                l_strInstitutionCode = l_administrator.getInstitutionCode();
                l_strBranchCode = l_administrator.getBranchCode();
            }

        
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
                
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
        
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
            //取引カレンダコンテキスト.受付時間区分 = ”17：アップロード（管理者）”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD);
        
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
            //取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
        
            //取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT); 
        
            //取引時間コンテキストをセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context
            );
            
            //受付日時、日付ロールをセットする            
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_context;
    }
    
    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG <BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 415BC5540295
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object l_context, Object l_returnValue)";
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
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG <BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 415BC55402A6
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object l_obj, Throwable l_throwable)";
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
