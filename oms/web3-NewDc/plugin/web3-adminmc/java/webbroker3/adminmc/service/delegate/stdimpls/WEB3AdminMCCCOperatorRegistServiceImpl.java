head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCオペレータ登録サービスImpl(WEB3AdminMCCCOperatorRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import java.sql.Timestamp;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import webbroker3.adminmc.define.WEB3AdminMCUserTypeDef;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistInputResponse;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOrderFlagDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御CCオペレータ登録サービスImpl)<BR>
 * 管理者メニュー制御CCオペレータ登録サービス実装クラス<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 * <BR>
 */
public class WEB3AdminMCCCOperatorRegistServiceImpl implements WEB3AdminMCCCOperatorRegistService 
{
    
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorRegistServiceImpl.class);     


    /**
     * @@roseuid 4198640D0177
     */
    public WEB3AdminMCCCOperatorRegistServiceImpl() 
    {
     
    }
    
    /**
     * CCオペレータ登録処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録入力ﾘｸｴｽﾄの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録確認ﾘｸｴｽﾄの場合 <BR>
     * 　@－validate扱者()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録完了ﾘｸｴｽﾄの場合 <BR>
     * 　@－submit扱者()をコールする。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F7E70000E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCCCOperatorRegistInputRequest)
        {            
            l_response = this.getInputScreen((WEB3AdminMCCCOperatorRegistInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminMCCCOperatorRegistConfirmRequest)
        {
            l_response = this.validateTrader((WEB3AdminMCCCOperatorRegistConfirmRequest)l_request);           
        }
        else if (l_request instanceof WEB3AdminMCCCOperatorRegistCompleteRequest)
        {
            l_response = this.submitTrader((WEB3AdminMCCCOperatorRegistCompleteRequest)l_request);           
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);           
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * CCオペレータ登録入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（CC登録）get入力画面」参照。 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録入力ﾘｸｴｽﾄデータオブジェクト<BR>
     * 
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F7E700010
     */
    protected WEB3AdminMCCCOperatorRegistInputResponse getInputScreen(WEB3AdminMCCCOperatorRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminMCCCOperatorRegistInputRequest l_request)";         
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //1.2 validate権限(機@能カテゴリコード（=オペレータ管理） : String, is更新（=true） : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR,true);
        
        
        //1.3 管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録入力ﾚｽﾎﾟﾝｽ(WEB3GenRequest)
        WEB3AdminMCCCOperatorRegistInputResponse l_response = (WEB3AdminMCCCOperatorRegistInputResponse)l_request.createResponse();
                 
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (validate扱者)<BR>
     * CCオペレータ登録確認処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（CC登録）validate扱者」参照。 <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（CC登録）validate扱者」<BR>
     *         具体位置    :分岐フロー 1.7(*1)扱者コード文字列が不正な場合<BR>
     *         （checkCode() != CHECK_NORMAL）、例外をスローする。<BR>
     * <BR>
     *          扱者コード（文字列）長不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01912 <BR> 
     * <BR>
     *          扱者コード（文字列）文字種不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01913 <BR> 
     * <BR>
     *          コード（文字列）のチェック方式に定義されていないものが指定された 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01914 <BR> 
     * ==========================================================    <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（CC登録）validate扱者」<BR>
     *         具体位置    :1.8 getTrader(arg0（=get証券会社()） : Institution, <BR>
     *         arg1（=部店コード） : String, arg2（=扱者コード） : String)<BR>
     *         "※ 扱者コード登録済みチェック<BR>
     *         入力された扱者コード（オペレータコード）にて、扱者オブジェクトを取得する。<BR>
     *         取得できた場合、登録済みの扱者コードと判断し、例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01190          <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（CC登録）validate扱者」<BR>
     *         具体位置    :分岐フロー 1.9(*2)扱者コードが既に存在する場合<BR>
     *         （オブジェクトが取得できた場合）、例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01190           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（CC登録）validate扱者」<BR>
     *         具体位置    :分岐フロー 1.13(*3)パスワード文字列が不正な場合<BR>
     *         （checkCode() != CHECK_NORMAL）、例外をスローする。<BR>
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
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録確認ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistConfirmResponse
     * @@roseuid 417F7E700012
     */
    protected WEB3AdminMCCCOperatorRegistConfirmResponse validateTrader(WEB3AdminMCCCOperatorRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateTrader(WEB3AdminMCCCOperatorRegistConfirmRequest l_request)";         
        log.entering(STR_METHOD_NAME); 
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();        
        
        //1.3 validate権限(機@能カテゴリコード（=オペレータ管理） : String, is更新（=true） : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR,true);
        
        //1.4 validate部店権限(String)
        l_web3Administrator.validateBranchPermission(l_request.ccOperatorRegistUnit.branchCode);
        
        //1.5 get証券会社( )
        Institution l_institution = l_web3Administrator.getInstitution();
        
        //1.6 checkCode(int, int, String, String)
        InstitutionRow l_row = (InstitutionRow)l_institution.getDataSourceObject();
        int l_intCheckResult = WEB3PasswordUtility.checkCode(l_row.getTrdCodeMin(), l_row.getTrdCodeMax(), l_row.getTrdCodeCheckMode(), l_request.ccOperatorRegistUnit.operatorCode);
        
        //1.7 (*1) 扱者コード文字列が不正な場合（checkCode() != CHECK_NORMAL）、例外をスローする。
        switch (l_intCheckResult)
        {
            case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01912,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[扱者コード文字列]=" + l_request.ccOperatorRegistUnit.operatorCode
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01913,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[扱者コード文字列]=" + l_request.ccOperatorRegistUnit.operatorCode
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01914,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[チェック方式]=" + l_row.getTrdCodeCheckMode()
                    );
        }

        
        //1.8 getTrader
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        try
        {        
            l_gentradeFinObjectManager.getTrader(l_institution, l_request.ccOperatorRegistUnit.operatorCode, l_request.ccOperatorRegistUnit.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            //1.10 getBranch
            AccountManager l_accountManager = l_finApp.getAccountManager();
            
            Branch l_branch = null;
            try
            {            
                l_branch = l_accountManager.getBranch(l_institution, l_request.ccOperatorRegistUnit.branchCode);
            }
            catch  (NotFoundException nfe)
            {
                log.error(STR_METHOD_NAME,nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);                
            }
            
            //1.11 getLoginTypeAttributes
            OpLoginAdminService l_opLoginAdminService =
                        (OpLoginAdminService)Services.getService(OpLoginAdminService.class); 
            
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();            
            long l_lngTraderTypeId = l_branchRow.getTraderTypeId();
                        
            Map l_loginTypeAttr = l_opLoginAdminService.getLoginTypeAttributes(l_lngTraderTypeId);
            
            //1.12 checkCode(int, int, String, String)
            String l_strCodeMin = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH);
            String l_strCodeMax = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH);

            l_intCheckResult = WEB3PasswordUtility.checkCode(Integer.parseInt(l_strCodeMin), Integer.parseInt(l_strCodeMax), 
                (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE), 
                l_request.ccOperatorRegistUnit.password1);
                
            //1.13 (*3) パスワード文字列が不正な場合（checkCode() != CHECK_NORMAL）、例外をスローする。
            switch (l_intCheckResult)
            {
                case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01915,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[パスワード文字列]=" + l_request.ccOperatorRegistUnit.password1
                        );
                
                case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01916,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[パスワード文字列]=" + l_request.ccOperatorRegistUnit.password1
                        );
                
                case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01914,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[チェック方式]=" + l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE)
                        );
            }
            
            //1.14 createResponse()
            WEB3AdminMCCCOperatorRegistConfirmResponse l_reponse = (WEB3AdminMCCCOperatorRegistConfirmResponse)l_request.createResponse();           

            log.exiting(STR_METHOD_NAME); 
            return l_reponse;                       
        }
        
        //1.9 (*2) 扱者コードが既に存在する場合（オブジェクトが取得できた場合）、例外をスローする。        
        log.error("扱者コードが既に存在する");
        log.exiting(STR_METHOD_NAME);
        throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_01190,
            this.getClass().getName() + STR_METHOD_NAME);  
    }
    
    /**
     * (submit扱者)<BR>
     * CCオペレータ登録完了処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（CC登録）submit扱者」参照。 <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（CC登録）submit扱者」<BR>
     *         具体位置   :分岐フロー 1.8(*1) 扱者コード文字列が不正な場合<BR>
     *         （checkCode() != CHECK_NORMAL）、例外をスローする。"<BR>
     * <BR>
     *          扱者コード（文字列）長不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01912 <BR> 
     * <BR>
     *          扱者コード（文字列）文字種不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01913 <BR> 
     * <BR>
     *          コード（文字列）のチェック方式に定義されていないものが指定された 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag             : BUSINESS_ERROR_01914 <BR> 
     * ==========================================================    <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（CC登録）submit扱者」<BR>
     *         具体位置    :1.9 getTrader(arg0（=get証券会社()） : Institution,<BR>
     *          arg1（=部店コード） : String, arg2（=扱者コード） : String)  <BR>
     *         "※ 扱者コード登録済みチェック<BR>
     *         入力された扱者コード（オペレータコード）にて、扱者オブジェクトを取得する。<BR>
     *         取得できた場合、登録済みの扱者コードと判断し"例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01190           <BR>
     * =========================================================== <BR> 
     * <BR>
     * =========================================================== <BR>        
     *         シーケンス図 :「管理者メニュー制御（CC登録）submit扱者」<BR>
     *         具体位置    : 分岐フロー 1.10(*1)  扱者コードが既に存在する場合<BR>
     *         （オブジェクトが取得できた場合）、例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :  BUSINESS_ERROR_01190            <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（CC登録）submit扱者」<BR>
     *         具体位置    : 分岐フロー 1.14(*4) パスワード文字列が不正な場合<BR>
     *         （checkCode() != CHECK_NORMAL）、例外をスローする。<BR>
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
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録完了ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistCompleteResponse
     * @@roseuid 417F7E700014
     */
    protected WEB3AdminMCCCOperatorRegistCompleteResponse submitTrader(WEB3AdminMCCCOperatorRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitTrader(WEB3AdminMCCCOperatorRegistCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 validate()
        l_request.validate(); 
                
        //1.2 getInstanceFromログイン情報
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=オペレータ管理） : String, is更新（=true） : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR,true);  
        
        //1.4 validate部店権限(String)
        l_web3Administrator.validateBranchPermission(l_request.ccOperatorRegistUnit.branchCode);                      
          
        //1.5 validate取引パスワード(String)
        l_web3Administrator.validateTradingPassword(l_request.password);
        
        //1.6 get証券会社( )
        Institution l_institution = l_web3Administrator.getInstitution();
        
        //1.7 checkCode(int, int, String, String)
        InstitutionRow l_row = (InstitutionRow)l_institution.getDataSourceObject();
        int l_intCheckResult = WEB3PasswordUtility.checkCode(l_row.getTrdCodeMin(), l_row.getTrdCodeMax(), l_row.getTrdCodeCheckMode(), l_request.ccOperatorRegistUnit.operatorCode);
        
        //1.8 (*1) 扱者コード文字列が不正な場合（checkCode() != CHECK_NORMAL）、例外をスローする。
        switch (l_intCheckResult)
        {
            case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01912,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[扱者コード文字列]=" + l_request.ccOperatorRegistUnit.operatorCode
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01913,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[扱者コード文字列]=" + l_request.ccOperatorRegistUnit.operatorCode
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01914,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "[チェック方式]=" + l_row.getTrdCodeCheckMode()
                    );
        }
        
        //1.9 getTrader
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        try
        {        
            l_gentradeFinObjectManager.getTrader(l_institution, l_request.ccOperatorRegistUnit.operatorCode, l_request.ccOperatorRegistUnit.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            //1.11 getBranch
            AccountManager l_accountManager = l_finApp.getAccountManager();
            
            Branch l_branch = null;
            try
            {            
                l_branch = l_accountManager.getBranch(l_institution, l_request.ccOperatorRegistUnit.branchCode);
            }
            catch  (NotFoundException nfe)
            {
                log.error(STR_METHOD_NAME,nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);                
            }
            
            //1.12 getLoginTypeAttributes(long)
            OpLoginAdminService l_opLoginAdminService =
                        (OpLoginAdminService)Services.getService(OpLoginAdminService.class); 
            
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            
            long l_lngTraderTypeId = 0;
            long l_lngAccountGroupId = 0;

            l_lngTraderTypeId = l_branchRow.getTraderTypeId();
            l_lngAccountGroupId = l_branchRow.getAccountGroupId();           
            
            Map l_loginTypeAttr = l_opLoginAdminService.getLoginTypeAttributes(l_lngTraderTypeId);
            
            //1.13 checkCode(int, int, String, String)
            String l_strCodeMin = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH);
            String l_strCodeMax = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH);

            l_intCheckResult = WEB3PasswordUtility.checkCode(Integer.parseInt(l_strCodeMin), Integer.parseInt(l_strCodeMax), 
                (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE), 
                l_request.ccOperatorRegistUnit.password1);
                
            //1.14 (*4) パスワード文字列が不正な場合（checkCode() != CHECK_NORMAL）、例外をスローする。
            switch (l_intCheckResult)
            {
                case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01915,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[パスワード文字列]=" + l_request.ccOperatorRegistUnit.password1
                        );
                
                case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01916,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[パスワード文字列]=" + l_request.ccOperatorRegistUnit.password1
                        );
                
                case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01914,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "[チェック方式]=" + l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE)
                        );
            }
            
            try
            {            
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                
//                //1.15 doInsertQuery(arg0(*5) : Row)
//                LoginParams l_loginParams = new LoginParams();
//                
//                String l_strLoginId = l_web3Administrator.getInstitutionCode() + l_request.ccOperatorRegistUnit.branchCode + l_request.ccOperatorRegistUnit.operatorCode;
//                l_loginParams.setLoginId(Long.parseLong(l_strLoginId)); 
//                l_loginParams.setTypeId(l_lngTraderTypeId);
//                l_loginParams.setAccountId(null);
//                l_loginParams.setInitialPassword("-");
//                l_loginParams.setHashedPassword(PasswordTools.hashPassword(l_request.ccOperatorRegistUnit.password1));
//                l_loginParams.setAffinityKey(null);
//                l_loginParams.setDisabled(Integer.parseInt(WEB3EnableOrderDef.ENABLE));
//                l_loginParams.setFailureCount(null);
//                l_loginParams.setLastFailureTimestamp(null);
//                
//                l_queryProcessor.doInsertQuery(l_loginParams); 
//                
//                //1.16 doInsertQuery(arg0(*5) : Row)
//                LoginUsernameParams l_loginUsrNmParams = new LoginUsernameParams();
//                
//                String l_strUsrName = WEB3AdminMCUserTypeDef.TRADER + l_web3Administrator.getInstitutionCode() + l_request.ccOperatorRegistUnit.branchCode + l_request.ccOperatorRegistUnit.operatorCode;
//                l_loginUsrNmParams.setUsername(l_strUsrName);
//                l_loginUsrNmParams.setLoginId(Long.parseLong(l_strLoginId));
//                
//                l_queryProcessor.doInsertQuery(l_loginUsrNmParams); 
                
                //
                //1.15 createLogin(long, String, String)
                LoginInfo l_loginInfo = l_opLoginAdminService.createLogin(l_lngTraderTypeId, WEB3AdminMCUserTypeDef.TRADER+l_institution.getInstitutionCode()+l_branch.getBranchCode()+l_request.ccOperatorRegistUnit.operatorCode, l_request.ccOperatorRegistUnit.password1);
                        
                //1.16 setLoginAttributes(long, Map)
                HashMap l_mapLoginAttr = new HashMap();            
                WEB3Crypt l_web3Crypt = new WEB3Crypt();
            
                l_mapLoginAttr.put(WEB3LoginAttributeKeyDef.INITIAL_PASSWORD, l_web3Crypt.encrypt(l_request.ccOperatorRegistUnit.password1));
                l_mapLoginAttr.put(WEB3LoginAttributeKeyDef.PASSWORD, l_web3Crypt.encrypt(l_request.ccOperatorRegistUnit.password1));
                l_mapLoginAttr.put(WEB3LoginAttributeKeyDef.LAST_PASSWORDCHANGE_UPDATER, l_web3Administrator.getAdministratorCode());
                l_mapLoginAttr.put(WEB3LoginAttributeKeyDef.LAST_PWDCHANGE, WEB3PasswordUtility.loginAttributeDateFormat.format(GtlUtils.getSystemTimestamp()));
            
                l_opLoginAdminService.setLoginAttributes(l_loginInfo.getLoginId(), l_mapLoginAttr);
            
                //1.18.1 addLoginGroupManager(long, long)
                l_opLoginAdminService.addLoginGroupManager(l_lngAccountGroupId, l_loginInfo.getLoginId());
            
                //1.19 doInsertQuery(Row)
                long l_lngTraderId=l_queryProcessor.doGetNewPkValueQuery(TraderRow.TYPE);
                
                TraderParams l_traderParams = new TraderParams();
                l_traderParams.setTraderId(l_lngTraderId);
                l_traderParams.setInstitutionCode(l_institution.getInstitutionCode());
                l_traderParams.setBranchId(l_branch.getBranchId());
                l_traderParams.setBranchCode(l_request.ccOperatorRegistUnit.branchCode);
                l_traderParams.setTraderCode(l_request.ccOperatorRegistUnit.operatorCode);
                l_traderParams.setTraderType(TraderTypeEnum.UNDEFINED);
                l_traderParams.setLoginId(l_loginInfo.getLoginId());
                l_traderParams.setFamilyName(l_request.ccOperatorRegistUnit.operatorName);
                l_traderParams.setMiddleName("0");
                l_traderParams.setGivenName("0");
                l_traderParams.setFamilyNameAlt1("0");
                l_traderParams.setMiddleNameAlt1(null);
                l_traderParams.setGivenNameAlt1("0");
                l_traderParams.setFamilyNameAlt2(null);
                l_traderParams.setMiddleNameAlt2(null);
                l_traderParams.setGivenNameAlt2(null);
                l_traderParams.setTradingPassword(null);
                l_traderParams.setAccountOrderFlag(l_request.ccOperatorRegistUnit.accountOrderDiv);
                l_traderParams.setDepartmentCode(l_request.ccOperatorRegistUnit.departmentCode);
                l_traderParams.setLastUpdater(l_web3Administrator.getAdministratorCode());
                Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
                l_traderParams.setCreatedTimestamp(l_tsSystemTime);
                l_traderParams.setLastUpdatedTimestamp(l_tsSystemTime);
                
                l_queryProcessor.doInsertQuery(l_traderParams); 
            }
            catch (DataFindException l_dex)
            {
            
                log.error("DBへのアクセスに失敗しました! ", l_dex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex);
            }
            catch(DataNetworkException l_dex)
            {
                log.error("DBへのアクセスに失敗しました! ", l_dex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dex.getMessage(),
                    l_dex);
            }
            catch (DataQueryException l_dex)
            {
                log.error("DBへのアクセスに失敗しました!  ", l_dex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dex.getMessage(),
                    l_dex);
            }   
            
            //1.19 createResponse
            WEB3AdminMCCCOperatorRegistCompleteResponse l_response = (WEB3AdminMCCCOperatorRegistCompleteResponse)l_request.createResponse();
            return l_response;
        }
        
        //1.10 (*1) 扱者コードが既に存在する場合（オブジェクトが取得できた場合）、例外をスローする。 
        log.error("扱者コードが既に存在する");
        log.exiting(STR_METHOD_NAME);
        throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_01190,
            this.getClass().getName() + STR_METHOD_NAME); 
            
    }
}
@
