head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文先物OP発注一件サービスインタセプタ(WEB3ToSuccIfoOrderUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/05/06 安陽(中訊) 新規作成モデルNo.311,341
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (連続注文先物OP発注一件サービスインタセプタ)<BR>
 * 連続注文先物OP発注一件サービスインタセプタ。<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3ToSuccIfoOrderUnitServiceInterceptor implements Interceptor
{
    /**
     * ログオブジェクト
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToSuccIfoOrderUnitServiceInterceptor.class);

    /**
     * @@roseuid 481EA5360027
     */
    public WEB3ToSuccIfoOrderUnitServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]を先物OP予約注文単位オブジェクトにキャストする。 <BR>
     * 　@－先物OP予約注文単位オブジェクトより、<BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード =<BR>
     * 　@　@　@先物OP予約注文単位.部店IDに該当する 部店オブジェクト.証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード =<BR>
     * 　@　@　@先物OP予約注文単位.部店IDに該当する 部店オブジェクト.部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = "株価指数先物OP" <BR>
     * 　@取引カレンダコンテキスト.銘柄コード =<BR>
     * 　@　@先物OP予約注文単位.銘柄IDに該当する先物OP銘柄オブジェクト.原資産銘柄コード <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 =<BR>
     * 　@　@先物OP予約注文単位.先物／オプション区分より判定して編集(*1) <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション =<BR>
     * 　@　@先物OP予約注文単位.注文種別より判定して編集(*2) <BR>
     * <BR>
     * 　@(*1)注文受付商品 <BR>
     * 　@　@　@先物／オプション区分 == "先物"の場合は、"先物"。 <BR>
     * 　@　@　@上記以外の場合は、"オプション"。  <BR>
     * <BR>
     * 　@(*2)注文受付トランザクション <BR>
     * 　@　@　@注文種別 ==
     * （"先物新規買建注文"or"OP新規買建注文"）の場合は、"買付（新規建買）"。 <BR>
     * 　@　@　@注文種別 ==
     * （"先物新規売建注文"or"OP新規売建注文"）の場合は、"売付（新規建売）"。 <BR>
     * 　@　@　@上記以外の場合は、"返済"。  <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストを
     * セットする。<BR>
     * 　@　@設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）　@予約注文に特有な発注審査を行うために、<BR>
     * 　@　@　@予約注文訂正フラグをセットする。 <BR>
     * 　@　@　@※既に注文は在るが、新規注文として発注審査を行うため。<BR>
     * 　@ <BR>
     * 　@　@"予約注文訂正フラグ"をLocalThreadにセットする。 <BR>
     * 　@　@－ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     * 　@　@　@　@WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER, <BR>
     * 　@　@　@　@BooleanEnum.True ) <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * @@return Object
     * @@roseuid 47DF4EEC0027
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams[0] == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）取引カレンダコンテキストに内容をセットする。
        //－サービスの引数[0]を先物OP予約注文単位オブジェクトにキャストする。
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = null;
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit = null;
        if (l_serviceParams[0] instanceof WEB3ToSuccIfoOrderUnitImpl)
        {
            l_orderUnit =
                (WEB3ToSuccIfoOrderUnitImpl)l_serviceParams[0];
            l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //部店を取得
            Branch l_branch = l_finApp.getAccountManager().getBranch(l_rsvIfoOrderUnitRow.getBranchId());

            WEB3IfoProductManagerImpl l_productMgr =
                (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

            //取引カレンダコンテキスト.証券会社コード =
            //  先物OP予約注文単位.部店IDに該当する 部店オブジェクト.証券会社コード
            l_context.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());

            //取引カレンダコンテキスト.部店コード =
            //  先物OP予約注文単位.部店IDに該当する 部店オブジェクト.部店コード
            l_context.setBranchCode(l_branch.getBranchCode());

            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //取引カレンダコンテキスト.受付時間区分 = "株価指数先物OP"
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

            //取引カレンダコンテキスト.銘柄コード =
            //  先物OP予約注文単位.銘柄IDに該当する先物OP銘柄オブジェクト.原資産銘柄コード
            WEB3IfoProductImpl l_ifoProductImpl =
                (WEB3IfoProductImpl)l_productMgr.getProduct(l_rsvIfoOrderUnitRow.getProductId());
            l_context.setProductCode(l_ifoProductImpl.getUnderlyingProductCode());

            //取引カレンダコンテキスト.注文受付商品 =
            //  先物OP予約注文単位.先物／オプション区分より判定して編集(*1)
            //　@(*1)注文受付商品
            //　@　@　@先物／オプション区分 == "先物"の場合は、"先物"。
            //　@　@　@上記以外の場合は、"オプション"。
            String l_strOrderAcceptProduct = null;
            if (WEB3FuturesOptionDivDef.FUTURES.equals(l_rsvIfoOrderUnitRow.getFutureOptionDiv()))
            {
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.FUTURE;
            }
            else
            {
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.OPTION;
            }

            l_context.setOrderAcceptProduct(l_strOrderAcceptProduct);

            //取引カレンダコンテキスト.注文受付トランザクション =
            //  先物OP予約注文単位.注文種別より判定して編集(*2)
            //　@(*2)注文受付トランザクション
            //　@　@　@注文種別 ==
            //   （"先物新規買建注文"or"OP新規買建注文"）の場合は、"買付（新規建買）"。
            //    　@注文種別 ==
            //   （"先物新規売建注文"or"OP新規売建注文"）の場合は、"売付（新規建売）"。
            //    　@上記以外の場合は、"返済"。
            String l_strOrderAcceptTransaction = null;
            if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_rsvIfoOrderUnitRow.getOrderType())
                || OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_rsvIfoOrderUnitRow.getOrderType()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_rsvIfoOrderUnitRow.getOrderType())
                || OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_rsvIfoOrderUnitRow.getOrderType()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }
            else
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.CLOSE_MARGIN;
            }

            l_context.setOrderAcceptTransaction(l_strOrderAcceptTransaction);

            //ThreadLocalSystemAttributesRegistry.setAttribute()にて
            //取引時間コンテキストをセットする
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

            //２）受付日時、日付ロールをセットする
            //取引時間管理.setTimestamp()をコールする
            WEB3GentradeTradingTimeManagement.setTimestamp();

            //３）　@予約注文に特有な発注審査を行うために、
            //　@　@　@予約注文訂正フラグをセットする。
            //　@　@　@※既に注文は在るが、新規注文として発注審査を行うため。
            //
            //　@　@"予約注文訂正フラグ"をLocalThreadにセットする。
            //　@　@－ThreadLocalSystemAttributesRegistry.setAttribute(
            //　@　@　@　@WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER,
            //　@　@　@　@BooleanEnum.True )
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER,
                BooleanEnum.TRUE);
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
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
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
     * WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * @@roseuid 47DF4EEC002A
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME =
            "onReturn(Object, Object)";
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
        //WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER, null);

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
     * WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * @@roseuid 47DF4EEC002D
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object, Throwable)";
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
        //WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER, null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
