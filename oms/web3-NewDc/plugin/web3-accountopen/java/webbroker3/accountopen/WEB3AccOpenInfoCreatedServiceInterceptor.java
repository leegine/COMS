head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInfoCreatedServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込サービスインタセプタ(WEB3AccOpenInfoCreatedServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 郭英 (中訊) 新規作成
Revesion History : 2009/08/10 張騰宇(中訊) 仕様変更 モデル165
*/

package webbroker3.accountopen;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (口座開設申込サービスインタセプタ)<BR>
 * 口座開設申込サービスインタセプタ<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3AccOpenInfoCreatedServiceInterceptor implements Interceptor
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenInfoCreatedServiceInterceptor.class);

    /**
     * @@roseuid 41B5AB7D02AF
     */
    public WEB3AccOpenInfoCreatedServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－リクエストデータの内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = ※証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード = ※部店コード <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”22：口座開設” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07:照会” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * 　@※証券会社コード，部店コード<BR>
     * 　@　@リクエストデータが口座開設申込入力リクエスト、<BR>
     * 　@　@　@或は口座開設メールアドレス登録入力リクエスト、 <BR>
     * 　@　@　@或は口座開設メールアドレス登録完了リクエストの場合、 <BR>
     * リクエストデータより編集する。<BR>
     * 　@　@以外の場合、リクエストデータ.口座開設申込情報より編集する。<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     * @@roseuid 419C8E5E0283
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = null;
        
        String l_strBranchCode = null;
              
        if (l_serviceParam[0] instanceof WEB3AccOpenApplyInputRequest)
        {
            WEB3AccOpenApplyInputRequest l_request = (WEB3AccOpenApplyInputRequest)l_serviceParam[0];
            
            //証券会社コード
            l_strInstitutionCode = l_request.institutionCode;     
            
            // 部店コード
            l_strBranchCode = l_request.branchCode;   
        }
        else if (l_serviceParam[0] instanceof WEB3AccOpenApplyConfirmRequest)
        {
            WEB3AccOpenApplyConfirmRequest l_request = (WEB3AccOpenApplyConfirmRequest)l_serviceParam[0];
            
            if (l_request.accoutOpenApplyInfo != null)
            {
                //証券会社コード
                l_strInstitutionCode = l_request.accoutOpenApplyInfo.institutionCode;     
            
                // 部店コード
                l_strBranchCode = l_request.accoutOpenApplyInfo.branchCode;
            }
        }
        else if (l_serviceParam[0] instanceof WEB3AccOpenApplyCompleteRequest)
        {
            WEB3AccOpenApplyCompleteRequest l_request = (WEB3AccOpenApplyCompleteRequest)l_serviceParam[0];
            
            if (l_request.accoutOpenApplyInfo != null)
            {
                //証券会社コード
                l_strInstitutionCode = l_request.accoutOpenApplyInfo.institutionCode;     
            
                // 部店コード
                l_strBranchCode = l_request.accoutOpenApplyInfo.branchCode; 
            }
        }
        else if (l_serviceParam[0] instanceof WEB3AccOpenMailAddrRegInputRequest)
        {
            WEB3AccOpenMailAddrRegInputRequest l_request = (WEB3AccOpenMailAddrRegInputRequest)l_serviceParam[0];
            //証券会社コード
            l_strInstitutionCode = l_request.institutionCode;

            // 部店コード
            l_strBranchCode = l_request.branchCode;
        }
        else if (l_serviceParam[0] instanceof WEB3AccOpenMailAddrRegCompleteRequest)
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request = (WEB3AccOpenMailAddrRegCompleteRequest)l_serviceParam[0];
            //証券会社コード
            l_strInstitutionCode = l_request.institutionCode;

            // 部店コード
            l_strBranchCode = l_request.branchCode;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try {
        
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
                
            //取引カレンダコンテキスト.証券会社コード = ※証券会社コード 
            l_context.setInstitutionCode(l_strInstitutionCode);
    
            //取引カレンダコンテキスト.部店コード = ※部店コード  
            l_context.setBranchCode(l_strBranchCode);
    
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”  
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
    
            // 取引カレンダコンテキスト.受付時間区分 = ”22：口座開設”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.ACCOUNT_OPEN);
            
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”  
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
    
            //取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
    
            //取引カレンダコンテキスト.注文受付トランザクション = ”07:照会”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            
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
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(), STR_METHOD_NAME);             
        }
    }

    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG <BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 419C8E5E0293
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(STR_METHOD_NAME);     
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG <BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 419C8E5E0296
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
