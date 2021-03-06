head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AdministratorLoginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者ログインサービス(WEB3AdministratorLoginServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/20 菊地(SRA) 新規作成
Revesion History    : 2004/07/28 中尾(SRA) 管理者テーブル関連のDAOがgtl-baseから
　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@webbroker3.gentrade.dataへ移動した事に伴う対応
 */

package webbroker3.login.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import webbroker3.login.message.WEB3AdministratorLoginRequest;
import webbroker3.login.message.WEB3AdministratorLoginResponse;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.login.service.delegate.WEB3AdministratorLoginService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeDef;
import webbroker3.common.define.WEB3PwdChangeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;

/**
 * （管理者ログインサービス）
 * <BR>
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3AdministratorLoginServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3AdministratorLoginService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdministratorLoginServiceImpl.class);

    /**
     * @@roseuid 408F4D8001D1
     */
    public WEB3AdministratorLoginServiceImpl()
    {

    }

    /**
     * 初期化をする。<BR>
     * 会社（DB:証券会社）のデータを取得する。<BR>
     * <BR>
     * 管理者コードの妥当性をチェックする。<BR>
     * 　@　@不正の場合、コード値エラーとしてパスワード・エラーをthrowする。<BR>
     * <BR>
     * LoginInfoを取得する。<BR>
     * 　@　@取得できなかった場合、NotFoundExceptionをthrowする。<BR>
     * <BR>
     * 管理者ログインエラー回数（有効性）をチェックする。<BR>
     * 　@　@無効の場合、管理者のロック中エラーをthrowする。<BR>
     * <BR>
     * ログイン（xTradeセッション生成）する。<BR>
     * 　@　@ログインエラーの場合、認証エラーとしてパスワードエラーをthrowする。<BR>
     * <BR>
     * 管理者（DB:管理者マスタ）のデータを取得する。<BR>
     * 管理者の部店IDを使用して部店（DB:部店）のデータを取得する。<BR>
     * <BR>
     * セッション属性設定ハンドラにディスパッチしてセッション属性を設定する。<BR>
     * <BR>
     * 管理者を管理者情報に変換する。<BR>
     * 会社を会社情報に変換する。<BR>
     * 部店を部店情報に変換する。<BR>
     * <BR>
     * リクエスト属性：ログインタイプが「パスワード変更」の場合は無条件で<BR>
     * パスワード変更必要とする。<BR>
     * そうでない場合は共通．パスワード・ユーティリティを使って必要性を判定する。<BR>
     * <BR>
     * パスワード変更が不要な場合<BR>
     * 　@　@管理者の最終ログイン時刻を更新する。<BR>
     * <BR>
     * 結果でレスポンスを作成し、処理を終了する。<BR>
     * <BR>
     * 処理の過程で例外が発生した場合、次の様に振舞う。<BR>
     * WEB3BaseExceptionが発生した場合<BR>
     * 　@　@catchした例外をそのままthrowする。<BR>
     * NotFoundExceptionが発生した場合<BR>
     * 　@　@不正アクセスとしてパスワード・エラーをthrowする。<BR>
     * それ以外の例外が発生した場合<BR>
     * 　@　@有り得ない異常として致命的なシステムエラーをthrowする。<BR>
     * <BR>
     * ※注１）　@レスポンス編集について<BR>
     * （レスポンス.パスワード変更フラグ == "1"：変更必要）の場合、<BR>
     * ログインタイプ属性より、パスワード最小長，パスワード最大長，<BR>
     * パスワードチェック方式を編集する。<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4084F84E0096
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdministratorLoginRequest l_loginReq =
            (WEB3AdministratorLoginRequest) l_request;
        WEB3AdministratorLoginResponse l_loginRes = null;
        try
        {
            String l_uname = l_loginReq.xTradeUsername;
            String l_ucode = l_loginReq.administratorCode;
            String l_pwd = l_loginReq.administratorPassword;
            String l_icode = l_loginReq.institutionCode;
            /*現在はダミー：String l_bcode = l_loginReq.branchCode;*/
            log.debug(
                STR_METHOD_NAME
                    + " .... inst = "
                    + l_icode
                    + ", mgr = "
                    + l_uname);

            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_inst = l_accountManager.getInstitution(l_icode);
            InstitutionRow l_institutionRow = (InstitutionRow) l_inst.getDataSourceObject();

            if (!checkAdministratorCode(l_ucode, l_institutionRow))
            { /* 管理者コード値不正 */
                log.debug(
                    STR_METHOD_NAME + " .... login failed(code value check).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90221,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "管理者(CODE: " + l_ucode + ") コード値不正.");
            }

            OpLoginAdminService l_adminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            LoginInfo l_loginInfo = l_adminService.getLoginInfo(l_uname);
            if (l_loginInfo == null)
            {
                throw new NotFoundException(l_uname + " not found.");
            }
            WEB3PasswordUtility l_pwdUtil =
                new WEB3PasswordUtility(l_loginInfo.getLoginId());
            log.debug(
                STR_METHOD_NAME
                    + " .... inst = "
                    + l_institutionRow.getInstitutionName()
                    + ", mgr = "
                    + l_loginInfo.getUsername());

            if (!isEnabledUser(l_loginInfo))
            { /* ロック中 */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90215,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "管理者(xNAME: " + l_uname + ") ロック中.");
            }

            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_sessionID = l_securityService.logIn(l_uname, l_pwd);
            if (l_sessionID == null)
            { /* ログイン失敗 */
                log.debug(
                    STR_METHOD_NAME + " .... login failed(authenticate).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90203,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "管理者(xNAME: " + l_uname + ") 認証エラー.");
            }
            log.debug(STR_METHOD_NAME + " .... sessionID = " + l_sessionID);

            AdministratorRow l_administratorRow =
                AdministratorDao.findRowByLoginId(l_loginInfo.getLoginId());
            if (l_administratorRow == null)
            {
                throw new NotFoundException(
                    "administrator not found.(login_id: "
                        + l_loginInfo.getLoginId()
                        + ")");
            }

            BranchRow l_branchRow =
                (BranchRow) l_accountManager.getBranch(l_administratorRow.getBranchId()).getDataSourceObject();

            WEB3SetSessionRequest l_setSessionReq = new WEB3SetSessionRequest();

            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.INSTITUTION_ID,
                Long.toString(l_institutionRow.getInstitutionId()));
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.BRANCH_ID,
                Long.toString(l_branchRow.getBranchId()));
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.RIGHT_LEVEL,
                l_administratorRow.getPermissionLevel());

            setSessionAttribute(l_sessionID, l_setSessionReq);

            l_loginRes =
                (WEB3AdministratorLoginResponse) l_request.createResponse();
            l_loginRes.xTradeSessionID = l_sessionID;
            l_loginRes.administratorInfo = getAdministratorInfo(
                l_administratorRow,
                l_loginInfo);
            l_loginRes.institutionInfo = getInstitutionInfo(l_institutionRow);
            l_loginRes.branchInfo = getBranchInfo(l_branchRow);

            if (WEB3LoginTypeDef.PWDCHANGE.equals(l_loginReq.loginType)
                || l_pwdUtil.isChangeNecessity())
            {
                l_loginRes.passwordChangeFlag = WEB3PwdChangeDef.REQUIRED;
            }
            else
            {
                l_loginRes.passwordChangeFlag = WEB3PwdChangeDef.UNNECESSARY;
                updateLastLoginTime(l_loginInfo.getLoginId());
            }
            
            //（レスポンス.パスワード変更フラグ == "1"：変更必要）の場合
            if(WEB3PwdChangeDef.REQUIRED.equals(l_loginRes.passwordChangeFlag))
            {
                //ログインタイプ属性を取得する
                HashMap loginTypeAttrs = new HashMap();
                loginTypeAttrs.putAll(l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId()));
                
                //ログインタイプ属性より、パスワード最小長，パスワード最大長，パスワードチェック方式を編集する。
                l_loginRes.passwordMaxLength = 
                    (String) loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH);
                l_loginRes.passwordMinLength = 
                    (String) loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH);
                l_loginRes.passwordCheckMethod = 
                    (String) loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE);
                
            }
        }
        catch (WEB3BaseException ex)
        {
            throw ex;
        }
        catch (NotFoundException ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_90221,
                getClass().getName() + "." + STR_METHOD_NAME,
                "会社(CODE: "
                    + l_loginReq.institutionCode
                    + ")"
                    + ", 管理者(xNAME: "
                    + l_loginReq.xTradeUsername
                    + ")"
                    + " 何れかが存在しない.不正アクセスとみなす.",
                ex);
        }
        catch (Exception ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage(),
                ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_loginRes;
    }
}
@
