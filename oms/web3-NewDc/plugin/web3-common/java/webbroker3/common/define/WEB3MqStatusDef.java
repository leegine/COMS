head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MqStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : MQステータス(WEB3MqStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * MQステータス
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3MqStatusDef
{

    /**
     * 0:未送信 　@　@
     */
    public final static String NOT_SEND_MAIL = "0";

    /**
     * 1:送信済み　@
     */
    public final static String MAIL_SENDED = "1";
    
}
@
