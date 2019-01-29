head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEqTypeSwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文株式切替一件サービスImpl(WEB3ToWLimitEqTypeSwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/17 徐宏偉 (中訊) 新規作成 （モデル）No.176 （DB更新仕様）No.025
Revesion History : 2006/11/30 徐宏偉 (中訊)（モデル）No.195 （DB更新仕様）No.032　@No.033
Revesion History : 2006/12/11 徐宏偉 (中訊)（モデル）No.207
Revesion History : 2007/01/29 柴双紅 (中訊)（モデル）No.227
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEqTypeSwitchUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (W指値注文株式切替一件サービスImpl)<BR>
 * 抽象クラス（abstract）<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public abstract class WEB3ToWLimitEqTypeSwitchUnitServiceImpl
    implements WEB3ToWLimitEqTypeSwitchUnitService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToWLimitEqTypeSwitchUnitServiceImpl.class);

    /**
     * W指値注文処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 『（W指値注文株式切替一件サービス）submit』<BR>
     * 参照。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     */
    public void submit(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submit(EqTypeOrderUnit l_eqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // getOrderUnit
        //注文単位を再取得する。
        //引数は以下の通りにセットする。
        //注文単位ID：　@パラメータ.注文単位.注文単位ID
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        try
        {
            l_eqTypeOrderUnit =
                (EqTypeOrderUnit)l_equityOrderManager.getOrderUnit(
                    l_eqTypeOrderUnit.getOrderUnitId());

        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //is処理対象(EqTypeOrderUnit)
        //指定された注文がW指値切替の対象であるかを判定する
        //引数は以下の通りにセットする。
        //注文単位：　@注文単位
        boolean l_blnIsProcessObject = this.isProcessObject(l_eqTypeOrderUnit);

        //（分岐フロー：　@処理対象外（is処理対象 == false）の場合）
        if (!l_blnIsProcessObject)
        {
            //処理対象外の場合、
            //何もせずそのままreturnする。
            //（正常ステータスで終了）
            log.debug("処理対象外（is処理対象 == false）の場合");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //validate切替処理可能(EqTypeOrderUnit)
        //W指値切替処理が可能な注文状態か判定する。
        //引数は以下の通りにセットする。
        //注文単位：　@注文単位
        //validate切替処理可能(EqTypeOrderUnit)
        //上位に例外をスローする
        this.validateSwitchPossible(l_eqTypeOrderUnit);
        boolean l_blnFailed = false;
        ChangeOrderSpec l_changeOrderSpec = null;
        WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;

        try
        {
            // get補助口座(口座ID : long, 補助口座ID : long)
            //補助口座を取得する。
            //引数は以下の通りに設定する。
            //口座ID：　@注文単位.getAccountId()
            //補助口座ID：　@注文単位.getSubAccountId()
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_subAccount = l_accountManager.getSubAccount(
                l_eqTypeOrderUnit.getAccountId(),
                l_eqTypeOrderUnit.getSubAccountId());

            //get発注日
            //注日チェックを行う。
            //（注文登録時の発注日と、現在日時から求めた発注日が異なる場合は発注エラーとする）
            //引数は以下の通りにセットする。
            //確認時発注日：　@注文単位.発注日
            Date l_datBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate);

            //create注文訂正内容(EqTypeOrderUnit, 補助口座)
            //注文訂正内容を作成する。
            //引数は以下の通りにセットする。
            //注文単位：　@注文単位
            //補助口座：　@get補助口座()の戻り値
            l_changeOrderSpec =
                this.createChangeOrderSpec(l_eqTypeOrderUnit, l_subAccount);

            //get概算代金計算結果(ChangeOrderSpec, EqTypeOrderUnit, 補助口座)
            //株式注文訂正内容をセットする。
            //引数は以下の通りに設定する。
            //注文訂正内容：　@create注文訂正内容()の戻り値
            //注文単位：　@注文単位
            //補助口座：　@get補助口座()の戻り値
            l_equityEstimatedPrice =
                this.getEstimatedPrice(l_changeOrderSpec, l_eqTypeOrderUnit, l_subAccount);

            //validate(ChangeOrderSpec, 補助口座)
            //株式注文訂正内容をセットする。
            //引数は以下の通りに設定する。
            //注文訂正内容：　@create注文訂正内容()の戻り値
            //補助口座：　@get補助口座()の戻り値
            EqTypeOrderValidationResult l_validationResult =
                this.validate(l_changeOrderSpec, l_subAccount);

            //validate取引余力(ChangeOrderSpec, EqTypeOrderValidationResult,
            //概算代金計算結果, EqTypeOrderUnit, 補助口座)
            //余力チェック及び、余力残高を更新する。
            //引数は以下の通りに設定する。
            //注文訂正内容：　@create注文訂正内容()の戻り値
            //発注審査結果：　@validate()の戻り値
            //概算代金計算結果：　@get概算代金計算結果()の戻り値
            //注文単位：　@注文単位
            //補助口座：　@get補助口座()の戻り値
            this.validateTradingPower(
                l_changeOrderSpec,
                l_validationResult,
                l_equityEstimatedPrice,
                l_eqTypeOrderUnit,
                l_subAccount);

        }
        catch (Exception l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            l_blnFailed = true;
        }
        if (!l_blnFailed)
        {
            //（実行結果に応じて注文データをUPDATEする）
            //正常終了した場合
            //submit切替(ChangeOrderSpec, 概算代金計算結果, 補助口座)
            //正常終了した場合、注文の切替を行う。
            //引数は以下の通りに設定する。
            //注文訂正内容：　@create注文訂正内容()の戻り値
            //概算代金計算結果：　@get概算代金計算結果()の戻り値
            //補助口座：　@get補助口座()の戻り値
            log.debug("正常終了した場合、注文の切替を行う。");
            this.submitSwitch(l_changeOrderSpec, l_equityEstimatedPrice, l_subAccount);
        }
        else
        {
            //例外がスローされた場合、
            //submit切替失敗()をコールする前に、
            //エラーログを出力する。
            //submit切替失敗(EqTypeOrderUnit, 補助口座, ChangeOrderSpec)
            //処理中に例外がスローされた場合、注文の切替を行う。
            //引数は以下の通りに設定する。
            //注文単位：　@注文単位
            //補助口座：　@get補助口座()の戻り値
            //注文訂正内容：　@create注文訂正内容()の戻り値
            log.debug("処理中に例外がスローされた場合、注文の切替を行う。");
            this.submitSwitchFail(l_eqTypeOrderUnit, l_subAccount, l_changeOrderSpec);
        }

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (is処理対象)<BR>
     * 指定の注文がW指値切替の処理対象であるかを判定する。<BR>
     * 処理対象の場合、trueを、処理対象外の場合、falseを返却する。<BR>
     * <BR>
     * 以下の条件、全てに該当する場合 <BR>
     * 処理対象としtrueを返却する。 <BR>
     * 以外、falseを返却する。 <BR>
     * <BR>
     * 　@・パラメータ.注文単位.注文有効状態 == "オープン"<BR>
     * 　@・パラメータ.注文単位.発注条件 == "W指値" <BR>
     * 　@・切替処理未済（拡張株式注文マネージャ.is未発注注文(注文単位) == true）<BR>
     * @@param l_orderUnit - (株式注文単位)
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isProcessObject(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isProcessObject(EqTypeOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //指定の注文がW指値切替の処理対象であるかを判定する。
        //処理対象の場合、trueを、処理対象外の場合、falseを返却する。
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderingCondition = l_eqtypeOrderUnitRow.getOrderConditionType();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsNotOrderedOrder = l_orderManager.isNotOrderedOrder(l_orderUnit);

        //以下の条件、全てに該当する場合
        //処理対象としtrueを返却する。
        //以外、falseを返却する。
        //
        //　@・パラメータ.注文単位.注文有効状態 == "オープン"
        //　@・パラメータ.注文単位.発注条件 == "W指値"
        //　@・切替処理未済（拡張株式注文マネージャ.is未発注注文(注文単位) == true）
        if (OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus())
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderingCondition)
            && l_blnIsNotOrderedOrder)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (validate切替処理可能)<BR>
     * 切替処理実施を待つ必要のある注文の場合、例外をスローする。<BR>
     * <BR>
     * １）　@注文状態チェック<BR>
     * 　@注文が市場未送信（*）の場合、<BR>
     * 　@または、パラメータ.注文単位.注文状態が以下のいずれかに該当する場合<BR>
     * 　@「受付中／訂正中／取消中の注文は切替処理不可」の <BR>
     * 　@例外をスローする。  <BR>
     * <BR>
     * 　@　@・"受付済（変更注文）" <BR>
     * 　@　@・"発注中（変更注文）" <BR>
     * 　@　@・"受付済（取消注文）" <BR>
     * 　@　@・"発注中（取消注文）" <BR>
     * <BR>
     * 　@（*）注文単位.市場から確認済みの数量＝NaNの場合、<BR>
     * 　@　@　@市場未送信の注文と判定する。<BR>
     * @@param l_orderUnit - (株式注文単位)
     * @@throws WEB3BaseException
     */
    public void validateSwitchPossible(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSwitchPossible(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("株式注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //切替処理実施を待つ必要のある注文の場合、例外をスローする。
        //１）　@注文状態チェック
        //　@注文が市場未送信（*）の場合
        //　@または、パラメータ.注文単位.注文状態が以下のいずれかに該当する場合
        //　@「受付中／訂正中／取消中の注文は切替処理不可」の
        //　@例外をスローする。
        //　@　@・"受付済（変更注文）"
        //　@　@・"発注中（変更注文）"
        //　@　@・"受付済（取消注文）"
        //　@　@・"発注中（取消注文）"
        //　@（*）注文単位.市場から確認済みの数量＝NaNの場合、
        //　@　@　@市場未送信の注文と判定する。
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if (Double.isNaN(l_orderUnit.getConfirmedQuantity())
            || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFYING.equals(l_orderStatus)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.CANCELLING.equals(l_orderStatus))
        {
            log.debug("注文が市場未送信または、受付中／訂正中／取消中の注文は切替処理不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02521,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create注文訂正内容)<BR>
     * 抽象メソッド（abstract）<BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (株式注文単位)
     * @@param l_subAccount - (補助口座)
     * @@return ChangeOrderSpec
     * @@throws WEB3BaseException
     */
    public abstract ChangeOrderSpec createChangeOrderSpec(
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * 抽象メソッド（abstract）<BR>
     * <BR>
     * @@param l_changeOrderSpec - (注文訂正内容)
     * @@param l_subAccount - (補助口座)
     * @@return EqTypeOrderValidationResult
     * @@throws WEB3BaseException
     */
    public abstract EqTypeOrderValidationResult validate(
        ChangeOrderSpec l_changeOrderSpec,
        SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (get概算代金計算結果)<BR>
     * 抽象メソッド（abstract）<BR>
     * <BR>
     * @@param l_changeOrderSpec - (注文訂正内容)
     * @@param l_eqTypeOrderUnit - (株式注文単位)
     * @@param l_subAccount - (補助口座)
     * @@return WEB3EquityEstimatedPrice
     * @@throws WEB3BaseException
     */
    public abstract WEB3EquityEstimatedPrice getEstimatedPrice(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (validate取引余力)<BR>
     * 抽象メソッド（abstract）<BR>
     * <BR>
     * @@param l_changeOrderSpec - (注文訂正内容)
     * @@param l_eqTypeOrderValidationResult - (発注審査結果)
     * @@param l_equityEstimatedPrice - (概算代金計算結果)
     * @@param l_eqTypeOrderUnit - (株式注文単位)
     * @@param l_subAccount - (補助口座)
     * @@throws WEB3BaseException
     */
    public abstract void validateTradingPower(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (submit切替)<BR>
     * 抽象メソッド（abstract）<BR>
     * <BR>
     * @@param l_changeOrderSpec - (注文訂正内容)
     * @@param l_equityEstimatedPrice - (概算代金計算結果)
     * @@param l_subAccount - (補助口座)
     * @@throws WEB3BaseException
     */
    public abstract void submitSwitch(
        ChangeOrderSpec l_changeOrderSpec,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (submit切替失敗)<BR>
     * 処理中に例外がスローされた場合、切替失敗の内容で注文を更新する。 <BR>
     * <BR>
     * １）　@拡張株式注文マネージャ.getストップ注文失効時概算代金計算結果()をコールする。 <BR>
     * <BR>
     * 　@　@※概算代金の再計算結果を取得する。 <BR>
     * 　@　@※（ストップ注文の注文単価で余力を拘束されている可能性がある為、  <BR>
     * 　@　@※　@リミット注文の注文単価で再計算を行う。）  <BR>
     * <BR>
     * 　@　@　@[getストップ注文失効時概算代金計算結果()をコールする。の引数] <BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     * 　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     * <BR>
     * ２）　@パラメータ.注文単位のcloneを作成する。  <BR>
     * <BR>
     * ３）　@ ２）にて作成したcloneに対し、DB更新仕様にそって更新値をセットする。 <BR>
     * <BR>
     * 　@　@※更新値の設定仕様 <BR>
     * 　@　@　@DB更新仕様  <BR>
     * 　@　@　@「W指値注文切替（NG）_注文単位テーブル仕様」参照。  <BR>
     * <BR>
     * ４）　@拡張株式注文マネージャ.create注文履歴()をコールする。 <BR>
     * <BR>
     * 　@　@　@[create注文履歴()に指定する引数] <BR>
     * 　@　@　@注文単位：　@３）で作成した注文単位 <BR>
     * 　@　@　@イベントタイプ：　@マーケット送信拒否 <BR>
     * <BR>
     * 　@　@※更新値の設定仕様（参考） <BR>
     * 　@　@　@DB更新仕様  <BR>
     * 　@　@　@「W指値注文切替（NG）_注文履歴テーブル仕様」参照。  <BR>
     * <BR>
     * ５）　@拡張株式注文マネージャ.update注文データ()をコールする。  <BR>
     * <BR>
     * 　@　@　@[update注文データ()に指定する引数] <BR>
     * 　@　@　@注文単位：　@３）で作成した注文単位 <BR>
     * 　@　@　@注文履歴：　@create注文履歴()の戻り値 <BR>
     * <BR>
     * ６）　@パラメータ.注文単位.注文種別が"現物買注文"の場合、または、 <BR>
     * 　@　@　@パラメータ.注文単位.注文カテゴリが"新規建注文"の場合、 <BR>
     * 　@　@　@取引余力サービス.余力再計算()をコールする。 <BR>
     * <BR>
     * 　@　@　@[余力再計算()の引数] <BR>
     * 　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (株式注文単位)
     * @@param l_subAccount - (補助口座)
     * @@param l_changeOrderSpec - (注文訂正内容)
     * @@throws WEB3BaseException
     */
    public void submitSwitchFail(
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount,
        ChangeOrderSpec l_changeOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitSwitchFail(EqTypeOrderUnit, SubAccount, ChangeOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("株式注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@拡張株式注文マネージャ.getストップ注文失効時概算代金計算結果()をコールする。
        //　@　@※概算代金の再計算結果を取得する。
        //　@　@※（ストップ注文の注文単価で余力を拘束されている可能性がある為
        //　@　@※　@リミット注文の注文単価で再計算を行う。）
        //　@　@　@[getストップ注文失効時概算代金計算結果()をコールする。の引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        //　@　@　@補助口座：　@パラメータ.補助口座
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        WEB3EquityEstimatedPrice l_estimatedPrice =
            l_orderManager.getStopOrderExpireEstimatedPrice(l_eqTypeOrderUnit, l_subAccount);

        //２）　@パラメータ.注文単位のcloneを作成する。
        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
            new EqtypeOrderUnitParams(l_eqTypeOrderUnitRow);

        //３）　@ ２）にて作成したcloneに対し、DB更新仕様にそって更新値をセットする。
        //　@　@※更新値の設定仕様
        //　@　@　@DB更新仕様
        //　@　@　@「W指値注文切替（NG）_注文単位テーブル仕様」参照。
        // 注文履歴最終通番 = （既存値） + 1
        l_eqTypeOrderUnitParams.setLastOrderActionSerialNo(
            l_eqTypeOrderUnitParams.getLastOrderActionSerialNo() + 1);

        // 発注条件 =  0：DEFAULT
        l_eqTypeOrderUnitParams.setOrderConditionType(
            WEB3OrderingConditionDef.DEFAULT);

        //発注条件 = null
        l_eqTypeOrderUnitParams.setOrderCondOperator(null);

        //逆指値基準値 = null
        l_eqTypeOrderUnitParams.setStopOrderPrice(null);

        //（W指値）訂正指値 = null
        l_eqTypeOrderUnitParams.setWLimitPrice(null);

        //注文状態 = 11：発注失敗（変更注文）
        l_eqTypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);

        //注文単価 = "インタセプタ.概算代金計算結果.get計算単価()
        //※インタセプタ.概算代金計算結果がnulｌの場合、（既存値）"
        WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor l_updateServiceInterceptor =
            new WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor(l_estimatedPrice);

        WEB3EquityEstimatedPrice l_equityEstimatedPrice =
            l_updateServiceInterceptor.l_equityEstimatedPrice;

        if (l_equityEstimatedPrice != null)
        {
            l_eqTypeOrderUnitParams.setPrice(
                l_equityEstimatedPrice.getCalcUnitPrice());
        }

        //this.指値　@※リミット注文の注文単価。
        if (l_eqTypeOrderUnitParams.getLimitPriceIsNull())
        {
            //市場から確認済みの注文単価
            l_eqTypeOrderUnitParams.setConfirmedOrderPrice(null);
        }
        else
        {
            //市場から確認済みの注文単価
            l_eqTypeOrderUnitParams.setConfirmedOrderPrice(
                l_eqTypeOrderUnitParams.getLimitPrice());
        }

        //概算受渡代金 = インタセプタ.概算代金計算結果.get概算受渡代金()
        //※インタセプタ.概算代金計算結果がnulｌの場合、（既存値）
        if (l_equityEstimatedPrice != null)
        {
            l_eqTypeOrderUnitParams.setEstimatedPrice(
                l_equityEstimatedPrice.getEstimateDeliveryAmount());
        }

        //注文訂正・取消区分 = D:W指値注文切替失敗
        l_eqTypeOrderUnitParams.setModifyCancelType(
            WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);

        //市場から確認済みの概算受渡代金 = インタセプタ.概算代金計算結果.get概算受渡代金()
        //※インタセプタ.概算代金計算結果がnulｌの場合、（既存値）
        if (l_equityEstimatedPrice != null)
        {
            l_eqTypeOrderUnitParams.setConfirmedEstimatedPrice(
                l_equityEstimatedPrice.getEstimateDeliveryAmount());
        }

        //リクエストタイプ = 5：失効
        l_eqTypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

        //更新日付 = 現在時刻
        l_eqTypeOrderUnitParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

        //元発注条件 = 更新前の発注条件
        l_eqTypeOrderUnitParams.setOrgOrderConditionType(
            l_eqTypeOrderUnitRow.getOrderConditionType());

        //元発注条件演算子 = 更新前の発注条件演算子
        l_eqTypeOrderUnitParams.setOrgOrderCondOperator(
            l_eqTypeOrderUnitRow.getOrderCondOperator());

        //元逆指値基準値 = 更新前の逆指値基準値
        if (l_eqTypeOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_eqTypeOrderUnitParams.setOrgStopOrderPrice(null);
        }
        else
        {
            l_eqTypeOrderUnitParams.setOrgStopOrderPrice(
                l_eqTypeOrderUnitRow.getStopOrderPrice());
        }

        //元（W指値）訂正指値 = 更新前の（W指値）訂正指値
        if (l_eqTypeOrderUnitRow.getWLimitPriceIsNull())
        {
            l_eqTypeOrderUnitParams.setOrgWLimitPrice(null);
        }
        else
        {
            l_eqTypeOrderUnitParams.setOrgWLimitPrice(
                l_eqTypeOrderUnitRow.getWLimitPrice());
        }

        //元（W指値）執行条件 = 更新前の（W指値）執行条件
        l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(
            l_eqTypeOrderUnitRow.getWLimitExecCondType());

        //（W指値）執行条件 = null
        l_eqTypeOrderUnitParams.setWLimitExecCondType(null);

        //４）　@拡張株式注文マネージャ.create注文履歴()をコールする。
        //　@　@　@[create注文履歴()に指定する引数]
        //　@　@　@注文単位：　@３）で作成した注文単位
        //　@　@　@イベントタイプ：　@マーケット送信拒否
        //　@　@※更新値の設定仕様（参考）
        //　@　@　@DB更新仕様
        //　@　@　@「W指値注文切替（NG）_注文履歴テーブル仕様」参照。
        EqTypeOrderUnit l_orderUnitClone =
            (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_eqTypeOrderUnitParams);
        EqTypeOrderAction l_typeOrderAction =
            (EqTypeOrderAction)l_orderManager.createOrderAction(
                l_orderUnitClone, OrderEventTypeEnum.MARKER_REFUSAL);

        //５）　@拡張株式注文マネージャ.update注文データ()をコールする。
        //　@　@　@[update注文データ()に指定する引数]
        //　@　@　@注文単位：　@３）で作成した注文単位
        //　@　@　@注文履歴：　@create注文履歴()の戻り値
        l_orderManager.updateOrderData(l_orderUnitClone, l_typeOrderAction);

        //６）　@パラメータ.注文単位.注文種別が"現物買注文"の場合、または、
        //　@　@　@パラメータ.注文単位.注文カテゴリが"新規建注文"の場合、
        //　@　@　@取引余力サービス.余力再計算()をコールする。
        //　@　@　@[余力再計算()の引数]
        //　@　@　@補助口座：　@パラメータ.補助口座

        if (OrderTypeEnum.EQUITY_BUY.equals(l_eqTypeOrderUnit.getOrderType())
            || OrderCategEnum.OPEN_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg()))
        {
            WEB3GentradeSubAccount l_gentradeSubAccount = null;
            if (l_subAccount != null)
            {
                l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
            }

            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                l_tpTradingPowerService.reCalcTradingPower(l_gentradeSubAccount);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
