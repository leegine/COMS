head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録変更確認リクエスト(WEB3AdminSLProductChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 トウ鋒鋼(中訊) 新規作成 仕様変更モデル760
Revision History : 2007/10/10 孫洪江(中訊) 仕様変更モデル801 モデル802
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (担保銘柄登録変更確認リクエスト)<BR>
 * 担保銘柄登録変更確認リクエストクラス<BR>
 * <BR>
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3AdminSLProductChangeConfirmRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLProductChangeConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_change_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709141050L;

    /**
     * (変更後銘柄登録情報)<BR>
     * 担保銘柄登録情報オブジェクト<BR>
     */
    public WEB3SLProductInfoUnit changedStockLoanProductInfo;

    /**
     * (担保銘柄検索キー)<BR>
     * 担保銘柄検索キー<BR>
     */
    public WEB3SLProductSearchConditions searchConditions;

    /**
     * @@roseuid 46E8908500C7
     */
    public WEB3AdminSLProductChangeConfirmRequest()
    {

    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * <BR>
     * １）　@リクエスト.担保銘柄検索キーのチェック<BR>
     * <BR>
     * 　@１－１）　@リクエスト.担保銘柄検索キーがnullの場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_02917<BR>
     * <BR>
     * 　@１－２）　@リクエスト.担保銘柄検索キー.適用期間fromがnullの場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_01444<BR>
     * <BR>
     * ２）　@リクエスト.変更後銘柄登録情報のチェック<BR>
     * <BR>
     * 　@２－１）　@リクエスト.変更後銘柄登録情報がnullの場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_02923<BR>
     * <BR>
     * 　@２－２）　@リクエスト.変更後銘柄登録情報.銘柄コードがnullの場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_00079<BR>
     * <BR>
     * 　@２－３）　@リクエスト.変更後銘柄登録情報.銘柄コードが半角数字以外の場合、<BR>
     * 　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_00815<BR>
     * <BR>
     * 　@２－４）　@リクエスト.変更後銘柄登録情報.銘柄タイプがnullの場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_02394<BR>
     * <BR>
     * 　@２－５）　@リクエスト.変更後銘柄登録情報.銘柄名がnullの場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_01062<BR>
     * <BR>
     * 　@２－６）　@リクエスト.変更後銘柄登録情報.適格区分がnullの場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_02930<BR>
     * <BR>
     * 　@２－７）　@リクエスト.変更後銘柄登録情報.掛目 != null かつ 半角数字以外<BR>
     * 　@　@　@　@　@　@の場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_02924<BR>
     * <BR>
     * 　@２－８）　@リクエスト.変更後銘柄登録情報.適用期間fromがnullの場合、<BR>
     * 　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_01444<BR>
     * <BR>
     * 　@２－９）　@リクエスト.変更後銘柄登録情報.適用期間from > <BR>
     * 　@　@　@　@　@　@　@リクエスト.変更後銘柄登録情報.適用期間to　@の場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_01446<BR>
     * <BR>
     * 　@２－１０）　@リクエスト.変更後銘柄登録情報.理由 != null<BR>
     *              かつ200Byte以上の場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_02926<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DD0EB2038B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@リクエスト.担保銘柄検索キーのチェック
        //　@１－１）　@リクエスト.担保銘柄検索キーがnullの場合、例外をスロー
        if (this.searchConditions == null)
        {
            log.debug("担保銘柄検索キーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02917,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "担保銘柄検索キーが未指定です。");
        }

        //　@１－２）　@リクエスト.担保銘柄検索キー.適用期間fromがnullの場合、例外をスロー
        if (this.searchConditions.targetPeriodFrom == null)
        {
            log.debug("日付未入力エラー(適用期間From)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01444,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "日付未入力エラー(適用期間From)");
        }

        //２）　@リクエスト.変更後銘柄登録情報のチェック
        //　@２－１）　@リクエスト.変更後銘柄登録情報がnullの場合、例外をスロー
        if (this.changedStockLoanProductInfo == null)
        {
            log.debug("変更後銘柄登録情報が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02923,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "変更後銘柄登録情報が未指定です。");
        }

        //　@２－２）　@リクエスト.変更後銘柄登録情報.銘柄コードがnullの場合、例外をスロー
        if (this.changedStockLoanProductInfo.productCode == null)
        {
            log.debug("銘柄コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードが未指定です。");
        }

        //　@２－３）　@リクエスト.変更後銘柄登録情報.銘柄コードが半角数字以外の場合、
        //            例外をスロー
        if (!WEB3StringTypeUtility.isDigit(this.changedStockLoanProductInfo.productCode))
        {
            log.debug("銘柄コードが数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードが数字以外の値です。");
        }

        //　@２－４）　@リクエスト.変更後銘柄登録情報.銘柄タイプがnullの場合、例外をスロー
        if (this.changedStockLoanProductInfo.productType == null)
        {
            log.debug("銘柄タイプが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02394,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄タイプが未指定です。");
        }

        //　@２－５）　@リクエスト.変更後銘柄登録情報.銘柄名がnullの場合、例外をスロー
        if (this.changedStockLoanProductInfo.productName == null)
        {
            log.debug("銘柄名が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01062,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄名が未指定です。");
        }

        //　@２－６）　@リクエスト.変更後銘柄登録情報.適格区分がnullの場合、例外をスロー
        if (this.changedStockLoanProductInfo.qualifiedDiv == null)
        {
            log.debug("適格区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02930,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適格区分が未指定です。");
        }

        //　@２－７）　@リクエスト.変更後銘柄登録情報.掛目 != null かつ 半角数字以外
        //          　@の場合、例外をスロー
        if (this.changedStockLoanProductInfo.weight != null
            && !WEB3StringTypeUtility.isDigit(this.changedStockLoanProductInfo.weight))
        {
            log.debug("掛目が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02924,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "掛目が半角数字以外の値です。");
        }

        //　@２－８）　@リクエスト.変更後銘柄登録情報.適用期間fromがnullの場合、
        // 　@　@　@　@　@　@例外をスロー
        if (this.changedStockLoanProductInfo.targetPeriodFrom == null)
        {
            log.debug("日付未入力エラー(適用期間From)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01444,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "日付未入力エラー(適用期間From)");
        }

        //　@２－９）　@リクエスト.変更後銘柄登録情報.適用期間from >
        // 　@　@　@　@　@　@ リクエスト.変更後銘柄登録情報.適用期間to　@の場合、例外をスロー
        if (this.changedStockLoanProductInfo.targetPeriodTo != null
            && WEB3DateUtility.compareToDay(this.changedStockLoanProductInfo.targetPeriodFrom,
            this.changedStockLoanProductInfo.targetPeriodTo) > 0)
        {
            log.debug("適用期間From/To整合性エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01446,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適用期間From/To整合性エラー");
        }

        //　@２－１０）　@リクエスト.変更後銘柄登録情報.理由 != null
        // かつ200Byte以上の場合、例外をスロー
        if (this.changedStockLoanProductInfo.reason != null
            && WEB3StringTypeUtility.getByteLength(this.changedStockLoanProductInfo.reason) >= 200)
        {
            log.debug("理由のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02926,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "理由のサイズが不正です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLProductChangeConfirmResponse(this);
    }
}
@
