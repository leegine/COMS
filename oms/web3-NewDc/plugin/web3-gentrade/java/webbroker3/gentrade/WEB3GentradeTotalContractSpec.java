head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTotalContractSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÊWvSpec(WEB3GentradeTotalContractSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/24 ç¾­ (u) VKì¬
*/
package webbroker3.gentrade;

/**
 * (ÊWvSpec)
 * ÊWvÌîñð\·NXB
 */
public class WEB3GentradeTotalContractSpec
{

    /**
     * Á¿R[h
     */
    public String productCode;

    /**
     * Ê
     */
    public double marginLongNum;

    /**
     * Ê
     */
    public double marginShortNum;

    /**
     * RXgN^B<BR>
     * øÌlð¯vpeBÉZbg·éB<BR>
     * @@param l_strProductCode - Á¿R[h
     * @@param l_dblMarginLongNum - Ê
     * @@param l_dblMarginShortNum - Ê
     * @@roseuid 412D70BB02BE
     */
    public WEB3GentradeTotalContractSpec(
        String l_strProductCode,
        double l_dblMarginLongNum,
        double l_dblMarginShortNum)
    {
        this.productCode = l_strProductCode;
        this.marginLongNum = l_dblMarginLongNum;
        this.marginShortNum = l_dblMarginShortNum;
    }
}
@
