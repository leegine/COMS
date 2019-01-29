head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
Author Name         : Daiwa Institute of Research
File Name           : サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄ(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest.java)
Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338,No.347
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄ)<BR>
 * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄクラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_upload_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101411L;

    /**
     * Logger<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest.class);

    /**
     * (サービス区分)<BR>
     * サービス区分<BR>
     */
    public String serviceDiv;

    /**
     * (アップロードファ@イル)<BR>
     * アップロードファ@イル<BR>
     */
    public String[] lines;

    /**
     * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 47B8D72F02A2
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@サービス区分のチェック<BR>
     * 　@１－１）　@this.サービス区分==nullの場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00758<BR>
     * 　@１－２）　@this.サービス区分!=nullであり、かつ桁数!=2桁の場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00831<BR>
     * 　@１－３）　@this.サービス区分!=nullであり、かつ半角数字以外が格納されている場合、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00882<BR>
     * <BR>
     * ２）　@アップロードファ@イルのチェック<BR>
     * 　@this.アップロードファ@イル==nullの場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00976<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B3EF7502C5
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //サービス区分のチェック
        //this.サービス区分==nullの場合、例外をスローする。
        if (this.serviceDiv == null)
        {
            log.debug("サービス区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + "." + STR_METHOD_NAME,
                "サービス区分が未指定です。");
        }

        //this.サービス区分!=nullであり、かつ桁数!=2桁の場合、例外をスローする。
        if (this.serviceDiv.length() != 2)
        {
            log.debug("サービス区分の桁数が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + "." + STR_METHOD_NAME,
                "サービス区分の桁数が不正です。");
        }

        //this.サービス区分!=nullであり、かつ半角数字以外が格納されている場合、
        //例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.serviceDiv))
        {
            log.debug("サービス区分が数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00882,
                getClass().getName() + "." + STR_METHOD_NAME,
                "サービス区分が数値以外の値です。");
        }

        //アップロードファ@イルのチェック
        //this.アップロードファ@イル==nullの場合、例外をスローする。
        if (this.lines == null)
        {
            log.debug("アップロードファ@イルが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                getClass().getName() + "." + STR_METHOD_NAME,
                "アップロードファ@イルが未指定です。");
        }
    }

    /**
     * (createレスポンス)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 47B8D73C01A6
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse(this);
    }
}
@
