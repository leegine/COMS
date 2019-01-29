head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccManagementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者FX口座管理サービス実装クラス(WEB3AdminFXAccManagementServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/25 王蘭芬(中訊)新規作成
                 : 2006/02/24 黄浩澎 (中訊) 仕様変更・モデル489
                 : 2006/03/17 玉岡 (SRA) 仕様変更・モデル521
Revesion History : 2008/05/20 柴双紅 (中訊) 仕様変更・モデルNo.862、No.863、No.864、No.871、No.879、No.880
                                                     モデルNo.881、No.885
Revesion History : 2008/06/23 柴双紅 (中訊) 仕様変更・モデルNo.902、No.903
Revesion History : 2008/09/22 武　@波 (中訊) 仕様変更・モデル1020,1037,1078
Revesion History : 2008/11/14 佐藤 (SCS) 仕様変更・モデル1085
Revesion History : 2009/03/23 車進 (中訊) 仕様変更・モデル1134、1157、ＤＢ更新仕様217
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.define.WEB3AioFxAccountInfoDivDef;
import webbroker3.aio.define.WEB3AioInputNumberDivDef;
import webbroker3.aio.define.WEB3AioAccountOpenCompleteDef;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeInputResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchConditionRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchConditionResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchResponse;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXAccManagementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccTypeDef;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccOpenDiv;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者FX口座管理サービスImpl) <BR>
 * 管理者FX口座管理サービス実装クラス
 * 
 * @@author 王蘭芬 (中訊)
 * @@version 1.0
 */
