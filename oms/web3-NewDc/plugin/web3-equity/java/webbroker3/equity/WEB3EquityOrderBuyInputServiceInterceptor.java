head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderBuyInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式買付注文入力サービスインタセプタ(WEB3EquityOrderBuyInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/01 欒学峰 (中訊) 新規作成
Revesion History : 2004/12/12 森川   (SRA)  残案件対応
Revesion History : 2005/01/05 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/12/25 柴双紅(中訊) モデルNo.1087
Revesion History : 2008/04/10 崔遠鵬(中訊) モデルNo.1311
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

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.equity.message.WEB3EquityBuyInputRequest;
import webbroker3.equity.message.WEB3EquityProductSelectRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式買付注文入力サービスインタセプタ）。
 * @@version 1.0
 */
public class WEB3EquityOrderBuyInputServiceInterceptor implements Interceptor
{

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderBuyInputServiceInterceptor.class);

    /**
     * @@roseuid 409B2E04031C
     */
    public WEB3EquityOrderBuyInputServiceInterceptor()
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
     * 　@?サービスの引数[0]をリクエストデータオブジェクトにキャストする。 <BR>
     * 　@?リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 　@取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = null<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = (*1) <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = (*2) <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”買付（新規建買）” <BR>
     * <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@取引時間コンテキストをセットする。 <BR>
     * 　@設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@?取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * (*1)受付時間区分 <BR>
     * 　@リクエストデータの型=="現物株式買付注文入力リクエスト"の場合、 <BR>
     * 　@　@?取引区分=="現物買注文"の場合は、"株式・信用"。 <BR>
     * 　@　@?取引区分が上記以外の場合は、"立会外分売"。 <BR>
     * 　@リクエストデータの型が上記以外の場合は、"株式・信用"。 <BR>
     * <BR>
     * (*2)注文受付商品 <BR>
     * 　@リクエストデータの型=="現物株式買付注文入力リクエスト"の場合、 <BR>
     * 　@　@?取引区分=="現物買注文"の場合は、"株式"。 <BR>
     * 　@　@?取引区分が上記以外の場合は、"立会外分売"。 <BR>
     * 　@リクエストデータの型が上記以外の場合は、"株式"。 <BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 406286410114
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String STR_METHOD_NAME = "onCall(Method , Object[] )";
        log.entering(STR_METHOD_NAME);        
    
        Object l_request = l_serviceParam[0];
        String l_strTradingTimeType = null;     //受付時間区分
        String l_strOrderAccProduct = null;     //注文受付商品
        
        //リクエストデータの型＝"現物株式買付注文入力リクエスト"の場合
        if (l_request instanceof WEB3EquityBuyInputRequest)
        {
            //リクエストデータの取得
            WEB3EquityBuyInputRequest l_inputRequest =
                (WEB3EquityBuyInputRequest) l_request;
                
            //取引カレンダコンテキスト設定用の 受付時間区分、注文受付商品の取得
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_inputRequest.tradingType))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
                l_strOrderAccProduct = WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET;
            }
        }
        //リクエストデータの型＝"現物株式買付注文銘柄選択リクエスト"の場合
        else if (l_request instanceof WEB3EquityProductSelectRequest)
        {       
            //取引カレンダコンテキスト設定用の銘柄コードの取得
            l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
            l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    
        long l_lngAccountId; // 口座コード
        String l_strInstitutionCode = null; // 証券会社コード
        String l_strBranchCode = null; // 部店コード
    
        //セキュリティサービスを取得
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        //AccountIdを取得
        l_lngAccountId = l_opLoginSec.getAccountId();
        //FinAppサービスを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        try
        {
            MainAccount l_acc;
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            l_strBranchCode = l_acc.getBranch().getBranchCode();
    
        }
        catch (NotFoundException nfe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }
    
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
        l_context.setInstitutionCode(l_strInstitutionCode);
        // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode(l_strBranchCode);
        // 取引カレンダコンテキスト.市場コード = null
        l_context.setMarketCode(null);
        //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // 取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
        l_context.setTradingTimeType(l_strTradingTimeType);
        // 取引カレンダコンテキスト.注文受付商品 = ”株式”
        l_context.setOrderAcceptProduct(l_strOrderAccProduct);
        // 取引カレンダコンテキスト.注文受付トランザクション =”買付”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
    
        // 取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
    
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);                  
        }
    
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (onReturn)。<BR>
     * <BR>
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * 現物株式買付注文入力サービス終了時にコールされる。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * "system_timestamp" <BR>
     * "xblocks.gtl.attributes.bizdate.offset" <BR>
     * "web3.tradingcalendarcontext" <BR>
     * @@param l_returnValue - onCallリターン値
     * @@param l_context - サービスメソッドリターン値
     * @@roseuid 406286410133
     */
    public void onReturn(Object l_returnValue, Object l_context)
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
     * (onThroable)。<BR>
     * <BR>
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 406286410143
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
