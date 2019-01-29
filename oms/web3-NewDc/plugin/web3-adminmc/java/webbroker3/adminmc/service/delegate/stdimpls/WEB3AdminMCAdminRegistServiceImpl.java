head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : (管理者メニュー制御管理者登録サービスImpl(WEB3AdminMCAdminRegistServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 王敏 (中訊) 新規作成
Revesion History : 2005/01/07 王敏 (中訊) 変更
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.define.WEB3AdminMCUserTypeDef;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistInputResponse;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者メニュー制御管理者登録サービスImpl)<BR>
 * 管理者メニュー制御管理者登録サービス実装クラス<BR>
 * @@author 王敏
 * @@version 1.0
 * <BR>
 */
public class WEB3AdminMCAdminRegistServiceImpl implements WEB3AdminMCAdminRegistService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminRegistServiceImpl.class);

    /**
     * @@roseuid 419864150138
     */
    public WEB3AdminMCAdminRegistServiceImpl() 
    {
     
    }
    
    /**
     * 管理者登録処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者登録入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者登録確認リクエストの場合 <BR>
     * 　@－validate管理者()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者登録完了リクエストの場合 <BR>
     * 　@－submit管理者()をコールする。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417CB6870393
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        // 引数のリクエストデータが、管理者メニュー制御管理者登録入力リクエストの場合
        if (l_request instanceof WEB3AdminMCAdminRegistInputRequest)
        {                           
            // get入力画面()をコールする
            l_response = this.getInputScreen((WEB3AdminMCAdminRegistInputRequest)l_request);
        }
        // 引数のリクエストデータが、管理者メニュー制御管理者登録確認リクエストの場合
        else if (l_request instanceof WEB3AdminMCAdminRegistConfirmRequest)
        {
            // validate管理者()をコールする
            l_response = this.validateAdministrator((WEB3AdminMCAdminRegistConfirmRequest)l_request);
        }
        // 引数のリクエストデータが、管理者メニュー制御管理者登録完了リクエストの場合
        else if (l_request instanceof WEB3AdminMCAdminRegistCompleteRequest)
        {
            // submit管理者()をコールする
            l_response = this.submitAdministrator((WEB3AdminMCAdminRegistCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                                                                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 管理者登録入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者登録）get入力画面」参照。 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者登録入力リクエストデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 417CB6870395
     */
    protected WEB3AdminMCAdminRegistInputResponse getInputScreen(WEB3AdminMCAdminRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminMCAdminRegistInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate権限(機@能カテゴリコード（=管理者管理）)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);
        
        //1.3 get証券会社コード
        String l_strInstitutionCode = l_administartor.getInstitutionCode();
        
        //1.4 isDIR管理者( )
        boolean l_blnisdiradmn = l_administartor.isDirAdministrator();
        
        //1.5 get管理者タイプ
        WEB3AdminMCAdminType[] l_admintype = WEB3AdminMCAdminType.getAdminType(l_strInstitutionCode, "permission_Level", l_blnisdiradmn);
        int l_recno = l_admintype.length;
        
        //1.6 管理者メニュー制御管理者登録入力レスポンス
        WEB3AdminMCAdminRegistInputResponse l_response = (WEB3AdminMCAdminRegistInputResponse)l_request.createResponse();
        
        //1.7 プロパティセット
        l_response.permissionLevelList = new String[l_recno];
        l_response.permissionLevelNameList = new String[l_recno];
        
        for (int i = 0; i < l_recno; i++)
        {
            //権限レベルコード一覧
            l_response.permissionLevelList[i] = l_admintype[i].getPermissionLevel();
            //権限レベル名一覧
            l_response.permissionLevelNameList[i] = l_admintype[i].getPermissionLevelName();
        }       
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate管理者)<BR>
     * 管理者登録確認処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者登録）validate管理者」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）validate管理者」<BR>
     *         具体位置    : 1.7(*1) 分岐フロー<BR>
     *         管理者コード文字列が不正な場合（checkCode() != CHECK_NORMAL）、<BR>
     *         例外をスローする。<BR>
     * <BR>
     *          管理者コード（文字列）長不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01917 <BR> 
     * <BR>
     *          管理者コード（文字列）文字種不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01918 <BR> 
     * <BR>
     *          コード（文字列）のチェック方式に定義されていないものが指定された 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01914 <BR> 
     *  ==========================================================    <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）validate管理者」<BR>
     *         具体位置    : 1.8 管理者(証券会社 : 証券会社, <BR>
     *         管理者コード : String)<BR>
     *         ※ 管理者コード登録済みチェック<BR>
     *         入力された管理者コードにて、<BR>
     *         管理者オブジェクトを生成する。<BR>
     *         生成できた場合、登録済みの管理者コードと判断し、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01229          <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）validate管理者」<BR>
     *         具体位置    : 1.9(*1) 分岐フロー<BR>
     *         管理者コードが既に存在する場合（オブジェクトが生成できた場合）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01229          <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）validate管理者」<BR>
     *         具体位置    : 1.11.3(*2.1) 分岐フロー<BR>
     *         DIR管理者の管理者タイプの場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）validate管理者」<BR>
     *         具体位置    : 1.13(*3) 分岐フロー<BR>
     *         メールアドレスが不正な場合（isMailAddress() == false）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_00777           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）validate管理者」<BR>
     *         具体位置    : 1.16 分岐フロー<BR>
     *         パスワード文字列が不正な場合（checkCode() != CHECK_NORMAL）、<BR>
     *         例外をスローする。<BR>
     * <BR>
     *          パスワード（文字列）長不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01915 <BR> 
     * <BR>
     *          パスワード（文字列）文字種不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01916 <BR> 
     * <BR>
     *          コード（文字列）のチェック方式に定義されていないものが指定された 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01914 <BR> 
     *  ==========================================================    <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者登録確認リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistConfirmResponse
     * @@roseuid 417CB6870397
     */
    protected WEB3AdminMCAdminRegistConfirmResponse validateAdministrator(WEB3AdminMCAdminRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAdministrator(WEB3AdminMCAdminRegistConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=管理者管理）)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);
        
        //1.4 validate部店権限
        l_administartor.validateBranchPermission(l_request.adminRegistUnit.branchCode);

        //1.5 get証券会社( )
        Institution l_institution = l_administartor.getInstitution();
        
        //1.6 checkCode(int, int, String, String)
        InstitutionRow l_row = (InstitutionRow)l_institution.getDataSourceObject();
        int l_codeMin = l_row.getAdminCodeMin();
        int l_codeMax = l_row.getAdminCodeMax();
        String l_chkMode = l_row.getAdminCodeCheckMode();
        String l_codeString = l_request.adminRegistUnit.administratorCode;
        int l_intchckcd = WEB3PasswordUtility.checkCode(l_codeMin, l_codeMax, l_chkMode, l_codeString);
        
        switch (l_intchckcd)
        {
            case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01917,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[管理者コード文字列]=" + l_codeString
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01918,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[管理者コード文字列]=" + l_codeString
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01914,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[チェック方式]=" + l_chkMode
                    );
        }
                
        //1.8  管理者
        try
        {
             new WEB3Administrator(l_institution, l_request.adminRegistUnit.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            //1.10 isDIR管理者( )
            boolean l_blnisdiradmn = l_administartor.isDirAdministrator();
            
            //1.11  ログイン中の管理者が通常管理者（isDIR管理者() == false）場合
            if (!l_blnisdiradmn)
            {
                //1.11.1 管理者タイプ(String, String)
                WEB3AdminMCAdminType l_adminmcadmintype = new WEB3AdminMCAdminType(l_institution.getInstitutionCode(), l_request.adminRegistUnit.permissionLevel);
                 
                //1.11.2 isDIR管理者( )
                boolean l_blnisdiradmn2 = l_adminmcadmintype.isDIRAdministrator();
                 
                //1.11.3  DIR管理者の管理者タイプの場合（isDIR管理者() == true）
                if (l_blnisdiradmn2)
                {
                    log.error("「DIR管理者の管理者タイプ」の例外をスローする。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                                  WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                                  this.getClass().getName() + STR_METHOD_NAME);
                }
            }
             
            //1.12 isMailAddress(String)
            if (l_request.adminRegistUnit.mailAddress != null)
            {
                boolean l_blnisMailAddress = WEB3StringTypeUtility.isMailAddress(l_request.adminRegistUnit.mailAddress);
                //1.13 メールアドレスが不正な場合（isMailAddress() == false）
                if (!l_blnisMailAddress)
                {
                    log.error("「メールアドレスが不正」の例外をスローする。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                                  WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                                  this.getClass().getName() + STR_METHOD_NAME);
                }                
            }

             
            //1.14 getLoginTypeAttributes(long)
            //拡張アカウントマネージャ
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager = 
                            (WEB3GentradeAccountManager)l_finApp.getAccountManager(); 
            Branch l_branch = null;
            try
            {
                l_branch = l_gentradeAccountManager.getBranch(
                                           l_institution, 
                                           l_request.adminRegistUnit.branchCode);//NotFoundException
            }
            catch(NotFoundException l_nfd)
            {  
                log.error(getClass().getName() + STR_METHOD_NAME, l_nfd);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                              WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                              this.getClass().getName() + STR_METHOD_NAME,
                              "branchCode = "+l_request.adminRegistUnit.branchCode + "の対応するデータ存在しません");  
            }
            //部店行の取得
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
             
            OpLoginAdminService l_opLoginAdminService =
                           (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
    
            Map l_loginTypeAttr = l_opLoginAdminService.getLoginTypeAttributes(l_branchRow.getAdminTypeId());

            //1.15 checkCode(int, int, String, String)

            int l_codeMin2 = Integer.parseInt((String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH));
            int l_codeMax2 = Integer.parseInt((String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH));
            String l_chkMode2 = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE);
            String l_codeString2 = l_request.adminRegistUnit.password1;
            int l_intchckcd2 = WEB3PasswordUtility.checkCode(l_codeMin2, l_codeMax2, l_chkMode2, l_codeString2);
             
            //1.16 パスワード文字列が不正な場合（checkCode() != CHECK_NORMAL）、例外をスローする
            switch (l_intchckcd2)
            {
                case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01915,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[パスワード文字列]=" + l_codeString2
                        );
                
                case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01916,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[パスワード文字列]=" + l_codeString2
                        );
                
                case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01914,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[チェック方式]=" + l_chkMode2
                        );
            }
             
            //1.17 createResponse( )
            WEB3AdminMCAdminRegistConfirmResponse l_response = (WEB3AdminMCAdminRegistConfirmResponse)l_request.createResponse();
             
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //1.9 管理者コードが既に存在する場合
        log.error("「管理者コードが既に存在する」の例外をスローする。");
        log.exiting(STR_METHOD_NAME);
        throw new WEB3BusinessLayerException(
                                              WEB3ErrorCatalog.BUSINESS_ERROR_01229,
                                              this.getClass().getName() + STR_METHOD_NAME);
        
    }
    
    /**
     * (submit管理者)<BR>
     * 管理者登録完了処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者登録）submit管理者」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）submit管理者」<BR>
     *         具体位置    : 1.8(*1) 分岐フロー<BR>
     *         管理者コード文字列が不正な場合（checkCode() != CHECK_NORMAL）、<BR>
     *         例外をスローする。<BR>
     * <BR>
     *          管理者コード（文字列）長不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01917 <BR> 
     * <BR>
     *          管理者コード（文字列）文字種不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01918 <BR> 
     * <BR>
     *          コード（文字列）のチェック方式に定義されていないものが指定された 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01914 <BR> 
     * ==========================================================    <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）submit管理者」<BR>
     *         具体位置    : 1.9 管理者(証券会社 : 証券会社, <BR>
     *         管理者コード : String)<BR>
     *         ※ 管理者コード登録済みチェック<BR>
     *         入力された管理者コードにて、管理者オブジェクトを生成する。<BR>
     *         生成できた場合、登録済みの管理者コードと判断し、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01229          <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）submit管理者」<BR>
     *         具体位置    : 1.10(*2) 分岐フロー<BR>
     *         管理者コードが既に存在する場合（オブジェクトが生成できた場合）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01229           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）submit管理者」<BR>
     *         具体位置    : 1.12.3(*3.1) 分岐フロー<BR>
     *         DIR管理者の管理者タイプの場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）submit管理者」<BR>
     *         具体位置    : 1.14(*4) 分岐フロー<BR>
     *         メールアドレスが不正な場合（isMailAddress() == false）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_00777           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者登録）submit管理者」<BR>
     *         具体位置    : 1.17(*5) 分岐フロー<BR>
     *         パスワード文字列が不正な場合（checkCode() != CHECK_NORMAL）、<BR>
     *         例外をスローする。<BR>
     * <BR>
     *          パスワード（文字列）長不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01915 <BR> 
     * <BR>
     *          パスワード（文字列）文字種不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01916 <BR> 
     * <BR>
     *          コード（文字列）のチェック方式に定義されていないものが指定された 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01914 <BR> 
     *  ==========================================================    <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者登録完了リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistCompleteResponse
     * @@roseuid 417CB68703A3
     */
    protected WEB3AdminMCAdminRegistCompleteResponse submitAdministrator(WEB3AdminMCAdminRegistCompleteRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitAdministrator(WEB3AdminMCAdminRegistCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=管理者管理）)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);
        
        //1.4 validate部店権限
        l_administartor.validateBranchPermission(l_request.adminRegistUnit.branchCode);
        
        //1.5 validate取引パスワード
        l_administartor.validateTradingPassword(l_request.password);
        
        //1.6 get証券会社( )
        Institution l_institution = l_administartor.getInstitution();
        
        //1.7 checkCode(int, int, String, String)
        InstitutionRow l_row = (InstitutionRow)l_institution.getDataSourceObject();
        int l_codeMin = l_row.getAdminCodeMin();
        int l_codeMax = l_row.getAdminCodeMax();
        String l_chkMode = l_row.getAdminCodeCheckMode();
        String l_codeString = l_request.adminRegistUnit.administratorCode;
        int l_intchckcd = WEB3PasswordUtility.checkCode(l_codeMin, l_codeMax, l_chkMode, l_codeString);
        
        //1.8 管理者コード文字列が不正な場合（checkCode() != CHECK_NORMAL）、例外をスローする
        
        switch (l_intchckcd)
        {
            case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01917,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[管理者コード文字列]=" + l_codeString
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01918,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[管理者コード文字列]=" + l_codeString
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01914,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[チェック方式]=" + l_chkMode
                    );
        }
        
        //1.9  管理者
        try
        {
              new WEB3Administrator(l_institution, l_request.adminRegistUnit.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            //1.11 isDIR管理者( )
            boolean l_blnisdiradmn = l_administartor.isDirAdministrator();
            
            //1.12  ログイン中の管理者が通常管理者（isDIR管理者() == false）場合
            if (!l_blnisdiradmn)
            {
                //1.12.1 管理者タイプ(String, String)
                WEB3AdminMCAdminType l_adminmcadmintype = new WEB3AdminMCAdminType(l_institution.getInstitutionCode(), l_request.adminRegistUnit.permissionLevel);
                 
                //1.12.2 isDIR管理者( )
                boolean l_blnisdiradmn2 = l_adminmcadmintype.isDIRAdministrator();
                 
                //1.12.3  DIR管理者の管理者タイプの場合（isDIR管理者() == true）
                if (l_blnisdiradmn2)
                {
                    log.error("「DIR管理者の管理者タイプ」の例外をスローする。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                                  WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                                  this.getClass().getName() + STR_METHOD_NAME);
                }
            }     
            
            if (l_request.adminRegistUnit.mailAddress != null)
            {
                //1.13 isMailAddress(String)
                boolean l_blnisMailAddress = WEB3StringTypeUtility.isMailAddress(l_request.adminRegistUnit.mailAddress);
                //1.14 メールアドレスが不正な場合（isMailAddress() == false）
                if (!l_blnisMailAddress)
                {
                    log.error("「メールアドレスが不正」の例外をスローする。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                                  WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                                  this.getClass().getName() + STR_METHOD_NAME);
                }                
            }
             
            //1.15 getLoginTypeAttributes(long)
            //拡張アカウントマネージャ
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager = 
                            (WEB3GentradeAccountManager)l_finApp.getAccountManager(); 
            Branch l_branch = null;
            try
            {
                l_branch = l_gentradeAccountManager.getBranch(
                                           l_institution, 
                                           l_request.adminRegistUnit.branchCode);//NotFoundException
            }
            catch(NotFoundException l_nfd)
            {  
                log.error(getClass().getName() + STR_METHOD_NAME, l_nfd);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "branchCode = "+l_request.adminRegistUnit.branchCode + "の対応するデータ存在しません");  
            }
            //部店行の取得
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
             
            OpLoginAdminService l_opLoginAdminService =
                           (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
    
            Map l_loginTypeAttr = l_opLoginAdminService.getLoginTypeAttributes(l_branchRow.getAdminTypeId());
             
            //1.16 checkCode(int, int, String, String)

            int l_codeMin2 = Integer.parseInt((String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH));
            int l_codeMax2 = Integer.parseInt((String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH));
            String l_chkMode2 = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE);
            String l_codeString2 = l_request.adminRegistUnit.password1;
            int l_intchckcd2 = WEB3PasswordUtility.checkCode(l_codeMin2, l_codeMax2, l_chkMode2, l_codeString2);
             
            switch (l_intchckcd2)
            {
                case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01915,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[パスワード文字列]=" + l_codeString2
                        );
                
                case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01916,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[パスワード文字列]=" + l_codeString2
                        );
                
                case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01914,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[チェック方式]=" + l_chkMode2
                        );
            }
             
            /*
            //1.18 doInsertQuery(arg0(*6) : Row)
            WEB3Crypt l_crypt =new WEB3Crypt();
            LoginParams  l_loginparams = new LoginParams();
            long l_lngloginid = Long.parseLong(l_institution.getInstitutionCode() 
                                                          + l_request.adminRegistUnit.branchCode
                                                          + l_request.adminRegistUnit.administratorCode);
            l_loginparams.setLoginId(l_lngloginid);
            l_loginparams.setTypeId(l_branchRow.getAdminTypeId());
            l_loginparams.setInitialPassword("-");
            l_loginparams.setHashedPassword(l_crypt.encrypt(l_request.adminRegistUnit.password1));
            l_loginparams.setDisabled(Integer.parseInt(WEB3EnableOrderDef.ENABLE));

            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doInsertQuery(l_loginparams);
            }
            catch (DataFindException l_epx)
            {
                log.error("DBへのアクセスに失敗しました! ", l_epx);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_epx);
            }
            catch (DataNetworkException l_epx)
            {
                log.error("DBへのアクセスに失敗しました! ", l_epx);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_epx);
            }
            catch (DataQueryException l_epx)
            {
                log.error("DBへのアクセスに失敗しました! ", l_epx);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_epx);
            } 
           */
            
           //1.18******************1月19日*****>>>>>>>>>>>>>>>>
           //createLogin(arg0（=部店.管理者ログインタイプＩＤ） : long, arg1（=ログインユーザ名） : String, arg2（=パスワード） : String) 
            
           String l_stradmintypenm = WEB3AdminMCUserTypeDef.ADMINISTRATOR + l_institution.getInstitutionCode() + l_request.adminRegistUnit.administratorCode;             
           LoginInfo l_loginfo = l_opLoginAdminService.createLogin(l_branchRow.getAdminTypeId(), l_stradmintypenm, l_request.adminRegistUnit.password1);
            
           //**********************************<<<<<<<<<<<<<<<<
            
           //1.19******************1月19日*****>>>>>>>>>>>>>>>>
           // setLoginAttributes
            
           WEB3Crypt l_crypt =new WEB3Crypt();
           HashMap l_loginAttr = new HashMap();
           l_loginAttr.put(WEB3LoginAttributeKeyDef.INITIAL_PASSWORD, l_crypt.encrypt(l_request.adminRegistUnit.password1));
           l_loginAttr.put(WEB3LoginAttributeKeyDef.PASSWORD, l_crypt.encrypt(l_request.adminRegistUnit.password1));
           l_loginAttr.put(WEB3LoginAttributeKeyDef.LAST_PASSWORDCHANGE_UPDATER, l_administartor.getAdministratorCode());
           l_loginAttr.put(WEB3LoginAttributeKeyDef.LAST_PWDCHANGE, WEB3PasswordUtility.loginAttributeDateFormat.format(GtlUtils.getSystemTimestamp()));
            
           long l_lngloginid = l_loginfo.getLoginId(); 
           l_opLoginAdminService.setLoginAttributes(l_lngloginid, l_loginAttr);
            
           //**********************************<<<<<<<<<<<<<<<<
            
            //1.19 doInsertQuery(arg0(*6) : Row)
