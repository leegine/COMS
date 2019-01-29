head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdCommonRequest.java;


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
File Name           : サービス利用管理者外部連携ID照会共通ﾘｸｴｽﾄ(WEB3AdminSrvRegiOtherOrgIdCommonRequest.java)
Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338,No.347
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OtherOrgStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者外部連携ID照会共通ﾘｸｴｽﾄ)<BR>
 * サービス利用管理者外部連携ID照会共通ﾘｸｴｽﾄクラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdCommonRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101400L;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdCommonRequest.class);

    /**
     * (サービス区分)<BR>
     * サービス区分<BR>
     */
    public String serviceDiv;

    /**
     * (通番)<BR>
     * 通番<BR>
     */
    public String seqNumber;

    /**
     * (ID)<BR>
     * ID<BR>
     */
    public String id;

    /**
     * (ステータス)<BR>
     * ステータス<BR>
     */
    public String status;

    /**
     * (部店コード)<BR>
     * 部店コード一覧<BR>
     */
    public String[] branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (適用開始日（自）)<BR>
     * 適用開始日（自）<BR>
     */
    public Date appliStartFrom;

    /**
     * (適用開始日（至）)<BR>
     * 適用開始日（至）<BR>
     */
    public Date appliStartTo;

    /**
     * (サービス利用管理者外部連携ID照会共通ﾘｸｴｽﾄ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 47B926EF0037
     */
    public WEB3AdminSrvRegiOtherOrgIdCommonRequest()
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
     * 　@１－２）　@this.サービス区分!=nullであり、<BR>
     * 　@　@　@　@　@　@かつ桁数!=2桁の場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00831<BR>
     * 　@１－３）　@this.サービス区分!=nullであり、<BR>
     * 　@　@　@　@　@　@かつ半角数字以外が格納されている場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00882<BR>
     * <BR>
     * ２）　@通番のチェック<BR>
     * 　@２－１）　@this.通番!=nullであり、かつ桁数>18桁の場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_03054<BR>
     * 　@２－２）　@this.通番!=nullであり、かつ半角数字以外が格納されている場合、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_03055<BR>
     * <BR>
     * ３）　@IDのチェック<BR>
     * 　@３－１）　@this.ID!=nullであり、かつ桁数!=8桁の場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00954<BR>
     * <BR>
     * ４）　@ステータスのチェック <BR>
     * 　@４－１）　@this.ステータスが以下の値以外の場合、例外をスローする。 <BR>
     * 　@　@　@・0：使用中 <BR>
     * 　@　@　@・1：無効（削除）<BR>
     * 　@　@　@・9：未使用<BR>
     * 　@　@　@・null：全て<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00890<BR>
     * <BR>
     * ５）　@部店コードのチェック<BR>
     * 　@５－１）　@this.部店コード==nullの場合、<BR>
     * 　@　@　@　@　@　@またはthis.部店コードの要素数==0の場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_02174<BR>
     * <BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_02175<BR>
     * 　@５－２）　@this.部店コード!=nullであり、<BR>
     * 　@　@　@　@　@　@かつ桁数!=3桁の場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00834<BR>
     * 　@５－３）　@this.部店コード!=nullであり、<BR>
     * 　@　@　@　@　@　@かつ半角数字以外が格納されている場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_01729<BR>
     * <BR>
     * ６）　@顧客コードのチェック<BR>
     * 　@６－１）　@this.顧客コード!=nullであり、かつ桁数!=6桁の場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00836<BR>
     * 　@６－２）　@this.顧客コード!=nullであり、かつ半角数字以外が格納されている場合、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_01043<BR>
     * <BR>
     * ７）　@適用開始日のチェック<BR>
     * 　@７－１）　@this.適用開始日（自）!=nullであり、かつthis.適用開始日（至）!=nullで、<BR>
     * 　@　@　@　@　@　@適用開始日（自）>適用開始日（至）の場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_03068<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B3E3C80375
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
        if (this.serviceDiv != null && this.serviceDiv.length() != 2)
        {
            log.debug("サービス区分の桁数が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + "." + STR_METHOD_NAME,
                "サービス区分の桁数が不正です。");
        }

        // this.サービス区分!=nullであり、かつ半角数字以外が格納されている場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.serviceDiv))
        {
            log.debug("サービス区分が数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00882,
                getClass().getName() + "." + STR_METHOD_NAME,
                "サービス区分が数値以外の値です。");
        }

        //通番のチェック
        //this.通番!=nullであり、かつ桁数>18桁の場合、例外をスローする。
        if (this.seqNumber != null && this.seqNumber.length() > 18)
        {
            log.debug("通番のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03054,
                getClass().getName() + "." + STR_METHOD_NAME,
                "通番のサイズが不正です。");
        }

        //this.通番!=nullであり、かつ半角数字以外が格納されている場合、例外をスローする。
        if (this.seqNumber != null && !WEB3StringTypeUtility.isDigit(this.seqNumber))
        {
            log.debug("通番が数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03055,
                getClass().getName() + "." + STR_METHOD_NAME,
                "通番が数値以外の値です。");
        }

        //IDのチェック
        //this.ID!=nullであり、かつ桁数!=8桁の場合、例外をスローする。
        if (this.id != null && this.id.length() != 8)
        {
            log.debug("IDのサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                getClass().getName() + "." + STR_METHOD_NAME,
                "IDのサイズが不正です。");
        }

        //ステータスのチェック
        //this.ステータスが以下の値以外の場合、例外をスローする。
        // 　@　@・0：使用中
        // 　@　@・1：無効（削除)
        // 　@　@・9：未使用
        // 　@　@・null：全て
        if (!(this.status == null
            || WEB3OtherOrgStatusDef.USING.equals(this.status.trim())
            || WEB3OtherOrgStatusDef.INVALIDITY.equals(this.status.trim())
            || WEB3OtherOrgStatusDef.DEFAULT.equals(this.status.trim())))
        {
            log.debug("ステータスが存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00890,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ステータスが存在しないコード値です。");
        }

        //部店コードのチェック
        //this.部店コード==nullの場合、またはthis.部店コードの要素数==0の場合、例外をスローする。
        if (this.branchCode == null)
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードがnullです。");
        }
        else if (this.branchCode.length == 0)
        {
            log.debug("部店コードの要素数が0です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02175,
                getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの要素数が0です。");
        }

        int l_intCnt = this.branchCode.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            if (this.branchCode[i] != null)
            {
                //this.部店コード!=nullであり、かつ桁数!=3桁の場合、例外をスローする。
                if (this.branchCode[i].length() != 3)
                {
                    log.debug("部店コードのサイズが不正です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "部店コードのサイズが不正です。");
                }

                //this.部店コード!=nullであり、かつ数値以外が格納されている場合、
                //例外をスローする。
                if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
                {
                    log.debug("部店コードが数値以外の値です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "部店コードが数値以外の値です。");
                }
            }
        }

        //顧客コードのチェック
        //this.顧客コード!=nullであり、かつ桁数!=6桁の場合、例外をスローする。 
        if (this.accountCode != null && this.accountCode.length() != 6)
        {
            log.debug("顧客コードのサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードのサイズが不正です。");
        }

        //this.顧客コード!=nullであり、かつ半角数字以外が格納されている場合、例外をスローする。
        if (this.accountCode != null && !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("顧客コードの値が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードの値が数字以外の値です。");
        }

        //適用開始日のチェック
        //this.適用開始日（自）!=nullであり、かつthis.適用開始日（至）!=nullで、
        //適用開始日（自）>適用開始日（至）の場合、例外をスローする。
        if (this.appliStartFrom != null && this.appliStartTo != null)
        {
            if (WEB3DateUtility.compareToDay(this.appliStartFrom, this.appliStartTo) > 0)
            {
                log.debug("適用開始日（自）が適用開始日（至）より未来日付です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03068,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "適用開始日（自）が適用開始日（至）より未来日付です。");
            }
        }
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
}@
