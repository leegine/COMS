head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherStatUpdConfRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・顧客情報登録伝票ステータス更新確認リクエスト(WEB3AdminDirSecAccRegVoucherStatUpdConfRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12 何文敏 (中訊) 新規作成 仕様変更 モデルNo.098
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
 * (管理者・顧客情報登録伝票ステータス更新確認リクエスト)<BR>
 * 管理者・顧客情報登録伝票ステータス更新確認リクエストクラス。<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdConfRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdConfRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dirsec_acc_reg_voucher_stat_upd_conf";

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
     * @@roseuid 466E0B6B0089
     */
    public WEB3AdminDirSecAccRegVoucherStatUpdConfRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １） 伝票作成状況チェック<BR>
     * 　@１-１） this.更新_伝票作成状況 == null の場合、<BR>
     * 　@　@　@　@　@「伝票作成状況を入力してください。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02825<BR>
     * <BR>
     * ２） エラー理由コードチェック<BR>
     * 　@２－１） this.更新_エラー理由コード == null の場合、<BR>
     * 　@　@　@　@　@「エラー理由コードを入力してください。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02826<BR>
     * <BR>
     * 　@２-２） this.更新_エラー理由コードの長さ > 4 OR<BR>
     * 　@　@　@　@　@this.更新_エラー理由コードが半角英数以外の場合、<BR>
     * 　@　@　@　@「エラー理由コードが不正です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02827<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4656454301E0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //当リクエストデータの整合性チェックを行う
        //（ただし、当クラス内で完結する簡易チェックのみとする。）
        //１） 伝票作成状況チェック
        //  １-１） this.更新_伝票作成状況 == null の場合
        //          「伝票作成状況を入力してください。」の例外をスローする
        if (this.updateVoucherMakeStatus == null)
        {
            log.debug("伝票作成状況を入力してください。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02825,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "伝票作成状況を入力してください。");
        }
        //２） エラー理由コードチェック
        //  ２－１） this.更新_エラー理由コード == null の場合
        //           「エラー理由コードを入力してください。」の例外をスローする
        if (this.updateErrorReasonCode == null)
        {
            log.debug("エラー理由コードを入力してください。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02826,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "エラー理由コードを入力してください。");
        }
        //  ２-２） this.更新_エラー理由コードの長さ > 4 OR
        //               this.更新_エラー理由コードが半角英数以外の場合
        //          「エラー理由コードが不正です。」の例外をスローする
        if (this.updateErrorReasonCode.length() > 4
            || !WEB3StringTypeUtility.isLetterOrDigit(this.updateErrorReasonCode))
        {
            log.debug("エラー理由コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "エラー理由コードが不正です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecAccRegVoucherStatUpdConfResponse(this);
    }
}
@
