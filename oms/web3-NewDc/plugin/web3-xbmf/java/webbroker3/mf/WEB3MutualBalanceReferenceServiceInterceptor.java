head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信残高照会サービスインタセプタ(WEB3MutualBalanceReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 于美麗 (中訊) 新規作成
*/

package webbroker3.mf;

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
 * (投信残高照会サービスインタセプタ)<BR>
 * 投信残高照会サービスインタセプタ
 *
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceServiceInterceptor implements Interceptor 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBalanceReferenceServiceInterceptor.class);
   
   /**
    * (onCall)<BR>
    * サービスメソッド開始時にコールされる。<BR> 
    * <BR>
    * 取引カレンダが利用するコンテキストを生成する。 <BR>
    * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
    * <BR>
    * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
    * 　@-リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
    *    取引時間コンテキストのプロパティをセットする。 <BR>
    * <BR>
    * 　@　@　@取引カレンダコンテキスト.証券会社コード = <BR>
    * OpLoginSecurityServiceより編集 <BR>
    * 　@　@　@取引カレンダコンテキスト.部店コード = <BR>
    *  OpLoginSecurityServiceより編集 <BR>
    * 　@　@　@取引カレンダコンテキスト.市場コード = "0:DEFAULT" <BR>
    * 　@　@　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
    * 　@　@　@取引カレンダコンテキスト.受付時間区分 = "06:投資信託"<BR>
    * 　@　@　@取引カレンダコンテキスト.注文受付商品 = "07:投資信託" <BR>
    * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = "07：照会"<BR> 
    * 
    * 　@-ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
    * 取引時間コンテキストをセットする。 <BR>
    * 　@　@［setAttributeに渡すパラメタ］ <BR>
    * 　@　@　@設定キー: 取引時間コンテキスト.TRADING_CALENDAR_TAG <BR>
    * 　@　@　@設定値: 取引時間コンテキスト <BR>
    * 
    * ２）　@受付日時、日付ロールをセットする。 <BR>
    * 　@-取引時間管理.setTimestamp()をコールする。 <BR>
    * @@param l_method - サービスメソッドオブジェクト
    * @@param l_serviceParam - サービスメソッド引数
    * @@return Object
    * @@roseuid 41AD8F0E0149
    */
   public Object onCall(Method l_method, Object[] l_serviceParam) 
   {
       final String STR_METHOD_NAME = 
           "onCall(Method l_method, Object[] l_serviceParam)";
       log.entering(STR_METHOD_NAME);
       
       //-リクエストデータの内容と、 OpLoginSecurityServiceの内容より
       //-取引時間コンテキストのプロパティをセットする        
       WEB3GentradeTradingClendarContext l_context =
           new WEB3GentradeTradingClendarContext();  

       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
           
       OpLoginSecurityService l_opLoginSec =
           (OpLoginSecurityService) Services.getService(
               OpLoginSecurityService.class);
               
       long l_lngAccountId = l_opLoginSec.getAccountId();
       WEB3GentradeAccountManager l_accMgr = 
           (WEB3GentradeAccountManager) l_finApp.getAccountManager();
       MainAccount l_acc = null;
       
       try{
           l_acc = l_accMgr.getMainAccount(l_lngAccountId);
           String l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
           String l_strBranchCode = l_acc.getBranch().getBranchCode(); 
           
           //取引カレンダコンテキスト.set証券会社コード
           l_context.setInstitutionCode(l_strInstitutionCode); 
           
           //取引カレンダコンテキスト.set部店コード
           l_context.setBranchCode(l_strBranchCode);
           
           //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
           l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
           
           //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
           l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
           
           //取引カレンダコンテキスト.受付時間区分 = "06:投資信託"
           l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
           
           //取引カレンダコンテキスト.注文受付商品 = ”07：投資信託”
           l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MUTUAL_FUND);
           
           //取引カレンダコンテキスト.注文受付トランザクション = "07：照会"
           l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
           
           //-ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセット
           ThreadLocalSystemAttributesRegistry.setAttribute(
                 WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                 l_context);
           
           WEB3GentradeTradingTimeManagement.setTimestamp();
       }
       catch (NotFoundException l_ex)
       {
           log.error("__an unexpected error__", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);
       }
       catch (WEB3BaseException l_ex)
       {
           log.error("__an unexpected error__ ",l_ex);
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
    * (onReturn)<BR>
    * サービスメソッド終了時にコールされる。<BR>
    * <BR>
    * 取引カレンダコンテキストクリア処理。 <BR>
    * <BR>
    * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
    * <BR>
    * 　@取引時間管理.TIMESTAMP_TAG <BR>
    * 　@取引時間管理.OFFSET_TAG <BR>
    * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG
    * @@param l_context - onCallリターン値
    * @@param l_returnValue - サービスメソッドリターン値
    * @@roseuid 41AD8F0E0178
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
    * (onThrowable)<BR>
    * サービスメソッドが例外をスローした場合にコールされる。<BR>
    * <BR>
    * 取引カレンダコンテキストクリア処理。 <BR>
    * <BR>
    * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
    * <BR>
    * 　@取引時間管理.TIMESTAMP_TAG <BR>
    * 　@取引時間管理.OFFSET_TAG <BR>
    * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG
    * @@param l_obj - onCallリターン値
    * @@param l_throwable - 例外オブジェクト
    * @@roseuid 41AD8F0E0197
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
}
@
