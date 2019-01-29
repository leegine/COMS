head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果アップロードインタセプタクラス(WEB3AdminIpoLotResultUploadInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/26 李海波 (中訊) 新規作成
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>068
*/

package webbroker3.ipo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
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
 * 管理者IPO抽選結果アップロードインタセプタクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultUploadInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoServiceInterceptor.class);
    
    /**
     * @@roseuid 411301770221
     */
    public WEB3AdminIpoLotResultUploadInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。  <BR>
     *  <BR>
     * 取引カレンダが利用するコンテキストを生成する。  <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）  <BR>
     *  <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。  <BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。  <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間 <BR>
     * コンテキストのプロパティをセットする。  <BR>
     *  <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     *   取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより 編集<BR>
     *   取引カレンダコンテキスト.市場コード = ”0：DEFAULT”  <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”17：アップロード（管理者）”  <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”  <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”20：IPO”  <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT” <BR>
     *  <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     * 取引時間コンテキストをセットする。  <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     *  <BR>
     * ２）　@受付日時、日付ロールをセットする。  <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。  <BR>
     *  <BR>
     * @@param l_method - サービスメソッド
     * @@param l_serviceParams - サービスメソッド引数
     * @@return Object
     * @@roseuid 40F23F640020
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {

        final String STR_METHOD_NAME = " onCall(Method,Object[])";

        log.entering(STR_METHOD_NAME);

        //１）　@取引カレンダコンテキストに内容をセットする。
        String l_strInstitutionCode = null; //証券会社コード
        String l_strBranchCode = null; //部店コード

        try
        {
                  	
            //管理者の場合は管理者オブジェクトから取得する
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            // 証券会社コードを取得
            l_strInstitutionCode = l_admin.getInstitutionCode();
            // 部店コードを取得 
            l_strBranchCode = l_admin.getBranchCode();              
        	
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            log.debug("取引時間コンテキストのプロパティをセット: ENTER");
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”17：アップロード（管理者）”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD);
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.注文受付商品 = ”20：IPO” 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.IPO);
            //取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            log.debug("取引時間コンテキストのプロパティをセット: END");

            //取引時間コンテキストをセットする。
            log.debug("取引時間コンテキストをセット: ENTER");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            log.debug("取引時間コンテキストをセット: END");

            //２）　@受付日時、日付ロールをセットする。
            //WEB3SystemLayerException
            log.debug("受付日時、日付ロールをセット: ENTER");
            WEB3GentradeTradingTimeManagement.setTimestamp();
            log.debug("受付日時、日付ロールをセット: END");

            log.exiting(STR_METHOD_NAME);
            return l_context;
            
        }
        catch (WEB3BaseException l_ex)
        {   
            
            log.error(l_ex.getMessage(), l_ex);
            
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
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 40F23F640030
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {

        final String STR_METHOD_NAME = " onReturn(Object,Object)";
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
     * サービスメソッドが例外をスローした場合にコールされる。  <BR>
     *  <BR>
     * 取引カレンダコンテキストクリア処理。  <BR>
     *  <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。  <BR>
     *  <BR>
     * 取引時間管理.TIMESTAMP_TAG  <BR>
     * 取引時間管理.OFFSET_TAG  <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 40F23F640033
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {

        final String STR_METHOD_NAME = " onThrowable(Object,Throwable)";
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
}
@
