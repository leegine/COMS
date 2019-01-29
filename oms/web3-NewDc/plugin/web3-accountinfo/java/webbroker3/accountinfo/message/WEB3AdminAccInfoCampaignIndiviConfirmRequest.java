head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定変更確認ﾘｸｴｽﾄ
                       (WEB3AdminAccInfoCampaignIndiviConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 孟亜南 (中訊) 新規作成
Revision History : 2007/02/01  モデルNo.165
Revision History : 2007/02/28 松井(SCS)モデルNo.203
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

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
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定変更確認ﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定変更確認ﾘｸｴｽﾄ<BR>
 * @@author 孟亜南
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviConfirmRequest extends WEB3GenRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignIndiviConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312039L;

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
     * 手数料割引キャンペーン条件情報
     */
    public WEB3AccInfoCampaignInfo commissionCampaignInfo;

    /**
     * @@roseuid 45C0876100D0
     */
    public WEB3AdminAccInfoCampaignIndiviConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCampaignIndiviConfirmResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １） 更新処理フラグのチェック<BR>
     *   １-１） 更新処理フラグが '0' か '1' か '2'以外の場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02710<BR>
     * <BR>
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]<BR>
     * ２）　@部店コードのチェック<BR>
     * 　@２－１）　@未入力の場合、『部店コード未入力エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00833<BR>
     * 　@２－２）　@３桁以外の場合、『部店コード桁数エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00834<BR>
     * <BR>
     * ３）　@顧客コードのチェック<BR>
     * 　@３－１）　@未入力の場合、『顧客コード未入力エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00835<BR>
     * 　@３－２）　@桁数が6でない場合、『顧客コード桁数エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00836<BR>
     * <BR>
     * ４）　@キャンペーン名称のチェック<BR>
     *   ４－１）　@101バイト以上の場合、『キャンペーン名称桁数エラー』例外をスローする。<BR>
     *        class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02709<BR>
     * [登録タイプ = '1'(個別顧客指定)の場合のみ、４－２）のチェックを行う]<BR>
     *   ４－２）　@未入力の場合、『キャンペーン名称未入力エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02712<BR>
     * [登録タイプ = '2'(強制顧客指定)の場合のみ、４－３）のチェックを行う]<BR>
     * 　@４－３）　@文字が入力されている場合、『キャンペーン名称入力エラー』例外をスローする。<BR>
     *       class : WEB3BusinessLayerException<BR>
     *       tag   :BUSINESS_ERROR_02725<BR>
     * <BR>
     * [リクエストデータ.更新処理フラグ = '0'(登録) or '1'(変更)の場合]<BR>
     * <BR>
     * ５） 登録タイプのチェック<BR>
     *  ５-１） 登録タイプが '1'(個別顧客指定) か '2'(強制個別顧客指定)以外の場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02711<BR>
     * <BR>
     * ６）　@対象期間From、Toのチェック<BR>
     *　@ ６－１）　@対象期間From,対象期間Toが入力された場合、<BR>
