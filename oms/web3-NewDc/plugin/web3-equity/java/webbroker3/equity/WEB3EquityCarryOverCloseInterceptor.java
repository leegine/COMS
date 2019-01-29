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
filename	WEB3EquityCarryOverCloseInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文繰越失効インタセプタ(WEB3EquityCarryOverCloseInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 趙林 (中訊) 新規作成
                 : 2004/11/8  法@旭　@修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/07/19 李　@俊　@(中訊)　@ 仕様変更　@モデル955
*/
package webbroker3.equity;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

/**
 * （株式注文繰越失効インタセプタ）。<BR>
 * <BR>
 * 繰越時失効処理のDB更新仕様カスタマイズ用クラス<BR>
 *（繰越エラー時に、繰越エラーの原因の記録を行う。）
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityCarryOverCloseInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityCarryOverCloseInterceptor.class);

    /**
     * (注文エラー理由コード) <BR>
     */
    private String orderErrReasonCd;

    /**
     * (コンストラクタ。) <BR>
     * <BR>
     * 引数の注文エラー理由コードを、プロパティに設定する。 <BR>
     * @@param l_strOrderErrReasonCd - 注文エラー理由コード <BR>
     * @@return WEB3EquityCarryOverCloseInterceptor
     * @@roseuid 4085D1600307
     */
    public WEB3EquityCarryOverCloseInterceptor(String l_strOrderErrReasonCd)
    {
        this.orderErrReasonCd = l_strOrderErrReasonCd;
    }

    /**
     * (更新値設定) <BR>
     * （mutateメソッドの実装） <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。 <BR>
     * <BR>
     * １） 拡張項目セット <BR>
     * 更新内容は、「注文繰越_株式注文単位テーブル.xls」の <BR>
     * 「（注文繰越[失効]）注文単位テーブル」シート参照。 <BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ) <BR>
     * INSERTまたは、UPDATE。 <BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。 <BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (処理) <BR>
     * （OrderManagerPersistenceContextにて定数定義） <BR>
     * @@param l_eqtypeOrderUnitParams - (注文単位Params) <BR>
     * 株式注文単位が保持する項目のパラメータクラス。 <BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 4085D0B801CF
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams) ";
        
        log.entering(STR_METHOD_NAME);
        if(l_eqtypeOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_eqtypeOrderUnitParams.setErrorReasonCode(this.orderErrReasonCd);

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
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
     * 引数の注文単位Params.注文ID、<BR>
     * 注文単位ＩＤに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@super.mutate(EqtypeOrderActionParams)をコールする。<BR>
     * ３）　@拡張項目セット<BR>
     * 　@○注文エラー理由コード<BR>
     * 　@　@[this.注文エラー理由コード != nullの場合]<BR>
     * 　@　@　@パラメータ.注文履歴Params.注文エラー理由コードに<BR>
     * this.注文エラー理由コードをセットする。<BR>
     * 　@　@[上記以外の場合]<BR>
     * 　@　@　@パラメータ.注文履歴Params.注文エラー理由コードに<BR>
     * ”0000:正常”をセットする。<BR>
     * 　@<BR>
     * 　@○その他の項目　@　@<BR>　@　@　@　@　@　@　@　@　@　@
     * 　@パラメータ.注文履歴Paramsの拡張項目に、<BR>
     * 注文単位オブジェクトの同名項目から値をセットし、返却する。<BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * 
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@param l_eqtypeOrderActionParams - (株式注文履歴Params)<BR>
     * 株式注文履歴が保持する項目のパラメータクラス。
     * @@return EqtypeOrderActionParams
     * @@roseuid 4110B6F7003B
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType, 
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_eqtypeOrderActionParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        // 注文単位取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        OrderManager l_orderMgr = l_tradingMod.getOrderManager();
        EqtypeOrderUnitRow l_orderUnitRow = null;

        try
        {
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(l_eqtypeOrderActionParams.order_unit_id);
            l_orderUnitRow = (EqtypeOrderUnitParams) l_orderUnit.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }
        
        //super.mutate(EqtypeOrderActionParams)をコールする。
        l_eqtypeOrderActionParams =
            super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_eqtypeOrderActionParams);
        
        // 取引者ＩＤ（注文単位テーブル．取引者ＩＤ）
        if (l_orderUnitRow.getTraderIdIsNull())
        {
            l_eqtypeOrderActionParams.setTraderId(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setTraderId(l_orderUnitRow.getTraderId());
        }
        
        // 値段条件（注文単位テーブル）
        l_eqtypeOrderActionParams.setPriceConditionType(l_orderUnitRow.getPriceConditionType());
        
        // 発注条件 (注文単位テーブル.発注条件)
        l_eqtypeOrderActionParams.setOrderConditionType(
            l_orderUnitRow.getOrderConditionType()
            );

        // 発注条件演算子 (注文単位テーブル.発注条件演算子)
        l_eqtypeOrderActionParams.setOrderCondOperator(
            l_orderUnitRow.getOrderCondOperator()
            );

        //逆指値基準値
        if (l_orderUnitRow.getStopOrderPriceIsNull())
        {
            l_eqtypeOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setStopOrderPrice(
                l_orderUnitRow.getStopOrderPrice());
        }

        //（W指値）訂正指値
        if (l_orderUnitRow.getWLimitPriceIsNull())
        {
            l_eqtypeOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setWLimitPrice(
            l_orderUnitRow.getWLimitPrice());
        }

        // 注文失効日付 (注文単位テーブル.注文失効日付)
        l_eqtypeOrderActionParams.setExpirationDate(
            l_orderUnitRow.getExpirationDate()
            );

        // 概算受渡代金 (株式注文単位テーブル.概算受渡代金)
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_eqtypeOrderActionParams.setEstimatedPrice(null);    
        }
        else
        {
            l_eqtypeOrderActionParams.setEstimatedPrice(
                l_orderUnitRow.getEstimatedPrice()
                );    
        }


        // 注文訂正・取消区分 (株式注文単位テーブル.注文訂正・取消区分)
        l_eqtypeOrderActionParams.setModifyCancelType(
            l_orderUnitRow.getModifyCancelType()
            );

        // 注文経路区分（注文単位テーブル．注文経路区分）
        l_eqtypeOrderActionParams.setOrderRootDiv(
            l_orderUnitRow.getOrderRootDiv());

        // 決済順序 (株式注文単位テーブル.決済順序)
        l_eqtypeOrderActionParams.setClosingOrderType(
            l_orderUnitRow.getClosingOrderType()
            );

        // 注文エラー理由コード (株式注文単位テーブル.注文エラー理由コード)
        if (this.orderErrReasonCd != null)
        {
            l_eqtypeOrderActionParams.setErrorReasonCode(this.orderErrReasonCd);
        }
        else
        {    
            l_eqtypeOrderActionParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.NORMAL
                );
        }
        // リクエストタイプ (株式注文単位テーブル.リクエストタイプ)
        l_eqtypeOrderActionParams.setRequestType(
            l_orderUnitRow.getRequestType()
            );
        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderActionParams;
    }
}
@
