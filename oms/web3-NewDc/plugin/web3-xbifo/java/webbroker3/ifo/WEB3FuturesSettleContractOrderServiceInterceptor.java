head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesSettleContractOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済注文サービスインタセプタ(WEB3FuturesSettleContractOrderServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 鄒鋭 (中訊) 新規作成
Revesion History : 2008/03/11 金傑 (中訊) 仕様変更 モデル841
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

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
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物返済注文サービスインタセプタ)<BR>
 * 株価指数先物返済注文サービスインタセプタ
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesSettleContractOrderServiceInterceptor implements Interceptor
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractOrderServiceInterceptor.class);
    /**
     * @@roseuid 40F7B0E20232
     */
    public WEB3FuturesSettleContractOrderServiceInterceptor()
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
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの<BR>
     *      内容より取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     *         OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     *         OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード = （*1) null<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”05：先物”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”03：返済”<BR>
     * <BR>
     *  (*1) 原資産銘柄コードは、execute( )からコールされるメソッド内で、<BR>
     * 　@　@　@建玉より取得して設定する。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *       にて取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * ３）　@リクエストデータの型が「株価指数先物返済注文完了リクエスト」の場合のみ、<BR>
     * 口座をロックする。<BR> 
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR> 
     * ※引数はOpLoginSecurityServiceより編集。 <BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 40A8C2AE0059
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

        OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);

        //取引カレンダコンテキストを生成する
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        try
        {

            //FinAppサービスを取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_opLoginSec.getAccountId()); //throw NotFoundException

            //取引カレンダコンテキストに内容をセットする。

            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
            l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());

            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
            l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());

            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

            // リクエストデータの型が「株価指数先物返済注文完了リクエスト」の場合のみ
            if (l_serviceParam[0] instanceof WEB3FuturesCloseMarginCompleteRequest)
            {
                // 拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする
                l_accountMgr.lockAccount(l_mainAccount.getInstitution().getInstitutionCode(),l_mainAccount.getBranch().getBranchCode(),l_mainAccount.getAccountCode());
            }

            //取引カレンダコンテキスト.商品コード = （*1) null
            l_context.setProductCode(null);

            //取引カレンダコンテキスト.注文受付商品 = ”05：先物”<BR>
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);

            //取引カレンダコンテキスト.注文受付トランザクション = ”03：返済”<BR>
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);

            //取引時間コンテキストをセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

            //受付日時、日付ロールをセットする
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(),this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_context;
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
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * @@roseuid 40A8C2AE0078
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

        //取引カレンダコンテキスト、および時価のクリア処理。

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 40A8C2AE0088
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //取引カレンダコンテキスト、および時価のクリア処理。

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
