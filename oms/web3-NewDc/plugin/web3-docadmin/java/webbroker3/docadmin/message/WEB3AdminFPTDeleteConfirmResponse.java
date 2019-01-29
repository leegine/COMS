head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTDeleteConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧削除確認レスポンス(WEB3AdminFPTDeleteConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/06 武波 (中訊) 新規作成 仕様変更・モデル No.011
*/

package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者金商法@交付閲覧削除確認レスポンス)<BR>
 * 管理者金商法@交付閲覧削除確認レスポンス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTDeleteConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_delete_confirm";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200711061018L;

    /**
     * @@roseuid 472FC5B5022A
     */
    public WEB3AdminFPTDeleteConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFPTDeleteConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
