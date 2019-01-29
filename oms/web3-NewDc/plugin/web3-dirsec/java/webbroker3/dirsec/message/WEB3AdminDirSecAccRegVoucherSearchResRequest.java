head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherSearchResRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・顧客情報登録伝票検索結果リクエスト(WEB3AdminDirSecAccRegVoucherSearchResRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12  何文敏 (中訊) 新規作成 仕様変更 モデルNo.098
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
 * (管理者・顧客情報登録伝票検索結果リクエスト)<BR>
 * 管理者・顧客情報登録伝票検索結果リクエストクラス。<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherSearchResRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherSearchResRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dirsec_acc_reg_voucher_search_res";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 200706121120L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (データコード)<BR>
     * データコード<BR>
     */
    public String dataCode;

    /**
     * (伝票送信日)<BR>
     * 伝票送信日<BR>
     */
    public String voucherSendDate;

    /**
     * @@roseuid 466E0B6B0173
     */
    public WEB3AdminDirSecAccRegVoucherSearchResRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １） 検索必須項目チェック<BR>
     * 　@１-１） this.部店コード == null OR this.顧客コード == null の場合、<BR>
     * 　@　@　@　@　@「検索必須項目がありません。」の例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02833<BR>
     * <BR>
     * 　@１-２） this.部店コードが半角数字以外の場合、<BR>
     * 　@　@　@　@　@「部店コードが不正です。」の例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_00779<BR>
     * <BR>
     * 　@１-３） this.顧客コードが半角数字以外の場合、<BR>
     * 　@　@　@　@　@「顧客コードが不正です。」の例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_00780<BR>
     * <BR>
     * <BR>
     * ２） データコードチェック<BR>
     * 　@２-１） this.データコード != null AND this.データコード の長さ > 5 の場合、<BR>
     * 　@　@　@　@　@「データコードが不正です。」の例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02828<BR>
     * <BR>
     * 　@２-２） this.データコード != null AND this.データコードが半角英数以外の場合、<BR>
     * 　@　@　@　@　@「データコードが不正です。」の例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02828<BR>
     * <BR>
     * ３） 伝票送信日チェック<BR>
     * 　@３-１） this.伝票送信日 != null AND this.伝票送信日の長さ != 8 の場合、<BR>
     * 　@　@　@　@　@「伝票送信日が不正です。」の例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02834<BR>
     * <BR>
     * 　@３-２） this.伝票送信日 != null AND this.伝票送信日が暦日でない場合、<BR>
     * 　@　@　@　@　@「伝票送信日が不正です。」の例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02834<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 465647A301A9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１） 検索必須項目チェック
        // １-１） this.部店コード == null OR this.顧客コード == null の場合、
        //       「検索必須項目がありません。」の例外をスローする。
        if (this.branchCode == null || this.accountCode == null)
        {
            log.debug("検索必須項目がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02833,
                this.getClass().getName() + STR_METHOD_NAME,
                "検索必須項目がありません。");
        }

        // １-２） this.部店コードが半角数字以外の場合、
        //       「部店コードが不正です。」の例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("部店コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コードが不正です。");
        }

        // １-３） this.顧客コードが半角数字以外の場合、
        //       「顧客コードが不正です。」の例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("顧客コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コードが不正です。");
        }

        //２） データコードチェック
        // ２-１） this.データコード != null AND this.データコード の長さ > 5 の場合、
        //       「データコードが不正です。」の例外をスローする。
        if (this.dataCode != null && this.dataCode.length() > 5)
        {
            log.debug("データコードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02828,
                this.getClass().getName() + STR_METHOD_NAME,
                "データコードが不正です。");
        }

        // ２-２） this.データコード != null AND this.データコードが半角英数以外の場合、
        //       「データコードが不正です。」の例外をスローする。
        if (this.dataCode != null
            && (!WEB3StringTypeUtility.isLetterOrDigit(this.dataCode)))
        {
            log.debug("データコードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02828,
                this.getClass().getName() + STR_METHOD_NAME,
                "データコードが不正です。");
        }

        //３） 伝票送信日チェック
        // ３-１） this.伝票送信日 != null AND this.伝票送信日の長さ != 8 の場合、
        //       「伝票送信日が不正です。」の例外をスローする。
        if (this.voucherSendDate != null && this.voucherSendDate.length() != 8)
        {
            log.debug("伝票送信日が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02834,
                this.getClass().getName() + STR_METHOD_NAME,
                "伝票送信日が不正です。");
        }

        // ３-２） this.伝票送信日 != null AND this.伝票送信日が暦日でない場合、
        //       「伝票送信日が不正です。」の例外をスローする。
        if (this.voucherSendDate != null
            && !WEB3StringTypeUtility.isDateStr(this.voucherSendDate, "yyyyMMdd"))
        {
            log.debug("伝票送信日が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02834,
                this.getClass().getName() + STR_METHOD_NAME,
                "伝票送信日が不正です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecAccRegVoucherSearchResResponse(this);
    }
}
@
