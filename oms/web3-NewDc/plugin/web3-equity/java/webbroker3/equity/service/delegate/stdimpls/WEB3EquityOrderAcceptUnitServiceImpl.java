head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文受付一件サービスimpl(WEB3EquityOrderAcceptUnitServiceimpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 鄒政 (中訊) 新規作成
Revesion History : 2004/10/22 法@旭修正
Revesion History : 2004/12/15 森川  (SRA) 残案件対応
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2005/03/09 劉（FLJ）キューの更新は株式受付一件サービスへ移す
Revesion History : 2006/11/01 唐性峰　@(中訊)モデルNo.1016
Revesion History : 2007/02/008 唐性峰　@(中訊)モデルNo.1125
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.equity.WEB3EquityOrderAcceptPersistenceInterceptor;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.equity.service.delegate.WEB3EquityOrderAcceptUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文受付一件サービスImpl）。<BR>
 * <BR>
 * 株式注文受付一件サービスの実装クラス。
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptUnitServiceImpl
    implements WEB3EquityOrderAcceptUnitService
{

    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderAcceptUnitServiceImpl.class);

    /**
     * (notify注文受付)。<BR>
     * <BR>
     * 【株式注文受付キューテーブル】注文受付のキューデータ一件に対する<BR>
     * 処理を行う。<BR>
     * （注文受付結果 == （"注文受付完了"、"エラー"）の場合）<BR>
     * @@param l_orderAcceptQueParams - 株式注文受付キューParams。
     * @@roseuid 4100EB860380
     */
    public void notifyOrderAccept(HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyOrderAccept(HostEqtypeOrderAcceptParams)";
        log.entering(STR_METHOD_NAME);
        if(l_hostEqtypeOrderAcceptParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeAccountManager l_accountMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        MarketAdapter l_marketAdaptor = l_tradingModule.getMarketAdapter();
        MarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            l_marketAdaptor.getMarketResponseReceiverCallbackService();

        //get注文単位
        EqTypeOrderUnit l_eqTypeOrderUnit = l_orderMgr.getOrderUnit(
            l_hostEqtypeOrderAcceptParams.getInstitutionCode(),//キューテーブル.証券会社コード
            l_hostEqtypeOrderAcceptParams.getBranchCode(),//キューテーブル.部店コード
            ProductTypeEnum.EQUITY,//株式
            l_hostEqtypeOrderAcceptParams.getOrderRequestNumber());//キューテーブル.識別コード
        long l_lngOrderId = l_eqTypeOrderUnit.getOrderId();
        
        if (OrderOpenStatusEnum.CLOSED.equals(l_eqTypeOrderUnit.getOrderOpenStatus()))
        {
            log.debug("受付対象注文はクローズ済 注文ID:" + l_lngOrderId);
            updateStatus(l_hostEqtypeOrderAcceptParams,WEB3HostStatusDef.COMPLETE_PROCESS);
            return;
        }

        //補助口座の取得
        WEB3GentradeSubAccount l_subAccount;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(
                l_eqTypeOrderUnit.getAccountId(), l_eqTypeOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(), l_nfe);
        }

        //2)  create株式注文受付インタセプタ
        WEB3EquityOrderAcceptPersistenceInterceptor l_interceptor =
            new WEB3EquityOrderAcceptPersistenceInterceptor(l_hostEqtypeOrderAcceptParams);

       //3) setThreadLocalPersistenceEventInterceptor(株式注文受付インタセプタ)
       l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_interceptor);

       String l_strAcceptStatus = l_hostEqtypeOrderAcceptParams.getAcceptStatus();
       if(WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(l_strAcceptStatus))
       {
           //株式注文受付キューParams.注文受付結果＝"注文受付完了"の場合

           DefaultNewOrderAcceptedMarketResponseMessage l_defaultNewOrderAcceptedMarketResponseMessage =
               new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
           ProcessingResult l_result =
               l_marketResponseReceiverCallbackService.process(l_defaultNewOrderAcceptedMarketResponseMessage);
           if (l_result.isFailedResult())
           {
               WEB3BaseException l_baseException = new WEB3BaseException(
                   l_result.getErrorInfo(),
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_result.getErrorInfo().getErrorMessage());
               log.error(STR_METHOD_NAME,l_baseException);
               throw l_baseException;
           }
       }
       else if(WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(l_strAcceptStatus))
       {
           //株式注文受付キューParams.注文受付結果＝"エラー"の場合

           DefaultNewOrderRejectedMarketResponseMessage l_defaultNewOrderRejectedMarketResponseMessage =
               new DefaultNewOrderRejectedMarketResponseMessage(l_lngOrderId);
           ProcessingResult l_result =
               l_marketResponseReceiverCallbackService.process(l_defaultNewOrderRejectedMarketResponseMessage);
           if (l_result.isFailedResult())
           {
               WEB3BaseException l_baseException = new WEB3BaseException(
                   l_result.getErrorInfo(),
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_result.getErrorInfo().getErrorMessage());
               log.error(STR_METHOD_NAME,l_baseException);
               throw l_baseException;
           }

           if (l_orderMgr.isReserveOrderConfirmRequire(l_eqTypeOrderUnit))
           {
               WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                   (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                       WEB3ToSuccReservationEqTypeOrderUpdateService.class);
               l_updateService.invalidateAllOrderUnit(l_eqTypeOrderUnit.getOrderId());
           }

           WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
               (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
           l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

           //reset市場コード(市場コード : String)
           //取引カレンダコンテキストの市場コードを再セットする。
           //[引数]
           //市場コード：　@注文単位.市場IDに該当する市場オブジェクト.市場コード
           EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
               (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
           String l_strMarketCode = null;
           try
           {
               l_strMarketCode =
                   l_finApp.getFinObjectManager().getMarket(l_eqtypeOrderUnitRow.getMarketId()).getMarketCode();
           }
           catch (NotFoundException l_nfe)
           {
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_nfe.getMessage(), l_nfe);
           }
           WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);

           //notifyルールエンジンサーバ(EqTypeOrderUnit, OrderManagerPersistenceContext)
           //ルールエンジンへの通知処理を行う。
           //引数は以下の通りに設定する。
           //注文単位 : 取得した注文単位
           //処理 : ORDER_REJECTED_BY_MKT
           try
           {
               l_eqTypeOrderUnit =
                   (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_eqTypeOrderUnit.getOrderUnitId());
           }
           catch (NotFoundException l_ex)
           {
               log.error(STR_METHOD_NAME, l_ex);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   getClass().getName() + STR_METHOD_NAME);
           }
           try
           {
               l_orderMgr.notifyRLS(l_eqTypeOrderUnit, OrderManagerPersistenceContext.ORDER_REJECTED_BY_MKT);
           }
           //(*)notifyルールエンジンサーバ()にて業務エラーがスローされた場合
           //(*)catchして処理を続行する。  ※ロールバックしない。
           catch (WEB3BusinessLayerException l_ex)
           {
               log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
           }
       }
       //処理区分更新
       String l_strStatus = WEB3HostStatusDef.COMPLETE_PROCESS;
       updateStatus(l_hostEqtypeOrderAcceptParams,l_strStatus);
       log.exiting(STR_METHOD_NAME);

    }

    /**
     * （処理区分をupdateする）。
     * @@param l_hostEqtypeOrderAcceptParams 株式注文受付キューParams
     * @@param l_strStatus 処理区分
     * @@roseuid 4042ED5D016E
     */
    public void updateStatus(HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams,String l_strStatus)
       throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "updateStatus(HostEqtypeOrderAcceptParams, String)";
                log.entering(STR_METHOD_NAME);
        if (l_hostEqtypeOrderAcceptParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            l_hostEqtypeOrderAcceptParams.setStatus(l_strStatus);
            l_hostEqtypeOrderAcceptParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_hostEqtypeOrderAcceptParams);
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME,l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify受付時間外)<BR>
     * 【株式注文受付キューテーブル】注文受付のキューデータ一件に対する処理を行う。<BR>
     * （注文受付結果 == "前場受付時間外エラー"の場合）
     * @@param l_params - (株式注文受付キューParams)<BR>
     * 株式注文受付キューParams。
     * @@throws WEB3BaseException
     */
    public void notifyOrderAcceptOvertime(
        HostEqtypeOrderAcceptParams l_params)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyOrderAcceptOvertime(HostEqtypeOrderAcceptParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_orderUnitBefore = l_orderManager.getOrderUnit(
            l_params.getInstitutionCode(),
            l_params.getBranchCode(),
            ProductTypeEnum.EQUITY,
            l_params.getOrderRequestNumber());
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnitBefore.getDataSourceObject();
        EqtypeOrderUnitParams l_orderUnitParams =
            new EqtypeOrderUnitParams(l_orderUnitRow);

        WEB3EquityFrontOrderService l_frontOrderService
            = (WEB3EquityFrontOrderService) Services.getService(WEB3EquityFrontOrderService.class);
        String l_strNextOrderRev
            = l_frontOrderService.getNextOrderRev(l_orderUnitRow.getOrderRev());
        String l_strNextConfirmedOrderRev
            = l_frontOrderService.getNextOrderRev(l_orderUnitRow.getConfirmedOrderRev());
        
        l_orderUnitParams.setOrderRev(l_strNextOrderRev);
        l_orderUnitParams.setConfirmedOrderRev(l_strNextConfirmedOrderRev);
        l_orderUnitParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());
        EqTypeOrderUnit l_orderUnitAfter =
            (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
        
        l_orderManager.updateOrderData(l_orderUnitAfter, false);
        
        l_frontOrderService.updateHostEqtypeOrderAllAtAcceptOvertime(
            l_orderUnitAfter, l_orderUnitBefore, false);
        
        updateStatus(l_params, WEB3HostStatusDef.COMPLETE_PROCESS);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
