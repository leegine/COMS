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
filename	WEB3EquityPTSCancelOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文取消サービスインタセプタ（WEB3EquityPTSCancelOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 孟亞南 (中訊) 新規作成モデルNo.1213
Revision History : 2008/01/24 張騰宇 (中訊) モデル1294
*/

package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquityCancelConfirmRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.util.WEB3LogUtility;


/**
 * ((PTS)現物株式注文取消サービスインタセプタ)<BR>
 * (PTS)現物株式注文取消サービスインタセプタ<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3EquityPTSCancelOrderServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSCancelOrderServiceInterceptor.class);

    /**
     * @@roseuid 4766071E03CF
     */
    public WEB3EquityPTSCancelOrderServiceInterceptor()
    {

    }

    /**
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@リクエストデータの型が「現物株式注文取消完了リクエスト」の場合のみ、<BR>
     * 　@　@　@口座をロックする。<BR>
     * <BR>
     * 　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, <BR>
     * 　@　@口座コード)をコールする。<BR>
     * 　@　@※引数はOpLoginSecurityServiceより編集。<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@−サービスの引数[0]をリクエストデータオブジェクト（*）にキャストする。<BR>
     * 　@−OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。<BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = <BR>
     * 　@　@　@注文単位.市場IDに該当する市場オブジェクト.市場コード<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = "株式・信用"<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = "株式"<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”取消”<BR>
     * <BR>
     * 　@（*）「現物株式注文取消確認リクエスト」または、<BR>
     * 　@　@　@　@「現物株式注文取消完了リクエスト」<BR>
     * <BR>
     * 　@−ThreadLocalSystemAttributesRegistry.setAttribute()にて<BR>
     * 　@　@取引時間コンテキストをセットする。<BR>
     * 　@　@設定キー：PTS取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@−PTS取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - (メソッド)<BR>
     * @@param l_serviceParams - (サービス引数)<BR>
     * @@return Object
     * @@roseuid 4743945A02A0
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

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        OpLoginSecurityService l_opLoginService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        long l_lngAccountId = l_opLoginService.getAccountId();
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
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

        l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        try
        {
            //リクエストデータの型が「現物株式注文取消完了リクエスト」の場合のみ、口座をロックする。
            if (l_serviceParams[0] instanceof WEB3EquityCancelCompleteRequest)
            {
                //拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
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

        Object l_request = l_serviceParams[0];
        String l_strOrderId = null;
        //取引カレンダコンテキストに内容をセットする。
        //−サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        if (l_request instanceof WEB3EquityCancelCompleteRequest)
        {
            //現物株式注文取消完了リクエスト
            WEB3EquityCancelCompleteRequest l_completeRequest =
                (WEB3EquityCancelCompleteRequest)l_request;
            l_strOrderId = l_completeRequest.id;
        }
        else if (l_request instanceof WEB3EquityCancelConfirmRequest)
        {
            //現物株式注文取消確認リクエスト
            WEB3EquityCancelConfirmRequest l_confirmRequest =
                (WEB3EquityCancelConfirmRequest)l_request;
            l_strOrderId = l_confirmRequest.id;
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

        if (l_strOrderId == null)
        {
            log.debug("注文IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }

        //−リクエストデータの内容と、OpLoginSecurityServiceの内容より
        //取引時間コンテキストのプロパティをセットする。
        String l_strMarketCode = null;
        EqTypeOrderUnit l_eqTypeOrderUnit = null;
        try
        {
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_strOrderId));
            l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
            long l_lngMarketId = l_eqtypeOrderUnitRow.getMarketId();

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(l_lngMarketId);
            l_strMarketCode = l_market.getMarketCode();

            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = 注文単位.市場IDに該当する市場オブジェクト.市場コード
            l_context.setMarketCode(l_strMarketCode);
            //取引カレンダコンテキスト.受付時間区分 = "株式・信用"
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            //取引カレンダコンテキスト.注文受付商品 = "株式"
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            //取引カレンダコンテキスト.注文受付トランザクション = ”取消”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
            // 取引カレンダコンテキスト.銘柄コード = 0 ： DEFAULT
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
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

        try
        {
            //受付日時、日付ロールをセットする。
            //PTS取引時間管理.setTimestamp()をコールする。
            WEB3EquityPTSTradingTimeManagement.setTimestamp();
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
     * 取引カレンダコンテキストクリア処理。<BR>
     * (PTS)株式注文取消サービス終了時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * PTS取引時間管理.TIMESTAMP_TAG<BR>
     * PTS取引時間管理.OFFSET_TAG<BR>
     * PTS取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 474397920286
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
     * 取引カレンダコンテキストクリア処理。<BR>
     * 例外発生時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * PTS取引時間管理.TIMESTAMP_TAG<BR>
     * PTS取引時間管理.OFFSET_TAG<BR>
     * PTS取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 4743979F011E
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
