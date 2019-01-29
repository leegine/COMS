head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・トリガー発行処理入力リクエスト(WEB3AdminDirSecTriggerIssueInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 許丹(中訊) 新規作成 モデルNo.116、No.118
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・トリガー発行処理入力リクエスト)<BR>
 * 管理者・トリガー発行処理入力リクエストクラス。<BR>
 * <BR>
 * @@author 許丹
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dir_sec_trigger_issue_input";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804171448L;

    /**
     * @@roseuid 4806E05400CD
     */
    public WEB3AdminDirSecTriggerIssueInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecTriggerIssueInputResponse(this);
    }
}
@
