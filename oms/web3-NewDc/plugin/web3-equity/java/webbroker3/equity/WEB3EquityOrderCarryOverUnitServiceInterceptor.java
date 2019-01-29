head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文繰越一件サービスインタセプタ(WEB3EquityOrderCarryOverPartServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/04 鄒政 (中訊) 新規作成
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （注文繰越一件サービスインタセプタ）。
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverUnitServiceInterceptor
    implements Interceptor
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderCarryOverUnitServiceInterceptor.class);

    /**
     * @@roseuid 40B2A39800AC
     */
    public WEB3EquityOrderCarryOverUnitServiceInterceptor()
    {

    }

    /**
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルより、サービスメソッド開始時にコールされる）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]を注文単位オブジェクトにキャストする。<BR>
     * 　@－注文単位オブジェクトの内容より取引カレンダコンテキストのプロパティを<BR>
     * 　@　@　@セットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 注文単位.部店IDに該当する<BR>
     * 　@　@　@部店オブジェクト.証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード = 注文単位.部店IDに該当する<BR>
     * 　@　@　@部店オブジェクト.部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = 注文単位.市場IDに該当する<BR>
     * 　@　@　@市場オブジェクト.市場コード<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = 注文単位.注文カテゴリより判定して編集(*1)<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = "照会"<BR>
     * 　@　@※注文繰越は、バッチ中／システム緊急停止中の場合のみ実行不可のため、<BR>
     * 　@　@※注文受付トランザクションには"照会"を設定する。<BR>
     * <BR>
     * 　@(*1)注文受付商品<BR>
     * 　@　@　@注文カテゴリ == "現物注文"の場合は、"株式"。<BR>
     * 　@　@　@上記以外の場合は、"信用取引"。<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object<BR>
     * @@roseuid 413832A60282
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);

        //サービスの引数[0]を注文単位オブジェクトにキャストする
        if (!(l_serviceParams[0] instanceof OrderUnit))
        {
            log.debug("l_serviceParams[0] is not instanceof OrderUnit: ENTER");
            log.error(
                "パラメータタイプ不正。",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    STR_METHOD_NAME));
            log.debug("l_serviceParams[0] is not instanceof OrderUnit: END");
            return null;
        }
        OrderUnit l_orderUnit = (OrderUnit)l_serviceParams[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Branch l_branch = null;
        Market l_market = null;

        try
        {
            //get部店オブジェクト
            l_branch =
                l_finApp.getAccountManager().getBranch(
                    l_orderUnit.getBranchId());
            //get市場オブジェクト
            l_market =
                l_finApp.getFinObjectManager().getMarket(
                    l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException nfe)
        {
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            log.error(STR_METHOD_NAME, l_runtimeException);
            throw l_runtimeException;
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        //取引カレンダコンテキスト.証券会社コード = 注文単位.部店ID の
        //部店オブジェクトの同項目
        l_context.setInstitutionCode(
            l_branch.getInstitution().getInstitutionCode());

        //取引カレンダコンテキスト.部店コード = 注文単位.部店ID の部店
        // オブジェクトの同項目
        l_context.setBranchCode(l_branch.getBranchCode());

        //取引カレンダコンテキスト.市場コード = 注文単位.市場ID の市場
        //オブジェクトの同項目
        l_context.setMarketCode(l_market.getMarketCode());

        //      取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

        //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        //取引カレンダコンテキスト.注文受付商品 = 注文単位.注文カテゴリより判定して編集
        if (OrderCategEnum.ASSET.equals(l_orderUnitRow.getOrderCateg()))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        else
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        }

        //取引カレンダコンテキスト.注文受付トランザクション = "照会"
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

        //取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //受付日時、日付ロールをセットする
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
     * @@param l_returnValue - onCallリターン値<BR>
     * @@param l_context - サービスメソッドリターン値<BR>
     * @@roseuid 413832A602A0
     */
    public void onReturn(Object l_returnValue, Object l_context)
    {
        final String STR_METHOD_NAME = "onReturn , Object[])";
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
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 413832A602B5
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable , Object[])";
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
