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
filename	WEB3AdminBondExecuteCommonInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者約定共通インタセプタ(WEB3AdminBondExecuteCommonInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
*/

package webbroker3.bd;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

/**
 * (債券管理者約定共通インタセプタ)<BR>
 * 債券管理者約定共通インタセプタクラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteCommonInterceptor extends WEB3AdminBondDefaultInterceptor 
{
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteCommonInterceptor.class);
    
    /**
     * (債券管理者約定共通インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D096E10021
     */
    public WEB3AdminBondExecuteCommonInterceptor() 
    {
     
    }
    
    /**
     * (（約定）更新値設定)<BR>
     * （mutateメソッドの実装）<BR> 
     * <BR>
     * 約定Paramsに拡張項目(*)設定し返却する。 <BR>
     *  <BR>
     * １）拡張項目セット<BR>
     *   パラメータ.約定Paramsの拡張項目に値をセットし、返却する。<BR>
     * 　@項目設定内容は<BR>
     * 　@「債券新規約定_債券約定テーブルDB更新仕様.xls」<BR>
     * 　@参照。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderExecutionParams
     * @@roseuid 44D9680F008C
     */
    public BondOrderExecutionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderExecutionParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderExecutionParams)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_params == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        long l_lngOrderUnitId = l_params.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondOrderManager l_orderManager =
            (WEB3BondOrderManager) l_tradingMod.getOrderManager();
        BondOrderUnit l_bondOrderUnit = null;
        
        // 注文単位オブジェクト取得
        l_bondOrderUnit = (BondOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);
        
        BondOrderUnitRow l_bondOrderUnitRow = 
            (BondOrderUnitRow) l_bondOrderUnit.getDataSourceObject();
        l_params.setDeliveryDate(l_bondOrderUnitRow.getDeliveryDate());
        l_params.setBizDate(l_bondOrderUnitRow.getBizDate());
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
