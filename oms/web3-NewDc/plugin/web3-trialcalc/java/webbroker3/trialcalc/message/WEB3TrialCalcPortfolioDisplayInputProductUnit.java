head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioDisplayInputProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオ表示入力銘柄明細(WEB3TrialCalcPortfolioDisplayInputProductUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.trialcalc.define.WEB3TrialCalcInputCapitalGainDivDef;
import webbroker3.trialcalc.define.WEB3TrialCalcMarketCodeDef;

/**
 * （計算サービスポートフォリオ表示入力銘柄明細）<BR>
 * <BR>
 * 計算サービスポートフォリオ表示入力銘柄明細。<BR>
 * <BR>
 * WEB3TrialCalcPortfolioDisplayInputProductUnit<BR>
 * @@author Umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioDisplayInputProductUnit extends Message
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioDisplayInputProductUnit.class);

    /**
     * maxLength int
     */
    private final static int MAXLENGTH = 8;

    /**
     * （銘柄コード）<BR>
     * <BR>
     * Product code.<BR>
     */
    public String productCode;

    /**
     * （市場コード）<BR>
     * <BR>
     * 市場コード。<BR>
     * （1：東京　@2：大阪　@3：名古屋　@6：福岡　@8：札幌　@9：NNM　@10：JASDAQ）
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code.<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  8 : SAPPORO<BR>
     *  9 : NNM   10 : JASDAQ)<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * （買付単価）<BR>
     * <BR>
     * 買付単価。<BR>
     * <BR>
     * Buy unit Price<BR>
     */
    public String buyPrice;

    /**
     * （入力／特定区分）<BR>
     * <BR>
     * 入力／特定区分。<BR>
     * （1：入力　@2：特定）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * inputCapitalGainDiv<BR>
     * (1 : Input  2 : Specific)<BR>
     */
    public String inputCapitalGainDiv;

    /**
     * （評価単価）<BR>
     * <BR>
     * 評価単価。<BR>
     * （時価指定の場合はnullをセット）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * evaluationPrice<BR>
     * (When current price is specified null is set)<BR>
     */
    public String evaluationPrice;


    /**
     * @@roseuid 41C81B1003BA
     */
    public WEB3TrialCalcPortfolioDisplayInputProductUnit()
    {

    }

    /**
     * 当クラスのプロパティの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@銘柄コードチェック<BR>
     * <BR>
     * 　@１－１）　@this.銘柄コード＝nullの場合、<BR>
     * 　@　@　@　@　@「銘柄コードがnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * <BR>
     * ２）　@市場コードチェック<BR>
     * <BR>
     * 　@２－１）　@this.市場コード≠null、かつ 以下の値以外の場合、<BR>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をthrowする。<BR>
     *          ・"1：東京"<BR>
     *          ・"2：大阪"<BR>
     *          ・"3：名古屋"<BR>
     *          ・"6：福岡"<BR>
     *          ・"8：札幌"<BR>
     *          ・"9：NNM"<BR>
     *          ・"10：JASDAQ"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * <BR>
     * ３）　@買付単価チェック<BR>
     * <BR>
     * 　@３－１）　@this.買付単価≠null、かつ<BR>
     * 　@　@　@　@　@以下のいずれかに該当する場合は、以下の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@　@・this.買付単価≠数字 (「買付単価が数字以外」の例外をthrow。)<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01391<BR>
     * <BR>
     * 　@　@　@　@・this.買付単価≦0　@　@  (「買付単価が0以下」の例外をthrow。)<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01392<BR>
     * <BR>
     * 　@　@　@　@・this.買付単価＝8桁を超える数字<BR>
     * 　@　@　@　@　@　@(「買付単価の桁数が8桁を超過」の例外をthrow。)<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01393<BR>
     * <BR>
     * <BR>
     * ４）　@入力／特定区分チェック<BR>
     * <BR>
     * 　@４－１）　@this.入力／特定区分＝nullの場合、<BR>
     * 　@　@　@　@　@「入力／特定区分がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01410<BR>
     * <BR>
     * <BR>
     * 　@４－２）　@this.入力／特定区分≠null、かつ 以下の値以外の場合、<BR>
     * 　@　@　@　@　@「入力／特定区分が未定義の値」の例外をthrowする。<BR>
     * 　@　@　@　@・"1：入力"<BR>
     * 　@　@　@　@・"2：特定"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01411<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * <BR>
     * 1) productCode check<BR>
     *  1-1) If "this.productCode = null"<BR>
     *       throw the following exception.<BR>
     *       [productCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * <BR>
     * 2) marketCode check<BR>
     *  2-1) If "this.marketCode is not null" and<BR>
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
     * 3) buyPrice check<BR>
     *  3-1) If "this.buyPrice is not null"<BR>
     *   3-1-1) If "this.buyPrice is not a number"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01391<BR>
     * <BR>
     * <BR>
     *   3-1-2) If "this.buyPrice is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01392<BR>
     * <BR>
     * <BR>
     *   3-1-3) If "this.buyPrice has more than 8 digits"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice has more than 8 digits]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01393<BR>
     * <BR>
     * <BR>
     * 4) inputCapitalGainDiv check<BR>
     *  4-1) If "this.inputCapitalGainDiv = null"<BR>
     *       throw the following exception.<BR>
     *       [inputCapitalGainDiv is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01410<BR>
     * <BR>
     * <BR>
     *  4-2) If "this.inputCapitalGainDiv is not null" and<BR>
     *          "this.inputCapitalGainDiv is not one of the following values"<BR>
     * 　@　@　@　@・"1：input"<BR>
     * 　@　@　@　@・"2：Specific"<BR>
     *       throw the following exception.<BR>
     *       [inputCapitalGainDiv is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01411<BR>
     * <BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4198093C0198
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.marketCode != null)
        {
            /**
             * To check if marketCode is not one of the following :
             * TOKYO,OSAKA,NAGOYA,FUKUOKA,SAPPORO,NNM,JASDAQ
             */
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
        if (this.buyPrice != null)
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
                "overflow : buyPrice = " + this.buyPrice);
            }
        }
        if (this.inputCapitalGainDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01410,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if ((!WEB3TrialCalcInputCapitalGainDivDef.INPUT.equals(this.inputCapitalGainDiv))
                && (!WEB3TrialCalcInputCapitalGainDivDef.SPECIFIC.equals(this.inputCapitalGainDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01411,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
