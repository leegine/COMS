head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyULServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設申込ULサービスImpl(WEB3AdminAccOpenApplyULServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 武波 (中訊) 新規作成 モデル No.148
Revision History : 2007/11/21 武波 (中訊) DB更新仕様 No.032,No.033
Revision History : 2007/11/21 謝旋 (中訊) 仕様変更 モデル No.150,No.151,No.152,No.154
Revision History : 2007/12/11 謝旋 (中訊) 仕様変更 モデル No.155
Revision History : 2007/12/17 武波 (中訊) 仕様変更 モデル No.157
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AdminAccOpenApplyULCsv;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.define.WEB3AccOpenAccountCodeAutoFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenUploadStateDivDef;
import webbroker3.accountopen.message.WEB3AccOpenUploadHistoryUnit;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyULService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3ValidateTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者口座開設申込ULサービスImpl)<BR>
 * 管理者口座開設申込ULサービス実装クラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyULServiceImpl implements WEB3AdminAccOpenApplyULService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyULServiceImpl.class);

    /**
     * @@roseuid 4743EF52019E
     */
    public WEB3AdminAccOpenApplyULServiceImpl()
    {

    }

    /**
     * 口座開設申込アップロード処理を行う。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設申込UL入力リクエストの場合<BR>
     * 　@－getアップロード画面()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設申込UL確認リクエストの場合<BR>
     * 　@－validateアップロードファ@イル()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設申込UL完了リクエストの場合<BR>
     * 　@－submitアップロードファ@イル()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設申込UL中止リクエストの場合<BR>
     * 　@－undoアップロードファ@イル()をコールする。<BR>
     * <BR>
     * ※サービスメソッドにて例外が発生した場合は、<BR>
     * 　@例外オブジェクトの追加文字列（WEB3BaseException.errorMessage）<BR>
     * をレスポンスデータ.errorMessageにセットする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299B140055
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

        if (l_request instanceof WEB3AdminAccOpenApplyUploadInputRequest)
        {
            //○ 引数のリクエストデータが、管理者口座開設申込UL入力リクエストの場合
            //　@－getアップロード画面()をコールする。
            l_response = this.getUploadScreen((WEB3AdminAccOpenApplyUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyUploadConfirmRequest)
        {
            //○ 引数のリクエストデータが、管理者口座開設申込UL確認リクエストの場合
            //　@－validateアップロードファ@イル()をコールする。
            l_response = this.validateUploadFile((WEB3AdminAccOpenApplyUploadConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyUploadCompleteRequest)
        {
            //○ 引数のリクエストデータが、管理者口座開設申込UL完了リクエストの場合
            //　@－submitアップロードファ@イル()をコールする。
            l_response = this.submitUploadFile((WEB3AdminAccOpenApplyUploadCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyUploadCancelRequest)
        {
            //○ 引数のリクエストデータが、管理者口座開設申込UL中止リクエストの場合
            //　@－undoアップロードファ@イル()をコールする。
            l_response = this.undoUploadFile((WEB3AdminAccOpenApplyUploadCancelRequest)l_request);
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
     * 口座開設申込アップロード入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「口座開設（申込UL）getアップロード画面」参照。<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * 管理者口座開設申込UL入力リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenApplyUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299B4D008F
     */
    protected WEB3AdminAccOpenApplyUploadInputResponse getUploadScreen(
        WEB3AdminAccOpenApplyUploadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUploadScreen(WEB3AdminAccOpenApplyUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(String, boolean)
        //機@能カテゴリコード：　@機@能カテゴリコード.口座開設申込アップロード（A0403）
        //is更新：　@true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_UPLOAD, true);

        //validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //口座開設申込ULCSV()
        WEB3AdminAccOpenApplyULCsv l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        //getアップロード最新履歴(long)
        //[getアップロード最新履歴()の引数]
        //データキー：0
        AdministratorUploadRow l_administratorUploadRow =
            (AdministratorUploadRow)l_adminAccOpenApplyULCsv.getUploadNewHistory(0);

        //アップロード履歴が存在する場合、処理実施
        WEB3AccOpenUploadHistoryUnit l_accOpenUploadHistoryUnit = null;
        if (l_administratorUploadRow != null)
        {
            //口座開設アップロード履歴明細( )
            l_accOpenUploadHistoryUnit = new WEB3AccOpenUploadHistoryUnit();

            //(*1.1)プロパティセット
            //※ アップロード行   ：　@getアップロード最新履歴（データキー：long）の戻り値
            //アップロード処理状態区分    ：
            if (l_administratorUploadRow.getUploadEndTimestamp() == null)
            {
                //（アップロード行.アップロード終了日時 == null）の場合、”アップロード中”
                l_accOpenUploadHistoryUnit.uploadStateDiv = WEB3AccOpenUploadStateDivDef.UPLOADING;
            }
            else
            {
                //（アップロード行.アップロード終了日時 != null）の場合、”アップロード済”
                l_accOpenUploadHistoryUnit.uploadStateDiv = WEB3AccOpenUploadStateDivDef.UPLOAD_COMPLETE;
            }

            //アップロード件数        ：　@アップロード行.アップロード件数
            l_accOpenUploadHistoryUnit.uploadNumber = l_administratorUploadRow.getUploadCount() + "";
            //アップロード開始日時  ：　@アップロード行.アップロード開始日時
            l_accOpenUploadHistoryUnit.uploadStartDate = l_administratorUploadRow.getUploadStartTimestamp();
            //アップロード終了日時  ：　@アップロード行.アップロード終了日時
            l_accOpenUploadHistoryUnit.uploadEndDate = l_administratorUploadRow.getUploadEndTimestamp();
            //口座開設エラー番号   ：　@アップロード行.メッセージコード
            l_accOpenUploadHistoryUnit.accOpenErrorId = l_administratorUploadRow.getMessageCode();
        }

        // createResponse( )
        WEB3AdminAccOpenApplyUploadInputResponse l_response =
            (WEB3AdminAccOpenApplyUploadInputResponse)l_request.createResponse();

        //(*2)プロパティセット
        //アップロード履歴一覧：
        //アップロード履歴が存在する場合、口座開設アップロード履歴明細オブジェクト。
        //アップロード履歴が存在しない場合、null。
        if (l_accOpenUploadHistoryUnit != null)
        {
            l_response.uploadHistoryList = l_accOpenUploadHistoryUnit;
        }
        else
        {
            l_response.uploadHistoryList = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validateアップロードファ@イル)<BR>
     * 口座開設申込アップロード確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「口座開設（申込UL）validateアップロードファ@イル」参照。<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * 管理者口座開設申込UL確認リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenApplyUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299C3C0211
     */
    protected WEB3AdminAccOpenApplyUploadConfirmResponse validateUploadFile(
        WEB3AdminAccOpenApplyUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateUploadFile(WEB3AdminAccOpenApplyUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        //機@能カテゴリコード：　@機@能カテゴリコード.口座開設申込アップロード（A0403）
        //is更新：　@true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_UPLOAD, true);

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //口座開設申込ULCSV
        WEB3AdminAccOpenApplyULCsv l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();
        //validate同時アップロード(アップロードＩＤ : Long)
        //[validate同時アップロード()に指定する引数]
        //アップロードＩＤ：　@null
        l_adminAccOpenApplyULCsv.validateSameTimeUpload(null);

        //get管理者コード( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        //get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //saveアップロード開始(データキー : long, 備考１ : String,
        //      備考２ : String, 備考３ : String, 更新者コード : String)
        //[saveアップロード開始()に指定する引数]
        //データキー：　@0
        //備考１：　@null
        //備考２：　@null
        //備考３：　@null
        //更新者コード：　@管理者.get管理者コード()
        l_adminAccOpenApplyULCsv.saveUpLoadStart(0, null, null, null, l_strAdministratorCode);

        // (*1) リクエストデータ.アップロードファ@イル[]の各要素毎のLOOP処理
        String[] l_strUploadFiles = l_request.uploadFile;

        for (int i = 0; i < l_strUploadFiles.length; i++)
        {
            int l_intLineNumber = -1;
            try
            {
                //add明細行(明細行文字列 : String)
                l_intLineNumber = l_adminAccOpenApplyULCsv.addRow(l_strUploadFiles[i]);
            }
            catch (WEB3BaseException l_ex)
            {
                //saveアップロードエラー(エラー情報 : ErrorInfo)
                //[saveアップロードエラー()に指定する引数]
                //エラー情報：　@（catchした例外）.getErrorInfo()
                l_adminAccOpenApplyULCsv.saveUploadError(l_ex.getErrorInfo());

                log.debug(l_ex.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage());
            }

            //空行の場合（add明細行()の戻り値 < 0）、当該要素の処理を中断（continue;）
            if (l_intLineNumber < 0)
            {
                continue;
            }

            try
            {
                //validateレコード番号(int)
                //[validateレコード番号()に指定する引数]
                //行番号：　@add明細行()の戻り値
                l_adminAccOpenApplyULCsv.validateRecordNumber(l_intLineNumber);
            }
            catch (WEB3BaseException l_ex)
            {
                //saveアップロードエラー(エラー情報 : ErrorInfo)
                //[saveアップロードエラー()に指定する引数]
                //エラー情報：　@（catchした例外）.getErrorInfo()
                l_adminAccOpenApplyULCsv.saveUploadError(l_ex.getErrorInfo());

                log.debug(l_intLineNumber + "　@" + l_ex.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_intLineNumber + "　@" + l_ex.getErrorMessage());
            }

            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = null;
            try
            {
                //validate識別コード(int)
                //行番号：　@add明細行()の戻り値
                l_adminAccOpenApplyULCsv.validateRequestNumber(l_intLineNumber);

                //validate口座区分(int)
                //行番号：　@add明細行()の戻り値
                l_adminAccOpenApplyULCsv.validateAccountType(l_intLineNumber);

                //validate資料請求日時(int)
                //行番号：　@add明細行()の戻り値
                l_adminAccOpenApplyULCsv.validateInfomationClaimDatetime(l_intLineNumber);

                //to口座開設見込客(int, String)
                //[to口座開設見込客()に指定する引数]
                //行番号：　@add明細行()の戻り値
                //証券会社コード：　@get証券会社コード()の戻り値
                l_accOpenExpAccountOpen =
                    l_adminAccOpenApplyULCsv.toExpAccountOpen(l_intLineNumber, l_strInstitutionCode);

                //validate口座開設申込情報(String, String)
                //[validate口座開設申込情報()に指定する引数]
                //チェックタイプ：チェックタイプ.口座開設アップロード
                //顧客コード自動採番チェック：0(自動採番を行わない)
                l_accOpenExpAccountOpen.validateAccountOpenRegistInfo(
                    WEB3ValidateTypeDef.ACCOUNT_OPEN_UPLOAD,
                    WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
            }
            catch (WEB3BaseException l_ex)
            {
                //saveアップロードエラー(エラー情報 : ErrorInfo)
                //[saveアップロードエラー()に指定する引数]
                //エラー情報：　@（catchした例外）.getErrorInfo()
                l_adminAccOpenApplyULCsv.saveUploadError(l_ex.getErrorInfo());

                String l_strRecordNumber = l_adminAccOpenApplyULCsv.getRecordNumber(l_intLineNumber);
                log.debug(l_strRecordNumber + "　@" + l_ex.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strRecordNumber + "　@" + l_ex.getErrorMessage());
            }

            try
            {
                //add明細行()の戻り値 > 0 の場合、以下の処理を行う
                if (l_intLineNumber > 0)
                {
                    // validate重複顧客(int)
                    //[validate重複顧客()に指定する引数]
                    //行番号：　@add明細行()の戻り値
                    l_adminAccOpenApplyULCsv.validateDuplicateAccount(l_intLineNumber);
                }

                //validate口座開設見込客登録(int, String)
                //[validate口座開設見込客登録()に指定する引数]
                //行番号：　@add明細行()の戻り値
                //証券会社コード：　@get証券会社コード()の戻り値
                l_adminAccOpenApplyULCsv.validateExpAccountOpenLogin(l_intLineNumber, l_strInstitutionCode);
            }
            catch (WEB3BaseException l_ex)
            {
                //saveアップロードエラー(エラー情報 : ErrorInfo)
                //[saveアップロードエラー()に指定する引数]
                //エラー情報：　@（catchした例外）.getErrorInfo()
                l_adminAccOpenApplyULCsv.saveUploadError(l_ex.getErrorInfo());

                log.debug(l_ex.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage());
            }
        }

        //get明細行数( )
        int l_intRowCount = l_adminAccOpenApplyULCsv.getRowCount();

        //getアップロードＩＤ( )
        String l_strUploadFileId = String.valueOf(l_adminAccOpenApplyULCsv.getAdministratorUploadId());

        //saveアップロードTemp( )
        l_adminAccOpenApplyULCsv.saveUploadTemp();

        //createResponse( )
        WEB3AdminAccOpenApplyUploadConfirmResponse l_response =
            (WEB3AdminAccOpenApplyUploadConfirmResponse)l_request.createResponse();

        //　@アップロード件数 ：　@get明細行数()の戻り値
        l_response.uploadNumber = String.valueOf(l_intRowCount);
        //　@アップロードID    ：　@getアップロードID()の戻り値
        l_response.uploadID = l_strUploadFileId;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitアップロードファ@イル)<BR>
     * 口座開設申込アップロード完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「口座開設（申込UL）submitアップロードファ@イル」参照。<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * 管理者口座開設申込UL完了リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenApplyUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299C3D01D2
     */
    protected WEB3AdminAccOpenApplyUploadCompleteResponse submitUploadFile(
        WEB3AdminAccOpenApplyUploadCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitUploadFile(WEB3AdminAccOpenApplyUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(String, boolean)
        //機@能カテゴリコード：　@機@能カテゴリコード.口座開設申込アップロード（A0403）
        //is更新：　@true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_UPLOAD, true);

        //validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate取引パスワード(パスワード : String)
        //パスワード：　@リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);

        //口座開設申込ULCSV()
        WEB3AdminAccOpenApplyULCsv l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        //validate同時アップロード(アップロードＩＤ : Long)
        //[validate同時アップロード()に指定する引数]
        //アップロードＩＤ：　@リクエストデータ.アップロードＩＤ
        l_adminAccOpenApplyULCsv.validateSameTimeUpload(new Long(l_request.uploadID));

        //get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //setDataFromアップロードTemp(アップロードＩＤ : long)
        //[setDataFromアップロードTemp()に指定する引数]
        //アップロードＩＤ：　@リクエストデータ.アップロードＩＤ
        l_adminAccOpenApplyULCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadID));

        //get明細行数( )
        int l_intRowCount = l_adminAccOpenApplyULCsv.getRowCount();

        //明細行の数分LOOP処理
        for (int i = 0; i < l_intRowCount; i++)
        {
            //to口座開設見込客(int, String)
            //[to口座開設見込客()に指定する引数]
            //行番号：　@index
            //証券会社コード：　@get証券会社コード()の戻り値
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                l_adminAccOpenApplyULCsv.toExpAccountOpen(i, l_strInstitutionCode);

            try
            {
                QueryProcessor l_queryProcessor =
                    Processors.getDefaultProcessor();

                ExpAccountOpenParams l_expAccountOpenParams =
                    (ExpAccountOpenParams)l_accOpenExpAccountOpen.getDataSourceObject();

                //口座開設見込客Params.証券会社コード
                l_expAccountOpenParams.setInstitutionCode(l_expAccountOpenParams.getInstitutionCode());

                AccountManager l_accountManager = GtlUtils.getAccountManager();
                Institution l_institution = l_accountManager.getInstitution(
                    l_expAccountOpenParams.getInstitutionCode());

                long l_lngBranchId = l_accountManager.getBranch(
                    l_institution, l_expAccountOpenParams.getBranchCode()).getBranchId();

                //証券会社ID:証券会社コードに該当する証券会社ID。
                l_expAccountOpenParams.setInstitutionId(l_institution.getInstitutionId());

                //部店ＩＤ:証券会社，部店コードに該当する部店ID。
                l_expAccountOpenParams.setBranchId(l_lngBranchId);

                //既存口座フラグ
                l_expAccountOpenParams.setExAccountFlag(BooleanEnum.FALSE);

                //現物株式フラグ
                l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);

                //信用取引フラグ
                l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);

                //債券フラグ
                l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);

                //転換社債フラグ
                l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);

                //投資信託（株式）フラグ
                l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);

                //投資信託（公社債）フラグ
                l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);

                //先物・オプションフラグ
                l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);

                //外国証券フラグ
                l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);

                //その他フラグ
                l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);

                //株式ミニ投資フラグ
                l_expAccountOpenParams.setInterestFlagMinistock(BooleanEnum.FALSE);

                //その他フラグ
                l_expAccountOpenParams.setInterestFlagEtc(BooleanEnum.FALSE);

                //検索用区分
                l_expAccountOpenParams.setIdConfirmFlag(BooleanEnum.FALSE);

                //更新者コード管理者.管理者コード
                l_expAccountOpenParams.setLastUpdater(l_admin.getAdministratorCode());

                //作成日時処理日時
                l_expAccountOpenParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //更新日時処理日時
                l_expAccountOpenParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                //作成者コード管理者.管理者コード
                l_expAccountOpenParams.setCreator(l_admin.getAdministratorCode());

                //doInsertQuery(arg0 : Row)
                l_queryProcessor.doInsertQuery(l_expAccountOpenParams);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //saveアップロード終了( )
        l_adminAccOpenApplyULCsv.saveUploadEnd();

        //deleteアップロードTemp( )
        l_adminAccOpenApplyULCsv.deleteUploadTemp();

        //createResponse( )
        WEB3AdminAccOpenApplyUploadCompleteResponse l_response =
            (WEB3AdminAccOpenApplyUploadCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undoアップロードファ@イル)<BR>
     * 口座開設申込アップロード中止処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「口座開設（申込UL）undoアップロードファ@イル」参照。<BR>
     * @@param l_request - (l_request)<BR>
     * 管理者口座開設申込UL中止リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenApplyUploadCancelResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299C3E01F2
     */
    protected WEB3AdminAccOpenApplyUploadCancelResponse undoUploadFile(
        WEB3AdminAccOpenApplyUploadCancelRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "undoUploadFile(WEB3AdminAccOpenApplyUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //口座開設申込ULCSV(long)
        WEB3AdminAccOpenApplyULCsv l_adminAccOpenApplyULCsv =
            new WEB3AdminAccOpenApplyULCsv(Long.parseLong(l_request.uploadID));

        //deleteアップロードTemp( )
        l_adminAccOpenApplyULCsv.deleteUploadTemp();

        //updateアップロード中止( )
        l_adminAccOpenApplyULCsv.updateUploadCancel();

        //createResponse( )
        WEB3AdminAccOpenApplyUploadCancelResponse l_response =
            (WEB3AdminAccOpenApplyUploadCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
