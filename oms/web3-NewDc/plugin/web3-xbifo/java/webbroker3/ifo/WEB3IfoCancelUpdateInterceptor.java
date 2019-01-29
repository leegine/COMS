head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP取消更新インタセプタクラス(WEB3IfoCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/15 艾興 (中訊) 新規作成
Revesion History : 2007/01/29 趙林鵬 (中訊) ＤＢ更新仕様No.141,No.154
*/
package webbroker3.ifo;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP取消更新インタセプタ)<BR>
 * 先物OP取消更新インタセプタクラス<BR>
 * @@author  艾興
 * @@version 1.0
 */
public class WEB3IfoCancelUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoCancelUpdateInterceptor.class);
    
    /**
     * @@roseuid 40C07C0C00EA
     */
    
    /**
     * (先物OP取消更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.ifo.WEB3IfoCancelUpdateInterceptor
     * @@roseuid 407E828F0177
     */
    public WEB3IfoCancelUpdateInterceptor() 
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
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、<BR>・
     * 却する。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 注文単位.先物／オプション区分 == "オプション"の場合 <BR>
     * 「OP取消_注文単位テーブル仕様.xls」参照。<BR> 
     * 注文単位.先物／オプション区分 == "先物"の場合 <BR> 
     * 「先物取消_注文単位テーブル仕様.xls」参照。<BR> 
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * 
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_ifoOrderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 407E7D25038A
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams) 
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "IfoOrderUnitParams l_ifoOrderUnitParams)";

        log.entering(STR_METHOD_NAME);
        if(l_ifoOrderUnitParams == null)
        {        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        // 取引者ID
        // インタセプタ.取引者ID
        if (traderId == 0)
        {   
            l_ifoOrderUnitParams.setTraderId(null);
        }
        else
        {
            l_ifoOrderUnitParams.setTraderId(traderId);
        }
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);

        // 注文訂正・取消区分
        if(l_ifoOrderUnitParams.getConfirmedOrderPriceIsNull())
        {
            l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);
        }
        else
        {
            l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELING);
        }

        // 注文経路区分
        String l_orderRootDiv ="";
        String l_orderRootDivString = WEB3SessionAttributeDef.ORDER_ROOT_DIV;
        l_orderRootDiv = l_opLoginSec.getSessionProperty(l_orderRootDivString);
        l_ifoOrderUnitParams.setOrderRootDiv(l_orderRootDiv);
        
        // 注文エラー理由コード
		l_ifoOrderUnitParams.setErrorReasonCode(
			WEB3ErrorReasonCodeDef.NORMAL);

		//発注経路区分
        //先物OP発注サービス.get訂正取消時発注経路区分()の戻り値
        WEB3IfoFrontOrderService l_service =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        OrderUnit l_orderUnit  = l_finApp.getTradingModule(
            ProductTypeEnum.IFO).getOrderManager().toOrderUnit(l_ifoOrderUnitParams);

        String l_strSubmitOrderRouteDiv = null;

        try
        {
            l_strSubmitOrderRouteDiv = l_service.getChangeSubmitOrderRouteDiv(
                (IfoOrderUnit)l_orderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnitParams;
    }
    
    /**
     * 取引者ID
     */
    private long traderId;
    
    /**
     * (set取引者ID)<BR>
     */
    public void setTraderId (long l_lngTraderID)
    {
        traderId = l_lngTraderID;
    }
}
@
