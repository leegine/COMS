head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesIndividualSettleContractListServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物個別返済一覧表示サービスインタセプタ(WEB3FuturesIndividualSettleContractListServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo;

import java.lang.reflect.Method;
import java.util.Hashtable;

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
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;

/**
 * (先物個別返済一覧表示サービスインタセプタ)<BR>
 * 株価指数先物個別返済一覧表示サービスインタセプタ
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesIndividualSettleContractListServiceInterceptor implements Interceptor
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesIndividualSettleContractListServiceInterceptor.class);

    /**
     * @@roseuid 40F7B0E20148
     */
    public WEB3FuturesIndividualSettleContractListServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@時価の変数をセットする。<BR>
     * ThreadLocalSystemAttributesRegistry.setAttribute()<BR>
     * にてThreadLocalに時価の変数をセットする<BR>
     * 　@　@　@　@　@　@　@設定キー：　@CURRENT_PRICE<BR>
     * 　@　@　@　@　@　@　@値：　@Hashtable（新規に生成したHashtable）<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの<BR>
     * 内容より取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     *   OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     *    OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード = null(*) <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”05：先物”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”03：返済”<BR>
     * <BR>
     *  (*1)(*)商品コードはexecute( )からコールされるメソッド内で、 <BR>
     *  建玉より取得して設定する。 <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *   にて取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 40A992200076
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

        //時価の変数をセットする。
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE, new Hashtable());

        OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);

        //取引カレンダコンテキストを生成する
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        try
        {
            //FinAppサービスを取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
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
            //取引カレンダコンテキスト.商品コード = null<BR>
            l_context.setProductCode(null);


            //取引カレンダコンテキスト.注文受付商品 = ”05：先物”
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
            log.error("該当データなし。", new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(), this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_context;
    }

    /**
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキスト、および時価のクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE<BR>
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * @@roseuid 40A992200095
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

        //取引カレンダコンテキスト、および時価のクリア処理。

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE, null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * 取引カレンダコンテキスト、および時価のクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE<BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 40A9922000A5
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //取引カレンダコンテキスト、および時価のクリア処理。

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE, null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
