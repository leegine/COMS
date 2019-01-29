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
filename	WEB3MarginChangeCloseMarginUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用返済訂正更新インタセプタ(WEB3MarginChangeCloseMarginUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 李松峰 (中訊) 新規作成
Revesion History : 2004/12/09 水落　@ (SRA)　@残案件対応
Revesion History : 2006/11/02 柴双紅 (中訊) ＤＢ更新仕様No.171
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;


/**
 * （信用返済訂正更新インタセプタ）。<BR>
 * <BR>
 * 返済注文訂正登録時の、DB更新仕様カスタマイズ用のクラス。<BR>
 * （EqTypeOrderManagerPersistenceEventInterceptorの実装）
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{ 
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
      WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginUpdateInterceptor.class);

    /**
     * (信用返済注文訂正内容)<BR>
     * 信用返済注文訂正内容オブジェクト。<BR>
     */
    private WEB3MarginChangeSettleContractOrderSpec l_creditCloseMarginChangeUpdateSpec;
    
    /**
     * (概算決済損益代金計算結果)<BR>
     * 概算決済損益代金計算結果オブジェクト。<BR>
     * （注文単価、概算受渡代金の設定に使用）<BR>
     */
    private WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice;
    
    /**
     * (注文経路区分)<BR>
     */
    private String orderRootDiv = null;
    
    /**
     * (代理入力者)<BR>
     */
    private WEB3GentradeTrader trader = null;
    
    /**
     * (信用返済訂正更新インタセプタ)<BR>
     * コンストラクタ。<BR>
     * 引数に指定されたオブジェクトを、同名のプロパティに設定する。<BR>
     * @@param l_creditCloseMarginChangeUpdateSpec - 信用返済注文訂正内容オブジェクト。<BR>
     * @@param l_equityRealizedProfitAndLossPrice - 概算決済損益代金計算結果オブジェクト。<BR>
     * @@param    l_orderRootdiv - 注文経路区分<BR>
     * @@param    l_trader       - 扱者<BR>
     */
    public WEB3MarginChangeCloseMarginUpdateInterceptor( 
        WEB3MarginChangeSettleContractOrderSpec l_creditCloseMarginChangeUpdateSpec, 
        WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice,
        String l_strOrderRootdiv,
        WEB3GentradeTrader l_trader) 
    {
        this.l_creditCloseMarginChangeUpdateSpec = l_creditCloseMarginChangeUpdateSpec;
        this.l_equityRealizedProfitAndLossPrice = l_equityRealizedProfitAndLossPrice;
        this.orderRootDiv = l_strOrderRootdiv;
        this.trader = l_trader;
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * １） 信用返済注文内容判定<BR>
     * 　@・this.信用返済注文訂正内容プロパティ、
     * 　@・this.概算決済損益代金計算結果プロパティ
     * 　@上記のいずれか１つでもnullの場合は、パラメータ.注文単位Paramsを返却し、処理を終了する。<BR>
     * <BR>
     * ２） 拡張項目セット<BR>
     * 　@this.信用返済注文訂正内容プロパティから、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。<BR>
     * <BR>
     * 更新内容はDB設定論理「信用返済訂正_株式注文単位テーブル.xls」参照。<BR>
     * @@param l_updateType - 更新タイプ<BR>
     * INSERTまたは、UPDATE。<BR>
     * 
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_process - 処理<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderUnitParams - 注文単位Params<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 40C6C3F001D4
     */
    public EqtypeOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
            OrderManagerPersistenceContext l_process, 
            EqtypeOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "注文単位Paramsに拡張項目:mutate()";
        log.entering(STR_METHOD_NAME);
        
        //信用返済注文内容判定<BR>
        //　@・this.信用返済注文訂正内容プロパティ、
        //　@・this.概算決済損益代金計算結果プロパティ
        //　@上記のいずれか１つでもnullの場合は、パラメータ.注文単位Paramsを返却し、処理を終了する
        if (this.l_creditCloseMarginChangeUpdateSpec == null 
            || this.l_equityRealizedProfitAndLossPrice == null)
        {
            return l_orderUnitParams;
        }

        //扱者 = インタセプタのプロパティ.代理入力者.取引者ID
        //  ※インタセプタのプロパティ.代理入力者==nullの場合は、nullをセット。
        if (this.trader == null)
        {
            l_orderUnitParams.setTraderId(null);
        }
        else
        {
            l_orderUnitParams.setTraderId(this.trader.getTraderId());
        }

        //執行条件
        l_orderUnitParams.setExecutionConditionType(this.l_creditCloseMarginChangeUpdateSpec.getModifiedExecutionCondition());

        //値段条件
        l_orderUnitParams.setPriceConditionType(this.l_creditCloseMarginChangeUpdateSpec.getModifiedPriceConditionType());
        
        //発注条件を取得
        String l_strOrderConditionType = l_orderUnitParams.getOrderConditionType();

        //発注条件演算子、逆指値基準値
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            l_orderUnitParams.setOrderCondOperator(null);
            l_orderUnitParams.setStopOrderPrice(null);
        }
        else
        {
            l_orderUnitParams.setOrderCondOperator(this.l_creditCloseMarginChangeUpdateSpec.getModifiedOrderCondOperator());
            l_orderUnitParams.setStopOrderPrice(this.l_creditCloseMarginChangeUpdateSpec.getModifiedStopOrderPrice());    
        }

        //（W指値）訂正指値
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType)
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            l_orderUnitParams.setWLimitPrice(null);
        }
        else
        {
            l_orderUnitParams.setWLimitPrice(this.l_creditCloseMarginChangeUpdateSpec.getModifiedWLimitPrice());
        }

        //注文失効日付
        l_orderUnitParams.setExpirationDate(this.l_creditCloseMarginChangeUpdateSpec.getModifiedExpirationDate());

        //注文単価
        l_orderUnitParams.setPrice(l_equityRealizedProfitAndLossPrice.getCalcUnitPrice());

        //概算受渡代金
        l_orderUnitParams.setEstimatedPrice(l_equityRealizedProfitAndLossPrice.getEstimatedRealizedProfitAndLossAmount());

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        OrderUnit l_orderUnit = l_orderManager.toOrderUnit(l_orderUnitParams);
 
        // 市場から確認済みの数量==Double.NaN（＝市場未送信）            
        if (l_orderUnitParams.getConfirmedQuantityIsNull())
        {
            // 注文状態
            // パラメータ.注文単位Params.注文状態（orderStatus）に”MODIFIED”をセット
            l_orderUnitParams.setOrderStatus(
                  OrderStatusEnum.MODIFIED);

            //注文訂正・取消区分
		    //this.isUnexecuted( )==trueの場合は、7：全部訂正完了。
		    if (l_orderUnit.isUnexecuted())
		    {
			  l_orderUnitParams.setModifyCancelType(
				  WEB3ModifyCancelTypeDef.CHANGED);
		    }
		    //    this.isUnexecuted( )==falseの場合は、6：一部訂正完了。
		    else
		    {
			  l_orderUnitParams.setModifyCancelType(
				  WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
		    }
        }
        // 市場から確認済みの数量!=Double.NaN（＝市場送信済）
        else
        {
            // 注文状態
            //パラメータ.注文単位Params.注文状態（orderStatus）に”MODIFY_ACCEPTED”をセット
            l_orderUnitParams.setOrderStatus(
                OrderStatusEnum.MODIFY_ACCEPTED);
                            
            //注文訂正・取消区分 = 5:訂正中
			l_orderUnitParams.setModifyCancelType(
			    WEB3ModifyCancelTypeDef.CHANGING);
        }

        //注文経路区分 = インタセプタのプロパティ.注文経路区分
        l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);
        
        //発注経路区分
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        String l_strSubmitOrderRouteDiv = null;
        try
        {
            l_strSubmitOrderRouteDiv =
                l_frontOrderService.getChangeSubmitOrderRouteDiv(
                    (EqTypeOrderUnit)l_orderUnit);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(l_wbe.getMessage(), l_wbe);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_wbe.getMessage(), l_wbe);
        }
        l_orderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
        
        // 注文エラー理由コード
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        //注文Rev.
        String l_strChangeOrderRev = null;
        try
        {
            l_strChangeOrderRev =
                l_frontOrderService.getChangeOrderRev((EqTypeOrderUnit)l_orderUnit);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(l_wbe.getMessage(), l_wbe);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_wbe.getMessage(), l_wbe);
        }
        l_orderUnitParams.setOrderRev(l_strChangeOrderRev);

        //（W指値）執行条件
        //信用返済注文訂正内容.get訂正後（W指値）執行条件( )
        //ただし、発注条件＝（0：DEFAULT（条件指定なし）、1：逆指値）の場合は、null。
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitParams.getOrderConditionType())
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitParams.getOrderConditionType()))
        {
            l_orderUnitParams.setWLimitExecCondType(null);
        }
        else
        {
            l_orderUnitParams.setWLimitExecCondType(
                this.l_creditCloseMarginChangeUpdateSpec.getModifiedWlimitExecCondType());
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
     * 　@引数の注文単位Params.注文単位ＩＤに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ３）　@拡張項目セット<BR>
     * 　@パラメータ.注文履歴Paramsの拡張項目に、注文単位オブジェクトの同名項目から値をセットし、
     * 　@返却する。<BR>
     * 　@※注文イベントタイプには”2：変更注文”をセットする。<BR>
     * <BR>
     * 更新内容はDB設定論理「信用返済訂正_株式注文履歴テーブル.xls」参照。<BR>
     * @@param l_updateType - 更新タイプ<BR>
     * INSERTまたは、UPDATE。<BR>
     * 
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_process - 処理<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderHistoryParams - 株式注文履歴Params<BR>
     * 株式注文履歴が保持する項目のパラメータクラス。<BR>
     * @@return EqtypeOrderActionParams
     * @@roseuid 40F22A74006A
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderActionParams l_orderHistoryParams) 
    {
        final String STR_METHOD_NAME = "注文履歴Paramsに拡張項目:mutate()";
        log.entering(STR_METHOD_NAME);
        
        //－super.mutate()を実施する。
        EqtypeOrderActionParams l_orderActionParams =
            super.mutate(
                l_updateType,
                l_process,
                l_orderHistoryParams);
        
        //注文イベントタイプ = 2:変更注文
        l_orderHistoryParams.setOrderEventType(OrderEventTypeEnum.CHANGE);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderHistoryParams;
    }
}
@
