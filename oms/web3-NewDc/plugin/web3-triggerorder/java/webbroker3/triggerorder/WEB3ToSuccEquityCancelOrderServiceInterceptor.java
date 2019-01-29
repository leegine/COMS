head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityCancelOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株式注文取消サービスインタセプタ(WEB3ToSuccEquityCancelOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  魏馨(中訊) 新規作成
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）株式注文取消サービスインタセプタ)<BR>
 *
 * @@ author 魏馨 <BR>
 * @@ version 1.0 <BR>
 */  
public class WEB3ToSuccEquityCancelOrderServiceInterceptor implements Interceptor 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityCancelOrderServiceInterceptor.class);

    /**
     * @@roseuid 436ACB9E0280
     */
    public WEB3ToSuccEquityCancelOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@リクエストデータの型が「（連続）現物株式注文取消完了リクエスト」の<BR>
     * 場合のみ、口座をロックする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストして判別する。<BR>
     * <BR>
     * 　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)<BR>
     * をコールする。<BR>
     * 　@※引数はOpLoginSecurityServiceより編集。<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－OpLoginSecurityServiceの内容より<BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。<BR>
     * 　@（*1）「（連続）現物株式注文取消確認リクエスト」または、<BR>
     * 　@　@　@　@「（連続）現物株式注文取消完了リクエスト」<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = リクエスト.IDに該当する予約<BR>
     * 注文単位.市場IDに該当する市場.市場コード<BR>
     * 　@　@※予約注文単位は、連続注文マネージャ.get株式予約注文単位<BR>
     * (リクエスト.ID)により取得。<BR>
     *   取引カレンダコンテキスト.銘柄コード = ”DEFAULT”<BR>  
     * 　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”株式”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”取消”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 取引時間コンテキストをセットする。<BR>
     * 設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - (メソッド)<BR>
     * メソッド<BR>
     * @@param l_serviceParam - (サービスの引数)<BR>
     * サービスのメソッドに渡される引数。<BR>
     * 株式注文サービスの場合、株式注文リクエストオブジェクト。<BR>
     * @@return Object
     * @@roseuid 4340CD3300DF
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);  
        
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.debug("サービスの引数 = null or length = 0");
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "サービスの引数 = null or length = 0");
        }
        
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        WEB3GentradeMainAccount l_account = null;
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
                
        long l_lngAccountId = l_opLoginSec.getAccountId();
        try
        {
            l_account =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_account.getInstitution().getInstitutionCode();
            l_strBranchCode = l_account.getBranch().getBranchCode();
            
            Object l_request = l_serviceParam[0];
            String l_strOrderId = null;
    
            if (l_request instanceof WEB3SuccEquityCancelCompleteRequest)
            {
                // １）　@リクエストデータの型が「（連続）現物株式注文取消完了リクエスト」の
                //     　@場合のみ、口座をロックする。
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_account.getAccountCode());
                
                // （連続）現物株式注文取消完了リクエスト
                WEB3SuccEquityCancelCompleteRequest l_completeRequest =
                    (WEB3SuccEquityCancelCompleteRequest)l_request;
                l_strOrderId = l_completeRequest.id;
            }
            else if (l_request instanceof WEB3SuccEquityCancelConfirmRequest)
            {
                // （連続）現物株式注文取消確認リクエスト
                WEB3SuccEquityCancelConfirmRequest l_confirmRequest =
                    (WEB3SuccEquityCancelConfirmRequest)l_request;
                l_strOrderId = l_confirmRequest.id;
            }
            else
            {
                String l_strErrorMsg = "request !=（連続）現物株式注文取消完了リクエスト and " +
                    "request !=（連続）現物株式注文取消確認リクエスト";
                log.debug(l_strErrorMsg);
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMsg);
            }
            
            String l_strMarketCode = null;
            WEB3ToSuccOrderManagerImpl l_managerImpl =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;    
            
            l_orderUnitImpl = l_managerImpl.getReserveEqtypeOrderUnit(Long.parseLong(l_strOrderId));
                                                                       
            Market l_market = l_orderUnitImpl.getMarket();          
            if (l_market != null)
            {
                l_strMarketCode = l_market.getMarketCode();                   
            }         
            // ２）　@取引カレンダコンテキストに内容をセットする。                               
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            // 取引カレンダコンテキスト.市場コード = リクエスト.IDに該当する予約
            //注文単位.市場IDに該当する市場.市場コード
            l_context.setMarketCode(l_strMarketCode);
            //取引カレンダコンテキスト.銘柄コード = ”DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            //取引カレンダコンテキスト.注文受付商品 = ”株式”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            //取引カレンダコンテキスト.注文受付トランザクション = ”取消”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
            // 取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);         
            // ３）　@受付日時、日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            log.exiting(STR_METHOD_NAME);
        
            return l_context;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
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
    }
    
    /**
     * 取引カレンダコンテキストクリア処理。<BR>
     * （連続）株式注文取消サービス終了時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 4340CD3300E2
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
     * 取引カレンダコンテキストクリア処理。<BR>
     * 例外発生時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 4340CD3300F1
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
