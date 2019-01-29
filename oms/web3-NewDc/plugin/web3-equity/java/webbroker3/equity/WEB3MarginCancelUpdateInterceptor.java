head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取消更新インタセプタ(WEB3MarginCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 凌建平 (中訊) 新規作成
                   2004/12/09 水落（SRA）残案件対応
                   2005/01/06 岡村 (SRA) JavaDoc修正
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取消更新インタセプタ）。<BR>
 * <BR>
 * 注文取消登録時の、DB更新仕様カスタマイズ用のクラス。<BR>
 * （EqTypeOrderManagerPersistenceEventInterceptorの実装）
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCancelUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCancelUpdateInterceptor.class);

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
     * @@param    l_trader           - 代理入力者<BR>
     */
    public WEB3MarginCancelUpdateInterceptor(
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
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。<BR>
     * <BR>
     * 更新内容は、DB設定論理「信用取消_株式注文単位テーブル.xls」参照。<BR>
     * <BR>
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
     * @@roseuid 40D2975C0185
     */
    public EqtypeOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = "WEB3MarginCancelUpdateInterceptor:mutate()";
        log.entering(STR_METHOD_NAME);

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
        
        //注文訂正・取消区分をセット
        if (l_orderUnitParams.getConfirmedQuantityIsNull())
        {
            //全部取消完了
            log.debug("市場に未送信の注文の取消の場合：　@3:全部取消完了");             
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);
        }
        else
        {
            //取消中
            log.debug("市場に送信済の注文の取消の場合：　@1:取消中");
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELING);
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
        
        //注文エラー理由コードをセット
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        // 元注文数量
        // this.市場から確認済の数量 == Double.NaN（=市場未送信）の場合
        if (l_orderUnitParams.getConfirmedQuantityIsNull())
        {
            l_orderUnitParams.setOriginalQuantity(0);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (信用取消更新インタセプタ)<BR>
     * コンストラクタ。<BR>
     * @@return WEB3MarginCancelUpdateInterceptor
     * @@roseuid 40D28AEB01B4
     */
    public WEB3MarginCancelUpdateInterceptor WEB3EquityCancelUpdateInterceptor() 
    {
        return null;
    }
}
@
