head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountMngServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 外株口座管理サービス実装クラス(WEB3AdminFEqConAccountMngServiceImpl)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/21 黄建(中訊) 新規作成
 */

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.WEB3FEqConTransferDataControlService;
import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.define.WEB3AioAccountOpenCompleteDef;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchInputResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AdminFEqConAccountMngService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (外株口座管理サービスImpl)<BR>
 * 外株口座管理サービス実装クラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFEqConAccountMngServiceImpl implements WEB3AdminFEqConAccountMngService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountMngServiceImpl.class);
    
    /**
     * 外株口座管理処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *   get入力画面()<BR>
     *   get検索画面()<BR>
     *   validate状況変更()<BR>
     *   submit状況変更()
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B66A008E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_request instanceof WEB3AdminFEqConAccountInfoSearchInputRequest)
        {
            //リクエストデータの具象データ型が「外株口座情報検索条件入力リクエスト」の場合
            WEB3AdminFEqConAccountInfoSearchInputResponse l_inputResponse =
                this.getInputScreen((WEB3AdminFEqConAccountInfoSearchInputRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_inputResponse;
        }
        else if (l_request instanceof WEB3AdminFEqConAccountInfoSearchRequest)
        {
            //リクエストデータの具象データ型が「外株口座情報検索リクエスト」の場合
            WEB3AdminFEqConAccountInfoSearchResponse l_searchResponse =
                this.getSearchScreen((WEB3AdminFEqConAccountInfoSearchRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_searchResponse;
        }
        else if (l_request instanceof WEB3AdminFEqConAccountStateChangeConfirmRequest)
        {
            //リクエストデータの具象データ型が「外株口座開設状況変更共通リクエスト」の場合
            WEB3AdminFEqConAccountStateChangeConfirmResponse l_commonResponse =
                this.validateStateChange((WEB3AdminFEqConAccountStateChangeConfirmRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_commonResponse;
        }
        else if (l_request instanceof WEB3AdminFEqConAccountStateChangeCompleteRequest)
        {
            //リクエストデータの具象データ型が「外株口座開設状況変更完了リクエスト」の場合
            WEB3AdminFEqConAccountStateChangeCompleteResponse l_completeResponse =
                this.submitStateChange((WEB3AdminFEqConAccountStateChangeCompleteRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_completeResponse;
        }
        else
        {
            log.debug("パラメータタイプ不正");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座管理）get入力画面」 参照
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountInfoSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B9CF0338
     */
    protected WEB3AdminFEqConAccountInfoSearchInputResponse getInputScreen(
        WEB3AdminFEqConAccountInfoSearchInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminFEqConAccountInfoSearchInputRequest l_request";
        log.entering(STR_METHOD_NAME);
        //1.1 validate( )  リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( ) getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, false);
        
        //1.4 validate部店権限(String)
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5  createResponse( ) レスポンスデータを生成する。
        WEB3AdminFEqConAccountInfoSearchInputResponse l_response =
            (WEB3AdminFEqConAccountInfoSearchInputResponse) l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME); 
        return l_response;
    }
    
    /**
     * (get検索画面)<BR>
     * 検索画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座管理）get検索画面」 参照
     * ------------------------------------------------
     * 1.6 get外国株式顧客(String, String, String)
     *  nullが返却された場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountInfoSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B9CF0357
     */
    protected WEB3AdminFEqConAccountInfoSearchResponse getSearchScreen(
        WEB3AdminFEqConAccountInfoSearchRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getSearchScreen(WEB3AdminFEqConAccountInfoSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
       
        //1.1 validate( )  リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( ) getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, false);
        
        //1.4 validate部店権限(String)
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5 get証券会社コード( ) 証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 get外国株式顧客(String, String, String)
        WEB3FEqConTransferDataControlService l_service =
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams =
                l_service.getFeqAccountByFeqAccountCode(
                    l_strInstitutionCode,
                    l_request.branchCode,
                    l_request.fstkAccountCode);
        }
        catch (NotFoundException l_ex)
        {
			log.error("__NotFound FeqAccountParams__", l_ex);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01387,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
        }    
       
	   //1.7 get顧客() 顧客を取得する。 
	    FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
	    WEB3GentradeAccountManager l_gentradeAccMgr =
		    (WEB3GentradeAccountManager) l_finApp.getAccountManager();
		    
	    String l_emailAddress;
	    boolean l_mainAccountFlg = true;
	    try
	    {
			MainAccount l_mainAccount = 
				l_gentradeAccMgr.getMainAccount(
				   l_feqAccountParams.getInstitutionCode(),
				   l_feqAccountParams.getBranchCode(),
				   l_feqAccountParams.getAccountCode());
				   
			MainAccountRow l_mainAccountRow = 
				(MainAccountRow)l_mainAccount.getDataSourceObject();
			l_emailAddress = l_mainAccountRow.getEmailAddress();			  	    	
	    }
	    catch(WEB3BaseException l_ex)
	    {
			l_emailAddress = null;
			l_mainAccountFlg = false;
	    }
         
        //1.8 createResponse( ) レスポンスデータを生成する。
        WEB3AdminFEqConAccountInfoSearchResponse l_response =
            (WEB3AdminFEqConAccountInfoSearchResponse) l_request.createResponse();
        
        //1.9  (*)プロパティセット
        // (*)レスポンスデータにプロパティをセットする。
        //レスポンスデータ.部店コード  ＝　@外国株式顧客.部店コード
        l_response.branchCode = l_feqAccountParams.getBranchCode();
        //レスポンスデータ.顧客ID  ＝　@外国株式顧客.外国株式顧客ID
        l_response.accountId = l_feqAccountParams.getFeqAccountId() + "";
        //レスポンスデータ.外株口座番号  ＝　@外国株式顧客.外国株式口座番号
        l_response.fstkAccountCode = l_feqAccountParams.getFeqAccountCode();
        //レスポンスデータ.名前(姓)  ＝　@外国株式顧客.名前(姓)
        l_response.familyName = l_feqAccountParams.getLastName();
        //レスポンスデータ.名前(名)  ＝　@外国株式顧客.名前(名)
        l_response.name = l_feqAccountParams.getFirstName();
        //レスポンスデータ.メールアドレス  ＝　@顧客.emailアドレス
        //get顧客()にて例外が発生した場合、「NULL」をセット
		l_response.mailAddress = l_emailAddress;
        //レスポンスデータ.顧客コード  ＝　@外国株式顧客.顧客コードの上6桁
        l_response.accountCode = l_feqAccountParams.getAccountCode().substring(0, 6);
        //レスポンスデータ.口座開設状況区分 ＝　@外国株式顧客.外国株式口座区分
        //get顧客()にて例外が発生した場合、「99(口座抹消)」をセット
        if (l_mainAccountFlg)
        {
			l_response.accountOpenStatusDiv = l_feqAccountParams.getAccountOpenDiv();
        }
        else
        {
			l_response.accountOpenStatusDiv = WEB3AioAccountOpenCompleteDef.OPEN_DELETE;
        }
        
        log.exiting(STR_METHOD_NAME); 
        return l_response;
    }
    
    /**
     * (validate状況変更)<BR>
     * 口座開設状況変更の確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座管理）validate状況変更」 参照
     * ------------------------------------------------
     * 1.4 get外国株式顧客(String)
     *  nullが返却された場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountStateChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B9CF0376
     */
    protected WEB3AdminFEqConAccountStateChangeConfirmResponse validateStateChange(
        WEB3AdminFEqConAccountStateChangeConfirmRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "validateStateChange(" +
            "WEB3AdminFEqConAccountStateChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )  リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( ) getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, true);
       
        //1.4 get外国株式顧客(String)
        WEB3FEqConTransferDataControlService l_service =
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = 
                l_service.getFeqAccountByAccountId(l_request.accountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFound FeqAccountParams__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }   

        //1.5 validate部店権限(String[]) 部店権限のチェックを行う。 
        l_admin.validateBranchPermission(l_feqAccountParams.getBranchCode());
        
        //1.6 createResponse( ) レスポンスデータを生成する。
        WEB3AdminFEqConAccountStateChangeConfirmResponse l_response = 
            (WEB3AdminFEqConAccountStateChangeConfirmResponse) l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (submit状況変更)<BR>
     * 口座開設状況変更の完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座管理）submit状況変更」 参照
     * ------------------------------------------------
     * 1.4 get外国株式顧客(String)
     *  nullが返却された場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountStateChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B9CF0386
     */
    protected WEB3AdminFEqConAccountStateChangeCompleteResponse submitStateChange(
        WEB3AdminFEqConAccountStateChangeCompleteRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "submitStateChange(" +
            "WEB3AdminFEqConAccountStateChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )  リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( ) getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, true);
        
        //1.4 get外国株式顧客(String)
        WEB3FEqConTransferDataControlService l_service =
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = 
                l_service.getFeqAccountByAccountId(l_request.accountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFound FeqAccountParams__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }    
        //1.5 validate部店権限(String[]) 部店権限のチェックを行う。 
        l_admin.validateBranchPermission(l_feqAccountParams.getBranchCode());
        
        //1.6 validate取引パスワード(String) 暗証番号のチェックを行う。
        l_admin.validateTradingPassword(l_request.password);
        
        //1.7 get証券会社コード( ) 証券会社コードを取得する。
        
        //1.8 get管理者コード( ) 管理者コードを取得する。
        String l_administratorcode = l_admin.getAdministratorCode();
        
        //1.9 update外国株式顧客(外国株式顧客Params, String, String)  
        //外国株式顧客テーブルを更新する。
        l_service.updateFeqAccount(
            l_feqAccountParams, 
            l_request.updatedAccountOpenStatusDiv, 
            l_administratorcode);
            
        //1.10 (*)リクエストデータ.更新後口座開設状況区分 == "開設済"の場合
        if (WEB3AioAccountDivDef.OPEN_COMPLETE.equals(l_request.updatedAccountOpenStatusDiv))
        {
            //1.10.1 get顧客() 顧客オブジェクトを取得する。 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
            WEB3GentradeAccountManager l_gentradeAccMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            MainAccount l_mainAccount =
                l_gentradeAccMgr.getMainAccount(
                    l_feqAccountParams.getInstitutionCode(),
                    l_feqAccountParams.getBranchCode(),
                    l_feqAccountParams.getAccountCode());
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_mainAccount.getDataSourceObject();                    
                        
            String l_strFeqAccOpenDiv = null;
            //MRF口座未開設(get顧客()の戻り値.MRF口座開設区分 == "DEFAULT(口座なし)")の場合、"開設" 
            if(WEB3AccountOpenDef.NOT_OPEN.equals(l_mainAccountRow.getMrfAccOpenDiv()))
            {
                l_strFeqAccOpenDiv = WEB3AccountOpenDef.OPEN;
            }
            //それ以外の場合、"未開設" 
            else
            {
                l_strFeqAccOpenDiv = WEB3AccountOpenDef.NOT_OPEN;
            }
            //1.10.2 update外株口座開設区分(String, String, String, String, String) 
            //顧客の外国証券口座開設区分を"開設"に更新する。
            //証券会社コード：　@get外国株式顧客()の戻り値.証券会社コード 
            //部店コード：　@get外国株式顧客()の戻り値.部店コード 
            //顧客コード：　@get外国株式顧客()の戻り値.顧客コード 
            //更新後外株口座開設区分：　@（以下のとおり） 
            //MRF口座未開設(get顧客()の戻り値.MRF口座開設区分 == "DEFAULT(口座なし)")の場合、"開設" 
            //それ以外の場合、"未開設" 
            //更新者コード：　@get管理者コード()の戻り値
            l_service.updateFeqAccountOpenDiv(
                l_feqAccountParams.getInstitutionCode(),
                l_feqAccountParams.getBranchCode(),
                l_feqAccountParams.getAccountCode(),
                l_strFeqAccOpenDiv,
                l_administratorcode);            
        }

        //1.11 (*)リクエストデータ.更新後口座開設状況区分 == "抹消"の場合
        if (WEB3AioAccountDivDef.DELETE.equals(l_request.updatedAccountOpenStatusDiv))
        {
            //1.11.1 update外株口座開設区分(String, String, String, String, String) 
            //顧客の外国証券口座開設区分を"未開設"に更新する
            l_service.updateFeqAccountOpenDiv(
                l_feqAccountParams.getInstitutionCode(),
                l_feqAccountParams.getBranchCode(),
                l_feqAccountParams.getAccountCode(),
                WEB3AccountOpenDef.DELETED,
                l_administratorcode);
        }

        //1.12 createResponse( ) レスポンスデータを生成する。
        WEB3AdminFEqConAccountStateChangeCompleteResponse l_response =
            (WEB3AdminFEqConAccountStateChangeCompleteResponse) l_request.createResponse();      
            
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
}
@
