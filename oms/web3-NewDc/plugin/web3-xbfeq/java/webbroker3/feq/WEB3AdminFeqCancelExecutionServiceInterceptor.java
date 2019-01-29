head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCancelExecutionServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来約定取消インタセプタ(WEB3AdminFeqCancelExecutionServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 鄭海良(中訊) 新規作成
                 : 2005/08/01 韋念瓊(中訊) レビュー       
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionCompleteRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者外国株式出来約定取消インタセプタ)<BR>
 * 管理者外国株式出来約定取消インタセプタ<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqCancelExecutionServiceInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqCancelExecutionServiceInterceptor.class);

    /**
     * @@roseuid 42D0CE42032C
     */
    public WEB3AdminFeqCancelExecutionServiceInterceptor() 
    {
     
    }
    
    /**
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－ログインセッションよりログインＩＤを取得，ログインＩＤに該当する管理者の情報<BR>
     * より、<BR>
     * 　@　@以下の通り取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 管理者.証券会社コード <BR>
     * 　@取引カレンダコンテキスト.部店コード = 管理者.部店コード <BR>
     * 　@取引カレンダコンテキスト.市場コード = null<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”10：外国株式” <BR>
     * 　@取引カレンダコンテキスト.商品コード = 0：DEFAULT<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”04：外国株”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間<BR>
     * コンテキストをセットする。 <BR>
     * 　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ※以下、サービスメソッドが「管理者外国株式出来約定取消完了リクエスト」<BR>
     * の場合のみ実施。<BR>
     * ３）　@口座をロックする。 <BR>
     * 　@３－１）　@サービスの引数[0]（：約定ＩＤ）にて、該当する約定オブジェクトを<BR>
     * 取得する。<BR>
     * 　@　@（外国株式注文マネージャ.getOrderExecution()を使用）<BR>
     * <BR>
     * 　@３－２）　@口座ロック<BR>
     * 　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード,<BR> 
     * 口座コード)を<BR>
     * コールする。 <BR>
     * 　@　@※ 引数は約定の情報より編集。 <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * 
     * @@param l_serviceParams - (サービスの引数)<BR>
     * サービスの引数配列<BR>
     * @@return Object
     * @@roseuid 4295FFDD01AC
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        long l_lngExecId = 0;

        try
        {
            //管理者
            WEB3Administrator l_administrator = 
                WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
            if (l_administrator == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //証券会社コード
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
            // 部店コード
            String l_strBranchCode = l_administrator.getBranchCode();
        
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
                   
            //１）　@取引カレンダコンテキストに内容をセットする。        
            //取引カレンダコンテキスト.証券会社コード = 管理者.証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //取引カレンダコンテキスト.部店コード = 管理者.部店コード
            l_context.setBranchCode(l_strBranchCode);
            
            //取引カレンダコンテキスト.市場コード = null”
            l_context.setMarketCode(null);
            
            //取引カレンダコンテキスト.受付時間区分 = ”10：外国株式”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = ”04：外国株”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
            
            //取引カレンダコンテキスト.注文受付トランザクション = null
            l_context.setOrderAcceptTransaction(null);
            
            // 取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        
            //取引時間管理
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

            //※以下、サービスメソッドが「管理者外国株式出来約定取消完了リクエスト」
            // の場合のみ実施
            if (l_serviceParams != null 
                && l_serviceParams[0] instanceof WEB3AdminFeqCancelExecutionCompleteRequest)
            {
                // ３）　@口座をロックする。 
                // 　@３－１）　@サービスの引数[0]（：約定ＩＤ）にて、該当する約定オブジェクトを取得する。
                // 　@　@（外国株式注文マネージャ.getOrderExecution()を使用）
                
                //get 約定ＩＤ
                WEB3AdminFeqCancelExecutionCompleteRequest l_request = 
                    (WEB3AdminFeqCancelExecutionCompleteRequest)l_serviceParams[0]; 
                if (WEB3StringTypeUtility.isNumber(l_request.execId))
                {
                    l_lngExecId = Long.parseLong(l_request.execId); 
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        this.getClass().getName() + STR_METHOD_NAME, 
                        "パラメータタイプ不正。");
                }
            
                // get外国株式注文マネージャ
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                if (l_finApp == null)
                {
                    String l_strMessage = "FinAppが存在しない。";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
                if (l_tradingModule == null)
                {
                    String l_strMessage = "TradingModuleが存在しない。";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
                WEB3FeqOrderManager l_orderManager = 
                    (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
                if (l_orderManager == null)
                {
                    String l_strMessage = "外国株式注文マネージャが存在しない。";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
            
                //get約定オブジェクト
                OrderExecution l_orderExecution = 
                    l_orderManager.getOrderExecution(l_lngExecId);//NotFoundException
                if (l_orderExecution == null)
                {
                    String l_strMessage = "約定オブジェクトが存在しない「" + l_lngExecId + "」。";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
            
                // 　@３－２）　@口座ロック
                // 　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード,口座コード)をコールする
                long l_lngBranchId = l_orderExecution.getBranchId();
                long l_lngAccountId = l_orderExecution.getAccountId();
                WEB3GentradeAccountManager l_accountManager = 
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                if (l_accountManager == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                         this.getClass().getName() + STR_METHOD_NAME,
                        "拡張アカウントマネージャが存在しない。");
                }
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);//NotFoundException
                Branch l_branch = l_accountManager.getBranch(l_lngBranchId);//NotFoundException
            
                l_accountManager.lockAccount(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_branch.getBranchCode(),
                    l_mainAccount.getAccountCode());//WEB3BaseException
            }

            log.exiting(STR_METHOD_NAME);
            return l_context;        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);                
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
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
     * @@param l_context - (onCall返却値)<BR>
     * onCall返却値<BR>
     * @@param l_returnValue - (サービスメソッド返却値)<BR>
     * サービスメソッド返却値<BR>
     * @@roseuid 4295FFDD01AF
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
            
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR> 
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCall返却値)<BR>
     * onCall返却値<BR>
     * @@param l_throwable - (例外)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 4295FFDD01BB
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
     
    }
}
@
