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
filename	WEB3IfoOrderAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文受付UnitServiceインタセプタクラス(WEB3IfoOrderAcceptUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/8 艾興 (中訊) 新規作成  
*/
package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP注文受付UnitServiceインタセプタ)<BR>
 * 先物OP注文受付UnitServiceインタセプタクラス<BR>
 * @@author  艾興
 * @@version 1.0
 */
public class WEB3IfoOrderAcceptUnitServiceInterceptor implements Interceptor
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOrderAcceptUnitServiceInterceptor.class);
    /**
     * @@roseuid 41AD58890280
     */
    public WEB3IfoOrderAcceptUnitServiceInterceptor()
    {

    }

    /**
     * (onCall)<BR>
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－引数.サービスの引数[0]を先物OP注文受付キューParamsオブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コ<BR>
     * ンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 先物OP注文受付キューParams.証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード = 先物OP注文受付キューParams.部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード = null<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = null<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）　@口座をロックする。<BR>
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     *      ※引数は先物OP注文受付キューParamsより編集。<BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object<BR>
     * @@roseuid 414AC42C0325
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        if (l_serviceParams[0] == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@取引カレンダコンテキストに内容をセットする。
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
  
        //取引カレンダコンテキストを生成する
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
            
        //引数.サービスの引数[0]を先物OP注文受付キューParamsオブジェクトにキャストする
        if (!(l_serviceParams[0] instanceof HostFotypeOrderAcceptParams))
        {
            log.error("パラメータタイプ不正");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //サービスの引数[0]を株式注文受付キューParamsオブジェクトにキャストする。
        HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams =
            (HostFotypeOrderAcceptParams)l_serviceParams[0];
            
        String l_strInstitutionCode = l_hostFotypeOrderAcceptParams.getInstitutionCode();
        String l_strBranchCode = l_hostFotypeOrderAcceptParams.getBranchCode();
        String l_strAccountCode = l_hostFotypeOrderAcceptParams.getAccountCode();
        
        //取引カレンダコンテキスト.証券会社コード = 先物OP注文受付キューParams.証券会社コード
        l_context.setInstitutionCode(l_strInstitutionCode);
        //取引カレンダコンテキスト.部店コード = 先物OP注文受付キューParams.部店コード
        l_context.setBranchCode(l_strBranchCode);
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
        l_context.setTradingTimeType(
            WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
        //取引カレンダコンテキスト.商品コード = null
        l_context.setProductCode(null);   
        //取引カレンダコンテキスト.注文受付商品 = null
        l_context.setOrderAcceptProduct(null);
        //取引カレンダコンテキスト.注文受付トランザクション = null
        l_context.setOrderAcceptTransaction(null);
        
        //　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。
        //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
        //１）日付ロールをセットする
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                                        
        // ３）　@口座をロックする
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        try
        {
            l_accountManager.lockAccount(l_strInstitutionCode,l_strBranchCode,l_strAccountCode);
        }
        catch(WEB3BaseException l_wbe)
        {
            log.debug(STR_METHOD_NAME, l_wbe);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbe.getMessage(),
                l_wbe);
        }

        log.exiting(STR_METHOD_NAME);
        return null;

    }

    /**
     * (onReturn)<BR>
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 414AC42C0354
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {

        final String STR_METHOD_NAME = "OnReturn";
        log.entering(STR_METHOD_NAME);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.exiting(STR_METHOD_NAME);
    

    }

    /**
     * (onThrowable)<BR>
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 414AC42C0364
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "OnReturn";
        log.entering(STR_METHOD_NAME);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
