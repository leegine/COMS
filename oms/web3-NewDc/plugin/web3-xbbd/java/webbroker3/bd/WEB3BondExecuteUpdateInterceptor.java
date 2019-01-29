head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券約定更新インタセプタ(WEB3BondExecuteUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/28 柴雙紅 (中訊) 新規作成
*/

package webbroker3.bd;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券約定更新インタセプタ)<BR>
 * 債券約定更新インタセプタクラス<BR>
 *
 * @@author 柴雙紅(中訊)
 * @@version 1.0
 */
public class WEB3BondExecuteUpdateInterceptor extends WEB3AdminBondExecuteCommonInterceptor
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondExecuteUpdateInterceptor.class);

    /**
     * (債券約定更新インタセプタ)<BR>
     * コンストラクタ<BR>
     */
    public WEB3BondExecuteUpdateInterceptor()
    {

    }

    /**
     * (（注文単位）更新値設)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * １）拡張項目セット<BR>
     * 　@パラメータ.債券注文単位Paramsの拡張項目に値をセットし、返却する。<BR>
     * 　@項目設定内容は　@「債券自動約定_債券注文単位テーブルDB更新仕様.xls」参照。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
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
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = this.getBondExecuteDateInfo();
        //受渡日 = 債券約定日情報.get受渡日
        l_params.setDeliveryDate(l_bondExecuteDateInfo.getDeliveryDate());
        //現地受渡日 = 債券約定日情報.get現地受渡日
        if (l_bondExecuteDateInfo.getForeignDeliveryDate() != null)
        {
            l_params.setForeignDeliveryDate(l_bondExecuteDateInfo.getForeignDeliveryDate());
        }

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            this.getBondEstimatedPriceCalcResult();

        //合計約定金額 = （既存値）+ 計算サービス.calc売買代金（数量,単価,債券銘柄）
        //数量＝約定数量
        //単価＝債券受渡代金計算結果.get単価
        //債券銘柄 = インタセプタ.get債券銘柄

        WEB3BondBizLogicProvider l_provider = new WEB3BondBizLogicProvider();
        BigDecimal l_bdExecutedAmount = null;
        try
        {
            l_bdExecutedAmount = l_provider.calcTradingPrice(
                new BigDecimal(String.valueOf(l_params.getExecutedQuantity())),
                l_bondEstimatedPriceCalcResult.getPrice(),
                this.getBondProduct());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (l_bdExecutedAmount != null)
        {
            double l_dblExecutedAmount =
                l_params.getExecutedAmount() + l_bdExecutedAmount.doubleValue();
            l_params.setExecutedAmount(l_dblExecutedAmount);
        }
        //注文約定区分 = 1：約定済
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.EXECUTED);
        //注文単価 = 債券受渡代金計算結果.get単価
        //約定単価 = 債券受渡代金計算結果.get単価
        BigDecimal l_bdPrice = l_bondEstimatedPriceCalcResult.getPrice();
        if (l_bdPrice != null)
        {
            l_params.setPrice(l_bdPrice.doubleValue());
            l_params.setExecutedPrice(l_bdPrice.doubleValue());
        }
        else
        {
            l_params.setPrice(null);
            l_params.setExecutedPrice(null);
        }
        // 約定為替レート = 債券受渡代金計算結果.get為替レート
        if (l_bondEstimatedPriceCalcResult.getFxRate() != null)
        {
            l_params.setExecFxRate(l_bondEstimatedPriceCalcResult.getFxRate().doubleValue());
        }
        else
        {
            l_params.setExecFxRate(null);
        }
        //売買代金（円貨） = 債券受渡代金計算結果.get売買代金（円貨）
        if (l_bondEstimatedPriceCalcResult.getTradingPrice() != null)
        {
            l_params.setTradingPrice(l_bondEstimatedPriceCalcResult.getTradingPrice().doubleValue());
        }
        else
        {
            l_params.setTradingPrice(null);
        }
        //売買代金（外貨）= 債券受渡代金計算結果.get売買代金（外貨）
        if (l_bondEstimatedPriceCalcResult.getForeignTradePrice() != null)
        {
            l_params.setForeignTradingPrice(l_bondEstimatedPriceCalcResult.getForeignTradePrice().doubleValue());
        }
        else
        {
            l_params.setForeignTradingPrice(null);
        }
        //経過利子（円貨） =  債券受渡代金計算結果.get経過利子（円貨）
        if (l_bondEstimatedPriceCalcResult.getAccruedInterest() != null)
        {
            l_params.setAccruedInterest(l_bondEstimatedPriceCalcResult.getAccruedInterest().doubleValue());
        }
        else
        {
            l_params.setAccruedInterest(null);
        }
        //経過利子（外貨）= 債券受渡代金計算結果.get経過利子（外貨）
        if (l_bondEstimatedPriceCalcResult.getForeignAccruedInterest() != null)
        {
            l_params.setForeignAccruedInterest(l_bondEstimatedPriceCalcResult.getForeignAccruedInterest().doubleValue());
        }
        else
        {
            l_params.setForeignAccruedInterest(null);
        }
        //受渡代金（円貨）=  債券受渡代金計算結果.get受渡代金（円貨）
        if (l_bondEstimatedPriceCalcResult.getEstimatedPrice() != null)
        {
            l_params.setEstimatedPrice(l_bondEstimatedPriceCalcResult.getEstimatedPrice().doubleValue());
        }
        else
        {
            l_params.setEstimatedPrice(null);
        }
        //受渡代金（外貨） =  債券受渡代金計算結果.get受渡代金（外貨）
        if (l_bondEstimatedPriceCalcResult.getForeignEstimatedPrice() != null)
        {
            l_params.setForeignEstimatedPrice(l_bondEstimatedPriceCalcResult.getForeignEstimatedPrice().doubleValue());
        }
        else
        {
            l_params.setForeignEstimatedPrice(null);
        }
        //経過日数         =  債券受渡代金計算結果.get経過日数
        if (l_bondEstimatedPriceCalcResult.getElapsedDays() != null)
        {
            l_params.setElapsedDays(l_bondEstimatedPriceCalcResult.getElapsedDays());
        }
        else
        {
            l_params.setElapsedDays(null);
        }
        //基準日数        =   債券受渡代金計算結果.get基準日数
        if (l_bondEstimatedPriceCalcResult.getCalcBaseDays() != null)
        {
            l_params.setCalcBaseDays(l_bondEstimatedPriceCalcResult.getCalcBaseDays());
        }
        else
        {
            l_params.setCalcBaseDays(null);
        }
        //約定日          =  債券約定日情報.get約定日
        l_params.setExecDate(l_bondExecuteDateInfo.getExecuteDate());

        //現地約定日      =   債券約定日情報.get現地約定日
        l_params.setForeignExecDate(l_bondExecuteDateInfo.getForeignExecuteDate());

        //入金日    =  債券約定日情報.get入金日
        l_params.setPaymentDate(l_bondExecuteDateInfo.getPaymentDate());

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
