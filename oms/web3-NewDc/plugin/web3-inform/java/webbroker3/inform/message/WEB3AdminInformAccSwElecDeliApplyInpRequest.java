head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliApplyInpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・口座切替・電子交付申込入力リクエスト(WEB3AdminInformAccSwElecDeliApplyInpRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 孫洪江 (中訊) 新規作成 仕様変更モデル097
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・口座切替・電子交付申込入力リクエスト)<BR>
 * 管理者・口座切替・電子交付申込入力リクエストクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliApplyInpRequest extends WEB3GenRequest
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformAccSwElecDeliApplyInpRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_inform_acc_sw_elec_deli_apply_inp";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707182111L;

    /**
     * (連絡種別)<BR>
     */
    public String informType;

    /**
     * (部店コード)<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     */
    public String accountCode;

    /**
     * (識別コード)<BR>
     */
    public String requestNumber;

    public WEB3AdminInformAccSwElecDeliApplyInpRequest()
    {

    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00833<BR>
     * 　@１－２）　@桁数が3でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00834<BR>
     * 　@１－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01729<BR>
     * <BR>
     * ２）　@顧客コードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00835<BR>
     * 　@２－２）　@桁数が6でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00836<BR>
     * 　@２－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01043<BR>
     * <BR>
     * ３）　@連絡種別のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01817<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        // １）　@部店コードのチェック
        // 　@１－１）　@未入力の場合、例外をスローする。
        if (this.branchCode == null)
        {
            log.debug("部店コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }

        // 　@１－２）　@桁数が3でない場合、例外をスローする。
        if (this.branchCode.length() != 3)
        {
            log.debug("部店コードのサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードのサイズが不正です。");
        }

        // 　@１－３）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!(WEB3StringTypeUtility.isDigit(this.branchCode)))
        {
            log.debug("部店コードが数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが数値以外の値です。");
        }

        // ２）　@顧客コードのチェック
        // 　@２－１）　@未入力の場合、例外をスローする。
        if (this.accountCode == null)
        {
            log.debug("顧客コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードが未指定です。");
        }

        // 　@２－２）　@桁数が6でない場合、例外をスローする。
        if (this.accountCode.length() != 6)
        {
            log.debug("顧客コードのサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードのサイズが不正です。");
        }

        // 　@２－３）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!(WEB3StringTypeUtility.isDigit(this.accountCode)))
        {
            log.debug("顧客コードの値が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードの値が数字以外の値です。");
        }

        // ３）　@連絡種別のチェック
        // 　@３－１）　@未入力の場合、例外をスローする。
        if (this.informType == null)
        {
            log.debug("連絡種別が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連絡種別が未指定です。");
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
        return new WEB3AdminInformAccSwElecDeliApplyInpResponse(this);
    }
}
@
