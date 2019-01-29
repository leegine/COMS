head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransToFXInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替入力サービスインタセプタ(WEB3FXTransToFXInputServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/1/20 屈陽 (中訊) 新規作成   
Revesion History : 2008/09/23 馮海濤 (中訊) 仕様変更・モデルNo.996、1024、1079
Revesion History : 2009/03/12 王志葵 (中訊) 仕様変更・モデルNo.1112
*/

package webbroker3.aio;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.message.WEB3FXTransToFXInputRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (FXへの振替入力サービスインタセプタ) <BR>
 * FXへの振替入力サービスインタセプタクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransToFXInputServiceInterceptor implements Interceptor
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXInputServiceInterceptor.class);
    
    /**
     * @@roseuid 41E780B1036B
     */
    public WEB3FXTransToFXInputServiceInterceptor()
    {
    }

    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@リクエストデータを取得する。<BR>
     * 　@　@サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     *<BR>
     * ２）　@FXシステム区分を取得する。 <BR>
     * 　@−会社別FXシステム条件テーブル(comp_fx_condition)を以下の条件で検索する。<BR>
     * 　@　@検索条件は１）で取得したリクエストデータと、OpLoginSecurityServiceの内容より取得すること。<BR>
     * <BR>
     *     ２−１）リクエストデータ.FXシステムコード がnullでない場合<BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社コード = OpLoginSecurityServiceより編集 <BR>
     * 　@　@部店コード = OpLoginSecurityServiceより編集  <BR>
     * 　@　@FXシステムコード = １）で取得したリクエストデータ.FXシステムコード <BR>
     * <BR>
     *     ２−２）リクエストデータ.FXシステムコード がnullの場合<BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社コード = OpLoginSecurityServiceより編集 <BR>
     * 　@　@部店コード = OpLoginSecurityServiceより編集  <BR>
     * <BR>
     * 　@　@※レコードが取得出来なかった場合または、複数件取得した場合は例外をthrowする。<BR>
     *<BR>
     * ３）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@　@１）で取得したリクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 　@　@　@　@取引時間コンテキストのプロパティをセットする。<BR>
     *<BR>
     * 　@　@　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = ”会社別FXシステム条件.受付時間区分”<BR>
     * 　@　@　@取引カレンダコンテキスト.商品コード = ”02：振替（出金）”<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付商品 = ”会社別FXシステム条件.受付時間区分”<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = ”09：振替”<BR>
     *<BR>
     * 　@　@−ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     * 　@　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     *<BR>
     * ４）　@受付日時、日付ロールをセットする。<BR>
     * 　@　@取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param l_method - サービスメソッド
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 41BCEFE600E3
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String l_strMethodName = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(l_strMethodName);

        //１）　@リクエストデータを取得する。
        //−サービスの引数[0]をリクエストデータオブジェクトにキャストする
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName,
                "パラメータ値不正。");
        }

        String l_strFxSystemCode = null;
        if (l_serviceParam[0] instanceof WEB3FXTransToFXInputRequest)
        {
            WEB3FXTransToFXInputRequest l_fxTransToFXInputRequest =
                (WEB3FXTransToFXInputRequest)l_serviceParam[0];
            l_strFxSystemCode = l_fxTransToFXInputRequest.fxSystemCode;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();

        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        long l_lngAccountId = l_opLoginSecurityService.getAccountId();

        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@FXシステム区分を取得する。
        //会社別FXシステム条件テーブル(comp_fx_condition)を以下の条件で検索する。
        //検索条件は１）で取得したリクエストデータと、OpLoginSecurityServiceの内容より取得すること。
        //[条件]
        //  証券会社コード = OpLoginSecurityServiceより編集
        //  部店コード = OpLoginSecurityServiceより編集
        //  FXシステムコード = １）で取得したリクエストデータ.FXシステムコード

        //証券会社コード = OpLoginSecurityServiceより編集
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //部店コード = OpLoginSecurityServiceより編集
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();

        String l_strQuery = " institution_code = ? and branch_code = ? ";

        List l_lisQuery = new ArrayList();
        l_lisQuery.add(l_strInstitutionCode);
        l_lisQuery.add(l_strBranchCode);

        if (l_strFxSystemCode != null)
        {
            l_strQuery += " and fx_system_code = ? ";
            l_lisQuery.add(l_strFxSystemCode);
        }

        Object[] l_objValues = new Object[l_lisQuery.size()];
        l_lisQuery.toArray(l_objValues);

        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                CompFxConditionRow.TYPE,
                l_strQuery,
                l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //※レコードが取得出来なかった場合または、複数件取得した場合は例外をthrowする。
        if (l_lisRows.size() == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                "テーブルに該当するデータがありません。");
        }

        if (l_lisRows.size() > 1)
        {
            log.debug("データ不整合エラー。");
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + l_strMethodName,
                "データ不整合エラー。");
        }

        CompFxConditionRow l_compFxConditionRow = (CompFxConditionRow)l_lisRows.get(0);

        //３）　@取引カレンダコンテキストに内容をセットする。
        //　@１）で取得したリクエストデータの内容と、OpLoginSecurityServiceの内容より
        //取引時間コンテキストのプロパティをセットする。
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
        l_context.setInstitutionCode(l_strInstitutionCode);
        //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode(l_strBranchCode);
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //引カレンダコンテキスト.受付時間区分 = ”会社別FXシステム条件.受付時間区分”
        l_context.setTradingTimeType(l_compFxConditionRow.getTradingTimeType());
        //取引カレンダコンテキスト.商品コード = ”02：振替（出金）”
        l_context.setProductCode(WEB3ProductCodeDef.TRANSFER_PAYMENT);
        //取引カレンダコンテキスト.注文受付商品 = ”会社別FXシステム条件.受付時間区分”
        l_context.setOrderAcceptProduct(l_compFxConditionRow.getTradingTimeType());
        //取引カレンダコンテキスト.注文受付トランザクション = ”09：振替”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.TRANSFER);

        try
        {
            //ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

            //２）受付日時、日付ロールをセットする
            //取引時間管理.setTimestamp()をコールする
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(l_strMethodName);

        return null;
    }

    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * 
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 41BCEFE60112
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        String l_strMethodName = "onReturn(Object l_context, Object l_returnValue)";
        log.entering(l_strMethodName);
        
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(l_strMethodName);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * 
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 41BCEFE60122
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        String l_strMethodName = "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(l_strMethodName);
        
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(l_strMethodName);
    }
}@
