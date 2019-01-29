head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建入力サービスインタセプタ(WEB3MarginOpenMarginInputServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10   王暁傑 (Sinocom) 新規作成 
                   2005/01/05 岡村   (SRA) 使用されないimport文を削除
                   2006/12/26 張騰宇 (中訊) モデル 1087
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginOpenMarginInputRequest;
import webbroker3.equity.message.WEB3MarginProductSelectRequest;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

/**
 * （信用取引新規建入力サービスインタセプタ）。<BR>
 * <BR>
 * 信用取引新規建入力サービスインタセプタクラス
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3MarginOpenMarginInputServiceInterceptor implements Interceptor 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOpenMarginInputServiceInterceptor.class);
            
    /**
     * @@roseuid 4142B32D035C
     */
    public WEB3MarginOpenMarginInputServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 　@　@取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     *       OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = 市場.is優先市場コード( )==trueの場合null。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@以外、リクエストデータ.市場コードを編集。<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     *      注文受付トランザクション(*)<BR>
     * <BR>
     * 　@(*)注文受付トランザクション<BR>
     * 　@－リクエストデータ.取引区分 == <BR>
     *        ”新規買建注文”の場合、”01：買付（新規建買）”<BR>
     * 　@－リクエストデータ.取引区分 == <BR>
     *        ”新規売建注文”の場合、”02：売付（新規建売）”<BR>
     * 　@－以外(リクエストデータ設定なし時)、”01：買付（新規建買）”をセット。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object<BR>
     * @@roseuid 407BBBDB01F3<BR>
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        String l_strMarketCode = null;
        String l_strTradingType = null;
        WEB3MarginOpenMarginInputRequest l_request = null;
        
        log.debug("l_serviceParams[0] = " + l_serviceParams[0].getClass().getName());
        
        if (l_serviceParams[0] instanceof WEB3MarginOpenMarginInputRequest)
        {
            l_request = (WEB3MarginOpenMarginInputRequest)l_serviceParams[0];
            l_strMarketCode = l_request.marketCode;
            l_strTradingType = l_request.tradingType;
        }
        else if ((l_serviceParams[0] instanceof WEB3MarginProductSelectRequest))
        {
            l_strMarketCode = null;            
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        
        try
        {
            
            
            //１）　@取引カレンダコンテキストに内容をセットする
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
        
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
            OpLoginSecurityService l_opLoginSecurityService = (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        
            MainAccount l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_opLoginSecurityService.getAccountId());
        
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
            
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
            
            //　@取引カレンダコンテキスト.市場コード = 市場.is優先市場コード( )==trueの場合null。
            //　@　@　@　@　@                          　@以外、リクエストデータ.市場コードを編集。
            if (WEB3GentradeMarket.isPriorityMarket(l_strMarketCode))
            {
                l_strMarketCode = null;
            }
            l_context.setMarketCode(l_strMarketCode);
            
            //取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            
            //取引カレンダコンテキスト.注文受付トランザクション = 
            //（－リクエストデータ.取引区分 == ”新規買建注文”の場合、”01：買付（新規建買）”
            //  －リクエストデータ.取引区分 == ”新規売建注文”の場合、”02：売付（新規建売）”
            //  －以外(リクエストデータ設定なし時)、”01：買付（新規建買）”をセット。)
            if (WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(l_strTradingType))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            }
            else if (WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(l_strTradingType))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
            }
            else
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            }
            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
            
            //受付日時、日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
                   
        }
        catch (NotFoundException l_nfex)
        {
            log.error(STR_METHOD_NAME, l_nfex);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_webex)
        {
            log.error(STR_METHOD_NAME, l_webex);
            throw new WEB3BaseRuntimeException(l_webex.getErrorInfo(), STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 407BBBDB0212
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 407BBBDB0222
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
    }
}
@
