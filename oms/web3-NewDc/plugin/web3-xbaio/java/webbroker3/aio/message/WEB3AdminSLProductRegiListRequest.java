head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegiListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録一覧リクエストクラス(WEB3AdminSLProductRegiListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 張騰宇 (中訊) 新規作成 モデル 760
Revision History : 2007/11/08 トウ鋒鋼 (中訊) モデル822
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (担保銘柄登録一覧リクエスト)<BR>
 * 担保銘柄登録一覧リクエストクラス<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminSLProductRegiListRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLProductRegiListRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_sl_product_regi_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709141544L;

    /**
     * (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * <BR>
     * 0：その他<BR>
     * 1：株式<BR>
     * 2：債券<BR>
     * 3：投資信託<BR>
     * 4：外国株<BR>
     * 5：現金<BR>
     * 6：先物オプション<BR>
     * 7：累積投資<BR>
     */
    public String productType;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * (適格区分)<BR>
     * 適格区分<BR>
     * <BR>
     * 0：不適格<BR>
     * 1：適格<BR>
     */
    public String qualifiedDiv;

    /**
     * (適用期間区分)<BR>
     * 適用期間区分<BR>
     * <BR>
     * 0：適用期間中<BR>
     * 1：適用期間外<BR>
     */
    public String targetPeriodDiv;

    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3SLSortKey[] sortKeys;

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
     * @@roseuid 46E890840387
     */
    public WEB3AdminSLProductRegiListRequest() 
    {

    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * <BR>
     * １） 銘柄タイプチェック<BR>
     * <BR>
     * リクエスト.銘柄タイプ != null and<BR>
     * リクエスト.銘柄タイプが半角数字以外<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02916 <BR>
     * <BR>
     * ２） 銘柄コードチェック<BR>
     * <BR>
     * リクエスト.銘柄コード != null and<BR>
     * リクエスト.銘柄コードが半角数字以外<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00815<BR>
     * <BR>
     * ３）適格区分チェック<BR>
     * <BR>
     * リクエスト.適格区分 != null and<BR>
     * (リクエスト.適格区分が半角数字以外 or<BR>
     * リクエスト.適格区分.length() != 1)<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02925<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02931<BR>
     * <BR>
     * ４）適用期間区分チェック<BR>
     * <BR>
     * リクエスト.適用期間区分 != null and<BR>
     * (リクエスト.適用期間区分が半角数字以外 or<BR>
     * リクエスト.適用期間区分.length() != 1)<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02932<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02933<BR>
     * <BR>
     * <BR>
     * ５） 要求ページ番号チェック<BR>
     * <BR>
     * リクエスト.要求ページ番号 = null or<BR>
     * リクエスト.要求ページ番号が数字以外 or<BR>
     * リクエスト.要求ページ番号 <= 0<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00089<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00090<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00616<BR>
     * <BR>
     * ６）ページ内表示行数チェック<BR>
     * <BR>
     * リクエスト.ページ内表示行数 = null or<BR>
     * リクエスト.ページ内表示行数が数字以外 or<BR>
     * リクエスト.ページ内表示行数 <= 0<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02224<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00092<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00617<BR>
     * <BR>
     * <BR>
     * ７）ソートキーチェック<BR>
     * <BR>
     * リクエストデータ.ソートキー = null or<BR>
     * リクエストデータ.ソートキー.length() = 0<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00231<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00232<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D7FC5A0020
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１） 銘柄タイプチェック  リクエスト.銘柄タイプ != null
        //and リクエスト.銘柄タイプが半角数字以外 の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isEmpty(this.productType)
            && !WEB3StringTypeUtility.isDigit(this.productType))
        {
            log.debug("銘柄タイプが半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02916,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄タイプが半角数字以外の値です。");
        }

        //２） 銘柄コードチェック  リクエスト.銘柄コード != null
        //and  リクエスト.銘柄コードが半角数字以外 の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isEmpty(this.productCode)
            && !WEB3StringTypeUtility.isDigit(this.productCode))
        {
            log.debug("銘柄コードが数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードが数字以外の値です。");
        }

        //３）適格区分チェック  リクエスト.適格区分 != null and
        //(リクエスト.適格区分が半角数字以外 or  
        //リクエスト.適格区分.length() != 1)  
        //の場合、例外をスローする。 
        if (!WEB3StringTypeUtility.isEmpty(this.qualifiedDiv)
            && !WEB3StringTypeUtility.isDigit(this.qualifiedDiv))
        {
            log.debug("適格区分が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02925,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適格区分が半角数字以外の値です。");
        }
        if (!WEB3StringTypeUtility.isEmpty(this.qualifiedDiv)
            && this.qualifiedDiv.length() != 1)
        {
            log.debug("適格区分のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02931,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適格区分のサイズが不正です。");
        }

        //４）適用期間区分チェック  リクエスト.適用期間区分 != null and
        //(リクエスト.適用期間区分が半角数字以外 or
        //リクエスト.適用期間区分.length() != 1)の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isEmpty(this.targetPeriodDiv)
             && !WEB3StringTypeUtility.isDigit(this.targetPeriodDiv))
        {
            log.debug("適用期間区分が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02932,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適用期間区分が半角数字以外の値です。");
        }
        if (!WEB3StringTypeUtility.isEmpty(this.targetPeriodDiv)
                && this.targetPeriodDiv.length() != 1)
        {
            log.debug("適用期間区分のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02933,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適用期間区分のサイズが不正です。");
        }

        //５） 要求ページ番号チェック リクエスト.要求ページ番号 = null or
        //リクエスト.要求ページ番号が数字以外 or
        //リクエスト.要求ページ番号 <= 0  の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        //６）ページ内表示行数チェック  リクエスト.ページ内表示行数 = null or
        //リクエスト.ページ内表示行数が数字以外 or
        //リクエスト.ページ内表示行数 <= 0 の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }
        
        //７）ソートキーチェック リクエストデータ.ソートキー = null
        //or  リクエストデータ.ソートキー.length() = 0 の場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        if (this.sortKeys.length == 0)
        {
             log.debug("ソートキーの要素数が０です。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "ソートキーの要素数が０です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createResponseの実装)<BR>
     * <BR>
     * 担保銘柄登録一覧レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLProductRegiListResponse(this);
    }
}
@
