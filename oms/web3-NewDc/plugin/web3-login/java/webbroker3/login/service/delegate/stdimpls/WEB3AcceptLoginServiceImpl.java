head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptLoginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客ログインサービス(WEB3AcceptLoginServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/25 菊地(SRA) 新規作成
Revesion History    : 2007/02/12 栄イ (中訊) 仕様変更モデルNo.032
Revesion History    : 2007/07/25 孫 (FLJ) 復号化可能な方法@で暗号化されたパスワードで認証　@を追加
Revesion History    : 2007/06/12 謝旋 (中訊) 仕様変更モデルNo.033
Revesion History    : 2007/06/12 徐宏偉 (中訊) 仕様変更モデルNo.033
Revesion History    : 2007/06/18 徐宏偉 (中訊) 仕様変更モデルNo.035
Revesion History    : 2007/06/28 何文敏 (中訊) 仕様変更モデルNo.036
Revesion History    : 2008/09/10 柴双紅 (中訊) 仕様変更モデルNo.055
Revesion History    : 2008/10/24 許丹 (中訊) 仕様変更モデルNo.056
 */
package webbroker3.login.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginLogRecordDef;
import webbroker3.common.define.WEB3LoginRejectIpStatusDef;
import webbroker3.common.define.WEB3LoginRejectipCheckDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3NameSerialNoDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3PwdChangeDef;
import webbroker3.common.define.WEB3PwdCheckFlagDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesPK;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.LoginHistoryDao;
import webbroker3.gentrade.data.LoginHistoryParams;
import webbroker3.gentrade.data.LoginRejectIpRow;
import webbroker3.login.define.WEB3LoginPasswordDef;
import webbroker3.login.message.WEB3AcceptLoginRequest;
import webbroker3.login.message.WEB3AcceptLoginResponse;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.login.service.delegate.WEB3AcceptLoginService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (顧客ログインサービス)<BR>
 * <BR>
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3AcceptLoginServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3AcceptLoginService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AcceptLoginServiceImpl.class);

    /**
     * @@roseuid 403EE9270073
     */
    public WEB3AcceptLoginServiceImpl()
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
     * 注文チャネルをチェックする。<BR>
     * 　@　@不正な値の場合、パスワードエラーをthrowする。<BR>
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
     * リクエスト.注文経路区分がIVRのとき<BR> 
     * 　@　@ログイン属性.復号化可能パスワードを復号化して取得する。<BR> 
     * 　@　@戻り値を、ログイン()時の引数.パスワードに使用する<BR> 
     * <BR> 
     * ログイン（xTradeセッション生成）する。<BR>
     * 　@　@ログインエラーの場合、認証エラーとしてパスワードエラーをthrowする。<BR>
     * <BR>
     * セッション属性設定ハンドラにディスパッチしてセッション属性を設定する。<BR>
     * <BR>
     * 顧客（DB:顧客マスタ）のデータを取得する。<BR>
     * 　@　@※login_id をキーとしてログインテーブルを検索し、account_idを取得する。<BR>
     * <BR>
     * 顧客を顧客情報に変換する。<BR>
     * 会社を会社情報に変換する。<BR>
     * 部店を部店情報に変換する。<BR>
     * 顧客マスタの先頭画面IDを取得する。<BR>
     * サービス実施状態取得を取得する。<BR>
     * <BR>
     * リクエスト.注文経路区分がIVRのとき<BR> 
     * 　@共通．パスワード・ユーティリティを使ってパスワード変更の必要性を判定する。<BR> 
     * 　@パスワード変更が不要な場合<BR> 
     * 　@　@　@顧客の最終ログイン時刻を更新する。<BR> 
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
     * <BR>
     * ※注１）　@レスポンス編集について<BR>
     * （レスポンス.パスワード変更フラグ == "1"：変更必要）の場合、<BR>
     * ログインタイプ属性より、パスワード最小長，パスワード最大長，<BR>
     * パスワードチェック方式を編集する。<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4016583F039F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AcceptLoginRequest l_loginReq = (WEB3AcceptLoginRequest) l_request;
        WEB3AcceptLoginResponse l_loginRes = null;
        try
        {
            //1) 会社（DB:証券会社）のデータを取得する。部店（DB:部店）のデータを取得する。
            String l_uname = l_loginReq.xTradeUsername;
            String l_pwd = l_loginReq.acceptPassword;
            String l_icode = l_loginReq.institutionCode;
            String l_bcode = l_loginReq.branchCode;
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

            //マシン日付取得
            //new Date()でマシン日付取得する
            Date l_datMachineTime = new Date();

            //2) 部店がログイン停止状態かチェックする。
            //ログイン停止中の場合、ログイン停止中エラーをthrowする。
            if (isLoginStop(l_branchRow))
            {
                log.debug(STR_METHOD_NAME + ".... ログイン停止中.");

                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90201;

                //※例外時、ログイン履歴登録
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_errorInfo,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "部店(CODE: " + l_bcode + ") ログイン停止中.");
            }
            
            //3) 注文チャネルをチェックする。
            //不正な値の場合、その他認証エラーをthrowする。
            if (!checkOrderChannel(l_loginReq.orderChannel))
            {
                log.debug(STR_METHOD_NAME + ".... 注文チャネル値不正.");

                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90221;

                //※例外時、ログイン履歴登録
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_errorInfo,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_uname + ") 注文チャネル値不正.");
            }

            //is拒否IP(long, String, String, String)
            //部店ID　@：　@getBranch().部店ID
            //会社コード　@：　@リクエスト.会社コード
            //注文経路区分　@：　@リクエスト.注文経路区分
            //IPアドレス　@：　@リクエスト.IPアドレス
            boolean l_blnIsRejectIp = this.isRejectIp(
                l_branchRow.getBranchId(),
                l_loginReq.institutionCode,
                l_loginReq.orderRootDiv,
                l_loginReq.ipAddress);
            if (l_blnIsRejectIp)
            {
                log.debug(STR_METHOD_NAME + ".... 拒否IPエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02821,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "拒否IPエラー。");
            }

            //4) 顧客コードの妥当性をチェックする。
            //コード値エラーとしてその他認証エラーをthrowする。
            if (!checkAcceptCode(l_loginReq.acceptCode, l_branchRow))
            {
                log.debug(
                    STR_METHOD_NAME + " .... login failed(code value check).");

                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90221;

                //※例外時、ログイン履歴登録
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

                throw new WEB3BusinessLayerException(
                    l_errorInfo,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(CODE: " + l_loginReq.acceptCode + ") コード値不正.");
            }
            
            //5) LoginInfoを取得する。
            //取得できなかった場合、NotFoundExceptionをthrowする。
            OpLoginAdminService l_adminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            LoginInfo l_loginInfo = l_adminService.getLoginInfo(l_uname);
            if (l_loginInfo == null)
            {
                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90221;
                //※例外時、ログイン履歴登録
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

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

            //ログインタイプ属性を取得する
            HashMap loginTypeAttrs = new HashMap();

            Map l_mapLoginTypeAttributes =
                l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
            loginTypeAttrs.putAll(l_mapLoginTypeAttributes);

            //ログイン属性を取得する
            Map l_loginAttributes = l_loginInfo.getAttributes();

            //validate識別番号(String, String, map, map)
            //識別番号　@：　@リクエスト.お客様識別番号
            //注文経路区分　@：　@リクエスト.注文経路区分
            //ログインタイプ属性　@：　@getLoginTypeAttributes()の戻り値
            //ログイン属性　@：　@getLoginAttributes()の戻り値
            ProcessingResult l_processingResult =
                this.validateDiscriminationCode(
                    l_loginReq.discriminationCode,
                    l_loginReq.orderRootDiv,
                    l_mapLoginTypeAttributes,
                    l_loginAttributes);

            if (l_processingResult.isFailedResult())
            {
                log.debug(STR_METHOD_NAME +
                    ".... ログインできませんでした。入力内容をご確認の上、再度ログインして下さい。");

                //※例外時、ログイン履歴登録
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_processingResult.getErrorInfo());

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90221,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "ログインできませんでした。入力内容をご確認の上、再度ログインして下さい。");
            }

            //6) 顧客ログインエラー回数（有効性）をチェックする。
            //無効の場合、顧客ロック中エラーをthrowする。<
            if (!isEnabledUser(l_loginInfo))
            {
                log.debug(STR_METHOD_NAME + ".... ロック中.");

                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90202;

                //※例外時、ログイン履歴登録
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_errorInfo,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_uname + ") ロック中.");
            }

            //リクエスト.注文経路区分がIVRのとき
            //ログイン属性.復号化可能パスワードを復号化して取得する。
            //戻り値を、ログイン()時の引数.パスワードに使用する
            if (WEB3OrderRootDivDef.IVR.equals(l_loginReq.orderRootDiv))
            {
                l_pwd = getPasswordForIVR(l_pwd, l_loginAttributes);
            }

            //20070725 孫　@暗号化されたパスワードで認証を追加
            try
            {
                WEB3DesPasswordCheckUtil.getInstance().checkDesPassword(l_icode,l_pwd,l_loginInfo);
            }
            catch (WEB3BusinessLayerException l_ex)
            {
                //※例外時、ログイン履歴登録
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_ex.getErrorInfo());
                throw l_ex;
            }

            //7) ログイン（xTradeセッション生成）する。
            //ログインエラーの場合、認証エラーとしてパスワードエラーをthrowする。
            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_sessionID = l_securityService.logIn(l_uname, l_pwd);
            if (l_sessionID == null)
            {
                log.debug(
                    STR_METHOD_NAME + " .... login failed(authenticate).");

                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90203;

                //※例外時、ログイン履歴登録
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

                throw new WEB3BusinessLayerException(
                    l_errorInfo,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客(xNAME: " + l_uname + ") 認証エラー.");
            }
            log.debug(STR_METHOD_NAME + " .... sessionID = " + l_sessionID);
            
            //8) セッション属性設定ハンドラにディスパッチしてセッション属性を設定する。
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
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.BRANCH_ID,
                Long.toString(l_branchRow.getBranchId()));
            StringTokenizer l_tokenizer = new StringTokenizer(l_sessionID, ":");
            String l_strToken = l_tokenizer.nextToken();
            l_strToken = l_tokenizer.nextToken();
            //李志強 U00720の暫定対応 start
