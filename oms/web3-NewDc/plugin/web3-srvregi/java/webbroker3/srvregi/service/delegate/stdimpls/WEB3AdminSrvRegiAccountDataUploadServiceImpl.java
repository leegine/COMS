head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データアップロードサービスImpl(WEB3AdminSrvRegiAccountDataUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
Revesion History : 2005/04/04 内田 亨(SRA) サービス区分チェック処理のバグ対応
Revesion History : 2007/06/05 孟亜南(中訊) 仕様変更モデルNo.238 No.239
Revesion History : 2007/06/28 崔遠鵬(中訊) 仕様変更モデルNo.275
Revesion History : 2007/07/11 孟亜南(中訊) モデル279
Revesion History : 2008/02/26 武波 仕様変更 モデル324，327,328
Revesion History : 2008/06/02 武波 (中訊) 仕様変更 モデルNo.382,No.388
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.srvregi.WEB3AdminSrvRegiAccountDataUploadCsv;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.define.WEB3SrvRegiUploadStatusDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadUnitService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者顧客データアップロードサービスImpl)<BR>
 * サービス利用管理者顧客データアップロードサービス　@実装クラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataUploadServiceImpl implements WEB3AdminSrvRegiAccountDataUploadService 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataUploadServiceImpl.class);
    
    /**
     * @@roseuid 416F392C004E
     */
    public WEB3AdminSrvRegiAccountDataUploadServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用管理者顧客アップロード処理を行う。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、サービス利用管理者顧客ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄの場合 <BR>
     * 　@－getアップロード画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、サービス利用管理者顧客ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄの場合 <BR>
     * 　@－validateアップロードファ@イル()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、サービス利用管理者顧客ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄの場合 <BR>
     * 　@－submitアップロードファ@イル()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、サービス利用管理者顧客ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄの場合 <BR>
     * 　@－undoアップロード()をコールする。<BR>
     * <BR>
     * ※サービスメソッドにて例外が発生した場合は、 <BR>
     * 　@例外オブジェクトの追加文字列（WEB3BaseException.errorMessage）<BR>
     * をレスポンスデータ.errorMessageにセットする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410F713E0066
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminSrvRegiUploadInputRequest)
        {
            //getアップロード画面
            l_response = getUploadScreen(
                (WEB3AdminSrvRegiUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminSrvRegiUploadConfirmRequest)
        {
        	//障害対応 エラーメッセージ表示対応        	
        	l_response = new WEB3AdminSrvRegiUploadConfirmResponse();
        	
        	try
        	{
				//validateアップロードファ@イル
				l_response = validateUploadFile(
					(WEB3AdminSrvRegiUploadConfirmRequest)l_request);
        	}
        	catch(WEB3BaseException ex)
        	{
        		l_response.errorInfo = ex.getErrorInfo();
        		l_response.errorMessage = ex.getErrorMessage();
        	}
            
        }
        else if (l_request instanceof WEB3AdminSrvRegiUploadCompleteRequest)
        {
			//障害対応 エラーメッセージ表示対応        	
			l_response = new WEB3AdminSrvRegiUploadCompleteResponse();
        	
        	try
        	{
	        	//submitアップロードファ@イル
	            l_response = submitUploadFile(
	                (WEB3AdminSrvRegiUploadCompleteRequest)l_request);
        	}
        	catch(WEB3BaseException ex)
        	{
				l_response.errorInfo = ex.getErrorInfo();
				l_response.errorMessage = ex.getErrorMessage();
        	}
        }
        else if (l_request instanceof WEB3AdminSrvRegiUploadCancelRequest)
        {
            //undoアップロード
            l_response = undoUpload(
                (WEB3AdminSrvRegiUploadCancelRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = "パラメータの類型が不正。";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getアップロード画面)<BR>
     * サービス利用顧客アップロード画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(サービス利用)顧客アップロード・getアップロード画面」参照。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputResponse
     * @@roseuid 410F723D0110
     */
    public WEB3AdminSrvRegiUploadInputResponse getUploadScreen(
        WEB3AdminSrvRegiUploadInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getUploadScreen(WEB3AdminSrvRegiUploadInputRequest)";
        log.entering(STR_METHOD_NAME );

		// validate
		l_request.validate();

        //2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
        
        //仕様変更対応 NO_207
        //4 isDIR管理者()の戻り値がfalseの場合、validate注文受付可能()
		if (!l_admin.isDirAdministrator())
		{
			WEB3SrvRegiTradingTimeManagement.validateOrderAccept();
		}
         
        boolean l_blnSameTimeUpload = true;
        
        //顧客データアップロードCSV( )
        WEB3AdminSrvRegiAccountDataUploadCsv l_accountDataUploadCsv = 
            new WEB3AdminSrvRegiAccountDataUploadCsv();
        //5 validate同時アップロード(long)
        try
        {
            l_accountDataUploadCsv.validateSameTimeUpload(null);
        }
        //障害対応 NO_2249
        catch (WEB3BaseException l_e)
        {
            l_blnSameTimeUpload = false;
        }
                    
        //6 get最新アップロード履歴(long)
        AdministratorUploadRow l_adminRow = l_accountDataUploadCsv.getLatestUploadAction(
            Long.parseLong(l_request.serviceDiv));
        
        //7 サービス利用管理者アップロード入力レスポンス( )
        WEB3AdminSrvRegiUploadInputResponse l_response = 
            (WEB3AdminSrvRegiUploadInputResponse)l_request.createResponse();
        
        //8 (*)プロパティセット
        if (!l_blnSameTimeUpload)
        {
            l_response.uploadStatus = WEB3SrvRegiUploadStatusDef.UPLOADING;
            l_response.endDate = null;
        }
        else
        {
            l_response.uploadStatus = WEB3SrvRegiUploadStatusDef.COMPLETE;
            if (l_adminRow != null)
            {
                l_response.endDate = l_adminRow.getUploadEndTimestamp();
            }
        }

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
    
    /**
     * (validateアップロードファ@イル)<BR>
     * サービス利用顧客アップロード確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(サービス利用)顧客アップロード・validateアップロードファ@イル」参照。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmResponse
     * @@roseuid 410F725F0064
     */
    public WEB3AdminSrvRegiUploadConfirmResponse validateUploadFile(
        WEB3AdminSrvRegiUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateUploadFile(WEB3AdminSrvRegiUploadConfirmRequest )";
        log.entering(STR_METHOD_NAME );

		//1 validate
		l_request.validate();

        //3 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //4 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
        
        //5 isDIR管理者()の戻り値がfalseの場合、受付時間チェックを実施する
        if (!l_admin.isDirAdministrator())//5.1 isDIR管理者( )
        {
            //5.2 validate注文受付可能( )
            WEB3SrvRegiTradingTimeManagement.validateOrderAccept();
        }
        
        //6 reset受付時間区分()
		WEB3SrvRegiTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SRVREGI);
        
        //7 顧客データアップロードCSV( )
        WEB3AdminSrvRegiAccountDataUploadCsv l_accountDataUploadCsv = 
            new WEB3AdminSrvRegiAccountDataUploadCsv();

        //8 validate同時アップロード(long)
        l_accountDataUploadCsv.validateSameTimeUpload(null);

        // getDataSourceObject
        AdministratorRow l_administratorRow =
            (AdministratorRow)l_admin.getDataSourceObject();

        //check明細行数
        //証券会社ＩＤ：管理者行.証券会社ＩＤ
        //明細行数：リクエストデータ.アップロードファ@イル.length() - 1
        l_accountDataUploadCsv.checkDetailLines(
            l_administratorRow.getInstitutionId(),
            l_request.lines.length - 1);

        //9 saveアップロード開始(long, String, String, String, String)
        l_accountDataUploadCsv.saveUpLoadStart(
            Long.parseLong(l_request.serviceDiv),
            null,
            null,
            null,
            l_admin.getAdministratorCode());
        
        //障害対応 NO_2164
        try
        {
            //10 validateキーヘッダ(String)
        	l_accountDataUploadCsv.validateKeyHeader(l_request.lines[0]);
        }
        catch(WEB3BaseException ex)
        {
			log.error("アップロードエラー．更新処理", ex);
			
			//例外が発生した場合、アップロードエラーを更新する
			//saveアップロードエラー(ErrorInfo)
			l_accountDataUploadCsv.saveUploadError(ex.getErrorInfo());
                    
			//障害対応 エラーメッセージ表示対応
			//throw（例外）
			throw new WEB3BaseException(
				 ex.getErrorInfo(),
				 this.getClass().getName() + STR_METHOD_NAME,
				 l_request.lines[0]);                    
        }
        
        //11 validate明細行(顧客データアップロードCSV, String[])
        String l_strInitializeAppliDiv = this.validateDetailsLine(l_accountDataUploadCsv ,l_request.lines, l_admin);
        
        //12 get明細行数( )
        int l_intRowNumber = l_accountDataUploadCsv.getRowCount();
        
        //13 getアップロードＩＤ( )
        long l_lngUploadId = l_accountDataUploadCsv.getAdministratorUploadId();
        
		//障害対応 NO_2164
		try
		{
			//14 saveアップロードTemp( )
			int l_intTempRowCount = l_accountDataUploadCsv.saveUploadTemp();
		}
		catch(WEB3BaseException ex)
		{
			log.error("アップロードエラー．更新処理", ex);
			
			//例外が発生した場合、アップロードエラーを更新する
			//saveアップロードエラー(ErrorInfo)
			l_accountDataUploadCsv.saveUploadError(ex.getErrorInfo());
                    
			//障害対応 エラーメッセージ表示対応
			//throw（例外）
			throw new WEB3BaseException(
				 ex.getErrorInfo(),
				 this.getClass().getName() + STR_METHOD_NAME);                    
		}
		
        //15 サービス利用管理者アップロード確認レスポンス( )
        WEB3AdminSrvRegiUploadConfirmResponse l_response = 
            (WEB3AdminSrvRegiUploadConfirmResponse)l_request.createResponse();
            
        //16 (*)プロパティセット
        l_response.lineCount = WEB3StringTypeUtility.formatNumber(l_intRowNumber);
        l_response.uploadId = WEB3StringTypeUtility.formatNumber(l_lngUploadId);
        
        //初期申込区分
        l_response.firstApplyDiv = l_strInitializeAppliDiv;

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
    
    /**
     * (submitアップロードファ@イル)<BR>
     * サービス利用顧客アップロード完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(サービス利用)顧客アップロード・submitアップロードファ@イル」参照。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteResponse
     * @@roseuid 410F72720304
     */
    public WEB3AdminSrvRegiUploadCompleteResponse submitUploadFile(
        WEB3AdminSrvRegiUploadCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitUploadFile(WEB3AdminSrvRegiUploadCompleteRequest )";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadCompleteResponse l_response = null;
        try
        {
			//1 validate
			l_request.validate();
            
            //3 getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //4 validate権限(機@能カテゴリコード : String, is更新 : boolean)
            l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
            
            //5 validate取引パスワード(パスワード : String)
            l_admin.validateTradingPassword(l_request.password);
            
            //6 isDIR管理者()の戻り値がfalseの場合、受付時間チェックを実施する
            if (!l_admin.isDirAdministrator())//6.1 isDIR管理者( )
            {
                //6.2 validate注文受付可能( )
                WEB3SrvRegiTradingTimeManagement.validateOrderAccept();
            }
            
			//7 reset受付時間区分()
			WEB3SrvRegiTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SRVREGI);

            //8 顧客データアップロードCSV( )
            WEB3AdminSrvRegiAccountDataUploadCsv l_accountDataUploadCsv = 
                new WEB3AdminSrvRegiAccountDataUploadCsv(Long.parseLong(l_request.uploadId));//WEB3-SRVREGI-A-UT-0086                               
            
            //9 validate同時アップロード(アップロードＩＤ : long)
            l_accountDataUploadCsv.validateSameTimeUpload(
                new Long(l_request.uploadId));
            
            //10 setDataFromアップロードTemp(アップロードＩＤ : long)
            l_accountDataUploadCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadId));
            
            //11 get明細行数( )顧
            int l_rowCount = l_accountDataUploadCsv.getRowCount();

            // getDataSourceObject
            AdministratorRow l_administratorRow =
                (AdministratorRow)l_admin.getDataSourceObject();

            //check明細行数
            //証券会社ＩＤ：管理者行.証券会社ＩＤ
            //明細行数：get明細行数()の戻り値
            l_accountDataUploadCsv.checkDetailLines(
                l_administratorRow.getInstitutionId(),
                l_rowCount);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                            

            //12 (*)明細行の数分（明細行[0]から）LOOP処理
            for (int i=0 ; i < l_rowCount; i++)
            {
				//障害対応 NO_2164
				try
				{
	                //12.1 get部店コード(int)
	                String l_strBranchCode = l_accountDataUploadCsv.getBranchCode(i);
	                
	                //12.2 validate部店権限(String[])
	                l_admin.validateBranchPermission(l_strBranchCode);
	
	                //12.3 getアップロード区分(行番号 : int)
	                String l_strUploadDiv = l_accountDataUploadCsv.getUploadDiv(i);
	                
	                //12.4 get申込登録ＩＤ(行番号 : int)
	                String l_strRegistId = l_accountDataUploadCsv.getRegistId(i);
	                
	                //12.5 get証券会社コード(行番号 : int)
	                String l_strInstitutionCode = l_accountDataUploadCsv.getInstitutionCode(i);
	                
	                //12.6 getサービス区分(行番号 : int)
	                String l_strSrvDiv = l_accountDataUploadCsv.getSrvDiv(i);
	                
	                //12.7 get顧客コード(行番号 : int)
	                String l_strAccountCode = l_accountDataUploadCsv.getAccountCode(i);
	                
	                //12.8 get申込抽選区分(行番号 : int)
	                String l_strAppliLotDiv = l_accountDataUploadCsv.getAppliLotDiv(i);
	                
	                //12.9 get申込日(行番号 : int)顧客
	                Date l_datAppliDate = l_accountDataUploadCsv.getAppliDate(i);
	                Timestamp l_tsAppliDate = null;
	                if (l_datAppliDate != null)
	                {
	                    l_tsAppliDate = new Timestamp(l_datAppliDate.getTime());
	                }
	
	                //12.10 get適用開始日(行番号 : int)
	                Date l_datAppliStartDate = l_accountDataUploadCsv.getAppliStartDate(i);
	                Timestamp l_tsAppliStartDate = null;
	                if (l_datAppliStartDate != null)
	                {
	                    l_tsAppliStartDate = new Timestamp(l_datAppliStartDate.getTime());
	                }
	                
	                //12.11 get適用終了日(行番号 : int)
	                Date l_datAppliEndDate = l_accountDataUploadCsv.getAppliEndDate(i);
	                Timestamp l_tsAppliEndDate  = null;
	                if (l_datAppliEndDate  != null)
	                {
	                    l_tsAppliEndDate  = new Timestamp(l_datAppliEndDate.getTime());
	                }
	                
	                //12.12 get登録区分(行番号 : int)
	                String l_strPaymentDiv = l_accountDataUploadCsv.getPaymentDiv(i);
	                
	                //12.13 get利用料金(行番号 : int)
	                Double l_dblUseAmt = l_accountDataUploadCsv.getUseAmt(i);
	                
	                //12.14 get出金日(行番号 : int)
	                Date l_datPaymentDate = l_accountDataUploadCsv.getPaymentDate(i);
	                Timestamp l_tsPaymentDate = null;
	                if (l_datPaymentDate != null)
	                {
	                    l_tsPaymentDate = new Timestamp(l_datPaymentDate .getTime());
	                }
	                
	                //12.15 get顧客(String, String, String)
	                WEB3GentradeMainAccount l_mainAccount = 
	                    l_accountManager.getMainAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
	
	                //12.16 getAccountCode( )
	                String l_strAccountCodeInDb = l_mainAccount.getAccountCode();

                    //getサービスマスター(String, String, boolean)
                    //証券会社コード=get証券会社コード（）の戻り値
                    //サービス区分=getサービス区分()の戻り値
                    //is行ロック=false
                    WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement =
                        new WEB3SrvRegiServiceInfoManagement();
                    WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
                        l_srvRegiServiceInfoManagement.getSrvMaster(
                            l_strInstitutionCode, l_strSrvDiv, false);

                    //＜分岐処理＞アップロード区分が新規登録の場合
                    if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
                    {
                        //validate特殊申込(サービスマスター, String, String, String, boolean)
                        //サービスマスター = getサービスマスター()の戻り値
                        //証券会社コード = get証券会社コード()の戻り値
                        //部店コード = get部店コード()の戻り値
                        //口座コード = getAccountCode( )
                        //新規申込区分 = true
                        l_srvRegiServiceInfoManagement.validateSpecialApply(
                            l_srvRegiServiceMaster,
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCodeInDb,
                            true);
                    }

	                //12.17 ＜分岐処理＞アップロード区分が変更登録の場合、適用期間の整合性チェックをする
	                if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_CHANGE_REGIST_LABEL.equals(l_strUploadDiv))
	                {
	                    //10.17.1 validate適用期間(...)
	                    WEB3SrvRegiRegistService l_appliRegiService = 
	                        (WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);
	                    l_appliRegiService.validateAppliPeriod(
	                        l_strInstitutionCode,
	                        l_strSrvDiv,
	                        l_strBranchCode,
	                        l_strAccountCodeInDb,
	                        l_tsAppliStartDate,
	                        l_tsAppliEndDate,
	                        new Long(l_strRegistId));

                        //validate特殊申込(サービスマスター, String, String, String, boolean)
                        //サービスマスター = getサービスマスターの戻り値
                        //証券会社コード = get証券会社コードの戻り値
                        //部店コード = get部店コードの戻り値
                        //口座コード = getAccountCode( )
                        //新規申込区分 = false
                        l_srvRegiServiceInfoManagement.validateSpecialApply(
                            l_srvRegiServiceMaster,
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCodeInDb,
                            false);
	                }
	                
	                WEB3AdminSrvRegiAccountDataUploadUnitService l_uploadUnitService = 
	                    (WEB3AdminSrvRegiAccountDataUploadUnitService) Services.getService(
	                        WEB3AdminSrvRegiAccountDataUploadUnitService.class);
	                //12.18
	                WEB3GentradeSubAccount l_subAccount = 
	                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                    //＜分岐処理＞（アップロード区分 == アップロード区分_申込属性 且つ get申込抽選区分 != 属性削除） の場合
                    if (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv)
                        && !WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ATTRIBUTE_DELETE_LABEL.equals(
                           l_strAppliLotDiv))
                    {
                        //validate無料登録日付設定
                        l_accountDataUploadCsv.validatePaymentFreeRegiDate(l_datAppliStartDate, l_datAppliEndDate);

                        //getサービス申込属性情報
                        List l_lisAttributeInfo = l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            l_strSrvDiv,
                            l_strUploadDiv);

                        //getサービス申込属性情報オブジェクト == null の場合
                        if (l_lisAttributeInfo == null)
                        {
                            //insertサービス申込属性(String, String, String, String, String, Timestamp, Timestamp)
                            l_uploadUnitService.insertSrvApplyAttribute(
                                l_strInstitutionCode,
                                l_strBranchCode,
                                l_strAccountCode,
                                l_strSrvDiv,
                                l_strAppliLotDiv,
                                l_tsAppliStartDate,
                                l_tsAppliEndDate);
                        }
                        //getサービス申込属性情報オブジェクト != null の場合
                        else
                        {
                            //updateサービス申込属性(String, String, String, String, String, Timestamp, Timestamp)
                            l_uploadUnitService.updateSrvApplyAttribute(
                                l_strInstitutionCode,
                                l_strBranchCode,
                                l_strAccountCode,
                                l_strSrvDiv,
                                l_strAppliLotDiv,
                                l_tsAppliStartDate,
                                l_tsAppliEndDate);
                        }
                    }
                    //＜分岐処理＞（アップロード区分 == アップロード区分_申込属性 且つ get申込抽選区分 == 属性削除） の場合
                    else if (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv)
                        && WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ATTRIBUTE_DELETE_LABEL.equals(
                           l_strAppliLotDiv))
                    {
                        int l_intDeleteSrvApplyAttribute = l_uploadUnitService.deleteSrvApplyAttribute(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            l_strSrvDiv);

                        if (l_intDeleteSrvApplyAttribute <= 0)
                        {
                            log.debug("削除該当レコードなし。");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02784,
                                this.getClass().getName() + STR_METHOD_NAME,
                                "削除該当レコードなし。");
                        }
                    }
                    //＜分岐処理＞上記以外の場合　@（アップロード区分 != アップロード区分_申込属性） の場合）
                    else
                    {
                        //12.19 update申込登録
                        l_uploadUnitService.updateAppliRegist(
                            l_subAccount,
                            l_strUploadDiv,
                            l_strRegistId == null ? null : new Long(l_strRegistId), 
                            l_strInstitutionCode,
                            l_strSrvDiv,
                            l_strBranchCode,
                            l_strAccountCodeInDb,
                            l_tsAppliStartDate,
                            l_tsAppliEndDate,
                            l_tsAppliDate,
                            l_strAppliLotDiv,
                            l_strPaymentDiv,
                            l_dblUseAmt,
                            l_tsPaymentDate ,
                            l_request.password);
                    }
	            }
				catch(WEB3BaseException ex)
				{
					log.error("アップロードエラー．更新処理", ex);
			
					//例外が発生した場合、アップロードエラーを更新する
					//saveアップロードエラー(ErrorInfo)
					l_accountDataUploadCsv.saveUploadError(ex.getErrorInfo());
					
					//アップロードTempテーブルのレコードを削除
					l_accountDataUploadCsv.deleteUploadTemp();
				
					StringBuffer l_sbErrorMessage = new StringBuffer( 
						l_accountDataUploadCsv.getBranchCode(i)
						+ ","
						+ l_accountDataUploadCsv.getSrvDiv(i) 
						+ "," 
						+ l_accountDataUploadCsv.getAccountCode(i));
					if(ex.getErrorMessage() != null && ex.getErrorMessage().trim().length() != 0) 
					{
						l_sbErrorMessage.append("," + ex.getErrorMessage());
					}
                
					String l_strErrorMessage = l_sbErrorMessage.toString();    
                    
					//障害対応 エラーメッセージ表示対応
					//throw（例外）
					throw new WEB3BaseException(
						 ex.getErrorInfo(),
						 this.getClass().getName() + STR_METHOD_NAME,
						 l_strErrorMessage);
				}
            }

            //13 saveアップロード終了( )
            l_accountDataUploadCsv.saveUploadEnd();
            
            //14 deleteアップロードTemp( )
            l_accountDataUploadCsv.deleteUploadTemp();
            
            //15 サービス利用管理者アップロード完了レスポンス( )
            l_response = 
                (WEB3AdminSrvRegiUploadCompleteResponse)l_request.createResponse();
        
        }
        catch (NotFoundException l_e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
    
    /**
     * (undoアップロード)<BR>
     * サービス利用顧客アップロード中止処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(サービス利用)顧客アップロード・undoアップロード」参照。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelResponse
     * @@roseuid 410F7292020A
     */
    public WEB3AdminSrvRegiUploadCancelResponse undoUpload(
        WEB3AdminSrvRegiUploadCancelRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " undoUpload(WEB3AdminSrvRegiUploadCancelRequest)";
        log.entering(STR_METHOD_NAME );

		// validate
		l_request.validate();
        
        //1 顧客データアップロードCSV(long)
        WEB3AdminSrvRegiAccountDataUploadCsv l_accountDataUploadCsv = 
            new WEB3AdminSrvRegiAccountDataUploadCsv(Long.parseLong(l_request.uploadId));

        //2 deleteアップロードTemp( )
        l_accountDataUploadCsv.deleteUploadTemp();
        
        //3 saveアップロード中止( )
        l_accountDataUploadCsv.saveUploadStop();
        
        //4 サービス利用管理者アップロード中止レスポンス( )
        WEB3AdminSrvRegiUploadCancelResponse l_response = 
            (WEB3AdminSrvRegiUploadCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
    
    /**
     * (validate明細行)<BR>
     * 明細行（アップロードデータ）のチェックを行う。 <BR>
     * <BR>
     * [validate明細行()に指定する引数] <BR>
     * 顧客データアップロードCSV：生成した顧客データアップロードCSV）<BR>
     * 明細行[]：　@リクエストデータ.アップロードファ@イル[] <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(サービス利用)顧客アップロード・validate明細行」参照。 <BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(サービス利用)顧客アップロード・validate明細行」): <BR>
     *         1.1.2.2:throw（例外）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00992<BR>
     * ==========================================================<BR>
     * ========================================================<BR>
     *シーケンス図(サービス利用)顧客アップロード・validate明細行」): <BR>
     *        1.1.14.1getサービス申込登録<BR>
     *        検索結果=0件（戻り値がnull）でない場合、例外をスローする。（2重登録エラー）<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00894<BR>
     *==========================================================<BR>
     *========================================================<BR>
     *シーケンス図(サービス利用)顧客アップロード・validate明細行」): <BR>
     *        1.1.15.1getサービス申込登録<BR>
     *        １） 検索結果＝0件の場合（nullが返却）、例外をスローする。（対象レコードなしエラー） <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00908<BR>
     *        ２） 検索結果≠0件の場合（≠null）、取得したサービス申込登録データの申込抽選区分が<BR>
     *        「試用申込」なら例外をスローする。（試用レコード変更不可エラー） <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00909<BR>
     *==========================================================<BR>
     * 
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(サービス利用)顧客アップロード・validate明細行」): <BR>
     *         1.1.23.2.1.1:getSrvLotInfo(String, String, Timestamp, int)<BR>
     *         getサービス抽選情報()の戻り値がnullの場合は、申込日が申込期間内ではないと判断し、<BR>
     *         例外をスローする。（申込日設定エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00993<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(サービス利用)顧客アップロード・validate明細行」): <BR>
     *         1.2.2:『ローカル変数「新規登録件数」 > <BR>
     *         get外部連携未使用件数(サービ区分 : String)の戻り値』の場合エラー<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03029<BR>
     * ==========================================================<BR>
     * @@param l_accountDataUploadCsv - (顧客データアップロードCSV)<BR>
     * 顧客データアップロードCSVオブジェクト<BR>
     * @@param l_strDetailsLines - (明細行)<BR>
     * @@param l_admin  - (管理者)<BR>
     * @@return String<BR>
     * @@roseuid 411087DF0022
     */
    public String validateDetailsLine(
        WEB3AdminSrvRegiAccountDataUploadCsv l_accountDataUploadCsv, String[] l_strDetailsLines, WEB3Administrator l_admin)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDetailsLine(WEB3AdminSrvRegiAccountDataUploadCsv , String[], WEB3Administrator)";
        log.entering(STR_METHOD_NAME );
        
        if (l_strDetailsLines == null)
        {
            log.exiting(STR_METHOD_NAME);
            String l_strErrorMessage = "アップロードデータnull!";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        
		// get初期申込区分()コールフラグ（一度でもコールされるとtrueが設定される）
		boolean l_blnInitializeAppliDivCall 	= false;
		// get初期申込区分()の結果フラグ（一度でも“無”が返却されるとtrueが設定される）
		boolean l_blnInitializeAppliDivHaveNot	= false;
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //1 ＜分岐処理＞明細行[1]～各要素毎のLOOP処理
        int l_intLineCount = l_strDetailsLines.length;
        long l_lngNewRegist = 0;
        WEB3SrvRegiServiceMaster l_serviceMaster = null;
        String l_strSrvDiv = null;
        for (int i = 1; i < l_intLineCount; i++)//QA WEB3-SRVREGI-A-FT-0100.xls
        {
            //1.1 add明細行(String)
            int l_intRowNumber = 0;
            try
            {
                l_intRowNumber = l_accountDataUploadCsv.addRow(l_strDetailsLines[i]);
            }
            catch (WEB3SystemLayerException l_e)
            {
                //1.2 (*)add明細行()で例外が発生した場合、アップロードエラーを更新する
                //1.2.1 saveアップロードエラー(ErrorInfo)
                l_accountDataUploadCsv.saveUploadError(l_e.getErrorInfo());//WEB3SystemLayerException
                
                log.error("add明細行エラー。", l_e);
                
                //障害対応 エラーメッセージ表示対応
                //1.2.2 throw（例外）
                throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00992,
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_strDetailsLines[i]);                              
            }
            
            try
            {
            	//障害対応 NO_2055
            	//1.3 空行の場合(add明細行の戻り値<0)
            	if(l_intRowNumber < 0)
            	{
            		//当該要素の処理を中断
            		continue;
            	}
            	
				//1.4 validate入力必須項目
				l_accountDataUploadCsv.validateDispensableItem(l_intRowNumber);
                
                //1.5 get部店コード(int)
                String l_strBranchCode = l_accountDataUploadCsv.getBranchCode(l_intRowNumber);
                
                //1.6 validate部店権限(String)
                l_admin.validateBranchPermission(l_strBranchCode);
                
                //1.7 getアップロード区分(int)
                String l_strUploadDiv = l_accountDataUploadCsv.getUploadDiv(l_intRowNumber);
                
                //1.8 validateアップロード区分(String)
                l_accountDataUploadCsv.validateUploadDiv(l_strUploadDiv);
                
                //1.9 get申込登録ＩＤ(int)
                String l_strRegistId = l_accountDataUploadCsv.getRegistId(l_intRowNumber);
                
                //1.10 get証券会社コード(int)
                String l_strInstitutionCode = l_accountDataUploadCsv.getInstitutionCode(l_intRowNumber);
                
                //1.11 get顧客コード(int)
                String l_strAccountCode = l_accountDataUploadCsv.getAccountCode(l_intRowNumber);
                
                //1.12 validate顧客(String, String, String)
                l_accountDataUploadCsv.validateAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                    
                //1.13 get顧客(String, String, String)
                WEB3GentradeMainAccount l_mainAccount = 
                    l_accountManager.getMainAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

                //1.14 getAccountCode( )
                String l_strAccountCodeInDb = l_mainAccount.getAccountCode();
                
                //1.15 getサービス区分(int) 
                l_strSrvDiv = l_accountDataUploadCsv.getSrvDiv(l_intRowNumber);
                
                //getアップロード区分()の戻り値が'3'以外の場合以下の処理を行う。          
                //1.16 validateサービス区分(String, String)                               
                if(!WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))                                                                              
                {                                                                         
                	l_accountDataUploadCsv.validateSrvDiv(l_strInstitutionCode, l_strSrvDiv);
                }                                                                         

                //1.17 get適用開始日(int)
                Date l_datAppliStartDate = l_accountDataUploadCsv.getAppliStartDate(l_intRowNumber);
                Timestamp l_tsAppliStartDate = null;
                if (l_datAppliStartDate != null)
                {
                    l_tsAppliStartDate = new Timestamp(l_datAppliStartDate.getTime());
                }
                
                //1.18 get適用終了日(int)
                Date l_datAppliEndDate = l_accountDataUploadCsv.getAppliEndDate(l_intRowNumber);
                Timestamp l_tsAppliEndDate  = null;
                if (l_datAppliEndDate  != null)
                {
                    l_tsAppliEndDate  = new Timestamp(l_datAppliEndDate.getTime());
                }
                
                //getサービス利用申込登録サービス
                WEB3SrvRegiRegistService l_appliRegiService = 
                    (WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);

                //1.19 getサービスマスター(String, String, boolean)
                WEB3SrvRegiServiceInfoManagement l_serviceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
                l_serviceMaster = l_serviceInfoManagement.getSrvMaster(
                    l_strInstitutionCode,
                    l_strSrvDiv,
                    false);;
                
                //1.20 get申込要サービス( )
                WEB3SrvRegiApplicationRequiredService l_requiredService = l_serviceMaster.getAppliRequiredSrv(false);
                if (l_requiredService == null)
                {
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + STR_METHOD_NAME);
                }

                String l_strLotSetup = l_requiredService.getLotDiv();
                
                String l_strPaymentDiv = null;

                if (!WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))
                {
                    //障害対応 NO_2140
                    //1.21 get登録区分(int)
                    l_strPaymentDiv = l_accountDataUploadCsv.getPaymentDiv(l_intRowNumber);

                    //1.22 validate登録区分(String)
                    l_accountDataUploadCsv.validatePaymentDiv(l_strPaymentDiv);
                }

                //＜分岐処理＞（アップロード区分≠アップロード区分_抽選結果アップロード） && 
                //（アップロード区分≠アップロード区分_申込属性）の場合に日付設定のチェックを行う
				if (!WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv)
                    && !WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))
				{
					//1.23.1 get申込日(int)
					Date l_datAppliDate = l_accountDataUploadCsv.getAppliDate(l_intRowNumber);

					//1.23.2 validate日付設定(String, String, String, Date, Date, Date)
					l_accountDataUploadCsv.validateTimestampSetup(
						l_strInstitutionCode, 
						l_strSrvDiv, 
						l_requiredService.getLotDiv(), 
						l_datAppliDate, 
						l_datAppliStartDate, 
						l_datAppliEndDate);
    
					//1.23.3 get利用料金(int)
				  Double l_dblUseAmt = l_accountDataUploadCsv.getUseAmt(l_intRowNumber);
                    
					//1.23.4 validate利用料金(double, String)
					if (l_dblUseAmt != null)
					{
					  l_accountDataUploadCsv.validateUseAmt(
						  l_dblUseAmt.doubleValue(), l_strPaymentDiv);
					}
				}

                //＜分岐処理＞（アップロード区分 == アップロード区分_申込属性） の場合に日付設定のチェックを行う
                if (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))
                {
                    //validate無料登録日付設定(Date, Date)
                    l_accountDataUploadCsv.validatePaymentFreeRegiDate(l_datAppliStartDate, l_datAppliEndDate);
                }

                //障害対応 NO_2142
                //1.24 ＜分岐処理＞新規登録時、サービス申込登録データの登録有無をチェックする
                if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
                {
		    //障害対応
                    //1.24.1 getサービス申込登録(String, String, String, String, String, String, boolean)
                    //QA:WEB3-SRVREGI-A-FT-0115
                    WEB3GentradeSrvRegiApplication l_srvRegiApp = l_appliRegiService.getServiceRegist(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCodeInDb,
						WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
                        WEB3EffectiveDivDef.EFFECTIVE,
                        false);
                    if (l_srvRegiApp != null)
                    {
                        String l_strErrorMessage = "2重登録エラー。";
                        log.debug(l_strErrorMessage);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00894, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strErrorMessage);            
                    }
                    
                    Long l_registId = null;
                    if (l_strRegistId != null)
                    {
                        l_registId = new Long(l_strRegistId);
                    }
                    //1.24.2 validate適用期間(String, String, String, String, Timestamp, Timestamp, Long)
                    l_appliRegiService.validateAppliPeriod(
                        l_strInstitutionCode,
                        l_strSrvDiv,
                        l_strBranchCode,
                        l_strAccountCodeInDb,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        l_registId);

                    //validate特殊申込(サービスマスター, String, String, String, boolean)
                    //サービスマスター = getサービスマスタ()ーの戻り値
                    //証券会社コード =get証券会社コード()の戻り値
                    //部店コード = get部店コード()の戻り値
                    //口座コード = getAccountCode()の戻り値
                    //新規申込区分 = true
                    l_serviceInfoManagement.validateSpecialApply(
                        l_serviceMaster,
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCodeInDb,
                        true);

                    //「ローカル変数「新規登録件数」 = ローカル変数「新規登録件数」 + 1」
                    l_lngNewRegist = l_lngNewRegist + 1;
                }
                
                //1.24 ＜分岐処理＞変更登録時、サービス申込登録データの適用期間の妥当性をチェックする
                if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_CHANGE_REGIST_LABEL.equals(l_strUploadDiv))
                {
                    //1.25.1 getサービス申込登録(String, String, String, String, long, )
                    WEB3GentradeSrvRegiApplication l_srvRegiApp = l_appliRegiService.getServiceRegist(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCodeInDb,
                        Long.parseLong(l_strRegistId),
                        false);
                    if (l_srvRegiApp == null)
                    {
                        String l_strErrorMessage = "対象レコードなしエラー。";
                        log.debug(l_strErrorMessage);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00908, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strErrorMessage);            
                    }
                    else
                    {
                        if (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_TRIAL_LABEL.equals(l_srvRegiApp.getAppliLotDiv()))
                        {
                            String l_strErrorMessage = "試用レコード変更不可エラー。";
                            log.debug(l_strErrorMessage);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00909, 
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_strErrorMessage);            
                        }
                    }
                    
                    //1.25.2 validate適用期間(String, String, String, String, Timestamp, Timestamp, Long)
                    l_appliRegiService.validateAppliPeriod(
                        l_strInstitutionCode,
                        l_strSrvDiv,
                        l_strBranchCode,
                        l_strAccountCodeInDb,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        new Long(l_strRegistId));

                    //validate特殊申込(サービスマスター, String, String, String, boolean)
                    //サービスマスター = getサービスマスター()の戻り値
                    //証券会社コード = get証券会社コード()の戻り値
                    //部店コード = get部店コード()の戻り値
                    //口座コード = getAccountCode()の戻り値
                    //新規申込区分 = false
                    l_serviceInfoManagement.validateSpecialApply(
                        l_serviceMaster,
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCodeInDb,
                        false);
                }
                else if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv))
                {
                    //1.26 抽選結果アップロード時、サービス申込登録データの登録有無をチェックする
                    //1.26.1 getサービス申込登録(String, String, String, String, long, )
                    WEB3GentradeSrvRegiApplication l_srvRegiApp = l_appliRegiService.getServiceRegist(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCodeInDb,
                        Long.parseLong(l_strRegistId),
                        false);
                    if (l_srvRegiApp == null)
                    {
                        String l_strErrorMessage = "対象レコードなしエラー。";
                        log.debug(l_strErrorMessage);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00908, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strErrorMessage);            
                    }
                }
    
                //1.27 get申込抽選区分(int)
                String l_strAppliLotDiv = l_accountDataUploadCsv.getAppliLotDiv(l_intRowNumber);
                
                //1.28 validate申込抽選区分(String, String, String)
                l_accountDataUploadCsv.validateAppliLotDiv(
                    l_strUploadDiv, 
                    l_requiredService.getLotDiv(), 
                    l_strAppliLotDiv);
    
                //1.29 ＜分岐処理＞アップロード区分＝新規登録 & 登録区分＝有料の場合に出金日のチェックをする
                if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv)
                    && WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_CHARGE_LABEL.equals(l_strPaymentDiv))//WEB3-SRVREGI-A-FT-0106.xls
                {
                    //1.29.1 get出金日(int)
                    Date l_datPaymentDate = l_accountDataUploadCsv.getPaymentDate(l_intRowNumber);
                    Timestamp l_tsPaymentDate = null;
                    if (l_datPaymentDate != null)
                    {
                        l_tsPaymentDate = new Timestamp(l_datPaymentDate.getTime());
                    }
                    
                    //1.29.2 validate出金日(String, Date)
                    l_accountDataUploadCsv.validatePaymentDate(l_datPaymentDate);
                    
                    //1.29.3 validate出金余力(String, String, String, double)
                    l_accountDataUploadCsv.validatePaymentPower(
                        l_strInstitutionCode, 
                        l_strBranchCode, 
                        l_strAccountCodeInDb, 
                        l_accountDataUploadCsv.getUseAmt(l_intRowNumber).doubleValue(),
                        l_tsPaymentDate);
                }
                
                // ＜分岐処理＞アップロード区分＝新規登録の場合、初期申込区分のチェックを行う。
				if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
				{
					//1.30.4 get初期申込区分(String, String, String, String)
					String l_strInitializeAppliDiv = l_appliRegiService.getInitializeAppliDiv(
						l_strInstitutionCode, 
						l_strBranchCode, 
						l_strSrvDiv, 
						l_strAccountCodeInDb);

					// get初期申込区分()コールフラグ設定
					l_blnInitializeAppliDivCall = true;
					
					if(l_strInitializeAppliDiv.equals(WEB3ConditionsValueDivDef.HAVE_NOT))
					{
						// get初期申込区分()の結果フラグ設定
						l_blnInitializeAppliDivHaveNot = true;                    
					}
				}

            }
            catch (WEB3BaseException l_e)
            {
                //1.31 getアップロード区分()～validate出金日()の手続きで例外が発生した場合、アップロードエラーを更新する
                                
                //1.31.1 saveアップロードエラー(ErrorInfo)
                l_accountDataUploadCsv.saveUploadError(l_e.getErrorInfo());

                StringBuffer l_sbErrorMessage = new StringBuffer( 
                    l_accountDataUploadCsv.getBranchCode(l_intRowNumber)
                    + ","
                    + l_accountDataUploadCsv.getSrvDiv(l_intRowNumber) 
                    + "," 
                    + l_accountDataUploadCsv.getAccountCode(l_intRowNumber));
                if(l_e.getErrorMessage() != null && l_e.getErrorMessage().trim().length() != 0) 
                {
                	l_sbErrorMessage.append("," + l_e.getErrorMessage());
                }
                
				String l_strErrorMessage = l_sbErrorMessage.toString();     
                log.error(l_strErrorMessage, l_e);
               	
                //1.31.2 throw（例外）
                throw new WEB3BaseException(
                     l_e.getErrorInfo(),
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_strErrorMessage,
                     l_e);      
            }
            
        }

        //＜分岐処理＞サービスマスターオブジェクト.特殊処理区分 = 1 (外部連携サービス)の場合
        //最終行のサービスマスターの値
        String l_strSpecialProcessDiv = null;
        if (l_serviceMaster != null)
        {
            SrvRegiMasterRow l_srvRegiMasterRow =
                (SrvRegiMasterRow)l_serviceMaster.getDataSourceObject();
            l_strSpecialProcessDiv = l_srvRegiMasterRow.getSpecialProcessDiv();
        }

        if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
        {
            //外部連携情報管理テーブルのステータスが未使用のレコードの件数を返却
            //サービス区分 = getサービス区分()の戻り値
            //最終行より取得したサービス区分の値
            WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
            Long l_otherOrgUnUsedCount =
                l_srvRegiOtherOrgService.getOtherOrgUnUsedCount(l_strSrvDiv);

            //『ローカル変数「新規登録件数」 > get外部連携未使用件数(サービ区分 : String)の戻り値』の場合エラー
            if (l_lngNewRegist > l_otherOrgUnUsedCount.longValue())
            {
                log.debug("新規登録件数が外部連携未使用件数を超えています。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03029,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "新規登録件数が外部連携未使用件数を超えています。");
            }
        }
		// 初期申込区分判定を行う。
		// 1.2 ＜LOOP処理にて、get初期申込区分()が一度もコールされなかった場合＞
		log.exiting(STR_METHOD_NAME);
		if (!l_blnInitializeAppliDivCall)
		{
			// 1.2.1 NULLをリターンする。
			return null;
		}
		// 1.3 ＜LOOP処理にて、１件でもget初期申込区分()から"無"が返却された場合＞
		else if(l_blnInitializeAppliDivHaveNot)
		{
			// 1.3.1 “無”をリターンする。
			return WEB3ConditionsValueDivDef.HAVE_NOT;
		}
		// 1.4 ＜LOOP処理にて、１件もget初期申込区分()から"無"が返却されなかった場合＞
		else
		{
			// 1.4.1 “有”をリターンする。
			return WEB3ConditionsValueDivDef.HAVE;
		}
    }
}
@
