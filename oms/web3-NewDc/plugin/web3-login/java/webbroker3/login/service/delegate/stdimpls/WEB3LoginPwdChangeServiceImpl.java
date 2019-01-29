head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LoginPwdChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ログイン時パスワード変更サービス(WEB3LoginPwdChangeServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/20 菊地(SRA) 新規作成
 */

package webbroker3.login.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;

import webbroker3.login.message.WEB3LoginPwdChangeRequest;
import webbroker3.login.message.WEB3LoginPwdChangeResponse;
import webbroker3.login.service.delegate.WEB3LoginPwdChangeService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LogoutFlagDef;
import webbroker3.common.define.WEB3ReloginFlagDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3PasswordUtility;

/**
 * （ログイン時パスワード変更サービス）
 * <BR>
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3LoginPwdChangeServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3LoginPwdChangeService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3LoginPwdChangeServiceImpl.class);

    /**
     * @@roseuid 40693597008C
     */
    public WEB3LoginPwdChangeServiceImpl()
    {
        super();
    }

    /**
     * 初期化をする。<BR>
     * <BR>
     * 現在パスワードがログイン中のユーザのものと一致するかチェックする。<BR>
     * 　@　@一致しない場合<BR>
     * 　@　@　@　@現在パスワード不一致（不正アクセス）をthrowする。<BR>
     * <BR>
     * 新パスワード１、２が一致しているかチェックする。<BR>
     * 　@　@一致しない場合<BR>
     * 　@　@　@　@パスワード入力ミスをthrowする。<BR>
     * <BR>
     * 共通．パスワード・ユーティリティを使ってセキュリティ・レベルに従ったパスワード値<BR>
     * チェックを実行する。<BR>
     * 　@　@返り値がチェック正常以外の場合、返り値に従った例外をthrowする。<BR>
     * 　@　@　@　@CHECK_ERROR_LENGTH：　@長さ不正<BR>
     * 　@　@　@　@CHECK_ERROR_CTYPE：　@文字種不正<BR>
     * 　@　@　@　@CHECK_ERROR_SAME_CURRENT：　@現在パスワードと一致<BR>
     * 　@　@　@　@CHECK_ERROR_SAME_NAME：　@ログイン名と一致<BR>
     * 　@　@　@　@CHECK_ERROR_SAME_BEFORE：　@旧パスワードと一致<BR>
     * 　@　@　@　@CHECK_ERROR_SIMILAR_BEFORE：　@旧パスワードと類似<BR>
     * 　@　@　@　@CHECK_ERROR_CONFIG：　@致命的なシステムエラー<BR>
     * <BR>
     * 共通．パスワード・ユーティリティを使用してパスワードを変更する。<BR>
     * 　@　@※新パスワードが現在パスワードと同じ場合でも実行する。<BR>
     * 　@　@　@（パスワード変更時刻を更新する為）<BR>
     * 　@　@パスワードを変更できなかった（返り値が失敗だった）場合<BR>
     * 　@　@　@　@致命的なシステムエラーをthrowする。<BR>
     * <BR>
     * ログインユーザの最終ログイン時間を更新する。<BR>
     * 　@　@※初回パスワード変更要求を迂回させない為、ここで最終ログイン時刻を更新。<BR>
     * <BR>
     * 共通．パスワード・ユーティリティを使って変更後再ログインを実施するか確認する。<BR>
     * 再ログインが必要な場合、<BR>
     * 　@またはログアウトフラグが「ログアウト実行」の場合<BR>
     * 　@　@ログアウト・ハンドラにディスパッチしてログアウトする。<BR>
     * <BR>
     * 結果でレスポンスを作成し、処理を終了する。<BR>
     * <BR>
     * 処理の過程で例外が発生した場合、次の様に振舞う。<BR>
     * WEB3BaseExceptionが発生した場合<BR>
     * 　@　@catchした例外をそのままthrowする。<BR>
     * NotFoundExceptionが発生した場合<BR>
     * 　@　@有り得ない異常として致命的なシステムエラーをthrowする。<BR>
     * それ以外の例外が発生した場合<BR>
     * 　@　@※changePassword実行時の例外も含む。ビジネス・エラーではない筈。<BR>
     * 　@　@有り得ない異常として致命的なシステムエラーをthrowする。<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse@@throws 
     * webbroker3.common.WEB3BaseException
     * @@roseuid 4063BC3F0365
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3LoginPwdChangeRequest l_pwdChangeReq =
            (WEB3LoginPwdChangeRequest) l_request;
        WEB3LoginPwdChangeResponse l_pwdChangeRes = null;
        try
        {
            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_securityService.getLoginId());

            String l_loginName = l_pwdChangeReq.loginName;
            String l_oldPwd = l_pwdChangeReq.oldPassword;
            String l_newPwd1 = l_pwdChangeReq.newPassword1;
            String l_newPwd2 = l_pwdChangeReq.newPassword2;

            if (!l_securityService.checkPassword(l_oldPwd))
            { /* 本人確認エラー.旧パスワード不一致 */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90210,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: "
                        + l_securityService.getLoginId()
                        + "　@本人確認エラー（旧パスワード不一致）.");
            }

            if (!l_newPwd1.equals(l_newPwd2))
            { /* 新パスワード入力ミス */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90211,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: "
                        + l_securityService.getLoginId()
                        + "　@新パスワード入力ミスチェックでエラー.");
            }

            WEB3PasswordUtility l_pwdUtil =
                new WEB3PasswordUtility(l_securityService.getLoginId());
            switch (l_pwdUtil.checkPassword(l_loginName, l_oldPwd, l_newPwd1))
            {
                case WEB3PasswordUtility.CHECK_ERROR_LENGTH :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90216,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "　@新パスワード長不正.");
                case WEB3PasswordUtility.CHECK_ERROR_CTYPE :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90214,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "　@新パスワード文字種不正.");
                case WEB3PasswordUtility.CHECK_ERROR_SAME_CURRENT :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90217,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "　@新パスワードが現在パスワードと同じ.");
                case WEB3PasswordUtility.CHECK_ERROR_SAME_NAME :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90218,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "　@新パスワードがログイン名と同じ.");
                case WEB3PasswordUtility.CHECK_ERROR_SAME_BEFORE :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90219,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "　@新パスワードが旧パスワード（３世代以前）と同じ.");
                case WEB3PasswordUtility.CHECK_ERROR_SIMILAR_BEFORE :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90220,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "　@新パスワードが旧パスワード（３世代以前）と類似.");
                case WEB3PasswordUtility.CHECK_ERROR_CONFIG :
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "パスワード変更時チェック関連のログインタイプ属性に不備がある.");
            }

            if (!l_pwdUtil.changePassword(l_oldPwd, l_newPwd1))
            { /* パスワード変更失敗 */
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: "
                        + l_securityService.getLoginId()
                        + "　@パスワード変更失敗.");
            }

            updateLastLoginTime(l_securityService.getLoginId());

            l_pwdChangeRes =
                (WEB3LoginPwdChangeResponse) l_request.createResponse();
            if (l_pwdUtil.isReloginNecessity())
            { /* パスワード変更時、再ログイン必要 */
                /* ※アクション側はこのフラグを見てWOLF2セッションをログアウトする。                 */
                /* 　@リクエストのログアウトフラグはSS遷移時にxTradeをログアウトする為のもの。         */
                /* 　@従って、リクエストでログアウト指示されたからといってこのフラグを立ててはいけない。*/
                /* 　@これらは似ているが意味が異なる。                                               */
                l_pwdChangeRes.reLoginFlag = WEB3ReloginFlagDef.RELOGIN;
            }
            else
            { /* パスワード変更時、再ログイン不要 */
                l_pwdChangeRes.reLoginFlag = WEB3ReloginFlagDef.CONTINUE;
            }

            if (WEB3ReloginFlagDef.RELOGIN.equals(l_pwdChangeRes.reLoginFlag)
                || WEB3LogoutFlagDef.LOGOUT.equals(l_pwdChangeReq.logoutFlag))
            { /* ログアウトする */
                dispatchLogout(null);
            }
        }
        catch (WEB3BaseException ex)
        {
            throw ex;
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
        return l_pwdChangeRes;
    }
}
@
