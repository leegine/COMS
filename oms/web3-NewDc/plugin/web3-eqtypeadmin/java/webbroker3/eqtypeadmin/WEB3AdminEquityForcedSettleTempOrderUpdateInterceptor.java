head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済仮注文更新インタセプタ(WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 何文敏(中訊) 新規作成 仕様変更モデルNo.131、モデルNo.147、モデルNo.154
*/
package webbroker3.eqtypeadmin;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.util.WEB3LogUtility;


/**
 * (強制決済仮注文更新インタセプタ)<BR>
 * 強制決済仮注文登録時の、DB更新仕様カスタマイズ用のクラス。<BR>
 * （EqTypeOrderManagerPersistenceEventInterceptorの実装）<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor extends
    WEB3MarginCloseMarginUpdateInterceptor
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor.class);

    /**
     * (強制決済理由区分)<BR>
     * 強制決済理由区分<BR>
     */
    private String forcedSettleReasonType;

    /**
     * (承認状態区分)<BR>
     * 承認状態区分<BR>
     */
    private String approveStatusType;

    /**
     * (保証金維持率)<BR>
     * 保証金維持率<BR>
     */
    private Double marginMaintenanceRate;

    /**
     * (追証発生日)<BR>
     * 追証発生日<BR>
     */
    private Date additionalMarginDate;

    /**
     * (追証経過日数)<BR>
     * 追証経過日数<BR>
     */
    private Integer additionalMarginAccruedDays;

    /**
     * (強制決済仮注文更新インタセプタ)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * １）　@継承した項目の値セット<BR>
     * 　@スーパークラスのコンストラクタをcallする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@信用返済注文内容：　@引数.信用返済注文内容<BR>
     * 　@　@　@手数料：　@引数.手数料<BR>
     * 　@　@　@概算決済損益代金計算結果：　@引数.概算決済損益代金計算結果<BR>
     * 　@　@　@弁済区分：　@引数.弁済区分<BR>
     * 　@　@　@弁済期限値：　@引数.弁済期限値<BR>
     * 　@　@　@初回注文の注文チャネル：　@引数.初回注文の注文チャネル<BR>
     * 　@　@　@注文経路区分：　@引数.注文経路区分<BR>
     * <BR>
     * ２）　@拡張項目の値セット<BR>
     * 　@引数に指定されたその他のオブジェクト／値を、同名のプロパティに設定する。<BR>
     * <BR>
     * @@param l_eqTypeSettleContractOrderSpec - (信用返済注文内容)<BR>
     * 信用返済注文内容<BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料<BR>
     * @@param l_equityRealizedProfitAndLossPrice - (概算決済損益代金計算結果)<BR>
     * 概算決済損益代金計算結果<BR>
     * @@param l_strRepaymentDiv - (弁済区分)<BR>
     * 弁済区分<BR>
     * @@param l_dbRepaymentNum - (弁済期限値)<BR>
     * 弁済期限値<BR>
     * @@param l_strOrderChanel - (初回注文の注文チャネル)<BR>
     * 初回注文の注文チャネル<BR>
     * @@param l_strOrderRootDiv - (注文経路区分)<BR>
     * 注文経路区分<BR>
     * @@param l_strForcedSettleReasonDiv - (強制決済理由区分)<BR>
     * 強制決済理由区分<BR>
     * @@param l_strComondebiStatusDiv - (承認状態区分)<BR>
     * 承認状態区分<BR>
     * @@param l_dbMarginMaintenanceRate - (保証金維持率)<BR>
     * 保証金維持率<BR>
     * @@param l_datAdditionalBizDate - (追証発生日)<BR>
     * 追証発生日<BR>
     * @@param l_dbAdditionalElapsedDays - (追証経過日数)<BR>
     * 追証経過日数<BR>
     * @@roseuid 460B99A30399
     */
    public WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor(
        WEB3MarginSettleContractOrderSpec l_eqTypeSettleContractOrderSpec,
        WEB3GentradeCommission l_commission,
        WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice,
        String l_strRepaymentDiv,
        double l_dbRepaymentNum,
        String l_strOrderChanel,
        String l_strOrderRootDiv,
        String l_strForcedSettleReasonDiv,
        String l_strComondebiStatusDiv,
        Double l_dbMarginMaintenanceRate,
        Date l_datAdditionalBizDate,
        Integer l_dbAdditionalElapsedDays)
    {
        // １）　@継承した項目の値セット<BR>
        // スーパークラスのコンストラクタをcallする。
        super(l_eqTypeSettleContractOrderSpec,
                l_commission,
                l_equityRealizedProfitAndLossPrice,
                l_strRepaymentDiv,
                l_dbRepaymentNum,
                l_strOrderChanel,
                l_strOrderRootDiv);

        // 拡張項目の値セット
        // 引数に指定されたその他のオブジェクト／値を、同名のプロパティに設定する。
        this.additionalMarginAccruedDays = l_dbAdditionalElapsedDays;
        this.additionalMarginDate = l_datAdditionalBizDate;
        this.approveStatusType = l_strComondebiStatusDiv;
        this.forcedSettleReasonType = l_strForcedSettleReasonDiv;
        this.marginMaintenanceRate = l_dbMarginMaintenanceRate;
    }

    /**
     * (更新値設定)<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * ※強制決済仮注文の更新を行う。<BR>
     * <BR>
     * １）　@強制決済関連項目以外の値セット<BR>
     * 　@super.更新値設定をcallする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@更新タイプ：　@引数.更新タイプ<BR>
     * 　@　@　@処理：　@引数.処理<BR>
     * 　@　@　@注文単位Params：　@引数.注文単位Params<BR>
     * <BR>
     * ２）　@強制決済関連項目の値セット<BR>
     * 　@１）の戻り値に、当クラスが保持するプロパティ値をセットし、返却する。<BR>
     * <BR>
     * 　@更新内容はDB設定論理「仮注文登録_株式注文単位テーブル.xls」参照。<BR>
     * <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * EqTypeOrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_context - (処理)<BR>
     * （EqTypeOrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_eqtypeOrderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 460B8DC200C7
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext,EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        // １）　@強制決済関連項目以外の値セット
        // super.更新値設定をcallする。
        super.mutate(l_updateType, l_context, l_eqtypeOrderUnitParams);

        // ２）　@強制決済関連項目の値セット
        if (l_eqtypeOrderUnitParams == null)
        {
            log.error("パラメータ値不正。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 承認者コード
        l_eqtypeOrderUnitParams.setApproverCode(null);

        // 承認／非承認日時
        l_eqtypeOrderUnitParams.setApproveTimestamp(null);

        // 強制決済理由区分
        // 強制決済仮注文更新インタセプタの同名項目値
        l_eqtypeOrderUnitParams.setForcedSettleReasonType(this.forcedSettleReasonType);

        // 承認状態区分
        // 強制決済仮注文更新インタセプタの同名項目値
        l_eqtypeOrderUnitParams.setApproveStatusType(this.approveStatusType);

        // 保証金維持率
        // 強制決済仮注文更新インタセプタの同名項目値
        l_eqtypeOrderUnitParams.setMarginMaintenanceRate(this.marginMaintenanceRate);

        // 追証発生日
        // 強制決済仮注文更新インタセプタの同名項目値
        l_eqtypeOrderUnitParams.setAdditionalMarginDate(this.additionalMarginDate);

        // 追証経過日数
        // 強制決済仮注文更新インタセプタの同名項目値
        if (additionalMarginAccruedDays != null)
        {
            l_eqtypeOrderUnitParams.setAdditionalMarginAccruedDays(
                this.additionalMarginAccruedDays.longValue());
        }

        // 強制失効区分
        // 0：オープン
        l_eqtypeOrderUnitParams.setForcedExpireType(WEB3ForcedExpireType.OPENING);

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
   }
}
@
