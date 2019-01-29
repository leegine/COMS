head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・トリガー発行処理一覧レスポンス(WEB3AdminDirSecTriggerIssueListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 許丹(中訊) 新規作成 モデルNo.116、No.118
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・トリガー発行処理一覧レスポンス)<BR>
 * 管理者・トリガー発行処理一覧レスポンスクラス。<BR>
 * <BR>
 * @@author 許丹
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_dir_sec_trigger_issue_list";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200804171506L;

    /**
     * (トリガー発行情報一覧)<BR>
     * 「トリガー発行情報テーブル」から取得したトリガー発行JOBレコードを保持する<BR>
     */
    public WEB3AdminDirSecTriggerIssueRecordDetail[] triggerIssueInfo;

    /**
     * (総ページ数)<BR>
     * 総ページ数。<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 総レコード数。<BR>
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号。<BR>
     */
    public String pageIndex;

    /**
     * @@roseuid 4806E054007F
     */
    public WEB3AdminDirSecTriggerIssueListResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3AdminDirSecTriggerIssueListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
