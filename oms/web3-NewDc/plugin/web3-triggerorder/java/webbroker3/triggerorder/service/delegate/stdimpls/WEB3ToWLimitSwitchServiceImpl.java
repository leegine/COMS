head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitSwitchServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文切替サービスImpl(WEB3ToWLimitSwitchServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 唐性峰(中訊) 新規作成
                   2006/11/20 徐宏偉 (中訊) モデル No.176, 192
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEqTypeSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquitySwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitIfoSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginCloseMarginSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginOpenMarginSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitSwitchService;
import webbroker3.util.WEB3LogUtility;

/**
 * (W指値注文切替サービスImpl)<BR>
 * W指値注文切替サービス実装クラス<BR>
 *
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3ToWLimitSwitchServiceImpl implements WEB3ToWLimitSwitchService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitSwitchServiceImpl.class);

    /**
     * @@roseuid 44E9077B0271
     */
    public WEB3ToWLimitSwitchServiceImpl()
    {

    }

    /**
     * (executeW指値注文切替)<BR>
     * W指値注文切替処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（W指値注文切替サービス）executeW指値注文切替」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * （切替対象の注文の注文ID）<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923CF803D2
     */
    public void executeWLimitSwitch(
        SubAccount l_subAccount,
        long l_lngOrderId,
        ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "executeWLimitSwitch(SubAccount l_subAccount, long l_lngOrderId, ProductTypeEnum l_productType)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_productType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1 （分岐フロー：　@引数の銘柄タイプ=="株式"の場合）
        //1.1.1 execute株式W指値注文切替
        //[引数]
        // 補助口座：　@パラメータ.補助口座
        // 注文ID：　@パラメータ.注文ID 
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            this.executeEquityWLimitSwitch(l_subAccount, l_lngOrderId);
        }

        //1.2 （分岐フロー：　@引数の銘柄タイプ=="先物オプション"の場合）
        //1.2.1  execute先物OPW指値注文切替
        //[引数]
        // 補助口座：　@パラメータ.補助口座
        // 注文ID：　@パラメータ.注文ID
        else if (ProductTypeEnum.IFO.equals(l_productType))
        {
            this.executeIfoWLimitSwitch(l_subAccount, l_lngOrderId);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (execute先物OPW指値注文切替)<BR>
     * 先物OPW指値注文切替処理を行う。<BR>
     * <BR>
     * １）　@注文単位オブジェクトを取得する。<BR>
     * 　@OP注文マネージャ.getOrderUnits()をコールする。<BR>
     * <BR>
     * 　@[getOrderUnits()に指定する引数] <BR>
     *　@ arg0：　@パラメータ.注文ID <BR>
     * <BR>
     * 　@注文単位が取得できなかった場合、<BR>
     * 　@log.error()にて、注文単位が取得できなかった<BR>
     * 　@メッセージと、口座ID、補助口座ID、注文ID、銘柄タイプを<BR>
     * 　@出力し、「該当データなし」の例外をスローする。<BR>
     * 　@class: WEB3SystemLayerException<BR>
     * 　@tag:   SYSTEM_ERROR_80005<BR>
     * <BR>
     * ２）　@W指値注文先物OP切替一件サービスImplを取得する。<BR>
     * <BR>
     * ３）　@取得した注文単位の注文カテゴリにより、<BR>
     * 　@一件サービスのメソッドを呼び分ける。<BR>
     * <BR>
     * 　@パラメータ.注文単位.注文カテゴリが、<BR>
     * 　@["OP新規建注文"の場合]<BR>
     * 　@　@２）にて取得したサービス.submitOP新規建W指値注文()を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@["OP返済注文"の場合]<BR>
     * 　@　@２）にて取得したサービス.submitOP返済W指値注文()を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@["先物新規建注文"の場合]<BR>
     * 　@　@２）にて取得したサービス.submit先物新規建W指値注文()を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@["先物返済注文"の場合]<BR>
     * 　@　@２）にて取得したサービス.submit先物返済W指値注文()を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@※上記submitメソッドの引数は、全て１）のメソッドの戻り値の<BR>
     * 　@0番目の要素をセットすること。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_orderId - (注文ID)<BR>
     * 注文ID。<BR>
     * （切替対象の注文の注文ID）<BR>
     * @@throws WEB3BaseException
     */
    protected void executeIfoWLimitSwitch(
        SubAccount l_subAccount,
        long l_orderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "executeIfoWLimitSwitch(SubAccount, long)";
        log.entering(STR_METHOD_NAME);

        //１）　@注文単位オブジェクトを取得する。
        //　@OP注文マネージャ.getOrderUnits()をコールする。
        //
        //　@[getOrderUnits()に指定する引数]
        //　@　@arg0：　@パラメータ.注文ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderId);

        //　@注文単位が取得できなかった場合、
        //　@log.error()にて、注文単位が取得できなかった
        //　@メッセージと、口座ID、補助口座ID、注文ID、銘柄タイプを
        //　@出力し、「該当データなし」の例外をスローする。
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.error("error in WEB3OptionOrderManagerImpl.getOrderUnits()"
                + " 口座ID:[" + l_subAccount.getAccountId()
                + "] 補助口座ID:[" + l_subAccount.getSubAccountId()
                + "] 注文ID:[" + l_orderId
                + "] 銘柄タイプ:[" + ProductTypeEnum.IFO + "]");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当データなし");
        }

        //２）　@W指値注文先物OP切替一件サービスImplを取得する。
        WEB3ToWLimitIfoSwitchUnitService l_toWLimitIfoSwitchUnitService =
            (WEB3ToWLimitIfoSwitchUnitService)Services.getService(WEB3ToWLimitIfoSwitchUnitService.class);

        //３）　@取得した注文単位の注文カテゴリにより、
        //　@一件サービスのメソッドを呼び分ける。
        //　@引数は、全て１）のメソッドの戻り値の 0番目の要素をセットすること。
        //　@パラメータ.注文単位.注文カテゴリが、
        //　@["OP新規建注文"の場合]
        //　@　@２）にて取得したサービス.submitOP新規建W指値注文()を
        //　@　@コールする。
        OrderCategEnum l_orderCategEnum = l_orderUnits[0].getOrderCateg();
        if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderCategEnum))
        {
            l_toWLimitIfoSwitchUnitService.submitOptionOpenContractWLimitOrder((IfoOrderUnit)l_orderUnits[0]);
        }

        //　@["OP返済注文"の場合]
        //　@　@２）にて取得したサービス.submitOP返済W指値注文()を
        //　@　@コールする。
        else if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderCategEnum))
        {
            l_toWLimitIfoSwitchUnitService.submitOptionSettleContractWLimitOrder((IfoOrderUnit)l_orderUnits[0]);
        }

        //　@["先物新規建注文"の場合]
        //　@　@２）にて取得したサービス.submit先物新規建W指値注文()を
        //　@　@コールする。
        else if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderCategEnum))
        {
            l_toWLimitIfoSwitchUnitService.submitFuturesOpenContractWLimitOrder((IfoOrderUnit)l_orderUnits[0]);
        }

        //　@["先物返済注文"の場合]
        //　@　@２）にて取得したサービス.submit先物返済W指値注文()を
        //　@　@コールする。
        else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderCategEnum))
        {
            l_toWLimitIfoSwitchUnitService.submitFuturesSettleContractWLimitOrder((IfoOrderUnit)l_orderUnits[0]);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (execute株式W指値注文切替)<BR>
     * 株式W指値注文切替処理を行う。<BR> 
     * <BR>
     * １）　@this.get株式注文データ()をコールする。 <BR>
     * <BR>
     * 　@　@　@[get株式注文データ()の引数] <BR>
     * 　@　@　@注文ID：　@パラメータ.注文ID <BR>
     * 　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     * <BR>
     * ２）　@this.get株式切替一件サービス()をコールする。 <BR>
     * <BR>
     * 　@　@　@[get株式切替一件サービス()の引数] <BR>
     * 　@　@　@注文単位：　@get株式注文データ()の戻り値 <BR>
     * <BR>
     * ３）　@get株式切替一件サービス()の戻り値.submit()をコールする。 <BR>
     * <BR>
     * 　@　@　@[submit()の引数] <BR>
     * 　@　@　@注文単位：　@get株式注文データ()の戻り値<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_orderId - (注文ID)<BR>
     * 注文ID。<BR>
     * （切替対象の注文の注文ID）<BR>
     * @@throws WEB3BaseException
     */
    protected void executeEquityWLimitSwitch(
        SubAccount l_subAccount,
        long l_orderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "executeEquityWLimitSwitch(SubAccount, long)";
        log.entering(STR_METHOD_NAME);

        //１）　@this.get株式注文データ()をコールする。
        //　@　@　@[get株式注文データ()の引数]
        //　@　@　@注文ID：　@パラメータ.注文ID
        //　@　@　@補助口座：　@パラメータ.補助口座
        EqTypeOrderUnit l_eqTypeOrderUnit =
            this.getEqTypeOrderData(l_orderId, l_subAccount);

        //２）　@this.get株式切替一件サービス()をコールする。
        //　@　@　@[get株式切替一件サービス()の引数]
        //　@　@　@注文単位：　@get株式注文データ()の戻り値
        WEB3ToWLimitEqTypeSwitchUnitService l_eqTypeSwitchUnitService =
            this.getEqTypeSwitchUnitService(l_eqTypeOrderUnit);

        //３）　@get株式切替一件サービス()の戻り値.submit()をコールする。
        //　@　@　@[submit()の引数]
        //　@　@　@注文単位：　@get株式注文データ()の戻り値
        l_eqTypeSwitchUnitService.submit(l_eqTypeOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get株式切替一件サービス)<BR>
     * パラメータ.注文単位に該当する切替一件サービスを返す。<BR>
     * <BR>
     * パラメータ.注文単位.注文カテゴリが、<BR>
     * 　@［ "現物注文"の場合 ］<BR>
     * 　@　@　@　@W指値注文現物株式切替一件サービスを返す。<BR>
     * <BR>
     * 　@［ "新規建注文"の場合 ］<BR>
     * 　@　@　@　@W指値注文信用取引新規建切替一件サービスを返す。<BR>
     * <BR>
     * 　@［ "返済注文"の場合 ］<BR>
     * 　@　@　@　@W指値注文信用取引返済切替一件サービスを返す。<BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (注文単位)
     * @@return WEB3ToWLimitEqTypeSwitchUnitService
     * @@throws WEB3BaseException
     */
    public WEB3ToWLimitEqTypeSwitchUnitService getEqTypeSwitchUnitService(
        EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEqTypeSwitchUnitService(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        OrderCategEnum l_orderCategEnum = l_eqTypeOrderUnit.getOrderCateg();
        //パラメータ.注文単位に該当する切替一件サービスを返す。
        //
        //パラメータ.注文単位.注文カテゴリが、
        //　@［ "現物注文"の場合 ］
        //　@　@　@　@W指値注文現物株式切替一件サービスを返す。
        if (OrderCategEnum.ASSET.equals(l_orderCategEnum))
        {
            WEB3ToWLimitEquitySwitchUnitService l_equitySwitchUnitService =
                (WEB3ToWLimitEquitySwitchUnitService)Services.getService(
                    WEB3ToWLimitEquitySwitchUnitService.class);
            log.exiting(STR_METHOD_NAME);
            return l_equitySwitchUnitService;
        }
        //　@［ "新規建注文"の場合 ］
        //　@　@　@　@W指値注文信用取引新規建切替一件サービスを返す。
        else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCategEnum))
        {
            WEB3ToWLimitMarginOpenMarginSwitchUnitService l_marginOpenMarginSwitchUnitService =
                (WEB3ToWLimitMarginOpenMarginSwitchUnitService)Services.getService(
                    WEB3ToWLimitMarginOpenMarginSwitchUnitService.class);
            log.exiting(STR_METHOD_NAME);
            return l_marginOpenMarginSwitchUnitService;
        }
        //　@［ "返済注文"の場合 ］
        //　@　@　@　@W指値注文信用取引返済切替一件サービスを返す。
        else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCategEnum))
        {
            WEB3ToWLimitMarginCloseMarginSwitchUnitService l_marginCloseMarginSwitchUnitService =
                (WEB3ToWLimitMarginCloseMarginSwitchUnitService)Services.getService(
                    WEB3ToWLimitMarginCloseMarginSwitchUnitService.class);
            log.exiting(STR_METHOD_NAME);
            return l_marginCloseMarginSwitchUnitService;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get株式注文データ)<BR>
     * パラメータ.注文IDに該当する注文単位を取得する。<BR>
     * <BR>
     * １）　@拡張株式注文マネージャ.getOrderUnits()をコールする。<BR>
     * <BR>
     * 　@　@　@[getOrderUnits()に指定する引数]<BR>
     * 　@　@　@arg0：　@パラメータ.注文ID  <BR>
     * <BR>
     * ２）　@注文単位が取得できなかった場合、<BR>
     * 　@　@　@　@　@log.error()にて、注文単位が取得できなかったメッセージと、<BR>
     * 　@　@　@　@　@口座ID、補助口座ID、注文ID、銘柄タイプを出力し、 <BR>
     * 　@　@　@　@　@「該当データなし」の業務エラーをスローする。  <BR>
     * <BR>
     * 　@　@以外の場合、<BR>
     * 　@　@　@　@１）で取得した注文単位の0番目の要素を返す。<BR>
     * <BR>
     * @@param l_eqTypeOrderId - (注文ID)
     * @@param l_subAccount - (補助口座)
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     */
    public EqTypeOrderUnit getEqTypeOrderData(
        long l_eqTypeOrderId,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEqTypeOrderData(long, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //パラメータ.注文IDに該当する注文単位を取得する。
        //１）　@拡張株式注文マネージャ.getOrderUnits()をコールする。
        //　@　@　@[getOrderUnits()に指定する引数]
        //　@　@　@arg0：　@パラメータ.注文ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_eqTypeOrderId);

        //２）　@注文単位が取得できなかった場合
        //　@　@　@　@　@log.error()にて、注文単位が取得できなかったメッセージと
        //　@　@　@　@　@口座ID、補助口座ID、注文ID、銘柄タイプを出力し
        //　@　@　@　@　@「該当データなし」の業務エラーをスローする。
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.error("error in WEB3EquityOrderManager.getOrderUnits()"
                + " 口座ID:[" + l_subAccount.getAccountId()
                + "] 補助口座ID:[" + l_subAccount.getSubAccountId()
                + "] 注文ID:[" + l_eqTypeOrderId
                + "] 銘柄タイプ:[" + ProductTypeEnum.EQUITY + "]");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当データなし");
        }

        //　@　@以外の場合、
        //　@　@　@　@１）で取得した注文単位の0番目の要素を返す。
        EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeOrderUnit;
    }
}
@
