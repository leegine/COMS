head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderManagerChangeOrderEventInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文訂正インタセプタ(WEB3EquityOrderManagerChangeOrderEventInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/19 周玲玲 (中訊) 新規作成
Revesion History : 2004/12/07 水落 (SRA) 残案件対応
Revesion History : 2005/01/05 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/11/02 柴双紅 (中訊) ＤＢ更新仕様No.169
Revesion History : 2007/12/19 趙林鵬 (中訊) ＤＢ更新仕様No.207
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文訂正インタセプタ）。
 * @@version 1.0
 */
public class WEB3EquityOrderManagerChangeOrderEventInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderManagerChangeOrderEventInterceptor.class);
    
    /**
     * (注文経路区分)<BR>
     */
    private String orderRootDiv = null;
    
    /**
     * (代理入力者)<BR>
     */
    private WEB3GentradeTrader trader = null;
    
    /**
     * (コンストラクタ)。<BR>
     * @@param    l_orderRootdiv - 注文経路区分<BR>
     * @@param    l_trader       - 扱者<BR>
     */
    public WEB3EquityOrderManagerChangeOrderEventInterceptor(
        String l_strOrderRootdiv, WEB3GentradeTrader l_trader)
    {
        this.orderRootDiv = l_strOrderRootdiv;
        this.trader = l_trader;
    }

    /**
     * (更新値設定)<BR>
     * <BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * 拡張項目セット<BR>
     * 　@this.株式注文内容プロパティから、パラメータ.注文単位Paramsの<BR>
     * 　@拡張項目に値をセットし、返却する。<BR>
     * 更新内容は、<BR>
     * 「（株式注文訂正[訂正実行]）株式注文単位テーブル」参照。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_eqtypeOrderUnitParams - (注文単位Params)<BR>
     * <BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams
     * @@roseuid 4044170803A9
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams) ";

        log.entering(STR_METHOD_NAME);
        
        WEB3EquityNewCashBasedOrderSpec equityOrderSpec = this.getEquityOrderSpec();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingMod.getOrderManager();
        OrderUnit l_orderUnit = l_orderMgr.toOrderUnit(l_eqtypeOrderUnitParams);
        
        //扱者 = インタセプタのプロパティ.代理入力者.取引者ID
        //  ※インタセプタのプロパティ.代理入力者==nullの場合は、nullをセット。
        if (this.trader == null)
        {
            l_eqtypeOrderUnitParams.setTraderId(null);
        }
        else
        {
            l_eqtypeOrderUnitParams.setTraderId(this.trader.getTraderId());
        }

        //執行条件 = 株式注文内容.getExecConditionType( )
        l_eqtypeOrderUnitParams.setExecutionConditionType(equityOrderSpec.getExecConditionType());
            
        // 値段条件
        l_eqtypeOrderUnitParams.setPriceConditionType(equityOrderSpec.getPriceConditionType());
            
        //注文失効日付 = 株式注文内容.getOrderExpDate( )            
        l_eqtypeOrderUnitParams.setExpirationDate(equityOrderSpec.getOrderExpDate());
                        
        //発注条件 = 株式注文内容.get発注条件( )
        l_eqtypeOrderUnitParams.setOrderConditionType(
            equityOrderSpec.getOrderCond());

        //（発注条件＝"DEFAULT（条件指定なし）"の場合はnullセット
        if (equityOrderSpec.getOrderCond().equals(WEB3OrderingConditionDef.DEFAULT))
        {                
            l_eqtypeOrderUnitParams.setOrderCondOperator(null);     // 発注条件演算子                
            l_eqtypeOrderUnitParams.setStopOrderPrice(null);        // 逆指値基準値                
            l_eqtypeOrderUnitParams.setWLimitPrice(null);           // （W指値）訂正指値
        }
        else
        {
            //発注条件演算子 = 株式注文内容.get発注条件演算子( )
            l_eqtypeOrderUnitParams.setOrderCondOperator(
                equityOrderSpec.getOrderCondOperator());

            //逆指値基準値 = 株式注文内容.get逆指値基準値( )
            l_eqtypeOrderUnitParams.setStopOrderPrice(
                equityOrderSpec.getStopLimitPriceBasePrice());

            // （W指値）訂正指値    （（0：DEFAULT、1：逆指値）の場合はnullセット）
            if (equityOrderSpec.getOrderCond().equals(WEB3OrderingConditionDef.STOP_LIMIT_PRICE))
            {
                l_eqtypeOrderUnitParams.setWLimitPrice(null);   // （W指値）訂正指値
            }
            else
            {                
                l_eqtypeOrderUnitParams.setWLimitPrice(
                    equityOrderSpec.getWLimitPriceChange());
            }                
        }
            

        // 市場から確認済みの数量==Double.NaN（＝市場未送信）            
        if (l_eqtypeOrderUnitParams.getConfirmedQuantityIsNull())
        {
            // 注文状態
            // パラメータ.注文単位Params.注文状態（orderStatus）に”MODIFIED”をセット
            l_eqtypeOrderUnitParams.setOrderStatus(
                OrderStatusEnum.MODIFIED);
                              
            //注文訂正・取消区分
            //    this.isUnexecuted( )==trueの場合は、7：全部訂正完了。
            //    this.isUnexecuted( )==falseの場合は、6：一部訂正完了。
            if (l_orderUnit.isUnexecuted())
            {
                l_eqtypeOrderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CHANGED);
            }
            else
            {
                l_eqtypeOrderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
            }      

        }
        // 市場から確認済みの数量!=Double.NaN（＝市場送信済）
        else
        {
            // 注文状態
            //パラメータ.注文単位Params.注文状態（orderStatus）に”MODIFY_ACCEPTED”をセット
            l_eqtypeOrderUnitParams.setOrderStatus(
                OrderStatusEnum.MODIFY_ACCEPTED);
                
            // 注文訂正・取消区分
            l_eqtypeOrderUnitParams.setModifyCancelType(
                WEB3ModifyCancelTypeDef.CHANGING);    
                            
        }            

        //注文単価 = 株式注文内容.get注文単価( )
        l_eqtypeOrderUnitParams.setPrice(
            equityOrderSpec.getOrderUnitPrice());

        //概算受渡代金 = 株式注文内容.get概算受渡代金( )
        l_eqtypeOrderUnitParams.setEstimatedPrice(
            equityOrderSpec.getEstimateDeliveryAmount());

        //注文経路区分 = インタセプタのプロパティ.注文経路区分
        l_eqtypeOrderUnitParams.setOrderRootDiv(this.orderRootDiv);

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
        l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);

        //注文エラー理由コード
        l_eqtypeOrderUnitParams.setErrorReasonCode(
            WEB3ErrorReasonCodeDef.NORMAL);
                        
        //注文Rev.
        WEB3GentradeFinObjectManager l_finOjbectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_eqtypeOrderUnitParams.getMarketId());
        }
        catch(NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        String l_strChangeOrderRev = null;
        try
        {
            //PTS市場注文の場合：
            if (l_market.isPTSMarket())
            {
                //株式発注サービス.getPTS訂正時注文Rev()の戻り値
                l_strChangeOrderRev =
                    l_frontOrderService.getPTSChangeOrderRev((EqTypeOrderUnit)l_orderUnit);
            }
            else
            {
                //株式発注サービス.get訂正時注文Rev()の戻り値
                l_strChangeOrderRev =
                    l_frontOrderService.getChangeOrderRev((EqTypeOrderUnit)l_orderUnit);
            }
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(l_wbe.getMessage(), l_wbe);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_wbe.getMessage(), l_wbe);
        }
        l_eqtypeOrderUnitParams.setOrderRev(l_strChangeOrderRev);

        // （W指値）執行条件
        //株式注文内容.get(W指値)執行条件( )
        //ただし、発注条件＝（0：DEFAULT（条件指定なし）、1：逆指値）の場合は、null。
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_eqtypeOrderUnitParams.getOrderConditionType())
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_eqtypeOrderUnitParams.getOrderConditionType()))
        {
            l_eqtypeOrderUnitParams.setWLimitExecCondType(null);
        }
        else
        {
            l_eqtypeOrderUnitParams.setWLimitExecCondType(equityOrderSpec.getWlimitExecCondType());
        }

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
    }

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * １）　@スーパークラスの同メソッド実施 <BR>
     * 　@-super.mutate()を実施する。 <BR>
     * <BR>
     * ２）　@以下の手続きを実施する。 <BR>
     * <BR>
     * ・ステイタス変更 <BR>
     * 　@-パラメータ.注文履歴Params.注文イベントタイプに”変更注文”をセットする。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_eqtypeOrderActionParams - (株式注文履歴Params)<BR>
     * 株式注文履歴が保持する項目のパラメータクラス。<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams
     * @@roseuid 404BCF6C0387
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_eqtypeOrderActionParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams) ";
        log.entering(STR_METHOD_NAME);

        //１）　@スーパークラスの同メソッド実施
        //－super.mutate()を実施する。
        EqtypeOrderActionParams l_orderActionParams =
            super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_eqtypeOrderActionParams);

        //ステイタス変更
        // 注文イベントタイプ＝”2：変更注文”セット
        l_eqtypeOrderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }

}
@
