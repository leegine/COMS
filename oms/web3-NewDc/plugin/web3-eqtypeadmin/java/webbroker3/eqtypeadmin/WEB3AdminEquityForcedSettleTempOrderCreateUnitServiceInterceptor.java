head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済仮注文作成一件サービスインタセプタ(WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 何文敏(中訊) 新規作成 仕様変更モデルNo.131
Revision History : 2008/01/18 張騰宇(中訊) 仕様変更モデルNo.176
*/

package webbroker3.eqtypeadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityManualExpireHandler;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (強制決済仮注文作成一件サービスインタセプタ)<BR>
 * 強制決済仮注文作成一件サービスインタセプタ。<BR>
 * <BR>
 * 強制決済仮注文作成一件サービスに対して設定する。<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor implements Interceptor
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityManualExpireHandler.class);

    /**
     * @@roseuid 462CA4140053
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor()
    {
     
    }
    
    /**
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルより、サービスメソッド開始時にコールされる）<BR>
     * <BR>
     * １）　@顧客オブジェクトの取得<BR>
     * 　@　@拡張アカウントマネージャ.get顧客()をcallする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@口座ID：　@建株Row.口座ID<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]を建株Rowオブジェクトにキャストする。<BR>
     * 　@－建株Rowオブジェクトの内容より取引カレンダコンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 顧客オブジェクトに該当する<BR>
     * 　@　@　@証券会社オブジェクト.証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード = 顧客オブジェクトに該当する<BR>
     * 　@　@　@部店オブジェクト.部店コード <BR>
     * 　@取引カレンダコンテキスト.市場コード = 建株Row.市場IDに該当する<BR>
     * 　@　@　@市場オブジェクト.市場コード<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = "信用取引"<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = "照会"<BR>
     * 　@　@※強制決済は、バッチ中／システム緊急停止中の場合のみ実行不可のため、<BR>
     * 　@　@※注文受付トランザクションには"照会"を設定する。<BR>
     * 　@　@※（仮注文作成時の発注審査でチェックする）<BR>
     * <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
     * 設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ４）　@口座をロックする。<BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@　@　@※引数は取得した顧客オブジェクトより編集。<BR> 
     * <BR>
     * ５）　@強制決済注文の発注審査スキップフラグをセットする。 <BR>
     * 　@ <BR>
     * 　@　@"強制決済注文発注審査スキップフラグ"をLocalThreadにセットする。 <BR>
     * 　@　@－ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     * 　@　@　@　@WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP, <BR>
     * 　@　@　@　@BooleanEnum.True ) <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 4603913E02C7
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // サービスの引数[0]を建株Rowオブジェクトにキャストする。
        // 口座ID：　@建株Row.口座ID
        EqtypeContractRow l_eqtypeContractRow =
            (EqtypeContractRow)l_serviceParams[0];

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        MainAccount l_mainAccount = null;
        WEB3GentradeMarket l_market = null;
        long l_lngMarketId = l_eqtypeContractRow.getMarketId();
        try
        {
            //      １）　@顧客オブジェクトの取得
            // 拡張アカウントマネージャ.get顧客()をcallする。
            l_mainAccount = l_accMgr.getMainAccount(l_eqtypeContractRow.getAccountId());

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(
                l_lngMarketId);
        }
        catch (NotFoundException l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex); 
        }

        // 取引カレンダコンテキストに内容をセットする。
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext(); 
        // 取引カレンダコンテキスト.証券会社コード = 顧客オブジェクトに該当する
        // 証券会社オブジェクト.証券会社コード
        String l_strInstitution = l_mainAccount.getInstitution().getInstitutionCode();
        l_context.setInstitutionCode(l_strInstitution);

        // 取引カレンダコンテキスト.部店コード = 顧客オブジェクトに該当する
        // 部店オブジェクト.部店コード
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        l_context.setBranchCode(l_strBranchCode);

        // 取引カレンダコンテキスト.市場コード = 建株Row.市場IDに該当する
        // 市場オブジェクト.市場コード
        l_context.setMarketCode(l_market.getMarketCode());

        // 取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

        // 取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        // 取引カレンダコンテキスト.注文受付商品 = "信用取引"
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);

        // 取引カレンダコンテキスト.注文受付トランザクション = "照会"
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

        // －ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。
        // 設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        String l_strAccountCode = l_mainAccount.getAccountCode();
        try
        {
            // ３）　@受付日時、日付ロールをセットする。
            // －取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
            // ４）　@口座をロックする。
            // 拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
            // ※引数は取得した顧客オブジェクトより編集。
            l_accMgr.lockAccount(l_strInstitution, l_strBranchCode, l_strAccountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //５）　@強制決済注文の発注審査スキップフラグをセットする。
        //　@　@"強制決済注文発注審査スキップフラグ"をLocalThreadにセットする。
        //　@　@－ThreadLocalSystemAttributesRegistry.setAttribute(
        //　@　@　@　@WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
        //　@　@　@　@BooleanEnum.True )
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
            BooleanEnum.TRUE);

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
     * WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 4603919702A8
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object l_context, Object l_returnValue)";
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
            null);
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
     * WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 460391DE0315
     */
    public void onThrowable(Object l_context, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object l_context, Throwable l_throwable)";
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
            null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
