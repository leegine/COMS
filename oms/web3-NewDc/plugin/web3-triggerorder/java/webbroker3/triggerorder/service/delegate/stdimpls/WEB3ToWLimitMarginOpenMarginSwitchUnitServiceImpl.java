head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文信用取引新規建切替一件サービスImpl(WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/20 李俊 (中訊) 新規作成 モデル No.176 No.195
Revesion History : 2006/12/11 徐宏偉 (中訊)（モデル）No.207 No.210
Revesion History : 2007/01/16 徐宏偉 (中訊)（モデル）No.220
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderValidationResult;
import webbroker3.equity.WEB3MarginChangeOpenMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginChangeOrderSpec;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginOpenMarginSwitchUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;

/**
 * (W指値注文信用取引新規建切替一件サービスImpl)
 *
 * @@author 李俊(中訊)
 * @@version 1.0
 */
public class WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl
    extends WEB3ToWLimitEqTypeSwitchUnitServiceImpl
    implements WEB3ToWLimitMarginOpenMarginSwitchUnitService
{

   /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl.class);

    /**
     * (create注文訂正内容)<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 信用新規建注文訂正内容を作成する。<BR>
     *<BR>
     *１）　@パラメータ.注文単位.getDataSourceObject()をコールする。<BR>
     *<BR>
     *２）　@注文単位.取引者ID≠nullの場合、 <BR>
     *　@　@拡張金融オブジェクトマネージャ.getTrader()をコールする。<BR>
     *<BR>
     *　@　@　@[getTrader()の引数] <BR>
     *　@　@　@取引者ID：　@パラメータ.注文単位.取引者ID <BR>
     *<BR>
     *３）　@拡張株式注文マネージャ.is出来るまで注文単位()をコールする。 <BR>
     *<BR>
     *　@　@　@[is出来るまで注文単位()の引数]<BR>
     *　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     *<BR>
     *４）　@信用新規建注文訂正内容オブジェクトを生成する。 <BR>
     *<BR>
     *　@　@　@[create新規建注文訂正内容()の引数] <BR>
     *　@　@　@注文ID：　@パラメータ.注文単位.注文ID <BR>
     *　@　@　@注文単位ID：　@パラメータ.注文単位.注文単位ID<BR>
     *　@　@　@訂正後注文株数：　@パラメータ.注文単位.注文数量 <BR>
     *　@　@　@訂正後指値：　@注文単位Row.（W指値）訂正指値 <BR>
     *　@　@　@訂正後執行条件：　@注文単位Row.（W指値）執行条件 <BR>
     *　@　@　@訂正後注文失効日：　@注文単位Row.注文失効日付 <BR>
     *　@　@　@訂正後値段条件：　@注文単位Row.値段条件 <BR>
     *　@　@　@発注条件：　@注文単位Row.発注条件<BR>
     *　@　@　@訂正後発注条件演算子：　@注文単位Row.発注条件演算子<BR>
     *　@　@　@訂正後逆指値基準値：　@注文単位Row.逆指値基準値 <BR>
     *　@　@　@訂正後（W指値）訂正指値：　@注文単位Row.（W指値）訂正指値 <BR>
     *　@　@　@訂正後（W指値）執行条件：　@注文単位Row.（W指値）執行条件 <BR>
     *　@　@　@訂正後is出来るまで注文：　@is出来るまで注文()の戻り値 <BR>
     *　@　@　@代理入力者：　@注文単位.取引者ID≠nullの場合、getTrader()の戻り値 <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@上記以外の場合、nullをセット。 <BR>
     *　@　@　@（W指値）有効状態区分：　@"ストップ注文有効"（固定）<BR>
     *<BR>
     *５）　@新規建注文訂正内容を返却する。 <BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (株式注文単位)
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

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //信用新規建注文訂正内容を作成する。
        //１）　@パラメータ.注文単位.getDataSourceObject()をコールする。
        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //２）　@注文単位.取引者ID≠nullの場合、
        //　@　@拡張金融オブジェクトマネージャ.getTrader()をコールする。
        //　@　@　@[getTrader()の引数]
        //　@　@　@取引者ID：　@パラメータ.注文単位.取引者ID
        Trader l_trader = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        try
        {
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
        //３）　@拡張株式注文マネージャ.is出来るまで注文単位()をコールする。
        //　@　@　@[is出来るまで注文単位()の引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        boolean l_blnIsCarryOrder = l_orderMgr.isCarriedOrderUnit(l_eqTypeOrderUnit);

        //４）　@信用新規建注文訂正内容オブジェクトを生成する。
        //　@　@　@[create新規建注文訂正内容()の引数]
        //　@　@　@注文ID：　@パラメータ.注文単位.注文ID
        //　@　@　@注文単位ID：　@パラメータ.注文単位.注文単位ID
        //　@　@　@訂正後注文株数：　@パラメータ.注文単位.注文数量
        //　@　@　@訂正後指値：　@注文単位Row.（W指値）訂正指値
        //　@　@　@訂正後執行条件：　@注文単位Row.（W指値）執行条件
        //　@　@　@訂正後注文失効日：　@注文単位Row.注文失効日付
        //　@　@　@訂正後値段条件：　@注文単位Row.値段条件
        //　@　@　@発注条件：　@注文単位Row.発注条件
        //　@　@　@訂正後発注条件演算子：　@注文単位Row.発注条件演算子
        //　@　@　@訂正後逆指値基準値：　@注文単位Row.逆指値基準値
        //　@　@　@訂正後（W指値）訂正指値：　@注文単位Row.（W指値）訂正指値
        //　@　@　@訂正後（W指値）執行条件：　@注文単位Row.（W指値）執行条件
        //　@　@　@訂正後is出来るまで注文：　@is出来るまで注文()の戻り値
        //　@　@　@代理入力者：　@注文単位.取引者ID≠nullの場合、getTrader()の戻り値
        //　@　@　@　@　@　@　@　@　@　@　@　@上記以外の場合、nullをセット。
        //　@　@　@（W指値）有効状態区分：　@"ストップ注文有効"（固定）

        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec =
            WEB3MarginChangeOrderSpec.createOpenMarginChangeOrderSpec(
                l_eqTypeOrderUnit.getOrderId(),
                l_eqTypeOrderUnit.getOrderUnitId(),
                l_eqTypeOrderUnit.getQuantity(),
                l_eqTypeOrderUnitRow.getWLimitPrice(),
                l_eqTypeOrderUnitRow.getWLimitExecCondType(),
                l_eqTypeOrderUnitRow.getExpirationDate(),
                l_eqTypeOrderUnitRow.getPriceConditionType(),
                l_eqTypeOrderUnitRow.getOrderConditionType(),
                l_eqTypeOrderUnitRow.getOrderCondOperator(),
                l_eqTypeOrderUnitRow.getStopOrderPrice(),
                l_eqTypeOrderUnitRow.getWLimitPrice(),
                l_eqTypeOrderUnitRow.getWLimitExecCondType(),
                l_blnIsCarryOrder,
                l_trader,
                WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE);

        log.exiting(STR_METHOD_NAME);
        //５）　@新規建注文訂正内容を返却する。
        return l_marginChangeOrderSpec;
    }

    /**
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 切替注文発注審査を行う。<BR>
     *<BR>
     *１）　@パラメータ.注文訂正内容を"新規建注文訂正内容"型にキャストする。  <BR>
     *<BR>
     *２）　@拡張株式注文マネージャ.validate現物株式訂正注文()をコールする。  <BR>
     *<BR>
     *　@　@　@[validate現物株式訂正注文()の引数]  <BR>
     *　@　@　@補助口座：　@パラメータ.補助口座  <BR>
     *　@　@　@株式注文訂正内容：　@新規建注文訂正内容 <BR>
     *　@　@　@isSkip遅延状況チェック：　@true（*1）  <BR>
     *<BR>
     *　@　@　@（*1）isSkip遅延状況チェックについて  <BR>
     *　@　@　@　@切替処理は遅延注文の切替処理も行うため、  <BR>
     *　@　@　@　@validate注文訂正可能状態()の遅延状況チェックを行わない。  <BR>
     *　@　@　@　@よってtrueを設定する。  <BR>
     *<BR>
     *３）　@２）で取得した株式発注審査結果.getProcessingResult().isFailedResult() == true の場合 <BR>
     *　@　@　@株式発注審査結果からエラー情報を取得し、例外をスローする。 <BR>
     *<BR>
     *４）　@validate現物株式訂正注文()の戻り値を返却する。<BR>
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
        //１）　@パラメータ.注文訂正内容を"新規建注文訂正内容"型にキャストする。
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec =
            (WEB3MarginChangeOrderSpec)l_changeOrderSpec;

        //２）　@拡張株式注文マネージャ.validate現物株式訂正注文()をコールする。
        //　@　@　@[validate現物株式訂正注文()の引数]
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@株式注文訂正内容：　@新規建注文訂正内容
        //　@　@　@isSkip遅延状況チェック：　@true（*1）
        //　@　@　@（*1）isSkip遅延状況チェックについて
        //　@　@　@　@切替処理は遅延注文の切替処理も行うため、
        //　@　@　@　@validate注文訂正可能状態()の遅延状況チェックを行わない。
        //　@　@　@　@よってtrueを設定する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        EqTypeOrderValidationResult l_validationResult =
            l_orderMgr.validateChangeOrder(
                l_subAccount,
                l_marginChangeOrderSpec,
                true);

        //３）　@２）で取得した株式発注審査結果.getProcessingResult().isFailedResult() == true の場合
        //　@　@　@株式発注審査結果からエラー情報を取得し、例外をスローする。
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //４）　@validate現物株式訂正注文()の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_validationResult;
    }

    /**
     * (get概算代金計算結果)<BR>
     *（継承元クラスの同名メソッドのオーバーライド） <BR>
     *<BR>
     *概算代金計算結果を取得する。 <BR>
     *<BR>
     *１）　@パラメータ.注文訂正内容を"信用新規建注文訂正内容"型にキャストする。 <BR>
     *<BR>
     *２）　@信用新規建注文訂正内容.get新規建注文訂正内容詳細()をコールする。 <BR>
     *<BR>
     *３）　@株式計算サービス.create手数料()をコールする。 <BR>
     *<BR>
     *　@　@　@[create手数料()の引数] <BR>
     *　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     *<BR>
     *４）　@拡張株式注文マネージャ.is売注文()をコールする。 <BR>
     *<BR>
     *　@　@　@[is売注文()の引数] <BR>
     *　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     *<BR>
     *５）　@拡張株式注文マネージャ.calc注文時建代金()をコールする。 <BR>
     *<BR>
     *　@　@　@[calc注文時建代金()の引数] <BR>
     *　@　@　@手数料：　@create手数料() <BR>
     *　@　@　@指値：　@新規建注文訂正内容詳細.getAfterChangePrice() (*1) <BR>
     *　@　@　@（W指値）訂正指値：　@信用新規建注文訂正内容.get訂正後（W指値）訂正指値() <BR>
     *　@　@　@逆指値基準値：　@信用新規建注文訂正内容.get訂正後逆指値基準値() <BR>
     *　@　@　@執行条件：　@信用新規建注文訂正内容.get訂正後執行条件() (*1) <BR>
     *　@　@　@（W指値）執行条件：　@信用新規建注文訂正内容.get訂正後（W指値）執行条件() <BR>
     *　@　@　@値段条件：　@信用新規建注文訂正内容.get訂正後値段条件() <BR>
     *　@　@　@発注条件：　@信用新規建注文訂正内容.get訂正後発注条件() <BR>
     *　@　@　@確認時取得時価：　@0（固定） <BR>
     *　@　@　@isストップ注文有効：　@true（固定） (*2) <BR>
     *　@　@　@is売建：　@is売注文()の戻り値 <BR>
     *　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     *　@　@　@取引銘柄：　@パラメータ.注文単位.getTradedProduct() <BR>
     *　@　@　@株数：　@信用新規建注文内容.get訂正後注文株数() <BR>
     *　@　@　@約定数量：　@パラメータ.注文単位.約定数量 <BR>
     *　@　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額  <BR>
     *　@　@　@isSkip金額チェック：　@false（固定） <BR>
     *<BR>
     *　@　@(*1)各項目、訂正後（ストップ注文）の値をセットする。 <BR>
     *　@　@(*2)ストップ注文有効時の概算代金を計算する。 <BR>
     *<BR>
     *６）　@信用新規建注文訂正内容.set訂正後計算単価()をコールする。<BR>
     *<BR>
     *　@　@　@[set訂正後計算単価()の引数]<BR>
     *　@　@　@訂正後計算単価：　@calc注文時建代金()の戻り値.get計算単価()<BR>
     *<BR>
     *７）　@信用新規建注文訂正内容.set訂正後建代金()をコールする。<BR>
     *<BR>
     *　@　@　@[set訂正後建代金()の引数]<BR>
     *　@　@　@建代金：　@calc注文時建代金()の戻り値.get概算建代金()<BR>
     *８）　@概算建代金計算結果オブジェクトを返却する。<BR>
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
        
        if (l_eqTypeOrderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@パラメータ.注文訂正内容を"信用新規建注文訂正内容"型にキャストする。
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec =
            (WEB3MarginChangeOrderSpec)l_changeOrderSpec;

        //２）　@信用新規建注文訂正内容.get新規建注文訂正内容詳細()をコールする。
        EqTypeChangeOrderUnitEntry l_changeOrderUnitEntry =
            l_marginChangeOrderSpec.getChangeOrderUnitEntry();

        //３）　@株式計算サービス.create手数料()をコールする。
        //
        //　@　@　@[create手数料()の引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_eqBizLogicProvider =
           (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();

        WEB3GentradeCommission l_commission =
            l_eqBizLogicProvider.createCommission((EqTypeOrderUnitImpl)l_eqTypeOrderUnit);

        //４）　@拡張株式注文マネージャ.is売注文()をコールする。
        //　@　@　@[is売注文()の引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        boolean l_blnIsSellOrder = l_orderMgr.isSellOrder(l_eqTypeOrderUnit);

        //５）　@拡張株式注文マネージャ.calc注文時建代金()をコールする。
        //
        //　@　@　@[calc注文時建代金()の引数]
        //　@　@　@手数料：　@create手数料()
        //　@　@　@指値：　@新規建注文訂正内容詳細.getAfterChangePrice() (*1)
        //　@　@　@（W指値）訂正指値：　@信用新規建注文訂正内容.get訂正後（W指値）訂正指値()
        //　@　@　@逆指値基準値：　@信用新規建注文訂正内容.get訂正後逆指値基準値()
        //　@　@　@執行条件：　@信用新規建注文訂正内容.get訂正後執行条件() (*1)
        //　@　@　@（W指値）執行条件：　@信用新規建注文訂正内容.get訂正後（W指値）執行条件()
        //　@　@　@値段条件：　@信用新規建注文訂正内容.get訂正後値段条件()
        //　@　@　@発注条件：　@信用新規建注文訂正内容.get訂正後発注条件()
        //　@　@　@確認時取得時価：　@0（固定）
        //　@　@　@isストップ注文有効：　@true（固定） (*2)
        //　@　@　@is売建：　@is売注文()の戻り値
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@取引銘柄：　@パラメータ.注文単位.getTradedProduct()
        //　@　@　@株数：　@信用新規建注文内容.get訂正後注文株数()
        //　@　@　@約定数量：　@パラメータ.注文単位.約定数量
        //　@　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額
        //　@　@　@isSkip金額チェック：　@false（固定）
        //
        //　@　@(*1)各項目、訂正後（ストップ注文）の値をセットする。
        //　@　@(*2)ストップ注文有効時の概算代金を計算する。

        EqTypeTradedProduct l_eqTypeTradedProduct = null;
        if (l_eqTypeOrderUnit.getTradedProduct() != null)
        {
            l_eqTypeTradedProduct = (EqTypeTradedProduct)l_eqTypeOrderUnit.getTradedProduct();
        }
        WEB3EquityEstimatedContractPrice l_estimatedContractPrice =
            l_orderMgr.calcContractAmountAtOrder(
                l_commission,
                l_changeOrderUnitEntry.getAfterChangePrice(),
                l_marginChangeOrderSpec.getModifiedWLimitPrice(),
                l_marginChangeOrderSpec.getModifiedStopOrderPrice(),
                l_marginChangeOrderSpec.getModifiedExecutionType(),
                l_marginChangeOrderSpec.getModifiedWlimitExecCondType(),
                l_marginChangeOrderSpec.getModifiedPriceConditionType(),
                l_marginChangeOrderSpec.getModifiedOrderConditionType(),
                "0",
                true,
                l_blnIsSellOrder,
                l_subAccount,
                l_eqTypeTradedProduct,
                l_marginChangeOrderSpec.getModifiedOrderQuantity(),
                l_eqTypeOrderUnit.getExecutedQuantity(),
                l_eqTypeOrderUnit.getExecutedAmount(),
                false);

        // ６）　@信用新規建注文訂正内容.set訂正後計算単価()をコールする
        // 　@　@　@[set訂正後計算単価()の引数]
        // 　@　@　@訂正後計算単価：　@calc注文時建代金()の戻り値.get計算単価()
        l_marginChangeOrderSpec.setModifiedCalcUnitPrice(
            l_estimatedContractPrice.getCalcUnitPrice());

        // ７）　@信用新規建注文訂正内容.set訂正後建代金()をコールする。
        // 　@　@　@[set訂正後建代金()の引数]
        // 　@　@　@建代金：　@calc注文時建代金()の戻り値.get概算建代金()
        l_marginChangeOrderSpec.setModifiedContractAmount(
            l_estimatedContractPrice.getEstimatedContractPrice());
        
        log.exiting(STR_METHOD_NAME);
        //8）　@概算建代金計算結果オブジェクトを返却する。
        return l_estimatedContractPrice;


    }

    /**
     * (validate取引余力)<BR>
     *余力チェックと、余力残高更新処理を行う。  <BR>
     *<BR>
     *１）　@パラメータ.注文訂正内容を"信用新規建注文訂正内容"型にキャストする。 <BR>
     *<BR>
     *２）　@信用新規建訂正更新インタセプタのインスタンスを生成する。 <BR>
     *<BR>
     *　@　@　@[信用新規建訂正更新インタセプタ()の引数] <BR>
     *　@　@　@信用新規建注文訂正内容：　@信用新規建注文訂正内容 <BR>
     *　@　@　@空売り規制対象フラグ：　@パラメータ.発注審査結果.is空売り規制対象フラグ()の戻り値 <BR>
     *　@　@　@注文経路区分：　@パラメータ.注文単位.注文経路区分 <BR>
     *　@　@　@代理入力者：　@信用新規建注文訂正内容.get扱者()の戻り値 <BR>
     *<BR>
     *３）　@取引余力サービス.validate取引余力()をコールする。 <BR>
     *<BR>
     *　@　@　@[validate取引余力()の引数] <BR>
     *　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     *　@　@　@注文内容インタセプタ：　@生成した信用新規建訂正更新インタセプタ <BR>
     *　@　@　@注文内容：　@信用新規建注文訂正内容 <BR>
     *　@　@　@注文種別：　@パラメータ.注文単位.注文種別 <BR>
     *　@　@　@余力更新フラグ：　@true <BR>
     *<BR>
     *４）　@取引余力結果の内容に該当する例外クラスをスローするため、 <BR>
     *　@　@拡張株式注文マネージャ.throw余力エラー詳細情報()をコールする。 <BR>
     *<BR>
     *　@　@　@[throw余力エラー詳細情報()の引数] <BR>
     *　@　@　@取引余力結果：　@validate取引余力()の戻り値 <BR>
     *　@　@　@注文種別：　@パラメータ.注文単位.注文種別 <BR>
     * @@param l_changeOrderSpec - (注文訂正内容)
     * @@param l_eqTypeOrderValidationResult - (発注審査結果)
     * @@param l_equityEstimatedPrice - (概算代金計算結果)
     * @@param l_eqTypeOrderUnit - (注文単位)
     * @@param l_subAccount - (補助口座)
     */
    public void validateTradingPower(ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradingPower("
            + "EqTypeOrderValidationResult,"
            + "WEB3EquityEstimatedPrice,"
            + "EqTypeOrderUnit,"
            + "SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null || l_changeOrderSpec == null
            || l_eqTypeOrderValidationResult ==null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@パラメータ.注文訂正内容を"信用新規建注文訂正内容"型にキャストする。
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec =
            (WEB3MarginChangeOrderSpec)l_changeOrderSpec;
        //２）　@信用新規建訂正更新インタセプタのインスタンスを生成する。
        //
        //　@　@　@[信用新規建訂正更新インタセプタ()の引数]
        //　@　@　@信用新規建注文訂正内容：　@信用新規建注文訂正内容
        //　@　@　@空売り規制対象フラグ：　@パラメータ.発注審査結果.is空売り規制対象フラグ()の戻り値
        //　@　@　@注文経路区分：　@パラメータ.注文単位.注文経路区分
        //　@　@　@代理入力者：　@信用新規建注文訂正内容.get扱者()の戻り値
        boolean l_blnIsShortSellRegulationTarget =
            ((WEB3EquityOrderValidationResult)l_eqTypeOrderValidationResult).getShortSellingRestraint();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderRootDiv = l_orderUnitRow.getOrderRootDiv();

        WEB3MarginChangeOpenMarginUpdateInterceptor l_intercepter =
            new WEB3MarginChangeOpenMarginUpdateInterceptor(
                l_marginChangeOrderSpec,
                l_blnIsShortSellRegulationTarget,
                l_strOrderRootDiv,
                (WEB3GentradeTrader)l_marginChangeOrderSpec.getTrader());

        //３）　@取引余力サービス.validate取引余力()をコールする。
        //
        //　@　@　@[validate取引余力()の引数]
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@注文内容インタセプタ：　@生成した信用新規建訂正更新インタセプタ
        //　@　@　@注文内容：　@信用新規建注文訂正内容
        //　@　@　@注文種別：　@パラメータ.注文単位.注文種別
        //　@　@　@余力更新フラグ：　@true
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);

        WEB3MarginChangeOpenMarginUpdateInterceptor[] l_interceptors =
            {l_intercepter};

        WEB3MarginChangeOrderSpec[] l_orderSpecs = {l_marginChangeOrderSpec};

        WEB3GentradeSubAccount l_gentradeSubAccount = null;
        if (l_subAccount != null)
        {
            l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        }
        WEB3TPTradingPowerResult l_tradingPowerResult =
            l_tradingPowerService.validateTradingPower(
            l_gentradeSubAccount,
            l_interceptors,
            l_orderSpecs,
            l_eqTypeOrderUnit.getOrderType(),
            true);

        //４）　@取引余力結果の内容に該当する例外クラスをスローするため、
        //　@　@拡張株式注文マネージャ.throw余力エラー詳細情報()をコールする。
        //
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
     *<BR>
     *注文の切替を行う。 <BR>
     *<BR>
     *１）　@パラメータ.注文訂正内容を"信用新規建注文訂正内容"型にキャストする。 <BR>
     *<BR>
     *２）　@パラメータ.補助口座.getMainAccount()をコールする。 <BR>
     *<BR>
     *３）　@W指値注文株式切替更新インタセプタ()をコールする。 <BR>
     *<BR>
     *　@　@　@[W指値注文株式切替更新インタセプタ()の引数] <BR>
     *　@　@　@概算代金計算結果：　@パラメータ.概算代金計算結果 <BR>
     *<BR>
     *４）　@拡張株式注文マネージャ.setThreadLocalPersistenceEventInterceptor()をコールする。 <BR>
     *<BR>
     *　@　@　@[setThreadLocalPersistenceEventInterceptor()の引数] <BR>
     *　@　@　@arg0：　@W指値注文株式切替更新インタセプタ <BR>
     *<BR>
     *５）　@拡張株式注文マネージャ.submitChangeOrder()をコールする。 <BR>
     *<BR>
     *　@　@　@[submitChangeOrder()の引数] <BR>
     *　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     *　@　@　@株式注文訂正内容：　@信用新規建注文訂正内容 <BR>
     *　@　@　@取引パスワード：　@getMainAccount().getTradingPassword()の戻り値をdecryptした値  <BR>
     *　@　@　@isSkip発注審査：　@true<BR>
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
        final String STR_METHOD_NAME = "submitSwitch(ChangeOrderSpec, " +
            "WEB3EquityEstimatedPrice, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）　@パラメータ.注文訂正内容を"信用新規建注文訂正内容"型にキャストする。
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec =
            (WEB3MarginChangeOrderSpec)l_changeOrderSpec;

        //２）　@パラメータ.補助口座.getMainAccount()をコールする。
        MainAccount l_mainAccount  = l_subAccount.getMainAccount();

        //３）　@W指値注文株式切替更新インタセプタ()をコールする。
        //
        //　@　@　@[W指値注文株式切替更新インタセプタ()の引数]
        //　@　@　@概算代金計算結果：　@パラメータ.概算代金計算結果
        WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor l_interceptor =
            new WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor(l_equityEstimatedPrice);

        //４）　@拡張株式注文マネージャ.setThreadLocalPersistenceEventInterceptor()をコールする。
        //
        //　@　@　@[setThreadLocalPersistenceEventInterceptor()の引数]
        //　@　@　@arg0：　@W指値注文株式切替更新インタセプタ
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //５）　@拡張株式注文マネージャ.submitChangeOrder()をコールする。
        //
        //　@　@　@[submitChangeOrder()の引数]
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@株式注文訂正内容：　@信用新規建注文訂正内容
        //　@　@　@取引パスワード：　@getMainAccount().getTradingPassword()の戻り値をdecryptした値
        //　@　@　@isSkip発注審査：　@true

        WEB3Crypt l_crypt = new WEB3Crypt();
        OrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitChangeOrder(
	            l_subAccount,
	            l_marginChangeOrderSpec,
	            l_crypt.decrypt(l_mainAccount.getTradingPassword()),
	            true);

        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
