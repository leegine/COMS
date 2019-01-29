head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽImpl(WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/17カク寛新 (中訊) 新規作成
                   2005/12/26 鄭徳懇 (中訊) 仕様変更No.074
                   2006/02/03 王維（日本中訊）仕様変更No.085
                   2006/02/08 呉艶飛 (中訊) ＤＢレイアウトNo.012
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoExclusiveTransferAccountUploadCsv;
import webbroker3.accountinfo.define.WEB3AccInfoUploadStateDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoUploadHistoryUnit;
import webbroker3.accountinfo.message.WEB3AdminAccInfoErrorAccount;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoExclusiveTransferAccountUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.ExclusiveUseAccountDao;
import webbroker3.gentrade.data.ExclusiveUseAccountParams;
import webbroker3.gentrade.data.FinInstitutionBankDao;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽImpl)<BR>
 * 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ実装クラス<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl
    extends WEB3AccInfoClientRequestService
    implements WEB3AdminAccInfoExclusiveTransferAccountUploadService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl.class);

    /**
     * @@roseuid 418F3A090148
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl()
    {

    }

    /**
     * 専用振込先口座アップロード処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報専用振込先口座<BR>
     * ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報専用振込先口座<BR>
     * ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄの場合 <BR>
     * 　@－validateアップロードファ@イル()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報専用振込先口座<BR>
     * ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄの場合 <BR>
     * 　@－submitアップロードファ@イル()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報専用振込先口座<BR>
     * ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄの場合 <BR>
     * 　@－undoアップロード()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415BC20B0266
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        //引数のリクエストデータが、管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄの場合
        if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest)
        {
            
            l_response = this.getUploadScreen((WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest) l_request);
        }
        
        //引数のリクエストデータが、管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄの場合 
        else if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest)
        {
            
            l_response = this.validateUploadFile((WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest) l_request);
        }
        
        //引数のリクエストデータが、管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄの場合  
        else if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest)
        {
            
            l_response = this.submitUploadFile((WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest) l_request);
        }
        
        //引数のリクエストデータが、管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄの場合 
        else if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest)
        {
            
            l_response = this.undoUpload((WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest) l_request);
        }
        else
        {
            
            log.exiting(STR_METHOD_NAME);
            log.error(" パラメータタイプ不正。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getアップロード画面)<BR>
     * 専用振込先口座アップロード画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（専用振込先口座ＵＬ）getアップロード画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse
     * @@roseuid 415BC288010E
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse getUploadScreen(WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = 
            " getUploadScreen(WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.2) validate権限(String, boolean),validate注文受付可能()
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3) 専用振込先口座アップロードCSV( )
        WEB3AdminAccInfoExclusiveTransferAccountUploadCsv l_eclusiveTransferAccountUploadCsv =
            new WEB3AdminAccInfoExclusiveTransferAccountUploadCsv();

        //1.4) get最新アップロード履歴(long) 
        AdministratorUploadRow l_administratorUploadRow = l_eclusiveTransferAccountUploadCsv.getLatestUploadAction(0);

        //1.5)  アップロード履歴が存在する場合、処理実施
        //1.6) 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ(WEB3GenRequest)
        WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse l_response = 
            (WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse) l_request.createResponse();
        if (l_administratorUploadRow != null)
        {
            
            //1.5.1) お客様情報アップロード履歴明細( )
            WEB3AccInfoUploadHistoryUnit l_accInfoUploadHistoryUnit = new WEB3AccInfoUploadHistoryUnit();

            //1.5.2) プロパティセット
            //　@アップロード処理状態区分
            //（アップロード行.アップロード終了日時 == null）の場合、”アップロード中
            if (l_administratorUploadRow.getUploadEndTimestamp() == null)
            {
                
                l_accInfoUploadHistoryUnit.uploadStateDiv = WEB3AccInfoUploadStateDivDef.UPOAD_CONFIRMING;
            }
            
            //（アップロード行.アップロード終了日時 != null）の場合、”アップロード済”
            else
            {
                
                l_accInfoUploadHistoryUnit.uploadStateDiv = WEB3AccInfoUploadStateDivDef.UPLOAD_COMPLETE;
            }
            
            //アップロード件数：　@アップロード行.アップロード件数
            l_accInfoUploadHistoryUnit.uploadNumber = "" + l_administratorUploadRow.getUploadCount();
            
            //アップロード開始日時：　@アップロード行.アップロード開始日時 
            l_accInfoUploadHistoryUnit.uploadStartDate = l_administratorUploadRow.getUploadStartTimestamp();
            
            //アップロード終了日時：　@アップロード行.アップロード終了日時 
            l_accInfoUploadHistoryUnit.uploadEndDate = l_administratorUploadRow.getUploadEndTimestamp();
            
            //お客様情報エラー番号：　@アップロード行.メッセージコード
            l_accInfoUploadHistoryUnit.accInfoErrorId = l_administratorUploadRow.getMessageCode();
            
            //1.7) プロパティセット
            //　@アップロード履歴一覧：　@
            //アップロード履歴が存在する場合、お客様情報アップロード履歴明細オブジェクト。            
            l_response.uploadHistoryList = l_accInfoUploadHistoryUnit;
        }
        
        //アップロード履歴が存在しない場合、null。
        else
        {
            
            l_response.uploadHistoryList = null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validateアップロードファ@イル)<BR>
     * 専用振込先口座アップロード確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（専用振込先口座ＵＬ）validateアップロードファ@イル」参照。 <BR> 
     * @@param l_request - 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse
     * @@roseuid 415BC2AC01AB
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse validateUploadFile(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME =
            " validateUploadFile(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3)validate権限(String, boolean),validate注文受付可能()
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4) 専用振込先口座アップロードCSV()
        WEB3AdminAccInfoExclusiveTransferAccountUploadCsv l_eclusiveTransferAccountUploadCsv =
            new WEB3AdminAccInfoExclusiveTransferAccountUploadCsv();

        //1.5) validate同時アップロード(long)
        l_eclusiveTransferAccountUploadCsv.validateSameTimeUpload(null);

        //1.6) get管理者コード( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //1.7) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.8)ArrayList()
        ArrayList l_array = new ArrayList();

        //1.9) saveアップロード開始(long, String, String, String, String)
        l_eclusiveTransferAccountUploadCsv.saveUpLoadStart(0, null, null, null, l_strAdministratorCode);

        //1.10) リクエストデータ.アップロードファ@イル[]の各要素毎のLOOP
        for (int i = 0; i < l_request.uploadFile.length; i++)
        {
            
            //1.10.1) add明細行(String)
            //1.10.2) add明細行()で例外がスローされた場合
            int l_intAddRow = 0;
            try
            {
                
                l_intAddRow = l_eclusiveTransferAccountUploadCsv.addRow(l_request.uploadFile[i]);
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.exiting(STR_METHOD_NAME);
                //1.10.2.1) saveアップロードエラー(ErrorInfo)
                l_eclusiveTransferAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());
                
                //1.10.2.2) throw （例外）
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //空行の場合（add明細行()の戻り値 < 0）、当該要素の処理を中断（continue;）
            if (l_intAddRow < 0)
            {
                continue;
            }

            //1.10.3) validate明細行(long, String)
            //1.10.4) validate重複顧客(long)
            //1.10.5) validate明細行()，validate重複顧客()にて例外がスローされた場合
            try
            {
                l_eclusiveTransferAccountUploadCsv.validateDetailsLine(l_intAddRow, l_strInstitutionCode);
                l_eclusiveTransferAccountUploadCsv.validateDuplicateAccount(l_intAddRow);
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.exiting(STR_METHOD_NAME);
                //1.10.5.1) saveアップロードエラー(ErrorInfo)
                l_eclusiveTransferAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());
                
                //1.10.5.2) throw （例外）
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage() + l_eclusiveTransferAccountUploadCsv.getBranchCode() + "." + l_eclusiveTransferAccountUploadCsv.getAccountCode(l_intAddRow),
                    l_ex);
            }
            
            try
            {
                //行番号：　@（add明細行()の戻り値）
                //証券会社コード：　@管理者.get証券会社コード()
                l_eclusiveTransferAccountUploadCsv.getMainAccount(l_intAddRow,l_administrator.getInstitutionCode());
            }
            catch (WEB3BaseException l_ex)
            {
                //1.10.7.1管理者お客様情報エラー顧客( )
                WEB3AdminAccInfoErrorAccount l_accInfoErrorAccount = new WEB3AdminAccInfoErrorAccount();
                //1.10.7.2get部店コード(int)
                String l_strBranchCode = l_eclusiveTransferAccountUploadCsv.getBranchCode(l_intAddRow);
                //1.10.7.3get顧客コード(int)
                String l_strAccountCode = l_eclusiveTransferAccountUploadCsv.getAccountCode(l_intAddRow);
                //1.10.7.4
                //プロパティセット

                //管理者お客様情報エラー顧客インスタンスに値をセットする。

                //部店コード：　@get部店コード()
                //顧客コード：　@get顧客コード()
                l_accInfoErrorAccount.accountCode = l_strAccountCode;
                l_accInfoErrorAccount.branchCode = l_strBranchCode;
                //1.10.7.5
                l_array.add(l_accInfoErrorAccount);
                //1.10.7.6
                l_eclusiveTransferAccountUploadCsv.deleteRow(l_intAddRow);
                
            }
        }
        //1.11toArray( )
        WEB3AdminAccInfoErrorAccount[] l_adminAccInfoErrorAccount = new WEB3AdminAccInfoErrorAccount[l_array.size()];
        l_array.toArray(l_adminAccInfoErrorAccount);

        //1.12) get明細行数( )
        int l_intRowCount = l_eclusiveTransferAccountUploadCsv.getRowCount();

        //1.13) getアップロードＩＤ( )
        long l_lngAdministratorUploadId = l_eclusiveTransferAccountUploadCsv.getAdministratorUploadId();

        //1.14) saveアップロードTemp( )
        l_eclusiveTransferAccountUploadCsv.saveUploadTemp();

        //1.15) createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse) l_request.createResponse();
            
        //1.16) プロパティセット
        //アップロード件数：　@getアップロード件数()
        l_response.uploadNumber = "" + l_intRowCount;
        
        //アップロードＩＤ：　@getアップロードＩＤ()
        l_response.uploadID = "" + l_lngAdministratorUploadId;
        
        //　@エラー顧客一覧：　@エラー顧客List.toArray()
        l_response.errorAccountList = l_adminAccInfoErrorAccount;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitアップロードファ@イル)<BR>
     * 専用振込先口座アップロード完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（専用振込先口座ＵＬ）submitアップロードファ@イル」参照。 <BR>
     *  ========================================================<BR>
     * シーケンス図「お客様情報（専用振込先口座ＵＬ）submitアップロードファ@イル」参照。<BR>
     *  1.10.11.1.isExist金融機@関(String, String)<BR>
     * 　@　@存在しない場合は、「銀行データ不整合」エラーをスローする。
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_01314<BR> 
     *  ========================================================<BR>
     * @@param l_request - 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse
     * @@roseuid 415BC2DE00E0
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse submitUploadFile(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " submitUploadFile(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();

        //1.2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3)validate権限(String, boolean),validate注文受付可能()
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4) validate取引パスワード(String)
        l_administrator.validateTradingPassword(l_request.password);

        //1.5) 専用振込先口座アップロードCSV(long)
        long l_lngUploadId = Long.parseLong(l_request.uploadID);
        WEB3AdminAccInfoExclusiveTransferAccountUploadCsv l_eclusiveTransferAccountUploadCsv =
            new WEB3AdminAccInfoExclusiveTransferAccountUploadCsv(l_lngUploadId);

        //1.6) validate同時アップロード(long)
        l_eclusiveTransferAccountUploadCsv.validateSameTimeUpload(new Long(l_lngUploadId));

        //1.7) setDataFromアップロードTemp(long)
        l_eclusiveTransferAccountUploadCsv.setDataFromUploadTemp(l_lngUploadId);

        //1.8) get明細行数( )
        int l_intRowCount = l_eclusiveTransferAccountUploadCsv.getRowCount();

        //1.9) 明細行の数分LOOP処理
        for (int i = 0; i < l_intRowCount; i++)
        {
            
            //1.9.1) get部店コード(int)
            String l_strBranchCode = l_eclusiveTransferAccountUploadCsv.getBranchCode(i);

            //1.9.2) get顧客(int, String)
            WEB3GentradeMainAccount l_mainAccount =
                l_eclusiveTransferAccountUploadCsv.getMainAccount(i, l_administrator.getInstitutionCode());

            //1.9.3) getAccountId( )
            long l_lngAccountId = l_mainAccount.getAccountId();

            // 1.9.4) get銀行名(int)
            String l_strFinInstitutionName = l_eclusiveTransferAccountUploadCsv.getFinInstitutionName(i);

            //1.9.5) get支店名(int)
            String l_strFinBranchName = l_eclusiveTransferAccountUploadCsv.getFinBranchName(i);

            //1.9.6) get支店コード(int)
            String l_strFinBranchCode = l_eclusiveTransferAccountUploadCsv.getFinBranchCode(i);

            //1.9.7) get口座種類名(int)
            String l_strFinAccountTypeName = l_eclusiveTransferAccountUploadCsv.getFinAccountTypeName(i);

            //1.9.8) get口座番号(int)
            String l_strFinAccountNo = l_eclusiveTransferAccountUploadCsv.getFinAccountNo(i);

            //1.9.9) get口座名義人(int)
            String l_strFinAccountName = l_eclusiveTransferAccountUploadCsv.getFinAccountName(i);

            //1.10.10 get銀行コード(int)
            String l_strFinInstitutionCode = l_eclusiveTransferAccountUploadCsv.getFinInstitutionCode(i);
            
            if (l_strFinInstitutionCode == null || l_strFinInstitutionCode.length() == 0)
            {
				log.debug("銀行コードが未入力です。");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02346, 
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"銀行コードが未入力です。");
            }
            
            //1.10.11 isExist金融機@関(String, String)
            //存在しない場合は、「銀行データ不整合」エラーをスローする。
            if (!isExistFinInstitution(l_strFinInstitutionCode, l_strFinBranchCode))
            {
                log.debug("金融機@関が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01314,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "金融機@関が存在しない。");
            }
            
            //1.10.12) isExist専用振込先口座(long)
            try
            {
                
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();
                
                if (this.isExistExclusiveTransferAccount(l_lngAccountId))
                {
                    
                    ExclusiveUseAccountParams l_exclusiveUseAccountParams =
                        new ExclusiveUseAccountParams(
                            ExclusiveUseAccountDao.findRowByPk(l_lngAccountId));

                    //1.10.13) doUpdateQuery(Row)
                    l_exclusiveUseAccountParams.setAccountId(l_lngAccountId);
                    l_exclusiveUseAccountParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
                    l_exclusiveUseAccountParams.setBranchCode(l_strBranchCode);
                    l_exclusiveUseAccountParams.setAccountCode(l_mainAccount.getAccountCode());
                    l_exclusiveUseAccountParams.setFinInstitutionName(l_strFinInstitutionName);
                    l_exclusiveUseAccountParams.setFinBranchName(l_strFinBranchName);
                    l_exclusiveUseAccountParams.setFinBranchCode(l_strFinBranchCode);
                    l_exclusiveUseAccountParams.setFinAccountTypeName(l_strFinAccountTypeName);
                    l_exclusiveUseAccountParams.setFinAccountNo(l_strFinAccountNo);
                    l_exclusiveUseAccountParams.setFinAccountName(l_strFinAccountName);
                    l_exclusiveUseAccountParams.setLastUpdater(l_administrator.getAdministratorCode());
                    l_exclusiveUseAccountParams.setLastUpdatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setFinInstitutionCode(l_strFinInstitutionCode);
                    l_queryProcessor.doUpdateQuery(l_exclusiveUseAccountParams);
                }
                else
                {
                    
                    ExclusiveUseAccountParams l_exclusiveUseAccountParams = new ExclusiveUseAccountParams();
                    
                    //1.10.14) doInsertQuery(Row) 
                    l_exclusiveUseAccountParams.setAccountId(l_lngAccountId);
                    l_exclusiveUseAccountParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
                    l_exclusiveUseAccountParams.setBranchCode(l_strBranchCode);
                    l_exclusiveUseAccountParams.setAccountCode(l_mainAccount.getAccountCode());
                    l_exclusiveUseAccountParams.setFinInstitutionName(l_strFinInstitutionName);
                    l_exclusiveUseAccountParams.setFinBranchName(l_strFinBranchName);
                    l_exclusiveUseAccountParams.setFinBranchCode(l_strFinBranchCode);
                    l_exclusiveUseAccountParams.setFinAccountTypeName(l_strFinAccountTypeName);
                    l_exclusiveUseAccountParams.setFinAccountNo(l_strFinAccountNo);
                    l_exclusiveUseAccountParams.setFinAccountName(l_strFinAccountName);
                    l_exclusiveUseAccountParams.setLastUpdater(l_administrator.getAdministratorCode());
                    l_exclusiveUseAccountParams.setCreatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setLastUpdatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setFinInstitutionCode(l_strFinInstitutionCode);
                    l_queryProcessor.doInsertQuery(l_exclusiveUseAccountParams);
                }
            }
            catch (DataFindException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //1.11) saveアップロード終了( )
        l_eclusiveTransferAccountUploadCsv.saveUploadEnd();

        //1.12) deleteアップロードTemp()
        l_eclusiveTransferAccountUploadCsv.deleteUploadTemp();

        //1.13) createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undoアップロード)<BR>
     * 専用振込先口座アップロード中止処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（専用振込先口座ＵＬ）undoアップロードファ@イル」参照。 <BR>
     * @@param l_request - 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse
     * @@roseuid 415BC2F80208
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse undoUpload(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = 
            " undoUpload(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        // 専用振込先口座アップロードCSV(long)
        WEB3AdminAccInfoExclusiveTransferAccountUploadCsv l_eclusiveTransferAccountUploadCsv =
            new WEB3AdminAccInfoExclusiveTransferAccountUploadCsv(Long.parseLong(l_request.uploadID));

        //deleteアップロードTemp( )
        l_eclusiveTransferAccountUploadCsv.deleteUploadTemp();

        //saveアップロード中止( )
        l_eclusiveTransferAccountUploadCsv.saveUploadStop();

        //createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (isExist専用振込先口座)<BR>
     * 専用振込先口座テーブルに既存行が存在するかを判定する。<BR>
     * <BR>
     * １）　@専用振込先口座テーブルより、指定の口座IDに該当する行を取得する。<BR>
     * ２）　@行が取得できればtrue、取得できなければfalseを返却する。<BR>
     * @@param l_lngAccountId - 口座ID
     * @@return boolean
     * @@roseuid 4161143E01BB
     */
    protected boolean isExistExclusiveTransferAccount(long l_lngAccountId) throws WEB3SystemLayerException
    {
        
        final String STR_METHOD_NAME = " isExistExclusiveTransferAccount(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            ExclusiveUseAccountDao.findRowByPk(l_lngAccountId);
        }
        catch (DataFindException l_ex)
        {
            
            return false;
        }
        catch (DataNetworkException l_ex)
        {
            
            log.error(" 予期しないシステムエラーが発生しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            
            log.error(" 予期しないシステムエラーが発生しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (isExist金融機@関)<BR>
     * 金融機@関テーブルに既存行が存在するかを判定する。<BR>
     * <BR>
     * １）　@金融機@関（銀行）マスタより、指定の銀行コードと支店コードに <BR>
     * 　@　@　@該当する行を取得する。 <BR>
     * ２）　@行が取得できればtrue、取得できなければfalseを返却する。<BR>
     * @@param l_strFinInstitutionCode - 銀行コード
     * @@param l_strBranchCode - 支店コード
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isExistFinInstitution(String l_strFinInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isExistFinInstitution(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@金融機@関（銀行）マスタより、指定の銀行コードと支店コードに該当する行を取得する。
        FinInstitutionBankRow l_finInstitutionBankRow = null;
        try
        {
            l_finInstitutionBankRow = FinInstitutionBankDao.findRowByPk(
                l_strFinInstitutionCode,
                l_strBranchCode);
            
            //２）　@行が取得できればtrue、取得できなければfalseを返却する。
            if (l_finInstitutionBankRow != null) 
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataFindException l_ex)
        {
            //例外をスロー
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataException l_ex)
        {
            //例外をスロー
            log.debug("DBへのアクセスに失敗しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DBへのアクセスに失敗しました。");
        }
    }
}
@
