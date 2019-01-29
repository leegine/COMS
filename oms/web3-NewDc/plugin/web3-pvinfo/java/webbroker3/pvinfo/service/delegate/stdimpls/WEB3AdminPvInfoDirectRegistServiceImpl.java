head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者ダイレクト指定登録サービスImpl(WEB3AdminPvInfoDirectRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/27 李丁銀(中訊) 作成
Revesion History : 2004/11/02 奚靖(中訊) 修正
Revesion History : 2007/06/27 徐宏偉(中訊) モデル078、実装の問題002
Revesion History : 2009/07/07 武波(中訊) モデル117,118,119
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
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
import webbroker3.pvinfo.define.WEB3PvInfoUploadStateDivDef;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.message.WEB3PvInfoUploadHistoryUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者ダイレクト指定登録サービスImpl)<BR>
 * 管理者ダイレクト指定登録サービス実装クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectRegistServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoDirectRegistService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectRegistServiceImpl.class);
        
    /**
     * ダイレクト指定登録処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・ダイレクト指定登録入力リクエストの場合<BR>
     * 　@this.getダイレクト指定登録入力画面()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・ダイレクト指定登録確認リクエストの場合<BR>
     * 　@this.validateダイレクト指定登録()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・ダイレクト指定登録完了リクエストの場合<BR>
     * 　@this.submitダイレクト指定登録()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・ダイレクト指定登録アップロード中止リクエストの場合<BR>
     * 　@this.undoダイレクト指定登録アップロード()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415D40FE00EA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        //ダイレクト指定登録処理を行う。
        if(l_request instanceof WEB3AdminPvInfoDirectRegistInputRequest)
        {
            //this.getダイレクト指定登録入力画面()メソッドをコールする。
            l_response = this.getDirectRegistInputScreen((WEB3AdminPvInfoDirectRegistInputRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoDirectRegistConfirmRequest)
        {
            //this.validateダイレクト指定登録()メソッドをコールする。
            l_response = this.validateDirectRegist((WEB3AdminPvInfoDirectRegistConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoDirectRegistCompleteRequest)
        {
            //this.submitダイレクト指定登録()メソッドをコールする。
            l_response = this.submitDirectRegist((WEB3AdminPvInfoDirectRegistCompleteRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoDirectRegistCancelRequest)
        {
            //this.undoダイレクト指定登録アップロード()メソッドをコールする。
            l_response = this.undoDirectRegistUpload((WEB3AdminPvInfoDirectRegistCancelRequest)l_request);
        }
        else
        {
            String l_strErrorInfo = "パラメータの類型が不正、該当する.WEB3AdminPvInfoDirectRegistInputRequest," + 
                "WEB3AdminPvInfoDirectRegistConfirmRequest, " + "WEB3AdminPvInfoDirectRegistCompleteRequest, " +
                "WEB3AdminPvInfoDirectRegistCancelRequest類型。";
            log.error(l_strErrorInfo);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo); 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    /**
     * (getダイレクト指定登録入力画面)<BR>
     * ダイレクト指定登録入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者ダイレクト指定登録サービス)getダイレクト<BR>指定登録入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定登録入力リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputResponse
     * @@roseuid 415D419C03C7
     */
    protected WEB3AdminPvInfoDirectRegistInputResponse getDirectRegistInputScreen(WEB3AdminPvInfoDirectRegistInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDirectRegistInputScreen(WEB3AdminPvInfoDirectRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistInputResponse l_response = null;

        //1.1 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報を執行します" );
        
        //1.2  validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限を執行します" );
        
        //1.3  登録対象顧客アップロードCSV()
        WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv();
        
        //1.4 get最新アップロード履歴(long)         
        AdministratorUploadRow l_adminUploadRow = l_registTargetAccountUploadCsv.getLatestUploadAction(0);
        log.debug("get最新アップロード履歴を執行します" );
        
        //1.6 create Response()
        l_response = (WEB3AdminPvInfoDirectRegistInputResponse)l_request.createResponse();
        
                
        //1.5 アップロード履歴が存在する場合、処理実施
        if(l_adminUploadRow != null)
        {

            //1.5.1 ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細()
            WEB3PvInfoUploadHistoryUnit l_uploadHistoryUnit = new WEB3PvInfoUploadHistoryUnit();
            
            //1.5.2 (*)プロパティセット
            log.debug("アップロード終了日時" + "=" + l_adminUploadRow.getUploadEndTimestamp());
            if(l_adminUploadRow.getUploadEndTimestamp() == null)
            {
                l_uploadHistoryUnit.uploadStateDiv = WEB3PvInfoUploadStateDivDef.UPLOAD_STATUS_PROCESS;
                log.debug("アップロード処理状態区分" + "=" + l_uploadHistoryUnit.uploadStateDiv);
            }
            else
            {
                l_uploadHistoryUnit.uploadStateDiv = WEB3PvInfoUploadStateDivDef.UPLOAD_STATUS_COMPLETE;
                log.debug("アップロード処理状態区分" + "=" + l_uploadHistoryUnit.uploadStateDiv);
            }
            
            l_uploadHistoryUnit.uploadNumber = WEB3StringTypeUtility.formatNumber(l_adminUploadRow.getUploadCount());
            log.debug("アップロード件数" + "=" + l_uploadHistoryUnit.uploadNumber);
            
            l_uploadHistoryUnit.uploadStartDate = l_adminUploadRow.getUploadStartTimestamp();
            log.debug("アップロード開始日時" + "=" + l_uploadHistoryUnit.uploadStartDate);
            
            l_uploadHistoryUnit.uploadEndDate = l_adminUploadRow.getUploadEndTimestamp();
            log.debug("アップロード終了日時" + "=" + l_uploadHistoryUnit.uploadEndDate);
            
            l_uploadHistoryUnit.pvInfoErrorId = l_adminUploadRow.getMessageCode(); 
            log.debug("ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝエラー番号" + "=" + l_uploadHistoryUnit.pvInfoErrorId);
            
            //1.7 (*)プロパティセット
            //アップロード履歴が存在する場合、ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細オブジェクト。
            l_response.uploadHistoryUnit = l_uploadHistoryUnit; 
            log.debug("アップロード履歴が存在する場合、ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細オブジェクトを執行します");
        }
        else
        {
            //　@アップロード履歴が存在しない場合、null。
            l_response.uploadHistoryUnit = null;
            log.debug("アップロード履歴が存在しない場合、nullを執行します");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダイレクト指定登録)<BR>
     * ダイレクト指定登録確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者ダイレクト指定登録サービス)validateダイレクト指定登録」参照<BR>
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
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定登録確認リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmResponse
     * @@roseuid 415D419C03E7
     */
    protected WEB3AdminPvInfoDirectRegistConfirmResponse validateDirectRegist(WEB3AdminPvInfoDirectRegistConfirmRequest l_request) throws WEB3BaseException
     
    {
        final String STR_METHOD_NAME = " validateDirectRegist(WEB3AdminPvInfoDirectRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistConfirmResponse l_response = null;
        //1.1 validate()
        l_request.validate();
        log.debug(" validateを執行します");
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug(" getInstanceFromログイン情報を執行します");
        
        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限を執行します");
       
        //1.4 (*1)分岐フロー
        int l_intRowCnt = 0;
        long l_lngUploadId = 0;
        log.debug("アップロードファ@イル" + "=" + l_request.uploadFile);
        if(l_request.uploadFile != null)     
        {      
            //1.4.1 reset受付時間区分(String)                  
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD_DAYLONG);
            log.debug("reset受付時間区分を執行します");
            
            //1.4.2 reset注文受付トランザクション(String)              
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            log.debug("reset注文受付トランザクションを執行します");
            
            //1.4.3 validate注文受付可能()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("validate注文受付可能を執行します");
            
            //1.4.4 * アップロードTempテーブル登録処理
            //1.4.4.1 登録対象顧客アップロードCSV()
            WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv();
            log.debug("登録対象顧客アップロードCSVを執行します");
            
            //1.4.4.2 validate同時アップロード(long)
            l_registTargetAccountUploadCsv.validateSameTimeUpload(null);
            log.debug("validate同時アップロードを執行します");
            
            //1.4.4.3 get管理者コード()
            String l_strAdminCode = l_admin.getAdministratorCode();
            log.debug("get管理者コードを執行します");
            
            //1.4.4.4 saveアップロード開始(long, String, String, String, String)               
            l_registTargetAccountUploadCsv.saveUpLoadStart(0, null, null, null, l_strAdminCode);
            log.debug("saveアップロード開始を執行します");
            
            //1.4.4.5 (*1) リクエストデータ.アップロードファ@イル[]の各要素毎のLOOP処理
            int l_intUploadFileCnt = l_request.uploadFile.length;
            for(int i = 0; i < l_intUploadFileCnt; i++)
            {
                int l_intCnt = 0;
                try
                {
                    //1.4.4.5.1 add明細行(String)(
                    l_intCnt = l_registTargetAccountUploadCsv.addRow(l_request.uploadFile[i]);
                    log.debug(" add明細行を執行します");
                }
                //1.4.4.5.2 (*1.1) add明細行()で例外がスローされた場合
                catch (WEB3SystemLayerException l_ex)
                {             
                    //1.4.4.5.2.1 saveアップロードエラー(ErrorInfo)                  
                    l_registTargetAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());
                        
                    //1.4.4.5.2.2 throw （例外）
                    log.error(STR_METHOD_NAME + l_ex.getErrorInfo().error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
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
                    //1.4.4.5.3 validate明細行(long)          
                    l_registTargetAccountUploadCsv.validateDetailsLine(l_intCnt);
                    log.debug(" validate明細行を執行します");
                }
                catch (WEB3BaseException l_ex)
                {
                    //1.4.4.5.5.1 saveアップロードエラー(ErrorInfo)
                    l_registTargetAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());
                    
                    //1.4.4.5.5.2 throw(例外）
                    log.error(getClass().getName() + "." + STR_METHOD_NAME 
                        + l_registTargetAccountUploadCsv.getBranchCode(l_intCnt) +  "," 
                        + l_registTargetAccountUploadCsv.getAccountCode(l_intCnt) );
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00856,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_registTargetAccountUploadCsv.getBranchCode(l_intCnt) + ","
                            + l_registTargetAccountUploadCsv.getAccountCode(l_intCnt));
                }

            }
            //1.4.4.6 get明細行数()
            l_intRowCnt = l_registTargetAccountUploadCsv.getRowCount();
            log.debug("get明細行数" + "=" + l_intRowCnt);
            
            //1.4.4.7 getアップロードＩＤ()
            l_lngUploadId = l_registTargetAccountUploadCsv.getAdministratorUploadId();
            log.debug("getアップロードＩＤ" + "=" + l_lngUploadId);
            
            //1.4.4.8 saveアップロードTemp()
            l_registTargetAccountUploadCsv.saveUploadTemp();
            log.debug(" saveアップロードTempを執行します");
        }
        //1.5 (*2)分岐フロー,アップロードなしの場合
        else
        {
            try
            {
                
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_gentradeAccountManager = 
                                      (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                //1.5.1  get部店(String, String)                   
                String l_strInstitutionCode = l_admin.getInstitutionCode();
                log.debug(" get証券会社コード" + "=" + l_strInstitutionCode);
                
                WEB3GentradeBranch l_branch = l_gentradeAccountManager.getWeb3GenBranch(l_strInstitutionCode, l_request.branchCode);
                log.debug(" get部店" + "=" + l_branch);
                
                if (l_branch == null)
                {
                    log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
               
                //1.5.2 get顧客(String, String)
                String l_strCode = l_branch.getInstitution().getInstitutionCode();
                log.debug("証券会社コード" + "=" + l_strCode);
                
                String l_strBranchCode = l_branch.getBranchCode();
                log.debug("部店コード" + "=" + l_strBranchCode);
                
                MainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(l_strCode, l_strBranchCode, l_request.accountCode);
                log.debug(" get顧客" + "=" + l_mainAccount);
                
                if(l_mainAccount == null)
                {
                    log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                        getClass().getName() + "." + STR_METHOD_NAME);
                } 
            }
            catch (NotFoundException l_ex)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);  
            }
            catch(WEB3SystemLayerException l_ex)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);  
            }
        }
          
        //1.6 get新規表示内容ID()
        WEB3PvInfoDataManager l_pvInfoDataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        long l_lntNewDisplayContentsId = l_pvInfoDataManager.getNewDisplayContentsId();
        log.debug("get新規表示内容ID" + "=" + l_lntNewDisplayContentsId);
        
        //1.7 createResponse()
        l_response = (WEB3AdminPvInfoDirectRegistConfirmResponse)l_request.createResponse();
        
        //1.8 (*) プロパティセット
        l_response.displayContentsId = WEB3StringTypeUtility.formatNumber(l_lntNewDisplayContentsId);
        log.debug("表示内容ID" + "=" + l_response.displayContentsId);
        log.debug("アップロードファ@イル" + "=" + l_request.uploadFile);
        if(l_request.uploadFile == null)
        {
            l_response.uploadNumber = null;
            log.debug("アップロード件数" + "=" + l_response.uploadNumber);
            
            l_response.uploadId = null;
            log.debug("アップロードＩＤ" + "=" + l_response.uploadId);
        }
        else
        {
            l_response.uploadNumber = WEB3StringTypeUtility.formatNumber(l_intRowCnt);
            log.debug("アップロード件数" + "=" + l_response.uploadNumber);
            
            l_response.uploadId = WEB3StringTypeUtility.formatNumber(l_lngUploadId);
            log.debug("アップロードＩＤ" + "=" + l_response.uploadId);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submitダイレクト指定登録)<BR>
     * ダイレクト指定登録完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者ダイレクト指定登録サービス)submitダイレクト指定登録」参照<BR>
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
     * ==========================================================<BR>
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
     * 管理者・ダイレクト指定登録完了リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteResponse
     * @@roseuid 415D419D000E
     */
    protected WEB3AdminPvInfoDirectRegistCompleteResponse submitDirectRegist(WEB3AdminPvInfoDirectRegistCompleteRequest l_request) 
        throws WEB3BaseException, WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = " submitDirectRegist(WEB3AdminPvInfoDirectRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistCompleteResponse l_response = null;
        
        //1.1 validate()
        l_request.validate();
        log.debug(" validateを執行します");
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug(" getInstanceFromログイン情報を執行します");
        
        //1.3  validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限を執行します");
        
        //1.4 validate取引パスワード(String)          
        l_admin.validateTradingPassword(l_request.password);
        log.debug(" validate取引パスワードを執行します");
        
        WEB3PvInfoDataManager l_pvInfoDataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        try
        {
            //管理者ダイレクト指定登録TransactionCallback(管理者 : 管理者, 表示内容情報 : 表示内容情報)
            WEB3AdminPvInfoDirectRegistTransactionCallback l_transactionCallback =
                new WEB3AdminPvInfoDirectRegistTransactionCallback(
                    l_admin, l_request.displayContentsUnit);

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

        //1.6 (*1)分岐フロー
        String l_strInstiCode = l_admin.getInstitutionCode();
        log.debug("証券会社コード" + "=" + l_strInstiCode);
        log.debug("アップロードID" + "=" + l_request.uploadId);
        if(l_request.uploadId != null)
        {
            //1.6.1 reset受付時間区分(String)
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD_DAYLONG);
            log.debug("  reset受付時間区分を執行します");
            
            //1.6.2 reset注文受付トランザクション(String)
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            log.debug(" reset注文受付トランザクションを執行します");
            
            //1.6.3 validate注文受付可能()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug(" validate注文受付可能を執行します");
            
            //1.6.4  ( * CSV一括顧客登録処理)
            //1.6.4.1 登録対象顧客アップロードCSV(long)
            long l_lngUploadId = Long.parseLong(l_request.uploadId);
            WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv(l_lngUploadId);
            log.debug(" 登録対象顧客アップロードCSVを執行します");
    
            //1.6.4.2 validate同時アップロード(long)
            l_registTargetAccountUploadCsv.validateSameTimeUpload(new Long(l_request.uploadId));
            log.debug(" validate同時アップロードを執行します");
    
            //1.6.4.3 setDataFromアップロードTemp(long)
            l_registTargetAccountUploadCsv.setDataFromUploadTemp(l_lngUploadId);
            log.debug(" setDataFromアップロードTempを執行します");
            
            //1.6.4.4 get明細行数()
            int l_intRegistTargetAccountUploadCsvCnt = l_registTargetAccountUploadCsv.getRowCount();
            log.debug("get明細行数" + "=" + l_intRegistTargetAccountUploadCsvCnt);

            //(*1)インスタンスを生成
            String l_strAccountAndBranchCode = null;

            //1.6.4.5 (*1) 明細行の数分LOOP処理
            for(int i = 0; i < l_intRegistTargetAccountUploadCsvCnt; i++)
            {
                //1.6.4.5.1 get部店コード(int)
                String l_strBranchCode = l_registTargetAccountUploadCsv.getBranchCode(i);
                log.debug("部店コード" + "=" + l_strBranchCode);
                
                //1.6.4.5.2 get顧客コード(int)
                String l_strAccountCode = l_registTargetAccountUploadCsv.getAccountCode(i);
                log.debug("顧客コード" + "=" + l_strAccountCode);

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
                            log.debug("重複顧客チェックエラー（同じ値の行が存在する）。");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_strBranchCode + "," + l_strAccountCode);
                        }
                    }

                    //1.6.4.5.3 insert閲覧履歴(String, String, String, long, boolean)               
                    l_pvInfoDataManager.insertBrowseHistory(l_strInstiCode, l_strBranchCode,
                        l_strAccountCode, Long.parseLong(l_request.displayContentsUnit.displayContentsId), false);
                    log.debug(" insert閲覧履歴を執行します");      
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
            
            //1.6.4.6  saveアップロード終了()
            l_registTargetAccountUploadCsv.saveUploadEnd();
            log.debug(" saveアップロード終了を執行します"); 
            
            //1.6.4.7 deleteアップロードTemp()
            l_registTargetAccountUploadCsv.deleteUploadTemp();
            log.debug(" deleteアップロードTempを執行します"); 
        }
        //1.7 (*2)分岐フロー
        else
        {
            try
            {
                //1.7.1 get部店(String, String)
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
                WEB3GentradeBranch l_branch = l_gentradeAccountManager.getWeb3GenBranch(l_strInstiCode, l_request.branchCode);
                log.debug("get部店 =" + l_branch);
                if (l_branch == null)
                 {
                     log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                     log.exiting(STR_METHOD_NAME);
                     throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                         getClass().getName() + "." + STR_METHOD_NAME);
                 }
                
                //1.7.2 get顧客(String, String)
                String l_strCode = l_branch.getInstitution().getInstitutionCode();
                log.debug("証券会社コード =" + l_strCode);
                
                String l_strBranchCode = l_branch.getBranchCode();
                log.debug("部店コード =" + l_strCode);
                
                MainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(l_strCode, l_strBranchCode, l_request.accountCode);
                log.debug("get顧客 =" + l_mainAccount);
                if(l_mainAccount == null)
                {
                    log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex); 
            }
            catch (WEB3SystemLayerException l_ex)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex); 
            }
            
            //1.7.3 insert閲覧履歴(String, String, String, long, boolean)
            l_pvInfoDataManager.insertBrowseHistory(l_strInstiCode, l_request.branchCode,
                 l_request.accountCode, Long.parseLong(l_request.displayContentsUnit.displayContentsId), false);
            
            log.debug("insert閲覧履歴 を執行します");               
        }
        //1.8 createResponse()
        l_response = (WEB3AdminPvInfoDirectRegistCompleteResponse)l_request.createResponse();
     
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undoダイレクト指定登録アップロード)<BR>
     * ダイレクト指定登録アップロード中止処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者ダイレクト指定登録サービス)undoダイレクト指定登録<BR>アップロード」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定登録アップロード中止リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelResponse
     * @@roseuid 415D419D002E
     */
    protected WEB3AdminPvInfoDirectRegistCancelResponse undoDirectRegistUpload(WEB3AdminPvInfoDirectRegistCancelRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " undoDirectRegistUpload(WEB3AdminPvInfoDirectRegistCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistCancelResponse l_response = null;
        
        //1.1 validate()
        l_request.validate();
        log.debug("validateを執行します");
        
        //1.2 登録対象顧客アップロードCSV(long)
        WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv(Long.parseLong(l_request.uploadId));
        log.debug("登録対象顧客アップロードCSVを執行します");
        
        //1.3 deleteアップロードTemp()
        l_registTargetAccountUploadCsv.deleteUploadTemp();
        log.debug("deleteアップロードTempを執行します");
        
        //1.4 saveアップロード中止()
        l_registTargetAccountUploadCsv.saveUploadStop();
        log.debug("saveアップロード中止を執行します");
        
        //1.5 createResponse()
        l_response = (WEB3AdminPvInfoDirectRegistCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (管理者ダイレクト指定登録TransactionCallback)<BR>
     * 管理者ダイレクト指定登録TransactionCallbackクラス<BR>
     */
    public class WEB3AdminPvInfoDirectRegistTransactionCallback implements TransactionCallback
    {
        /**
         * ログユーティリティ
         */
        private  WEB3LogUtility l_log =
            WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectRegistTransactionCallback.class);

        /**
         * (管理者)<BR>
         * 管理者<BR>
         */
        private WEB3Administrator administrator;

        /**
         * (表示内容情報)<BR>
         * 表示内容情報<BR>
         */
        private WEB3PvInfoDisplayContentsUnit displayContentsUnit;

        /**
         * (管理者ダイレクト指定登録TransactionCallback )<BR>
         * コンストラクタ<BR>
         * <BR>
         * 引数.管理者と引数.表示内容情報を該当の変数に保存する。<BR>
         * @@param l_administrator - (管理者)<BR>
         * 管理者<BR>
         * @@param l_displayContentsUnit - (表示内容情報)<BR>
         * 表示内容情報<BR>
         */
        public WEB3AdminPvInfoDirectRegistTransactionCallback(
            WEB3Administrator l_administrator,
            WEB3PvInfoDisplayContentsUnit l_displayContentsUnit)
        {
            final String STR_METHOD_NAME = "WEB3AdminPvInfoDirectRegistTransactionCallback("
                + "WEB3Administrator, WEB3PvInfoDisplayContentsUnit)";
            log.entering(STR_METHOD_NAME);

            //引数.管理者と引数.表示内容情報を該当の変数に保存する。
            this.administrator = l_administrator;
            this.displayContentsUnit = l_displayContentsUnit;

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * 表示内容登録処理を行う。<BR>
         * <BR>
         * １）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.insert表示内容()をコールする。<BR>
         * <BR>
         * [引数]<BR>
         * 管理者：　@this.管理者<BR>
         * 表示内容情報： this.表示内容情報<BR>
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
                //１）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.insert表示内容()をコールする。
                WEB3PvInfoDataManager l_pvInfoDataManager =
                    (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
                l_pvInfoDataManager.insertDisplayContents(
                    this.administrator,
                    this.displayContentsUnit);
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
