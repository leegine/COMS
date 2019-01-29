head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSOrderManagerReusableValidations.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS発注審査個別チェック（WEB3EquityPTSOrderManagerReusableValidations.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/19 崔遠鵬 (中訊) 新規作成 モデルNo.1208、1257 計算式書No.020
Revision History : 2007/12/21 崔遠鵬(中訊) モデルNo.1261、1263、1276 計算式書No.021
Revision History : 2008/04/15 劉剣(中訊) モデルNo.1312
Revision History : 2009/09/10 孟亞南(中訊) 計算式書No.022
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeableTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MaxMinFlagDef;
import webbroker3.common.define.WEB3PriceRangeTypeDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS発注審査個別チェック)<BR>
 * PTS発注審査個別チェッククラス<BR>
 *
 * @@author 崔遠鵬
 * @@version 1.0
 */
public class WEB3EquityPTSOrderManagerReusableValidations
    extends WEB3EquityTypeOrderManagerReusableValidations
{
    /**
     * (ログユーティリティ)
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityPTSOrderManagerReusableValidations.class);

    /**
     * @@roseuid 4766165100BC
     */
    public WEB3EquityPTSOrderManagerReusableValidations()
    {

    }

    /**
     * (validatePTS注文訂正可能状態)<BR>
     * 訂正が可能な注文状態かをチェックする。 <BR>
     * <BR>
     * １）　@引数の注文オブジェクトより、注文単位オブジェクトを取得する。 <BR>
     * 　@　@　@以下、取得した注文単位オブジェクトの0番目の要素を使用する。 <BR>
     * <BR>
     * ２）　@注文有効状態（order_open_status）、<BR>
     * 　@　@　@及び注文状態（order_status）のチェックを行う。 <BR>
     * <BR>
     * 　@２－１） 注文有効状態のチェック <BR>
     * 　@　@　@・注文有効状態がOPEN以外の場合は訂正不可とし、 <BR>
     * 　@　@　@　@例外をthrowする。 <BR>
     * 　@　@　@　@class: OrderValidationException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00032<BR>
     * <BR>
     * 　@２－２） 注文状態のチェック <BR>
     * 　@　@市場開局／閉局によって以下の通りチェックを行う。 <BR>
     * 　@　@[市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合] <BR>
     * <BR>
     * 　@　@　@・注文状態が以下のいずれかに該当する場合は訂正不可とし、 <BR>
     * 　@　@　@　@例外をthrowする。 <BR>
     * 　@　@　@　@------------------------------------------------ <BR>
     * 　@　@　@　@ACCEPTED<BR>
     * 　@　@　@　@CANCEL_ACCEPTED <BR>
     * 　@　@　@　@CANCELLING <BR>
     * 　@　@　@　@MODIFY_ACCEPTED <BR>
     * 　@　@　@　@MODIFYING <BR>
     * 　@　@　@　@ORDERING <BR>
     * 　@　@　@　@------------------------------------------------ <BR>
     * 　@　@　@　@class: OrderValidationException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00032<BR>
     * <BR>
     * 　@　@　@・注文単位.市場から確認済みの数量 == NaN（＝市場受付未済）<BR>
     * 　@　@　@　@の場合は訂正不可とし、例外をthrowする。 <BR>
     * 　@　@　@　@class: OrderValidationException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00032<BR>
     * <BR>
     * 　@　@[市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合] <BR>
     * <BR>
     * 　@　@・注文状態が以下のいずれかに該当する場合は訂正不可とし、 <BR>
     * 　@　@　@例外をthrowする。 <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@CANCEL_ACCEPTED <BR>
     * 　@　@　@CANCELLING <BR>
     * 　@　@　@MODIFY_ACCEPTED <BR>
     * 　@　@　@MODIFYING <BR>
     * 　@　@　@ORDERING <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@class: OrderValidationException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00032<BR>
     * @@param l_order - (注文)<BR>
     * @@roseuid 47344A590343
     */
    public void validatePTSOrderForChangeability(Order l_order)
        throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validatePTSOrderForChangeability(Order)";
        log.entering(STR_METHOD_NAME);

        if (l_order == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }

        //１）引数の注文オブジェクトより、注文単位オブジェクトを取得する。
        //以下、取得した注文単位オブジェクトの0番目の要素を使用する。
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        OrderUnit l_orderUnit = l_orderUnits[0];

        //２）注文有効状態（order_open_status）、及び注文状態（order_status）のチェックを行う。
        //２－１）注文有効状態のチェック
        //・注文有効状態がOPEN以外の場合は訂正不可とし、例外をthrowする。
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (!OrderOpenStatusEnum.OPEN.equals(l_orderUnitRow.getOrderOpenStatus()))
        {
            log.debug("注文を受付られる状態ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
        }

        //２－２）注文状態のチェック
        //市場開局／閉局によって以下の通りチェックを行う。
        boolean l_blnIsTradeOpenTimeZone;
        try
        {
            l_blnIsTradeOpenTimeZone = WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone();
        }
        catch (WEB3SystemLayerException l_exp)
        {
            log.error(l_exp.getErrorInfo().getErrorMessage(), l_exp);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(l_exp.getErrorInfo());
        }

        int l_intStatus = l_orderUnitRow.getOrderStatus().intValue();
        if (l_blnIsTradeOpenTimeZone)
        {
            //市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
            if ((l_intStatus == OrderStatusEnum.IntValues.ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
            {
                //・注文状態が以下のいずれかに該当する場合は訂正不可とし、例外をthrowする。
                //ACCEPTED
                //CANCEL_ACCEPTED
                //CANCELLING
                //MODIFY_ACCEPTED
                //MODIFYING
                //ORDERING
                log.debug("注文を受付られる状態ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }

            if (Double.isNaN(l_orderUnit.getConfirmedQuantity()))
            {
                //・注文単位.市場から確認済みの数量 == NaN（＝市場受付未済）の場合は訂正不可とし、
                //例外をthrowする。
                log.debug("注文を受付られる状態ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }
        else
        {
            //市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合]

            if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
            {
                //・注文状態が以下のいずれかに該当する場合は訂正不可とし、例外をthrowする。
                //CANCEL_ACCEPTED
                //CANCELLING
                //MODIFY_ACCEPTED
                //MODEFYING
                //ORDERING
                log.debug("注文を受付られる状態ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePTS注文取消可能状態)<BR>
     * 取消が可能な注文状態かをチェックする。<BR>
     * <BR>
     * １）　@引数の注文オブジェクトより、注文単位オブジェクトを取得する。<BR>
     * 　@以下、取得した注文単位オブジェクトの0番目の要素を使用する。<BR>
     * <BR>
     * ２）　@注文有効状態（order_open_status）、<BR>
     * 　@　@　@及び注文状態（order_status）のチェックを行う。<BR>
     * <BR>
     * 　@２－１）　@注文有効状態のチェック<BR>
     * 　@　@・注文有効状態がCLOSEDの場合は取消不可とし、例外をthrowする。<BR>
     * 　@　@　@class: OrderValidationException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00820<BR>
     * <BR>
     * 　@２－２）　@注文状態のチェック<BR>
     * 　@　@市場開局／閉局によって以下の通りチェックを行う。<BR>
     * <BR>
     * =======================================================================<BR>
     * 　@　@[市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合]<BR>
     * 　@　@　@・注文状態が以下のいずれかに該当する場合は取消不可とし、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@class: OrderValidationException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00032<BR>
     * 　@　@　@　@------------------------------------------------<BR>
     * 　@　@　@　@ACCEPTED<BR>
     * 　@　@　@　@CANCEL_ACCEPTED<BR>
     * 　@　@　@　@CANCELLING<BR>
     * 　@　@　@　@MODIFY_ACCEPTED<BR>
     * 　@　@　@　@MODIFYING<BR>
     * 　@　@　@　@ORDERING<BR>
     * 　@　@　@　@------------------------------------------------<BR>
     * 　@　@　@・注文単位.市場から確認済みの数量 == NaN（＝市場受付未済）<BR>
     * 　@　@　@　@の場合は取消不可とし、例外をthrowする。<BR>
     * 　@　@　@　@class: OrderValidationException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00032<BR>
     * <BR>
     * =======================================================================<BR>
     * 　@　@[市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == false）の場合]<BR>
     * 　@　@　@・注文状態が以下のいずれかに該当する場合は取消不可とし、 <BR>
     * 　@　@　@　@例外をthrowする。 <BR>
     * 　@　@　@　@------------------------------------------------ <BR>
     * 　@　@　@　@CANCEL_ACCEPTED <BR>
     * 　@　@　@　@CANCELLING <BR>
     * 　@　@　@　@MODIFY_ACCEPTED <BR>
     * 　@　@　@　@MODIFYING <BR>
     * 　@　@　@　@ORDERING <BR>
     * 　@　@　@　@------------------------------------------------ <BR>
     * 　@　@　@　@class: OrderValidationException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00032<BR>
     * @@param l_order - (注文)<BR>
     * @@roseuid 47344C2B01DE
     */
    public void validatePTSOrderForCancellation(Order l_order)
        throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validatePTSOrderForCancellation(Order)";
        log.entering(STR_METHOD_NAME);

        if (l_order == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }

        //１）引数の注文オブジェクトより、注文単位オブジェクトを取得する。
        //以下、取得した注文単位オブジェクトの0番目の要素を使用する。
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnits[0].getDataSourceObject();

        //２）注文有効状態（order_open_status）、及び注文状態（order_status）のチェックを行う。
        if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus()))
        {
            //２－１）注文有効状態のチェック
            //・注文有効状態がCLOSEDの場合は取消不可とし、例外をthrowする。
            log.debug("クローズした注文は当日約定の現引現渡注文を除いて取消できません。");
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00820);
        }

        //２－２）注文状態のチェック
        //市場開局／閉局によって以下の通りチェックを行う。
        boolean l_blnIsTradeOpenTimeZone;
        try
        {
            l_blnIsTradeOpenTimeZone = WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone();
        }
        catch (WEB3SystemLayerException l_exp)
        {
            log.error(l_exp.getErrorInfo().getErrorMessage(), l_exp);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(l_exp.getErrorInfo());
        }

        int l_intStatus = l_orderUnitRow.getOrderStatus().intValue();
        if (l_blnIsTradeOpenTimeZone)
        {
            //市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合

            if ((l_intStatus == OrderStatusEnum.IntValues.ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
            {
                //・注文状態が以下のいずれかに該当する場合は取消不可とし、例外をthrowする。
                //ACCEPTED
                //CANCEL_ACCEPTED
                //CANCELLING
                //MODIFY_ACCEPTED
                //MODEFYING
                //ORDERING
                log.debug("注文を受付られる状態ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }

            if (Double.isNaN(l_orderUnits[0].getConfirmedQuantity()))
            {
                //・注文単位.市場から確認済みの数量 == NaN（＝市場受付未済）の場合は取消不可とし、
                //例外をthrowする。
                log.debug("注文を受付られる状態ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }
        else
        {
            //市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == false）の場合

            if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
            {
                //・注文状態が以下のいずれかに該当する場合は取消不可とし、
                //例外をthrowする。
                //CANCEL_ACCEPTED
                //CANCELLING
                //MODIFY_ACCEPTED
                //MODEFYING
                //ORDERING
                log.debug("注文を受付られる状態ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePTS市場別取引可能上限金額)<BR>
     * 概算金額値が、会社・部店・市場で一度に<BR>
     * 　@取引可能な上限金額を超えていないかチェックを行う。 <BR>
     * <BR>
     * １）　@（部店市場別・PTS）取扱条件オブジェクトを取得する。<BR>
     * <BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@証券会社コード = 部店.getInstitution().getInstitutionCode() <BR>
     * 　@部店コード = 部店.getBranchCode()<BR>
     * 　@市場コード = 引数.市場.getMarketCode()<BR>
     * <BR>
     * ２）　@１）で取得した（部店市場別・PTS）取扱条件.get取引可能上限金額()をコールする。<BR>
     * <BR>
     * ３）　@（ ２）で取得した取引可能上限金額値　@＜　@拘束売買代金 ）の場合<BR>
     * 　@　@　@取引可能上限値を超過していると判断し、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02972<BR>
     * @@param l_branch - (部店)<BR>
     * @@param l_market - (市場)<BR>
     * @@param l_dblRestraintTurnover - (拘束売買代金)<BR>
     * @@roseuid 47344C7101B1
     */
    public void validatePTSMarketMaxHandlingPrice(
        Branch l_branch,
        Market l_market,
        double l_dblRestraintTurnover) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSMarketMaxHandlingPrice(Branch, Market, double)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null || l_market == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）（部店市場別・PTS）取扱条件オブジェクトを取得する。
        //[コンストラクタの引数]
        //証券会社コード = 部店.getInstitution().getInstitutionCode()
        //部店コード = 部店.getBranchCode()
        //市場コード = 引数.市場.getMarketCode()
        WEB3GentradeBranchMarketPTSDealtCond l_ptsBranchMarketDealtCond =
            new WEB3GentradeBranchMarketPTSDealtCond(
                l_branch.getInstitution().getInstitutionCode(),
                l_branch.getBranchCode(),
                l_market.getMarketCode());

        //２）１）で取得した（部店市場別・PTS）取扱条件.get取引可能上限金額()をコールする。
        double l_dblMaxHandlingPrice = l_ptsBranchMarketDealtCond.getMaxHandlingPrice();

        if (l_dblMaxHandlingPrice < l_dblRestraintTurnover)
        {
            //３）（ ２）で取得した取引可能金額上限値＜拘束売買代金）の場合
            //取引可能上限値を超過していると判断し、例外をスローする。
            log.debug("指定市場の取引可能上限金額を超えています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02972,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定市場の取引可能上限金額を超えています。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate取扱可能PTS市場)<BR>
     * 会社部店の取扱可能市場かをチェックする。 <BR>
     * <BR>
     * １）　@（部店市場別・PTS）取扱条件オブジェクトを取得する。<BR>
     * <BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@証券会社コード ： 引数.部店.getInstitution().getInstitutionCode() <BR>
     * 　@部店コード ： 引数.部店.getBranchCode()<BR>
     * 　@市場コード ： 引数.取引銘柄.getMarket().getMarketCode()<BR>
     * <BR>
     * ２）　@（部店市場別・PTS）取扱条件オブジェクト.is取扱可能()をコールする。<BR>
     * <BR>
     * 　@[is取扱可能()の引数]<BR>
     * 　@銘柄タイプ　@：　@”株式”<BR>
     * <BR>
     * ３）　@取扱不可（is取扱可能 = false）の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02108<BR>
     * @@param l_branch - (部店)<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * @@roseuid 4741845B02C7
     */
    public void validateHandlingPossiblePTSMarket(
        Branch l_branch,
        WEB3EquityTradedProduct l_tradedProduct)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingPossiblePTSMarket(Branch, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null || l_tradedProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）（部店市場別・PTS）取扱条件オブジェクトを取得する。
        //[コンストラクタの引数]
        //証券会社コード：引数.部店.getInstitution().getInstitutionCode()
        //部店コード：引数.部店.getBranchCode()
        //市場コード：引数.取引銘柄.getMarket().getMarketCode()
        WEB3GentradeBranchMarketPTSDealtCond l_ptsBranchMarketDealtCond =
            new WEB3GentradeBranchMarketPTSDealtCond(
                l_branch.getInstitution().getInstitutionCode(),
                l_branch.getBranchCode(),
                l_tradedProduct.getMarket().getMarketCode());

        //２）（部店市場別・PTS）取扱条件オブジェクト.is取扱可能()をコールする。
        //[is取扱可能()の引数]
        //銘柄タイプ：”株式”
        boolean l_blnIsHandlingPossible = l_ptsBranchMarketDealtCond.isHandlingPossible(ProductTypeEnum.EQUITY);

        if (!l_blnIsHandlingPossible)
        {
            //３）取扱不可（is取扱可能 = false）の場合、例外をスローする。
            log.debug("部店の取扱可能市場ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00158,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店の取扱可能市場ではありません。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate株数（PTS）)<BR>
     * 株数のチェックを行う。 <BR>
     * <BR>
     * １）　@株数が0またはマイナス値でないかチェックを行う。<BR>
     * 　@super.validateQuantity()をコールする。<BR>
     * <BR>
     * 　@[validateQuantity()の引数]<BR>
     * 　@株数：　@引数.株数<BR>
     * <BR>
     * ２）　@株数が売買単位の整数倍であるかチェックを行う。 <BR>
     * 　@super.checkLotSize()をコールする。<BR>
     * <BR>
     * 　@[checkLotSize()の引数]<BR>
     * 　@売買単位：　@引数.取引銘柄.get売買単位()<BR>
     * 　@株数：　@引数.株数<BR>
     * <BR>
     * ３）　@注文株数が、HOST側で<BR>
     * 　@　@　@受付可能な株数上限値を超えていないかどうかのチェックを行う。 <BR>
     * 　@this.validate株数（指定可能上限値）()をコールする。<BR>
     * <BR>
     * 　@[validate株数（指定可能上限値）()の引数]<BR>
     * 　@部店：　@引数.部店<BR>
     * 　@株数：　@引数.株数<BR>
     * <BR>
     * ４）　@（部店市場別・PTS）取扱条件オブジェクトを取得する。<BR>
     * <BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@証券会社コード = 引数.部店.getInstitution().getInstitutionCode() <BR>
     * 　@部店コード = 引数.部店.getBranchCode()<BR>
     * 　@市場コード = 引数.取引銘柄.getMarket().getMarketCode()<BR>
     * <BR>
     * ５）　@市場の売買上限株数を計算する。 <BR>
     * <BR>
     * 　@（計算式） <BR>
     * 　@取引銘柄.売買単位　@×　@売買限度単位(*1)　@＝　@市場の売買上限株数<BR>
     * <BR>
     * 　@(*1) 売買限度単位： <BR>
     * 　@　@　@－取引銘柄.強制限度単位 != null の場合、<BR>
     * 　@　@　@　@　@　@　@　@取引銘柄.強制限度単位 を使用する。 <BR>
     * 　@　@　@－取引銘柄.強制限度単位 == null の場合、 <BR>
     * 　@　@　@　@　@　@　@　@４）で取得した（部店市場別・PTS）取扱条件.get売買限度単位（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@の戻り値を使用する。<BR>
     * <BR>
     * ５）　@（市場の売買上限株数　@< 株数）の場合、例外をスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00160<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * @@param l_branch - (部店)<BR>
     * @@param l_dblStockQuantity - (株数)<BR>
     * @@roseuid 47424A76006B
     */
    public void validatePTSQuantity(
        TradedProduct l_tradedProduct,
        WEB3GentradeBranch l_branch, double l_dblStockQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSQuantity(WEB3EquityTradedProduct, WEB3GentradeBranch, double)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null || l_tradedProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();

        //１）株数が0またはマイナス値でないかチェックを行う。
        //super.validateQuantity()をコールする。
        //[validateQuantity()の引数]
        //株数：引数.株数
        try
        {
            super.validateQuantity(l_dblStockQuantity);
        }
        catch (OrderValidationException l_ove)
        {
            log.debug("注文数量が0以下の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が0以下の値です。");
        }

        //２）株数が売買単位の整数倍であるかチェックを行う。
        //super.checkLotSize()をコールする。
        //[checkLotSize()の引数]
        //売買単位：　@引数.取引銘柄.get売買単位()
        //株数：　@引数.株数
        try
        {
            super.checkLotSize(
                l_tradedProductRow.getLotSize(),
                l_dblStockQuantity);
        }
        catch (OrderValidationException l_ove)
        {
            log.debug("株数が売買単位の整数倍ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_00708,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              "株数が売買単位の整数倍ではありません。");
        }

        //３）注文株数が、HOST側で受付可能な株数上限値を超えていないかどうかのチェックを行う。
        //this.validate株数（指定可能上限値）()をコールする。
        //[validate株数（指定可能上限値）()の引数]
        //部店：引数.部店
        //株数：引数.株数
        this.validateQuantity(l_branch, l_dblStockQuantity);

        //４）（部店市場別・PTS）取扱条件オブジェクトを取得する。
        //[コンストラクタの引数]
        //証券会社コード=引数.部店.getInstitution().getInstitutionCode()
        //部店コード=引数.部店.getBranchCode()
        //市場コード=引数.取引銘柄.getMarket().getMarketCode()
        WEB3GentradeBranchMarketPTSDealtCond l_ptsBranchMarketDealtCond =
            new WEB3GentradeBranchMarketPTSDealtCond(
                l_branch.getInstitution().getInstitutionCode(),
                l_branch.getBranchCode(),
                l_tradedProduct.getMarket().getMarketCode());

        //(*1) 売買限度単位：
        double l_dblLimitedUnit = 0.0D;
        if (!l_tradedProductRow.getCompulsiveLimitedUnitIsNull())
        {
            //－取引銘柄.強制限度単位 != null の場合、
            //取引銘柄.強制限度単位 を使用する。
            l_dblLimitedUnit = l_tradedProductRow.getCompulsiveLimitedUnit();
        }
        else
        {
            //－取引銘柄.強制限度単位 == nullの場合、
            //４）で取得した（部店市場別・PTS）取扱条件.get売買限度単位（）の戻り値を使用する。
            l_dblLimitedUnit = l_ptsBranchMarketDealtCond.getLimitedUnit();
        }

        //５）市場の売買上限株数を計算する。
        //（計算式）
        //取引銘柄.売買単位　@×　@売買限度単位(*1)　@＝　@市場の売買上限株数
        BigDecimal l_bdLimitedUnit = new BigDecimal(l_dblLimitedUnit + "");
        BigDecimal l_bdResultHighQuantity =
            l_bdLimitedUnit.multiply(new BigDecimal(l_tradedProductRow.getLotSize() + ""));
        double l_dblResultHighQuantity = l_bdResultHighQuantity.doubleValue();

        if (l_dblResultHighQuantity < l_dblStockQuantity)
        {
            //５）（市場の売買上限株数　@< 株数）の場合、例外をスローする。
            log.debug("株数が売買上限株数を超えています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00160,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "株数が売買上限株数を超えています。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate指値注文（PTS）)<BR>
     * 指値注文のみ指定可。<BR>
     * <BR>
     * １）　@成行（引数.株式注文内容.isLimitOrder == false）が指定されていた場合、<BR>
     * 　@　@　@「PTS市場では、成行指定不可。」の例外をスローする。　@<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02974<BR>
     * @@param l_equityNewCashBasedOrderSpec - (株式注文内容)<BR>
     * @@roseuid 474CEABA0074
     */
    public void validatePTSLimitOrder(WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSLimitOrder(WEB3EquityNewCashBasedOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_equityNewCashBasedOrderSpec == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        if (!l_equityNewCashBasedOrderSpec.isLimitOrder())
        {
            //１）成行（引数.株式注文内容.isLimitOrder == false）が指定されていた場合、
            //「PTS市場では、成行指定不可。」の例外をスローする。
            log.debug("PTS市場では、成行指定不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02974,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "PTS市場では、成行指定不可。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc基準値（PTS値幅チェック用）)<BR>
     * <BR>
     * １）　@引数.取引銘柄.get基準値(終値)()をコールする。<BR>
     * <BR>
     * ２）　@１）の戻り値を返却する。<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * @@return double
     * @@roseuid 474D465E01EE
     */
    public double calcBasePriceForPTSPriceRange(WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "calcBasePriceForPTSPriceRange(WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）引数.取引銘柄.get基準値(終値)()をコールする。
        double l_dblBasePrice = l_tradedProduct.getLastClosingPrice();

        //２）１）の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblBasePrice;
    }

    /**
     * (validate顧客銘柄別取引停止（PTS）)<BR>
     * 顧客銘柄別取引停止チェックを行う。 <BR>
     * <BR>
     * １）　@引数の補助口座オブジェクトより、顧客オブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@顧客.isPTS取引停止銘柄( )をコールする。 <BR>
     * <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@＜isPTS取引停止銘柄( )：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@銘柄ID：　@引数の銘柄ID <BR>
     * 　@　@　@注文種別：　@引数の注文種別 <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * 　@　@　@戻り値 == trueの場合は「顧客は指定銘柄の該当取引停止中」の例外をthrowする。 <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_01357<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座。<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID。 <BR>
     * 銘柄を特定しない場合は、0（全銘柄）をセット。<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別。<BR>
     * @@roseuid 474E0B6E0373
     */
    public void validatePTSAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum l_orderType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSAccountProductOrderStop(SubAccount, long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）引数の補助口座オブジェクトより、顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_account =
            new WEB3GentradeMainAccount((MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject());

        //２）顧客.isPTS取引停止銘柄( )をコールする。
        //銘柄ID：　@引数の銘柄ID
        //注文種別：　@引数の注文種別
        if (l_account.isPTSTradeStopProduct(l_lngProductId, l_orderType))
        {
            //戻り値 == trueの場合は「顧客は指定銘柄の該当取引停止中」の例外をthrowする。
            log.debug("顧客は指定銘柄の該当取引停止中。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01357,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座ID：[" + l_account.getAccountId()
                + "] 銘柄ID：[" + l_lngProductId
                + "] 注文種別：[" + l_orderType + "]");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate訂正項目（PTS）)<BR>
     * 注文訂正にて、訂正された項目が正しいかをチェックする。 <BR>
     * <BR>
     * エラー内容は以下の通り。 <BR>
     * 　@－訂正元注文の株数より訂正株数が多い場合。 <BR>
     * 　@－訂正元注文が一部出来の状態で、訂正株数が約定数以下の場合。 <BR>
     * 　@－同時訂正不可の市場で、株数と同時訂正対象項目が同時に訂正されている場合。 <BR>
     * 　@－訂正元注文から何も訂正が入っていない場合。 <BR>
     * <BR>
     * ------------------------------------------------------------------- <BR>
     * １）　@株数訂正チェック <BR>
     * 　@isChange株数（注文単位、訂正後株数）をコールし、 <BR>
     * 　@株数訂正値のチェックを行う。 <BR>
     * <BR>
     * ２）　@単価チェック <BR>
     * 　@isChange単価（注文単位、訂正後指値）をコールし、 <BR>
     * 　@単価訂正値のチェックを行う。 <BR>
     * <BR>
     * ３）　@執行条件に訂正が入っているかのチェック <BR>
     * 　@isChange執行条件（注文単位、訂正後執行条件）をコールする。 <BR>
     * <BR>
     * ４）　@株数と同時訂正対象項目(*1)を同時に訂正している場合、<BR>
     * 　@　@　@同時訂正可能な市場かどうかのチェック <BR>
     * <BR>
     * 　@(*1)同時訂正対象項目 <BR>
     * 　@　@　@・単価（指値） <BR>
     * 　@　@　@・執行条件 <BR>
     * <BR>
     * 　@　@訂正元注文単位の値を使用し、「同時訂正」であるかどうかを判定する。 <BR>
     * <BR>
     * 　@　@isChange株数( )がtrueを返却した場合のみ、 <BR>
     * 　@　@{isChange単価( )、isChange執行条件( )}のいずれか一つでもtrueを返却していれば <BR>
     * 　@　@「同時訂正」と判定する。 <BR>
     * <BR>
     * 　@　@「同時訂正」に該当する場合のみ、<BR>
     * 　@　@拡張プロダクトマネージャ.getMarket(注文単位.市場ID)にて <BR>
     * 　@　@市場オブジェクトを取得する。 <BR>
     * 　@　@市場オブジェクト.同時訂正可能区分＝”複数項目同時訂正不可”の場合 <BR>
     * 　@　@は、「複数項目同時訂正不可」の例外をスローする。 <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_00687<BR>
     * <BR>
     * ５）　@訂正が入っているかのチェック <BR>
     * 　@isChange株数( )、isChange単価( )、 <BR>
     * 　@isChange執行条件( ) のすべてがfalseを返却した場合、 <BR>
     * 　@訂正元注文から何も訂正されていないと判断し、 <BR>
     * 　@「訂正入力なし」の例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_02103<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 訂正元注文（原注文）の注文単位オブジェクト<BR>
     * @@param l_dblModifiedQuantity - (訂正後株数)<BR>
     * 訂正後株数<BR>
     * @@param l_dblModifiedLimitPrice - (訂正後指値)<BR>
     * 訂正後指値<BR>
     * @@param l_eqTypeExecutionConditionType - (訂正後執行条件)<BR>
     * 訂正後執行条件<BR>
     * @@param l_strModifiedPriceConditionType - (訂正後値段条件)<BR>
     * 訂正後値段条件<BR>
     * @@param l_strModifiedOrderConditionType - (訂正後発注条件)<BR>
     * 訂正後発注条件<BR>
     * @@param l_strModifiedOrderCondOperator - (訂正後発注条件演算子)<BR>
     * 訂正後発注条件演算子<BR>
     * @@param l_dblModifiedStopOrderPrice - (訂正後逆指値基準値)<BR>
     * 訂正後逆指値基準値<BR>
     * @@param l_dblModifiedWLimitPrice - (訂正後（W指値）訂正指値)<BR>
     * 訂正後（W指値）訂正指値<BR>
     * @@param l_modifiedWLimitExecCondType - (訂正後（W指値）執行条件)<BR>
     * 訂正後（W指値）執行条件<BR>
     * @@param l_modifiedIsCarriedOrder - (訂正後is出来るまで注文)<BR>
     * 訂正後の注文が出来るまで注文かどうかを判別するフラグ。 <BR>
     * <BR>
     * true：　@出来るまで注文 <BR>
     * false：　@当日注文 <BR>
     * @@param l_datModifiedExpirationDate - (訂正後注文失効日)<BR>
     * 訂正後注文失効日<BR>
     * @@param l_modifiedSettleContractEntries - (訂正後決済指定エントリ)<BR>
     * 訂正後決済指定エントリの配列。決済注文の場合のみセットする。<BR>
     * @@roseuid 474E0DDF02F7
     */
    public void validatePTSChangeItem(
        EqTypeOrderUnit l_orderUnit,
        double l_dblModifiedQuantity,
        double l_dblModifiedLimitPrice,
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType,
        String l_strModifiedPriceConditionType,
        String l_strModifiedOrderConditionType,
        String l_strModifiedOrderCondOperator,
        double l_dblModifiedStopOrderPrice,
        double l_dblModifiedWLimitPrice,
        EqTypeExecutionConditionType l_modifiedWLimitExecCondType,
        boolean l_modifiedIsCarriedOrder,
        Date l_datModifiedExpirationDate,
        EqTypeSettleContractOrderEntry[] l_modifiedSettleContractEntries)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSChangeItem(EqTypeOrderUnit, double, double, " +
            "EqTypeExecutionConditionType, String, String, String, double, double, " +
            "EqTypeExecutionConditionType, boolean, Date, EqTypeSettleContractOrderEntry[])";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //1)株数訂正チェック
        //isChange株数（注文単位、訂正後株数）をコールし、株数訂正値のチェックを行う。
        boolean l_blnIsChangeQuantity = this.isChangeQuantity(l_orderUnit, l_dblModifiedQuantity);

        //2)単価チェック
        //isChange単価（注文単位、訂正後指値）をコールし、単価訂正値のチェックを行う。
        boolean l_blnIsChangePrice = this.isChangePrice(l_orderUnit, l_dblModifiedLimitPrice);

        //3)執行条件に訂正が入っているかのチェック
        //isChange執行条件（注文単位、訂正後執行条件）をコールする。
        boolean l_blnIsChangeExecutionCondition =
            this.isChangeExecutionCondition(l_orderUnit, l_eqTypeExecutionConditionType);

        //4)株数と同時訂正対象項目を同時に訂正している場合、同時訂正可能な市場かどうかのチェック
        if (l_blnIsChangeQuantity && (l_blnIsChangePrice || l_blnIsChangeExecutionCondition))
        {
            //isChange株数( )がtrueを返却した場合のみ、
            //{isChange単価( )、isChange執行条件( )}のいずれか一つでもtrueを返却していれば「同時訂正」と判定する。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();

            //拡張プロダクトマネージャ.getMarket(注文単位.市場ID)にて
            //市場オブジェクトを取得する。
            try
            {
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();

                if (WEB3ChangeableTypeDef.CANNOT_CHANGE.equals(l_marketRow.getChangeableType()))
                {
                    log.debug("複数項目同時訂正不可。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00687,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "複数項目同時訂正不可。");
                }
            }
            catch (NotFoundException l_nfe)
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
        }

        //５）訂正が入っているかのチェック
        if (!l_blnIsChangeQuantity && !l_blnIsChangePrice && !l_blnIsChangeExecutionCondition)
        {
            //isChange株数()、isChange単価()、
            //isChange執行条件()のすべてがfalseを返却した場合、
            //訂正元注文から何も訂正されていないと判断し、
            //「訂正入力なし」の例外をスローする。
            log.debug("訂正入力されていません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02103,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "訂正入力されていません。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate訂正時注文Rev上限（PTS）)<BR>
     * 訂正時の注文Revが上限を超えないかどうかをチェックする。 <BR>
     * <BR>
     * １）　@引数の注文単位オブジェクトのcloneオブジェクトを生成し、 <BR>
     * 　@　@　@引数の訂正後の値をそれぞれ該当する項目にセットする。 <BR>
     * <BR>
     * 　@　@　@訂正後株数：　@　@　@　@注文数量 <BR>
     * 　@　@　@訂正後指値：　@　@　@　@指値 <BR>
     * 　@　@　@訂正後執行条件：　@執行条件 <BR>
     * 　@　@　@訂正後値段条件：　@値段条件 <BR>
     * <BR>
     * ２）　@株式発注サービス.getPTS訂正時注文Rev()をコールする。 <BR>
     * 　@　@　@引数には、１）で作成した注文単位オブジェクトをセットする。 <BR>
     * 　@　@　@※例外（上限回数オーバー等）がthrowされた場合は、 <BR>
     * 　@　@　@※その例外をそのままthrowする。 <BR>
     * @@param l_orderUnit - (訂正前注文単位)<BR>
     * 訂正前の注文単位オブジェクト。<BR>
     * @@param l_dblQuantity - (訂正後株数)<BR>
     * 訂正後株数<BR>
     * @@param l_dblLimitPrice - (訂正後指値)<BR>
     * 訂正後指値<BR>
     * @@param l_executionConditionType - (訂正後執行条件)<BR>
     * 訂正後執行条件<BR>
     * @@param l_strPriceConditionType - (訂正後値段条件)<BR>
     * 訂正後値段条件<BR>
     * @@roseuid 474E2A500296
     */
    public void validatePTSChangeOrderRevUpperLimit(
        EqTypeOrderUnit l_orderUnit,
        double l_dblQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_executionConditionType,
        String l_strPriceConditionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSChangeOrderRevUpperLimit(EqTypeOrderUnit," +
            " double, double, EqTypeExecutionConditionType, String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）引数の注文単位オブジェクトのcloneオブジェクトを生成し、
        //引数の訂正後の値をそれぞれ該当する項目にセットする。
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_orderUnitParams =
            new EqtypeOrderUnitParams(l_orderUnitRow);

        //訂正後株数：注文数量
        //訂正後指値：指値
        //訂正後執行条件：執行条件
        //訂正後値段条件：値段条件
        l_orderUnitParams.setQuantity(l_dblQuantity);
        l_orderUnitParams.setLimitPrice(l_dblLimitPrice);
        l_orderUnitParams.setExecutionConditionType(l_executionConditionType);
        l_orderUnitParams.setPriceConditionType(l_strPriceConditionType);

        //２）株式発注サービス.getPTS訂正時注文Rev()をコールする。
        //引数には、１）で作成した注文単位オブジェクトをセットする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_eqTypeOrderUnit =
            (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);

        //※例外（上限回数オーバー等）がthrowされた場合は、
        //※その例外をそのままthrowする。
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        l_frontOrderService.getPTSChangeOrderRev(l_eqTypeOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is権利落ち日（PTS）)<BR>
     * 発注日が権利落ち日であるかどうかの判定を行う。<BR>
     * <BR>
     * 発注日＝権利落ち日の場合はtrueを、<BR>
     * 上記以外の場合はfalseを返す。<BR>
     * 当メソッドの詳細は<BR>
     * 「基本設計計算式書（エクイティ）.doc」の<BR>
     * 「[●共通7]PTS取引用・権利落ち日チェック」を参照。<BR>
     * ※　@営業日計算の際には、営業日計算クラスを使用する。<BR>
     * @@param l_tsOrderBizDate - (発注日)<BR>
     * @@param l_tsYearlyBooksClosingDate - (権利確定日)<BR>
     * 権利確定日を指定する。通常は、【株式銘柄テーブル】決算日が指定される。<BR>
     * @@return boolean
     * @@roseuid 4753F6A20321
     */
    public boolean isPTSDevidendRightDate(
        Timestamp l_tsOrderBizDate,
        Timestamp l_tsYearlyBooksClosingDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSDevidendRightDate(Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        if (l_tsOrderBizDate == null || l_tsYearlyBooksClosingDate == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //権利落ち日算出処理
        WEB3GentradeBizDate l_bizDateCalcUtil =
            new WEB3GentradeBizDate(l_tsYearlyBooksClosingDate);

        //「権利確定日」の３営業日前の日付を算出
        Timestamp l_tsDevidendRightDate = l_bizDateCalcUtil.roll(-3);

        if (WEB3DateUtility.compareToDay(l_tsOrderBizDate, l_tsDevidendRightDate) == 0)
        {
            //該当銘柄の権利落ち日であると判定しtrueを返す。
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            //上記以外の場合はfalseを返す。
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (validate注文単価（PTS）)<BR>
     * 指値が適切であるかどうかのチェックを行う。<BR>
     * <BR>
     * シーケンス図『（PTS注文）validate注文単価』参照。<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@return boolean
     * @@roseuid 4753F6A23381
     */
    public boolean validatePTSPrice(
        double l_dblLimitPrice,
        WEB3EquityTradedProduct l_tradedProduct,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSPrice(double, WEB3EquityTradedProduct, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //呼値チェック（PTS）
        if (!this.isPTSTickValueDef((WEB3EquityTradedProduct)l_tradedProduct, l_dblLimitPrice))
        {
            //呼値チェック（PTS）() == falseの場合、falseを返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //呼値チェック（PTS）() == trueの場合、処理を継行する。
        //取引銘柄Row
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();

        //株式銘柄Rowを取得
        EqtypeProductRow l_productRow =
            (EqtypeProductRow)l_tradedProduct.getProduct().getDataSourceObject();

        //is権利落ち日
        Timestamp l_tsOrderBizDate =
            new Timestamp(WEB3DateUtility.getDate(
            l_tradedProductRow.getValidUntilBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());
        boolean l_blnIsPTSDevidendRightDate =
            this.isPTSDevidendRightDate(l_tsOrderBizDate, l_productRow.getYearlyBooksClosingDate());

        //Is値幅チェック対象外
        boolean l_blnIsPriceRangeCheck = false;
        if (WEB3PriceRangeTypeDef.CHECK.equals(l_tradedProductRow.getPriceRangeType()))
        {
            l_blnIsPriceRangeCheck = true;
        }

        if (!l_blnIsPTSDevidendRightDate && l_blnIsPriceRangeCheck)
        {
            log.exiting(STR_METHOD_NAME);
            return this.isPTSPriceRange((WEB3EquityTradedProduct)l_tradedProduct, l_dblLimitPrice);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (呼値チェック（PTS）)<BR>
     * 指値が刻み値の整数倍かどうか（指値が刻み値で割り切れるかどうか）をチェックする。<BR>
     * 整数倍の場合はtrueを、非整数倍の場合はfalseを返す。<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * @@param l_dblOrderPrice - (注文単価)<BR>
     * @@return boolean
     * @@roseuid 47364A530343
     */
    protected boolean isPTSTickValueDef(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblOrderPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSTickValueDef(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblOrderPrice < 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityProductManager l_productManager = null;
        l_productManager =
            (WEB3EquityProductManager)GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        BigDecimal l_bdTickValue = null;
        try
        {
            //刻み値取得
            double l_dblTickValue =
                l_productManager.getTickValue(l_tradedProduct, l_dblOrderPrice);
            l_bdTickValue = new BigDecimal(l_dblTickValue);
        }
        catch (WEB3BaseException l_ble)
        {
            log.error("指定された呼値が正しくありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00030,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (l_dblOrderPrice % l_bdTickValue.doubleValue() != 0)
        {
            // 呼値エラー
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00030,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (値幅チェック（PTS）)<BR>
     * 【値幅テーブル】から値幅を取得して値幅上限／値幅下限を算出し、<BR>
     * 指値が値幅の範囲内であるかどうかをチェックする。<BR>
     * 指値が値幅の範囲内である場合はtrueを、それ以外の場合はfalseを、<BR>
     * それぞれ返す。<BR>
     * <BR>
     * 当メソッドの詳細は<BR>
     * 「基本設計計算式書（エクイティ）.doc」の「[3]値幅チェック」を参照。<BR>
     * <BR>
     * シーケンス図「（PTS注文)validate注文単価（PTS）」参照。<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * @@param l_dblOrderPrice - (注文単価)<BR>
     * @@return boolean
     * @@roseuid 4731945B05C7
     */
    protected boolean isPTSPriceRange(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblOrderPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSPriceRange(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdBasePrice = null;
        BigDecimal l_bdResultPriceHigh = null;
        BigDecimal l_bdResultPriceLow = null;

        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        // 注文単価
        BigDecimal l_bdOrderPrice = new BigDecimal(l_dblOrderPrice);

        //(1) [●共通6]基準値算出（値幅チェック用）　@を行う
        l_bdBasePrice =
            new BigDecimal(this.calcBasePriceForPTSPriceRange(l_tradedProduct));
        log.debug("*** 「基準値」 ：  " + l_bdBasePrice.doubleValue());

        //(2) (1)で算出した基準値を使用し、値幅上限計算時に
        // 使用する値幅を[●共通2]値幅算出 により計算する。
        BigDecimal l_bdPriceRangeHigh =
            new BigDecimal(
                this.calcPriceRange(
                    l_tradedProduct,
                    l_bdBasePrice.doubleValue(),
                    WEB3MaxMinFlagDef.MAXIMUM));
        log.debug("*** 「値幅(上限計算時)」 ：  " + l_bdPriceRangeHigh.doubleValue());

        //(3) （(1)で取得した基準値＋(2)で取得した値幅）を「基準値」
        // として[●共通1]刻み値取得　@を行い、
        // 値幅上限に対する刻み値を取得する。
        BigDecimal l_bdTickValue =
            new BigDecimal(
                l_productManager.getTickValue(
                    l_tradedProduct,
                    l_bdBasePrice.add(l_bdPriceRangeHigh).doubleValue()));
        log.debug("*** 「刻み値(上限計算時)」 ：  " + l_bdTickValue.doubleValue());

        //(4) (3)で取得した刻み値を指値単位とし、(2)で取得した
        // 値幅上限計算時に使用する値幅を元に[●共通3]値幅上限算出
        // を行い、値幅上限を算出する
        l_bdResultPriceHigh =
            new BigDecimal(
                this.calcStopHighPrice(
                    l_bdBasePrice.doubleValue(),
                    l_bdPriceRangeHigh.doubleValue(),
                    l_bdTickValue.doubleValue()));
        log.debug("*** 「値幅上限」 ：  " + l_bdResultPriceHigh.doubleValue());

        //(5) (1)で算出した基準値を使用し、値幅下限計算時に使用する値幅
        // を[●共通2]値幅算出 により計算する
        BigDecimal l_bdPriceRangeLow =
            new BigDecimal(
                this.calcPriceRange(
                    l_tradedProduct,
                    l_bdBasePrice.doubleValue(),
                    WEB3MaxMinFlagDef.MINIMUM));
        log.debug("*** 「値幅(下限計算時)」 ：  " + l_bdPriceRangeLow.doubleValue());

        //(6) (4)で取得した値幅上限、及び(5)で取得した値幅下限計算時に
        // 使用する値幅を元に、値幅下限を算出する。
        //(基準値－値幅)≦0 の場合　@⇒　@値幅下限＝1とする。
        //上記以外の場合　@⇒　@値幅下限＝(基準値－値幅)　@
        // (小数点以下を切り捨て)とする。
        l_bdResultPriceLow = l_bdBasePrice.subtract(l_bdPriceRangeLow);
        if (l_bdResultPriceLow.compareTo(new BigDecimal(0)) <= 0)
        {
            l_bdResultPriceLow = new BigDecimal(1);
        }
        else
        {
            l_bdResultPriceLow = new BigDecimal(l_bdResultPriceLow.longValue());
        }
        log.debug("*** 「値幅下限」 ：  " + l_bdResultPriceLow.doubleValue());

        //値幅チェック
        if ((l_bdOrderPrice.compareTo(l_bdResultPriceHigh) > 0)
            || (l_bdOrderPrice.compareTo(l_bdResultPriceLow) < 0))
        {
            String l_strMessage = "(取引銘柄=" + l_tradedProduct.getTradedProductId()
                + ")値幅上限： " + l_bdResultPriceHigh
                + "、値幅下限：" + l_bdResultPriceLow
                + "、注文単価：" + l_bdOrderPrice;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00031,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                WEB3EquityTypeOrderManagerReusableValidations.ORDER_ERROR_TAG
                + WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR
                );
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate税区分（PTS）)<BR>
     * 税区分のチェックを行う。<BR>
     * 売り注文で、税区分がストックオプションの場合はエラーとする。<BR>
     * <BR>
     * <BR>
     * １）　@引数.is売注文 ＝ true かつ <BR>
     * 　@　@　@引数.税区分 ＝ ストックオプションの場合<BR>
     * <BR>
     * 　@　@　@例外「PTS市場でストックオプション売注文不可。」をスローする。<BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_03069 <BR>
     * @@param l_blnIsSellOrder - (is売注文)<BR>
     * @@param l_taxType - (税区分)<BR>
     * @@throws WEB3BaseException<BR>
     */
    public void validatePTSTaxType(boolean l_blnIsSellOrder, TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSTaxType(boolean, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数.is売注文 ＝ true かつ
        //引数.税区分 ＝ ストックオプションの場合
        if (l_blnIsSellOrder && TaxTypeEnum.STOCK_OPTION.equals(l_taxType))
        {
            log.debug("PTS市場でストックオプション売注文不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03069,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "PTS市場でストックオプション売注文不可。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
