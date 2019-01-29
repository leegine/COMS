head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文株式切替更新インタセプタ(WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/20 徐宏偉 (中訊) 新規作成 （モデル）No.176 （DB更新仕様）No.025
*/
package webbroker3.triggerorder;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3MarginUpdateEventInterceptor;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * (W指値注文株式切替更新インタセプタ)
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor
    extends WEB3MarginUpdateEventInterceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor.class);

    /**
     * 概算代金計算結果
     */
    public WEB3EquityEstimatedPrice l_equityEstimatedPrice;

    /**
     * (W指値注文株式切替更新インタセプタ)<BR>
     * コンストラクタ  <BR>
     * <BR>
     * インスタンスを生成し、引数を自身のプロパティにセットする。<BR>
     * <BR>
     * @@param l_estimatedPrice - (概算代金計算結果)
     */
    public WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor(
        WEB3EquityEstimatedPrice l_estimatedPrice)
    {
        this.l_equityEstimatedPrice = l_estimatedPrice;
    }

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 引数の注文単位Paramsに拡張項目(*)を設定し返却する。<BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * １） 拡張項目セット  <BR>
     * <BR>
     * 　@　@更新内容は、DB更新仕様  <BR>
     * 　@　@　@「W指値注文切替(OK)_注文単位テーブル.xls」<BR>
     * 　@　@参照。 <BR>
     * <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * @@param l_context - (処理)<BR>
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * @@return EqtypeOrderUnitParams
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //執行条件   this.（W指値）執行条件
        l_orderUnitParams.setExecutionConditionType(
            l_orderUnitParams.getWLimitExecCondType());

        //注文単価   インタセプタ.概算代金計算結果.get計算単価()
        l_orderUnitParams.setPrice(
            this.l_equityEstimatedPrice.getCalcUnitPrice());

        //概算受渡代金  インタセプタ.概算代金計算結果.get概算受渡代金()
        l_orderUnitParams.setEstimatedPrice(
            this.l_equityEstimatedPrice.getEstimateDeliveryAmount());

        //注文訂正・取消区分  A：W指値注文切替中
        l_orderUnitParams.setModifyCancelType(
            WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING);

        //発注経路区分   株式発注サービス.get訂正取消時発注経路区分()の戻り値

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        OrderUnit l_orderUnit = l_orderManager.toOrderUnit(l_orderUnitParams);
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        String l_strSubmitOrderRouteDiv = null;
        String l_strChangeOrderRev = null;
        try
        {
            l_strSubmitOrderRouteDiv =
                l_frontOrderService.getChangeSubmitOrderRouteDiv(
                    (EqTypeOrderUnit)l_orderUnit);
            l_strChangeOrderRev =
                l_frontOrderService.getChangeOrderRev((EqTypeOrderUnit)l_orderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        l_orderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);

        // リクエストタイプ  1：時価サーバ
        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);

        //注文Rev.  株式発注サービス.get訂正時注文Rev()の戻り値
        l_orderUnitParams.setOrderRev(l_strChangeOrderRev);
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;

    }
}
@
