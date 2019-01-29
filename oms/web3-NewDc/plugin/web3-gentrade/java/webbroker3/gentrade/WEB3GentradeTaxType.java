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
filename	WEB3GentradeTaxType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 税種類定数定義クラス(WEB3GentradeTaxType.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/04 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.gentrade;

/**
 * （税種類）<BR>
 *<BR>
 * 税種類の定数を定義する。<BR>
 *<BR>
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public interface WEB3GentradeTaxType
{

    /**
     * 消費税を示すコード値<BR>
     */
    public final static String CONSUMPTION_TAX = "10";

    /**
     * 譲渡益税を示すコード値<BR>
     */
    public final static String CAPITAL_GAIN_TAX = "20";

    /**
     * 投信源泉徴収（株式型・国税）を示すコード値<BR>
     */
    public final static String MF_WITHHOLDING_STOCK_NATIONAL_TAX = "30";

    /**
     * 投信源泉徴収（債券型・国税）を示すコード値<BR>
     */
    public final static String MF_WITHHOLDING_BOND__NATIONAL_TAX = "40";

    /**
     * 投信源泉徴収（株式型・地方税）を示すコード値<BR>
     */
    public final static String MF_WITHHOLDING_STOCK_LOCAL_TAX = "50";

    /**
     * 投信源泉徴収（債券型・地方税）を示すコード値<BR>
     */
    public final static String MF_WITHHOLDING_BOND_LOCAL_TAX = "60";
}
@
