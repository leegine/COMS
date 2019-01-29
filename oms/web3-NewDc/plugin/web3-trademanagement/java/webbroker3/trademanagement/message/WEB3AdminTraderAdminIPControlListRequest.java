head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP一覧リクエスト(WEB3AdminTraderAdminIPControlListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・ログイン拒否IP一覧リクエスト)<BR>
 * 管理者・ログイン拒否IP一覧リクエストクラス。<BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlListRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221750L;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数。<BR>
     */
    public String pageSize;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号。<BR>
     */
    public String pageIndex;

    /**
     * (ソートキー)<BR>
     */
    public WEB3AdminTraderAdminIPControlSortKey[] sortKeys;

    /**
     * @@roseuid 48D75CD60398
     */
    public WEB3AdminTraderAdminIPControlListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）ページ内表示行数チェック<BR>
     * 　@１－１）this.ページ内表示行数 == nullの場合、<BR>
     * 　@　@　@　@　@「ページ内表示行数の入力が不正です。」の例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00091<BR>
     * <BR>
     * 　@１－２）this.ページ内表示行数が半角数字以外の値であった場合、<BR>
     * 　@　@　@　@　@「ページ内表示行数が数字以外の値です。」の例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@１－３）this.ページ内表示行数 <= 0であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数の値が0以下です。」の例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00617<BR>
     * <BR>
     * ２）要求ページ番号チェック<BR>
     * 　@２－１）this.要求ページ番号 == nullの場合、<BR>
     * 　@　@　@　@　@「要求ページ番号が未指定です。」の例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@２－２）this.要求ページ番号が半角数字以外の値であった場合、<BR>
     * 　@　@　@　@　@「要求ページ番号が数字以外の値です。」の例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@２－３）this.要求ページ番号 <= 0であった場合、<BR>
     * 　@　@　@　@「要求ページ番号の値が0以下です。」の例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * ３）　@ソートキーチェック<BR>
     * 　@３－１）this.ソートキー == nullであった場合<BR>
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@３－２）this.ソートキー.length == 0だった場合<BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@３－３）this.ソートキーの全要素に対して下記のチェックを行う。<BR>
     * 　@　@３－３－１）ソートキー.validate()をコールする。<BR>
     * @@roseuid 48BE52B003A4
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）ページ内表示行数チェック
        //　@１－１）this.ページ内表示行数 == nullの場合、
        //　@　@　@　@　@「ページ内表示行数の入力が不正です。」の例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数の入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }

        //　@１－２）this.ページ内表示行数が半角数字以外の値であった場合、
        //　@　@　@　@　@「ページ内表示行数が数字以外の値です。」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        //　@１－３）this.ページ内表示行数 <= 0であった場合、
        //　@　@　@　@「ページ内表示行数の値が0以下です。」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }

        //２）要求ページ番号チェック
        //　@２－１）this.要求ページ番号 == nullの場合、
        //　@　@　@　@　@「要求ページ番号が未指定です。」の例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        //　@２－２）this.要求ページ番号が半角数字以外の値であった場合、
        //　@　@　@　@　@「要求ページ番号が数字以外の値です。」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        //　@２－３）this.要求ページ番号 <= 0であった場合、
        //　@　@　@　@「要求ページ番号の値が0以下です。」の例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        //３）　@ソートキーチェック
        //　@３－１）this.ソートキー == nullであった場合
        //　@　@　@　@「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーがnull");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        //　@３－２）this.ソートキー.length == 0だった場合
        //　@　@　@　@「ソートキー.要素数が0」の例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキー.要素数が0");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }

        //　@３－３）this.ソートキーの全要素に対して下記のチェックを行う。
        //　@　@３－３－１）ソートキー.validate()をコールする。
        int l_intLength = this.sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {
            this.sortKeys[i].validate();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminIPControlListResponse(this);
    }
}
@
