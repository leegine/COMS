head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.56.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件変更確認ﾘｸｴｽﾄ
                       (WEB3AdminAccInfoCampaignAccOpenConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 孟亜南 (中訊) 新規作成
Revision History : 2007/02/01  モデルNo.165
Revision History : 2007/02/28 Inomata(SCS)モデルNo.203
Revision History : 2007/03/07 Inomata(SCS)モデルNo.209
*/

package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoAccountOpenDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoRegistTypeDef;
import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件変更確認ﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件変更確認ﾘｸｴｽﾄ<BR>
 * @@author 孟亜南
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenConfirmRequest extends WEB3GenRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignAccOpenConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312031L;

    /**
     * (更新処理フラグ)<BR>
     * 更新処理フラグ<BR>
     * <BR>
     * 0：登録処理<BR>
     * 1：更新処理<BR>
     * 2：削除処理<BR>
     */
    public String updateFlag;

    /**
     * 口座開設条件指定ｷｬﾝﾍﾟｰﾝ更新情報
     */
    public WEB3AccInfoCampaignInfo commissionCampaignInfo;

    /**
     * @@roseuid 45C0875F0295
     */
    public WEB3AdminAccInfoCampaignAccOpenConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCampaignAccOpenConfirmResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。 <BR>
     * <BR>
     * １） 更新処理フラグのチェック<BR>
     *   １-１） 更新処理フラグ != (0 or 1 or 2) の場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02710<BR>
     * <BR>
     * ２） 更新処理フラグが 0 （登録）又は 1（更新） の場合、以下処理を行う。<BR>
     * <BR>
     *   ２-１） 商品コードのチェック<BR>
     *     ２-１-１） 商品コード配列がnullの場合、『商品未選択エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02718<BR>
     * <BR>
     *   ２-２） キャンペーン名称のチェック<BR>
     *     ２-２-１） キャンペーン名称が未入力の場合    、『キャンペーン名称未入力エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02712<BR>
     *     ２-２-２） キャンペーン名称101バイト以上の場合、『キャンペーン名称桁数エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02709<BR>
     * <BR>
     *   ２-３） 部店コードのチェック<BR>
     *     ２-３-１）  部店コードが3桁以外の場合、『部店コード桁数エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00834<BR>
     * <BR>
     *   ２-４） 扱者コードのチェック<BR>
     *     ２-４-１） 扱者コードが6桁以上の場合、『扱者コード桁数エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01912<BR>
     * <BR>
     *   ２-５） 口座開設日指定が選択された場合（口座開設経過期間（月）!=null && 口座開設経過期間（日）!=null ）<BR>
     *           以下処理を行う。<BR>
     *     ２-５-１） 対象期間チェック<BR>
     *       ２-５-１-１） 口座開設経過期間（月）が未入力 又は 口座開設経過期間（日）が未入力の場合、<BR>
     *                     『対象期間未入力エラー』例外をスローする。<BR>
     * 　@　@　@  class : WEB3BusinessLayerException<BR>
     * 　@　@　@  tag   : BUSINESS_ERROR_02713<BR>
     *       ２-５-１-２） 口座開設経過期間（月）が０～１２の整数以外の場合、<BR>
     *                     『対象期間エラー』例外をスローする。<BR>
     * 　@　@　@  class : WEB3BusinessLayerException<BR>
     * 　@　@　@  tag   : BUSINESS_ERROR_02715<BR>
     *       ２-５-１-３） 口座開設経過期間（日）が０～３１の整数以外の場合、<BR>
     *                     『対象期間エラー』例外をスローする。<BR>
     * 　@　@　@  class : WEB3BusinessLayerException<BR>
     * 　@　@　@  tag   : BUSINESS_ERROR_02715<BR>
     *       ２-５-１-４） 口座開設経過期間（月）==０ && 口座開設経過期間（日）==０ の場合、<BR>
     *                     『対象期間エラー』例外をスローする。<BR>
     * 　@　@　@  class : WEB3BusinessLayerException<BR>
     * 　@　@　@  tag   : BUSINESS_ERROR_02715<BR>
     * <BR>
     *     ２-５-２） 口座開設区分のチェック<BR>
     *       ２-５-２-１） 口座開設区分が 1:総合口座　@2:信用口座 3:先物OP口座　@4:FX口座　@5:中国株口座 以外の場合、<BR>
     *                     『口座開設区分エラー』例外をスローする。<BR>
     * 　@　@  　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@  tag   : BUSINESS_ERROR_02719<BR>
     * <BR>
     *   ２-６） 期間指定が選択された場合（口座開設経過期間（月）==null && 口座開設経過期間（日）==null ）<BR>
     *     ２-６-１） 対象期間チェック<BR>
     *       ２-６-１-１） 対象期間From又は対象期間Toが未入力の場合、<BR>
     *                     『対象期間未入力エラー』例外をスローする。<BR>
     * 　@　@　@  class : WEB3BusinessLayerException<BR>
     * 　@　@　@  tag   : BUSINESS_ERROR_02713<BR>
     *       ２-６-１-２） 対象期間From > 対象期間Toの場合、『対象期間エラー』例外をスローする。<BR>
     * 　@　@　@  class : WEB3BusinessLayerException<BR>
     * 　@　@　@  tag   : BUSINESS_ERROR_02715<BR>
     *<BR>
     *     ２-６-２） 口座開設区分のチェック
     *       ２-６-２-１） 口座開設区分が 1:総合口座　@2:信用口座 3:先物OP口座　@4:FX口座　@5:中国株口座　@null 以外の場合、<BR>
     *                     『口座開設区分エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02719<BR>
     * <BR>
     *   ２-７） 徴収率のチェック<BR>
     *     ２-７-１） 徴収率が未入力の場合、『徴収率未入力エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02080<BR>
     * <BR>
     *     ２-７-２） 徴収率が 0 ～ 100 の整数以外の場合、『徴収率エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02082<BR>
     * <BR>
     *   ２-８） 口座開設日のチェック<BR>
     *     ２-８-１） 口座開設日From!=null && 口座開設日To==null 又は <BR>
     *                口座開設日From==null && 口座開設日To!=nullの場合、『口座開設日エラー』例外をスローする<BR>
     *        class : WEB3BusinessLayerException<BR>
     *        tag   : BUSINESS_ERROR_02720<BR>
     *     ２-８-２） 口座開設区分==null && 口座開設日From!=null 又は 口座開設区分!=null && 口座開設日From==null の場合、<BR>
     *                『口座開設日エラー』例外をスローする。<BR>
     *        class : WEB3BusinessLayerException<BR>
     *        tag   : BUSINESS_ERROR_02720<BR>
     *     ２-８-３） 口座開設日From > 口座開設日Toの場合、『口座開設日エラー』例外をスローする。<BR>
     *        class : WEB3BusinessLayerException<BR>
     *        tag   : BUSINESS_ERROR_02720<BR>
     * <BR>
     *   ２-９） 登録タイプのチェック<BR>
     *     ２-９-１） 登録タイプ != 0 の場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02722<BR>
     * <BR>
     * ３） 更新処理フラグが 1（更新）又は 2（削除） の場合、以下処理を行う。<BR>
     *   ３-１） 手数料割引キャンペーン条件IDのチェック<BR>
     *     ３-１-１） 手数料割引キャンペーン条件IDが未入力の場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02716<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 45A7655202DF     * <BR>
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validate()";
        log.entering(STR_METHOD_NAME);

        //１） 更新処理フラグのチェック
        //  １-１） 更新処理フラグ != (0 or 1 or 2) の場合、例外をスローする。
        if (!(WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.DELETE.equals(this.updateFlag)))
        {
            log.debug("更新処理フラグの値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02710,
                this.getClass().getName() + STR_METHOD_NAME,
                " 更新処理フラグ = " + this.updateFlag);
        }

        //２） 更新処理フラグが 0 （登録）又は 1（更新） の場合、以下処理を行う。
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag))
        {
            //  ２-１） 商品コードのチェック
            //      ２-１-１） 商品コード配列がnullの場合、『商品未選択エラー』例外をスローする。
            if (this.commissionCampaignInfo.itemCode == null)
            {
                log.debug("商品未選択エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02718,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 商品コード = " + this.commissionCampaignInfo.itemCode);
            }

            //  ２-２） キャンペーン名称のチェック
            //      ２-２-１） キャンペーン名称が未入力の場合  、『キャンペーン名称未入力エラー』例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.campaignName))
            {
                log.debug("キャンペーン名称未入力エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02712,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " キャンペーン名称 = " + this.commissionCampaignInfo.campaignName);
            }

            //      ２-２-２） キャンペーン名称101バイト以上の場合、『キャンペーン名称桁数エラー』例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.commissionCampaignInfo.campaignName) >= 101)
            {
                log.debug("キャンペーン名称桁数エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02709,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " キャンペーン名称 = " + this.commissionCampaignInfo.campaignName);
            }

            //  ２-３） 部店コードのチェック
            //      ２-３-１）  部店コードが3桁以外の場合、『部店コード桁数エラー』例外をスローする
            if (this.commissionCampaignInfo.branchCode != null)
            {
                if (this.commissionCampaignInfo.branchCode.length() != 3)
                {
                    log.debug("部店コードのサイズが不正です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 部店コード = " + this.commissionCampaignInfo.branchCode);
                }
            }

            //  ２-４） 扱者コードのチェック
            //      ２-４-１） 扱者コードが6桁以上の場合、『扱者コード桁数エラー』例外をスローする。
            if (this.commissionCampaignInfo.traderCode != null)
            {
                if (this.commissionCampaignInfo.traderCode.length() >= 6)
                {
                    log.debug("扱者コード（文字列）の長さが不正です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01912,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 扱者コード = " + this.commissionCampaignInfo.traderCode);
                }
            }

            //  ２-５） 口座開設日指定が選択された場合（口座開設経過期間（月）!=null && 口座開設経過期間（日）!=null ）以下処理を行う。
            if (this.commissionCampaignInfo.accopenPassPeriodMonth != null
                    && this.commissionCampaignInfo.accopenPassPeriodDay != null)
            {
                //    ２-５-１） 対象期間チェック
                //    ２-５-１-１） 口座開設経過期間（月）が未入力 && 口座開設経過期間（日）が未入力の場合、
                //                『対象期間未入力エラー』例外をスローする。
                if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.accopenPassPeriodMonth)
                        && WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.accopenPassPeriodDay))
                {
                    log.debug("対象期間未入力エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02713,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "  口座開設経過期間（月） = " + this.commissionCampaignInfo.accopenPassPeriodMonth
                        + " 口座開設経過期間（日） = " + this.commissionCampaignInfo.accopenPassPeriodDay);
                }

                //      ２-５-１-２） 口座開設経過期間（月）が０～１２の整数以外の場合、『対象期間エラー』例外をスローする。
                if (WEB3StringTypeUtility.isInteger(this.commissionCampaignInfo.accopenPassPeriodMonth))
                {
                    if (Integer.parseInt(this.commissionCampaignInfo.accopenPassPeriodMonth) > 12
                            || Integer.parseInt(this.commissionCampaignInfo.accopenPassPeriodMonth) < 0)
                    {
                        log.debug("対象期間エラー。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "  口座開設経過期間（月） = " + this.commissionCampaignInfo.accopenPassPeriodMonth);
                    }
                }
                else if (!WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.accopenPassPeriodMonth) )
                {
                    log.debug("対象期間エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "  口座開設経過期間（月） = " + this.commissionCampaignInfo.accopenPassPeriodMonth);
                }

                //      ２-５-１-３） 口座開設経過期間（日）が０～３１の整数以外の場合、『対象期間エラー』例外をスローする。
                if (WEB3StringTypeUtility.isInteger(this.commissionCampaignInfo.accopenPassPeriodDay))
                {
                    if (Integer.parseInt(this.commissionCampaignInfo.accopenPassPeriodDay) > 31
                            || Integer.parseInt(this.commissionCampaignInfo.accopenPassPeriodDay) < 0)
                    {
                        log.debug("対象期間エラー。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "  口座開設経過期間（日） = " + this.commissionCampaignInfo.accopenPassPeriodDay);
                    }
                }
                else if (!WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.accopenPassPeriodDay))
                {
                    log.debug("対象期間エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "  口座開設経過期間（日） = " + this.commissionCampaignInfo.accopenPassPeriodDay);
                }

                //      ２-５-１-４） 口座開設経過期間（月）==０ && 口座開設経過期間（日）==０ の場合、
                //                    『対象期間エラー』例外をスローする。

                String l_strPassPeriodMonth =
                    this.commissionCampaignInfo.accopenPassPeriodMonth;
                int l_intPassPeriodMonth = Integer.parseInt(l_strPassPeriodMonth);
                String l_strPassPeriodDay =
                    this.commissionCampaignInfo.accopenPassPeriodDay;
                int l_intPassPeriodDay = Integer.parseInt(l_strPassPeriodDay);
                if (l_intPassPeriodMonth == 0 && l_intPassPeriodDay == 0)
                {
                    log.debug("対象期間エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "対象期間エラー。");
                }
                //    ２-５-２） 口座開設区分のチェック
                //      ２-５-２-１） 口座開設区分が 1:総合口座　@2:信用口座 3:先物OP口座　@4:FX口座　@5:中国株口座 以外の場合、『口座開設区分エラー』例外をスローする。
                if (!(WEB3AccInfoAccountOpenDivDef.MULTIPLE.equals(this.commissionCampaignInfo.accountOpenDiv)
                        || WEB3AccInfoAccountOpenDivDef.MARGIN.equals(this.commissionCampaignInfo.accountOpenDiv)
                        || WEB3AccInfoAccountOpenDivDef.IFO.equals(this.commissionCampaignInfo.accountOpenDiv)
                        || WEB3AccInfoAccountOpenDivDef.FX.equals(this.commissionCampaignInfo.accountOpenDiv)
                        || WEB3AccInfoAccountOpenDivDef.CHINESE_EQUITY.equals(this.commissionCampaignInfo.accountOpenDiv)))
                {
                    log.debug("口座開設区分エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02719,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 口座開設区分 = " + this.commissionCampaignInfo.accountOpenDiv);
                }
            }

            //  ２-６） 期間指定が選択された場合（口座開設経過期間（月）==null && 口座開設経過期間（日）==null ）
            if (this.commissionCampaignInfo.accopenPassPeriodMonth == null
                    && this.commissionCampaignInfo.accopenPassPeriodDay == null)
            {
                //      ２-６-１） 対象期間チェック
                //      ２-６-１-１） 対象期間From又は対象期間Toが未入力の場合、『対象期間未入力エラー』例外をスローする。
                if (this.commissionCampaignInfo.targetPeriodFrom == null
                        || this.commissionCampaignInfo.targetPeriodTo == null)
                {
                    log.debug("対象期間未入力エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02713,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "  対象期間From = " + this.commissionCampaignInfo.targetPeriodFrom
                        + " 対象期間To = " + this.commissionCampaignInfo.targetPeriodTo);
                }

                //       ２-６-１-２） 対象期間From > 対象期間Toの場合、『対象期間エラー』例外をスローする。
                if (WEB3DateUtility.compareToDay(this.commissionCampaignInfo.targetPeriodFrom,
                    this.commissionCampaignInfo.targetPeriodTo) > 0)
                {
                    log.debug("対象期間エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "  対象期間From = " + this.commissionCampaignInfo.targetPeriodFrom
                        + " 対象期間To = " + this.commissionCampaignInfo.targetPeriodTo);
                }
                //      ２-６-２） 口座開設区分のチェック
                //        ２-６-２-１） 口座開設区分が 1:総合口座　@2:信用口座 3:先物OP口座　@4:FX口座　@5:中国株口座　@null 以外の場合、
                //                      『口座開設区分エラー』例外をスローする。
                if (this.commissionCampaignInfo.targetPeriodFrom == null
                    && this.commissionCampaignInfo.targetPeriodTo == null)
                {
                    if (!(WEB3AccInfoAccountOpenDivDef.MULTIPLE.equals(this.commissionCampaignInfo.accountOpenDiv)
                            || WEB3AccInfoAccountOpenDivDef.MARGIN.equals(this.commissionCampaignInfo.accountOpenDiv)
                            || WEB3AccInfoAccountOpenDivDef.IFO.equals(this.commissionCampaignInfo.accountOpenDiv)
                            || WEB3AccInfoAccountOpenDivDef.FX.equals(this.commissionCampaignInfo.accountOpenDiv)
                            || WEB3AccInfoAccountOpenDivDef.CHINESE_EQUITY.equals(this.commissionCampaignInfo.accountOpenDiv)
                            || this.commissionCampaignInfo.accountOpenDiv == null))
                    {
                        log.debug("口座開設区分エラー。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02719,
                            this.getClass().getName() + STR_METHOD_NAME,
                            " 口座開設区分 = " + this.commissionCampaignInfo.accountOpenDiv);
                    }
                }
            }

            //  ２-７） 徴収率のチェック
            //      ２-７-１） 徴収率が未入力の場合、『徴収率未入力エラー』例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.collectRate))
            {
                log.debug("徴収率が未入力です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02080,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 徴収率 = " + this.commissionCampaignInfo.collectRate);
            }

            //      ２-７-２） 徴収率が 0 ～ 100 の整数以外の場合、『徴収率エラー』例外をスローする。
            if (WEB3StringTypeUtility.isInteger(this.commissionCampaignInfo.collectRate))
            {
                if (Integer.parseInt(this.commissionCampaignInfo.collectRate) > 100
                        || Integer.parseInt(this.commissionCampaignInfo.collectRate) < 0)
                {
                    log.debug("徴収率の値が不正です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 徴収率 = " + this.commissionCampaignInfo.collectRate);
                }
            }
            else
            {
                log.debug("徴収率の値が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 徴収率 = " + this.commissionCampaignInfo.collectRate);
            }

            //  ２-８） 口座開設日のチェック
            //    ２-８-１） 口座開設日From!=null && 口座開設日To==null 又は 口座開設日From==null && 口座開設日To!=nullの場合、『口座開設日エラー』例外をスローする
            if ((this.commissionCampaignInfo.accountOpenDateFrom != null
                    && this.commissionCampaignInfo.accountOpenDateTo == null)
                    || (this.commissionCampaignInfo.accountOpenDateFrom == null
                    && this.commissionCampaignInfo.accountOpenDateTo != null))
            {
                log.debug("口座開設日エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02720,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 口座開設日From = " + this.commissionCampaignInfo.accountOpenDateFrom
                    + " 口座開設日To = " + this.commissionCampaignInfo.accountOpenDateTo);
            }
            //    ２-８-２） 口座開設区分==null && 口座開設日From!=null 又は 口座開設区分!=null && 口座開設日From==null の場合、
            //               『口座開設日エラー』例外をスローする。
            if ((this.commissionCampaignInfo.accountOpenDiv == null
                    && this.commissionCampaignInfo.accountOpenDateFrom != null)
                    || (this.commissionCampaignInfo.accountOpenDiv != null
                    && this.commissionCampaignInfo.accountOpenDateFrom == null))
            {
                log.debug("口座開設日エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02720,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 口座開設区分 = " + this.commissionCampaignInfo.accountOpenDiv
                    + " 口座開設日From = " + this.commissionCampaignInfo.accountOpenDateFrom
                    + " 口座開設日To = " + this.commissionCampaignInfo.accountOpenDateTo);
            }

            //      ２-８-３） 口座開設日From > 口座開設日Toの場合、『口座開設日エラー』例外をスローする。
            if (this.commissionCampaignInfo.accountOpenDateFrom != null
                && this.commissionCampaignInfo.accountOpenDateTo != null)
            {
                if (WEB3DateUtility.compareToDay(this.commissionCampaignInfo.accountOpenDateFrom,
                        this.commissionCampaignInfo.accountOpenDateTo) > 0)
                {
                    log.debug("口座開設日エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02720,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 口座開設日From = " + this.commissionCampaignInfo.accountOpenDateFrom
                        + " 口座開設日To = " + this.commissionCampaignInfo.accountOpenDateTo);
                }
            }

            //２-９） 登録タイプのチェック
            //  ２-９-１） 登録タイプ != 0 の場合、例外をスローする。
            if (!WEB3AccInfoRegistTypeDef.ACCOPEN_CONDITION.equals(this.commissionCampaignInfo.registType))
            {
                log.debug("登録タイプが'0'以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02722,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 登録タイプ = " + this.commissionCampaignInfo.registType);
            }
        }

        //３） 更新処理フラグが 1（更新）又は 2（削除） の場合、以下処理を行う。
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag)
            || WEB3AccInfoUpdateFlagDef.DELETE.equals(this.updateFlag))
        {
            //  ３-１） 手数料割引キャンペーン条件IDのチェック
            //      ３-１-１） 手数料割引キャンペーン条件IDが未入力の場合、例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.campaignId))
            {
                log.debug("手数料割引キャンペーン条件IDが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02716,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 手数料割引キャンペーン条件ID = " + this.commissionCampaignInfo.campaignId);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
