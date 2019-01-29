head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptAutoLoginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客成りすまし自動ログインサービス(WEB3OpeAcceptAutoLoginServiceImpl.java)
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import webbroker3.login.service.delegate.WEB3OpeAcceptAutoLoginService;
import webbroker3.login.message.WEB3OpeAcceptAutoLoginRequest;
import webbroker3.login.message.WEB3OpeAcceptAutoLoginResponse;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (顧客成りすまし自動ログインサービス)<BR>
 * <BR>
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3OpeAcceptAutoLoginServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3OpeAcceptAutoLoginService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OpeAcceptAutoLoginServiceImpl.class);

    /**
     * @@roseuid 406D318D02CE
     */
    public WEB3OpeAcceptAutoLoginServiceImpl()
    {

    }

    /**
     * 初期化をする。<BR>
     * 会社（DB:証券会社）のデータを取得する。<BR>
     * CCオペレータの部店（DB:部店）のデータを取得する。<BR>
     * <BR>
     * 注文チャネルをチェックする。<BR>
     * 　@　@不正な値の場合、取引不能エラーをthrowする。<BR>
     * <BR>
     * CCオペレータの部店がログイン停止状態かチェックする。<BR>
     * 　@　@ログイン停止中の場合、ログイン停止中エラーをthrowする。<BR>
     * <BR>
     * CCオペレータコードの妥当性をチェックする。<BR>
     * 　@　@コード値エラーとして取引不能エラーをthrowする。<BR>
     * <BR>
     * 顧客の部店（DB:部店）のデータを取得する。<BR>
     * ※これ以降、部店の情報は顧客のものを使用する。<BR>
     * <BR>
     * 顧客の部店がログイン停止状態かチェックする。<BR>
     * 　@　@ログイン停止中の場合、ログイン停止中エラーをthrowする。<BR>
     * <BR>
     * 顧客コードの妥当性をチェックする。<BR>
     * 　@　@コード値エラーとして取引不能エラーをthrowする。<BR>
     * <BR>
     * CCオペレータのLoginInfoを取得する。<BR>
     * 　@　@不正アクセスとして取引不能エラーをthrowする。<BR>
     * <BR>
     * 顧客のLoginInfoを取得する。<BR>
     * 　@　@不正アクセスとして取引不能エラーをthrowする。<BR>
     * <BR>
     * 顧客（DB:顧客マスタ）のデータを取得する。<BR>
     * 　@　@※login_id をキーとしてログインテーブルを検索し、account_idを取得する。<BR>
     * <BR>
     * 「下り」としてハッシュ値を計算する。<BR>
     * 受信したハッシュ値と計算したハッシュ値を比較する。<BR>
     * 　@　@一致していない場合、取引不能エラーをthrowする。<BR>
     * <BR>
     * ログイン（xTradeセッション生成）する。<BR>
     * 　@　@ログインに失敗した場合<BR>
     * 　@　@　@　@認証エラーとして取引不能エラーをthrowする。<BR>
     * <BR>
     * 顧客に成りすまし、セッションIDを取得する。<BR>
     * ※login_id をキーとしてログインテーブルを検索し、account_idを取得する。<BR>
     * ※xTradeセッションIDを渡す必要がある。<BR>
     * <BR>
     * FinObjectManagerを取得する。<BR>
     * CCオペレータ（DB:扱者マスタ）のデータを取得する。<BR>
     * <BR>
     * セッション属性設定ハンドラにディスパッチしてセッション属性を設定する。<BR>
     * ※部店IDには顧客の部店IDを設定する。<BR>
     * <BR>
     * CCオペレータのLoginInfoをセットする。<BR>
     * CCオペレータをCCオペレータ情報に変換する。<BR>
     * 顧客のLoginInfoをセットする。<BR>
     * 顧客を顧客情報に変換する。<BR>
     * 会社を会社情報に変換する。<BR>
     * 部店を部店情報に変換する。<BR>
     * サービス実施状態取得を取得する。<BR>
     * <BR>
     * 結果でレスポンスを作成し、処理を終了する。<BR>
     * <BR>
     * 処理の過程で例外が発生した場合、次の様に振舞う。<BR>
     * WEB3BaseExceptionが発生した場合<BR>
     * 　@　@catchした例外をそのままthrowする。<BR>
     * NotFoundExceptionが発生した場合<BR>
     * 　@　@不正アクセスとして取引不能エラーをthrowする。<BR>
     * それ以外の例外が発生した場合<BR>
     * 　@　@有り得ない異常として致命的なシステムエラーをthrowする。<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 406BE37B0109
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3OpeAcceptAutoLoginRequest l_loginReq =
            (WEB3OpeAcceptAutoLoginRequest) l_request;
        WEB3OpeAcceptAutoLoginResponse l_loginRes = null;
        try
        {
            String l_icode = l_loginReq.institutionCode;
            String l_opeBcode = l_loginReq.ccOpeBranchCode;
            String l_opeUname = l_loginReq.xTradeCCOpeUsername;
            String l_pwd = l_loginReq.ccOperatorPassword;
            String l_opeUcode = l_loginReq.ccOperatorCode;
            String l_userBcode = l_loginReq.acceptBranchCode;
            String l_userUname = l_loginReq.xTradeAcceptUsername;
            log.debug(
                STR_METHOD_NAME
                    + " .... inst = "
                    + l_icode
                    + ", opebrch = "
                    + l_opeBcode
                    + ", ope = "
                    + l_opeUname
                    + ", userbrch = "
                    + l_userBcode
                    + ", user = "
                    + l_userUname);

            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_inst = l_accountManager.getInstitution(l_icode);
            InstitutionRow l_institutionRow = (InstitutionRow) l_inst.getDataSourceObject();

            if (!checkOrderChannel(l_loginReq.orderChannel))
            { /* 注文チャネル値不正 */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_opeUname + ") 注文チャネル値不正.");
            }

            /*-------- CCオペレータ関係データ取得＆チェック --------*/
            BranchRow l_branchRow =
                (BranchRow) l_accountManager
                    .getBranch(l_inst, l_opeBcode)
                    .getDataSourceObject();

            if (isLoginStop(l_branchRow))
            { /* ログイン停止中 */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90201,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "CCオペレータの部店(CODE: " + l_opeBcode + ") ログイン停止中.");
            }

            if (!checkTraderCode(l_loginReq.ccOperatorCode, l_institutionRow))
            { /* CCオペレータコード値不正 */
                log.debug(STR_METHOD_NAME + " .... operator code check error.");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "CCオペレータ(CODE: " + l_loginReq.ccOperatorCode + ") コード値不正.");
            }

            /*-------- 顧客関係データ取得＆チェック --------*/
            l_branchRow =
                (BranchRow) l_accountManager
                    .getBranch(l_inst, l_userBcode)
                    .getDataSourceObject();
            /* ※これ以降、部店は顧客のものを使用する。 */

            if (isLoginStop(l_branchRow))
            { /* ログイン停止中 */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90201,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客の部店(CODE: " + l_userBcode + ") ログイン停止中.");
            }

            if (!checkAcceptCode(l_loginReq.acceptCode, l_branchRow))
            { /* 顧客コード値不正 */
                log.debug(STR_METHOD_NAME + " .... user code check error.");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(CODE: " + l_loginReq.acceptCode + ") コード値不正.");
            }

            /*-------- CCオペレータ・ログイン情報存在チェック --------*/
            OpLoginAdminService l_adminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            LoginInfo l_loginInfo = l_adminService.getLoginInfo(l_opeUname);
            if (l_loginInfo == null)
            {
                log.debug(STR_METHOD_NAME + " .... LoginInfo not found.");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "CCオペレータ(xNAME: " + l_opeUname + ") 存在しない.");
            }
            LoginInfo l_opeLoginInfo = l_loginInfo;
            log.debug(
                STR_METHOD_NAME
                    + " .... operator login_id = "
                    + l_loginInfo.getLoginId());

            /*-------- 顧客・ログイン情報存在チェック --------*/
            l_loginInfo = l_adminService.getLoginInfo(l_userUname);
            if (l_loginInfo == null)
            {
                log.debug(STR_METHOD_NAME + " .... LoginInfo not found.");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_userUname + ") 存在しない.");
            }
            LoginInfo l_userLoginInfo = l_loginInfo;
            log.debug(
                STR_METHOD_NAME
                    + " .... user login_id = "
                    + l_loginInfo.getLoginId());

            //顧客（DB:顧客マスタ）のデータを取得する。
            // ※login_id をキーとしてログインテーブルを検索し、account_idを取得する。
            LoginRow l_loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_accountManager.getMainAccount(l_loginRow.getAccountId()).getDataSourceObject();
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_mainAccountRow.getFamilyName());

            String l_web3HashValue = calcHashValue(
                DOWN_HASH_VALUE,
                l_institutionRow,
                l_branchRow,
                l_mainAccountRow);
            if (!l_web3HashValue.equals(l_loginReq.hashValue))
            { /* ハッシュ値不正 */
                log.debug(STR_METHOD_NAME + " .... invalid hash value..");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_userUname + ") ハッシュ値不正.");
            }

            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_sessionID = l_securityService.logIn(l_opeUname, l_pwd);
            if (l_sessionID == null)
            { /* CCオペレータ・ログイン失敗 */
                log.debug(
                    STR_METHOD_NAME
                        + " .... operator login failed(authenticate).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "CCオペレータ(xNAME: " + l_opeUname + ") 認証エラー.");
            }
            log.debug(STR_METHOD_NAME + " .... sessionID(成りすまし前) = " + l_sessionID);

            /* 顧客に成りすます. */
            l_sessionID = changeAccount(l_loginRow.getAccountId(), l_sessionID);
            log.debug(STR_METHOD_NAME + " .... sessionID(成りすまし後) = " + l_sessionID);
            
            FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
            TraderRow l_traderRow =
                (TraderRow) l_finMng
                    .getTrader(l_inst, l_opeUcode, l_opeBcode)
                    .getDataSourceObject();
            log.debug(
                STR_METHOD_NAME
                    + " .... operator = "
                    + l_traderRow.getFamilyName());

            WEB3SetSessionRequest l_setSessionReq = new WEB3SetSessionRequest();

            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.ORDER_CHANNEL,
                l_loginReq.orderChannel);
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV,
                (l_loginReq.orderRootDiv != null)
                    ? l_loginReq.orderRootDiv
                    : WEB3OrderRootDivDef.PC);
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.INSTITUTION_ID,
                Long.toString(l_institutionRow.getInstitutionId()));
            l_setSessionReq
                .sessionAttributes
                .put(
                    WEB3SessionAttributeDef.BRANCH_ID,
                    Long.toString(l_branchRow.getBranchId()) /* 顧客の部店ID */
            );
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.CCOPERATOR_ID,
                Long.toString(l_traderRow.getTraderId()));

            setSessionAttribute(l_sessionID, l_setSessionReq);

            l_loginRes =
                (WEB3OpeAcceptAutoLoginResponse) l_request.createResponse();
            l_loginRes.xTradeSessionID = l_sessionID;
            l_loginInfo = l_opeLoginInfo;
            l_loginRes.ccOperatorInfo = getTraderInfo(l_traderRow, l_loginInfo);
            l_loginInfo = l_userLoginInfo;
            l_loginRes.acceptInfo = getAcceptInfo(
                l_loginReq.acceptCode,
                l_mainAccountRow,
                l_loginInfo,
                l_loginReq.orderRootDiv);
            l_loginRes.institutionInfo = getInstitutionInfo(l_institutionRow);
            l_loginRes.branchInfo = getBranchInfo(l_branchRow);
            l_loginRes.serviceImpState = getServiceImpState(
                l_institutionRow,
                l_branchRow,
                l_mainAccountRow);
        }
        catch (WEB3BaseException ex)
        {
            throw ex;
        }
        catch (NotFoundException ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                getClass().getName() + "." + STR_METHOD_NAME,
                "会社(CODE: "
                    + l_loginReq.institutionCode
                    + ")"
                    + ", オペ部店(CODE: "
                    + l_loginReq.ccOpeBranchCode
                    + ")"
                    + ", オペ(xNAME: "
                    + l_loginReq.xTradeCCOpeUsername
                    + ")"
                    + ", 顧部店(CODE: "
                    + l_loginReq.acceptBranchCode
                    + ")"
                    + ", 顧客(xNAME: "
                    + l_loginReq.xTradeAcceptUsername
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
        return l_loginRes;
    }
}
@
