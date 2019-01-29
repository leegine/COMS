head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文信用取引返済切替一件サービスImpl(WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/17 齊珂(中訊) 新規作成 （モデル）No.176 195 197
Revision History : 2007/01/16 徐宏偉 (中訊)（モデル）No.220
Revision History : 2007/01/26 唐性峰 (中訊)（モデル）No.226
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginChangeSettleContractOrderSpec;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginCloseMarginSwitchUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (W指値注文信用取引返済切替一件サービスImpl)<BR>
 *
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl
    extends WEB3ToWLimitEqTypeSwitchUnitServiceImpl
    implements WEB3ToWLimitMarginCloseMarginSwitchUnitService

{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl.class);

    /**
     * constructor
     */
    public WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl()
    {

    }

    /**
     *（create注文訂正内容）<BR>
     *（継承元クラスの同名メソッドのオーバーライド）<BR>
     *<BR>
     *信用返済注文訂正内容を作成する。<BR>
     * <BR>
     *１）　@パラメータ.注文単位.getDataSourceObject()をコールする。<BR>
     * <BR>
     *２）　@株式ポジションマネージャ.create決済建株エントリ()をコールする。<BR>
     * <BR>
     *　@　@　@[create決済建株エントリ()の引数]<BR>
     *　@　@　@注文単位ID：　@パラメータ.注文単位.注文単位ID <BR>
     * <BR>
     *３）　@注文訂正詳細オブジェクト（EqTypeContractSettleChangeOrderUnitEntry()）<BR>
     *　@　@を生成する。<BR>
     * <BR>
     *　@　@　@[EqTypeContractSettleChangeOrderUnitEntry()の引数] <BR>
     *　@　@　@注文単位ID：　@パラメータ.注文単位.注文単位ID <BR>
     *　@　@　@訂正後指値：　@注文単位Row.（W指値） 訂正指値 <BR>
     *　@　@　@決済建株エントリ：　@create決済建株エントリ()の戻り値<BR>
     * <BR>
     *４）　@拡張株式注文マネージャ.is出来るまで注文単位()をコールする。<BR>
     * <BR>
     *　@　@　@[is出来るまで注文単位()の引数] <BR>
     *　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     *５）　@信用返済注文訂正内容オブジェクトを生成する。<BR>
     * <BR>
     *　@　@　@[create返済注文訂正内容()の引数]<BR>
     *　@　@　@注文ID：　@パラメータ.注文単位.注文ID<BR>
     *　@　@　@注文訂正詳細：　@注文訂正詳細オブジェクト<BR>
     *　@　@　@訂正後値段条件：　@注文単位Row.値段条件<BR>
     *　@　@　@訂正後発注条件：　@注文単位Row.発注条件<BR>
     *　@　@　@訂正後発注条件演算子：　@注文単位Row.発注条件演算子<BR>
     *　@　@　@訂正後逆指値基準値： 注文単位Row.逆指値基準値<BR>
     *　@　@　@訂正後（W指値）訂正指値： 注文単位Row.（W指値）訂正指値<BR>
     *　@　@　@訂正後執行条件：　@注文単位Row.（W指値）執行条件<BR>
     *　@　@　@訂正後注文失効日：　@注文単位Row.注文失効日付<BR>
     *　@　@　@訂正後is出来るまで注文：　@is出来るまで注文()の戻り値<BR>
     *　@　@　@訂正後（W指値）執行条件：　@注文単位Row.（W指値）執行条件<BR>
     *　@　@　@（W指値）有効状態区分：　@"ストップ注文有効"（固定）<BR>
     * <BR>
     *６）　@信用返済注文訂正内容を返却する。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@return ChangeOrderSpec <BR>
     * @@throws WEB3BaseException
     */
    public ChangeOrderSpec createChangeOrderSpec(
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createChangeOrderSpec(EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@パラメータ.注文単位.getDataSourceObject()をコールする。
        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //パラメータ.注文単位.注文単位ID
        long l_lngEqOrderUnitId = l_eqTypeOrderUnit.getOrderUnitId();

        //注文単位Row.（W指値） 訂正指値
        double l_dblWLimitPrice = l_eqTypeOrderUnitRow.getWLimitPrice();

        //２）　@株式ポジションマネージャ.create決済建株エントリ()をコールする。
        //　@　@　@[create決済建株エントリ()の引数]
        //　@　@　@注文単位ID：　@パラメータ.注文単位.注文単位ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

        EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntry =
            l_positionManager.createCloseMarginContractEntry(l_lngEqOrderUnitId);

        //３）　@注文訂正詳細オブジェクト（EqTypeContractSettleChangeOrderUnitEntry()）を生成する。
        //　@　@　@[EqTypeContractSettleChangeOrderUnitEntry()の引数]
        //　@　@　@注文単位ID：　@パラメータ.注文単位.注文単位ID
        //　@　@　@訂正後指値：　@注文単位Row.（W指値） 訂正指値
        //　@　@　@決済建株エントリ：　@create決済建株エントリ()の戻り値
        EqTypeContractSettleChangeOrderUnitEntry l_eqTypeContractSettleChangeOrderUnitEntry =
            new EqTypeContractSettleChangeOrderUnitEntry(
                l_lngEqOrderUnitId,
                l_dblWLimitPrice,
                l_eqTypeSettleContractOrderEntry);

        //４）　@拡張株式注文マネージャ.is出来るまで注文単位()をコールする。
        //　@　@　@[is出来るまで注文単位()の引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_eqTypeOrderUnit);

        //５）信用返済注文訂正内容オブジェクトを生成する。
        //　@　@　@[create返済注文訂正内容()の引数]
        //　@　@　@注文ID：　@パラメータ.注文単位.注文ID
        //　@　@　@注文訂正詳細：　@注文訂正詳細オブジェクト
        //　@　@　@訂正後値段条件：　@注文単位Row.値段条件
        //　@　@　@訂正後発注条件：　@注文単位Row.発注条件
        //　@　@　@訂正後発注条件演算子：　@注文単位Row.発注条件演算子
        //　@　@　@訂正後逆指値基準値： 注文単位Row.逆指値基準値
        //　@　@　@訂正後（W指値）訂正指値： 注文単位Row.（W指値）訂正指値
        //　@　@　@訂正後執行条件：　@注文単位Row.（W指値）執行条件
        //　@　@　@訂正後注文失効日：　@注文単位Row.注文失効日付
        //　@　@　@訂正後is出来るまで注文：　@is出来るまで注文()の戻り値
        //　@　@　@訂正後（W指値）執行条件：　@注文単位Row.（W指値）執行条件
        //　@　@　@（W指値）有効状態区分：　@"ストップ注文有効"（固定）
        WEB3MarginChangeSettleContractOrderSpec l_orderSpec =
            WEB3MarginChangeSettleContractOrderSpec.createCloseMarginChangeOrderSpec(
                l_eqTypeOrderUnit.getOrderId(),
                l_eqTypeContractSettleChangeOrderUnitEntry,
                l_eqTypeOrderUnitRow.getPriceConditionType(),
                l_eqTypeOrderUnitRow.getOrderConditionType(),
                l_eqTypeOrderUnitRow.getOrderCondOperator(),
                l_eqTypeOrderUnitRow.getStopOrderPrice(),
                l_eqTypeOrderUnitRow.getWLimitPrice(),
                l_eqTypeOrderUnitRow.getWLimitExecCondType(),
                l_eqTypeOrderUnitRow.getExpirationDate(),
                l_blnIsCarriedOrderUnit,
                l_eqTypeOrderUnitRow.getWLimitExecCondType(),
                WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE
                );

        //６）　@信用返済注文訂正内容を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_orderSpec;
    }
    /**
     *（継承元クラスの同名メソッドのオーバーライド）<BR>
     *<BR>
     *切替注文発注審査を行う。<BR>
     *<BR>
     *１）　@パラメータ.注文訂正内容を"信用返済注文訂正内容"型にキャストする。<BR>
     *<BR>
     *２）　@拡張株式注文マネージャ.validate返済注文訂正()をコールする。<BR>
     *<BR>
     *　@　@　@[validate返済注文訂正()の引数]<BR>
     *　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     *　@　@　@信用返済注文訂正内容：　@信用返済注文訂正内容<BR>
     *　@　@　@isSkip遅延状況チェック：　@true（*1）<BR>
     *<BR>
     *　@　@　@（*1）isSkip遅延状況チェックについて<BR>
     *　@　@　@　@切替処理は遅延注文の切替処理も行うため、<BR>
     *　@　@　@　@validate注文訂正可能状態()の遅延状況チェックを行わない。<BR>
     *　@　@　@　@よってtrueを設定する。<BR>
     *<BR>
     *３）　@２）で取得したEqTypeOrderValidationResult.getProcessingResult().isFailedResult()<BR>
     * 　@　@　@　@ == true の場合<BR>
     *　@　@　@EqTypeOrderValidationResultからエラー情報を取得し、例外をスローする。<BR>
     *<BR>
     *４）　@validate返済注文訂正()の戻り値を返却する。<BR>
     * @@param l_changeOrderSpec - (注文訂正内容)<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@return EqTypeOrderValidationResult <BR>
     * @@throws WEB3BaseException
     */
    public EqTypeOrderValidationResult validate(
        ChangeOrderSpec l_changeOrderSpec,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate(ChangeOrderSpec, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@パラメータ.注文訂正内容を"信用返済注文訂正内容"型にキャストする。
        WEB3MarginChangeSettleContractOrderSpec l_marginChangeSettleContractOrderSpec =
            (WEB3MarginChangeSettleContractOrderSpec)l_changeOrderSpec;

        //２）　@拡張株式注文マネージャ.validate返済注文訂正()をコールする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //　@　@　@[validate返済注文訂正()の引数]
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@信用返済注文訂正内容：　@信用返済注文訂正内容
        //　@　@　@isSkip遅延状況チェック：　@true（*1）
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult =
            l_orderManager.validateChangeSettleContractOrder(l_subAccount,
                l_marginChangeSettleContractOrderSpec,
                true);

        //３）　@２）で取得したEqTypeOrderValidationResult.getProcessingResult().isFailedResult()
        //           == true の場合
        //　@　@　@EqTypeOrderValidationResultからエラー情報を取得し、例外をスローする。
        boolean l_blnIsFailedResult =
            l_eqTypeOrderValidationResult.getProcessingResult().isFailedResult();

        if (l_blnIsFailedResult)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_eqTypeOrderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //４）　@validate返済注文訂正()の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeOrderValidationResult;
    }

    /**
     *（get概算代金計算結果）<BR>
     *（継承元クラスの同名メソッドのオーバーライド）<BR>
     *<BR>
     *概算代金計算結果を取得する。<BR>
     *<BR>
     *１）　@パラメータ.注文訂正内容を"信用返済注文訂正内容"型にキャストする。<BR>
     *<BR>
     *２）　@信用返済注文訂正内容.get注文訂正詳細()をコールする。<BR>
     *<BR>
     *３）　@株式計算サービス.create手数料()をコールする。<BR>
     *<BR>
     *　@　@　@[create手数料()の引数]<BR>
     *　@　@　@注文単位：　@パラメータ.注文単位<BR>
     *<BR>
     *４）３）で取得した手数料.setIs指値()をコールする。<BR>
     * <BR>
     * [setIs指値()の引数]<BR>
     * is指値：　@注文訂正詳細.getAfterChangePrice()≠"0"の場合のみ、trueをセット<BR>
     * 　@　@　@　@　@　@以外、falseをセット<BR>
     * <BR>
     *５）　@拡張株式注文マネージャ.calc概算決済損益代金()をコールする。<BR>
     *<BR>
     *　@　@　@[calc概算決済損益代金()の引数]<BR>
     *　@　@　@手数料：　@create手数料()の戻り値() (*1)<BR>
     *　@　@　@指値：　@注文訂正詳細.getAfterChangePrice()<BR>
     *　@　@　@補助口座：　@パラメータ.補助口座<BR>
     *　@　@　@取引銘柄：　@パラメータ.注文単位..getTradedProduct()<BR>
     *　@　@　@決済建株エントリ：　@注文訂正詳細.getAfterChangeSettleContractOrderEntries()<BR>
     *　@　@　@数量：　@注文訂正詳細.getAfterChangeTotalQuantity()<BR>
     *　@　@　@注文単位：　@パラメータ.注文単位<BR>
     *　@　@　@今回約定数量：　@0（固定）<BR>
     *　@　@　@今回約定単価：　@0（固定）<BR>
     *　@　@　@isSkip金額チェック：　@false（スキップしない）固定<BR>
     *<BR>
     *　@　@　@(*1)各項目、訂正後（ストップ注文）の値をセットする。<BR>
     *<BR>
     *6）　@概算決済損益代金計算結果オブジェクトを返却する。<BR>
     * @@param l_changeOrderSpec - (注文訂正内容)<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@return WEB3EquityEstimatedPrice
     * @@throws WEB3BaseException
     */
    public WEB3EquityEstimatedPrice getEstimatedPrice(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimatedPrice(ChangeOrderSpec, "
            + "EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null || l_eqTypeOrderUnit == null || l_subAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@パラメータ.注文訂正内容を"信用返済注文訂正内容"型にキャストする。
        WEB3MarginChangeSettleContractOrderSpec l_marginChangeSettleContractOrderSpec =
            (WEB3MarginChangeSettleContractOrderSpec)l_changeOrderSpec;

        //２）　@信用返済注文訂正内容.get注文訂正詳細()をコールする。
        EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry =
            l_marginChangeSettleContractOrderSpec.getChangeOrderUnitEntry();

        //３）　@株式計算サービス.create手数料()をコールする。
        //　@　@　@[create手数料()の引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityBizLogicProvider l_eqBizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();

        WEB3GentradeCommission l_commission =
            l_eqBizLogicProvider.createCommission((EqTypeOrderUnitImpl)l_eqTypeOrderUnit);

        //４）　@３）で取得した手数料.setIs指値()をコールする。
        //　@[setIs指値()の引数]
        //　@is指値：　@注文訂正詳細.getAfterChangePrice()≠"0"の場合のみ、trueをセット
        //　@　@　@　@　@　@　@以外、falseをセット
        boolean l_blnIsLimitPrice = false;
        if (l_changeOrderUnitEntry.getAfterChangePrice() != 0)
        {
            l_blnIsLimitPrice = true;
        }
        l_commission.setIsLimitPrice(l_blnIsLimitPrice);

        //４）　@拡張株式注文マネージャ.calc概算決済損益代金()をコールする。
        //　@　@　@[calc概算決済損益代金()の引数]
        //　@　@　@手数料：　@create手数料()の戻り値() (*1)
        //　@　@　@指値：　@注文訂正詳細.getAfterChangePrice()
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@取引銘柄：　@パラメータ.注文単位..getTradedProduct()
        //　@　@　@決済建株エントリ：　@注文訂正詳細.getAfterChangeSettleContractOrderEntries()
        //　@　@　@数量：　@注文訂正詳細.getAfterChangeTotalQuantity()
        //　@　@　@注文単位：　@パラメータ.注文単位
        //　@　@　@今回約定数量：　@0（固定）
        //　@　@　@今回約定単価：　@0（固定）
        //　@　@　@isSkip金額チェック：　@false（スキップしない）固定
        //　@　@　@(*1)各項目、訂正後（ストップ注文）の値をセットする。
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        SubAccountRow l_subAcctRow =
            (SubAccountRow)l_subAccount.getDataSourceObject();
        WEB3GentradeSubAccount l_subAcc = new WEB3GentradeSubAccount(l_subAcctRow);

        WEB3EquityTradedProduct l_tradeProduct =
            (WEB3EquityTradedProduct)l_eqTypeOrderUnit.getTradedProduct();

        WEB3EquityRealizedProfitAndLossPrice l_estimatedPrice =
            l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_commission,
                l_changeOrderUnitEntry.getAfterChangePrice(),
                l_subAcc,
                l_tradeProduct,
                l_changeOrderUnitEntry.getAfterChangeSettleContractOrderEntries(),
                l_changeOrderUnitEntry.getAfterChangeTotalQuantity(),
                l_eqTypeOrderUnit,
                0,
                0,
                false);
        
        //５）　@概算決済損益代金計算結果オブジェクトを返却する。 
        log.exiting(STR_METHOD_NAME);
        return l_estimatedPrice;
    }

    /**
     * （validate取引余力）<BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * ※未実装<BR>
     * @@param l_changeOrderSpec - (注文訂正内容)<BR>
     * @@param l_eqTypeOrderValidationResult - (発注審査結果)<BR>
     * @@param l_equityEstimatedPrice - (概算代金計算結果)<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@throws WEB3BaseException
     */
    public void validateTradingPower(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {

    }

    /**
     *（submit切替）<BR>
     *（継承元クラスの同名メソッドのオーバーライド）<BR>
     *<BR>
     *注文の切替を行う。<BR>
     *<BR>
     *１）　@パラメータ.注文訂正内容を"信用返済注文訂正内容"型にキャストする。 <BR>
     *<BR>
     *２）　@パラメータ.注文単位.取引者ID≠nullの場合、 <BR>
     *　@　@拡張金融オブジェクトマネージャ.getTrader()をコールする。<BR>
     *<BR>
     *　@　@　@[getTrader()の引数] <BR>
     *　@　@　@取引者ID：　@パラメータ.注文単位.取引者ID <BR>
     *<BR>
     *３）　@W指値注文株式切替更新インタセプタ()をコールする。 <BR>
     *<BR>
     *　@　@　@[W指値注文株式切替更新インタセプタ()の引数] <BR>
     *　@　@　@概算代金計算結果：　@パラメータ.概算代金計算結果 <BR>
     *<BR>
     *４）　@拡張株式注文マネージャ.setThreadLocalPersistenceEventInterceptor()をコールする。<BR>
     *<BR>
     *　@　@　@[setThreadLocalPersistenceEventInterceptor()の引数] <BR>
     *　@　@　@arg0：　@W指値注文株式切替更新インタセプタ <BR>
     *<BR>
     *５）　@拡張株式注文マネージャ.submitChangeSettleContractOrder()をコールする。 <BR>
     *<BR>
     *　@　@　@[submitChangeSettleContractOrder()の引数] <BR>
     *　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     *　@　@　@返済注文訂正内容：　@信用返済注文訂正内容 <BR>
     *　@　@　@取引パスワード：　@getMainAccount().getTradingPassword()の戻り値をdecryptした値  <BR>
     *　@　@　@isSkip発注審査：　@true <BR>
     *<BR>
     *６）　@取引余力サービス.余力再計算()をコールする。 <BR>
     *<BR>
     *　@　@　@[余力再計算()の引数] <BR>
     *　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     * @@param l_changeOrderSpec - (注文訂正内容)<BR>
     * @@param l_equityEstimatedPrice - (概算代金計算結果)<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@throws WEB3BaseException
     */
    public void submitSwitch(
        ChangeOrderSpec l_changeOrderSpec,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSwitch(ChangeOrderSpec, "
            + "WEB3EquityEstimatedPrice, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null || l_subAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@パラメータ.注文訂正内容を"信用返済注文訂正内容"型にキャストする。
        WEB3MarginChangeSettleContractOrderSpec l_marginChangeSettleContractOrderSpec =
            (WEB3MarginChangeSettleContractOrderSpec)l_changeOrderSpec;
        
        //２）　@パラメータ.補助口座.getMainAccount()をコールする。
        MainAccount l_mainAcc = l_subAccount.getMainAccount();

        //３）　@W指値注文株式切替更新インタセプタ()をコールする。
        //　@　@　@[W指値注文株式切替更新インタセプタ()の引数]
        //　@　@　@概算代金計算結果：　@パラメータ.概算代金計算結果
        WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor l_interceptor =
            new WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor(l_equityEstimatedPrice);

        //４）　@拡張株式注文マネージャ.setThreadLocalPersistenceEventInterceptor()をコールする。
        //　@　@　@[setThreadLocalPersistenceEventInterceptor()の引数]
        //　@　@　@arg0：　@W指値注文株式切替更新インタセプタ
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //５）　@拡張株式注文マネージャ.submitChangeSettleContractOrder()をコールする。
        //　@　@　@[submitChangeSettleContractOrder()の引数]
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@返済注文訂正内容：　@信用返済注文訂正内容
        //　@　@　@取引パスワード：　@getMainAccount().getTradingPassword()の戻り値をdecryptした値
        //　@　@　@isSkip発注審査：　@true
        WEB3Crypt l_webCrypt = new WEB3Crypt();
        EqTypeOrderSubmissionResult l_submitRes =
            l_orderManager.submitChangeSettleContractOrder(l_subAccount,
                l_marginChangeSettleContractOrderSpec,
                l_webCrypt.decrypt(l_mainAcc.getTradingPassword()),
                true);

        if (l_submitRes.getProcessingResult().isFailedResult())
        {
            log.debug(" __Error[注文訂正更新]__"
                + l_submitRes.getProcessingResult().getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_submitRes.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //６）　@取引余力サービス.余力再計算()をコールする。
        //　@　@　@[余力再計算()の引数]
        //　@　@　@補助口座：　@パラメータ.補助口座
        SubAccountRow l_subAcctRow =
            (SubAccountRow)l_subAccount.getDataSourceObject();
        WEB3GentradeSubAccount l_subAcc = new WEB3GentradeSubAccount(l_subAcctRow);

        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        l_tradingPowerService.reCalcTradingPower(l_subAcc);

        log.exiting(STR_METHOD_NAME);
    }
}
@
