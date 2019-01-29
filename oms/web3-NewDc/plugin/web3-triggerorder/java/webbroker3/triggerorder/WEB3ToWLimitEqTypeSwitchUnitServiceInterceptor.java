head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文株式切替一件サービスインタセプタ(WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/17 齊珂(中訊) 新規作成 （モデル）No.176
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (W指値注文株式切替一件サービスインタセプタ)<BR>
 *
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToEquityManualOrderMainServiceInterceptor.class);

    /**
     *取引カレンダが利用するコンテキストを生成する。<BR>
     *（xTradeカーネルよりサービス実行前に呼び出される）<BR>
     *<BR>
     *１）　@サービスの引数[0]を株式注文単位オブジェクトにキャストする。 <BR>
     *<BR>
     *２）　@注文単位.getDataSourceObject()をコールする。  <BR>
     *<BR>
     *３）　@拡張アカウントマネージャ.getBranch()をコールする。  <BR>
     *<BR>
     *　@　@　@[getBranch()に設定する引数]  <BR>
     *　@　@　@arg0：　@注文単位.getBranchId()の戻り値  <BR>
     *<BR>
     *４）　@getBranch()の戻り値.getInstitution()をコールする。  <BR>
     *<BR>
     *５）　@拡張金融オブジェクトマネージャ.getMarket()をコールする。  <BR>
     *<BR>
     *　@　@　@[getMarket()に設定する引数]  <BR>
     *　@　@　@注文単位Row.getMarketId()  <BR>
     *<BR>
     *６）　@取引カレンダコンテキストに内容をセットする。  <BR>
     *　@－注文単位の内容より取引時間コンテキストの  <BR>
     *　@　@　@プロパティをセットする。  <BR>
     *<BR>
     *　@取引カレンダコンテキスト.証券会社コード = getInstitution()の戻り値.getInstitutionCode()  <BR>
     *　@取引カレンダコンテキスト.部店コード = getBranch()の戻り値.getBranchCode() <BR>
     *　@取引カレンダコンテキスト.市場コード = getMarket()の戻り値.getMarketCode() <BR>
     *　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用” <BR>
     *　@取引カレンダコンテキスト.銘柄コード = ”DEFAULT” <BR>
     *　@取引カレンダコンテキスト.注文受付商品 = (*1)  <BR>
     *　@取引カレンダコンテキスト.注文受付トランザクション = ”訂正” <BR>
     *<BR>
     *　@(*1)注文受付商品  <BR>
     *　@　@　@・注文カテゴリ（注文単位.getOrderCateg()）が”現物注文”の場合、<BR>
     *　@　@　@　@　@　@”株式”をセットする。  <BR>
     *　@　@　@・注文カテゴリが上記以外の場合、  <BR>
     *　@　@　@　@　@　@”信用”をセットする。  <BR>
     *<BR>
     *　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて  <BR>
     *　@　@　@取引時間コンテキストをセットする。  <BR>
     *　@　@　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     *<BR>
     *７）　@スキップvalidate特殊執行条件取扱停止をセットする。 <BR>
     *　@ThreadLocalSystemAttributesRegistry.setAttribute()にて <BR>
     *　@スキップvalidate特殊執行条件取扱停止に"TRUE"をセットする。 <BR>
     *　@　@-ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     *　@　@　@　@WEB3LocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,<BR>
     *　@　@　@　@BooleanEnum.TRUE) <BR>
     *<BR>
     *８）　@受付日時、日付ロールをセットする。  <BR>
     *　@－取引時間管理.setTimestamp()をコールする。  <BR>
     *<BR>
     *９）　@口座をロックする。  <BR>
     *　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。  <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //取引カレンダが利用するコンテキストを生成する。
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        //１）　@サービスの引数[0]を株式注文単位オブジェクトにキャストする。
        EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_serviceParam[0];

        //２）　@注文単位.getDataSourceObject()をコールする。
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //３）　@拡張アカウントマネージャ.getBranch()をコールする。
        //　@　@　@[getBranch()に設定する引数]
        //　@　@　@arg0：　@注文単位.getBranchId()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManaer =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        try
        {
            Branch l_branch =
                l_gentradeAccountManaer.getBranch(l_eqTypeOrderUnit.getBranchId());

            //４）　@getBranch()の戻り値.getInstitution()をコールする。
            Institution l_institution = l_branch.getInstitution();

            //５）　@拡張金融オブジェクトマネージャ.getMarket()をコールする。
            //　@　@　@[getMarket()に設定する引数]
            //　@　@　@注文単位Row.getMarketId()
            WEB3GentradeFinObjectManager l_finObjManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            Market l_market = l_finObjManager.getMarket(l_eqtypeOrderUnitRow.getMarketId());

            //６）　@取引カレンダコンテキストに内容をセットする。
            //－注文単位の内容より取引時間コンテキストの
            //　@　@　@プロパティをセットする。
            //　@取引カレンダコンテキスト.証券会社コード = getInstitution()の戻り値.getInstitutionCode()
            l_context.setInstitutionCode(l_institution.getInstitutionCode());

            //　@取引カレンダコンテキスト.部店コード = getBranch()の戻り値.getBranchCode()
            l_context.setBranchCode(l_branch.getBranchCode());

            //　@取引カレンダコンテキスト.市場コード = getMarket()の戻り値.getMarketCode()
            l_context.setMarketCode(l_market.getMarketCode());

            //　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

            //　@取引カレンダコンテキスト.銘柄コード = ”DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //　@取引カレンダコンテキスト.注文受付商品 = (*1)
            // (*1)注文受付商品
            // 　@　@　@・注文カテゴリ（注文単位.getOrderCateg()）が”現物注文”の場合、
            //　@　@　@　@　@　@”株式”をセットする。
            //　@　@　@・注文カテゴリが上記以外の場合、
            //　@　@　@　@　@　@”信用”をセットする。
            if (OrderCategEnum.ASSET.equals(l_eqTypeOrderUnit.getOrderCateg()))
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            }
            else
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            }

            //　@取引カレンダコンテキスト.注文受付トランザクション = ”訂正”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);

            //　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて
            //　@　@　@取引時間コンテキストをセットする。
            //　@　@　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

            //７）　@スキップvalidate特殊執行条件取扱停止をセットする。
            //　@ThreadLocalSystemAttributesRegistry.setAttribute()にて
            //　@スキップvalidate特殊執行条件取扱停止に"TRUE"をセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP, BooleanEnum.TRUE);

            //８）　@受付日時、日付ロールをセットする。
            //　@－取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();

            //９）　@口座をロックする。
            //　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
            MainAccount l_mainAcc =
                l_gentradeAccountManaer.getMainAccount(l_eqTypeOrderUnit.getAccountId());
            l_gentradeAccountManaer.lockAccount(l_institution.getInstitutionCode(),
                l_branch.getBranchCode(),
                l_mainAcc.getAccountCode()
                );
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_context;
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
     * WEB3LocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
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
     * WEB3LocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
            null);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
