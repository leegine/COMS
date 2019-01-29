head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueRecordDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー発行レコード詳細(WEB3AdminDirSecTriggerIssueRecordDetail.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 許丹(中訊) 新規作成 モデルNo.116
*/
package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (トリガー発行レコード詳細)<BR>
 * トリガー発行機@能・各画面で表示される値を保持するクラス。<BR>
 * <BR>
 * @@author 許丹
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueRecordDetail extends Message
{

    /**
     * (シェル名称)<BR>
     * シェル名称を保持。<BR>
     * トリガー発行情報テーブル：「シェル名称」から取得<BR>
     */
    public String shellName;

    /**
     * (トリガー名称)<BR>
     * トリガー名称を保持。<BR>
     * トリガー発行情報テーブル：「トリガー名称」から取得<BR>
     */
    public String triggerName;

    /**
     * (再発行可能フラグ)<BR>
     * 再発行可能フラグを保持。<BR>
     * トリガー発行情報テーブル：「再発行可能フラグ」より取得<BR>
     */
    public String reissuePossibleFlag;

    /**
     * (ユーザーデータ)<BR>
     * ユーザーデータを保持。<BR>
     * トリガー発行情報テーブル：「ユーザーデータ」から取得<BR>
     */
    public String userData;

    /**
     * (データコード)<BR>
     * データコードを保持。<BR>
     * トリガー発行情報テーブル：「データコード」から取得<BR>
     */
    public String dataCode;

    /**
     * @@roseuid 4806E05303DA
     */
    public WEB3AdminDirSecTriggerIssueRecordDetail()
    {

    }
}
@
