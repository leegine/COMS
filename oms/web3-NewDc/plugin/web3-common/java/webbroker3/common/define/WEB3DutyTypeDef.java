head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DutyTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÅíÞ@@èè`C^tFCX(WEB3TaxTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)@@VKì¬
Revesion History : 2007/07/26 hC (u) caCAEg(Å¦e[u)Éæé
Revesion History : 2008/04/11 hC(u) ¤ÊdlÏXÇä EcaCAEgNo621
*/
package webbroker3.common.define;

/**
 * ÅíÞ@@èè`C^tFCX
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3DutyTypeDef
{
    /**
     * 10 : ÁïÅ
     */
    public static final String CONSUMPTION_TAX = "10";

    /**
     * 20 : ÷nvÅ
     */
    public static final String CPITAL_GAIN_TAX = "20";

    /**
     * 21 : ÷nvÅiÅj
     */
    public static final String CPITAL_GAIN_TAX_NATIONAL = "21";

    /**
     * 22 : ÷nvÅinûÅj
     */
    public static final String CPITAL_GAIN_TAX_LOCAL = "22";

    /**
     * 30 : M¹ò¥ûi®^EÅj
     */
    public final static String MF_WITHHOLDING_STOCK_NATIONAL_TAX = "30";

    /**
     * 40 : M¹ò¥ûiÂ^EÅj
     */
    public final static String MF_WITHHOLDING_BOND__NATIONAL_TAX = "40";

    /**
     * 50 : M¹ò¥ûi®^EnûÅj
     */
    public final static String MF_WITHHOLDING_STOCK_LOCAL_TAX = "50";

    /**
     * 60 : M¹ò¥ûiÂ^EnûÅj
     */
    public final static String MF_WITHHOLDING_BOND_LOCAL_TAX = "60";

    /**
     * 70 : àÂ¹ò¥û¦
     */
    public final static String DOMESTIC_BOND_WITHHOLDING_TAX = "70";
}
@
