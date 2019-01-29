head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoPasswordChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報暗証番号変更サービスImpl(WEB3AccInfoPasswordChangeServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoPasswordChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3SecurityLevelDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.WEB3TradingPasswordUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報暗証番号変更サービスImpl)<BR>
 * お客様情報暗証番号変更サービス実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoPasswordChangeServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoPasswordChangeService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoPasswordChangeServiceImpl.class);
    
    /**
     * @@roseuid 418F39FF003E
     */
    public WEB3AccInfoPasswordChangeServiceImpl() 
    {
     
    }
    
    /**
     * 暗証番号変更処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、お客様情報暗証番号変更入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * ○ 引数のリクエストデータが、お客様情報暗証番号変更リクエストの場合 <BR>
     * 　@－submit変更()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 416BBA0B0338
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AccInfoPasswordChangeInputRequest)
        {
            l_response = getInputScreen((WEB3AccInfoPasswordChangeInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AccInfoPasswordChangeCompleteRequest)
        {
            l_response = submitChange((WEB3AccInfoPasswordChangeCompleteRequest)l_request);
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
     * 暗証番号変更入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「お客様情報（暗証番号変更）get入力画面」参照。<BR>
     * @@param l_request - お客様情報暗証番号変更入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputResponse
     * @@roseuid 416BBA0B0357
     */
    protected WEB3AccInfoPasswordChangeInputResponse getInputScreen(WEB3AccInfoPasswordChangeInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AccInfoPasswordChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //受付時間チェックを行う。  
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //顧客オブジェクトを取得する。 
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)getMainAccount();
        
        //ログインIDを取得する。
        long l_lngLoginId = l_mainAccount.getLoginId();
        
        //取引パスワードユーティリティを生成する。
        WEB3TradingPasswordUtility l_tradingPwdUtil = new WEB3TradingPasswordUtility(l_lngLoginId);
        
        WEB3AccInfoPasswordChangeInputResponse l_response = (WEB3AccInfoPasswordChangeInputResponse)l_request.createResponse();
        
        //パスワード最小長：　@getPasswordMinLength()の戻り値
        l_response.passwordLower = l_tradingPwdUtil.getPasswordMinLength();
        
        //パスワード最大長：　@getPasswordMaxLength()の戻り値
        l_response.passwordUpper = l_tradingPwdUtil.getPasswordMaxLength();
        
        //パスワードチェック方式：　@getPasswordCheckMode()の戻り値
        l_response.passwordCheckType = l_tradingPwdUtil.getPasswordCheckMode();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    
    /**
     * (submit変更)<BR>
     * 暗証番号変更完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「お客様情報（暗証番号変更）submit変更」参照。<BR>
     * <BR>
     *  ==========================================================    <BR>
     *          シーケンス図 :  お客様情報（暗証番号変更）submit変更                  <BR>
     *          具体位置     :  1.10  （checkPassword() != CHECK_NORMAL）の場合 、 <BR>
     * <BR>
     *          新パスワード長不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90216                    <BR> 
     * <BR>
     *          新パスワード文字種不正 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90214                    <BR> 
     * <BR>
     *          新パスワードが現在パスワードと同じ 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90217                    <BR> 
     * <BR>
     *          新パスワードがログイン名と同じ 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90218                    <BR>  
     * <BR>
     *          新パスワードが旧パスワード（３世代以前）と同じ 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90219                    <BR> 
     * <BR>
     *          新パスワードが旧パスワード（３世代以前）と類似 例外をスローする。 <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90220                    <BR> 
     * <BR>
     *          パスワード変更時チェック関連のログインタイプ属性に不備がある 例外をスローする。 <BR>
     *          class           : WEB3SystemLayerException                <BR>
     *          tag             : SYSTEM_ERROR_80002                    <BR>
     *  ==========================================================    <BR>
     * <BR>
     * @@param l_request - お客様情報暗証番号変更リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteResponse
     * @@roseuid 416BBA0B0376
     */
    protected WEB3AccInfoPasswordChangeCompleteResponse submitChange(WEB3AccInfoPasswordChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AccInfoPasswordChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //受付時間チェックを行う。  
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)getMainAccount();
        
        //注文チェックインスタンスを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //取引パスワードをチェックする。
        OrderValidationResult l_validationResult = l_orderValidator.validateTradingPassword(l_mainAccount, l_request.oldPassword);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("チェックエラーの場合はを例外をスローする");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //ログインIDを取得する。
        long l_lngLoginId = l_mainAccount.getLoginId();
        
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //取引パスワードユーティリティを生成する。
        WEB3TradingPasswordUtility l_tradingPwdUtil = new WEB3TradingPasswordUtility(l_lngLoginId);
        
        //セキュリティ・レベルに従って、新パスワードの値チェックを実行する。
        int l_intCheckResult = l_tradingPwdUtil.checkPassword(
            l_request.loginName,        //リクエストデータ.ログイン名
            l_request.oldPassword,      //リクエストデータ.旧暗証番号
            l_request.newPassword1      //リクエストデータ.新暗証番号１
            );
            
        //（checkPassword() != CHECK_NORMAL）の場合、対応する例外をスローする。
        switch (l_intCheckResult)
        {
            case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90216,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "　@新パスワード長不正."
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90214,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "　@新パスワード文字種不正."
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_SAME_CURRENT:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90217,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "　@新パスワードが現在パスワードと同じ."
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_SAME_NAME:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90218,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "　@新パスワードがログイン名と同じ."
                    );
                    
            case WEB3PasswordUtility.CHECK_ERROR_SAME_BEFORE:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90219,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "　@新パスワードが旧パスワード（３世代以前）と同じ."
                    );
                    
            case WEB3PasswordUtility.CHECK_ERROR_SIMILAR_BEFORE:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90220,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "　@新パスワードが旧パスワード（３世代以前）と類似."
                    );
                    
            case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "パスワード変更時チェック関連のログインタイプ属性に不備がある."
                    );
        }

        //1世代前～３世代前取引パスワードを更新する。
        //※　@セキュリティ・レベル == ”高”または、”やや高”の場合のみ
        
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            
        LoginInfo l_loginInfo = l_opLoginAdminService.getLoginInfo(l_lngLoginId);
        
        Map l_loginTypeAttr = l_opLoginAdminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());     
        String l_strSecurityLevel = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL);
        if (WEB3SecurityLevelDef.HIGH.equals(l_strSecurityLevel) || WEB3SecurityLevelDef.LITTLE_HIGH.equals(l_strSecurityLevel))
        {
            l_tradingPwdUtil.saveOldTradingPasswords(l_mainAccount.getTradingPassword());
        }
        
        //WEB3暗号化機@能にて、新暗証番号を暗号化する。
        WEB3Crypt l_crypt = new WEB3Crypt();
        String l_strEncryptPwd = l_crypt.encrypt(l_request.newPassword1);
        
        //顧客マスタ更新
        //getDataSourceObject()にて取得した顧客マスタ行について、取引パスワードを更新（DB Update）する。
        Map l_changeMap = new Hashtable();
        
        //取引パスワード = 新暗証番号(暗号化)
        l_changeMap.put("trading_password", l_strEncryptPwd);
        
        //取引パスワード更新者コード = 口座コード
        l_changeMap.put("trading_password_updater", l_mainAccount.getAccountCode());
        
        //処理日時
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();
        
        //取引パスワード更新日時 = 処理日時
        l_changeMap.put("tr_pwd_last_update_timestamp", l_datProcessDate);
        
        //更新日時 = 処理日時
        l_changeMap.put("last_updated_timestamp", l_datProcessDate);
               
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            l_queryProcessor.doUpdateQuery(l_mainAccountRow.getPrimaryKey(), l_changeMap);
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
        
        WEB3AccInfoPasswordChangeCompleteResponse l_response = (WEB3AccInfoPasswordChangeCompleteResponse)l_request.createResponse();
     
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
