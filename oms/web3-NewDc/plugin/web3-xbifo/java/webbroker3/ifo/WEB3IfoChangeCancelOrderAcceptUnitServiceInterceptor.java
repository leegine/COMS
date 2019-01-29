head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP訂正取消受付UnitServiceインタセプタクラス(WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/8 艾興 (中訊) 新規作成  
*/
package webbroker3.ifo;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

/**
 * (先物OP訂正取消受付UnitServiceインタセプタ)<BR>
 * 先物OP訂正取消受付UnitServiceインタセプタクラス<BR>
 * @@author  艾興
 * @@version 1.0
 */
public class WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor
    implements Interceptor
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor.class);

    /**
     * @@roseuid 41AD62EB0242
     */
    public WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。
     * 
     * 取引カレンダが利用するコンテキストを生成する。
     * （xTradeカーネルよりサービス実行前に呼び出される）
     * 
     * １）　@取引カレンダコンテキストに内容をセットする。
     * 　@－引数.サービスの引数[0]を先物OP注文受付キューParamsオブジェクトにキャストする。
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。
     * 
     * 　@取引カレンダコンテキスト.証券会社コード = 先物OP注文受付キューParams.証券会社コード
     * 　@取引カレンダコンテキスト.部店コード = 先物OP注文受付キューParams.部店コード
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”
     * 　@取引カレンダコンテキスト.商品コード = null
     * 　@取引カレンダコンテキスト.注文受付商品 = null
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null
     * 
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * ２）日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）　@当日場中の基準値取得のための属性をセットする。 <BR>
     * 　@－取引時間管理.set発注日計算用基準日時( )にて<BR>
     * 　@　@　@発注日計算の基準日時に、注文単位.発注日＋HHMMSSとして"000000"をセットする。 <BR>
     * <BR>
     * ３）　@口座をロックする。<BR>
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     *      ※引数は先物OP注文受付キューParamsより編集。<BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 414AC3CF0086
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
        //２）日付ロールをセットする
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
        
        //注文単位から発注日を取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tm.getOrderManager();
        IfoOrderUnit l_orderUnit = null;
        try
        {
	        l_orderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(
	            l_strInstitutionCode,
	            l_strBranchCode,
	            ProductTypeEnum.IFO,
	            l_hostFotypeOrderAcceptParams.getOrderRequestNumber());
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
        
        IfoOrderUnitRow l_ifoOrderUnitRow = 
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
        Date l_bizDate = 
            WEB3DateUtility.getDate(
            l_ifoOrderUnitRow.getBizDate() + WEB3GentradeTimeDef.MIN_RETURN, 
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
                
        //取引時間管理.set発注日計算用基準日時()のコール 
        WEB3GentradeTradingTimeManagement.setBaseTimestampForOrderBizDate(
            new Timestamp(l_bizDate.getTime()));
          
        // ３）　@口座をロックする
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
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 取引時間管理.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * 　@※取引時間管理.clear発注日計算用基準日時( )をコール。<BR>
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 414AC3CF00A5
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
        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
        log.exiting(STR_METHOD_NAME);
    

    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 取引時間管理.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * 　@※取引時間管理.clear発注日計算用基準日時( )をコール。<BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 414AC3CF00D4
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
        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
        log.exiting(STR_METHOD_NAME);
    }
}
@
