head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeOpenMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : （連続）信用取引訂正新規建サービスImpl(WEB3ToSuccMarginChangeOpenMarginServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/17　@呉　@鈞(中訊) 新規作成
 Revesion History : 2006/08/30 柴雙紅(中訊) 仕様変更モデル165
 Revesion History : 2007/01/11 齊珂  (中訊) 仕様変更モデル216
 Revesion History : 2007/01/19 肖志偉(中訊) 仕様変更モデル225                 
 Revesion History : 2007/08/20 武波(中訊) モデル242
 */

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCommonRequest;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginRequestAdapter;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccEqtypeChangeOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeOpenMarginService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）信用取引訂正新規建サービスImpl) <BR>
 * （連続）信用取引訂正新規建サービス実装クラス <BR>
 * 
 * @@author 呉 鈞(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeOpenMarginServiceImpl extends WEB3ToSuccMarginOpenMarginServiceImpl implements
    WEB3ToSuccMarginChangeOpenMarginService
{
    /**
     * (ログ出力ユーティリティ。) <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeOpenMarginServiceImpl.class);

    /**
     * @@roseuid 436ACF7A01D4
     */
    public WEB3ToSuccMarginChangeOpenMarginServiceImpl()
    {

    }

    /**
     * （連続）信用取引訂正新規建サービス処理を実施する <BR>
     * <BR>
     * リクエストデータの型により、 <BR>
     * validate注文とsubmit注文を呼び分ける。 <BR>
     * 
     * @@param l_request -
     *            リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCCF803B0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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
        if (l_request instanceof WEB3SuccMarginOpenChangeConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccMarginOpenChangeConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginOpenChangeCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccMarginOpenChangeCompleteRequest) l_request);
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
     * (validate注文) <BR>
     * （連続）信用取引の訂正新規建発注審査を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（（連続）信用取引訂正新規建サービス）validate注文」参照。 <BR>
     * 
     * @@param l_request -
     *            (リクエストデータ) <BR>
     *            （連続）信用取引注文訂正新規建確認リクエスト <BR>
     * @@return WEB3SuccMarginOpenChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCDF903B0
     */
    protected WEB3SuccMarginOpenChangeConfirmResponse validateOrder(WEB3SuccMarginOpenChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginOpenChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        //1.2 get株式予約注文単位(long)
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }

        //1.3 validate訂正可能状態
        l_orderUnitImpl.validateOrderForChangeability();

        //1.4 create確認リクエスト
        WEB3SuccMarginOpenConfirmRequest l_confirmRequest = this.createConfirmRequest(l_request, l_orderUnitImpl);

        //1.5 validate注文(（連続）信用取引新規建注文確認リクエスト)
        WEB3SuccMarginOpenConfirmResponse l_confirmResponse = super.validateOrder(l_confirmRequest);

        //1.6 get補助口座
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        if (l_confirmResponse.estimatedPrice == null)
        {
            l_confirmResponse.estimatedPrice = "0";
        }

        //1.7 validate訂正時取引余力(補助口座, double, 株式予約注文単位Impl)
        this.validateChangeTradingPower(
            l_subAccount, 
            Double.parseDouble(l_confirmResponse.estimatedPrice),
            l_orderUnitImpl);

        //1.8 createResponse( )
        WEB3SuccMarginOpenChangeConfirmResponse l_response = 
            (WEB3SuccMarginOpenChangeConfirmResponse) l_request.createResponse();

        //確認時発注日
        l_response.checkDate = l_confirmResponse.checkDate;
        
        //概算受渡代金
        l_response.estimatedPrice = l_confirmResponse.estimatedPrice;

        //取引終了警告市場コード一覧
        l_response.messageSuspension = l_confirmResponse.messageSuspension;

        //内出来株数
        l_response.partContQuantity = null;

        //手数料情報
        l_response.commissionInfo = l_confirmResponse.commissionInfo;

        //確認時単価
        l_response.checkPrice = l_confirmResponse.checkPrice;

        //インサイダー警告表示フラグ
        l_response.insiderWarningFlag = l_confirmResponse.insiderWarningFlag;

        //注文有効期限：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.expirationDate = l_confirmResponse.expirationDate;

        //調整後単価
        if (l_confirmRequest.priceAdjustmentValueInfo != null)
        {
            l_response.afterAdjustmentPrice = l_confirmResponse.afterAdjustmentPrice;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文) <BR>
     * （連続）信用取引の訂正新規建注文を登録する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（（連続）信用取引訂正新規建サービス）submit注文」参照。 <BR>
     * 
     * @@param l_request -
     *            (リクエストデータ) <BR>
     *            （連続）信用取引注文訂正新規建完了リクエスト <BR>
     * @@return WEB3SuccMarginOpenChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCDF903BF
     */
    protected WEB3SuccMarginOpenChangeCompleteResponse submitOrder(WEB3SuccMarginOpenChangeCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginOpenChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        //1.2 get株式予約注文単位(long)
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }

        //1.3 validate訂正可能状態
        l_orderUnitImpl.validateOrderForChangeability();

        //1.4 create完了リクエスト
        WEB3SuccMarginOpenCompleteRequest l_completeRequest = this.createCompleteRequest(l_request, l_orderUnitImpl);

        //1.5 submit注文(（連続）信用取引新規建注文完了リクエスト)
        WEB3SuccMarginOpenCompleteResponse l_completeResponse = super.submitOrder(l_completeRequest);

        //1.6 get補助口座
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.7 validate訂正時取引余力(補助口座, double, 株式予約注文単位Impl)
        this.validateChangeTradingPower(l_subAccount, Double.parseDouble(l_request.estimatedPrice), l_orderUnitImpl);

        //1.8 create株式予約注文訂正内容(long, double, double, double, double, Date,
        // boolean, 扱者, Double)
        if (l_request.limitPrice == null)
        {
            l_request.limitPrice = "0";
        }
        double l_dblModifiedLimitPrice = Double.parseDouble(l_request.limitPrice);
        Date l_datModifiedExpirationDate = l_request.checkDate;
        boolean l_blnModifiedIsCarriedOrder = false;
        Double l_modifiedPriceAdjustValue = null;
        if ((WEB3OrderPriceDivDef.MARKET_PRICE).equals(l_request.orderPriceDiv))
        {
            l_dblModifiedLimitPrice = 0;
        }

        if ((WEB3OrderExpirationDateTypeDef.CARRIED_ORDER).equals(l_request.expirationDateType))
        {
            l_datModifiedExpirationDate = l_request.expirationDate;
            l_blnModifiedIsCarriedOrder = true;
        }

        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_modifiedPriceAdjustValue = new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }
        
        if (l_request.orderQuantity == null)
        {
            l_request.orderQuantity = "0";
        }
        
        if (l_request.checkPrice == null)
        {
            l_request.checkPrice = "0";
        }
        
        WEB3ToSuccEqtypeChangeOrderSpec l_changeOrderSpec = WEB3ToSuccEqtypeChangeOrderSpec.createEqtypeChangeOrderSpec(
            l_orderUnitImpl.getOrderId(), 
            Double.parseDouble(l_request.orderQuantity),
            l_dblModifiedLimitPrice, 
            Double.parseDouble(l_request.estimatedPrice), 
            Double.parseDouble(l_request.checkPrice), 
            l_datModifiedExpirationDate, 
            l_blnModifiedIsCarriedOrder,
            (WEB3GentradeTrader) this.getTrader(), 
            l_modifiedPriceAdjustValue);

        //1.9 submit株式訂正予約注文(SubAccount, 株式予約注文訂正内容, String, 株式予約注文単位Impl)
        l_toOrderManager.submitEqtypeChangeOrder(l_subAccount, l_changeOrderSpec, l_request.password, l_orderUnitImpl);

        //1.10 createResponse( )
        WEB3SuccMarginOpenChangeCompleteResponse l_response = 
            (WEB3SuccMarginOpenChangeCompleteResponse) l_request.createResponse();

        //更新時間
        l_response.lastUpdatedTimestamp = l_completeResponse.lastUpdatedTimestamp;

        //識別番号
        l_response.orderActionId = l_completeResponse.orderActionId;

        //インサイダー警告表示フラグ
        l_response.insiderWarningFlag = l_completeResponse.insiderWarningFlag;

        //連続注文設定フラグ
        l_response.succSettingFlag = false;

        //注文有効期限：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.expirationDate = l_completeResponse.expirationDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate取引余力) <BR>
     * 取引余力をチェックする。 <BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装） <BR>
     * 
     * @@param l_subAccount -
     *            (補助口座) <BR>
     *            補助口座オブジェクト。 <BR>
     * @@param l_orderSpec -
     *            (信用新規建注文内容) <BR>
     *            信用新規建注文内容オブジェクト。 <BR>
     * @@param l_blnUpdateFlg -
     *            (余力更新フラグ) <BR>
     *            余力更新フラグ。 <BR>
     *            （false： 確認時、true： 完了時） <BR>
     * @@param l_commission -
     *            (手数料) <BR>
     *            手数料オブジェクト <BR>
     * @@param l_validationResult -
     *            (発注審査結果) <BR>
     *            信用新規建新規注文発注審査結果オブジェクト <BR>
     * @@return WEB3TPTradingPowerResult
     * @@roseuid 433BCEEF0016
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginOpenContractOrderSpec l_orderSpec, 
        boolean l_blnUpdateFlg, 
        WEB3GentradeCommission l_commission,
        WEB3MarginNewOrderValidationResult l_validationResult)
    {
        final String STR_METHOD_NAME = " validateTradingPower(" +
            " WEB3GentradeSubAccount," +
            " WEB3MarginOpenContractOrderSpec," +
            " boolean," +
            " WEB3GentradeCommission," +
            " WEB3MarginNewOrderValidationResult)";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (validate訂正時取引余力) <BR>
     * 取引余力をチェックし、取引余力結果オブジェクトを返却する。 <BR>
     * <BR>
     * 余力チェックを実施する部店(*2)の場合のみ、<BR>
     * 新規建可能額(*1)と引数の概算受渡代金を比較し、 <BR>
     * （概算受渡代金 > 新規建可能額(*1)）の場合は、 <BR>
     * 「取引余力不足」の例外をthrowする。 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00667 <BR>
     * <BR>
     * 以外、nullを返却する。 <BR>
     * <BR>
     * (*1)新規建可能額 <BR>
     * 取引余力サービス.get信用新規建可能額～連続注文～( <BR>
     * 引数の補助口座, 引数の訂正前注文単位.概算受渡代金)の戻り値を、 <BR>
     * 新規建可能額とする。 <BR>
     * <BR>
     * (*2)余力チェックを実施する部店<BR>
     * 連続注文マネージャImpl.is余力チェック実施部店()==trueの場合は、<BR>
     * 余力チェックを実施する部店であると判定する。<BR>
     * @@param l_subAccount -
     *            (補助口座) <BR>
     *            補助口座オブジェクト。 <BR>
     * @@param l_dblEstimatedPrice -
     *            (概算受渡代金) <BR>
     *            概算受渡代金。 <BR>
     * @@param l_beforeChangingRsvEqOrderUnit -
     *            (訂正前注文単位) <BR>
     *            訂正対象の株式予約注文単位オブジェクト。 <BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     * @@roseuid 433C92BD03C2
     */
    protected WEB3TPTradingPowerResult validateChangeTradingPower(WEB3GentradeSubAccount l_subAccount,
        double l_dblEstimatedPrice, WEB3ToSuccEqTypeOrderUnitImpl l_beforeChangingRsvEqOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChangeTradingPower(" +
            " WEB3GentradeSubAccount," + 
            " double,"+ 
            " WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        if (l_toOrderManager.isCheckTradingPowerBranch(l_subAccount) == false)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);

        RsvEqOrderUnitRow l_orderUnitRow = (RsvEqOrderUnitRow) l_beforeChangingRsvEqOrderUnit.getDataSourceObject();
        //新規建可能額
        double l_dblTradingPower = l_tradingPowerService.getSuccMarginTradingPower(
            l_subAccount, 
            new Double(l_orderUnitRow.getEstimatedPrice()));

        if (l_dblEstimatedPrice > l_dblTradingPower)
        {
            log.debug("取引余力不足エラー。（信用新規建可能額不足）");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00667, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "取引余力不足エラー。（信用新規建可能額不足）");
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (submit新規建注文) <BR>
     * 予約注文を登録する。 <BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装） <BR>
     * 
     * @@param l_subAccount -
     *            (補助口座) <BR>
     *            補助口座オブジェクト。 <BR>
     * @@param l_orderSpec -
     *            (補助口座) <BR>
     *            信用新規建注文内容オブジェクト。 <BR>
     * @@param l_lngOrderId -
     *            (注文ID) <BR>
     *            予約注文の注文ID。
     * @@param l_strTradingPassword -
     *            (取引パスワード) <BR>
     *            取引パスワード。 <BR>
     * @@param l_requestAdaptor -
     *            (新規建リクエストアダプタ) <BR>
     *            信用取引新規建リクエストアダプタオブジェクト <BR>
     * @@roseuid 433C92F800F3
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginOpenContractOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword,
        WEB3MarginOpenMarginRequestAdapter l_requestAdaptor)
    {
        final String STR_METHOD_NAME = " submitOpenContractOrder(" +
            " WEB3GentradeSubAccount,"+
            " WEB3MarginOpenContractOrderSpec," + 
            " long,"+
            " String,"+
            " WEB3MarginOpenMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (validate連続注文最大設定数) <BR>
     * 連続注文の最大設定数を超過してしまわないかどうかをチェックする。 <BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装） <BR>
     * 
     * @@param l_parentOrderUnit -
     *            (親注文の注文単位) <BR>
     *            親注文の注文単位。 <BR>
     * @@roseuid 433C92BD03C7
     */
    protected void validateSuccOrderMaxQuantity(EqTypeOrderUnit l_parentOrderUnit)
    {
        final String STR_METHOD_NAME = " validateSuccOrderMaxQuantity(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (create確認リクエスト) <BR>
     * 引数のリクエストデータ、予約注文単位より <BR>
     * （連続）信用取引新規建注文確認リクエストオブジェクトを <BR>
     * 作成する。 <BR>
     * <BR>
     * １） 戻り値のインスタンスを生成する。 <BR>
     * <BR>
     * ２） 共通プロパティセット。 <BR>
     * this.set信用取引共通リクエスト()をコールする。 <BR>
     * <BR>
     * [set信用取引共通リクエスト()にセットするパラメータ] <BR>
     * （output）共通リクエスト： １）の戻り値 <BR>
     * （input）共通リクエスト： パラメータ.リクエストデータ <BR>
     * <BR>
     * ３） 生成したインスタンス特有のプロパティをセットする。 <BR>
     * 銘柄コード = 予約注文単位.get銘柄().銘柄コード <BR>
     * 市場コード = 予約注文単位.get市場().市場コード <BR>
     * 口座区分 = 株式データアダプタ.get口座区分(予約注文単位.getTaxType)  <BR>
     * 取引区分 = 予約注文単位.getメッセージ用取引区分() <BR>
     * 弁済 = 信用取引弁済オブジェクト(*1) <BR>
     * 連続注文共通情報 = 予約注文単位.create連続注文共通情報() <BR>
     * 単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報<BR>
     * <BR>
     * (*1)信用取引弁済インスタンスを生成し、以下のプロパティをセット。 <BR>
     * 弁済区分 = 予約注文単位.弁済区分 <BR>
     * 弁済期限値 = 予約注文単位.弁済期限値 <BR>
     * <BR>
     * ４） プロパティセットした確認リクエストを返却する。 <BR>
     * 
     * @@param l_request -
     *            (リクエストデータ) <BR>
     *            リクエストデータ。 <BR>
     * @@param l_orderUnit -
     *            (予約注文単位) <BR>
     *            株式予約注文単位Implオブジェクト <BR>
     * @@return WEB3SuccMarginOpenConfirmRequest
     * @@throws WEB3BaseException
     * @@roseuid 433BD3BF0267
     */
    protected WEB3SuccMarginOpenConfirmRequest createConfirmRequest(
        WEB3SuccMarginOpenChangeConfirmRequest l_request,
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createConfirmRequest(" +
            " WEB3SuccMarginOpenChangeConfirmRequest, "+
            " WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //１） 戻り値のインスタンスを生成する
        WEB3SuccMarginOpenConfirmRequest l_confirmRequest = new WEB3SuccMarginOpenConfirmRequest();

        //２） 共通プロパティセット
        this.setMarginCommonRequest(l_confirmRequest, l_request);

        //３） 生成したインスタンス特有のプロパティをセットする
        WEB3MarginRepaymentUnit l_marginRepaymentUnit = new WEB3MarginRepaymentUnit();
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_orderUnit.getDataSourceObject();
        //弁済区分
        l_marginRepaymentUnit.repaymentDiv = l_rsvEqOrderUnitRow.getRepaymentType();
        //弁済期限値
        l_marginRepaymentUnit.repaymentTimeLimit = String.valueOf(l_rsvEqOrderUnitRow.getRepaymentNum());
        
        Product l_product = l_orderUnit.getProduct();
        
        if (l_product == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "テーブルに該当するデータがありません。");
        }
        
        //銘柄コード
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject(); 
        l_confirmRequest.productCode = l_productRow.getProductCode();

        //市場コード
        try
        {
            l_confirmRequest.marketCode = l_orderUnit.getMarket().getMarketCode();
        }
        catch (NotFoundException l_ne)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ne.getMessage(), 
                l_ne);
        }

        //口座区分
        l_confirmRequest.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());

        //取引区分
        l_confirmRequest.tradingType = l_orderUnit.getMsgTradingType();

        //弁済
        l_confirmRequest.repayment = l_marginRepaymentUnit;

        //連続注文共通情報
        l_confirmRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();

        //単価調整値情報
        l_confirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        //４） プロパティセットした確認リクエストを返却する
        log.exiting(STR_METHOD_NAME);
        return l_confirmRequest;
    }

    /**
     * (create完了リクエスト) <BR>
     * 引数のリクエストデータ、予約注文単位より <BR>
     * （連続）信用取引新規建注文完了リクエストオブジェクトを <BR>
     * 作成する。 <BR>
     * <BR>
     * １） 戻り値のインスタンスを生成する。 <BR>
     * <BR>
     * ２） 共通プロパティセット。 <BR>
     * this.set信用取引共通リクエスト()をコールする。 <BR>
     * <BR>
     * [set信用取引共通リクエスト()にセットするパラメータ] <BR>
     * （output）共通リクエスト： １）の戻り値 <BR>
     * （input）共通リクエスト： パラメータ.リクエストデータ <BR>
     * <BR>
     * ３） 生成したインスタンス特有のプロパティをセットする。 <BR>
     * 注文ID = 予約注文単位.注文ID <BR>
     * 銘柄コード = 予約注文単位.get銘柄().銘柄コード <BR>
     * 市場コード = 予約注文単位.get市場().市場コード <BR>
     * 口座区分 = 株式データアダプタ.get口座区分(予約注文単位.getTaxType)  <BR>
     * 取引区分 = 予約注文単位.getメッセージ用取引区分() <BR>
     * 弁済 = 信用取引弁済オブジェクト(*1) <BR>
     * 連続注文共通情報 = 予約注文単位.create連続注文共通情報() <BR>
     * 単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報<BR>
     * 調整後単価 = パラメータ.リクエストデータ.調整後単価<BR>
     * 確認時単価 = パラメータ.リクエストデータ.確認時単価 <BR>
     * 確認時発注日 = パラメータ.リクエストデータ.確認時発注日 <BR>
     * 暗証番号 = パラメータ.リクエストデータ.暗証番号 <BR>
     * <BR>
     * (*1)信用取引弁済インスタンスを生成し、以下のプロパティをセット。 <BR>
     * 弁済区分 = 予約注文単位.弁済区分 <BR>
     * 弁済期限値 = 予約注文単位.弁済期限値 <BR>
     * <BR>
     * ４） プロパティセットした完了リクエストを返却する。 <BR>
     * 
     * @@param l_request -
     *            (リクエストデータ) <BR>
     *            リクエストデータ。 <BR>
     * @@param l_orderUnit -
     *            (予約注文単位) <BR>
     *            株式予約注文単位Implオブジェクト <BR>
     * @@return WEB3SuccMarginOpenCompleteRequest
     * @@throws WEB3BaseException
     * @@roseuid 433C91CE0326
     */
    protected WEB3SuccMarginOpenCompleteRequest createCompleteRequest(
        WEB3SuccMarginOpenChangeCompleteRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCompleteRequest(" +
            " WEB3SuccMarginOpenChangeCompleteRequest, "+
            " WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //１） 戻り値のインスタンスを生成する
        WEB3SuccMarginOpenCompleteRequest l_completeRequest = new WEB3SuccMarginOpenCompleteRequest();

        //２） 共通プロパティセット
        this.setMarginCommonRequest(l_completeRequest, l_request);

        //生成したインスタンス特有のプロパティをセットする
        WEB3MarginRepaymentUnit l_marginRepaymentUnit = new WEB3MarginRepaymentUnit();
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_orderUnit.getDataSourceObject();

        //弁済区分
        l_marginRepaymentUnit.repaymentDiv = l_rsvEqOrderUnitRow.getRepaymentType();

        //弁済期限値
        l_marginRepaymentUnit.repaymentTimeLimit = String.valueOf(l_rsvEqOrderUnitRow.getRepaymentNum());

        //注文ID
        l_completeRequest.orderId = String.valueOf(l_orderUnit.getOrderId());
        
        Product l_product = l_orderUnit.getProduct();

        if (l_product == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "テーブルに該当するデータがありません。");
        }
        
        //銘柄コード
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject(); 
        l_completeRequest.productCode = l_productRow.getProductCode();

        //市場コード
        try
        {
            l_completeRequest.marketCode = l_orderUnit.getMarket().getMarketCode();
        }
        catch (NotFoundException l_ne)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ne.getMessage(), 
                l_ne);
        }

        //口座区分
        l_completeRequest.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());

        //取引区分
        l_completeRequest.tradingType = l_orderUnit.getMsgTradingType();

        //弁済
        l_completeRequest.repayment = l_marginRepaymentUnit;

        //連続注文共通情報
        l_completeRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();

        //単価調整値情報
        l_completeRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        //調整後単価
        l_completeRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;

        //確認時単価
        l_completeRequest.checkPrice = l_request.checkPrice;

        //確認時発注日
        l_completeRequest.checkDate = l_request.checkDate;

        //暗証番号
        l_completeRequest.password = l_request.password;

        log.exiting(STR_METHOD_NAME);
        return l_completeRequest;
    }

    /**
     * (set信用取引共通リクエスト) <BR>
     * 指定された「（output）共通リクエスト」のインスタンスに、プロパティをセットする。 <BR>
     * <BR>
     * 以下のプロパティに、「（input）共通リクエスト」の同名プロパティの値をセットする。 <BR>
     * <BR>
     * [対象プロパティ] <BR>
     * 注文株数 <BR>
     * 注文単価区分 <BR>
     * 注文単価 <BR>
     * 値段条件 <BR>
     * 執行条件 <BR>
     * 注文期限区分 <BR>
     * 注文有効期限 <BR>
     * 発注条件区分 <BR>
     * 逆指値用発注条件単価 <BR>
     * 逆指値用発注条件演算子 <BR>
     * W指値用発注条件単価 <BR>
     * W指値用発注条件演算子 <BR>
     * W指値用注文単価区分 <BR>
     * W指値用注文単価 <BR>
     * 
     * @@param l_outputCommonRequest -
     *            (（output）共通リクエスト) <BR>
     *            信用取引共通リクエストオブジェクト <BR>
     * @@param l_inputCommonRequest -
     *            (（input）共通リクエスト) <BR>
     *            信用取引共通リクエストオブジェクト <BR>
     * @@return WEB3MarginCommonRequest
     * @@roseuid 433BD8530333
     */
    protected WEB3MarginCommonRequest setMarginCommonRequest(WEB3MarginCommonRequest l_outputCommonRequest,
        WEB3MarginCommonRequest l_inputCommonRequest)
    {
        final String STR_METHOD_NAME = " setMarginCommonRequest(WEB3MarginCommonRequest, WEB3MarginCommonRequest)";
        log.entering(STR_METHOD_NAME);

        //注文株数
        l_outputCommonRequest.orderQuantity = l_inputCommonRequest.orderQuantity;

        //注文単価区分
        l_outputCommonRequest.orderPriceDiv = l_inputCommonRequest.orderPriceDiv;

        //注文単価
        l_outputCommonRequest.limitPrice = l_inputCommonRequest.limitPrice;

        //値段条件
        l_outputCommonRequest.priceCondType = l_inputCommonRequest.priceCondType;

        //執行条件
        l_outputCommonRequest.execCondType = l_inputCommonRequest.execCondType;

        //注文期限区分
        l_outputCommonRequest.expirationDateType = l_inputCommonRequest.expirationDateType;

        //注文有効期限
        l_outputCommonRequest.expirationDate = l_inputCommonRequest.expirationDate;

        //発注条件区分
        l_outputCommonRequest.orderCondType = l_inputCommonRequest.orderCondType;

        //逆指値用発注条件単価
        l_outputCommonRequest.stopOrderCondPrice = l_inputCommonRequest.stopOrderCondPrice;

        //逆指値用発注条件演算子
        l_outputCommonRequest.stopOrderCondOperator = l_inputCommonRequest.stopOrderCondOperator;

        //W指値用発注条件単価
        l_outputCommonRequest.wlimitOrderCondPrice = l_inputCommonRequest.wlimitOrderCondPrice;

        //W指値用発注条件演算子
        l_outputCommonRequest.wlimitOrderCondOperator = l_inputCommonRequest.wlimitOrderCondOperator;

        //W指値用注文単価区分
        l_outputCommonRequest.wLimitOrderPriceDiv = l_inputCommonRequest.wLimitOrderPriceDiv;

        //W指値用注文単価
        l_outputCommonRequest.wLimitPrice = l_inputCommonRequest.wLimitPrice;

        log.exiting(STR_METHOD_NAME);
        return l_outputCommonRequest;
    }

    /**
     * (notify予約注文登録) <BR>
     * 予約注文の登録をルールエンジンサーバに通知する。 <BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装） <BR>
     * 
     * @@param l_lngSubOrderId -
     *            (子注文の注文ID) <BR>
     *            子注文の注文ID。 <BR>
     * @@roseuid 435EE19103E2
     */
    protected void notifyRsvOrderRegister(long l_lngSubOrderId)
    {
        final String STR_METHOD_NAME = " notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return;
    }
}@
