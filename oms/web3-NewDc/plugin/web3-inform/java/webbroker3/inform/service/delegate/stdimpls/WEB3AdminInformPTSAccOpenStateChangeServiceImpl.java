head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.45.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者PTS口座開設状況変更サービスImpl(WEB3AdminInformPTSAccOpenStateChangeServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/27 柴双紅(中訊) 新規作成 モデルNo.129、No.131、No.132、ＤＢ更新仕様No.021
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExtDiv1Def;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.define.WEB3informAfterPtsAccOpenDiv;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccOpenStateChangeService;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;

/**
 * 管理者PTS口座開設状況変更サービスImpl<BR>
 * 管理者PTS口座開設状況変更サービス実装クラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccOpenStateChangeServiceImpl implements
    WEB3AdminInformPTSAccOpenStateChangeService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformPTSAccOpenStateChangeServiceImpl.class);

    /**
     * @@roseuid 47B9271A015F
     */
    public WEB3AdminInformPTSAccOpenStateChangeServiceImpl()
    {

    }

    /**
     * 管理者PTS口座開設状況変更サービス処理を行う。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A0132203E3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeSrcRequest)
        {
            //管理者PTS口座開設状況変更検索画面の取得を行う。
            l_response =
                this.getSearchScreen((WEB3AdminInformPTSAccOpenStateChangeSrcRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeInpRequest)
        {
            //管理者PTS口座開設状況変更入力画面の取得を行う。
            l_response =
                this.getInputScreen((WEB3AdminInformPTSAccOpenStateChangeInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeCnfRequest)
        {
            //管理者PTS口座開設状況変更確認処理を行う。
            l_response =
                this.validateChange((WEB3AdminInformPTSAccOpenStateChangeCnfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeCmpRequest)
        {
            //管理者PTS口座開設状況変更完了処理を行う。
            l_response =
                this.submitChange((WEB3AdminInformPTSAccOpenStateChangeCmpRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get検索画面)<BR>
     * 管理者PTS口座開設状況変更検索画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（PTS口座開設状況変更）get検索画面」 参照<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeSrcResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A0133F0249
     */
    protected WEB3AdminInformPTSAccOpenStateChangeSrcResponse getSearchScreen(
        WEB3AdminInformPTSAccOpenStateChangeSrcRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchScreen(WEB3AdminInformPTSAccOpenStateChangeSrcRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        // 機@能カテゴリコード： "A0501"
        // is更新：false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            false);

        //createResponse( )
        WEB3AdminInformPTSAccOpenStateChangeSrcResponse l_response =
            (WEB3AdminInformPTSAccOpenStateChangeSrcResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面 )<BR>
     * 管理者PTS口座開設状況変更入力画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（PTS口座開設状況変更）get入力画面」 参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A013730264
     */
    protected WEB3AdminInformPTSAccOpenStateChangeInpResponse getInputScreen(
        WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminInformPTSAccOpenStateChangeInpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        // 機@能カテゴリコード： "A0501"
        // is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            true);

        //validate部店権限(部店コード : String)
        //[引数]
        // 部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
        //[get顧客()に指定する引数]
        // 証券会社コード：　@get証券会社コード()の戻り値
        // 部店コード：　@リクエストデータ.部店コード
        // 口座コード：　@リクエストデータ.顧客コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAcccount =
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);

        //getDataSourceObject( )
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAcccount.getDataSourceObject();

        //createResponse( )
        WEB3AdminInformPTSAccOpenStateChangeInpResponse l_response =
            (WEB3AdminInformPTSAccOpenStateChangeInpResponse)l_request.createResponse();

        //プロパティセット
        //顧客名：　@顧客行.名前（苗字）
        l_response.accountName = l_mainAccountRow.getFamilyName();

        //変更前申込区分：　@顧客行.PTS口座開設区分
        l_response.beforePtsAccOpenDiv = l_mainAccountRow.getPtsAccOpenDiv();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更)<BR>
     * 管理者PTS口座開設状況変更確認処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者（PTS口座開設状況変更）validate変更」 参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A0137612F3
     */
    protected WEB3AdminInformPTSAccOpenStateChangeCnfResponse validateChange(
        WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminInformPTSAccOpenStateChangeCnfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        // 機@能カテゴリコード： "A0501"
        // is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            true);

        //validate部店権限(部店コード : String)
        //[引数]
        // 部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
        //[get顧客()に指定する引数]
        // 証券会社コード：　@get証券会社コード()の戻り値
        // 部店コード：　@リクエストデータ.部店コード
        // 口座コード：　@リクエストデータ.顧客コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAcccount =
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);

        //validate申込区分変更有無(顧客, String)
        //[引数]
        // 顧客：　@get顧客()の戻り値
        // 変更後申込区分：リクエストデータ.変更後申込区分
        this.validateApplyDivIsChange(
            l_mainAcccount,
            l_request.afterPtsAccOpenDiv);

        //createResponse( )
        WEB3AdminInformPTSAccOpenStateChangeCnfResponse l_response =
            (WEB3AdminInformPTSAccOpenStateChangeCnfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit変更)<BR>
     * 管理者PTS口座開設状況変更完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（PTS口座開設状況変更）submit変更」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeCmpResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B11D8A024B
     */
    protected WEB3AdminInformPTSAccOpenStateChangeCmpResponse submitChange(
        WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminInformPTSAccOpenStateChangeCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        // 機@能カテゴリコード： "A0501"
        // is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            true);

        //validate部店権限(部店コード : String)
        //[引数]
        // 部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);

        //validate取引パスワード(パスワード : String)
        //[validate取引パスワード()に指定する引数]
        // パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
        //[get顧客()に指定する引数]
        // 証券会社コード：　@get証券会社コード()の戻り値
        // 部店コード：　@リクエストデータ.部店コード
        // 口座コード：　@リクエストデータ.顧客コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAcccount =
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);

        //getDataSourceObject( )
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAcccount.getDataSourceObject();

        //validate申込区分変更有無(顧客, String)
        //[引数]
        // 顧客：　@get顧客()の戻り値
        // 変更後申込区分：リクエストデータ.変更後申込区分
        this.validateApplyDivIsChange(
            l_mainAcccount,
            l_request.afterPtsAccOpenDiv);

        //get新規識別コード(String, String)
        //[引数]
        // 証券会社コード： 顧客行.証券会社コード
        // 連絡種別： リクエストデータ.連絡種別
        WEB3InformHostReqOrderNumberManageService l_informHostReqOrderNumberManageService =
            (WEB3InformHostReqOrderNumberManageService)Services.getService(
                WEB3InformHostReqOrderNumberManageService.class);
        String l_strRequestCode =
            l_informHostReqOrderNumberManageService.getNewOrderRequestCode(
                l_mainAccountRow.getInstitutionCode(),
                l_request.informType);

        //get各種連絡(顧客, String)
        //[引数]
        // 顧客：　@顧客オブジェクト
        // 連絡種別：　@リクエストデータ.連絡種別
        WEB3Inform l_inform =
            WEB3Inform.getVariousInform(l_mainAcccount, l_request.informType);

        //get管理者コード( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //get各種連絡の戻り値 != null の場合、以下の処理を行う。
        if (l_inform != null)
        {
            //updatePTS各種連絡(Map)
            // ・区分１：　@"0"（無効）
            // ・更新者コード：　@get管理者コード()の戻り値
            // ・更新日時：　@処理日時
            Map l_informUpdateMap = new HashMap();
            l_informUpdateMap.put("ext_div1", WEB3ExtDiv1Def.INVALIDITY);
            l_informUpdateMap.put("last_updater", l_strAdministratorCode);
            l_informUpdateMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            l_inform.updateInform(l_informUpdateMap);
        }

        //createNew各種連絡(顧客, String, String, String, String)
        //[引数]
        // 顧客：　@顧客
        // 連絡種別：　@リクエストデータ.連絡種別
        // PTS口座開設区分：　@リクエストデータ.変更後申込区分
        // 更新者コード：　@get管理者コード()の戻り値
        // 識別コード：　@連絡管理識別コード採番サービス.get新規識別コード()の戻り値
        WEB3Inform l_newVariousInform =
            WEB3Inform.createNewVariousInform(
                l_mainAcccount,
                l_request.informType,
                l_request.afterPtsAccOpenDiv,
                l_strAdministratorCode,
                l_strRequestCode);

        // saveNew各種連絡( )
        l_newVariousInform.saveNewVariousInform();

        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doUpdateQuery(arg0 : PrimaryKey, arg1 : Map)
            //[doUpdateQuery()に指定する引数]
            // arg0（primaryKey）：　@顧客行.口座ID
            // arg1（changes）：
            //　@　@【ｘTrade】補足資料.DB更新「PTS口座開設申込_顧客マスター」参照。
            MainAccountPK l_mainAccountPK =
                new MainAccountPK(l_mainAccountRow.getAccountId());

            Map l_mainAccountUpdateMap = new HashMap();
            //PTS口座開設区分
            // 管理者の場合、リクエストデータ.変更後申込区分
            l_mainAccountUpdateMap.put("pts_acc_open_div", l_request.afterPtsAccOpenDiv);

            //PTS口座開設区分更新者コード
            // 管理者の場合、管理者テーブル.管理者コード
            l_mainAccountUpdateMap.put("pts_acc_open_div_last_updater", l_strAdministratorCode);

            //PTS口座開設区分更新日時
            // 処理日時
            l_mainAccountUpdateMap.put("pts_acc_open_div_timestamp", GtlUtils.getSystemTimestamp());

            l_queryProcessor.doUpdateQuery(
                l_mainAccountPK,
                l_mainAccountUpdateMap);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //createResponse( )
        WEB3AdminInformPTSAccOpenStateChangeCmpResponse l_response =
            (WEB3AdminInformPTSAccOpenStateChangeCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate申込区分変更有無)<BR>
     * PTS口座開設区分に変更があるかチェックを行う。<BR>
     * <BR>
     * １）　@顧客.isPTS口座開設()をコールする。<BR>
     * <BR>
     * ２）　@以下のいずれかに該当する場合、例外をthrowする。<BR>
     * 　@　@　@エラーメッセージ「申込区分に変更がありません。」<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_03046<BR>
     * <BR>
     * ２－１）　@引数.変更後申込区分 == "1"（開設） の場合、<BR>
     * 　@　@　@　@　@１）の戻り値 == true<BR>
     * <BR>
     * ２－２）　@引数.変更後申込区分 == "0"（未開設） の場合、<BR>
     * 　@　@　@　@　@１）の戻り値 == false<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strAfterChangeApplyDiv - (変更後申込区分)<BR>
     * 変更後申込区分<BR>
     * 0：未開設<BR>
     * 1：開設<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A2C31102E5
     */
    private void validateApplyDivIsChange(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strAfterChangeApplyDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateApplyDivIsChange(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        //顧客.isPTS口座開設()をコールする。
        boolean l_blnIsPTSAccountOpen = l_mainAccount.isPTSAccountOpen();

        boolean l_blnErrorFlag = false;
        //引数.変更後申込区分 == "1"（開設） の場合、
        //isPTS口座開設()の戻り値 == true
        if (WEB3informAfterPtsAccOpenDiv.OPEN.equals(l_strAfterChangeApplyDiv)
            && l_blnIsPTSAccountOpen)
        {
            l_blnErrorFlag = true;
        }

        //引数.変更後申込区分 == "0"（未開設） の場合、
        //isPTS口座開設()の戻り値 == false
        if (WEB3informAfterPtsAccOpenDiv.NOT_OPEN.equals(l_strAfterChangeApplyDiv)
            && !l_blnIsPTSAccountOpen)
        {
            l_blnErrorFlag = true;
        }

        if (l_blnErrorFlag)
        {
            log.debug("申込区分に変更がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03046,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "申込区分に変更がありません。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
