head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3HostSendDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : host送信区分定数定義インタフェイス(WEB3HostSendDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * host送信区分　@定数定義インタフェイス
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3HostSendDivDef
{
    /**
     * 0：未送信
     */
    public static final String UNSEND = "0";

    /**
     * 1：送信済
     */
    public static final String SENDED = "1";

    /**
     * 2:送信不要
     */
    public static final String NOT_SEND = "2";
}
@
