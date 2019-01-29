head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.34.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminProductDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminProductDivDef(WEB3AdminProductDivDef.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.define;

/**
 * WEB3AdminProductDivDef
 * <BR>
 * @@author sudhindrakinnal
 * @@version 1.0
 */
public interface WEB3AdminProductDivDef
{
    /**
     *現物株式
     */
    public final static String EQUITY = "0";

    /**
     *信用取引
     */
    public final static String MARGIN = "1";

    /**
     *株式ミニ投資
     */
    public final static String MINI_STOCK = "2";

    /**
     *オプション
     */
    public final static String OPTION = "3";

    /**
     *先物
     */
    public final static String FUTURE = "4";

    /**
     *投信
     */
    public final static String MF = "5";

    /**
     *累投
     */
    public final static String RUITO = "6";
    
    /**
     *外国株式
     */
    public final static String FEQ = "7";
}@
