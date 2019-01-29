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
filename	WEB3AdminFPTDeleteConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧削除確認リクエスト(WEB3AdminFPTDeleteConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/06 武波 (中訊) 新規作成 仕様変更・モデル No.011
Revision History : 2008/01/25 周墨洋 (中訊) 仕様変更・モデルNo.022
*/

package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者金商法@交付閲覧削除確認リクエスト)<BR>
 * 管理者金商法@交付閲覧削除確認リクエストクラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTDeleteConfirmRequest extends WEB3GenRequest
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDeleteConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_delete_confirm";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200711061017L;

    /**
     * (金商法@交付閲覧削除行)<BR>
     * 金商法@交付閲覧削除行<BR>
     */
    public WEB3FPTHistoryInfoUnit financialProductTradeDeleteRow;

    /**
     * @@roseuid 472FC5B40343
     */
    public WEB3AdminFPTDeleteConfirmRequest()
    {

    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １） 金商法@交付閲覧削除行 == null の場合、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_02784<BR>
     * <BR>
     * ２） 金商法@交付閲覧削除行.部店コード == null の場合、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_00833<BR>
     * <BR>
     * ３） 金商法@交付閲覧削除行.顧客コード == null の場合、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_00835<BR>
     * <BR>
     * ４） 金商法@交付閲覧削除行.書面区分 == null の場合、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_02948<BR>
     * <BR>
     * ５） 金商法@交付閲覧削除行.電子鳩銘柄コード == null の場合、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_03009<BR>
     * <BR>
     * ６） 金商法@交付閲覧削除行.書面交付日 == null の場合、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_02943<BR>
     * <BR>
     * ７） 金商法@交付閲覧削除行.書面種類コード == null の場合、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_03013<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4726CAE00273
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１） 金商法@交付閲覧削除行 == null の場合、例外をスローする。
        if (this.financialProductTradeDeleteRow == null)
        {
            log.debug("削除該当レコードなし。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02784,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "削除該当レコードなし。");
        }

        //２） 金商法@交付閲覧削除行.部店コード == null の場合、例外をスローする。
        if (this.financialProductTradeDeleteRow.branchCode == null)
        {
            log.debug("部店コードが未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }

        //３） 金商法@交付閲覧削除行.顧客コード == null の場合、例外をスローする。
        if (this.financialProductTradeDeleteRow.acceptCode == null)
        {
            log.debug("顧客コードが未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードが未指定です。");
        }

        //４） 金商法@交付閲覧削除行.書面区分 == null の場合、例外をスローする。
        if (this.financialProductTradeDeleteRow.documentDiv == null)
        {
            log.debug("書面区分が未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02948,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面区分が未指定です。");
        }

        //５） 金商法@交付閲覧削除行.電子鳩銘柄コード == null の場合、例外をスローする。
        if (this.financialProductTradeDeleteRow.batoProductCode == null)
        {
            log.debug("電子鳩銘柄コードが未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03009,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子鳩銘柄コードが未指定です。");
        }

        //６） 金商法@交付閲覧削除行.書面交付日 == null の場合、例外をスローする。
        if (this.financialProductTradeDeleteRow.docuDeliDate == null)
        {
            log.debug("書面交付日が未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02943,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面交付日が未指定です。");
        }

        //７） 金商法@交付閲覧削除行.書面種類コード == null の場合、例外をスローする。
        if (this.financialProductTradeDeleteRow.documentCategory == null)
        {
            log.debug("書面種類コードが未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03013,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面種類コードが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
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
        return new WEB3AdminFPTDeleteConfirmResponse(this);
    }

}
@
