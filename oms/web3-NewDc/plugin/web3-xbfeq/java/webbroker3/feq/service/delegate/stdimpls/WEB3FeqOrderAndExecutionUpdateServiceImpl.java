head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAndExecutionUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式出来／約定更新サービスImpl(WEB3FeqOrderAndExecutionUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 呉艶飛(中訊) 新規作成
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.DefaultFeqOrderFillMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqExecuteCancelUpdateInterceptor;
import webbroker3.feq.WEB3FeqExecuteUpdateInterceptor;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.service.delegate.WEB3FeqOrderAndExecutionUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式出来／約定更新サービスImpl) <BR>
 * 外国株式出来／約定更新サービス実装クラス <BR>
 * TransactionalInterceptor.TX_JOIN_EXISTING <BR>
 * <BR>
 * @@ author 呉艶飛 <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqOrderAndExecutionUpdateServiceImpl implements WEB3FeqOrderAndExecutionUpdateService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FeqOrderAndExecutionUpdateServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F50167
     */
    public WEB3FeqOrderAndExecutionUpdateServiceImpl() 
    {
     
    }
    
    /**
     * (update約定情報) <BR>
     * 出来／約定更新処理を行う <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（出来／約定共通）update約定情報」参照。 <BR>
     * @@param l_hostFeqOrderExecNotifyParams - (外株出来通知キュー) <BR>
     * 外株出来通知キュー行オブジェクト <BR>
     *  <BR>
     * ※外株出来通知キューParamsはDDLより自動生成する。 <BR>
     * @@throws WEB3BaseException
     * @@roseuid 428AFC1C02F0
     */
    public void updateExecuteUnit(HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExecuteUnit(HostFeqOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_hostFeqOrderExecNotifyParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManger = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        //1.1更新イベントインタセプタを生成する。 
        WEB3FeqExecuteUpdateInterceptor l_updateInterceptor = new WEB3FeqExecuteUpdateInterceptor(l_hostFeqOrderExecNotifyParams);
        
        //1.2更新イベントインタセプタを注文マネージャにセットする。 
        l_orderManger.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //発注日：　@外株出来通知キューParams.発注日 
        Date l_datExecTime = l_hostFeqOrderExecNotifyParams.getOrderBizDate();
        log.debug("発注日 = " + l_datExecTime);
        
        GtlUtils.truncateDate(l_datExecTime);
        String l_strEmpCode = l_hostFeqOrderExecNotifyParams.getOrderEmpCode();
        log.debug("l_strEmpCode = " + l_strEmpCode);
        
        //1.3運用コードに該当する注文単位を取得する。  
        FeqOrderUnit l_feqOrderUnit = l_orderManger.getValidOrderUnitByOrderEmpCode(l_datExecTime, l_strEmpCode);
        
        //1.4約定コールバックメッセージを生成する。  
        DefaultFeqOrderFillMarketResponseMessage l_marketResponseMessage = 
            new DefaultFeqOrderFillMarketResponseMessage(
                l_feqOrderUnit.getOrderId(), 
                l_feqOrderUnit.getOrderUnitId(),
                l_hostFeqOrderExecNotifyParams.getExecQuantity(), 
                l_hostFeqOrderExecNotifyParams.getExecPrice(),
                l_hostFeqOrderExecNotifyParams.getFxRate());
        
        log.debug("注文ＩＤ = " + l_marketResponseMessage.getOrderId());
        log.debug("注文単位ＩＤ = " + l_marketResponseMessage.getOrderUnitId());
        log.debug("約定株数 = " + l_marketResponseMessage.getFillQuantity());
        log.debug("約定単価 = " + l_marketResponseMessage.getFillPrice());
        log.debug("為替レート = " + l_marketResponseMessage.getFxRate());
        
        l_updateInterceptor.setFeqOrderUnit(l_feqOrderUnit);
        l_updateInterceptor.setExecQuantity(
            l_hostFeqOrderExecNotifyParams.getExecQuantity());
        l_updateInterceptor.setExecPrice(
            l_hostFeqOrderExecNotifyParams.getExecPrice());
        l_updateInterceptor.setFxRate(
            l_hostFeqOrderExecNotifyParams.getFxRate());
        
        //1.5指定の内容で、約定情報を更新する。 
        FeqMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (FeqMarketResponseReceiverCallbackService)l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
        l_marketResponseReceiverCallbackService.process(l_marketResponseMessage);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update約定取消) <BR>
     * 出来／約定取消更新処理を行う <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（出来／約定共通）update約定取消」参照。 <BR>
     * @@param l_hostFeqOrderExecNotifyParams - (外株出来通知キュー) <BR>
     * 外株出来通知キュー行オブジェクト <BR>
     * （約定ＩＤ指定の場合null） <BR>
     *  <BR>
     * ※外株出来通知キューParamsはDDLより自動生成する。 <BR>
     * 
     * @@param l_lngExecuteId - (約定ＩＤ) <BR>
     * （外株出来通知キュー指定の場合null）
     * @@throws WEB3BaseException
     * @@roseuid 428B156201D7
     */
    public void updateExecuteCancel(
        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams, 
        long l_lngExecuteId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExecuteUnit()";
        log.entering(STR_METHOD_NAME);
        
        if (l_lngExecuteId == 0 && l_hostFeqOrderExecNotifyParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManger = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        WEB3FeqPositionManager l_positionManager = (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
        WEB3FeqOrderExecution l_orderExcution  = null;
        
        //1.1約定ＩＤの入力がある場合（約定ＩＤ != 0）
        long l_lngOrderId = 0;
        long l_lngOrderExecutionId = 0;
        try
        {
            if (l_lngExecuteId != 0)
            {
                //1.1.1約定オブジェクトを取得する。 
                l_orderExcution = (WEB3FeqOrderExecution)l_orderManger.getOrderExecution(l_lngExecuteId);
                
                //1.1.1注文ＩＤを取得する。
                FeqOrderExecutionRow l_orderExecutionRow = (FeqOrderExecutionRow)l_orderExcution.getDataSourceObject();
                
                l_lngOrderId = l_orderExecutionRow.getOrderId();
               
                //1.1.1約定ＩＤを取得する。
                l_lngOrderExecutionId = l_orderExecutionRow.getOrderExecutionId();
            }
            else
            {
                //1.2.1運用コードに該当する注文単位を取得する。 
                Date l_datExecTime = l_hostFeqOrderExecNotifyParams.getExecTimestamp();
                GtlUtils.truncateDate(l_datExecTime);
                log.debug("l_datExecTime = " + l_datExecTime);
                
                String l_strEmpCode = l_hostFeqOrderExecNotifyParams.getOrderEmpCode();
                log.debug("運用コード = " + l_strEmpCode);
                
                FeqOrderUnit l_feqOrderUnit = l_orderManger.getValidOrderUnitByOrderEmpCode(l_datExecTime, l_strEmpCode);
                
                //1.2.2約定を取得する。 
                l_orderExcution = 
                    (WEB3FeqOrderExecution)l_orderManger.getExecution(
                        l_feqOrderUnit.getOrderUnitId(), 
                        l_hostFeqOrderExecNotifyParams.getExecQuantity(), 
                        l_hostFeqOrderExecNotifyParams.getExecPrice());
                FeqOrderExecutionRow l_orderExecutionRow = (FeqOrderExecutionRow)l_orderExcution.getDataSourceObject();
                
                //1.2.3注文ＩＤを取得する。
                l_lngOrderId = l_orderExecutionRow.getOrderId();
                
                //1.2.4約定ＩＤを取得する。
                l_lngOrderExecutionId = l_orderExecutionRow.getOrderExecutionId();
            }
            //1.3更新イベントインタセプタを生成する。 
            WEB3FeqExecuteCancelUpdateInterceptor l_updateInterceptor = new WEB3FeqExecuteCancelUpdateInterceptor(l_orderExcution);
            
            //1.4更新イベントインタセプタを注文マネージャにセットする。
            l_orderManger.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            //1.5更新イベントインタセプタをポジションマネージャにセットする。
            l_positionManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            log.debug("注文ＩＤ = " + l_lngOrderId);
            log.debug("約定ＩＤ = " + l_lngOrderExecutionId);
            
            //1.6約定取消コールバックメッセージを生成する。
            DefaultUndoOrderFillMarketResponseMessage l_marketResponseMessage = 
                new DefaultUndoOrderFillMarketResponseMessage(l_lngOrderId, l_lngOrderExecutionId);
            
            //1.7指定の内容で、約定情報を更新する。
            FeqOrderUnit l_orderunit =
                (FeqOrderUnit)l_orderManger.getOrderUnit(l_orderExcution.getOrderUnitId());
            l_updateInterceptor.setFeqOrderUnit(l_orderunit);
            
            FeqMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                (FeqMarketResponseReceiverCallbackService)l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
            l_marketResponseReceiverCallbackService.process(l_marketResponseMessage);
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() +  "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }     
    }
}
@
