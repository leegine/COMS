head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorAccountListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータ対象顧客一覧リクエスト(WEB3CCOperatorAccountListRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 周墨洋 (中訊) 新規作成・モデルNo.039
*/

package webbroker3.login.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.login.define.WEB3TraderAccountInfosSortKeyDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (CCオペレータ対象顧客一覧リクエスト)<BR>
 * CCオペレータ対象顧客一覧リクエストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3CCOperatorAccountListRequest extends WEB3GenRequest
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3CCOperatorAccountListRequest.class);

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231120L;

    /**
     * TAGNAME<BR>
     */
    public static final String TAGNAME = "request";

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "cc_operator_account_list";

    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String acceptCode;

    /**
     * (お名前(漢字))<BR>
     * お名前(漢字)
     */
    public String nameKanji;

    /**
     * (お名前(カナ))<BR>
     * お名前(カナ)
     */
    public String nameKana;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数
     */
    public String pageSize;

    /**
     * (ソートキー)<BR>
     * 対象顧客ソートキーオブジェクトの配列
     */
    public WEB3TraderAccountInfosSortKey[] sortKeys;

    /**
     * (CCオペレータ対象顧客一覧リクエスト)<BR>
     * コンストラクタ<BR>
     */
    public WEB3CCOperatorAccountListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）ソートキーチェック<BR>
     * 　@１－１）this.ソートキー == nullであった場合<BR>
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@１－２）this.ソートキー.length == 0だった場合<BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@１－３）this.ソートキーの全要素に対して<BR>
     * 　@　@　@　@下記のチェックを行う。<BR>
     * 　@　@１－３－１）ソートキー.validate()をコールする。<BR>
     * <BR>
     * 　@　@１－３－２）ソートキー.キー項目に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@・「顧客コード」<BR>
     * 　@　@・「名前」<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_00086<BR>
     * <BR>
     * ２）要求ページ番号チェック<BR>
     * 　@２－１）this.要求ページ番号 == nullであった場合、<BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@２－２）this.要求ページ番号が数字以外の値であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@２－３）this.要求ページ番号 ≦ 0であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_00616<BR>
     * <BR>
     * ３）ページ内表示行数チェック<BR>
     * 　@３－１）this.ページ内表示行数 == nullであった場合、<BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_02224<BR>
     * <BR>
     * 　@３－２）this.ページ内表示行数が数字以外の値であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@３－３）this.ページ内表示行数 ≦ 0であった場合、<BR>
     * 　@　@　@　@「'ページ内表示行数の値が0以下です。」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）ソートキーチェック
        // 　@１－１）this.ソートキー == nullであった場合
        // 　@　@　@　@「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        // 　@１－２）this.ソートキー.length == 0だった場合
        // 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。
        int l_intSortKeysLen = this.sortKeys.length;
        if (l_intSortKeysLen == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }

        // 　@１－３）this.ソートキーの全要素に対して
        // 　@　@　@　@下記のチェックを行う。
        for (int i = 0 ; i < l_intSortKeysLen; i++)
        {
            // 　@　@１－３－１）ソートキー.validate()をコールする。
            sortKeys[i].validate();

            // 　@　@１－３－２）ソートキー.キー項目に下記の項目以外が
            // 　@　@　@設定されていたら、
            // 　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。
            // 　@　@・「顧客コード」
            // 　@　@・「名前」
            if (!WEB3TraderAccountInfosSortKeyDef.ACCEPT_CODE.equals(sortKeys[i].keyItem)
                && !WEB3TraderAccountInfosSortKeyDef.NAME_KANA.equals(sortKeys[i].keyItem))
            {
                log.debug("ソートキーのキー項目の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }
        }

        // ２）要求ページ番号チェック
        // 　@２－１）this.要求ページ番号 == nullであった場合、
        // 　@　@　@　@「要求ページ番号がnull」の例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        // 　@２－２）this.要求ページ番号が数字以外の値であった場合、
        // 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        // 　@２－３）this.要求ページ番号 ≦ 0であった場合、
        // 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        // ３）ページ内表示行数チェック
        // 　@３－１）this.ページ内表示行数 == nullであった場合、
        // 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }

        // 　@３－２）this.ページ内表示行数が数字以外の値であった場合、
        // 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        // 　@３－３）this.ページ内表示行数 ≦ 0であった場合、
        // 　@　@　@　@「'ページ内表示行数の値が0以下です。」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3CCOperatorAccountListResponse(this);
    }

}
@
