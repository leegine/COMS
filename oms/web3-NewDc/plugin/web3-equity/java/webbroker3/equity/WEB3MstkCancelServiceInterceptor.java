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
filename	WEB3MstkCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文取消サービスインタセプタ(WEB3MstkCancelServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 カク寛新(中訊) 新規作成
                   2004/12/10 岡村和明(SRA) 残案件対応 Ｎｏ.２９５
                   2004/12/29 岡村和明(SRA) JavaDoc修正
                   2005/01/05 岡村和明(SRA) 口座ロック対応
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
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.message.WEB3MstkCancelCompleteRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資注文取消サービスインタセプタ）。<BR>
 * <BR>
 * 株式ミニ投資注文取消サービスインタセプタ
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3MstkCancelServiceInterceptor implements Interceptor 
{
     
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MstkCancelServiceInterceptor.class);

    /**
     * 
     */
    public WEB3MstkCancelServiceInterceptor() 
    {
     
    }
    
    /**
     * （onCall）。<BR>
     * <BR>
     * サービスサービス開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR> 
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。 <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityService<BR>
     * の内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     * OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityService<BR>
     * より編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”05：株式ミニ投資” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”02：株式ミニ投資” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション =  ”06：取消” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コンテキストをセットする。 <BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * ３）　@リクエストデータの型が「株式ミニ投資注文取消完了リクエスト」の場合のみ、口座をロックする。<BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@　@　@※引数はOpLoginSecurityServiceより編集。
     * @@param l_method arg0
     * @@param l_serviceParams arg1
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        
        final String STR_METHOD_NAME = " onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        
        Object l_request = l_serviceParams[0];
        OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);

        //取引カレンダコンテキストを生成する
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        try
        {
            
            //FinAppサービスを取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            AccountManager l_accountMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_opLoginSec.getAccountId()); 
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            l_context.setInstitutionCode(l_strInstitutionCode);
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //取引カレンダコンテキスト.受付時間区分 = ”05：株式ミニ投資”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MINI_STOCK);

            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //取引カレンダコンテキスト.注文受付商品 = ”02：株式ミニ投資”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MINI_STOCK);

            //取引カレンダコンテキスト.注文受付トランザクション = ”06：取消”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);

            //取引時間コンテキストをセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            //受付日時、日付ロールをセットする
            WEB3GentradeTradingTimeManagement.setTimestamp();

            if(l_request instanceof WEB3MstkCancelCompleteRequest)
            {
                log.debug("拡張アカウントマネージャの口座をロックします。");
                WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                // 拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
                l_accountManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_mainAccount.getAccountCode());
            }
        }
        catch (NotFoundException l_ex)
        {
            
            log.error(l_ex.getMessage(), new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex));
            
        }

        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);    
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_context;
              
    }
    
    /**
     * （onReturn）。<BR>
     * <BR>
     * サービスメソッド終了時にコールされる。<BR> 
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_context arg0
     * @@param l_returnValue arg1
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        
        final String STR_METHOD_NAME = " onReturn(Object l_context, Object l_returnValue)";
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
     * （onThrowable）。<BR>
     * <BR>
     * サービスメソッドが例外をスローした場合にコールされる<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG
     * 取引時間管理.OFFSET_TAG
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj arg0
     * @@param l_throwable arg1
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        
        final String STR_METHOD_NAME = " onThrowable(Object l_obj, Throwable l_throwable)";
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
