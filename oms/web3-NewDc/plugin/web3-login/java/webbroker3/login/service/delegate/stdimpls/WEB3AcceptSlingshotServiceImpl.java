head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptSlingshotServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客SS遷移サービス(WEB3AcceptSlingshotServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/21 菊地(SRA) 新規作成
 */

package webbroker3.login.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.login.service.delegate.WEB3AcceptSlingshotService;
import webbroker3.login.message.WEB3AcceptSlingshotResponse;
import webbroker3.login.message.WEB3AcceptSlingshotRequest;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3PwdChangeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3PasswordUtility;

/**
 * （顧客SS遷移サービス）<BR>
 * <BR>
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3AcceptSlingshotServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3AcceptSlingshotService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AcceptSlingshotServiceImpl.class);

    /**
     * @@roseuid 40690783000F
     */
    public WEB3AcceptSlingshotServiceImpl()
    {
        super();
    }

    /**
     * 初期化をする。<BR>
     * 会社（DB:証券会社）のデータを取得する。<BR>
     * 部店（DB:部店）のデータを取得する。<BR>
     * <BR>
     * 部店がログイン停止状態かチェックする。<BR>
     * 　@　@ログイン停止中の場合、ログイン停止中エラーをthrowする。<BR>
     * <BR>
     * 顧客コードの妥当性をチェックする。<BR>
     * 　@　@コード値エラーとしてパスワードエラーをthrowする。<BR>
     * <BR>
     * LoginInfoを取得する。<BR>
     * 　@　@取得できなかった場合、NotFoundExceptionをthrowする。<BR>
     * <BR>
     * 顧客ログインエラー回数（有効性）をチェックする。<BR>
     * 　@　@無効の場合、顧客ロック中エラーをthrowする。<BR>
     * <BR>
     * ログイン（xTradeセッション生成）する。<BR>
     * 　@　@ログインエラーの場合、認証エラーとしてパスワードエラーをthrowする。<BR>
     * <BR>
     * 顧客（DB:顧客マスタ）のデータを取得する。<BR>
     * 　@　@※login_id をキーとしてログインテーブルを検索し、account_idを取得する。<BR>
     * <BR>
     * スリングショットが利用可能かチェックする。<BR>
     * 利用不能な場合<BR>
     * 　@　@ログアウト・ハンドラにディスパッチしてログアウトする。
     * 　@　@スリングショット利用不可エラーをthrowする。<BR>
     * <BR>
     * 「上り」のハッシュ値を計算する。<BR>
     * <BR>
     * 共通．パスワード・ユーティリティを使ってパスワード変更の必要性を判定する。<BR>
     * パスワード変更必要の場合<BR>
     * 　@　@レスポンスにxTradeセッションIDをセットする。<BR>
     * 　@　@セッション属性設定ハンドラにディスパッチして属性を設定する。<BR>
     * パスワード変更不要の場合<BR>
     * 　@　@顧客最終ログイン時刻更新。<BR>
     * 　@　@ログアウト・ハンドラにディスパッチしてログアウトする。<BR>
     * <BR>
     * 結果でレスポンスを作成し、処理を終了する。<BR>
     * <BR>
     * 処理の過程で例外が発生した場合、次の様に振舞う。<BR>
     * WEB3BaseExceptionが発生した場合<BR>
     * 　@　@catchした例外をそのままthrowする。<BR>
     * NotFoundExceptionが発生した場合<BR>
     * 　@　@不正アクセスとしてパスワードエラーをthrowする。<BR>
     * それ以外の例外が発生した場合<BR>
     * 　@　@有り得ない異常として致命的なシステムエラーをthrowする。<BR>
     * ※注１）　@レスポンス編集について<BR>
     * （レスポンス.パスワード変更フラグ == "1"：変更必要）の場合、<BR>
     * ログインタイプ属性より、パスワード最小長，パスワード最大長，<BR>
     * パスワードチェック方式を編集する。<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406278D30251
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AcceptSlingshotRequest l_ssReq =
            (WEB3AcceptSlingshotRequest) l_request;
        WEB3AcceptSlingshotResponse l_ssRes = null;
        try
        {
            String l_uname = l_ssReq.xTradeUsername;
            String l_pwd = l_ssReq.acceptPassword;
            String l_icode = l_ssReq.institutionCode;
            String l_bcode = l_ssReq.branchCode;
            log.debug(
                STR_METHOD_NAME
                    + " .... inst = "
                    + l_icode
                    + ", brch = "
                    + l_bcode
                    + ", user = "
                    + l_uname);

            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_inst = l_accountManager.getInstitution(l_icode);
            InstitutionRow l_institutionRow = (InstitutionRow) l_inst.getDataSourceObject();
            BranchRow l_branchRow =
                (BranchRow) l_accountManager.getBranch(l_inst, l_bcode).getDataSourceObject();

            if (isLoginStop(l_branchRow))
            { /* ログイン停止中 */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90201,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "部店(CODE: " + l_bcode + ") ログイン停止中.");
            }

            if (!checkAcceptCode(l_ssReq.acceptCode, l_branchRow))
            { /* 顧客コード値不正 */
                log.debug(
                    STR_METHOD_NAME + " .... login failed(code value check).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90221,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(CODE: " + l_ssReq.acceptCode + ") コード値不正.");
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
                    + ", brch = "
                    + l_branchRow.getBranchName()
                    + ", user = "
                    + l_loginInfo.getUsername());

            if (!isEnabledUser(l_loginInfo))
            { /* ロック中 */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90202,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_uname + ") ロック中.");
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
                    "顧客(xNAME: " + l_uname + ") 認証エラー.");
            }
            log.debug(STR_METHOD_NAME + " .... sessionID = " + l_sessionID);

            //顧客（DB:顧客マスタ）のデータを取得する。
            // ※login_id をキーとしてログインテーブルを検索し、account_idを取得する。
            LoginRow l_loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_accountManager.getMainAccount(l_loginRow.getAccountId()).getDataSourceObject();
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_mainAccountRow.getFamilyName());

            if (!isRegiSlingshot(l_ssReq.infoServiceCode, false, l_institutionRow))
            { /* スリングショット利用不可 */
                dispatchLogout(l_sessionID);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90209,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_uname + ") スリングショット利用不可.");
            }

            l_ssRes = (WEB3AcceptSlingshotResponse) l_request.createResponse();
            l_ssRes.hashValue = calcHashValue(
                UP_HASH_VALUE,
                l_institutionRow,
                l_branchRow,
                l_mainAccountRow);
            l_ssRes.acceptCodeCD = l_mainAccountRow.getAccountCode();

            if (l_pwdUtil.isChangeNecessity())
            {
                l_ssRes.passwordChangeFlag = WEB3PwdChangeDef.REQUIRED;
                l_ssRes.xTradeSessionID = l_sessionID;

                WEB3SetSessionRequest l_setSessionReq =
                    new WEB3SetSessionRequest();
                l_setSessionReq.sessionAttributes.put(
                    WEB3SessionAttributeDef.INSTITUTION_ID,
                    Long.toString(l_institutionRow.getInstitutionId()));
                l_setSessionReq.sessionAttributes.put(
                    WEB3SessionAttributeDef.BRANCH_ID,
                    Long.toString(l_branchRow.getBranchId()));
                setSessionAttribute(l_sessionID, l_setSessionReq);
            }
            else
            {
                l_ssRes.passwordChangeFlag = WEB3PwdChangeDef.UNNECESSARY;

                updateLastLoginTime(l_loginInfo.getLoginId());
                dispatchLogout(l_sessionID);
            }
            
            // （レスポンス.パスワード変更フラグ == "1"：変更必要）の場合
            if(WEB3PwdChangeDef.REQUIRED.equals(l_ssRes.passwordChangeFlag))
            {
                //ログインタイプ属性を取得する
                HashMap loginTypeAttrs = new HashMap();
                loginTypeAttrs.putAll(l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId()));
                
                //ログインタイプ属性より、パスワード最小長，パスワード最大長，パスワードチェック方式を編集する。
                l_ssRes.passwordMaxLength = 
                    (String) loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH);
                l_ssRes.passwordMinLength = 
                    (String) loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH);
                l_ssRes.passwordCheckMethod = 
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
                    + l_ssReq.institutionCode
                    + ")"
                    + ", 部店(CODE: "
                    + l_ssReq.branchCode
                    + ")"
                    + ", 顧客(xNAME: "
                    + l_ssReq.xTradeUsername
                    + ")"
                    + " 何れかが存在しない.不正アクセスとみなす.",
                ex);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
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
        return l_ssRes;
    }
}
@
