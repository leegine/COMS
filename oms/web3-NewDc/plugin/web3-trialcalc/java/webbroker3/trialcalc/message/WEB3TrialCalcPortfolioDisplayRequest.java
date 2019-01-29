head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオ表示リクエスト(WEB3TrialCalcPortfolioDisplayRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.trialcalc.define.WEB3TrialCalcDisplayTargetDef;
import webbroker3.trialcalc.define.WEB3TrialCalcKeyItemDef;
import webbroker3.trialcalc.define.WEB3TrialCalcOrderFormDef;

/**
 * （計算サービスポートフォリオ表示リクエスト）<BR>
 * <BR>
 * WEB3TrialCalcPortfolioDisplayRequest<BR>
 * <BR>
 * 計算サービスポートフォリオサービス（ポートフォリオ表示）のリクエストデータ。<BR>
 * @@author umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioDisplayRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_portfolio_display";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501101155L;

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioDisplayRequest.class);

    /**
     * （表示対象）<BR>
     * <BR>
     * 表示対象の指定。<BR>
     * （1：ポートフォリオ登録分のみ　@2：特定分のみ　@3：両方合せる）<BR>
     * （null指定可）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * displayTarget<BR>
     * (1 : Portfolio registration only  2 : Specific only  3 : Both)<BR>
     */
    public String displayTarget;

    /**
     * （ソートキー）<BR>
     * <BR>
     * 計算サービスソートキーの一覧<BR>
     * <BR>
     * ＜対象項目＞<BR>
     * 銘柄コード<BR>
     * 市場コード<BR>
     * 株数<BR>
     * 時価<BR>
     * 買付単価<BR>
     * 買付代金<BR>
     * 評価額<BR>
     * 評価損益<BR>
     * 評価損益率<BR>
     * 入力／特定区分<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Sort keys<BR>
     * <BR>
     * List of calculation service sorting key<BR>
     * <Target Items><BR>
     * productCode<BR>
     * marketCode<BR>
     * orderQuantity<BR>
     * currentPrice<BR>
     * buyPrice<BR>
     * buyAmount<BR>
     * appraisalPrice<BR>
     * appraisalProfitLoss<BR>
     * appraisalProfitLossRate<BR>
     * inputCapitalGainDiv<BR>
     */
    public webbroker3.trialcalc.message.WEB3TrialCalcSortKeyUnit[] sortKeys;

    /**
     * （入力銘柄明細一覧）<BR>
     * <BR>
     * 入力銘柄明細一覧。<BR>
     * （評価単価入力時のみ設定）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Input Product details list.<BR>
     * Set only when evaluationPrice is input.<BR>
     * <BR>
     */
    public WEB3TrialCalcPortfolioDisplayInputProductUnit[] portfolioDisplayInputProductUnit;

    /**
     * （ポートフォリオコード）<BR>
     * <BR>
     * portfolioCode<BR>
     */
    public String portfolioCode;

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
     * theWEB3TrialCalcSortKeyUnit WEB3TrialCalcSortKeyUnit
     */
    public WEB3TrialCalcSortKeyUnit[] theWEB3TrialCalcSortKeyUnit;

    /**
     * theWEB3TrialCalcPortfolioDisplayInputProductUnit
     * WEB3TrialCalcPortfolioDisplayInputProductUnit
     */
    public WEB3TrialCalcPortfolioDisplayInputProductUnit[]
                theWEB3TrialCalcPortfolioDisplayInputProductUnit;

    /**
     * intMinValue int
     */
    private int intMinValue = 0;

    /**
     * @@roseuid 41C811B40223
     */
    public WEB3TrialCalcPortfolioDisplayRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@表示対象チェック<BR>
     * <BR>
     * 　@１－１）　@this.表示対象≠null、かつ 以下の値以外の場合、<BR>
     * 　@　@　@　@　@「表示対象が未定義の値」の例外をthrowする。<BR>
     * 　@　@　@　@・"1：ポートフォリオ登録分のみ"<BR>
     * 　@　@　@　@・"2：特定分のみ"<BR>
     * 　@　@　@　@・"3：両方合せる"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01415<BR>
     * <BR>
     * <BR>
     * ２）　@ソートキーチェック<BR>
     * <BR>
     * 　@２－１）　@this.ソートキー＝nullの場合、<BR>
     * 　@　@　@　@「ソートキーがnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * <BR>
     * 　@２－２）　@this.ソートキー.要素数＝0の場合、<BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * <BR>
     * 　@２－３）　@this.ソートキーの全要素に対して<BR>
     * 　@　@　@　@下記のチェックを行う。<BR>
     * 　@　@２－３－１）　@ソートキー.validate()をコールする。<BR>
     * <BR>
     * 　@　@２－３－２）　@ソートキー.キー項目に下記の項目以外が<BR>
     * 　@　@　@　@　@　@　@設定されていたら、<BR>
     * 　@　@　@　@　@　@　@「ソートキー.キー項目が未定義の値」の例外をthrowする。<BR>
     * 　@　@　@　@　@　@　@・「銘柄コード」<BR>
     * 　@　@　@　@　@　@　@・「市場コード」<BR>
     * 　@　@　@　@　@　@　@・「株数」<BR>
     * 　@　@　@　@　@　@　@・「時価」<BR>
     * 　@　@　@　@　@　@　@・「買付単価」<BR>
     * 　@　@　@　@　@　@　@・「買付代金」<BR>
     * 　@　@　@　@　@　@　@・「評価額」<BR>
     * 　@　@　@　@　@　@　@・「評価損益」<BR>
     * 　@　@　@　@　@　@　@・「評価損益率」<BR>
     * 　@　@　@　@　@　@　@・「入力／特定区分」<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * <BR>
     * ３）　@入力銘柄明細一覧チェック<BR>
     * <BR>
     * 　@３－１）　@this.入力銘柄明細一覧≠nullの場合、要素数＝0の場合は<BR>
     * 　@　@　@　@「入力銘柄明細一覧.要素数が0」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00793<BR>
     * <BR>
     * <BR>
     * 　@３－２）　@this.入力銘柄明細一覧の全要素に対して<BR>
     * 　@　@　@　@下記のチェックを行う。<BR>
     * 　@　@３－２－１）　@入力銘柄明細一覧.validate()をコールする。<BR>
     * <BR>
     * ４）　@ポートフォリオコードチェック<BR>
     * <BR>
     * 　@４－１）　@this.ポートフォリオコード＝nullの場合、<BR>
     * 　@　@　@　@　@「ポートフォリオコードがnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01418<BR>
     * <BR>
     * <BR>
     * ５）　@注文形態チェック<BR>
     * <BR>
     * 　@５－１）　@this.注文形態＝nullの場合、「注文形態がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01394<BR>
     * <BR>
     * 　@５－２）　@this.注文形態≠null、かつ 以下の値以外の場合、<BR>
     * 　@　@　@　@　@「注文形態が未定義の値」の例外をthrowする。<BR>
     * 　@　@　@　@・"1：インターネット"<BR>
     * 　@　@　@　@・"2：コールセンター"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01395<BR>
     * <BR>
     * <BR>
     * ６）　@入力銘柄明細一覧・ポートフォリオコードチェック<BR>
     * <BR>
     * 　@６－１）　@this.入力銘柄明細一覧＝null、かつ
     * this.ポートフォリオコード＝nullの場合、<BR>
     * 　@　@　@　@　@　@「対象明細の指定なし」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01419<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * 1) displayTarget check<BR>
     *  1-1) If "this.displayTarget is not null" and<BR>
     *           "this.displayTarget is not one of the following values.<BR>
     *         ・"1：ONLY PORTFOLIO"<BR>
     *         ・"2：ONLY SPECIAL ACOUNT"<BR>
     *         ・"3：BOTH"<BR>
     *         throw the following exception. [displayTarget is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01415<BR>
     * <BR>
     * <BR>
     * 2) sortKey check<BR>
     *  2-1) If "this.sortKey = null"<BR>
     *         throw the following exception. [sortKey is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * <BR>
     *  2-2) If "this.sortKeys.(number of elements) = 0<BR>
     *         throw the following exception.<BR>
     *         [sortKey.(number of elements) is 0]       <BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * <BR>
     *  2-3) Check for all the values of this.sortKeys<BR>
     *   2-3-1) Call "sortKeys.validate()"<BR>
     * <BR>
     *   2-3-2) If things except the following item are set in<BR>
     *            sortKeys.(key item) <BR>
     *               ・[productCode]<BR>
     *               ・[marketCode]<BR>
     *               ・[orderQuantity]<BR>
     *               ・[currentPrice]<BR>
     *               ・[buyPrice]<BR>
     *               ・[buyAmount]<BR>
     *               ・[appraisalPrice]<BR>
     *               ・[appraisalProfitLoss]<BR>
     *               ・[appraisalProfitLossRate]<BR>
     *               ・[inputCapitalGainDiv]<BR>
     *            throw the following exception<BR>
     *            [sortKey.(number of elements) is 0]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * <BR>
     * 3) portfolioDisplayInputProductUnit check<BR>
     *  3-1) If "this.portfolioDisplayInputProductUnit is not null" and<BR>
     *           "number of elements = 0"<BR>
     *            throw the following exception.<BR>
     *            [portfolioDisplayInputProductUnit.(number of elements) is 0]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00793<BR>
     * <BR>
     * <BR>
     *  3-2)  Check for all the values of this.portfolioDisplayInputProductUnit.<BR>
     *   3-2-1) Call portfolioDisplayInputProductUnit.validate().<BR>
     * <BR>
     * 4) portfolioCode check<BR>
     *  4-1) If "this.portfolioCode = null"<BR>
     *        throw the following exception. [portfolioCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01418<BR>
     * <BR>
     * <BR>
     * 5) orderForm check<BR>
     *  5-1) If "this.orderForm = null"<BR>
     *        throw the following exception. [orderForm is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01394<BR>
     * <BR>
     * <BR>
     *  5-2) If "this.orderForm is not null" and <BR>
     *           "this.orderForm is not one of the following values".<BR>
     *              ・"1：Internet"<BR>
     *              ・"2：Call Center"<BR>
     *         throw the following exception. [orderForm is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01395<BR>
     * <BR>
     * <BR>
     * 6) portfolioDisplayInputProductUnit / portfolioCode check<BR>
     *  6-1) If "this.portfolioDisplayInputProductUnit = null" and<BR>
     *           "this.portfolioCode = null"<BR>
     *         throw the following exception. <BR>
     *         [The object details are not specified]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01419<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 418B4E1900C4
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.displayTarget != null)
        {
            if ((!WEB3TrialCalcDisplayTargetDef.ONLY_PORTFOLIO.equals(this.displayTarget))
                && (!WEB3TrialCalcDisplayTargetDef.ONLY_SPECIAL_ACCOUNT.equals(this.displayTarget))
                && (!WEB3TrialCalcDisplayTargetDef.BOTH.equals(this.displayTarget)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01415,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (sortKeys.length == intMinValue)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
            this.getClass().getName() + "." + STR_METHOD_NAME);

        }
        for (int i = intMinValue; i < sortKeys.length; i++)
        {
            sortKeys[i].validate();
            if (!WEB3TrialCalcKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.ORDER_QUANTITY.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.CURRENT_PRICE.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.BUY_AMOUNT.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.BUY_PRICE.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.APPRAISAL_PRICE.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.APPRAISAL_PROFIT_LOSS.equals(this.sortKeys[i].keyItem)
				&& !WEB3TrialCalcKeyItemDef.INPUT_CAPITALCAIN_DIV.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.APPRAISAL_PROFIT_LOSS_RATE.equals(
                    this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        if (this.portfolioDisplayInputProductUnit != null
            && this.portfolioDisplayInputProductUnit.length == intMinValue)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00793,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.portfolioDisplayInputProductUnit != null)
        {
            for (int i = intMinValue; i < portfolioDisplayInputProductUnit.length; i++)
            {
                portfolioDisplayInputProductUnit[i].validate();
            }
        }
        if (this.portfolioCode == null)
        {
            if (this.portfolioDisplayInputProductUnit != null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01418,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01419,
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
}

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3TrialCalcPortfolioDisplayResponse(this);
    }
}
@
