head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticProductUpdateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券銘柄更新情報(WEB3BondDomesticProductUpdateInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/09 何文敏 (中訊) 新規作成 仕様変更・モデルNo.200
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.common.define.WEB3ProspectusCheckDivDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (国内債券銘柄更新情報)<BR>
 * 国内債券銘柄更新情報<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3BondDomesticProductUpdateInfo extends Message
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticProductUpdateInfo.class);

    /**
     * (取扱区分)<BR>
     * 取扱区分<BR>
     * <BR>
     * 0：不可<BR>　@
     * 2：顧客<BR>
     * <BR>
     */
    public String tradeHandleDiv;

    /**
     * (売買区分)<BR>
     * 売買区分<BR>
     * <BR>
     * 3：応募<BR>
     */
    public String dealingType;

    /**
     * (応募開始日（WEB3）)<BR>
     * 応募開始日（WEB3）<BR>
     */
    public Date recruitStartDateWEB3;

    /**
     * (応募終了日（WEB3）)<BR>
     * 応募終了日（WEB3）<BR>
     */
    public Date recruitEndDateWEB3;

    /**
     * (応募開始日（インターネット）)<BR>
     * 応募開始日（インターネット）<BR>
     */
    public Date recruitStartDateInterNet;

    /**
     * (応募終了日（インターネット）)<BR>
     * 応募終了日（インターネット）<BR>
     */
    public Date recruitEndDateInterNet;

    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;

    /**
     * (銘柄名（WEB3))<BR>
     * 銘柄名（WEB3)<BR>
     */
    public String productNameWEB3;

    /**
     * (申込単位)<BR>
     * 申込単位<BR>
     */
    public String applyUnit;

    /**
     * (最低額面)<BR>
     * 最低額面<BR>
     */
    public String minFaceAmount;

    /**
     * (最高額面)<BR>
     * 最高額面<BR>
     */
    public String maxFaceAmount;

    /**
     * (目論見書閲覧チェック区分)<BR>
     * 目論見書閲覧チェック区分<BR>
     * <BR>
     * 0：目論見書をチェックしない(要)<BR>
     * 1：目論見書をチェックする(不要）<BR>
     */
    public String prospectusCheckDiv;

    /**
     * (国内債券銘柄更新情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 4663798C0157
     */
    public WEB3BondDomesticProductUpdateInfo()
    {

    }

    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）取扱区分チェック<BR>
     * 　@　@・取扱区分 == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02843<BR>
     * 　@　@・取扱区分が ’不可’ or ’顧客’でない場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02844<BR>
     * <BR>
     * ２）売買区分チェック<BR>
     * 　@　@・売買区分 == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_01402<BR>
     * 　@　@・売買区分が’応募’でない場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_01403<BR>
     * <BR>
     * ３）応募開始日（WEB3）チェック<BR>
     * 　@　@・応募開始日（WEB3) == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02845<BR>
     * <BR>
     * ４）応募終了日（WEB3)チェック<BR>
     * 　@　@・応募終了日（WEB3) == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02846<BR>
     * <BR>
     * ５）応募開始日（インターネット）チェック<BR>
     * 　@　@・応募開始日（インターネット) == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02847<BR>
     * <BR>
     * ６）応募終了日（インターネット)チェック<BR>
     * 　@　@・応募終了日（インターネット) == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02848<BR>
     * <BR>
     * ７）受渡日チェック<BR>
     * 　@　@・受渡日 == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_01079<BR>
     * <BR>
     * ８）銘柄名（WEB3)チェック<BR>
     * 　@　@・銘柄名（WEB3） == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02849<BR>
     * 　@　@・64バイト以内でない場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02850<BR>
     * <BR>
     * ９）申込単位チェック<BR>
     * 　@　@・申込単位 == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02851<BR>
     * 　@　@・申込単位が１１桁以内でない場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02584<BR>
     * 　@　@・申込単位が整数でない場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02585<BR>
     * 　@　@・申込単位＜＝０の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02586<BR>
     * <BR>
     * １０）最低額面チェック<BR>
     * 　@　@・最低額面 == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02852<BR>
     * 　@　@・最低額面が１１桁以内でない場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02587<BR>
     * 　@　@・最低額面が整数でない場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02588<BR>
     * 　@　@・最低額面が＜０の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02589<BR>
     * <BR>
     * １１）最高額面チェック<BR>
     * 　@　@・最高額面 == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02853<BR>
     * 　@　@・最高額面が１１桁以内でない場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02590<BR>
     * 　@　@・最高額面が整数でない場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02591<BR>
     * 　@　@・最高額面が＜０の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02592<BR>
     * <BR>
     * １２）目論見書閲覧チェック区分チェック<BR>
     * 　@　@・目論見書閲覧チェック区分 == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02854<BR>
     * 　@　@・目論見書閲覧チェック区分が’目論見書をチェックしない’ or ’目論見書を<BR>
     * 　@　@チェックする’でない場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02855<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4663AE460148
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）取扱区分チェック
        // ・取扱区分 == null の場合、例外をスローする。
        if (this.tradeHandleDiv == null)
        {
            log.debug("取扱区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02843,
                this.getClass().getName() + STR_METHOD_NAME,
                "取扱区分が未指定です。");
        }

        // ・取扱区分が ’不可’ or ’顧客’でない場合、例外をスローする。
        if (!(WEB3TradeHandleDivDef.DISABLED.equals(this.tradeHandleDiv)
            || WEB3TradeHandleDivDef.MANAGER_CUSTOMER.equals(this.tradeHandleDiv)))
        {
            log.debug("取扱区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02844,
                this.getClass().getName() + STR_METHOD_NAME,
                "取扱区分の値が存在しないコード値です。");
        }

        // ２）売買区分チェック
        // ・売買区分 == null の場合、例外をスローする。
        if (this.dealingType == null)
        {
            log.debug("売買区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01402,
                this.getClass().getName() + STR_METHOD_NAME,
                "売買区分が未指定です。");
        }

        // ・売買区分が’応募’でない場合、例外をスローする。
        if (!WEB3BondTradeTypeDef.RECRUIT.equals(this.dealingType))
        {
            log.debug("売買区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                this.getClass().getName() + STR_METHOD_NAME,
                "売買区分の値が存在しないコード値です。");
        }

        // ３）応募開始日（WEB3）チェック
        // ・応募開始日（WEB3) == null の場合、例外をスローする。
        if (this.recruitStartDateWEB3 == null)
        {
            log.debug("応募開始日（WEB3)が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02845,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募開始日（WEB3)が未指定です。");
        }

        // ４）応募終了日（WEB3)チェック
        //　@・応募終了日（WEB3) == null の場合、例外をスローする。
        if (this.recruitEndDateWEB3 == null)
        {
            log.debug("応募終了日（WEB3)が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02846,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募終了日（WEB3)が未指定です。");
        }

        // ５）応募開始日（インターネット）チェック
        // ・応募開始日（インターネット) == null の場合、例外をスローする。
        if (this.recruitStartDateInterNet == null)
        {
            log.debug("応募開始日（インターネット)が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02847,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募開始日（インターネット)が未指定です。");
        }

        // ６）応募終了日（インターネット)チェック
        // ・応募終了日（インターネット) == null の場合、例外をスローする。
        if (this.recruitEndDateInterNet == null)
        {
            log.debug("応募終了日（インターネット)が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02848,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募終了日（インターネット)が未指定です。");
        }

        // ７）受渡日チェック
        // ・受渡日 == null の場合、例外をスローする。
        if (this.deliveryDate == null)
        {
            log.debug("受渡日が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                this.getClass().getName() + STR_METHOD_NAME,
                "受渡日が未指定です。");
        }

        // ８）銘柄名（WEB3)チェック
        // ・銘柄名（WEB3） == null の場合、例外をスローする。
        if (this.productNameWEB3 == null)
        {
            log.debug("銘柄名（WEB3)が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02849,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄名（WEB3)が未指定です。");
        }

        // ・64バイト以内でない場合、例外をスローする。
        if (WEB3StringTypeUtility.getByteLength(this.productNameWEB3) > 64)
        {
            log.debug("銘柄名（WEB3)のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02850,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄名（WEB3)のサイズが不正です。");
        }

        // ９）申込単位チェック
        // ・申込単位 == null の場合、例外をスローする。
        if (this.applyUnit == null)
        {
            log.debug("申込単位が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02851,
                this.getClass().getName() + STR_METHOD_NAME,
                "申込単位が未指定です。");
        }

        // ・申込単位が１１桁以内でない場合、例外をスローする。
        if (WEB3StringTypeUtility.getByteLength(this.applyUnit) > 11)
        {
            log.debug("申込単位のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02584,
                this.getClass().getName() + STR_METHOD_NAME,
                "申込単位のサイズが不正です。");
        }

        // ・申込単位が整数でない場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.applyUnit))
        {
            log.debug("申込単位が整数以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02585,
                this.getClass().getName() + STR_METHOD_NAME,
                "申込単位が整数以外の値です。");
        }

        // ・申込単位＜＝０の場合、例外をスローする。
        if (Long.parseLong(this.applyUnit) <= 0)
        {
            log.debug("申込単位が0以下の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02586,
                this.getClass().getName() + STR_METHOD_NAME,
                "申込単位が0以下の値です。");
        }

        // １０）最低額面チェック
        // ・最低額面 == null の場合、例外をスローする。
        if (this.minFaceAmount == null)
        {
            log.debug("最低額面が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02852,
                this.getClass().getName() + STR_METHOD_NAME,
                "最低額面が未指定です。");
        }

        // ・最低額面が１１桁以内でない場合、例外をスローする。
        if (WEB3StringTypeUtility.getByteLength(this.minFaceAmount) > 11)
        {
            log.debug("最低額面のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02587,
                this.getClass().getName() + STR_METHOD_NAME,
                "最低額面のサイズが不正です。");
        }

        // ・最低額面が整数でない場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.minFaceAmount))
        {
            log.debug("最低額面が整数以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02588,
                this.getClass().getName() + STR_METHOD_NAME,
                "最低額面が整数以外の値です。");
        }

        // ・最低額面が＜０の場合、例外をスローする。
        if (Long.parseLong(this.minFaceAmount) < 0)
        {
            log.debug("最低額面が0より小さい値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02589,
                this.getClass().getName() + STR_METHOD_NAME,
                "最低額面が0より小さい値です。");
        }

        // １1）最高額面チェック
        // ・最高額面 == null の場合、例外をスローする。
        if (this.maxFaceAmount == null)
        {
            log.debug("最高額面が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02853,
                this.getClass().getName() + STR_METHOD_NAME,
                "最高額面が未指定です。");
        }

        // ・最高額面が１１桁以内でない場合、例外をスローする。
        if (WEB3StringTypeUtility.getByteLength(this.maxFaceAmount) > 11)
        {
            log.debug("最高額面のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02590,
                this.getClass().getName() + STR_METHOD_NAME,
                "最高額面のサイズが不正です。");
        }

        // ・最高額面が整数でない場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.maxFaceAmount))
        {
            log.debug("最高額面が整数以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02591,
                this.getClass().getName() + STR_METHOD_NAME,
                "最高額面が整数以外の値です。");
        }

        // ・最高額面が＜０の場合、例外をスローする。
        if (Long.parseLong(this.maxFaceAmount) < 0)
        {
            log.debug("最高額面が0より小さい値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02592,
                this.getClass().getName() + STR_METHOD_NAME,
                "最高額面が0より小さい値です。");
        }

        // １２）目論見書閲覧チェック区分チェック
        // ・目論見書閲覧チェック区分 == null の場合、例外をスローする。
        if (this.prospectusCheckDiv == null)
        {
            log.debug("目論見書閲覧チェック区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02854,
                this.getClass().getName() + STR_METHOD_NAME,
                "目論見書閲覧チェック区分が未指定です。");
        }

        // ・目論見書閲覧チェック区分が’目論見書をチェックしない’ or ’
        // 目論見書をチェックする’でない場合、例外をスローする。
        if (!(WEB3ProspectusCheckDivDef.PROSPECTUS_CHECK.equals(this.prospectusCheckDiv)
            || WEB3ProspectusCheckDivDef.PROSPECTUS_NOT_CHECK.equals(this.prospectusCheckDiv)))
        {
            log.debug("目論見書閲覧チェック区分が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02855,
                this.getClass().getName() + STR_METHOD_NAME,
                "目論見書閲覧チェック区分が存在しないコード値です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
