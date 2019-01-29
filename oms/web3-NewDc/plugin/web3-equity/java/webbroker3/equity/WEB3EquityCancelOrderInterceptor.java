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
filename	WEB3EquityCancelOrderInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文取消インタセプタ(WEB3EquityCancelOrderInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 李雲峰 (中訊) 新規作成
                   2004/12/07 水落（SRA） 残案件対応
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文取消インタセプタ）。
 * @@version 1.0
 */
public class WEB3EquityCancelOrderInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityCancelOrderInterceptor.class);
    
    /**
     * (注文経路区分)<BR>
     */
    private String orderRootDiv = null;
    
    /**
     * (代理入力者)<BR>
     */
    private WEB3GentradeTrader trader = null;
    
    /**
     * （受渡代金）。
     */
    private double deliveryPrice = 0.0;

    /**
     * (コンストラクタ)。<BR>
     * @@param    l_dblDeliveryPrice - 受渡代金
     * @@param    l_orderRootdiv     - 注文経路区分<BR>
     * @@param    l_trader           - 扱者<BR>
     */
    public WEB3EquityCancelOrderInterceptor(
        double l_dblDeliveryPrice,
        String l_strOrderRootdiv,
        WEB3GentradeTrader l_trader)
    {
        this.deliveryPrice = l_dblDeliveryPrice;
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
     * １） 拡張項目セット<BR>
     * （注文状態が”受付済み（取消注文）：<BR>
     * CANCEL_ACCEPTED”または”発注済み（取消注文)<BR>
     * ：CANCELLED”の場合のみ処理を実施する。<BR>
     * 　@以外は、引数をそのまま返却し処理を終了する）<BR>
     * 　@注文単位Paramsの値を「注文取消」の状態に設定し返却する。<BR>
     * <BR>
     * 更新内容は、「注文取消_株式注文単位テーブル.xls」参照。<BR>
     * <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     *<BR>
     * @@param l_manage - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams
     * @@roseuid 403EEAE5029A
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_manage,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderUnitParams)";

        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 注文状態＝”12：受付済み（取消注文）”又は”14：発注済み（取消注文）”の場合のみ
        // 「注文取消」状態に設定する。
        if (l_orderUnitParams.getOrderStatus().equals(OrderStatusEnum.CANCEL_ACCEPTED)
            || l_orderUnitParams.getOrderStatus().equals(OrderStatusEnum.CANCELLED))
        {
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
            
            //概算受渡金額設定
            l_orderUnitParams.setEstimatedPrice(this.deliveryPrice);
            
            // 市場から確認済みの数量==Double.NaN（＝市場未送信）            
            if (l_orderUnitParams.getConfirmedQuantityIsNull())
            {                    
                // 注文訂正・取消区分（3：全部取消完了）
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CANCELED);
            }
            // 市場から確認済みの数量!=Double.NaN（＝市場送信済）
            else
            {
                // 注文訂正・取消区分（1：取消中）
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CANCELING);     
            }

            //注文経路区分 = インタセプタのプロパティ.注文経路区分
            l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);
            
            //発注経路区分
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
            String l_strSubmitOrderRouteDiv = null;
            try
            {
                l_strSubmitOrderRouteDiv =
                    l_frontOrderService.getChangeSubmitOrderRouteDiv(l_orderUnit);
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
            
            //注文エラー理由コード
            l_orderUnitParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.NORMAL);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
