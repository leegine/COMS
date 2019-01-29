head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetAccountServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客成りすましサービス(WEB3SetAccountServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/26 菊地(SRA) 新規作成
 */

package webbroker3.login.service.delegate.stdimpls;

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

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.login.message.WEB3SetAccountRequest;
import webbroker3.login.message.WEB3SetAccountResponse;
import webbroker3.login.service.delegate.WEB3SetAccountService;
import webbroker3.util.WEB3LogUtility;

/**
 * (顧客成りすましサービス)
 * <BR>
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3SetAccountServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3SetAccountService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SetAccountServiceImpl.class);

    /**
     * @@roseuid 404835050203
     */
    public WEB3SetAccountServiceImpl()
    {

    }

    /**
     * 初期化をする。<BR>
     * <BR>
     * セッション属性：会社IDを取得する。<BR>
     * 　@　@会社IDが取得できなかった場合<BR>
     * 　@　@　@　@有り得ない異常として致命的なシステムエラーをthrowする。<BR>
     * <BR>
     * 会社（DB:証券会社）のデータを取得する。<BR>
     * 部店（DB:部店）のデータを取得する。<BR>
     * <BR>
     * 部店がログイン停止状態かチェックする。<BR>
     * 　@　@ログイン停止中の場合、ログイン停止中エラーをthrowする。<BR>
     * <BR>
     * 注文チャネルをチェックする。<BR>
     * 　@　@不正な値の場合、パスワード不一致（成りすまし不能）エラーをthrowする。<BR>
     * 　@　@※通常有り得ない。不正アクセスとみなす。<BR>
     * <BR>
     * 成りすまし対象の顧客コードをチェックする。<BR>
     * 　@　@顧客コード値不正エラーをthrowする。<BR>
     * <BR>
     * LoginInfoを取得する。<BR>
     * 　@　@※この時取得するLoginInfoは成りすます顧客のもの。<BR>
     * 　@　@成りすまし対象顧客のLoginInfoが取得できなかった場合<BR>
     * 　@　@指定顧客データなしエラーをthrowする。<BR>
     * <BR>
     * 共通．パスワード・ユーティリティを使って顧客成りすまし時パスワード<BR>
     * チェックを行う。<BR>
     * 　@　@※ユーティリティはCCオペレータで生成。引数で顧客のIDを渡す。<BR>
     * 　@　@成りすまし時パスワードチェック実施で不一致が発生した場合<BR>
     * 　@　@　@　@パスワード不一致（成りすまし不能）エラーをthrowする。<BR>
     * <BR>
     * 顧客に成りすまし、セッションIDを取得する。<BR>
     *  ※login_id をキーとしてログインテーブルを検索し、account_idを取得する。<BR>
     *  ※xTradeセッションIDは不要。<BR>
     * <BR>
     * セッション属性を設定する。<BR>
     * 　@　@※ここではActiveSessionと関連付けられているので、直接設定する。<BR>
     * 　@　@※成りすます顧客の部店IDを再設定する。<BR>
     * <BR>
     * 顧客（DB:顧客マスタ）データを取得する。<BR>
     * 　@　@※login_id をキーとしてログインテーブルを検索し、account_idを取得する。<BR>
     * 顧客を顧客情報に変換する。<BR>
     * 部店を部店情報に変換する。<BR>
     * 顧客マスタの先頭画面IDを取得する。<BR>
     * <BR>
     * サービス実施状態取得を取得する。<BR>
     * <BR>
     * 結果でレスポンスを作成し、処理を終了する。<BR>
     * <BR>
     * 処理の過程で例外が発生した場合、次の様に振舞う。<BR>
     * WEB3BaseExceptionが発生した場合<BR>
     * 　@　@catchした例外をそのままthrowする。<BR>
     * NotFoundExceptionが発生した場合<BR>
     * 　@　@有り得ない異常として致命的なシステムエラーをthrowする。<BR>
     * それ以外の例外が発生した場合<BR>
     * 　@　@有り得ない異常として致命的なシステムエラーをthrowする。<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4044589502B5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SetAccountRequest l_setAccountReq =
            (WEB3SetAccountRequest) l_request;
        WEB3SetAccountResponse l_setAccountRes = null;
        try
        {
            String l_uname = l_setAccountReq.xTradeUsername;
            String l_pwd = l_setAccountReq.acceptPassword;
            String l_bcode = l_setAccountReq.branchCode;
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_uname
                    + ", branch = "
                    + l_bcode);

            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_instIDStr =
                l_securityService.getSessionProperty(
                    WEB3SessionAttributeDef.INSTITUTION_ID);
            if (l_instIDStr == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "会社IDがセッション属性にセットされていない.（システム異常）");
            }

            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_inst =
                l_accountManager.getInstitution(Long.parseLong(l_instIDStr));
            InstitutionRow l_institutionRow = (InstitutionRow) l_inst.getDataSourceObject();
            BranchRow l_branchRow =
                (BranchRow) l_accountManager
                    .getBranch(l_inst, l_bcode)
                    .getDataSourceObject();

            if (isLoginStop(l_branchRow))
            { /* ログイン停止中 */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90201,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "部店(CODE: " + l_bcode + ") ログイン停止中.");
            }

            if (!checkOrderChannel(l_setAccountReq.orderChannel))
            { /* 注文チャネル値不正 */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90213,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_uname + ") 注文チャネル値不正.");
            }

            if (!checkAcceptCode(l_setAccountReq.acceptCode, l_branchRow))
            { /* 顧客コード値不正 */
                log.debug(
                    STR_METHOD_NAME + " .... login failed(code value check).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90207,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(CODE: " + l_setAccountReq.acceptCode + ") コード値不正.");
            }

            OpLoginAdminService l_adminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            LoginInfo l_loginInfo = l_adminService.getLoginInfo(l_uname);
            if (l_loginInfo == null)
            { /* CCオペレータには指定ユーザ名が存在しない事を知らせる必要がある */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90205,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_uname + ") は存在しない.");
            }
            WEB3PasswordUtility l_pwdUtil =
                new WEB3PasswordUtility(l_securityService.getLoginId());
            log.debug(
                STR_METHOD_NAME
                    + " .... brch = "
                    + l_branchRow.getBranchName()
                    + ", user = "
                    + l_loginInfo.getUsername());

            if (!l_pwdUtil
                .checkSetAccountPassword(
                    l_setAccountReq.passwordCheckFlag,
                    l_loginInfo.getLoginId(),
                    l_pwd))
            { /* パスワード不一致 */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90213,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_uname + ") パスワード不一致.");
            }
            
            //login_id をキーとしてログインテーブルを検索し、account_idを取得する
            LoginRow l_loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());
            
            /* 顧客に成りすます. */
            String l_sessionID = 
                changeAccount(l_loginRow.getAccountId(), null);
            log.debug("セッションID:[" + l_sessionID + "]");

            /* セッション属性設定 */
            l_securityService.setSessionProperty(
                WEB3SessionAttributeDef.ORDER_CHANNEL,
                l_setAccountReq.orderChannel);
            l_securityService.setSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV,
                (l_setAccountReq.orderRootDiv != null)
                    ? l_setAccountReq.orderRootDiv
                    : WEB3OrderRootDivDef.CALLCENTER);
            l_securityService.setSessionProperty(
                WEB3SessionAttributeDef.BRANCH_ID,
                Long.toString(l_branchRow.getBranchId()));

            //顧客（DB:顧客マスタ）のデータを取得する。
            // ※login_id をキーとしてログインテーブルを検索し、account_idを取得する。
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_accountManager.getMainAccount(l_loginRow.getAccountId()).getDataSourceObject();
                
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_mainAccountRow.getFamilyName());

            l_setAccountRes =
                (WEB3SetAccountResponse) l_request.createResponse();
            l_setAccountRes.acceptInfo = getAcceptInfo(
                l_setAccountReq.acceptCode,
                l_mainAccountRow,
                l_loginInfo,
                l_setAccountReq.orderRootDiv);
            l_setAccountRes.branchInfo = getBranchInfo(l_branchRow);
            l_setAccountRes.serviceImpState = getServiceImpState(
                l_institutionRow,
                l_branchRow,
                l_mainAccountRow);
            l_setAccountRes.topPageID = l_mainAccountRow.getTopPageId();
            l_setAccountRes.xTradeSessionID = l_sessionID;
        }
        catch (WEB3BaseException ex)
        {
            throw ex;
        }
        catch (NotFoundException ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                "部店、または顧客マスタに必要なデータが存在しない.",
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
        return l_setAccountRes;
    }
}
@
