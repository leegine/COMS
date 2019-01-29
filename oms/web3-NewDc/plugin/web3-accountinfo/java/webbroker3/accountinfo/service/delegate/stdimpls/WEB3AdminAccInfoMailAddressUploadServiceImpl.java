head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレスアップロードサービスImpl(WEB3AdminAccInfoMailAddressUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 呉艶飛 (中訊) 新規作成
                   2006/03/24 呉艶飛 (中訊) モデルNo.102  
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoMailAddressUploadCsv;
import webbroker3.accountinfo.define.WEB3AccInfoDeleteDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoMailFlagDef;
import webbroker3.accountinfo.define.WEB3AccInfoUploadStateDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoUploadHistoryUnit;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCancelRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCancelResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報メールアドレスアップロードサービスImpl)<BR>
 *  管理者お客様情報メールアドレスアップロードサービス実装クラス<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoMailAddressUploadServiceImpl 
    extends WEB3AccInfoClientRequestService 
    implements WEB3AdminAccInfoMailAddressUploadService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        	WEB3AdminAccInfoMailAddressUploadServiceImpl.class);
	
    public WEB3AdminAccInfoMailAddressUploadServiceImpl()
    {

    }
    
    /**
     * メールアドレスアップロード処理を行う。<BR> 
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、メールアドレスｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄの場合<BR> 
     * 　@－getアップロード画面()をコールする。<BR>  
     * <BR>
     * ○ 引数のリクエストデータが、メールアドレスｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄの場合<BR>
     * 　@－validateアップロードファ@イル()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、メールアドレスｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄの場合<BR>
     * 　@－submitアップロードファ@イル()をコールする。<BR> 
     * <BR>
     * ○ 引数のリクエストデータが、メールアドレスｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄの場合<BR>  
     *　@－undoアップロード()をコールする。<BR>   
     *<BR>
     *※サービスメソッドにて例外が発生した場合は、 <BR>
     *　@例外オブジェクトの追加文字列（WEB3BaseException.errorMessage）<BR> 
     * をレスポンスデータ.errorMessageにセットする。<BR> 
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        //○ 引数のリクエストデータが、メールアドレスｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄの場合
        //－getアップロード画面()をコールする。
        if (l_request instanceof WEB3AdminAccInfoMailAddressUploadInputRequest)
        {
            l_response = 
                this.getUploadScreen(
                    (WEB3AdminAccInfoMailAddressUploadInputRequest)l_request);
        }
        
		//○ 引数のリクエストデータが、メールアドレスｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄの場合
		//－validateアップロードファ@イル()をコールする。
        else if (l_request instanceof WEB3AdminAccInfoMailAddressUploadConfirmRequest)
        {
            l_response = 
                this.validateUploadFile(
                    (WEB3AdminAccInfoMailAddressUploadConfirmRequest)l_request);
        }
        
        // ○ 引数のリクエストデータが、メールアドレスｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄの場合
        //－submitアップロードファ@イル()をコールする。
        else if (l_request instanceof WEB3AdminAccInfoMailAddressUploadCompleteRequest)
        {
            l_response = 
                this.submitUploadFile(
                    (WEB3AdminAccInfoMailAddressUploadCompleteRequest)l_request);
        }
        
        //○ 引数のリクエストデータが、メールアドレスｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄの場合
        //－undoアップロード()をコールする。
        else if (l_request instanceof WEB3AdminAccInfoMailAddressUploadCancelRequest)
        {
            l_response = 
                this.undoUpload(
                    (WEB3AdminAccInfoMailAddressUploadCancelRequest)l_request);
        }
        else
        {
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
     * メールアドレスアップロード画面表示処理を行う。<BR> 
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（メールアドレスＵＬ）getアップロード画面」参照。 <BR>
     * <BR>
     * @@param l_request - 管理者お客様情報メールアドレスアップロード入力リクエスト<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminAccInfoMailAddressUploadInputResponse<BR>
     */
    protected WEB3AdminAccInfoMailAddressUploadInputResponse getUploadScreen(
        WEB3AdminAccInfoMailAddressUploadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getUploadScreen(WEB3AdminAccInfoMailAddressUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数] 
        //	機@能カテゴリコード：　@機@能カテゴリコード.顧客基本情報（基本） 
        //	is更新：　@true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);

        //1.3 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4 メールアドレスアップロードCSV()
        WEB3AdminAccInfoMailAddressUploadCsv l_uploadCsv = 
            new WEB3AdminAccInfoMailAddressUploadCsv();

        //1.5 getアップロード最新履歴(（管理者共通）アップロード行[])
        //アイテムの定義
        //	取得したアップロード履歴の一覧から『備考1』に『中止』 
        //	を含まない最新のデータを取得する。
        AdministratorUploadRow l_uploadRow = l_uploadCsv.getUploadNewHistory(0L);

        //1.6(*1）アップロード履歴が存在する場合、処理実施)
        //1.7 管理者お客様情報メールアドレスアップロードレスポンス()
    	WEB3AdminAccInfoMailAddressUploadInputResponse l_response = 
            (WEB3AdminAccInfoMailAddressUploadInputResponse) l_request.createResponse();

        if (l_uploadRow != null)
        {
        	//1.6.1 お客様情報アップロード履歴明細()(
        	WEB3AccInfoUploadHistoryUnit l_historyUnit = 
        	    new WEB3AccInfoUploadHistoryUnit();
        	
        	//1.6.2 (*1.1)プロパティセット
        	//(*1.1) お客様情報アップロード履歴明細メッセージオブジェクトプロパティに以下の通り値をセットする。
        	//　@※ アップロード行：　@getアップロード最新履歴の戻り値
        	//　@アップロード処理状態区分：
        	if (l_uploadRow.getUploadEndTimestamp() == null)
        	{
            	//（アップロード行.アップロード終了日時 == null）の場合、”アップロード中”
        		l_historyUnit.uploadStateDiv = 
        		    WEB3AccInfoUploadStateDivDef.UPOAD_CONFIRMING;
        	}
        	else
        	{
            	//（アップロード行.アップロード終了日時 != null）の場合、”アップロード済” 
        		l_historyUnit.uploadStateDiv = 
        		    WEB3AccInfoUploadStateDivDef.UPLOAD_COMPLETE;
        	}
        	
        	//　@アップロード件数：　@アップロード行.アップロード件数
        	l_historyUnit.uploadNumber = "" + l_uploadRow.getUploadCount();
        	
        	//　@アップロード開始日時：　@アップロード行.アップロード開始日時
        	l_historyUnit.uploadStartDate = l_uploadRow.getUploadStartTimestamp();
        	
        	//　@アップロード終了日時：　@アップロード行.アップロード終了日時
        	l_historyUnit.uploadEndDate = l_uploadRow.getUploadEndTimestamp();
        	
        	//　@お客様エラー番号：アップロード行.メッセージコード
        	l_historyUnit.accInfoErrorId = l_uploadRow.getMessageCode();
        	
        	//1.8 (*2)プロパティセット
        	//(*2) レスポンスデータプロパティに値をセットする。
        	//　@アップロード履歴一覧：　@
        	//　@　@アップロード履歴が存在する場合、お客様情報アップロード履歴明細オブジェクト。
        	l_response.uploadHistoryList = l_historyUnit;
        }
        else
        {
        	// アップロード履歴が存在しない場合、null。
        	l_response.uploadHistoryList = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validateアップロードファ@イル)<BR>
     * メールアドレスアップロード確認処理を行う。<BR> 
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（メールアドレスＵＬ）validateアップロードファ@イル」参照。<BR> 
     * <BR>
     * @@param l_request - 管理者お客様情報メールアドレスアップロード確認リクエスト<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminAccInfoMailAddressUploadConfirmResponse<BR>
     */
    protected WEB3AdminAccInfoMailAddressUploadConfirmResponse validateUploadFile(
        WEB3AdminAccInfoMailAddressUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateUploadFile(WEB3AdminAccInfoMailAddressUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1  validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数] 
        //	機@能カテゴリコード：　@機@能カテゴリコード.顧客基本情報（基本）
        //	is更新：　@true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);

        //1.4 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 メールアドレスアップロードCSV()
        WEB3AdminAccInfoMailAddressUploadCsv l_uploadCsv = 
            new WEB3AdminAccInfoMailAddressUploadCsv();

        //1.6 validate同時アップロード(アップロードＩＤ : long)
        //[validate同時アップロード()に指定する引数]  
        //	アップロードＩＤ：　@null
        l_uploadCsv.validateSameTimeUpload(null);
        
        try
        {
            //1.7 check明細行数(l_request)
            l_uploadCsv.checkDetailLines(l_request);
        }
        catch (WEB3BaseException l_exp)
        {
            //1.8 check明細行数()で例外がスローされた場合
            //check明細行数()にて例外がスローされた場合  
            //　@－アップロードエラーを更新する。  
            //　@－上位に例外をスローする。
            log.error("check明細行数()にて例外がスローされた場合 ", l_exp);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            	l_exp.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getErrorMessage());
        }

        //1.9 get管理者コード()
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        
        //1.10 get証券会社コード()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.11 saveアップロード開始(データキー : long, 備考１ : String, 備考２ : String, 備考３ : String, 更新者コード : String)
        //[saveアップロード開始()に指定する引数]  
        //	データキー：　@0  
        //	備考１：　@null  
        //	備考２：　@null　@  
        //	備考３：　@null  
        //	更新者コード：　@管理者.get管理者コード()
        l_uploadCsv.saveUpLoadStart(
            0, null, null, null, l_strAdministratorCode);

        //1.12(*1) リクエストデータ.アップロードファ@イル[]の各要素毎のLOOP処理
        int l_intCount = 0;
        if (l_request.uploadFile != null)
        {
        	l_intCount = l_request.uploadFile.length;
        }

        for (int i = 0; i < l_intCount; i++)
        {
        	int l_intLineNumber = 0;
        	try
        	{
            	//1.12.1 add明細行(明細行文字列 : String)
            	//[add明細行()に指定する引数]  
            	//	明細行文字列：　@リクエストデータ.アップロードファ@イル[index]
        		l_intLineNumber = l_uploadCsv.addRow(l_request.uploadFile[i]);
        	}
        	catch (WEB3SystemLayerException l_exp)
        	{
            	//1.12.2(*1.1) add明細行()で例外がスローされた場合
        		//saveアップロードエラー(エラー情報 : ErrorInfo)
        		//[saveアップロードエラー()に指定する引数]  
        		//	エラー情報：　@（catchした例外）.getErrorInfo()
        		l_uploadCsv.saveUploadError(l_exp.getErrorInfo());
        		
        		//1.12.2.2throw(例外)
                log.error("add明細行()で例外がスローされた場合 ", l_exp);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                	l_exp.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getErrorMessage());
        	}
        	
        	//1.12.3 (*) 空行の場合（add明細行()の戻り値 < 0）、当該要素の処理を中断（continue;）
        	if (l_intLineNumber < 0)
        	{
        		continue;
        	}
        	
        	try
        	{
                //1.12.4  validate明細行(long, String)(メールアドレスアップロードCSV::validate明細行)
                //[validate明細行()に指定する引数]  
                //  行番号：　@（add明細行()の戻り値）  
                //  証券会社コード：　@get証券会社コード()
                l_uploadCsv.validateDetailLine(l_intLineNumber, l_strInstitutionCode);
                
            	//1.12.5 validate重複顧客(long)(メールアドレスアップロードCSV::validate重複顧客)
            	//[validate重複顧客()に指定する引数]  
            	//	行番号：　@（add明細行()の戻り値） 
            	l_uploadCsv.validateDuplicateAccount(l_intLineNumber);
        	}
        	catch (WEB3BaseException l_exp)
        	{
            	//1.12.6(*1.2) validate明細行()，validate重複顧客()にて例外がスローされた場合
        		//saveアップロードエラー(エラー情報 : ErrorInfo)
        		//[saveアップロードエラー()に指定する引数]  
        		//	エラー情報：　@（catchした例外）.getErrorInfo()
        		l_uploadCsv.saveUploadError(l_exp.getErrorInfo());
        		
        		//部店コード
        		String l_strBranchCode = l_uploadCsv.getBranchCode(l_intLineNumber);
        		
        		//顧客コード
        		String l_strAccountCode = 
        		    l_uploadCsv.getAccountCode(l_intLineNumber);
        		
        		//1.12.6.2 throw（例外）
                log.error("validate明細行()，validate重複顧客()にて例外がスローされた場合 ", l_exp);
                log.exiting(STR_METHOD_NAME);
                log.error(l_exp.getErrorMessage(), l_exp);
                throw new WEB3BusinessLayerException(
                	l_exp.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strBranchCode + "," + l_strAccountCode,
                    l_exp);
        	}
        }

        //1.13 getアップロードＩＤ( )
        String l_strUploadId = "" + l_uploadCsv.getAdministratorUploadId();
        
        //1.14 saveアップロードTemp()
        l_uploadCsv.saveUploadTemp();
        
        //1.15 createResponse()
        WEB3AdminAccInfoMailAddressUploadConfirmResponse l_response = 
            (WEB3AdminAccInfoMailAddressUploadConfirmResponse) l_request.createResponse();

        //1.16 (*)プロパティセット
        l_response.uploadNumber = String.valueOf(l_uploadCsv.getRowCount());
        l_response.uploadID = l_strUploadId;

        log.exiting(STR_METHOD_NAME);
    	return l_response;
    }
    
    /**
     * (submitアップロードファ@イル)<BR>
     * メールアドレスアップロード完了処理を行う。<BR> 
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（メールアドレスＵＬ）submitアップロードファ@イル」参照。<BR> 
     * <BR>
     * @@param l_request - 管理者お客様情報メールアドレスアップロード完了リクエスト<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminAccInfoMailAddressUploadCompleteResponse<BR>
     */
    protected WEB3AdminAccInfoMailAddressUploadCompleteResponse submitUploadFile(
        WEB3AdminAccInfoMailAddressUploadCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitUploadFile(WEB3AdminAccInfoMailAddressUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1  validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数] 
        //	機@能カテゴリコード：　@機@能カテゴリコード.顧客基本情報（基本） 
        //	is更新：　@true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);

        //1.4 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 validate取引パスワード(パスワード : String)
        //[validate取引パスワード()に指定する引数]  
        //	パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //1.6 メールアドレスアップロードCSV()
        WEB3AdminAccInfoMailAddressUploadCsv l_uploadCsv = 
            new WEB3AdminAccInfoMailAddressUploadCsv();
        
        //1.7 validate同時アップロード(アップロードＩＤ : long)
        //[validate同時アップロード()に指定する引数]  
        //	アップロードＩＤ：　@リクエストデータ.アップロードＩＤ
        l_uploadCsv.validateSameTimeUpload(new Long(l_request.uploadID));
        
        //1.8 setDataFromアップロードTemp(アップロードＩＤ : long)
        //[setDataFromアップロードTemp()に指定する引数]  
        //	アップロードＩＤ：　@リクエストデータ.アップロードＩＤ
        l_uploadCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadID));
        
        //1.9 get明細行数( )
        int l_intLineNumber = l_uploadCsv.getRowCount();
        
        //1.10(*1) 明細行の数分LOOP処理
        for (int i = 0; i < l_intLineNumber; i++)
        {
        	//1.10.1 get部店コード(int)(メールアドレスアップロードCSV::get部店コード)
        	//[get部店コード()に指定する引数]  
        	//	行番号：　@index
        	String l_strBranchCode = l_uploadCsv.getBranchCode(i);
        	
        	//1.10.2 get顧客(int, String)(メールアドレスアップロードCSV::get顧客)
        	//[get顧客()に指定する引数]  
        	//	行番号：　@index  
        	//	証券会社コード：　@管理者.get証券会社コード()
        	MainAccount l_mainAccount = 
        	    l_uploadCsv.getAccount(i, l_administrator.getInstitutionCode());
        	
        	//1.10.3 getAccountId()
        	l_mainAccount.getAccountId();
        	
        	//1.10.4 getメールアドレス(int)(メールアドレスアップロードCSV::getメールアドレス)
        	//[getメールアドレス()に指定する引数]  
        	//	行番号：　@index
        	String l_strMailAddress = l_uploadCsv.getMailAddress(i);
        	
        	//1.10.5 get案内メール送信フラグ(int)(メールアドレスアップロードCSV::get案内メール送信フラグ)
        	//[get案内メール送信フラグ()に指定する引数]  
        	//	行番号：　@index
        	String l_strMailSendFlag = 
        	    l_uploadCsv.getInformationMailFlag(i);

        	//1.10.6 get削除区分(int)(メールアドレスアップロードCSV::get削除区分)
        	//[get処理フラグ()に指定する引数]  
        	//	行番号：　@index
        	String l_strDeleteDiv = l_uploadCsv.getDeleteDiv(i);
        	
        	try
        	{
        		MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();       		
        		MainAccountParams l_mainAccountParams = 
        		    new MainAccountParams(l_mainAccountRow);

        		//1.10.7 doUpdateQuery(arg0 : Row)
            	QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            	if (WEB3AccInfoDeleteDivDef.INFORMATION_MAIL_FLAG_UPDATE.equals(l_strDeleteDiv))
            	{
            		if (l_strMailAddress != null)
            		{
                		//emailアドレス
                		l_mainAccountParams.setEmailAddress(
                		    l_strMailAddress);

            			//emailアドレス更新者コード
                		l_mainAccountParams.setEmailLastUpdater(
                		    l_administrator.getAdministratorCode());
                		
                		//emailアドレス更新日時
                		l_mainAccountParams.setEmailLastUpdatedTimestamp(
                		    GtlUtils.getSystemTimestamp());
            		}

            		//更新日時
            		l_mainAccountParams.setLastUpdatedTimestamp(
            		    GtlUtils.getSystemTimestamp());
            		
            		if (l_strMailSendFlag != null)
            		{
                		//案内メール送信フラグ
            			BooleanEnum l_blnEnum = BooleanEnum.TRUE;
            			if (WEB3AccInfoMailFlagDef.FALSE.equals(l_strMailSendFlag))
            			{
            				l_blnEnum = BooleanEnum.FALSE;
            			}
                		l_mainAccountParams.setInformationMailFlag(l_blnEnum);
            			
            			//案内メール送信フラグ更新者コード
                		l_mainAccountParams.setInfMailFlgLastUpdater(
                		    l_administrator.getAdministratorCode());
                		
                		//案内メール送信フラグ更新日時
                		l_mainAccountParams.setInfMailFlgUpdatedTimestamp(
                		    GtlUtils.getSystemTimestamp());
            		}
            	}
            	else if (WEB3AccInfoDeleteDivDef.INFORMATION_MAIL_FLAG_DELETE.equals(l_strDeleteDiv))
            	{
            		//案内メール送信フラグ = 0：送信不要
            		l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
            		
            		//emailアドレス = null
            		l_mainAccountParams.setEmailAddress(null);
            		
            		//emailアドレス更新者コード
            		l_mainAccountParams.setEmailLastUpdater(
            		    l_administrator.getAdministratorCode());
            		
            		//emailアドレス更新日時
            		l_mainAccountParams.setEmailLastUpdatedTimestamp(
            		    GtlUtils.getSystemTimestamp());
            		
            		//更新日時
            		l_mainAccountParams.setLastUpdatedTimestamp(
            		    GtlUtils.getSystemTimestamp());
            		
            		//案内メール送信フラグ更新者コード
            		l_mainAccountParams.setInfMailFlgLastUpdater(
            		    l_administrator.getAdministratorCode());
            		
            		//案内メール送信フラグ更新日時
            		l_mainAccountParams.setInfMailFlgUpdatedTimestamp(
            		    GtlUtils.getSystemTimestamp());
            	}

            	l_QueryProcessor.doUpdateQuery(l_mainAccountParams);
        	}
        	catch (DataNetworkException l_exp)
        	{
                log.error(l_exp.getMessage(), l_exp);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp);
        	}
        	catch (DataQueryException l_exp)
        	{
                log.error(l_exp.getMessage(), l_exp);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp);
        	}
        }
        
        //1.11 saveアップロード終了()
        l_uploadCsv.saveUploadEnd();

        //1.12 deleteアップロードTemp()
        l_uploadCsv.deleteUploadTemp();

        //1.13 createResponse()
        WEB3AdminAccInfoMailAddressUploadCompleteResponse l_response = 
            (WEB3AdminAccInfoMailAddressUploadCompleteResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undoアップロード)<BR>
     * メールアドレスアップロード中止処理を行う。<BR> 
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（メールアドレスＵＬ）undoアップロードファ@イル」参照。 <BR>
     * <BR>
     * @@param l_request - 管理者お客様情報メールアドレスアップロード中止リクエスト<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminAccInfoMailAddressUploadCancelResponse<BR>
     */
    protected WEB3AdminAccInfoMailAddressUploadCancelResponse undoUpload(
        WEB3AdminAccInfoMailAddressUploadCancelRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " undoUpload(WEB3AdminAccInfoMailAddressUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 メールアドレスアップロードCSV(long)
        //[コンストラクタの引数]  
        //	アップロードＩＤ：　@リクエストデータ.アップロードＩＤ 
        WEB3AdminAccInfoMailAddressUploadCsv l_uploadCsv = 
            new WEB3AdminAccInfoMailAddressUploadCsv(
            	Long.parseLong(l_request.uploadID));
        
        //1.2 deleteアップロードTemp()
        l_uploadCsv.deleteUploadTemp();
        
        //1.3 アップロード中止をDBに更新する。
        l_uploadCsv.updateUploadEnd();

        //1.4 createResponse()
        WEB3AdminAccInfoMailAddressUploadCancelResponse l_response = 
            (WEB3AdminAccInfoMailAddressUploadCancelResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
