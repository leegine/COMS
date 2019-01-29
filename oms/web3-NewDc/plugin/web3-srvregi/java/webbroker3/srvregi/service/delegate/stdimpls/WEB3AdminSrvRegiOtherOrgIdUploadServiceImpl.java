head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽImpl(WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/10 武波 (中訊) 新規作成 モデル337
Revision History : 2008/03/19 武波 (中訊) 仕様変更 モデル357
Revision History : 2008/04/10 武波 (中訊) 仕様変更 モデル366
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.srvregi.WEB3AdminSrvRegiOtherOrgIdUploadCsv;
import webbroker3.srvregi.define.WEB3SrvRegiUploadStateDivDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputResponse;
import webbroker3.srvregi.message.WEB3SrvRegiUploadHistoryInfoUnit;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽImpl)<BR>
 * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ実装クラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl implements WEB3AdminSrvRegiOtherOrgIdUploadService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl.class);

    /**
     * @@roseuid 47D11130013A
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl()
    {

    }

    /**
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ処理を行う。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄの場合<BR>
     * 　@－getアップロード画面()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄの場合<BR>
     * 　@－validateアップロードファ@イル()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄの場合<BR>
     * 　@－submitアップロードファ@イル()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄの場合<BR>
     * 　@－undoアップロード()をコールする。<BR>
     * <BR>
     * ※サービスメソッドにて例外が発生した場合は、<BR>
     * 　@例外オブジェクトの追加文字列（WEB3BaseException.errorMessage）<BR>
     * 　@をレスポンスデータ.errorMessageにセットする。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B9417F0031
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

        if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadInputRequest)
        {
            // 引数のリクエストデータが、管理者金商法@交付閲覧アップロード入力リクエストの場合
            //－getアップロード画面()をコールする。
            l_response = this.getUploadScreen(
                (WEB3AdminSrvRegiOtherOrgIdUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest)
        {
            //引数のリクエストデータが、管理者金商法@交付閲覧アップロード確認リクエストの場合
            //－validateアップロードファ@イル()をコールする。
            l_response = this.validateUploadFile(
                (WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)
        {
            //引数のリクエストデータが、管理者金商法@交付閲覧アップロード完了リクエストの場合
            //－submitアップロードファ@イル()をコールする。
            l_response = this.submitUploadFile(
                (WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest)
        {
            //引数のリクエストデータが、管理者金商法@交付閲覧アップロード中止リクエストの場合
            //－undoアップロード()をコールする。
            l_response = this.undoUpload(
                (WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest)l_request);
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
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(サービス利用)外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・getアップロード画面」参照。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B941DD0332
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadInputResponse getUploadScreen(
        WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUploadScreen(WEB3AdminSrvRegiOtherOrgIdUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード：機@能カテゴリコード.サービス利用管理(外部連携)
        //is更新：true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_OTHERORG, true);

        //isDIR管理者( )
        boolean l_blnIsDirAdministrator = l_admin.isDirAdministrator();

        //(*1) isDIR管理者()=false の場合、受付時間チェックを実施
        if (!l_blnIsDirAdministrator)
        {
            //validate注文受付可能( )
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }

        //外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV(Long)
        //アップロードＩＤ：　@null
        WEB3AdminSrvRegiOtherOrgIdUploadCsv l_adminSrvRegiOtherOrgIdUploadCsv =
            new WEB3AdminSrvRegiOtherOrgIdUploadCsv(null);

        //get最新アップロード履歴(データキー : long)
        //データキー：0
        AdministratorUploadRow l_administratorUploadRow =
            l_adminSrvRegiOtherOrgIdUploadCsv.getLatestUploadAction(0);

        //(*2) アップロード履歴が存在する場合、処理実施
        WEB3SrvRegiUploadHistoryInfoUnit l_srvRegiUploadHistoryInfoUnit = null;
        if (l_administratorUploadRow != null)
        {
            //サービス利用アップロード履歴明細( )
            l_srvRegiUploadHistoryInfoUnit =
                new WEB3SrvRegiUploadHistoryInfoUnit();
            //(*2.1) プロパティセット
            //(*2.1)サービス利用アップロード履歴明細メッセージオブジェクトプロパティに値をセットする。
            //アップロード処理状態区分：
            //①@アップロード行.アップロード終了日時＝null の場合は、「アップロード中」
            if (l_administratorUploadRow.getUploadEndTimestamp() == null)
            {
                l_srvRegiUploadHistoryInfoUnit.uploadStateDiv =
                    WEB3SrvRegiUploadStateDivDef.UPLOADING;
            }
            //②アップロード行.アップロード終了日時≠null の場合は、「アップロード済」
            else
            {
                l_srvRegiUploadHistoryInfoUnit.uploadStateDiv =
                    WEB3SrvRegiUploadStateDivDef.UPLOAD_COMPLETE;
            }

            //アップロード件数：アップロード行.アップロード件数
            l_srvRegiUploadHistoryInfoUnit.uploadNumber =
                l_administratorUploadRow.getUploadCount() + "";

            //アップロード開始日時：アップロード行.アップロード開始日時
            l_srvRegiUploadHistoryInfoUnit.uploadStartDate =
                l_administratorUploadRow.getUploadStartTimestamp();

            //アップロード終了日時：アップロード行.アップロード終了日時
            l_srvRegiUploadHistoryInfoUnit.uploadEndDate =
                l_administratorUploadRow.getUploadEndTimestamp();

            //サービス利用エラー番号：アップロード行.メッセージコード
            l_srvRegiUploadHistoryInfoUnit.srvRegiErrorId =
                l_administratorUploadRow.getMessageCode();
        }

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ( )
        WEB3AdminSrvRegiOtherOrgIdUploadInputResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdUploadInputResponse)l_request.createResponse();

        //(*3)レスポンスデータプロパティに値をセットする。
        //アップロード履歴一覧：
        //アップロード履歴が存在する場合、サービス利用アップロード履歴明細オブジェクト
        if (l_srvRegiUploadHistoryInfoUnit != null)
        {
            l_response.uploadHistoryUnit = l_srvRegiUploadHistoryInfoUnit;
        }
        else
        {
            //アップロード履歴が存在しない場合、null
            l_response.uploadHistoryUnit = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validateアップロードファ@イル)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(サービス利用)外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・validateアップロードファ@イル」参照。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B941DD0341
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse validateUploadFile(
        WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateUploadFile(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード：機@能カテゴリコード.サービス利用管理(外部連携)
        //is更新：true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_OTHERORG, true);

        //isDIR管理者( )
        boolean l_blnIsDirAdministrator = l_admin.isDirAdministrator();

        //(*1) isDIR管理者()=false の場合、受付時間チェックを実施
        if (!l_blnIsDirAdministrator)
        {
            //validate注文受付可能( )
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }

        //外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV(Long)
        //アップロードＩＤ：　@null
        WEB3AdminSrvRegiOtherOrgIdUploadCsv l_adminSrvRegiOtherOrgIdUploadCsv =
            new WEB3AdminSrvRegiOtherOrgIdUploadCsv(null);

        //setアップロード区分(String, String)
        //アップロード区分：　@null
        //明細行文字列：　@リクエストデータ.アップロードファ@イル[0]
        l_adminSrvRegiOtherOrgIdUploadCsv.setUploadDiv(null, l_request.lines[0]);

        //createカラムヘッダ( )
        l_adminSrvRegiOtherOrgIdUploadCsv.createColumnHeader();

        //validate同時アップロード(アップロードＩＤ : Long)
        //アップロードID：null
        l_adminSrvRegiOtherOrgIdUploadCsv.validateSameTimeUpload(null);

        //get管理者コード( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();

        //saveアップロード開始(データキー : long, 備考１ : String, 備考２ : String, 備考３ : String, 更新者コード : String)
        //データキー：　@0
        //備考１：　@null
        //備考２：　@null
        //備考３：　@null
        //更新者コード：　@管理者.get管理者コード()
        l_adminSrvRegiOtherOrgIdUploadCsv.saveUpLoadStart(0, null, null, null, l_strAdministratorCode);

        //リクエストデータ.アップロードファ@イル[]の各要素毎のLOOP処理
        for (int i = 0; i < l_request.lines.length; i++)
        {
            int l_intLineNumber = -1;
            //add明細行(明細行文字列 : String)
            //明細行文字列：　@リクエストデータ.アップロードファ@イル[index]
            try
            {
                l_intLineNumber =
                    l_adminSrvRegiOtherOrgIdUploadCsv.addRow(l_request.lines[i]);
            }
            catch (WEB3BaseException l_ex)
            {
                //add明細行()で例外がスローされた場合
                //－アップロードエラーを更新する。
                //－上位に例外をスローする。
                //saveアップロードエラー(エラー情報 : ErrorInfo)
                //エラー情報：　@（catchした例外）.getErrorInfo()
                l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadError(l_ex.getErrorInfo());

                log.error("add明細行()にて例外がスローされた場合", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00992,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.lines[i]);
            }

            //(*) 空行の場合（add明細行()の戻り値 < 0）、当該要素の処理を中断
            if (l_intLineNumber < 0)
            {
                continue;
            }

            try
            {
                //validate明細行(int)
                l_adminSrvRegiOtherOrgIdUploadCsv.validateDetailLine(l_intLineNumber);

                //validateアップロードファ@イル内重複(int)
                l_adminSrvRegiOtherOrgIdUploadCsv.validateUploadFileInnerRepeat(l_intLineNumber);

                //isアップロード新規登録( )
                boolean l_blnIsUploadNewRegist = l_adminSrvRegiOtherOrgIdUploadCsv.isUploadNewRegist();

                //isアップロード新規登録( )=false の場合
                if (!l_blnIsUploadNewRegist)
                {
                    //get部店コード(int)
                    String l_strBranchCode =
                        l_adminSrvRegiOtherOrgIdUploadCsv.getBranchCode(l_intLineNumber);

                    //get部店コード()≠null の場合、部店権限チェックを実施
                    if (l_strBranchCode != null)
                    {
                        // validate部店権限(部店コード)
                        //部店コード：get部店コード()の戻り値
                        l_admin.validateBranchPermission(l_strBranchCode);
                    }
                }

                //validateレコード整合性(int, String)
                //行番号：　@add明細行()の戻り値
                //サービス区分：　@リクエストデータ.サービス区分
                l_adminSrvRegiOtherOrgIdUploadCsv.validateRecodeMatch(
                    l_intLineNumber, l_request.serviceDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                //validate明細行()～validateレコード整合性()にて例外がスローされた場合
                //－アップロードエラーを更新する。
                //－上位に例外をスローする。
                //saveアップロードエラー(エラー情報 : ErrorInfo)
                //エラー情報：　@（catchした例外）.getErrorInfo()
                l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadError(l_ex.getErrorInfo());

                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_adminSrvRegiOtherOrgIdUploadCsv.getSequenceNumber(l_intLineNumber)
                    + "," + l_adminSrvRegiOtherOrgIdUploadCsv.getId(l_intLineNumber));
            }
        }

        //get明細行数( )
        int l_intRowCount = l_adminSrvRegiOtherOrgIdUploadCsv.getRowCount();

        //getアップロードＩＤ( )
        long l_lngUploadID = l_adminSrvRegiOtherOrgIdUploadCsv.getAdministratorUploadId();

        //getアップロード区分( )
        String l_strUploadDiv = l_adminSrvRegiOtherOrgIdUploadCsv.getUploadDiv();

        //saveアップロードTemp( )
        l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadTemp();

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認ﾚｽﾎﾟﾝｽ( )
        WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse)l_request.createResponse();

        //レスポンスプロパティに値をセットする。
        //アップロード件数：get明細行数()の戻り値
        l_response.lineCount = l_intRowCount + "";
        //アップロードID：getアップロードID()の戻り値
        l_response.uploadId = l_lngUploadID + "";
        //アップロード区分：getアップロード区
        l_response.uploadDiv = l_strUploadDiv;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitアップロードファ@イル)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(サービス利用)外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・submitアップロードファ@イル」参照。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B941DD0343
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse submitUploadFile(
        WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitUploadFile(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード：機@能カテゴリコード.サービス利用管理(外部連携)
        //is更新：true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_OTHERORG, true);

        //isDIR管理者( )
        boolean l_blnIsDirAdministrator = l_admin.isDirAdministrator();

        //(*1) isDIR管理者()=false の場合、受付時間チェックを実施
        if (!l_blnIsDirAdministrator)
        {
            //validate注文受付可能( )
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }

        //get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV(Long)
        //アップロードＩＤ：　@リクエストのアップロードＩＤ（String）をLong型に変換してセット
        WEB3AdminSrvRegiOtherOrgIdUploadCsv l_adminSrvRegiOtherOrgIdUploadCsv =
            new WEB3AdminSrvRegiOtherOrgIdUploadCsv(
                new Long(l_request.uploadId));

        //setアップロード区分(String, String)
        //アップロード区分：　@リクエストデータ.アップロード区分
        //明細行文字列：　@null
        l_adminSrvRegiOtherOrgIdUploadCsv.setUploadDiv(l_request.uploadDiv, null);

        //createカラムヘッダ( )
        l_adminSrvRegiOtherOrgIdUploadCsv.createColumnHeader();

        //validate同時アップロード(アップロードＩＤ : Long)
        //アップロードID：　@リクエストのアップロードＩＤ（String）をLong型に変換してセット
        l_adminSrvRegiOtherOrgIdUploadCsv.validateSameTimeUpload(
            new Long(l_request.uploadId));

        //validate取引パスワード(パスワード : String)
        //パスワード：　@リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);

        //setDataFromアップロードTemp(アップロードＩＤ : long)
        //アップロードＩＤ：　@リクエストのアップロードＩＤ（String）をlong型に変換してセット
        l_adminSrvRegiOtherOrgIdUploadCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadId));

        //明細行の数分（明細行[0]から）LOOP処理
        int l_intRowCount = l_adminSrvRegiOtherOrgIdUploadCsv.getRowCount();
        for (int i = 0; i < l_intRowCount; i++)
        {
            try
            {
                //get通番(int)
                long l_lngSequenceNumber = l_adminSrvRegiOtherOrgIdUploadCsv.getSequenceNumber(i);

                //getステータス(int)
                String l_strStatus = l_adminSrvRegiOtherOrgIdUploadCsv.getStatus(i);

                //isアップロード新規登録( )
                boolean l_blnIsUploadNewRegist = l_adminSrvRegiOtherOrgIdUploadCsv.isUploadNewRegist();

                WEB3AdminSrvRegiOtherOrgIdUploadUnitService l_adminSrvRegiOtherOrgIdUploadUnitService =
                    (WEB3AdminSrvRegiOtherOrgIdUploadUnitService)Services.getService(
                        WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class);
                //isアップロード新規登録( )=true の場合
                if (l_blnIsUploadNewRegist)
                {
                    //getID(int)
                    String l_strId = l_adminSrvRegiOtherOrgIdUploadCsv.getId(i);

                    //getパスワード(int)
                    String l_strPassword = l_adminSrvRegiOtherOrgIdUploadCsv.getPassword(i);

                    //insert外部連携情報(long, String, String, String, String, String)
                    //通番：　@get通番()の戻り値
                    //サービス区分：　@リクエストデータ.サービス区分
                    //ID：　@getID()の戻り値
                    //パスワード：　@getパスワード()の戻り値
                    //ステータス：　@getステータス()の戻り値
                    //証券会社コード：　@管理者.get証券会社コード() の戻り値
                    l_adminSrvRegiOtherOrgIdUploadUnitService.insertOtherOrgInfo(
                        l_lngSequenceNumber,
                        l_request.serviceDiv,
                        l_strId,
                        l_strPassword,
                        l_strStatus,
                        l_strInstitutionCode);
                }
                else
                {
                    //get部店コード(int)
                    String l_strBranchCode =
                        l_adminSrvRegiOtherOrgIdUploadCsv.getBranchCode(i);

                    //get部店コード()≠null の場合、部店権限チェックを実施
                    if (l_strBranchCode != null)
                    {
                        // validate部店権限(部店コード)
                        //部店コード：get部店コード()の戻り値
                        l_admin.validateBranchPermission(l_strBranchCode);
                    }

                    //update外部連携情報(long, String, String)
                    //通番：　@get通番()の戻り値
                    //サービス区分：　@リクエストデータ.サービス区分
                    //ステータス：　@getステータス()の戻り値
                    l_adminSrvRegiOtherOrgIdUploadUnitService.updateOtherOrgInfo(
                        l_lngSequenceNumber,
                        l_request.serviceDiv,
                        l_strStatus);
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //saveアップロードエラー(エラー情報 : ErrorInfo)
                //エラー情報：　@（catchした例外）.getErrorInfo()
                l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadError(l_ex.getErrorInfo());
                String l_strId = l_adminSrvRegiOtherOrgIdUploadCsv.getId(i);
                long l_lngSequenceNumber = l_adminSrvRegiOtherOrgIdUploadCsv.getSequenceNumber(i);
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_lngSequenceNumber + "," + l_strId);
            }
        }

        //saveアップロード終了( )
        l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadEnd();

        //deleteアップロードTemp( )
        l_adminSrvRegiOtherOrgIdUploadCsv.deleteUploadTemp();

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽ( )
        WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undoアップロード)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(サービス利用)外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・undoアップロード」参照。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B941DD0345
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse undoUpload(
        WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "undoUpload(WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV(Long)
        //アップロードＩＤ：　@リクエストのアップロードＩＤ（String）をLong型に変換してセット
        WEB3AdminSrvRegiOtherOrgIdUploadCsv l_adminSrvRegiOtherOrgIdUploadCsv =
            new WEB3AdminSrvRegiOtherOrgIdUploadCsv(new Long(l_request.uploadId));

        //deleteアップロードTemp( )
        l_adminSrvRegiOtherOrgIdUploadCsv.deleteUploadTemp();

        //saveアップロード中止( )
        l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadStop();

        //createResponse( )
        WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
