head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株式注文訂正サービスインタセプタ(WEB3ToSuccEquityChangeOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder;

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
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）株式注文訂正サービスインタセプタ)<BR>
 * （連続）株式注文訂正サービスインタセプタ。<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccEquityChangeOrderServiceInterceptor implements Interceptor 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityChangeOrderServiceInterceptor.class);
    
    /**
     * @@roseuid 436ACB9F01D4
     */
    public WEB3ToSuccEquityChangeOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@リクエストデータの型が「（連続）現物株式注文訂正完了リクエスト」<BR>
     *     の場合のみ、口座をロックする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストして判別する。<BR>
     * <BR>
     * 　@拡張アカウントマネージャ.lock口座(証券会社コード,<BR>
     *   部店コード, 口座コード)をコールする。<BR>
     * 　@※引数はOpLoginSecurityServiceより編集。<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－OpLoginSecurityServiceの内容より<BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。<BR>
     * 　@（*1）「（連続）現物株式注文訂正確認リクエスト」または、<BR>
     * 　@　@　@　@「（連続）現物株式注文訂正完了リクエスト」<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = リクエスト.ID<BR>
     *    に該当する予約注文単位.市場IDに該当する市場.市場コード<BR>
     * 　@　@※予約注文単位は、<BR>
     *    連続注文マネージャ.get株式予約注文単位(リクエスト.ID)により取得。<BR>
     *   取引カレンダコンテキスト.銘柄コード = ”DEFAULT”<BR>  
     * 　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”株式”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”訂正”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
     * 設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - メソッド<BR>
     * @@param l_serviceParam - (サービスの引数)<BR>
     * サービスのメソッドに渡される引数。<BR>
     * 株式注文サービスの場合、株式注文リクエストオブジェクト。<BR>
     * @@return Object
     * @@roseuid 433BCE9B0269
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        //check parameter
        if (l_serviceParam == null 
            && l_serviceParam.length == 0)
        {
            log.error("パラメータ値不正。");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);   
        }
        
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
    
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            MainAccount l_mainAccount = 
                l_accountManager.getMainAccount(l_lngAccountId);//NotFoundException

            // １）　@リクエストデータの型が「（連続）現物株式注文訂正完了リクエスト」
            //     の場合のみ、口座をロックする。
            // 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストして判別する。
            // 
            // 　@拡張アカウントマネージャ.lock口座(証券会社コード,
            //   部店コード, 口座コード)をコールする。
            // 　@※引数はOpLoginSecurityServiceより編集。

            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            String l_strAccountCode = l_mainAccount.getAccountCode();
            
            long l_lngOrderId = 0;
            if (l_serviceParam[0] instanceof WEB3SuccEquityChangeCompleteRequest)
            {
                WEB3SuccEquityChangeCompleteRequest l_completeRequest = 
                    (WEB3SuccEquityChangeCompleteRequest)l_serviceParam[0];
                l_lngOrderId = Long.parseLong(l_completeRequest.id);
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode);
            }
            else if (l_serviceParam[0] instanceof WEB3SuccEquityChangeConfirmRequest)
            {
                WEB3SuccEquityChangeConfirmRequest l_confirmRequest = 
                    (WEB3SuccEquityChangeConfirmRequest)l_serviceParam[0];
                l_lngOrderId = Long.parseLong(l_confirmRequest.id);
            }
            else
            {
                log.debug("パラメータタイプ不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
                   
            //２）　@取引カレンダコンテキストに内容をセットする。        
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            
            //取引カレンダコンテキスト.市場コード = リクエスト.IDに該当する予約注文単位.市場IDに該当する市場.市場コード
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = 
                l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);//NotFoundException,WEB3BaseException
            WEB3GentradeMarket l_market = l_orderUnit.getMarket();
            if (l_market != null)
            {
                l_context.setMarketCode(l_market.getMarketCode());
            }
            //取引カレンダコンテキスト.銘柄コード = ”DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            
            //取引カレンダコンテキスト.注文受付商品 = ”株式”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            
            //取引カレンダコンテキスト.注文受付トランザクション = 訂正
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
            
            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        
            //取引時間管理
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException
            log.exiting(STR_METHOD_NAME);
            return l_context;        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);                
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
    }
    
    /**
     * 取引カレンダコンテキストクリア処理。<BR>
     * （連続）株式注文訂正サービス終了時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 433BCE9B0288
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
     * @@roseuid 433BCE9B02A7
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
