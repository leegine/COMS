head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInvestmentExperienceDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資経験年数(WEB3AccOpenInvestmentExperienceDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 張威 (中訊) 新規作成
*/

package webbroker3.accountopen.define;

/**
 * 投資経験年数
 * 
 * @@author 張威(中訊)
 * @@version 1.0
 */
public interface WEB3AccOpenInvestmentExperienceDef
{
    /**
     * 0:  なし
     */
    public static final String NO_EXPERIENCE = "0";

    /**
     * 1:  １年
     */
    public static final String ONE_YEAR = "1";

    /**
     * 2:  ２年
     */
    public static final String TWO_YEARS = "2";

    /**
     * 3:  ３年
     */
    public static final String THREE_YEARS = "3";

    /**
     * 4:  ４年
     */
    public static final String FOUR_YEARS = "4";

    /**
     * 5:  ５年
     */
    public static final String FIVE_YEARS = "5";

    /**
     * null:  ５年以上
     */
    public static final String MORE_THAN_FIVE_YEARS = null;
}
@
