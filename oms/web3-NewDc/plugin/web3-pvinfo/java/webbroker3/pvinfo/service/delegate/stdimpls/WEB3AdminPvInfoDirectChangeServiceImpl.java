head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者ダイレクト指定変更サービスImpl(WEB3AdminPvInfoDirectChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/27 李弘毅(中訊) 作成
Revesion History : 2007/06/27 徐宏偉(中訊) モデル078、実装の問題002
Revesion History : 2009/07/06 武波(中訊) モデル115,116,120
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.WEB3PvInfoRegistTargetAccountUploadCsv;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.define.WEB3PvInfoUploadStateDivDef;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.message.WEB3PvInfoUploadHistoryUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectChangeService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者ダイレクト指定変更サービスImpl)<BR>
 * 管理者ダイレクト指定変更サービス実装クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectChangeServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoDirectChangeService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectChangeServiceImpl.class);

    /**
     * ダイレクト指定変更処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・ダイレクト指定変更入力リクエストの場合<BR>
     * 　@this.getダイレクト指定変更入力画面()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・ダイレクト指定変更確認リクエストの場合<BR>
     * 　@this.validateダイレクト指定変更()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・ダイレクト指定変更完了リクエストの場合<BR>
     * 　@this.submitダイレクト指定変更()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・ダイレクト指定変更アップロード中止リクエストの場合<BR>
     * 　@this.undoダイレクト指定変更()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 416103B401C1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminPvInfoDirectChangeInputRequest)
        {
            l_response = this.getDirectChangeInputScreen((WEB3AdminPvInfoDirectChangeInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminPvInfoDirectChangeConfirmRequest)
        {
            l_response = this.validateDirectChange((WEB3AdminPvInfoDirectChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminPvInfoDirectChangeCompleteRequest)
        {
            l_response = this.submitDirectChange((WEB3AdminPvInfoDirectChangeCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminPvInfoDirectChangeCancelRequest)
        {
            l_response = this.undoDirectChangeUpload((WEB3AdminPvInfoDirectChangeCancelRequest)l_request);
        }
        else
        {
            String l_strErrorMessage =
                "パラメータの類型が不正、該当するWEB3AdminPvInfoDirectChangeInputRequest," +
                "WEB3AdminPvInfoDirectChangeConfirmRequest,WEB3AdminPvInfoDirectChangeCancelRequest,WEB3AdminPvInfoDirectChangeCompleteRequest類型。";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getダイレクト指定変更入力画面)<BR>
     * ダイレクト指定変更入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者ダイレクト指定変更サービス)getダイレクト指定変更入力画面」参照<BR>
     * ========================================================<BR>
     * get表示内容Params(表示内容ID:long)<BR>
     * <BR>
     * nullが返却された場合は、<BR>
     * 「該当データ未存在」の例外をスローする。<BR>
     *   class: WEB5BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定変更入力リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputResponse
     * @@roseuid 41610416027D
     */
    protected WEB3AdminPvInfoDirectChangeInputResponse getDirectChangeInputScreen(WEB3AdminPvInfoDirectChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDirectChangeInputScreen(WEB3AdminPvInfoDirectChangeInputRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1 validate()
        l_request.validate();

        //2 getInstanceFromログイン情報()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //3 validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);

        //4 get表示内容Params(long)
        long l_lngDisplayContentsId = Long.valueOf(l_request.displayContentsId).longValue();
        DisplayContentsParams l_dispContentsParams = l_dataManager.getDisplayContentsParams(l_lngDisplayContentsId);

        if (l_dispContentsParams == null)
        {
            log.error("get表示内容Params失敗する.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5 表示内容情報()
        WEB3PvInfoDisplayContentsUnit l_dispContentsUnit = new WEB3PvInfoDisplayContentsUnit();

        //6 (*)プロパティセット
        l_dispContentsUnit.displayContentsId = WEB3StringTypeUtility.formatNumber(l_dispContentsParams.display_contents_id);
        log.debug("表示内容情報.displayContentsId" + "=" + l_dispContentsUnit.displayContentsId);
        l_dispContentsUnit.conditionNumber = l_dispContentsParams.condition_no;
        log.debug("表示内容情報.conditionNumber" + "=" + l_dispContentsUnit.conditionNumber);
        l_dispContentsUnit.priorityDiv = l_dispContentsParams.priority_div;
        log.debug("表示内容情報.priorityDiv" + "=" + l_dispContentsUnit.priorityDiv);
        l_dispContentsUnit.listStartDate = WEB3DateUtility.formatDate(l_dispContentsParams.term_from, "yyyyMMddHHmm");
        log.debug("表示内容情報.listStartDate" + "=" + l_dispContentsUnit.listStartDate);
        l_dispContentsUnit.listEndDate = WEB3DateUtility.formatDate(l_dispContentsParams.term_to, "yyyyMMddHHmm");
        log.debug("表示内容情報.listEndDate" + "=" + l_dispContentsUnit.listEndDate);
        l_dispContentsUnit.displayTitle = l_dispContentsParams.display_title;
        log.debug("表示内容情報.displayTitle" + "=" + l_dispContentsUnit.displayTitle);
        l_dispContentsUnit.displayMessage = l_dispContentsParams.display_message;
        log.debug("表示内容情報.displayMessage" + "=" + l_dispContentsUnit.displayMessage);
        l_dispContentsUnit.displayColor = l_dispContentsParams.display_color;
        log.debug("表示内容情報.displayColor" + "=" + l_dispContentsUnit.displayColor);

        if (WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_dispContentsParams.blink_display_flag))
        {
            l_dispContentsUnit.blinkDisplayFlag = true;
        }
        else
        {
            l_dispContentsUnit.blinkDisplayFlag = false;
        }
        log.debug("表示内容情報.blinkDisplayFlag" + "=" + l_dispContentsUnit.blinkDisplayFlag);

        l_dispContentsUnit.displayUrl = l_dispContentsParams.display_url;
        log.debug("表示内容情報.displayUrl" + "=" + l_dispContentsUnit.displayUrl);

        if (WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_dispContentsParams.last_update_time_display_flag))
        {
            l_dispContentsUnit.lastUpdateTimeDisplayFlag = false;
        }
        else
        {
            l_dispContentsUnit.lastUpdateTimeDisplayFlag = true;
        }
        log.debug("表示内容情報.lastUpdateTimeDisplayFlag" + "=" + l_dispContentsUnit.lastUpdateTimeDisplayFlag);

        if (WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_dispContentsParams.effective_flag))
        {
            l_dispContentsUnit.effectiveFlag = false;
        }
        else
        {
            l_dispContentsUnit.effectiveFlag = true;
        }
        log.debug("表示内容情報.effectiveFlag" + "=" + l_dispContentsUnit.effectiveFlag);
        l_dispContentsUnit.displayDevice = l_dispContentsParams.display_device;
        log.debug("表示内容情報.displayDevice" + "=" + l_dispContentsUnit.displayDevice);
        l_dispContentsUnit.lastUpdateMember = l_dispContentsParams.last_update_member;
        log.debug("表示内容情報.lastUpdateMember" + "=" + l_dispContentsUnit.lastUpdateMember);
        l_dispContentsUnit.lastUpdatedTimestamp = l_dispContentsParams.last_updated_timestamp;
        log.debug("表示内容情報.lastUpdatedTimestamp" + "=" + l_dispContentsUnit.lastUpdatedTimestamp);

        //7 登録対象顧客アップロードCSV()
        WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv();

        //8 get最新アップロード履歴(long)[get最新アップロード履歴()に指定する引数]、データキー：　@0
        AdministratorUploadRow l_adminUploadRow = l_registTargetAccountUploadCsv.getLatestUploadAction(0);

        //9 (*)アップロード履歴が存在する場合、処理実施
        WEB3PvInfoUploadHistoryUnit l_uploadHistoryUnit = null;
        if (l_adminUploadRow != null)
        {
            // 9.1  ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細()
            l_uploadHistoryUnit = new WEB3PvInfoUploadHistoryUnit();

            //9.2(*)プロパティセット
            if (l_adminUploadRow.getUploadEndTimestamp() == null)
            {
                l_uploadHistoryUnit.uploadStateDiv = WEB3PvInfoUploadStateDivDef.UPLOAD_STATUS_PROCESS;
            }
            else
            {
                l_uploadHistoryUnit.uploadStateDiv = WEB3PvInfoUploadStateDivDef.UPLOAD_STATUS_COMPLETE;
            }
            log.debug("ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細.uploadStateDiv" + "=" + l_uploadHistoryUnit.uploadStateDiv);
            l_uploadHistoryUnit.uploadNumber = WEB3StringTypeUtility.formatNumber(l_adminUploadRow.getUploadCount());
            log.debug("ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細.uploadNumber" + "=" + l_uploadHistoryUnit.uploadNumber);
            l_uploadHistoryUnit.uploadStartDate = l_adminUploadRow.getUploadStartTimestamp();
            log.debug("ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細.uploadStartDate" + "=" + l_uploadHistoryUnit.uploadStartDate);
            l_uploadHistoryUnit.uploadEndDate = l_adminUploadRow.getUploadEndTimestamp();
            log.debug("ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細.uploadEndDate" + "=" + l_uploadHistoryUnit.uploadEndDate);
            l_uploadHistoryUnit.pvInfoErrorId = l_adminUploadRow.getMessageCode();
            log.debug("ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細.pvInfoErrorId" + "=" + l_uploadHistoryUnit.pvInfoErrorId);
        }

        //10 create Response()
        WEB3AdminPvInfoDirectChangeInputResponse l_response = (WEB3AdminPvInfoDirectChangeInputResponse)l_request.createResponse();

        //11 (*)プロパティセット
        l_response.displayContentsUnit = l_dispContentsUnit;
        l_response.uploadHistoryUnit = l_uploadHistoryUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validateダイレクト指定変更)<BR>
     * ダイレクト指定変更確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者ダイレクト指定変更サービス)validateダイレクト指定変更」参照<BR>
     * ========================================================<BR>
     * get部店(証券会社コード:String, 部店コード:String)<BR>
     * <BR>
     * いづれかが例外をスローした場合は、<BR>「顧客未存在エラー」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * ========================================================<BR>
     * get顧客(証券会社コード:String, 部店コード:String)<BR>
     * <BR>
     * いづれかが例外をスローした場合は、<BR>「顧客未存在エラー」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定変更確認リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmResponse
     * @@roseuid 41610416029C
     */
    protected WEB3AdminPvInfoDirectChangeConfirmResponse validateDirectChange(WEB3AdminPvInfoDirectChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDirectChange(WEB3AdminPvInfoDirectChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;

        //1 validate()
        l_request.validate();

        //2 getInstanceFromログイン情報()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //3 validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);

        //4  (*1)分岐フロー
        int l_intRowCnt = 0;
        long l_lngAdminUploadId = 0;

        if (l_request.uploadFile != null)
        {
            //4.1 reset受付時間区分(String)
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD_DAYLONG);

            //4.2reset注文受付トランザクション(String)
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);

            //4.3 validate注文受付可能()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            //4.4 アップロードTempテーブル登録処理
            //4.4.1 登録対象顧客アップロードCSV()
            WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv();

            //4.4.2 validate同時アップロード(long)[validate同時アップロード()に指定する引数]アップロードＩＤ：null
            l_registTargetAccountUploadCsv.validateSameTimeUpload(null);

            //4.4.3 get管理者コード()
            String l_strAdminCode = l_administrator.getAdministratorCode();
            log.debug("管理者コード:l_strAdminCode" + l_strAdminCode);

            //4.4.4 saveアップロード開始(long, String, String, String, String)
            l_registTargetAccountUploadCsv.saveUpLoadStart(0, null, null, null, l_strAdminCode);

            //4.4.5 (*1) リクエストデータ.アップロードファ@イル[]の各要素毎のLOOP処理
            int l_intUploadFileCnt = l_request.uploadFile.length;
            log.debug("アップロードファ@イル[]の各要素毎のLOOP數:l_intUploadFileCnt = " + l_intUploadFileCnt);
            for (int i = 0; i < l_intUploadFileCnt; i ++)
            {
                int l_intCnt = 0;
                
                //4.4.5.1  add明細行(明細行文字列（=アップロードファ@イル[index]） : String)
                try
                {
                    l_intCnt = l_registTargetAccountUploadCsv.addRow(l_request.uploadFile[i]);
                    log.debug("add明細行(明細行文字列（=アップロードファ@イル[index]） : String)を執行します");
                }
                catch (WEB3SystemLayerException l_ex)
                {
                    //4.4.5.2 add明細行()で例外がスローされた場合
                    //4.4.5.2.1 saveアップロードエラー(ErrorInfo)
                    l_registTargetAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());

                    //4.4.5.2.2 throw(例外)
                    log.error("add明細行失敗する");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException (
                        WEB3ErrorCatalog.BUSINESS_ERROR_00856,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getErrorMessage(),
                        l_ex);
                }
                
                //空行の場合（add明細行()の戻り値 < 0）、当該要素の処理を中断（continue;）
                if (l_intCnt < 0)
                {
                    continue;
                }
                
                try
                {
                    //4.4.5.3 validate明細行(long)
                    l_registTargetAccountUploadCsv.validateDetailsLine(l_intCnt);
                    log.debug("validate明細行(long)を執行します");
                }
                catch (WEB3BaseException l_ex)
                {
                    //4.4.5.5 (*1.2) validate明細行()，validate重複顧客()にて例外がスローされた場合
                    //4.4.5.5.1 saveアップロードエラー(ErrorInfo)
                    l_registTargetAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());

                    //4.4.5.5.2 throw（例外）
                    log.error("validate明細行 or validate重複顧客失敗する");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException (
                        WEB3ErrorCatalog.BUSINESS_ERROR_00856,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_registTargetAccountUploadCsv.getBranchCode(l_intCnt) + ","
                            + l_registTargetAccountUploadCsv.getAccountCode(l_intCnt));
                }

            }

            //4.4.6 get明細行数()
            l_intRowCnt = l_registTargetAccountUploadCsv.getRowCount();
            log.debug("明細行数:l_intRowCnt = " + l_intRowCnt);

            //4.4.7 getアップロードＩＤ()
            l_lngAdminUploadId = l_registTargetAccountUploadCsv.getAdministratorUploadId();
            log.debug("アップロードＩＤ:l_lngAdminUploadId = " + l_lngAdminUploadId);

            //4.4.8 saveアップロードTemp()
            l_registTargetAccountUploadCsv.saveUploadTemp();
        }
        //5 (*2)分岐フロー
        else
        {

            //5.1 get部店(String, String)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_accMgr;

            try
            {
                //証券会社コードを取得
                String l_strInstitutionCode = l_administrator.getInstitutionCode();
                log.debug("証券会社コード:l_strInstitutionCode =" + l_strInstitutionCode);
                WEB3GentradeBranch l_branch = l_accountManager.getWeb3GenBranch(l_strInstitutionCode, l_request.branchCode);
                log.debug("get部店(String, String)を執行します");

                //5.2 get顧客(String, String, String)
                String l_strInstCode = l_branch.getInstitution().getInstitutionCode();
                String l_strBranchCode = l_branch.getBranchCode();
                l_accountManager.getMainAccount(l_strInstCode, l_strBranchCode, l_request.accountCode);
                log.debug("get顧客(String, String, String)を執行します");
            }
            catch (NotFoundException l_ex)
            {
                log.error("部店 or 顧客取得を失敗する");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //6 creat Response
        WEB3AdminPvInfoDirectChangeConfirmResponse l_response = (WEB3AdminPvInfoDirectChangeConfirmResponse)l_request.createResponse();

        //7 プロパティセット
        if (l_request.uploadFile == null)
        {
            l_response.uploadNumber = null;
            l_response.uploadId = null;
        }
        else
        {
            l_response.uploadNumber = WEB3StringTypeUtility.formatNumber(l_intRowCnt);
            l_response.uploadId = WEB3StringTypeUtility.formatNumber(l_lngAdminUploadId);
        }

        log.debug("*************l_response.uploadNumber = " + l_response.uploadNumber);
        log.debug("*************l_response.uploadId = " + l_response.uploadId);
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitダイレクト指定変更)<BR>
     * ダイレクト指定変更完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者ダイレクト指定変更サービス)submitダイレクト指定変更」参照<BR>
     * ========================================================<BR>
     * get部店(証券会社コード:String, 部店コード:String)<BR>
     * <BR>
     * いづれかが例外をスローした場合は、<BR>「顧客未存在エラー」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * =========================================================<BR>
     * ========================================================<BR>
     * get顧客(証券会社コード:String, 部店コード:String)<BR>
     * <BR>
     * いづれかが例外をスローした場合は、<BR>「顧客未存在エラー」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * =========================================================<BR>
     * ========================================================<BR>
     * 重複チェック<BR>
     * <BR>
     * 以下①@と②を比較し、同じ値の場合例外をスロー<BR>
     * ①@(*1)の値<BR>
     * ②[get顧客コード()の戻り値]と[get部店コード()の戻り値]を結合した値<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00517<BR>
     * =========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定変更完了リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteResponse
     * @@roseuid 4161041602BB
     */
    protected WEB3AdminPvInfoDirectChangeCompleteResponse submitDirectChange(WEB3AdminPvInfoDirectChangeCompleteRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " submitDirectChange(WEB3AdminPvInfoDirectChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        int l_intRowCnt = 0;

        //1 validate()
        l_request.validate();

        //2 getInstanceFromログイン情報()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //3 validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);

        //4 validate取引パスワード(String)
        l_administrator.validateTradingPassword(l_request.password);

        try
        {
            //管理者ダイレクト指定変更TransactionCallback(表示内容情報 : 表示内容情報)
            //表示内容情報： リクエストデータ.表示内容情報
            WEB3AdminPvInfoDirectChangeTransactionCallback l_transactionCallback =
                new WEB3AdminPvInfoDirectChangeTransactionCallback(l_request.displayContentsUnit);

            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doTransaction(arg0 : int, arg1 : TransactionCallback)
            //[引数]
            //トランザクション属性：　@TX_CREATE_NEW
            //トランザクションコールバック：　@管理者ダイレクト指定変更TransactionCallbackインスタンス
            l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_CREATE_NEW, l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error( "DBへのアクセスに失敗しました。" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //7 分岐フロー
        String l_strInstCode = l_administrator.getInstitutionCode();
        long l_lngDisplayContentsId = Long.parseLong(l_request.displayContentsUnit.displayContentsId);
        if (l_request.uploadId != null)
        {
            //7.1 reset受付時間区分(String)
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD_DAYLONG);

            //7.2reset注文受付トランザクション(String)
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);

            //7.3 validate注文受付可能()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            //7.4 ( * CSV一括顧客変更処理)
            //7.4.1 登録対象顧客アップロードCSV(アップロードID : long)
            long l_lngUploadId = Long.parseLong(l_request.uploadId);
            WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv(l_lngUploadId);

            //7.4.2 validate同時アップロード(long)
            l_registTargetAccountUploadCsv.validateSameTimeUpload(new Long(l_lngUploadId));

            //7.4.3 setDataFromアップロードTemp(long)
            l_registTargetAccountUploadCsv.setDataFromUploadTemp(l_lngUploadId);

            //7.4.4 get明細行数()
            l_intRowCnt = l_registTargetAccountUploadCsv.getRowCount();
            log.debug("********明細行数 = " + l_intRowCnt);

            //7.4.5  delete閲覧履歴(long)            
            l_dataManager.deleteBrowseHistory(l_lngDisplayContentsId);
            log.debug("delete閲覧履歴(long)を執行します");

            //(*1)インスタンスを生成
            String l_strAccountAndBranchCode = null;

            //7.4.6 明細行の数分LOOP処理
            for (int i = 0; i < l_intRowCnt; i++)
            {
                //7.4.6.1 get部店コード(int)
                String l_strBranchCode = l_registTargetAccountUploadCsv.getBranchCode(i);
                log.debug("部店コード = " + l_strBranchCode);

                //7.4.6.2 get顧客コード(int)
                String l_strAccountCode = l_registTargetAccountUploadCsv.getAccountCode(i);
                log.debug("顧客コード = " + l_strAccountCode);

                try
                {
                    //(*1)がnull以外の場合
                    if (l_strAccountAndBranchCode != null)
                    {
                        //(*4)重複チェック
                        //以下①@と②を比較し、同じ値の場合例外をスロー
                        //①@(*1)の値
                        //②[get顧客コード()の戻り値]と[get部店コード()の戻り値]を結合した値
                        if (l_strAccountAndBranchCode.equals(l_strAccountCode + l_strBranchCode))
                        {
                            log.debug(l_strBranchCode + "," + l_strAccountCode
                                + "重複顧客チェックエラー（同じ値の行が存在する）。");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_strBranchCode + "," + l_strAccountCode);
                        }
                    }

                    //7.4.6.3 insert閲覧履歴(String, String, String, long, boolean)
                    l_dataManager.insertBrowseHistory(
                        l_strInstCode, l_strBranchCode, l_strAccountCode, l_lngDisplayContentsId, false);
                    log.debug("insert閲覧履歴(String, String, String, long, boolean)を執行します");
                }
                catch (WEB3BaseException l_ex)
                {
                    //(*4)重複チェック，insert閲覧履歴()にて例外がスローされた場合
                    //　@－アップロードエラーを更新する。
                    //　@－上位に例外をスローする。
                    //saveアップロードエラー(エラー情報 : ErrorInfo)
                    //アップロードエラーをアップロードテーブルに更新する
                    //[saveアップロードエラー()に指定する引数]
                    //エラー情報：　@（catchした例外）.getErrorInfo()
                    l_registTargetAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());
                    log.debug(l_ex.getMessage());
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException(
                        l_ex.getErrorInfo(),
                        getClass().getName() + STR_METHOD_NAME,
                        l_strBranchCode + "," + l_strAccountCode);
                }

                //(*1)に以下の値をセット
                //[get顧客コード()の戻り値]と[get部店コード()の戻り値]を結合した値をセット
                l_strAccountAndBranchCode = l_strAccountCode + l_strBranchCode;
            }

            //7.4.7saveアップロード終了()
            l_registTargetAccountUploadCsv.saveUploadEnd();
            log.debug("saveアップロード終了()を執行します");

            //7.4.8 deleteアップロードTemp()
            l_registTargetAccountUploadCsv.deleteUploadTemp();
            log.debug("deleteアップロードTemp()を執行します");
        }
        else
        {
            //8 分岐フロー
            try
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                AccountManager l_accMgr = l_finApp.getAccountManager();
                WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_accMgr;
                //8.1 get部店(String, String)
                WEB3GentradeBranch l_branch = l_accountManager.getWeb3GenBranch(l_strInstCode, l_request.branchCode);
                log.debug("get部店(String, String)を執行します");

                //8.2 get顧客(String, String, String)
                String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
                String l_strBranchCode = l_branch.getBranchCode();
                l_accountManager.getMainAccount(l_strInstitutionCode, l_strBranchCode, l_request.accountCode);
                log.debug("get顧客(String, String, String)を執行します");
            }
            catch (NotFoundException l_ex)
            {
                log.error("部店 or 顧客取得を失敗する");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //8.3 delete閲覧履歴(long)
            l_dataManager.deleteBrowseHistory(l_lngDisplayContentsId);
            log.debug("delete閲覧履歴(long)を執行します");

            //8.4 insert閲覧履歴(String, String, String, long, boolean)
            l_dataManager.insertBrowseHistory(l_strInstCode, l_request.branchCode, l_request.accountCode, Long.parseLong(l_request.displayContentsUnit.displayContentsId), false);
            log.debug("insert閲覧履歴(String, String, String, long, boolean)を執行します");

        }
        //9 createResponse()
        WEB3AdminPvInfoDirectChangeCompleteResponse l_response = (WEB3AdminPvInfoDirectChangeCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undoダイレクト指定変更アップロード)<BR>
     * ダイレクト指定変更アップロード中止処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者ダイレクト指定変更サービス)undoダイレクト指定変更アップロード」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定変更アップロード中止リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelResponse
     * @@roseuid 4161041602CB
     */
    protected WEB3AdminPvInfoDirectChangeCancelResponse undoDirectChangeUpload(WEB3AdminPvInfoDirectChangeCancelRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " undoDirectChangeUpload(WEB3AdminPvInfoDirectChangeCancelRequest)";
        log.entering(STR_METHOD_NAME );

        //1 validate()
        l_request.validate();

        //2 登録対象顧客アップロードCSV(アップロードID : long)
        long l_lngUploadId = Long.parseLong(l_request.uploadId);
        WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv(l_lngUploadId);

        //3 deleteアップロードTemp()
        l_registTargetAccountUploadCsv.deleteUploadTemp();
        log.debug("deleteアップロードTemp()を執行します");

        //4 saveアップロード中止()
        l_registTargetAccountUploadCsv.saveUploadStop();
        log.debug("saveアップロード中止()を執行します");

        //5  createResponse()
        WEB3AdminPvInfoDirectChangeCancelResponse l_response = (WEB3AdminPvInfoDirectChangeCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (管理者ダイレクト指定変更TransactionCallback)<BR>
     * 管理者ダイレクト指定変更TransactionCallbackクラス<BR>
     */
    public class WEB3AdminPvInfoDirectChangeTransactionCallback implements TransactionCallback
    {
        /**
         * ログユーティリティ
         */
        private  WEB3LogUtility l_log =
            WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectChangeTransactionCallback.class);

        /**
         * (表示内容情報)<BR>
         * 表示内容情報<BR>
         */
        private WEB3PvInfoDisplayContentsUnit displayContentsUnit;

        /**
         * (管理者ダイレクト指定変更TransactionCallback)<BR>
         * コンストラクタ<BR>
         * <BR>
         * 引数.表示内容情報を該当の変数に保存する。<BR>
         * @@param l_displayContentsUnit - (表示内容情報)<BR>
         * 表示内容情報<BR>
         */
        public WEB3AdminPvInfoDirectChangeTransactionCallback(
            WEB3PvInfoDisplayContentsUnit l_displayContentsUnit)
        {
            final String STR_METHOD_NAME = "WEB3AdminPvInfoDirectRegistTransactionCallback("
                + "WEB3PvInfoDisplayContentsUnit)";
            log.entering(STR_METHOD_NAME);

            //引数.表示内容情報を該当の変数に保存する。
            this.displayContentsUnit = l_displayContentsUnit;

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * 表示内容更新処理を行う。<BR>
         * <BR>
         * １）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.create表示内容Params()をコールする。<BR>
         * <BR>
         * [引数]<BR>
         * 表示内容情報： this.表示内容情報<BR>
         * <BR>
         * ２）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.update表示内容()をコールする。<BR>
         * <BR>
         * [引数]<BR>
         * 表示内容Params： create表示内容Params()の戻り値<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         */
        public Object process()
            throws
            DataNetworkException,
            DataQueryException,
            DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            try
            {
                //１）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.create表示内容Params()をコールする。
                //表示内容情報： this.表示内容情報
                WEB3PvInfoDataManager l_dataManager =
                    (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

                DisplayContentsParams l_dispContentsParam =
                    l_dataManager.createDisplayContentsParams(this.displayContentsUnit);

                //２）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.update表示内容()をコールする。
                //表示内容Params： create表示内容Params()の戻り値
                l_dataManager.updateDisplayContents(l_dispContentsParam);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
