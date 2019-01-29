head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFSonarSendCheckDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : sonar送信チェック 定数定義インタフェイス(WEB3MFSonarSendCheckDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/08/08 SRA太田 新規作成
*/
package webbroker3.mf.define;

/**
 * sonar送信チェック 定数定義インタフェイス
 * @@author SRA太田
 * @@version 1.0
 */
public interface WEB3MFSonarSendCheckDivDef
{
    /**
     * sonar送信可能性なし
     */
    public static final String SEND_NO_POSSIBILITY = "0";

    /**
     * sonar送信可能性あり
     */
    public static final String SEND_POSSIBILITY = "1";
}

@
