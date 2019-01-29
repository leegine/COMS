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
filename	WEB3EquityReceiveCancelInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文取消通知インタセプタ(WEB3EquityReceiveCancelInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 李綱 (中訊) 新規作成
Revesion History : 2004/12/10 水落 (SRA) 残案件対応
Revesion History : 2005/01/05 岡村 (SRA) JavaDoc修正
Revesion History : 2006/11/28 柴双紅 (中訊) ＤＢ更新仕様No.186,No.187
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
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文取消通知インタセプタ）。
 * @@version 1.0
 */
public class WEB3EquityReceiveCancelInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveCancelInterceptor.class);
    
    /**
     * (株式訂正取消通知キューParams)<BR>
     */
    private HostEqtypeOrderClmdReceiptParams hostEqtypeOrderClmdReceiptParams;

    /**
     * (株式取消通知内容)<BR>
     */
    private WEB3EquityReceiveCancelSpec receiveCancelSpec;

    /**
     * @@roseuid 40A99E0601E4<BR>
     */
    public WEB3EquityReceiveCancelInterceptor()
    {

    }

    /**
     * (株式注文取消通知インタセプタ)<BR>
     * 
     * コンストラクタ。<BR>
     * 引数の株式訂正取消通知キューParamsオブジェクトを、プロパティに設定する。<BR>
     * @@param l_quoteParams - 株式訂正取消通知キューParams<BR>
     * @@return webbroker3.equity.WEB3EquityReceiveCancelInterceptor<BR>
     * @@roseuid 403DE09901AF<BR>
     */
    public WEB3EquityReceiveCancelInterceptor(HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams)
    {
        this.hostEqtypeOrderClmdReceiptParams = l_hostEqtypeOrderClmdReceiptParams;
    }

    /**
     * (更新値設定)<BR>
     * 
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * 
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * 
     * １） 拡張項目セット<BR>
     * （注文状態が”発注済み（取消注文）：CANCELLED”または”発注失敗（取消注文）：NOT<BR>
     * _CANCELLED”の場合のみ処理を実施する。<BR>
     * 　@以外は、引数をそのまま返却し処理を終了する）<BR>
     * 　@注文単位Paramsの値を「取消通知」の状態に設定し返却する。<BR>
     * 
     * 更新内容は、「株式訂正取消通知_株式注文単位テーブル.xls」参照。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR> 
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return EqtypeOrderUnitParams<BR>
     * @@roseuid 403DE09901AB<BR>
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);

        OrderStatusEnum l_orderStatus = l_orderUnitParams.getOrderStatus();
        if (OrderStatusEnum.CANCELLED.equals(l_orderStatus) ||
            OrderStatusEnum.NOT_CANCELLED.equals(l_orderStatus))
        {
            //指値
            l_orderUnitParams.setLimitPrice(
                this.hostEqtypeOrderClmdReceiptParams.getModifiedLimitPrice());

            //執行条件
            l_orderUnitParams.setExecutionConditionType(
                this.receiveCancelSpec.getChangeAfterExecCond());
            
            //値段条件
            l_orderUnitParams.setPriceConditionType(
                this.receiveCancelSpec.getChangeAfterPriceConditionType());

            //取消失敗かつリミット注文有効(*1）の場合
            //(*1)
            //通知キュー.訂正取消通知区分="取消失敗"の場合、取消失敗。
            //株式データアダプタ.getＷ指値用有効状態区分()＝"リミット注文有効"の場合、
            //　@リミット注文有効。
            //引数に設定する注文単位には、更新前の注文単位を指定する
            boolean l_blnCanmodReceiptType = false;
            if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(
                this.hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
            {
                l_blnCanmodReceiptType = true;
            }
            String l_strWLimitEnableStatusDiv = null;
            try
            {
                l_strWLimitEnableStatusDiv = WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
            }
            catch(WEB3BaseException l_exc)
            {
                log.debug(STR_METHOD_NAME, l_exc);
                throw new WEB3BaseRuntimeException(
                    l_exc.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exc.getMessage(),
                    l_exc);
            }
            boolean l_blnStopEnableType = false;
            if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
            {
                l_blnStopEnableType = true;
            }
            if (l_blnCanmodReceiptType && l_blnStopEnableType)
            {
                //元発注条件  更新前の発注条件  以外、（既存値）
                l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());
                //元発注条件演算子  更新前の発注条件演算子  以外、（既存値）
                l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());
                //元逆指値基準値  更新前の逆指値基準値  以外、（既存値）
                if (l_orderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
                }
                //元（W指値）訂正指値  更新前の（W指値）訂正指値  以外、（既存値）
                if (l_orderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
                }
                //元（W指値）執行条件  更新前の（W指値）執行条件  以外、（既存値）
                l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());
                //（W指値）執行条件  null  以外、（既存値）
                l_orderUnitParams.setWLimitExecCondType(null);
                //発注条件 0：DEFAULT（条件指定なし） 以外、（既存値）
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
                //発注条件演算子 null 以外、（既存値）
                l_orderUnitParams.setOrderCondOperator(null);
                //逆指値基準値 null 以外、（既存値）
                l_orderUnitParams.setStopOrderPrice(null);
                //（W指値）訂正指値 null 以外、（既存値）
                l_orderUnitParams.setWLimitPrice(null);
                //リクエストタイプ  5:失効  以外、（既存値）
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }
            //市場から確認済みの指値
            l_orderUnitParams.setConfirmedPrice(
                this.hostEqtypeOrderClmdReceiptParams.getModifiedLimitPrice());

            //注文単価  株式取消通知内容.注文単価
            l_orderUnitParams.setPrice(this.receiveCancelSpec.getLimitPrice());

            //概算受渡代金
            l_orderUnitParams.setEstimatedPrice(
                this.receiveCancelSpec.getEstimatedPrice());
            
            //注文訂正・取消区分
            String l_strModifiedResult = this.hostEqtypeOrderClmdReceiptParams.getModifiedResult();
            if (WEB3ModifiedResultDef.ALL_CANCEL.equals(l_strModifiedResult))
            {
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CANCELED);
            }
            else if (WEB3ModifiedResultDef.PARTIALLY_CANCEL.equals(l_strModifiedResult) ||
                     WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_NO_EXECUTED.equals(l_strModifiedResult) ||
                     WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_EXECUTED.equals(l_strModifiedResult))
            {
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.PART_CANCELED);
            }
            else
            {
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CANCEL_ERROR);
            }
            
            //発注経路区分
            if (this.hostEqtypeOrderClmdReceiptParams.getModSubmitOrderRouteDiv() != null)
            {
                l_orderUnitParams.setSubmitOrderRouteDiv(
                    this.hostEqtypeOrderClmdReceiptParams.getModSubmitOrderRouteDiv());
            }

            //市場から確認済みの注文単価   株式取消通知内容.注文単価
            l_orderUnitParams.setConfirmedOrderPrice(this.receiveCancelSpec.getLimitPrice());

            //市場から確認済みの概算受渡代金
            l_orderUnitParams.setConfirmedEstimatedPrice(
                this.receiveCancelSpec.getEstimatedPrice());
            
            //市場から確認済みの執行条件
            l_orderUnitParams.setConfirmedExecConditionType(
                this.receiveCancelSpec.getChangeAfterExecCond());
            
            //市場から確認済みの値段条件
            l_orderUnitParams.setConfirmedPriceConditionType(
                this.receiveCancelSpec.getChangeAfterPriceConditionType());
                
            //元注文数量
            try 
            {
                //現在時刻に該当する空売り規制時間テーブル.空売り数量計上方法@=="閉局扱いで計上"の場合、
                //株式訂正取消通知キューテーブル.訂正後数量※0クリアされる
                String l_strShortSelCountMethodDiv =
                    l_orderManager.getShortSellingRestraintTime().getShortSellingCountMethodDiv();
                if (WEB3ShortSellingCountMethodDivDef.COUNT_AS_CLOSE.equals(l_strShortSelCountMethodDiv))
                {
                    l_orderUnitParams.setOriginalQuantity(
                        this.hostEqtypeOrderClmdReceiptParams.getModifiedQuantity());                
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
     * (get株式取消通知内容)<BR>
     * 
     * 株式取消通知内容オブジェクトを取得する。<BR>
     * @@roseuid 4045847702A0<BR>
     */
    public WEB3EquityReceiveCancelSpec getReceiveCancelSpec()
    {
        return this.receiveCancelSpec;
    }

    /**
     * (set株式取消通知内容)<BR>
     * 
     * 株式取消通知内容オブジェクトをセットする。<BR>
     * @@roseuid 40458477030D<BR>
     */
    public void setReceiveCancelSpec(WEB3EquityReceiveCancelSpec l_equityReceiveCancelSpec)
    {
        this.receiveCancelSpec = l_equityReceiveCancelSpec;
    }
}
@
