head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeOpenContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）OP訂正新規建サービスImpl（WEB3ToSuccOptionChangeOpenContractServiceImpl.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/11 トウ鋒鋼 (中訊) 新規作成 モデル282
Revision History : 2008/04/14 トウ鋒鋼 (中訊) 仕様変更 モデル317
Revision History : 2008/04/22 トウ鋒鋼 (中訊) 仕様変更 モデル336
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.message.WEB3OptionsCommonRequest;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccIfoChangeOpenContractOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.define.WEB3ToSuccOpProductTypeDef;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）OP訂正新規建サービスImpl)<BR>
 * （連続）オプション訂正新規建サービス実装クラス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeOpenContractServiceImpl
    extends WEB3ToSuccOptionOpenContractOrderServiceImpl
    implements WEB3ToSuccOptionChangeOpenContractService
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeOpenContractServiceImpl.class);

    /**
     * @@roseuid 47FDBE400186
     */
    public WEB3ToSuccOptionChangeOpenContractServiceImpl()
    {

    }

    /**
     * （連続）オプション訂正新規建サービス処理を実施する。<BR>
     * リクエストデータの型により、validate()注文または、submit注文()メソッドをコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A920630056
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
        if (l_request instanceof WEB3SuccOptionsOpenChangeConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccOptionsOpenChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccOptionsOpenChangeCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccOptionsOpenChangeCompleteRequest)l_request);
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
     * （連続）オプション訂正新規建注文の発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）OP訂正新規建サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）株価指数オプション訂正新規建注文確認リクエストデータオブジェクト<BR>
     * @@return WEB3SuccOptionsOpenChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A920630037
     */
    protected WEB3SuccOptionsOpenChangeConfirmResponse validateOrder(
        WEB3SuccOptionsOpenChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccOptionsOpenChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // リクエストデータの整合性をチェックする。
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // get先物OP予約注文単位(long)
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        // validate訂正可能状態( )
        l_toSuccIfoOrderUnit.validateOrderForChangeability();

        // create確認リクエスト(（連続）株価指数オプション訂正新規建確認リクエスト, 先物OP予約注文単位Impl)
        WEB3SuccOptionsOpenConfirmRequest l_confirmRequest =
            this.createConfirmRequest(l_request, l_toSuccIfoOrderUnit);

        // validate注文(（連続）株価指数オプション新規建注文確認リクエスト)
        WEB3SuccOptionsOpenConfirmResponse l_confirmResponse = super.validateOrder(l_confirmRequest);

        // validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
        l_orderManager.validateEveningSessionOrderPossibleChange(
            l_request.expirationDateType, l_toSuccIfoOrderUnit);

        // createResponse( )
        WEB3SuccOptionsOpenChangeConfirmResponse l_response =
            (WEB3SuccOptionsOpenChangeConfirmResponse)l_request.createResponse();

        // 内約定数量 ： 0(固定)
        l_response.partExecQuantity = "0";

        // 概算受渡代金　@：　@super.validate注文()のレスポンスから同名プロパティをセット
        l_response.estimatedPrice = l_confirmResponse.estimatedPrice;

        // 手数料コース ： super.validate注文()のレスポンスから同名プロパティをセット
        l_response.commissionCourse = l_confirmResponse.commissionCourse;

        // 手数料 ： super.validate注文()のレスポンスから同名プロパティをセット
        l_response.commission = l_confirmResponse.commission;

        // 手数料消費税 ： super.validate注文()のレスポンスから同名プロパティをセット
        l_response.commissionConsumptionTax = l_confirmResponse.commissionConsumptionTax;

        // 取引終了警告文言 ： super.validate注文()のレスポンスから同名プロパティをセット
        l_response.messageSuspension = l_confirmResponse.messageSuspension;

        // 確認時単価 ： super.validate注文()のレスポンスから同名プロパティをセット
        l_response.checkPrice = l_confirmResponse.checkPrice;

        // 確認時発注日 ： super.validate注文()のレスポンスから同名プロパティをセット
        l_response.checkDate = l_confirmResponse.checkDate;

        // 注文有効期限 ： super.validate注文()のレスポンスから同名プロパティをセット
        l_response.expirationDate = l_confirmResponse.expirationDate;

        // 調整後単価 ：
        // 　@リクエスト.単価調整値情報≠null（±指値指定）の場合
        // 　@　@　@super.validate注文()のレスポンスから同名プロパティをセット
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_response.afterAdjustmentPrice = l_confirmResponse.afterAdjustmentPrice;
        }
        // 　@上記以外の場合
        // 　@　@　@nullをセット
        else
        {
            l_response.afterAdjustmentPrice = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * （連続）オプション訂正新規建注文の更新を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）OP訂正新規建サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）株価指数オプション訂正新規建注文完了リクエストデータオブジェクト<BR>
     * @@return WEB3SuccOptionsOpenChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A920630046
     */
    protected WEB3SuccOptionsOpenChangeCompleteResponse submitOrder(
        WEB3SuccOptionsOpenChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccOptionsOpenChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // リクエストデータの整合性をチェックする。
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // get先物OP予約注文単位(long)
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        // validate訂正可能状態( )
        l_toSuccIfoOrderUnit.validateOrderForChangeability();

        // create完了リクエスト(（連続）株価指数オプション訂正新規建完了リクエスト, 先物OP予約注文単位Impl)
        WEB3SuccOptionsOpenCompleteRequest l_completeRequest =
            this.createCompleteRequest(l_request, l_toSuccIfoOrderUnit);

        // submit注文(（連続）株価指数オプション新規建注文完了リクエスト)
        WEB3SuccOptionsOpenCompleteResponse l_completeResponse = super.submitOrder(l_completeRequest);

        // validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
        l_orderManager.validateEveningSessionOrderPossibleChange(
            l_request.expirationDateType, l_toSuccIfoOrderUnit);

        // get補助口座( )
        SubAccount l_subAccount = this.getSubAccount();

        // 訂正後指値
        double l_dblModifiedLimitPrice = 0.0;

        // リクエストデータ.注文単価区分 が "成行" 以外の場合、リクエストデータ.注文単価をセット
        if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblModifiedLimitPrice = Double.parseDouble(l_request.limitPrice);
        }

        // 訂正後計算単価
        double l_dblModifiedCalcUnitPrice = 0.0D;

        // リクエストデータ.確認時単価 != nullの場合、リクエストデータ.確認時単価
        if (l_request.checkPrice != null)
        {
            l_dblModifiedCalcUnitPrice = Double.parseDouble(l_request.checkPrice);
        }
        // リクエストデータ.確認時単価 == nullの場合
        else
        {
            // リクエストデータ.単価調整情報 != nullの場合、リクエストデータ.調整後単価
            if (l_request.priceAdjustmentValueInfo != null)
            {
                l_dblModifiedCalcUnitPrice = Double.parseDouble(l_request.afterAdjustmentPrice);
            }
            // 以外、リクエストデータ.注文単価
            else
            {
                l_dblModifiedCalcUnitPrice = Double.parseDouble(l_request.limitPrice);
            }
        }

        // 訂正後注文失効日
        Date l_datModifiedExpirationDate = null;

        // リクエストデータ.注文期限区分 == "出来るまで注文"の場合、リクエストデータ.注文有効期限をセット
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_datModifiedExpirationDate = l_request.expirationDate;
        }
        // 以外、リクエストデータ.確認時発注日をセット
        else
        {
            l_datModifiedExpirationDate = l_request.checkDate;
        }

        // 代理入力者
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();

        // 訂正後単価調整値
        Double l_modifiedPriceAdjustValue = null;
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_modifiedPriceAdjustValue =
                new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        // create先物OP予約新規建注文訂正内容(long, double, double, double, double, Date, 扱者, Double, String)
        WEB3ToSuccIfoChangeOpenContractOrderSpec l_toSuccIfoChangeOpenContractOrderSpec =
            WEB3ToSuccIfoChangeOpenContractOrderSpec.createIfoChangeOpenContractOrderSpec(
            l_toSuccIfoOrderUnit.getOrderId(),
            Double.parseDouble(l_request.opOrderQuantity),
            l_dblModifiedLimitPrice,
            Double.parseDouble(l_request.estimatedPrice),
            l_dblModifiedCalcUnitPrice,
            l_datModifiedExpirationDate,
            l_trader,
            l_modifiedPriceAdjustValue,
            l_request.expirationDateType);

        // submit先物OP訂正予約新規建注文(SubAccount, 先物OP予約新規建注文訂正内容, String, 先物OP予約注文単位Impl)
        l_orderManager.submitIfoChangeOpenContractOrder(
            l_subAccount,
            l_toSuccIfoChangeOpenContractOrderSpec,
            l_request.password,
            l_toSuccIfoOrderUnit);

        // createResponse( )
        WEB3SuccOptionsOpenChangeCompleteResponse l_response =
            (WEB3SuccOptionsOpenChangeCompleteResponse)l_request.createResponse();

        // 更新時間：super.submit注文のレスポンスから同名プロパティをセット
        l_response.lastUpdatedTimestamp = l_completeResponse.lastUpdatedTimestamp;
        // 識別番号：super.submit注文のレスポンスから同名プロパティをセット
        l_response.orderActionId = l_completeResponse.orderActionId;
        // 連続注文設定フラグ：false(固定)
        l_response.succSettingFlag = false;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit新規建注文)<BR>
     * 予約注文を登録する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_orderSpec - (新規建注文内容)<BR>
     * 新規建注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 予約注文の注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_requestAdaptor - (新規建リクエストアダプタ)<BR>
     * オプション新規建リクエストアダプタオブジェクト。<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (先物OP概算受渡代金計算結果)<BR>
     * 先物OP概算受渡代金計算結果。<BR>
     * @@roseuid 47C3A5DE0348
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        IfoOpenContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3OptionOpenContractOrderRequestAdapter l_requestAdaptor,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
    {
        return;
    }

    /**
     * (validate連続注文最大設定数)<BR>
     * 連続注文の最大設定数を超過しないかチェックする。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト。<BR>
     * @@roseuid 47C3A9F40352
     */
    protected void validateSuccOrderMaxQuantity(IfoOrderUnit l_parentOrderUnit)
    {
        return;
    }

    /**
     * (create確認リクエスト)<BR>
     * 引数のリクエストデータ、予約注文単位より、<BR>
     * （連続）株価指数オプション新規建注文確認リクエストオブジェクトを作成する。<BR>
     * <BR>
     * 　@１） 戻り値のインスタンスを生成する。<BR>
     * <BR>
     * 　@２） 共通プロパティセット。<BR>
     * <BR>
     * 　@　@　@ this.setオプション共通リクエスト()をコールする。<BR>
     * <BR>
     * 　@　@　@ [引数設定仕様]<BR>
     * 　@　@　@ （output）共通リクエスト ： １）の戻り値<BR>
     * 　@　@　@ （input） 共通リクエスト ： 引数.リクエストデータ<BR>
     * <BR>
     * 　@３） 生成したインスタンス特有のプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@　@ 銘柄コード = 予約注文単位.get銘柄().銘柄コード<BR>
     * 　@　@　@　@ 建区分 = 予約注文単位.is買注文() == trueの場合、"買建"。以外の場合、"売建"。<BR>
     * 　@　@　@　@ 取引市場 = 予約注文単位.get市場().getMarketCode()の戻り値<BR>
     * 　@　@　@　@ 指数種別 = 予約注文単位.get銘柄().get原資産銘柄コード()の戻り値<BR>
     * 　@　@　@　@ 限月 = 予約注文単位.get銘柄().getMonthOfDelivery()の戻り値<BR>
     * 　@　@　@　@ オプション商品区分 = 予約注文単位.get銘柄().getDerivativeType()<BR>
     * 　@　@　@　@ 行使価格 = 予約注文単位.get銘柄().getStrikePrice()<BR>
     * 　@　@　@　@ 連続注文共通情報 = 予約注文単位.create連続注文共通情報()の戻り値<BR>
     * 　@　@　@　@ 単価調整値情報 = 引数.リクエストデータ.単価調整値情報<BR>
     * <BR>
     * 　@４）　@プロパティセットした確認リクエストを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@param l_orderUnit - (予約注文単位)<BR>
     * 先物OP予約注文単位Implオブジェクト。<BR>
     * @@return WEB3SuccOptionsOpenConfirmRequest
     * @@throws WEB3BaseException
     * @@roseuid 47C3AB610291
     */
    protected WEB3SuccOptionsOpenConfirmRequest createConfirmRequest(
        WEB3SuccOptionsOpenChangeConfirmRequest l_request,
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createConfirmRequest(WEB3SuccOptionsOpenChangeConfirmRequest, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null || l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １） 戻り値のインスタンスを生成する。
        WEB3SuccOptionsOpenConfirmRequest l_confirmRequest = new WEB3SuccOptionsOpenConfirmRequest();

        // ２） 共通プロパティセット。
        // 　@this.setオプション共通リクエスト()をコールする。
        this.setOptionsCommonRequest(l_confirmRequest, l_request);

        IfoProductRow l_ifoProductRow = (IfoProductRow)l_orderUnit.getProduct().getDataSourceObject();

        // ３） 生成したインスタンス特有のプロパティをセットする。
        // 銘柄コード = 予約注文単位.get銘柄().銘柄コード
        l_confirmRequest.opProductCode = l_ifoProductRow.getProductCode();

        // 建区分
        // 予約注文単位.is買注文() == trueの場合、"買建"。
        if (l_orderUnit.isBuyOrder())
        {
            l_confirmRequest.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
        }
        // 以外の場合、"売建"
        else
        {
            l_confirmRequest.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
        }

        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 取引市場 = 予約注文単位.get市場().getMarketCode()の戻り値
        l_confirmRequest.marketCode = l_market.getMarketCode();

        // 指数種別 = 予約注文単位.get銘柄().get原資産銘柄コード()の戻り値
        l_confirmRequest.targetProductCode = l_ifoProductRow.getUnderlyingProductCode();

        // 限月 = 予約注文単位.get銘柄().getMonthOfDelivery()の戻り値
        l_confirmRequest.delivaryMonth = l_ifoProductRow.getMonthOfDelivery();

        // オプション商品区分 = 予約注文単位.get銘柄().getDerivativeType()
        if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
        {
            l_confirmRequest.opProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
        }
        else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
        {
            l_confirmRequest.opProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
        }

        // 行使価格 = 予約注文単位.get銘柄().getStrikePrice()
        l_confirmRequest.strikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProductRow.getStrikePrice());

        // 連続注文共通情報 = 予約注文単位.create連続注文共通情報()の戻り値
        l_confirmRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();

        // 単価調整値情報 = 引数.リクエストデータ.単価調整値情報
        l_confirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        log.exiting(STR_METHOD_NAME);
        return l_confirmRequest;
    }

    /**
     * (create完了リクエスト)<BR>
     * 引数のリクエストデータ、予約注文単位より、<BR>
     * （連続）株価指数オプション新規建注文完了リクエストオブジェクトを作成する。<BR>
     * <BR>
     * 　@１） 戻り値のインスタンスを生成する。<BR>
     * <BR>
     * 　@２） 共通プロパティセット。<BR>
     * <BR>
     * 　@　@　@ this.setオプション共通リクエスト()をコールする。<BR>
     * <BR>
     * 　@　@　@ [引数設定仕様]<BR>
     * 　@　@　@ （output）共通リクエスト ： １）の戻り値<BR>
     * 　@　@　@ （input） 共通リクエスト ： 引数.リクエストデータ<BR>
     * <BR>
     * 　@３） 生成したインスタンス特有のプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@　@ 注文ID = 予約注文単位.注文ID<BR>
     * 　@　@　@　@ 銘柄コード = 予約注文単位.get銘柄().銘柄コード<BR>
     * 　@　@　@　@ 建区分 = 予約注文単位.is買注文() == trueの場合、"買建"。以外の場合、"売建"。<BR>
     * 　@　@　@　@ 取引市場 = 予約注文単位.get市場().getMarketCode()の戻り値<BR>
     * 　@　@　@　@ 暗証番号 = 引数.リクエストデータ.暗証番号<BR>
     * 　@　@　@　@ 確認時単価 = 引数.リクエストデータ.確認時単価<BR>
     * 　@　@　@　@ 確認時発注日 = 引数.リクエストデータ.確認時発注日<BR>
     * 　@　@　@　@ 指数種別 = 予約注文単位.get銘柄().get原資産銘柄コード()の戻り値<BR>
     * 　@　@　@　@ 限月 = 予約注文単位.get銘柄().getMonthOfDelivery()の戻り値<BR>
     * 　@　@　@　@ オプション商品区分 = 予約注文単位.get銘柄().getDerivativeType()<BR>
     * 　@　@　@　@ 行使価格 = 予約注文単位.get銘柄().getStrikePrice()<BR>
     * 　@　@　@　@ 連続注文共通情報 = 予約注文単位.create連続注文共通情報()の戻り値<BR>
     * 　@　@　@　@ 単価調整値情報 = 引数.リクエストデータ.単価調整値情報<BR>
     * 　@　@　@　@ 調整後単価 = 引数.リクエストデータ.調整後単価<BR>
     * <BR>
     * 　@４）　@プロパティセットした完了リクエストを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_orderUnit - (予約注文単位)<BR>
     * 先物OP予約注文単位Implオブジェクト。<BR>
     * @@return WEB3SuccOptionsOpenCompleteRequest
     * @@throws WEB3BaseException
     * @@roseuid 47C3AE5D032F
     */
    protected WEB3SuccOptionsOpenCompleteRequest createCompleteRequest(
        WEB3SuccOptionsOpenChangeCompleteRequest l_request,
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createcompleteRequest(WEB3SuccOptionsOpenChangeCompleteRequest, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null || l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １） 戻り値のインスタンスを生成する。
        WEB3SuccOptionsOpenCompleteRequest l_completeRequest = new WEB3SuccOptionsOpenCompleteRequest();

        // ２） 共通プロパティセット。
        // 　@this.setオプション共通リクエスト()をコールする。
        this.setOptionsCommonRequest(l_completeRequest, l_request);

        IfoProductRow l_ifoProductRow = (IfoProductRow)l_orderUnit.getProduct().getDataSourceObject();

        // ３） 生成したインスタンス特有のプロパティをセットする。
        // 注文ID = 予約注文単位.注文ID
        l_completeRequest.orderId = WEB3StringTypeUtility.formatNumber(l_orderUnit.getOrderId());

        // 銘柄コード = 予約注文単位.get銘柄().銘柄コード
        l_completeRequest.opProductCode = l_ifoProductRow.getProductCode();

        // 建区分
        // 予約注文単位.is買注文() == trueの場合、"買建"。
        if (l_orderUnit.isBuyOrder())
        {
            l_completeRequest.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
        }
        // 以外の場合、"売建"
        else
        {
            l_completeRequest.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
        }

        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 取引市場 = 予約注文単位.get市場().getMarketCode()の戻り値
        l_completeRequest.marketCode = l_market.getMarketCode();

        // 暗証番号 = 引数.リクエストデータ.暗証番号
        l_completeRequest.password = l_request.password;

        // 確認時単価 = 引数.リクエストデータ.確認時単価
        l_completeRequest.checkPrice = l_request.checkPrice;

        // 確認時発注日 = 引数.リクエストデータ.確認時発注日
        l_completeRequest.checkDate = l_request.checkDate;

        // 指数種別 = 予約注文単位.get銘柄().get原資産銘柄コード()の戻り値
        l_completeRequest.targetProductCode = l_ifoProductRow.getUnderlyingProductCode();

        // 限月 = 予約注文単位.get銘柄().getMonthOfDelivery()の戻り値
        l_completeRequest.delivaryMonth = l_ifoProductRow.getMonthOfDelivery();

        // オプション商品区分 = 予約注文単位.get銘柄().getDerivativeType()
        if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
        {
            l_completeRequest.opProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
        }
        else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
        {
            l_completeRequest.opProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
        }

        // 行使価格 = 予約注文単位.get銘柄().getStrikePrice()
        l_completeRequest.strikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProductRow.getStrikePrice());

        // 連続注文共通情報 = 予約注文単位.create連続注文共通情報()の戻り値
        l_completeRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();

        // 単価調整値情報 = 引数.リクエストデータ.単価調整値情報
        l_completeRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        // 調整後単価 = 引数.リクエストデータ.調整後単価
        l_completeRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;

        log.exiting(STR_METHOD_NAME);
        return l_completeRequest;
    }

    /**
     * (setOP共通リクエスト)<BR>
     * 指定された「（output）共通リクエスト」のインスタンスに、プロパティをセットする。<BR>
     * <BR>
     * 以下のプロパティについて、「（input）共通リクエスト」の同名プロパティの値をセットする。<BR>
     * <BR>
     * 　@[対象プロパティ]<BR>
     * <BR>
     * 　@　@ 注文数量<BR>
     * 　@　@ 注文単価区分<BR>
     * 　@　@ 注文単価<BR>
     * 　@　@ 執行条件<BR>
     * 　@　@ 注文期限区分<BR>
     * 　@　@ 注文有効期限<BR>
     * 　@　@ 発注条件区分<BR>
     * 　@　@ 逆指値用プレミアム／原資産価格<BR>
     * 　@　@ 逆指値用発注条件単価<BR>
     * 　@　@ 逆指値用発注条件演算子<BR>
     * 　@　@ W指値用プレミアム／原資産価格<BR>
     * 　@　@ W指値用発注条件単価<BR>
     * 　@　@ W指値用発注条件演算子<BR>
     * 　@　@ W指値用注文単価区分<BR>
     * 　@　@ W指値用注文単価<BR>
     * 　@　@ W指値用執行条件<BR>
     * 　@　@ W指値用有効状態区分<BR>
     * @@param l_outputCommonRequest - (（output）共通リクエスト)<BR>
     * 株価指数オプション共通リクエストオブジェクト。<BR>
     * @@param l_inputCommonRequest - (（input）共通リクエスト)<BR>
     * 株価指数オプション共通リクエストオブジェクト。<BR>
     * @@return WEB3OptionsCommonRequest
     * @@roseuid 47C3B07F01DB
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

        // 逆指値用プレミアム／原資産価格
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
     * (notify予約注文登録)<BR>
     * 予約注文の登録をルールエンジンサーバに通知する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_lngChildOrderId - (子注文の注文ID)<BR>
     * 子注文の注文ID。<BR>
     * @@roseuid 47C3B3100244
     */
    protected void notifyRsvOrderRegister(long l_lngChildOrderId)
    {
        return;
    }
}
@
