head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExpirationDateListServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文有効期限取得サービスインタセプタ(WEB3ExpirationDateListServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 于瀟(中訊) 新規作成モデル319
*/

package webbroker3.gentrade;

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
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.message.WEB3ExpirationDateListRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (注文有効期限取得サービスインタセプタ)<BR>
 * 注文有効期限取得サービスインタセプタクラス<BR>
 *
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ExpirationDateListServiceInterceptor implements Interceptor
{
    /**
     * WEB3LogUtility log<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ExpirationDateListServiceInterceptor.class);

    /**
     * @@roseuid 47B3E127007D
     */
    public WEB3ExpirationDateListServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@-サービスの引数[0]をリクエストデータオブジェクトにキャストする。 <BR>
     * 　@-リクエストデータの内容と、OpLoginSecurityServiceの内容<BR>
     * 　@　@　@より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード =<BR>
     * 　@　@（リクエストデータ.商品区分 = ”現物株式”または、 ”信用取引”の場合、<BR>
     * 　@　@　@　@　@リクエストデータ.市場コード<BR>
     * 　@　@　@リクエストデータ.商品区分 = ”先物”または、”オプション”の場合、<BR>
     * 　@　@　@　@　@”DEFAULT” ）<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = <BR>
     * 　@　@（リクエストデータ.商品区分 = ”現物株式”または、”信用取引”の場合、<BR>
     * 　@　@　@　@　@”DEFAULT” <BR>
     * 　@　@　@リクエストデータ.商品区分 = ”先物”または、”オプション”の場合、<BR>
     * 　@　@　@リクエストデータ.指数種別 = nullの場合、”DEFAULT”  以外、<BR>
     * 　@　@　@　@　@リクエストデータ.指数種別）<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = <BR>
     * 　@　@（リクエストデータ.商品区分 = ”現物株式”または、”信用取引”の場合、<BR>
     * 　@　@　@　@　@”株式・信用”<BR>
     * 　@　@　@リクエストデータ.商品区分 = ”先物”または、”オプション”の場合、<BR>
     * 　@　@　@　@　@”株価指数先物OP”）<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = <BR>
     * 　@　@（リクエストデータ.商品区分 = ”現物株式”の場合、”株式”<BR>
     * 　@　@　@リクエストデータ.商品区分 = ”信用取引”の場合、”信用取引” <BR>
     * 　@　@　@リクエストデータ.商品区分 = ”先物”の場合、”先物” <BR>
     * 　@　@　@リクエストデータ.商品区分 = ”オプション”の場合、”オプション”）<BR>
     * <BR>
     * 　@-ThreadLocalSystemAttributesRegistry.setAttribute( )に<BR>
     * 　@　@　@て取引時間コンテキストをセットする。 <BR>
     * 設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@-取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 47A905AF01A9
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        //取引カレンダコンテキストに内容をセットする。
        //-サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        //-リクエストデータの内容と、OpLoginSecurityServiceの内容より
        //取引時間コンテキストのプロパティをセットする
        Object l_request = l_serviceParams[0];
        WEB3ExpirationDateListRequest l_expirationDateListRequest = null;
        if (l_request instanceof WEB3ExpirationDateListRequest)
        {
            l_expirationDateListRequest =
                (WEB3ExpirationDateListRequest)l_request;
        }

        long l_lngAccountId; // 口座コード
        String l_strInstitutionCode = null; // 証券会社コード
        String l_strBranchCode = null; // 部店コード
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        //セキュリティサービスを取得
        try
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            //AccountIdを取得
            l_lngAccountId = l_opLoginSec.getAccountId();
            //FinAppサービスを取得
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_acc;
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            l_strBranchCode = l_acc.getBranch().getBranchCode();
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
        l_context.setInstitutionCode(l_strInstitutionCode);

        //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode(l_strBranchCode);

        //取引カレンダコンテキスト.市場コード =
        //リクエストデータ.商品区分 = ”現物株式”または、 ”信用取引”の場合、リクエストデータ.市場コード
        //リクエストデータ.商品区分 = ”先物”または、”オプション”の場合、”DEFAULT” ）
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setMarketCode(l_expirationDateListRequest.marketCode);
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        }

        //取引カレンダコンテキスト.銘柄コード =
        //（リクエストデータ.商品区分 = ”現物株式”または、”信用取引”の場合、”DEFAULT”
        //リクエストデータ.商品区分 = ”先物”または、”オプション”の場合、
        //リクエストデータ.指数種別 = nullの場合、”DEFAULT”  以外、リクエストデータ.指数種別）
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            if (l_expirationDateListRequest.targetProductCode == null)
            {
                l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            }
            else
            {
                l_context.setProductCode(l_expirationDateListRequest.targetProductCode);
            }
        }

        //取引カレンダコンテキスト.受付時間区分 =
        //（リクエストデータ.商品区分 = ”現物株式”または、”信用取引”の場合、”株式・信用”
        //リクエストデータ.商品区分 = ”先物”または、”オプション”の場合、”株価指数先物OP”
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
        }

        //取引カレンダコンテキスト.注文受付商品 =
        //（リクエストデータ.商品区分 = ”現物株式”の場合、”株式”
        //リクエストデータ.商品区分 = ”信用取引”の場合、”信用取引”
        //リクエストデータ.商品区分 = ”先物”の場合、”先物”
        //リクエストデータ.商品区分 = ”オプション”の場合、”オプション”）
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        else if (WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
        }
        else if (WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
        }

        //ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。
        //設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_context;
    }

    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * 注文有効期限取得サービス終了時にコールされる。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 47A905BE01F0
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
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 47A905D40098
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
