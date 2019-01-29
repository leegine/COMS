head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCloseNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               /**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP失効通知UnitServiceImpl(WEB3IfoCloseNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/19 盧法@旭 (中訊) 新規作成
              001: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
              002: 2006/07/27 唐性峰 (中訊) モデルNo.505対応
              003: 2006/11/29 周捷(中訊) 仕様変更モデルNo.578,586
Revesion History : 2008/03/17 張騰宇 (中訊)仕様変更 モデル833
*/

package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoCloseNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP失効通知UnitServiceImpl)<BR>
 * 先物OP失効通知１件サービス実装クラス<BR>
 * <BR>
 * １件ごとの失効通知処理を実施する。<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)を指定する。<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3IfoCloseNotifyUnitServiceImpl implements WEB3IfoCloseNotifyUnitService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoCloseNotifyUnitServiceImpl.class);
    /**
     * @@roseuid 40C0752E03A9
     */
    public WEB3IfoCloseNotifyUnitServiceImpl()
    {

    }
    FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    //取得MarkerAdapter
    TradingModule l_tradingMod =
        l_finApp.getTradingModule(ProductTypeEnum.IFO);

    /**
     * (notify失効)<BR>
     * 失効処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP失効通知）notify失効」参照。<BR>
     * <BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_dblExecutionQuantity - 約定数量
     * @@param l_strCloseReasonCode - 失効理由コード
     * @@param l_strCloseNotifyType - 失効通知区分<BR>
     * @@throws WEB3BaseException
     * @@roseuid 408C987301AE
     */
    public String notifyClose(OrderUnit l_orderUnit, double l_dblExecutionQuantity, String l_strCloseReasonCode, String l_strCloseNotifyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyClose(OrderUnit l_orderUnit, double l_dblExecutionQuantity, String l_strCloseReasonCode, String l_strCloseNotifyType)";
        log.entering(STR_METHOD_NAME);

        if((l_orderUnit == null) ||l_strCloseReasonCode == null || l_strCloseNotifyType == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".notifyClose");
        }

        if(l_strCloseReasonCode.length() < 0 || l_strCloseNotifyType.length() < 0 )
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".notifyClose");
        }

        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
		//1.1注文単位を再取得する。（口座ロック競合した場合を考慮）
		OrderUnit l_reOrderUnit = l_orderUnit;
		try
		{
			l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
		}
		catch(NotFoundException l_nfe)
		{
			log.error("DBへのアクセスに失敗しました。", l_nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(),
				l_nfe);
		}
		
        if(l_dblExecutionQuantity < 0)
        {
            log.error("約定数量<0");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() +  ".notifyClose");
        }

        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();      
        
        //1.2先物OP失効通知更新インタセプタ( )
        WEB3IfoCloseNotifyUpdateInterceptor l_closeNotifyUpdateINterceptor = 
            new WEB3IfoCloseNotifyUpdateInterceptor();

        //set失効通知区分(String)
        //失効通知区分：　@パラメータ.失効通知区分
        l_closeNotifyUpdateINterceptor.setCloseNotifyType(l_strCloseNotifyType);

        //1.3setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_closeNotifyUpdateINterceptor);
      
        //get IfoMarketResponseReceiverCallbackServiceImpl Object
        IfoMarketResponseReceiverCallbackService l_marketResponseReceiveCallbackService =
            (IfoMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        //get WEB3IfoExecutedMailSendServiceImpl Object
        WEB3IfoExecutedMailSendService l_executedMailSendService =
            (WEB3IfoExecutedMailSendService)Services.getService(WEB3IfoExecutedMailSendService.class);
        
        //1.4: 約定数量を取得する
        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0D;
        }
        log.debug("約定数量を取得:" + l_dblExecutedQuantity);

        //1.5: 注文ＩＤを取得する
        long l_lngOrderId = l_reOrderUnit.getOrderId();
        log.debug(" 注文ＩＤを取得:" + l_lngOrderId);

        try
        {
            //1.6:(*1)失効(出来待ち数量あり)の場合のみ実施
            ProcessingResult l_processingResult = null;
            if((WEB3CloseNotifyTypeDef.CLOSE.equals(l_strCloseNotifyType)) &&
                (l_dblExecutionQuantity > l_dblExecutedQuantity))
            {
                //1.6.1:update失効注文(OrderUnit, OrderExpirationStatusEnum)
                updateCloseOrder(l_reOrderUnit,OrderExpirationStatusEnum.EXPIRING); 
                //1.6.2:return     
                return WEB3StatusDef.DEALING;
            }
            
            //1.7: (*2) 失効（出来待ち数量なし）の場合のみ実施
            if (WEB3CloseNotifyTypeDef.CLOSE.equals(l_strCloseNotifyType))
            {
                //失効オブジェクトを生成する
                //1.7.1:DefaultOrderInvalidatedMarketResponseMessage(注文ID : long)
                DefaultOrderInvalidatedMarketResponseMessage
                    l_orderInvalidatedMarketResponseMessage = new DefaultOrderInvalidatedMarketResponseMessage(l_lngOrderId);

                // 失効を注文に更新する
                //1.7.2: process(失効 : OrderInvalidatedMarketResponseMessage)
                l_processingResult = l_marketResponseReceiveCallbackService.process(l_orderInvalidatedMarketResponseMessage);
                
                if (l_processingResult.isFailedResult())
                {
                    log.error("失効を注文に更新するfail");
                    throw new WEB3BaseException(
                        l_processingResult.getErrorInfo(),
                        STR_METHOD_NAME);
                }
                
				l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
                
				
				//1.7.3:（*2-1）出来ありの場合のみ実施
                //（*2-1）分岐フロー  注文単位.isPartiallyExecuted()=trueの場合実施する。
                //sendMailProcess(OrderUnit, String)
				if(l_reOrderUnit.isPartiallyExecuted())
				{
					l_executedMailSendService.sendMailProcess(l_reOrderUnit,null);
				}
                //1.7.4: 約定メール送信テーブルに失効メール行を挿入する
                //sendMailProcess(OrderUnit, String)
                l_executedMailSendService.sendMailProcess(l_reOrderUnit,l_strCloseReasonCode);                

                //is予約注文確認要(IfoOrderUnit)
                boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist((IfoOrderUnit)l_reOrderUnit);
                //予約注文確認要（is予約注文確認要() == true）の場合
                if (l_blnIsReserveOrderExist)
                {
                    WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                        (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                            WEB3ToSuccReservationIfoOrderUpdateService.class);
                    // invalidateAll予約注文単位(親注文の注文ID : long)
                    l_ifoOrderUpdateService.invalidateAllOrderUnit(l_lngOrderId);
                }

                try
                {
                    //1.7.5:notifyルールエンジンサーバ(IfoOrderUnit, OrderManagerPersistenceContext)
                    l_orderManager.notifyRLS((IfoOrderUnit) l_reOrderUnit, OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT);
                }
                //1.7.6 notifyルールエンジンサーバ()にて業務エラーがスローされた場合
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
                }
            }
            //1.8: (*3) 失効取消の場合のみ実施
            else if(WEB3CloseNotifyTypeDef.CLOSE_CANCEL.equals(l_strCloseNotifyType))
            {
                //1.8.1: DefaultUndoOrderInvalidatedMarketResponseMessage(注文ID : long)
                DefaultUndoOrderInvalidatedMarketResponseMessage l_UndoOrderInvalidatedMarketResponseMessage =
                    new DefaultUndoOrderInvalidatedMarketResponseMessage(l_lngOrderId);

                //1.8.2: process(失効取消 : UndoOrderInvalidatedMarketResponseMessage)
                l_processingResult = l_marketResponseReceiveCallbackService.process(l_UndoOrderInvalidatedMarketResponseMessage);
                if (l_processingResult.isFailedResult())
                {
                    throw new WEB3BaseException(l_processingResult.getErrorInfo(),STR_METHOD_NAME);
                }

                // 失効メールを”無効”で更新する
				l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
                //1.8.3:undoSendMail(OrderUnit)
                l_executedMailSendService.undoSendMail(l_reOrderUnit);                 
            }
            
            //1.9:(*4)一部失効、または、失効取消の場合
            if (l_dblExecutionQuantity > 0 || WEB3CloseNotifyTypeDef.CLOSE_CANCEL.equals(l_strCloseNotifyType))
            {
				l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
                IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_reOrderUnit.getDataSourceObject();
                //1.9.1:update概算受渡代金(注文単位 : OrderUnit)
                if (WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitRow.getFutureOptionDiv()))
                {
                    WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                        (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
                    l_optionOrderManagerImpl.updateEstimateDeliveryAmount(l_reOrderUnit);
                }
                //1.9.2:update先物概算受渡代金(注文単位 : OrderUnit)
                else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_orderUnitRow.getFutureOptionDiv()))
                {
                    WEB3FuturesOrderManagerImpl l_futuresOrderManagerImpl = 
                        (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();
                    l_futuresOrderManagerImpl.updateFuturesEstimateDeliveryAmount(l_reOrderUnit);
                }
            }
            
            //取得顧客(MainAccount)
            WEB3GentradeSubAccount l_subAccount = null;
            long l_lngMainAccountId = l_reOrderUnit.getAccountId();

            //取得補助口座 
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                l_lngMainAccountId, l_reOrderUnit.getSubAccountId());            

            //1.10 (*5)分岐フロー
            //注文単位.補助口座IDに該当する補助口座のタイプ != 7（証拠金口座）
            //の場合、処理を実施する。        
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                //1.10.1 余力再計算(補助口座 : 補助口座)
				WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
					(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
				l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
            }         

            //1.11:
            return WEB3StatusDef.DEALT;
        }   
        catch (NotFoundException l_nfex)
        {
            log.error(STR_METHOD_NAME, l_nfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + ".notifyClose"); 
        }
    }

    /**
     * (update失効注文)<BR>
     * 注文単位の失効区分を更新する。<BR>
     * <BR>
     *     以下の条件に該当する注文単位レコードをupdateする。<BR> 
     *　@    [条件] <BR>
     *　@　@    注文単位テーブル.注文単位ID = パラメータ.注文単位.注文単位ID <BR>
     *<BR>
     *　@    [更新内容] <BR>
     *　@　@    注文単位レコード.失効区分 = パラメータ.失効区分<BR>
     *　@　@    注文単位レコード.更新日付 = 現在日時 <BR>
     *  DB更新仕様<BR>
     * 「失効通知_株式注文単位テーブル.xls」の
     * 「OP失効通知_注文単位テーブル DB更新仕様 [失効中]」シート参照。     
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_orderExpirationStatus - 失効区分<BR>
     */
    protected void updateCloseOrder(OrderUnit l_orderUnit,
    OrderExpirationStatusEnum l_orderExpirationStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateCloseOrder(OrderUnit l_orderUnit," +
            "OrderExpirationStatusEnum l_orderExpirationStatus)";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitRow l_orderUnitRow = null;
        try
        {
            l_orderUnitRow = 
                (IfoOrderUnitRow)l_tradingMod.getOrderManager().
                    getOrderUnit(l_orderUnit.getOrderUnitId()).getDataSourceObject();
            IfoOrderUnitParams l_orderUnitParams = new IfoOrderUnitParams(l_orderUnitRow);
            l_orderUnitParams.setExpirationStatus(l_orderExpirationStatus);           
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            QueryProcessor l_queryProcessor = null;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
        }
        catch(NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                STR_METHOD_NAME);             
        }
        catch(DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);             
        
        }
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);             
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);             

        }
        log.exiting(STR_METHOD_NAME);

    }

    


}
@
