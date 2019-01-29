head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOpenContractChangeUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP新規建訂正更新インタセプタクラス(WEB3IfoOpenContractChangeUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 艾興 (中訊) 新規作成
Revesion History : 2006/07/13 郭英 (中訊) DB更新仕様No.089,095
Revesion History : 2007/01/29 柴双紅 (中訊) DB更新仕様No.132,140
*/
package webbroker3.ifo;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.define.WEB3IfoOrderModifyCancelTypeDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;

/**
 * (先物OP新規建訂正更新インタセプタ)<BR>
 * 先物OP新規建訂正更新インタセプタクラス<BR>
 * @@author  艾興
 * @@version 1.0
 */
public class WEB3IfoOpenContractChangeUpdateInterceptor
    extends WEB3IfoOrderUpdateInterceptor
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOpenContractChangeUpdateInterceptor.class);
    /**
     * 新規建訂正内容オブジェクト
     */
    private WEB3IfoOpenContractChangeSpec ifoOpenContractOrderSpec;

	/**
	 * 取引者ID
	 */
	private long traderId;
    
	/**
	 * 注文経路区分
	 */
	private String orderRootDiv;
    
    /**
     * @@roseuid 40C07C030242
     */
    public WEB3IfoOpenContractChangeUpdateInterceptor()
    {

    }

    /**
     * (先物OP新規建訂正更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数の先物OP新規建訂正個別をプロパティにセットする。<BR>
     * @@param l_openContractChangeSpec - 新規建訂正内容<BR>
     * 新規建訂正内容オブジェクト<BR>
     * @@return webbroker3.ifo.WEB3IfoOpenContractChangeUpdateInterceptor
     * @@roseuid 4069578201A5
     */
    public WEB3IfoOpenContractChangeUpdateInterceptor(WEB3IfoOpenContractChangeSpec l_openContractChangeSpec)
    {
        ifoOpenContractOrderSpec = l_openContractChangeSpec;
    }
    
    /**
     * (set取引者ID)<BR>
     */
    public void setTraderId (long l_lngTraderID)
    {
        traderId = l_lngTraderID;
    }
	/**
	 * (set注文経路区分)
	 */
	public void setOrderRootDiv(String l_strOrderRootDiv)
	{
		orderRootDiv = l_strOrderRootDiv;
	}
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、<BR>・
     * 却する。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     *   注文単位.先物／オプション区分 == "先物"の場合 
     *      「先物新規建訂正_注文単位テーブル仕様.xls」参照 
     *   注文単位.先物／オプション区分 == "オプション"の場合
     *      「OP新規建訂正_注文単位テーブル仕様.xls」参照 
     * 
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * 
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_ifoOrderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 406957D2034B
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams)
    {
        final String STR_METHOD_NAME = getClass().getName()
                + ".mutate(OrderManagerPersistenceType "
                + "l_orderManagerPersistenceType, "
                + "OrderManagerPersistenceContext "
                + "l_orderManagerPersistenceContext, "
                + "IfoOrderUnitParams l_ifoOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+  "." + STR_METHOD_NAME);          
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        OrderUnit l_orderUnit = null;

        //throws NotfoundException
        l_orderUnit = l_finApp.getTradingModule(
            ProductTypeEnum.IFO).getOrderManager().toOrderUnit(l_ifoOrderUnitParams);

        // 執行条件<br>
        if (ifoOpenContractOrderSpec == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        IfoOrderExecutionConditionType l_IfoOrderExecutionConditionType =
            ifoOpenContractOrderSpec.getChangeExecCondType();
        l_ifoOrderUnitParams.setExecutionConditionType(l_IfoOrderExecutionConditionType);

        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
        {
            // (発注条件演算子)<BR>       
            l_ifoOrderUnitParams.setOrderCondOperator(null);

            // (逆指値基準値タイプ)<BR>      
            l_ifoOrderUnitParams.setStopPriceType(null);

            // (逆指値基準値)<BR>
            l_ifoOrderUnitParams.setStopOrderPrice(null);

            // （W指値）訂正指値)<BR>        
            l_ifoOrderUnitParams.setWLimitPrice(null);
        }
        else
        {
            // (発注条件演算子)<BR>       
            l_ifoOrderUnitParams.setOrderCondOperator(this.orderCondOperator);

            // (逆指値基準値タイプ)<BR>      
            l_ifoOrderUnitParams.setStopPriceType(this.stopOrderBasePriceType);

            // (逆指値基準値)<BR>
            l_ifoOrderUnitParams.setStopOrderPrice(this.stopOrderBasePrice);
            
            // （W指値）訂正指値)<BR>            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
            {
                l_ifoOrderUnitParams.setWLimitPrice(null);
            }
            else
            {
                l_ifoOrderUnitParams.setWLimitPrice(this.wLimitPriceChange);
            }                    
        }

        //注文失効日付 
        l_ifoOrderUnitParams.setExpirationDate(
        WEB3DateUtility.toDay(ifoOpenContractOrderSpec.getChangeExpirationDate()));

        //取引者ID
        // インタセプタ.取引者ID
        if (traderId == 0)
        {   
            l_ifoOrderUnitParams.setTraderId(null);
        }
        else
        {
            l_ifoOrderUnitParams.setTraderId(traderId);
        }
        
        //注文状態
        if (l_ifoOrderUnitParams.getConfirmedQuantityIsNull())
        {
            //原注文が市場未送信(市場から確認済みの数量 == NaN)の場合：10:発注済み（変更注文）
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        }
        else
        {
            //原注文が市場送信済(市場から確認済みの数量 != NaN)の場合：7:受付済（変更注文）
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
        }


        //注文単価
        double l_price;
        if (this.estimateDeliveryAmounCalcResult == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        l_price = this.estimateDeliveryAmounCalcResult.getCalcUnitPrice();
        l_ifoOrderUnitParams.setPrice(l_price);

        //概算受渡代金
        double l_estimatePrice;
        l_estimatePrice =
            this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount();
        l_ifoOrderUnitParams.setEstimatedPrice(l_estimatePrice);

        //注文訂正・取消区分
        //注文が市場送信済(市場から確認済みの数量 != NaN)の場合は、5:訂正中    
        if (!l_ifoOrderUnitParams.getConfirmedQuantityIsNull())
        {
            l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGING);
        }
        else
        {
            if (l_orderUnit.isUnexecuted())
            {
                //this.isUnexecuted( )==trueの場合は、7：全部訂正完了。
                l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGED);
            }
            else
            {
                //this.isUnexecuted( )==falseの場合は、6：一部訂正完了。
                l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
            }
        }

        //インタセプタより取得した同項目の値
        String l_orderRootDiv = this.orderRootDiv;
        l_ifoOrderUnitParams.setOrderRootDiv(l_orderRootDiv);

        //0000：正常
        l_ifoOrderUnitParams.setErrorReasonCode(WEB3IfoOrderModifyCancelTypeDef.ERROR_STATUS_NORMAL);
        
        //（W指値）執行条件
        //新規建訂正内容.get（W指値）執行条件( )
        //※発注条件＝（0：DEFAULT（条件指定なし）、1：逆指値）の場合は、null
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
        {
            l_ifoOrderUnitParams.setWLimitExecCondType(null);
        }
        else
        {
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
            {
                l_ifoOrderUnitParams.setWLimitExecCondType(null);
            }
            else
            {
                l_ifoOrderUnitParams.setWLimitExecCondType(
                    this.ifoOpenContractOrderSpec.getWLimitExecCondType());
            }
        }

        OrderUnit l_orderUnitAfter =
            l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager().toOrderUnit(l_ifoOrderUnitParams);
        WEB3IfoFrontOrderService l_ifoOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
        try
        {
            //発注経路区分
            //先物OP発注サービス.get訂正取消時発注経路区分()の戻り値
            String l_strOrderRouteDiv =
                l_ifoOrderService.getChangeSubmitOrderRouteDiv((IfoOrderUnit)l_orderUnit);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv(l_strOrderRouteDiv);
    
            //注文Rev.
            //先物OP発注サービス.get訂正時注文Rev()の戻り値
            String l_strOrderRev = l_ifoOrderService.getChangeOrderRev((IfoOrderUnit)l_orderUnitAfter);
            l_ifoOrderUnitParams.setOrderRev(l_strOrderRev);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnitParams;
    }

    /**
     * (更新値設定)<BR>
     * (mutateメソッドの実装）<BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * プロパティの内容より、パラメータ.注文履歴Paramsの拡張項目に値をセットし、返却する。<BR>
     * １）スーパークラスの更新値設定()メソッドをコールする。<BR>
     * ２）注文イベントタイプに”２：変更注文”をセットする。<BR>
     *【ｘTrade】補足資料.DB更新<BR>
     * オプションの場合<BR>
     *  「OP新規建訂正_注文履歴テーブル仕様.xls」参照。<BR>
     * 先物の場合<BR>
     *  「先物新規建訂正_注文履歴テーブル仕様.xls」参照。<BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     *   INSERTまたは、UPDATE。<BR>
     *   OrderManagerPersistenceTypeにて定数定義。<BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     *  （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_ifoOrderActionParams - (注文単位Params)<BR>
     *   株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderActionParams
     * @@roseuid 406957D2034B
     */
    public IfoOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderActionParams l_ifoOrderActionParams)
    {
        final String STR_METHOD_NAME = "注文履歴Paramsに拡張項目:mutate()";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderActionParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+  "." + STR_METHOD_NAME);          
        }

        super.mutate(l_orderManagerPersistenceType, l_orderManagerPersistenceContext, l_ifoOrderActionParams);
        l_ifoOrderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderActionParams;
    }

}
@
