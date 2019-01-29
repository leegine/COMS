head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.34.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminSumProductTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminSumProductTypeDef(WEB3AdminSumProductTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/11 齊珂 (中訊) 仕様変更No.55修正
*/
package webbroker3.adminorderexecinquiry.define;

/**
 * WEB3AdminSumProductTypeDef
 * <BR>
 * @@author sudhindrakinnal
 * @@version 1.0
 */
public interface WEB3AdminSumProductTypeDef
{
    /**
     * EQUITY
     */
    public final static String EQUITY = "0";

    /**
     * MARGIN
     */
    public final static String MARGIN = "1";

    /**
     * MINI_STOCK
     */
    public final static String MINI_STOCK = "2";

    /**
     * FORIGN_STOCK
     */
    public final static String FORIGN_STOCK = "3";

    /**
     * OPTION
     */
    public final static String OPTION = "4";

    /**
     * FUTURE
     */
    public final static String FUTURE = "5";

    /**
     * MF
     */
    public final static String MF = "6";

    /**
     * MIDIUM_TERM_GOV_FUND
     */
    public final static String MIDIUM_TERM_GOV_FUND = "7";

    /**
     * MMF_SET
     */
    public final static String MMF_SET = "8";

    /**
     * BOND
     */
    public final static String BOND = "9";
}@
