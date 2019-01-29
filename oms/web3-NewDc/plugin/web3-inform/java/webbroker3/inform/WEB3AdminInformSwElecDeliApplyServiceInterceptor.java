head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformSwElecDeliApplyServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座切替・電子交付申込サービスインタセプタクラス（WEB3AdminInformSwElecDeliApplyServiceInterceptor.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 金傑（中訊）新規作成 モデルNo.099
*/
package webbroker3.inform;

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
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者口座切替・電子交付申込サービスインタセプタ)<BR>
 * 管理者口座切替・電子交付申込サービスインタセプタクラス<BR>
 * <BR>
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3AdminInformSwElecDeliApplyServiceInterceptor implements Interceptor
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformSwElecDeliApplyServiceInterceptor.class);

    public WEB3AdminInformSwElecDeliApplyServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     *１）取引カレンダコンテキストに内容をセットする。<BR>
     *  －リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間 <BR>
     *  コンテキストのプロパティをセットする。<BR>
     *  <BR>
     *  取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     *  取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     *  取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     *  取引カレンダコンテキスト.受付時間区分 = ”35：顧客情報伝票作成”<BR>
     *  取引カレンダコンテキスト.商品コード = ”0：DEFAULT”<BR>
     *  取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス”<BR>
     *  取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”<BR>
     *  <BR>
     *  －ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間 <BR>
     *  コンテキストをセットする。<BR>
     *  設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     *  <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * －取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - (サービスメソッド) <BR>
     * サービスメソッド
     * @@param l_serviceParams - (サービスメソッド引数) <BR>
     * サービスメソッド引数
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // 取引カレンダコンテキストに内容をセットする
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        try
        {
            //－リクエストデータの内容と、OpLoginSecurityServiceの内容より
            //  取引時間コンテキストのプロパティをセットする。
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            MainAccount l_mainAccount = null;

            String l_strInstitutionCode = null;
            String l_strBranchCode = null;
            if (l_opLoginSec.isAccountIdSet())
            {
                // 管理者かそれ以外かを判定し、
                // MainAccountを取得
                l_mainAccount = l_accountManager.getMainAccount(l_opLoginSec.getAccountId());

                // 証券会社コードを取得
                l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();

                // 部店コードを取得
                l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            }
            else
            {
                // 口座IDがnullかどうかで、管理者の場合
                // 管理者を取得
                WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

                // 証券会社コードを取得
                l_strInstitutionCode = l_administrator.getInstitutionCode();

                // 部店コードを取得
                l_strBranchCode = l_administrator.getBranchCode();
            }
            // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);

            // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);

            // 取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            // 取引カレンダコンテキスト.受付時間区分 = ”35：顧客情報伝票作成”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.ACCOUNTINFO_VOUCHER_CREATE);

            // 取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            // 取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);

            // 取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);

            //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。
            //  設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //２）受付日時、日付ロールをセットする。
            //－取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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

        log.exiting(STR_METHOD_NAME);
        return l_context;
    }

    /**
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCallリターン値) <BR>
     * onCallリターン値 <BR>
     * @@param l_returnValue - (サービスメソッドリターン値) <BR>
     * サービスメソッドリターン <BR>
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }

}
@
