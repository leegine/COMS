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
filename	WEB3IfoOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文受付UnitService実装クラス(WEB3IfoOrderAcceptUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/8 艾興 (中訊) 新規作成  
Revesion History : 2006/7/13 唐性峰（中訊）モデルNo.503対応
Revesion History : 2007/01/25 周捷 (中訊) 仕様変更 モデル605、608
Revesion History : 2008/03/17 張騰宇 (中訊)仕様変更 モデル833
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoAcceptedUpdateInterceptor;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptUnitService;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP注文受付UnitServiceImpl)<BR>
 * 先物OP注文受付UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 */
public class WEB3IfoOrderAcceptUnitServiceImpl
    implements WEB3IfoOrderAcceptUnitService
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOrderAcceptUnitServiceImpl.class);

    /**
     * @@roseuid 41AD65460109
     */
    public WEB3IfoOrderAcceptUnitServiceImpl()
    {

    }

    /**
     * (notify注文受付)<BR>
     * 注文受付処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP注文受付）notify注文受付」 参照<BR>
     * @@param l_hostFotypeOrderAcceptParams - 注文受付キューParamsオブジェクト
     * @@roseuid 4190C47100C3
     */
    public void notifyOrderAccept(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyOrderAccept(HostFotypeOrderAcceptParams)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        IfoMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (IfoMarketResponseReceiverCallbackService)l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();

        String l_strInstitutionCode = l_hostFotypeOrderAcceptParams.getInstitutionCode();
        String l_strBranchCode = l_hostFotypeOrderAcceptParams.getBranchCode();
        String l_strRequstCode = l_hostFotypeOrderAcceptParams.getOrderRequestNumber();

        IfoOrderUnit l_orderUnit = null;
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        
        try
        {
            //1.1get注文単位
            l_orderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(
                l_strInstitutionCode,
                l_strBranchCode,
                ProductTypeEnum.IFO,
                l_strRequstCode);

            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_orderUnit.getAccountId(),l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.2先物OP注文受付更新インタセプタ
        WEB3IfoAcceptedUpdateInterceptor l_ifoAcceptedUndateInterceptor =
            new WEB3IfoAcceptedUpdateInterceptor(
                l_hostFotypeOrderAcceptParams.getErrorMessage(),
                l_hostFotypeOrderAcceptParams.getSubmitOrderRouteDiv());

        //1.3setThreadLocalPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_ifoAcceptedUndateInterceptor);
        long l_lngOrderId = l_orderUnit.getOrderId();

        //1.4受付完了の場合
        if (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(
            l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
            //1.4.1 DefaultNewOrderAcceptedMarketResponseMessage(long)(
            DefaultNewOrderAcceptedMarketResponseMessage l_defaultNewOrderAcceptedMarketResponseMessage =
                new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
            
            //1.4.2 process(受付結果 : NewOrderAcceptedMarketResponseMessage)
            ProcessingResult l_prcessResult = 
                l_marketResponseReceiverCallbackService.process(
                    l_defaultNewOrderAcceptedMarketResponseMessage);
            if (l_prcessResult.isFailedResult())
            {
                log.error("l_prcessResult.isFailedResult()");
                throw new WEB3BusinessLayerException(
                    l_prcessResult.getErrorInfo(),
                    STR_METHOD_NAME);
            }   

        }
        else
        {
            //1.5.1 DefaultNewOrderRejectedMarketResponseMessage
            DefaultNewOrderRejectedMarketResponseMessage l_defaultNewOrderRejectedMarketResponseMessage = 
                new DefaultNewOrderRejectedMarketResponseMessage(l_lngOrderId);

            //1.5.2 process(受付結果 : NewOrderRejectedMarketResponseMessage)
            ProcessingResult l_prcessResult = 
                l_marketResponseReceiverCallbackService.process(
                    l_defaultNewOrderRejectedMarketResponseMessage);
            if (l_prcessResult.isFailedResult())
            {
                log.error("l_prcessResult.isFailedResult()");
                throw new WEB3BusinessLayerException(
                    l_prcessResult.getErrorInfo(),
                    STR_METHOD_NAME);
            }   

            //is予約注文確認要(IfoOrderUnit)
            boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist(l_orderUnit);
            //予約注文確認要（is予約注文確認要() == true）の場合
            if (l_blnIsReserveOrderExist)
            {
                WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);
                // invalidateAll予約注文単位(親注文の注文ID : long)
                l_ifoOrderUpdateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }
            
            //1.5.4 余力再計算(補助口座 : 補助口座)
            //  [引数] 
            //  補助口座： get補助口座()の戻り値 
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
				(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            //補助口座の補助口座タイプ != 7（証拠金口座）の場合、実施
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
				l_tpTradingPowerReCalcService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
            }
            
            IfoOrderUnit l_ifoOrderUnit = null;
            try
            {
                l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            } 
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    getClass().getName() + STR_METHOD_NAME);
            }                 
            try
            {
                //reset銘柄コード
                String l_strProductCode =
                    ((IfoProductRow)l_orderUnit.getProduct().getDataSourceObject()).getUnderlyingProductCode();
                WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);
                
                //1.5.5 notifyルールエンジンサーバ(IfoOrderUnit, OrderManagerPersistenceContext)
                l_orderManager.notifyRLS(l_ifoOrderUnit, OrderManagerPersistenceContext.ORDER_REJECTED_BY_MKT );
            }
            //1.5.6 notifyルールエンジンサーバ()にて業務エラーがスローされた場合
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify受付時間外)<BR>
     * 受付時間外処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP注文受付）notify受付時間外」 参照<BR>
     * @@param l_hostFotypeOrderAcceptParams - 注文受付キューParamsオブジェクト
     */
    public void notifyOrderAcceptOvertime(
        HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyOrderAcceptOvertime(HostFotypeOrderAcceptParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostFotypeOrderAcceptParams == null)
        {
            log.debug(STR_METHOD_NAME + "パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //get注文単位(String, String, ProductTypeEnum, String)
        //[引数]
        //証券会社コード　@：注文受付キュー.証券会社コード
        //部店コード　@：注文受付キュー.部店コード
        //商品タイプ　@：”先物オプション”
        //識別コード　@：注文受付キュー.識別コード
        IfoOrderUnit l_ifoOrderUnit =
            (IfoOrderUnit)l_orderManager.getOrderUnit(
                l_hostFotypeOrderAcceptParams.getInstitutionCode(),
                l_hostFotypeOrderAcceptParams.getBranchCode(),
                ProductTypeEnum.IFO,
                l_hostFotypeOrderAcceptParams.getOrderRequestNumber());

        //update用の注文単位オブジェクトcloneを生成する。
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams(l_ifoOrderUnitRow);

        WEB3IfoFrontOrderService l_service =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

        //注文Rev：　@先物OP発注サービス.getNext注文Rev（注文単位.注文Rev.）
        l_ifoOrderUnitParams.setOrderRev(
            l_service.getNextOrderRev(l_ifoOrderUnitRow.getOrderRev()));

        //市場から確認済の注文Rev：　@先物OP発注サービス.getNext注文Rev（
        //  注文単位.市場から確認済みの注文Rev.）
        l_ifoOrderUnitParams.setConfirmedOrderRev(
            l_service.getNextOrderRev(l_ifoOrderUnitRow.getConfirmedOrderRev()));

        //更新日付：　@GtlUtils.getTradingSystem().getSystemTimestamp()（現在日時）
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        IfoOrderUnit l_ifoOrderUnitClone =
            (IfoOrderUnit)l_orderManager.toOrderUnit(l_ifoOrderUnitParams);

        //update注文データ(IfoOrderUnit, boolean)
        //[引数]
        // 注文単位：　@作成した注文単位のcloneオブジェクト
        // is履歴作成：　@false（履歴を作成しない）
        l_orderManager.updateOrderData(l_ifoOrderUnitClone, false);

        //update先物OP注文取引キューAT受付時間外(IfoOrderUnit, IfoOrderUnit, boolean)
        //[引数]
        // 注文単位（更新後）：　@作成した注文単位のcloneオブジェクト
        // 注文単位（更新前）：　@OP注文マネージャ.get注文単位()で取得した注文単位オブジェクト
        // is取消：　@false（取消以外）
        l_service.updateHostFotypeOrderAllAtAcceptOvertime(l_ifoOrderUnitClone, l_ifoOrderUnit, false);

        log.exiting(STR_METHOD_NAME);
    }
}
@
