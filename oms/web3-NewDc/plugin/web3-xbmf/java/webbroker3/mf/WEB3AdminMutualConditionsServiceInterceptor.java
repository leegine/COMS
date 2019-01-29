head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者投信銘柄条件登録サービスインタセプタ(WEB3AdminMutualConditionsServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 王蘭芬(中訊)新規作成
*/
package webbroker3.mf;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteRequest;

/**
 * 管理者投信銘柄条件登録サービスインタセプタ<BR>
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualConditionsServiceInterceptor implements Interceptor 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsServiceInterceptor.class);
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストの<BR>
     * 　@　@プロパティをセットする。<BR>
     * <BR>
     * 　@　@　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.銘柄コード = リクエストデータで渡された銘柄コード <BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = WEB3TradingTimeTypeDef.投資信託<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付商品 = ”07：投資信託”<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT（すべて）” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする。<BR>
     * 　@　@［setAttributeに渡すパラメタ］<BR>
     * 　@　@　@設定キー： 取引時間コンテキスト.TRADING_CALENDAR_TAG<BR>
     * 　@　@　@設定値： 取引時間コンテキスト<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParam - サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 40B14C310302
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        
        if (l_serviceParam.length == 0)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }       
        
        // 銘柄コードを取得
        Object l_request = l_serviceParam[0];
        String l_strProductCode = null; //銘柄コード
        WEB3AdminMutualConditionsInputRequest l_inputRequest = null;
        WEB3AdminMutualConditionsConfirmRequest l_confirmRequest = null;
        WEB3AdminMutualConditionsCompleteRequest l_completeRequest = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
        if (l_request instanceof WEB3AdminMutualConditionsInputRequest)
        {            
            l_inputRequest = (WEB3AdminMutualConditionsInputRequest) l_request;
            try 
            {
                    if (l_inputRequest != null && l_inputRequest.id != null)
                    {    
                    WEB3MutualFundProduct l_mfProduct = 
                        (WEB3MutualFundProduct)l_mfProductManager.getProduct(
                                Long.parseLong(l_inputRequest.id));
                    l_strProductCode = l_mfProduct.getProductCode();
                 }
            }
            catch (NotFoundException l_ex)
            {
                log.error("データ不整合エラー。", l_ex);
            }
        }
        else if (l_request instanceof WEB3AdminMutualConditionsConfirmRequest)
        {
            l_confirmRequest = (WEB3AdminMutualConditionsConfirmRequest) l_request;
            if (l_confirmRequest != null 
                        && l_confirmRequest.mutualProductInfo.mutualProductCode != null)
            {
                 l_strProductCode = l_confirmRequest.mutualProductInfo.mutualProductCode;
            }
        }        
        else if (l_request instanceof WEB3AdminMutualConditionsCompleteRequest) 
        {
            l_completeRequest = (WEB3AdminMutualConditionsCompleteRequest)l_request;
            if (l_completeRequest != null 
                        && l_completeRequest.mutualProductInfo.mutualProductCode != null)
            {
                 l_strProductCode = l_completeRequest.mutualProductInfo.mutualProductCode;
            }
        }
        else
        {
            log.debug(" パラメータError ");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@取引カレンダコンテキストに内容をセットする。
        try 
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();   
            // 証券会社コードを取得                                                                                                                   
            String l_strInstitutionCode = l_admin.getInstitutionCode();
            // 部店コードを取得                                                                                                                     
            String l_strBranchCode = l_admin.getBranchCode();
            //リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.銘柄コード = リクエストデータで渡された銘柄コード 
            l_context.setProductCode(l_strProductCode);
            //取引カレンダコンテキスト.受付時間区分 = WEB3TradingTimeTypeDef.投資信託 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            //取引カレンダコンテキスト.注文受付商品 = ”07：投資信託”                                                                                        
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MUTUAL_FUND);
            //取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT（すべて）” 
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.DEFAULT);
            //－ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
                
            log.debug("証券会社コード = "+l_context.getInstitutionCode());
            log.debug("部店コード = "+l_context.getBranchCode());
            log.debug("市場コード = "+l_context.getMarketCode());
            log.debug("銘柄コード = "+l_context.getProductCode());
            log.debug("受付時間区分 = "+l_context.getTradingTimeType());
            log.debug("注文受付商品 = "+l_context.getOrderAcceptProduct());
            log.debug("注文受付トランザクション = "+l_context.getOrderAcceptTransaction());
            
            //受付日時、日付ロールをセットする
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("Error: ", l_ex);
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
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 40B14C310321
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    }
    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>s
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 40B14C310331
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    }

}
@
