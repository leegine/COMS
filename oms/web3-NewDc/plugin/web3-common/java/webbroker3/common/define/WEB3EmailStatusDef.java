head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EmailStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3EmailStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 電子メイル送信ステイタス　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3EmailStatusDef
{
    /**
     * 0 : 未処理（Email未送信）　@
     */
    public static final String EMAIL_NOT_SEND = "0";

    /**
     * 1 : 処理済（Email送信済）
     */
    public static final String EMAIL_SEND_END = "1";

}
@
