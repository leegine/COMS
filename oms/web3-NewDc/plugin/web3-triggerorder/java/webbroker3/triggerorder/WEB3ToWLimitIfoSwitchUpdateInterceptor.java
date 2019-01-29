head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitIfoSwitchUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文先物OP切替更新インタセプタ(WEB3ToWLimitIfoSwitchUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/23　@肖志偉(中訊) 新規作成
Revesion History : 2007/01/30  金傑 (中訊) トリガー注文ＤＢ更新仕様039
Revesion History : 2008/04/10  趙林鵬 (中訊) トリガー注文ＤＢ更新仕様045
*/
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoUpdateInterceptor;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (W指値注文先物OP切替更新インタセプタ)<BR>
 * W指値注文先物OP切替更新インタセプタ<BR>
 *
 * @@author 肖志偉
 * @@version 1.0
 */
public class WEB3ToWLimitIfoSwitchUpdateInterceptor extends WEB3IfoUpdateInterceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToWLimitIfoSwitchUpdateInterceptor.class);

    /**
     * (扱者)<BR>
     * 扱者オブジェクト<BR>
     */
    public WEB3GentradeTrader trader;

    /**
     * (概算受渡代金計算結果)<BR>
     * 概算受渡代金計算結果オブジェクト<BR>
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult;

    /**
     * (訂正内容)<BR>
     * 訂正内容オブジェクト<BR>
     * ※以下のいずれか。<BR>
     * 　@・新規建訂正内容<BR>
     * 　@・返済訂正内容<BR>
     */
    public Object changeSpec;

    /**
     * @@roseuid 44E90ED7004E
     */
    public WEB3ToWLimitIfoSwitchUpdateInterceptor()
    {

    }

    /**
     * (W指値注文先物OP切替更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数を自身のプロパティにセットする。<BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクト
     * @@param l_estimateDeliveryAmountCalcResult - (概算受渡代金計算結果)<BR>
     * 概算受渡代金計算結果オブジェクト
     * @@param l_changeSpec - (訂正内容)<BR>
     * 訂正内容オブジェクト<BR>
     * ※以下のいずれか。<BR>
     * 　@・新規建訂正内容<BR>
     * 　@・返済訂正内容<BR>
     * @@roseuid 44926F460153
     */
    public WEB3ToWLimitIfoSwitchUpdateInterceptor(
        WEB3GentradeTrader l_trader,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult,
        Object l_changeSpec)
    {
        this.trader = l_trader;
        this.estimateDeliveryAmounCalcResult
            = l_estimateDeliveryAmountCalcResult;
        this.changeSpec = l_changeSpec;
    }

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 引数の注文単位Paramsに拡張項目(*)を設定し返却する。<BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * １） 拡張項目セット<BR>
     * 　@更新内容は、DB更新仕様 <BR>
     * 　@「W指値注文切替(OK)_注文単位テーブル.xls」参照。<BR>
     * <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * @@param l_context - (コンテキスト)<BR>
     * @@param l_orderUnitParams - (注文単位行)<BR>
     * 注文単位行オブジェクト<BR>
     * @@return IfoOrderUnitParams
     * @@roseuid 44926F460173
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        IfoOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME =
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("注文単位Params == null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //取引者ID
        if (this.trader != null)
        {
            l_orderUnitParams.setTraderId(this.trader.getTraderId());
        }

        if (this.changeSpec instanceof WEB3IfoOpenContractChangeSpec)
        {
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
                (WEB3IfoOpenContractChangeSpec)this.changeSpec;

            //執行条件
            l_orderUnitParams.setExecutionConditionType(
                l_ifoOpenContractChangeSpec.getChangeExecCondType());

        }
        else if (this.changeSpec instanceof WEB3IfoChangeSettleContractOrderSpec)
        {
            WEB3IfoChangeSettleContractOrderSpec l_ifoChangeSettleContractOrderSpec =
                (WEB3IfoChangeSettleContractOrderSpec)this.changeSpec;

            //執行条件
            l_orderUnitParams.setExecutionConditionType(
                l_ifoChangeSettleContractOrderSpec.getChangeExecCondType());
        }

        //注文状態
        l_orderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);

        //注文単価
        l_orderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());

        //概算受渡代金
        l_orderUnitParams.setEstimatedPrice(
            this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());

        //注文訂正・取消区分
        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING);

        //注文エラー理由コード
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        //リクエストタイプ
        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
        

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        IfoOrderManager l_ifoMgr=(IfoOrderManager)l_tradingModule.getOrderManager();

        IfoOrderUnit l_ifoOrderUnit = l_ifoOrderUnit = 
        	(IfoOrderUnit)l_ifoMgr.toOrderUnit(l_orderUnitParams);

        WEB3IfoFrontOrderService l_service = (WEB3IfoFrontOrderService) 
        						Services.getService(WEB3IfoFrontOrderService.class);
        try
		{
			// 発注経路区分 submit_order_route_div
			l_orderUnitParams.setSubmitOrderRouteDiv(
					l_service.getChangeSubmitOrderRouteDiv(l_ifoOrderUnit));

			// 注文Rev.order_rev
			l_orderUnitParams.setOrderRev(
					l_service.getChangeOrderRev(l_ifoOrderUnit));
		}
		catch (WEB3BaseException l_web3BaseException)
		{
			log.error(STR_METHOD_NAME, l_web3BaseException);
			throw new WEB3BaseRuntimeException(l_web3BaseException.getErrorInfo(), 
					STR_METHOD_NAME);
		}
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * １）　@注文単位オブジェクト取得<BR>
     * <BR>
     * 引数の注文履歴Params.注文ID、注文単位ＩＤに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@拡張項目セット<BR>
     * 　@更新内容は、「W指値注文切替（OK）_注文履歴テーブル仕様.xls」参照。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     *
     * @@param l_context - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_orderActionParams - (注文履歴Params)<BR>
     * 注文履歴行オブジェクト。<BR>
     * @@return IfoOrderActionParams
     * @@roseuid 44963AB7025C
     */
    public IfoOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        IfoOrderActionParams l_orderActionParams)
    {
        final String STR_METHOD_NAME =
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderActionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_orderActionParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("注文履歴Params == null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow = null;
        try
        {
            l_ifoOrderUnitRow = IfoOrderUnitDao.findRowByPk(l_orderActionParams.getOrderUnitId());
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //注文履歴ＩＤ
        try
        {
            l_orderActionParams.setOrderActionId(IfoOrderActionDao.newPkValue());
        }
        catch (DataNetworkException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //注文イベントタイプ
        l_orderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);

        //  発注条件      order_condition_type
        //注文単位テーブル.発注条件より編集。
        l_orderActionParams.setOrderConditionType(l_ifoOrderUnitRow.getOrderConditionType());

        // 発注条件演算子       order_cond_operator
        //注文単位テーブル.発注条件演算子より編集。
        l_orderActionParams.setOrderCondOperator(l_ifoOrderUnitRow.getOrderCondOperator());

        //  逆指値基準値タイプ       stop_price_type
        //  注文単位テーブル.逆指値基準値タイプより編集。
        l_orderActionParams.setStopPriceType(l_ifoOrderUnitRow.getStopPriceType());

        //  逆指値基準値        stop_order_price
        //  注文単位テーブル.逆指値基準値より編集。
        if (l_ifoOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_orderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_orderActionParams.setStopOrderPrice(l_ifoOrderUnitRow.getStopOrderPrice());
        }

        //  （W指値）訂正指値        w_limit_price
        // 注文単位テーブル.（W指値）訂正指値より編集。
        if (l_ifoOrderUnitRow.getWLimitPriceIsNull())
        {
            l_orderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setWLimitPrice(l_ifoOrderUnitRow.getWLimitPrice());
        }

        // 注文失効日付           expiration_date
        //注文単位テーブル.注文失効日付より編集。
        l_orderActionParams.setExpirationDate(l_ifoOrderUnitRow.getExpirationDate());

        //  概算受渡代金           estimated_price
        //  注文単位テーブル.概算受渡代金より編集
        if (l_ifoOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_orderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_orderActionParams.setEstimatedPrice(l_ifoOrderUnitRow.getEstimatedPrice());
        }

        // 注文訂正・取消区分        modify_cancel_type
        //注文単位テーブル.注文訂正・取消区分より編集
        l_orderActionParams.setModifyCancelType(l_ifoOrderUnitRow.getModifyCancelType());

        //  決済順序             closing_order
        //注文単位テーブル.決済順序より編集
        l_orderActionParams.setClosingOrder(l_ifoOrderUnitRow.getClosingOrder());

        //  注文エラー理由コード         error_reason_code
        //注文単位テーブル.注文エラー理由コードより編集
        l_orderActionParams.setErrorReasonCode(l_ifoOrderUnitRow.getErrorReasonCode());

        //  リクエストタイプ              request_type
        //注文単位テーブル.リクエストタイプより編集
        l_orderActionParams.setRequestType(l_ifoOrderUnitRow.getRequestType());

        // 取引者ID                 trader_id
        //注文単位テーブル.取引者IDより編集
        if (l_ifoOrderUnitRow.getTraderIdIsNull())
        {
            l_orderActionParams.setTraderId(null);
        }
        else
        {
            l_orderActionParams.setTraderId(l_ifoOrderUnitRow.getTraderId());
        }

        //  注文経路区分            order_root_div
        //注文単位テーブル.注文経路区分より編集
        l_orderActionParams.setOrderRootDiv(l_ifoOrderUnitRow.getOrderRootDiv());

        //  元発注条件            org_order_condition_type
        //注文単位テーブル.元発注条件より編集。
        l_orderActionParams.setOrgOrderConditionType(l_ifoOrderUnitRow.getOrgOrderConditionType());

        // 元発注条件演算子       org_order_cond_operator
        //注文単位テーブル.元発注条件演算子より編集。
        l_orderActionParams.setOrgOrderCondOperator(l_ifoOrderUnitRow.getOrgOrderCondOperator());

        //  元逆指値基準値タイプ          org_stop_price_type
        //  注文単位テーブル.元逆指値基準値タイプより編集。
        l_orderActionParams.setOrgStopPriceType(l_ifoOrderUnitRow.getOrgStopPriceType());

        // 元逆指値基準値         org_stop_order_price
        //注文単位テーブル.元逆指値基準値より編集。
        if (l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_orderActionParams.setOrgStopOrderPrice(null);
        }
        else
        {
            l_orderActionParams.setOrgStopOrderPrice(l_ifoOrderUnitRow.getOrgStopOrderPrice());
        }

        // 元（W指値）訂正指値           org_w_limit_price
        //  注文単位テーブル.（W指値）訂正指値より編集。
        if (l_ifoOrderUnitRow.getOrgWLimitPriceIsNull())
        {
            l_orderActionParams.setOrgWLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setOrgWLimitPrice(l_ifoOrderUnitRow.getOrgWLimitPrice());
        }

        //  元（W指値）執行条件          org_w_limit_exec_cond_type
        //注文単位テーブル.元（W指値）執行条件より編集。
        l_orderActionParams.setOrgWLimitExecCondType(
            l_ifoOrderUnitRow.getOrgWLimitExecCondType());

        //（W指値）執行条件           w_limit_exec_cond_type
        //注文単位テーブル.（W指値）執行条件より編集
        l_orderActionParams.setWLimitExecCondType(
            l_ifoOrderUnitRow.getWLimitExecCondType());

        // （W指値）切替前指値              w_limit_before_limit_price
        //注文単位テーブル.（W指値）切替前指値より編集
        if (l_ifoOrderUnitRow.getWLimitBeforeLimitPriceIsNull())
        {
            l_orderActionParams.setWLimitBeforeLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setWLimitBeforeLimitPrice(
                l_ifoOrderUnitRow.getWLimitBeforeLimitPrice());
        }

        //（W指値）切替前執行条件         w_limit_before_exec_cond_type
        //注文単位テーブル.（W指値）切替前執行条件より編集
        l_orderActionParams.setWLimitBeforeExecCondType(
            l_ifoOrderUnitRow.getWLimitBeforeExecCondType());

        //市場から確認済みの執行条件         confirmed_exec_condition_type
        //注文単位テーブル.市場から確認済みの執行条件より編集
        l_orderActionParams.setConfirmedExecConditionType(
            l_ifoOrderUnitRow.getConfirmedExecConditionType());

        //注文期限区分expiration_date_type
        //注文単位テーブル.注文期限区分より編集。
        l_orderActionParams.setExpirationDateType(
            l_ifoOrderUnitRow.getExpirationDateType());

        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
}
@
