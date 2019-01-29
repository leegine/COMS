head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡サービスインタセプタ(WEB3MarginSwapMarginServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 凌建平 (中訊) 新規作成
Revesion History : 2004/12/10 森川   (SRA)  残案件対応
*/

package webbroker3.equity;

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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引現引現渡サービスインタセプタ）。<BR>
 * <BR>
 * 信用取引現引現渡サービスインタセプタクラス。
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginServiceInterceptor implements Interceptor 
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginSwapMarginServiceInterceptor.class);
    
    /**
     * (コンストラクタ)。<BR>
     * @@roseuid 4142B32C00A8
     */
    public WEB3MarginSwapMarginServiceInterceptor() 
    {
    }
    
    /**
     * (onCall)。<BR>
     * <BR>
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。 <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より <BR>
     * 　@　@取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = null(*) <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”19：現引・現渡” <BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”03：信用取引” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”04：現引・現渡” <BR>
     * <BR>
     * 　@(*) 市場コードは、execute( )からコールされるメソッド内で、 <BR>
     * 　@　@　@建株より取得して設定する。 <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     * 　@　@取引時間コンテキストをセットする。 <BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）　@リクエストデータの型が「信用取引現引現渡注文完了リクエスト」の場合のみ、
     * 　@　@　@口座をロックする。 <BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)
     * 　@　@　@をコールする。 <BR>
     * 　@　@　@※引数はOpLoginSecurityServiceより編集。 <BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 4056938802B9
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        //信用取引現引現渡発注審査リクエスト
        //Object l_request = l_serviceParams[0];
        if (l_serviceParams[0] instanceof WEB3MarginSwapMarginConfirmRequest)
        {
            //WEB3MarginSwapMarginConfirmRequest l_request = (WEB3MarginSwapMarginConfirmRequest)l_serviceParams[0];
        }
        //信用取引現引現渡注文登録リクエスト
        else if (l_serviceParams[0] instanceof WEB3MarginSwapMarginCompleteRequest)
        {
            //WEB3MarginSwapMarginCompleteRequest l_request = (WEB3MarginSwapMarginCompleteRequest)l_serviceParams[0];
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }

        long l_lngAccountId;                    // 口座コード
        String l_strInstitutionCode = null;     // 証券会社コード
        String l_strBranchCode = null;          // 部店コード
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //セキュリティサービスを取得
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        WEB3GentradeAccountManager l_accountManager;
        MainAccount l_mainAccount;
        try
        { 
            // 口座コードを取得
            l_lngAccountId = l_opLoginSec.getAccountId();
            //FinAppサービスを取得
            l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            // 証券会社コード
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            // 部店コードを取得
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        }
        catch (NotFoundException nfe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }
                    
        //１）　@取引カレンダコンテキストに内容をセットする
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
        l_context.setInstitutionCode(l_strInstitutionCode);
        // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode(l_strBranchCode);
        // 取引カレンダコンテキスト.市場コード = null
        l_context.setMarketCode(null);
        // 取引カレンダコンテキスト.受付時間区分 = ”19：現引・現渡”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
        // 取引カレンダコンテキスト.銘柄コード = 0 ： DEFAULT
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // 取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        // 取引カレンダコンテキスト.注文受付トランザクション = ”04：現引・現渡”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
        
        // 取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //２）　@受付日時、日付ロールをセットする                    
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理

            //--------------------
            //口座をロックする
            //(リクエストデータの型が「信用取引新規建注文完了リクエスト」の場合)
            //--------------------
            if (l_serviceParams[0] instanceof WEB3MarginSwapMarginCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode, l_strBranchCode, l_mainAccount.getAccountCode());
            }
            
        }
        catch (WEB3BaseException l_be)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_be.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_be.getMessage(), l_be);                  
        }
            

        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (onReturn)。<BR>
     * <BR>
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 4056938802C8
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
     * (onThrowable)。<BR>
     * <BR>
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 4056938802E8
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
