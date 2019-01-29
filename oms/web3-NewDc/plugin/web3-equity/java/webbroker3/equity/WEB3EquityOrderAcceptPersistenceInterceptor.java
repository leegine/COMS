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
filename	WEB3EquityOrderAcceptPersistenceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文受付インタセプタ(WEB3EquityOrderAcceptPersistenceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/19 洪華 (中訊) 新規作成
Revesion History : 2004/09/20 鄒政 (中訊) 修正
Revesion History : 2004/09/24 法@旭 (中訊) 修正
Revesion History : 2004/12/10 水落 (SRA) 修正
Revesion History : 2005/01/05 岡村 (SRA) JavaDoc修正
Revesion History : 2006/07/12 肖志偉 (中訊) ＤＢ更新仕様155
Revesion History : 2006/08/01 李俊 (中訊) ＤＢ更新仕様162
Revesion History : 2006/11/01 柴双紅 (中訊) ＤＢ更新仕様No.172
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文受付インタセプタ）。<BR>
 * <BR>
 * 株式注文単位、株式注文履歴テーブルのカスタマイズ項目に確定後の値をセットする。
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptPersistenceInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (ログ出力ユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderAcceptPersistenceInterceptor.class);

    /**
     * (株式注文受付キューParams)<BR>
     *【株式注文受付キューテーブル】の１レコード。<BR>
     */
    private HostEqtypeOrderAcceptParams hostEqtypeOrderAcceptParams;

    /**
     * （株式注文受付インタセプタ）<BR>
     * <BR>
     * コンストラクタ。<BR>
     */
    public WEB3EquityOrderAcceptPersistenceInterceptor()
    {
        super();
    }

    /**
     * (株式注文受付インタセプタ) <BR>
     * コンストラクタ。 <BR>
     * 引数を、クラスのプロパティにセットする。 <BR>
     * @@param l_hostEqtypeOrderAcceptParams - (株式注文入力受付キューParams) <BR>
     * 【株式注文受付キューテーブル】の１レコード。 <BR>
     * @@return webbroker3.equity.WEB3EquityOrderNotifyIntercepter
     * @@roseuid 403EF16602DD
     */
    public WEB3EquityOrderAcceptPersistenceInterceptor(
        HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams)
    {
        this.hostEqtypeOrderAcceptParams = l_hostEqtypeOrderAcceptParams;
    }

    /**
     * （更新値設定）<BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * １） 拡張項目セット<BR>
     * （注文状態が”発注済（新規注文）：ORDERED”または”発注失敗（新規注文）：<BR>
     * NOT_ORDERED”の場合のみ<BR>
     * 　@処理を実施する）<BR>
     * <BR>
     * 注文状態が”発注済（新規注文）：ORDERED”の場合の更新内容は、<BR>
     * 「株式注文受付_株式注文単位テーブル.xls」<BR>
     * の「DB更新仕様[受付OK]（株式注文単位テーブル）」シートを参照。<BR>
     * 注文状態が”発注失敗（新規注文）：NOT_ORDERED”の場合の更新内容は、<BR>
     * 「株式注文受付_株式注文単位テーブル.xls」<BR>
     * の「DB更新仕様[受付エラー]（株式注文単位テーブル）」シートを参照。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - （更新タイプ）<BR>
     * INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - （処理）<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderUnitParams - （注文単位Params）<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 4143CE5F01B6
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 注文状態が”発注済（新規注文）：ORDERED”の場合の更新内容は、
        //「株式注文受付_株式注文単位テーブル.xls」の
        //「DB更新仕様[受付OK]（株式注文単位テーブル）」シートを参照
        if (OrderStatusEnum.ORDERED.equals(l_orderUnitParams.getOrderStatus()))
        {
            //市場から確認済みの注文単価
            if (l_orderUnitParams.getPriceIsNull())
            {
                l_orderUnitParams.setConfirmedOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedOrderPrice(
                    l_orderUnitParams.getPrice());
            }
            if (l_orderUnitParams.getEstimatedPriceIsNull())
            {
                //市場から確認済みの概算受渡代金
                l_orderUnitParams.setConfirmedEstimatedPrice(null);
            }
            else
            {
                //市場から確認済みの概算受渡代金
                l_orderUnitParams.setConfirmedEstimatedPrice(
                    l_orderUnitParams.getEstimatedPrice());                
            }

            //市場から確認済みの執行条件
            l_orderUnitParams.setConfirmedExecConditionType(
                l_orderUnitParams.getExecutionConditionType());
                
            //市場から確認済みの値段条件
            l_orderUnitParams.setConfirmedPriceConditionType(
                l_orderUnitParams.getPriceConditionType());
            
            //発注経路区分
            if (hostEqtypeOrderAcceptParams != null)
            {
                //注文受付[受付OK]の場合
                l_orderUnitParams.setSubmitOrderRouteDiv(
                    hostEqtypeOrderAcceptParams.getSubmitOrderRouteDiv());
            }
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitParams.getOrderConditionType()))
            {
                //元発注条件
	            l_orderUnitParams.setOrgOrderConditionType(
	                l_orderUnitParams.getOrderConditionType());
	            
	            //元発注条件演算子
	            l_orderUnitParams.setOrgOrderCondOperator(
	                l_orderUnitParams.getOrderCondOperator());
	            
	            //元逆指値基準値
	            if (l_orderUnitParams.getStopOrderPriceIsNull())
	            {
	                l_orderUnitParams.setOrgStopOrderPrice(null);
	            }
	            else 
	            {
	                l_orderUnitParams.setOrgStopOrderPrice(
	                    l_orderUnitParams.getStopOrderPrice());
	            }
	            
	            //発注条件
	            l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
	            
	            //発注条件演算子
	            l_orderUnitParams.setOrderCondOperator(null);
	            
	            //逆指値基準値
	            l_orderUnitParams.setStopOrderPrice(null);
	            
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                Branch l_branch = null;
                try
                {
                    l_branch = l_accManager.getBranch(l_orderUnitParams.getBranchId());
                }
                catch(NotFoundException l_nfe)
                {
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                
                WEB3GentradeInstitution l_institution =
                    (WEB3GentradeInstitution)l_branch.getInstitution();
                boolean l_blnIsSubmitOrderDelayDisregard =
                    l_institution.isSubmitOrderDelayDisregard();

            }
        }
        //注文状態が”発注失敗（新規注文）：NOT_ORDERED”の場合の更新内容は、
        //「株式注文受付_株式注文単位テーブル.xls」の
        //「DB更新仕様[受付エラー]（株式注文単位テーブル）」シートを参照。
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnitParams.getOrderStatus()))
        {
            if (hostEqtypeOrderAcceptParams != null)
            {
                //注文受付[受付エラー]の場合
                
                //発注経路区分
                l_orderUnitParams.setSubmitOrderRouteDiv(
                    hostEqtypeOrderAcceptParams.getSubmitOrderRouteDiv());
                
                //注文エラー理由コード
                l_orderUnitParams.setErrorReasonCode(
                    hostEqtypeOrderAcceptParams.getErrorMessage());
            }
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
     * ２）　@拡張項目セット<BR>
     * 　@○注文エラー理由コード<BR>
     * 　@　@[this.株式注文受付キューParams == null の場合<BR>
     * （＝注文通知からのコール時）]、または<BR>
     * 　@　@[this.株式注文受付キューParams.注文受付結果 == <BR>
     * ”注文受付完了”の場合]<BR>
     * 　@　@　@パラメータ.注文履歴Params.注文エラー理由コードに、<BR>
     * ”0000:正常”をセットする。<BR>
     * 　@　@[上記以外の場合]<BR>
     * 　@　@　@パラメータ.注文履歴Params.注文エラー理由コードに、<BR>
     * 注文単位オブジェクトの同名項目から<BR>
     * 　@　@　@値をセットする。<BR>
     * 　@<BR>
     * 　@○その他の項目　@<BR>
     * 　@パラメータ.注文履歴Paramsの拡張項目に、<BR>
     * 注文単位オブジェクトの同名項目から値をセットし、<BR>
     * 　@返却する。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderActionParams - (株式注文履歴Params)<BR>
     * 株式注文履歴が保持する項目のパラメータクラス。
     * @@return EqtypeOrderActionParams
     * @@roseuid 4110B75A0173
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_orderActionParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderActionParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        OrderManager l_orderMgr = l_tradingMod.getOrderManager();
        EqTypeOrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_orderActionParams.order_unit_id);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        l_orderActionParams =
            super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_orderActionParams);
        
        // 注文エラー理由コード
        if (this.hostEqtypeOrderAcceptParams == null)
        {
            //注文通知の場合
            l_orderActionParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.NORMAL);
        }
        else
        {
            if (this.hostEqtypeOrderAcceptParams.getAcceptStatus().equals(WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE))
            {
                //注文受付[受付OK]の場合
                l_orderActionParams.setErrorReasonCode(
                    WEB3ErrorReasonCodeDef.NORMAL);
            }
            else
            {
                //注文受付[受付エラー]の場合
                l_orderActionParams.setErrorReasonCode(
                    l_orderUnitRow.getErrorReasonCode());
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
}
@
