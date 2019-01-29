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
filename	WEB3MarginSwapMarginAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡受付一件サービス実装(WEB3MarginSwapMarginAcceptUnitServiceImpl.java)
Author Name      : 2004/10/8 盧法@旭(中訊) 新規作成
Revesion History : 2004/12/13 岡村和明(SAR) 残案件対応 Ｎｏ.２５９＆Ｎｏ.３５５
                   2004/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3MarginSwapMarginAcceptInterceptor;
import webbroker3.equity.data.HostEqtypeSwapAcceptParams;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptUnitService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import java.util.Map;
import com.fitechlabs.xtrade.kernel.data.Processors;
import java.util.HashMap;
import webbroker3.common.define.WEB3StatusDef;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

/**
 * （信用取引現引現渡受付一件サービスImpl）。<BR>
 * <BR>
 * 信用取引現引現渡受付一件サービス実装クラス
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptUnitServiceImpl implements WEB3MarginSwapMarginAcceptUnitService
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginSwapMarginAcceptUnitServiceImpl.class);

    /**
     * @@roseuid 41419C070008
     */
    public WEB3MarginSwapMarginAcceptUnitServiceImpl()
    {

    }

    /**
     * (notify現引現渡受付)<BR>
     *【現引現渡受付キューテーブル】キューデータ一件に対する処理を行う。
     * @@param l_swapMarginAcceptQueParams - (現引現渡受付キューParams)<BR>
     * @@roseuid 41010FA900A1
     */
    public void notifySwapMarginAccept(HostEqtypeSwapAcceptParams l_hostEqtypeSwapAcceptParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifySwapMarginAccept";
        log.entering(STR_METHOD_NAME);
        if (l_hostEqtypeSwapAcceptParams == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.3.1.1get注文単位(証券会社コード : String, 部店コード : String, 商品タイプ : ProductTypeEnum, 識別コード : String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        String l_strInstitutionCode = l_hostEqtypeSwapAcceptParams.getInstitutionCode();
        String l_strBranchCode = l_hostEqtypeSwapAcceptParams.getBranchCode();
        String l_strRequestCode = l_hostEqtypeSwapAcceptParams.getOrderRequestNumber();
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tm.getOrderManager();
        OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.EQUITY, l_strRequestCode);
		//補助口座の取得
		WEB3GentradeSubAccount l_subAccount;
		try{
			l_subAccount =(WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
					l_orderUnit.getAccountId(),
					l_orderUnit.getSubAccountId());
		}
		catch (NotFoundException l_nfe)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(), l_nfe);
		}

        //1.3.1.2get orderUnits
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if (OrderStatusEnum.CANCELLED.equals(l_orderStatus) || OrderStatusEnum.NOT_CANCELLED.equals(l_orderStatus))
        {
            //一件処理内部へ移す.start
            try{
                QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
                Map l_mapChanges = new HashMap();
                log.debug("処理対象キューレコードをupdateする");
                l_mapChanges.put("status", WEB3StatusDef.DEALT);
                l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                l_QueryProcessor.doUpdateQuery(l_hostEqtypeSwapAcceptParams.getPrimaryKey(),
                                               l_mapChanges);
                //.end
                log.exiting(STR_METHOD_NAME);
            }
            catch (DataNetworkException l_dne)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            catch (DataQueryException l_dne)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            return;
        }
        long l_lngOrderId = l_orderUnit.getOrderId();
        //1.3.1.3現渡受付インタセプタ(現引現渡受付キューParams : 現引現渡受付キューParams)
        WEB3MarginSwapMarginAcceptInterceptor l_interceptor = new WEB3MarginSwapMarginAcceptInterceptor(l_hostEqtypeSwapAcceptParams);
        //1.3.1.4 setThreadLocalPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        //1.3.1.5)新規注文の場合
        if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus))
        {
            //1.3.1.5.1注文受付完了の場合
            if (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(l_hostEqtypeSwapAcceptParams.accept_status))
            {
                //1.3.1.5.1.1DefaultNewOrderAcceptMarketResponseMessage(注文ID : long)
                DefaultNewOrderAcceptedMarketResponseMessage l_message = new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
                //1.3.1.5.1.2 process
                ProcessingResult l_result = l_tm.getMarketAdapter().getMarketResponseReceiverCallbackService().process(l_message);
                if (l_result.isFailedResult())
                {
                    WEB3BaseException l_baseException = new WEB3BaseException(l_result.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME, l_result.getErrorInfo().getErrorMessage());
                    log.error(STR_METHOD_NAME, l_baseException);
                    throw l_baseException;
                }
            }
            //1.3.1.5.2注文受付エラーの場合
            if (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(l_hostEqtypeSwapAcceptParams.accept_status))
            {
                //1.3.1.5.2.1DefaultNewOrderRejectedMarketResponseMessage(注文ID : long)
                DefaultNewOrderRejectedMarketResponseMessage l_message = new DefaultNewOrderRejectedMarketResponseMessage(l_lngOrderId);
                //1.3.1.5.2.2 process
                ProcessingResult l_result = l_tm.getMarketAdapter().getMarketResponseReceiverCallbackService().process(l_message);
                
                if (l_orderManager.isReserveOrderConfirmRequire((EqTypeOrderUnit)l_orderUnit))
                {
                    WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                        (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                            WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                    l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
                }
                
                //余力再計算(補助口座 : 補助口座)
				log.debug("余力再計算を行う");
				WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
					(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
				l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                if (l_result.isFailedResult())
                {
                    WEB3BaseException l_baseException = new WEB3BaseException(l_result.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME, l_result.getErrorInfo().getErrorMessage());
                    log.error(STR_METHOD_NAME, l_baseException);
                    throw l_baseException;
                }
            }
        }
        //取消注文の場合
        else if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus))
        {
            //1.3.1.6.1注文受付完了の場合
            if (WEB3AcceptStatusDef.OVER.equals(l_hostEqtypeSwapAcceptParams.accept_status))
            {
                //1.3.1.6.1.1DefaultCancelOrderSentMarketResponseMessage(注文ID : long)
                DefaultCancelOrderSentMarketResponseMessage l_message = new DefaultCancelOrderSentMarketResponseMessage(l_lngOrderId);
                //1.3.1.6.1.2 process
                ProcessingResult l_result = l_tm.getMarketAdapter().getMarketResponseReceiverCallbackService().process(l_message);
                if (l_result.isFailedResult())
                {
                    WEB3BaseException l_baseException = new WEB3BaseException(l_result.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME, l_result.getErrorInfo().getErrorMessage());
                    log.error(STR_METHOD_NAME, l_baseException);
                    throw l_baseException;
                }
            }
            //1.3.1.6.2注文受付エラーの場合
            if (WEB3AcceptStatusDef.ERROR.equals(l_hostEqtypeSwapAcceptParams.accept_status))
            {
                //1.3.1.6.2.1DefaultCancelOrderRejectedMarketResponseMessage(注文ID : long)
                DefaultCancelOrderRejectedMarketResponseMessage l_message = new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);
                //1.3.1.6.2.2 process
                ProcessingResult l_result = l_tm.getMarketAdapter().getMarketResponseReceiverCallbackService().process(l_message);
				//余力再計算(補助口座 : 補助口座)
				log.debug("余力再計算を行う");
				WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
					(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
				l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                if (l_result.isFailedResult())
                {
                    WEB3BaseException l_baseException = new WEB3BaseException(l_result.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME, l_result.getErrorInfo().getErrorMessage());
                    log.error(STR_METHOD_NAME, l_baseException);
                    throw l_baseException;
                }
            }
        }
        //一件処理内部へ移す.start
        try{
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            Map l_mapChanges = new HashMap();
            log.debug("処理対象キューレコードをupdateする");
            l_mapChanges.put("status", WEB3StatusDef.DEALT);
            l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            l_QueryProcessor.doUpdateQuery(l_hostEqtypeSwapAcceptParams.getPrimaryKey(),
                                           l_mapChanges);
            //.end
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dne)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

    }
}
@
