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
filename	WEB3AdminFPTUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧アップロード確認レスポンス(WEB3AdminFPTUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 武波 (中訊) 新規作成 モデル No.013
*/

package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者金商法@交付閲覧アップロード確認レスポンス)<BR>
 * 管理者金商法@交付閲覧アップロード確認レスポンス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTUploadConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_upload_confirm";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200712071148L;

    /**
     * (アップロード件数)<BR>
     * アップロード件数<BR>
     */
    public String uploadNumber;

    /**
     * (アップロードID)<BR>
     * アップロードID<BR>
     */
    public String uploadId;

    /**
     * @@roseuid 4758B278030D
     */
    public WEB3AdminFPTUploadConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFPTUploadConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
