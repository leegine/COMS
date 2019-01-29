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
filename	WEB3AdminFPTDocumentCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付書面共通リクエスト(WEB3AdminFPTDocumentCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 馮海濤 (中訊) 新規作成 モデルNo.037,モデルNo.044
*/
package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.define.WEB3AdminFPTProcessFlagDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者金商法@交付書面共通リクエスト)<BR>
 * 管理者金商法@交付書面共通リクエストクラス<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentCommonRequest extends WEB3GenRequest
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentCommonRequest.class);

    /**
     * (書面更新情報)<BR>
     * 書面更新情報<BR>
     */
    public WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList;

    /**
     * (更新処理フラグ)<BR>
     * 更新処理フラグ<BR>
     * <BR>
     * 0：登録<BR>
     * 1：更新<BR>
     * 2：削除<BR>
     */
    public String updateProcessFlag;

    /**
     * @@roseuid 47CBC5AE029F
     */
    public WEB3AdminFPTDocumentCommonRequest()
    {

    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * <BR>
     * １）　@更新処理フラグチェック<BR>
     * <BR>
     * 　@１-１）　@this.更新処理フラグ != (0 or 1 or 2) の場合、<BR>
     * 　@　@「更新処理が不明です。」 例外をスローする。<BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03030<BR>
     * <BR>
     * ２）　@書面更新情報チェック<BR>
     * <BR>
     * 　@２-１）　@書面更新情報 == nullの場合、「更新情報が不正です」例外をスローする。<BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03031<BR>
     * <BR>
     * 　@２-２）　@書面更新情報の長さ回数Loopを行う。（インデックス：index）<BR>
     * <BR>
     * 　@　@２-２-１）　@書面区分チェック<BR>
     * <BR>
     * 　@　@　@２-２-１-１）　@this.書面更新情報[index].書面区分 == (null or "")の場合、<BR>
     * 　@　@　@　@　@例外（BUSINESS_ERROR_02948）をスローする。<BR>
     * 　@　@　@２-２-１-２）　@this.書面更新情報[index].書面区分が半角数字以外の場合、<BR>
     * 　@　@　@　@　@例外（BUSINESS_ERROR_02941）をスローする。<BR>
     * 　@　@　@２-２-１-３）　@this.書面更新情報[index].書面区分が4桁以上 の場合、<BR>
     * 　@　@　@　@　@例外（BUSINESS_ERROR_02942）をスローする。<BR>
     * <BR>
     * 　@　@２-２-２）　@書面種類コードチェック<BR>
     * <BR>
     * 　@　@　@２-２-２-１）　@this.書面更新情報[index].書面種類コード == (null or "")の場合、<BR>
     * 　@　@　@　@　@例外（BUSINESS_ERROR_03013）をスローする。<BR>
     * 　@　@　@２-２-２-２）　@this.書面更新情報[index].書面種類コードが半角数字以外の場合、<BR>
     * 　@　@　@　@　@例外（BUSINESS_ERROR_02997）をスローする。<BR>
     * 　@　@　@２-２-２-３）　@this.書面更新情報[index].書面種類コードが4桁以上の場合、<BR>
     * 　@　@　@　@　@例外（BUSINESS_ERROR_02997）をスローする。<BR>
     * <BR>
     * 　@　@２-２-３）　@書面通番チェック<BR>
     * <BR>
     * 　@　@　@２-２-３-１）　@this.書面更新情報[index].書面通番 == (null or "")の場合、<BR>
     * 　@　@　@　@　@「書面通番が未設定です。」例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03032<BR>
     * 　@　@　@２-２-３-２）　@this.書面更新情報[index].書面通番 が半角数字以外の場合、<BR>
     * 　@　@　@　@　@「書面通番が不正です。」例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03033<BR>
     * 　@　@　@２-２-３-３）　@this.書面更新情報[index].書面通番 が5桁以上の場合、<BR>
     * 　@　@　@　@　@「書面通番が不正です。」例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03033<BR>
     * <BR>
     * 　@　@２-２-４） 摘要チェック<BR>
     * <BR>
     * 　@　@　@２-２-４-１）　@this.書面更新情報[index].摘要 != (null or "") かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@半角文字が混在している場合、<BR>
     * 　@　@　@　@　@「摘要は全角文字で入力してください。」例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03034<BR>
     * 　@　@　@２-２-４-２）　@this.書面更新情報[index].摘要 != (null or "") かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@文字数が201バイト以上の場合、<BR>
     * 　@　@　@　@　@「摘要は100文字以内で設定してください」例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03035<BR>
     * <BR>
     * 　@　@２-２-５）　@this.更新処理フラグ == (0 or 1) の場合、有効区分チェック<BR>
     * <BR>
     * 　@　@　@２-２-５-１）　@this.書面更新情報[index].有効区分 != (0 or 1) の場合、<BR>
     * 　@　@　@　@　@「有効区分が不正です。」例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03036<BR>
     * <BR>
     * 　@　@２-２-６）　@this.更新処理フラグ == 1 の場合、有効区分数チェック<BR>
     * 　@　@　@　@　@　@　@this.書面更新情報の各要素において、<BR>
     * 　@　@　@　@　@　@　@書面区分と書面種類コードが等しい要素が複数存在する場合、<BR>
     * 　@　@　@　@　@　@　@有効区分 == 0 が2以上存在する場合、<BR>
     * 　@　@　@　@　@　@　@「同じ書面種類中に、有効な書面が複数存在します。」例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03049<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BCF9760172
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 更新処理フラグチェック
        //this.更新処理フラグ != (0 or 1 or 2) の場合、「更新処理が不明です。」 例外をスローする。
        if (!WEB3AdminFPTProcessFlagDivDef.INSERT.equals(this.updateProcessFlag) &&
            !WEB3AdminFPTProcessFlagDivDef.UPDATE.equals(this.updateProcessFlag) &&
            !WEB3AdminFPTProcessFlagDivDef.DELETE.equals(this.updateProcessFlag))
        {
            log.debug("更新処理が不明です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03030,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "更新処理が不明です。");
        }

        //書面更新情報チェック
        // 書面更新情報 == null の場合、「更新情報が不正です」例外をスローする。
        if (this.documentUpdateList == null)
        {
            log.debug("更新情報が不正です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03031,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "更新情報が不正です。");
        }

        //書面更新情報の長さ回数Loopを行う。（インデックス：index）
        //書面区分チェック
        // this.書面更新情報[index].書面区分 == (null or "") の場合、
        //      例外（BUSINESS_ERROR_02948）をスローする。
        // this.書面更新情報[index].書面区分が半角数字以外 の場合、
        //      例外（BUSINESS_ERROR_02941）をスローする。
        // this.書面更新情報[index].書面区分が4桁以上 の場合、
        //      例外（BUSINESS_ERROR_02942）をスローする。
        int l_intdocumentUpdateListCnt = this.documentUpdateList.length;
        for (int i = 0; i < l_intdocumentUpdateListCnt; i++)
        {
            if (this.documentUpdateList[i].documentDiv == null || this.documentUpdateList[i].documentDiv == "")
            {
                log.debug("書面区分が未指定です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02948,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面区分が未指定です。");
            }

            if (!WEB3StringTypeUtility.isDigit(this.documentUpdateList[i].documentDiv))
            {
                log.debug("書面区分が半角数字以外の値です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02941,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面区分が半角数字以外の値です。");
            }

            if (this.documentUpdateList[i].documentDiv.length() >= 4)
            {
                log.debug("書面区分のサイズが不正です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02942,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面区分のサイズが不正です。");
            }

            //書面種類コードチェック
            // this.書面更新情報[index].書面種類コード == (null or "") の場合、
            //      例外（BUSINESS_ERROR_03013）をスローする。
            // this.書面更新情報[index].書面種類コードが半角数字以外 の場合、
            //      例外（BUSINESS_ERROR_02997）をスローする。
            // this.書面更新情報[index].書面種類コードが4桁以上 の場合、
            //      例外（BUSINESS_ERROR_02997）をスローする。
            if (this.documentUpdateList[i].documentCategory == null ||
                this.documentUpdateList[i].documentCategory == "")
            {
                log.debug("書面種類コードが未指定です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03013,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面種類コードが未指定です。");
            }

            if (!WEB3StringTypeUtility.isDigit(this.documentUpdateList[i].documentCategory))
            {
                log.debug("書面種類コードが不正です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面種類コードが不正です。");
            }

            if (this.documentUpdateList[i].documentCategory.length() >= 4)
            {
                log.debug("書面種類コードが不正です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面種類コードが不正です。");
            }

            //書面通番チェック
            //this.書面更新情報[index].書面通番 == (null or "") の場合、
            //      「書面通番が未設定です。」例外をスローする。
            //this.書面更新情報[index].書面通番 が半角数字以外 の場合、
            //      「書面通番が不正です。」例外をスローする。
            //this.書面更新情報[index].書面通番 が5桁以上の場合、
            //      「書面通番が不正です。」例外をスローする。
            if (this.documentUpdateList[i].documentNumber == null ||
                this.documentUpdateList[i].documentNumber == "")
            {
                log.debug("書面通番が未設定です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03032,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面通番が未設定です。");
            }

            if (!WEB3StringTypeUtility.isDigit(this.documentUpdateList[i].documentNumber))
            {
                log.debug("書面通番が不正です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03033,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面通番が不正です。");
            }

            if (this.documentUpdateList[i].documentNumber.length() >= 5)
            {
                log.debug("書面通番が不正です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03033,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面通番が不正です。");
            }

            //摘要チェック
            // this.書面更新情報[index].摘要 != (null or "") かつ
            //  半角文字が混在している場合、「摘要は全角文字で入力してください。」例外をスローする。
            // this.書面更新情報[index].摘要 != (null or "") かつ
            //  文字数が201バイト以上の場合、「摘要は100文字以内で設定してください」例外をスローする。
            if (this.documentUpdateList[i].remarks != null && this.documentUpdateList[i].remarks != "")
            {
                if (!WEB3StringTypeUtility.isMulti(this.documentUpdateList[i].remarks))
                {
                    log.debug("摘要は全角文字で入力してください。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03034,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "摘要は全角文字で入力してください。");
                }

                if (this.documentUpdateList[i].remarks.length() > 100)
                {
                    log.debug("摘要は100文字以内で設定してください。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03035,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "摘要は100文字以内で設定してください。");
                }
            }

            // this.更新処理フラグ == (0 or 1) の場合、有効区分チェック
            // this.書面更新情報[index].有効区分 != (0 or 1) の場合、「有効区分が不正です。」例外をスローする。
            if (WEB3AdminFPTProcessFlagDivDef.INSERT.equals(this.updateProcessFlag) ||
                WEB3AdminFPTProcessFlagDivDef.UPDATE.equals(this.updateProcessFlag))
            {
                if (!WEB3ValidFlagDef.VALID.equals(this.documentUpdateList[i].validFlag) &&
                    !WEB3ValidFlagDef.INVALID.equals(this.documentUpdateList[i].validFlag))
                {
                    log.debug("有効区分が不正です。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03036,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "有効区分が不正です。");
                }
            }
        }

        // this.更新処理フラグ == 1 の場合、有効区分数チェック
        //this.書面更新情報の各要素において、書面区分と書面種類コードが等しい要素が複数存在する場合、
        //有効区分 == 0 が2以上存在する場合、「同じ書面種類中に、有効な書面が複数存在します。」例外をスローする。
        if (WEB3AdminFPTProcessFlagDivDef.UPDATE.equals(this.updateProcessFlag))
        {
            for (int i = 0; i < l_intdocumentUpdateListCnt - 1; i++)
            {
                for (int j = i + 1; j < l_intdocumentUpdateListCnt; j++)
                {
                    if (((this.documentUpdateList[i].documentDiv.equals(this.documentUpdateList[j].documentDiv)) && 
                        (this.documentUpdateList[i].documentCategory.equals(this.documentUpdateList[j].documentCategory))) 
                        && (WEB3ValidFlagDef.VALID.equals(this.documentUpdateList[i].validFlag) && 
                            WEB3ValidFlagDef.VALID.equals(this.documentUpdateList[j].validFlag)))
                    {
                        log.debug("同じ書面種類中に、有効な書面が複数存在します。");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03049,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "同じ書面種類中に、有効な書面が複数存在します。");
                    }
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
