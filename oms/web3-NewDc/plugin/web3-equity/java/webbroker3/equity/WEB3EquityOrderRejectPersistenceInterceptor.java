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
filename	WEB3EquityOrderRejectPersistenceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文訂正取消受付インタセプタ(WEB3EquityOrderRejectPersistenceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 周玲玲 (中訊) 新規作成
Revesion History : 2004/9/27 盧法@旭 (中訊) mutate メソッドの完成
                   2004/11/1 法@旭　@修正   
                   2004/12/10 水落（SRA） 修正
                   2005/01/05 岡村和明(SRA) JavaDoc修正
                   2006/11/02 唐性峰　@(中訊)モデルNo.1022
                   2006/11/03 柴双紅 (中訊) ＤＢ更新仕様No.175
                   2006/11/28 柴双紅 (中訊) モデルNo1060,ＤＢ更新仕様No184,No191
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文訂正取消受付インタセプタ）。<BR>
 * <BR>
 * 株式の訂正取消受付サービスにおいて、<BR>
 * 訂正エラー／取消エラー時のDB設定仕様をカスタマイズするためのインタセプタ。<BR>
 * <BR>
 * 株式訂正取消受付で使用する。
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityOrderRejectPersistenceInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (ログ出力ユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderRejectPersistenceInterceptor.class);

    /**
     * (株式注文受付キューParams) <BR>
     * 【株式注文受付キューテーブル】の１レコード。<BR>
     */
    private HostEqtypeOrderAcceptParams hostEqtypeOrderAcceptParams;

    /**
     * (概算代金計算結果)<BR>
     * 概算代金計算結果オブジェクト<BR>
     */
    private WEB3EquityEstimatedPrice eqyityEstimatedPrice;

    /**
     * （株式注文訂正取消受付インタセプタ）<BR>
     * <BR>
     * コンストラクタ。<BR>
     */
    public WEB3EquityOrderRejectPersistenceInterceptor()
    {
        super();
    }

    /**
     * （株式注文訂正取消受付インタセプタ）<BR>
     * コンストラクタ。<BR>
     * <BR>
     * @@param l_hostEqtypeOrderAcceptParams - (株式注文受付キューParams)<BR>
     * 【株式注文受付キューテーブル】の１レコード。<BR>
     */
    public WEB3EquityOrderRejectPersistenceInterceptor(HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams)
    {
        this.hostEqtypeOrderAcceptParams = l_hostEqtypeOrderAcceptParams;
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
     * （注文状態が”発注失敗（変更注文）：NOT_MODIFIED” または <BR>
     * ”発注失敗（取消注文）：NOT_CANCELLED” の場合のみ処理を実施する。<BR>
     * 　@以外は、引数をそのまま返却し処理を終了する）<BR>
     * <BR>
     * 更新内容は、「株式訂正取消受付_株式注文単位テーブル.xls」の<BR>
     * 「（株式訂正取消受付[訂正受付エラー]）株式注文単位テーブル」シート、<BR>
     * 「（株式訂正取消受付[取消受付エラー]）株式注文単位テーブル」シート参照。<BR>
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
     * @@return EqtypeOrderUnitParams<BR>
     * @@roseuid 4035E715036B<BR>
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

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
        boolean l_blnStopOderSwitching = false;
        String l_strWLimitEnableStatusDiv = null;
        try
        {
            l_blnStopOderSwitching = l_orderManager.isStopOrderSwitching(l_orderUnit);
            l_strWLimitEnableStatusDiv =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
        }
        catch (WEB3BaseException l_exc)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }

        //（注文状態が”発注失敗（変更注文）：NOT_MODIFIED” 
        if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitParams.getOrderStatus()))
        {
            // 執行条件
            l_orderUnitParams.setExecutionConditionType(l_orderUnitParams.getConfirmedExecConditionType());
            
            // 値段条件
			l_orderUnitParams.setPriceConditionType(l_orderUnitParams.getConfirmedPriceConditionType());
 
            //ストップ注文切替中(*1)の場合、
            //　@(*1)拡張株式注文マネージャ.isストップ注文切替中()＝trueの場合、ストップ注文切替中。
            //　@引数に設定する注文単位には、更新前の注文単位を指定する
            //（EqtypeOrderUnitParamsを拡張株式注文マネージャ.toOrderUnit()にて注文単位型にする）
            if (l_blnStopOderSwitching)
            {
                //元発注条件 　@更新前の発注条件  以外、（既存値）
                l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());
                //元発注条件演算子 　@更新前の発注条件演算子   以外、　@（既存値）
                l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());
                //元逆指値基準値  更新前の逆指値基準値  以外、　@（既存値）
                if (l_orderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
                }
                //元（W指値）訂正指値  更新前の（W指値）訂正指値  以外、　@（既存値）
                if (l_orderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
                }
                //元（W指値）執行条件   更新前の（W指値）執行条件 以外、（既存値）
                l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());
                //（W指値）執行条件  　@null  以外、　@（既存値）
                l_orderUnitParams.setWLimitExecCondType(null);
                //発注条件 　@0：DEFAULT 以外、（既存値）
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
                //発注条件演算子  null 以外、（既存値）
                l_orderUnitParams.setOrderCondOperator(null);
                //逆指値基準値   null 以外、（既存値）
                l_orderUnitParams.setStopOrderPrice(null);
                //（W指値）訂正指値   null 以外、（既存値）
                l_orderUnitParams.setWLimitPrice(null);
                //概算代金計算結果がnull以外の場合
                if (this.eqyityEstimatedPrice != null)
                {
                    double l_dbCalcUnitPrice = this.eqyityEstimatedPrice.getCalcUnitPrice();
                    double l_dbEstimateDeliveryAmount = this.eqyityEstimatedPrice.getEstimateDeliveryAmount();
                    //注文単価 概算代金計算結果.get計算単価
                    l_orderUnitParams.setPrice(l_dbCalcUnitPrice);
                    // 概算受渡代金  概算代金計算結果.get概算受渡代金
                    l_orderUnitParams.setEstimatedPrice(l_dbEstimateDeliveryAmount);
                    //市場から確認済みの注文単価  概算代金計算結果.get計算単価  以外、（既存値)
                    l_orderUnitParams.setConfirmedOrderPrice(l_dbCalcUnitPrice);
                    //市場から確認済みの概算受渡代金  概算代金計算結果.get概算受渡代金  以外、（既存値)
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_dbEstimateDeliveryAmount);
                }
                //注文訂正・取消区分 D：W指値注文切替失敗
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);
                //リクエストタイプ 　@5:失効 以外、（既存値)
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }
            else
            {
                //注文訂正・取消区分 以外、8:訂正失敗
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGE_ERROR);
            }

            if (!l_blnStopOderSwitching || this.eqyityEstimatedPrice == null)
            {
                //注文単価  以外、　@市場から確認済みの注文単価
                if (l_orderUnitParams.getConfirmedOrderPriceIsNull())
                {
                    l_orderUnitParams.setPrice(null);
                }
                else
                {
                    l_orderUnitParams.setPrice(l_orderUnitParams.getConfirmedOrderPrice());
                }

                //概算受渡代金  以外、  　@市場から確認済みの概算受渡代金
                if (l_orderUnitParams.getConfirmedEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.getConfirmedEstimatedPrice());
                }
            }

            // 注文エラー理由コード
            l_orderUnitParams.setErrorReasonCode(
                this.hostEqtypeOrderAcceptParams.getErrorMessage());

            log.exiting(STR_METHOD_NAME);
            return l_orderUnitParams;
        }
        
        //”発注失敗（取消注文）：NOT_CANCELLED” の場合
        if (OrderStatusEnum.NOT_CANCELLED.equals(l_orderUnitParams.getOrderStatus()))
        {
            //リミット注文有効(*1)の場合
            //(*1)株式データアダプタ.getＷ指値用有効状態区分()＝"リミット注文有効"の場合、リミット注文有効。
            //　@引数に設定する注文単位には、更新前の注文単位を指定する
            //　@（EqtypeOrderUnitParamsを拡張株式注文マネージャ.toOrderUnit()にて注文単位型にする）
            if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
            {
                //　@元発注条件  更新前の発注条件  以外、（既存値）
                l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());
                //　@元発注条件演算子   　@更新前の発注条件演算子   以外、（既存値）
                l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());
                //　@元逆指値基準値   　@更新前の逆指値基準値     以外、（既存値）
                if (l_orderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
                }
                //　@元（W指値）訂正指値    更新前の（W指値）訂正指値    以外、（既存値）
                if (l_orderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
                }
                //　@元（W指値）執行条件  更新前の（W指値）執行条件   以外、（既存値）
                l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());
                //　@（W指値）執行条件   null   以外、（既存値）
                l_orderUnitParams.setWLimitExecCondType(null);
                //　@発注条件      　@0：DEFAULT     以外、（既存値）
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
                //　@発注条件演算子   null   以外、（既存値）
                l_orderUnitParams.setOrderCondOperator(null);
                //　@逆指値基準値    null   以外、（既存値）
                l_orderUnitParams.setStopOrderPrice(null);
                //　@（W指値）訂正指値   null   以外、（既存値）
                l_orderUnitParams.setWLimitPrice(null);
                //概算代金計算結果がnull以外の場合
                if (this.eqyityEstimatedPrice != null)
                {
                    double l_dbCalcUnitPrice = this.eqyityEstimatedPrice.getCalcUnitPrice();
                    double l_dbEstimateDeliveryAmount = this.eqyityEstimatedPrice.getEstimateDeliveryAmount();
                    //　@注文単価  概算代金計算結果.get計算単価  以外、（既存値）
                    l_orderUnitParams.setPrice(l_dbCalcUnitPrice);
                    //　@概算受渡代金  概算代金計算結果.get概算受渡代金
                    l_orderUnitParams.setEstimatedPrice(l_dbEstimateDeliveryAmount);
                    //　@市場から確認済みの注文単価  概算代金計算結果.get計算単価  以外、（既存値）
                    l_orderUnitParams.setConfirmedOrderPrice(l_dbCalcUnitPrice);
                    //　@市場から確認済みの概算受渡代金   概算代金計算結果.get概算受渡代金  以外、（既存値）
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_dbEstimateDeliveryAmount);
                }
                //リクエストタイプ  5:失効  以外、（既存値）
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }

            if (!l_blnStopOderSwitching || this.eqyityEstimatedPrice == null)
            {
                //概算受渡代金  以外、市場から確認済みの概算受渡代金
                if (l_orderUnitParams.getConfirmedEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.getConfirmedEstimatedPrice());
                }
            }
            
            // 注文訂正・取消区分
            l_orderUnitParams.setModifyCancelType(
                WEB3ModifyCancelTypeDef.CANCEL_ERROR);

            // 注文エラー理由コード
            l_orderUnitParams.setErrorReasonCode(
                this.hostEqtypeOrderAcceptParams.getErrorMessage());
               
            return l_orderUnitParams;
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
     * 引数の注文単位Params.注文ID、注文単位ＩＤに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@拡張項目セット<BR>
     * 　@○注文エラー理由コード<BR>
     * 　@　@[this.株式注文受付キューParams.注文受付結果 == ”注文受付完了”の場合]<BR>
     * 　@　@　@パラメータ.注文履歴Params.注文エラー理由コードに、”0000:正常”をセットする。<BR>
     * 　@　@[上記以外の場合]<BR>
     * 　@　@　@パラメータ.注文履歴Params.注文エラー理由コードに、注文単位オブジェクトの同名項目から値をセットする。<BR>
     * 　@<BR>
     * 　@○その他の項目　@　@　@　@　@　@　@　@　@　@　@　@<BR>
     * 　@パラメータ.注文履歴Paramsの拡張項目に、注文単位オブジェクトの同名項目から値をセットし、返却する。<BR>
     * <BR>
     * ３）　@訂正失敗の場合（パラメータ.注文履歴Params.注文状態=="発注失敗（変更注文）"の場合のみ、<BR>
     * 　@　@xTrade標準項目の更新仕様をカスタマイズする。<BR>
     * 　@　@※xTrade標準実装では、訂正失敗時には<BR>
     * 　@　@※訂正失敗処理を行う前の注文単位の値が設定されてしまうため。<BR>
     * <BR>
     * 　@　@注文単価（price）：　@注文単位.指値 をセット。<BR>
     * 　@　@注文数量（quantity）：　@注文単位の同項目の値をセット。<BR>
     * 　@　@市場と確認済の指値（confirmed_price）：　@注文単位の同項目の値をセット。<BR>
     * 　@　@市場と確認済の数量（confirmed_quantity）：　@注文単位の同項目の値をセット。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * EqTypeOrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （EqTypeOrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderActionParams - (株式注文履歴Params)<BR>
     * 株式注文履歴が保持する項目のパラメータクラス。<BR>
     * @@return EqtypeOrderActionParams
     * @@roseuid 4110B7CC004A
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
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderManager = (EqTypeOrderManager)l_tradingMod.getOrderManager();               
        EqTypeOrderUnit l_orderUnit = null;
        try 
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                    l_orderActionParams.getOrderUnitId());
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(l_ex.getMessage() , l_ex);
            throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
            this.getClass().getName() + "." + STR_METHOD_NAME);     
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        l_orderActionParams =
            super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_orderActionParams);
        
        if (WEB3AcceptStatusDef.OVER.equals(this.hostEqtypeOrderAcceptParams.getAcceptStatus()))
        {
            l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);    
        }
        else 
        {
            l_orderActionParams.setErrorReasonCode(l_orderUnitRow.getErrorReasonCode());    
        }
        
        if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderActionParams.getOrderStatus()))
        {
            l_orderActionParams.setPrice(l_orderUnitRow.getLimitPrice());
            l_orderActionParams.setQuantity(l_orderUnitRow.getQuantity());
            l_orderActionParams.setConfirmedPrice(l_orderUnitRow.getConfirmedPrice());
            l_orderActionParams.setConfirmedQuantity(l_orderUnitRow.getConfirmedQuantity());
        }
        
        return l_orderActionParams;
    }

    /**
     * (get概算代金計算結果)<BR>
     * 概算代金計算結果を取得する。<BR>
     * @@return WEB3EquityEstimatedPrice
     */
    public WEB3EquityEstimatedPrice getEquityEstimatedPrice()
    {
        return this.eqyityEstimatedPrice;
    }

    /**
     * (set概算代金計算結果)<BR>
     * 概算代金計算結果をセットする。<BR>
     * <BR>
     * [引数]<BR>
     * 　@引数を、プロパティ「概算代金計算結果」にセットする。<BR>
     * @@param l_equityEstimatedPrice - (概算代金計算結果)<BR>
     * 概算代金計算結果<BR>
     */
    public void setEquityEstimatedPrice(WEB3EquityEstimatedPrice l_equityEstimatedPrice)
    {
        this.eqyityEstimatedPrice = l_equityEstimatedPrice;
    }
}
@
