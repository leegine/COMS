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
filename	WEB3AdminFPTDocumentListReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付書面照会一覧レスポンス(WEB3AdminFPTDocumentListReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 馮海濤 (中訊) 新規作成 モデルNo.037
*/
package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者金商法@交付書面照会一覧レスポンス)<BR>
 * 管理者金商法@交付書面照会一覧レスポンスクラス<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentListReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_document_list_reference";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200803031755L;

    /**
     * (書面種類一覧)<BR>
     * 書面種類一覧<BR>
     */
    public WEB3FPTDocumentUpdateInfoUnit[] documentCategoryList;

    /**
     * @@roseuid 47CBC5AE01F4
     */
    public WEB3AdminFPTDocumentListReferenceResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFPTDocumentListReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
