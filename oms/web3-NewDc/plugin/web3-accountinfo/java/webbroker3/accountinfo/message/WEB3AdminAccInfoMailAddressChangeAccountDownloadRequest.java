head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
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
 * (管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest extends WEB3GenRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressChangeAccountDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082121L;

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
     * @@roseuid 418F385D01D4
     */
    public WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse(this);
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
     * ６）　@　@ソートキーのチェック  <BR>
     * 　@６－１）　@ソートキーが未入力lの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * 　@６－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * 　@６－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。  <BR>
     * 　@　@　@６－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@　@６－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 <BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * 　@　@　@　@ 顧客メールアドレス情報.部店コード<BR>
     * 　@　@　@　@ 顧客メールアドレス情報.顧客コード<BR>
     * 　@　@　@　@ 顧客メールアドレス情報.更新日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4147F4BC02CA
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            log.error("部店コード未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, getClass().getName() + STR_METHOD_NAME, "部店コード未入力");
        }
        if (this.startDate == null || "".equals(this.startDate))
        {
            log.error("開始日未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01149, getClass().getName() + STR_METHOD_NAME, "開始日未入力");
        }
        if (this.endDate == null || "".equals(this.endDate))
        {
            log.error("終了日未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01150, getClass().getName() + STR_METHOD_NAME, "終了日未入力");
        }
        if (WEB3DateUtility.compareToDay(this.startDate, this.endDate) >0)
        {
            log.error("（開始日 > 終了日）の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01151, getClass().getName() + STR_METHOD_NAME, "開始日 > 終了日");
        }

        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            this.pageIndex = "1";
        }
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.error("要求ページ番号数字以外の文字が含まれる場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, getClass().getName() + STR_METHOD_NAME, "要求ページ番号数字以外の文字");
        }
        if (Double.parseDouble(this.pageIndex) <= 0)
        {
            log.error("要求ページ番号マイナス値の文字が含まれる場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, getClass().getName() + STR_METHOD_NAME, "要求ページ番号マイナス値の文字");
        }

        if (this.pageSize == null || "".equals(this.pageSize))
        {
            log.error("ページ内表示行数未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, getClass().getName() + STR_METHOD_NAME, "ページ内表示行数未入力");
        }
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.error("ページ内表示行数数字以外の文字が含まれる場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, getClass().getName() + STR_METHOD_NAME, "ページ内表示行数数字以外の文字");
        }
        if (Double.parseDouble(this.pageSize) <= 0)
        {
            log.error("ページ内表示行数マイナス値の文字が含まれる場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, getClass().getName() + STR_METHOD_NAME, "ページ内表示行数マイナス値の文字");
        }
        
        if (this.sortKeys == null)
        {
            log.error("ソートキー未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, getClass().getName() + STR_METHOD_NAME, "ソートキー未入力");
        }
        if (this.sortKeys.length == 0)
        {
            log.error("（ソートキーの要素数 == 0）の場合、 例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, getClass().getName() + STR_METHOD_NAME, "ソートキーの要素数 == 0");
        }
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            if ((!WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(this.sortKeys[i].keyItem)))
            {
                log.error("ソートキー.キー項目が下記の項目名以外の場合");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    getClass().getName() + STR_METHOD_NAME, 
                    "ソートキー.キー項目が下記の項目名以外:部店コード, 顧客コード, 更新日");
            }
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
