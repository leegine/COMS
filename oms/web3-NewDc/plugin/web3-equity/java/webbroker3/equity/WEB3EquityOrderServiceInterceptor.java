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
filename	WEB3EquityOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文サービスインタセプタ(WEB3EquityOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 洪華(中訊) 新規作成
Revesion History : 2004/12/20 中尾寿彦(SRA) 残案件対応による修正
Revesion History : 2005/01/05 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/12/25 柴双紅(中訊) モデルNo.1087
*/
package webbroker3.equity;

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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文サービスインタセプタ）。<BR>
 * <BR> 
 * 株式注文サービスに登録するインタセプタクラス。
 * @@version 1.0
 */
public class WEB3EquityOrderServiceInterceptor
    implements Interceptor
{

    /**
     * （コンストラクタ）。
     * @@roseuid 402219B1036A
     */
    public WEB3EquityOrderServiceInterceptor()
    {
    }

    /**
     * （Logger）。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderServiceInterceptor.class);
    
    /**
     * （onCall）。<BR>
     *<BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = 市場.is優先市場コード( )==trueの場合null。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@以外、リクエストデータ.市場コードを編集。<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = (*1)<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = (*2)<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = リクエストデータより判定して編集(*3)<BR>
     * <BR>
     * 　@(*1)受付時間区分<BR>
     * 　@　@　@リクエストデータの型が「現物株式買付注文確認リクエスト」<BR>
     * 　@　@　@　@または「現物株式買付注文完了リクエスト」の場合は、以下の通り。<BR>
     * 　@　@　@　@－取引区分=="現物買注文"の場合は、"株式・信用"。<BR>
     * 　@　@　@　@－取引区分が上記以外の場合は、"立会外分売"。<BR>
     * 　@　@　@リクエストデータの型が上記以外の場合は、"株式・信用"。<BR>
     * <BR>
     * 　@(*2)注文受付商品<BR>
     * 　@　@　@リクエストデータの型が「現物株式買付注文確認リクエスト」<BR>
     * 　@　@　@　@または「現物株式買付注文完了リクエスト」の場合は、以下の通り。<BR>
     * 　@　@　@　@－取引区分=="現物買注文"の場合は、"株式"。<BR>
     * 　@　@　@　@－取引区分が上記以外の場合は、"立会外分売"。<BR>
     * 　@　@　@リクエストデータの型が上記以外の場合は、"株式"。<BR>
     * <BR>
     * 　@(*3)リクエストデータの型が「現物株式買付注文確認リクエスト」<BR>
     * 　@　@　@　@または「現物株式買付注文完了リクエスト」であれば、”買付”。<BR>
     * 　@　@　@　@上記以外であれば、”売付”。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
     * 設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）　@リクエストデータの型が「現物株式買付注文完了リクエスト」または<BR>
     * 　@　@　@「現物株式売付注文完了リクエスト」の場合のみ、口座をロックする。<BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@　@　@※引数はOpLoginSecurityServiceより編集。
     * @@param l_method （メソッド）。<BR>
     * 　@　@　@メソッド。
     * @@param l_serviceParam -（サービスの引数）。<BR>
     * 　@　@　@サービスのメソッドに渡される引数。<BR>
     * 　@　@　@株式注文サービスの場合、株式注文リクエストオブジェクト。
     * @@return Object<BR>
     * @@roseuid 4014AC0201A0
     */
    public java.lang.Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        // １）　@取引カレンダコンテキストに内容をセットする。
        // 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        Object l_request = l_serviceParam[0];
        String l_strMarketCode = null;
        String l_strTradingTimeType = null;
        String l_strOrderAccProduct = null;
        String l_strOrderAccTransction = null;
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            WEB3EquityBuyConfirmRequest l_buyConfirmRequest =
                (WEB3EquityBuyConfirmRequest)l_request;
            l_strMarketCode = l_buyConfirmRequest.marketCode;
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_buyConfirmRequest.tradingType))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
                l_strOrderAccProduct = WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET;
            }
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
        }
        else if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            WEB3EquityBuyCompleteRequest l_buyCompleteRequest =
                (WEB3EquityBuyCompleteRequest)l_request;
            l_strMarketCode = l_buyCompleteRequest.marketCode;
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_buyCompleteRequest.tradingType))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
                l_strOrderAccProduct = WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET;
            }
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
        }
        else if (l_request instanceof WEB3EquitySellConfirmRequest)
        {
            WEB3EquitySellConfirmRequest l_sellConfirmRequest =
                (WEB3EquitySellConfirmRequest)l_request;
            l_strMarketCode = l_sellConfirmRequest.marketCode;
            l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
            l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
        }
        else if (l_request instanceof WEB3EquitySellCompleteRequest)
        {
            WEB3EquitySellCompleteRequest l_sellCompleteRequest =
                (WEB3EquitySellCompleteRequest)l_request;
            l_strMarketCode = l_sellCompleteRequest.marketCode;
            l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
            l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
        }
        else
        {
            log.error("__株式注文サービスは未対応です__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より
        // 　@　@取引時間コンテキストのプロパティをセットする。
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        WEB3GentradeMainAccount l_account = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        OpLoginSecurityService l_opLoginSec =
              (OpLoginSecurityService) Services.getService(
                  OpLoginSecurityService.class);
        try
        {
            long l_lngAccountId = l_opLoginSec.getAccountId();
            l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_account.getInstitution().getInstitutionCode();
            l_strBranchCode = l_account.getBranch().getBranchCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
        l_context.setInstitutionCode(l_strInstitutionCode);
        // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode(l_strBranchCode);
        // 取引カレンダコンテキスト.市場コード = 市場.is優先市場コード( )==trueの場合null。
        // 以外、リクエストデータ.市場コードを編集。
        if (WEB3GentradeMarket.isPriorityMarket(l_strMarketCode))
        {
            l_context.setMarketCode(null);
        }
        else
        {
            l_context.setMarketCode(l_strMarketCode);
        }
        // 取引カレンダコンテキスト.受付時間区分
        l_context.setTradingTimeType(l_strTradingTimeType);
        // 取引カレンダコンテキスト.注文受付商品
        l_context.setOrderAcceptProduct(l_strOrderAccProduct);
        // 取引カレンダコンテキスト.注文受付トランザクション
        l_context.setOrderAcceptTransaction(l_strOrderAccTransction);
        //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT); 

        // 取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
              l_context);
        try
        {
            // ２）　@受付日時、日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            // ３）　@リクエストデータの型が「現物株式買付注文完了リクエスト」または
            // 　@　@　@「現物株式売付注文完了リクエスト」の場合のみ、口座をロックする。
            if (l_request instanceof WEB3EquityBuyCompleteRequest ||
                l_request instanceof WEB3EquitySellCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_account.getAccountCode());
            }
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
     * （onReturn）。<BR>
     *<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * 株式注文サービス終了時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_context （onCallリターン値）。
     * @@param l_returnValue （サービスメソッドリターン値）。
     * @@roseuid 4014AC0202C9
     */
    public void onReturn(
        Object l_context,
        Object l_returnValue)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
    }

    /**
     * （onThrowable）。<BR>
     *<BR> 
     * 取引カレンダコンテキストクリア処理。<BR>
     * 例外発生時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj （onCallリターン値）。
     * @@param l_throwable （例外オブジェクト）。
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
