head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPackageAdjustServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント一括調整サービスImpl(WEB3AdminPointPackageAdjustServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.point.WEB3AdminPointPackageAdjustUploadCsv;
import webbroker3.point.WEB3PointAdjust;
import webbroker3.point.WEB3PointApplyManager;
import webbroker3.point.define.WEB3UploadStateDef;
import webbroker3.point.message.WEB3AdminPointUploadCancelRequest;
import webbroker3.point.message.WEB3AdminPointUploadCancelResponse;
import webbroker3.point.message.WEB3AdminPointUploadCompleteRequest;
import webbroker3.point.message.WEB3AdminPointUploadCompleteResponse;
import webbroker3.point.message.WEB3AdminPointUploadConfirmRequest;
import webbroker3.point.message.WEB3AdminPointUploadConfirmResponse;
import webbroker3.point.message.WEB3AdminPointUploadInputRequest;
import webbroker3.point.message.WEB3AdminPointUploadInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPackageAdjustService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (ポイント一括調整サービスImpl)<BR>
 * ポイント一括調整サービス実装クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointPackageAdjustServiceImpl implements WEB3AdminPointPackageAdjustService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointPackageAdjustServiceImpl.class);

    /**
     * ポイント一括調整サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * <BR>
     *    get入力画面()<BR>
     *    validate調整()<BR>
     *    submit調整()<BR>
     *    undo調整()<BR>
     * <BR>
     * 上記メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 419845DF0105
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request instanceof WEB3AdminPointUploadInputRequest)
        {
            WEB3AdminPointUploadInputResponse l_response = 
                this.getInputScreen((WEB3AdminPointUploadInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointUploadConfirmRequest)
        {
			WEB3AdminPointUploadConfirmResponse l_response = new WEB3AdminPointUploadConfirmResponse();
			
			try {
				l_response = this.validatePackageAdjust((WEB3AdminPointUploadConfirmRequest)l_request);
			} catch (WEB3BaseException l_e) {
				log.error(STR_METHOD_NAME, l_e);
				log.exiting(STR_METHOD_NAME);
				l_response.errorInfo = l_e.getErrorInfo();
				l_response.errorMessage = l_e.getErrorMessage();
			}
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointUploadCompleteRequest)
        {
            WEB3AdminPointUploadCompleteResponse l_response = 
                this.submitPackageAdjust((WEB3AdminPointUploadCompleteRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointUploadCancelRequest)
        {
            WEB3AdminPointUploadCancelResponse l_response = 
                this.undoPackageAdjust((WEB3AdminPointUploadCancelRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント一括調整）get入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadInputResponse
     * @@roseuid 419845DF0134
     */
    protected WEB3AdminPointUploadInputResponse getInputScreen(WEB3AdminPointUploadInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointUploadInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PACKAGE_ADJUST, true);
        
        //1.3 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4 ポイント一括調整アップロードCSV( )
        WEB3AdminPointPackageAdjustUploadCsv l_csv = new WEB3AdminPointPackageAdjustUploadCsv();
        
        //1.5 validate同時アップロード(long)
        l_csv.validateSameTimeUpload(null);
        
        //1.6 get最新アップロード履歴(long)
        AdministratorUploadRow l_row = l_csv.getLatestUploadAction(l_admin.getInstitution().getInstitutionId());
        
        //1.7 createResponse( )
        WEB3AdminPointUploadInputResponse l_response = (WEB3AdminPointUploadInputResponse)l_request.createResponse();
        
        if (l_response == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.8 (*)プロパティセット
        if (l_row != null)
        {
            log.debug("l_row != null");
            
            //アップロード処理状態区分
            if (l_row.getUploadEndTimestamp() == null)
            {
                log.debug("l_row.getUploadEndTimestamp() == null");
                l_response.uploadState = WEB3UploadStateDef.UPLOADING;
            }
            else
            {
                log.debug("l_row.getUploadEndTimestamp() != null");
                l_response.uploadState = WEB3UploadStateDef.UPLOAD_COMPLETE;
            }
            
            if (!l_row.getUploadCountIsNull())
            {
                //アップロード処理件数
                l_response.uploadCount = WEB3StringTypeUtility.formatNumber(l_row.getUploadCount());
            }
            
            //アップロード開始日時
            l_response.uploadStartDate = l_row.getUploadStartTimestamp();
            
            //アップロード終了日時
            l_response.uploadEndDate = l_row.getUploadEndTimestamp();
            
            //アップロードエラー番号
            l_response.uploadErrorNo = l_row.getMessageCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate一括調整)<BR>
     * アップロードデータの審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント一括調整）validate一括調整」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadConfirmResponse
     * @@roseuid 419845DF0154
     */
    protected WEB3AdminPointUploadConfirmResponse validatePackageAdjust(WEB3AdminPointUploadConfirmRequest l_request)
        throws WEB3BaseException 
    {

        final String STR_METHOD_NAME = " validatePackageAdjust(WEB3AdminPointUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PACKAGE_ADJUST, true);
        
        //1.3 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4 ポイント一括調整アップロードCSV( )
        WEB3AdminPointPackageAdjustUploadCsv l_csv = new WEB3AdminPointPackageAdjustUploadCsv();
        
        //1.5 validate同時アップロード(long)
        l_csv.validateSameTimeUpload(null);
        
        //1.6 saveアップロード開始(long, String, String, String, String)
        l_csv.saveUpLoadStart(l_admin.getInstitution().getInstitutionId(), null, null, null, l_admin.getAdministratorCode());
        
        //1.7 validate明細行(ポイント一括調整アップロードCSV, String[], 管理者)
        this.validateDetailsLine(l_csv, l_request.uploadFile, l_admin);
        
        //1.8 getアップロードＩＤ( )
        long l_lngUploadId = l_csv.getAdministratorUploadId();
        
        //1.9 saveアップロードTemp( )
        int l_intLineCount  = l_csv.saveUploadTemp();
        
        //1.10 createResponse( )
        WEB3AdminPointUploadConfirmResponse l_response = (WEB3AdminPointUploadConfirmResponse)l_request.createResponse();
        
        if (l_response == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.11 (*)プロパティセット
        l_response.lineCount = WEB3StringTypeUtility.formatNumber(l_intLineCount);
        l_response.uploadId = WEB3StringTypeUtility.formatNumber(l_lngUploadId);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit一括調整)<BR>
     * アップロードデータの登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント一括調整）submit一括調整」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadCompleteResponse
     * @@roseuid 419845DF0163
     */
    protected WEB3AdminPointUploadCompleteResponse submitPackageAdjust(WEB3AdminPointUploadCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitPackageAdjust(WEB3AdminPointUploadCompleteRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PACKAGE_ADJUST, true);
        
        //1.3 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.4 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 ポイント一括調整アップロードCSV( )
        WEB3AdminPointPackageAdjustUploadCsv l_csv = new WEB3AdminPointPackageAdjustUploadCsv(Long.parseLong(l_request.uploadId));
        
        //1.6 validate同時アップロード( )
        l_csv.validateSameTimeUpload();
        
        //1.7 setDataFromアップロードTemp(アップロードＩＤ : long)
        l_csv.setDataFromUploadTemp();
        
        //1.8 get明細行数( )
        int l_intRowCount = l_csv.getRowCount();

        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //1.9 (*)明細行の数分LOOP処理
        for (int i = 0; i < l_intRowCount; i++)
        {
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            int l_intAdjustPoint = 0;
            String l_strAccountCodeInDb = null;
            
            try
            {
                //1.9.1 get部店コード(int)
                l_strBranchCode = l_csv.getBranchCode(i);
            
                //1.9.2 get顧客コード(行番号 : int)
                l_strAccountCode = l_csv.getAccountCode(i);
            
                //1.9.3 get調整ポイント(int)
                l_intAdjustPoint = (int)l_csv.getAdjustPoint(i);
                
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                if (l_finApp == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                WEB3GentradeAccountManager l_accountManager = 
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                if (l_accountManager == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME); 
                }
                
                WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
                    l_admin.getInstitutionCode(), l_strBranchCode, l_strAccountCode);//WEB3BaseException
                if (l_mainAccount == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                l_strAccountCodeInDb = l_mainAccount.getAccountCode();
            
                //1.9.4 get利用可能ポイント
                long l_lngUsePossiblePoint = l_applyManager.getUsePossiblePoint(
                    l_admin.getInstitutionCode(), 
                    l_strBranchCode, 
                    l_strAccountCodeInDb);//WEB3BaseException
            
                //1.9.5 validate調整ポイント(String, long)
                l_applyManager.validateAdjustPoint(String.valueOf(l_intAdjustPoint), l_lngUsePossiblePoint);//WEB3BaseException
            }
            //1.9.6 (*1)
            catch (WEB3BaseException l_e)
            {
                //1.9.6.1 saveアップロードエラー
                l_csv.saveUploadError(l_e.getErrorInfo());//WEB3SystemLayerException
                                
                String l_strErrorMessage = 
                    l_csv.getBranchCode(i)
                    + "."
                    + l_csv.getAccountCode(i) 
                    + "." 
                    + l_csv.getAdjustPoint(i)
                    + "."
                    + l_e.getErrorMessage(); 
                log.error(l_strErrorMessage, l_e);                
                //1.9.6.2 throw（例外）
                throw new WEB3BaseException(
                     l_e.getErrorInfo(),
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_strErrorMessage,
                     l_e);      
            }            

            //1.9.7 ポイント調整(String, String, String, int)
            WEB3PointAdjust l_adjust = new WEB3PointAdjust(
                l_admin.getInstitutionCode(), 
                l_strBranchCode, 
                l_strAccountCodeInDb,
                l_intAdjustPoint);

            //1.9.8 saveNew調整(ポイント調整, 管理者)
            l_applyManager.saveNewAdjust(l_adjust, l_admin);//WEB3BaseException
        }
        
        //1.10 saveアップロード終了( )
        l_csv.saveUploadEnd();
        
        //1.11 deleteアップロードTemp( )
        l_csv.deleteUploadTemp();
        
        //1.12 createResponse( )
        WEB3AdminPointUploadCompleteResponse l_response = (WEB3AdminPointUploadCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undo一括調整)<BR>
     * アップロードの中止を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント一括調整）undo一括調整」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadCancelResponse
     * @@roseuid 419845DF0125
     */
    protected WEB3AdminPointUploadCancelResponse undoPackageAdjust(WEB3AdminPointUploadCancelRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " undoPackageAdjust(WEB3AdminPointUploadCancelRequest )";
        log.entering(STR_METHOD_NAME);

        //1.1 ポイント一括調整アップロードCSV(long)
        WEB3AdminPointPackageAdjustUploadCsv l_csv = new WEB3AdminPointPackageAdjustUploadCsv(Long.parseLong(l_request.uploadId));
        
        //1.2 deleteアップロードTemp( )
        l_csv.deleteUploadTemp();// throws WEB3SystemLayerException
        
        //1.3 saveアップロード中止( )
        l_csv.saveUploadStop();// throws WEB3SystemLayerException
        
        //1.4 createResponse( )
        WEB3AdminPointUploadCancelResponse l_response = (WEB3AdminPointUploadCancelResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate明細行)<BR>
     * 明細行のチェックを行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント一括調整）validate明細行」 参照<BR>
     *  ========================================================<BR>
     * シーケンス図(「（ポイント一括調整）validate明細行」): <BR>
     *         1.1.7get顧客(String, String, String)<BR>
     *          顧客オブジェクトが取得できなかった場合は、例外<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * @@param l_uploadCsv - (アップロードCSV)<BR>
     * ポイント一括調整アップロードCSVオブジェクト<BR>
     * 
     * @@param l_strDetailsLines - (明細行)<BR>
     * 明細行<BR>
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 4199DE2E03A9
     */
    protected void validateDetailsLine(
        WEB3AdminPointPackageAdjustUploadCsv l_uploadCsv, 
        String[] l_strDetailsLines, 
        WEB3Administrator l_admin)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDetailsLine(WEB3AdminPointPackageAdjustUploadCsv ,String[] ,WEB3Administrator )";
        log.entering(STR_METHOD_NAME);
        
        if (l_uploadCsv == null || l_strDetailsLines == null || l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            String l_strErrorMessage = "アップロードデータnull!";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        if (l_accountManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        int l_intLineCount = l_strDetailsLines.length;
        //1.1 (*1)明細行各要素毎のLoop処理
        for (int i = 0; i < l_intLineCount; i++)
        {
            log.debug("loop count:" + i);
            int l_intNewLineNumber = 0;
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            long l_lngAdjustPoint = 0;
            try
            {
                //1.1.1 add明細行(String)
                l_intNewLineNumber = l_uploadCsv.addRow(l_strDetailsLines[i]);//WEB3SystemLayerException
            }
            //1.1.2 (*2)
            catch (WEB3SystemLayerException l_e)
            {
                //例外の追加文字列（WEB3BaseException.errorMessage）に
                //明細行文字列（明細行[index]）をセットする。
                String l_strErrorMessage = l_strDetailsLines[i];                               
                
                if (WEB3ErrorCatalog.SYSTEM_ERROR_80023.equals(l_e.getErrorInfo()))
                {
                    //1.1.2.1 saveアップロードエラー(ErrorInfo)
                    l_uploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_01721);
                    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01721,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strErrorMessage);
                }

                //1.1.2.1 saveアップロードエラー(ErrorInfo)
                l_uploadCsv.saveUploadError(l_e.getErrorInfo());                
                
                //1.1.2.2 例外をスロー
                throw new WEB3SystemLayerException(
                     l_e.getErrorInfo(),
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_strErrorMessage);  
            }
            
            try
            {
				//1.1.3.(*) 空行の場合（add明細行()の戻り値 < 0）、当該要素の処理を中断（continue;）
				if (l_intNewLineNumber < 0)
				{
					continue;
				}

                //1.1.4 get部店コード(int)
                l_strBranchCode = l_uploadCsv.getBranchCode(l_intNewLineNumber);
            
				//1.1.5 get顧客コード(int)
				l_strAccountCode = l_uploadCsv.getAccountCode(l_intNewLineNumber);

				//1.1.6 get調整ポイント(int)
				l_lngAdjustPoint = l_uploadCsv.getAdjustPoint(l_intNewLineNumber);
            
                //1.1.7 validate部店コード(String)
                l_uploadCsv.validateBranchCode(l_strBranchCode);//WEB3BaseException
            
                //1.1.8 validate顧客コード(String)
                l_uploadCsv.validateAccountCode(l_strAccountCode);//WEB3BaseException
            
                //1.1.9 get顧客(String, String, String)                
                WEB3GentradeMainAccount l_mainAccount = null;
                
                try
                {                    
                    l_mainAccount = 
                        l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_strBranchCode, l_strAccountCode);//WEB3SystemLayerException
                }       
                catch(WEB3SystemLayerException l_ex)
                {
					if (WEB3ErrorCatalog.SYSTEM_ERROR_80005.error_code.equals(l_ex.getErrorInfo().error_code) ||
						WEB3ErrorCatalog.SYSTEM_ERROR_80006.error_code.equals(l_ex.getErrorInfo().error_code))
                    {
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                            getClass().getName() + STR_METHOD_NAME,
                            "該当する顧客が存在しません。");
                    }
                    else
                    {
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw l_ex;
                    }
                }
                
                String l_strAccountCodeInDb = l_mainAccount.getAccountCode();
                
                //1.1.10 validate重複顧客(int)
                l_uploadCsv.validateDuplicateAccount(l_intNewLineNumber);// WEB3BaseException
            
                //1.1.11 get利用可能ポイント(String, String, String)
                WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
                if (l_applyManager == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                long l_lngUsePossiblePoint = l_applyManager.getUsePossiblePoint(l_admin.getInstitutionCode(), l_strBranchCode, l_strAccountCodeInDb);//WEB3BaseException
            
                //1.1.12 validate調整ポイント(String, long)
                l_applyManager.validateAdjustPoint(String.valueOf(l_lngAdjustPoint), l_lngUsePossiblePoint);// WEB3BaseException
            }
            //1.1.13 (*3)
            catch (WEB3BaseException l_e)
            {
                log.debug("catch web3baseexception");
                //1.1.12.1 saveアップロードエラー(ErrorInfo)
                l_uploadCsv.saveUploadError(l_e.getErrorInfo());//WEB3SystemLayerException                

                //例外の追加文字列（WEB3BaseException.errorMessage）に
                //明細行文字列（明細行[index]）をセットする。
                String l_strErrorMessage = l_strDetailsLines[i];             
                
				//1.1.12.2 例外をスロー
				if (l_e instanceof  WEB3SystemLayerException)
				{
					throw new WEB3SystemLayerException(
						 l_e.getErrorInfo(),
						 this.getClass().getName() + STR_METHOD_NAME,
						 l_strErrorMessage);      
				}
				else if ( l_e instanceof WEB3BusinessLayerException)
				{
					throw new WEB3BusinessLayerException(
						 l_e.getErrorInfo(),
						 this.getClass().getName() + STR_METHOD_NAME,
						 l_strErrorMessage);      
				}
            }
        }        
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
