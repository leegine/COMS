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
filename	WEB3AdminFPTUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧アップロード確認リクエスト(WEB3AdminFPTUploadConfirmRequest.java)
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


/**
 * (管理者金商法@交付閲覧アップロード確認リクエスト)<BR>
 * 管理者金商法@交付閲覧アップロード確認リクエスト<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTUploadConfirmRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_upload_confirm";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200712071147L;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadConfirmRequest.class);

    /**
     * (アップロードファ@イル)<BR>
     * アップロードファ@イル<BR>
     */
    public String[] uploadFile;

    /**
     * (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 0：登録<BR>
     * 1：削除<BR>
     */
    public String statusDiv;

    /**
     * @@roseuid 4758B27802BF
     */
    public WEB3AdminFPTUploadConfirmRequest()
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
        return new WEB3AdminFPTUploadConfirmResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １）　@アップロードファ@イルのチェック<BR>
     * 　@１－１）　@未入力の場合、「アップロードファ@イルが未指定です。」<BR>
     * 　@　@　@　@　@　@（BUSINESS_ERROR_00976）例外をスローする。<BR>
     * 　@　@ class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@:　@BUSINESS_ERROR_00976<BR>
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
     * @@roseuid 473814C50213
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@アップロードファ@イルのチェック
        //１－１）　@未入力の場合、「アップロードファ@イルが未指定です。」
        //（BUSINESS_ERROR_00976）例外をスローする。
        if (this.uploadFile == null || this.uploadFile.length == 0)
        {
            log.debug("アップロードファ@イルが未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "アップロードファ@イルが未指定です。");
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
