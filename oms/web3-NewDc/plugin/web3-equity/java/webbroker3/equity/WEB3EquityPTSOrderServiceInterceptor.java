head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文サービスインタセプタ（WEB3EquityPTSOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 孟亞南 (中訊) 新規作成モデルNo.1215
Revision History : 2008/01/24 張騰宇 (中訊) モデル1294
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
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.util.WEB3LogUtility;


/**
 * ((PTS)現物株式注文サービスインタセプタ)<BR>
 * (PTS)現物株式注文サービスインタセプタ<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3EquityPTSOrderServiceInterceptor implements Interceptor
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSOrderServiceInterceptor.class);

    /**
     * @@roseuid 4766071E0332
     */
    public WEB3EquityPTSOrderServiceInterceptor()
    {

    }

    /**
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。 <BR>
     * 　@－リクエストデータの内容と、<BR>
     * 　@　@OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = リクエストデータ.市場コードを編集。 <BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”  <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = "株式・信用"<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = "株式" <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     * 　@　@　@　@リクエストデータより判定して編集(*1) <BR>
     * <BR>
     * 　@(*1)リクエストデータの型が「現物株式買付注文確認リクエスト」 <BR>
     * 　@　@　@　@または「現物株式買付注文完了リクエスト」であれば、”買付”。 <BR>
     * 　@　@　@　@上記以外であれば、”売付”。 <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute()にて<BR>
     * 　@　@取引時間コンテキストをセットする。 <BR>
     * 　@　@設定キー：PTS取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－PTS取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）　@リクエストデータの型が「現物株式買付注文完了リクエスト」または <BR>
     * 　@　@　@「現物株式売付注文完了リクエスト」の場合のみ、口座をロックする。 <BR>
     * <BR>
     * 　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@※引数はOpLoginSecurityServiceより編集。 <BR>
     * @@param l_method - (メソッド)<BR>
     * @@param l_serviceParams - (サービスの引数)<BR>
     * @@return Object
     * @@roseuid 474A9A78016C
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //取引カレンダコンテキストに内容をセットする。
        //サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        Object l_request = l_serviceParams[0];
        //市場コード
        String l_strMarketCode = null;
        //注文受付トランザクション
        String l_strOrderAccTransction = null;
        //現物株式買付注文確認リクエスト
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            WEB3EquityBuyConfirmRequest l_buyConfirmRequest =
                (WEB3EquityBuyConfirmRequest)l_request;
            //市場コード
            l_strMarketCode = l_buyConfirmRequest.marketCode;
            //注文受付トランザクション ”買付”
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
        }
        //現物株式買付注文完了リクエスト
        else if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            WEB3EquityBuyCompleteRequest l_buyCompleteRequest =
                (WEB3EquityBuyCompleteRequest)l_request;
            //市場コード
            l_strMarketCode = l_buyCompleteRequest.marketCode;
            //注文受付トランザクション ”買付”
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
        }
        //現物株式売付注文完了リクエスト
        else if (l_request instanceof WEB3EquitySellCompleteRequest)
        {
            WEB3EquitySellCompleteRequest l_sellCompleteRequest =
                (WEB3EquitySellCompleteRequest)l_request;
            //市場コード
            l_strMarketCode = l_sellCompleteRequest.marketCode;
            //注文受付トランザクション ”売付”
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
        }
        //現物株式売付注文確認リクエスト
        else if (l_request instanceof WEB3EquitySellConfirmRequest)
        {
            WEB3EquitySellConfirmRequest l_sellConfirmRequest =
                (WEB3EquitySellConfirmRequest)l_request;
            //市場コード
            l_strMarketCode = l_sellConfirmRequest.marketCode;
            //注文受付トランザクション ”売付”
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        //取引カレンダコンテキスト
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        OpLoginSecurityService l_opLoginService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        try
        {
            long l_lngAccountId = l_opLoginService.getAccountId();
            l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
        l_context.setInstitutionCode(l_strInstitutionCode);
        //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode(l_strBranchCode);
        //取引カレンダコンテキスト.市場コード = リクエストデータ.市場コードを編集。
        l_context.setMarketCode(l_strMarketCode);
        //取引カレンダコンテキスト.受付時間区分 = "株式・信用"
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        //取引カレンダコンテキスト.注文受付商品 = "株式"
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        //取引カレンダコンテキスト.注文受付トランザクション = リクエストデータより判定して
        l_context.setOrderAcceptTransaction(l_strOrderAccTransction);
        //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        //取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        try
        {
            //受付日時、日付ロールをセットする。
            WEB3EquityPTSTradingTimeManagement.setTimestamp();

            //リクエストデータの型が「現物株式買付注文完了リクエスト」または
            //「現物株式売付注文完了リクエスト」の場合のみ、口座をロックする。
            if (l_request instanceof WEB3EquityBuyCompleteRequest
                || l_request instanceof WEB3EquitySellCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_mainAccount.getAccountCode());
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
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
     * 取引カレンダコンテキストクリア処理。 <BR>
     * PTS現物株式注文サービス終了時にコールされる。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * PTS取引時間管理.TIMESTAMP_TAG <BR>
     * PTS取引時間管理.OFFSET_TAG <BR>
     * PTS取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 474A9ABD0012
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        //PTS取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG, null);

        //PTS取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.OFFSET_TAG, null);

        //PTS取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 取引カレンダコンテキストクリア処理。 <BR>
     * 例外発生時にコールされる。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * PTS取引時間管理.TIMESTAMP_TAG <BR>
     * PTS取引時間管理.OFFSET_TAG <BR>
     * PTS取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 474A9B0600AC
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        //PTS取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG, null);

        //PTS取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.OFFSET_TAG, null);

        //PTS取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
