head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccOpenSendMailStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設完了メール送信ステータス定数定義インタフェイス(WEB3AccOpenSendMailStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/31 孟暁シン(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 口座開設完了メール送信ステータス 定数定義インタフェイス
 *
 * @@author 孟暁シン(中訊)
 * @@version 1.0
 */
public interface WEB3AccOpenSendMailStatusDef
{
    /**
     * 0：未処理（Email未送信）
     */
    public final static String EMAIL_NOT_SEND = "0";

    /**
     * 1：処理済（Email送信済）
     */
    public final static String EMAIL_SENDED = "1";
}
@
