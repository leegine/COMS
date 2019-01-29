head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondExecuteReferenceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会インタセプタ(WEB3AdminBondExecuteReferenceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏 (中訊) 新規作成  
*/

package webbroker3.adminorderexecinquiry;

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
 * (債券管理者注文約定照会インタセプタ)<BR>
 * 債券管理者注文約定照会インタセプタクラス<BR>
 * 
 * @@author 何文敏（中訊）
 * @@version 1.0
 */
public  class WEB3AdminBondExecuteReferenceInterceptor implements Interceptor 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteReferenceInterceptor.class);
    
    /**
     * @@roseuid 44E335A30271
     */
    public WEB3AdminBondExecuteReferenceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR> 
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR> 
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR> 
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR> 
     * 　@－OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR> 
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = "0:DEFAULT"<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = "0:DEFAULT"<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = "25:債券"<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = "28:債券"<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = "07：照会"<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR> 
     * 設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR> 
     * 　@－取引時間管理.setTimestamp()をコールする。
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッド
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数
     * @@return Object
     * @@roseuid 44C6E6030365
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
                
        //１）取引カレンダコンテキストに内容をセットする
        try
        {
            //管理者
            WEB3Administrator l_administrator = 
                WEB3Administrator.getInstanceFromLoginInfo();
            
            //リクエストデータの内容と、OpLoginSecurityServiceの内容より
            //取引時間コンテキストのプロパティをセットする
            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();
            
            //証券会社コードを取得する
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
            
            //部店コードを取得する
            String l_strBranchCode = l_administrator.getBranchCode();
    
            //取引カレンダコンテキスト.set証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //取引カレンダコンテキスト.set部店コード
            l_context.setBranchCode(l_strBranchCode);
            
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.銘柄コード = "0:DEFAULT" 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.受付時間区分 = "25:債券" 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
            
            //取引カレンダコンテキスト.注文受付商品 = "28:債券" 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.BOND);
            
            //取引カレンダコンテキスト.注文受付トランザクション = "07：照会" 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする 
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //２）受付日時、日付ロールをセットする
            //取引時間管理.setTimestamp()をコールする   
            WEB3GentradeTradingTimeManagement.setTimestamp();

            log.exiting(STR_METHOD_NAME);
            return null;
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR> 
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR> 
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR> 
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR> 
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値
     * @@param l_returnValue - (ビジネスメソッドリターン値)<BR>
     * ビジネスメソッドリターン値
     * @@roseuid 44C6E6150336
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        
        String l_strMethodName = "onReturn(Object l_context, Object l_returnValue)";
        log.entering(l_strMethodName);
        
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);        
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR> 
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR> 
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR> 
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR> 
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値
     * @@param l_throwable - (ビジネスメソッドリターン値)<BR>
     * ビジネスメソッドリターン値
     * @@roseuid 44C6E62F01DF
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    { 
        String STR_METHOD_NAME = "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);    

        log.exiting(STR_METHOD_NAME);
    }
    
}
@
