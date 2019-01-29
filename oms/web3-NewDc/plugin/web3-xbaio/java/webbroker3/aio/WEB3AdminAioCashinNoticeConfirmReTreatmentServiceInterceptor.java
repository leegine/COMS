head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.27.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioCashinNoticeConfirmReTreatmentServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金通知確認再処理サービスインタセプタクラス(WEB3AdminAioCashinNoticeConfirmReTreatmentServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/20 李俊 (中訊) 新規作成              
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (入金通知確認再処理サービスインタセプタ)<BR>
 * 入金通知確認再処理サービスインタセプタクラス<BR>
 * 
 * @@author 李俊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioCashinNoticeConfirmReTreatmentServiceInterceptor implements Interceptor 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeConfirmReTreatmentServiceInterceptor.class);
    
    /**
     * サービスメソッド開始時にコールされる。  <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。  <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）  <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。  <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より <BR>
     * 取引時間コンテキストのプロパティをセットする。  <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード =  <BR>
     *      より編集  <BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集  <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”  <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”31：入金通知再処理”  <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”  <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”31：入金通知再処理”  <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”00:DEFAULT”  <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     * 取引時間コンテキストをセットする。  <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。  <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * 
     * @@param l_method - (サービスメソッド)<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * @@return Object
     * @@roseuid 4107A4060150
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = null; // 証券会社コード
        String l_strBranchCode = null;      // 部店コード
 
        try
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //証券会社コードを取得
            l_strInstitutionCode = l_admin.getInstitutionCode();
            //部店コードを取得            
            l_strBranchCode = l_admin.getBranchCode();
                
            //リクエストデータの内容と、
            //OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
                
            // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.受付時間区分 = ”31：入金通知再処理” 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.CASHIN_NOTICE_REDEALING);
            
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = ”31：入金通知再処理”   
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.CASHIN_NOTICE_REDEALING);
            
            //取引カレンダコンテキスト.注文受付トランザクション = ”00:DEFAULT”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()にて
            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            //２）受付日時、日付ロールをセットする
            //WEB3BaseException
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__WEB3GentradeTradingTimeManagement.setTimestamp Error__", l_ex);
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
     * サービスメソッド終了時にコールされる。  <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。  <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。  <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG  <BR>
     * 　@取引時間管理.OFFSET_TAG  <BR>
     * 　@取引時間管理.TRADING_CAL_CONTEXT_PATH 
     * @@param l_context - (onCallリターン値)<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * @@roseuid 4107A406015F
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn()";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@param l_obj - (onCallリターン値)<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * @@roseuid 4107A406016F
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
    }
}
@
