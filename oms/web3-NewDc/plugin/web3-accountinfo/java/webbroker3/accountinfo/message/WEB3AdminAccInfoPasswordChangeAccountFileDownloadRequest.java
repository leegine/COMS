head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報暗証番号変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ(WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 張宝楠 (中訊) 新規作成
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


/**
 * (管理者お客様情報暗証番号変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報暗証番号変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_passwordChangeAccountFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082107L;

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
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3AccInfoSortKey[] sortKeys;

    /**
     * @@roseuid 418F38600177
     */
    public WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse(this);
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
     * ４）　@ソートキーのチェック  <BR>
     * 　@４－１）　@ソートキーが未入力lの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * 　@４－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * 　@４－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。  <BR>
     * 　@　@　@４－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@　@４－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、<BR>
     *  例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * 　@　@　@　@ 暗証番号変更顧客情報.部店コード<BR>
     * 　@　@　@　@ 暗証番号変更顧客情報情報.顧客コード<BR>
     * 　@　@　@　@ 暗証番号変更顧客情報情報.更新日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 416B7FBE0357
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
                "部店コード未入力");
        }

        //２）　@開始日のチェック
        //２－１）　@未入力の場合、例外をスローする。
        if (this.startDate == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01149,
                this.getClass().getName() + STR_METHOD_NAME,
                "開始日未入力");
        }

        //３）　@終了日のチェック
        //３－１）　@未入力の場合、例外をスローする。
        if (this.endDate == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01150,
                this.getClass().getName() + STR_METHOD_NAME,
                "終了日未入力");
        }

        //３－２）　@（開始日 > 終了日）の場合、例外をスローする。
        if (WEB3DateUtility.compareToDay(this.startDate, this.endDate) > 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01151,
                this.getClass().getName() + STR_METHOD_NAME,
                "開始日 > 終了日");
        }

        //４）　@ソートキーのチェック
        //４－１）　@ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキー未入力");
        }

        //４－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーの要素数 == 0");
        }

        //４－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。
        for (int i = 0; i < this.sortKeys.length; i++)
        {

            //４－３－１）　@ソートキー.validate()をコールする。
            this.sortKeys[i].validate();

            //４－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。
            //暗証番号変更顧客情報.部店コード
            //暗証番号変更顧客情報情報.顧客コード
            //暗証番号変更顧客情報情報.更新日

            if (!WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキー.キー項目が下記の項目名以外:部店コード, 顧客コード, 更新日");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