　@　@ *　@　@　@　@     対象期間From ＞ 対象期間Toの場合、『対象期間エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02715<BR>
     * <BR>
     * ７）　@徴収率のチェック<BR>
     * 　@７－１）　@未入力の場合、『徴収率未入力エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02080<BR>
     * 　@７－２）　@0～100の整数以外の場合、『徴収率エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02082<BR>
     * @@throws WEB3BaseException
     * @@roseuid 45A327C6011F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validate()";
        log.entering(STR_METHOD_NAME);

        //１） 更新処理フラグのチェック
        //    １-１） 更新処理フラグが '0' か '1' か '2'以外の場合、例外をスローする。
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

        //[リクエストデータ.更新処理フラグ = '0'(登録)の場合]
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag))
        {
            //２）　@部店コードのチェック
            //  ２－１）　@未入力の場合、『部店コード未入力エラー』例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.branchCode))
            {
                log.debug("部店コードが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 部店コード = " + this.commissionCampaignInfo.branchCode);
            }

            //　@２－２）　@３桁以外の場合、『部店コード桁数エラー』例外をスローする。
            if (this.commissionCampaignInfo.branchCode.length() != 3)
            {
                log.debug("部店コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 部店コード = " + this.commissionCampaignInfo.branchCode);
            }

            //３）　@顧客コードのチェック
            //  ３－１）　@未入力の場合、『顧客コード未入力エラー』例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.accountCode))
            {
                log.debug("顧客コードが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 顧客コード = " + this.commissionCampaignInfo.accountCode);
            }

            //  ３－２）　@桁数が6でない場合、『顧客コード桁数エラー』例外をスローする。
            if (this.commissionCampaignInfo.accountCode.length() != 6)
            {
                log.debug("顧客コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 顧客コード = " + this.commissionCampaignInfo.accountCode);
            }

            //４）　@キャンペーン名称のチェック
            //  ４－１）　@101バイト以上の場合、『キャンペーン名称桁数エラー』例外をスローする。
            if (this.commissionCampaignInfo.campaignName != null)
            {
                if (WEB3StringTypeUtility.getByteLength(this.commissionCampaignInfo.campaignName) >= 101)
                {
                    log.debug("キャンペーン名称桁数エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02709,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " キャンペーン名称 = " + this.commissionCampaignInfo.campaignName);
                }
            }

            //[登録タイプ = '1'(個別顧客指定)の場合のみ、５－２）のチェックを行う]
            if (WEB3AccInfoRegistTypeDef.INDIVIDUAL.equals(this.commissionCampaignInfo.registType))
            {
                //  ４－２）　@未入力の場合、『キャンペーン名称未入力エラー』例外をスローする。
                if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.campaignName))
                {
                    log.debug("キャンペーン名称未入力エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02712,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " キャンペーン名称 = " + this.commissionCampaignInfo.campaignName);
                }
            }

            //[登録タイプ = '2'(強制顧客指定)の場合のみ、４－３）のチェックを行う]
            if (WEB3AccInfoRegistTypeDef.FORCE_INDIVIDUAL.equals(this.commissionCampaignInfo.registType))
            {
                //  ４－３）　@文字が入力されている場合、『キャンペーン名称入力エラー』例外をスローする。
                if (!WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.campaignName))
                {
                    log.debug("キャンペーン名称入力エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02725,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " キャンペーン名称 = " + this.commissionCampaignInfo.campaignName);
                }
            }
        }

        //[リクエストデータ.更新処理フラグ = '0'(登録) or '1'(変更)の場合]
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag))
        {

            //  ５-１） 登録タイプが '1'(個別顧客指定) か '2'(強制個別顧客指定)以外の場合、例外をスローする。
            if (!(WEB3AccInfoRegistTypeDef.INDIVIDUAL.equals(this.commissionCampaignInfo.registType)
                    || WEB3AccInfoRegistTypeDef.FORCE_INDIVIDUAL.equals(this.commissionCampaignInfo.registType)))
            {
                log.debug("登録タイプが'1'(個別顧客指定) か '2'(強制個別顧客指定)以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02711,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 登録タイプ = " + this.commissionCampaignInfo.registType);
            }

            //６）　@対象期間From、Toのチェック
            //  ６－１）　@対象期間From,対象期間Toが入力された場合、
            //          対象期間From ＞ 対象期間Toの場合、『対象期間エラー』例外をスローする。
            if (this.commissionCampaignInfo.targetPeriodFrom != null
                && this.commissionCampaignInfo.targetPeriodTo != null)
            {
                if (WEB3DateUtility.compareToDay(this.commissionCampaignInfo.targetPeriodFrom,
                    this.commissionCampaignInfo.targetPeriodTo) > 0)
                {
                    log.debug("対象期間エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 対象期間From = " + this.commissionCampaignInfo.targetPeriodFrom
                        + " 対象期間To = " + this.commissionCampaignInfo.targetPeriodTo);
                }
            }

            //７）　@徴収率のチェック
            //７－１）　@未入力の場合、『徴収率未入力エラー』例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.collectRate))
            {
                log.debug("徴収率が未入力です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02080,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 徴収率 = " + this.commissionCampaignInfo.collectRate);
            }

            //７－２）　@0～100の整数以外の場合、『徴収率エラー』例外をスローする。
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
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
