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
filename	WEB3AdminFPTUpdateCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付更新共通リクエスト(WEB3AdminFPTUpdateCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 武波 (中訊) 新規作成
Revision History : 2007/10/09 何文敏 (中訊) モデルNo.004
Revision History : 2008/01/25 周墨洋 (中訊) 仕様変更・モデルNo.022
Revision History : 2008/01/29 周墨洋 (中訊) 仕様変更・モデルNo.028
*/

package webbroker3.docadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者金商法@交付更新共通リクエスト)<BR>
 * 管理者金商法@交付更新共通リクエストクラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTUpdateCommonRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_update_common";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709291406L;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUpdateCommonRequest.class);

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String acceptCode;

    /**
     * (書面交付日)<BR>
     * 書面交付日<BR>
     */
    public Date docuDeliDate;

    /**
     * (書面種類一覧)<BR>
     * 書面種類一覧<BR>
     */
    public WEB3FPTDocumentCategoryDetailsInfoUnit[] documentCategoryList;

    /**
     * @@roseuid 46FDDD3C02DE
     */
    public WEB3AdminFPTUpdateCommonRequest()
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
        return null;
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １） 部店コードチェック<BR>
     * <BR>
     * 　@　@リクエスト.部店コードが未設定 又は<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00833<BR>
     * 　@　@リクエスト.部店コードが半角数字以外 又は<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_01729<BR>
     * 　@　@リクエスト.部店コードが3桁以外 の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00834<BR>
     * <BR>
     * ２） 顧客コードチェック<BR>
     * <BR>
     * 　@　@リクエスト.顧客コードが未設定 又は<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00835<BR>
     * 　@　@リクエスト.顧客コードが半角数字以外 又は<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_01043<BR>
     * 　@　@リクエスト.顧客コードが6桁以外 の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00836<BR>
     * <BR>
     * ３） 書面交付日チェック<BR>
     * <BR>
     * 　@　@リクエスト.書面交付日が未設定 の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02943<BR>
     * <BR>
     * ４） 書面種類一覧チェック(書面種類一覧配列全要素に対してチェックを行う)<BR>
     * <BR>
     * 　@４-１） リクエスト.書面種類一覧がnullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_03006<BR>
     * <BR>
     * 　@４-２） リクエスト.書面種類一覧[index].書面区分管理情報がnull、または<BR>
     * 　@　@　@　@　@リクエスト.書面種類一覧[index].電子鳩銘柄コード管理情報がnullの場合、<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_03007<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_03008<BR>
     * <BR>
     * 　@４-３） リクエスト.書面種類一覧[index].書面区分管理情報.書面区分 が未設定<BR>
     * 　@　@　@　@　@又は半角数字以外 又は 4桁以上 の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02948<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02941<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02942<BR>
     * <BR>
     * 　@４-４） リクエスト.書面種類一覧[index].書面区分管理情報.書面チェック区分 が未設定<BR>
     * 　@　@　@　@　@又は半角数字以外 又は 3桁以上 の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02944<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02945<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02946<BR>
     * <BR>
     * 　@４-５） リクエスト.書面種類一覧[index].電子鳩銘柄コード管理情報チェック<BR>
     * 　@　@　@　@　@(電子鳩銘柄コード管理情報配列全要素に対してチェックを行う)<BR>
     * 　@　@４-５-１） リクエスト.書面種類一覧[index].電子鳩銘柄コード管理情報[idx].電子鳩銘柄コード<BR>
     * 　@　@　@　@　@が未設定 又は 半角数字以外 又は 桁数が4桁 or 7桁以外<BR>
     * 　@　@　@　@　@の場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_03009<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_03010<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_03011<BR>
     * <BR>
     * 　@　@４-５-２） リクエスト.書面種類一覧[index].電子鳩銘柄コード管理情報[idx].有効区分<BR>
     * 　@　@　@　@　@が未設定 又は 0、1 以外の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_03012<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_03014<BR>
     * <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46F3B756012A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１） 部店コードチェック
        //リクエスト.部店コードが未設定 又は
        //リクエスト.部店コードが半角数字以外 又は
        //リクエスト.部店コードが3桁以外 の場合、例外をスローする。
        if (this.branchCode == null)
        {
            log.debug("部店コードが未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        else if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("部店コードが数値以外の値です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが数値以外の値です。");
        }
        else if (this.branchCode.length() != 3)
        {
            log.debug("部店コードのサイズが不正です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードのサイズが不正です。");
        }

        //２） 顧客コードチェック
        //リクエスト.顧客コードが未設定 又は
        //リクエスト.顧客コードが半角数字以外 又は
        //リクエスト.顧客コードが6桁以外 の場合、例外をスローする。
        if (this.acceptCode == null)
        {
            log.debug("顧客コードが未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードが未指定です。");
        }
        else if (!WEB3StringTypeUtility.isDigit(this.acceptCode))
        {
            log.debug("顧客コードの値が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードの値が数字以外の値です。");
        }
        else if (this.acceptCode.length() != 6)
        {
             log.debug("顧客コードのサイズが不正です。");
             log.exiting(STR_METHOD_NAME);

             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "顧客コードのサイズが不正です。");
        }

        //３） 書面交付日チェック
        //リクエスト.書面交付日が未設定 の場合、例外をスローする。
        if (this.docuDeliDate == null)
        {
            log.debug("書面交付日が未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02943,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面交付日が未指定です。");
        }

        //４） 書面種類一覧チェック(書面種類一覧配列全要素に対してチェックを行う)
        //４-１） リクエスト.書面種類一覧がnullの場合、例外をスローする。
        if (this.documentCategoryList == null)
        {
            log.debug("書面種類一覧が未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面種類一覧が未指定です。");
        }
        else
        {

            int l_intDocumentCategoryCount = this.documentCategoryList.length;
            for (int i = 0; i < l_intDocumentCategoryCount; i++)
            {
                if (this.documentCategoryList[i] == null)
                {
                    log.debug("書面種類一覧が未指定です。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03006,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "書面種類一覧が未指定です。");
                }

                //４-２） リクエスト.書面種類一覧[index].書面区分管理情報がnull、または
                //　@　@　@　@リクエスト.書面種類一覧[index].電子鳩銘柄コード管理情報がnullの場合、
                //　@　@　@　@例外をスローする。
                if (this.documentCategoryList[i].documentDivList == null)
                {
                    log.debug("書面区分管理情報が未指定です。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03007,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "書面区分管理情報が未指定です。");
                }
                else if (this.documentCategoryList[i].batoProductCodeAdminInfo == null)
                {
                    log.debug("電子鳩銘柄コード管理情報が未指定です。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03008,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "電子鳩銘柄コード管理情報が未指定です。");
                }

                //４-３） リクエスト.書面種類一覧[index].書面区分管理情報.書面区分 が未設定
                //　@　@　@　@又は半角数字以外 又は 4桁以上 の場合、例外をスローする。
                if (this.documentCategoryList[i].documentDivList.documentDiv == null)
                {
                    log.debug("書面区分が未指定です。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02948,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "書面区分が未指定です。");
                }
                else if (!WEB3StringTypeUtility.isDigit(
                    this.documentCategoryList[i].documentDivList.documentDiv))
                {
                    log.debug("書面区分が半角数字以外の値です。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02941,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "書面区分が半角数字以外の値です。");
                }
                else if (this.documentCategoryList[i].documentDivList.documentDiv.length() >= 4)
                {
                    log.debug("書面区分のサイズが不正です。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02942,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "書面区分のサイズが不正です。");
                }

                //４-４） リクエスト.書面種類一覧[index].書面区分管理情報.書面チェック区分 が未設定
                //　@　@　@　@又は半角数字以外 又は 3桁以上 の場合、例外をスローする。
                if (this.documentCategoryList[i].documentDivList.docuCheckDiv == null)
                {
                    log.debug("書面チェック区分が未指定です。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02944,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "書面チェック区分が未指定です。");
                }
                else if (!WEB3StringTypeUtility.isDigit(
                    this.documentCategoryList[i].documentDivList.docuCheckDiv))
                {
                    log.debug("書面チェック区分が半角数字以外の値です。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02945,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "書面チェック区分が半角数字以外の値です。");
                }
                else if (this.documentCategoryList[i].documentDivList.docuCheckDiv.length() >= 3)
                {
                    log.debug("書面チェック区分のサイズが不正です。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02946,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "書面チェック区分のサイズが不正です。");
                }

                //４-５） リクエスト.書面種類一覧[index].電子鳩銘柄コード管理情報チェック
                //　@　@　@　@(電子鳩銘柄コード管理情報配列全要素に対してチェックを行う)
                int l_intBatoProductCodeAdminInfoCounter =
                    this.documentCategoryList[i].batoProductCodeAdminInfo.length;
                for (int j = 0; j < l_intBatoProductCodeAdminInfoCounter; j++)
                {
                    //４-５-１） リクエスト.書面種類一覧[index].電子鳩銘柄コード管理情報[idx].電子鳩銘柄コード
                    //　@　@　@　@が未設定 又は 半角数字以外 又は 桁数が4桁 or 7桁以外
                    //　@　@　@　@の場合、例外をスローする。
                    if (this.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode == null)
                    {
                        log.debug("電子鳩銘柄コードが未指定です。");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03009,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "電子鳩銘柄コードが未指定です。");
                    }
                    else if (
                        !WEB3StringTypeUtility.isDigit(
                            this.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode))
                    {
                        log.debug("電子鳩銘柄コードは半角数字以外の値です。");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03010,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "電子鳩銘柄コードは半角数字以外の値です。");
                    }
                    else if (this.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode.length() != 4
                        && this.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode.length() != 7)
                    {
                        log.debug("電子鳩銘柄コードのサイズが不正です。");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03011,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "電子鳩銘柄コードのサイズが不正です。");
                    }
                    //４-５-２） リクエスト.書面種類一覧[index].電子鳩銘柄コード管理情報[idx].有効区分
                    //　@　@　@　@が未設定 又は 0、1 以外の場合、例外をスローする。
                    if (this.documentCategoryList[i].batoProductCodeAdminInfo[j].validFlag == null)
                    {
                        log.debug("有効区分が未設定です。");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03012,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "有効区分が未設定です。");
                    }
                    else if (!WEB3ValidFlagDef.VALID.equals(
                            this.documentCategoryList[i].batoProductCodeAdminInfo[j].validFlag)
                        && !WEB3ValidFlagDef.INVALID.equals(
                            this.documentCategoryList[i].batoProductCodeAdminInfo[j].validFlag))
                    {
                        log.debug("有効区分が未定義の値です。");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03014,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "有効区分が未定義の値です。");
                    }
                }
            }

        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
