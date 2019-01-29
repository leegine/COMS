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
filename	WEB3AdminBondExecuteCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者約定取消更新インタセプタ(WEB3AdminBondExecuteCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
                 : 2006/10/12 齊珂   (中訊) WEBⅢ開発標準の見直しの対応（newBigDecimal部分）
*/

package webbroker3.bd;

import java.math.BigDecimal;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (債券管理者約定取消更新インタセプタ)<BR>
 * 債券管理者約定取消更新インタセプタクラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteCancelUpdateInterceptor extends WEB3AdminBondDefaultInterceptor 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteCancelUpdateInterceptor.class);
    
    /**
     * (拡張債券注文単位)<BR>
     * 拡張債券注文単位<BR>
     */
    private WEB3BondOrderUnit bondOrderUnit;
    
    /**
     * (債券管理者約定取消更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D96A5D038A
     */
    public WEB3AdminBondExecuteCancelUpdateInterceptor() 
    {
     
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * １）拡張項目セット<BR>
     * 　@パラメータ.債券注文単位Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 　@項目設定内容は 「債券約定取消_債券注文単位テーブルDB更新仕様.xls」参照。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44D96A5D03A9
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
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            if (WEB3DateUtility.compare(
                WEB3DateUtility.getDate(l_params.getBizDate(), "yyyyMMdd"),
                WEB3GentradeTradingTimeManagement.getOrderBizDate()) < 0)
            {
                //約定数量
                l_params.setExecutedQuantity(
                    new BigDecimal(String.valueOf(l_params.getExecutedQuantity())).add(
                        new BigDecimal(
                        	String.valueOf(this.getBondOrderUnit().getExecutedQuantity()))).doubleValue());
                
                //host送信区分
                l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);
            }
            else
            {
                //合計約定金額
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
                WEB3BondBizLogicProvider l_provider =
                    (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();
                BigDecimal l_bdQuantity = 
                	new BigDecimal(String.valueOf(this.getBondOrderUnit().getExecutedQuantity()));
                BigDecimal l_bdPrice = new BigDecimal(String.valueOf(this.getBondOrderUnit().getExecutedPrice()));
                BigDecimal l_bdTradePrice = 
                    l_provider.calcTradingPrice(l_bdQuantity, l_bdPrice, this.getBondProduct());
                if (l_bdTradePrice != null)
                {    
                    double l_dblTradePrice = l_bdTradePrice.doubleValue();
                    l_params.setExecutedAmount(l_params.getExecutedAmount() - l_dblTradePrice);
                }
                
                //host送信区分
                l_params.setHostSendDiv(WEB3HostSendDivDef.NOT_SEND);
            }
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
        
        //0：未約定
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.UNEXECUTED);
          
        //管理者.get管理者コード()
        l_params.setAdministratorCode(this.getAdministrator().getAdministratorCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (get拡張債券注文単位)<BR>
     * get拡張債券注文単位<BR>
     * <BR>
     * 　@拡張債券注文単位を返す<BR>
     * @@return webbroker3.bd.WEB3BondOrderUnit
     * @@roseuid 44E05DFB0372
     */
    public WEB3BondOrderUnit getBondOrderUnit() 
    {
        return this.bondOrderUnit;
    }
    
    /**
     * (set拡張債券注文単位)<BR>
     * set拡張債券注文単位<BR>
     * <BR>
     * 　@拡張債券注文単位をセットする<BR>
     * @@param l_bondOrderUnit - (拡張債券注文単位)<BR>
     * 拡張債券注文単位<BR>
     * @@roseuid 44E05E2D014F
     */
    public void setBondOrderUnit(WEB3BondOrderUnit l_bondOrderUnit) 
    {
        this.bondOrderUnit = l_bondOrderUnit;
    }
}
@
