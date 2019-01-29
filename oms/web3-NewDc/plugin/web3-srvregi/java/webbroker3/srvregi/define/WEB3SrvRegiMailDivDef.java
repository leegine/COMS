head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiMailDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール区分(WEB3SrvRegiMailDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵(sinocom) 新規作成
*/
package webbroker3.srvregi.define;

/**
 * メール区分
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SrvRegiMailDivDef
{
    /**
     * 0：送信しない  　@
     */
    public final static String NOT_SEND_MAIL = "0";

    /**
     * 1：送信する　@
     */
    public final static String MAIL_SENDED = "1";

}
@
