head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeCloseMarginServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引訂正返済サービスインタセプタ(WEB3ToSuccMarginChangeCloseMarginServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17 譚漢江(中訊) 新規作成
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
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）信用取引訂正返済サービスインタセプタ)<BR>
 * （連続）信用取引訂正返済サービスインタセプタ<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeCloseMarginServiceInterceptor implements Interceptor 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeCloseMarginServiceInterceptor.class);

    /**
     * @@roseuid 436ACB3200FA
     */
    public WEB3ToSuccMarginChangeCloseMarginServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@リクエストデータの型が「（連続）信用取引注文訂正返済完了リクエスト」の<BR>
     * 場合のみ、口座をロックする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストして判別する。<BR>
     * <BR>
     * 　@拡張アカウントマネージャ.lock口座<BR>
     * 　@(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@※引数はOpLoginSecurityServiceより編集。<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     *   取引カレンダコンテキスト.市場コード = (*1)<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”05：訂正”<BR>
     * <BR>
     * 　@(*1) リクエストデータオブジェクト.IDに該当する予約注文単位オブジェクトより編集。<BR>
     * 　@　@　@連続注文マネージャ.get予約注文単位(リクエストデータ.ID)で取得した<BR>
     * 　@　@　@予約注文単位.get市場().市場コードをセットする。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 433D00720180
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        try
        {
            //取引カレンダが利用するコンテキストを生成する。
            //（xTradeカーネルよりサービス実行前に呼び出される）
            //１）　@リクエストデータの型が「（連続）信用取引注文訂正返済完了リクエスト」の場合のみ、口座をロックする。
            //　@－サービスの引数[0]をリクエストデータオブジェクトにキャストして判別する。
            //　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
            //　@※引数はOpLoginSecurityServiceより編集。
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            long l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //拡張アカウントマネージャ取得する    
            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager) l_finApp.getAccountManager();  
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);            
            //証券会社コード
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            //部店コード
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            //リクエストデータ.ID
            String l_strOrderId = null;
            
            if (l_serviceParam[0] instanceof WEB3SuccMarginCloseChangeCompleteRequest)
            {  
                l_accMgr.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_mainAccount.getAccountCode());
                WEB3SuccMarginCloseChangeCompleteRequest l_completeRequest =
                    (WEB3SuccMarginCloseChangeCompleteRequest) l_serviceParam[0];
                l_strOrderId = l_completeRequest.id;
            }
            else if (l_serviceParam[0] instanceof WEB3SuccMarginCloseChangeConfirmRequest)
            {
                WEB3SuccMarginCloseChangeConfirmRequest l_confirmRequest = 
                    (WEB3SuccMarginCloseChangeConfirmRequest) l_serviceParam[0];
                l_strOrderId = l_confirmRequest.id;
            }
            else
            {
                log.debug("パラメータタイプ不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータタイプ不正。");
            }

            //２）　@取引カレンダコンテキストに内容をセットする。
            //　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より
            //　@　@　@取引時間コンテキストのプロパティをセットする。
            //証券会社コードを取得
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            //　@取引カレンダコンテキスト.証券会社コード = 証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCode);
            //　@取引カレンダコンテキスト.部店コード = 部店コード
            l_context.setBranchCode(l_strBranchCode);

            //　@(*)リクエストデータオブジェクト.IDに該当する予約注文単位オブジェクトより編集。
            //　@　@　@連続注文マネージャ.get予約注文単位(リクエストデータ.ID)で取得した
            //　@　@　@予約注文単位.get市場().市場コードをセットする。
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            long l_lngOrderId = Long.parseLong(l_strOrderId);
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            
            if (l_orderUnit == null)
            {
                log.debug("予期しないシステムエラーが発生しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "予期しないシステムエラーが発生しました。");
            }

            //取引カレンダコンテキスト.市場コード　@=　@予約注文単位.get市場().市場コード
            l_context.setMarketCode(l_orderUnit.getMarket().getMarketCode()); 
            //取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            //取引カレンダコンテキスト.注文受付トランザクション = ”05：訂正”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
            
            //　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて
            //　@　@　@取引時間コンテキストをセットする。
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);        
            //３）　@受付日時、日付ロールをセットする。
            //　@－取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }    
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 433D007201AF
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        //取引時間管理.TIMESTAMP_TAG
        log.debug("取引時間管理.TIMESTAMP_TAG = " + 
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //取引時間管理.OFFSET_TAG
        log.debug("取引時間管理.OFFSET_TAG = " + 
            WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        log.debug("取引時間管理.TRADING_CAL_CONTEXT_PATH = " + 
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 433D007201BE
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        //取引時間管理.TIMESTAMP_TAG
        log.debug("取引時間管理.TIMESTAMP_TAG = " + 
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //取引時間管理.OFFSET_TAG
        log.debug("取引時間管理.OFFSET_TAG = " + 
            WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        log.debug("取引時間管理.TRADING_CAL_CONTEXT_PATH = " + 
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
