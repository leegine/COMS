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
filename	WEB3AdminFPTDocumentUpdateInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付書面更新入力レスポンス(WEB3AdminFPTDocumentUpdateInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 馮海濤 (中訊) 新規作成 モデルNo.037
*/
package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者金商法@交付書面更新入力レスポンス)<BR>
 * 管理者金商法@交付書面更新入力レスポンスクラス<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentUpdateInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_document_update_input";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200803031811L;

    /**
     * (書面区分管理一覧)<BR>
     * 書面区分管理一覧<BR>
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;

    /**
     * @@roseuid 47CBC5AD037A
     */
    public WEB3AdminFPTDocumentUpdateInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFPTDocumentUpdateInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
