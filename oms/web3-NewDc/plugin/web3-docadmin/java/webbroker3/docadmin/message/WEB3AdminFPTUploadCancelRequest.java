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
filename	WEB3AdminFPTUploadCancelRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧アップロード中止リクエスト(WEB3AdminFPTUploadCancelRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 武波 (中訊) 新規作成 モデル No.013
*/

package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者金商法@交付閲覧アップロード中止リクエスト)<BR>
 * 管理者金商法@交付閲覧アップロード中止リクエスト<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTUploadCancelRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_upload_cancel";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200712071136L;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadCancelRequest.class);

    /**
     * (アップロードID)<BR>
     * アップロードID<BR>
     */
    public String uploadId;

    /**
     * @@roseuid 4758B2790000
     */
    public WEB3AdminFPTUploadCancelRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFPTUploadCancelResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １）　@アップロードIDのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@ class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@:　@BUSINESS_ERROR_00973<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4738152603B9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@アップロードIDのチェック
        //１－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.uploadId))
        {
            log.debug("アップロードIDが未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "アップロードIDが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
