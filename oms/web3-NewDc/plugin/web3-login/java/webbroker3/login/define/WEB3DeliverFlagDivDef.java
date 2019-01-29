head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.21.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3DeliverFlagDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 交付済フラグ定数定義(WEB3DeliverFlagDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/28 謝旋(中訊) 新規作成
*/

package webbroker3.login.define;

/**
 * 交付済フラグ定数定義
 *
 * @@author 謝旋(中訊)
 * @@version 1.0
 */
public interface WEB3DeliverFlagDivDef
{
    /**
     * 0：交付未済
     */
    public final static String UNDELIVERY = "0";

    /**
     * 1：交付済
     */
    public final static String DELIVERY = "1";

    /**
     * 2：交付不要
     */
    public final static String NO_DELIVERY = "2";
}
@
