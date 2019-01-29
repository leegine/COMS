head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliDeleteCmpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・口座切替・電子交付申込取消完了リクエスト(WEB3AdminInformAccSwElecDeliDeleteCmpRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 趙林鵬(中訊) 新規作成 モデルNo.110
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
 * (管理者・口座切替・電子交付申込取消完了リクエスト)<BR>
 * 管理者・口座切替・電子交付申込取消完了リクエスト<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminInformAccSwElecDeliDeleteCmpRequest extends WEB3GenRequest
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformAccSwElecDeliDeleteCmpRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_acc_sw_elec_deli_delete_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709191020L;

    /**
     * (連絡種別)<BR>
     * 連絡種別<BR>
     */
    public String informType;

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
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * 管理者・口座切替・電子交付申込取消完了リクエスト<BR>
     */
    public WEB3AdminInformAccSwElecDeliDeleteCmpRequest()
    {

    }

    /**
     * ストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@１－２）　@桁数が3でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00834<BR>
     * <BR>
     * 　@１－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01729<BR>
     * <BR>
     * ２）　@顧客コードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00835<BR>
     * <BR>
     * 　@２－２）　@桁数が6でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00836<BR>
     * <BR>
     * 　@２－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01043<BR>
     * <BR>
     * ３）　@連絡種別のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01817<BR>
     * <BR>
     * ４）　@識別コードのチェック<BR>
     * 　@４－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00829<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //部店コードのチェック
        //未入力の場合、例外をスローする。
        if (this.branchCode == null)
        {
            log.debug("銘柄コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードが未指定です。");
        }

        //桁数が3でない場合、例外をスローする。
        if (this.branchCode.length() != 3)
        {
            log.debug("部店コードのサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードのサイズが不正です。");
        }

        //数字以外の文字が含まれる場合、例外をスローする。
        if (!(WEB3StringTypeUtility.isDigit(this.branchCode)))
        {
            log.debug("部店コードが数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが数値以外の値です。");
        }

        // 顧客コードのチェック
        //未入力の場合、例外をスローする。
        if (this.accountCode == null)
        {
            log.debug("顧客コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードが未指定です。");
        }

        // 桁数が6でない場合、例外をスローする。
        if (this.accountCode.length() != 6)
        {
            log.debug("顧客コードのサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードのサイズが不正です。");
        }

        //数字以外の文字が含まれる場合、例外をスローする。<BR>
        if (!(WEB3StringTypeUtility.isDigit(this.accountCode)))
        {
            log.debug("顧客コードの値が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードの値が数字以外の値です。");
        }

        // 連絡種別のチェック
        // 未入力の場合、例外をスローする。
        if (this.informType == null)
        {
            log.debug("連絡種別が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連絡種別が未指定です。");
        }

        //識別コードのチェック
        //未入力の場合、例外をスローする。
        if (this.requestNumber == null)
        {
            log.debug("識別コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "識別コードが未指定です。");
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
        return new WEB3AdminInformAccSwElecDeliDeleteCmpResponse(this);
    }
}
@
