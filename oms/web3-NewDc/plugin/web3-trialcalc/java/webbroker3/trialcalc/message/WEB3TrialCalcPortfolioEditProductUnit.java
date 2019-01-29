head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioEditProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオ編集銘柄明細(WEB3TrialCalcPortfolioEditProductUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.trialcalc.define.WEB3TrialCalcMarketCodeDef;

/**
 * （計算サービスポートフォリオ編集銘柄明細）<BR>
 * <BR>
 * 計算サービスポートフォリオ編集銘柄明細。<BR>
 * <BR>
 * WEB3TrialCalcPortfolioEditProductUnit<BR>
 * @@author Prabhu
 * @@version 1.0
 * <BR>
 */
public class WEB3TrialCalcPortfolioEditProductUnit extends Message
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioEditProductUnit.class);

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
     * （買単価）<BR>
     * <BR>
     * 買単価。<BR>
     * （null指定可）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * buyPrice. (Null can be specified)<BR>
     */
    public String buyPrice;

    /**
     * 市場コード。<BR>
     * （1：東京　@2：大阪　@3：名古屋　@6：福岡　@8：札幌　@9：NNM　@10：JASDAQ）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  7 : SAPPORO<BR>
     *  9 : NNM    10 : JASDAQ)<BR>
     */
    public String marketCode;

    /**
     * @@roseuid 41C81B2D0243
     */
    public WEB3TrialCalcPortfolioEditProductUnit()
    {

    }

    /**
     * 当クラスのプロパティの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * ※銘柄明細毎にエラーチェック結果を返すため、<BR>
     * ※株数、買単価の値チェックは、サービス内部で実施する。<BR>
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
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
    *For every product details an error check result is given.<BR>
    *Therefore the check for orderQuantity and buyPrice is executed in service.<BR>
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
     * @@roseuid 41986EF501E6
     * @@throws WEB3BaseException WEB3BaseException
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
        log.exiting(STR_METHOD_NAME);
    }
}
@
