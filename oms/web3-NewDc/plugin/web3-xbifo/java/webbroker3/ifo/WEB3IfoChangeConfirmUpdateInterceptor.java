head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeConfirmUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 訂正確定更新インタセプタ(WEB3IfoChangeConfirmUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 盧法@旭 (中訊) 新規作成
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.define.WEB3IfoOrderModifyCancelTypeDef;
/**
 * (訂正確定更新インタセプタ)<BR>
 * 先物OP訂正確定更新インタセプタクラス<BR>
 * @@author  盧法@旭
 * @@version 1.0
 */
public class WEB3IfoChangeConfirmUpdateInterceptor extends WEB3IfoUpdateInterceptor 
{
    /**
      * ログ出力ユーティリティ。
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeConfirmUpdateInterceptor.class);
    
    /**
     * (更新値設定)<BR>
     *（mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、<BR>・
     * 却する。<BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 注文単位.先物／オプション区分 == "オプション"の場合 <BR> 
     * 「OP訂正確定_注文単位テーブル仕様.xls」参照。<BR> 
     * 注文単位.先物／オプション区分 == "先物"の場合 <BR>
     * 「先物訂正確定_注文単位テーブル仕様.xls」参照。<BR> 
     * @@param l_updateType - INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * @@param l_dealing - （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_orderUnitParams - <注文単位Params><BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40A4785200C4
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_dealing, 
        IfoOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME =
             ".mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }        
        
        try
        {
            //注文単価を設定する
            if (l_orderUnitParams.getPriceIsNull())
            {
                l_orderUnitParams.setConfirmedOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
            }
            
            //概算受渡代金を設定する
            if (l_orderUnitParams.getEstimatedPriceIsNull())
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
            }

            //執行条件を設定する
            l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType());  

            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            //取得注文単位オブジェクト           
            //long l_lngOrderUnitId = l_orderUnitParams.getOrderUnitId();            

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr = 
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
            OrderUnit l_orderUnit = l_orderMgr.toOrderUnit(l_orderUnitParams);
            //注文訂正・取消区分をセット
            if (l_orderUnit.isUnexecuted() == false)
            {
                log.debug("Enter the if path that l_orderUnit.isUnexecuted() is false ");
                l_orderUnitParams.setModifyCancelType(WEB3IfoOrderModifyCancelTypeDef.ORDER_REVISED_PARTLY);
                log.debug("Exiting the if path that l_orderUnit.isUnexecuted() is false");
            }
            else
            {
                log.debug("Enter the else path.");
                l_orderUnitParams.setModifyCancelType(WEB3IfoOrderModifyCancelTypeDef.ORDER_REVISED_ALL);
                log.debug("Exiting the els path.");
            }
            log.debug("Exiting the try path ."); 
        }
        catch(Exception l_ex)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
        
    }
    
    /**
     * (先物OP訂正確定更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.ifo.WEB3IfoChangeConfirmUpdateInterceptor
     * @@roseuid 40A47975028A
     */
    public WEB3IfoChangeConfirmUpdateInterceptor() 
    {

    }
}
@
