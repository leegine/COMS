head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_loginPasswordChangeAccountDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082126L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String[] branchCode;

    /**
     * (開始日)<BR>
     * 開始日（自）<BR>
     */
    public Date startDate;

    /**
     * (終了日)<BR>
     * 終了日（自）<BR>
     */
    public Date endDate;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;

    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3AccInfoSortKey[] sortKeys;

    /**
     * @@roseuid 418F3854035B
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest()
    {

    }
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse(this);
    }

    /**
     * (validate)<BR>
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）　@開始日のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01149<BR>
     * <BR>
     * ３）　@終了日のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01150<BR>
     * 　@３－２）　@（開始日 > 終了日）の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01151<BR>
     * <BR>
     * ４）　@要求ページ番号チェック <BR>
     * 　@４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * 　@４－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ５）　@ページ内表示行数チェック <BR>
     * 　@５－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * 　@５－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * ６）　@ソートキーのチェック  <BR>
     * 　@６－１）　@ソートキーが未入力lの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * 　@６－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00813<BR>
     * 　@６－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。  <BR>
     * 　@　@　@６－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@　@６－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 <BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01152<BR>
     * 　@　@　@　@ パスワード変更顧客情報.部店コード<BR>
     * 　@　@　@　@ パスワード変更顧客情報情報.顧客コード<BR>
     * 　@　@　@　@ パスワード変更顧客情報情報.更新日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4147D58300B7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@部店コードのチェック
        //１－１）　@未入力の場合、例外をスローする。
        if (this.branchCode == null || branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME,
                " 部店コードが未指定です");
        }

        //２）　@開始日のチェック
        //２－１）　@未入力の場合、例外をスローする。
        if (this.startDate == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01149,
                this.getClass().getName() + STR_METHOD_NAME,
                " 開始日を入力しません");
        }

        //３）　@終了日のチェック
        //３－１）　@未入力の場合、例外をスローする。
        if (this.endDate == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01150,
                this.getClass().getName() + STR_METHOD_NAME,
                " 終了日を入力しません");
        }

        //３－２）　@（開始日 > 終了日）の場合、例外をスローする。
        if (WEB3DateUtility.compareToDay(this.startDate, this.endDate) > 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01151,
                this.getClass().getName() + STR_METHOD_NAME,
                " （開始日 > 終了日）です");
        }

        //４）　@要求ページ番号チェック
        //４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            this.pageIndex = "1";
        }

        //４－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                " 要求ページ番号が数字以外の値です");
        }

        //４－３）　@マイナス値の場合、例外をスローする。
        int l_lngPageIndex = Integer.parseInt(this.pageIndex);
        if (l_lngPageIndex <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                " 要求ページ番号の値が0以下です");
        }

        //５）　@ページ内表示行数チェック
        //５－１）　@未入力の場合、例外をスローする。
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                " ページ内表示行数の入力が不正です");
        }

        //５－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                " ページ内表示行数が数字以外の値です");
        }

        //５－３）　@マイナス値の場合、例外をスローする。
        int l_lngPageSize = Integer.parseInt(this.pageSize);
        if (l_lngPageSize <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                " ページ内表示行数の値が0以下です");
        }

        //６）　@ソートキーのチェック
        //６－１）　@ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                " ソートキーが未指定です");
        }

        //６－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                " ソートキーの要素数が０です");
        }

        //６－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。
        for (int i = 0; i < this.sortKeys.length; i++)
        {

            //６－３－１）　@ソートキー.validate()をコールする。
            this.sortKeys[i].validate();

            //６－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。
            //パスワード変更顧客情報.部店コード
            //パスワード変更顧客情報情報.顧客コード
            //パスワード変更顧客情報情報.更新日

            if (!WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ソートキーのキー項目の値が存在しないコード値です");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
