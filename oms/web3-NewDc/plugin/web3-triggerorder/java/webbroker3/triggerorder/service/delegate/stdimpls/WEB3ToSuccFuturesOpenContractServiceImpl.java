head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesOpenContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物新規建注文サービス注文Impl(WEB3ToSuccFuturesOpenContractServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 于瀟(中訊) 新規作成モデル257
Revision History : 2008/03/18 于瀟(中訊) 仕様変更モデル290
Revision History : 2008/03/19 于瀟(中訊) 仕様変更モデル294
Revision History : 2008/04/01 于瀟(中訊) 仕様変更モデル310
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractRequestAdapter;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractServiceImpl;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）先物新規建注文サービスImpl)<BR>
 * （連続）先物新規建注文サービス実装クラス<BR>
 *
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ToSuccFuturesOpenContractServiceImpl
    extends WEB3FuturesOpenContractServiceImpl
    implements WEB3ToSuccFuturesOpenContractService
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesOpenContractServiceImpl.class);

    /**
     * @@roseuid 47D882AE00A9
     */
    public WEB3ToSuccFuturesOpenContractServiceImpl()
    {

    }

    /**
     * （連続）先物新規建注文サービス処理を実施する。<BR>
     * <BR>
     * 　@リクエストデータの型により、以下のメソッドを呼び分ける。 <BR>
     * <BR>
     * 　@　@[（連続）先物新規建注文確認リクエストの場合] <BR>
     * 　@　@　@this.validate注文()をコールする。 <BR>
     * 　@　@[（連続）先物新規建注文完了リクエストの場合] <BR>
     * 　@　@　@this.submit注文()をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A93E3E0063
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

        //リクエストデータの型により、以下のメソッドを呼び分ける
        //[（連続）先物新規建注文確認リクエストの場合]
        //　@this.validate注文()をコールする。
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3SuccFuturesOpenConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccFuturesOpenConfirmRequest)l_request);
        }
        //[（連続）先物新規建注文完了リクエストの場合]
        //　@this.submit注文()をコールする。
        else if (l_request instanceof WEB3SuccFuturesOpenCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccFuturesOpenCompleteRequest)l_request);
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
     * （連続）先物新規建発注審査を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（（連続）先物新規建サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）株価指数先物新規建注文確認リクエストデータオブジェクト。<BR>
     * @@return WEB3SuccFuturesOpenConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A93E3E0034
     */
    protected WEB3SuccFuturesOpenConfirmResponse validateOrder(WEB3SuccFuturesOpenConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccFuturesOpenConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする
        l_request.validate();

        //補助口座を取得する
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)getSubAccount();

        //親注文の注文単位を取得する
        //[引数]
        //（親注文）注文ID ： リクエスト.連続注文共通情報.（親注文）注文ID
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(Long.parseLong(l_request.succCommonInfo.parentOrderId));

        //反対取引指定時のリクエストデータについてプロパティチェックを行う
        //[引数]
        //リクエストデータ ： 引数.リクエストデータ
        //親注文の注文単位 ： 連続注文マネージャ.get先物OP親注文の注文単位()の戻り値
        this.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);

        //連続注文共通のチェックを行う
        //[引数]
        //補助口座 ： 取得した補助口座オブジェクト
        //銘柄タイプ ： "先物OP"（固定）
        //先物／オプション区分 ： "先物"（固定）
        //連続注文取引区分 ： リクエスト.連続注文共通情報.連続注文取引区分
        //親注文の注文単位 ： 取得した親注文の注文単位オブジェクト
        l_orderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.FUTURES,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //連続注文最大設定数を超過しないかチェックを行う
        //[引数]
        //親注文の注文単位 ： 取得した親注文の注文単位オブジェクト
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //先物新規建発注時確認処理を行う
        //[引数]
        //リクエストデータ ： リクエストデータ
        WEB3SuccFuturesOpenConfirmResponse l_response =
            (WEB3SuccFuturesOpenConfirmResponse)super.validateOrder(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * （連続）先物新規建注文登録を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（（連続）先物新規建サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）株価指数先物新規建注文完了リクエストデータオブジェクト。<BR>
     * @@return WEB3SuccFuturesOpenCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A93E3E0053
     */
    protected WEB3SuccFuturesOpenCompleteResponse submitOrder(WEB3SuccFuturesOpenCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccFuturesOpenCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする
        l_request.validate();

        //補助口座を取得する
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)getSubAccount();

        //親注文の注文単位を取得する
        //[引数]
        //（親注文）注文ID ： リクエスト.連続注文共通情報.（親注文）注文ID
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(Long.parseLong(l_request.succCommonInfo.parentOrderId));

        //反対取引指定時のリクエストデータについてプロパティチェックを行う
        //　@[引数]
        //リクエストデータ ： 引数.リクエストデータ
        //親注文の注文単位 ： 連続注文マネージャ.get先物OP親注文の注文単位()の戻り値
        this.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);

        //連続注文共通のチェックを行う
        //[引数]
        //補助口座 ： 取得した補助口座オブジェクト
        //銘柄タイプ ： "先物OP"（固定）
        //先物／オプション区分 ： "先物"（固定）
        //連続注文取引区分 ： リクエスト.連続注文共通情報.連続注文取引区分
        //親注文の注文単位 ： 取得した親注文の注文単位オブジェクト
        l_orderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.FUTURES,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //連続注文最大設定数を超過しないかチェックを行う
        //[引数]
        //親注文の注文単位 ： 取得した親注文の注文単位オブジェクト
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //先物新規建注文登録処理を行う
        //[引数]
        //リクエストデータ ： リクエストデータ
        WEB3SuccFuturesOpenCompleteResponse l_response =
            (WEB3SuccFuturesOpenCompleteResponse)super.submitOrder(l_request);

        //予約注文の登録をルールエンジンサーバに通知する
        //[引数]
        //子注文の注文ID ： リクエスト.注文ID
        this.notifyRsvOrderRegister(Long.parseLong(l_request.orderId));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate取引余力)<BR>
     * 証拠金のチェックを行う。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_orderSpecIntercepter - (注文内容インタセプタ)<BR>
     * 注文内容インタセプタ。<BR>
     * @@param l_orderSpec - (注文内容)<BR>
     * 注文内容。<BR>
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ。<BR>
     * <BR>
     * true ： 余力再計算処理を実施する <BR>
     * false ： 余力再計算処理を実施しない<BR>
     * @@return WEB3TPTradingPowerResult
     * @@roseuid 47ABD8B30056
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        SubAccount l_subAccount,
        Object[] l_orderSpecIntercepter,
        Object[] l_orderSpec,
        boolean l_blnUpdateFlg)
    {
        return null;
    }

    /**
     * (validate連続注文最大設定数)<BR>
     * 連続注文の最大設定数を超過しないかチェックをする。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図<BR>
     * 「（（連続）先物新規建サービス）validate注文／（（連続）先物新規建サービス）submit注文」参照<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47ABB71D0202
     */
    protected void validateSuccOrderMaxQuantity(IfoOrderUnit l_parentOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSuccOrderMaxQuantity(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //連続注文最大設定数を超過しないかチェックをする
        //[引数]
        //親注文の注文単位　@：　@引数.親注文の注文単位オブジェクト
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        l_orderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateリクエストデータat反対取引)<BR>
     * 反対取引指定時に、固有の（リクエストデータ）プロパティチェックを行う。 <BR>
     * <BR>
     * 　@１） 連続注文マネージャImpl.is反対売買取引() == false（＝反対取引でない）の場合、<BR>
     * 　@　@　@　@　@returnする。<BR>
     * <BR>
     * 　@　@　@ [引数設定仕様]<BR>
     * <BR>
     * 　@　@　@　@ 連続注文取引区分 ： 引数.リクエスト.連続注文共通情報.連続注文取引区分<BR>
     * 　@　@　@　@ 親注文の注文単位 ： 引数.親注文の注文単位オブジェクト<BR>
     * <BR>
     * 　@２） 以下の条件のいずれかに該当する場合は、<BR>
     * 　@　@　@　@ 「反対取引時の銘柄指定が、親注文と不整合」の例外をthrowする。<BR>
     * 　@　@　@ 引数.リクエストデータ.銘柄コード ≠ <BR>
     * 　@　@　@　@　@引数.親注文の注文単位.銘柄IDに該当する銘柄コード<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02250<BR>
     * <BR>
     * 　@　@　@ ※引数のリクエストデータは、以下のいずれかにキャストすること。<BR>
     * 　@　@　@　@ ・（連続）先物新規建注文確認リクエスト<BR>
     * 　@　@　@　@ ・（連続）先物新規建注文完了リクエスト<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47ABB71D01FF
     */
    protected void validateRequestDataAtReversingTrade(
        WEB3GenRequest l_request,
        IfoOrderUnit l_parentOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateRequestDataAtReversingTrade(WEB3GenRequest, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccCommonInfo l_succCommonInfo = null;
        String l_strProductCode = null;
        if (l_request instanceof WEB3SuccFuturesOpenConfirmRequest)
        {
            WEB3SuccFuturesOpenConfirmRequest l_succFuturesOpenConfirmRequest =
                (WEB3SuccFuturesOpenConfirmRequest)l_request;
            l_succCommonInfo = l_succFuturesOpenConfirmRequest.succCommonInfo;
            l_strProductCode = l_succFuturesOpenConfirmRequest.futProductCode;
        }
        else if (l_request instanceof WEB3SuccFuturesOpenCompleteRequest)
        {
            WEB3SuccFuturesOpenCompleteRequest l_succFuturesOpenCompleteRequest =
                (WEB3SuccFuturesOpenCompleteRequest)l_request;
            l_succCommonInfo = l_succFuturesOpenCompleteRequest.succCommonInfo;
            l_strProductCode = l_succFuturesOpenCompleteRequest.futProductCode;
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

        //連続注文マネージャImpl.is反対売買取引() == false（＝反対取引でない）の場合、returnする
        //[引数設定仕様]
        //連続注文取引区分 ： 引数.リクエスト.連続注文共通情報.連続注文取引区分
        //親注文の注文単位 ： 引数.親注文の注文単位オブジェクト
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        if (!l_orderManager.isReversingTrade(
            l_succCommonInfo.succTradingType,
            l_parentOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //以下の条件のいずれかに該当する場合は、「反対取引時の銘柄指定が、親注文と不整合」の例外をthrowする
        //引数.リクエストデータ.銘柄コード ≠ 引数.親注文の注文単位.銘柄IDに該当する銘柄コード
        IfoProductImpl l_product = (IfoProductImpl)l_parentOrderUnit.getProduct();
        if ((l_strProductCode == null) || !l_strProductCode.equals(l_product.getProductCode()))
        {
           log.debug("反対取引時の銘柄指定が、親注文と不整合です。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02250,
               getClass().getName() + "." + STR_METHOD_NAME,
               "反対取引時の銘柄指定が、親注文と不整合です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit新規建注文)<BR>
     * 予約注文の注文登録を行う。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 　@連続注文マネージャ.submit先物OP新規建新規予約注文()をコールする。<BR>
     * <BR>
     * 　@[引数設定仕様]<BR>
     * <BR>
     * 　@　@補助口座 ： 引数.補助口座<BR>
     * 　@　@注文内容 ： 引数.先物新規建注文内容<BR>
     * 　@　@注文ID ： 引数.注文ID<BR>
     * 　@　@取引パスワード ： 引数.取引パスワード<BR>
     * 　@　@連続注文取引区分 ： <BR>
     * 　@　@　@引数.リクエストアダプタ.リクエスト.連続注文共通情報.連続注文取引区分<BR>
     * 　@　@単価調整値 ： <BR>
     * 　@　@　@引数.リクエストアダプタ.リクエスト.単価調整値情報.get単価調整値()<BR>
     * 　@　@※引数のリクエストアダプタ.リクエスト.単価調整値情報 == nullの場合、<BR>
     * 　@　@　@nullをセット<BR>
     * 　@　@親注文の注文単位 ： 引数.リクエストアダプタ.親注文の注文単位<BR>
     * 　@　@先物OP概算受渡代金計算結果 ： 引数.先物OP概算受渡代金計算結果<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_openContractOrderSpec - (新規建注文内容)<BR>
     * 新規建注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 予約注文の注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_requestAdaptor - (新規建リクエストアダプタ)<BR>
     * 先物新規建リクエストアダプタオブジェクト。<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (先物OP概算受渡代金計算結果)<BR>
     * 先物OP概算受渡代金計算結果。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47ABB71D01F0
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        IfoOpenContractOrderSpec l_openContractOrderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3FuturesOpenContractRequestAdapter l_requestAdaptor,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOpenContractOrder("
            + "WEB3GentradeSubAccount, "
            + "IfoOpenContractOrderSpec, "
            + "long, "
            + "String, "
            + "WEB3FuturesOpenContractRequestAdapter, "
            + "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccFuturesOpenContractRequestAdapter l_adapter = null;
        IfoOrderUnit l_ifoOrderUnit = null;
        if (l_requestAdaptor instanceof WEB3ToSuccFuturesOpenContractRequestAdapter)
        {
            l_adapter =
                (WEB3ToSuccFuturesOpenContractRequestAdapter)l_requestAdaptor;
            l_ifoOrderUnit = l_adapter.parentOrderUnit;
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

        //連続注文マネージャ.submit先物OP新規建新規予約注文()をコールする
        //[引数設定仕様]
        //補助口座 ： 引数.補助口座
        //注文内容 ： 引数.先物新規建注文内容
        //注文ID ： 引数.注文ID
        //取引パスワード ： 引数.取引パスワード
        //連続注文取引区分 ： 引数.リクエストアダプタ.リクエスト.連続注文共通情報.連続注文取引区分
        //単価調整値 ： 引数.リクエストアダプタ.リクエスト.単価調整値情報.get単価調整値()
        //※引数のリクエストアダプタ.リクエスト.単価調整値情報 == nullの場合、nullをセット
        //親注文の注文単位 ： 引数.リクエストアダプタ.親注文の注文単位
        //先物OP概算受渡代金計算結果 ： 引数.先物OP概算受渡代金計算結果
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3SuccCommonInfo l_succCommonInfo = null;
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccFuturesOpenCompleteRequest)
        {
            WEB3SuccFuturesOpenCompleteRequest l_succFuturesOpenCompleteRequest =
                (WEB3SuccFuturesOpenCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_succFuturesOpenCompleteRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_succFuturesOpenCompleteRequest.priceAdjustmentValueInfo;
        }

        Double l_price = null;
        if (l_priceAdjustmentValueInfo == null)
        {
            l_price = null;
        }
        else
        {
            l_price = new Double(l_priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        l_orderManager.submitIfoOpenContractNewOrder(
            l_subAccount,
            (WEB3IfoOpenContractOrderSpec)l_openContractOrderSpec,
            l_lngOrderId,
            l_strTradingPassword,
            l_succCommonInfo.succTradingType,
            l_price,
            l_ifoOrderUnit,
            l_estimateDeliveryAmountCalcResult);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify予約注文登録)<BR>
     * 予約注文の登録をルールエンジンサーバへ通知する。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（（連続）先物新規建サービス）submit注文」参照<BR>
     * @@param l_lngRsvOrderId - (子注文の注文ID)<BR>
     * 子注文の注文ID。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47ABB71D0204
     */
    protected void notifyRsvOrderRegister(long l_lngRsvOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);

        //予約注文の登録をルールエンジンサーバへ通知する
        //連続注文マネージャ.get先物OP予約注文単位()にて、予約注文単位を取得する
        //[引数]
        //注文ID　@：　@引数.子注文の注文ID
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit = null;
        l_ifoOrderUnit = l_orderManager.getReserveIfoOrderUnit(l_lngRsvOrderId);

        //先物注文マネージャ.notifyルールエンジンサーバ()をコールする
        //[引数]
        //注文単位 ： get先物OP予約注文単位()の戻り値
        //処理 ： NEW_OPEN_CONTRACT_ORDER
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        l_orderMgr.notifyRLS(
            l_ifoOrderUnit,
            OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 　@（連続）先物新規建リクエストアダプタ.create()をコールする。<BR>
     * <BR>
     * 　@[引数設定仕様]<BR>
     * <BR>
     * 　@　@リクエスト ： 引数.リクエスト<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3FuturesOpenContractRequestAdapter
     * @@throws WEB3BaseException
     * @@roseuid 47B1207F0191
     */
    protected WEB3FuturesOpenContractRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //（連続）先物新規建リクエストアダプタ.create()をコールする
        //[引数設定仕様]
        //リクエスト ： 引数.リクエスト
        WEB3FuturesOpenContractRequestAdapter l_adapter =
            WEB3ToSuccFuturesOpenContractRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (set単価)<BR>
     * 引数のレスポンス．調整後単価に単価を設定する。<BR>
     * <BR>
     * 　@ リクエストデータ.単価調整値情報 ≠ null（±指値指定）の場合<BR>
     * 　@　@　@レスポンス.調整後単価に、<BR>
     * 　@　@　@引数のリクエストアダプタ．get単価()の戻り値をセットする。<BR>
     * <BR>
     * 　@ 上記以外の場合<BR>
     * 　@　@ returnをする。<BR>
     * @@param l_adapter - (先物新規建注文リクエストアダプタ)<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンスオブジェクト。<BR>
     * @@roseuid 47B104C702DA
     */
    protected void setPrice(WEB3FuturesOpenContractRequestAdapter l_adapter, WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setPrice(WEB3FuturesOpenContractRequestAdapter, WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータ.単価調整値情報 ≠ null（±指値指定）の場合
        //レスポンス.調整後単価に、引数のリクエストアダプタ．get単価()の戻り値をセットする
        WEB3ToSuccFuturesOpenContractRequestAdapter l_requestAdapter =
            (WEB3ToSuccFuturesOpenContractRequestAdapter)l_adapter;
        WEB3SuccFuturesOpenConfirmRequest l_request =
            (WEB3SuccFuturesOpenConfirmRequest)l_requestAdapter.request;

        //リクエスト.単価調整値情報≠null（±指値指定）の場合
        if (l_request.priceAdjustmentValueInfo != null)
        {
            WEB3SuccFuturesOpenConfirmResponse l_confirmResponse =
                (WEB3SuccFuturesOpenConfirmResponse)l_response;
            l_confirmResponse.afterAdjustmentPrice = WEB3StringTypeUtility.formatNumber(l_requestAdapter.getPrice());
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
