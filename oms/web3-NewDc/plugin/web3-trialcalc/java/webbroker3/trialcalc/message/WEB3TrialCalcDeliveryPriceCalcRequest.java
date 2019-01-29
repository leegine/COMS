head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcDeliveryPriceCalcRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス受渡代金計算リクエスト(WEB3TrialCalcDeliveryPriceCalcRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.trialcalc.define.WEB3TrialCalcDealingTypeDef;
import webbroker3.trialcalc.define.WEB3TrialCalcEquityMiniDivDef;
import webbroker3.trialcalc.define.WEB3TrialCalcMarketCodeDef;
import webbroker3.trialcalc.define.WEB3TrialCalcOrderFormDef;

/**
 * （計算サービス受渡代金計算リクエスト）<BR>
 * <BR>
 * 計算サービス受渡代金計算サービスのリクエストデータ。<BR>
 * <BR>
 * WEB3TrialCalcDeliveryPriceCalcRequest<BR>
 * @@author sararavanan
 * @@version 1.0
 */
public class WEB3TrialCalcDeliveryPriceCalcRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_estimatedamount_calc";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503301100L;

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcDeliveryPriceCalcRequest.class);
    /**
     * （売買区分）<BR>
     * <BR>
     * 売買区分。<BR>
     * （1：買い　@2：売り）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * dealingType<BR>
     * (1 : Buy  2 : Sell)<BR>
     */
    public String dealingType;

    /**
     * （銘柄コード）<BR>
     * <BR>
     * productCode<BR>
     */
    public String productCode;

    /**
     * （株数）<BR>
     * <BR>
     * orderQuantity<BR>
     */
    public String orderQuantity;

    /**
     * （株単価）<BR>
     * <BR>
     * 株単価。<BR>
     * （null指定可）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * orderPrice<BR>
     * (Null can be specified)<BR>
     * <BR>
     */
    public String orderPrice;

    /**
     * 市場コード。<BR>
     * （1：東京　@2：大阪　@3：名古屋　@6：福岡　@8：札幌　@9：NNM　@10：JASDAQ）<BR>
     * （null指定可）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  8 : SAPPORO<BR>
     *  9 : NNM    10 : JASDAQ)<BR>
     */
    public String marketCode;

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
     * <BR>
     */
    public String equityMiniDiv;

    /**
     * @@roseuid 41C813DD00CC
     */
    public WEB3TrialCalcDeliveryPriceCalcRequest()
    {
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@売買区分チェック<BR>
     * <BR>
     * 　@１－１）　@this.売買区分＝nullの場合、「売買区分がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01402<BR>
     * <BR>
     * 　@１－２）　@this.売買区分≠null、かつ 以下の値以外の場合、<BR>
     * 　@　@　@　@　@「売買区分が未定義の値」の例外をthrowする。<BR>
     * 　@　@　@　@・"1：買い"<BR>
     * 　@　@　@　@・"2：売り"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01403<BR>
     * <BR>
     * <BR>
     * ２）　@銘柄コードチェック<BR>
     * <BR>
     * 　@２－１）　@this.銘柄コード＝nullの場合、「銘柄コードがnull」の例外をthrowする。
     * <BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * <BR>
     * ３）　@株数チェック<BR>
     * <BR>
     * 　@３－１）　@this.株数＝nullの場合、「株数がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00074<BR>
     * <BR>
     * 　@３－２）　@this.株数≠null、かつ
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
     * ４）　@株単価チェック<BR>
     * <BR>
     * 　@４－１）　@this.株単価≠null、かつ
     * 以下のいずれかに該当する場合は、以下の例外をthrowする。<BR>
     * 　@　@　@・this.株単価≠数字<BR>
     * 　@　@　@　@　@　@「株単価が数字以外」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01404<BR>
     * <BR>
     * 　@　@　@・this.株単価≦0<BR>
     * 　@　@　@　@　@　@「株単価が0以下」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01405<BR>
     * <BR>
     * 　@　@　@・this.株単価＝8桁を超える数字<BR>
     * 　@　@　@　@　@　@「株単価の桁数が8桁を超過」の例外をthrow。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01406<BR>
     * <BR>
     * <BR>
     * ５）　@市場コードチェック<BR>
     * <BR>
     * 　@５－１）　@this.市場コード≠null、かつ 以下の値以外の場合、<BR>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をthrowする。<BR>
     * 　@　@　@　@・"1：東京"<BR>
     * 　@　@　@　@・"2：東京"<BR>
     * 　@　@　@　@・"3：名古屋"<BR>
     * 　@　@　@　@・"6：福岡"<BR>
     * 　@　@　@　@・"8：札幌"<BR>
     * 　@　@　@　@・"9：NNM"<BR>
     * 　@　@　@　@・"10：JASDAQ"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * <BR>
     * ６）　@注文形態チェック<BR>
     * <BR>
     * 　@６－１）　@this.注文形態＝nullの場合、「注文形態がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01394<BR>
     * <BR>
     * 　@６－２）　@this.注文形態≠null、かつ 以下の値以外の場合、<BR>
     * 　@　@　@　@　@「注文形態が未定義の値」の例外をthrowする。<BR>
     * 　@　@　@　@・"1：インターネット"<BR>
     * 　@　@　@　@・"2：コールセンター"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01395<BR>
     * <BR>
     * <BR>
     * ７）　@現物／ミニ株区分チェック<BR>
     * <BR>
     * 　@７－１）　@this.現物／ミニ株区分＝nullの場合、「現物／ミニ株区分がnull」の例外?
     * throwする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01396<BR>
     * <BR>
     * 　@７－２）　@this.現物／ミニ株区分≠null、かつ 以下の値以外の場合、 <BR>
     * 　@　@　@　@　@「現物／ミニ株区分が未定義の値」の例外をthrowする。<BR>
     * 　@　@　@　@・"1：現物株式"<BR>
     * 　@　@　@　@・"2：ミニ株"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01397<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * <BR>
     * 1) dealingType check<BR>
     *  1-1) If "this.dealingType = null"<BR>
     *       throw the following exception.<BR>
     *       [dealingType is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01402<BR>
     * <BR>
     * <BR>
     *  1-2) If "this.dealingType is not null" and<BR>
     *          "this.dealingType is not one of the following values"<BR>
     *         ・"1 : Buy"<BR>
     *         ・"2 : Sell"<BR>
     *       throw the following exception.<BR>
     *       [dealingType is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01403<BR>
     * <BR>
     * <BR>
     * 2) productCode check<BR>
     *  2-1) If "this.productCode = null<BR>
     *       throw the following exception.<BR>
     *       [productCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * <BR>
     * 3) orderQuantity check<BR>
     *  3-1) If "this.orderQuantity = null"<BR>
     *       throw the following exception.<BR>
     *       [orderQuantity is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00074<BR>
     * <BR>
     * <BR>
     *  3-2) If "this.orderQuantity is not null"<BR>
     *   3-2-1) If "this.orderQuantity is not a number"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00075<BR>
     * <BR>
     * <BR>
     *   3-2-2) If "this.orderQuantity is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00076<BR>
     * <BR>
     * <BR>
     *   3-2-3) If "this.orderQuantity has more than 8 digits"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity has more than 8 digits]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00077<BR>
     * <BR>
     * <BR>
     * 4) orderPrice check<BR>
     *  4-1) If "this.orderPrice is not null"<BR>
     *   4-1-1) If "this.orderPrice is not a number"<BR>
     *          throw the following exception.<BR>
     *          [orderPrice is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01404<BR>
     * <BR>
     * <BR>
     *   4-1-2) If "this.orderPrice is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [orderPrice is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01405<BR>
     * <BR>
     * <BR>
     *   4-1-3) If "this.orderPrice has more than 8 digits"<BR>
     *          throw the following exception.<BR>
     *          [orderPrice has more than 8 digits]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01406<BR>
     * <BR>
     * <BR>
     * 5) marketCode check<BR>
     *  5-1) If "this.marketCode is not null" and<BR>
     *          "this.marketCode is not one of the following values"<BR>
     *          ・"1：TOKYO"<BR>
     *          ・"2：OSAKA"<BR>
     *          ・"3：NAGOYA"<BR>
     *          ・"6：FUKUOKA"<BR>
     *          ・"8：SAPPORO"<BR>
     *          ・"9：NNM"<BR>
     *          ・"10：JASDAQ"<BR>
     *       throw the following exception.<BR>
     *       [marketCode is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * <BR>
     * 6) orderForm check<BR>
     *  6-1) If "this.orderForm = null<BR>
     *       throw the following exception.<BR>
     *       [orderForm is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01394<BR>
     * <BR>
     * <BR>
     *  6-2) If "this.orderForm is not null" and <BR>
     *           "this.orderForm is not one of the following values".<BR>
     *              ・"1：Internet"<BR>
     *              ・"2：Call Center"<BR>
     *         throw the following exception. [orderForm is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01395<BR>
     * <BR>
     * <BR>
     * 7) equityMiniDiv check<BR>
     *  7-1) If "this.equityMiniDiv = null<BR>
     *       throw the following exception.<BR>
     *       [equityMiniDiv is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01396<BR>
     * <BR>
     *  7-2) If "this.equityMiniDiv is not null" and <BR>
     *           "this.equityMiniDiv is not one of the following values".<BR>
     *              ・"1：equity"<BR>
     *              ・"2：Mini stock division"<BR>
     *         throw the following exception. [equityMiniDiv is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01397<BR>
     * <BR>
     * @@exception WEB3BaseException is throwing
     * @@roseuid 418B4E250018
     */
    public void validate() throws WEB3BaseException
    {
        final int l_intEight = 8;
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.dealingType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01402,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if ((!WEB3TrialCalcDealingTypeDef.BUY.equals(this.dealingType))
                && (!WEB3TrialCalcDealingTypeDef.SELL.equals(this.dealingType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        if (this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

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
            if (WEB3StringTypeUtility.getByteLength(this.orderQuantity) > l_intEight)
            {

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "Overflow : orderQuantity = " + this.orderQuantity);
            }
        }

        if (this.orderPrice != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.orderPrice))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01404,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (Double.parseDouble(this.orderPrice) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01405,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if ((WEB3StringTypeUtility.getByteLength(this.orderPrice)) > l_intEight)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01406,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "overflow : orderPrice = " +  this.orderPrice);
            }
        }

        if (this.marketCode != null)
        {
            if ((!WEB3TrialCalcMarketCodeDef.TOKYO.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.OSAKA.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.NAGOYA.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.FUKUOKA.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.SAPPORO.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.NNM.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.JASDAQ.equals(this.marketCode)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        if (this.orderForm == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01394,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if ((!WEB3TrialCalcOrderFormDef.INTERNET.equals(this.orderForm))
                && (!WEB3TrialCalcOrderFormDef.CALL_CENTER.equals(this.orderForm)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01395,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        if (this.equityMiniDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01396,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if ((!WEB3TrialCalcEquityMiniDivDef.EQUITY.equals(this.equityMiniDiv))
                && (!WEB3TrialCalcEquityMiniDivDef.MINI_STOCK_DIVISION.equals(this.equityMiniDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01397,
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
        return new WEB3TrialCalcDeliveryPriceCalcResponse(this);
    }
}
@
