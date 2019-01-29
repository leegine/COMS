head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqUploadServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式アップロードサービスインタセプタ(WEB3AdminFeqUploadServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 呉艶飛(中訊) 新規作成
*/
package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者外国株式アップロードサービスインタセプタ) <BR>
 * 管理者外国株式アップロードサービスインタセプタ
 * 
 * @@ author 呉艶飛 
 * @@ version 1.0
 */
public class WEB3AdminFeqUploadServiceInterceptor implements Interceptor 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminFeqUploadServiceInterceptor.class);
    
    /**
     * @@roseuid 42D0CED30232
     */
    public WEB3AdminFeqUploadServiceInterceptor() 
    {
     
    }
    
    /**
     * (onCall) <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）  <BR>
     *  <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。  <BR>
     * 　@－ログインセッションよりログインＩＤを取得， <BR>
     * 　@　@ログインＩＤに該当する管理者の情報より、 <BR>
     * 　@　@以下の通り取引時間コンテキストのプロパティをセットする。  <BR>
     *  <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 管理者.証券会社コード  <BR>
     * 　@取引カレンダコンテキスト.部店コード = 管理者.部店コード  <BR>
     * 　@取引カレンダコンテキスト.市場コード = null <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 =   <BR>
     * 　@　@　@　@”26：アップロード終日（管理者） ”  <BR>
     * 　@取引カレンダコンテキスト.商品コード = 0：DEFAULT <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”04：外国株” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”  <BR>
     *   <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( ) <BR>
     * 　@　@にて取引時間コンテキストをセットする。  <BR>
     * 　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     *  <BR>
     * ２）　@日付ロールをセットする。  <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。  <BR>
     * @@param l_method - (サービスメソッド) <BR>
     * サービスメソッドオブジェクト
     * 
     * @@param l_serviceParams - (サービスの引数) <BR>
     * サービスの引数配列
     * @@return Object
     * @@roseuid 429B2C4300E3
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

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
            
            //取引カレンダコンテキスト.受付時間区分 = ”26：アップロード終日（管理者） ”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD_DAYLONG);
            
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = ”04：外国株”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
            
            //取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT” 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            
            // 取引時間コンテキストをセットする
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
            log.error("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);                
        }
    }
    
    /**
     * (onReturn) <BR>
     * サービスメソッド終了時にコールされる。  <BR>
     * 取引カレンダコンテキストクリア処理。  <BR>
     *  <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。  <BR>
     *  <BR>
     * 取引時間管理.TIMESTAMP_TAG  <BR>
     * 取引時間管理.OFFSET_TAG  <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * @@param l_context - (onCall返却値)
     * @@param l_returnValue - (サービスメソッド返却値)
     * @@roseuid 429B2C4300E6
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
     * (onThrowable) <BR>
     * サービスメソッドが例外をスローした場合にコールされる。  <BR>
     * 取引カレンダコンテキストクリア処理。  <BR>
     *  <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。  <BR>
     *  <BR>
     * 取引時間管理.TIMESTAMP_TAG  <BR>
     * 取引時間管理.OFFSET_TAG  <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * @@param l_obj - (onCall返却値)
     * @@param l_throwable - (例外) <BR>
     * 例外オブジェクト
     * @@roseuid 429B2C4300F3
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
