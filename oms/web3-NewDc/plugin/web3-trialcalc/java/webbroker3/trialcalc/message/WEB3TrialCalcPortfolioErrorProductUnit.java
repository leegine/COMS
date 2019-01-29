head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioErrorProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオエラー銘柄明細(WEB3TrialCalcPortfolioErrorProductUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （計算サービスポートフォリオエラー銘柄明細）<BR>
 * <BR>
 * 計算サービスポートフォリオエラー銘柄明細。<BR>
 * <BR>
 * WEB3TrialCalcPortfolioErrorProductUnit<BR>
 * @@author Prabhu
 * @@version 1.0
 * <BR>
 */
public class WEB3TrialCalcPortfolioErrorProductUnit extends Message
{
    /**
     * （銘柄コード）<BR>
     * <BR>
     * Product Code<BR>
     */
    public String productCode;

    /**
     * （銘柄名）<BR>
     * <BR>
     * Product Name<BR>
     */
    public String productName;

    /**
     * （株数）<BR>
     * <BR>
     * Order Quantity<BR>
     */
    public String orderQuantity;

    /**
     * （買単価）<BR>
     * （null指定可）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Buy price.(Null can be specified.)<BR>
     * <BR>
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
     * <BR>
     */
    public String marketCode;

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
     * @@roseuid 41C81B36009D
     */
    public WEB3TrialCalcPortfolioErrorProductUnit()
    {

    }
}
@
