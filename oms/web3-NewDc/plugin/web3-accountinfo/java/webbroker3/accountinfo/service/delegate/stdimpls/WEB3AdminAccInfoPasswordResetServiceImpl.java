head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordResetServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報暗証番号リセットサービスImpl(WEB3AdminAccInfoPasswordResetServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordResetService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報暗証番号リセットサービスImpl)<BR>
 * 管理者お客様情報暗証番号リセットサービス実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordResetServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoPasswordResetService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoPasswordResetServiceImpl.class);   
    
    /**
     * @@roseuid 418F3A03030D
     */
    public WEB3AdminAccInfoPasswordResetServiceImpl() 
    {
     
    }
    
    /**
     * 暗証番号リセット処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報暗証番号リセット<BR>
     * 入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報暗証番号リセット<BR>
     * リクエストの場合 <BR>
     * 　@－submitリセット()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41649D6401C2
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminAccInfoPasswordResetInputRequest)
        {
            l_response = getInputScreen((WEB3AdminAccInfoPasswordResetInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoPasswordResetRequest)
        {
            l_response = submitReset((WEB3AdminAccInfoPasswordResetRequest)l_request);
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
     * 暗証番号リセット入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（暗証番号リセット）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報暗証番号リセット入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B77A101B1
     */
    protected WEB3AdminAccInfoPasswordResetInputResponse getInputScreen(WEB3AdminAccInfoPasswordResetInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoPasswordResetInputRequest l_request)";
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
        WEB3AdminAccInfoPasswordResetInputResponse l_response = (WEB3AdminAccInfoPasswordResetInputResponse)l_request.createResponse();        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submitリセット)<BR>
     * 暗証番号リセットを実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（暗証番号リセット）submitリセット」参照。 <BR>
     * @@param l_request - 管理者お客様情報暗証番号リセットリクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetResponse
     * @@roseuid 41649D6401C4
     */
    protected WEB3AdminAccInfoPasswordResetResponse submitReset(WEB3AdminAccInfoPasswordResetRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " submitReset(WEB3AdminAccInfoPasswordResetRequest l_request)";
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
        
        //顧客マスタテーブルを更新する。
        MainAccountPK l_mainAccountPk = new MainAccountPK(l_mainAccount.getAccountId());
        
        Map l_changeMap = new HashMap();
        
        //顧客マスタ.取引パスワード = get(ログイン属性名.復号化可能初期パスワード)の戻り値
        l_changeMap.put("trading_password", l_strInitPwd);
        
        //顧客マスタ.取引パスワード更新者コード = 管理者.管理者コード
        l_changeMap.put("trading_password_updater", l_admin.getAdministratorCode());
        
        //処理日時
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();
        
        //顧客マスタ.取引パスワード更新日時 = TradingSystem.getSystemTymestamp()
        l_changeMap.put("tr_pwd_last_update_timestamp", l_datProcessDate);
        
        //顧客マスタ.更新日時 = TradingSystem.getSystemTymestamp()
        l_changeMap.put("last_updated_timestamp", l_datProcessDate);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            l_queryProcessor.doUpdateQuery(l_mainAccountPk, l_changeMap);
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
        
        //レスポンスデータを生成する。 
        WEB3AdminAccInfoPasswordResetResponse l_response = (WEB3AdminAccInfoPasswordResetResponse)l_request.createResponse();        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
