head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.38.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽｲﾝﾀｾﾌﾟﾀ(WEB3AdminSrvRegiOtherOrgIdDownloadServiceInterceptor.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 柴双紅(中訊) 新規作成 モデルNo.336
*/

package webbroker3.srvregi;

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
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽｲﾝﾀｾﾌﾟﾀ)<BR>
 * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽｲﾝﾀｾﾌﾟﾀクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadServiceInterceptor implements Interceptor
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminSrvRegiOtherOrgIdDownloadServiceInterceptor.class);

    /**
     * @@roseuid 47D1112B03BB
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－リクエストデータの内容と、<BR>
     * 　@OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”27：サービス利用”<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”21：サービス利用”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute()<BR>
     * 　@にて取引時間コンテキストをセットする。<BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 47B2953B02A6
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

        //OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする
        long l_lngAccountId;
        //証券会社コード
        String l_strInstitutionCode = null;
        //部店コード
        String l_strBranchCode = null;
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        //AccountIdを取得
        l_lngAccountId = l_opLoginSec.getAccountId();
        //FinAppサービスを取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        try
        {
            if (l_opLoginSec.isAccountIdSet())
            {
                MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
                l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
                l_strBranchCode = l_acc.getBranch().getBranchCode();
            }
            else
            {
                //口座IDがnullかどうかで、管理者の場合
                //管理者を取得
                WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
    
                //証券会社コードを取得
                l_strInstitutionCode = l_administrator.getInstitutionCode();
    
                //部店コードを取得
                l_strBranchCode = l_administrator.getBranchCode();
            }
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
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

        //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
        l_context.setInstitutionCode(l_strInstitutionCode);
        //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode(l_strBranchCode);
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_context.setMarketCode(WEB3ProductCodeDef.DEFAULT);
        //取引カレンダコンテキスト.受付時間区分 = ”27：サービス利用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SRVREGI);
        //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        //取引カレンダコンテキスト.注文受付商品 = ”21：サービス利用”
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.SRV_REGI);
        //取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);

        //取引時間コンテキストをセットする。
        //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context
            );
        //受付日時、日付ロールをセットする
        //－取引時間管理.setTimestamp()をコールする。
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
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
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 47B2953B02A9
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
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 47B2953B02B6
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
