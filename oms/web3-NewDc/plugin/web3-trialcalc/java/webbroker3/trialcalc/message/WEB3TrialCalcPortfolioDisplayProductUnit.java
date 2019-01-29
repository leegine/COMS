head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioDisplayProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオ表示銘柄明細(WEB3TrialCalcPortfolioDisplayProductUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （計算サービスポートフォリオ表示銘柄明細）<BR>
 * <BR>
 * WEB3TrialCalcPortfolioDisplayProductUnit<BR>
 * <BR>
 * 計算サービスポートフォリオ表示銘柄明細。<BR>
 * @@author umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioDisplayProductUnit extends Message
{
    /**
     * （銘柄コード）<BR>
     * <BR>
     * productCode<BR>
     */
    public String productCode;

    /**
     * （銘柄名）<BR>
     * <BR>
     * productName<BR>
     */
    public String productName;

    /**
     * （市場コード）<BR>
     * <BR>
     * 市場コード。<BR>
     * （1：東京　@2：大阪　@3：名古屋　@6：福岡　@8：札幌　@9：NNM　@10：JASDAQ）<BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code.<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  8 : SAPPORO<BR>
     *  9 : NNM   10 : JASDAQ)<BR>
     */
    public String marketCode;

    /**
     * （株数）<BR>
     * <BR>
     * orderQuantity<BR>
     */
    public String orderQuantity;

    /**
     * （時価取得区分）<BR>
     * <BR>
     * 時価取得区分。<BR>
     * （1：時価　@2：前日終値　@3：当日終値）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * currentPriceGetDiv<BR>
     * (1 : Current price  2 : Last close price  3 : Today close price)<BR>
     */
    public String currentPriceGetDiv;

    /**
     * （時価）<BR>
     * <BR>
     * 時価。<BR>
     * （時価取得時（リクエスト.株単価＝null）のみセット）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * currentPrice<BR>
     * (Only set when current price is acquired.（リクエスト.株単価＝null）)<BR>
     */
    public String currentPrice;

    /**
     * （取引時間（時価発表時間））<BR>
     * <BR>
     * 取引時間（時価発表時間）。<BR>
     * （時価取得時（リクエスト.株単価＝null）のみセット）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>currentPrice<BR>
     * (Only set when current price is acquired.（リクエスト.株単価＝null）)<BR>
     */
    public Date currentPriceTime;

    /**
     * （買付単価）<BR>
     * <BR>
     * buyPrice<BR>
     */
    public String buyPrice;

    /**
     * （買付代金）<BR>
     * <BR>
     * buyAmount<BR>
     */
    public String buyAmount;

    /**
     * （評価単価）<BR>
     * <BR>
     * evaluationPrice<BR>
     */
    public String evaluationPrice;

    /**
     * （評価額）<BR>
     * <BR>
     * appraisalPrice<BR>
     */
    public String appraisalPrice;

    /**
     * （評価損益）<BR>
     * <BR>
     * appraisalProfitLoss<BR>
     */
    public String appraisalProfitLoss;

    /**
     * （評価損益率）<BR>
     * <BR>
     * appraisalProfitLossRate(percent)<BR>
     * <BR>
     * 評価損益率。（％単位）<BR>
     */
    public String appraisalProfitLossRate;

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
     * <BR>
     */
    public String inputCapitalGainDiv;

    /**
     * （エラーコード）<BR>
     * <BR>
     * エラーコード。<BR>
     * （0001：指定市場は非上場<BR>
     * 　@0002：取扱市場なし<BR>
     * 　@0003：優先市場一覧に取扱市場なし<BR>
     * 　@0004：時価取得エラー<BR>
     * 　@0005：委託手数料取得エラー<BR>
     * 　@0006；該当銘柄は存在しない<BR>
     * 　@0007；該当取引銘柄は存在しない<BR>
     * 　@1001：株数が数字以外<BR>
     * 　@1002：株数が0以下<BR>
     * 　@1003：株数の桁数が8桁を超過<BR>
     * 　@1004：買単価が数字以外<BR>
     * 　@1005：買単価が0以下<BR>
     * 　@1006：買単価の桁数が8桁を超過<BR>
     * 　@1007：評価単価が数字以外<BR>
     * 　@1008：評価単価が0以下<BR>
     * 　@1009：評価単価の桁数が8桁を超過<BR>
     * 　@2001：指定が重複（銘柄コード、市場コード、買単価が同一の明細あり）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Error code<BR>
     * (0001 : Designated market is non-listing.<BR>
     *  0002 : No handling market<BR>
     *  0003 : No handling market in the priority market list. <BR>
     *  0004 : Get price acquisition error<BR>
     *  0005 : Brokerage acquisition error<BR>
     *  0006 : The corresponding product doesn't exist.<BR>
     *  0007 : The corresponding dealings product doesn't exist. <BR>
     *  1001 : orderQuantity is not a number<BR>
     *  1002 : orderQuantity is 0 or less<BR>
     *  1003 : orderQuantity has more than eight digits<BR>
     *  1004 : buyPrice is not a number<BR>
     *  1005 : buyPrice is 0 or less<BR>
     *  1006 : buyPrice has more than eight digits<BR>
     *  1007 : evaluationPrice is not a number<BR>
     *  1008 : evaluationPrice is 0 or less<BR>
     *  1009 : evaluationPrice has more than eight digits<BR>
     *  2001 : Specification overlaps（productCode、marketCode、buyPrice  The same
     * details exist. ）<BR>
     */
    public String errorCode;

    /**
     * @@roseuid 41C81B1A003F
     */
    public WEB3TrialCalcPortfolioDisplayProductUnit()
    {

    }
}
@
