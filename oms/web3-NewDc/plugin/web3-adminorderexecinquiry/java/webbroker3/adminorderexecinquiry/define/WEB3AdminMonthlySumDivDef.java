head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.34.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMonthlySumDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminMonthlySumDivDef(WEB3AdminMonthlySumDivDef.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.define;

/**
 * WEB3AdminMonthlySumDivDef
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public interface WEB3AdminMonthlySumDivDef
{
    /**
     * PAST_THREE_MONTH
     */
    public final static String PAST_THREE_MONTH = "0";

    /**
     * PAST_SIX_MONTH
     */
    public final static String PAST_SIX_MONTH = "1";

    /**
     * PAST_TWELVE_MONTH
     */
    public final static String PAST_TWELVE_MONTH = "2";
}@
