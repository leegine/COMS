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
filename	WEB3AdminFPTUploadCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧アップロード完了リクエスト(WEB3AdminFPTUploadCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 武波 (中訊) 新規作成 モデル No.013
*/

package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.define.WEB3FPTStatusDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者金商法@交付閲覧アップロード完了リクエスト)<BR>
 * 管理者金商法@交付閲覧アップロード完了リクエスト<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTUploadCompleteRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_upload_complete";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200712071142L;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadCompleteRequest.class);

    /**
     * (アップロードID)<BR>
     * アップロードID<BR>
     */
    public String uploadId;

    /**
     * (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 0：登録<BR>
     * 1：削除<BR>
     */
    public String statusDiv;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * @@roseuid 4758B278035B
     */
    public WEB3AdminFPTUploadCompleteRequest()
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
        return new WEB3AdminFPTUploadCompleteResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １）　@アップロードIDのチェック<BR>
     * 　@１－１）　@未入力の場合、「アップロードIDが未指定です。」<BR>
     * 　@　@　@　@　@　@（BUSINESS_ERROR_00973）例外をスローする。<BR>
     * 　@　@ class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@:　@BUSINESS_ERROR_00973<BR>
     * <BR>
     * ２）　@処理区分のチェック<BR>
     * 　@２-１）　@未入力の場合、「処理区分が未指定です。」<BR>
     * 　@　@　@　@　@　@（BUSINESS_ERROR_01249）例外をスローする。<BR>
     * 　@　@ class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@:　@BUSINESS_ERROR_01249<BR>
     * <BR>
     * 　@２-２）　@0 または1以外の場合、「処理区分の値が存在しないコード値です。」<BR>
     * 　@　@　@　@　@（BUSINESS_ERROR_01250）例外をスローする。<BR>
     * 　@　@ class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@:　@BUSINESS_ERROR_01250<BR>
     * @@throws WEB3BaseException
     * @@roseuid 473814FD023F
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

        //２）　@処理区分のチェック
        //２-１）　@未入力の場合、「処理区分が未指定です。」
        //（BUSINESS_ERROR_01249）例外をスローする。
        if (this.statusDiv == null)
        {
            log.debug("処理区分が未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分が未指定です。");
        }

        //２-２）　@0 または1以外の場合、「処理区分の値が存在しないコード値です。」
        //（BUSINESS_ERROR_01250）例外をスローする。
        if (!(WEB3FPTStatusDivDef.LOGIN.equals(this.statusDiv)
            || WEB3FPTStatusDivDef.DELETE.equals(this.statusDiv)))
        {
            log.debug("処理区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分の値が存在しないコード値です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
