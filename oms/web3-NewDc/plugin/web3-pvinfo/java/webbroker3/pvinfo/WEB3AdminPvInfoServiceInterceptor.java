head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ管理者サービスインタセプタ(WEB3AdminPvInfoServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/20 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo;

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
 * (ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ管理者サービスインタセプタ)<BR>
 * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ管理者サービスインタセプタ<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoServiceInterceptor implements Interceptor
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoServiceInterceptor.class);

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－OpLoginSecurityServiceの内容より取引時間コンテキストの<BR>
     * 　@　@　@プロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”00：その他” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”22：顧客サービス” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - (arg0)<BR>
     * @@param l_serviceParam - (arg1)<BR>
     * @@return Object
     * @@roseuid 41624ED20382
     */
    public java.lang.Object onCall(Method l_method, Object[] l_serviceParam)
    {

        final String STR_METHOD_NAME = " onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        //１）　@取引カレンダコンテキストに内容をセットする。
        String l_strInstitutionCode = null; //証券会社コード
        String l_strBranchCode = null; //部店コード
        MainAccount l_mainAccount;

        try
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            log.debug("getService(OpLoginSecurityService.class): " + l_opLoginSec);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();

            if(l_opLoginSec.isAccountIdSet())
            {
                //管理者かそれ以外かを判定し、
                //MainAccountを取得
                l_mainAccount = l_accMgr.getMainAccount(l_opLoginSec.getAccountId());    //throw NotFoundException

                //証券会社コードを取得
                l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
                log.debug("Institution Code: " + l_strInstitutionCode);

                //部店コードを取得
                l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                log.debug("Branch Code: " + l_strBranchCode);
            }
            else
            {
                //口座IDがnullかどうかで、管理者の場合
                //管理者を取得
                WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

                //証券会社コードを取得
                l_strInstitutionCode = l_administrator.getInstitutionCode();
                log.debug("Institution Code: " + l_strInstitutionCode);

                //部店コードを取得
                l_strBranchCode = l_administrator.getBranchCode();
                log.debug("Branch Code: " + l_strBranchCode);
            }

            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

            log.debug("取引時間コンテキストのプロパティをセット: ENTER");
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”00：その他”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.注文受付商品 = ”22：顧客サービス”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
            //取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            log.debug("取引時間コンテキストのプロパティをセット: END");

            //取引時間コンテキストをセットする。
            log.debug("取引時間コンテキストをセット: ENTER");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            log.debug("取引時間コンテキストをセット: END");

            //２）　@受付日時、日付ロールをセットする。
            //WEB3SystemLayerException
            log.debug("受付日時、日付ロールをセット: ENTER");
            WEB3GentradeTradingTimeManagement.setTimestamp();
            log.debug("受付日時、日付ロールをセット: END");

            log.exiting(STR_METHOD_NAME);

            return l_context;
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。" + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

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
     * @@param l_context - (arg0)<BR>
     * @@param l_returnValue - (arg1)<BR>
     * @@roseuid 41624ED203A1
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {

        final String STR_METHOD_NAME = " onReturn(Object,Object)";
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
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (arg0)<BR>
     * @@param l_throwable - (arg1)<BR>
     * @@roseuid 41624ED203C0
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {

        final String STR_METHOD_NAME = " onThrowable(Object,Throwable)";
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
