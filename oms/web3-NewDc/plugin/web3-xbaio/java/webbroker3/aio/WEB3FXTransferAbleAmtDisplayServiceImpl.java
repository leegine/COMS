head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.26.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransferAbleAmtDisplayServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替可能額表示サービスImpl(WEB3FXTransferAbleAmtDisplayServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 張騰宇 (中訊) 新規作成 仕様変更・モデル1174,1183
Revision History : 2009/09/16 孟亞南 (中訊) 仕様変更・モデル1208,1209,1210,1222,1229,1233
Revision History : 2009/10/27 張騰宇 (中訊) 仕様変更・モデル1248
*/

package webbroker3.aio;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXTransferAbleAmtUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (振替可能額表示サービスImpl)<BR>
 * 振替可能額表示サービスImpl<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3FXTransferAbleAmtDisplayServiceImpl implements WEB3FXTransferAbleAmtDisplayService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransferAbleAmtDisplayServiceImpl.class);

    /**
     * (getFXから振替可能額（チェックなし）)<BR>
     * FXから振替可能額（チェックなし）を取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「getFXから振替可能額（チェックなし）」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件)<BR>
     * 会社別FXシステム条件<BR>
     * @@return WEB3FXTransferAbleAmtUnit[]
     * @@throws WEB3BaseException
     */
    public WEB3FXTransferAbleAmtUnit[] getFXTransferAbleAmtNoCheck(
        SubAccount l_subAccount, CompFxConditionParams l_compFxConditionParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getFXTransferAbleAmtCheck(SubAccount, CompFxConditionParams)";
        log.entering (STR_METHOD_NAME);

        //create振替可能額依頼電文明細
        //[引数]
        //補助口座：　@　@　@　@　@　@　@　@　@引数.補助口座 
        //会社別FXシステム条件：　@引数.会社別FXシステム条件
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
            (WEB3FXGftAskingTelegramUnit)this.createTransferAbleAmtAskingTelegramUnit(l_subAccount, l_compFxConditionParams);
        //send外部接続依頼メッセージ
        //[引数]
        //会社別FXシステム条件：　@引数.会社別FXシステム条件
        //GFT依頼電文明細： 取得したGFT依頼電文明細
        WEB3FXConnCommonService l_service =
            (WEB3FXConnCommonService)Services.getService(
                WEB3FXConnCommonService.class);
        WEB3ExtConnection l_extConnection =
            l_service.sendExtConnAskingMessage(
                l_compFxConditionParams,
                l_fxGftAskingTelegramUnit);
        //返却した外部接続から外部接続.RESULT_CODEを取得する
        String l_strResultCode = (String)l_extConnection.getResult(WEB3ExtConnection.RESULT_CODE);

        //外部接続.RESULT_CODE = ”GFT接続エラー”(00000990)の場合
        if (WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000990.equals(l_strResultCode))
        {
            log.debug("外部システム接続エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外部システム接続エラー。");
        }

        //createFX口座情報一覧(証券会社コード : String, 部店コード : String,
        //  顧客コード : String, FXシステムコード : String)
        //[引数]
        // 証券会社コード：引数.会社別FXシステム条件.証券会社コード
        // 部店コード：引数.会社別FXシステム条件.部店コード
        // 顧客コード：引数.補助口座から取得した顧客コード
        // FXシステムコード：引数.会社別FXシステム条件.FXシステムコード
        WEB3FXDataControlService l_dataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);

        WEB3FXAccInformationUnit[] l_accInformationUnits =
            l_dataControlService.createFXAccInformationUnits(
                l_compFxConditionParams.getInstitutionCode(),
                l_compFxConditionParams.getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_compFxConditionParams.getFxSystemCode());

        //(*)プロパティセット
        //FXから振替可能額情報[].FX口座情報 = createFX口座情報一覧()の戻り値
        int l_intAccInformationUnitsLength = 0;
        if (l_accInformationUnits != null)
        {
            l_intAccInformationUnitsLength = l_accInformationUnits.length;
        }

        //FXから振替可能額情報( )
        WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnits =
            new WEB3FXTransferAbleAmtUnit[l_intAccInformationUnitsLength];
        for (int i = 0; i < l_intAccInformationUnitsLength; i++)
        {
            l_transferAbleAmtUnits[i] = new WEB3FXTransferAbleAmtUnit();
            //FXから振替可能額情報.コース区分 = createFX口座情報一覧()の戻り値の要素.コース区分
            l_transferAbleAmtUnits[i].fxCourseDiv = l_accInformationUnits[i].fxCourseDiv;

            //FXから振替可能額情報.口座番号 = createFX口座情報一覧()の戻り値の要素.口座番号
            l_transferAbleAmtUnits[i].fxAccountCode = l_accInformationUnits[i].fxAccountCode;

            //FXから振替可能額情報.振替可能額 = null
            l_transferAbleAmtUnits[i].transferableAmt = null;
        }

        WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnitsReturns = null;
        //get結果コード取得した結果コード == 00000000：処理完了の場合
        if (WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000000.equals(l_strResultCode))
        {
            //get振替可能額
            //[引数]
            //外部接続：send外部接続依頼メッセージ()の戻り値 
            //FXから振替可能額情報[]：1.4で生成したFXから振替可能額情報[]
            l_transferAbleAmtUnitsReturns =
                this.getTransferAbleAmt(
                    l_extConnection, l_transferAbleAmtUnits);

            //FXから振替可能額情報を返却する
            log.exiting(STR_METHOD_NAME);
            return l_transferAbleAmtUnitsReturns;
        }

        //FXから振替可能額情報を返却する
        log.exiting(STR_METHOD_NAME);
        return l_transferAbleAmtUnits;
    }

    /**
     * (getFXから振替可能額（チェックあり）)<BR>
     * FXから振替可能額（チェックあり）を取得する。<BR>
     * <BR>
     * １）　@this.getFXから振替可能額（チェックなし）をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@補助口座：引数.補助口座<BR>
     * 　@　@　@会社別FXシステム条件：引数.会社別FXシステム条件<BR>
     * <BR>
     * ２）　@FXから振替可能額情報が取得できない場合、例外「SYSTEM_ERROR_80005」をスローする。<BR>
     * <BR>
     * ３）　@３－１）　@１）で戻り値から引数.コース区分によって該当するＦＸから<BR>
     * 　@　@　@　@　@振替可能額情報を取得する。<BR>
     * 　@　@　@３－２）　@指定したコース区分によって、<BR>
     * 　@　@　@　@　@該当する振替可能額情報.コース区分が存在しない場合、<BR>
     * 　@　@　@　@　@　@　@例外「指定したコースの可能額が取得できません」をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:   BUSINESS_ERROR_03162<BR>
     * 　@　@　@３－３）　@３－１）で取得した振替可能額 != nullの場合、以下の処理を行う。<BR>
     * <BR>
     * 　@　@　@３－４）　@３－１）で取得した「振替可能額」は引数.振替金額より小さい場合、<BR>
     * 　@　@　@　@　@例外「振替金額が可能額を超えています」をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:   BUSINESS_ERROR_00761<BR>
     * ４）　@３－１）で取得した「ＦＸから振替可能額情報」を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件)<BR>
     * 会社別FXシステム条件<BR>
     * @@param l_strTransferAmount - (振替金額)<BR>
     * 振替金額<BR>
     * @@param l_strCourseDiv - (コース区分)<BR>
     * コース区分<BR>
     * @@return WEB3FXTransferAbleAmtUnit
     * @@throws WEB3BaseException
     */
    public WEB3FXTransferAbleAmtUnit getFXTransferAbleAmtCheck(
        SubAccount l_subAccount, CompFxConditionParams l_compFxConditionParams,
        String l_strTransferAmount, String l_strCourseDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFXTransferAbleAmtNoCheck("
            + "SubAccount, CompFxConditionParams, String, String)";
        log.entering (STR_METHOD_NAME );

        //this.getFXから振替可能額（チェックなし）をコールする
        //[引数]
        // 補助口座：引数.補助口座
        // 会社別FXシステム条件：引数.会社別FXシステム条件
        WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnits =
            this.getFXTransferAbleAmtNoCheck(l_subAccount, l_compFxConditionParams);

        int l_intLength = 0;
        if (l_transferAbleAmtUnits == null || l_transferAbleAmtUnits.length == 0)
        {
            //２）　@FXから振替可能額情報が取得できない場合、例外「SYSTEM_ERROR_80005」をスローする
            log.debug("FX口座情報取得エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        else
        {
            l_intLength = l_transferAbleAmtUnits.length;
        }

        //３－１）１）で戻り値から引数.コース区分によって該当するＦＸから振替可能額情報を取得する。
        int l_intCount = 0;
        boolean l_blnHasFlag = false;
        for (int i = 0; i < l_intLength; i++)
        {
            if (WEB3Toolkit.isEquals(l_strCourseDiv, l_transferAbleAmtUnits[i].fxCourseDiv))
            {
                l_blnHasFlag = true;
                l_intCount = i;
                break;
            }
        }

        if (l_blnHasFlag)
        {
            if (l_transferAbleAmtUnits[l_intCount].transferableAmt != null)
            {
                //３－４）　@３－１）で取得した振替可能額 != nullの場合、以下の処理を行う
                //３－５）　@３－１）で取得した「振替可能額」は引数.振替金額より小さい場合、
                // 例外「振替金額が可能額を超えています」をスローする。
                if (Double.parseDouble(l_transferAbleAmtUnits[l_intCount].transferableAmt)
                    < Double.parseDouble(l_strTransferAmount))
                {
                    log.debug("振替金額が可能額を超えています。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00761,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "振替金額が可能額を超えています。");
                }
            }

            //取得した「ＦＸから振替可能額情報」を返却する
            log.exiting(STR_METHOD_NAME);
            return l_transferAbleAmtUnits[l_intCount];
        }
        else
        {
            //FXから振替可能額情報が取得できない場合、
            //指定したコース区分によって、該当する振替可能額情報.コース区分が存在しない場合
            //例外「指定したコースの可能額が取得できません」をスローする
            log.debug("指定したコースの可能額が取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03162,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定したコースの可能額が取得できません。");
        }
    }
    
    /**
     * (get振替可能額)<BR>
     * <BR>
     * 振替可能額を取得する。 <BR>
     * <BR>
     * １）引数.外部接続.getResult()を呼び出す。<BR>
     * [引数] <BR>
     * 項目名： 外部接続.AMOUNT<BR>
     * <BR>
     * ２）で取得した振替可能額hashmapから、各口座番号がkeyとして、べつべつに値を与える。<BR>
     * 　@　@引数.FXから振替可能額情報[ｎ].口座番号 == １）で取得した要素.口座番号の場合、<BR>
     * 　@　@FXから振替可能額情報[ｎ].振替可能額 = １）で取得した要素の口座番号がkeyとして、<BR>
     * 　@　@取得するhashmapのvalue。<BR>
     * 　@　@（*）振替可能額を取得できない場合、もしくは取得した値が数値以外の場合、nullをセットする。<BR>
     * <BR>
     * ３）FXから振替可能額情報を返却する。<BR>
     * <BR>
     * @@param l_extConnection - (外部接続)<BR>
     * 外部接続<BR>
     * @@param l_transferAbleAmtUnit - (FXから振替可能額情報)<BR>
     * FXから振替可能額情報<BR>
     * @@return WEB3FXTransferAbleAmtUnit[]
     */
    protected WEB3FXTransferAbleAmtUnit[] getTransferAbleAmt(
        WEB3ExtConnection l_extConnection,
        WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnit)
    {
        final String STR_METHOD_NAME = "getTransferAbleAmt(WEB3ExtConnection, WEB3FXTransferAbleAmtUnit)";
        log.entering (STR_METHOD_NAME );
        //引数.外部接続.getResult()を呼び出す。
        //[引数]
        //項目名： 外部接続.AMOUNT
        Object l_result = l_extConnection.getResult(WEB3ExtConnection.AMOUNT);

        HashMap l_hmAmount = null;
        if (l_result != null)
        {
            l_hmAmount = (HashMap)l_result;
        }
        else
        {
            l_hmAmount = new HashMap();
        }
        //取得した振替可能額hashmapから、各口座番号がkeyとして、べつべつに値を与える
        //引数.FXから振替可能額情報[ｎ].口座番号 == １）で取得した要素.口座番号の場合、
        //FXから振替可能額情報[ｎ].振替可能額 = １）で取得した要素の口座番号がkeyとして、
        //取得するhashmapのvalue。　@
        for (int i = 0; i < l_transferAbleAmtUnit.length; i++)
        {
            if (l_hmAmount.containsKey(l_transferAbleAmtUnit[i].fxAccountCode))
            {
                String l_strTransferableAmt = (String)l_hmAmount.get(l_transferAbleAmtUnit[i].fxAccountCode);
                if (WEB3StringTypeUtility.isNumber(l_strTransferableAmt))
                {
                    l_transferAbleAmtUnit[i].transferableAmt = l_strTransferableAmt;
                }
                else 
                {
                    //取得した値が数値以外の場合、nullをセットする。
                    l_transferAbleAmtUnit[i].transferableAmt = null;
                }
            }
            else
            {
                //（*）振替可能額を取得できない場合、nullをセットする。
                l_transferAbleAmtUnit[i].transferableAmt = null;
            }
        }

        //FXから振替可能額情報を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_transferAbleAmtUnit;
    }
    
    /**
     * (create振替可能額依頼電文明細)<BR>
     * <BR>
     * 依頼電文明細を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「create振替可能額依頼電文明細」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件)<BR>
     * 会社別FXシステム条件<BR>
     * @@return Message
     * @@throws WEB3BaseException
     */
    protected Message createTransferAbleAmtAskingTelegramUnit(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createTransferAbleAmtAskingTelegramUnit("
            + "SubAccount, CompFxConditionParams)";
        log.entering (STR_METHOD_NAME );
        //getFX顧客(証券会社コード : String, 部店コード : String, FXシステムコード : String, 顧客コード : String)
        WEB3FXDataControlService l_dataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //証券会社コード = 補助口座から取得した.証券会社コード
        //部店コード = 補助口座から取得した.部店コード
        //FXシステムコード = 会社別FXシステム条件.FXシステムコード
        //顧客コード = 補助口座から取得した顧客コード
        FxAccountParams l_fxAccountParams = null;
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        try
        {
            l_fxAccountParams = l_dataControlService.getFXAccount(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_compFxConditionParams.getFxSystemCode(),
                l_subAccount.getMainAccount().getAccountCode());
        } 
        catch (NotFoundException l_ex)
        {
            log.error(" テーブルに該当するデータがありません: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //get変換FXログインID(証券会社ID : long, FXシステムコード : String, FXログインID頭文字 : String, FXログインＩＤ : long)
        //証券会社ID：　@引数.補助口座から取得した証券会社ID
        //FXシステムコード：　@引数.会社別FXシステム条件Params.FXシステムコード
        //FXログインID頭文字：　@引数.会社別FXシステム条件Params.FXログインID頭文字
        //FXログインID：　@1.1）で取得したFX顧客Params.FXログインID
        String l_strChangedFXLoginID = l_dataControlService.getChangedFXLoginID(
            l_subAccount.getInstitution().getInstitutionId(),
            l_compFxConditionParams.getFxSystemCode(),
            l_compFxConditionParams.getFxHeadOfLoginId(),
            l_fxAccountParams.getFxLoginId());

        // get新規識別コード(証券会社コード : String, 部店コード : String, 銘柄タイプ : ProductTypeEnum)
        // [引数] 
        // 証券会社コード：  引数.会社別FXシステム条件.証券会社コード
        // 部店コード： 引数.会社別FXシステム条件.部店コード
        // 銘柄タイプ： 5（現金）
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_compFxConditionParams.getInstitutionCode(), 
            l_compFxConditionParams.getBranchCode(), 
            ProductTypeEnum.CASH);

        //GFT依頼電文明細のインスタンスを生成する。 
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
        //（*）プロパティセット
        //(*)GFT依頼電文明細に必要なプロパティをセットする
        //（下記以外のプロパティは設定しない）
        //DIR→GFT送信日時 ：現在時刻（システムタイムスタンプ）
        l_fxGftAskingTelegramUnit.dirSendTime =
            WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

        //処理区分 ：07：振替可能額
        l_fxGftAskingTelegramUnit.gftOperationDiv = WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT;

        //初期ログインID：get変換FXログインID
        l_fxGftAskingTelegramUnit.fxFirstLoginId = l_strChangedFXLoginID;
        
        //会社コード：補助口座.証券会社コード
        l_fxGftAskingTelegramUnit.institutionCode = l_subAccount.getInstitution().getInstitutionCode();

        //識別コード：get新規識別コード()の戻り値
        l_fxGftAskingTelegramUnit.requestNumber = l_strNewNumber;

        //部店コード：  引数.会社別FXシステム条件.部店コード
        l_fxGftAskingTelegramUnit.branchCode = l_compFxConditionParams.getBranchCode();

        //GFT依頼電文明細を返却する
        log.exiting(STR_METHOD_NAME);
        return l_fxGftAskingTelegramUnit;
    }
}
@
