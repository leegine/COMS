head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeCancelNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正取消通知UnitService実装クラス(WEB3FuturesChangeCancelNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/21 盧法@旭 (中訊) 新規作成
                 : 2006/07/28 柴雙紅 (中訊)　@仕様変更　@モデル502
                 : 2006/11/29 周捷(中訊) 仕様変更モデルNo.577
Revesion History : 2008/03/17 張騰宇 (中訊)仕様変更 モデル833
*/

package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.define.WEB3IfoCanmodReceiptTypeDef;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;

/**
 * (先物訂正取消通知UnitServiceImpl)<BR>
 * 株価指数先物訂正取消通知UnitService実装クラス<BR>
 * <BR>
 * 注文１件ごとの訂正取消通知処理を実施する。<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)を指定する。<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3FuturesChangeCancelNotifyUnitServiceImpl
    implements WEB3FuturesChangeCancelNotifyUnitService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesChangeCancelNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40F7A2D00261
     */
    public WEB3FuturesChangeCancelNotifyUnitServiceImpl()
    {

    }

    /**
     * (notify訂正)<BR>
     * 訂正通知処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物訂正取消通知）notify訂正」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_ifoChangeCancelNotifyInterceptor - 先物OP訂正取消通知インタセプタオブジェクト<BR>
     * @@roseuid 40A8A06F0266
     */
    public void notifyChange(
        OrderUnit l_orderUnit,
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor)
        throws WEB3BaseException    
    {
        final String STR_METHOD_NAME = "notifyChange()";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null || l_ifoChangeCancelNotifyInterceptor == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
        IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingMod.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoChangeCancelNotifyInterceptor);

		//1:注文単位を再取得する。（口座ロック競合した場合を考慮）
		OrderUnit l_reOrderUnit = l_orderUnit;
		try
		{
			l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
		}
		catch (NotFoundException l_nfe)
		{
			log.error("データ不整合エラー。", l_nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(),
				l_nfe); 
		}
        
        //3:取得訂正後指値
        double l_dblChangedLimitPrice =
            l_ifoChangeCancelNotifyInterceptor.getChangedLimitPrice();
        if (Double.isNaN(l_dblChangedLimitPrice))
        {
            l_dblChangedLimitPrice = 0;
        }
        log.debug("訂正後指値" + l_dblChangedLimitPrice);

        //4:取得訂正後数量
        double l_dblChangedQuantity =
            l_ifoChangeCancelNotifyInterceptor.getChangedQuantity();
        if (Double.isNaN(l_dblChangedQuantity))
        {
            l_dblChangedQuantity = 0;
        }
        log.debug("訂正後数量" + l_dblChangedQuantity);
        //取得訂正前数量
        double l_dblQuantity = l_reOrderUnit.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }
        log.debug("取得訂正前数量" + l_dblQuantity);

        //5:取得取引銘柄
        TradedProduct l_tradedProduct = l_reOrderUnit.getTradedProduct();

        //6：注文カテゴリを取得する
        OrderCategEnum l_orderCateg = l_reOrderUnit.getOrderCateg();

        //7: 約定数量
        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }
        log.debug("約定数量" + l_dblExecutedQuantity);

        //8: 合計約定金額
        double l_dblExecutedAmount = l_reOrderUnit.getExecutedAmount();
        if (Double.isNaN(l_dblExecutedAmount))
        {
            l_dblExecutedAmount = 0;
        }
        log.debug(" 合計約定金額" + l_dblExecutedAmount);
        
        //9: 手数料オブジェクトを生成する
        WEB3IfoBizLogicProvider l_bizLogicProvider = new WEB3IfoBizLogicProvider();
        WEB3GentradeCommission l_commision =
            l_bizLogicProvider.createCommission(
                l_reOrderUnit.getOrderUnitId(),
                l_dblChangedQuantity);

        //注文UnitId
        long l_lngOrderUnitId = l_reOrderUnit.getOrderUnitId();    
        log.debug("注文UnitId" + l_lngOrderUnitId );

        //注文Id
        long l_lngOrderId = l_reOrderUnit.getOrderId();
        log.debug("注文Id " + l_lngOrderId);

        //顧客Id
        long l_lngMainAccountId = l_reOrderUnit.getAccountId();
        log.debug("顧客Id" + l_lngMainAccountId);

        //SubAccountID
        long l_lngSubAccountId = l_reOrderUnit.getSubAccountId();
        log.debug("SubAccountID is:" + l_lngSubAccountId);

        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            //取得補助口座
            l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_lngMainAccountId,
                    l_lngSubAccountId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("データ不整合エラー。", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe); 
        }
        
        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateCalResult = new WEB3IfoEstimateDeliveryAmountCalcResult();

        //10:注文カテゴリが”新規建注文”の場合
        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderCateg))
        {
            // 11: calc訂正時概算建代金()
            //概算建代金を計算する。
            //  [calc概算建代金()に指定する引数]
            //  手数料：　@手数料オブジェクト
            //  指値：　@get訂正後指値()の戻り値
            //  補助口座：　@注文単位.補助口座ＩＤに該当する補助口座オブジェクト
            //  先物OP取引銘柄：　@注文単位.getTradedProduct()
            //  数量： get訂正後数量()の戻り値
            //  約定数量：　@注文単位.getExecutedQuantity()
            //  合計約定金額：　@注文単位.getExecutedAmount()
            //  isSkip金額チェック：　@true
            l_estimateCalResult = l_orderMgr.calcChangeEstimatePrice(
                l_commision,
                l_dblChangedLimitPrice,
                l_subAccount,
                (WEB3IfoTradedProductImpl)l_tradedProduct,
                l_dblChangedQuantity,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                true);
            log.debug("訂正時概算受渡代金 is:" + l_estimateCalResult.getCalcUnitPrice());

            //12: set概算受渡代金
            //  [引数]
            //  概算受渡代金：
            //  概算受渡代金計算結果（calc訂正時概算建代金の戻り値）.概算受渡代金
            l_estimateCalResult.setEstimateDeliveryAmount(l_estimateCalResult.getEstimateDeliveryAmount());
        }

        //13:注文カテゴリが”返済注文”の場合
        // is返済注文 92：先物返済注文
        if ((OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderCateg)))
        {
            log.debug("注文カテゴリが”返済注文”の場合");
			//16: adjust返済指定情報
			WEB3IfoPositionManagerImpl l_positionMgr =
				(WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
			l_positionMgr.adjustClosingContractSpecs(
				l_lngMainAccountId,
				l_lngSubAccountId,
				l_lngOrderId,
				l_lngOrderUnitId,
				l_dblQuantity,
				l_dblChangedQuantity);

            //17:create返済建玉エントリ
            WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl = 
                (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();
            SettleContractEntry[] l_entry = l_ifoPositionManagerImpl.createSettleContractEntry(l_lngOrderUnitId);

            //18:get side
            SideEnum l_side = null;
            if (SideEnum.BUY.equals(l_reOrderUnit.getSide()))
            {
                l_side = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_reOrderUnit.getSide()))
            {
                l_side = SideEnum.BUY;
            }
            
            //19:calc訂正時概算決済損益
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;

            l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_reOrderUnit.getTradedProduct();

            l_estimateCalResult =
                l_orderMgr.calcChangeEstimateSettlementIncome(
                    l_commision,
                    l_dblChangedLimitPrice,
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_ifoTradedProduct,
                    l_entry,
                    l_dblChangedQuantity,
                    l_side,
                    l_dblExecutedQuantity,
                    l_lngOrderUnitId,
                    true);
        }

        //20:get計算単価
        double l_dblCalOrderUnit = l_estimateCalResult.getCalcUnitPrice();
        if (Double.isNaN(l_dblCalOrderUnit))
        {
            l_dblCalOrderUnit = 0;
        }
        log.debug("計算単価" + l_dblCalOrderUnit);
        //21:set計算単価
        l_ifoChangeCancelNotifyInterceptor.setCalcUnitPrice(l_dblCalOrderUnit);

        //22: get概算受渡代金
        double l_dblEstimateAmount = l_estimateCalResult.getEstimateDeliveryAmount();
        if (Double.isNaN(l_dblEstimateAmount))
        {
            l_dblEstimateAmount = 0;
        }
        log.debug("概算受渡代金" + l_dblEstimateAmount);
        //23:set概算受渡代金
        l_ifoChangeCancelNotifyInterceptor.setEstimateDeliveryAmount(
            l_dblEstimateAmount);
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

        //24: get訂正取消通知区分
        String l_strChangeCancelNotifyDivision =
            l_ifoChangeCancelNotifyInterceptor.getChangeCancelNotifyDivision();
        log.debug("訂正取消通知区分" + l_strChangeCancelNotifyDivision);

        //取得MarketAdapter
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        IfoMarketResponseReceiverCallbackService l_receiverCallbackService =
            (IfoMarketResponseReceiverCallbackService)l_marketAdapter
                .getMarketResponseReceiverCallbackService();

        //25: 訂正完了の場合のみ実施
        if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision))
        {
            //26:訂正結果（訂正完了）オブジェクトを生成
            DefaultChangeOrderAcceptedMarketResponseMessage l_marketAcceptedResponseMessage =
                new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId);

            //27:訂正完了を注文に更新する
            ProcessingResult l_result = l_receiverCallbackService.process(l_marketAcceptedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //31: 全部約定済みかを判定する
            OrderUnit l_newOrderUnit = l_reOrderUnit;
            try
            {
                l_newOrderUnit =  l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            //isFullyExecuted( )
            boolean l_isFullyExecuted = l_newOrderUnit.isFullyExecuted();
            //sendMailProcess  
            //9: 全部約定済みかを判定する
            if (l_isFullyExecuted)
            {   
                WEB3IfoExecutedMailSendService l_mailSendService = 
                    (WEB3IfoExecutedMailSendService) Services.
                    getService(WEB3IfoExecutedMailSendService.class);
            
                //27.1: 約定メール送信テーブルに約定メ-ル行を挿入する    
                l_mailSendService.sendMailProcess(l_newOrderUnit, null);
                
                //27.2 notifyルールエンジンサーバ(IfoOrderUnit, OrderManagerPersistenceContext)
                try
                {
                    WEB3FuturesOrderManagerImpl l_optionOrderManager = (WEB3FuturesOrderManagerImpl)l_orderManager;
                    l_optionOrderManager.notifyRLS((IfoOrderUnit) l_newOrderUnit, OrderManagerPersistenceContext.FILL_ORDER);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
                }
            }
        }

        //28: 訂正失敗の場合のみ実施
        if (WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_strChangeCancelNotifyDivision))
        {
            //29:訂正結果（訂正失敗）オブジェクトを生成
            DefaultChangeOrderRejectedMarketResponseMessage l_marketRejectedResponseMessage =
                new DefaultChangeOrderRejectedMarketResponseMessage(l_lngOrderId);

            //30:訂正失敗を注文に更新する
            ProcessingResult l_result = l_receiverCallbackService.process(l_marketRejectedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }


        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify取消)<BR>
     * 取消通知処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物訂正取消通知）notify取消」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_ifoChangeCancelNotifyInterceptor - 先物OP訂正取消通知インタセプタオブジェクト<BR>
     * @@roseuid 40A8A06F0285
     */
    public String notifyCancel(
        OrderUnit l_orderUnit,
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancel(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) ";
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
        IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingMod.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoChangeCancelNotifyInterceptor);
  
        //取得MarketAdapter
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        // get the service
        IfoMarketResponseReceiverCallbackService l_callbackServiceImpl =
            (IfoMarketResponseReceiverCallbackService)l_marketAdapter
                .getMarketResponseReceiverCallbackService();

        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

		//注文単位を再取得する。（口座ロック競合した場合を考慮）
		OrderUnit l_reOrderUnit = l_orderUnit;
		try
		{
			l_reOrderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
		}
		catch (NotFoundException l_nfe)
		{
			log.error("データ不整合エラー。", l_nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(),
				l_nfe); 
		}

        //1.3 取得訂正取消通知区分
        String l_strDivision =
            l_ifoChangeCancelNotifyInterceptor.getChangeCancelNotifyDivision();
        //取得OrderId
        long l_lngOrderId = l_reOrderUnit.getOrderId();
        //1.4 get訂正取消結果コード
        String l_strResultCode = l_ifoChangeCancelNotifyInterceptor.getChangeCancelResultCode();

        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }

        //1.5 get訂正後数量
        double l_dblChangedQuantity = l_ifoChangeCancelNotifyInterceptor.getChangedQuantity();
        if ((WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(l_strDivision))
        && (l_dblChangedQuantity > l_dblExecutedQuantity))
        {
            return WEB3StatusDef.DEALING;
        }
        log.debug("訂正後数量 = " + l_dblChangedQuantity);       
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

        //先物注文マネージャを取得
        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();

        //1.8 取消完了
        if (WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(l_strDivision))
        {
            //1.8.1 (get訂正後数量 > 0)の場合実施。
            if (l_dblChangedQuantity > 0)
            {
                double l_dblNetAmount = 0D;

                //1.8.1.1 get受渡金額合計(IfoOrderUnit)
                l_dblNetAmount = l_orderMgr.getNetAmount((IfoOrderUnit)l_reOrderUnit);

                //1.8.1.2 set概算受渡代金(double)
                l_ifoChangeCancelNotifyInterceptor.setEstimateDeliveryAmount(l_dblNetAmount);
            }
            
            //1.8.2 isSONAR取消(IfoOrderUnit)
            boolean l_blnIsSONARCancel = l_orderMgr.isSONARCancel((IfoOrderUnit)l_reOrderUnit);
            
            //1.8.3 取消結果（取消完了）オブジェクトを生成する
            DefaultCancelOrderAcceptedMarketResponseMessage l_acceptedResponseMessage =
                new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);

            //1.8.4 取消完了を注文に更新する
            ProcessingResult l_result = l_callbackServiceImpl.process(l_acceptedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //is予約注文確認要(IfoOrderUnit)
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist((IfoOrderUnit)l_reOrderUnit);

            //予約注文確認要（is予約注文確認要() == true）の場合
            if (l_blnIsReserveOrderExist)
            {
                WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);
                //cancelAll予約注文単位(親注文の注文ID : long)
                l_ifoOrderUpdateService.cancelAllOrderUnit(((IfoOrderUnit)l_reOrderUnit).getOrderId());
            }

            OrderUnit l_newOrderUnit = l_reOrderUnit;
            try
            {
                l_newOrderUnit =  l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            //SONAR入力の取消（isSONAR取消()の戻り値 == true）の場合
            if(l_blnIsSONARCancel)
            {
                //1.8.5 notifyルールエンジンサーバ(IfoOrderUnit, OrderManagerPersistenceContext)
                try
                {
                    l_orderMgr.notifyRLS((IfoOrderUnit) l_newOrderUnit, 
                        OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
                }
            }
            
            //l_isUnexecuted = false( )
            boolean l_isUnexecuted = l_newOrderUnit.isUnexecuted();
            //sendMailProcess  
            //1.8.6 全部約定済みかを判定する
            if (!l_isUnexecuted)
            {                   
                WEB3IfoExecutedMailSendService l_mailSendService = 
                    (WEB3IfoExecutedMailSendService) Services.
                    getService(WEB3IfoExecutedMailSendService.class);
            
                //1.8.7 約定メール送信テーブルに約定メ-ル行を挿入する    
                l_mailSendService.sendMailProcess(l_newOrderUnit, null);
            }
        }

        //1.9 取消失敗
        else if (WEB3IfoCanmodReceiptTypeDef.CANCELED_FAILED.equals(l_strDivision))
        {
            try
            {
                l_reOrderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            //(*)SONAR入力の取消（isSONAR取消()の戻り値 == true）の場合
            if (l_orderMgr.isSONARCancel((IfoOrderUnit)l_orderUnit))
            {
                //1.9.3 notifyルールエンジンサーバ(IfoOrderUnit, OrderManagerPersistenceContext)
                try
                {
                    l_orderMgr.notifyRLS((IfoOrderUnit) l_reOrderUnit, 
                        OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT );
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
                }
            }
            
            //1.9.1 取消結果（取消失敗）オブジェクトを生成する
            DefaultCancelOrderRejectedMarketResponseMessage l_rejectedResponseMessage =
                new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);

            //1.9.2 取消失敗を注文に更新する
            ProcessingResult l_result = l_callbackServiceImpl.process(l_rejectedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
        }

        log.debug(STR_METHOD_NAME);
        return WEB3StatusDef.DEALT;
    }

    /**
     * (is訂正更新済)<BR>
     * 指定注文単位が訂正更新済みかを判定する。<BR>
     * <BR>
     * －訂正更新済みの場合はtrueを返却する。<BR>
     * －訂正更新していない（HOSTからの訂正入力）場合はfalseを返却する。<BR>
     * <BR>
     * 引数の注文単位オブジェクト.注文状態が以下の場合trueを返却する。<BR>
     * 　@”受付済（変更注文）”：OrderStatusEnum.MODIFY_ACCEPTED<BR>
     * 　@”発注中（変更注文）”：OrderStatusEnum.MODIFYING<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@param l_orderUnit - 注文単位<BR>
     * @@return boolean
     * @@roseuid 40A8A06F0295
     */
    protected boolean isChangeUpdateEnd(OrderUnit l_orderUnit)
    {

        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if ((OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus))
            || (OrderStatusEnum.MODIFYING.equals(l_orderStatus)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (is取消更新済)<BR>
     * 指定注文単位が取消更新済みかを判定する。<BR>
     * <BR>
     * －取消更新済みの場合はtrueを返却する。<BR>
     * －取消更新していない（HOSTからの取消入力）場合はfalseを返却する。<BR>
     * <BR>
     * 引数の注文単位オブジェクト.注文状態が以下の場合trueを返却する。<BR>
     * 　@”受付済（取消注文）”：OrderStatusEnum.CANCEL_ACCEPTED<BR>
     * 　@”発注中（取消注文）”：OrderStatusEnum.CANCELLING<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@param l_orderUnit - 注文単位<BR>
     * @@return boolean
     * @@roseuid 40A8A06F02B4
     */
    protected boolean isCancelUpdateEnd(OrderUnit l_orderUnit)
    {
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if ((OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus))
            || (OrderStatusEnum.CANCELLING.equals(l_orderStatus)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
@
