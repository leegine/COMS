head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3HashAuthenticationAcceptLoginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ハッシュ認証ログインサービス実現(WEB3HashAuthenticationAcceptLoginServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/06/20 栄イ(中訊) 新規作成
                      2006/12/08 孫(FLJ) セッション設定内容追加
*/

package webbroker3.login.service.delegate.stdimpls;

import java.util.StringTokenizer;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.login.message.WEB3HashAuthenticationAcceptLoginRequest;
import webbroker3.login.message.WEB3HashAuthenticationAcceptLoginResponse;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.login.service.delegate.WEB3DigestKey;
import webbroker3.login.service.delegate.WEB3DigestService;
import webbroker3.login.service.delegate.WEB3HashAuthenticationAcceptLoginService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributePK;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (ハッシュ認証ログインサービス実現)<BR>
 * <BR>
 * @@author      栄イ(中訊)
 * @@version     1.00
 */
public class WEB3HashAuthenticationAcceptLoginServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3HashAuthenticationAcceptLoginService
{

    /**
     * ログ出力
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        	WEB3HashAuthenticationAcceptLoginServiceImpl.class);

    /**
     * @@roseuid 404835050203
     */
    public WEB3HashAuthenticationAcceptLoginServiceImpl()
    {

    }

    /**
     * 初期化をする。<BR>
     * ハッシュ認証ログインサービスを実行する<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ハッシュ認証ログイン）execute」 参照<BR>
     * <BR>
     * 部店がログイン停止状態かチェックする。<BR>
     * ログイン停止中の場合、ログイン停止中エラーをthrowする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_90201<BR>
     * <BR>
     * 注文チャネルをチェックする。<BR>
     * 不正な値の場合、その他認証エラーをthrowする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_90221<BR>
     * <BR>
     * 顧客コードの妥当性をチャックする。<BR>
     * コード値エラーとしてその他認証エラーをthrowする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_90222<BR>
     * <BR>
     * 顧客ログインエラー回数(有効性)をチェックする。<BR>
     * 無効な場合、顧客ロック中エラーをthrowする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_90202<BR>
     * <BR>
     * 作られたキーを認証サービスに渡し、有効なキーであるかどうかを認証、<BR>
     * 失敗の場合、メソ()ッドを呼び出しところからエラーを投げる。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_90223<BR>
     * <BR>
     * ログイン（xTradeセッション生成）する。<BR>
     * <BR>
     * セッション属性設定ハンドラにディスパッチしてセッション属性を設定する。<BR>
     * <BR>
     * レスポンス作成<BR>
     *    顧客（DB:顧客マスタ）のデータを取得する。<BR>
     *    顧客を顧客情報に変換する。<BR>
     *    会社を会社情報に変換する。<BR>
     *    部店を部店情報に変換する。<BR>
     *    顧客マスタの先頭画面IDを取得する。<BR>
     *    サービス実施状態取得を取得する。<BR>
     * <BR>
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4044589502B5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3HashAuthenticationAcceptLoginRequest l_loginRequest =
        	(WEB3HashAuthenticationAcceptLoginRequest)l_request;
 
        //部店コードを取得
        String l_strBranchCode=l_loginRequest.branchCode;

        //xTradeユーザ名を取得
        String l_strUsername=l_loginRequest.xTradeUsername;

        //顧客コードを取得
        String l_strAcceptCode=l_loginRequest.acceptCode;

        try
        {
            //1 getInstitution(String)
            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_loginRequest.institutionCode);
            
            //2 getBranch(Institution, String)
            InstitutionRow l_institutionRow = (InstitutionRow) l_institution.getDataSourceObject();
            BranchRow l_branchRow = (BranchRow) l_accountManager.getBranch(
            	l_institution, l_strBranchCode).getDataSourceObject();
            
            //3 isログイン停止(BranchRow)
            //部店がログイン停止状態かチェックする。
            //ログイン停止中の場合、ログイン停止中エラーをthrowする。
            if (this.isLoginStop(l_branchRow))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90201,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "部店(CODE: " + l_strBranchCode + ") ログイン停止中.");
            }
            
            //3.1 check注文チャネル(String)
            //注文チャネルをチェックする。
            //不正な値の場合、その他認証エラーをthrowする。
            if (!this.checkOrderChannel(l_loginRequest.orderChannel))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90221,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_strUsername + ") 注文チャネル値不正.");
            }
            
            //3.2 check顧客コード(String, BranchRow)
            //顧客コードの妥当性をチャックする。
            //コード値エラーとしてその他認証エラーをthrowする。(エラーコード新規)
            if (!this.checkAcceptCode(l_strAcceptCode, l_branchRow))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90222,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(CODE: " + l_strAcceptCode + ") コード値不正.");
            }
            
            //3.3 getLoginInfo(long)
            OpLoginAdminService l_adminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            LoginInfo l_loginInfo = l_adminService.getLoginInfo(l_strUsername);
            
            //3.4 isログインユーザ有効(LoginInfo)
            //顧客ログインエラー回数(有効性)をチェックする。
            //無効な場合、顧客ロック中エラーをthrowする。
            if (!isEnabledUser(l_loginInfo))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90202,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "ログインユーザ(INFO: " + l_loginInfo + ") 無効.");
            }
            
            //3.4.1 << create >>
            //ログインリクエストの「認証用ハッシュ値」、「作成日時」、
            //「GUID」三つの値を用いて、認証キーオブジェクトを作成。
            WEB3DigestKey l_digestKey = new WEB3DigestKey();
            l_digestKey.setKey1(WEB3DateUtility.formatDate(l_loginRequest.createDate, "yyyyMMddHHmmss"));
            l_digestKey.setKey3(l_loginRequest.guid);
            l_digestKey.setKey4(l_loginRequest.hstr);
            
            //3.4.2  is有効なキー(WEB3DigestKey)
            //作られたキーを認証サービスに渡し、有効なキーであるかどうかを認証、失敗の場合、
            //メソ()ッドを呼び出しところからエラーを投げる。(新規エラーコード：SHA1認証エラー)
            //認証メソッド内部の実現：
            //valueA = リクエストの「認証用ハッシュ値」　@（請求先のハッシュ値）
            //valueB = サービス計算したハッシュ値　@（請求先の「作成日時」、「GUID」、
            //	と固定文字列「HimawariWEBBROKER3」より計算）
            //valueA = valueBの場合認証成功 (true)、valueA != valueBの場合認証失敗 (false)。
            WEB3DigestService l_digestService = 
            	(WEB3DigestService)Services.getService(WEB3DigestService.class);
            if (!l_digestService.isValidKey(l_digestKey))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90223,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "キー(KEY: " + l_digestKey + ") 無効.");
            }

            //3.4.3 doFindByPrimaryKeyQuery(PrimaryKey)
            //LoginAttributePKオブジェクトにLogin_IDと
            //WEB3LoginAttributeKeyDef.PASSWORDを指定して、パスワードを取得
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            LoginAttributePK l_loginAttributePK = new LoginAttributePK(
            	l_loginInfo.getLoginId(),
            	WEB3LoginAttributeKeyDef.PASSWORD);
            LoginAttributeRow l_loginAttributeRow = 
            	(LoginAttributeRow) l_queryProcessor.doFindByPrimaryKeyQuery(l_loginAttributePK);

            //3.4.4 decrypt(String)
            //新規WEB3Cryptオブジェクトを用いて前のステップで取得したパスワードを復号化
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strDecryptPassword = l_crypt.decrypt(l_loginAttributeRow.getAttributeValue());

            //3.4.5 logIn(String, String)
            //ログインを実行
            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_strSessionID = l_securityService.logIn(l_strUsername, l_strDecryptPassword);

            //3.4.6 setセッション属性(String, WEB3SetSessionRequest)
            //セッション属性設定ハンドラにディスパッチしてセッション属性を設定する
            WEB3SetSessionRequest l_setSessionRequest = new WEB3SetSessionRequest();
            
            //↓↓　@　@2006/12/08 孫(FLJ) セッション設定内容追加
            l_setSessionRequest.sessionAttributes.put(
                    WEB3SessionAttributeDef.ORDER_CHANNEL,
                    l_loginRequest.orderChannel);
            l_setSessionRequest.sessionAttributes.put(
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV,
                    (l_loginRequest.orderRootDiv != null)
                        ? l_loginRequest.orderRootDiv
                        : WEB3OrderRootDivDef.PC);
            l_setSessionRequest.sessionAttributes.put(
                    WEB3SessionAttributeDef.INSTITUTION_ID,
                    Long.toString(l_institutionRow.getInstitutionId()));
            l_setSessionRequest.sessionAttributes.put(
                    WEB3SessionAttributeDef.BRANCH_ID,
                    Long.toString(l_branchRow.getBranchId()));
                StringTokenizer l_tokenizer = new StringTokenizer(l_strSessionID, ":");
                String l_strToken = l_tokenizer.nextToken();
                l_strToken = l_tokenizer.nextToken();
                l_strToken = l_tokenizer.nextToken();
                l_setSessionRequest.sessionAttributes.put(
                    WEB3SessionAttributeDef.SESSION_ID,
                    l_strToken);
                if (l_loginRequest.ipAddress != null)
                {
                    l_setSessionRequest.sessionAttributes.put(
                        WEB3SessionAttributeDef.IP_ADDRESS,
                        l_loginRequest.ipAddress);
                }            
            //↑↑　@　@2006/12/08 孫(FLJ) セッション設定内容追加
                
            setSessionAttribute(l_strSessionID, l_setSessionRequest);

            //3.4.7 getMainAccount(long)
            //これらのステップによりレスポンス作成
            //顧客(DB:顧客マスタ)のデータを取得する
            //顧客を顧客情報に変換する。
            //会社を会社情報に変換する。
            //部店を部店情報に変換する。
            //顧客マスタの先頭画面IDを取得する。
            //サービス実施状態取得する。
            WEB3HashAuthenticationAcceptLoginResponse l_loginResponse = 
            	(WEB3HashAuthenticationAcceptLoginResponse) l_request.createResponse();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(
            	LoginDao.findRowByPk(l_loginInfo.getLoginId()).getAccountId());

            //3.4.8 get顧客情報(String, MainAccountRow, LoginInfo, String)
            MainAccountRow l_mainAccountRow = 
            	(MainAccountRow)l_mainAccount.getDataSourceObject();
            l_loginResponse.acceptInfo = this.getAcceptInfo(
            	l_strAcceptCode, 
            	l_mainAccountRow, 
            	l_loginInfo,
                l_loginRequest.orderRootDiv);
            
            //3.5 get会社情報(InstitutionRow)
            l_loginResponse.institutionInfo = this.getInstitutionInfo(l_institutionRow);
            
            //3.6 get部店情報(BranchRow)
            l_loginResponse.branchInfo = this.getBranchInfo(l_branchRow);
            
            //3.7 getサービス実施状態(InstitutionRow, BranchRow, MainAccountRow)
            l_loginResponse.serviceImpState = this.getServiceImpState(
            	l_institutionRow, 
            	l_branchRow, 
            	l_mainAccountRow);
            
            //顧客マスタの先頭画面IDを取得する。
            l_loginResponse.topPageID = l_mainAccountRow.getTopPageId();
            
            //xTradeセッションIDを取得する。
            l_loginResponse.xTradeSessionID = l_strSessionID;

            log.exiting(STR_METHOD_NAME);
            return l_loginResponse;
        }
        catch (NotFoundException l_nfexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_nfexp.getMessage(),
                l_nfexp);
        }
        catch (DataNetworkException l_dnwexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_dnwexp.getMessage(),
                l_dnwexp);
        }
        catch (DataQueryException l_dqexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_dqexp.getMessage(),
                l_dqexp);
        }
    }
}
@
