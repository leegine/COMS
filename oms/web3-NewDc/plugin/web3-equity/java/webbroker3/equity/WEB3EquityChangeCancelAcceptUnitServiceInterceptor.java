head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 訂正取消受付一件サービスインタセプタクラス(WEB3EquityChangeCancelAcceptUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/22 盧法@旭 (中訊) 新規作成
Revesion History : 2004/12/10 岡村和明(SRA) 残案件対応 Ｎｏ.２４９
Revesion History : 2004/12/21 岡村和明(SRA) JavaDoc修正
Revesion History : 2005/01/05 岡村和明(SRA) 口座ロック修正対応
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2007/01/31 柴双紅(中訊) モデル1119、1121
*/

package webbroker3.equity;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * （株式訂正取消受付一件サービスインタセプタ）。<BR>
 * <BR>
 * 訂正取消受付一件サービスインタセプタクラス
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3EquityChangeCancelAcceptUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityChangeCancelAcceptUnitServiceInterceptor.class);
        
    /**
     * @@roseuid 414567A90250
     */
    public WEB3EquityChangeCancelAcceptUnitServiceInterceptor() 
    {
     
    }
    
    /**
     * <p>（onCall）。</p>
     * <p>取引カレンダが利用するコンテキストを生成する。<br>
     * （xTradeカーネルより、サービスメソッド開始時にコールされる）<br>
     * <br>
     * １）　@取引カレンダコンテキストに内容をセットする。<br>
     * 　@－サービスの引数[0]を株式注文受付キューParamsオブジェクトにキャストする。<br>
     * 　@－株式注文受付キューParamsオブジェクトの内容より<br>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。<br>
     * 　@取引カレンダコンテキスト.証券会社コード = 株式注文受付キューParamsの同項目<br>
     * 　@取引カレンダコンテキスト.部店コード = 株式注文受付キューParamsの同項目<br>
     * 　@取引カレンダコンテキスト.市場コード = null<br>
     * 　@取引時間コンテキスト.受付時間区分 = ”株式・信用”<br>
     * 　@取引時間コンテキスト.銘柄コード = ”0：DEFAULT”<br>
     * 　@取引カレンダコンテキスト.注文受付商品 = null<br>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null<br>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<br>
     * 設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH<br>
     * <br>
     * ２）　@受付日時、日付ロールをセットする。<br>
     * 　@－取引時間管理.setTimestamp()をコールする。<br>
     * <br>
     * ３）　@当日場中の基準値取得のための属性をセットする。<br>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<br>
     * 　@「下り処理で必ず場中扱い」をセットする。<br>
     * 　@　@設定キー： 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<br>
     * 　@－拡張株式注文マネージャ.get注文単位()にて注文単位を取得する。<br>
     * 　@　@[引数]<br>
     * 　@　@　@証券会社コード：　@株式注文受付キューParamsの同項目<br>
     * 　@　@　@部店コード：　@株式注文受付キューParamsの同項目<br>
     * 　@　@　@商品タイプ：　@ProductTypeEnum."株式"（EQUITY）<br>
     * 　@　@　@識別コード：　@株式注文受付キューParamsの同項目<br>
     * 　@－取引時間管理.set発注日計算用基準日時( )にて<br>
     * 　@　@発注日計算の基準日時に、注文単位.発注日＋HHMMSSとして"000000"をセットする。<br>
     * <br>
     * ４）　@口座をロックする。<br>
     * <br>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<br>
     * 　@　@　@※引数は株式注文受付キューParamsより編集。</p>
     * @@param l_method サービスメソッドオブジェクト
     * @@param l_serviceParams サービスメソッド引数
     * @@return Object
     * @@roseuid 41083F100018
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        if(l_serviceParams[0] == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //サービスの引数[0]を株式注文受付キューParamsオブジェクトにキャストす
        HostEqtypeOrderAcceptParams l_accParams = (HostEqtypeOrderAcceptParams) l_serviceParams[0];

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
            
        String l_strInstitutionCode = null; //証券会社コード
        String l_strBranchCode  = null; //部店コード

        l_strInstitutionCode = l_accParams.getInstitutionCode();
        l_strBranchCode = l_accParams.getBranchCode();
                    
        //取引カレンダコンテキスト.証券会社コード
        l_context.setInstitutionCode(l_strInstitutionCode);
            
        //取引カレンダコンテキスト.部店コード 
        l_context.setBranchCode(l_strBranchCode);
        
        //取引カレンダコンテキスト.市場コード 
        l_context.setMarketCode(null);
        
        //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        // 取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //取引カレンダコンテキスト.注文受付商品 = ”株式”
        l_context.setOrderAcceptProduct(null);
        
        //取引カレンダコンテキスト.注文受付トランザクション = 
        l_context.setOrderAcceptTransaction(null);
            
        //取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);


        //--------------------
        //受付日時をセットする。
        //--------------------
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_wse)
        {
            throw new WEB3BaseRuntimeException(
                l_wse.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wse.getMessage(), l_wse);                  
        }

        //当日場中の基準値取得のための属性をセットする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        //拡張株式注文マネージャを生成
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_eqTypeOrderUnit = null;
        try
        {
            l_eqTypeOrderUnit =
                l_orderMgr.getOrderUnit(
                    l_accParams.getInstitutionCode(),
                    l_accParams.getBranchCode(),
                    ProductTypeEnum.EQUITY,
                    l_accParams.getOrderRequestNumber());
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            WEB3EquityBackServiceOnlineDef.ONLINE);
        Date l_bizDate =
            WEB3DateUtility.getDate(
            l_orderUnitRow.getBizDate() + WEB3GentradeTimeDef.MIN_RETURN,
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        WEB3GentradeTradingTimeManagement.setBaseTimestampForOrderBizDate(
            new Timestamp(l_bizDate.getTime()));

        //--------------------
        //口座をロックする
        //--------------------
        try
        {
            log.debug("拡張アカウントマネージャの口座をロックします。");
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            // 拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
            l_accountManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_accParams.getAccountCode());
        }
        catch (WEB3BaseException l_be)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_be.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_be.getMessage(), l_be);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_context;
    }   
    /**
     * <p>（onReturn）。</p>
     * <p>取引カレンダコンテキストクリア処理。<br>
     * 訂正取消受付サービス終了時にコールされる。<br>
     * <br>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<br>
     * <br>
     * 取引時間管理.TIMESTAMP_TAG <br>
     * 取引時間管理.OFFSET_TAG <br>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <br>
     * 取引時間管理.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<br>
     * 　@※取引時間管理.clear発注日計算用基準日時( )をコール。<br>
     * 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<br>
     * @@param l_returnValue onCallリターン値
     * @@param l_context サービスメソッドリターン値
     * @@roseuid 41083F10001B
     */
    public void onReturn(Object l_returnValue, Object l_context) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            null);

        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }
    
    /**
     * <p>（onThrowable）。</p>
     * <p>取引カレンダコンテキストクリア処理。<br>
     * サービスメソッドが例外をスローした場合にコールされる。<br>
     * <br>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<br>
     * <br>
     * 取引時間管理.TIMESTAMP_TAG <br>
     * 取引時間管理.OFFSET_TAG <br>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <br>
     * 取引時間管理.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<br>
     * 　@※取引時間管理.clear発注日計算用基準日時( )をコール。<br>
     * 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE <br>
     * <br>
     * @@param l_obj onCallリターン値
     * @@param l_throwable 例外オブジェクト
     * @@roseuid 41083F100028
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
                null);

        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }
}
@
