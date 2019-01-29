head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧アップロードサービスImpl(WEB3AdminFPTUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 武波 (中訊) 新規作成 モデル No.012,No.014
Revision History : 2007/12/11 武波 (中訊) 仕様変更 モデル No.017,No.018
Revision History : 2007/12/21 謝旋 (中訊) 仕様変更 モデル No.020
Revision History : 2007/12/25 謝旋 (中訊) 仕様変更 モデル No.021
Revision History : 2008/01/28 武波 (中訊) 仕様変更 モデル No.027,No.033
Revision History : 2008/03/26 馮海濤 (中訊) 仕様変更  モデルNo.052
*/

package webbroker3.docadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CategCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTBatoProductCodeManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDeliveryManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDeliveryManagementHistory;
import webbroker3.docadmin.WEB3FPTUploadCsv;
import webbroker3.docadmin.define.WEB3FPTStatusDivDef;
import webbroker3.docadmin.define.WEB3FPTUploadStateDivDef;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputResponse;
import webbroker3.docadmin.message.WEB3FPTUploadHistoryInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTUploadService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.DocDeliveryManagementHistParams;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.define.WEB3GentradeDateAnalysisFlagDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付閲覧アップロードサービスImpl)<BR>
 * 管理者金商法@交付閲覧アップロードサービスImpl<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTUploadServiceImpl implements WEB3AdminFPTUploadService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadServiceImpl.class);

    /**
     * @@roseuid 4758B27801F4
     */
    public WEB3AdminFPTUploadServiceImpl()
    {

    }

    /**
     * 金商法@交付閲覧アップロード処理を行う。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付閲覧アップロード入力リクエストの場合<BR>
     * 　@－getアップロード画面()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付閲覧アップロード確認リクエストの場合<BR>
     * 　@－validateアップロードファ@イル()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付閲覧アップロード完了リクエストの場合<BR>
     * 　@－submitアップロードファ@イル()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付閲覧アップロード中止リクエストの場合<BR>
     * 　@－undoアップロード()をコールする。<BR>
     * <BR>
     * ※サービスメソッドにて例外が発生した場合は、<BR>
     * 　@例外オブジェクトの追加文字列（WEB3BaseException.errorMessage）<BR>
     * をレスポンスデータ.errorMessageにセットする。<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F20E02C7
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

        //１）　@リクエストデータの型により、以下の通りメソッドをコールする。
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminFPTUploadInputRequest)
        {
            // 引数のリクエストデータが、管理者金商法@交付閲覧アップロード入力リクエストの場合
            //－getアップロード画面()をコールする。
            l_response = this.getUploadScreen((WEB3AdminFPTUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTUploadConfirmRequest)
        {
            //引数のリクエストデータが、管理者金商法@交付閲覧アップロード確認リクエストの場合
            //－validateアップロードファ@イル()をコールする。
            l_response = this.validateUploadFile((WEB3AdminFPTUploadConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTUploadCompleteRequest)
        {
            //引数のリクエストデータが、管理者金商法@交付閲覧アップロード完了リクエストの場合
            //－submitアップロードファ@イル()をコールする。
            l_response = this.submitUploadFile((WEB3AdminFPTUploadCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTUploadCancelRequest)
        {
            //引数のリクエストデータが、管理者金商法@交付閲覧アップロード中止リクエストの場合
            //－undoアップロード()をコールする。
            l_response = this.undoUploadFile((WEB3AdminFPTUploadCancelRequest)l_request);
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
     * (getアップロード画面)<BR>
     * 金商法@交付閲覧アップロード入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「getアップロード画面」参照。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F5CD0351
     */
    protected WEB3AdminFPTUploadInputResponse getUploadScreen(
        WEB3AdminFPTUploadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUploadScreen(WEB3AdminFPTUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード：G0103(金商法@交付閲覧アップロード)
        //is更新：true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY, true);

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //金商法@交付閲覧アップロードCSV( )
        WEB3FPTUploadCsv l_fPTUploadCsv = new WEB3FPTUploadCsv();

        //get最新アップロード履歴(データキー : long)
        //データキー：　@0
        AdministratorUploadRow l_administratorUploadRow = l_fPTUploadCsv.getLatestUploadAction(0);

        //金商法@交付閲覧アップロード履歴明細
        WEB3FPTUploadHistoryInfoUnit l_fPTUploadHistoryInfoUnit = null;

        //(*1)アップロード履歴が存在する場合、処理実施
        if (l_administratorUploadRow != null)
        {
            l_fPTUploadHistoryInfoUnit = new WEB3FPTUploadHistoryInfoUnit();

            //アップロード処理状態区分
            if (l_administratorUploadRow.getUploadEndTimestamp() == null)
            {
                l_fPTUploadHistoryInfoUnit.uploadStateDiv = WEB3FPTUploadStateDivDef.UPLOADING;
            }
            else
            {
                l_fPTUploadHistoryInfoUnit.uploadStateDiv = WEB3FPTUploadStateDivDef.UPLOAD_COMPLETE;
            }

            //アップロード件数
            l_fPTUploadHistoryInfoUnit.uploadNumber = String.valueOf(l_administratorUploadRow.getUploadCount());

            //アップロード開始日時
            l_fPTUploadHistoryInfoUnit.uploadStartDate = l_administratorUploadRow.getUploadStartTimestamp();

            //アップロード終了日時
            l_fPTUploadHistoryInfoUnit.uploadEndDate = l_administratorUploadRow.getUploadEndTimestamp();

            //金商法@交付閲覧エラー番号
            l_fPTUploadHistoryInfoUnit.fptErrorId = l_administratorUploadRow.getMessageCode();

            //処理区分
            l_fPTUploadHistoryInfoUnit.statusDiv = l_administratorUploadRow.getNote2();
        }

        //createResponse
        WEB3AdminFPTUploadInputResponse l_response =
            (WEB3AdminFPTUploadInputResponse)l_request.createResponse();

        //(*2)プロパティセット
        l_response.uploadHistoryUnit = l_fPTUploadHistoryInfoUnit;

        return l_response;
    }

    /**
     * (validateアップロードファ@イル)<BR>
     * 金商法@交付閲覧アップロード確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validateアップロードファ@イル」参照。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F62901D6
     */
    protected WEB3AdminFPTUploadConfirmResponse validateUploadFile(
        WEB3AdminFPTUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateUploadFile(WEB3AdminFPTUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード：G0103(金商法@交付閲覧アップロード)
        //is更新：true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY, true);

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //金商法@交付閲覧アップロードCSV( )
        WEB3FPTUploadCsv l_fPTUploadCsv = new WEB3FPTUploadCsv();

        //validate同時アップロード(アップロードＩＤ : Long)
        //アップロードＩＤ：　@null
        l_fPTUploadCsv.validateSameTimeUpload(null);

        try
        {
            //check明細行数
            //l_request： リクエストデータ
            l_fPTUploadCsv.checkDetailLines(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("check明細行数()にて例外がスローされた場合", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getErrorMessage());
        }

        //get管理者コード( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //saveアップロード開始
        //データキー：　@0
        //備考１：　@null
        //備考２：　@リクエストデータ.処理区分
        //備考３：　@null
        //更新者コード：　@管理者.get管理者コード()
        l_fPTUploadCsv.saveUpLoadStart(
            0,
            null,
            l_request.statusDiv,
            null,
            l_strAdministratorCode);

        //リクエストデータ.アップロードファ@イル[]の各要素毎のLOOP処理
        for (int i = 0; i < l_request.uploadFile.length; i++)
        {
            int l_intLineNumber = -1;
            try
            {
                //add明細行(明細行文字列 : String, 日付解析フラグ : String)
                //明細行文字列：　@リクエストデータ.アップロードファ@イル[index]
                //日付解析フラグ： "1"（厳密な解析）
                l_intLineNumber = l_fPTUploadCsv.addRow(
                    l_request.uploadFile[i],
                    WEB3GentradeDateAnalysisFlagDivDef.STRICTLY_ANALYSIS);
            }
            catch (WEB3BaseException l_ex)
            {
                //saveアップロードエラー(エラー情報 : ErrorInfo)
                //エラー情報：　@（catchした例外）.getErrorInfo()
                l_fPTUploadCsv.saveUploadError(l_ex.getErrorInfo());

                log.error("add明細行()にて例外がスローされた場合", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    (i + 1) + "," + l_request.uploadFile[i]);
            }

            //(*) 空行の場合（add明細行()の戻り値 < 0）、当該要素の処理を中断
            if (l_intLineNumber < 0)
            {
                continue;
            }

            try
            {
                //validate明細行(int, 管理者)
                //行番号：　@（add明細行()の戻り値）
                //管理者：　@getInstanceFromログイン情報()の戻り値
                l_fPTUploadCsv.validateDetailLine(l_intLineNumber, l_administrator);

                //validate重複顧客(int, String, String)
                //行番号：　@（add明細行()の戻り値）
                //証券会社コード： 管理者.get証券会社コード()の戻り値
                //処理区分： リクエストデータ.処理区分
                l_fPTUploadCsv.validateDuplicateAccount(
                    l_intLineNumber,
                    l_strInstitutionCode,
                    l_request.statusDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                //saveアップロードエラー(エラー情報 : ErrorInfo)
                //エラー情報：　@（catchした例外）.getErrorInfo()
                l_fPTUploadCsv.saveUploadError(l_ex.getErrorInfo());

                String l_strBranchCode = "";
                String l_strAccountCode = "";
                String l_strBatoProductCode = "";
                String l_strDeliveryDate = "";

                if (l_fPTUploadCsv.getBranchCode(l_intLineNumber) != null)
                {
                    l_strBranchCode = l_fPTUploadCsv.getBranchCode(l_intLineNumber);
                }
                if (l_fPTUploadCsv.getAccountCode(l_intLineNumber) != null)
                {
                    l_strAccountCode = l_fPTUploadCsv.getAccountCode(l_intLineNumber);
                }
                if (l_fPTUploadCsv.getBatoProductCode(l_intLineNumber) != null)
                {
                    l_strBatoProductCode = l_fPTUploadCsv.getBatoProductCode(l_intLineNumber);
                }
                if (l_fPTUploadCsv.getDeliveryDate(l_intLineNumber) != null)
                {
                    l_strDeliveryDate = l_fPTUploadCsv.getDeliveryDate(l_intLineNumber);
                }

                log.error("validate明細行()とvalidate重複顧客()にて例外がスローされた場合", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    (l_intLineNumber + 1) + "," + l_strBranchCode + ","
                    + l_strAccountCode + ","
                    + l_strBatoProductCode + ","
                    + l_strDeliveryDate);
            }
        }

        //getアップロードＩＤ( )
        String l_strUploadID = String.valueOf(l_fPTUploadCsv.getAdministratorUploadId());

        //saveアップロードTemp( )
        l_fPTUploadCsv.saveUploadTemp();

        //get明細行数( )
        String l_strRowCount = String.valueOf(l_fPTUploadCsv.getRowCount());

        //createResponse( )
        WEB3AdminFPTUploadConfirmResponse l_response =
            (WEB3AdminFPTUploadConfirmResponse)l_request.createResponse();

        //　@アップロード件数 ：　@get明細行数()の戻り値
        l_response.uploadNumber = l_strRowCount;
        //　@アップロードID    ：　@getアップロードID()の戻り値
        l_response.uploadId = l_strUploadID;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitアップロードファ@イル)<BR>
     * 金商法@交付閲覧アップロード完了画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submitアップロードファ@イル」参照。<BR>
     * ======================================================== <BR>
     * 具体位置：既に登録済みであった場合（書面交付管理.get書面交付管理行() != null）、
     * 例外をスローする。
     * 　@　@class: WEB3BaseException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_02962 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：削除行が存在しない場合（書面交付管理.get書面区交付管理行() == null）、
     * 例外をスローする。
     * 　@　@class: WEB3BaseException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_02963 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：is電子鳩銘柄コード()の戻り値==falseの場合、
     * 例外をスローする。
     * 　@　@class: WEB3BaseException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_02995 <BR>
     * ======================================================== <BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F62A005E
     */
    protected WEB3AdminFPTUploadCompleteResponse submitUploadFile(
        WEB3AdminFPTUploadCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitUploadFile(WEB3AdminFPTUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード：G0103(金商法@交付閲覧アップロード)
        //is更新：true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY, true);

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate取引パスワード(パスワード : String)
        //パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //金商法@交付閲覧アップロードCSV( )
        WEB3FPTUploadCsv l_fPTUploadCsv = new WEB3FPTUploadCsv();

        Long l_uploadId = Long.valueOf(l_request.uploadId);
        //validate同時アップロード(アップロードＩＤ : Long)
        //アップロードＩＤ：　@リクエストデータ.アップロードＩＤ
        l_fPTUploadCsv.validateSameTimeUpload(l_uploadId);

        //setDataFromアップロードTemp(アップロードＩＤ : long)
        //アップロードＩＤ：　@リクエストデータ.アップロードＩＤ
        l_fPTUploadCsv.setDataFromUploadTemp(l_uploadId.longValue());

        //get明細行数( )
        int l_intRowCount = l_fPTUploadCsv.getRowCount();

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //(*1) 明細行の数分LOOP処理
        for (int i = 0; i < l_intRowCount; i++)
        {
            //get顧客(int, String)
            //行番号：　@index
            //証券会社コード：　@管理者.get証券会社コード()
            WEB3GentradeMainAccount l_gentradeMainAccount =
                l_fPTUploadCsv.getMainAccount(i, l_strInstitutionCode);

            //getAccountId( )
            long l_lngAccountId = l_gentradeMainAccount.getAccountId();

            //get部店コード(int)
            //行番号：　@index
            String l_strBranchCode = l_fPTUploadCsv.getBranchCode(i);

            //get顧客コード(int)
            //行番号：　@index
            l_fPTUploadCsv.getAccountCode(i);

            //get書面交付日(int)
            //行番号：　@index
            String l_strDeliveryDate = l_fPTUploadCsv.getDeliveryDate(i);

            //get電子鳩銘柄コード
            //行番号：　@index
            String l_strBatoProductCode = l_fPTUploadCsv.getBatoProductCode(i);

            //電子鳩銘柄コード管理
            //証券会社コード： 管理者.get証券会社コード()の戻り値
            //部店コード： 金商法@交付閲覧アップロードCSV.get部店コード()の戻り値
            //書面区分： "010"
            //電子鳩銘柄コード： 金商法@交付閲覧アップロードCSV.get電子鳩銘柄コード()の戻り値
            WEB3AdminFPTBatoProductCodeManagement l_batoProductCodeManagement =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator.getInstitutionCode(),
                    l_strBranchCode,
                    WEB3CategCodeDef.DOCUMENT_DELIVERY,
                    l_strBatoProductCode);

            //is電子鳩銘柄コード
            //is電子鳩銘柄コード()の戻り値==falseの場合、例外をスローする。
            //is電子鳩銘柄コード()の戻り値==falseの場合、、「電子鳩銘柄コードが不正です。」 （BUSINESS_ERROR_XXXXX）例外をスローする。
            if (!l_batoProductCodeManagement.isBatoProductCode())
            {
                log.debug("既に登録済みであった場合、登録アップロード処理不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02995,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    (i + 1) + "," + l_fPTUploadCsv.getBranchCode(i) + ","
                    + l_fPTUploadCsv.getAccountCode(i) + ","
                    + l_strBatoProductCode + ","
                    + l_fPTUploadCsv.getDeliveryDate(i));
            }

            //validate部店権限(部店コード : String)
            //部店コード： get部店コード()の戻り値
            l_administrator.validateBranchPermission(l_strBranchCode);

            //get書面交付管理行(long, String, String, Date,String)
            //口座ID： 顧客.getAccountId()の戻り値
            //書面区分： "010"
            //銘柄コード： get電子鳩銘柄コード()の戻り値
            //書面交付日： get書面交付日()の戻り値
            //書面種類コード： get電子鳩銘柄コード()の戻り値左３桁
            WEB3AdminFPTDocDeliveryManagement l_adminFPTDocDeliveryManagement =
                new WEB3AdminFPTDocDeliveryManagement();
            DocDeliveryManagementRow l_docDeliveryManagementRow =
                l_adminFPTDocDeliveryManagement.getDocDivManagementParams(
                    l_lngAccountId,
                    WEB3CategCodeDef.DOCUMENT_DELIVERY,
                    l_strBatoProductCode,
                    WEB3DateUtility.getDate(l_strDeliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                    l_strBatoProductCode.substring(0, 3));

            //登録アップロード処理（リクエストデータ.処理区分=="0"）の場合
            if (WEB3FPTStatusDivDef.LOGIN.equals(l_request.statusDiv))
            {
                //既に登録済みであった場合（書面交付管理.get書面交付管理行() != null）、例外をスローする。
                if (l_docDeliveryManagementRow != null)
                {
                    log.debug("既に登録済みであった場合、登録アップロード処理不可。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02962,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        (i + 1) + "," + l_fPTUploadCsv.getBranchCode(i) + ","
                        + l_fPTUploadCsv.getAccountCode(i) + ","
                        + l_strBatoProductCode + ","
                        + l_fPTUploadCsv.getDeliveryDate(i));
                }

                //(*1.2)プロパティセット
                //書面交付管理テーブルParamsを生成し、以下を設定する。
                DocDeliveryManagementParams l_docDeliveryManagementParams = new DocDeliveryManagementParams();
                //書面交付管理テーブルParams.口座ID = 顧客.getAccountId()の戻り値
                l_docDeliveryManagementParams.setAccountId(l_lngAccountId);
                //書面交付管理テーブルParams.証券会社コード = 管理者.get証券会社コード()の戻り値
                l_docDeliveryManagementParams.setInstitutionCode(l_strInstitutionCode);
                //書面交付管理テーブルParams.部店コード = 金商法@交付閲覧アップロードCSV.get部店コード()の戻り値
                l_docDeliveryManagementParams.setBranchCode(l_fPTUploadCsv.getBranchCode(i));
                //書面交付管理テーブルParams.顧客コード = 顧客.getAccountCode()の戻り値
                l_docDeliveryManagementParams.setAccountCode(l_gentradeMainAccount.getAccountCode());
                //書面交付管理テーブルParams.書面区分 = "010"
                l_docDeliveryManagementParams.setDocumentDiv(WEB3CategCodeDef.DOCUMENT_DELIVERY);
                //書面交付管理テーブルParams.銘柄コード = 金商法@交付閲覧アップロードCSV.get電子鳩銘柄コード()の戻り値
                l_docDeliveryManagementParams.setProductCode(l_strBatoProductCode);
                //書面交付管理テーブルParams.書面交付日 = 金商法@交付閲覧アップロードCSV.get書面交付日()の戻り値
                l_docDeliveryManagementParams.setDeliveryDate(
                    WEB3DateUtility.getDate(l_strDeliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
                //書面交付管理テーブルParams.削除フラグ = "0"
                l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);
                //書面交付管理テーブルParams.更新者コード = 管理者.get管理者コード()の戻り値
                l_docDeliveryManagementParams.setLastUpdater(l_administrator.getAdministratorCode());
                //書面交付管理テーブルParams.作成日付 = 現在日時
                l_docDeliveryManagementParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                //書面交付管理テーブルParams.更新日付 = 現在日時
                l_docDeliveryManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //書面交付管理テーブルParams.書面種類コード = 金商法@交付閲覧アップロードCSV.get電子鳩銘柄コード()の戻り値左３桁
                l_docDeliveryManagementParams.setDocumentCategory(l_strBatoProductCode.substring(0, 3));
                //書面交付管理テーブルParams.みなし交付日 = null
                l_docDeliveryManagementParams.setDeemedDeliveryDate(null);
                //insert書面交付管理テーブル(書面交付管理テーブルParams)
                //(*)プロパティセットで作成した書面交付管理テーブルParams
                l_adminFPTDocDeliveryManagement.insertDocDeliveryManagementParams(l_docDeliveryManagementParams);
            }

            //削除アップロード処理（リクエストデータ.処理区分=="1"）の場合
            if (WEB3FPTStatusDivDef.DELETE.equals(l_request.statusDiv))
            {
                //削除行が存在しない場合（書面交付管理.get書面交付管理行() == null）、例外をスローする。
                if (l_docDeliveryManagementRow == null)
                {
                    log.debug("削除行が存在しない場合、削除アップロード処理不可。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02963,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        (i + 1) + "," + l_fPTUploadCsv.getBranchCode(i) + ","
                        + l_fPTUploadCsv.getAccountCode(i) + ","
                        + l_strBatoProductCode + ","
                        + l_fPTUploadCsv.getDeliveryDate(i));
                }

                //(*1.3)プロパティセット
                //書面交付管理履歴Paramsを生成し、
                //get書面交付管理一覧()の戻り値（以下書面交付管理行）を設定する。
                DocDeliveryManagementHistParams l_docDeliveryManagementHistParams =
                    new DocDeliveryManagementHistParams();
                //書面交付管理履歴テーブルParams.口座ID = 書面交付管理行.get口座ID()
                l_docDeliveryManagementHistParams.setAccountId(l_docDeliveryManagementRow.getAccountId());
                //書面交付管理履歴テーブルParams.証券会社コード = 書面交付管理行.get証券会社コード()
                l_docDeliveryManagementHistParams.setInstitutionCode(l_docDeliveryManagementRow.getInstitutionCode());
                //書面交付管理履歴テーブルParams.部店コード = 書面交付管理行.get部店コード()
                l_docDeliveryManagementHistParams.setBranchCode(l_docDeliveryManagementRow.getBranchCode());
                //書面交付管理履歴テーブルParams.顧客コード = 書面交付管理行.get顧客コード()
                l_docDeliveryManagementHistParams.setAccountCode(l_docDeliveryManagementRow.getAccountCode());
                //書面交付管理履歴テーブルParams.書面区分 = 書面交付管理行.get書面区分()
                l_docDeliveryManagementHistParams.setDocumentDiv(l_docDeliveryManagementRow.getDocumentDiv());
                //書面交付管理履歴テーブルParams.銘柄コード = 書面交付管理行.get銘柄コード()
                l_docDeliveryManagementHistParams.setProductCode(l_docDeliveryManagementRow.getProductCode());
                //書面交付管理履歴テーブルParams.書面交付日 = 書面交付管理行.get書面交付日()
                l_docDeliveryManagementHistParams.setDeliveryDate(l_docDeliveryManagementRow.getDeliveryDate());
                //書面交付管理履歴テーブルParams.削除フラグ = 書面交付管理行.get削除フラグ()
                l_docDeliveryManagementHistParams.setDeleteFlag(l_docDeliveryManagementRow.getDeleteFlag());
                //書面交付管理履歴テーブルParams.更新者コード = 書面交付管理行.get更新者コード()
                l_docDeliveryManagementHistParams.setLastUpdater(l_docDeliveryManagementRow.getLastUpdater());
                //書面交付管理履歴テーブルParams.作成日付 = 書面交付管理行.get作成日付()
                l_docDeliveryManagementHistParams.setCreatedTimestamp(l_docDeliveryManagementRow.getCreatedTimestamp());
                //書面交付管理履歴テーブルParams.更新日付 = 書面交付管理行.get更新日付()
                l_docDeliveryManagementHistParams.setLastUpdatedTimestamp(
                    l_docDeliveryManagementRow.getLastUpdatedTimestamp());
                //書面交付管理履歴テーブルParams.削除者 = 管理者.get管理者コード()
                l_docDeliveryManagementHistParams.setDeleteUser(l_administrator.getAdministratorCode());
                //書面交付管理履歴テーブルParams.削除日時 = 現在日時
                l_docDeliveryManagementHistParams.setDeleteTimestamp(GtlUtils.getSystemTimestamp());
                //書面交付管理履歴テーブルParams.書面種類コード = 書面交付管理行.get書面種類コード()
                l_docDeliveryManagementHistParams.setDocumentCategory(
                    l_docDeliveryManagementRow.getDocumentCategory());
                //書面交付管理履歴テーブルParams.みなし交付日 = 書面交付管理行.getみなし交付日()
                l_docDeliveryManagementHistParams.setDeemedDeliveryDate(
                    l_docDeliveryManagementRow.getDeemedDeliveryDate());

                //書面交付管理履歴( )
                WEB3AdminFPTDocDeliveryManagementHistory l_adminFPTDocDeliveryManagementHistory =
                    new WEB3AdminFPTDocDeliveryManagementHistory();

                //insert書面交付管理履歴テーブル(書面交付管理履歴テーブルParams)
                l_adminFPTDocDeliveryManagementHistory.insertDocDeliveryManagementHist(
                    l_docDeliveryManagementHistParams);

                //delete書面交付管理(long, String, String, Date, String)
                //口座ID： get書面交付管理一覧()の戻り値.get口座ID()
                //書面区分：get書面交付管理一覧()の戻り値.get書面区分()
                //銘柄コード： get書面交付管理一覧()の戻り値.get銘柄コード()
                //書面交付日： get書面交付管理一覧()の戻り値.get書面交付日()
                //書面種類コード： get書面交付管理一覧()の戻り値.get書面種類コード()の戻り値
                l_adminFPTDocDeliveryManagement.deleteDocDivManagement(
                    l_docDeliveryManagementRow.getAccountId(),
                    l_docDeliveryManagementRow.getDocumentDiv(),
                    l_docDeliveryManagementRow.getProductCode(),
                    l_docDeliveryManagementRow.getDeliveryDate(),
                    l_docDeliveryManagementRow.getDocumentCategory());
            }
        }

        //saveアップロード終了( )
        l_fPTUploadCsv.saveUploadEnd();

        //deleteアップロードTemp( )
        l_fPTUploadCsv.deleteUploadTemp();

        //createResponse( )
        WEB3AdminFPTUploadCompleteResponse l_response =
            (WEB3AdminFPTUploadCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undoアップロードファ@イル)<BR>
     * 金商法@交付閲覧アップロード中止画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「undoアップロードファ@イル参照。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTUploadCancelResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F62A0233
     */
    protected WEB3AdminFPTUploadCancelResponse undoUploadFile(
        WEB3AdminFPTUploadCancelRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "undoUploadFile(WEB3AdminFPTUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //金商法@交付閲覧アップロードCSV(long)
        //アップロードＩＤ：　@リクエストデータ.アップロードＩＤ
        WEB3FPTUploadCsv l_fPTUploadCsv = new WEB3FPTUploadCsv(Long.parseLong(l_request.uploadId));

        //deleteアップロードTemp( )
        l_fPTUploadCsv.deleteUploadTemp();

        //updateアップロード中止( )
        l_fPTUploadCsv.updateUploadCancel();

        //createResponse( )
        WEB3AdminFPTUploadCancelResponse l_response =
            (WEB3AdminFPTUploadCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
