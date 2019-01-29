head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3QuoteTypeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価区分  定数定義インタフェイス(WEB3QuoteTypeDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01　@沢村仁士 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 時価区分 定数定義インタフェイス<BR>
 * <BR>
 * @@author 沢村仁士(SRA)
 * @@version 1.0
 */
public interface WEB3QuoteTypeDivDef
{

    /**
     *  1：現在値<BR>
     */
    public static final String CURRENT_PRICE = "1";

    /**
     *  2：売気配値<BR>
     */
    public static final String BID_PRICE = "2";

    /**
     *  3：買気配値<BR>
     */
    public static final String ASK_PRICE = "3";

    /**
     *  4：前日終値<BR>
     */
    public static final String LAST_CLOSING_PRICE = "4";
}
@
