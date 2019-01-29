head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossCalcRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス損益計算リクエスト(WEB3TrialCalcProfitLossCalcRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import java.math.BigDecimal;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.trialcalc.define.WEB3TrialCalcEquityMiniDivDef;
import webbroker3.trialcalc.define.WEB3TrialCalcOrderFormDef;

/**
 * （計算サービス損益計算リクエスト）<BR>
 * <BR>
 * 計算サービス損益計算サービス（損益計算実行）のリクエストデータ。<BR>
 * <BR>
 * WEB3TrialCalcProfitLossCalcRequest<BR>
 * @@author umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcProfitLossCalcRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_profitloss_calc";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501101150L;

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcProfitLossCalcRequest.class);

    /**
     *maxLength int
     */
    private final static int MAXLENGTH = 8;

    /**
     *tenAsDenominator int
     */
    private final static int TEN_AS_DENOMINATOR = 10;

    /**
     * （株数）<BR>
     * <BR>
     * orderQuantity<BR>
     */
    public String orderQuantity;

    /**
     * （買単価）<BR>
     * <BR>
     * buyPrice<BR>
     */
    public String buyPrice;

    /**
     * （売単価）<BR>
     * <BR>
     * sellPrice<BR>
     */
    public String sellPrice;

    /**
     * （注文形態）<BR>
     * <BR>
     * 注文形態。<BR>
     * （1：インターネット　@2：コールセンター）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * orderForm<BR>
     * (1 : Internet  2 : Call center)<BR>
     */
    public String orderForm;

    /**
     * （現物／ミニ株区分）<BR>
     * <BR>
     * 現物／ミニ株区分。<BR>
     * （1：現物株式　@2：ミニ株）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * equityMiniDiv<BR>
     * (1 : equity  2 : Mini stock division)<BR>
     */
    public String equityMiniDiv;

    /**
     * （売買単位）<BR>
     * <BR>
     * 売買単位。<BR>
     * （ミニ株の場合のみ指定）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * dealingUnit<BR>
     * (Specify only with "Mini stock division")<BR>
     * <BR>
     */
    public String dealingUnit;

    /**
     * @@roseuid 41C81797005E
     */
    public WEB3TrialCalcProfitLossCalcRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@株数チェック<BR>
     * <BR>
     * 　@１－１）　@this.株数＝nullの場合、「株数がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00074<BR>
     * <BR>
     * 　@１－２）　@this.株数≠null、かつ
     * 以下のいずれかに該当する場合は、以下の例外をthrowする。<BR>
     * 　@　@　@・this.株数≠数字<BR>
     * 　@　@　@　@　@　@「株数が数字以外」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00075<BR>
     * <BR>
     * 　@　@　@・this.株数≦0<BR>
     * 　@　@　@　@　@　@「株数が0以下」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00076<BR>
     * <BR>
     * 　@　@　@・this.株数＝8桁を超える数字<BR>
     * 　@　@　@　@　@　@「株数の桁数が8桁を超過」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00077<BR>
     * <BR>
     * <BR>
     * ２）　@買単価チェック<BR>
     * <BR>
     * 　@２－１）　@this.買単価＝nullの場合、「買単価がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01390<BR>
     * <BR>
     * 　@２－２）　@this.買単価≠null、かつ
     * 以下のいずれかに該当する場合は、以下の例外をthrowする。<BR>
     * 　@　@　@・this.買単価≠数字<BR>
     * 　@　@　@　@　@　@「買単価が数字以外」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01391<BR>
     * <BR>
     * 　@　@　@・this.買単価≦0<BR>
     * 　@　@　@　@　@　@「買単価が0以下」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01392<BR>
     * <BR>
     * 　@　@　@・this.買単価＝8桁を超える数字<BR>
     * 　@　@　@　@　@　@「買単価の桁数が8桁を超過」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01393<BR>
     * <BR>
     * <BR>
     * ３）　@売単価チェック<BR>
     * <BR>
     * 　@３－１）　@this.売単価＝nullの場合、「売単価がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01420<BR>
     * <BR>
     * 　@３－２）　@this.売単価≠null、かつ
     * 以下のいずれかに該当する場合は、以下の例外をthrowする。<BR>
     * 　@　@　@・this.売単価≠数字<BR>
     * 　@　@　@　@　@　@「売単価が数字以外」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01391<BR>
     * <BR>
     * 　@　@　@・this.売単価≦0<BR>
     * 　@　@　@　@　@　@「売単価が0以下」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01392<BR>
     * <BR>
     * 　@　@　@・this.売単価＝8桁を超える数字<BR>
     * 　@　@　@　@　@　@「売単価の桁数が8桁を超過」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01393<BR>
     * <BR>
     * <BR>
     * ４）　@注文形態チェック<BR>
     * <BR>
     * 　@４－１）　@this.注文形態＝nullの場合、「注文形態がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01396<BR>
     * <BR>
     * 　@４－２）　@this.注文形態≠null、かつ 以下の値以外の場合、<BR>
     * 　@　@　@　@　@「注文形態が未定義の値」の例外をthrowする。<BR>
     * 　@　@　@　@・"1：インターネット"<BR>
     * 　@　@　@　@・"2：コールセンター"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01397<BR>
     * <BR>
     * <BR>
     * ５）　@現物／ミニ株区分チェック<BR>
     * <BR>
     * 　@５－１）　@this.現物／ミニ株区分＝nullの場合、「現物／ミニ株区分がnull」の例外?
     * throwする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01396<BR>
     * <BR>
     * 　@５－２）　@this.現物／ミニ株区分≠null、かつ 以下の値以外の場合、 <BR>
     * 　@　@　@　@　@「現物／ミニ株区分が未定義の値」の例外をthrowする。<BR>
     * 　@　@　@　@・"1：現物株式"<BR>
     * 　@　@　@　@・"2：ミニ株"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01397<BR>
     * <BR>
     * <BR>
     * ６）　@現物／ミニ株区分・売買単位チェック<BR>
     * <BR>
     * 　@６－１）　@this.現物／ミニ株区分＝"2：ミニ株"の場合、<BR>
     * 　@　@　@　@　@　@this.売買単位＝nullであれば「売買単位がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01398<BR>
     * <BR>
     * 　@６－２）　@this.売買単位≠null、かつ
     * 以下のいずれかに該当する場合は、以下の例外をthrowする。<BR>
     * 　@　@　@・this.売買単位≠数字<BR>
     * 　@　@　@　@　@　@「売買単位が数字以外」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01399<BR>
     * <BR>
     * 　@　@　@・this.売買単位≦0<BR>
     * 　@　@　@　@　@　@「売買単位が0以下」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01400<BR>
     * <BR>
     * <BR>
     * ７）　@株数・現物／ミニ株区分・売買単位チェック<BR>
     * <BR>
     * 　@　@　@・this.現物／ミニ株区分＝"2：ミニ株"の場合、かつ<BR>
     * 　@　@　@　@this.株数％（this.売買単位÷10）＞0
     * の場合（＝除算で余りが出る場合）、<BR>
     * 　@　@　@　@　@　@「株数が売買単位の10分の1に対して非整数倍」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01401<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * <BR>
     * 1) orderQuantity check<BR>
     *  1-1) If "this.orderQuantity = null"<BR>
     *       throw the following exception.<BR>
     *       [orderQuantity is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00074<BR>
     * <BR>
     * <BR>
     *  1-2) If "this.orderQuantity is not null"<BR>
     *   1-2-1) If "this.orderQuantity is not a number"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00075<BR>
     * <BR>
     * <BR>
     *   1-2-2) If "this.orderQuantity is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00076<BR>
     * <BR>
     * <BR>
     *   1-2-3) If "this.orderQuantity has more than 8 digits"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity has more than 8 digits]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00077<BR>
     * <BR>
     * <BR>
     * 2) buyPrice check<BR>
     *  2-1) If "this.buyPrice = null"<BR>
     *       throw the following exception.<BR>
     *       [buyPrice is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01390<BR>
     * <BR>
     * <BR>
     *  2-2) If "this.buyPrice is not null"<BR>
     *   2-2-1) If "this.buyPrice is not a number"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01391<BR>
     * <BR>
     * <BR>
     *   2-2-2) If "this.buyPrice is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01392<BR>
     * <BR>
     * <BR>
     *   2-2-3) If "this.buyPrice has more than 8 digits"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice has more than 8 digits]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01393<BR>
     * <BR>
     * <BR>
     * 3) sellPrice check<BR>
     *  3-1) If "this.sellPrice = null"<BR>
     *       throw the following exception.<BR>
     *       [sellPrice is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01420<BR>
     * <BR>
     * <BR>
     *  3-2) If "this.sellPrice is not null"<BR>
     *   3-2-1) If "this.sellPrice is not a number"<BR>
     *          throw the following exception.<BR>
     *          [sellPrice is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01391<BR>
     * <BR>
     * <BR>
     *   3-2-2) If "this.sellPrice is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [sellPrice is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01392<BR>
     * <BR>
     * <BR>
     *   3-2-3) If "this.sellPrice has more than 8 digits"<BR>
     *          throw the following exception.<BR>
     *          [sellPrice has more than 8 digits]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01393<BR>
     * <BR>
     * <BR>
     * 4) orderForm check<BR>
     *  4-1) If "this.orderForm = null<BR>
     *       throw the following exception.<BR>
     *       [orderForm is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01396<BR>
     * <BR>
     * <BR>
     *  4-2) If "this.orderForm is not null" and <BR>
     *           "this.orderForm is not one of the following values".<BR>
     *              ・"1：Internet"<BR>
     *              ・"2：Call Center"<BR>
     *         throw the following exception. [orderForm is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01397<BR>
     * <BR>
     * <BR>
     * 5) equityMiniDiv check<BR>
     *  5-1) If "this.equityMiniDiv = null<BR>
     *       throw the following exception.<BR>
     *       [equityMiniDiv is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01396<BR>
     * <BR>
     * <BR>
     *  5-2) If "this.equityMiniDiv is not null" and <BR>
     *          "this.equityMiniDiv is not one of the following values".<BR>
     *              ・"1：equity"<BR>
     *              ・"2：Mini stock division"<BR>
     *        throw the following exception. [equityMiniDiv is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01397<BR>
     * <BR>
     * <BR>
     * 6) equityMiniDiv / dealingUnit check<BR>
     *  6-1) If "this.equityMiniDiv = '2'(Mini stock division)" and<BR>
     *          "this.dealingUnit = null"<BR>
     *       throw the following exception. [dealingUnit is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01398<BR>
     * <BR>
     * <BR>
     *  6-2) If "this.dealingUnit is not null" and <BR>
     *          "this.dealingUnit is one of the following values".<BR>
     * <BR>
     *   6-2-1) If "this.dealingUnit is not a number"<BR>
     *          throw the following exception.<BR>
     *          [dealingUnit is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01399<BR>
     * <BR>
     * <BR>
     *   6-2-2) If "this.dealingUnit is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [dealingUnit is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01400<BR>
     * <BR>
     * <BR>
     * 7) orderQuantity, equityMiniDiv, dealingUnit check<BR>
     *  7-1) If "this.equityMiniDiv = '2'(Mini stock division)" and<BR>
     *          "this.orderQuantitiy%(this.dealingUnit / 10) > 0 (=There is
     * remainder)<BR>
     *          throw the following exception.<BR>
     *          [The number of stocks is non-integral multiples against<BR>
     *           1/10 at each buying and selling. ]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01401<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 418B464B017F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        int l_intDealingUnit = 0;
        int l_intOrderQuantity = 0;
        BigDecimal l_bdDealingUnit;
        BigDecimal l_bdOrderQuantity;
        BigDecimal l_bdOneTenth;
        BigDecimal l_bdTen;
        final int l_intTen = TEN_AS_DENOMINATOR;
        log.entering(STR_METHOD_NAME);
        if (this.orderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if (!WEB3StringTypeUtility.isNumber(this.orderQuantity))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (Double.parseDouble(this.orderQuantity) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (WEB3StringTypeUtility.getByteLength(this.orderQuantity) > MAXLENGTH)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "Overflow : orderQuantity = " + this.orderQuantity);
            }
        }

        if (this.buyPrice == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01390,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if (!WEB3StringTypeUtility.isNumber(this.buyPrice))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01391,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (Double.parseDouble(this.buyPrice) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01392,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (WEB3StringTypeUtility.getByteLength(this.buyPrice) > MAXLENGTH)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01393,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "overflow : buyPrice= " + this.buyPrice);
            }
        }

        if (this.sellPrice == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01420,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if (!WEB3StringTypeUtility.isNumber(this.sellPrice))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01391,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (Double.parseDouble(this.sellPrice) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01392,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (WEB3StringTypeUtility.getByteLength(this.sellPrice) > MAXLENGTH)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01393,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "overflow : sellPrice = " + this.sellPrice);

            }
        }
        //Mismatch in the javadoc and source file.
        //Implementation is done based on WEB3ErrorCatalog.java information
        if (this.orderForm == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01396,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if ((!WEB3TrialCalcOrderFormDef.INTERNET.equals(this.orderForm))
                && (!WEB3TrialCalcOrderFormDef.CALL_CENTER.equals(this.orderForm)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01397,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        if (this.equityMiniDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01396,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        } else if (
            (!WEB3TrialCalcEquityMiniDivDef.EQUITY.equals(this.equityMiniDiv))
                && (!WEB3TrialCalcEquityMiniDivDef.MINI_STOCK_DIVISION.equals(this.equityMiniDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01397,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.dealingUnit == null && WEB3TrialCalcEquityMiniDivDef.MINI_STOCK_DIVISION.equals
                (this.equityMiniDiv))
        {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01398,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        } else if (this.dealingUnit != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.dealingUnit))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01399,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            } else if (Double.parseDouble(this.dealingUnit) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01400,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

       if (WEB3TrialCalcEquityMiniDivDef.MINI_STOCK_DIVISION.equals(this.equityMiniDiv))
       {
           l_bdDealingUnit = new BigDecimal(this.dealingUnit);
           l_bdOrderQuantity = new BigDecimal(this.orderQuantity);
           double l_dblCalc = 0;
           l_bdTen = new BigDecimal(l_intTen);
           l_bdOneTenth = l_bdDealingUnit.divide(l_bdTen, 2);
           l_dblCalc = l_bdOrderQuantity.doubleValue() % l_bdOneTenth.doubleValue();
           if (l_dblCalc > 0)
           {
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01401,
               this.getClass().getName() + "." + STR_METHOD_NAME);
           }
       }
       log.exiting(STR_METHOD_NAME);
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3TrialCalcProfitLossCalcResponse(this);
    }
}
@
