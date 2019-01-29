head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionCancelOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）OP取消注文サービスインタセプタ（WEB3ToSuccOptionCancelOrderServiceInterceptor.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 金傑 (中訊) 新規作成 モデルNo.280
*/
package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）OP取消注文サービスインタセプタ)<BR>
 * （連続）OP取消注文サービスインタセプタ<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3ToSuccOptionCancelOrderServiceInterceptor implements Interceptor
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionCancelOrderServiceInterceptor.class);

    /**
     * @@roseuid 47FDC51403C8
     */
    public WEB3ToSuccOptionCancelOrderServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@リクエストデータの型が<BR>
     * 「（連続）株価指数オプション取消注文完了リクエスト」の場合のみ、口座をロックする。 <BR>
     * 　@-サービスの引数[0]をリクエストデータオブジェクトにキャストして判別する。<BR>
     * 　@-拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 <BR>
     *      ※引数はOpLoginSecurityServiceより編集。 <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * <BR>
     * 　@-リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 　@取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”12：株価指数先物OP（取消訂正）” <BR>
     * 　@取引カレンダコンテキスト.商品コード = （*1) 原資産銘柄コード <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”06：オプション” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”06：取消” <BR>
     * <BR>
     *  (*1) 原資産銘柄コードの取得方法@ <BR>
     * 　@リクエストデータ.ＩＤに該当する予約注文単位オブジェクトを生成し、 <BR>
     * 　@予約注文単位オブジェクト.get銘柄().get原資産銘柄コード ()で取得する。 <BR>
     * <BR>
     * 　@-ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 <BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。 <BR>
     * 　@-取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * @@return Object
     * @@roseuid 47A914B10155
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        // セキュリティサービスを取得
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        // 口座ＩＤ
        long l_lngAccountId = l_opLoginSec.getAccountId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        // 取引カレンダコンテキスト
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strProductCode = null;
        try
        {
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            // 証券会社コード
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            // 部店コード
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            // 口座コード
            l_strAccountCode = l_mainAccount.getAccountCode();

            long l_lngOrderId;

            Object l_request = l_serviceParams[0];
            // リクエストデータの型が「（連続）株価指数オプション取消注文完了リクエスト」の場合のみ、口座をロックする。
            if (l_request instanceof WEB3SuccOptionsCancelCompleteRequest)
            {
                WEB3SuccOptionsCancelCompleteRequest l_completeRequest =
                    (WEB3SuccOptionsCancelCompleteRequest)l_request;
                l_lngOrderId = Long.parseLong(l_completeRequest.id);

                // 拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
                l_accMgr.lockAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
            }
            else if (l_request instanceof WEB3SuccOptionsCancelConfirmRequest)
            {
                WEB3SuccOptionsCancelConfirmRequest l_confirmRequest =
                    (WEB3SuccOptionsCancelConfirmRequest)l_request;
                l_lngOrderId = Long.parseLong(l_confirmRequest.id);
            }
            else
            {
                log.debug("パラメータタイプ不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータタイプ不正。");
            }

            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit =
                l_orderManager.getReserveIfoOrderUnit(l_lngOrderId);

            IfoProductRow l_ifoProductRow = (IfoProductRow)l_toSuccIfoOrderUnit.getProduct().getDataSourceObject();
            l_strProductCode = l_ifoProductRow.getUnderlyingProductCode();
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
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
        l_context.setInstitutionCode(l_strInstitutionCode);
        // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode(l_strBranchCode);
        // 取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        // 取引カレンダコンテキスト.受付時間区分 = ”12：株価指数先物OP（取消訂正）”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL);
        // 取引カレンダコンテキスト.商品コード = （*1) 原資産銘柄コード
        l_context.setProductCode(l_strProductCode);
        // 取引カレンダコンテキスト.注文受付商品 = ”06 : オプション”
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
        // 取引カレンダコンテキスト.注文受付トランザクション = ”06：取消”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);

        // －ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする。
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        try
        {
            // 取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
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
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * @@roseuid 47A914B10165
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (onThrowable)<BR>
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * @@roseuid 47A914B10175
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