public class WEB3AdminFXAccManagementServiceImpl implements
    WEB3AdminFXAccManagementService
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccManagementServiceImpl.class);

    /**
     * @@roseuid 41E7934A03B9
     */
    public WEB3AdminFXAccManagementServiceImpl()
    {
    }

    /**
     * (execute) <BR>
     * 管理者FX口座管理処理を行う。 <BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドを呼び分ける。 <BR>
     * <BR>
     * ○管理者・FX口座情報検索条件入力リクエストの場合 <BR>
     * this.get条件入力画面()メソッドをコールする。 <BR>
     * <BR>
     * ○管理者・FX口座情報検索リクエストの場合 <BR>
     * this.get検索結果()メソッドをコールする。 <BR>
     * <BR>
     * ○管理者・FX口座情報変更入力リクエストの場合 <BR>
     * this.get変更入力画面()メソッドをコールする。 <BR>
     * <BR>
     * ○管理者・FX口座情報変更確認リクエストの場合 <BR>
     * this.validate変更()メソッドをコールする。 <BR>
     * <BR>
     * ○管理者・FX口座情報変更完了リクエストの場合 <BR>
     * this.submit変更()メソッドをコールする。
     * 
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD377602AC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMETHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(l_strMETHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMETHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        
        //リクエストデータの型により、以下のメソッドを 
        //呼び分ける。 

        //○管理者・FX口座情報検索条件入力リクエストの場合 
        if (l_request instanceof WEB3AdminFXAccInfoSearchConditionRequest)
        {
            //　@this.get条件入力画面()メソッドをコールする。 
            l_response =
                this.getCondInputScreen((WEB3AdminFXAccInfoSearchConditionRequest) l_request);
        }
        //○管理者・FX口座情報検索リクエストの場合 
        else if (l_request instanceof WEB3AdminFXAccInfoSearchRequest)
        {
            //　@this.get検索結果()メソッドをコールする。 
            l_response =
                this.getQueryResult((WEB3AdminFXAccInfoSearchRequest) l_request);
        }
        //○管理者・FX口座情報変更入力リクエストの場合 
        else if (l_request instanceof WEB3AdminFXAccInfoChangeInputRequest)
        {
            //　@this.get変更入力画面()メソッドをコールする。 
            l_response =
                this.getUpdInputScreen((WEB3AdminFXAccInfoChangeInputRequest) l_request);
        }
        //○管理者・FX口座情報変更確認リクエストの場合 
        else if (l_request instanceof WEB3AdminFXAccInfoChangeConfirmRequest)
        {
            //　@this.validate変更()メソッドをコールする。 
            l_response =
                this.validateChange((WEB3AdminFXAccInfoChangeConfirmRequest) l_request);
        }
        //○管理者・FX口座情報変更完了リクエストの場合 
        else if (l_request instanceof WEB3AdminFXAccInfoChangeCompleteRequest)
        {
            //　@this.submit変更()メソッドをコールする。
            l_response =
                this.submitChange((WEB3AdminFXAccInfoChangeCompleteRequest) l_request);
        }
       else
        {
            log.debug(l_strMETHOD_NAME
                    + "リクエストデータがタイプ不正, but is " + l_request.getClass().getName());
            log.exiting(l_strMETHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                l_strMETHOD_NAME);
        }
        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (get条件入力画面) <BR>
     * (管理者FX口座管理)口座情報検索条件入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座管理サービス)get条件入力画面」参照
     * 
     * @@param l_request - 管理者・FX口座情報検索条件入力リクエストオブジェクト
     * @@return WEB3AdminFXAccInfoSearchConditionResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD380D022F
     */
    protected WEB3AdminFXAccInfoSearchConditionResponse getCondInputScreen(
        WEB3AdminFXAccInfoSearchConditionRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "getCondInputScreen(WEB3AdminFXAccInfoSearchConditionRequest l_request)";
        log.entering(l_strMETHOD_NAME);

        //1.1 validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2  getInstanceFromログイン情報( )
        // ログイン情報から管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3 管理者権限のチェックを行う。 
        // [引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.為替保証金管理(口座管理・口座開設管理) 
        // is更新：　@false(更新なし) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
            false);
        
        // 1.4 部店権限のチェックを行う。
        // [引数] 
        // 部店コード：　@リクエストデータ.部店コード一覧
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        // 1.5 createResponse
        WEB3AdminFXAccInfoSearchConditionResponse l_response = 
            (WEB3AdminFXAccInfoSearchConditionResponse)l_request.createResponse();
        
        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (get検索結果) <BR>
     * (管理者FX口座管理)口座情報検索処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座管理サービス)get検索結果」参照 <BR>
     * ======================================================== <BR>
     * シーケンス図(「為替保証金サービスモデル（管理者）/ 管理者FX口座管理」) <BR>
     * (get検索結果)getQueryResult <BR>
     * : 1.8 getFX顧客(String, String[], String)nullが返却された の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01808 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - 管理者・FX口座情報検索リクエストオブジェクト
     * @@return WEB3AdminFXAccInfoSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD380D024E
     */
    protected WEB3AdminFXAccInfoSearchResponse getQueryResult(
        WEB3AdminFXAccInfoSearchRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "getQueryResult(WEB3AdminFXAccInfoSearchRequest l_request)";
        log.entering(l_strMETHOD_NAME);

        // 1 ) リクエストデータの整合性をチェックする。
        l_request.validate();
        
        // 2 ) ログイン情報から管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) 管理者権限のチェックを行う。 
        // [引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.為替保証金管理(口座管理・口座開設管理) 
        // is更新：　@false(更新なし) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
            false);
        
        // 4 ) 部店権限のチェックを行う。
        // [引数] 
        // 部店コード：　@リクエストデータ.部店コード
        l_admin.validateBranchPermission(l_request.branchCode);
        
        // 5 ) 証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // 6 ) 検索条件文字列を作成する。 
        // [引数] 
        // 証券会社コード：　@get証券会社コード()の戻り値 
        // 部店コード：　@リクエストデータ.部店コード 
        // 入力番号区分：　@リクエストデータ.入力番号区分 
        // 入力番号：　@リクエストデータ.入力番号
        // FXシステムコード：　@リクエストデータ.FXシステムコード
        String l_strWhere = 
            this.createQueryString(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.inputNumberDiv,
                l_request.inputNumber,
                l_request.fxSystemCode);
        
        // 7 ) 検索条件データコンテナを作成する。 
        // [引数] 
        // 証券会社コード：　@get証券会社コード()の戻り値 
        // 部店コード：　@リクエストデータ.部店コード 
        // 入力番号区分：　@リクエストデータ.入力番号区分 
        // 入力番号：　@リクエストデータ.入力番号
        // FXシステムコード：　@リクエストデータ.FXシステムコード
        String[] l_arrVars = 
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.inputNumberDiv,
                l_request.inputNumber,
                l_request.fxSystemCode);
        
        // 8 )  getFX顧客(String, String[], String)
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class); 
        
        // FX顧客を取得する。
        // [引数] 
        // 検索条件文字列：　@create検索条件文字列()の戻り値 
        // 検索条件でーたコンテナ：　@create検索条件データコンテナ()の戻り値 
        // ソート条件：　@null
        FxAccountParams[] l_fxAccountParamses = 
            l_controlService.getFXAccounts(l_strWhere, l_arrVars, null);
        
        // getFX顧客の戻り値がnullである の場合
        if (l_fxAccountParamses == null)
        {
            log.debug("FX顧客を取得できない");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01808,
                this.getClass().getName() + "." + l_strMETHOD_NAME,
                "FX顧客を取得できない");
        }
        
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
		//拡張アカウントマネージャ取得する    
		WEB3GentradeAccountManager l_accountManager =
			(WEB3GentradeAccountManager) l_finApp.getAccountManager();
		
		boolean l_mainAccountFlg = true;	
		try
		{
			// 9 ) 顧客インスタンスを取得する。 
			// [引数] 
			// 証券会社コード： getFX顧客()の戻り値.証券会社コード 
			// 部店コード： getFX顧客()の戻り値.部店コード 
			// 口座コード： getFX顧客()の戻り値.顧客コード 
			WEB3GentradeMainAccount l_mainAccount = 
				l_accountManager.getMainAccount(
					l_fxAccountParamses[0].getInstitutionCode(),
					l_fxAccountParamses[0].getBranchCode(),
					l_fxAccountParamses[0].getAccountCode()
					);			
		}
		catch(WEB3BaseException l_ex)
		{
			l_mainAccountFlg = false;			
		}
		// update end
       
        
        // 9 ) createFX口座情報一覧
        //FX口座情報一覧を作成する。 
        //[引数] 
        //証券会社コード：　@getFX顧客()の戻り値.証券会社コード 
        //部店コード：　@getFX顧客()の戻り値.部店コード 
        //顧客コード：　@getFX顧客()の戻り値.顧客コード
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        WEB3FXAccInformationUnit[] l_fxAccINformationUnits = 
            l_controlService.createFXAccInformationUnits(
                l_fxAccountParamses[0].getInstitutionCode(),
                l_fxAccountParamses[0].getBranchCode(),
                l_fxAccountParamses[0].getAccountCode(),
                l_request.fxSystemCode);
        
        // 10 ) createResponse( )
        // レスポンスデータを生成する。
        WEB3AdminFXAccInfoSearchResponse l_response = 
            (WEB3AdminFXAccInfoSearchResponse)l_request.createResponse();
        
        // 11 )プロパティセット
        //(*)レスポンスデータにプロパティをセットする。
        // ※FX顧客は、getFX顧客()の戻り値の0番目の要素を使用する。
        //(本機@能では必ず一意になる条件が入力
        
        // レスポンスデータ.顧客コード       ＝　@FX顧客.顧客コード
        String l_strAccountCodeToSet = l_fxAccountParamses[0].getAccountCode();
        if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
        {
            l_strAccountCodeToSet = l_fxAccountParamses[0].getAccountCode().substring(0, 6);
        }
        l_response.accountCode = l_strAccountCodeToSet;
        //レスポンスデータ.(FX)ログインID       ＝　@FX顧客.FXログインID
        l_response.fxLoginId = l_fxAccountParamses[0].getFxLoginId() + "";
        //レスポンスデータ.(FX)名前(姓)      ＝　@FX顧客.名前(姓)
        l_response.fxFirstName = l_fxAccountParamses[0].getFxFirstName();
        //レスポンスデータ.(FX)名前(名)      ＝　@FX顧客.名前(名)
        l_response.fxLastName = l_fxAccountParamses[0].getFxLastName();
        //レスポンスデータ.(FX)メールアドレス    ＝　@FX顧客.FXメールアドレス
        l_response.fxMailAddress = l_fxAccountParamses[0].getFxMailAddress();
        //レスポンスデータ.FX口座情報一覧   ＝　@createFX口座情報一覧()の戻り値
        l_response.fxAccInformationList = l_fxAccINformationUnits;
        //レスポンスデータ.口座開設状況区分   ＝　@FX顧客.FX口座区分
        //get顧客()にて例外が発生した場合は、「99(口座抹消)」をセット
        if ( l_mainAccountFlg)
        {
			l_response.accountOpenStatusDiv = l_fxAccountParamses[0].getFxAccountDiv();
        }
        else
        {
			l_response.accountOpenStatusDiv = WEB3AioAccountOpenCompleteDef.OPEN_DELETE;
        }

        //レスポンスデータ.FXシステムコード = FX顧客.FXシステムコード
        l_response.fxSystemCode = l_fxAccountParamses[0].getFxSystemCode();

        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (get変更入力画面) <BR>
     * (管理者FX口座管理)口座情報変更入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座管理サービス)get変更入力画面」参照 <BR>
     * ======================================================== <BR>
     * シーケンス図(「為替保証金サービスモデル（管理者）/ 管理者FX口座管理」) <BR>
     * （get変更入力画面）getUpdInputScreen <BR>
     * : 1.6 getFX顧客(String, String, String)戻り値がnull の場合、例外をスローする。<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01808 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - 管理者・FX口座情報変更入力リクエストオブジェクト
     * @@return WEB3AdminFXAccInfoChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD380D025D
     */
    protected WEB3AdminFXAccInfoChangeInputResponse getUpdInputScreen(
        WEB3AdminFXAccInfoChangeInputRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "getUpdInputScreen(WEB3AdminFXAccInfoChangeInputRequest l_request)";
        log.entering(l_strMETHOD_NAME);

        // 1 ) リクエストデータの整合性をチェックする。
        l_request.validate();
        
        // 2 ) ログイン情報から管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) 管理者権限のチェックを行う。 
        // [引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.為替保証金管理(口座管理・口座開設管理) 
        // is更新：　@false(更新なし) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
            false);
        
        // 4 ) 部店権限のチェックを行う。
        // [引数] 
        // 部店コード：　@リクエストデータ.部店コード
        l_admin.validateBranchPermission(l_request.branchCode);
        
        // 5 ) 証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class); 

        //get会社別FXシステム条件
        // (証券会社コード : String, 部店コード : String, FXシステムコード : String)
        //[引数の設定]
        // 証券会社コード：　@get証券会社コード()の戻り値
        // 部店コード：　@リクエストデータ.部店コード
        // FXシステムコード：　@リクエストデータ.FXシステムコード
        CompFxConditionParams l_compFxConditionParams;
        try
        {
            l_compFxConditionParams = l_controlService.getCompFxCondition(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(l_strMETHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMETHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getFX顧客(String, String, String, String)
        // FX顧客を取得する。
        // [引数] 
        // 証券会社コード：　@get証券会社コード()の戻り値 
        // 部店コード：　@リクエストデータ.部店コード 
        // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード
        // 顧客コード：　@リクエストデータ.顧客コード
        FxAccountParams l_fxAccountParams = null;
        try
        {
            l_fxAccountParams = l_controlService.getFXAccount(l_strInstitutionCode, 
                l_request.branchCode, l_compFxConditionParams.getFxSystemCode(),
                l_request.accountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("FX顧客を取得できない", l_ex);
            l_fxAccountParams = null;
        }
        
        // getFX顧客の戻り値がnullである の場合
        if (l_fxAccountParams == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01808,
                this.getClass().getName() + "." + l_strMETHOD_NAME,
                "FX顧客を取得できない");
        }
        
        //createFX口座情報一覧(String, String, String, String)
        //FX口座情報一覧を作成する。 
        //[引数] 
        //証券会社コード：　@getFX顧客()の戻り値.証券会社コード 
        //部店コード：　@getFX顧客()の戻り値.部店コード 
        //顧客コード：　@getFX顧客()の戻り値.顧客コード
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        WEB3FXAccInformationUnit[] l_fxAccINformationUnits = 
            l_controlService.createFXAccInformationUnits(
                l_fxAccountParams.getInstitutionCode(),
                l_fxAccountParams.getBranchCode(),
                l_fxAccountParams.getAccountCode(),
                l_request.fxSystemCode);
        
        // 10 ) createResponse( )
        // レスポンスデータを生成する。
        WEB3AdminFXAccInfoChangeInputResponse l_response = 
            (WEB3AdminFXAccInfoChangeInputResponse)l_request.createResponse();
        
        // 11 )プロパティセット
        //(*)レスポンスデータにプロパティをセットする。
        
        //レスポンスデータ.部店コード        ＝　@FX顧客.部店コード
        l_response.branchCode = l_fxAccountParams.getBranchCode();
        //レスポンスデータ.顧客コード       ＝　@FX顧客.顧客コード
        String l_strAccountCodeToSet = l_fxAccountParams.getAccountCode();
        if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
        {
            l_strAccountCodeToSet = l_fxAccountParams.getAccountCode().substring(0, 6);
        }
        l_response.accountCode = l_strAccountCodeToSet;
        //レスポンスデータ.(FX)ログインID       ＝　@FX顧客.FXログインID
        l_response.fxLoginId = l_fxAccountParams.getFxLoginId() + "";
        //レスポンスデータ.(FX)名前(姓)      ＝　@FX顧客.名前(姓)
        l_response.fxFirstName = l_fxAccountParams.getFxFirstName();
        //レスポンスデータ.(FX)名前(名)      ＝　@FX顧客.名前(名)
        l_response.fxLastName = l_fxAccountParams.getFxLastName();
        //レスポンスデータ.(FX)メールアドレス    ＝　@FX顧客.FXメールアドレス
        l_response.fxMailAddress = l_fxAccountParams.getFxMailAddress();
        //レスポンスデータ.FX口座情報一覧   ＝　@createFX口座情報一覧()の戻り値
        l_response.fxAccInformationList = l_fxAccINformationUnits;
        //レスポンスデータ.口座開設状況区分   ＝　@FX顧客.FX口座区分
        l_response.accountOpenStatusDiv = l_fxAccountParams.getFxAccountDiv();

        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更) <BR>
     * (管理者FX口座管理)口座情報変更確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座管理サービス)validate変更」参照
     * @@param l_request - 管理者・FX口座情報変更確認リクエストオブジェクト
     * @@return WEB3AdminFXAccInfoChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD380D027D
     */
    protected WEB3AdminFXAccInfoChangeConfirmResponse validateChange(
        WEB3AdminFXAccInfoChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "validateChange(WEB3AdminFXAccInfoChangeConfirmRequest l_request)";
        log.entering(l_strMETHOD_NAME);

        // 1 ) リクエストデータの整合性をチェックする。
        l_request.validate();
        
        // 2 ) ログイン情報から管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) 管理者権限のチェックを行う。 
        // [引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.為替保証金管理(口座管理・口座開設管理) 
        // is更新：　@true(更新あり) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
            true);
        
        // 4 ) 部店権限のチェックを行う。
        // [引数] 
        // 部店コード：　@リクエストデータ.部店コード
        l_admin.validateBranchPermission(l_request.branchCode);
        
        // 5 )  (*)リクエストデータ.更新後メールアドレス != nullの場合
        // ―――――ステープ1 )
        if(l_request.updatedMailAddress != null)
        {
            // 5.1) isMailAddress(String)
            // メールアドレスに使用できる文字のみセットされているかをチェックする。
            // [引数] 
            // l_str：　@リクエストデータ.更新後メールアドレス
            if (!WEB3StringTypeUtility.isMailAddress(l_request.updatedMailAddress))
            {
                log.debug("メールアドレスチェックエラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                    this.getClass().getName() + "." + l_strMETHOD_NAME,
                    "リクエストデータ.FXメールアドレス≠適切な内容" + 
                    "リクエストデータ.FXメールアドレス = " + l_request.updatedMailAddress); 
            }
        }
        
        // 6 ) createResponse( )
        // レスポンスデータを生成する。
        WEB3AdminFXAccInfoChangeConfirmResponse l_response = 
            (WEB3AdminFXAccInfoChangeConfirmResponse)l_request.createResponse();
        
        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (submit変更) <BR>
     * (管理者FX口座管理)口座情報変更完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座管理サービス)submit変更」参照 <BR>
     * ======================================================== <BR>
     * シーケンス図(「為替保証金サービスモデル（管理者）/ 管理者FX口座管理」) <BR>
     * （submit変更）submitUpd <BR>
     * : 1.9 getFX顧客(String, String, String)戻り値がnull の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01808 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - 管理者・FX口座情報変更完了リクエストオブジェクト
     * @@return WEB3AdminFXAccInfoChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD380D029C
     */
    protected WEB3AdminFXAccInfoChangeCompleteResponse submitChange(
        WEB3AdminFXAccInfoChangeCompleteRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "submitChange(WEB3AdminFXAccInfoChangeCompleteRequest l_request)";
        log.entering(l_strMETHOD_NAME);

        // 1.1 ) リクエストデータの整合性をチェックする。
        l_request.validate();
        
        // 1.2 ) ログイン情報から管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3 ) 管理者権限のチェックを行う。 
        // [引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.為替保証金管理(口座管理・口座開設管理) 
        // is更新：　@true(更新あり) 
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
                true);
        
        // 1.4 ) 部店権限のチェックを行う。 
        // [引数] 
        // 部店コード：　@リクエストデータ.FX検索条件.部店コード
        l_admin.validateBranchPermission(l_request.branchCode);
        
        // 1.5 ) 暗証番号のチェックを行う。 
        // [引数] 
        // パスワード：　@リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);

        // 1.6 ) 証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        // 1.7 ) 管理者コードを取得する。
        String l_strAdminCode =  l_admin.getAdministratorCode();
        
        // 1.8 )  (*)リクエストデータ.更新後メールアドレス != nullの場合
        // ―――――ステープ1 )
        if(l_request.updatedMailAddress != null)
        {
            // 1.8.1) isMailAddress(String)
            // メールアドレスに使用できる文字のみセットされているかをチェックする。
            // [引数] 
            // l_str：　@リクエストデータ.更新後メールアドレス
            if (!WEB3StringTypeUtility.isMailAddress(l_request.updatedMailAddress))
            {
                log.debug("メールアドレスチェックエラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                    this.getClass().getName() + "." + l_strMETHOD_NAME,
                    "リクエストデータ.FXメールアドレス≠適切な内容" + 
                    "リクエストデータ.FXメールアドレス = " + l_request.updatedMailAddress); 
            }
        }
        
        //getFX顧客(String, String, String, String)
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class); 

        //get会社別FXシステム条件(String, String, String)
        //[引数の設定]
        //証券会社コード：　@get証券会社コード()の戻り値
        //部店コード：　@リクエストデータ.部店コード
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        CompFxConditionParams l_compFxConditionParams;
        try
        {
            l_compFxConditionParams = l_controlService.getCompFxCondition(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(l_strMETHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMETHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // FX顧客を取得する。
        // [引数] 
        // 証券会社コード：　@get証券会社コード()の戻り値 
        // 部店コード：　@リクエストデータ.部店コード 
        // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード
        // 顧客コード：　@リクエストデータ.顧客コード
        // 
        FxAccountParams l_fxAccountParams = null;
        try
        {
            l_fxAccountParams = l_controlService.getFXAccount(l_strInstitutionCode, 
                l_request.branchCode, l_compFxConditionParams.getFxSystemCode(), l_request.accountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("FX顧客を取得できない", l_ex);
            l_fxAccountParams = null;
        }
        
        // getFX顧客の戻り値がnullである の場合
        if (l_fxAccountParams == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01808,
                this.getClass().getName() + "." + l_strMETHOD_NAME,
                "FX顧客を取得できない");
        }

        // リクエストデータ.更新後口座開設状況区分 != nullの場合
        if(l_request.updatedAccountOpenStatusDiv != null){
            //updateFX顧客(FX顧客Params, String, String)
            //FX顧客テーブルの口座開設状況区分を更新する。 
            //[引数] 
            //FX顧客Params：　@getFX顧客()の戻り値 
            //更新後口座開設状況区分：　@リクエストデータ.更新後口座開設状況区分 
            //更新者コード：　@get管理者コード()の戻り値
            l_controlService.updateFXAccount(l_fxAccountParams, 
                    l_request.updatedAccountOpenStatusDiv,
                    l_strAdminCode);
        }
        
        // リクエストデータ.更新後メールアドレス != nullの場合
        if(l_request.updatedMailAddress != null)
        {
            //get会社別FXシステム条件の戻り値外部接続システムコード=="01:GFT"の場合
        	if (WEB3ExtConnectSystemCodeDef.GFT.equals(
        	    l_compFxConditionParams.getExtConnectSystemCode()))
        	{
                //FXシステムコード一覧を取得する。
                //[引数の設定]
                //証券会社コード：　@get証券会社コード()の戻り値
                //部店コード：　@リクエストデータ.部店コード
                ArrayList l_lisFxSystemCodeLists =
                    l_controlService.getGFTFxSystemCodeLists(
                        l_strInstitutionCode,
                        l_request.branchCode);

                //updateFX顧客(FX顧客Params, String, String, ArrayList)
                //FX顧客テーブルのメールアドレスを更新する。
                //[引数]
                //FX顧客Params：　@getFX顧客()の戻り値
                //更新後メールアドレス：　@リクエストデータ.更新後メールアドレス
                //更新者コード：　@get管理者コード()の戻り値
                //FXシステムコード一覧：　@getGFTFXシステムコード一覧()の戻り値
                l_controlService.updateFXAccount(
                    l_fxAccountParams,
                    l_request.updatedMailAddress,
                    l_strAdminCode,
                    l_lisFxSystemCodeLists);
        	}
        	//get会社別FXシステム条件の戻り値外部接続システムコード=="01:GFT"以外の場合
        	else
        	{
                //updateFX顧客メールアドレス(FX顧客Params, String, String)
                //[引数]
                //　@FX顧客Params：　@getFX顧客()の戻り値
                //　@更新後メールアドレス：　@リクエストデータ.更新後メールアドレス
                //　@更新者コード：　@get管理者コード()の戻り値
                l_controlService.updateFXAccountMailAddress(
                    l_fxAccountParams,
                    l_request.updatedMailAddress,
                    l_strAdminCode);
        	}
        }
        
        // 1.11 )  (*)リクエストデータ.更新後FX口座情報一覧 != nullの場合
        // ―――――ステープ1 )
        
        if (l_request.updatedFxAccInformationList != null)
        {
			// 1.11.1 ) (*)リクエストデータ.更新後FX口座情報一覧の要素数分Loop処理
			for (int i = 0; i < l_request.updatedFxAccInformationList.length; i ++)
			{
				WEB3FXAccInformationUnit l_fxAccInfoUnit = 
					l_request.updatedFxAccInformationList[i];
            
				FxAccountCodeParams l_fxAccountCodeParams = null;
            
				//getFX口座番号(String, String, String, String, String)
				//FX口座番号を取得する。 
				//[引数] 
				//証券会社コード：　@getFX顧客()の戻り値.証券会社コード 
				//部店コード：　@getFX顧客()の戻り値.部店コード 
				//顧客コード：　@getFX顧客()の戻り値.顧客コード 
				//コース区分：　@処理対象のFX口座情報.コース区分
                //FXシステムコード：　@getFX顧客()の戻り値.FXシステムコード
				try
				{
					l_fxAccountCodeParams = l_controlService.getFXAccountCode(
						l_fxAccountParams.getInstitutionCode(),
						l_fxAccountParams.getBranchCode(),
						l_fxAccountParams.getAccountCode(),
						l_fxAccInfoUnit.fxCourseDiv,
                        l_fxAccountParams.getFxSystemCode());
				}
				catch (NotFoundException l_ex)
				{
					log.debug("NotFoundException: ", l_ex);
					throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80005,
						this.getClass().getName() + "." + l_strMETHOD_NAME,
						l_ex.getMessage(),
						l_ex);
				}
            
				// 1.11.1.2 ) updateFX口座番号(FX口座番号Params, String, String)
				//FX口座番号テーブルを更新する。 
				// [引数] 
				// FX口座番号Params：　@getFX口座番号()の戻り値 
				// 更新後口座番号：　@処理対象のFX口座情報.口座番号 
				// 更新者コード：　@get管理者コード()の戻り値
				l_controlService.updateFXAccountCode(l_fxAccountCodeParams, 
					l_fxAccInfoUnit.fxAccountCode, l_strAdminCode);
			}
        }

        //リクエストデータ.処理区分 == "口座開設状況変更"の場合
        if (WEB3AioFxAccountInfoDivDef.ACCOUNT_OPEN_STATUS.equals(l_request.operationDiv))
        {
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

            //顧客オブジェクトを取得する。
            //[引数]
            //証券会社コード：　@getFX顧客()の戻り値.証券会社コード
            //部店コード：　@getFX顧客()の戻り値.部店コード
            //口座コード：　@getFX顧客()の戻り値.顧客コード
            WEB3GentradeMainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_fxAccountParams.getInstitutionCode(),
                    l_fxAccountParams.getBranchCode(),
                    l_fxAccountParams.getAccountCode());

            //FX口座開設区分を取得する。
            //[引数]
            //更新後口座開設状況区分：　@リクエストデータ.更新後口座開設状況区分
            String l_strFXAccOpenDiv =
                l_controlService.getFXAccountOpenDiv(l_request.updatedAccountOpenStatusDiv);

            //get会社別FXシステム条件の戻り値.口座種別=="01:(FX)”の場合
            if (WEB3AccTypeDef.FX.equals(l_compFxConditionParams.getAccType()))
            {
                //顧客のFX口座開設区分を更新する。
                //[引数]
                //　@証券会社コード：　@getFX顧客()の戻り値.証券会社コード
                //　@部店コード：　@getFX顧客()の戻り値.部店コード
                //　@顧客コード：　@getFX顧客()の戻り値.顧客コード
                //　@更新後FX口座開設区分：　@getFX口座開設区分()の戻り値
                //　@更新者コード：　@get管理者コード()の戻り値
                l_controlService.updateFXAccountOpenDiv(
                    l_fxAccountParams.getInstitutionCode(),
                    l_fxAccountParams.getBranchCode(),
                    l_fxAccountParams.getAccountCode(),
                    l_strFXAccOpenDiv,
                    l_strAdminCode);
            }

            //口座開設区分テーブル.口座開設区分を更新する。
            //[引数]
            //　@口座ID：　@get顧客()の戻り値.口座ID
            //　@口座種別：get会社別FXシステム条件（）の戻り値.口座種別
            //　@口座開設区分：　@getFX口座開設区分()の戻り値
            //　@更新者コード：　@get管理者コード()の戻り値
            WEB3GentradeAccOpenDiv l_genAccOpenDiv = new WEB3GentradeAccOpenDiv();
            l_genAccOpenDiv.updateAccOpenDiv(
                l_mainAccount.getAccountId(),
                l_compFxConditionParams.getAccType(),
                l_strFXAccOpenDiv,
                l_strAdminCode);
        }

        // 1.13 ) createResponse( )
        // レスポンスデータを生成する。
        WEB3AdminFXAccInfoChangeCompleteResponse l_response = 
            (WEB3AdminFXAccInfoChangeCompleteResponse)l_request.createResponse();
        
        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列) <BR>
     * 検索条件文字列を作成する。 <BR>
     * <BR>
     * １）空の検索条件文字列(：String)を生成する。 <BR>
     * <BR>
     * ２）証券会社条件を検索条件に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += " institution_code = ? "<BR>
     * <BR>
     * ３）部店条件を検索条件に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and branch_code = ? "<BR>
     * <BR>
     * ４）入力された番号を検索条件に追加する。 <BR>
     * パラメータ.入力番号区分が、 <BR>
     * ["FXログインID"の場合] <BR>
     * FXログインID条件を検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and fx_login_id = ? "<BR>
     * <BR>
     * ["FX口座番号" or "顧客コード"の場合] <BR>
     * 顧客コード条件を検索条件文字列に追加する。 <BR>
     * ※先頭6byteで比較する。 <BR>
     * <BR>
     * 検索条件文字列 += "and substr(account_code, 0, 6) = ? "<BR>
     * <BR>
     * ５）FXシステムコード<BR>
     * <BR>
     * 　@パラメータ.FXシステムコード != nullの場合FXシステムコードを検索条件に追加する。<BR>
     * 　@検索条件文字列 += "and fx_system_code= ? "<BR>
     * <BR>
     * ６）作成した検索条件文字列を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strInputNumberDiv - 入力番号区分
     * 1：FXログインID 2：FX口座番号 3：顧客コード
     * @@param l_strInputNumber - 入力番号
     * 入力番号区分＝1（FXログインID）の場合、<BR>
     * FX）ログインID 入力番号区分＝2（FX口座番号）の場合、<BR>
     * FX）口座番号 入力番号区分＝3（顧客コード）の場合、顧客コード
     * @@param l_strFxSystemCode - FXシステムコード
     * @@return String
     * @@roseuid 41BECB5F00BE
     */
    protected String createQueryString(String l_strInstitutionCode,
        String l_strBranchCode, String l_strInputNumberDiv,
        String l_strInputNumber, String l_strFxSystemCode)
    {
        String l_strMETHOD_NAME = "createQueryString(String l_strInstitutionCode," +
            "String l_strBranchCode, String l_strInputNumberDiv,String l_strInputNumber, " +
            "String l_strFxSystemCode)";
        log.entering(l_strMETHOD_NAME);

        //検索条件文字列を作成する。 

        //１）空の検索条件文字列(：String)を生成する。 
        String l_strQueryString = "";
        
        //２）証券会社条件を検索条件に追加する。 
        //　@検索条件文字列 += " institution_code = ? " 
        l_strQueryString += " institution_code = ? ";

        //３）部店条件を検索条件に追加する。 
        //　@検索条件文字列 += "and branch_code = ? " 
        l_strQueryString += "and branch_code = ? ";

        //４）入力された番号を検索条件に追加する。 
        //　@パラメータ.入力番号区分が、 
        //　@["FXログインID"の場合] 
        //　@　@FXログインID条件を検索条件文字列に追加する。 
        if (WEB3AioInputNumberDivDef.FX_LOGINID.equals(l_strInputNumberDiv))
        {
            //　@　@検索条件文字列 += "and fx_login_id = ? "
            l_strQueryString += "and fx_login_id = ? ";
        }

        //　@["FX口座番号" or "顧客コード"の場合] 
        //　@　@顧客コード条件を検索条件文字列に追加する。 
        //　@　@※先頭6byteで比較する。 
        if (WEB3AioInputNumberDivDef.FX_ACCOUNT_CODE.equals(l_strInputNumberDiv)
            || WEB3AioInputNumberDivDef.ACCOUNT_CODE.equals(l_strInputNumberDiv))
        {
            //　@　@検索条件文字列 += "and substr(account_code, 0, 6) = ? "
            l_strQueryString += "and substr(account_code, 0, 6) = ? ";
        }

        //５）FXシステムコード
        //パラメータ.FXシステムコード != nullの場合FXシステムコードを検索条件に追加する。
        //　@検索条件文字列 += "and fx_system_code= ? "
        if (l_strFxSystemCode != null)
        {
            l_strQueryString += " and fx_system_code = ? ";
        }

        //作成した検索条件文字列を返却する。 
        log.exiting(l_strMETHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create検索条件データコンテナ) <BR>
     * 検索条件文字列を作成する。 <BR>
     * <BR>
     * １）ArrayListを生成する。 <BR>
     * <BR>
     * ２）証券会社条件を追加する。 <BR>
     * パラメータ.証券会社コードをArrayListに追加する。 <BR>
     * <BR>
     * ３）部店条件を追加する。 <BR>
     * パラメータ.部店コードをArrayListに追加する。 <BR>
     * <BR>
     * ４）入力された番号を追加する。 <BR>
     * [パラメータ.入力番号区分 == "FX口座番号"の場合] <BR>
     * FXデータ制御サービスImpl.get顧客コード()の戻り値の<BR>
     * 先頭6byteをArrayListに追加する。 <BR>
     * <BR>
     * [get顧客コード()にセットするパラメータ] <BR>
     * 証券会社コード： パラメータ.証券会社コード <BR>
     * 部店コード： パラメータ.部店コード <BR>
     * FX口座番号： パラメータ.入力番号 <BR>
     * FXシステムコード：　@パラメータ.FXシステムコード<BR>
     * <BR>
     * [上記以外の場合] <BR>
     * パラメータ.入力番号をArrayListに追加する。 <BR>
     * <BR>
     * ５）FXシステムコードを追加する。<BR>
     * 　@　@[パラメータ.FXシステムコード != nullの場合]<BR>
     * 　@　@　@パラメータ.FXシステムコードをArrayListに追加する。<BR>
     * <BR>
     * ６）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strInputNumberDiv - 入力番号区分
     * 1：FXログインID 2：FX口座番号 3：顧客コード
     * @@param l_strInputNumber - 入力番号
     * 入力番号区分＝1（FXログインID）の場合、<BR>
     * FX）ログインID 入力番号区分＝2（FX口座番号）の場合、<BR>
     * FX）口座番号 入力番号区分＝3（顧客コード）の場合、顧客コード
     * @@param l_strFxSystemCode - FXシステムコード
     * @@return String[]
     * @@roseuid 41BECB5F00CE
     */
    protected String[] createQueryContainer(String l_strInstitutionCode,
        String l_strBranchCode, String l_strInputNumberDiv,
        String l_strInputNumber, String l_strFxSystemCode)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "createQueryContainer(String l_strInstitutionCode," +
            "String l_strBranchCode, String l_strInputNumberDiv,String l_strInputNumber, " +
            "String l_strFxSystemCode)";
        log.entering(l_strMETHOD_NAME);

        //検索条件文字列を作成する。 

        //１）ArrayListを生成する。 
        List l_lisQuereContainer = new ArrayList();
        
        //２）証券会社条件を追加する。 
        //　@パラメータ.証券会社コードをArrayListに追加する。 
        l_lisQuereContainer.add(l_strInstitutionCode);
        
        //３）部店条件を追加する。 
        //　@パラメータ.部店コードをArrayListに追加する。 
        l_lisQuereContainer.add(l_strBranchCode);
        
        //４）入力された番号を追加する。 
        //　@[パラメータ.入力番号区分 == "FX口座番号"の場合] 
        if (WEB3AioInputNumberDivDef.FX_ACCOUNT_CODE.equals(l_strInputNumberDiv))
        {
            WEB3FXDataControlService l_controlService = 
                (WEB3FXDataControlService) Services.getService(
                    WEB3FXDataControlService.class); 
            //　@　@FXデータ制御サービスImpl.get顧客コード()の戻り値の
            //　@　@先頭6byteをArrayListに追加する。 
            //
            //　@　@[get顧客コード()にセットするパラメータ] 
            //　@　@　@証券会社コード：　@パラメータ.証券会社コード 
            //　@　@　@部店コード：　@パラメータ.部店コード 
            //　@　@　@FX口座番号：　@パラメータ.入力番号
            //      FXシステムコード：　@パラメータ.FXシステムコード
            try
            {
                String l_strAccountCode =
                    l_controlService.getAccountCode(l_strInstitutionCode, 
                        l_strBranchCode, l_strInputNumber, l_strFxSystemCode);
                //　@　@FXデータ制御サービスImpl.get顧客コード()の戻り値の
                //　@　@先頭6byteをArrayListに追加する。 
                l_lisQuereContainer.add(l_strAccountCode.substring(0, 6));
            }
            catch (NotFoundException l_ex)
            {
                log.debug("NotFoundException: ", l_ex);
				log.debug("FX顧客を取得できない");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01808,
					this.getClass().getName() + "." + l_strMETHOD_NAME,
					"FX顧客を取得できない",
					l_ex);
            }
        }
        //　@[上記以外の場合] 
        //　@　@パラメータ.入力番号をArrayListに追加する。 
        else
        {
            l_lisQuereContainer.add(l_strInputNumber);
        }

        //FXシステムコードを追加する。
        //　@[パラメータ.FXシステムコード != nullの場合]
        //　@　@パラメータ.FXシステムコードをArrayListに追加する。
        if (l_strFxSystemCode != null)
        {
            l_lisQuereContainer.add(l_strFxSystemCode);
        }

        //生成したArrayList.toArray()の戻り値を返却する。 
        String[] l_strQuereContainers = new String[l_lisQuereContainer.size()];
        l_lisQuereContainer.toArray(l_strQuereContainers);
        
        log.exiting(l_strMETHOD_NAME);
        return l_strQuereContainers;
    }
}@
