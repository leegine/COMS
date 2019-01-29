head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MFStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 発注区分定数定義インタフェイス(WEB3MFStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 発注区分定数定義インタフェイス<BR>
 * （定時定額買付条件変更テーブルの変更区分の参考用）<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public interface WEB3MFStatusDef
{
    /**
     * 1:SONAR未送信
     */
    public final static String SONAR_NOT_SEND = "1";

    /**
     * 2:SONAR送信済
     */
    public final static String SONAR_SENDED = "2";

    /**
     * 3:SONAR反映済
     */
    public final static String SONAR_REFLECTED = "3";

    /**
     * 4:SONAR送信対象外
     */
    public final static String SONAR_SEND_EXCEPT_OBJECT = "4";
}@