//            l_tokenizer = new StringTokenizer(l_strToken, "-");
			//李志強 U00720の暫定対応 end
            l_strToken = l_tokenizer.nextToken();
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.SESSION_ID,
                l_strToken);
            if (l_loginReq.ipAddress != null)
            {
                l_setSessionReq.sessionAttributes.put(
                    WEB3SessionAttributeDef.IP_ADDRESS,
                    l_loginReq.ipAddress);
            }

            setSessionAttribute(l_sessionID, l_setSessionReq);

            //9) 顧客（DB:顧客マスタ）のデータを取得する。
            // ※login_id をキーとしてログインテーブルを検索し、account_idを取得する。
            LoginRow l_loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_accountManager.getMainAccount(l_loginRow.getAccountId()).getDataSourceObject();
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_mainAccountRow.getFamilyName());

            l_loginRes = (WEB3AcceptLoginResponse) l_request.createResponse();
            l_loginRes.xTradeSessionID = l_sessionID;
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
            l_loginRes.topPageID = l_mainAccountRow.getTopPageId();
            
            //リクエスト.注文経路区分がIVRのとき
            //共通．パスワード・ユーティリティを使ってパスワード変更の必要性を判定する
            //パスワード変更が不要な場合 顧客の最終ログイン時刻を更新する
            if (!WEB3OrderRootDivDef.IVR.equals(l_loginReq.orderRootDiv))
            {
                if (!l_pwdUtil.isChangeNecessity())
                {
                    updateLastLoginTime(l_loginInfo.getLoginId());
                    l_loginRes.passwordChangeFlag = WEB3PwdChangeDef.UNNECESSARY;
                }
                else
                {
                    l_loginRes.passwordChangeFlag = WEB3PwdChangeDef.REQUIRED;
                }
            }
            else
            {
                l_loginRes.passwordChangeFlag = WEB3PwdChangeDef.UNNECESSARY;
            }

            //insertログイン履歴
            //部店ID　@：　@getBranch().部店ID
            //会社コード　@：　@リクエスト.会社コード
            //部店コード　@：　@リクエスト.部店コード
            //顧客コード　@：　@リクエスト.顧客コード
            //識別番号　@：　@リクエスト.お客様識別番号
            //口座ＩＤ　@：　@リクエスト.顧客ID
            //IPアドレス　@：　@リクエスト.IPアドレス
            //注文経路区分　@：　@リクエスト.注文経路区分
            //注文チャネル　@：　@リクエスト.注文チャネル
            //マシン日付　@：　@取得したマシン日付
            //エラー情報　@：　@null
            this.insertLoginHistory(
                l_branchRow.getBranchId(),
                l_loginReq.institutionCode,
                l_loginReq.branchCode,
                l_loginReq.acceptCode,
                l_loginReq.discriminationCode,
                l_loginReq.account_id,
                l_loginReq.ipAddress,
                l_loginReq.orderRootDiv,
                l_loginReq.orderChannel,
                l_datMachineTime,
                null);

            //11) （レスポンス.パスワード変更フラグ == "1"：変更必要）の場合
            if(WEB3PwdChangeDef.REQUIRED.equals(l_loginRes.passwordChangeFlag))
            {
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
                    + ", 部店(CODE: "
                    + l_loginReq.branchCode
                    + ")"
                    + ", 顧客(xNAME: "
                    + l_loginReq.xTradeUsername
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

    /**
     * (getパスワードForIVR)<BR>
     * <BR>
     * ログイン属性.復号化可能パスワードを復号化して取得する。 <BR>
     * （注文経路がIVRのときのみ使用） <BR>
     * <BR>
     * １）ログイン属性.復号化可能初期パスワードを復号化 <BR>
     * <BR>
     * 　@　@※復号化可能初期パスワードを取得できないときはNotFoundExceptionをthrowする <BR>
     * <BR>
     * ２）引数.パスワードと復号化したパスワードが同じとき <BR>
     * <BR>
     * 　@　@ログイン属性.復号化可能パスワードを復号化してリターンする <BR>
     * <BR>
     * 　@　@※復号化可能パスワードを取得できないときはNotFoundExceptionをthrowする <BR>
     * <BR>
     * ３）上記以外のとき <BR>
     * <BR>
     * 　@　@space4桁をリターンする<BR>
     * <BR>
     * @@param l_strPassword - (パスワード)<BR>
     * @@param l_loginAttributes - (ログイン属性)<BR>
     * @@return String
     * @@throws NotFoundException
     */
    public String getPasswordForIVR(String l_strPassword, Map l_loginAttributes) 
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getPasswordForIVR(String, Map)";
        log.entering(STR_METHOD_NAME);

        //１）ログイン属性.復号化可能初期パスワードを復号化
        //※復号化可能初期パスワードを取得できないときはNotFoundExceptionをthrowする
        Object l_objInitialPassword
            = l_loginAttributes.get(WEB3LoginAttributeKeyDef.INITIAL_PASSWORD);
        String l_strDecryptInitialPassword = null;
        if (l_objInitialPassword == null)
        {
            log.debug("復号化可能初期パスワードを取得できない。");
            log.exiting(STR_METHOD_NAME);
            throw new NotFoundException("復号化可能初期パスワードを取得できない。");
        }
        else
        {
            WEB3Crypt l_crypt = new WEB3Crypt();
            l_strDecryptInitialPassword = l_crypt.decrypt(l_objInitialPassword.toString());
        }

        //２）引数.パスワードと復号化したパスワードが同じとき
        //ログイン属性.復号化可能パスワードを復号化してリターンする
        //※復号化可能パスワードを取得できないときはNotFoundExceptionをthrowする
        if (l_strPassword.equals(l_strDecryptInitialPassword))
        {
            Object l_objEncryptedPassword
                = l_loginAttributes.get(WEB3LoginAttributeKeyDef.PASSWORD);
            if (l_objEncryptedPassword == null)
            {
                log.debug("復号化可能パスワードを取得できない。");
                log.exiting(STR_METHOD_NAME);
                throw new NotFoundException("復号化可能パスワードを取得できない。");
            }
            else
            {
                WEB3Crypt l_crypt = new WEB3Crypt();
                log.exiting(STR_METHOD_NAME);
                return l_crypt.decrypt(l_objEncryptedPassword.toString());
            }
        }

        //３）上記以外のとき 
        //space4桁をリターンする
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3LoginPasswordDef.SPACE;
        }
    }

    /**
     * (insertログイン履歴)<BR>
     * ログイン履歴にデータ登録する<BR>
     * <BR>
     * １）部店プリファ@レンステーブルより、以下の属性のレコードを取得する<BR>
     * <BR>
     * 　@　@[取得条件]<BR>
     * 　@　@部店ID：引数.部店ID<BR>
     * 　@　@属性名："login.log.record."＋引数.注文経路区分<BR>
     * 　@　@連番：1<BR>
     * <BR>
     * 　@　@※レコードないか、レコードの値が「実施しない」の場合は当メソッド<BR>
     * 　@　@　@　@終了<BR>
     * <BR>
     * ２）取得したレコードの値が「実施する」のとき、ログイン履歴<BR>
     * 　@　@テーブルにinsertする<BR>
     * <BR>
     * 　@　@※編集内容は顧客ログイン－ログイン履歴.xlsのログイン履歴－<BR>
     * 　@　@　@　@DB更新（登録）を参照<BR>
     * <BR>
     * @@param l_lngBranchID - 部店ID
     * @@param l_strInstitutionCode - 会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strDiscriminationCode - 識別番号
     * @@param l_strAccountID - 口座ID
     * @@param l_strIpAddress - IPアドレス
     * @@param l_strOrderRootDiv - 注文経路区分
     * @@param l_strOrderChannel - 注文チャネル
     * @@param l_datMachineTime - マシン日付
     * @@param l_errorInfo - エラー情報
     * @@throws WEB3BaseException
     */
    protected void insertLoginHistory(
        long l_lngBranchID,
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strDiscriminationCode,
        String l_strAccountID,
        String l_strIpAddress,
        String l_strOrderRootDiv,
        String l_strOrderChannel,
        Date l_datMachineTime,
        ErrorInfo l_errorInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertLoginHistory(long, String, String, String, String,"
            + "long, String, String, String, Date, ErrorInfo)";
        log.entering(STR_METHOD_NAME);

        BranchPreferencesPK l_branchPreferencesPK = new BranchPreferencesPK();

        //部店ID：引数.部店ID
        l_branchPreferencesPK.branch_id = l_lngBranchID;
        //属性名："login.log.record."＋引数.注文経路区分
        l_branchPreferencesPK.name =
            WEB3BranchPreferencesNameDef.LOGIN_LOG_RECORD + l_strOrderRootDiv;
        //連番：1
        l_branchPreferencesPK.name_serial_no = Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO);

        try
        {
            BranchPreferencesRow l_branchPreferencesRow = BranchPreferencesDao.findRowByPk(l_branchPreferencesPK);

            if (l_branchPreferencesRow != null)
            {
                if (WEB3EnforcementDef.NOT_ENFORCEMENT.equals(l_branchPreferencesRow.getValue()))
                {
                    //レコードの値が「実施しない」の場合は当メソッド終了
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
                else if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchPreferencesRow.getValue()))
                {
                    //取得したレコードの値が「実施する」のとき、ログイン履歴テーブルにinsertする
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    LoginHistoryParams l_loginHistoryParams = new LoginHistoryParams();

                    //ログイン履歴ID
                    l_loginHistoryParams.setLoginHistoryId(LoginHistoryDao.newPkValue());
                    //証券会社コード
                    l_loginHistoryParams.setInstitutionCode(l_strInstitutionCode);
                    //部店コード
                    l_loginHistoryParams.setBranchCode(l_strBranchCode);
                    //顧客コード
                    l_loginHistoryParams.setAccountCode(l_strAccountCode);
                    //識別番号
                    //リクエスト.お客様識別番号 != nullは同項目、以外null
                    if (l_strDiscriminationCode != null)
                    {
                        l_loginHistoryParams.setDiscriminationCode(l_strDiscriminationCode);
                    }
                    //口座ＩＤ
                    l_loginHistoryParams.setAccountId(
                        l_strAccountID != null ? Long.parseLong(l_strAccountID) : 0
                    );
                    //IPアドレス
                    //リクエスト.IPアドレス != nullは同項目、以外null
                    if (l_strIpAddress != null)
                    {
                        l_loginHistoryParams.setIpAddress(l_strIpAddress);
                    }
                    //注文経路区分
                    l_loginHistoryParams.setOrderRootDiv(l_strOrderRootDiv);
                    //注文チャネル
                    l_loginHistoryParams.setOrderChannel(l_strOrderChannel);
                    //ログイン日時
                    l_loginHistoryParams.setLoginTimestamp(l_datMachineTime);

                    if (l_errorInfo == null)
                    {
                        //ログイン成否
                        //正常終了時（ErrorInfo == null）　@：　@0
                        l_loginHistoryParams.setLoginFailure(WEB3LoginLogRecordDef.DEFAULT);

                        //ログインエラー情報
                        //正常終了時（ErrorInfo == null）　@：　@null
                        l_loginHistoryParams.setLoginErrorInfo(null);
                    }
                    else
                    {
                        //ログイン成否
                        //エラー終了時（ErrorInfo != null）：　@1
                        l_loginHistoryParams.setLoginFailure(WEB3LoginLogRecordDef.EXECUTE);

                        //ログインエラー情報
                        //エラー終了時（ErrorInfo != null）：　@ErrorInfo.toString()
                        l_loginHistoryParams.setLoginErrorInfo(l_errorInfo.toString());
                    }

                    Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
                    //作成日付
                    //現在時刻（＝GtlUtils.getSystemTimestamp()）
                    l_loginHistoryParams.setCreatedTimestamp(l_tsSystemTimestamp);
                    //更新日付
                    //現在時刻（＝GtlUtils.getSystemTimestamp()）
                    l_loginHistoryParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                    l_queryProcessor.doInsertQuery(l_loginHistoryParams);
                }
            }
        }
        catch (DataFindException l_ex)
        {
            //レコードないの場合は当メソッド終了
            log.debug(l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);

            return;
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is拒否IP)<BR>
     * 特定IPからのアクセスを拒否する<BR>
     * <BR>
     * １）部店プリファ@レンステーブルより、以下の属性のレコードを取得する<BR>
     * <BR>
     * 　@　@[取得条件]<BR>
     * 　@　@部店ID：引数.部店ID<BR>
     * 　@　@属性名："login.rejectip.check."＋引数.注文経路区分<BR>
     * 　@　@連番：1<BR>
     * <BR>
     * 　@　@※レコードないか、レコードの値が「チェックなし」の場合はfalseを返却する<BR>
     * <BR>
     * ２）取得したレコードの値が「チェックあり」のとき以下の処理を行う<BR>
     * <BR>
     * 　@　@ログイン拒否IP tblを検索し、該当recある場合はtrue、以外はfalseを返却する<BR>
     * <BR>
     * 　@　@[取得条件]<BR>
     * 　@　@証券会社コード：引数.会社コード<BR>
     * 　@　@IPアドレス：引数.IPアドレス<BR>
     * 　@　@ステータス：0（対象）<BR>
     * 　@　@適用開始日時 <= 現在時刻（※1） < 適用終了日時<BR>
     * <BR>
     * 　@　@※1：GtlUtils.getSystemTimestamp()<BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID
     * @@param l_strInstitutionCode - (会社コード)<BR>
     * 会社コード
     * @@param l_strOrderRootDiv - (注文経路区分)<BR>
     * 注文経路区分
     * @@param l_strIpAddress - (IPアドレス)<BR>
     * IPアドレス
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isRejectIp(
        long l_lngBranchId,
        String l_strInstitutionCode,
        String l_strOrderRootDiv,
        String l_strIpAddress) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isRejectIp(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）部店プリファ@レンステーブルより、以下の属性のレコードを取得する
        //　@　@[取得条件]
        //　@　@部店ID：引数.部店ID
        //　@　@属性名："login.rejectip.check."＋引数.注文経路区分
        //　@　@連番：1
        //　@　@※レコードないか、レコードの値が「チェックなし」の場合はfalseを返却する
        BranchPreferencesRow l_branchPreferencesRow = null;

        try
        {
            String l_strBranchPreferencesName =
                WEB3BranchPreferencesNameDef.LOGIN_REJECTIP_CHECK + l_strOrderRootDiv;
            l_branchPreferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    l_strBranchPreferencesName,
                    Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO));

            //レコードないか、レコードの値が「チェックなし」の場合はfalseを返却する
            if (l_branchPreferencesRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                String l_strValue = l_branchPreferencesRow.getValue();
                if (WEB3LoginRejectipCheckDef.DEFAULT.equals(l_strValue))
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }

                //* ２）取得したレコードの値が「チェックあり」のとき以下の処理を行う
                //* 　@　@ログイン拒否IP tblを検索し、該当recある場合はtrue、以外はfalseを返却する
                //* 　@　@[取得条件]
                //* 　@　@証券会社コード：引数.会社コード
                //* 　@　@IPアドレス：引数.IPアドレス
                //* 　@　@ステータス：0（対象）
                //* 　@　@適用開始日時 <= 現在時刻（※1） < 適用終了日時
                //* 　@　@※1：GtlUtils.getSystemTimestamp()
                else if (WEB3LoginRejectipCheckDef.EXECUTE.equals(l_strValue))
                {
                    StringBuffer l_sbQueryString = new StringBuffer();
                    l_sbQueryString.append(" institution_code = ? ");
                    l_sbQueryString.append(" and ip_address = ? ");
                    l_sbQueryString.append(" and status = ? ");
                    l_sbQueryString.append(" and appli_start_timestamp <= ? ");
                    l_sbQueryString.append(" and appli_end_timestamp > ? ");

                    List l_lisValue = new ArrayList();
                    l_lisValue.add(l_strInstitutionCode);
                    l_lisValue.add(l_strIpAddress);
                    l_lisValue.add(WEB3LoginRejectIpStatusDef.OBJECT);

                    Timestamp l_tsNowTime = GtlUtils.getSystemTimestamp();
                    String l_strNowTime =
                        WEB3DateUtility.formatDate(l_tsNowTime, "yyyyMMddHHmmss");
                    Date l_datNowTime = WEB3DateUtility.getDate(l_strNowTime, "yyyyMMddHHmmss");
                    l_lisValue.add(l_datNowTime);
                    l_lisValue.add(l_datNowTime);

                    Object[] l_queryDataContainers = new Object[l_lisValue.size()];

                    l_lisValue.toArray(l_queryDataContainers);

                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    List l_lisLoginRejectIpRecord =
                        l_queryProcessor.doFindAllQuery(
                            LoginRejectIpRow.TYPE,
                            l_sbQueryString.toString(),
                            l_queryDataContainers);

                    if (l_lisLoginRejectIpRecord.isEmpty())
                    {
                        log.exiting(STR_METHOD_NAME);
                        return false;
                    }
                    else
                    {
                        log.exiting(STR_METHOD_NAME);
                        return true;
                    }
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate識別番号)<BR>
     * 入力されたお客様識別番号が、登録済み内容と同じかチェックする<BR>
     * <BR>
     * １）ログインタイプ属性.お客様識別番号チェック要否に、<BR>
     * 　@引数.注文経路区分のレコードがないか、「チェックなし」の場合<BR>
     * <BR>
     * 　@ProcessingResult.newSuccessResultInstance()の戻り値を返却する<BR>
     * <BR>
     * ２）取得したレコードの値が「チェックあり」のとき以下の処理を行う<BR>
     * <BR>
     * 　@（１）ログイン属性.復号化可能お客様識別番号がない<BR>
     * <BR>
     * 　@　@　@ａ．引数.識別番号 != nullの場合<BR>
     * <BR>
     * 　@　@　@　@　@ProcessingResult.newFailedResultInstance()の戻り値を返却する<BR>
     * 　@　@　@　@　@　@[返却する内容]<BR>
     * 　@　@　@　@　@　@お客様識別番号不正エラー<BR>
     * <BR>
     * 　@（２）ログイン属性.復号化可能お客様識別番号がある<BR>
     * <BR>
     * 　@　@　@ａ．WEB3暗号化機@能にて、引数.識別番号を暗号化<BR>
     * <BR>
     * 　@　@　@ｂ．暗号化した識別番号 != ログイン属性.復号化可能お客様識別番号の場合<BR>
     * <BR>
     * 　@　@　@　@　@ProcessingResult.newFailedResultInstance()の戻り値を返却する<BR>
     * 　@　@　@　@　@　@[返却する内容]<BR>
     * 　@　@　@　@　@　@お客様識別番号相違エラー<BR>
     * <BR>
     * ３）ProcessingResult.newSuccessResultInstance()の戻り値を返却する<BR>
     * @@param l_strDiscriminationCode - (識別番号)<BR>
     * 識別番号
     * @@param l_strOrderRootDiv - (注文経路区分)<BR>
     * 注文経路区分
     * @@param l_mapLoginTypeAttributes - (ログインタイプ属性)<BR>
     * ログインタイプ属性
     * @@param l_mapLoginAttributes - (ログイン属性)<BR>
     * ログイン属性
     * @@return ProcessingResult
     * @@throws WEB3BaseException
     */
    protected ProcessingResult validateDiscriminationCode(
        String l_strDiscriminationCode,
        String l_strOrderRootDiv,
        Map l_mapLoginTypeAttributes,
        Map l_mapLoginAttributes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDiscriminationCode(String, String, Map, Map)";
        log.entering(STR_METHOD_NAME);

        // 入力されたお客様識別番号が、登録済み内容と同じかチェックする
        // １）ログインタイプ属性.お客様識別番号チェック要否に、
        // 引数.注文経路区分のレコードがないか、「チェックなし」の場合
        //  　@ProcessingResult.newSuccessResultInstance()の戻り値を返却する
        String l_strDiscriminationCodeCheck = (String)l_mapLoginTypeAttributes.get(
            WEB3LoginTypeAttributeKeyDef.DISCRIMINATION_CD_CHECK + l_strOrderRootDiv);
        if (l_strDiscriminationCodeCheck == null
            || WEB3PwdCheckFlagDef.NO_CHECK.equals(l_strDiscriminationCodeCheck))
        {
            log.exiting(STR_METHOD_NAME);
            return ProcessingResult.newSuccessResultInstance();
        }
        else if (WEB3PwdCheckFlagDef.CHECK.equals(l_strDiscriminationCodeCheck))
        {
            //２）取得したレコードの値が「チェックあり」のとき以下の処理を行う
            // （１）ログイン属性.復号化可能お客様識別番号がない
            String l_strEncryptedDiscriminationCode =
                (String)l_mapLoginAttributes.get(WEB3LoginAttributeKeyDef.WEB3_ENCRYPTED_DISCRIMINATION_CD);
            if (l_strEncryptedDiscriminationCode == null)
            {
                //ａ．引数.識別番号 != nullの場合
                //ProcessingResult.newFailedResultInstance()の戻り値を返却する
                //[返却する内容]
                //お客様識別番号不正エラー
                if (l_strDiscriminationCode != null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_02819);
                }
            }
            // （２）ログイン属性.復号化可能お客様識別番号がある
            else
            {
                //  ａ．WEB3暗号化機@能にて、引数.識別番号を暗号化
                WEB3Crypt l_crypt = new WEB3Crypt();
                String l_strEncryptPwd = l_crypt.encrypt(l_strDiscriminationCode);
                //  ｂ．暗号化した識別番号 != ログイン属性.復号化可能お客様識別番号の場合
                //ProcessingResult.newFailedResultInstance()の戻り値を返却する
                //　@[返却する内容]
                // お客様識別番号相違エラー
                if (!l_strEncryptedDiscriminationCode.equals(l_strEncryptPwd))
                {
                    log.exiting(STR_METHOD_NAME);
                    return ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_02820);
                }
            }
        }

        //３）ProcessingResult.newSuccessResultInstance()の戻り値を返却する
        log.exiting(STR_METHOD_NAME);
        return ProcessingResult.newSuccessResultInstance();
    }
}
@
