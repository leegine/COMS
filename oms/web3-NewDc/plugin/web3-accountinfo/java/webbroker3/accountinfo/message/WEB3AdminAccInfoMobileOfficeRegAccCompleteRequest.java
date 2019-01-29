head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了リクエスト(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/14 周捷 (中訊) 新規作成 モデルNo.153
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3JudgmentResultDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了リクエスト)<BR>
 * 管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了リクエストクラス
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegAccComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200612141400L;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest.class);

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * (部店コード)<BR>
     * 部店コードの配列<BR>
     */
    public String[] branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コードの配列<BR>
     */
    public String[] accountCode;

    /**
     * (判定結果区分)<BR>
     * 判定結果区分<BR>
     * <BR>
     * 1：　@承認<BR>
     * 2：　@不可<BR>
     */
    public String judgmentResultDiv;

    /**
     * リクエストデータの整合性をチェックする。 <BR>
     * <BR>
     * １）　@部店コードのチェック <BR>
     * 　@１－１）　@未入力の場合、例外をスローする。  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）　@顧客コードのチェック <BR>
     * 　@２－１）　@未入力の場合、例外をスローする。  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * <BR>
     * ３）　@判定結果区分のチェック <BR>
     * 　@３－１）　@未入力の場合、例外をスローする。  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01157<BR>
     * <BR>
     * 　@３－２）　@不正なコード値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01158<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@部店コードのチェック
        //１－１）　@未入力の場合、例外をスローする。
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            log.debug("部店コード未入力の場合、例外をスロー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }

        //２）　@顧客コードのチェック
        //２－１）　@未入力の場合、例外をスローする。
        if (this.accountCode == null || this.accountCode.length == 0)
        {
            log.debug("顧客コード未入力の場合、例外をスロー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                getClass().getName() + STR_METHOD_NAME,
                "顧客コードが未指定です。");
        }

        //３）　@判定結果区分のチェック
        //３－１）　@未入力の場合、例外をスローする。
        if (this.judgmentResultDiv == null)
        {
            log.debug("判定結果区分未入力の場合、例外をスロー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01157,
                getClass().getName() + STR_METHOD_NAME,
                "判定結果区分が未指定です。");
        }

        //３－２）　@不正なコード値の場合、例外をスローする。
        else if (!WEB3JudgmentResultDivDef.CONSENT.equals(judgmentResultDiv)
            && !WEB3JudgmentResultDivDef.IMPOSSIBILITY.equals(judgmentResultDiv))
        {
            log.debug("判定結果区分が不正なコード値の場合、例外をスロー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01158,
                getClass().getName() + STR_METHOD_NAME,
                "判定結果区分が存在しないコード値です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse(this);
    }
}
@
