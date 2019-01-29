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
filename	WEB3AdminFPTDocumentListReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付書面照会一覧リクエスト(WEB3AdminFPTDocumentListReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 馮海濤 (中訊) 新規作成 モデルNo.037
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
 * (管理者金商法@交付書面照会一覧リクエスト)<BR>
 * 管理者金商法@交付書面照会一覧リクエストクラス<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentListReferenceRequest extends WEB3GenRequest
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentListReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_document_list_reference";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200803031745L;

    /**
     * (書面区分管理一覧)<BR>
     * 書面区分管理一覧<BR>
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;

    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     * <BR>
     * キー項目は以下のとおり<BR>
     * <BR>
     * ・電子鳩銘柄コード<BR>
     * ・有効区分<BR>
     */
    public WEB3AdminFPTSortKey[] sortKeys;

    /**
     * @@roseuid 47CBC5AE0251
     */
    public WEB3AdminFPTDocumentListReferenceRequest()
    {

    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * <BR>
     * １）　@書面区分管理一覧チェック(書面区分管理一覧配列全要素に対してチェックを行う)<BR>
     * <BR>
     * 　@１-１）　@this.書面区分管理情報一覧 == nullの場合、<BR>
     * 　@　@例外（BUSINESS_ERROR_03007）をスローする。<BR>
     * <BR>
     * 　@１-２）　@this.書面区分管理情報一覧[index].書面区分 == (null or "")の場合、<BR>
     * 　@　@例外（BUSINESS_ERROR_02948）をスローする。<BR>
     * 　@１-３）　@this.書面区分管理情報一覧[index].書面区分が半角数字以外 の場合、<BR>
     * 　@　@例外（BUSINESS_ERROR_02941）をスローする。<BR>
     * 　@１-４）　@this.書面区分管理情報一覧[index].書面区分が4桁以上 の場合、<BR>
     * 　@　@例外（BUSINESS_ERROR_02942）をスローする。<BR>
     * <BR>
     * 　@１-５）　@this.書面区分管理情報一覧[index].書面種類コード == (null or "")の場合、<BR>
     * 　@　@例外（BUSINESS_ERROR_03013）をスローする。<BR>
     * 　@１-６）　@this.書面区分管理情報一覧[index].書面種類コードが半角数字以外の場合、<BR>
     * 　@　@例外（BUSINESS_ERROR_02997）をスローする。<BR>
     * 　@１-７）　@this.書面区分管理情報一覧[index].書面種類コードが4桁以上 の場合、<BR>
     * 　@　@例外（BUSINESS_ERROR_02997）をスローする。<BR>
     * <BR>
     * <BR>
     * ２）　@ソートキーチェック <BR>
     * <BR>
     * 　@　@this.ソートキー = null or<BR>
     * 　@　@this.ソートキー.length() = 0の場合、例外をスローする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BD21D4018E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //　@this.書面区分管理情報一覧 == null
        // の場合、例外（BUSINESS_ERROR_03007）をスローする。
        if (this.documentDivList == null)
        {
            log.debug("書面区分管理情報が未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03007,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面区分管理情報が未指定です。");
        }

        int l_intdocumentDivListCnt = this.documentDivList.length;
        for (int i = 0; i < l_intdocumentDivListCnt; i++)
        {
            // this.書面区分管理情報一覧[index].書面区分 == (null or "") の場合、
            //例外（BUSINESS_ERROR_02948）をスローする。
            if (this.documentDivList[i].documentDiv == null || this.documentDivList[i].documentDiv == "")
            {
                log.debug("書面区分が未指定です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02948,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面区分が未指定です。");
            }

            //this.書面区分管理情報一覧[index].書面区分が半角数字以外 の場合、
            //例外（BUSINESS_ERROR_02941）をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.documentDivList[i].documentDiv))
            {
                log.debug("書面区分が半角数字以外の値です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02941,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面区分が半角数字以外の値です。");
            }

            //this.書面区分管理情報一覧[index].書面区分が4桁以上 の場合、
            //例外（BUSINESS_ERROR_02942）をスローする。

            if (this.documentDivList[i].documentDiv.length() >= 4)
            {
                log.debug("書面区分のサイズが不正です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02942,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面区分のサイズが不正です。");
            }

            //this.書面区分管理情報一覧[index].書面種類コード == (null or "") の場合、
            //例外（BUSINESS_ERROR_03013）をスローする。
            if (this.documentDivList[i].documentCategory == null || this.documentDivList[i].documentCategory == "")
            {
                log.debug("書面種類コードが未指定です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03013,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面種類コードが未指定です。");
            }

            //this.書面区分管理情報一覧[index].書面種類コードが半角数字以外 の場合、
            //例外（BUSINESS_ERROR_02997）をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.documentDivList[i].documentCategory))
            {
                log.debug("書面種類コードが不正です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面種類コードが不正です。");
            }

            // this.書面区分管理情報一覧[index].書面種類コードが4桁以上 の場合、
            //例外（BUSINESS_ERROR_02997）をスローする。
            if (this.documentDivList[i].documentCategory.length() >= 4)
            {
                log.debug("書面種類コードが不正です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面種類コードが不正です。");
            }
        }

        //ソートキーチェック
        //this.ソートキー = null or
        //this.ソートキー.length() = 0
        //の場合、例外をスローする。
        if (this.sortKeys == null || this.sortKeys.length == 0)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
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
        return new WEB3AdminFPTDocumentListReferenceResponse(this);
    }
}
@
