head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeClosingContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）OP訂正返済サービスImpl（WEB3ToSuccOptionChangeClosingContractServiceImpl.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 孟亞南(中訊) 新規作成 モデルNo.307
Revision History : 2008/04/22 孟亞南(中訊) 仕様変更 モデルNo.336
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCommonRequest;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccIfoChangeSettleContractOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeClosingContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）OP訂正返済サービスImpl)<BR>
 * （連続）OP訂正返済サービス実装クラス<BR>
 *
 * @@author 孟亞南(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeClosingContractServiceImpl
    extends WEB3ToSuccOptionSettleContractOrderServiceImpl
    implements WEB3ToSuccOptionChangeClosingContractService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeClosingContractServiceImpl.class);

    /**
     * @@roseuid 47FDBE40030D
     */
    public WEB3ToSuccOptionChangeClosingContractServiceImpl()
    {

    }

    /**
     * （連続）株価指数オプション訂正返済サービス処理を実施する。<BR>
     * <BR>
     * 　@リクエストデータの型により、以下のメソッドを呼び分ける。 <BR>
     * <BR>
     * 　@　@[（連続）株価指数オプション訂正返済確認リクエストの場合] <BR>
     * 　@　@　@this.validate注文()をコールする。 <BR>
     * 　@　@[（連続）株価指数オプション訂正返済完了リクエストの場合] <BR>
     * 　@　@　@this.submit注文()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A92B440218
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        // [（連続）株価指数オプション訂正返済確認リクエストの場合]
        if (l_request instanceof WEB3SuccOptionsCloseChangeConfirmRequest)
        {
            // this.validate注文()をコールする。
            l_response = this.validateOrder((WEB3SuccOptionsCloseChangeConfirmRequest)l_request);
        }
        // [（連続）株価指数オプション訂正返済完了リクエストの場合]
        else if (l_request instanceof WEB3SuccOptionsCloseChangeCompleteRequest)
        {
            // this.submit注文()をコールする。
            l_response = this.submitOrder((WEB3SuccOptionsCloseChangeCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate注文)<BR>
     * （連続）株価指数オプションの訂正返済発注審査を行う。<BR>
     * <BR>
     * 「（（連続）OP訂正返済サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccOptionsCloseChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A92B440228
     */
    protected WEB3SuccOptionsCloseChangeConfirmResponse validateOrder(
        WEB3SuccOptionsCloseChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccOptionsCloseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // リクエストデータの整合性をチェックする。
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // get先物OP予約注文単位(long)
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        // validateリクエストデータat反対取引(WEB3GenRequest, 先物OP予約注文単位Impl)
        this.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);

        // validate訂正可能状態( )
        l_ifoOrderUnit.validateOrderForChangeability();

        // validate決済済建玉(先物OP予約注文単位Impl)
        this.validateSettledContract(l_ifoOrderUnit);

        // create確認リクエスト(（連続）株価指数オプション訂正返済確認リクエスト, 先物OP予約注文単位Impl)
        WEB3SuccOptionsCloseConfirmRequest l_confirmRequest =
            this.createConfirmRequest(l_request, l_ifoOrderUnit);

        // validate注文(（連続）株価指数オプション返済注文確認リクエスト)
        WEB3SuccOptionsCloseConfirmResponse l_confirmResponse = super.validateOrder(l_confirmRequest);

        // validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
        l_orderManager.validateEveningSessionOrderPossibleChange(l_request.expirationDateType, l_ifoOrderUnit);

        // createResponse( )
        WEB3SuccOptionsCloseChangeConfirmResponse l_response =
            (WEB3SuccOptionsCloseChangeConfirmResponse)l_request.createResponse();

        // 建玉明細：　@super.validate注文()のレスポンスから同名プロパティをセット
        l_response.contractUnits = l_confirmResponse.contractUnits;

        // 概算受渡代金：　@super.validate注文()のレスポンスから同名プロパティをセット
        l_response.estimatedPrice = l_confirmResponse.estimatedPrice;

        // 手数料コース：　@super.validate注文()のレスポンスから同名プロパティをセット
        l_response.commissionCourse = l_confirmResponse.commissionCourse;

        // 手数料：　@super.validate注文()のレスポンスから同名プロパティをセット
        l_response.commission = l_confirmResponse.commission;

        // 手数料消費税：　@super.validate注文()のレスポンスから同名プロパティをセット
        l_response.commissionConsumptionTax = l_confirmResponse.commissionConsumptionTax;

        // 取引終了警告文言：　@super.validate注文()のレスポンスから同名プロパティをセット
        l_response.messageSuspension = l_confirmResponse.messageSuspension;

        // 確認時単価：　@super.validate注文()のレスポンスから同名プロパティをセット
        l_response.checkPrice = l_confirmResponse.checkPrice;

        // 確認時発注日：　@super.validate注文()のレスポンスから同名プロパティをセット
        l_response.checkDate = l_confirmResponse.checkDate;

        // 注文有効期限：　@super.validate注文()のレスポンスから同名プロパティをセット
        l_response.expirationDate = l_confirmResponse.expirationDate;

        // 調整後単価 ：
        // リクエスト.単価調整値情報≠null（±指値指定）の場合
        if (l_request.priceAdjustmentValueInfo != null)
        {
            // super.validate注文()のレスポンスから同名プロパティをセット
            l_response.afterAdjustmentPrice = l_confirmResponse.afterAdjustmentPrice;
        }
        else
        {
            l_response.afterAdjustmentPrice = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * （連続）株価指数オプションの訂正返済注文を登録する。<BR>
     * <BR>
     * 「（（連続）OP訂正返済サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccOptionsCloseChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A92B440237
     */
    protected WEB3SuccOptionsCloseChangeCompleteResponse submitOrder(
        WEB3SuccOptionsCloseChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccOptionsCloseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // リクエストデータの整合性をチェックする。
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // get先物OP予約注文単位(long)
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        // validateリクエストデータat反対取引(WEB3GenRequest, 先物OP予約注文単位Impl)
        this.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);

        // validate訂正可能状態( )
        l_ifoOrderUnit.validateOrderForChangeability();

        // validate決済済建玉(先物OP予約注文単位Impl)
        this.validateSettledContract(l_ifoOrderUnit);

        // create完了リクエスト(（連続）株価指数オプション訂正返済完了リクエスト, 先物OP予約注文単位Impl)
        WEB3SuccOptionsCloseCompleteRequest l_completeRequest =
            this.createCompleteRequest(l_request, l_ifoOrderUnit);

        // submit注文(（連続）株価指数オプション返済完了リクエスト)
        WEB3SuccOptionsCloseCompleteResponse l_completeResponse = super.submitOrder(l_completeRequest);

        // validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
        l_orderManager.validateEveningSessionOrderPossibleChange(l_request.expirationDateType, l_ifoOrderUnit);

        // createリクエストアダプタ
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
            this.createRequestAdapter(l_completeRequest);

        // create返済建玉エントリ(先物返済注文リクエストアダプタ, 返済建玉[])
        SettleContractEntry[] l_settleContractEntries =
            this.createSettleContractEntry(l_requestAdapter, l_request.closeMarginContractUnits);

        // 訂正後指値
        double l_dblModifiedLimitPrice = 0.0D;
        // リクエストデータ.注文単価区分 != "成行"の場合
        if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblModifiedLimitPrice = Double.parseDouble(l_request.limitPrice);
        }

        // 訂正後注文失効日
        Date l_datModifiedExpirationDate = null;
        // リクエストデータ.注文期限区分 == "出来るまで注文"の場合
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_datModifiedExpirationDate = l_request.expirationDate;
        }
        else
        {
            l_datModifiedExpirationDate = l_request.checkDate;
        }

        // 代理入力者
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();

        // 訂正後単価調整値
        Double l_modifiedPriceAdjustValue = null;
        // リクエストデータ.単価調整値情報 != nullの場合
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_modifiedPriceAdjustValue =
                new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        // create先物OP予約返済注文訂正内容(long, double, SettleContractEntry[],
        //  double, double, Date, 扱者, Double, String)
        WEB3ToSuccIfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec =
            WEB3ToSuccIfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                l_ifoOrderUnit.getOrderId(),
                l_dblModifiedLimitPrice,
                l_settleContractEntries,
                Double.parseDouble(l_request.estimatedPrice),
                Double.parseDouble(l_request.checkPrice),
                l_datModifiedExpirationDate,
                l_trader,
                l_modifiedPriceAdjustValue,
                l_request.expirationDateType);

        // submit先物OP訂正予約返済注文(SubAccount, 先物OP予約返済注文訂正内容, String, 先物OP予約注文単位Impl)
        l_orderManager.submitIfoChangeSettleContractOrder(
            this.getSubAccount(),
            l_changeSettleContractOrderSpec,
            l_request.password,
            l_ifoOrderUnit);

        // createResponse( )
        WEB3SuccOptionsCloseChangeCompleteResponse l_response =
            (WEB3SuccOptionsCloseChangeCompleteResponse)l_request.createResponse();

        // 更新時間：　@　@super.submit注文()のレスポンスから同名プロパティをセット
        l_response.lastUpdatedTimestamp = l_completeResponse.lastUpdatedTimestamp;

        // 識別番号：　@　@super.submit注文()のレスポンスから同名プロパティをセット
        l_response.orderActionId = l_completeResponse.orderActionId;

        // 連続注文設定フラグ：　@false（固定）
        l_response.succSettingFlag = false;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validateリクエストデータat反対取引)<BR>
     * 反対取引指定時に固有の、リクエストデータのプロパティチェックを行う。 <BR>
     * <BR>
     * １）　@パラメータ.予約注文単位.is反対売買取引()==false <BR>
     * 　@　@　@（＝反対取引でない）の場合は、 <BR>
     * 　@　@　@リクエストデータ.validateAT既存残取引()をコールし、 <BR>
     * 　@　@　@returnする。 <BR>
     * <BR>
     * ２）　@リクエストデータ.validateAT反対取引()をコールする。 <BR>
     * <BR>
     * ３）　@パラメータ.予約注文単位.決済順序 != null　@かつ <BR>
     * 　@パラメータ.予約注文単位.決済順序 == "ランダム"の <BR>
     * 　@場合、以下の処理を実施する。 <BR>
     * 　@３－１）　@リクエストデータ.返済建玉の要素数分、 <BR>
     * 　@　@以下の処理を繰り返す。 <BR>
     * 　@　@３－１－１）　@返済建玉.数量が以下のいずれかの場合は、 <BR>
     * 　@　@　@　@　@「返済建玉の数量指定が不正」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@・null  <BR>
     * 　@　@　@　@　@　@・数字以外  <BR>
     * 　@　@　@　@　@　@・０以下の数字  <BR>
     * 　@　@　@　@　@　@・８桁を超える数字  <BR>
     * 　@　@class：WEB3BusinessLayerException<BR>
     * 　@　@　@tag：BUSINESS_ERROR_03060<BR>
     * <BR>
     * 　@　@※引数のリクエストデータは、以下のいずれかにキャストすること。 <BR>
     * 　@　@　@・（連続）株価指数オプション訂正返済確認リクエスト<BR>
     * 　@　@　@・（連続）株価指数オプション訂正返済完了リクエスト<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_rsvOrderUnit - (予約注文単位)<BR>
     * 予約注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47EC51930331
     */
    protected void validateRequestDataAtReversingTrade(
        WEB3GenRequest l_request, WEB3ToSuccIfoOrderUnitImpl l_rsvOrderUnit) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "validateRequestDataAtReversingTrade(WEB3GenRequest, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null || l_rsvOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3SuccOptionsCloseChangeConfirmRequest l_confirmRequest = null;
        WEB3SuccOptionsCloseChangeCompleteRequest l_completeRequest = null;
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits = null;
        if (l_request instanceof WEB3SuccOptionsCloseChangeConfirmRequest)
        {
            l_confirmRequest = (WEB3SuccOptionsCloseChangeConfirmRequest)l_request;
            l_closeMarginContractUnits = l_confirmRequest.closeMarginContractUnits;
        }
        else if (l_request instanceof WEB3SuccOptionsCloseChangeCompleteRequest)
        {
            l_completeRequest = (WEB3SuccOptionsCloseChangeCompleteRequest)l_request;
            l_closeMarginContractUnits = l_completeRequest.closeMarginContractUnits;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        // パラメータ.予約注文単位.is反対売買取引()==false（＝反対取引でない）の場合
        if (!l_rsvOrderUnit.isReversingTrade())
        {
            // リクエストデータ.validateAT既存残取引()をコールし、returnする。
            if (l_confirmRequest != null)
            {
                l_confirmRequest.validateATExistingRemainderTrading();
            }
            else if (l_completeRequest != null)
            {
                l_completeRequest.validateATExistingRemainderTrading();
            }

            log.exiting(STR_METHOD_NAME);
            return;
        }

        // リクエストデータ.validateAT反対取引()をコールする。
        if (l_confirmRequest != null)
        {
            l_confirmRequest.validateATReserveOrder();
        }
        else if (l_completeRequest != null)
        {
            l_completeRequest.validateATReserveOrder();
        }

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_rsvOrderUnit.getDataSourceObject();

        // 決済順序
        String l_strClosingOrder = l_rsvIfoOrderUnitRow.getClosingOrder();

        WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = null;

        // パラメータ.予約注文単位.決済順序 != null　@かつ
        // パラメータ.予約注文単位.決済順序 == "ランダム"の場合、以下の処理を実施する。
        if (l_strClosingOrder != null && WEB3ClosingOrderDef.RANDOM.equals(l_strClosingOrder))
        {
            int l_intLength = l_closeMarginContractUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_closeMarginContractUnit = l_closeMarginContractUnits[i];
                String l_strQuantity = l_closeMarginContractUnit.contractOrderQuantity;

                if (l_strQuantity == null || !WEB3StringTypeUtility.isNumber(l_strQuantity)
                    || Long.parseLong(l_strQuantity) <= 0 || l_strQuantity.length() > 8)
                {
                    log.debug("返済建玉の注文数量指定が不正。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03060,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "返済建玉の注文数量指定が不正。");
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create確認リクエスト)<BR>
     * 引数のリクエストデータ、予約注文単位より<BR>
     * （連続）株価指数オプション返済注文確認リクエストを<BR>
     * 作成する。 <BR>
     * <BR>
     * １）　@戻り値のインスタンスを生成する。 <BR>
     * <BR>
     * ２）　@共通プロパティセット。 <BR>
     * 　@this.set株価指数オプション共通入力リクエスト()をコールする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@（output）共通リクエスト：　@１）の戻り値 <BR>
     * 　@　@（input）共通リクエスト：　@パラメータ.リクエストデータ <BR>
     * <BR>
     * ３）　@生成したインスタンス特有のプロパティをセットする。 <BR>
     * 　@返済建玉 = パラメータ.リクエストデータ.返済建玉<BR>
     * 　@決済順序 = 予約注文単位.決済順序<BR>
     * 　@連続注文共通情報 = 予約注文単位.create連続注文共通情報()<BR>
     * 　@単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報 <BR>
     * <BR>
     * ４）　@プロパティセットした確認リクエストを返却する。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_toSuccIfoOrderUnit - (予約注文単位)<BR>
     * 予約注文単位<BR>
     * @@return WEB3SuccOptionsCloseConfirmRequest
     * @@roseuid 47EC5B350281
     */
    protected WEB3SuccOptionsCloseConfirmRequest createConfirmRequest(
        WEB3SuccOptionsCloseChangeConfirmRequest l_request,
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit)
    {
        final String STR_METHOD_NAME =
            "createConfirmRequest(WEB3SuccOptionsCloseChangeConfirmRequest, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        // 戻り値のインスタンスを生成する。
        WEB3SuccOptionsCloseConfirmRequest l_confirmRequest = new WEB3SuccOptionsCloseConfirmRequest();

        // 共通プロパティセット。
        // this.set株価指数オプション共通入力リクエスト()をコールする。
        this.setOptionsCommonRequest(l_confirmRequest, l_request);

        // 生成したインスタンス特有のプロパティをセットする。
        // 返済建玉 = パラメータ.リクエストデータ.返済建玉
        l_confirmRequest.closeMarginContractUnits = l_request.closeMarginContractUnits;

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnit.getDataSourceObject();

        // 決済順序 = 予約注文単位.決済順序
        l_confirmRequest.closingOrder = l_rsvIfoOrderUnitRow.getClosingOrder();

        // 連続注文共通情報 = 予約注文単位.create連続注文共通情報()
        l_confirmRequest.succCommonInfo = l_toSuccIfoOrderUnit.createSuccCommonInfo();

        // 単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報
        l_confirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        log.exiting(STR_METHOD_NAME);
        return l_confirmRequest;
    }

    /**
     * (create完了リクエスト)<BR>
     * 引数のリクエストデータ、予約注文単位より<BR>
     * （連続）株価指数オプション返済注文完了リクエストオブジェクトを <BR>
     * 作成する。 <BR>
     * <BR>
     * １）　@戻り値のインスタンスを生成する。 <BR>
     * <BR>
     * ２）　@共通プロパティセット。 <BR>
     * 　@this.set株価指数オプション共通入力リクエスト()をコールする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@（output）共通リクエスト：　@１）の戻り値 <BR>
     * 　@　@（input）共通リクエスト：　@パラメータ.リクエストデータ <BR>
     * <BR>
     * ３）　@生成したインスタンス特有のプロパティをセットする。<BR>
     * 　@注文ID = 予約注文単位.注文ID<BR>
     * 　@返済建玉 = パラメータ.リクエストデータ.返済建玉<BR>
     * 　@決済順序 = 予約注文単位.決済順序<BR>
     * 　@暗証番号 = パラメータ.リクエストデータ.暗証番号<BR>
     * 　@確認時単価 = パラメータ.リクエストデータ.確認時単価<BR>
     * 　@確認時発注日 = パラメータ.リクエストデータ.確認時発注日<BR>
     * 　@連続注文共通情報 = 予約注文単位.create連続注文共通情報()<BR>
     * 　@単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報<BR>
     * 　@調整後単価 = パラメータ.リクエストデータ.調整後単価<BR>
     * <BR>
     * ４）　@プロパティセットした完了リクエストを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_toSuccIfoOrderUnit - (予約注文単位)<BR>
     * 予約注文単位<BR>
     * @@return WEB3SuccOptionsCloseCompleteRequest
     * @@roseuid 47EC5B350291
     */
    protected WEB3SuccOptionsCloseCompleteRequest createCompleteRequest(
        WEB3SuccOptionsCloseChangeCompleteRequest l_request, WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit)
    {
        final String STR_METHOD_NAME =
            "createCompleteRequest(WEB3SuccOptionsCloseChangeCompleteRequest, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        // 戻り値のインスタンスを生成する。
        WEB3SuccOptionsCloseCompleteRequest l_completeRequest = new WEB3SuccOptionsCloseCompleteRequest();

        // 共通プロパティセット。
        // this.set株価指数オプション共通入力リクエスト()をコールする。
        this.setOptionsCommonRequest(l_completeRequest, l_request);

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnit.getDataSourceObject();

        // 生成したインスタンス特有のプロパティをセットする。
        // 注文ID = 予約注文単位.注文ID
        l_completeRequest.orderId = String.valueOf(l_rsvIfoOrderUnitRow.getOrderId());

        // 返済建玉 = パラメータ.リクエストデータ.返済建玉
        l_completeRequest.closeMarginContractUnits = l_request.closeMarginContractUnits;

        // 決済順序 = 予約注文単位.決済順序
        l_completeRequest.closingOrder = l_rsvIfoOrderUnitRow.getClosingOrder();

        // 暗証番号 = パラメータ.リクエストデータ.暗証番号
        l_completeRequest.password = l_request.password;

        // 確認時単価 = パラメータ.リクエストデータ.確認時単価
        l_completeRequest.checkPrice = l_request.checkPrice;

        // 確認時発注日 = パラメータ.リクエストデータ.確認時発注日
        l_completeRequest.checkDate = l_request.checkDate;

        // 連続注文共通情報 = 予約注文単位.create連続注文共通情報()
        l_completeRequest.succCommonInfo = l_toSuccIfoOrderUnit.createSuccCommonInfo();

        // 単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報
        l_completeRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        // 調整後単価 = パラメータ.リクエストデータ.調整後単価
        l_completeRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;

        log.exiting(STR_METHOD_NAME);
        return l_completeRequest;
    }

    /**
     * (create返済建玉エントリ)<BR>
     * 返済建玉エントリを作成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@連続注文マネージャImpl.create返済建玉エントリ()をコールし、 <BR>
     * 　@戻り値を返却する。 <BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@返済建玉：　@生成した返済建玉(*1) <BR>
     * <BR>
     * 　@(*1)以下のプロパティをセットした返済建玉インスタンス <BR>
     * 　@　@のみを要素とする配列 <BR>
     * <BR>
     * 　@　@返済建玉.注文数量：　@this.get注文数量()の戻り値を設定する。 <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@リクエストアダプタ：　@パラメータ.リクエストアダプタ <BR>
     * <BR>
     * ２）　@１）以外の場合、 <BR>
     * 　@OP注文マネージャ.create返済建玉エントリ()をコールし、 <BR>
     * 　@戻り値を返却する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@注文単位ID：　@-1（予約注文未発注の状態を表す。）<BR>
     * 　@　@注文数量：　@パラメータ.リクエストアダプタ.get注文数量()戻り値 <BR>
     * 　@　@返済建玉[]：　@パラメータ.返済建玉 <BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@param l_closeMarginContractUnits - (返済建玉オブジェクトの配列)<BR>
     * （リクエストデータ）
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 47ECC7070380
     */
    protected SettleContractEntry[] createSettleContractEntry(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry("
            + "WEB3OptionSettleContractOrderRequestAdapter, WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;

        SettleContractEntry[] l_settleContractEntries = null;

        // 反対売買の場合
        if (l_adapter.isReversingOrder())
        {
            // this.get注文数量()の戻り値を設定する。
            double l_dblOrderQuantity = this.getOrderQuantity(l_adapter);

            int l_intLength = l_closeMarginContractUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_closeMarginContractUnits[i].contractOrderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);
            }

            // 連続注文マネージャImplを取得
            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

            // 連続注文マネージャImpl.create返済建玉エントリ()
            l_settleContractEntries =
                l_orderManager.createSettleContractEntry(l_closeMarginContractUnits);
        }
        // 以外の場合
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

            // OP注文マネージャを取得
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

            // OP注文マネージャ.create返済建玉エントリ()
            l_settleContractEntries =
                l_orderManager.createSettleContractEntry(
                -1,
                l_adapter.getOrderQuantity(),
                l_closeMarginContractUnits);
        }

        log.exiting(STR_METHOD_NAME);
        return l_settleContractEntries;
    }

    /**
     * (get注文数量)<BR>
     * 返済建玉に設定する注文数量を返却する。<BR>
     * <BR>
     * １）　@注文数量を求める。<BR>　@
     * 　@　@取得した決済順序≠"ランダム"の場合は、<BR>
     * 　@　@　@　@リクエストアダプタ.リクエストデータ.注文数量 を使用。<BR>
     * 　@　@取得した決済順序=="ランダム"の場合は、<BR>
     * 　@　@　@　@リクエストアダプタ.リクエストデータ.返済建玉の全要素の注文数量のSUM値を使用。<BR>
     * <BR>
     * ２）　@１）で求めた注文数量 > リクエストアダプタ.親注文の注文単位.注文数量の場合、<BR>
     * 　@　@「注文数量が親注文の注文数量を超過」の例外をスローする。<BR>
     * 　@　@　@class：WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag：BUSINESS_ERROR_03065<BR>
     * <BR>
     * ３）　@２）の条件に該当しない場合、１）で求めた注文数量を返却する。<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 47ECC9470042
     */
    private double getOrderQuantity(WEB3ToSuccOptionSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderQuantity(WEB3ToSuccOptionSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        // 注文数量を求める。
        double l_dblOrderQuantity = 0.0D;

        WEB3SuccOptionsCloseConfirmRequest l_confirmRequest = null;
        WEB3SuccOptionsCloseCompleteRequest l_completeRequest = null;
        String l_strClosingOrder = null;
        String l_strfutOrderQuantity = null;
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits = null;

        if (l_requestAdapter.request instanceof WEB3SuccOptionsCloseConfirmRequest)
        {
            l_confirmRequest = (WEB3SuccOptionsCloseConfirmRequest)l_requestAdapter.request;
            l_strClosingOrder = l_confirmRequest.closingOrder;
            l_strfutOrderQuantity = l_confirmRequest.opOrderQuantity;
            l_closeMarginContractUnits = l_confirmRequest.closeMarginContractUnits;
        }
        else if (l_requestAdapter.request instanceof WEB3SuccOptionsCloseCompleteRequest)
        {
            l_completeRequest = (WEB3SuccOptionsCloseCompleteRequest)l_requestAdapter.request;
            l_strClosingOrder = l_completeRequest.closingOrder;
            l_strfutOrderQuantity = l_completeRequest.opOrderQuantity;
            l_closeMarginContractUnits = l_completeRequest.closeMarginContractUnits;
        }

        // 取得した決済順序≠"ランダム"の場合
        if (!WEB3ClosingOrderDef.RANDOM.equals(l_strClosingOrder))
        {
            // リクエストアダプタ.リクエストデータ.注文数量 を使用
            l_dblOrderQuantity = Double.parseDouble(l_strfutOrderQuantity);
        }
        // 取得した決済順序=="ランダム"の場合
        else
        {
            BigDecimal l_bdOrderQuantitySum = new BigDecimal("0");

            int l_intLength = l_closeMarginContractUnits.length;
            // リクエストアダプタ.リクエストデータ.返済建玉の全要素の注文数量のSUM値を使用。
            for (int i = 0; i < l_intLength; i++)
            {
                String l_strOrderQuantity = l_closeMarginContractUnits[i].contractOrderQuantity;
                l_bdOrderQuantitySum = l_bdOrderQuantitySum.add(new BigDecimal(l_strOrderQuantity));
            }

            l_dblOrderQuantity = l_bdOrderQuantitySum.doubleValue();
        }

        // 求めた注文数量 > リクエストアダプタ.親注文の注文単位.注文数量の場合
        if (l_dblOrderQuantity > l_requestAdapter.parentOrderUnit.getQuantity())
        {
            log.debug("注文数量が親注文の注文数量を超過しています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03065,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が親注文の注文数量を超過しています。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblOrderQuantity;
    }

    /**
     * (validate連続注文最大設定数)<BR>
     * 連続注文の最大設定数を超過しないかチェックをする。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位<BR>
     * @@roseuid 47ECC97001F2
     */
    protected void validateSuccOrderMaxQuantity(OrderUnit l_parentOrderUnit)
    {
        return;
    }

    /**
     * (notify予約注文登録)<BR>
     * 予約注文の登録をルールエンジンサーバに通知する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_lngChildOrderId - (子注文の注文ID)<BR>
     * 子注文の注文ID。<BR>
     * @@roseuid 47ECC97F01D0
     */
    protected void notifyRsvOrderRegister(long l_lngChildOrderId)
    {
        return;
    }

    /**
     * (submit返済注文)<BR>
     * OP返済注文を登録する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_orderSpec - (返済注文内容)<BR>
     * 返済注文内容<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (概算受渡代金計算結果)<BR>
     * 概算受渡代金計算結果<BR>
     * @@roseuid 47ECC992018F
     */
    protected void submitSettleContractOrder(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        WEB3GentradeCommission l_commission,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
    {
        return;
    }

    /**
     * (set株価指数オプション共通入力リクエスト)<BR>
     * 指定された「（output）共通リクエスト」のインスタンスに、プロパティをセットする。<BR>
     * <BR>
     * 以下のプロパティに、「（input）共通リクエスト」の同名プロパティの値をセットする。<BR>
     * <BR>
     * [対象プロパティ]<BR>
     * 　@注文数量<BR>
     * 　@注文単価区分<BR>
     * 　@注文単価<BR>
     * 　@執行条件<BR>
     * 　@注文期限区分<BR>
     * 　@注文有効期限<BR>
     * 　@発注条件区分<BR>
     * 　@逆指値用プレミアム／原資産価格<BR>
     * 　@逆指値用発注条件単価<BR>
     * 　@逆指値用発注条件演算子<BR>
     * 　@W指値用プレミアム／原資産価格<BR>
     * 　@W指値用発注条件単価<BR>
     * 　@W指値用発注条件演算子<BR>
     * 　@W指値用注文単価区分<BR>
     * 　@W指値用注文単価<BR>
     * 　@W指値用執行条件<BR>
     * 　@W指値用有効状態区分<BR>
     * @@param l_outputCommonRequest - (（output）共通リクエスト)<BR>
     * 株価指数オプション共通入力リクエスト<BR>
     * @@param l_inputCommonRequest - (（input）共通リクエスト)<BR>
     * 株価指数オプション共通入力リクエスト<BR>
     * @@return 株価指数オプション共通入力リクエスト
     * @@roseuid 47EC5B3502A0
     */
    protected WEB3OptionsCommonRequest setOptionsCommonRequest(
        WEB3OptionsCommonRequest l_outputCommonRequest,
        WEB3OptionsCommonRequest l_inputCommonRequest)
    {
        final String STR_METHOD_NAME =
            "setOptionsCommonRequest(WEB3OptionsCommonRequest, WEB3OptionsCommonRequest)";
        log.entering(STR_METHOD_NAME);

        // 注文数量
        l_outputCommonRequest.opOrderQuantity = l_inputCommonRequest.opOrderQuantity;

        // 注文単価区分
        l_outputCommonRequest.orderPriceDiv = l_inputCommonRequest.orderPriceDiv;

        // 注文単価
        l_outputCommonRequest.limitPrice = l_inputCommonRequest.limitPrice;

        // 執行条件
        l_outputCommonRequest.execCondType = l_inputCommonRequest.execCondType;

        // 注文期限区分
        l_outputCommonRequest.expirationDateType = l_inputCommonRequest.expirationDateType;

        // 注文有効期限
        l_outputCommonRequest.expirationDate = l_inputCommonRequest.expirationDate;

        // 発注条件区分
        l_outputCommonRequest.orderCondType = l_inputCommonRequest.orderCondType;

        //逆指値用プレミアム／原資産価格
        l_outputCommonRequest.stopPremium_underlyingAssets = l_inputCommonRequest.stopPremium_underlyingAssets;

        // 逆指値用発注条件単価
        l_outputCommonRequest.stopOrderCondPrice = l_inputCommonRequest.stopOrderCondPrice;

        // 逆指値用発注条件演算子
        l_outputCommonRequest.stopOrderCondOperator = l_inputCommonRequest.stopOrderCondOperator;

        // W指値用プレミアム／原資産価格
        l_outputCommonRequest.wlimitPremium_underlyingAssets = l_inputCommonRequest.wlimitPremium_underlyingAssets;

        // W指値用発注条件単価
        l_outputCommonRequest.wlimitOrderCondPrice = l_inputCommonRequest.wlimitOrderCondPrice;

        // W指値用発注条件演算子
        l_outputCommonRequest.wlimitOrderCondOperator = l_inputCommonRequest.wlimitOrderCondOperator;

        // W指値用注文単価区分
        l_outputCommonRequest.wLimitOrderPriceDiv = l_inputCommonRequest.wLimitOrderPriceDiv;

        // W指値用注文単価
        l_outputCommonRequest.wLimitPrice = l_inputCommonRequest.wLimitPrice;

        // W指値用執行条件
        l_outputCommonRequest.wlimitExecCondType = l_inputCommonRequest.wlimitExecCondType;

        // W指値用有効状態区分
        l_outputCommonRequest.wlimitEnableStatusDiv = l_inputCommonRequest.wlimitEnableStatusDiv;

        log.exiting(STR_METHOD_NAME);
        return l_outputCommonRequest;
    }

    /**
     * (validate決済済建玉)<BR>
     * 予約決済注文の対象となっている建玉が<BR>
     * 別注文により決済済となっているかどうかチェックする。<BR>
     * <BR>
     * １）　@連続注文マネージャImpl.create建玉明細ByOrder()<BR>
     * 　@をコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@予約注文単位：　@パラメータ.予約注文単位<BR>
     * <BR>
     * 　@nullが返却された場合、<BR>
     * 　@「予約決済対象建玉は別注文により決済済」<BR>
     * 　@の例外をスローする。<BR>
     * 　@class：WEB3BusinessLayerException<BR>
     * 　@　@tag：BUSINESS_ERROR_03066<BR>
     * @@param l_toSuccIfoOrderUnit - (予約注文単位)<BR>
     * 先物OP予約注文単位オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47EC5E7A0247
     */
    protected void validateSettledContract(WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSettledContract(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // 連続注文マネージャImpl.create建玉明細ByOrder()をコールする。
        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            l_orderManager.createIfoContractUnitByOrder(l_toSuccIfoOrderUnit);

        // nullが返却された場合
        if (l_contractUnits == null)
        {
            log.debug("予約決済対象建玉は別注文により決済済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03066,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "予約決済対象建玉は別注文により決済済です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
