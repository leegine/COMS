head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式出来通知一件サービスインタセプタ(WEB3EquityOrderExecNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 鄭海良(中訊) 新規作成
Revesion History : 2004/12/02 中尾寿彦(SRA) 残案件対応による修正
Revesion History : 2005/01/05 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/02/20 大澤喜宗@ (SRA) 仕様変更管理台帳（モデル）№862の対応
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

/**
 * （現物株式出来通知一件サービスインタセプタ）。
 * @@version 1.0
 */
public class WEB3EquityOrderExecNotifyUnitServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityOrderExecNotifyUnitServiceInterceptor.class);

    /**
     * コンストラクタ<BR>
     */
    public WEB3EquityOrderExecNotifyUnitServiceInterceptor()
    {
    }

    /**
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルより、サービスメソッド開始時にコールされる）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]を注文単位オブジェクトにキャストする。<BR>
     * 　@－サービスの引数[1]を株式出来通知キューParamsオブジェクトにキャストする。<BR>
     * 　@－株式出来通知キューParamsオブジェクトの内容より取引時間コンテキストのプロパティを<BR>
     * 　@　@　@セットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 株式出来通知キューParamsの同項目<BR>
     * 　@取引カレンダコンテキスト.部店コード = 株式出来通知キューParamsの同項目<BR>
     * 　@取引カレンダコンテキスト.市場コード = 注文単位オブジェクト.市場IDに該当する<BR>
     * 　@　@　@市場オブジェクト.市場コード<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = null<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     * 設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）　@当日場中の基準値取得のための属性をセットする。 <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@「下り処理で必ず場中扱い」をセットする。 <BR>
     * 　@　@　@　@設定キー： 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * 　@－取引時間管理.set発注日計算用基準日時( )にて <BR>
     * 　@　@発注日計算の基準日時に、注文単位.発注日＋HHMMSSとして"000000"をセットする。<BR>
     * <BR>
     * ４）　@口座をロックする。<BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード(*1))をコールする。<BR>
     * 　@　@　@※引数は株式出来通知キューParamsより編集。<BR>
     * 　@　@　@※ただし、(*1)==（0 or null）の場合は、注文単位.口座IDに該当する顧客オブジェクト.口座コードを<BR>
     * 　@　@　@※編集する。<BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParams - サービスメソッド引数
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        // １）　@取引カレンダコンテキストに内容をセットする。
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_serviceParams[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        HostEquityOrderExecNotifyParams l_execNotifyParams =
            (HostEquityOrderExecNotifyParams)l_serviceParams[1];
        String l_strInstitutionCode = l_execNotifyParams.getInstitutionCode();
        String l_strBranchCode = l_execNotifyParams.getBranchCode();
        String l_strAccountCode = l_execNotifyParams.getAccountCode();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        String l_strMarketCode = null;
        long l_lngMarketId = l_orderUnitRow.getMarketId();
        try
        {
            WEB3GentradeFinObjectManager l_objectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarket(l_lngMarketId);
            l_strMarketCode = l_market.getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        // 取引カレンダコンテキスト.証券会社コード = 株式出来通知キューParamsの同項目
        l_context.setInstitutionCode(l_strInstitutionCode);
        // 取引カレンダコンテキスト.部店コード = 株式出来通知キューParamsの同項目
        l_context.setBranchCode(l_strBranchCode);
        // 取引カレンダコンテキスト.市場コード = 注文単位オブジェクト.市場IDに該当する市場オブジェクト.市場コード
        l_context.setMarketCode(l_strMarketCode);
        // 取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        // 取引カレンダコンテキスト.銘柄コード = 0 ： DEFAULT
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // 取引カレンダコンテキスト.注文受付商品 = null
        l_context.setOrderAcceptProduct(null);
        // 取引カレンダコンテキスト.注文受付トランザクション = null
        l_context.setOrderAcceptTransaction(null);

        // 取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        // ２）　@受付日時、日付ロールをセットする。
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            WEB3EquityBackServiceOnlineDef.ONLINE);
        Date l_bizDate = 
        	WEB3DateUtility.getDate(
        	l_orderUnitRow.getBizDate() + WEB3GentradeTimeDef.MIN_RETURN, 
			WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
		WEB3GentradeTradingTimeManagement.setBaseTimestampForOrderBizDate(
			new Timestamp(l_bizDate.getTime()));
        
        // ４）　@口座をロックする。
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        if (l_strAccountCode == null ||
            Integer.parseInt(l_strAccountCode.trim()) == 0)
        {
            try
            {
                MainAccount l_account =
                    l_accountManager.getMainAccount(l_orderUnit.getAccountId());
                l_strAccountCode = l_account.getAccountCode();
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
            }
        }
        try
        {
            l_accountManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);
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

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * （onReturn）<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * 注文繰越一件サービス終了時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 取引時間管理.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * 　@※取引時間管理.clear発注日計算用基準日時( )をコール。<BR>
     * 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * @@param l_returnValue - onCallリターン値
     * @@param l_context - サービスメソッドリターン値
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
     * （onThrowable）<BR>
     * <BR>
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 取引時間管理.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * 　@※取引時間管理.clear発注日計算用基準日時( )をコール。<BR>
     * 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * @@param l_returnValue - onCallリターン値
     * @@param l_context - サービスメソッドリターン値
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
