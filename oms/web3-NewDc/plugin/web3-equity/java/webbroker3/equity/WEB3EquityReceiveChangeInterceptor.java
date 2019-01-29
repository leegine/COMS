head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityReceiveChangeInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文訂正通知インタセプタ(WEB3EquityReceiveChangeInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 李綱 (中訊) 新規作成
Revesion History : 2004/12/10 水落 (SRA) 残案件対応
Revesion History : 2005/01/05 岡村 (SRA) JavaDoc修正
Revesion History : 2006/11/03 柴双紅 (中訊) ＤＢ更新仕様No.179
Revesion History : 2006/11/28 柴双紅 (中訊) ＤＢ更新仕様No.185
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3ShortSellingCountMethodDivDef;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文訂正通知インタセプタ）。<BR>
 * <BR>
 * 以下のケースで使用する、注文データ更新仕様カスタマイズ用のクラス。<BR>
 * <BR>
 * ・WebBroker側の注文訂正に対する、通知受信時の注文単位の更新<BR>
 * ・SONAR側の注文訂正及び通知受信時の注文単位の更新
 * @@version 1.0
 */
public class WEB3EquityReceiveChangeInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * (ログ出力ユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveChangeInterceptor.class);

    /**
     * (株式訂正取消通知キューParams)<BR>
     */
    private HostEqtypeOrderClmdReceiptParams changeCancelNotifyQueueParams;

    /**
     * (株式訂正通知内容)<BR>
     */
    private WEB3EquityReceiveChangeSpec changeNotifySpec;

    public WEB3EquityReceiveChangeInterceptor()
    {

    }
    
    /**
     * (株式注文訂正通知インタセプタ)<BR>
     * コンストラクタ。<BR>
     * 引数の株式訂正取消通知キューParamsオブジェクトを、プロパティに設定する。<BR>
     * <BR>
     * @@param l_changeCancelNotifyQueueParams - 株式訂正取消通知キューParams<BR>
     * @@return webbroker3.equity.WEB3EquityOrderReceiveChangeInterceptor<BR>
     * @@roseuid 4039F7390118<BR>
     */
    public WEB3EquityReceiveChangeInterceptor(HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams)
    {
        this.changeCancelNotifyQueueParams = l_hostEqtypeOrderClmdReceiptParams;
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
     * （注文状態が”発注済み（変更注文）：MODIFIED”または”発注失敗（変更注文）：NOT_<BR>
     * MODIFIED”に該当する場合のみ、処理を実施する。以外は、引数をそのまま返却し処理を<BR>
     * 終了する）<BR>
     * 　@注文単位Paramsの値を「訂正通知」の状態に設定し返却する。<BR>
     * <BR>
     * 更新内容は、「株式訂正取消通知_株式注文単位テーブル.xls」参照。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams<BR>
     * @@roseuid 4039F7390114<BR>
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        OrderStatusEnum l_orderStatus = l_orderUnitParams.getOrderStatus();
        if (OrderStatusEnum.MODIFIED.equals(l_orderStatus) ||
            OrderStatusEnum.NOT_MODIFIED.equals(l_orderStatus))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
            EqTypeOrderUnit l_orderUnitBefore = null;
            try
            {
                l_orderUnitBefore =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnitParams.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnitBefore.getDataSourceObject();
            EqtypeOrderUnitParams l_orderUnitParamBefore = new EqtypeOrderUnitParams(l_orderUnitRow);
            boolean l_blnStopOderSwitching = false;
            try
            {
                l_blnStopOderSwitching = l_orderManager.isStopOrderSwitching(l_orderUnit);
            }
            catch (WEB3BaseException l_exc)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exc.getMessage(),
                    l_exc);
            }

            //株式注文訂正取消通知キューテーブル.訂正取消通知区分
            String l_strCanmodReceiptType = 
                this.changeCancelNotifyQueueParams.getCanmodReceiptType();

            //注文数量
            l_orderUnitParams.setQuantity(
                this.changeCancelNotifyQueueParams.getModifiedQuantity());

            //指値
            l_orderUnitParams.setLimitPrice(
                this.changeCancelNotifyQueueParams.getModifiedLimitPrice());

            //執行条件
            l_orderUnitParams.setExecutionConditionType(
                this.changeNotifySpec.getChangeAfterExecCondType());
                
            //値段条件
            l_orderUnitParams.setPriceConditionType(
                this.changeNotifySpec.getChangeAfterPriceConditionType());


            //ストップ注文切替中(*1）の場合
            //(*1)拡張株式注文マネージャ.isストップ注文切替中()＝trueの場合、ストップ注文切替中。
            //引数に設定する注文単位には、更新前の注文単位を指定する
            //（EqtypeOrderUnitParamsを拡張株式注文マネージャ.toOrderUnit()にて注文単位型にする）
            //(*2)ストップ注文切替中(*1)、かつ、株式注文訂正取消通知キューテーブル.
            //訂正取消通知区分 == ”訂正完了”の場合、ストップ注文切替OK。
            if (l_blnStopOderSwitching)
            {
                //元発注条件 更新前の発注条件以外、（既存値）
                l_orderUnitParams.setOrgOrderConditionType(
                    l_orderUnitParamBefore.getOrderConditionType());

                //元発注条件演算子  更新前の発注条件演算子以外、（既存値）
                l_orderUnitParams.setOrgOrderCondOperator(
                    l_orderUnitParamBefore.getOrderCondOperator());

                //元逆指値基準値  更新前の逆指値基準値 以外、（既存値）
                if (l_orderUnitParamBefore.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgStopOrderPrice(
                        l_orderUnitParamBefore.getStopOrderPrice());
                }

                //元（W指値）訂正指値 更新前の（W指値）訂正指値 以外、（既存値）
                if (l_orderUnitParamBefore.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgWLimitPrice(
                        l_orderUnitParamBefore.getWLimitPrice());
                }

                //元（W指値）執行条件  更新前の（W指値）執行条件 以外、（既存値）
                l_orderUnitParams.setOrgWLimitExecCondType(
                    l_orderUnitParamBefore.getWLimitExecCondType());

                //（W指値）執行条件 null  以外、（既存値）
                l_orderUnitParams.setWLimitExecCondType(null);

                //ストップ注文切替OK(*2）
                if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strCanmodReceiptType))
                {
                    //（W指値）切替前指値 更新前の市場から確認済みの指値 以外、（既存値）
                    if (l_orderUnitParamBefore.getConfirmedPriceIsNull())
                    {
                        l_orderUnitParams.setWLimitBeforeLimitPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setWLimitBeforeLimitPrice(
                            l_orderUnitParamBefore.getConfirmedPrice());
                    }
    
                    //（W指値）切替前執行条件 更新前の市場から確認済みの執行条件 以外、（既存値）
                    l_orderUnitParams.setWLimitBeforeExecCondType(
                        l_orderUnitParamBefore.getConfirmedExecConditionType());
                }

                //発注条件 0：DEFAULT（条件指定なし） 以外、（既存値）
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //発注条件演算子 null 以外、（既存値）
                l_orderUnitParams.setOrderCondOperator(null);

                //逆指値基準値 null 以外、（既存値）
                l_orderUnitParams.setStopOrderPrice(null);

                //（W指値）訂正指値 null 以外、（既存値）
                l_orderUnitParams.setWLimitPrice(null);
            }

            //市場から確認済みの数量
            l_orderUnitParams.setConfirmedQuantity(
                this.changeCancelNotifyQueueParams.getModifiedQuantity());

            //市場から確認済みの指値
            l_orderUnitParams.setConfirmedPrice(
                this.changeCancelNotifyQueueParams.getModifiedLimitPrice());
                
            //注文有効状態
            if (OrderStatusEnum.MODIFIED.equals(l_orderStatus))
            {
                if (l_orderUnitParams.getConfirmedQuantity() > l_orderUnitParams.getExecutedQuantity())
                {
                    l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
                }
                else
                {
                    l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
                }
            }
            
            //注文単価
            l_orderUnitParams.setPrice(this.changeNotifySpec.getLimitPrice());

            //概算受渡代金
            l_orderUnitParams.setEstimatedPrice(
                this.changeNotifySpec.getEstimateDeliveryAmount());

            //注文訂正・取消区分
            String l_strModifiedResult =
                this.changeCancelNotifyQueueParams.getModifiedResult();
            if (WEB3ModifiedResultDef.ALL_CHANGED_NO_EXECUTED.equals(l_strModifiedResult) ||
                WEB3ModifiedResultDef.ALL_CHANGED_PARTIALLY_EXECUTED.equals(l_strModifiedResult))
            {
                //ストップ注文切替中(*1)
                if (l_blnStopOderSwitching)
                {
                    //C:W指値注文全部切替完了
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED);
                }
                else
                {
                    // 以外、7:全部訂正完了
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.CHANGED);
                }
            }
            else if (WEB3ModifiedResultDef.PARTIALLY_CHANGED.equals(l_strModifiedResult) ||
                 WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_NO_EXECUTED.equals(l_strModifiedResult) ||
                 WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_PARTIALLY_EXECUTED.equals(l_strModifiedResult))
            {
                //ストップ注文切替中(*1)
                if (l_blnStopOderSwitching)
                {
                    //B:W指値注文一部切替完了
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED);
                }
                else
                {
                    //以外、6:一部訂正完了
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
                }
            }
            else
            {
                //ストップ注文切替中(*1)
                if (l_blnStopOderSwitching)
                {
                    //D:W指値注文切替失敗
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);
                }
                else
                {
                    //以外、8:訂正失敗
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.CHANGE_ERROR);
                }
            }

            //発注経路区分
            if (this.changeCancelNotifyQueueParams.getModSubmitOrderRouteDiv() != null)
            {
                l_orderUnitParams.setSubmitOrderRouteDiv(
                    this.changeCancelNotifyQueueParams.getModSubmitOrderRouteDiv());
            }
            
            //市場から確認済みの注文単価
            l_orderUnitParams.setConfirmedOrderPrice(
                this.changeNotifySpec.getLimitPrice());
            
            //市場から確認済みの概算受渡代金
            l_orderUnitParams.setConfirmedEstimatedPrice(
                this.changeNotifySpec.getEstimateDeliveryAmount());

            //市場から確認済みの執行条件            
            l_orderUnitParams.setConfirmedExecConditionType(
                this.changeNotifySpec.getChangeAfterExecCondType());

            //リクエストタイプ
            //ストップ注文切替中(*1)の場合、
            if (l_blnStopOderSwitching)
            {
                if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strCanmodReceiptType))
                {
                    //株式注文訂正取消通知キューテーブル.訂正取消通知区分 == ”訂正完了”
                    // であれば、”2：切替完了”
                    l_orderUnitParams.setRequestType(WEB3RequestTypeDef.TRANSFERED);
                }
                else if (WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_strCanmodReceiptType))
                {
                    //株式注文訂正取消通知キューテーブル.訂正取消通知区分 == ”訂正失敗”
                    //  であれば、”5：失効”
                    l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
                }
            }

            //市場から確認済みの値段条件
            l_orderUnitParams.setConfirmedPriceConditionType(
                this.changeNotifySpec.getChangeAfterPriceConditionType());
            
            //市場から確認済みの注文Rev.
            l_orderUnitParams.setConfirmedOrderRev(
                this.changeNotifySpec.getChangeAfterOrderRev());
                
            //元注文数量
            try 
            {
                //現在時刻に該当する空売り規制時間テーブル.空売り数量計上方法@=="閉局扱いで計上"の場合、
                //株式訂正取消通知キューテーブル.訂正後数量
                String l_strShortSelCountMethodDiv =
                    l_orderManager.getShortSellingRestraintTime().getShortSellingCountMethodDiv();
                if (WEB3ShortSellingCountMethodDivDef.COUNT_AS_CLOSE.equals(l_strShortSelCountMethodDiv))
                {
                    l_orderUnitParams.setOriginalQuantity(
                        this.changeCancelNotifyQueueParams.getModifiedQuantity());                
                }
            } 
            catch (WEB3BaseException e) 
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.getMessage(),
                    e);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }

    /**
     * (get株式訂正通知内容)<BR>
     * 株式訂正通知内容オブジェクトを取得する。<BR>
     * @@return WEB3EquityReceiveChangeSpec<BR>
     * @@roseuid 4045841B038A<BR>
     */
    public WEB3EquityReceiveChangeSpec getEquityChangeNotifySpec()
    {
        return this.changeNotifySpec;
    }

    /**
     * (set株式訂正通知内容)<BR>
     * 株式訂正通知内容オブジェクトをセットする。<BR>
     * @@param l_changeNotifySpec - (株式訂正通知内容)<BR>
     * 株式訂正通知内容オブジェクト<BR>
     * @@roseuid 4045841C0000<BR>
     */
    public void setEquityChangeNotifySpec(WEB3EquityReceiveChangeSpec l_receiveChangeSpec)
    {
        this.changeNotifySpec = l_receiveChangeSpec;
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
     * 引数の注文単位Params.注文ID、注文単位ＩＤに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@super.mutate(EqtypeOrderActionParams)をコールする。<BR>
     * <BR>
     * ３）　@xTrade標準項目の更新仕様をカスタマイズする。<BR>
     * 　@　@※xTrade標準実装では、<BR>
     * 　@　@※訂正失敗時には訂正取消通知を行う前の注文単位の値が設定されてしまうため。<BR>
     * <BR>
     * 　@　@注文単価（price）：　@注文単位.指値 をセット。<BR>
     * 　@　@注文数量（quantity）：　@注文単位の同項目の値をセット。<BR>
     * 　@　@市場と確認済の指値（confirmed_price）：　@注文単位の同項目の値をセット。<BR>
     * 　@　@市場と確認済の数量（confirmed_quantity）：　@注文単位の同項目の値をセット。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （EqTypeOrderManagerPersistenceContextにて定数定義）
     * @@param l_orderActionParams - (株式注文履歴Params)<BR>
     * 株式注文履歴が保持する項目のパラメータクラス。
     * @@return EqtypeOrderActionParams
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_orderActionParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        EqTypeOrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                    l_orderActionParams.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        l_orderActionParams = super.mutate(
            l_orderManagerPersistenceType,
            l_orderManagerPersistenceContext,
            l_orderActionParams);
        
        l_orderActionParams.setPrice(l_orderUnitRow.getLimitPrice());
        l_orderActionParams.setQuantity(l_orderUnitRow.getQuantity());
        l_orderActionParams.setConfirmedPrice(l_orderUnitRow.getConfirmedPrice());
        l_orderActionParams.setConfirmedQuantity(l_orderUnitRow.getConfirmedQuantity());
        
        log.entering(STR_METHOD_NAME);
        return l_orderActionParams;
    }
}
@
