head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者約定更新インタセプタ(WEB3AdminBondExecuteUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
                 : 2006/10/12 齊珂   (中訊) WEBⅢ開発標準の見直しの対応（newBigDecimal部分）
                 : 2006/10/16  張騰宇 (中訊) ＤＢ更新仕様No.019
*/

package webbroker3.bd;

import java.math.BigDecimal;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (債券管理者約定更新インタセプタ)<BR>
 * 債券管理者約定更新インタセプタクラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteUpdateInterceptor extends WEB3AdminBondExecuteCommonInterceptor 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteUpdateInterceptor.class);
    
    /**
     * (債券管理者約定更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D969AD0157
     */
    public WEB3AdminBondExecuteUpdateInterceptor() 
    {
     
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * １）拡張項目セット<BR>
     * 　@パラメータ.債券注文単位Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 　@項目設定内容は　@「債券約定_債券注文単位テーブルDB更新仕様.xls」<BR>
     * 　@参照。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44D969AD0138
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType, 
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_params == null)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        
        //債券約定日情報.get受渡日
        l_params.setDeliveryDate(this.getBondExecuteDateInfo().getDeliveryDate());
        
        //債券約定日情報.get現地受渡日
        l_params.setForeignDeliveryDate(this.getBondExecuteDateInfo().getForeignDeliveryDate());
        
        //（既存値）+ 計算サービス.calc売買代金（数量,単価,債券銘柄）
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondBizLogicProvider l_provider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_params.getExecutedQuantity()));
        BigDecimal l_bdPrice = this.getBondEstimatedPriceCalcResult().getPrice();
        BigDecimal l_bdtemp = null;
        try
        {
            l_bdtemp = l_provider.calcTradingPrice(l_bdQuantity, l_bdPrice, this.getBondProduct());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);  
        }
        if (l_bdtemp != null)
        {
            l_params.setExecutedAmount(
                new BigDecimal(String.valueOf(l_params.getExecutedAmount())).add(l_bdtemp).doubleValue());
            
            //債券受渡代金計算結果.get単価
            l_params.setExecutedPrice(l_bdtemp.doubleValue());
        }
        
        //1：約定済
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.EXECUTED);
                
        //債券受渡代金計算結果.get為替レート
        if (this.getBondEstimatedPriceCalcResult().getFxRate() != null)
        {
            BigDecimal l_bdFxRate = this.getBondEstimatedPriceCalcResult().getFxRate();
            l_params.setExecFxRate(l_bdFxRate.doubleValue());
        }
        
        //約定単価
        if (this.getBondEstimatedPriceCalcResult().getPrice() != null)
        {
            BigDecimal l_bdExecutedPrice = this.getBondEstimatedPriceCalcResult().getPrice();
            l_params.setExecutedPrice(l_bdExecutedPrice.doubleValue());
        }
        
        //債券受渡代金計算結果.get売買代金（円貨）
        if (this.getBondEstimatedPriceCalcResult().getTradingPrice() != null)
        {
            BigDecimal l_bdTradingPrice = this.getBondEstimatedPriceCalcResult().getTradingPrice();
            l_params.setTradingPrice(l_bdTradingPrice.doubleValue());
        }
        
        //債券受渡代金計算結果.get売買代金（外貨）
        if (this.getBondEstimatedPriceCalcResult().getForeignTradePrice() != null)
        {
            BigDecimal l_bdForeignTradingPrice = 
                this.getBondEstimatedPriceCalcResult().getForeignTradePrice();
            l_params.setForeignTradingPrice(l_bdForeignTradingPrice.doubleValue());
        }
        
        //債券受渡代金計算結果.get経過利子（円貨）
        if (this.getBondEstimatedPriceCalcResult().getAccruedInterest() != null)
        {
            BigDecimal l_bdAccruedInterest = 
                this.getBondEstimatedPriceCalcResult().getAccruedInterest();
            l_params.setAccruedInterest(l_bdAccruedInterest.doubleValue());
        }
        
        //債券受渡代金計算結果.get経過利子（外貨）
        if (this.getBondEstimatedPriceCalcResult().getForeignAccruedInterest() != null)
        {
            BigDecimal l_bdForeignAccruedInterest = 
                this.getBondEstimatedPriceCalcResult().getForeignAccruedInterest();
            l_params.setForeignAccruedInterest(l_bdForeignAccruedInterest.doubleValue());
        }
        
        //債券受渡代金計算結果.get受渡代金（円貨）
        if (this.getBondEstimatedPriceCalcResult().getEstimatedPrice() != null)
        {
            BigDecimal l_bdEstimatedPrice = 
                this.getBondEstimatedPriceCalcResult().getEstimatedPrice();
            l_params.setEstimatedPrice(l_bdEstimatedPrice.doubleValue());
        }
        
        //債券受渡代金計算結果.get受渡代金（外貨）
        if (this.getBondEstimatedPriceCalcResult().getForeignEstimatedPrice() != null)
        {
            BigDecimal l_bdForeignEstimatedPrice = 
                this.getBondEstimatedPriceCalcResult().getForeignEstimatedPrice();
            l_params.setForeignEstimatedPrice(l_bdForeignEstimatedPrice.doubleValue());
        }
        
        //債券受渡代金計算結果.get経過日数
        if (this.getBondEstimatedPriceCalcResult().getElapsedDays() != null)
        {
            l_params.setElapsedDays(this.getBondEstimatedPriceCalcResult().getElapsedDays());
        }
        else
        {
            l_params.setElapsedDays(null);
        }
        
        //債券受渡代金計算結果.get基準日数
        if (this.getBondEstimatedPriceCalcResult().getCalcBaseDays() != null)
        {
            l_params.setCalcBaseDays(this.getBondEstimatedPriceCalcResult().getCalcBaseDays());
        }
        else
        {
            l_params.setCalcBaseDays(null);
        }
        
        //債券約定日情報.get約定日
        l_params.setExecDate(this.getBondExecuteDateInfo().getExecuteDate());
        
        //債券約定日情報.get現地約定日
        l_params.setForeignExecDate(this.getBondExecuteDateInfo().getForeignExecuteDate());
        
        //債券約定日情報.get入金日
        l_params.setPaymentDate(this.getBondExecuteDateInfo().getPaymentDate());
        
        //インタセプタ.getカストディアンコード
        l_params.setCustodianCode(this.getCustodianCode());
        
        //0：未送信
        l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);
        
        //管理者.get管理者コード
        l_params.setAdministratorCode(this.getAdministrator().getAdministratorCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
