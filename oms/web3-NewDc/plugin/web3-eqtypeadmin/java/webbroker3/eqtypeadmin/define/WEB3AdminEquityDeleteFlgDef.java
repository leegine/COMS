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
filename	WEB3AdminEquityDeleteFlgDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminEquityDeleteFlgDef(WEB3AdminEquityDeleteFlgDef.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.define;

/**
 * WEB3AdminEquityDeleteFlgDef <BR>
 * @@author Manju Sagar
 * @@version 1.0
 */
public interface WEB3AdminEquityDeleteFlgDef
{

    /**
     * 未削除
     */
    public final static String NOT_DELETE = "0";

    /**
     * 削除
     */
    public final static String DELETE = "1";
}
@
