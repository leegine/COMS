head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券取消更新インタセプタ(WEB3BondCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/11 唐性峰 (中訊) 新規作成
                   2006/10/08 趙林鵬(中訊) ＤＢ更新仕様No.013
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;


/**
 * (債券取消更新インタセプタ)
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondCancelUpdateInterceptor implements BondOrderManagerPersistenceEventInterceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondCancelUpdateInterceptor.class);

    /**
     * (代理入力者)<BR>
     * 代理入力者<BR>
     * （※代理入力の場合のみ設定される）
     */
    private WEB3GentradeTrader trader;

     /**
     * (債券取消更新インタセプタ)<BR>
     * コンストラクタ
     * @@roseuid 44DFD390032C
     */
    public WEB3BondCancelUpdateInterceptor()
    {

    }

    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * 注文単位テーブル更新 <BR>
     * 　@注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * 項目設定内容は、DB更新仕様 <BR>
     * 「取消_債券注文単位テーブルDB更新仕様.xls」 <BR>
     * <BR>
     * 参照。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        //注文約定区分    order_exec_status
        //2：取消済
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.CANCELED);

        //注文経路区分     order_root_div
        //セッションから取得した注文経路区分
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        l_params.setOrderRootDiv(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //host送信区分    host_send_div
        //2:送信不要
        l_params.setHostSendDiv(WEB3HostSendDivDef.NOT_SEND);

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }

    /**
     * (（注文履歴）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * １）　@注文単位オブジェクト取得 <BR>
     * <BR>
     * 引数の注文履歴Params.注文ID、注文単位ＩＤに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@拡張項目セット <BR>
     * 　@パラメータ.注文履歴Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 　@項目設定内容は、 <BR>
     * 　@「取消_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderActionParams
     */
    public BondOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderActionParams l_params)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderActionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        OrderManager l_orderManager = l_tradingModule.getOrderManager();

        BondOrderUnitRow l_orderUnitRow = null;
        try
        {
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_params.getOrderUnitId());
            l_orderUnitRow = (BondOrderUnitRow) l_orderUnit.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //取引者ＩＤ    trader_id
        //債券注文単位テーブル.取引者ID
        if (l_orderUnitRow.getTraderIdIsNull())
        {
            l_params.setTraderId(null);
        }
        else
        {
            l_params.setTraderId(l_orderUnitRow.getTraderId());
        }

        //取引    deal_type
        //債券注文単位テーブル.取引
        l_params.setDealType(l_orderUnitRow.getDealType());

        //注文単価    price
        //債券注文単位テーブル.注文単価
        if (l_orderUnitRow.getPriceIsNull())
        {
            l_params.setPrice(null);
        }
        else
        {
            l_params.setPrice(l_orderUnitRow.getPrice());
        }

        //指値    limit_price
        //債券注文単位テーブル.指値
        if (l_orderUnitRow.getLimitPriceIsNull())
        {
            l_params.setLimitPrice(null);
        }
        else
        {
            l_params.setLimitPrice(l_orderUnitRow.getLimitPrice());
        }

        //注文約定区分  order_exec_status
        //債券注文単位テーブル.注文約定区分
        l_params.setOrderExecStatus(l_orderUnitRow.getOrderExecStatus());

        //約定日        exec_date
        //債券注文単位テーブル.約定日
        l_params.setExecDate(l_orderUnitRow.getExecDate());

        //現地約定日        foreign_exec_date
        //債券注文単位テーブル.現地約定日
        l_params.setForeignExecDate(l_orderUnitRow.getForeignExecDate());

        //受渡日        delivery_date
        //債券注文単位テーブル.受渡日
        l_params.setDeliveryDate(l_orderUnitRow.getDeliveryDate());

        //現地受渡日        foreign_delivery_date
        //債券注文単位テーブル.現地受渡日
        l_params.setForeignDeliveryDate(l_orderUnitRow.getForeignDeliveryDate());

        //入金日  payment_date
        //債券注文単位テーブル.入金日
        l_params.setPaymentDate(l_orderUnitRow.getPaymentDate());

        //基準為替レート        base_fx_rate
        //債券注文単位テーブル.基準為替レート
        if (l_orderUnitRow.getBaseFxRateIsNull())
        {
            l_params.setBaseFxRate(null);
        }
        else
        {
            l_params.setBaseFxRate(l_orderUnitRow.getBaseFxRate());
        }

        //約定為替レート        exec_fx_rate
        //債券注文単位テーブル.約定為替レート
        if (l_orderUnitRow.getExecFxRateIsNull())
        {
            l_params.setExecFxRate(null);
        }
        else
        {
            l_params.setExecFxRate(l_orderUnitRow.getExecFxRate());
        }

        //売買代金（円貨）        trading_price
        //債券注文単位テーブル.売買代金（円貨）
        if (l_orderUnitRow.getTradingPriceIsNull())
        {
            l_params.setTradingPrice(null);
        }
        else
        {
            l_params.setTradingPrice(l_orderUnitRow.getTradingPrice());
        }

        //売買代金（外貨）        foreign_trading_price
        //債券注文単位テーブル.売買代金（外貨）
        if (l_orderUnitRow.getForeignTradingPriceIsNull())
        {
            l_params.setForeignTradingPrice(null);
        }
        else
        {
            l_params.setForeignTradingPrice(l_orderUnitRow.getForeignTradingPrice());
        }

        //経過利子（円貨）        accrued_interest
        //債券注文単位テーブル.経過利子（円貨）
        if (l_orderUnitRow.getAccruedInterestIsNull())
        {
            l_params.setAccruedInterest(null);
        }
        else
        {
            l_params.setAccruedInterest(l_orderUnitRow.getAccruedInterest());
        }

        //経過利子（外貨）        foreign_accrued_interest
        //債券注文単位テーブル.経過利子（外貨）
        if (l_orderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_params.setForeignAccruedInterest(null);
        }
        else
        {
            l_params.setForeignAccruedInterest(l_orderUnitRow.getForeignAccruedInterest());
        }

        //受渡代金（円貨）        estimated_price
        //債券注文単位テーブル.受渡代金（円貨）
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_params.setEstimatedPrice(null);
        }
        else
        {
            l_params.setEstimatedPrice(l_orderUnitRow.getEstimatedPrice());
        }

        //受渡代金（外貨）        foreign_estimated_price
        //債券注文単位テーブル.受渡代金（外貨）
        if (l_orderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_params.setForeignEstimatedPrice(null);
        }
        else
        {
            l_params.setForeignEstimatedPrice(l_orderUnitRow.getForeignEstimatedPrice());
        }

        //中途換金調整額   adjustment_before_maturity
        //債券注文単位テーブル.中途換金調整額
        if (l_orderUnitRow.getAdjustmentBeforeMaturityIsNull())
        {
            l_params.setAdjustmentBeforeMaturity(null);
        }
        else
        {
            l_params.setAdjustmentBeforeMaturity(
                l_orderUnitRow.getAdjustmentBeforeMaturity());
        }

        //経過日数        elapsed_days
        //債券注文単位テーブル.経過日数
        if (l_orderUnitRow.getElapsedDaysIsNull())
        {
            l_params.setElapsedDays(null);
        }
        else
        {
            l_params.setElapsedDays(l_orderUnitRow.getElapsedDays());
        }

        //基準日数        calc_base_days
        //債券注文単位テーブル.基準日数
        if (l_orderUnitRow.getCalcBaseDaysIsNull())
        {
            l_params.setCalcBaseDays(null);
        }
        else
        {
            l_params.setCalcBaseDays(l_orderUnitRow.getCalcBaseDays());
        }

        //カストディアンコード        custodian_code
        //債券注文単位テーブル.カストディアンコード
        l_params.setCustodianCode(l_orderUnitRow.getCustodianCode());

        //注文経路区分        order_root_div
        //債券注文単位テーブル.注文経路区分
        l_params.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());

        //管理者コード        administrator_code
        //債券注文単位テーブル.管理者コード
        l_params.setAdministratorCode(l_orderUnitRow.getAdministratorCode());

        //注文エラー理由コード        error_reason_code
        //債券注文単位テーブル.注文エラー理由コード
        l_params.setErrorReasonCode(l_orderUnitRow.getErrorReasonCode());

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }

    /**
     * (get代理入力者)<BR>
     * 代理入力者を取得する。
     * @@return WEB3GentradeTrader
     * @@roseuid 44E441C60077
     */
    public WEB3GentradeTrader getTrader()
    {
        return trader;
    }

    /**
     * (set代理入力者)<BR>
     * 代理入力者をセットする。
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者
     * @@roseuid 44E442020362
     */
    public void setTrader(WEB3GentradeTrader l_trader)
    {
        this.trader = l_trader;
    }

    public BondOrderExecutionParams mutate(OrderManagerPersistenceType arg0, OrderManagerPersistenceContext arg1, BondOrderExecutionParams arg2)
    {
        return null;
    }

    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1)
    {
        return null;
    }
}
@
