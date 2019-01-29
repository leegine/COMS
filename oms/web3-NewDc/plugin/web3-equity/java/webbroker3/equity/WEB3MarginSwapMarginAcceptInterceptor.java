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
filename	WEB3MarginSwapMarginAcceptInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用現引現渡受付インタセプタ(WEB3MarginSwapMarginAcceptInterceptor.java)
Author Name      : 2004/9/2４ 盧法@旭(中訊) 新規作成
                 : 2004/11/2 法@旭　@修正   
                 : 2004/12/09 水落（SRA）　@残案件対応
                 : 2004/12/28 岡村（SRA）　@JavaDoc修正
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
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

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.equity.data.HostEqtypeSwapAcceptParams;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用現引現渡受付インタセプタ）。<BR>
 * <BR>
 * 現引現渡注文受付時の、DB更新仕様カスタマイズ用のクラス。<BR>
 *（EqTypeOrderManagerPersistenceEventInterceptorの実装）
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginAcceptInterceptor.class);   
    /**
     * （現引現渡受付キューParams）。<BR>
     * <BR>
     * 【現引現渡受付キューテーブル】の１レコード。<BR>
     * 信用取引現引現渡注文通知サービスからコールする場合は、<BR>
     * nullがセットされる。
     */
    private HostEqtypeSwapAcceptParams hostEqtypeSwapAcceptParams ;
    
    /**
     * @@roseuid 4142BBF50106
     */
    public WEB3MarginSwapMarginAcceptInterceptor() 
    {
     
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
     * ※注文状態が<BR>
     * 　@”発注済（新規注文）：ORDERED” または<BR>
     * 　@”発注失敗（新規注文）：NOT_ORDERED” または<BR>
     * 　@”発注失敗（取消注文）：NOT_CANCELLED” の場合のみ処理を実施する。<BR>
     * 　@以外は、引数をそのまま返却し処理を終了する。<BR>
     * <BR>
     * ○注文状態が”発注済（新規注文）：ORDERED”の場合の更新内容は、<BR>
     * 　@「（注文受付）現引現渡受付_株式注文単位テーブル.xls」の<BR>
     * 　@「（現引現渡注文受付）[受付OK]株式注文単位テーブル」シートを参照。<BR>
     * <BR>
     * ○注文状態が”発注失敗（新規注文）：NOT_ORDERED”の場合の更新内容は、<BR>
     * 　@「（注文受付）現引現渡受付_株式注文単位テーブル.xls」の<BR>
     * 　@「（現引現渡注文受付）[受付エラー]株式注文単位テーブル」を参照。<BR>
     * <BR>
     * ○注文状態が”発注失敗（取消注文）：NOT_CANCELLED”の場合の更新内容は、<BR>
     * 　@「（取消受付）現引現渡受付_株式注文単位テーブル.xls」の<BR>
     * 　@「（現引現渡取消受付）[取消受付エラー]株式注文単位テーブル」シート参照。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。
     * @@param l_process - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。
     * @@return EqtypeOrderUnitParams
     * @@roseuid 410DA99F0023
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
        
        OrderStatusEnum l_orderStatus = l_orderUnitParams.getOrderStatus();
        //（現引現渡注文受付）[受付OK]株式注文単位テーブル」シート
        if (OrderStatusEnum.ORDERED.equals(l_orderStatus))
        {
            //市場から確認済みの注文単価
            double l_dblPrice = l_orderUnitParams.getPrice();
            if (l_orderUnitParams.getPriceIsNull())
            {
                l_orderUnitParams.setConfirmedOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedOrderPrice(l_dblPrice);
            }

            double l_dblEstimatePrice = l_orderUnitParams.getEstimatedPrice();
            //市場から確認済みの概算受渡代金
            if (l_orderUnitParams.getEstimatedPriceIsNull())
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(l_dblEstimatePrice);
            }

            //市場から確認済みの執行条件
            EqTypeExecutionConditionType l_type = l_orderUnitParams.getExecutionConditionType();
            if (l_type == null)
            {
                l_orderUnitParams.setConfirmedExecConditionType(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedExecConditionType(l_type);
            }
            
            //市場から確認済みの値段条件            
            l_orderUnitParams.setConfirmedPriceConditionType(
                l_orderUnitParams.getPriceConditionType());
        }
        //現引現渡注文受付）[受付エラー]株式注文単位テーブル
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderStatus))
        {
            l_orderUnitParams.setErrorReasonCode(
                this.hostEqtypeSwapAcceptParams.getErrorMessage());
        }
        //注文状態が”発注失敗（取消注文）：NOT_CANCELLED”の場合
        else if (OrderStatusEnum.NOT_CANCELLED.equals(l_orderStatus))
        {
            // 概算受渡代金
            if (l_orderUnitParams.getConfirmedEstimatedPriceIsNull())
            {
                l_orderUnitParams.setEstimatedPrice(null);    
            }
            else
            {
                l_orderUnitParams.setEstimatedPrice(
                    l_orderUnitParams.getConfirmedEstimatedPrice());
            }
            
            //注文訂正・取消区分
            l_orderUnitParams.setModifyCancelType(
                WEB3ModifyCancelTypeDef.CANCEL_ERROR);
            
            //注文エラー理由コード
            l_orderUnitParams.setErrorReasonCode(
                this.hostEqtypeSwapAcceptParams.getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * （信用現引現渡受付インタセプタ）。<BR>
     * <BR>
     * コンストラクタ。<BR>
     * 引数に指定されたオブジェクトを、同名のプロパティに設定する。<BR>
     * @@param l_swapMarginAcceptCueParams - 現引現渡受付キューParams<BR>
     * 　@　@　@【現引現渡受付キューテーブル】の１レコード。<BR>
     * @@return webbroker3.margin.WEB3MarginSwapMarginAcceptInterceptor
     * @@roseuid 410DA99F0032
     */
    public WEB3MarginSwapMarginAcceptInterceptor(HostEqtypeSwapAcceptParams l_hostEqtypeSwapAcceptParams) 
    {
        this.hostEqtypeSwapAcceptParams = l_hostEqtypeSwapAcceptParams;
    }
    
    /**
     * （更新値設定）。<BR>
     * <BR>
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
     * 　@　@[this.現引現渡受付キューParams == null の場合（＝注文通知からのコール時）]、または<BR>
     * 　@　@[this.現引現渡受付キューParams.注文受付結果 == ”注文受付完了”の場合]<BR>
     * 　@　@　@パラメータ.注文履歴Params.注文エラー理由コードに、”0000:正常”をセットする。<BR>
     * 　@　@[上記以外の場合]<BR>
     * 　@　@　@パラメータ.注文履歴Params.注文エラー理由コードに、注文単位オブジェクトの同名項目から<BR>
     * 　@　@　@値をセットする。<BR>
     * <BR>
     * 　@○その他の項目<BR>　@　@　@　@　@　@　@　@　@　@
     * 　@パラメータ.注文履歴Paramsの拡張項目に、注文単位オブジェクトの同名項目から値をセットし、<BR>
     * 　@返却する。
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
     * @@roseuid 4110C514004A
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_orderActionParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderMgr = (EqTypeOrderManager) l_tradingMod.getOrderManager();
        EqTypeOrderUnit l_orderUnit = null;
        try 
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderMgr.getOrderUnit(
                    l_orderActionParams.getOrderUnitId());
        } 
        catch (NotFoundException l_ex) 
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        l_orderActionParams =
            super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_orderActionParams);
        
        if (this.hostEqtypeSwapAcceptParams == null ||
            WEB3AcceptStatusDef.OVER.equals(this.hostEqtypeSwapAcceptParams.getAcceptStatus()))
        {
            l_orderActionParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.NORMAL);
        }
        else
        {
            l_orderActionParams.setErrorReasonCode(
                l_orderUnitRow.getErrorReasonCode());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
}
@
