head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListReferenceRequest.java;


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
File Name           : サービス利用管理者外部連携ID一覧照会ﾘｸｴｽﾄ(WEB3AdminSrvRegiOtherOrgIdListReferenceRequest.java)
Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者外部連携ID一覧照会ﾘｸｴｽﾄ)<BR>
 * サービス利用管理者外部連携ID一覧照会ﾘｸｴｽﾄクラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdListReferenceRequest extends WEB3AdminSrvRegiOtherOrgIdCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgId_list_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101403L;

    /**
     * Logger<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdListReferenceRequest.class);

    /**
     * (サービス利用ソートキー)<BR>
     * 対象項目<BR>
     * 　@通番／ID／ステータス／部店コード／口座コード／適用期間From<BR>
     * 　@　@　@/適用期間To／最終更新日／最終更新者<BR>
     */
    public WEB3SrvRegiSortKey[] sortKeys;

    /**
     * (要求ページ番号)<BR>
     * 表示させたいページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * 1ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;

    /**
     * (サービス利用管理者外部連携ID一覧照会ﾘｸｴｽﾄ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 47B8D1790005
     */
    public WEB3AdminSrvRegiOtherOrgIdListReferenceRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す。<BR>
     * <BR>
     * ２）　@サービス利用ソートキーのチェック<BR>
     * 　@２－１）　@this.サービス利用ソートキー==nullの場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00231<BR>
     * 　@２－２）　@this.サービス利用ソートキーの要素数==0の場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00232<BR>
     * 　@２－３）　@this.サービス利用ソートキーの要素数分、以下を繰り返す。<BR>
     * 　@　@２－３－１）　@this.サービス利用ソートキー.キー項目==nullの場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00085<BR>
     * 　@　@２－３－２）　@this.サービス利用ソートキー.昇順／降順==nullの場合、例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00087<BR>
     * 　@　@２－３－３）　@this.サービス利用ソートキー.昇順／降順が以下の値以外だった場合、<BR>
     * 　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@"A:昇順"<BR>
     * 　@　@　@"D:降順"<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00088<BR>
     * <BR>
     * ３） 要求ページ番号チェック<BR>
     * 　@３－１）　@this.要求ページ番号==nullの値であれば例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00089<BR>
     * 　@３－２）　@this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00090<BR>
     * <BR>
     * ４）　@ページ内表示行数チェック<BR>
     * 　@４－１）　@this.ページ内表示行数==nullの値であれば例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_02224<BR>
     * 　@４－２）　@this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00092<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B3DE9D0287
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //スーパークラスのvalidateメソッドを呼び出す。
        super.validate();

        //サービス利用ソートキーのチェック
        //this.サービス利用ソートキー==nullの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        //this.サービス利用ソートキーの要素数==0の場合、例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }

        //this.サービス利用ソートキーの要素数分、以下を繰り返す。
        int l_intCnt = this.sortKeys.length;
        for (int i = 0;i < l_intCnt;i++)
        {
            //this.サービス利用ソートキー.キー項目==nullの場合、例外をスローする。
            if (this.sortKeys[i].keyItem == null)
            {
                log.debug("ソートキーのキー項目が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目が未指定です。");
            }

            // this.サービス利用ソートキー.昇順／降順==nullの場合、例外をスローする。
            if (this.sortKeys[i].ascDesc == null)
            {
                log.debug("昇順／降順が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が未指定です。");
            }

            // this.サービス利用ソートキー.昇順／降順が以下の値以外だった場合、例外をスローする。
            // 　@"A:昇順"
            // 　@"D:降順"
            if (!(WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc.trim())
                    || WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc.trim())))
            {
                log.debug("昇順／降順が”A：昇順”、”D：降順”以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            }
        }

        //要求ページ番号チェック
        //this.要求ページ番号==nullの値であれば例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        //this.要求ページ番号が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        //ページ内表示行数チェック
        //this.ページ内表示行数==nullの値であれば例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }

        // this.ページ内表示行数が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
    }

    /**
     * (createレスポンス)<BR>
     * サービス利用管理者外部連携ID一覧照会レスポンスを生成して返却する。<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 47B8D19802B0
     */
    public WEB3GenResponse createResponse()
    {
     return new WEB3AdminSrvRegiOtherOrgIdListReferenceResponse(this);
    }
}
@
