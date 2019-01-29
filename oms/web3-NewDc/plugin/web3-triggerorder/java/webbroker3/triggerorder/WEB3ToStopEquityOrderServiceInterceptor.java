head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 逆指値注文株式発注更新インタセプタ(WEB3ToStopEquityOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/12 大澤　@喜宗@(SRA) 新規作成
*/
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3MarginUpdateEventInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * (逆指値注文株式発注更新インタセプタ)<BR>
 * Eqtype逆指値発注処理のDB更新仕様カスタマイズ用クラス<BR>
 * @@author 大澤　@喜宗@
 * @@version 1.0
 */
public class WEB3ToStopEquityOrderServiceInterceptor extends WEB3MarginUpdateEventInterceptor 
{    
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopEquityOrderServiceInterceptor.class);
    
    /**
     * 注文エラー理由コード<BR> 
     */
    private String errorCode = null;
    
    /**
     * （コンストラクタ）<BR>
     * 引数の注文エラー理由コードを、プロパティに設定する。
     * @@param l_errorCode
     */
    public WEB3ToStopEquityOrderServiceInterceptor(String l_errorCode)
    {
        errorCode = l_errorCode;
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
     * 更新内容は、「逆指値注文発注(NG)_株式注文単位テーブル.xls」参照。<BR>
     * <BR>
     * @@param l_updateType - 更新タイプ<BR>
     * INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_process - 処理<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderUnitParams - 注文単位Params<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //注文エラー理由コード
        l_orderUnitParams.setErrorReasonCode(this.getErrorReasonCode());
        //リクエストタイプ
        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.ORDER_FAILURE);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * （更新値設定）<BR>
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
     * 　@更新内容は、「逆指値注文発注(NG)_株式注文履歴テーブル.xls」参照。<BR> 
     * <BR>
     * @@param l_updateType - 更新タイプ<BR>
     * INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_process - 処理<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderActionParams - 株式注文履歴Params<BR>
     * 株式注文履歴が保持する項目のパラメータクラス。<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process,
        EqtypeOrderActionParams l_orderActionParams)
    {
        final String STR_METHOD_NAME = "mutate";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderActionParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        long l_lngOrderUnitId = l_orderActionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager) l_tradingMod.getOrderManager();                
        EqTypeOrderUnit l_orderUnit;
        
        try 
        {
            l_orderUnit = (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_lngOrderUnitId);
        } 
        catch (NotFoundException e) 
        {
            log.error(STR_METHOD_NAME, e);
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //取引者ＩＤ
        if (l_row.getTraderIdIsNull())
        {
            l_orderActionParams.setTraderId(null);
        }
        else
        {
            l_orderActionParams.setTraderId(l_row.getTraderId());
        }
        //値段条件
        l_orderActionParams.setPriceConditionType(l_row.getPriceConditionType());
        //発注条件
        l_orderActionParams.setOrderConditionType(l_row.getOrderConditionType());
        //発注条件演算子
        l_orderActionParams.setOrderCondOperator(l_row.getOrderCondOperator());
        //逆指値基準値
        if (l_row.getStopOrderPriceIsNull())
        {
            l_orderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_orderActionParams.setStopOrderPrice(l_row.getStopOrderPrice());
        }
        //（W指値）訂正指値
        if (l_row.getWLimitPriceIsNull())
        {
            l_orderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setWLimitPrice(l_row.getWLimitPrice());
        }
        //注文失効日付
        l_orderActionParams.setExpirationDate(l_row.getExpirationDate());
        //概算受渡代金
        if (l_row.getEstimatedPriceIsNull())
        {
            l_orderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_orderActionParams.setEstimatedPrice(l_row.getEstimatedPrice());
        }
        //注文訂正・取消区分
        l_orderActionParams.setModifyCancelType(l_row.getModifyCancelType());
        //注文経路区分
        l_orderActionParams.setOrderRootDiv(l_row.getOrderRootDiv());
        //決済順序区分
        l_orderActionParams.setClosingOrderType(l_row.getClosingOrderType());
        //注文エラー理由コード
        l_orderActionParams.setErrorReasonCode(this.getErrorReasonCode());
        //リクエストタイプ
        l_orderActionParams.setRequestType(l_row.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
    
    /**
     * （get注文エラー理由コード）<BR>
     * this.注文エラー理由コードを返却する。<BR>
     * <BR>
     * @@return String
     */
    public String getErrorReasonCode()
    {
        return this.errorCode;
    }    
}
@
