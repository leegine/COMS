head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.52.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b065d184d;
filename	WEB3ReportRegDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : 報告書申込区分定数定義インタフェイス(WEB3ReportRegDiv.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/10 劉レイ(北京中訊) 新規作成
 */
package webbroker3.common.define;

/**
 * (報告書申込区分)<BR>
 * 電子交付管理テーブルの報告書申込区分<BR>
 * <BR>
 * @@author 劉レイ
 * @@version 1.0
 */
public interface WEB3ReportRegDivDef
{
    /**
     * 0:申込中
     */
    public static final String APPLYING = "0";

    /**
     * 1:申込完了
     */
    public static final String APPLIED = "1";

    /**
     * 2:取消完了
     */
    public static final String CANCELLED = "2";

    /**
     * 9:SONAR送信済
     */
    public static final String SONAR_MAIL_SENDED = "9";
}
@