//            LoginUsernameParams  l_loginusernameparams = new LoginUsernameParams();
//             
//            l_loginusernameparams.setUsername(WEB3AdminMCUserTypeDef.ADMINISTRATOR
//                                                               + l_institution.getInstitutionCode()
//                                                               + l_request.adminRegistUnit.administratorCode);
//            l_loginusernameparams.setLoginId(l_lngloginid);
//             
//            try
//            {
//                QueryProcessor l_processor = Processors.getDefaultProcessor();
//                l_processor.doInsertQuery(l_loginusernameparams);
//            }
//            catch (DataFindException l_epx)
//            {
//                log.error("DBへのアクセスに失敗しました! ", l_epx);
//                throw new WEB3BaseRuntimeException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
//                    this.getClass().getName() + "." + STR_METHOD_NAME,
//                    l_epx);
//            }
//            catch (DataNetworkException l_epx)
//            {
//                log.error("DBへのアクセスに失敗しました! ", l_epx);
//                throw new WEB3BaseRuntimeException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
//                    this.getClass().getName() + "." + STR_METHOD_NAME,
//                    l_epx);
//            }
//            catch (DataQueryException l_epx)
//            {
//                log.error("DBへのアクセスに失敗しました! ", l_epx);
//                throw new WEB3BaseRuntimeException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
//                    this.getClass().getName() + "." + STR_METHOD_NAME,
//                    l_epx);
//            } 
             
             
            //1.21 get管理者コード( )
            String l_admncode = l_administartor.getAdministratorCode();
             
            //1.22 doInsertQuery(Row)
            AdministratorParams  l_administratorparams = new AdministratorParams();
            l_administratorparams.setAdministratorCode(l_request.adminRegistUnit.administratorCode);
            l_administratorparams.setInstitutionId(l_institution.getInstitutionId());
            l_administratorparams.setInstitutionCode(l_institution.getInstitutionCode());
            l_administratorparams.setBranchId(l_branch.getBranchId());
            l_administratorparams.setBranchCode(l_request.adminRegistUnit.branchCode);
            l_administratorparams.setLoginId(l_lngloginid);
            l_administratorparams.setName(l_request.adminRegistUnit.administratorName);
            if (l_request.adminRegistUnit.mailAddress != null)
            {
                l_administratorparams.setEmailAddress(l_request.adminRegistUnit.mailAddress);
            }
            String l_tradingpassword = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);
            if (l_tradingpassword.equals(WEB3TradingPwdEnvDef.USE_TRADING_PWD))
            {
                l_administratorparams.setTradingPassword(l_crypt.encrypt(l_request.adminRegistUnit.password1));
            }
            l_administratorparams.setPermissionLevel(l_request.adminRegistUnit.permissionLevel);
            l_administratorparams.setLastUpdater(l_admncode);
            l_administratorparams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_administratorparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_administratorparams.setAdministratorId(l_processor.doGetNewPkValueQuery(AdministratorRow.TYPE));
                l_processor.doInsertQuery(l_administratorparams);
            }
            catch (DataFindException l_epx)
            {
                log.error("DBへのアクセスに失敗しました! ", l_epx);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_epx);
            }
            catch (DataNetworkException l_epx)
            {
                log.error("DBへのアクセスに失敗しました! ", l_epx);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_epx);
            }
            catch (DataQueryException l_epx)
            {
                log.error("DBへのアクセスに失敗しました! ", l_epx);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_epx);
            } 
             
            //1.23 createResponse( )
            WEB3AdminMCAdminRegistCompleteResponse l_response = (WEB3AdminMCAdminRegistCompleteResponse)l_request.createResponse();
             
            log.exiting(STR_METHOD_NAME);
            return l_response;
       }
       //1.10 管理者コードが既に存在する場合
       log.error("「管理者コードが既に存在する」の例外をスローする。");
       log.exiting(STR_METHOD_NAME);
       throw new WEB3BusinessLayerException(
                                             WEB3ErrorCatalog.BUSINESS_ERROR_01229,
                                             this.getClass().getName() + STR_METHOD_NAME);
        
    }

}
@
