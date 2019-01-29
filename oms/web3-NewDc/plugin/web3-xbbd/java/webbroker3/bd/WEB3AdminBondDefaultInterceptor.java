head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDefaultInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者デフォルトインタセプタ(WEB3AdminBondDefaultInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
Revesion History : 2007/07/25 劉立峰(中訊) 仕様変更モデルNO.240
*/

package webbroker3.bd;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

/**
 * (債券管理者デフォルトインタセプタ)<BR>
 * 債券管理者デフォルトインタセプタクラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondDefaultInterceptor implements BondOrderManagerPersistenceEventInterceptor
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDefaultInterceptor.class);
    
    /**
     * (管理者)<BR>
     * 管理者<BR>
     */
    private WEB3Administrator administrator;
    
    /**
     * (債券約定日情報)<BR>
     * 債券約定日情報<BR>
     */
    private WEB3BondExecuteDateInfo bondExecuteDateInfo;
    
    /**
     * (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果<BR>
     */
    private WEB3BondEstimatedPriceCalcResult estimatedPriceCalcResult;
    
    /**
     * (カストディアンコード)<BR>
     * カストディアンコード<BR>
     */
    private String custodianCode;
    
    /**
     * (債券銘柄)<BR>
     * 債券銘柄<BR>
     */
    private WEB3BondProduct bondProduct;
    
    /**
     * (債券管理者デフォルトインタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D878160295
     */
    public WEB3AdminBondDefaultInterceptor() 
    {
     
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * １）　@注文単位Paramsを返却する<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44D966030119
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderUnitParams l_params) 
    {
        //１）　@注文単位Paramsを返却する
        return l_params;
    }
    
    /**
     * (（注文履歴）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * １）　@拡張項目セット <BR>
     *   パラメータ.注文履歴Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 　@項目設定内容は<BR>
     * 　@「債券新規注文_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * 　@「債券注文受付_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * 　@「債券新規約定_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * 　@「債券約定取消_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * 　@「債券注文取消（未約定_外国債券）_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * 　@「債券注文取消（約定済_外国債券）_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * 　@「債券取消受付_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * 　@「債券注文取消（未約定_国内債券）_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * 　@「債券注文取消（約定済_国内債券）_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * 　@参照 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderActionParams
     * @@roseuid 44D8787D0018
     */
    public BondOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderActionParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderActionParams)";
        log.entering(STR_METHOD_NAME);
       
        if (l_params == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）拡張項目セット 
        long l_lngOrderUnitId = l_params.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondOrderManager l_orderManager = (WEB3BondOrderManager) l_tradingMod.getOrderManager();
        BondOrderUnit l_bondOrderUnit = null;
        
        // 注文単位オブジェクト取得
        l_bondOrderUnit = (BondOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);

        BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow) l_bondOrderUnit.getDataSourceObject();
        
        //債券注文単位テーブル.取引者ID
        if (!l_bondOrderUnitRow.getTraderIdIsNull())
        {
            l_params.setTraderId(l_bondOrderUnitRow.getTraderId());
        }
        
        //債券注文単位テーブル.取引
        l_params.setDealType(l_bondOrderUnitRow.getDealType());
        
        //債券注文単位テーブル.注文単価
        if (!l_bondOrderUnitRow.getPriceIsNull())
        {
            l_params.setPrice(l_bondOrderUnitRow.getPrice());
        }
       
        //債券注文単位テーブル.指値
        if (!l_bondOrderUnitRow.getLimitPriceIsNull())
        {
            l_params.setLimitPrice(l_bondOrderUnitRow.getLimitPrice());
        }
        
        //債券注文単位テーブル.約定日
        l_params.setExecDate(l_bondOrderUnitRow.getExecDate());
        
        //債券注文単位テーブル.現地約定日
        l_params.setForeignExecDate(l_bondOrderUnitRow.getForeignExecDate());
        
        //債券注文単位テーブル.受渡日
        l_params.setDeliveryDate(l_bondOrderUnitRow.getDeliveryDate());
        
        //債券注文単位テーブル.現地受渡日
        l_params.setForeignDeliveryDate(l_bondOrderUnitRow.getForeignDeliveryDate());
        
        //債券注文単位テーブル.入金日
        if (l_bondOrderUnitRow.getPaymentDateIsSet())
        {
            l_params.setPaymentDate(l_bondOrderUnitRow.getPaymentDate());
        }
        
        //債券注文単位テーブル.基準為替レート
        if (!l_bondOrderUnitRow.getBaseFxRateIsNull())
        {
            l_params.setBaseFxRate(l_bondOrderUnitRow.getBaseFxRate());
        }
        
        //債券注文単位テーブル.約定為替レート
        if (!l_bondOrderUnitRow.getExecFxRateIsNull())
        {
            l_params.setExecFxRate(l_bondOrderUnitRow.getExecFxRate());
        }
        
        //債券注文単位テーブル.売買代金（円貨）
        if (!l_bondOrderUnitRow.getTradingPriceIsNull())
        {
            l_params.setTradingPrice(l_bondOrderUnitRow.getTradingPrice());
        }
        
        //債券注文単位テーブル.売買代金（外貨）
        if (!l_bondOrderUnitRow.getForeignTradingPriceIsNull())
        {
            l_params.setForeignTradingPrice(l_bondOrderUnitRow.getForeignTradingPrice());
        }
        
        //債券注文単位テーブル.経過利子（円貨）
        if (!l_bondOrderUnitRow.getAccruedInterestIsNull())
        {
            l_params.setAccruedInterest(l_bondOrderUnitRow.getAccruedInterest());
        }
        
        //債券注文単位テーブル.経過利子（外貨）
        if (!l_bondOrderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_params.setForeignAccruedInterest(l_bondOrderUnitRow.getForeignAccruedInterest());
        }
        
        //債券注文単位テーブル.受渡代金（円貨）
        if (!l_bondOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_params.setEstimatedPrice(l_bondOrderUnitRow.getEstimatedPrice());
        }
        //債券注文単位テーブル.受渡代金（外貨）
        if (!l_bondOrderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_params.setForeignEstimatedPrice(l_bondOrderUnitRow.getForeignEstimatedPrice());
        }
        
        //債券注文単位テーブル.中途換金調整額
        if (!l_bondOrderUnitRow.getAdjustmentBeforeMaturityIsNull())
        {
            l_params.setAdjustmentBeforeMaturity(l_bondOrderUnitRow.getAdjustmentBeforeMaturity());
        }
        
        //債券注文単位テーブル.経過日数
        if (!l_bondOrderUnitRow.getElapsedDaysIsNull())
        {
            l_params.setElapsedDays(l_bondOrderUnitRow.getElapsedDays());
        }
        
        //債券注文単位テーブル.基準日数
        if (!l_bondOrderUnitRow.getCalcBaseDaysIsNull())
        {
            l_params.setCalcBaseDays(l_bondOrderUnitRow.getCalcBaseDays());
        }
        
        //債券注文単位テーブル.カストディアンコード
        l_params.setCustodianCode(l_bondOrderUnitRow.getCustodianCode());
        
        //債券注文単位テーブル.注文経路区分
        l_params.setOrderRootDiv(l_bondOrderUnitRow.getOrderRootDiv());
        
        //債券注文単位テーブル.管理者コード
        l_params.setAdministratorCode(l_bondOrderUnitRow.getAdministratorCode());
        
        //債券注文単位テーブル.注文エラー理由コード
        l_params.setErrorReasonCode(l_bondOrderUnitRow.getErrorReasonCode());
        
        //暫定對應：2006/09/27 徐大方 注文約定区分
        l_params.setOrderExecStatus(l_bondOrderUnitRow.getOrderExecStatus());
   
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (（約定）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * １）BondOrderExecutionParamsを返す<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderExecutionParams
     * @@roseuid 44D9601C002E
     */
    public BondOrderExecutionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderExecutionParams l_params) 
    {
        //１）BondOrderExecutionParamsを返す
        return l_params;
    }
    
    /**
     * nullを返す。<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_class - (arg1)<BR>
     * @@roseuid 44D8790D031A
     */
    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType l_persistenceType, Class l_class)
    {
        return null;
    }
    
    /**
     * (get管理者)<BR>
     * 管理者を返却する<BR>
     * @@return WEB3Administrator
     * @@roseuid 44D8780A00CF
     */
    public WEB3Administrator getAdministrator() 
    {
        return this.administrator;
    }
    
    /**
     * (set管理者)<BR>
     * 管理者をセットする<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者<BR>
     * @@roseuid 44D8780A00C0
     */
    public void setAdministrator(WEB3Administrator l_administrator) 
    {
        this.administrator = l_administrator;
    }
    
    /**
     * (get債券約定日情報)<BR>
     * 債券約定日情報を返却する<BR>
     * @@return WEB3BondExecuteDateInfo
     * @@roseuid 44D965650000
     */
    public WEB3BondExecuteDateInfo getBondExecuteDateInfo() 
    {
        return this.bondExecuteDateInfo;
    }
    
    /**
     * (set債券約定日情報)<BR>
     * 債券約定日情報を設定する<BR>
     * @@param l_bondExecuteDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報<BR>
     * @@roseuid 44D96565002E
     */
    public void setBondExecuteDateInfo(WEB3BondExecuteDateInfo l_bondExecuteDateInfo) 
    {
        this.bondExecuteDateInfo = l_bondExecuteDateInfo;
    }
    
    /**
     * (set債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果を設定する<BR>
     * @@param l_estimatedPriceCalcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果オブジェクト<BR>
     * @@roseuid 44D96565005D
     */
    public void setBondEstimatedPriceCalcResult(WEB3BondEstimatedPriceCalcResult l_estimatedPriceCalcResult) 
    {
        this.estimatedPriceCalcResult = l_estimatedPriceCalcResult;
    }
    
    /**
     * (get債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果を返却する<BR>
     * @@return WEB3BondEstimatedPriceCalcResult
     * @@roseuid 44D96565007D
     */
    public WEB3BondEstimatedPriceCalcResult getBondEstimatedPriceCalcResult() 
    {
        return this.estimatedPriceCalcResult;
    }
    
    /**
     * (getカストディアンコード)<BR>
     * カストディアンコードを返却する<BR>
     * @@return String
     * @@roseuid 44D9656500BB
     */
    public String getCustodianCode() 
    {
        return this.custodianCode;
    }
    
    /**
     * (setカストディアンコード)<BR>
     * カストディアンコードを設定する<BR>
     * @@param l_strCustodianCode - (カストディアンコード)<BR>
     * カストディアンコード<BR>
     * @@roseuid 44D9656500DA
     */
    public void setCustodianCode(String l_strCustodianCode) 
    {
        this.custodianCode = l_strCustodianCode;
    }
    
    /**
     * (get債券銘柄)<BR>
     * 債券銘柄を返却する<BR>
     * @@return String
     * @@roseuid 44D965970399
     */
    public WEB3BondProduct getBondProduct() 
    {
        return this.bondProduct;
    }
    
    /**
     * (set債券銘柄)<BR>
     * 債券銘柄を設定する<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@roseuid 44D9659703C8
     */
    public void setBondProduct(WEB3BondProduct l_bondProduct) 
    {
        this.bondProduct = l_bondProduct;
    }
}
@
