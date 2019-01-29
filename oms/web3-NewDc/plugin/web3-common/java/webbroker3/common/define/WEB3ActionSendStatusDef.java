head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ActionSendStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 履歴送信状態定数定義インタフェイス(WEB3ActionSendStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/27 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 履歴送信状態定数定義インタフェイス<BR>
 * （ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴ﾃｰﾌﾞﾙの履歴送信状態の参考用）<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public interface WEB3ActionSendStatusDef
{
    /**
     * 0：DEFAULT（初期値）　@
     */
    public final static String DEFAULT = "0";

    /**
     * 1：SONAR送信済　@　@　@　@ 　@　@　@ 　@　@
     */
    public final static String SONAR_MAIL_SENDED = "1";
}
@
