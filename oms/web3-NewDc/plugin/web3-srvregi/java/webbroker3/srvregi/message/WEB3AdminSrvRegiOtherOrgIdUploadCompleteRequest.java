head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest.java;


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
File Name           : サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄ(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest.java)
Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338,No.347
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiUploadDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄ)<BR>
 * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄクラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_upload_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101409L;

    /**
     * Logger<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest.class);

    /**
     * (サービス区分)<BR>
     * サービス区分<BR>
     */
    public String serviceDiv;

    /**
     * (アップロードID)<BR>
     * アップロードID<BR>
     */
    public String uploadId;

    /**
     * (アップロード区分)<BR>
     * アップロード区分<BR>
     */
    public String uploadDiv;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 47B8D764014C
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest()
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
     * ２）　@アップロードIDのチェック<BR>
     * 　@this.アップロードID==nullの場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00973<BR>
     * <BR>
     * ３）　@アップロード区分のチェック<BR>
     * 　@３－１）　@this.アップロード区分==nullの場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_03056<BR>
     * 　@３－２）　@this.アップロード区分!=nullであり、かつ以下の値に該当しない場合、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@　@・0：登録<BR>
     * 　@　@　@　@　@　@・1：変更<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_01020<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B3EF9E00BB
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

        //this.サービス区分!=nullであり、かつ半角数字以外が格納されている場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.serviceDiv))
        {
            log.debug("サービス区分が数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00882,
                getClass().getName() + "." + STR_METHOD_NAME,
                "サービス区分が数値以外の値です。");
        }

        //アップロードIDのチェック
        //this.アップロードID==nullの場合、例外をスローする。
        if (this.uploadId == null)
        {
            log.debug("アップロードIDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                getClass().getName() + "." + STR_METHOD_NAME,
                "アップロードIDが未指定です。");
        }

        //アップロード区分のチェック
        //this.アップロード区分==nullの場合、例外をスローする。
        if (this.uploadDiv == null)
        {
            log.debug("アップロード区分が未指定です。。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03056,
                getClass().getName() + "." + STR_METHOD_NAME,
                "アップロード区分が未指定です。");
        }

        //this.アップロード区分!=nullであり、かつ以下の値に該当しない場合、例外をスローする。
        // 　@　@　@　@　@　@・0：登録
        // 　@　@　@　@　@　@・1：変更
        if (!WEB3SrvRegiUploadDivDef.REGIST_RECORD.equals(this.uploadDiv.trim())
            && !WEB3SrvRegiUploadDivDef.CHANGE_RECORD.equals(this.uploadDiv.trim()))
        {
            log.debug("アップロード区分の値が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01020,
                getClass().getName() + "." + STR_METHOD_NAME,
                "アップロード区分の値が不正です。");
        }
    }

    /**
     * (createレスポンス)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 47B8D76B0033
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse(this);
    }
}
@
