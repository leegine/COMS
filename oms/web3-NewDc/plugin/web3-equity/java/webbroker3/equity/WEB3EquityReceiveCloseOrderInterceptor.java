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
filename	WEB3EquityReceiveCloseOrderInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文失効インタセプタ(WEB3EquityReceiveCloseOrderInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/25 李綱 (中訊) 新規作成
Revesion History : 2004/09/20 鄒政 (中訊) 修正
Revesion History : 2005/01/05 岡村 (SRA) JavaDoc修正
Revesion History : 2006/11/28 柴双紅 (中訊) ＤＢ更新仕様No.188
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文失効インタセプタ）。<BR>
 * <BR>
 * 株式注文単位、株式注文履歴テーブルのカスタマイズ項目に失効時の値をセットする。
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityReceiveCloseOrderInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveCloseOrderInterceptor.class);
    
    /**
     * （コンストラクタ）。<BR>
     * <BR>
     * インスタンスを生成し、プロパティに引数をセットして返却する。
     * @@param isCloseOrderCancel （is失効取消）
     * @@param l_strErrMessage （エラーメッセージ）<BR>
     * 　@　@　@株式失効通知キューParams.エラーメッセージをセット。
     * @@param l_dblEstimatedPrice - (受渡代金)<BR>
     * 対象注文単位の受渡代金をセットする。<BR>
     * @@param l_dblPrice - (注文単価)<BR>
     * 注文単価。<BR>
     * @@roseuid 4143D80100C1
     */
    public WEB3EquityReceiveCloseOrderInterceptor(
        boolean isCloseOrderCancel,
        String l_strErrMessage,
        double l_dblEstimatedPrice,
        double l_dblPrice) 
    {
        this.isCloseOrderCancel = isCloseOrderCancel;
        this.errorCode = l_strErrMessage;
        this.estimatedPrice = l_dblEstimatedPrice;
        this.price = l_dblPrice;
    }
    
    /**
     * （is失効取消）。<BR>
     * <BR>
     * 失効取消かどうかを保持するプロパティ。<BR>
     * 　@－失効取消の場合true<BR>
     * 　@－失効通知の場合false
     */
    private boolean isCloseOrderCancel;
    
    /**
     * （エラーコード）。<BR>
     * 【株式失効通知キューテーブル】エラーメッセージ を設定。
     */
    private String errorCode;
    
    /**
     * (受渡代金)。<BR>
     * 受渡代金。
     */
    private double estimatedPrice;
    
    /**
     * (注文単価)。<BR>
     * 注文単価。
     */
    private double price;
    
    /**
     * （更新値設定）。<BR>
     * <BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * １） 拡張項目セット
     *    更新内容は、「失効通知_株式注文単位テーブル仕様.xls」を参照。
     * @@param l_updateType
     * @@param l_manage
     * @@param l_orderUnitParams
     * @@return EqtypeOrderUnitParams
     * @@roseuid 4143D80100D5
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_manage,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." +  STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);

        //失効通知区分=="失効取消"かつリミット注文有効(*1)の場合
        //(*1)株式データアダプタ.getＷ指値用有効状態区分()＝"リミット注文有効"の場合、リミット注文有効。
        //引数に設定する注文単位には、更新前の注文単位を指定する
        //（EqtypeOrderUnitParamsを拡張株式注文マネージャ.toOrderUnit()にて注文単位型にする）
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
        if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv)
            && this.isCloseOrderCancel)
        {
            //元発注条件  更新前の発注条件   以外の場合：（既存値）
            l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());
            //元発注条件演算子  更新前の発注条件演算子    以外の場合：（既存値）
            l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());
            //元逆指値基準値   更新前の逆指値基準値    以外の場合：（既存値）
            if (l_orderUnitParams.getStopOrderPriceIsNull())
            {
                l_orderUnitParams.setOrgStopOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
            }
            //元（W指値）訂正指値   更新前の（W指値）訂正指値     以外の場合：（既存値）
            if (l_orderUnitParams.getWLimitPriceIsNull())
            {
                l_orderUnitParams.setOrgWLimitPrice(null);
            }
            else
            {
                l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
            }
            //元（W指値）執行条件   更新前の（W指値）執行条件     以外の場合：（既存値）
            l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());
            //（W指値）執行条件   null  以外の場合：（既存値）
            l_orderUnitParams.setWLimitExecCondType(null);
            //発注条件  　@0:DEFAULT  以外の場合：（既存値）
            l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
            //発注条件演算子   null  以外の場合：（既存値）
            l_orderUnitParams.setOrderCondOperator(null);
            //逆指値基準値    null  以外の場合：（既存値）
            l_orderUnitParams.setStopOrderPrice(null);
            //（W指値）訂正指値     null  以外の場合：（既存値）
            l_orderUnitParams.setWLimitPrice(null);
            //リクエストタイプ    5:失効   以外の場合：（既存値）
            l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
        }
        //注文単価
        l_orderUnitParams.setPrice(this.price);
        //概算受渡代金
        l_orderUnitParams.setEstimatedPrice(this.estimatedPrice);
        if (!l_orderUnitParams.getConfirmedQuantityIsNull())
        {
            //市場から確認済の注文単価
            l_orderUnitParams.setConfirmedOrderPrice(this.price);
            //市場から確認済みの概算受渡代金
            l_orderUnitParams.setConfirmedEstimatedPrice(this.estimatedPrice);
        }
        //注文エラー理由コード
        l_orderUnitParams.setErrorReasonCode(this.errorCode);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
