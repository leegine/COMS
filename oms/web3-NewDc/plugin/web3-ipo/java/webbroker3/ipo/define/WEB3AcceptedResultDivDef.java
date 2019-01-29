head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AcceptedResultDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO受付結果区分(WEB3AcceptedResultDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/25 鄭海良(sinocom) 新規作成
*/
package webbroker3.ipo.define;

/**
 * IPO受付結果区分
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3AcceptedResultDivDef
{

    /**
     * 1：   有効
     */
    public final static String VALID = "1";

    /**
     * 2：   無効
     */
    public final static String INVALID = "2";
    
}
 @
