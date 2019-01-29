head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoSettleContractChangeUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP返済訂正更新インタセプタ(WEB3IfoSettleContractChangeUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/11 盧法@旭 (中訊) 新規作成
Revesion History : 2006/07/13 郭英 (中訊) DB更新仕様No.090,093,108
Revesion History : 2007/01/29 柴双紅 (中訊) DB更新仕様No.136,152
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
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
 * (先物OP返済訂正更新インタセプタ)<BR>
 * 先物OP返済訂正更新インタセプタクラス<BR>
 * @@author 盧法@旭
 * @@version 1.0 
 */
public class WEB3IfoSettleContractChangeUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoSettleContractChangeUpdateInterceptor.class);
  
    /**
     * (返済訂正内容)<BR>
     * 返済訂正内容オブジェクト<BR>
     */
    private WEB3IfoChangeSettleContractOrderSpec SettleContractChangeSpc;
    
    /**
     * (決済順序)
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
     * 一括返済時の場合設定<BR>
     */
    private String settleSequence;
    
	/**
	 * 注文経路区分
	 */
	private String orderRootDiv;
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * 
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * 
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、<BR>・
     * 却する。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     *   注文単位.先物／オプション区分 == "先物"の場合<BR> 
     *      「先物返済訂正_注文単位テーブル仕様.xls」参照 <BR>
     *   注文単位.先物／オプション区分 == "オプション"の場合<BR>
     *      「OP返済訂正_注文単位テーブル仕様.xls」参照<BR> 
     * @@param l_updateType - 更新タイプ<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * 
     * @@param l_process - 処理<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_orderUnitParams - 注文単位Params<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40695893013F
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process, 
        IfoOrderUnitParams l_orderUnitParams) 
    {

        final String STR_METHOD_NAME =
            ".mutate(OrderManagerPersistenceType l_updateType," +
            "OrderManagerPersistenceContext l_process, " +
            "IfoOrderUnitParams l_orderUnitParams)"; 
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnitParams == null)
        {
            log.debug("Enter the path that the l_orderUnitParams is null.");
            log.error(STR_METHOD_NAME,new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ STR_METHOD_NAME ));
            log.debug("Exit the path that the l_orderUnitParams is null.");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        } 
        
        try
        {
            if (traderId == 0)
            {   
                l_orderUnitParams.setTraderId(null);
            }
            else
            {
                l_orderUnitParams.setTraderId(traderId);
            }

            //執行条件
            l_orderUnitParams.setExecutionConditionType(SettleContractChangeSpc.getChangeExecCondType());
            
            //インタセプタ.発注条件を設定する
            l_orderUnitParams.setOrderConditionType(this.orderCond);
            
            if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
            {               
                //インタセプタ.発注条件演算子を設定する
                l_orderUnitParams.setOrderCondOperator(null);   
                
                //インタセプタ.逆指値基準値タイプを設定する
                l_orderUnitParams.setStopPriceType(null);
                
                //インタセプタ.逆指値基準値を設定する
                l_orderUnitParams.setStopOrderPrice(null);
                //（W指値）訂正指値)
                l_orderUnitParams.setWLimitPrice(null);
            }
            else
            {                
                //インタセプタ.発注条件演算子を設定する
                l_orderUnitParams.setOrderCondOperator(this.orderCondOperator);                
                log.debug("発注条件演算子:"+ this.orderCondOperator);
                
                //インタセプタ.逆指値基準値タイプを設定する
                l_orderUnitParams.setStopPriceType(this.stopOrderBasePriceType);
                
                //インタセプタ.逆指値基準値を設定する
                l_orderUnitParams.setStopOrderPrice(this.stopOrderBasePrice);   
                
                // （W指値）訂正指値)           
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
                {
                    l_orderUnitParams.setWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitPrice(this.wLimitPriceChange);
                }                   
            }                   
            //注文失効日付 
            l_orderUnitParams.setExpirationDate(WEB3DateUtility.toDay(this.SettleContractChangeSpc.getChangeExpirationDate()));
            log.debug("get the value:" + l_orderUnitParams.getExpirationDate().toString() + "is the same as the set value:" + this.SettleContractChangeSpc.getChangeExpirationDate().toString());                        
            
            //原注文が市場未送信(市場から確認済みの数量 == NaN)の場合：10:発注済み（変更注文）
            if (l_orderUnitParams.getConfirmedQuantityIsNull())
            {
                l_orderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            }
            //原注文が市場送信済(市場から確認済みの数量 != NaN)の場合：7:受付済（変更注文）
            else
            {
                l_orderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            }
            
            //注文エラー理由コード 
            l_orderUnitParams.setErrorReasonCode(WEB3IfoOrderModifyCancelTypeDef.ERROR_STATUS_NORMAL);
            log.debug("get the value:" + l_orderUnitParams.getErrorReasonCode() + "is the same as the set value:" + WEB3IfoOrderModifyCancelTypeDef.ERROR_STATUS_NORMAL);                        

            //注文単価              
            l_orderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());
            log.debug("get the value:" + l_orderUnitParams.getPrice() + "is the same as the set value:" + this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());                        
            //概算受渡代金 
            l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());
            log.debug("get the value:" + l_orderUnitParams.getEstimatedPrice() + "is the same as the set value:" + this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());                        
            //get 注文経路区分                
            String l_strOrderRootDiv =
                this.orderRootDiv;
            //注文経路区分
            l_orderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
            //注文訂正・取消区分                     
            //取得注文単位オブジェクト
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
            WEB3OptionOrderManagerImpl l_orderMgr = 
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();                       
            long l_lngOrderId = l_orderUnitParams.getOrderUnitId();
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(l_lngOrderId);
            //注文訂正・取消区分をセット
            
            //注文が市場送信済(市場から確認済みの数量 != NaN)の場合は、5:訂正中    
            if (!l_orderUnitParams.getConfirmedQuantityIsNull())
            {
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGING);
            }
            else
            {
                //this.isUnexecuted( )==trueの場合は、7：全部訂正完了。
                if (l_orderUnit.isUnexecuted())
                {
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.CHANGED);
                }
                
                //this.isUnexecuted( )==falseの場合は、6：一部訂正完了。
                else
                {
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
                }
            }     
            
            //（W指値）執行条件
            //返済訂正内容.get（W指値）執行条件( )
            //※発注条件＝（0：DEFAULT（条件指定なし）、1：逆指値）の場合は、null
            if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
            {
                l_orderUnitParams.setWLimitExecCondType(null);
            }
            else
            {
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
                {
                    l_orderUnitParams.setWLimitExecCondType(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitExecCondType(
                        this.SettleContractChangeSpc.getWLimitExecCondType());
                }
            }

            OrderUnit l_orderUnitAfter = l_orderMgr.toOrderUnit(l_orderUnitParams);
            WEB3IfoFrontOrderService l_ifoOrderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            //発注経路区分
            //先物OP発注サービス.get訂正取消時発注経路区分()の戻り値
            String l_strOrderRouteDiv =
                l_ifoOrderService.getChangeSubmitOrderRouteDiv((IfoOrderUnit)l_orderUnit);
            l_orderUnitParams.setSubmitOrderRouteDiv(l_strOrderRouteDiv);

            //注文Rev.
            //先物OP発注サービス.get訂正時注文Rev()の戻り値
            String l_strOrderRev = l_ifoOrderService.getChangeOrderRev((IfoOrderUnit)l_orderUnitAfter);
            l_orderUnitParams.setOrderRev(l_strOrderRev);

            log.debug("Exit the try path.");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(Exception l_ex)
        {
             log.error(
                  "__an unexpected error__",
                   new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                   this.getClass().getName() + STR_METHOD_NAME,
                   l_ex.toString(),
                   l_ex));   
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     *（mutateメソッドの実装）<BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * プロパティの内容より、パラメータ.注文履歴Paramsの拡張項目に値をセットし、返却する。<BR>
     * １）スーパークラスの更新値設定()メソッドをコールする。<BR>
     * ２）注文イベントタイプに”２：変更注文”をセットする。<BR>
     *【ｘTrade】補足資料.DB更新<BR>
     * オプションの場合<BR>
     *  「OP返済訂正_注文履歴テーブル仕様.xls」参照。<BR>
     * 先物の場合<BR>
     *  「先物返済訂正_注文履歴テーブル仕様.xls」参照。<BR>
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
    
    /**
     * (返済訂正更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数の返済訂正個別エントリをプロパティにセットする。<BR>
     * @@param l_settleContractChangeSpec - 返済訂正内容
     * 返済訂正内容オブジェクト
     * @@return webbroker3.ifo.WEB3IfoSettleContractChangeUpdateInterceptor
     * @@roseuid 40695893015E
     */
    public WEB3IfoSettleContractChangeUpdateInterceptor(WEB3IfoChangeSettleContractOrderSpec l_settleContractChangeSpec) 
    {
        this.SettleContractChangeSpc = l_settleContractChangeSpec;    
    }
    
    /**
     * (set決済順序)<BR>
     * 決済順序をセットする。<BR>
     * @@param l_strSettleSequence - 決済順序<BR>
     * <BR>
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
     * 一括返済時の場合設定<BR>
     * @@roseuid 40695893016D
     */
    public void setSettleSequence(String l_strSettleSequence) 
    {
        this.settleSequence = l_strSettleSequence; 
    }
    
    /**
     * (get決済順序)<BR>
     * 決済順序を取得する。<BR>
     * @@return String
     * @@roseuid 4069589301AC
     */
    public String getSettleSequence() 
    {
        //return null;
        return this.settleSequence;
    }
    
    /**
     * 取引者ID
     */
    private long traderId;
    
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
}
@
