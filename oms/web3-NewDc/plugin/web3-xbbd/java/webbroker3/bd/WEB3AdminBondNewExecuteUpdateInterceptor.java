head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondNewExecuteUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者新規約定更新インタセプタ(WEB3AdminBondNewExecuteUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
                   2006/10/12 齊珂   (中訊) WEBⅢ開発標準の見直しの対応（newBigDecimal部分）
*/

package webbroker3.bd;

import java.math.BigDecimal;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (債券管理者新規約定更新インタセプタ)<BR>
 * 債券管理者新規約定更新インタセプタクラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondNewExecuteUpdateInterceptor extends WEB3AdminBondExecuteCommonInterceptor 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondNewExecuteUpdateInterceptor.class);
    
    /**
     * (債券管理者新規約定更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44CCA6E502CD
     */
    public WEB3AdminBondNewExecuteUpdateInterceptor() 
    {
     
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     *  <BR>
     * １）拡張項目セット<BR>
     * 　@パラメータ.債券注文単位Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 　@項目設定内容は　@「債券新規約定_債券注文単位テーブルDB更新仕様.xls」<BR>
     * 　@参照。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44CCA2770203
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params) 
    {
        final String STR_METHOD_NAME = 
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
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
        //（既存値）+ 計算サービス.get売買代金（数量,単価,債券銘柄）
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondBizLogicProvider l_provider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();
        BigDecimal l_bdQuantity = this.getBondEstimatedPriceCalcResult().getQuantity();
        BigDecimal l_bdPrice = this.getBondEstimatedPriceCalcResult().getPrice();
        BigDecimal l_bdtemp;
        try
        {
            l_bdtemp = 
                l_provider.calcTradingPrice(l_bdQuantity, l_bdPrice, this.getBondProduct());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);  
        }
        l_params.setExecutedAmount(
            new BigDecimal(String.valueOf(l_params.getExecutedAmount())).add(l_bdtemp).doubleValue());
        
        //1：約定済
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.EXECUTED);
        
        //基準為替レート
        if (this.getBondEstimatedPriceCalcResult().getFxRate() != null)
        {
            l_params.setExecFxRate(
                this.getBondEstimatedPriceCalcResult().getFxRate().doubleValue());
        }
        else 
        {
            l_params.setExecFxRate(null);
        }
        
        //市場から確認済みの指値
        if (this.getBondEstimatedPriceCalcResult().getPrice() != null)
        {
            l_params.setExecutedPrice(
                this.getBondEstimatedPriceCalcResult().getPrice().doubleValue());
        }
        else 
        {
            l_params.setExecutedPrice(null);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
