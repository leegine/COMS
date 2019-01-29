head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券売却入力サービスインタセプタ(WEB3BondSellInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
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
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (債券売却入力サービスインタセプタ)<BR>
 * 債券売却入力サービスインタセプタ<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondSellInputServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSellInputServiceInterceptor.class);
    
    /**
     * @@roseuid 44FCD0BB029F
     */
    public WEB3BondSellInputServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@-リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@取引カレンダコンテキスト.証券会社コード = <BR>
     * 　@　@　@　@　@OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.市場コード = "0：DEFAULT"<BR>
     * 　@　@　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = "25：債券"<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付商品 = "28：債券"<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = "02：売付"<BR>
     * <BR>
     * 　@-ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     * 　@　@［setAttributeに渡すパラメタ］<BR>
     * 　@　@　@設定キー: 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 　@　@　@設定値: 取引時間コンテキスト<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッド<BR>
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 44E9911F0232
     */
    public java.lang.Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        try
        {
            //１）　@取引カレンダコンテキストに内容をセットする。 
            //　@-リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストの 
            //　@プロパティをセットする。
            OpLoginSecurityService l_opLoginSecurityService = 
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            
            FinApp l_finapp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finapp.getAccountManager();
            
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            
            //証券会社コード
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();
            
            //部店コード
            String l_strBranchCode =
                l_mainAccount.getBranch().getBranchCode();
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            
            //　@　@　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //　@　@　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 
            l_context.setBranchCode(l_strBranchCode);
            
            //　@　@　@取引カレンダコンテキスト.市場コード = "0：DEFAULT" 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //　@　@　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //　@　@　@取引カレンダコンテキスト.受付時間区分 = "25：債券" 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
            
            //　@　@　@取引カレンダコンテキスト.注文受付商品 = "28：債券" 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.BOND);
            
            //　@　@　@取引カレンダコンテキスト.注文受付トランザクション = "02：売付" 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
            
            //　@-ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストを 
            //　@セットする。 
            //　@　@［setAttributeに渡すパラメタ］ 
            //　@　@　@設定キー: 取引時間管理.TRADING_CAL_CONTEXT_PATH 
            //　@　@　@設定値: 取引時間コンテキスト 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);            

            //２）　@受付日時、日付ロールをセットする。 
            //　@取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getMainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
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
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_returnValue - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_context - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 44E9911F0251
     */
    public void onReturn(Object l_returnValue, Object l_context) 
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object )";
        log.entering(STR_METHOD_NAME);
        
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
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 44E9911F0261
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
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
