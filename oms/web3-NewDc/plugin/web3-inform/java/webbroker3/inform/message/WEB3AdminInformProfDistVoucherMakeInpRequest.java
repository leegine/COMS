head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherMakeInpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・口座伝票作成入力リクエスト(WEB3AdminInformProfDistVoucherMakeInpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.054、073
Revision History    : 2007/06/14 周墨洋(中訊) 修正 モデルNo.083
Revision History    : 2007/06/22 佐藤(SCS) 修正 モデルNo.095
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.define.WEB3InformRegistDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・口座伝票作成入力リクエスト)<BR>
 * 管理者・口座伝票作成入力リクエストクラス
 */
public class WEB3AdminInformProfDistVoucherMakeInpRequest extends WEB3GenRequest
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistVoucherMakeInpRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_voucher_make_inp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200706042212L;

    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;

    /**
     * (指定区分)<BR>
     * 指定区分
     */
    public String specifyDiv;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;

    /**
     * (登録区分)<BR>
     * 登録区分
     */
    public String registDiv;

    /**
     * (商品)<BR>
     * 商品
     */
    public String product;

    /**
     * (振替区分)<BR>
     * 振替区分
     */
    public String transferDiv;

    /**
     * @@roseuid 4663A9D60232
     */
    public WEB3AdminInformProfDistVoucherMakeInpRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformProfDistVoucherMakeInpResponse(this);
    }

    /**
     * リクエストデータの簡易チェックを行う。<BR>
     * <BR>
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コード<BR>
     * <BR>
     * this.部店コード == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_02174<BR>
     * <BR>
     * this.部店コード != 半角数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_01729<BR>
     * this.部店コード.length() != 3<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00779<BR>
     * 　@<BR>
     * の場合、例外をスローする。<BR>
     * <BR>
     * ２）顧客コード<BR>
     * <BR>
     * this.顧客コード == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00835<BR>
     * <BR>
     * this.顧客コード != 半角数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_01043<BR>
     * <BR>
     * this.顧客コード.length() != 6<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00780<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * <BR>
     * ３）指定区分<BR>
     * <BR>
     * this.指定区分 != null の場合、以下のチェック<BR>
     * 　@this.指定区分 != 半角英数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_02794<BR>
     * <BR>
     * 　@this.指定区分.length() != 1<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_02795<BR>
     * <BR>
     * 　@の場合、例外をスローする。<BR>
     * <BR>
     * ４）銘柄コード<BR>
     * <BR>
     * this.銘柄コード != null の場合、以下のチェック<BR>
     * 　@this.銘柄コード != 半角数字<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00815<BR>
     * <BR>
     * 　@の場合、例外をスローする。<BR>
     * <BR>
     * ５）登録区分<BR>
     * <BR>
     * this.登録区分 != null の場合、以下のチェック<BR>
     * 　@this.登録区分 != 1：新規 or 3：削除<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00841<BR>
     * <BR>
     * 　@の場合、例外をスローする。<BR>
     * <BR>
     * ６）商品<BR>
     * <BR>
     * this.商品 != null の場合、以下のチェック<BR>
     * 　@this.商品 != 半角英数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_02792<BR>
     * <BR>
     * 　@this.商品.length() != 2<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_02793<BR>
     * <BR>
     * 　@の場合、例外をスローする。<BR>
     * <BR>
     * ７）振替区分<BR>
     * <BR>
     * this.振替区分 != null の場合、以下のチェック<BR>
     * 　@this.振替区分 != １：銀行振込 or ５：郵便振込<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_01772<BR>
     * <BR>
     * 　@の場合、例外をスローする。<BR>
     * @@throws  WEB3BaseException
     * @@roseuid 4643C934002B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // this.部店コード == null
        if (this.branchCode == null)
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コードがnullです。");
        }

        // this.部店コード != 半角数字
        if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("部店コードが数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コードが数値以外の値です。");
        }

        // this.部店コード.length() != 3
        if (this.branchCode.length() != 3)
        {
            log.debug("部店コードの入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コードの入力が不正です。");
        }

        // this.顧客コード == null
        if (this.accountCode == null)
        {
            log.debug("顧客コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コードが未指定です。");
        }

        // this.顧客コード != 半角数字
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("顧客コードの値が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コードの値が数字以外の値です。");
        }

        // this.顧客コード.length() != 6
        if (this.accountCode.length() != 6)
        {
            log.debug("顧客コードの入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コードの入力が不正です。");
        }

        // this.指定区分 != null の場合、以下のチェック
        if (this.specifyDiv != null)
        {
            // this.指定区分 != 半角英数字
            if (!WEB3StringTypeUtility.isLetterOrDigit(this.specifyDiv))
            {
                log.debug("指定区分が半角英数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02794,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "指定区分が半角英数字以外の値です。");
            }

            // this.指定区分.length() != 1
            if (this.specifyDiv.length() != 1)
            {
                log.debug("指定区分のサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02795,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "指定区分のサイズが不正です。");
            }
        }

        // this.銘柄コード != null の場合、以下のチェック
        if (this.productCode != null)
        {
            // this.銘柄コード != 半角数字
            if (!WEB3StringTypeUtility.isDigit(this.productCode))
            {
                log.debug("銘柄コードが数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "銘柄コードが数字以外の値です。");
            }
        }

        // this.登録区分 == null
        if (this.registDiv == null)
        {
            log.debug("登録区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02835,
                this.getClass().getName() + STR_METHOD_NAME,
                "登録区分が未指定です。");
        }

        // this.登録区分 != 1：新規 or 3：削除
        if (!WEB3InformRegistDivDef.REGISTRATION.equals(this.registDiv) &&
            !WEB3InformRegistDivDef.DELETE.equals(this.registDiv))
        {
            log.debug("登録区分が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00841,
                this.getClass().getName() + STR_METHOD_NAME,
                "登録区分が存在しないコード値です。");
        }

        // this.商品 != null の場合、以下のチェック
        if (this.product != null)
        {
            // this.商品 != 半角英数字
            if (!WEB3StringTypeUtility.isLetterOrDigit(this.product))
            {
                log.debug("商品が半角英数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02792,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "商品が半角英数字以外の値です。");
            }

            // 商品のレングスを1桁でチェックするように修正 2007.06.22 SCS佐藤
            // this.商品.length() != 1
            if (this.product.length() != 1)
            {
                log.debug("商品のサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02793,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "商品のサイズが不正です。");
            }
        }

        // this.振替区分 != null の場合、以下のチェック
        if (this.transferDiv != null)
        {
            // this.振替区分 != １：銀行振込 or ５：郵便振込
            if (!WEB3TransferDivDef.BANK_TRANSFER.equals(this.transferDiv) &&
                !WEB3TransferDivDef.POSTAL_TRANSFER.equals(this.transferDiv))
            {
                log.debug("振替区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01772,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "振替区分が存在しないコード値です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
