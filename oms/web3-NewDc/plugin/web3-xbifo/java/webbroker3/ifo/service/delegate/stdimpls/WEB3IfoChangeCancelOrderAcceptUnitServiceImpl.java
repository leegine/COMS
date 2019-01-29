head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeCancelOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP訂正取消受付UnitService実装クラス(WEB3IfoChangeCancelOrderAcceptUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/8 艾興 (中訊) 新規作成  
Revesion History : 2006/7/14 張騰宇（中訊）モデルNo.504,521対応
Revesion History : 2006/11/28 徐大方（中訊）モデルNo.575対応
Revesion History : 2006/12/20 周捷 (中訊) モデルNo.613
Revesion History : 2007/01/29 齊珂 (中訊) モデルNo.609
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoChangeCancelAcceptedUpdateInterceptor;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.service.delegate.WEB3IfoChangeCancelOrderAcceptUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP訂正取消受付UnitServiceImpl)<BR>
 * 先物OP訂正取消受付UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 */
public class WEB3IfoChangeCancelOrderAcceptUnitServiceImpl
    implements WEB3IfoChangeCancelOrderAcceptUnitService
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeCancelOrderAcceptUnitServiceImpl.class);

    /**
     * @@roseuid 41AD654601A5
     */
    public WEB3IfoChangeCancelOrderAcceptUnitServiceImpl()
    {

    }

    /**
     * (notify訂正取消受付)<BR>
     * 訂正取消受付処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP訂正取消受付）notify訂正取消受付」 参照<BR>
     * @@param l_hostFotypeOrderAcceptParams - 注文受付キューParamsオブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 4190CAC0017E
     */
    public void notifyChangeCancelOrderAccept(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyChangeCancelOrderAccept(HostFotypeOrderAcceptParams)";
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

        try
        {
            //1.1get注文単位
            l_orderUnit =
                (IfoOrderUnit)l_orderManager.getOrderUnit(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    ProductTypeEnum.IFO,
                    l_strRequstCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.2getOrderUnits
        long l_lngOrderId = l_orderUnit.getOrderId();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        OrderUnit l_orderUnitNew = l_orderUnits[0];
        if (OrderStatusEnum.MODIFIED.equals(l_orderUnitNew.getOrderStatus())
            || OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitNew.getOrderStatus())
            || OrderStatusEnum.CANCELLED.equals(l_orderUnitNew.getOrderStatus())
            || OrderStatusEnum.NOT_CANCELLED.equals(l_orderUnitNew.getOrderStatus()))
        {
            return;
        }

        //1.3先物OP訂正取消受付更新インタセプタ(String)(
        WEB3IfoChangeCancelAcceptedUpdateInterceptor l_ifoChangeCancelAcceptedUpdateInterceptor
            = new WEB3IfoChangeCancelAcceptedUpdateInterceptor(l_hostFotypeOrderAcceptParams.getErrorMessage());

        //get補助口座(口座ID : long, 補助口座ID : long)
        //補助口座を取得する。
        //[引数]
        //口座ID：　@注文単位.口座ID
        //補助口座ID：　@注文単位.補助口座ID
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //ストップ注文失効となる注文の場合
        //　@①@受付エラー かつ
        //注文状態 == "受付済（変更注文）" かつ
        //OP注文マネージャ.isストップ注文切替中(注文単位) == trueの場合
        //　@②受付エラー かつ
        //注文状態 == "受付済（取消注文）" かつ
        //先物OPデータアダプタ.getW指値用有効状態区分(注文単位) == "リミット注文有効"の場合
        if ((WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(l_hostFotypeOrderAcceptParams.getAcceptStatus())
            && OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnit.getOrderStatus())
            && l_orderManager.isStopOrderSwitching(l_orderUnit))
            || (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(l_hostFotypeOrderAcceptParams.getAcceptStatus())
                && OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnit.getOrderStatus())
                && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(
                    WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit))))
        {
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            long l_lngProductId = l_ifoOrderUnitRow.getProductId();

            //reset銘柄コード(銘柄コード : String)
            //[引数]
            //　@銘柄コード：　@注文単位.銘柄IDに該当する先物OP銘柄.get原資産銘柄コード()
            try
            {
                WEB3IfoProductImpl l_ifoProduct =
                    (WEB3IfoProductImpl)l_productManager.getProduct(l_lngProductId);
                WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct.getUnderlyingProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //getストップ注文失効時概算代金計算結果(IfoOrderUnit, 補助口座)
            //ストップ注文失効時の概算代金計算結果を取得する。
            //[引数]
            //注文単位：　@注文単位
            //補助口座：　@補助口座
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult =
                l_orderManager.getStopOrderExpireEstimatedPrice(l_orderUnit, l_subAccount);

            //set概算受渡代金計算結果(先物OP概算受渡代金計算結果)
            //概算受渡代金計算結果をセットする。
            //[引数]
            //概算受渡代金計算結果：　@getストップ注文失効時概算代金計算結果()の戻り値
            l_ifoChangeCancelAcceptedUpdateInterceptor.setEstimateDeliveryAmountCalcResult(
                l_estimateDeliveryAmountCalcResult);
        }

        //1.4setThreadLocalPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoChangeCancelAcceptedUpdateInterceptor);        

        //1.5 (*1)訂正受付完了の場合のみ実施
        // (*1) 分岐フロー
        // 注文状態 == OrderStatusEnum.MODIFY_ACCEPTED
        // && 受付完了の場合のみ実施。
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitNew.getOrderStatus()) 
            && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(
                l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
            DefaultChangeOrderSentMarketResponseMessage l_message = 
                new DefaultChangeOrderSentMarketResponseMessage(l_lngOrderId);
            l_marketResponseReceiverCallbackService.process(l_message);
        }

        //1.6 (*2)訂正受付エラーの場合のみ実施
        // (*2) 分岐フロー
        // 注文状態 == OrderStatusEnum.MODIFY_ACCEPTED
        // && 受付エラーの場合のみ実施
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitNew.getOrderStatus()) 
            && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(
                l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
            DefaultChangeOrderRejectedMarketResponseMessage l_message = 
                new DefaultChangeOrderRejectedMarketResponseMessage(l_lngOrderId);
            l_marketResponseReceiverCallbackService.process(l_message);
        }

        //1.7 (*3)取消受付完了の場合のみ実施
        // (*3) 分岐フロー
        // 注文状態 == OrderStatusEnum.CANCEL_ACCEPTED
        // && 受付完了の場合のみ実施
        if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitNew.getOrderStatus()) 
            && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
            DefaultCancelOrderSentMarketResponseMessage l_message = 
                new DefaultCancelOrderSentMarketResponseMessage(l_lngOrderId);
            l_marketResponseReceiverCallbackService.process(l_message);
        }

        //1.8 (*4)取消受付エラーの場合のみ実施
        // (*4) 分岐フロー
        // 注文状態 == OrderStatusEnum.CANCEL_ACCEPTED
        // && 受付エラーの場合のみ実施
        if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitNew.getOrderStatus()) 
            && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(
                l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
            DefaultCancelOrderRejectedMarketResponseMessage l_message = 
                new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);
            l_marketResponseReceiverCallbackService.process(l_message);
        }
        
        //1.9 余力再計算(補助口座 : 補助口座)
        // 補助口座のタイプ != 7（証拠金口座） 
        // && 受付エラー の場合のみ実施
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType())
            && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(
                l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
				(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
			l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        }        

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify訂正取消受付時間外)<BR>
     * 訂正取消受付時間外処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP訂正取消受付）notify訂正取消受付時間外」 参照<BR>
     * @@param l_hostFotypeOrderAcceptParams - (注文受付キューParams)<BR>
     * 注文受付キューParamsオブジェクト
     * @@throws WEB3BaseException
     */
    public void notifyChangeCancelOrderAcceptOvertime(
        HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyChangeCancelOrderAcceptOvertime(HostFotypeOrderAcceptParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostFotypeOrderAcceptParams == null)
        {
            log.debug(STR_METHOD_NAME + "パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        IfoOrderUnit l_ifoUpdateOrderUnit = null;
        boolean l_blnIsCancel = true;

        //1.1 get注文単位(String, String, ProductTypeEnum, String)
        //キューレコードに該当する注文単位を取得する。
        //　@[引数]
        //　@証券会社コード　@：注文受付キュー.証券会社コード
        //　@部店コード　@：注文受付キュー.部店コード
        //　@商品タイプ　@：”先物オプション”
        //　@識別コード　@：注文受付キュー.識別コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //先物OP発注サービス
        WEB3IfoFrontOrderService l_ifoFrontOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

        IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_optionOrderManagerImpl.getOrderUnit(
            l_hostFotypeOrderAcceptParams.getInstitutionCode(),
            l_hostFotypeOrderAcceptParams.getBranchCode(),
            ProductTypeEnum.IFO,
            l_hostFotypeOrderAcceptParams.getOrderRequestNumber());

        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();

        //1.2 分岐フロー：
        // 取得した注文単位.注文状態 == OrderStatusEnum.MODIFY_ACCEPTED(受付済(変更注文))の場合のみ
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus))
        {
            l_blnIsCancel = false;

            //1.2.1 update用の注文単位オブジェクトcloneを生成する
            IfoOrderUnitRow l_ifoUpdateOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            String l_strOrderRev = l_ifoUpdateOrderUnitRow.getOrderRev();

            IfoOrderUnitParams l_params = new IfoOrderUnitParams(l_ifoUpdateOrderUnitRow);

            String l_strNextOrderRev = l_ifoFrontOrderService.getNextOrderRev(l_strOrderRev);

            //プロパティのセット
            l_params.setOrderRev(l_strNextOrderRev);
            l_params.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());

            l_ifoUpdateOrderUnit = (IfoOrderUnit)l_optionOrderManagerImpl.toOrderUnit(l_params);

            //1.2.2 update注文データ(IfoOrderUnit, boolean)
            //[引数]
            //注文単位：　@作成した注文単位のcloneオブジェクト
            //is履歴作成：　@false（履歴を作成しない）
            l_optionOrderManagerImpl.updateOrderData(l_ifoUpdateOrderUnit, false);
        }

        //1.3 update先物OP注文取引キューAT受付時間外(IfoOrderUnit, IfoOrderUnit, boolean)
        l_ifoFrontOrderService.updateHostFotypeOrderAllAtAcceptOvertime(
            l_ifoUpdateOrderUnit,
            l_orderUnit,
            l_blnIsCancel);

        log.exiting(STR_METHOD_NAME);
    }
}
@
