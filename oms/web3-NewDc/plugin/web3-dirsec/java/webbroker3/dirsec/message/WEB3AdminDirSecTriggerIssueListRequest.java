head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・トリガー発行処理一覧リクエスト(WEB3AdminDirSecTriggerIssueListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 許丹(中訊) 新規作成 モデルNo.116、No.118、No.122、No.123
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・トリガー発行処理一覧リクエスト)<BR>
 * 管理者・トリガー発行処理一覧リクエストクラス。<BR>
 * <BR>
 * @@author 許丹
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueListRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dir_sec_trigger_issue_list";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804171518L;

    /**
     * (ページ内表示行数)<BR>
     * トリガー発行JOB一覧画面で表示する行数を保持。<BR>
     */
    public String pageSize;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号。<BR>
     */
    public String pageIndex;

    /**
     * (ソートキー)<BR>
     */
    public WEB3AdminDirSecTriggerIssueSortKey[] sortKeys;

    /**
     * @@roseuid 4806E0540031
     */
    public WEB3AdminDirSecTriggerIssueListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）ページ内表示行数チェック<BR>
     * 　@１－１）this.ページ内表示行数 == nullの場合、<BR>
     * 　@　@　@　@　@「ページ内表示行数の入力が不正です。」の例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00091<BR>
     * <BR>
     * 　@１－２）this.ページ内表示行数が数字以外の値であった場合、<BR>
     * 　@　@　@　@　@「ページ内表示行数が数字以外の値です。」の例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@１－３）this.ページ内表示行数 <= 0であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数の値が0以下です。」の例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00617<BR>
     * <BR>
     * ２）表示番号チェック<BR>
     * 　@２－１）this.表示ページ番号 == nullの場合、<BR>
     * 　@　@　@　@　@「要求ページ番号が未指定です。」の例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@２－２）this.表示ページ番号が数字以外の値であった場合、<BR>
     * 　@　@　@　@　@「要求ページ番号が数字以外の値です。」の例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@２－３）this.表示ページ番号 <= 0であった場合、<BR>
     * 　@　@　@　@「要求ページ番号の値が0以下です。」の例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00616<BR>
     * <BR>
     * ３）　@ソートキーチェック<BR>
     * 　@３－１）this.トリガー発行ソートキー == nullであった場合<BR>
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@３－２）this.トリガー発行ソートキー.length == 0だった場合<BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@３－３）this.トリガー発行ソートキーの全要素に対して<BR>
     * 　@　@　@　@下記のチェックを行う。<BR>
     * 　@　@３－３－１）トリガー発行ソートキー.validate()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47CFAA6601F3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //ページ内表示行数チェック
        //this.ページ内表示行数 == nullの場合、
        //「ページ内表示行数の入力が不正です。」の例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数の入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }

        //this.ページ内表示行数が数字以外の値であった場合、
        //「ページ内表示行数が数字以外の値です。」の例外をスローする。
        else if (!(WEB3StringTypeUtility.isInteger(this.pageSize)))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        //this.ページ内表示行数 <= 0であった場合、
        //「ページ内表示行数の値が0以下です。」の例外をスローする。
        else if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }

        //表示番号チェック
        //this.表示ページ番号 == nullの場合、
        //「要求ページ番号が未指定です。」の例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        //this.表示ページ番号が数字以外の値であった場合、
        //「要求ページ番号が数字以外の値です。」の例外をスローする。
        else if (!(WEB3StringTypeUtility.isInteger(this.pageIndex)))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        //this.表示ページ番号 <= 0であった場合、
        //「要求ページ番号の値が0以下です。」の例外をスローする。
        else if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        //ソートキーチェック
        //this.トリガー発行ソートキー == nullであった場合
        //「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        //this.トリガー発行ソートキー.length == 0だった場合
        //「ソートキー.要素数が0」の例外をスローする。

        else if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }

        //this.トリガー発行ソートキーの全要素に対して
        //下記のチェックを行う。
        //トリガー発行ソートキー.validate()をコールする。
        else
        {
            int l_intLength = this.sortKeys.length;
            for (int i = 0; i < l_intLength; i++)
            {
                this.sortKeys[i].validate();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecTriggerIssueListResponse(this);
    }
}
@
