head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransConnectionImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替接続Impl(WEB3FXTransConnectionImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/16 張騰宇 (中訊) 新規作成 仕様変更モデル1195 1228
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (振替接続Impl)<BR>
 * 振替接続Impl<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3FXTransConnectionImpl extends WEB3FXConnCommonServiceImpl
    implements WEB3FXTransConnection
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransConnectionImpl.class);

    /**
     * (updateGFT振替状況)<BR>
     * 受信結果をGFT振替状況テーブルのデータに反映する。 <BR>
     * <BR>
     * １）FXデータ制御サービス.getGFT振替状況（）をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * 識別コード： 引数.識別コード <BR>
     * <BR>
     * ２）FXデータ制御サービス.getGFT振替状況（）の戻り値の振替状況区分 ＝＝ <BR>
     * 「3：取消」の場合、 <BR>
     * 　@「その他のFXシステムエラー」(BUSINESS_ERROR_01800)として例外をthrowする。 <BR>
     * <BR>
     * ３）FXデータ制御サービス.updateGFT振替状況（）をコールする。 <BR>
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
    public void updateGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode, String l_strRequestNumber, String l_strResultCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateGFTTransferStatus(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）FXデータ制御サービス.getGFT振替状況（）をコールする。
        //[引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //識別コード： 引数.識別コード
        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        GftTransferStatusParams l_gftTransferStatusParams =
            l_fxDataControlService.getGFTTransferStatus(l_strInstitutionCode, l_strBranchCode, l_strRequestNumber);

        //２）FXデータ制御サービス.getGFT振替状況（）の戻り値の振替状況区分 ＝＝
        //「3：取消」の場合、
        //　@「その他のFXシステムエラー」(BUSINESS_ERROR_01800)として例外をthrowする。
        if (WEB3TransferStatusDivDef.CANCEL.equals(l_gftTransferStatusParams.getTransferStatusDiv()))
        {
            log.debug("その他のFXシステムエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "その他のFXシステムエラー。");
        }

        //３）FXデータ制御サービス.updateGFT振替状況（）をコールする。
        //[引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //識別コード： 引数.識別コード
        //受付結果コード： 引数.受付結果コード
        l_fxDataControlService.updateGFTTransferStatus(
            l_strInstitutionCode, l_strBranchCode, l_strRequestNumber, l_strResultCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (do振替実行)<BR>
     * 振替実行を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「do振替実行」参照<BR>
     * @@param l_compFxConditionParams  - (会社別FXシステム条件Params)<BR>
     * 会社別FXシステム条件Params<BR>
     * @@param l_fXGftAskingTelegramUnit - (GFT依頼電文明細)<BR>
     * GFT依頼電文明細<BR>
     * @@return WEB3FXGftResultNoticeTelegramUnit
     * @@throws WEB3BaseException
     */
    public WEB3FXGftResultNoticeTelegramUnit doTransfer(
        CompFxConditionParams l_compFxConditionParams, WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "doTransfer(CompFxConditionParams, WEB3FXGftAskingTelegramUnit)";
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
        //受付結果コード： 返却した外部接続.getResult(外部接続.CONNECT_RESULT)
        this.updateGFTTransferStatus(
            l_fXGftAskingTelegramUnit.institutionCode,
            l_fXGftAskingTelegramUnit.branchCode,
            l_fXGftAskingTelegramUnit.requestNumber,
            (String)l_extConnection.getResult(WEB3ExtConnection.CONNECT_RESULT));

        //createGFT結果通知電文明細(GFT依頼電文明細, FX口座情報[], String)
        //[引数]
        //GFT依頼電文明細： 引数.GFT依頼電文明細
        //FX口座情報一覧：  null
        //受付結果コード： 返却した外部接続.getResult(外部接続.RESULT_CODE)の戻り値
        String l_strResultCode = (String)l_extConnection.getResult(WEB3ExtConnection.RESULT_CODE);
        WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
            this.createGftResultNoticeTelegramUnit(l_fXGftAskingTelegramUnit, null, l_strResultCode);

        log.exiting(STR_METHOD_NAME);
        return l_fXGftResultNoticeTelegramUnit;
    }
}
@
