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
filename	WEB3EquityChangeConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文訂正確定インタセプタ(WEB3EquityChangeConfirmInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 張 生 (中訊) 新規作成
                   2004/05/27 孟 東 (中訊) mutateメソッド完成
                   2004/05/27 盧法@旭 (中訊) mutateメソッド完成 
                   2004/11/02 法@旭 修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文訂正確定インタセプタ）。<BR>
 * <BR>
 * 以下のケースで使用する、注文データ更新仕様カスタマイズ用のクラス。<BR>
 * <BR>
 * ・市場閉局後～注文繰越までの間の、市場送信済注文に対する訂正内容を、訂正が市場に<BR>
 * 受け入れられたものとして確定する。<BR>
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityChangeConfirmInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeConfirmInterceptor.class);

    /**
     * (株式注文訂正確定インタセプタ)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.equity.WEB3EquityOrderChangeConfirmInterceptor
     * @@roseuid 407A179503B1
     */
    public WEB3EquityChangeConfirmInterceptor()
    {

    }

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * １） 拡張項目セット<BR>
     * （注文状態が”発注済み（変更注文）：MODIFIED”に該当する場合のみ、<BR>
     * 処理を実施する。<BR>
     * 　@以外は、引数をそのまま返却し処理を終了する）<BR>
     * 　@注文単位Paramsの値を「発注済（変更注文）」の状態に設定し返却する。<BR>
     * <BR>
     * 更新内容は、「注文訂正_株式注文単位テーブル.xls」の<BR>
     * 「（株式注文訂正 DB更新内容[訂正確定]）株式注文単位テーブル」シート参照。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * 
     * @@param l_context - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。
     * @@return EqtypeOrderUnitParams
     * @@roseuid 406D06D803C6
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
        EqtypeOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (OrderStatusEnum.MODIFIED.equals(l_orderUnitParams.getOrderStatus()))
        {
            double l_dblConfirmedOrderPrice = 0.0;
            double l_dblConfirmedEstimatedPrice = 0.0;
            EqTypeExecutionConditionType l_confirmedExecConditionType = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            EqTypeOrderManager l_orderMgr = (EqTypeOrderManager) l_tradingMod.getOrderManager();               
            OrderUnit l_orderUnit = null;
            try 
            {
                l_orderUnit =
                    l_orderMgr.getOrderUnit(l_orderUnitParams.getOrderUnitId());
            }
            catch (NotFoundException l_nfe) 
            {
                log.debug(l_nfe.getMessage());
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            log.debug("order unit id:"+ l_orderUnit.getOrderUnitId());
            //set order status
            l_orderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            //注文訂正・取消区分をセット
            if (l_orderUnit.isUnexecuted())
            {
                //全部訂正完了
                log.debug("isUnexecuted true: executed_quantityがNullの場合");
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGED);
            }
            else
            {
                //一部訂正完了
                log.debug("false: それ以外の場合");
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
            }
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            //市場から確認済みの注文単価
            l_dblConfirmedOrderPrice = l_orderUnitRow.getPrice();
            if (l_orderUnitRow.getConfirmedOrderPriceIsNull())
            {
                l_orderUnitParams.setConfirmedOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedOrderPrice(l_dblConfirmedOrderPrice);
            }
            //市場から確認済みの概算受渡代金
            l_dblConfirmedEstimatedPrice =
                l_orderUnitRow.getEstimatedPrice();
            if (l_orderUnitRow.getEstimatedPriceIsNull())
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(null);   
            }
            else
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(
                    l_dblConfirmedEstimatedPrice);
            }
            //市場から確認済みの執行条件
            l_confirmedExecConditionType =
                l_orderUnitRow.getExecutionConditionType();
            l_orderUnitParams.setConfirmedExecConditionType(
                l_confirmedExecConditionType);
            //注文エラー理由コードをセット
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
