head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.31.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXAccOpenConnectionImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設接続Impl(WEB3FXAccOpenConnectionImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/16 張騰宇 (中訊) 新規作成 仕様変更モデル1195
*/
package webbroker3.aio;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設接続Impl)<BR>
 * 口座開設接続Impl<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3FXAccOpenConnectionImpl extends WEB3FXConnCommonServiceImpl
    implements WEB3FXAccOpenConnection
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenConnectionImpl.class);

    /**
     * (do口座開設実行)<BR>
     * 口座開設実行を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「do口座開設実行」参照<BR>
     * @@param l_compFxConditionParams  - (会社別FXシステム条件Params)<BR>
     * 会社別FXシステム条件Params<BR>
     * @@param l_fXGftAskingTelegramUnit - (GFT依頼電文明細)<BR>
     * GFT依頼電文明細<BR>
     * @@return WEB3FXGftResultNoticeTelegramUnit
     * @@throws WEB3BaseException
     */
    public WEB3FXGftResultNoticeTelegramUnit doAccountOpen(
        CompFxConditionParams l_compFxConditionParams, WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "doAccountOpen(CompFxConditionParams, WEB3FXGftAskingTelegramUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_fXGftAskingTelegramUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //send外部接続依頼メッセージ(会社別FXシステム条件Params, GFT依頼電文明細)
        //[引数]
        //       　@会社別FXシステム条件 = 引数.会社別FXシステム条件Params
        //       　@GFT依頼電文明細 = 引数.GFT依頼電文明細
        WEB3ExtConnection l_extConnection =
            this.sendExtConnAskingMessage(l_compFxConditionParams, l_fXGftAskingTelegramUnit);

        //updateGFT口座開設状況
        //[引数]
        //証券会社コード： 引数.GFT依頼電文明細.会社コード
        //部店コード： 引数.GFT依頼電文明細.部店コード
        //識別コード： 引数.GFT依頼電文明細.識別コード
        //受付結果コード： 1.1で返却した外部接続.getResult(外部接続.CONNECT_RESULT)の戻り値
        this.updateGFTAccountOpenStatus(
            l_fXGftAskingTelegramUnit.institutionCode,
            l_fXGftAskingTelegramUnit.branchCode,
            l_fXGftAskingTelegramUnit.requestNumber,
            (String)l_extConnection.getResult(WEB3ExtConnection.CONNECT_RESULT));

         //FX口座情報一覧を生成する
        //返却した外部接続から、FX口座情報一覧を取得する：
        List l_lisFXAccInformationUnits = new ArrayList();

        //外部接続.getResult(外部接続.FX_ACC_01) != nullの場合、
        //　@FX口座情報がFX口座情報配列に追加する。
        //　@　@口座番号:外部接続.getResultの戻り値
        //　@　@コース区分：1：1万通貨コー
        Long l_resultFXACC01 = (Long)l_extConnection.getResult(WEB3ExtConnection.FX_ACC_01);
        if (l_resultFXACC01 != null)
        {
            WEB3FXAccInformationUnit l_fXAccInformationUnit = new WEB3FXAccInformationUnit();
            l_fXAccInformationUnit.fxAccountCode = l_resultFXACC01 + "";
            l_fXAccInformationUnit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.ONE_COSE;
            l_lisFXAccInformationUnits.add(l_fXAccInformationUnit);
        }

        //外部接続.getResult(外部接続.FX_ACC_10) != nullの場合、
        //　@FX口座情報がFX口座情報配列に追加する。
        //　@　@口座番号:外部接続.getResultの戻り値
        //　@　@コース区分：2：10万通貨コース
        Long l_resultFXACC10 = (Long)l_extConnection.getResult(WEB3ExtConnection.FX_ACC_10);
        if (l_resultFXACC10 != null)
        {
            WEB3FXAccInformationUnit l_fXAccInformationUnit = new WEB3FXAccInformationUnit();
            l_fXAccInformationUnit.fxAccountCode = l_resultFXACC10 + "";
            l_fXAccInformationUnit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.TEN_COSE;
            l_lisFXAccInformationUnits.add(l_fXAccInformationUnit);
        }

        //外部接続.getResult(外部接続.CFD_ACC) != nullの場合、
        //　@FX口座情報がFX口座情報配列に追加する。
        //　@　@口座番号:外部接続.getResultの戻り値
        //　@　@コース区分：3：CFDコース
        Long l_resultCFDACC = (Long)l_extConnection.getResult(WEB3ExtConnection.CFD_ACC);
        if (l_resultCFDACC != null)
        {
            WEB3FXAccInformationUnit l_fXAccInformationUnit = new WEB3FXAccInformationUnit();
            l_fXAccInformationUnit.fxAccountCode = l_resultCFDACC + "";
            l_fXAccInformationUnit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE;
            l_lisFXAccInformationUnits.add(l_fXAccInformationUnit);
        }

        WEB3FXAccInformationUnit[] l_fXAccInformationUnits =
            new WEB3FXAccInformationUnit[l_lisFXAccInformationUnits.size()];
        l_lisFXAccInformationUnits.toArray(l_fXAccInformationUnits);

        //createGFT結果通知電文明細(GFT依頼電文明細, FX口座情報[], String)
        //[引数]
        //GFT依頼電文明細： 引数.GFT依頼電文明細
        //FX口座情報一覧： 1.3で生成したFX口座情報一覧
        //受付結果コード： 1.1で返却した外部接続.getResult(外部接続.RESULT_CODE)の戻り値
        String l_strResultCode = (String)l_extConnection.getResult(WEB3ExtConnection.RESULT_CODE);
        WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
            this.createGftResultNoticeTelegramUnit(l_fXGftAskingTelegramUnit, l_fXAccInformationUnits, l_strResultCode);

        log.exiting(STR_METHOD_NAME);
        return l_fXGftResultNoticeTelegramUnit;
    }

    /**
     * (updateGFT口座開設状況)<BR>
     * 受信結果をGFT口座開設状況テーブルのデータに反映する。 <BR>
     * <BR>
     * １）FXデータ制御サービス.getGFT口座開設状況（）をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * 識別コード： 引数.識別コード <BR>
     * <BR>
     * ２）FXデータ制御サービス.getGFT口座開設状況（）の戻り値の口座開設状況区分 ！＝ <BR>
     * 「0：口座開設中」の場合、 <BR>
     * 　@「その他のFXシステムエラー」(BUSINESS_ERROR_01800)として例外をthrowする。 <BR>
     * <BR>
     * ３）FXデータ制御サービス.updateGFT口座開設状況（）をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * 識別コード： 引数.識別コード <BR>
     * 受付結果コード： 引数.受付結果コード <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード<BR>
     * @@param l_strResultCode - (受付結果コード)<BR>
     * 受付結果コード<BR>
     * @@throws WEB3BaseException
     */
    public void updateGFTAccountOpenStatus(
        String l_strInstitutionCode, String l_strBranchCode, String l_strRequestNumber, String l_strResultCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateGFTAccountOpenStatus(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）FXデータ制御サービス.getGFT口座開設状況（）をコールする。
        //[引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //識別コード： 引数.識別コード
        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
            l_fxDataControlService.getGFTAccountOpenStatus(l_strInstitutionCode, l_strBranchCode, l_strRequestNumber);

        //２）FXデータ制御サービス.getGFT口座開設状況（）の戻り値の口座開設状況区分 ！＝
        //「0：口座開設中」の場合、
        //　@「その他のFXシステムエラー」(BUSINESS_ERROR_01800)として例外をthrowする。
        if (!WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
            l_gftAccountOpenStatusParams.getAccountOpenStatusDiv()))
        {
            log.debug("その他のFXシステムエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "その他のFXシステムエラー。");
        }

        //３）FXデータ制御サービス.updateGFT口座開設状況（）をコールする。
        //[引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //識別コード： 引数.識別コード
        //受付結果コード： 引数.受付結果コード
        l_fxDataControlService.updateGFTAccountOpenStatus(
            l_strInstitutionCode, l_strBranchCode, l_strRequestNumber, l_strResultCode);

        log.exiting(STR_METHOD_NAME);
    }
}
@
