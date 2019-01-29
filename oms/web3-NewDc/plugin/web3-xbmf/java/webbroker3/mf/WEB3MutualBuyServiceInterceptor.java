head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付注文サービスインタセプタ(WEB3MutualBuyServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 周勇 (中訊) 新規作成
                   2004/08/23 黄建 (中訊) レビュー 
*/
package webbroker3.mf;

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
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mf.message.WEB3MutualBuyCompleteRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託買付注文サービスインタセプタ<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyServiceInterceptor implements Interceptor
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyServiceInterceptor.class);
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。
     * 　@−リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コン<BR>テキストの<BR>
     * 　@　@プロパティをセットする。<BR>
     * <BR>
     * 　@　@　@取引カレンダコンテキスト.証券会社コード = <BR>
     * OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.部店コード = <BR>
     * OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = <BR>
     * WEB3TradingTimeTypeDef.投資信託<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付商品 = ”07：投資信託”<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     *  ”01：買付（新規建買）”<BR>
     * <BR>
     * 　@−ThreadLocalSystemAttributesRegistry.setAttribute()<BR>
     * にて取引時間コンテキストをセットする。<BR>
     * 　@　@［setAttributeに渡すパラメタ］<BR>
     * 　@　@　@設定キー： 取引時間コンテキスト.TRADING_CALENDAR_TAG<BR>
     * 　@　@　@設定値： 取引時間コンテキスト<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@−取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）　@リクエストデータの型が「投信買付注文完了リクエスト」の場合のみ、口座をロックする。 <BR>
     * 　@−拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 <BR>
     * 　@　@※引数はOpLoginSecurityServiceより編集。<BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 40555D740250
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@取引カレンダコンテキストに内容をセットする。        
        try
        {
            WEB3GenRequest l_request = null;
            if(l_serviceParam[0] instanceof WEB3GenRequest)
            {
                l_request = (WEB3GenRequest) l_serviceParam[0];
            }
            else
            {
                log.debug(" パラメータError ");
                throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            long l_lngAccountId = 0; //口座コード
            String l_strInstitutionCode = null; // 証券会社コード
            String l_strBranchCode = null; // 部店コード
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                    OpLoginSecurityService.class);
            l_lngAccountId = l_opLoginSec.getAccountId();
            MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            l_strBranchCode = l_acc.getBranch().getBranchCode();
            //リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = WEB3TradingTimeTypeDef.投資信託 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            //取引カレンダコンテキスト.注文受付商品 = ”07：投資信託”                                                                                        
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MUTUAL_FUND);
            //取引カレンダコンテキスト.注文受付トランザクション = ”01：買付（新規建買）”
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            //−ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            //受付日時、日付ロールをセットする
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //３）　@リクエストデータの型が「投信買付注文完了リクエスト」の場合のみ、口座をロックする
            if(l_request instanceof WEB3MutualBuyCompleteRequest)
            {
                String l_strAccountCode = l_acc.getAccountCode();                
                //口座をロックする。      
                l_accMgr.lockAccount(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);                
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("no find MainAccount");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("not can ThreadLocalSystemAttributesRegistry.setAttribute()");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } //取引時間管理
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 40555D740260
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    }
    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 40555D740270
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    }
}
@
