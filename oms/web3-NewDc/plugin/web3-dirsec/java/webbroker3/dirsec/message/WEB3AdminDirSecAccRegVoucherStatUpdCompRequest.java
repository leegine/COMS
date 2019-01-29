head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherStatUpdCompRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・顧客情報登録伝票ステータス更新完了リクエスト(WEB3AdminDirSecAccRegVoucherStatUpdCompRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12 何文敏 (中訊) 新規作成 仕様変更 モデルNo.098
Revision History    : 2007/06/15 徐宏偉 (中訊) 仕様変更 モデルNo.100
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・顧客情報登録伝票ステータス更新完了リクエスト)<BR>
 * 管理者・顧客情報登録伝票ステータス更新完了リクエストクラス。<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdCompRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdCompRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dirsec_acc_reg_voucher_stat_upd_comp";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 200706121120L;

    /**
     * (更新_伝票作成状況)<BR>
     * 修正後の伝票作成状況<BR>
     * <BR>
     * 3：受付完了<BR>
     * 4：受付エラー<BR>
     */
    public String updateVoucherMakeStatus;

    /**
     * (更新_エラー理由コード)<BR>
     * 修正後のエラー理由コード<BR>
     */
    public String updateErrorReasonCode;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * (顧客情報登録伝票レコード)<BR>
     * 顧客情報登録伝票レコード<BR>
     */
    public WEB3AdminDirSecAccVoucherRecordDetail[] accVoucherRecord;

    /**
     * @@roseuid 466E0B6B00F6
     */
    public WEB3AdminDirSecAccRegVoucherStatUpdCompRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * <BR>
     * １） 顧客情報登録伝票レコード詳細配列 != nullの場合、<BR>
     * 　@　@顧客情報登録伝票レコード詳細配列チェックを行う。<BR>
     * <BR>
     * 　@　@顧客情報登録伝票レコード詳細配列 == null<BR>
     * 　@　@or 顧客情報登録伝票レコード詳細配列の長さ == 0 の場合、<BR>
     * 　@　@「更新対象のレコードが不正です。」例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02838<BR>
     * <BR>
     * 　@１-１） 証券会社コードチェック<BR>
     * 　@　@１-１-１） 顧客情報登録伝票レコード詳細[index].証券会社コード == nullの場合、<BR>
     * 　@　@　@　@「証券会社コードが不正です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_01023<BR>
     * <BR>
     * 　@１-２） 部店コードチェック<BR>
     * 　@　@１-２-１） 顧客情報登録伝票レコード詳細[index].部店コード == nullの場合、<BR>
     * 　@　@　@　@「部店コードが不正です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00779<BR>
     * 　@１-３） 顧客コードチェック<BR>
     * 　@　@１-３-１） 顧客情報登録伝票レコード詳細[index].顧客コード == nullの場合、<BR>
     * 　@　@　@　@「顧客コードが不正です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00780<BR>
     * 　@１-４） データコードチェック<BR>
     * 　@　@１-４-１） 顧客情報登録伝票レコード詳細[index].データコード == null の場合、<BR>
     * 　@　@　@　@「データコードが不正です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02828<BR>
     * 　@１-５） 識別コードチェック<BR>
     * 　@　@１-５-１） 顧客情報登録伝票レコード詳細[index].識別コード == null の場合、<BR>
     * 　@　@　@　@「識別コードが不正です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02829<BR>
     * 　@１-６） 連絡種別、伝票通番チェック<BR>
     * 　@　@１-６-１） 顧客情報登録伝票レコード詳細[index].口座開設伝票フラグ == FALSE<BR>
     * 　@　@　@　@AND 顧客情報登録伝票レコード詳細[index]連絡種別 == null の場合、<BR>
     * 　@　@　@　@「連絡種別が不正です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02830<BR>
     * <BR>
     * 　@　@１-６-２） 顧客情報登録伝票レコード詳細[index].口座開設伝票フラグ == TRUE<BR>
     * 　@　@　@　@AND 顧客情報登録伝票レコード詳細[index].伝票通番 == null の場合、<BR>
     * 　@　@　@　@「伝票通番が不正です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02831<BR>
     * <BR>
     * ２） 伝票作成状況チェック<BR>
     * 　@２-１） this.更新_伝票作成状況 == null の場合、<BR>
     * 　@　@　@「伝票作成状況を入力してください。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02825<BR>
     * <BR>
     * ３） エラー理由コードチェック<BR>
     * 　@３-１） this.更新_エラー理由コード == null の場合、<BR>
     * 　@　@　@「エラー理由コードを入力してください。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02826<BR>
     * <BR>
     * 　@３-２） this.更新_エラー理由コードの長さ > 4 OR<BR>
     * 　@　@　@this.更新_エラー理由コードが半角英数以外の場合、<BR>
     * 　@　@　@「エラー理由コードが不正です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02827<BR>
     * <BR>
     * ４）　@暗証番号チェック<BR>
     * 　@４-１）　@this.暗証番号 == nullの場合、<BR>
     * 　@　@　@「暗証番号が不正です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02832<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4655278700F2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //        １） 顧客情報登録伝票レコード詳細配列 != nullの場合、
        //        顧客情報登録伝票レコード詳細配列チェックを行う。
        //
        //        顧客情報登録伝票レコード詳細配列 == null
        //         or 顧客情報登録伝票レコード詳細配列の長さ == 0 の場合、
        //        「更新対象のレコードが不正です。」例外をスローする。
        if (accVoucherRecord == null || accVoucherRecord.length == 0)
        {
            log.debug("更新対象のレコードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02838,
                this.getClass().getName() + STR_METHOD_NAME,
                "更新対象のレコードが不正です。");
        }

        for (int i = 0; i < accVoucherRecord.length; i++)
        {
            //１-１） 証券会社コードチェック
            //       １-１-１） 顧客情報登録伝票レコード詳細[index].証券会社コード == nullの場合
            //               「証券会社コードが不正です。」の例外をスローする。
            if (accVoucherRecord[i].institutionCode == null)
            {
                log.debug("証券会社コードが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01023,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "証券会社コードが不正です。");
            }

            //     １-２） 部店コードチェック
            //       １-２-１） 顧客情報登録伝票レコード詳細[index].部店コード == nullの場合、
            //               「部店コードが不正です。」の例外をスローする。
            if (accVoucherRecord[i].branchCode == null)
            {
                log.debug("部店コードが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "部店コードが不正です。");
            }

            //     １-３） 顧客コードチェック
            //       １-３-１） 顧客情報登録伝票レコード詳細[index].顧客コード == nullの場合、
            //               「顧客コードが不正です。」の例外をスローする。
            if (accVoucherRecord[i].accountCode == null)
            {
                log.debug("顧客コードが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "顧客コードが不正です。");
            }

            //     １-４） データコードチェック
            //       １-４-１） 顧客情報登録伝票レコード詳細[index].データコード == null の場合、
            //               「データコードが不正です。」の例外をスローする。
            if (accVoucherRecord[i].dataCode == null)
            {
                log.debug("データコードが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02828,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "データコードが不正です。");
            }

            //     １-５） 識別コードチェック
            //       １-５-１） 顧客情報登録伝票レコード詳細[index].識別コード == null の場合、
            //               「識別コードが不正です。」の例外をスローする。
            if (accVoucherRecord[i].requestNumber == null)
            {
                log.debug("識別コードが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02829,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "識別コードが不正です。");
            }

            //     １-６） 連絡種別、伝票通番チェック
            //       １-６-１） 顧客情報登録伝票レコード詳細[index].口座開設伝票フラグ == FALSE
            //                   AND 顧客情報登録伝票レコード詳細[index]連絡種別 == null の場合、
            //                  「連絡種別が不正です。」の例外をスローする。
            if (!accVoucherRecord[i].voucherFlag && (accVoucherRecord[i].infoType == null))
            {
                log.debug("連絡種別が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02830,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "連絡種別が不正です。");
            }

            //       １-６-２） 顧客情報登録伝票レコード詳細[index].口座開設伝票フラグ == TRUE
            //                   AND 顧客情報登録伝票レコード詳細[index].伝票通番 == null の場合、
            //                 「伝票通番が不正です。」の例外をスローする。
            if (accVoucherRecord[i].voucherFlag && (accVoucherRecord[i].voucherNumber == null))
            {
                log.debug("伝票通番が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02831,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "伝票通番が不正です。");
            }

        }

        //   ２） 伝票作成状況チェック
        //     ２-１） this.更新_伝票作成状況 == null の場合、
        //             「伝票作成状況を入力してください。」の例外をスローする。
        if (this.updateVoucherMakeStatus == null)
        {
            log.debug("伝票作成状況を入力してください。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02825,
                this.getClass().getName() + STR_METHOD_NAME,
                "伝票作成状況を入力してください。");
        }

        //   ３） エラー理由コードチェック
        //     ３-１） this.更新_エラー理由コード == null の場合、
        //              「エラー理由コードを入力してください。」の例外をスローする。
        if (this.updateErrorReasonCode == null)
        {
            log.debug("エラー理由コードを入力してください。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02826,
                this.getClass().getName() + STR_METHOD_NAME,
                "エラー理由コードを入力してください。");
        }

        //     ３-２） this.更新_エラー理由コードの長さ > 4 OR
        //          this.更新_エラー理由コードが半角英数以外の場合、
        //           「エラー理由コードが不正です。」の例外をスローする。
        if (this.updateErrorReasonCode.length() > 4
            || !WEB3StringTypeUtility.isLetterOrDigit(this.updateErrorReasonCode))
        {
            log.debug("エラー理由コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02827,
                this.getClass().getName() + STR_METHOD_NAME,
                "エラー理由コードが不正です。");
        }

        //   ４）　@暗証番号チェック
        //   　@４-１）　@this.暗証番号 == nullの場合、
        //   　@　@　@　@　@「暗証番号が不正です。」の例外をスローする。
        if (this.password == null)
        {
            log.debug("暗証番号が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02832,
                this.getClass().getName() + STR_METHOD_NAME,
                "暗証番号が不正です。");
        }

       log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecAccRegVoucherStatUpdCompResponse(this);
    }
}
@
