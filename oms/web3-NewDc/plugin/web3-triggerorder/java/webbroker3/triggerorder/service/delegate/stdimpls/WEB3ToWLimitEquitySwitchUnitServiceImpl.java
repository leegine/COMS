head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEquitySwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文現物株式切替一件サービスImpl(WEB3ToWLimitEquitySwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/17 徐宏偉 (中訊) 新規作成 （モデル）No.176
Revesion History : 2006/11/30 徐宏偉 (中訊)（モデル） No.193 No.194　@No.195
Revesion History : 2006/12/11 徐宏偉 (中訊)（モデル）No.207
Revesion History : 2007/01/15 徐宏偉 (中訊)（モデル）No.220
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityChangeOrderSpec;
import webbroker3.equity.WEB3EquityChangeOrderUnitEntry;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderManagerChangeOrderEventInterceptor;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquitySwitchUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (W指値注文現物株式切替一件サービスImpl)
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3ToWLimitEquitySwitchUnitServiceImpl
    extends WEB3ToWLimitEqTypeSwitchUnitServiceImpl
    implements WEB3ToWLimitEquitySwitchUnitService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToWLimitEquitySwitchUnitServiceImpl.class);

    /**
     * (create注文訂正内容)<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 注文訂正内容を作成する。<BR>
     * <BR>
     * １）　@パラメータ.注文単位.getDataSourceObject()をコールする。<BR>
     * <BR>
     * ２）　@注文単位.取引者ID≠nullの場合、<BR>
     * 　@　@拡張金融オブジェクトマネージャ.getTrader()をコールする。<BR>
     * <BR>
     * 　@　@　@[getTrader()の引数] <BR>
     * 　@　@　@取引者ID：　@パラメータ.注文単位.取引者ID <BR>
     * <BR>
     * ３）　@パラメータ.補助口座.getInstitution()をコールする。<BR>
     * <BR>
     * ４）　@this.create株式注文訂正値詳細()をコールする。 <BR>
     * <BR>
     * 　@　@　@[create株式注文訂正値詳細()の引数] <BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ５）　@株式注文訂正内容オブジェクトを生成する。 <BR>
     * <BR>
     * 　@　@　@[株式注文訂正内容()の引数] <BR>
     * 　@　@　@注文ID：　@パラメータ.注文単位.注文ID <BR>
     * 　@　@　@株式注文訂正値詳細：　@create株式注文訂正値詳細()の戻り値 <BR>
     * 　@　@　@証券会社コード：　@getInstitution().getInstitutionCode()の戻り値 <BR>
     * 　@　@　@注文チャネル：　@注文単位Row.初回注文の注文チャネル <BR>
     * 　@　@　@扱者：　@注文単位.取引者ID≠nullの場合、getTrader()の戻り値 <BR>
     * 　@　@　@　@　@　@　@　@上記以外、null <BR>
     * <BR>
     * ６）　@株式注文訂正内容を返却する。<BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (注文単位)
     * @@param l_subAccount - (補助口座)
     * @@return ChangeOrderSpec
     * @@throws WEB3BaseException
     */
    public ChangeOrderSpec createChangeOrderSpec(
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createChangeOrderSpec(EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null || l_subAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //（継承元クラスの同名メソッドのオーバーライド）
        //注文訂正内容を作成する。
        //１）　@パラメータ.注文単位.getDataSourceObject()をコールする。
        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //扱者
        Trader l_trader = null;
        try
        {
            //２）　@注文単位.取引者ID≠nullの場合、
            //　@　@拡張金融オブジェクトマネージャ.getTrader()をコールする。
            //　@　@　@[getTrader()の引数]
            //　@　@　@取引者ID：　@パラメータ.注文単位.取引者ID
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            if (!l_eqTypeOrderUnitRow.getTraderIdIsNull())
            {
                l_trader = l_finObjManager.getTrader(l_eqTypeOrderUnitRow.getTraderId());
            }
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

        //３）　@パラメータ.補助口座.getInstitution()をコールする。
        Institution l_institution = l_subAccount.getInstitution();

        //４）　@this.create株式注文訂正値詳細()をコールする。
        //　@　@　@[create株式注文訂正値詳細()の引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            this.createChangeOrderUnitEntry(l_eqTypeOrderUnit);

        //５）　@株式注文訂正内容オブジェクトを生成する。
        //　@　@　@[株式注文訂正内容()の引数]
        //　@　@　@注文ID：　@パラメータ.注文単位.注文ID
        //　@　@　@株式注文訂正値詳細：　@create株式注文訂正値詳細()の戻り値
        //　@　@　@証券会社コード：　@getInstitution().getInstitutionCode()の戻り値
        //　@　@　@注文チャネル：　@注文単位Row.初回注文の注文チャネル
        //　@　@　@扱者：　@注文単位.取引者ID≠nullの場合、getTrader()の戻り値
        //　@　@　@　@　@　@　@　@上記以外、null
        WEB3EquityChangeOrderSpec l_changeOrderSpec =
            new WEB3EquityChangeOrderSpec(
                l_eqTypeOrderUnitRow.getOrderId(),
                l_changeOrderUnitEntry,
                l_institution.getInstitutionCode(),
                l_eqTypeOrderUnitRow.getOrderChanel(),
                l_trader);

        //６）　@株式注文訂正内容を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_changeOrderSpec;
    }

    /**
     * (create株式注文訂正値詳細)<BR>
     * 注文訂正値詳細オブジェクトを生成し、 <BR>
     * パラメータ.注文単位の内容よりプロパティをセットする。 <BR>
     * <BR>
     * １）　@注文単位.getDataSourceObject()をコールする。  <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.is出来るまで注文単位()をコールする。<BR>
     * <BR>
     * 　@　@　@[is出来るまで注文単位()の引数] <BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ３）　@株式注文訂正値詳細オブジェクトを生成する。 <BR>
     * <BR>
     * 　@　@　@[株式注文訂正値詳細()の引数] <BR>
     * 　@　@　@訂正後執行条件：　@注文単位Row.（W指値）執行条件 <BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     * 　@　@　@訂正後is出来るまで注文：　@is出来るまで注文単位()の戻り値 <BR>
     * 　@　@　@訂正後値段条件：　@注文単位Row.値段条件 <BR>
     * 　@　@　@訂正後発注条件：　@注文単位Row.発注条件 <BR>
     * 　@　@　@訂正後発注条件演算子：　@注文単位Row.発注条件演算子  <BR>
     * 　@　@　@訂正後逆指値基準値：　@注文単位Row.逆指値基準値 <BR>
     * 　@　@　@訂正後（W指値）訂正指値：　@注文単位Row.（W指値）訂正指値 <BR>
     * 　@　@　@訂正後注文失効日：　@注文単位Row.注文失効日付 <BR>
     * 　@　@　@訂正後（W指値）執行条件：　@注文単位Row.（W指値）執行条件 <BR>
     * 　@　@　@（W指値）有効状態区分：　@"ストップ注文有効"（固定） <BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (注文単位)
     * @@return WEB3EquityChangeOrderUnitEntry
     * @@throws WEB3BaseException
     */
    protected WEB3EquityChangeOrderUnitEntry createChangeOrderUnitEntry(
        EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createChangeOrderUnitEntry(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //注文訂正値詳細オブジェクトを生成し、
        //パラメータ.注文単位の内容よりプロパティをセットする。
        //１）　@注文単位.getDataSourceObject()をコールする。
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //２）　@拡張株式注文マネージャ.is出来るまで注文単位()をコールする。
        //　@　@　@[is出来るまで注文単位()の引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnCarriedOrderUnit =
            l_orderManager.isCarriedOrderUnit(l_eqTypeOrderUnit);

        //３）　@株式注文訂正値詳細オブジェクトを生成する。
        //　@　@　@[株式注文訂正値詳細()の引数]
        //　@　@　@訂正後執行条件：　@注文単位Row.（W指値）執行条件
        //　@　@　@注文単位：　@パラメータ.注文単位
        //　@　@　@訂正後is出来るまで注文：　@is出来るまで注文単位()の戻り値
        //　@　@　@訂正後値段条件：　@注文単位Row.値段条件
        //　@　@　@訂正後発注条件：　@注文単位Row.発注条件
        //　@　@　@訂正後発注条件演算子：　@注文単位Row.発注条件演算子
        //　@　@　@訂正後逆指値基準値：　@注文単位Row.逆指値基準値
        //　@　@　@訂正後（W指値）訂正指値：　@注文単位Row.（W指値）訂正指値
        //　@　@　@訂正後注文失効日：　@注文単位Row.注文失効日付
        //　@　@　@訂正後（W指値）執行条件：　@注文単位Row.（W指値）執行条件
        //　@　@　@（W指値）有効状態区分：　@"ストップ注文有効"（固定）
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            new WEB3EquityChangeOrderUnitEntry(
                l_eqtypeOrderUnitRow.getWLimitExecCondType(),
                l_eqTypeOrderUnit,
                l_blnCarriedOrderUnit,
                l_eqtypeOrderUnitRow.getPriceConditionType(),
                l_eqtypeOrderUnitRow.getOrderConditionType(),
                l_eqtypeOrderUnitRow.getOrderCondOperator(),
                l_eqtypeOrderUnitRow.getStopOrderPrice(),
                l_eqtypeOrderUnitRow.getWLimitPrice(),
                l_eqtypeOrderUnitRow.getExpirationDate(),
                l_eqtypeOrderUnitRow.getWLimitExecCondType(),
                WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE
                );

        log.exiting(STR_METHOD_NAME);
        return l_changeOrderUnitEntry;
    }

    /**
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 切替注文発注審査を行う。 <BR>
     * <BR>
     * １）　@パラメータ.注文訂正内容を"株式注文訂正内容"型にキャストする。 <BR>
     * <BR>
     * ２）　@取引時間管理.validate注文受付可能()をコールする。 <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.validate現物株式訂正注文()をコールする。 <BR>
     * <BR>
     * 　@　@　@[validate現物株式訂正注文()の引数] <BR>
     * 　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     * 　@　@　@株式注文訂正内容：　@株式注文訂正内容 <BR>
     * 　@　@　@isSkip遅延状況チェック：　@true（*1） <BR>
     * <BR>
     * 　@　@　@（*1）isSkip遅延状況チェックについて <BR>
     * 　@　@　@　@切替処理は遅延注文の切替処理も行うため、 <BR>
     * 　@　@　@　@validate注文訂正可能状態()の遅延状況チェックを行わない。 <BR>
     * 　@　@　@　@よってtrueを設定する。 <BR>
     * <BR>
     * ４）　@２）で取得した株式発注審査結果.getProcessingResult().isFailedResult() == true の場合 <BR>
     * 　@　@　@validate現物株式訂正注文()の戻り値からエラー情報を取得し、例外をスローする。 <BR>
     * <BR>
     * ５）　@validate現物株式訂正注文()の戻り値を返却する。<BR>
     * <BR>
     * @@param l_changeOrderSpec - (注文訂正内容)
     * @@param l_subAccount - (補助口座)
     * @@return EqTypeOrderValidationResult
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
        //（継承元クラスの同名メソッドのオーバーライド）
        //切替注文発注審査を行う。
        //１）　@パラメータ.注文訂正内容を"株式注文訂正内容"型にキャストする。
        WEB3EquityChangeOrderSpec l_equityChangeOrderSpec =
            (WEB3EquityChangeOrderSpec)l_changeOrderSpec;

        //２）　@取引時間管理.validate注文受付可能()をコールする
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //３）　@拡張株式注文マネージャ.validate現物株式訂正注文()をコールする。
        //　@　@　@[validate現物株式訂正注文()の引数]
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@株式注文訂正内容：　@株式注文訂正内容
        //　@　@　@isSkip遅延状況チェック：　@true（*1）
        //　@　@　@（*1）isSkip遅延状況チェックについて
        //　@　@　@　@切替処理は遅延注文の切替処理も行うため、
        //　@　@　@　@validate注文訂正可能状態()の遅延状況チェックを行わない。
        //　@　@　@　@よってtrueを設定する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderValidationResult l_orderValidationResult =
            l_orderManager.validateChangeOrder(
                l_subAccount, l_equityChangeOrderSpec, true);

        //４）　@２）で取得した株式発注審査結果.getProcessingResult().isFailedResult() == true の場合
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("validate現物株式訂正注文Error"
                + l_orderValidationResult.getProcessingResult().getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //５）　@validate現物株式訂正注文()の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (get概算代金計算結果)<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 概算代金計算結果を取得する。<BR>
     * <BR>
     * １）　@パラメータ.注文訂正内容を"株式注文訂正内容"型にキャストする。<BR>
     * <BR>
     * ２）　@株式注文訂正内容.create株式注文内容()をコールする。<BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.calc概算受渡代金()をコールする。<BR>
     * <BR>
     * 　@　@　@[calc概算受渡代金()の引数]<BR>
     * 　@　@　@手数料：　@株式注文内容.get手数料()<BR>
     * 　@　@　@指値：　@株式注文内容.getLimitPrice() (*1)<BR>
     * 　@　@　@（W指値)訂正指値：　@株式注文内容.get（W指値）訂正指値()<BR>
     * 　@　@　@逆指値基準値：　@株式注文内容.get逆指値基準値()<BR>
     * 　@　@　@執行条件：　@株式注文内容.getExecConditionType() (*1)<BR>
     * 　@　@　@（W指値）執行条件：　@株式注文内容.get（W指値）執行条件()<BR>
     * 　@　@　@値段条件：　@株式注文内容.get値段条件()<BR>
     * 　@　@　@発注条件：　@株式注文内容.get発注条件()<BR>
     * 　@　@　@確認時取得時価：　@0（固定）<BR>
     * 　@　@　@isストップ注文有効：　@true（固定） (*2)<BR>
     * 　@　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@　@取引銘柄：　@パラメータ.注文単位.getTradedProduct()<BR>
     * 　@　@　@株数：　@株式注文内容.getQuantity()<BR>
     * 　@　@　@is売注文：　@株式注文内容.isSellOrder()<BR>
     * 　@　@　@約定数量：　@パラメータ.注文単位.約定数量<BR>
     * 　@　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額 <BR>
     * 　@　@　@isSkip金額チェック：　@false（固定） <BR>
     * <BR>
     * 　@　@(*1)各項目、訂正後（ストップ注文）の値をセットする。<BR>
     * 　@　@(*2)ストップ注文有効時の概算代金を計算する。<BR>
     * <BR>
     * ４）　@株式注文内容.set注文単価()をコールする。<BR>
     * <BR>
     * 　@　@　@[set注文単価()の引数]<BR>
     * 　@　@　@注文単価：　@calc概算受渡代金()の戻り値.get計算単価()<BR>
     * <BR>
     * ５）　@株式注文内容.set概算受渡代金()をコールする。<BR>
     * <BR>
     * 　@　@　@[set概算受渡代金()の引数]<BR>
     * 　@　@　@概算金額：　@calc概算受渡代金()の戻り値.get概算受渡代金()<BR>
     * <BR>
     * ６）　@概算受渡代金計算結果オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_changeOrderSpec - (注文訂正内容)
     * @@param l_eqTypeOrderUnit - (注文単位)
     * @@param l_subAccount - (補助口座)
     * @@return WEB3EquityEstimatedPrice
     * @@throws WEB3BaseException
     */
    public WEB3EquityEstimatedPrice getEstimatedPrice(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEstimatedPrice(ChangeOrderSpec, EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null || l_eqTypeOrderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //（継承元クラスの同名メソッドのオーバーライド）
        //概算代金計算結果を取得する。
        //１）　@パラメータ.注文訂正内容を"株式注文訂正内容"型にキャストする。
        WEB3EquityChangeOrderSpec l_equityChangeOrderSpec =
            (WEB3EquityChangeOrderSpec)l_changeOrderSpec;

        //２）　@株式注文訂正内容.create株式注文内容()をコールする。
        WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
            l_equityChangeOrderSpec.createOrderSpec();

        //３）　@拡張株式注文マネージャ.calc概算受渡代金()をコールする。
        //　@　@　@[calc概算受渡代金()の引数]
        //　@　@　@手数料：　@株式注文内容.get手数料()
        //　@　@　@指値：　@株式注文内容.getLimitPrice() (*1)
        //　@　@　@（W指値)訂正指値：　@株式注文内容.get（W指値）訂正指値()
        //　@　@　@逆指値基準値：　@株式注文内容.get逆指値基準値()
        //　@　@　@執行条件：　@株式注文内容.getExecConditionType() (*1)
        //　@　@　@（W指値）執行条件：　@株式注文内容.get（W指値）執行条件()
        //　@　@　@値段条件：　@株式注文内容.get値段条件()
        //　@　@　@発注条件：　@株式注文内容.get発注条件()
        //　@　@　@確認時取得時価：　@0（固定）
        //　@　@　@isストップ注文有効：　@true（固定） (*2)
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@取引銘柄：　@パラメータ.注文単位.getTradedProduct()
        //　@　@　@株数：　@株式注文内容.getQuantity()
        //　@　@　@is売注文：　@株式注文内容.isSellOrder()
        //　@　@　@約定数量：　@パラメータ.注文単位.約定数量
        //　@　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額
        //　@　@　@isSkip金額チェック：　@false（固定）
        //　@　@(*1)各項目、訂正後（ストップ注文）の値をセットする。
        //　@　@(*2)ストップ注文有効時の概算代金を計算する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3EquityTradedProduct l_tradedProduct = null;
        if (l_eqTypeOrderUnit.getTradedProduct() != null)
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_eqTypeOrderUnit.getTradedProduct();
        }

        WEB3EquityEstimatedDeliveryPrice l_equityEstimatedDeliveryPrice =
            l_orderManager.calcEstimateDeliveryAmount(
            l_equityNewCashBasedOrderSpec.getCommission(),
            l_equityNewCashBasedOrderSpec.getLimitPrice(),
            l_equityNewCashBasedOrderSpec.getWLimitPriceChange(),
            l_equityNewCashBasedOrderSpec.getStopLimitPriceBasePrice(),
            l_equityNewCashBasedOrderSpec.getExecConditionType(),
            l_equityNewCashBasedOrderSpec.getWlimitExecCondType(),
            l_equityNewCashBasedOrderSpec.getPriceConditionType(),
            l_equityNewCashBasedOrderSpec.getOrderCond(),
            "0",
            true,
            l_subAccount,
            l_tradedProduct,
            l_equityNewCashBasedOrderSpec.getQuantity(),
            l_equityNewCashBasedOrderSpec.isSellOrder(),
            l_eqTypeOrderUnit.getExecutedQuantity(),
            l_eqTypeOrderUnit.getExecutedAmount(),
            false);

        // ４）　@株式注文内容.set注文単価()をコールする
        // 　@　@　@[set注文単価()の引数]
        // 　@　@　@注文単価：　@calc概算受渡代金()の戻り値.get計算単価()
        l_equityNewCashBasedOrderSpec.setOrderUnitPrice(
            l_equityEstimatedDeliveryPrice.getCalcUnitPrice());

        // ５）　@株式注文内容.set概算受渡代金()をコールする。
        // 　@　@　@[set概算受渡代金()の引数]
        // 　@　@　@概算金額：　@calc概算受渡代金()の戻り値.get概算受渡代金()
        l_equityNewCashBasedOrderSpec.setEstimateDeliveryAmount(
            l_equityEstimatedDeliveryPrice.getEstimateDeliveryAmount());

        //6）　@概算受渡代金計算結果オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_equityEstimatedDeliveryPrice;
    }

    /**
     * (validate取引余力)<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 余力チェックと、余力残高更新処理を行う。<BR>
     * <BR>
     * １）　@パラメータ.注文訂正内容を"株式注文訂正内容"型にキャストする。<BR>
     * <BR>
     * ２）　@株式注文訂正内容.get株式注文内容()をコールする。<BR>
     * <BR>
     * ３）　@株式注文訂正インタセプタのインスタンスを生成する。<BR>
     * <BR>
     * 　@　@　@[株式注文訂正インタセプタ()の引数]<BR>
     * 　@　@　@注文経路区分：　@パラメータ.注文単位.注文経路区分<BR>
     * 　@　@　@代理入力者：　@株式注文訂正内容.getTrader()の戻り値<BR>
     * <BR>
     * ４）　@株式注文訂正インタセプタ.set株式注文内容()をコールする。<BR>
     * <BR>
     * 　@　@　@[set株式注文内容()の引数]<BR>
     * 　@　@　@株式注文内容：　@株式注文内容<BR>
     * <BR>
     * ５）　@取引余力サービス.validate取引余力()をコールする。<BR>
     * <BR>
     * 　@　@　@[validate取引余力()の引数]<BR>
     * 　@　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@　@注文内容インタセプタ：　@株式注文訂正インタセプタ<BR>
     * 　@　@　@注文内容：　@株式注文訂正内容<BR>
     * 　@　@　@注文種別：　@パラメータ.注文単位.注文種別<BR>
     * 　@　@　@余力更新フラグ：　@true<BR>
     * <BR>
     * ６）　@取引余力結果の内容に該当する例外オブジェクトをスローするため、<BR>
     * 　@　@拡張株式注文マネージャ.throw余力エラー詳細情報()をコールする。 <BR>
     * <BR>
     * 　@　@　@[throw余力エラー詳細情報()の引数]<BR>
     * 　@　@　@取引余力結果：　@validate取引余力()の戻り値<BR>
     * 　@　@　@注文種別：　@パラメータ.注文単位.注文種別<BR>
     * <BR>
     * @@param l_changeOrderSpec - (注文訂正内容)
     * @@param l_eqTypeOrderValidationResult - (発注審査結果)
     * @@param l_equityEstimatedPrice - (概算代金計算結果)
     * @@param l_eqTypeOrderUnit - (注文単位)
     * @@param l_subAccount - (補助口座)
     * @@throws WEB3BaseException
     */
    public void validateTradingPower(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradingPower("
            + "ChangeOrderSpec,"
            + "EqTypeOrderValidationResult,"
            + "WEB3EquityEstimatedPrice,"
            + "EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null || l_changeOrderSpec == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //（継承元クラスの同名メソッドのオーバーライド）
        //余力チェックと、余力残高更新処理を行う。
        //１）　@パラメータ.注文訂正内容を"株式注文訂正内容"型にキャストする。
        WEB3EquityChangeOrderSpec l_equityChangeOrderSpec =
            (WEB3EquityChangeOrderSpec)l_changeOrderSpec;

        //２）　@株式注文訂正内容.get株式注文内容()をコールする。
        WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
            l_equityChangeOrderSpec.getNewCachBasedOrderSpec();

        //３）　@株式注文訂正インタセプタのインスタンスを生成する。
        //　@　@　@[株式注文訂正インタセプタ()の引数]
        //　@　@　@注文経路区分：　@パラメータ.注文単位.注文経路区分
        //　@　@　@代理入力者：　@株式注文訂正内容.getTrader()の戻り値
        WEB3GentradeTrader l_trader = null;
        if (l_equityNewCashBasedOrderSpec.getTrader() != null)
        {
            l_trader = (WEB3GentradeTrader)l_equityNewCashBasedOrderSpec.getTrader();
        }
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        WEB3EquityOrderManagerChangeOrderEventInterceptor l_changeOrderEventInterceptor =
            new WEB3EquityOrderManagerChangeOrderEventInterceptor(
                l_eqtypeOrderUnitRow.getOrderRootDiv(), l_trader);

        //４）　@株式注文訂正インタセプタ.set株式注文内容()をコールする。
        //　@　@　@[set株式注文内容()の引数]
        //　@　@　@株式注文内容：　@株式注文内容
        l_changeOrderEventInterceptor.setEquityOrderSpec(l_equityNewCashBasedOrderSpec);

        //５）　@取引余力サービス.validate取引余力()をコールする。
        //　@　@　@[validate取引余力()の引数]
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@注文内容インタセプタ：　@株式注文訂正インタセプタ
        //　@　@　@注文内容：　@株式注文訂正内容
        //　@　@　@注文種別：　@パラメータ.注文単位.注文種別
        //　@　@　@余力更新フラグ：　@true
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);

        WEB3EquityOrderManagerChangeOrderEventInterceptor[] l_interceptor =
            {l_changeOrderEventInterceptor};

        WEB3EquityChangeOrderSpec[] l_orderSpec = {l_equityChangeOrderSpec};

        WEB3GentradeSubAccount l_gentradeSubAccount = null;
        if (l_subAccount != null)
        {
            l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        }
        WEB3TPTradingPowerResult l_tradingPowerResult =
            l_tradingPowerService.validateTradingPower(
            l_gentradeSubAccount,
            l_interceptor,
            l_orderSpec,
            l_eqTypeOrderUnit.getOrderType(),
            true);

        //６）　@取引余力結果の内容に該当する例外オブジェクトをスローするため、
        //　@　@拡張株式注文マネージャ.throw余力エラー詳細情報()をコールする。
        //　@　@　@[throw余力エラー詳細情報()の引数]
        //　@　@　@取引余力結果：　@validate取引余力()の戻り値
        //　@　@　@注文種別：　@パラメータ.注文単位.注文種別
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        l_orderManager.throwTpErrorInfo(
            l_tradingPowerResult,
            l_eqTypeOrderUnit.getOrderType());
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit切替)<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 注文の切替を行う。<BR>
     * <BR>
     * １）　@パラメータ.注文訂正内容を"株式注文訂正内容"型にキャストする。<BR>
     * <BR>
     * ２）　@パラメータ.補助口座.getMainAccount()をコールする。<BR>
     * <BR>
     * ３）　@W指値注文株式切替更新インタセプタを生成する。<BR>
     * <BR>
     * 　@　@　@[W指値注文株式切替更新インタセプタ()の引数]<BR>
     * 　@　@　@概算代金計算結果：　@パラメータ.概算代金計算結果<BR>
     * <BR>
     * ４）　@拡張株式注文マネージャ.setThreadLocalPersistenceEventInterceptor()をコールする。<BR>
     * <BR>
     * 　@　@　@[setThreadLocalPersistenceEventInterceptor()の引数]<BR>
     * 　@　@　@arg0：　@W指値注文株式切替更新インタセプタ<BR>
     * <BR>
     * ５）　@拡張株式注文マネージャ.submitChangeOrder()をコールする。<BR>
     * <BR>
     * 　@　@　@[submitChangeOrder()の引数]<BR>
     * 　@　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@　@株式注文訂正内容：　@株式注文訂正内容<BR>
     * 　@　@　@取引パスワード：　@getMainAccount().getTradingPassword()の戻り値をdecryptした値<BR>
     * 　@　@　@isSkip発注審査：　@true<BR>
     * <BR>
     * @@param l_changeOrderSpec - (注文訂正内容)
     * @@param l_equityEstimatedPrice - (概算代金計算結果)
     * @@param l_subAccount - (補助口座)
     * @@throws WEB3BaseException
     */
    public void submitSwitch(
        ChangeOrderSpec l_changeOrderSpec,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEstimatedPrice(ChangeOrderSpec, WEB3EquityEstimatedPrice, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null || l_subAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //（継承元クラスの同名メソッドのオーバーライド）
        //注文の切替を行う。
        //１）　@パラメータ.注文訂正内容を"株式注文訂正内容"型にキャストする。
        WEB3EquityChangeOrderSpec l_equityChangeOrderSpec =
            (WEB3EquityChangeOrderSpec)l_changeOrderSpec;

        //２）　@パラメータ.補助口座.getMainAccount()をコールする。
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //３）　@W指値注文株式切替更新インタセプタを生成する。
        //　@　@　@[W指値注文株式切替更新インタセプタ()の引数]
        //　@　@　@概算代金計算結果：　@パラメータ.概算代金計算結果
        WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor l_updateServiceInterceptor =
            new WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor(l_equityEstimatedPrice);

        //４）　@拡張株式注文マネージャ.setThreadLocalPersistenceEventInterceptor()をコールする。
        //　@　@　@[setThreadLocalPersistenceEventInterceptor()の引数]
        //　@　@　@arg0：　@W指値注文株式切替更新インタセプタ
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateServiceInterceptor);

        //５）　@拡張株式注文マネージャ.submitChangeOrder()をコールする。
        //　@　@　@[submitChangeOrder()の引数]
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@株式注文訂正内容：　@株式注文訂正内容
        //　@　@　@取引パスワード：　@getMainAccount().getTradingPassword()の戻り値をdecryptした値
        //　@　@　@isSkip発注審査：　@true
        WEB3Crypt l_webCrypt = new WEB3Crypt();
        EqTypeOrderSubmissionResult l_submitRes =
            l_orderManager.submitChangeOrder(
                l_subAccount,
                l_equityChangeOrderSpec,
                l_webCrypt.decrypt(l_mainAccount.getTradingPassword()),
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

        log.exiting(STR_METHOD_NAME);
    }
}
@
