head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXTransferManagementUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替管理UnitServiceサービスインタセプタクラス(WEB3AdminFXTransferManagementUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 于美麗 (中訊) 新規作成
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX振替管理UnitServiceサービスインタセプタ) <BR>
 * FX振替管理UnitServiceサービスインタセプタクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AdminFXTransferManagementUnitServiceInterceptor 
    implements Interceptor
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferManagementUnitServiceInterceptor.class);

    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １） 取引カレンダコンテキストに内容をセットする。 <BR>
     * －サービスの引数[0]をGFT振替状況Paramsにキャストする。 <BR>
     * －GFT振替状況Paramsの内容より取引時間コンテ <BR>
     * キストのプロパティをセットする。 <BR>
     * <BR>
     * 取引カレンダコンテキスト.証券会社コード =<BR>
     * GFT振替状況Params.証券会社コード <BR>
     * 取引カレンダコンテキスト.部店コード =<BR>
     * GFT振替状況Params.部店コード <BR>
     * 取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 取引カレンダコンテキスト.受付時間区分 = ”00：その他” <BR>
     * 取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 取引カレンダコンテキスト.注文受付商品 = ”23：為替保証金” <BR>
     * 取引カレンダコンテキスト.注文受付トランザクション = ”09：振替” <BR>
     * <BR>
     * －ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２） 受付日時、日付ロールをセットする。 <BR>
     * －取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３） 口座をロックする。 <BR>
     * －拡張アカウントマネージャ.lock口座( <BR>
     *      証券会社コード,部店コード, 口座コード)をコールする。 <BR>
     * ※引数はGFT振替状況Paramsより編集。
     * 
     * @@param l_method - サービスメソッド
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 41C8D7410330
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        if (l_serviceParam.length == 0)
        {
            log.debug(" パラメータError ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // －サービスの引数[0]をGFT振替状況Paramsにキャストする。
        GftTransferStatusRow l_gftTransferStatusRow = null;
        if(l_serviceParam[0] instanceof GftTransferStatusRow)
        {
            l_gftTransferStatusRow = (GftTransferStatusRow) l_serviceParam[0];
        }
        else
        {
            log.debug(" パラメータError ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
 
        try
        {                    
            //－GFT振替状況Paramsの内容より取引時間コンテキストのプロパティをセットする
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
                
            // 取引カレンダコンテキスト.証券会社コード = GFT振替状況Params.証券会社コード 
            l_context.setInstitutionCode(l_gftTransferStatusRow.getInstitutionCode());
            
            // 取引カレンダコンテキスト.部店コード = GFT振替状況Params.部店コード 
            l_context.setBranchCode(l_gftTransferStatusRow.getBranchCode());
            
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.受付時間区分 = ”00：その他”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = ”23：為替保証金” 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.EXCHANGE_GUARANTEE);
            
            //取引カレンダコンテキスト.注文受付トランザクション = ”09：振替”
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.TRANSFER);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()にて
            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);                
            
            //２）受付日時、日付ロールをセットする
            //WEB3BaseException
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
            
            //FinAppサービスを取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
            
            //３）口座をロックする。
            // －拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
            // ※引数はGFT振替状況Paramsより編集。                     
            l_accMgr.lockAccount(
                l_gftTransferStatusRow.getInstitutionCode(),
                l_gftTransferStatusRow.getBranchCode(),
                l_gftTransferStatusRow.getAccountCode());                        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * 
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 41C8D7410350
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
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
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * 
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 41C8D741036F
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
}@
