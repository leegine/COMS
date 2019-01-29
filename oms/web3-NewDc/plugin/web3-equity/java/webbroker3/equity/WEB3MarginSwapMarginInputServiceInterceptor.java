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
filename	WEB3MarginSwapMarginInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡入力サービスインタセプタ(WEB3MarginSwapMarginInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 凌建平 (中訊) 新規作成
Revesion History : 2004/12/07 森川   (SRA) 残案件対応
*/

package webbroker3.equity;

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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.message.WEB3MarginSwapMarginInputRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引現引現渡入力サービスインタセプタ）。<BR>
 * <BR>
 * 信用取引現引現渡入力サービスインタセプタクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginInputServiceInterceptor implements Interceptor 
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginSwapMarginInputServiceInterceptor.class);
    
    /**
     * (コンストラクタ)。<BR>
     * @@roseuid 4142B32C026A
     */
    public WEB3MarginSwapMarginInputServiceInterceptor() 
    {
     
    }
    
    /**
     * (onCall)。<BR>
     * <BR>
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、<BR>
     *     OpLoginSecurityServiceの内容より取引時間コンテキストの<BR>
     * 　@　@プロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     *       OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = null<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”19：現引・現渡”<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”04：現引・現渡”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object<BR>
     * @@roseuid 407CA52C02EA
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        //信用取引現引現渡入力リクエスト
        Object l_request = l_serviceParams[0];
        if (!(l_request instanceof WEB3MarginSwapMarginInputRequest))
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "request !=信用取引現引現渡入力リクエスト"));
            return null;
        }

        long l_lngAccountId;                    // 口座コード
        String l_strInstitutionCode = null;     // 証券会社コード
        String l_strBranchCode = null;          // 部店コード
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //セキュリティサービスを取得
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        try
        { 
            // 口座コードを取得
            l_lngAccountId = l_opLoginSec.getAccountId();
            //FinAppサービスを取得
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            // 証券会社コード
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            // 部店コードを取得
            l_strBranchCode = l_acc.getBranch().getBranchCode();
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
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);                  
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
     * @@roseuid 407CA52C0309
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
     * サービスメソッドが例外をスローした場合にコールされる<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 407CA52C0319
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
