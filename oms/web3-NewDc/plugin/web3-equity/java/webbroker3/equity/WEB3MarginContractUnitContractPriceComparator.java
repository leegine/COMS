head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractUnitContractPriceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建株明細_建単価Comparator(WEB3MarginContractUnitContractPriceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/20 中尾寿彦 (SRA) 新規作成
*/

package webbroker3.equity;

import java.util.Comparator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.common.define.WEB3AscDescDef;

/**
 * （建株明細_建単価Comparator）。<BR>
 * <BR>
 * 建株明細_建単価Comparator
 * @@author 中尾寿彦
 * @@version 1.0
 */
public class WEB3MarginContractUnitContractPriceComparator implements Comparator 
{
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3MarginContractUnitContractPriceComparator.class);
    
    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;
    
    /**
     * (建株明細_建単価Comparator)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数.orderByをthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@return WEB3MarginContractUnitContractPriceComparator
     */
    public WEB3MarginContractUnitContractPriceComparator(String l_strOrderBy) 
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy) &&
            !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException(
                "パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * (compare)<BR>
     * （compareの実装）<BR>
     * <BR>
     * 建単価の比較を行う。<BR>
     * <BR>
     * １）　@引数のcast<BR>
     * 　@引数の建株明細1、建株明細2を、信用取引建株明細型にcastする。<BR>
     * <BR>
     * ３）　@比較<BR>
     * 　@２）でcastした建株明細1、建株明細2について<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）]<BR>
     * 　@・（建株明細1.建単価 ＜ 建株明細2.建単価）の場合、負の整数（-1）を返却する。<BR>
     * 　@・（建株明細1.建単価 == 建株明細2.建単価）の場合、0を返却する。<BR>
     * 　@・（建株明細1.建単価 ＞ 建株明細2.建単価）の場合、正の整数（1）を返却する。<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）]<BR>
     * 　@・（建株明細1.建単価 ＜ 建株明細2.建単価）の場合、正の整数（1）を返却する。<BR>
     * 　@・（建株明細1.建単価 == 建株明細2.建単価）の場合、0を返却する。<BR>
     * 　@・（建株明細1.建単価 ＞ 建株明細2.建単価）の場合、負の整数（-1）を返却する。<BR>
     * <BR>
     * @@param l_objFirst - (建株明細1)<BR>
     * 信用取引建株明細オブジェクト
     * @@param l_objSecond - (建株明細2)<BR>
     * 信用取引建株明細オブジェクト
     * @@return int
     */
    public int compare(Object l_objFirst, Object l_objSecond) 
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblFirstContractPrice = 0D;
        double l_dblSecondContractPrice = 0D;
        if (l_objFirst instanceof WEB3MarginContractUnit &&
            l_objSecond instanceof WEB3MarginContractUnit)
        {
            l_dblFirstContractPrice =
                Double.parseDouble(((WEB3MarginContractUnit)l_objFirst).contractPrice);
            l_dblSecondContractPrice =
                Double.parseDouble(((WEB3MarginContractUnit)l_objSecond).contractPrice);            
        }
        else
        {
            throw new IllegalArgumentException(
                "パラメータは信用取引建株明細クラスでありません。");
        }
        
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //昇順指定の場合
            if (l_dblFirstContractPrice < l_dblSecondContractPrice)
            {
                return -1;
            }
            else if (l_dblFirstContractPrice == l_dblSecondContractPrice)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            //降順指定の場合
            if (l_dblFirstContractPrice < l_dblSecondContractPrice)
            {
                return 1;
            }
            else if (l_dblFirstContractPrice == l_dblSecondContractPrice)
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }
    }
    
    /**
     * (equals)
     * （equalsの実装）<BR>
     * <BR>
     * 未使用。<BR> 
     * falseを返却する。<BR>
     * <BR>
     * @@param l_object - (比較対照のオブジェクト)<BR>
     * @@return boolean
     */
    public boolean equals(Object l_object)
    {
        return false;
    }
}
@
