head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.30.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金UnitServiceインタセプタ(WEB3AioSonarCashTransUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 黄建 (中訊) 新規作成
                   2004/10/26 屈陽 (中訊) レビュー
                   2004/12/09 周勇 (中訊) 残対応
*/

package webbroker3.aio;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import webbroker3.aio.data.HostCashTransferParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (SONAR入出金UnitServiceインタセプタ)<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioSonarCashTransUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransUnitServiceInterceptor.class);
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     *  <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR> 
     * （xTradeカーネルよりサービス実行前に呼び出される）  <BR>
     *  <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。  <BR>
     * 　@－引数.サービスの引数[0]を入出金Paramsオブジェクトにキャストする。  <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityService<BR>
     * の内容より取引時間コ ンテキストのプロパティをセットする。  <BR>
     *  <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     * 入出金Params.証券会社コード <BR> 
     * 　@取引カレンダコンテキスト.部店コード = 入出金Params.部店コード  <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”  <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = (*1)受付時間区分 <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”  <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”14：入出金”  <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション =<BR>
     *  (*2)注文受付トランザクション  <BR>
     *  <BR>
     *   (*1)受付時間区分の取得方法@ <BR>
     *     入出金Params.入出金区分 = 1：出金 の場合、”16：出金” <BR>
     *     入出金Params.入出金区分 = 2：入金 の場合、”14：入金” <BR>
     *  <BR>
     *   (*2)注文受付トランザクションの取得方法@ <BR>
     *     入出金Params.入出金区分 = 1：出金 の場合、”08：出金” <BR>
     *     入出金Params.入出金区分 = 2：入金 の場合、”10：入金” <BR>
     *  <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コン テキストをセットする。  <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     *  <BR>
     * ２）　@受付日時、日付ロールをセットする。  <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。  <BR>
     *  <BR>
     * ３）　@口座をロックする。 <BR>
　@   *  －拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)<BR>
    *   をコールする。<BR> 
    *   ※引数は入出金Paramsより編集。 <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * @@return Object
     * @@roseuid 41416FC002C1
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
       
        if (l_serviceParams == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_serviceParams.length == 0)
        {
            log.debug("CollectionタイプのパラメータSizeは０できない。");
               throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@取引カレンダコンテキストに内容をセットする。  
        
        //－引数.サービスの引数[0]を入出金Paramsオブジェクトにキャストする。
        HostCashTransferParams l_hostCashTransferParams = 
            (HostCashTransferParams) l_serviceParams[0];

        //(*2)注文受付トランザクション  
        //(*1)受付時間区分の取得方法@ 
        //入出金Params.入出金区分 = 1：出金 の場合、”16：出金” 
        //入出金Params.入出金区分 = 2：入金 の場合、”14：入金” 
        String l_strTradingTimeType = null;
        if ((WEB3OrderDivDef.CASHOUT).equals(
            (l_hostCashTransferParams.getOrderDiv())))
        {
            l_strTradingTimeType = WEB3TradingTimeTypeDef.PAYMENT;
        }
        else
        {
            l_strTradingTimeType = WEB3TradingTimeTypeDef.RECIEPT;
       
        }
       
        // (*2)注文受付トランザクションの取得方法@
        //入出金Params.入出金区分 = 1：出金 の場合、”08：出金” 
        //    入出金Params.入出金区分 = 2：入金 の場合、”10：入金” 
        String l_strOrderAccTransaction = null;
        if ((WEB3OrderDivDef.CASHOUT).equals(
            (l_hostCashTransferParams.getOrderDiv())))
        {
            l_strOrderAccTransaction = WEB3OrderAccTransactionDef.PAYMENT;
        }
        else
        {
            l_strOrderAccTransaction = WEB3OrderAccTransactionDef.CASH_IN; 
        }
        
        //－取引カレンダコンテキストにの取得
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
        
        //取引カレンダコンテキスト.証券会社コード = 
        //入出金Params.証券会社コード 
        l_context.setInstitutionCode(
            l_hostCashTransferParams.getInstitutionCode());
        
        //取引カレンダコンテキスト.部店コード = 入出金Params.部店コード  
        l_context.setBranchCode(l_hostCashTransferParams.getBranchCode());
        
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”  
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
        //取引カレンダコンテキスト.受付時間区分 = (*1)受付時間区分 
        l_context.setTradingTimeType(l_strTradingTimeType);
        
        //取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //取引カレンダコンテキスト.注文受付商品 = ”14：入出金”  
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.PAYMENT);

        //取引カレンダコンテキスト.注文受付トランザクション =
        l_context.setOrderAcceptTransaction(l_strOrderAccTransaction);

        //－ThreadLocalSystemAttributesRegistry.setAttribute( )
        //にて取引時間コン テキストをセットする。  
        // 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
     
        //取引カレンダコンテキストに内容をセットする明細
        
        try
        {
            // ２）　@受付日時、日付ロールをセットする。 
            // 　@－取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //========ramain zhou-yong NO.1 begin =========
            
            //３）　@口座をロックする。 
            //－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
            //※引数は入出金Paramsより編集。 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            l_accountManager.lockAccount(
                l_hostCashTransferParams.getInstitutionCode(),
                l_hostCashTransferParams.getBranchCode(),
                l_hostCashTransferParams.getAccountCode());

            //========ramain zhou-yong NO.1 end =========
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。  <BR>
     *  <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。  <BR>
     *  <BR>
     * 取引時間管理.TIMESTAMP_TAG  <BR>
     * 取引時間管理.OFFSET_TAG  <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * @@param l_context - (onCallリターン値)<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * @@roseuid 41416FC002E0
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME =
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //取引カレンダコンテキスト.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     *  <BR>
     * 取引カレンダコンテキストクリア処理。 <BR> 
     *  <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。  <BR>
     *  <BR>
     * 取引時間管理.TIMESTAMP_TAG  <BR>
     * 取引時間管理.OFFSET_TAG  <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * @@roseuid 41416FC00300
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //１）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //取引カレンダコンテキスト.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
            
        log.exiting(STR_METHOD_NAME);
    }
}
@
