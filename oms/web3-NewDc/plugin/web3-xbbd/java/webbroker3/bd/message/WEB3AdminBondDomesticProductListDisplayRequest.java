head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.56.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductListDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄一覧画面表示リクエスト(WEB3AdminBondDomesticProductListDisplayRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 周墨洋 (中訊) 新規作成 モデル192
*/

package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondSortKeyDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者国内債券銘柄一覧画面表示リクエスト)<BR>
 * 管理者国内債券銘柄一覧画面表示リクエスト<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListDisplayRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductListDisplayRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_product_list_display";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20070709100000L;

    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3BondSortKey[] sortKeys;

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
     * (検索条件)<BR>
     * 検索条件<BR>
     */
    public WEB3AdminBondDomesticProductListSearchConditionUnit searchCondition;

    /**
     * @@roseuid 4691C5EB03E0
     */
    public WEB3AdminBondDomesticProductListDisplayRequest()
    {

    }

    /**
     * 当クラスの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）ソートキーチェック<BR>
     * 　@１－１）ソートキー == null の場合、<BR>
     * 　@　@「ソートキーがnull」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@１－２）ソートキーの要素数が0の場合、<BR>
     * 　@　@「ソートキーの要素数が0」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@１－３）ソートキーの配列の個数分、<BR>
     * 　@　@　@　@　@　@繰り返して以下のチェックを行う。 <BR>
     * <BR>
     * 　@　@１－３－１）ソートキー.validate()をコールする。<BR>
     * <BR>
     * 　@　@１－３－２）ソートキー.キー項目に銘柄コード、回号コード、発行日、償還日、<BR>
     * 　@　@　@　@　@　@　@　@利率　@以外が存在した場合、<BR>
     * 　@　@　@　@　@　@　@　@「ソートキーのキー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_00086<BR>
     * <BR>
     * ２）要求ページ番号チェック<BR>
     * 　@２－１）要求ページ番号 == null の場合、<BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@２－２）要求ページ番号が数字以外の値であった場合、 <BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。 <BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@２－３）要求ページ番号≦０ の場合、<BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_00616<BR>
     * <BR>
     * ３）ページ内表示行数チェック <BR>
     * 　@３－１）ページ内表示行数 == null の場合、 <BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。 <BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02224<BR>
     * <BR>
     * 　@３－２）ページ内表示行数が数字以外の値であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。 <BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@３－３）ページ内表示行数≦０ の場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_00617<BR>
     * <BR>
     * ４）検索条件チェック<BR>
     * 　@４－１）管理者国内債券銘柄一覧検索条件.validate()をコールする。<BR>
     * <BR>
     * @@roseuid 46636FE40334
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）ソートキーチェック
        //　@１－１）ソートキー == null の場合、
        //　@　@「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        //　@１－２）ソートキーの要素数が0の場合、
        //　@　@「ソートキーの要素数が0」の例外をスローする。
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

        //　@１－３）ソートキーの配列の個数分、
        //　@　@　@　@　@　@繰り返して以下のチェックを行う。
        for (int i = 0 ; i < l_intSortKeysLen; i++)
        {
            //　@　@１－３－１）ソートキー.validate()をコールする。
            sortKeys[i].validate();

            //　@　@１－３－２）ソートキー.キー項目に銘柄コード、回号コード、発行日、償還日、
            //　@　@　@　@　@　@　@　@利率　@以外が存在した場合、
            //　@　@　@　@　@　@　@　@「ソートキーのキー項目が未定義の値」の例外をスローする。
            if (!WEB3BondSortKeyDef.PRODUCT_CODE.equals(sortKeys[i].keyItem)
                && !WEB3BondSortKeyDef.PRODUCT_ISSUE_CODE.equals(sortKeys[i].keyItem)
                && !WEB3BondSortKeyDef.ISSUE_DATE.equals(sortKeys[i].keyItem)
                && !WEB3BondSortKeyDef.MATURITY_DATE.equals(sortKeys[i].keyItem)
                && !WEB3BondSortKeyDef.COUPON.equals(sortKeys[i].keyItem))
            {
                log.debug("ソートキーのキー項目の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }
        }

        //２）要求ページ番号チェック
        //　@２－１）要求ページ番号 == null の場合、
        //　@　@　@　@「要求ページ番号がnull」の例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        //　@２－２）要求ページ番号が数字以外の値であった場合、
        //　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        //　@２－３）要求ページ番号≦０ の場合、
        //　@　@　@　@「要求ページ番号が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        //３）ページ内表示行数チェック
        //　@３－１）ページ内表示行数 == null の場合、
        //　@　@　@　@「ページ内表示行数がnull」の例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }

        //　@３－２）ページ内表示行数が数字以外の値であった場合、
        //　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        //　@３－３）ページ内表示行数≦０ の場合、
        //　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }

        //４）検索条件チェック
        //　@４－１）管理者国内債券銘柄一覧検索条件.validate()をコールする。
        searchCondition.validate();


        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 管理者国内債券銘柄一覧画面表示レスポンスオブジェクトを生成して返す。
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
         return new WEB3AdminBondDomesticProductListDisplayResponse(this);
    }

}
@
