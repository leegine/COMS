head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.24.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXAccOpenServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座開設サービスインタセプタ(WEB3FXAccOpenServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/25 周勇 (中訊) 新規作成
Revesion History : 2008/09/22 武波 (中訊) 仕様変更・モデル1014,1022,1033,1068,1079
Revesion History : 2009/03/18 車進 (中訊) 仕様変更・モデル1120
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.message.WEB3FXAccOpenAskingRequest;
import webbroker3.aio.message.WEB3FXAccOpenCompleteRequest;
import webbroker3.aio.message.WEB3FXAccOpenCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXAccOpenConfirmRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX口座開設サービスインタセプタ) <BR>
 * FX口座開設サービスインタセプタクラス <BR>
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FXAccOpenServiceInterceptor implements Interceptor
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FXAccOpenServiceInterceptor.class);
    
    /**
     * @@roseuid 41E7829801A5
     */
    public WEB3FXAccOpenServiceInterceptor()
    {
    }

    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@リクエストデータを取得する。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@<BR>
     * ２）　@FXシステム区分を取得する。<BR>
     * 　@－会社別FXシステム条件テーブル(comp_fx_condition)を以下の条件で検索する。<BR>
     * 　@　@検索条件は１）で取得したリクエストデータと、<BR>
     * 　@　@　@　@　@OpLoginSecurityServiceの内容より取得すること。<BR>
     * <BR>
     * 　@　@２－１）リクエストデータ.FXシステムコード がnullでない場合<BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@　@部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@　@FXシステムコード = １）で取得したリクエストデータ.FXシステムコード<BR>
     * <BR>
     * 　@　@２－２）リクエストデータ.FXシステムコード がnullの場合<BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@　@部店コード = OpLoginSecurityServiceより編集<BR>
     * <BR>
     * 　@　@※レコードが取得出来なかった場合または、複数件取得した場合は例外をthrowする。<BR>
     * ３）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－１）で取得したリクエストデータの内容と、<BR>
     * 　@　@　@OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@　@取引カレンダコンテキスト.受付時間区分 = ２）で取得した会社別FXシステムParam.受付時間区分<BR>
     * 　@　@取引カレンダコンテキスト.商品コード = ”01：口座開設”<BR>
     * 　@　@取引カレンダコンテキスト.注文受付商品 = ２）で取得した会社別FXシステムParam.受付時間区分<BR>
     * 　@　@取引カレンダコンテキスト.注文受付トランザクション = ”01：買付”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *      にて取引時間コンテキストをセットする。<BR>
     * 　@　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ４）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * 
     * @@param l_method - サービスメソッド
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 41C78BE002E2
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@リクエストデータを取得する。
        //－サービスの引数[0]をリクエストデータオブジェクトにキャストする
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(
            OpLoginSecurityService.class);
        
        long l_lngAccountId = l_opLoginSecurityService.getAccountId();

        Object l_request = l_serviceParam[0];
        String l_strFxSystemCode = null;
        if (l_request instanceof WEB3FXAccOpenCompleteSoapRequest)
        {
            l_strFxSystemCode =
                ((WEB3FXAccOpenCompleteSoapRequest)l_request).fxSystemCode;
        }
        else if (l_request instanceof WEB3FXAccOpenAskingRequest)
        {
            l_strFxSystemCode =
                ((WEB3FXAccOpenAskingRequest)l_request).fxSystemCode;
        }
        else if (l_request instanceof WEB3FXAccOpenConfirmRequest)
        {
            l_strFxSystemCode =
                ((WEB3FXAccOpenConfirmRequest)l_request).fxSystemCode;
        }
        else if (l_request instanceof WEB3FXAccOpenCompleteRequest)
        {
            l_strFxSystemCode =
                ((WEB3FXAccOpenCompleteRequest)l_request).fxSystemCode;
        }

        //２）　@FXシステム区分を取得する。
        //[条件]
        //証券会社コード = OpLoginSecurityServiceより編集
        //部店コード = OpLoginSecurityServiceより編集
        //FXシステムコード = １）で取得したリクエストデータ.FXシステムコード
        try
        {

            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            
            //証券会社コードを取得する
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            
            //部店コードを取得する
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

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

            List l_lisCompFxConditionRows = l_queryProcessor.doFindAllQuery(
                CompFxConditionRow.TYPE,
                l_strQuery,
                l_objValues);

            if (l_lisCompFxConditionRows.size() == 0)
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }

            if (l_lisCompFxConditionRows.size() > 1)
            {
                log.debug("データ不整合エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "データ不整合エラー。");
            }

            CompFxConditionRow l_compFxConditionRow =
                (CompFxConditionRow)l_lisCompFxConditionRows.get(0);
            //３）　@取引カレンダコンテキストに内容をセットする。
            //－１）で取得したリクエストデータの内容と、
            //OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);

            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);

            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //取引カレンダコンテキスト.受付時間区分 = ２）で取得した会社別FXシステムParam.受付時間区分
            l_context.setTradingTimeType(l_compFxConditionRow.getTradingTimeType());

            //取引カレンダコンテキスト.商品コード = ”01：口座開設”
            l_context.setProductCode(WEB3ProductCodeDef.ACCOUNT_OPEN);

            //取引カレンダコンテキスト.注文受付商品 = ２）で取得した会社別FXシステムParam.受付時間区分
            l_context.setOrderAcceptProduct(l_compFxConditionRow.getTradingTimeType());

            //取引カレンダコンテキスト.注文受付トランザクション = ”01：買付”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);

            //ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする 
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //４）受付日時、日付ロールをセットする
            //取引時間管理.setTimestamp()をコールする 
            //throw SystemLayerException  
            WEB3GentradeTradingTimeManagement.setTimestamp();    
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in l_accountManager.getMainAccount__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in lockAccount__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                            
        log.exiting(STR_METHOD_NAME);        
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
     * @@roseuid 41C78BE002F1
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
        
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
        
        log.exiting(STR_METHOD_NAME);  
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
     * @@roseuid 41C78BE00311
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);
        
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

        log.exiting(STR_METHOD_NAME);
    }
}@
