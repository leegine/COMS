head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcBuyAmountComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス買付代金Comparator(WEB3TrialCalcBuyAmountComparator.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc;

import java.math.BigDecimal;
import java.util.Comparator;

import webbroker3.util.WEB3LogUtility;

import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayProductUnit;

/**
 * （計算サービス買付代金Comparator）<BR>
 * <BR>
 * 計算サービス買付代金Comparatorクラス。<BR>
 * <BR>
 * WEB3TrialCalcBuyAmountComparator<BR>
 * @@author Dixit Deepankar
 * @@version 1.0
 */
public class WEB3TrialCalcBuyAmountComparator implements Comparator
{
    /**
     * Variable for Log
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcBuyAmountComparator.class);

    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順<BR>
     * 　@D：降順<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Flag that specifies ascending order (:asc) and descending order (:desc). <BR>
     * <BR>
     *  A : Ascend<BR>
     *  D : Descend<BR>
     * <BR>
     * <BR>
     */
    private String orderBy;

    /**
     * @@roseuid 41E3C565012C
     */
    public WEB3TrialCalcBuyAmountComparator()
    {

    }

    /**
     * （計算サービス買付代金Comparator）<BR>
     * <BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数.orderByをthis.orderByにセットする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Constructor<BR>
     * Set argument.orderBy to this.orderBy<BR>
     * <BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順<BR>
     * 　@D：降順<BR>
     * @@roseuid 41986BD50050
     */
    public WEB3TrialCalcBuyAmountComparator(String l_strOrderBy)
    {
        this.orderBy = l_strOrderBy;
    }

    /**
     * （compareの実装）<BR>
     * <BR>
     * 買付代金の比較を行う。<BR>
     * <BR>
     * １）　@引数のcast<BR>
     * 　@引数の銘柄明細1、銘柄明細2を、計算サービスポートフォリオ表示銘柄明細型にcastす
     * る。<BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastした銘柄明細1、銘柄明細2について、以下の判定を行う。<BR>
     * 　@※銘柄明細1.買付代金 == nullの場合は、銘柄明細1.買付代金を0として扱う。<BR>
     * 　@※銘柄明細2.買付代金 == nullの場合は、銘柄明細2.買付代金を0として扱う。<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（銘柄明細1.買付代金 <
     * 銘柄明細2.買付代金）の場合、負の整数（-1）を返却する。 <BR>
     * 　@・（銘柄明細1.買付代金 == 銘柄明細2.買付代金）の場合、0を返却する。 <BR>
     * 　@・（銘柄明細1.買付代金 >
     * 銘柄明細2.買付代金）の場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（銘柄明細1.買付代金 <
     * 銘柄明細2.買付代金）の場合、正の整数（1）を返却する。 <BR>
     * 　@・（銘柄明細1.買付代金 == 銘柄明細2.買付代金）の場合、0を返却する。 <BR>
     * 　@・（銘柄明細1.買付代金 >
     * 銘柄明細2.買付代金）の場合、負の整数（-1）を返却する。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * buyAmount compare<BR>
     * <BR>
     * 1) Argument <BR>
     *  1-1) Cast argument l_productDetails1, l_productDetails2 into <BR>
     *       WEB3TrialCalcPortfolioDisplayProductUnit format<BR>
     * <BR>
     * 2) Compare <BR>
     * 　@１）Compare the following whith the arguments cast at 1-1)<BR>
      * If "l_productDetails1.buyAmount == null"<BR>
     *        l_productDetails1.buyAmount = 0<BR>
      * If "l_productDetails2.buyAmount == null"<BR>
     *        l_productDetails2.buyAmount = 0<BR>
     * <BR>
     * 　@[Ascending order specification (this.orderBy == "Ascend"）] <BR>
      *If "l_productDetails1.buyAmount < l_productDetails2.buyAmount"<BR>
     *       return '-1'<BR>
      *If "l_productDetails1.buyAmount == l_productDetails2.buyAmount"<BR>
     *       return '0'<BR>
      *If "l_productDetails1.buyAmount > l_productDetails2.buyAmount"<BR>
     *       return '1'<BR>
     * <BR>
     * 　@[Descending order specification（this.orderBy == ”Descend”）] <BR>
     * 　@・If "l_productDetails1.buyAmount < l_productDetails2.buyAmount"<BR>
     *      return '1'<BR>
     * 　@・If "l_productDetails1.buyAmount == l_productDetails2.buyAmount"<BR>
     *      return '0'<BR>
     * 　@・If "l_productDetails1.buyAmount > l_productDetails2.buyAmount"<BR>
     *      return '-1'<BR>
     * <BR>
     * @@param l_productDetails1 - 計算サービスポートフォリオ表示銘柄明細オブジェクト。
     * @@param l_productDetails2 - 計算サービスポートフォリオ表示銘柄明細オブジェクト。
     * @@return int
     * @@roseuid 41986BD50052
     */
    public int compare(Object l_productDetails1, Object l_productDetails2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcPortfolioDisplayProductUnit l_displayProductUnit1 =
            (WEB3TrialCalcPortfolioDisplayProductUnit) l_productDetails1;
        WEB3TrialCalcPortfolioDisplayProductUnit l_displayProductUnit2 =
            (WEB3TrialCalcPortfolioDisplayProductUnit) l_productDetails2;

        String l_strBuyAmount1 = l_displayProductUnit1.buyAmount;
        String l_strBuyAmount2 = l_displayProductUnit2.buyAmount;

        // Check l_strBuyAmount1 for null , empty String
        if (l_strBuyAmount1 == null || "".equals(l_strBuyAmount1))
        {
            l_strBuyAmount1 = "0";
        }

        // Check l_strBuyAmount2 for null , empty String
        if (l_strBuyAmount2 == null || "".equals(l_strBuyAmount2))
        {
            l_strBuyAmount2 = "0";
        }

        BigDecimal l_bdBuyAmount1 = new BigDecimal(l_strBuyAmount1);
        BigDecimal l_bdBuyAmount2 = new BigDecimal(l_strBuyAmount2);

        // Check Ascending order specification (this.orderBy == "Ascend")
        if ("A".equals(this.orderBy))
        {
            log.exiting(STR_METHOD_NAME);
            return l_bdBuyAmount1.compareTo(l_bdBuyAmount2);
        } else
        {
            // Check Descending order specification（this.orderBy == ”Descend”）
            log.exiting(STR_METHOD_NAME);
            return l_bdBuyAmount2.compareTo(l_bdBuyAmount1);
        }
    }

    /**
     * （equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。 <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Unused .  False is returned. <BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 41986BD50060
     */
    public boolean equals(Object l_arg0)
    {
        final String STR_METHOD_NAME = "equals(Object)";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return false;
    }
}@
