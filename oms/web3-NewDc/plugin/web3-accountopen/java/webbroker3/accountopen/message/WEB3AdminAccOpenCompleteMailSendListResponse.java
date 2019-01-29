head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.01.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設完了メール送信一覧レスポンス(WEB3AdminAccOpenCompleteMailSendListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 張学剛 新規作成
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設完了メール送信一覧レスポンス)<BR>
 * 管理者口座開設完了メール送信一覧レスポンス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_completeMailSendList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081609L;

    /**
     * (部店コード)<BR>
     * ＭＬ送信対象の部店コード配列<BR>
     */
    public String[] branchCode;

    /**
     * (顧客コード)<BR>
     * ＭＬ送信対象の顧客コード配列<BR>
     */
    public String[] accountCode;

    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;

    /**
     * @@roseuid 41B45E75035B
     */
    public WEB3AdminAccOpenCompleteMailSendListResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenCompleteMailSendListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
