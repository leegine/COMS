head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録共通リクエスト(WEB3AdminSLProductRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 趙林鵬(中訊) 新規作成 モデルNo.760
Revision History : 2007/10/11 孫洪江(中訊) 仕様変更モデルNo.805
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
 * (担保銘柄登録共通リクエスト)<BR>
 * 担保銘柄登録共通リクエストクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminSLProductRegistCommonRequest extends WEB3GenRequest
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLProductRegistCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_regist_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709141600L;

    /**
     * (銘柄登録情報)<BR>
     * 担保名銘柄登録情報オブジェクト<BR>
     */
    public WEB3SLProductInfoUnit stockLoanProductInfo;

    /**
     * @@roseuid 46E8908402AC
     */
    public WEB3AdminSLProductRegistCommonRequest()
    {

    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@銘柄コードのチェック<BR>
     * 　@１－１）　@リクエスト.銘柄登録情報.銘柄コードがnullの場合、<BR>
     * 　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_00079<BR>
     * <BR>
     * 　@１－２）　@リクエスト.銘柄登録情報.銘柄コードが半角数字以外の場合、<BR>
     * 　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_00815<BR>
     * <BR>
     * ２）　@銘柄タイプのチェック<BR>
     * 　@２－１）　@リクエスト.銘柄登録情報.銘柄タイプがnullの場合、<BR>
     * 　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_01109<BR>
     * <BR>
     * ３）　@適格区分のチェック<BR>
     * 　@３－１）　@リクエスト.銘柄登録情報.適格区分がnullの場合、<BR>
     * 　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02930<BR>
     * <BR>
     * ４）　@掛目のチェック<BR>
     * 　@４－１）　@リクエスト.銘柄登録情報.掛目 != null かつ 半角数字以外<BR>
     * 　@　@　@　@　@　@の場合、例外をスロー<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02924<BR>
     * <BR>
     * ５）　@適用期間fromのチェック<BR>
     * 　@５－１）　@リクエスト.銘柄登録情報.適用期間fromがnullの場合、<BR>
     * 　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_01444<BR>
     * <BR>
     * ６）　@適用期間大小チェック<BR>
     * 　@６－１）リクエスト.銘柄登録情報.適用期間to != nullの場合、<BR>　@
     * 　@　@　@　@　@　@リクエスト.銘柄登録情報.適用期間from ><BR>
     * 　@　@　@　@　@　@リクエスト.銘柄登録情報.適用期間to<BR>
     * 　@　@　@　@　@　@の場合、例外をスロー<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_01446<BR>
     * <BR>
     * ７）　@理由のチェック<BR>
     * 　@７－１）　@リクエスト.銘柄登録情報.理由 != null<BR>
     * 　@　@　@　@　@　@かつ200Byte以上の場合、例外をスロー<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02926<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D646A10119
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //リクエスト.銘柄登録情報.銘柄コードがnullの場合、
        //例外をスロー
        if (this.stockLoanProductInfo.productCode == null)
        {
            log.debug("銘柄コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードが未指定です。");
        }

        //リクエスト.銘柄登録情報.銘柄コードが半角数字以外の場合
        //例外をスロー
        if (!WEB3StringTypeUtility.isDigit(this.stockLoanProductInfo.productCode))
        {
            log.debug("銘柄コードが数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードが数字以外の値です。");
        }

        //リクエスト.銘柄登録情報.銘柄タイプがnullの場合、
        //例外をスロー
        if (this.stockLoanProductInfo.productType == null)
        {
            log.debug("銘柄タイプ区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01109,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄タイプ区分が未指定です。");
        }

        //リクエスト.銘柄登録情報.適格区分がnullの場合、
        //例外をスロー
        if (this.stockLoanProductInfo.qualifiedDiv == null)
        {
            log.debug("適格区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02930,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適格区分が未指定です。");
        }

        //リクエスト.銘柄登録情報.掛目 != null かつ 半角数字以外
        //の場合、例外をスロー
        if (this.stockLoanProductInfo.weight != null
            && !WEB3StringTypeUtility.isDigit(this.stockLoanProductInfo.weight))
        {
            log.debug("掛目が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02924,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "掛目が半角数字以外の値です。");
        }

        //リクエスト.銘柄登録情報.適用期間fromがnullの場合、
        //例外をスロー
        if (this.stockLoanProductInfo.targetPeriodFrom == null)
        {
            log.debug("日付未入力エラー(適用期間From)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01444,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "日付未入力エラー(適用期間From)");
        }

        //リクエスト.銘柄登録情報.適用期間to != nullの場合、
        //リクエスト.銘柄登録情報.適用期間from > リクエスト.銘柄登録情報.適用期間to
        //の場合、例外をスロー
        if (this.stockLoanProductInfo.targetPeriodTo != null
            && WEB3DateUtility.compareToDay(this.stockLoanProductInfo.targetPeriodFrom,
                this.stockLoanProductInfo.targetPeriodTo) > 0)
        {
            log.debug("適用期間From/To整合性エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01446,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適用期間From/To整合性エラー");
        }

        //リクエスト.銘柄登録情報.理由 != null かつ200Byte以上の場合、例外をスロー
        if (this.stockLoanProductInfo.reason != null
            && WEB3StringTypeUtility.getByteLength(this.stockLoanProductInfo.reason) >= 200)
        {
            log.debug("理由のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02926,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "理由のサイズが不正です。");
        }
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
