head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOffFloorProductListServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 立会外分売銘柄一覧サービスインタセプタ(WEB3EquityOffFloorProductListServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 森川(SRA) 新規作成
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * （立会外分売銘柄一覧サービスインタセプタ）。<BR>
 * <BR>
 * 立会外分売銘柄一覧サービスインタセプタクラス。
 * @@version 1.0
 */
public class WEB3EquityOffFloorProductListServiceInterceptor implements Interceptor
{
    /**
     * (ログ出力ユーティリティ)。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityOffFloorProductListServiceInterceptor.class);

    /**
     * (デフォルトのコンストラクタ)。<BR>
     * <BR>
     * 立会外分売銘柄一覧サービスインタセプタを生成する。<BR>
     */
    public WEB3EquityOffFloorProductListServiceInterceptor()
    {
    }
    

    /**
     * (onCall)。<BR>
     * <BR>
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－OpLoginSecurityServiceの内容より取引時間コンテキストの<BR>
     * 　@　@プロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = null <BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”立会外分売” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”立会外分売” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”照会” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@取引時間コンテキストをセットする。 <BR>
     * 設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * @@param l_method - サービスメソッド<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParam - サービスメソッド引数<Br>
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        OpLoginSecurityService l_opLoginSec
            = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        
        MainAccount l_mainAccount;                  //顧客
        String      l_strInstitutionCode = null;    // 証券会社コード
        String      l_strBranchCode = null;         // 部店コード
        long       l_lngAccountId;

        try
        {
            l_lngAccountId          = l_opLoginSec.getAccountId();
            l_mainAccount           = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode    = l_mainAccount.getInstitution().getInstitutionCode();
            l_strBranchCode         = l_mainAccount.getBranch().getBranchCode();
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(), l_nfe);
        }

        //取引カレンダコンテキストに内容をセットする。
        WEB3GentradeTradingClendarContext l_context
            = new WEB3GentradeTradingClendarContext();
            
        l_context.setInstitutionCode(l_strInstitutionCode);
        l_context.setBranchCode(l_strBranchCode);
        l_context.setMarketCode(null);
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

        //取引時間コンテキストをセットする。
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        //受付日時、日付ロールをセットする。
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_wse)
        {
            throw new WEB3BaseRuntimeException(
                l_wse.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wse.getMessage(), l_wse);                  
        }

        log.exiting(STR_METHOD_NAME);
        return l_context;
    }
    

    /**
     * (onReturn)。<BR>
     * <BR>
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * 立会外分売銘柄一覧サービス終了時にコールされる。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
    }
    

    /**
     * (onThrowable)。<BR>
     * <BR>
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
    }

}
@
