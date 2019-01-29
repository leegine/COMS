head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductTypeOrderSubmitterPersistenceManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文更新マネージャ(WEB3FeqProductTypeOrderSubmitterPersistenceManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 王煜 (中訊) 新規作成
*/
package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.FeqOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqProductTypeOrderSubmitterPersistenceManager;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文更新マネージャ)<BR>
 * 外国株式注文更新マネージャ<BR>
 * 
 * @@author 王煜(中訊)
 * @@version 1.0 
 */
public class WEB3FeqProductTypeOrderSubmitterPersistenceManager extends FeqProductTypeOrderSubmitterPersistenceManager 
{
    
    /**
     * (ログユーティリティ)<BR>
     */
    protected static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqProductTypeOrderSubmitterPersistenceManager.class);

    /**
     * @@roseuid 42CE39EB037A
     */
    public WEB3FeqProductTypeOrderSubmitterPersistenceManager() 
    {
     
    }
    
    /**
     * スーパークラスに自身のインスタンスを登録する。 <BR>
     * <BR>
     * （プラグイン初期化時にコールされる） <BR>
     * <BR>
     * --- <BR>
     * super.setInstance(this); <BR>
     * --- <BR>
     * @@roseuid 42B25A3402C9
     */
    public void register() 
    {
        super.setInstance(this);         
    }
    
    /**
     * (create約定)<BR>
     * 注文単位行／約定情報より、約定行オブジェクトを生成する。<BR>
     * <BR>
     * １）super.getInitializedOrderExecutionParams()をコールしする。<BR>
     * <BR>
     * [getInitializedOrderExecutionParams()に指定する引数]<BR>
     * arg0（：orderUnitRow）：　@注文単位行<BR>
     * arg1（：execQty）：　@約定数量<BR>
     * arg2（：execPrice）：　@約定単価<BR>
     * arg3（：fxate）：　@約定為替レート<BR>
     * <BR>
     * ２）戻り値の行オブジェクト.約定日時に引数.約定日時をセットする。<BR>
     * <BR>
     * ３）行オブジェクトから約定オブジェクトを生成し（<BR>
     * OrderManager.toOrderExecution()）返却する。<BR>
     * @@param l_orderUnitRow - (注文単位行)<BR>
     * 注文単位行オブジェクト<BR>
     * @@param l_dblQuantity - (約定数量)<BR>
     * 約定数量<BR>
     * @@param l_dblPrice - (約定単価)<BR>
     * 約定単価<BR>
     * @@param l_datExecDate - (約定日時)<BR>
     * 約定日時<BR>
     * @@param l_dblExecRate - (約定為替レート)<BR>
     * 約定為替レート<BR>
     * @@return webbroker3.feq.WEB3FeqOrderExecution
     * @@throws WEB3SystemLayerException
     * @@roseuid 42B25C200038
     */
    public WEB3FeqOrderExecution createExecution(
        FeqOrderUnitRow l_orderUnitRow, 
        double l_dblQuantity, 
        double l_dblPrice,
        Date l_datExecDate,
        double l_dblExecRate) throws WEB3SystemLayerException 
    {
        String STR_METHOD_NAME = 
            "createExecution(FeqOrderUnitRow, double, double, double)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        try
        {
            //[getInitializedOrderExecutionParams()に指定する引数] 
            //arg0（：orderUnitRow）：注文単位行 
            //arg1（：execQty）：約定数量 
            //arg2（：execPrice）：約定単価 
            //arg3（：fxate）：約定為替レート
            FeqOrderExecutionParams l_feqOrderExecutionParams = super.getInitializedOrderExecutionParams(
                l_orderUnitRow, 
                l_dblQuantity, 
                l_dblPrice, 
                l_dblExecRate);

            // ２）戻り値の行オブジェクト.約定日時に引数.約定日時をセットする。
            l_feqOrderExecutionParams.setExecTimestamp(l_datExecDate);

            WEB3FeqOrderExecution l_orderExecution = 
                (WEB3FeqOrderExecution)l_tradingModule.getOrderManager().toOrderExecution(l_feqOrderExecutionParams); 

            log.exiting(STR_METHOD_NAME);
            return l_orderExecution;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }    
    }   

    /**
     * (handle約定更新)<BR>
     * 注文データに約定情報を更新する。<BR>
     * <BR>
     * this.handleOrderFillMarketUpdates()に委譲する。<BR>
     * @@param l_lngOrderId - (注文ＩＤ)<BR>
     * 注文ＩＤ<BR>
     * @@param l_execCallbackMessage - (約定コールバックメッセージ)<BR>
     * 約定コールバックメッセージ<BR>
     * @@return OrderExecution
     * @@roseuid 42B7D67003AA
     */
    public OrderExecution handleExecutionUpdate(
        long l_lngOrderId, 
        FeqOrderFillMarketResponseMessage l_execCallbackMessage) 
    {
        return (OrderExecution) this.handleOrderFillMarketUpdates(l_lngOrderId, l_execCallbackMessage);
    }
}
@
