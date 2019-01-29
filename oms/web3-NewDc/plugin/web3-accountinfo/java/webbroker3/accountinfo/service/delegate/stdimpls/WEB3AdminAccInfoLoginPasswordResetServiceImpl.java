head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordResetServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報パスワードリセットサービスImpl(WEB3AdminAccInfoLoginPasswordResetServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginPK;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.PasswordUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordResetService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginDisabledFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報パスワードリセットサービスImpl)<BR>
 * 管理者お客様情報パスワードリセットサービス実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordResetServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoLoginPasswordResetService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordResetServiceImpl.class);    
         
    /**
     * @@roseuid 418F3A0003B9
     */
    public WEB3AdminAccInfoLoginPasswordResetServiceImpl() 
    {
     
    }
    
    /**
     * パスワードリセット処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報パスワードリセット入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報パスワードリセットリクエストの場合 <BR>
     * 　@－submitリセット()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4158E8500191
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminAccInfoLoginPasswordResetInputRequest)
        {
            l_response = getInputScreen((WEB3AdminAccInfoLoginPasswordResetInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoLoginPasswordResetRequest)
        {
            l_response = submitReset((WEB3AdminAccInfoLoginPasswordResetRequest)l_request);
        }
        else
        {
            log.error("パラメータタイプ不正。");
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request
                );
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * パスワードリセット入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（パスワードリセット）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報パスワードリセット入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B72700366
     */
    protected WEB3AdminAccInfoLoginPasswordResetInputResponse getInputScreen(WEB3AdminAccInfoLoginPasswordResetInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoLoginPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //管理者の権限チェックを行う。
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, true);
        
        //証券会社コードを取得する。 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //顧客オブジェクトを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_accountMgr.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //部店権限をチェックする。
        l_admin.validateBranchPermission(l_request.branchCode);

        //レスポンスデータを生成する。 
        WEB3AdminAccInfoLoginPasswordResetInputResponse l_response = (WEB3AdminAccInfoLoginPasswordResetInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submitリセット)<BR>
     * パスワードリセットを実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（パスワードリセット）submitリセット」参照。 <BR>
     * @@param l_request - 管理者お客様情報パスワードリセットリクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetResponse
     * @@roseuid 4158E78A02CA
     */
    protected WEB3AdminAccInfoLoginPasswordResetResponse submitReset(WEB3AdminAccInfoLoginPasswordResetRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitReset(WEB3AdminAccInfoLoginPasswordResetRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //管理者の権限チェックを行う。
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, true);
        
        //取引パスワードをチェックする。 
        l_admin.validateTradingPassword(l_request.password);
        
        //証券会社コードを取得する。 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //顧客オブジェクトを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = 
            l_accountMgr.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //部店権限をチェックする。
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //顧客のログインＩＤを取得する。
        long l_lngLoginId = l_mainAccount.getLoginId();
        
        //ログインＩＤに該当するログイン属性を取得する。
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        
        Map l_loginAttr = l_opLoginAdminService.getLoginAttributes(l_lngLoginId);
        
        //ログイン属性より、初期パスワードを取得する。
        String l_strInitPwd = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.INITIAL_PASSWORD);

        //パスワードの復号化を行う。
        WEB3Crypt l_crypt = new WEB3Crypt();
        String l_strDecryptPwd = l_crypt.decrypt(l_strInitPwd); 
        
        //パスワードをハッシュ化する。 
        String l_strHashPwd = PasswordUtils.hashPassword(l_strDecryptPwd);
        
        //ログインテーブルを更新する。
        LoginPK l_loginPk = new LoginPK(l_lngLoginId);
        
        Map l_changeMap = new HashMap();
        
        //ログイン.パスワード = hashPassword()の戻り値 ※初期パスワードをhashしたもの。
        l_changeMap.put("hashed_password", l_strHashPwd);
        
        //ログイン.ログイン有効性 = enabled
        l_changeMap.put("disabled", WEB3LoginDisabledFlagDef.ACCINFO_ENABLED);
        
        //ログイン.ログインエラー回数 = null
        //Need Test to verify - zhang-bn
        l_changeMap.put("failure_count", null);
        
        //ログイン.最終ログインエラー時刻 = null
        //Need Test to verify - zhang-bn
        l_changeMap.put("last_failure_timestamp", null);
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            l_queryProcessor.doUpdateQuery(l_loginPk, l_changeMap);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //前回パスワード更新者コードをセットする。
        l_loginAttr.put(WEB3LoginAttributeKeyDef.LAST_PASSWORDCHANGE_UPDATER, l_admin.getAdministratorCode());

        //前回パスワード変更日付をセットする。
        l_loginAttr.put(
            WEB3LoginAttributeKeyDef.LAST_PWDCHANGE, 
            WEB3PasswordUtility.loginAttributeDateFormat.format(GtlUtils.getSystemTimestamp())); 

        //復号化可能パスワードをセットする。        
        //arg0（key）：　@ログイン属性名.復号化可能パスワード（WEB3_ENCRYPTED_PASSWORD）
        //arg1（value）：　@復号化可能初期パスワード※
        //※復号化可能初期パスワード
        //ログイン属性.get(ログイン属性名.復号化可能初期パスワード)の戻り値
        l_loginAttr.put(WEB3LoginAttributeKeyDef.PASSWORD,l_strInitPwd);
        
        //ログイン属性を更新する。
        l_opLoginAdminService.setLoginAttributes(l_lngLoginId, l_loginAttr); 

        //レスポンスデータを生成する。 
        WEB3AdminAccInfoLoginPasswordResetResponse l_response = (WEB3AdminAccInfoLoginPasswordResetResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
