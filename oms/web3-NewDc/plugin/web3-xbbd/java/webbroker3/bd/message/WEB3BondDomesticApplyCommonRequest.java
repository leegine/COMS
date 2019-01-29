head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募共通リクエスト(WEB3BondDomesticApplyCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 モデルNo.227
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (国内債券応募共通リクエスト)<BR>
 * 国内債券応募共通リクエスト<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyCommonRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_domestic_apply_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

    /**
     * (銘柄ID)<BR>
     * 銘柄ID<BR>
     */
    public String productId;

    /**
     * (申込金額)<BR>
     * 申込金額<BR>
     */
    public String applyAmount;

    /**
     * (紹介区分)<BR>
     * 紹介区分<BR>
     * <BR>
     * 1：直接取引<BR>
     * 2：単純紹介<BR>
     * 3：商品紹介<BR>
     * 4：仲介取引<BR>
     * <BR>
     */
    public String introduceStoreDiv;

    /**
     * (紹介店コード)<BR>
     * 紹介店コード<BR>
     */
    public String introduceStoreCode;

    /**
     * @@roseuid 46A473FD0280
     */
    public WEB3BondDomesticApplyCommonRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }

    /**
     * 当リクエストデータの整合性をチェックする。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * 1）　@銘柄IDチェック<BR>
     * 　@　@　@銘柄ID == nullの場合、例外をスローする。 <BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_02229<BR>
     * <BR>
     * ２）　@申込金額チェック<BR>
     * 　@２－１）　@申込金額 == nullの場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_02880<BR>
     * 　@２－２）　@申込金額が整数でない場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_02881<BR>
     * 　@２－３）　@申込金額 ≦ 0の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_02882<BR>
     * 　@２－４）　@申込金額 ＞ 11桁の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_02883<BR>
     * @@throws WEB3BaseException
     * @@roseuid 469F47BE01A6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //1）　@銘柄IDチェック
        //銘柄ID == nullの場合、例外をスローする。
        if (this.productId == null)
        {
            log.debug("銘柄IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄IDが未指定です。");
        }

        //２）　@申込金額チェック
        //２－１）　@申込金額 == nullの場合、例外をスローする。
        if (this.applyAmount == null)
        {
            log.debug("申込金額が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02880,
                this.getClass().getName() + STR_METHOD_NAME,
                "申込金額が未指定です。");
        }

        //２－２）　@申込金額が整数でない場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.applyAmount))
        {
            log.debug("申込金額が整数以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02881,
                this.getClass().getName() + STR_METHOD_NAME,
                "申込金額が整数以外の値です。");
        }

        //２－３）　@申込金額 ≦ 0の場合、例外をスローする。
        if (Double.parseDouble(this.applyAmount) <= 0)
        {
            log.debug("申込金額が0以下の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02882,
                this.getClass().getName() + STR_METHOD_NAME,
                "申込金額が0以下の値です。");
        }

        //２－４）　@申込金額 ＞ 11桁の場合、例外をスローする。
        if (this.applyAmount.length() > 11)
        {
            log.debug("申込金額のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02883,
                this.getClass().getName() + STR_METHOD_NAME,
                "申込金額のサイズが不正です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
