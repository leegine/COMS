head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者FX口座開設アップロードサービスImpl(WEB3AdminFXAccOpenUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 鄭徳懇(中訊) 新規作成
                 : 2006/03/01 情野（SRA） 受入障害U02776対応
                 : 2006/03/09 情野（SRA） エラー口座一覧のシンボル名変更対応
Revesion History : 2008/09/08 劉仁和 (中訊) 仕様変更・モデル960~モデル964
Revesion History : 2009/03/21 車進 (中訊) 仕様変更・モデル1132,ＤＢ更新仕様215
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.aio.WEB3AdminFXAccOpenUploadCsv;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCancelRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCancelResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadInputResponse;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXUploadHistoryUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXAccOpenUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3AccTypeDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccOpenDiv;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.aio.define.WEB3UploadStateDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者FX口座開設アップロードサービスImpl)<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadServiceImpl implements WEB3AdminFXAccOpenUploadService 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenUploadServiceImpl.class);
    
    /**
     * @@roseuid 43F49A6D03A9
     */
    public WEB3AdminFXAccOpenUploadServiceImpl() 
    {
     
    }
    
    /**
     * 管理者FX口座開設アップロード処理を実施する。 <BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。 <BR>
     * <BR>
     * －getアップロード画面() <BR>
     * －validateアップロードファ@イル() <BR>
     * －submitアップロード() <BR>
     * －undoアップロード() <BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E04257004B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFXAccOpenUploadInputRequest)
        {
            l_response = this.getUploadScreen((WEB3AdminFXAccOpenUploadInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminFXAccOpenUploadConfirmRequest)
        {
            l_response = this.validateUploadFile((WEB3AdminFXAccOpenUploadConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminFXAccOpenUploadCompleteRequest)
        {
            l_response = this.submitUpload((WEB3AdminFXAccOpenUploadCompleteRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminFXAccOpenUploadCancelRequest)
        {
            l_response = this.undoUpload((WEB3AdminFXAccOpenUploadCancelRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getアップロード画面)<BR>
     * アップロード画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座開設ULサービス)getアップロード画面」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXAccOpenUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E0425903B6
     */
    protected WEB3AdminFXAccOpenUploadInputResponse getUploadScreen(WEB3AdminFXAccOpenUploadInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUploadScreen(WEB3AdminFXAccOpenUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE, true);
        
        //1.3 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4 管理者FX口座開設アップロードCSV( )
        WEB3AdminFXAccOpenUploadCsv l_fxAccOpenUploadCsv = new WEB3AdminFXAccOpenUploadCsv();
        
        //1.5 get最新アップロード履歴(データキー : long)
        AdministratorUploadRow l_row = 
            l_fxAccOpenUploadCsv.getLatestUploadAction(l_admin.getInstitution().getInstitutionId());
        
        //1.6 最新アップロード履歴が存在する場合（get最新アップロード履歴() != null）
        WEB3FXUploadHistoryUnit l_unit = null;
        if (l_row != null)
        {
            //1.6.1 FXアップロード履歴明細( )
            l_unit = new WEB3FXUploadHistoryUnit();
            
            //(*)FXアップロード履歴明細プロパティに以下の通り、値をセットする。
            //アップロード処理状態区分：
            //　@（アップロード行.アップロード終了日時 == nullの場合）　@”アップロード中”
            //　@（アップロード行.アップロード終了日時 != nullの場合）　@”アップロード済”
            if (l_row.getUploadEndTimestamp() == null)
            {
                l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOADING;
            }
            else
            {
                l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOAD_COMPLETE;
            }
            
            //アップロード件数：　@アップロード行.アップロード件数
            l_unit.uploadNumber = String.valueOf(l_row.getUploadCount());
            
            //アップロード開始日時：　@アップロード行.アップロード開始日時
            l_unit.uploadStartDate = l_row.getUploadStartTimestamp();
            
            //アップロード終了日時：　@アップロード行.アップロード終了日時
            l_unit.uploadEndDate = l_row.getUploadEndTimestamp();
            
            // FXエラー番号：　@アップロード行.メッセージコード
            l_unit.fxErrorId = l_row.getMessageCode();
        }
        
        //1.7 createResponse( )
        WEB3AdminFXAccOpenUploadInputResponse l_response = 
            (WEB3AdminFXAccOpenUploadInputResponse) l_request.createResponse();
        
        //1.8 (*)プロパティセット
        //(*)レスポンスデータプロパティに以下の通り、値をセットする。
        //FXアップロード履歴明細：　@
        //最新アップロード履歴が存在する場合、FXアップロード履歴明細オブジェクト。
        //最新アップロード履歴が存在しない場合、null。
        l_response.uploadHistoryUnit = l_unit;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validateアップロードファ@イル)<BR>
     * アップロードファ@イル確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座開設ULサービス)validateアップロードファ@イル」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXAccOpenUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E04268007A
     */
    protected WEB3AdminFXAccOpenUploadConfirmResponse validateUploadFile(
        WEB3AdminFXAccOpenUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateUploadFile(WEB3AdminFXAccOpenUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE, true);
        
        //1.4 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5 管理者FX口座開設アップロードCSV( )
        WEB3AdminFXAccOpenUploadCsv l_fxAccOpenUploadCsv = new WEB3AdminFXAccOpenUploadCsv();
        
        //1.6 validate同時アップロード(アップロードＩＤ : long)
        l_fxAccOpenUploadCsv.validateSameTimeUpload(null);
        
        //1.7 saveアップロード開始(データキー : long, 
        //備考１ : String, 備考２ : String, 
        //備考３ : String, 更新者コード : String)
        l_fxAccOpenUploadCsv.saveUpLoadStart(
            l_admin.getInstitution().getInstitutionId(), 
            null, 
            null, 
            null, 
            l_admin.getAdministratorCode());
        
        //1.8 ArrayList( )
        ArrayList l_lisDuplicateAccounts = new ArrayList();
        
        //1.9 ArrayList( )
        ArrayList l_lisErrorAccounts = new ArrayList();
        
        //1.10 リクエストデータ.アップロードファ@イル[]の各要素毎のLoop処理 
        int l_uploadFilelength = l_request.uploadFile.length; 
        for (int i = 0; i < l_uploadFilelength; i++)
        {
            //1.10.1 add明細行(明細行文字列 : String)
            int l_intLineNumber = 0;
            try
            {
                l_intLineNumber = l_fxAccOpenUploadCsv.addRow(l_request.uploadFile[i]);
            }
            //1.10.2 add明細行()が例外をスローした場合
            catch (WEB3SystemLayerException l_ex)
            {
                //1.10.2.1 saveアップロードエラー(エラー情報 : ErrorInfo)
                l_fxAccOpenUploadCsv.saveUploadError(l_ex.getErrorInfo());
                
                //1.10.2.2 catchした例外を上位に再スローする 
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(), 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    String.valueOf(i + 1), 
                    l_ex);
            }
            
            //1.10.3 空行の場合（add明細行()の戻り値） < 0）、
            //当該要素の処理を中断（continue;）
            if (l_intLineNumber < 0)
            {
                continue;
            }
            
            //1.10.4 validate明細行(int)
            try
            {
                l_fxAccOpenUploadCsv.validateDetailsLine(l_intLineNumber);
            }
            //1.10.5 validate明細行()が例外をスローした場合
            catch (WEB3BaseException l_ex)
            {
                //1.10.5.1 get利用者コード(int)
                String l_strUserCode = l_fxAccOpenUploadCsv.getUserCode(l_intLineNumber);
                
                //1.10.5.2 get備考(int)
                String l_strRemark = l_fxAccOpenUploadCsv.getRemark(l_intLineNumber);
                
                //1.10.5.3 saveアップロードエラー(エラー情報 : ErrorInfo)
                l_fxAccOpenUploadCsv.saveUploadError(l_ex.getErrorInfo());
                
                //1.10.5.4 catchした例外を上位に再スローする
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_ex.getErrorInfo(), 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_strUserCode + "," + l_strRemark, 
                    l_ex);
            }
            
            try
            {
                //1.10.6 validate重複行(int)
                l_fxAccOpenUploadCsv.validateDuplicateLine(l_intLineNumber);
            }
            //1.10.7 validate重複行()が例外をスローした場合、追加した明細行を削除し、
            //当該要素の処理を中断（continue;）
            catch (WEB3BaseException l_ex)
            {
                //1.10.7.1 get利用者コード(int)
                String l_strUserCode = l_fxAccOpenUploadCsv.getUserCode(l_intLineNumber);
                
                //1.10.9.2 add(arg0 : Object)
                l_lisDuplicateAccounts.add(l_strUserCode);
                
                //1.10.7.3 delete明細行(行番号 : int)
                l_fxAccOpenUploadCsv.deleteRow(l_intLineNumber);
                
                continue;
            }
         
            try
            {
                //1.10.8 getGFT口座開設状況(int)
                l_fxAccOpenUploadCsv.getGFTAccountOpenStatus(l_intLineNumber);
            }
            //1.10.9 getGFT口座開設状()が例外をスローした場合、追加した明細行を削除し、
            //当該要素の処理を中断（continue;）
            catch (WEB3BaseException l_ex)
            {
                //1.10.9.1 get利用者コード(int)
                String l_strUserCode = l_fxAccOpenUploadCsv.getUserCode(l_intLineNumber);
                
                //1.10.9.2 add(arg0 : Object)
                l_lisErrorAccounts.add(l_strUserCode);
                
                //1.10.9.3 delete明細行(行番号 : int)
                l_fxAccOpenUploadCsv.deleteRow(l_intLineNumber);
                
                continue;
            }
        }
        
        //1.11 get明細行数( )
        int l_intRowCount = l_fxAccOpenUploadCsv.getRowCount();
        
        //1.12 getアップロードＩＤ( )
        long l_lngUploadId = l_fxAccOpenUploadCsv.getAdministratorUploadId();
                    
        //1.13 saveアップロードTemp( )
        l_fxAccOpenUploadCsv.saveUploadTemp();
        
        //1.14 createResponse( )
        WEB3AdminFXAccOpenUploadConfirmResponse l_response = 
            (WEB3AdminFXAccOpenUploadConfirmResponse) l_request.createResponse();
        
        //1.15 (*)プロパティセット
        //(*)レスポンスデータプロパティに、以下の通り値をセットする。
        //アップロード件数：　@get明細行数()の戻り値
        l_response.uploadNumber = String.valueOf(l_intRowCount);
        
        //アップロードID：　@getアップロードID()の戻り値
        l_response.uploadId = String.valueOf(l_lngUploadId);
        
        //重複口座一覧：　@重複口座ListのString型の配列
        String[] l_strDuplicateAccounts = new String[l_lisDuplicateAccounts.size()];
        l_lisDuplicateAccounts.toArray(l_strDuplicateAccounts);
        l_response.duplicateAccountList = l_strDuplicateAccounts;
        
        //エラー口座一覧：　@エラー口座ListのString型の配列
        String[] l_strErrorAccounts = new String[l_lisErrorAccounts.size()];
        l_lisErrorAccounts.toArray(l_strErrorAccounts);                                       
        l_response.errorAccountList = l_strErrorAccounts;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submitアップロード)<BR>
     * アップロード完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座開設ULサービス)submitアップロード」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXAccOpenUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E04274002C
     */
    protected WEB3AdminFXAccOpenUploadCompleteResponse submitUpload(WEB3AdminFXAccOpenUploadCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitUpload(WEB3AdminFXAccOpenUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE, true);
        
        //1.4 validate取引パスワード(パスワード : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.6 管理者FX口座開設アップロードCSV( )
        WEB3AdminFXAccOpenUploadCsv l_fxAccOpenUploadCsv = new WEB3AdminFXAccOpenUploadCsv();
        
        //1.7 validate同時アップロード(アップロードＩＤ : long)
        
        l_fxAccOpenUploadCsv.validateSameTimeUpload(Long.valueOf(l_request.uploadId));
        
        //1.8 setDataFromアップロードTemp(アップロードＩＤ : long)
        l_fxAccOpenUploadCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadId));
        
        //1.9 get明細行数( )
        int l_intRowCount = l_fxAccOpenUploadCsv.getRowCount();
        
        //1.10 FX口座情報一覧の作成
        WEB3FXAccInformationUnit[] l_units = new WEB3FXAccInformationUnit[2];
        l_units[0] = new WEB3FXAccInformationUnit();
        l_units[0].fxCourseDiv = WEB3GftTransStatusCourseDivDef.ONE_COSE;
        l_units[0].fxAccountCode = "111111";
        l_units[1] = new WEB3FXAccInformationUnit();
        l_units[1].fxCourseDiv = WEB3GftTransStatusCourseDivDef.TEN_COSE;
        l_units[1].fxAccountCode = "222222";
        
        //1.11 ArrayList( )
        ArrayList l_lisErrorAccounts = new ArrayList();
        
        //1.12 明細行の数分Loop処理
        for (int i = 0; i < l_intRowCount; i++)
        {
            //1.12.1 FX口座開設1件処理
            try
            {
                //1.12.1.1 getGFT口座開設状況(int)
                GftAccountOpenStatusParams l_params = l_fxAccOpenUploadCsv.getGFTAccountOpenStatus(i);
                
                //1.12.1.2 insertFX顧客(
                //GFT口座開設状況Params : GFT口座開設状況Params, 
                //更新者コード : String)
                String l_strAdminCode = l_admin.getAdministratorCode();
                WEB3FXDataControlService l_fxDataControlService = 
                    (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
                l_fxDataControlService.insertFXAccount(l_params, l_strAdminCode);
                
                //1.12.1.3 FX口座情報一覧の要素数分Loop処理
                for (int j = 0; j < l_units.length; j++)
                {
                    //1.12.1.3.1 insertFX口座番号(
                    //GFT口座開設状況Params : GFT口座開設状況Params, 
                    //FX口座情報 : FX口座情報, 更新者コード : String)
                    l_fxDataControlService.insertFXAccountCode(l_params, l_units[j], l_strAdminCode);
                }
                
                //1.12.1.4 get顧客(証券会社コード : String, 
                //部店コード : String, 口座コード : String)
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
                WEB3GentradeAccountManager l_AccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                String l_strInstitutionCode = l_params.getInstitutionCode();
                String l_strBranceCode = l_params.getBranchCode();
                String l_strAccountCode = l_params.getAccountCode();
                MainAccount l_mainAccount = l_AccountManager.getMainAccount(
                    l_strInstitutionCode, 
                    l_strBranceCode, 
                    l_strAccountCode);

                //updateFX口座開設区分(String, String, String, String, String)
                //[引数]
                //証券会社コード：　@getGFT口座開設状況()の戻り値.get証券会社コード()
                //部店コード：　@getGFT口座開設状況()の戻り値.get部店コード()
                //顧客コード：　@getGFT口座開設状況()の戻り値.get顧客コード()
                //FX口座開設区分：　@"口座開設"
                //更新者コード：　@管理者オブジェクト.get管理者コード()
                l_fxDataControlService.updateFXAccountOpenDiv(
                    l_strInstitutionCode,
                    l_strBranceCode,
                    l_strAccountCode,
                    WEB3AccountOpenDef.OPEN,
                    l_strAdminCode);

                //insert口座開設区分(口座ID : long, 口座種別 : String,
                //  口座開設区分 : String, 更新者コード : String)
                //口座開設区分テーブル(acc_open_div)に行のinsertを行う
                //[引数]
                //口座ID：顧客マスタテーブルより、引数の証券会社コード、
                //        部店コード、口座コードに該当する口座IDを取得し返却する。
                //口座種別："01：FX"
                //口座開設区分："1:開設済"
                //更新者コード：管理者オブジェクト.get管理者コード()
                long l_lngAccountId = l_mainAccount.getAccountId();
                WEB3GentradeAccOpenDiv l_genAccOpenDiv = new WEB3GentradeAccOpenDiv();
                l_genAccOpenDiv.insertAccOpenDiv(
                    l_lngAccountId,
                    WEB3AccTypeDef.FX,
                    WEB3AccountOpenDef.OPEN,
                    l_strAdminCode);

                //1.12.1.6 updateGFT口座開設状況(GFT口座開設状況Params : GFT口座開設状況Params, 
                //更新後ステータス区分 : String, 更新後FX口座情報一覧 : FX口座情報[], 
                //更新者コード : String, 更新後約諾書区分 : String)
                l_fxDataControlService.updateGFTAccountOpenStatus(
                    l_params, 
                    WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE, 
                    l_units, 
                    l_strAdminCode, 
                    null);
            }
            //1.12.2 FX口座開設1件処理で例外がスローされた場合、当該明細行を削除し、
            //処理を中断(continue;)
            catch (WEB3BaseException l_ex)
            {
                //1.12.2.1 get利用者コード(int)
                String l_strUserCode = l_fxAccOpenUploadCsv.getUserCode(i);
                
                //1.12.2.2 add(arg0 : Object)
                l_lisErrorAccounts.add(l_strUserCode);
                
                //1.12.2.3 delete明細行(行番号 : int)
                l_fxAccOpenUploadCsv.deleteRow(i);
                
                //1.12.2.4 ループのindexとループ数（明細行数）を１マイナスする。
                i--;    
                l_intRowCount--;
                continue;
            }
        }
        
        //1.13 get明細行数( )
        int l_intRowNumber = l_fxAccOpenUploadCsv.getRowCount();
        
        //1.14 saveアップロード終了( )
        l_fxAccOpenUploadCsv.saveUploadEnd();
                    
        //1.15 saveアップロードTemp( )
        l_fxAccOpenUploadCsv.deleteUploadTemp();

        //1.16 createResponse( ) 
        WEB3AdminFXAccOpenUploadCompleteResponse l_response = 
            (WEB3AdminFXAccOpenUploadCompleteResponse) l_request.createResponse();
        
        //1.17 (*)レスポンスデータプロパティに、以下の通り値をセットする。
        //アップロード件数：　@get明細行数()の戻り値
        l_response.uploadNumber = String.valueOf(l_intRowNumber);
        
        //エラー口座一覧：　@エラー口座ListのString型の配列
        String[] l_strErrorAccounts = new String[l_lisErrorAccounts.size()];
        l_lisErrorAccounts.toArray(l_strErrorAccounts);
        l_response.errorAccountList = l_strErrorAccounts;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undoアップロード)<BR>
     * アップロード中止処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座開設ULサービス)undoアップロード」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXAccOpenUploadCancelResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E0428100E8
     */
    protected WEB3AdminFXAccOpenUploadCancelResponse undoUpload(WEB3AdminFXAccOpenUploadCancelRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " undoUpload(WEB3AdminFXAccOpenUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 管理者FX口座開設アップロードCSV(long)
        WEB3AdminFXAccOpenUploadCsv l_fxAccOpenUploadCsv = 
            new WEB3AdminFXAccOpenUploadCsv(Long.parseLong(l_request.uploadId));
        
        //1.3 deleteアップロードTemp( )
        l_fxAccOpenUploadCsv.deleteUploadTemp();
        
        //1.4 saveアップロード中止( )
        l_fxAccOpenUploadCsv.saveUploadStop();
        
        //1.5 createResponse( )
        log.exiting(STR_METHOD_NAME);
        return (WEB3AdminFXAccOpenUploadCancelResponse) l_request.createResponse();
    }
}
@
