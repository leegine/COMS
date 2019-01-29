head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3QuoteFromDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価取得区分  定数定義インタフェイス(WEB3QuoteFromDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/10　@中尾寿彦 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 時価取得区分 定数定義インタフェイス<BR>
 * <BR>
 * @@author 中尾寿彦(SRA)
 * @@version 1.0
 */
public interface WEB3QuoteFromDivDef
{

    /**
     *  1：時価<BR>
     */
    public static final String MARKET_PRICE = "1";

    /**
     *  2：前日終値<BR>
     */
    public static final String LAST_CLOSING_PRICE = "2";

    /**
     *  3：当日終値<BR>
     */
    public static final String CLOSING_PRICE = "3";
}
@
