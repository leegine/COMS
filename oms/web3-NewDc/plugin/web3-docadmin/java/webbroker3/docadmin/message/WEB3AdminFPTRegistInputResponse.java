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
filename	WEB3AdminFPTRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧登録入力レスポンス(WEB3AdminFPTRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 武波 (中訊) 新規作成
Revision History : 2008/01/25 周墨洋 (中訊) 仕様変更・モデルNo.022
*/

package webbroker3.docadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者金商法@交付閲覧登録入力レスポンス)<BR>
 * 管理者金商法@交付閲覧登録入力レスポンスクラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTRegistInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_regist_input";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709291415L;

    /**
     * (現在日付)<BR>
     * 現在日付<BR>
     */
    public Date currentDate;

    /**
     * (書面種類一覧)<BR>
     * 書面種類一覧<BR>
     */
    public WEB3FPTDocumentCategoryDetailsInfoUnit[] documentCategoryList;

    /**
     * @@roseuid 46FDDD3B0251
     */
    public WEB3AdminFPTRegistInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFPTRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
