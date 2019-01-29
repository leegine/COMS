head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧検索入力レスポンス(WEB3AdminFPTSearchInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 武波 (中訊) 新規作成
*/

package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者金商法@交付閲覧検索入力レスポンス)<BR>
 * 管理者金商法@交付閲覧検索入力レスポンスクラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTSearchInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_search_input";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709291412L;

    /**
     * (書面区分管理一覧)<BR>
     * 書面区分管理一覧<BR>
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;

    /**
     * @@roseuid 46FDDD3900EA
     */
    public WEB3AdminFPTSearchInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFPTSearchInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
