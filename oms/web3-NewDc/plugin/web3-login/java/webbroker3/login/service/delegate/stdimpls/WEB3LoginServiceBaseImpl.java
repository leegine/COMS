head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.05.27.33;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3c84d8ad6447b3c;
filename	WEB3LoginServiceBaseImpl.java;

1.1
date	2011.03.15.05.24.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LoginServiceBaseImpl.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研ビジネス・イノベーション
File Name           : ログインサービスのベースクラス(WEB3LoginServiceBaseImpl.java)
Author Name         : Daiwa Institute of Research Business Innovation
Revesion History    : 2004/05/25 菊地(SRA) 新規作成
Revesion History    : 2004/07/28 中尾(SRA) 管理者テーブル関連のDAOがgtl-baseから
　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@webbroker3.gentrade.dataへ移動した事に伴う対応
Revesion History    : 2005/04/05 劉(FLJ) LOGIN_ATTRIBUTEの更新に伴うデットロック対応
Revesion History    : 2006/06/26 栄イ (中訊) QA「WEB3-login-A-FT-0021」を対応
Revesion History    : 2006/08/24 栄イ (中訊)仕様変更・モデル030
Revesion History    : 2006/09/1 栄イ (中訊)仕様変更・モデル031
Revesion History    : 2006/09/1 栄イ (中訊)指摘事項を対応
Revesion History    : 2007/06/14 劉立峰 (中訊)仕様変更・モデル034
Revesion History    : 2007/07/25 周墨洋 (中訊)仕様変更・モデル041
Revesion History    : 2007/08/30 周墨洋 (中訊)仕様変更・モデル044
Revesion History    : 2007/10/31 柴双紅 (中訊) 仕様変更・モデル048
Revesion History    : 2008/01/28 謝旋 (中訊) 仕様変更・モデル050
Revesion History    : 2008/02/01 謝旋 (中訊) 仕様変更・モデル051
Revesion History    : 2008/02/05 謝旋 (中訊) 仕様変更・モデル052
Revesion History    : 2008/02/15 武波 (中訊) 仕様変更・モデル054
Revesion History    : 2009/03/12 車進 (中訊) 仕様変更・モデル057
Revesion History    : 2010/11/10 劉レイ (中訊) 仕様変更・モデル058
*/
package webbroker3.login.service.delegate.stdimpls;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandlerDispatcher;
import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginPlugin;
import com.fitechlabs.xtrade.plugin.security.oplogin.message.AttachToAccountRequest;
import com.fitechlabs.xtrade.plugin.security.oplogin.message.AttachToAccountResponse;
import com.fitechlabs.xtrade.plugin.security.oplogin.message.SessionRequest;
import com.fitechlabs.xtrade.plugin.security.oplogin.message.SessionResponse;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.WEB3ServiceImpState;
import webbroker3.gentrade.data.AccOpenDivRow;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeDao;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocCategoryManagementRow;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.gentrade.data.EleDeliveryManagementDao;
import webbroker3.gentrade.data.EleDeliveryManagementRow;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.gentrade.data.SonarTraderRow;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.login.define.WEB3DeliverFlagDivDef;
import webbroker3.login.define.WEB3SecuredLoanAccOpenDivDef;
import webbroker3.login.message.WEB3AcceptInfo;
import webbroker3.login.message.WEB3AccOpenDivUnit;
import webbroker3.login.message.WEB3AdministratorInfo;
import webbroker3.login.message.WEB3BranchInfo;
import webbroker3.login.message.WEB3DocumentDeliverInfoUnit;
import webbroker3.login.message.WEB3InstitutionInfo;
import webbroker3.login.message.WEB3LogoutRequest;
import webbroker3.login.message.WEB3LogoutResponse;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.login.message.WEB3SetSessionResponse;
import webbroker3.login.message.WEB3SonarOperatorInfo;
import webbroker3.login.message.WEB3TraderInfo;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AccountOpenStatusDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3CumulativeAccountDivDef;
import webbroker3.common.define.WEB3DeliveryTargetDef;
import webbroker3.common.define.WEB3DocumentCheckDivDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3FutOpTradeRegistDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginStopDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3OnlyMobileOpenDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3PTSAccOpenDivDef;
import webbroker3.common.define.WEB3SecuredLoanSecAccOpenDivDef;
import webbroker3.common.define.WEB3SlingshotCheckFlagDef;
import webbroker3.common.define.WEB3StockOptionAccOpenDivDef;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeDao;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeRow;
/**
 * (WEB3ログインサービスベース)<BR>
 * ログインサービスのベースクラス<BR>
 * <BR>
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3LoginServiceBaseImpl implements WEB3BusinessService
{

    /**
     * スリングショット時のハッシュ値を計算する時に使用するキー。<BR>
     * 会社、送信、受信に関わらず固定の部分をプログラム内部に保持する。<BR>
     * <BR>
     * 値："0011050220218934748520ZJxwDdj84DDjerIUED4iORIXSecurities0074857517917wqw90H
     * <BR>
     * Sjh4H9Wk345Q9FJreafaesfdsfdsdOKYhkOIIRfhklQWP"<BR>
     */
    protected static final String HASH_CONST =
        "0011050220218934748520ZJxwDdj84DDjerIUED4iORIXSecurities0074857517917wqw90HSjh4H9Wk345Q9FJreafaesfdsfdsdOKYhkOIIRfhklQWP";

    /**
     * 「上り」（DIR→CSK）方向のハッシュ値を指示する値
     */
    protected static final int UP_HASH_VALUE = 1;

    /**
     * 「下り」（CSK→DIR）方向のハッシュ値を指示する値
     */
    protected static final int DOWN_HASH_VALUE = 2;

    /**
     * 通常のWebbrokerシステム用のハッシュ値を指示する値
     */
    protected static final int OTHER_HASH_VALUE = 0;

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3LoginServiceBaseImpl.class);

    /**
     * @@roseuid 403EE52A0258
     */
    public WEB3LoginServiceBaseImpl()
    {

    }

    /**
     * (isログイン停止)<BR>
     * 部店のログイン許可区分を取得する。<BR>
     * ログイン許可区分が”許可しない”の場合、ログイン停止（true）を返す。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@”許可する”　@の場合、ログイン可能（false）を返す。<BR>
     * @@param l_branchRow - 部店<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017AD04002E
     */
    protected boolean isLoginStop(BranchRow l_branchRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isLoginStop()";
        log.entering(STR_METHOD_NAME);

        String l_stopDiv = l_branchRow.getLoginStopDiv();
        log.debug(STR_METHOD_NAME + " .... LOGIN_STOP_DIV = " + l_stopDiv);
        if (WEB3LoginStopDef.DISABLE.equals(l_stopDiv))
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... 部店(ID: "
                    + l_branchRow.getBranchId()
                    + ") ログイン停止中");
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (check注文チャネル)<BR>
     * 注文チャネルがWEB3ChannelDefに定義されている値かをチェックする。<BR>
     * 定義されている値の場合：true、定義されていない値の場合：falseを返す。<BR>
     * @@param l_orderChannel - 注文チャネル<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     */
    protected boolean checkOrderChannel(String l_orderChannel)
        throws WEB3BaseException
    {
        final String ORDER_CHANNELS[] =
            new String[] {
                WEB3ChannelDef.BRANCH,
                WEB3ChannelDef.INTERNET,
                WEB3ChannelDef.CALL_CENTER,
                WEB3ChannelDef.MOBILE,
                WEB3ChannelDef.FIXED_TIME_AMOUNT };
        final String STR_METHOD_NAME = "checkOrderChannel(String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderChannel == null)
        {
            log.debug(STR_METHOD_NAME + " .... 注文チャネルがnull.");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        for (int idx = 0; idx < ORDER_CHANNELS.length; idx++)
        {
            if (l_orderChannel.equals(ORDER_CHANNELS[idx]))
            { /* 注文チャネルは正しい値である */
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.debug(
            STR_METHOD_NAME + " .... 注文チャネルが不正.[値：" + l_orderChannel + "]");
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (check顧客コード)<BR>
     * 部店から顧客コード最小長＆最大長、顧客コードチェックモードを<BR>
     * 取得する。<BR>
     * checkコード()メソッドを使用して顧客コードをチェックする。<BR>
     * 妥当な値の場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_inputCode - 入力された顧客コード<BR>
     * @@param l_branchRow - 部店<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 403D76C6009B
     */
    protected boolean checkAcceptCode(String l_inputCode, BranchRow l_branchRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkAcceptCode(String)";
        log.entering(STR_METHOD_NAME);

        int l_codeMin = l_branchRow.getAccountCodeMin();
        int l_codeMax = l_branchRow.getAccountCodeMax();
        String l_chkMode = l_branchRow.getAccountCodeCheckMode();
        int l_result =
            WEB3PasswordUtility.checkCode(
                l_codeMin,
                l_codeMax,
                l_chkMode,
                l_inputCode);
        log.exiting(STR_METHOD_NAME);
        return (l_result == WEB3PasswordUtility.CHECK_NORMAL);
    }

    /**
     * (checkCCオペレータコード)<BR>
     * 証券会社からCCオペレータコード最小長＆最大長、<BR>
     * CCオペレータコードチェックモードを取得する。<BR>
     * checkコード()メソッドを使用してCCオペレータコードをチェックする。<BR>
     * 妥当な値の場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_inputCode - 入力されたCCオペレータコード<BR>
     * @@param l_institutionRow - 証券会社<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 403D76D30280
     */
    protected boolean checkTraderCode(String l_inputCode, InstitutionRow l_institutionRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkTraderCode(String)";
        log.entering(STR_METHOD_NAME);

        int l_codeMin = l_institutionRow.getTrdCodeMin();
        int l_codeMax = l_institutionRow.getTrdCodeMax();
        String l_chkMode = l_institutionRow.getTrdCodeCheckMode();
        int l_result =
            WEB3PasswordUtility.checkCode(
                l_codeMin,
                l_codeMax,
                l_chkMode,
                l_inputCode);
        log.exiting(STR_METHOD_NAME);
        return (l_result == WEB3PasswordUtility.CHECK_NORMAL);
    }

    /**
     * (check管理者コード)<BR>
     * 証券会社から管理者コード最小長＆最大長、<BR>
     * 管理者コードチェックモードを取得する。<BR>
     * checkコード()メソッドを使用してCCオペレータコードをチェックする。<BR>
     * 妥当な値の場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_inputCode - 入力された管理者コード<BR>
     * @@param l_institutionRow - 証券会社<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2F0AE0171
     */
    protected boolean checkAdministratorCode(String l_inputCode, InstitutionRow l_institutionRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkAdministratorCode(String)";
        log.entering(STR_METHOD_NAME);

        int l_codeMin = l_institutionRow.getAdminCodeMin();
        int l_codeMax = l_institutionRow.getAdminCodeMax();
        String l_chkMode = l_institutionRow.getAdminCodeCheckMode();
        int l_result =
            WEB3PasswordUtility.checkCode(
                l_codeMin,
                l_codeMax,
                l_chkMode,
                l_inputCode);
        log.exiting(STR_METHOD_NAME);
        return (l_result == WEB3PasswordUtility.CHECK_NORMAL);
    }

    /**
     * (顧客成りすまし)<BR>
     * AttachToAccountRequestを生成する。<BR>
     * 生成したAttachToAccountRequestに引数：accountIDをセットする。<BR>
     * 引数：xTradeセッションIDがnullの場合<BR>
     * 　@　@AttachToAccountRequestをディスパッチに使用するリクエストとする。<BR>
     * そうでない場合<BR>
     * 　@　@セッション・リクエストを生成する。<BR>
     * 　@　@セッション・リクエストにxTradeセッションIDをセットする。<BR>
     * 　@　@セッション・リクエストにAttachToAccountRequestをセットする。<BR>
     * 　@　@セッション・リクエストをディスパッチに使用するリクエストとする。<BR>
     * PlainRequestsHandlerにディスパッチして顧客に成りすます。<BR>
     * <BR>
     * 引数：xTradeセッションIDがnullの場合<BR>
     * 　@　@結果をResponseとして取得する。<BR>
     * そうでない場合<BR>
     * 　@　@結果をセッション・レスポンスとして取得する。<BR>
     * 　@　@セキュリティ・エラーが発生している場合<BR>
     * 　@　@　@　@返されたエラー情報でWEB3SystemLayerExceptionをthrowする。<BR>
     * 　@　@セッション・レスポンスからResponseを取得する。<BR>
     * <BR>
     * Responseを確認する。<BR>
     * 　@　@CCオペレータが顧客に成りすます権限を持っていない場合<BR>
     * 　@　@※返されたErrorInfoのErrorCodeが、OpLoginPluginの<BR>
     * 　@　@※NOT_REACHABLE_ERRORのErrorCodeと等しい場合<BR>
     * 　@　@　@　@成りすまし権限なしエラーをthrowする。<BR>
     * 　@　@それ以外のエラーの場合<BR>
     * 　@　@　@　@返されたエラー情報でWEB3BusinessLayerExceptionをthrowする。<BR>
     * <BR>
     * Responseに設定された、新しいセッションIDを返す。<BR>
     * <BR>
     * @@param l_accountID - 口座ID<BR>
     * @@param l_sessionID - セッションID<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@return String
     * @@roseuid 4017B0AD0000
     */
    protected String changeAccount(long l_accountID, String l_sessionID)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "changeAccount(long, String)";
        log.entering(STR_METHOD_NAME);

        Request l_request = new AttachToAccountRequest();
        ((AttachToAccountRequest) l_request).account_id = l_accountID;
        if (l_sessionID != null)
        {
            SessionRequest l_sessionReq = new SessionRequest();
            l_sessionReq.session_id = l_sessionID;
            l_sessionReq.request = new Request[1];
            l_sessionReq.request[0] = l_request;
            l_request = l_sessionReq;
        }

        Response l_response = MessageHandlerDispatcher.Handle(l_request);
        if (l_sessionID != null)
        {
            SessionResponse l_sessionRes = (SessionResponse) l_response;
            if (l_sessionRes.getSecurityError() != null)
            {
                log.debug(
                    STR_METHOD_NAME
                        + " .... exception: "
                        + l_sessionRes.getSecurityError().toString());
                throw new WEB3SystemLayerException(
                    l_sessionRes.getSecurityError(),
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客成りすましへのディスパッチでセキュリティ・エラーが発生");
            }
            l_response = l_sessionRes.response[0];
        }

        if (l_response.server_exception != null)
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... exception: "
                    + l_response.server_exception.toString());

            String l_errorCode = l_response.server_exception.getErrorCode();
            String l_notReachable =
                OpLoginPlugin.NOT_REACHABLE_ERROR.getErrorCode();
            if (l_errorCode.equals(l_notReachable))
            { /* 指定顧客に成りすます権限がない */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90204,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "CCオペレータは顧客(ID: " + l_accountID + ") に成りすます権限がない.");
            }
            else
            { /* 権限なし以外のエラー */
                throw new WEB3BusinessLayerException(
                    l_response.server_exception,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客成りすましでExceptionが発生");
            }
        }

        log.exiting(STR_METHOD_NAME);
        return ((AttachToAccountResponse)l_response).getSessionId();
    }

    /**
     * (isログインユーザ有効)<BR>
     * 対象ユーザがdisableになっているかチェックする。<BR>
     * 　@disableの場合、無効(false)を返す。<BR>
     * <BR>
     * ログインタイプ属性から対象ユーザのログインエラー回数を取得する。<BR>
     * ログインエラー回数が引数の上限値以上の場合<BR>
     * 　@　@対象ユーザをdisable()する。<BR>
     * 　@　@無効（false）を返す。<BR>
     * 有効（true）を返す。<BR>
     * @@param l_loginInfo - ログイン情報<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 403D60C200DA
     */
    protected boolean isEnabledUser(LoginInfo l_loginInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isEnabledUser()";
        log.entering(STR_METHOD_NAME);

        if (l_loginInfo.isDisabled())
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... ユーザ(xTradeName: "
                    + l_loginInfo.getUsername()
                    + ") disabled.");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        OpLoginAdminService l_adminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        HashMap loginTypeAttrs = new HashMap();
        loginTypeAttrs.putAll(
            l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId()));
        String l_tempErrorMax =
            (String) loginTypeAttrs.get(
                WEB3LoginTypeAttributeKeyDef.LOGIN_ERROR_MAX);
        if (l_tempErrorMax == null)
        {
            log.error(
                STR_METHOD_NAME
                    + " .... ログインエラー回数上限が設定されていない.（LOGIN_ID: "
                    + l_loginInfo.getLoginId()
                    + "）");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        int l_errorMax = Integer.parseInt(l_tempErrorMax);
        if (l_loginInfo.getFailureCount() >= l_errorMax)
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... ユーザ(xTradeName: "
                    + l_loginInfo.getUsername()
                    + ") ロック中");
            l_adminService.disableLogin(l_loginInfo.getLoginId());
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (isスリングショット利用可能)<BR>
     * 顧客成りすましの場合（引数の成りすましフラグがtrueの場合）<BR>
     * 　@　@会社のSS利用可能チェック有無フラグを取得する。<BR>
     * 　@　@SS利用可能チェック有無フラグが「チェックなし」の場合<BR>
     * 　@　@　@　@以降の利用可能チェックを行わず、利用可能(true)とする。<BR>
     * <BR>
     * ※具体的な判定方法@は有料情報の取扱い仕様の検討後。<BR>
     * <BR>
     * 条件を満たす場合<BR>
     * 　@　@スリングショット利用可能(true)とする。<BR>
     * 条件を満たさない場合<BR>
     * 　@　@スリングショット利用不可能(false)とする。<BR>
     * @@param l_serviceCode<BR>
     * @@param l_isOpeAccept - 成りすまし：true、通常：false<BR>
     * @@param l_institutionRow - 証券会社<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B38B0290
     */
    protected boolean isRegiSlingshot(
        String l_serviceCode,
        boolean l_isOpeAccept,
        InstitutionRow l_institutionRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isRegiSlingshot(String, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_isOpeAccept)
        { /* 顧客成りすましの場合 */
            String l_ssCheckFlag = l_institutionRow.getSlingshotCheckFlag();
            if (l_ssCheckFlag.equals(WEB3SlingshotCheckFlagDef.DISABLED))
            { /* 成りすまし時は利用可能チェックを行わない（無条件で利用可能とする） */
                log.debug(
                    STR_METHOD_NAME + " .... 成りすまし時スリングショット利用可能チェックなし会社.利用可能.");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return true;

        // TODO: 有料情報の取扱い仕様検討後に実装
        // try
        // {
        //     QueryProcessor l_qp  = Processors.getDefaultProcessor();
        //     PayinfoRgstPK  l_pk  = new PayinfoRgstPK(mainAccountRow.getAccountId(), l_serviceCode);
        //     PayinfoRgstRow l_row = (PayinfoRgstRow)l_qp.doFindByPrimaryKeyQuery(l_pk);
        //
        //     if ( l_row.getPayinfoRgstStatus().equals(WEB3PayinfoRgstStatusDef.REGISTERED) ||
        //          l_row.getPayinfoRgstStatus().equals(WEB3PayinfoRgstStatusDef.NO_CHARGE ) ||
        //          l_row.getPayinfoRgstStatus().equals(WEB3PayinfoRgstStatusDef.TRIAL     ) )
        //     {   /* スリングショット利用可能 */
        //         log.debug(STR_METHOD_NAME + " .... スリングショット利用可能（登録状態：" + l_row.getPayinfoRgstStatus() + "）");
        //         log.exiting(STR_METHOD_NAME);
        //         return true;
        //     }
        // }
        // catch ( DataFindException e )
        // {   /* 該当データなし */
        //     log.debug(STR_METHOD_NAME + " .... 該当する有料情報登録データなし.スリングショット利用不可.");
        //     log.exiting(STR_METHOD_NAME);
        //     return false;
        // }
        // catch ( DataQueryException e )
        // {   /* DBアクセスエラー */
        //     throw new WEB3SystemLayerException(
        //         WEB3ErrorCatalog.SYSTEM_ERROR_00002,
        //         getClass().getName() + "." + STR_METHOD_NAME,
        //         e.getMessage() + " .... 有料情報登録情報の検索でDataQueryException発生.",
        //         e
        //     );
        // }
        // catch ( DataNetworkException e )
        // {   /* ネットワークエラー */
        //     throw new WEB3SystemLayerException(
        //         WEB3ErrorCatalog.SYSTEM_ERROR_00002,
        //         getClass().getName() + "." + STR_METHOD_NAME,
        //         e.getMessage() + " .... 有料情報登録情報の検索でDataNetworkException発生.",
        //         e
        //     );
        // }
        //
        // log.debug(STR_METHOD_NAME + " .... 対象有料情報が登録されていない.スリングショット利用不可.");
        // log.exiting(STR_METHOD_NAME);
        // return false;
    }

    /**
     * (calcハッシュ値)<BR>
     * 現在日付・時刻を取得する。<BR>
     * 現在時刻が当日の午前4:30未満の場合、前日の日付文字列を生成する。<BR>
     * 現在時刻が当日の午前4:30以上の場合、当日の日付文字列を生成する。<BR>
     * ※書式：yyyyMMdd<BR>
     * <BR>
     * 次の様に顧客情報文字列を作る。<BR>
     * 　@　@会社コード（2桁）＋部店コード（3桁）＋顧客コード（CD付7桁）<BR>
     * <BR>
     * 引数が「上り」を指定している場合<BR>
     * 　@　@会社の送信時ハッシュキーA、Bを取得する。<BR>
     * 引数が「下り」を指定している場合<BR>
     * 　@　@会社の受信時ハッシュキーA,Bを取得する。<BR>
     * 引数が「上り」でも「下り」でもない場合<BR>
     * 　@　@ハッシュキーAに定数のハッシュキーをセットする。<BR>
     * 　@　@ハッシュキーBに空文字列をセットする。<BR>
     * 　@　@顧客情報文字列に空文字列をセットする。<BR>
     * <BR>
     * 次の様に、ハッシュする文字列を作成する。<BR>
     * 　@　@日付文字列＋ハッシュキーA＋顧客情報文字列＋ハッシュキーB<BR>
     * Java標準のメッセージ・ダイジェスト機@能を使用してSHA-1でハッシュする。<BR>
     * 結果を16進文字列に変換する。<BR>
     * 　@　@※コードを16進に変換する際、1桁の場合は先頭に"0"を付ける。<BR>
     * <BR>
     * 結果を返す。<BR>
     * @@param l_isSend - 上り（DIR→スリングショット）：1
     * 下り（スリングショット→DIR）：2
     * それ以外：0
     * @@param l_institutionRow - 証券会社<BR>
     * @@param l_branchRow - 部店<BR>
     * @@param l_mainAccountRow - 顧客<BR>
     * @@return java.lang.String
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B3B40196
     */
    protected String calcHashValue(
        int l_isSend,
        InstitutionRow l_institutionRow,
        BranchRow l_branchRow,
        MainAccountRow l_mainAccountRow)
        throws WEB3BaseException
    {
        final SimpleDateFormat l_hashFormat = new SimpleDateFormat("yyyyMMdd");

        final String STR_METHOD_NAME = "calcHashValue(int)";
        log.entering(STR_METHOD_NAME);

        Calendar l_now = Calendar.getInstance();
        int l_hour = l_now.get(Calendar.HOUR_OF_DAY);
        int l_minute = l_now.get(Calendar.MINUTE);
        log.debug(
            STR_METHOD_NAME
                + "now  = "
                + l_now.getTime()
                + " (hour = "
                + l_hour
                + ", minute = "
                + l_minute
                + ")");

        if ((l_hour < 4) || ((l_hour == 4) && (l_minute < 30)))
        { /* 午前4:30未満の場合 */
            log.debug(STR_METHOD_NAME + "ハッシュ値の計算に前日の日付を使用する.");
            l_now.add(Calendar.DAY_OF_MONTH, -1); /* 前日の日付を使用する */
        }
        String l_dateString = l_hashFormat.format(l_now.getTime());
        log.debug(STR_METHOD_NAME + "date = " + l_dateString);

        String l_acceptString =
            l_institutionRow.getInstitutionCode()
                + l_branchRow.getBranchCode()
                + l_mainAccountRow.getAccountCode();
        log.debug(STR_METHOD_NAME + "acpt = " + l_acceptString);

        String l_hashA;
        String l_hashB;
        switch (l_isSend)
        {
            case UP_HASH_VALUE : /* DIR → CSK */
                l_hashA = l_institutionRow.getHashSendA();
                l_hashB = l_institutionRow.getHashSendB();
                break;
            case DOWN_HASH_VALUE : /* CSK → DIR */
                l_hashA = l_institutionRow.getHashReceiveA();
                l_hashB = l_institutionRow.getHashReceiveB();
                break;
            default :
                l_hashA = HASH_CONST;
                l_hashB = "";
                l_acceptString = "";
        }
        String l_hashString = l_dateString + l_hashA + l_acceptString + l_hashB;

        String l_hashValue = "";
        try
        {
            byte[] l_byteKey = l_hashString.getBytes();
            MessageDigest l_md = MessageDigest.getInstance("SHA");
            l_md.reset();
            l_md.update(l_byteKey);
            byte[] l_byteValue = l_md.digest();

            StringBuffer l_hashBuffer = new StringBuffer();
            for (int idx = 0; idx < l_byteValue.length; idx++)
            {
                String l_hexCode =
                    Integer.toHexString((int) (l_byteValue[idx] & 0x00FF));
                if (l_hexCode.length() == 1)
                {
                    l_hashBuffer.append("0"); /* "0"を付けて2桁の16進表現にする */
                }
                l_hashBuffer.append(l_hexCode);
            }
            l_hashValue = l_hashBuffer.toString();
            log.debug(STR_METHOD_NAME + "hash_value(str) = " + l_hashValue);
        }
        catch (Exception e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage() + " .... ハッシュ値計算中に例外が発生.通常では有り得ない.",
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_hashValue;
    }

    /**
     * (update最終ログイン時刻)<BR>
     * 対象者のログイン属性全てを更新用のMapに取得する。<BR>
     * 現在時刻を取得し、ログイン属性時刻書式の文字列に変換する。<BR>
     * 変換した結果で更新用Map内の最終ログイン時刻の属性値を更新する。<BR>
     * 更新用Mapの内容で対象者のログイン属性を更新する。<BR>
     * @@param l_loginID - 顧客、またはCCオペレータのログインID
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B3F901E4
     */
    protected void updateLastLoginTime(final long l_loginID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateLastLoginTime(long)";
        log.entering(STR_METHOD_NAME);

        final Date now = new Date();
//修正　@劉（FLJ）begin

//        HashMap l_loginAttr = new HashMap();
//        l_loginAttr.putAll(adminService.getLoginAttributes(l_loginID));
//
//        log.debug(
//            STR_METHOD_NAME
//                + " .... LAST_LOGIN_TIME = "
//                + l_loginAttr.get(WEB3LoginAttributeKeyDef.LAST_LOGIN));
//
//        l_loginAttr.put(
//            WEB3LoginAttributeKeyDef.LAST_LOGIN,
//            WEB3PasswordUtility.loginAttributeDateFormat.format(now));
//        adminService.setLoginAttributes(l_loginID, l_loginAttr);

        try
        {
            final QueryProcessor qp = Processors.getDefaultProcessor();
            qp.doTransaction(QueryProcessor.TX_CREATE_NEW, new TransactionCallback()
            {

                public Object process() throws DataQueryException, DataNetworkException
                {
                    qp.lockAccount(l_loginID, true);
                    LoginAttributeParams params = new LoginAttributeParams();
                    params.setLoginId(l_loginID);
                    params.setAttributeName(WEB3LoginAttributeKeyDef.LAST_LOGIN);
                    params.setAttributeValue(WEB3PasswordUtility.loginAttributeDateFormat.
                                             format(now));
                    LoginAttributeRow row = null;
                    try
                    {
                        row = LoginAttributeDao.findRowByPk(l_loginID,
                            WEB3LoginAttributeKeyDef.LAST_LOGIN);
                    }
                    catch (Exception e)
                    {

                    }

                    if (row == null)
                    {
                        qp.doInsertQuery(params);
                    }
                    else
                    {
                        qp.doUpdateQuery(params);
                    }

                    return null;
                }

            });

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
//修正　@劉（FLJ）end

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getCCオペレータ情報)<BR>
     * CCオペレータ（DB:扱者マスタ）のデータをコピーしてレスポンス用のCCオペレータ<BR>
     * 情報を作成する。<BR>
     * <BR>
     * レスポンス設定<BR>
     * 　@部店コード　@　@　@　@：引数.l_traderRow.getBranchCode()の戻り値<BR>
     * 　@扱者ID　@　@　@　@　@　@：引数.l_traderRow.getTraderId()の戻り値<BR>
     * 　@扱者Code　@　@　@　@　@：引数.l_traderRow.getTraderCode()の戻り値<BR>
     * 　@xTradeユーザ名　@　@：引数.l_loginInfo.getUsername()の戻り値<BR>
     * 　@扱者名（漢字）　@　@：引数.l_traderRow.getFamilyName()の戻り値<BR>
     * 　@扱者名（カナ）　@　@：引数.l_traderRow.getFamilyNameAlt1()の戻り値<BR>
     * 　@代行注文可否フラグ：引数.l_traderRow.getAccountOrderFlag()の戻り値<BR>
     * 　@最終ログイン時刻　@：引数.l_loginInfo.ログイン属性.最終ログイン時刻<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@※取得できなければnull<BR>`
     * 　@所属コード　@　@　@　@：引数.l_traderRow.getDepartmentCode()の戻り値<BR>
     * <BR>
     * コピーの過程で何らかの例外が発生した場合<BR>
     * 　@　@致命的なシステムエラーをthrowする。<BR>
     * @@param l_traderRow - 扱者<BR>
     * @@param l_loginInfo - ログイン情報<BR>
     * @@return webbroker3.login.message.WEB3TraderInfo
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B4FD00CB
     */
    protected WEB3TraderInfo getTraderInfo(
        TraderRow l_traderRow,
        LoginInfo l_loginInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTraderInfo()";
        log.entering(STR_METHOD_NAME);

        WEB3TraderInfo l_traderInfo = new WEB3TraderInfo();
        try
        {
            l_traderInfo.branchCode = l_traderRow.getBranchCode();
            l_traderInfo.traderID = l_traderRow.getTraderId();
            l_traderInfo.traderCode = l_traderRow.getTraderCode();
            l_traderInfo.xTradeUsername = l_loginInfo.getUsername();
            l_traderInfo.nameKanji = l_traderRow.getFamilyName();
            l_traderInfo.nameKana = l_traderRow.getFamilyNameAlt1();
            l_traderInfo.accountOrderFlag = l_traderRow.getAccountOrderFlag();

            String l_lastLogin =
                (String) l_loginInfo.getAttributes().get(
                    WEB3LoginAttributeKeyDef.LAST_LOGIN);
            l_traderInfo.lastLoginTime = null;

            l_traderInfo.departmentCode = l_traderRow.getDepartmentCode();

            if (l_lastLogin != null)
            {
                try
                {
                    l_traderInfo.lastLoginTime =
                        WEB3PasswordUtility.loginAttributeDateFormat.parse(
                            l_lastLogin);
                }
                catch (ParseException pex)
                {
                }
            }
            log.debug(
                STR_METHOD_NAME
                    + " .... lastLogin = "
                    + l_traderInfo.lastLoginTime);
        }
        catch (Exception ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage() + " .... ここで例外が発生する事は有り得ない筈",
                ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_traderInfo;
    }

    /**
     * (get顧客情報)<BR>
     *  <BR>
     * 顧客（DB:顧客マスタ）のデータをコピーしてレスポンス用の顧客情報を<BR>
     * 作成する。コピーの過程で何らかの例外が発生した場合、<BR>
     * 致命的なシステムエラーをthrowする。<BR>
     * 　@<BR>
     * [編集内容]<BR>
     * 口座ＩＤ = 引数.顧客.口座ＩＤ<BR>
     * 顧客コード = 引数の６桁顧客コード<BR>
     * ＣＤ付顧客コード = 引数.顧客.口座コード<BR>
     * 名前（漢字） = 引数.顧客.名前（苗字） ※1 顧客名（漢字）として使用<BR>
     * 名前（カナ） = 引数.顧客.名前（苗字１） ※2 顧客名（カナ）として使用<BR>
     * 性別 = 引数.顧客.性別<BR>
     * 最終ログイン時刻 = 引数.ログイン情報.getAttributes().get(ログイン属性名.最終ログイン時刻)<BR>
     * 信用取引登録フラグ = <BR>
     * ※3 制度，一般のどちらかで口座開設をしていればtrue，<BR>
     * どちらも開設していなければfalse。<BR>
     * －（引数.顧客.制度信用口座開設区分 == １：口座開設 || <BR>
     * 　@　@引数.顧客.一般信用口座開設区分 == １：口座開設）の場合true，<BR>
     * －以外、false。<BR>
     * 先物OP取引登録 = <BR>
     * －（顧客※4.is先物OP口座開設（先物／オプション区分.先物） == false &&<BR>
     * 顧客※4.is先物OP口座開設（先物／オプション区分.オプション） == true）の場合、<BR>
     * １：登録済み（OP買建取引）<BR>
     * －（顧客※4.is先物OP口座開設（先物／オプション区分.先物） == true &&<BR>
     * 顧客※4.is先物OP口座開設（先物／オプション区分.オプション） == false）の場合、<BR>
     * １：登録済み（先物取引）<BR>
     * －（顧客※4.is先物OP口座開設（先物／オプション区分.先物） == true &&<BR>
     * 顧客※4.is先物OP口座開設（先物／オプション区分.オプション） == true）の場合、<BR>
     * １：登録済み（先物／OP取引）<BR>
     * －以外、0:登録なし。<BR>
     * 累投登録フラグ = <BR>
     * （引数.顧客.累投口座開設区分 == 累投口座開設）の場合true，以外false<BR>
     * 為替保証金口座開設区分 = <BR>
     *   －（引数.顧客.ＦＸ口座開設区分 == 0：DEFAULT）の場合、0：　@口座なし。<BR>
     *   －（引数.顧客.ＦＸ口座開設区分 == 1：口座開設）の場合、1：　@口座開設。<BR>
     *   －（引数.顧客.ＦＸ口座開設区分 == 2：口座抹消）の場合、2：　@口座抹消。<BR>
     *   －（引数.顧客.ＦＸ口座開設区分 == 3：振替停止）の場合、3：　@振替停止。<BR>
     * 外国株式連携口座開設区分 = <BR>
     *   －（引数.顧客.外国株式連携口座開設区分 == 0：DEFAULT）の場合、0：　@口座なし。<BR>
     *   －（引数.顧客.外国株式連携口座開設区分 == 1：口座開設）の場合、1：　@口座開設。<BR>
     *   －（引数.顧客.外国株式連携口座開設区分 == 2：口座抹消）の場合、2：　@口座抹消<BR>
     * リアル時価取得フラグ = 顧客※4.isリアル客()<BR>
     * 停止フラグ = ※5<BR>
     * Ｙ客フラグ = （引数.顧客.Ｙ客区分 == Ｙ客）の場合true，以外false。<BR>
     * 外国証券口座開設区分 = <BR>
     *   －（引数.顧客.外国証券口座開設区分 == 0：未開設）の場合、0：　@口座なし。<BR>
     *   －（引数.顧客.外国証券口座開設区分 == 1：開設）の場合、1：　@口座開設。<BR>
     * 預り証券評価制区分 = <BR>
     *   －（顧客余力条件※6.預り証券評価制区分 == 0：未実施）の場合、0：　@未実施。<BR>
     *   －（顧客余力条件※6.預り証券評価制区分 == 1：実施）の場合、1：　@実施。<BR>
     * ストックオプション口座開設区分 = <BR>
     *   －（引数.顧客.ストックオプション口座開設区分 == NULL）の場合、0：　@口座なし。 <BR>
     *   －（引数.顧客.ストックオプション口座開設区分 == 0：未開設）の場合、0：　@口座なし。<BR> 
     *   －（引数.顧客.ストックオプション口座開設区分 == 1：開設）の場合、1：　@口座開設。 <BR>
     *   －（引数.顧客.ストックオプション口座開設区分の値が上記以外）の場合、 <BR>
     * 　@　@　@「パラメータ値不正」の例外をthrowする。 <BR>
     *   class: WEB3SystemLayerException<BR>
     *   tag:  SYSTEM_ERROR_80017<BR>
     *  <BR>
     *  モバイル専用口座開設区分 = <BR>
     *   －（引数.顧客.モバイル専用口座開設区分 == NULL）の場合、0：　@口座なし。 <BR>
     *   －（引数.顧客.モバイル専用口座開設区分 == 0：未開設）の場合、0：　@口座なし。 <BR>
     *   －（引数.顧客.モバイル専用口座開設区分 == 1：開設）の場合、1：　@口座開設。 <BR>
     *   －（引数.顧客.モバイル専用口座開設区分の値が上記以外）の場合、 <BR>
　@　@ *　@     「パラメータ値不正」の例外をthrowする。<BR>
　@　@ *   class: WEB3BaseRuntimeException<BR>
　@　@ *   tag:  SYSTEM_ERROR_80017<BR>
     *  <BR>
     * 担保ローン口座開設区分 = <BR>
     * 　@－（引数.顧客.証券担保ローン区分 == NULL）の場合、0：　@口座なし。<BR>
     * 　@－（引数.顧客.証券担保ローン区分 == 0：未開設）の場合、0：　@口座なし。<BR>
     * 　@－（引数.顧客.証券担保ローン区分 == 1：開設）の場合、<BR>
     * 　@　@　@　@　@1)株券担保ローン口座テーブルから該当顧客のデータを取得する。<BR>
     * 　@　@　@　@　@　@　@[条件]<BR>
     * 　@　@　@　@　@　@　@口座ID = 引数.顧客.口座ID and 開設状況 = 2：成約<BR>
     * 　@　@　@　@　@2)データが取得できなかった場合、0：　@口座なし。<BR>
     * 　@　@　@　@　@3)データが取得できた場合、1：　@口座開設。<BR>
     * 　@－（引数.顧客.証券担保ローン区分の値が上記以外）の場合、<BR>
     * 　@　@　@「パラメータ値不正」の例外をthrowする。 <BR>
     * 　@class : WEB3SystemLayerException<BR>
     * 　@tag   : SYSTEM_ERROR_80017<BR>
     *  <BR>
     * 書面交付管理情報 = this.get書面交付管理情報()の戻り値。 <BR>
     * 　@　@[get書面交付管理情報の引数] <BR>
     * 　@　@顧客：引数.顧客 <BR>
     * 　@　@注文経路区分：引数.注文経路区分 <BR>
     * 　@　@信用取引登録フラグ：上の処理で取得した信用取引登録フラグ <BR>
     * 　@　@先物OP取引登録：上の処理で取得した先物OP取引登録<BR>
     * <BR>
     * SONAR扱者情報 =<BR>
     * 　@－（引数.顧客.扱者コード（SONAR） == NULL）の場合、NULL。<BR>
     * 　@－（引数.顧客.扱者コード（SONAR）が上記以外）の場合、<BR>
     * 　@　@　@　@１）SONAR扱者マスタより該当データを取得する。<BR>
     * 　@　@　@　@　@　@　@　@[条件]<BR>
     * 　@　@　@　@　@　@　@　@証券会社コード = 引数.顧客.証券会社コード<BR>
     * 　@　@　@　@　@　@　@　@部店コード       = 引数.顧客.部店コード<BR>
     * 　@　@　@　@　@　@　@　@扱者コード       = 引数.顧客.扱者コード（SONAR）<BR>
     * 　@　@　@　@２）データが取得できなかった場合、NULL。<BR>
     * 　@　@　@　@３）データが取得できた場合、以下の通り設定する。<BR>
     * 　@　@　@　@　@SONAR扱者情報.扱者コード　@　@　@= SONAR扱者マスタ.扱者コード<BR>
     * 　@　@　@　@　@SONAR扱者情報.扱者名(カナ) 　@= SONAR扱者マスタ.扱者名(カナ)<BR>
     * 　@　@　@　@　@SONAR扱者情報.扱者名(漢字)　@= SONAR扱者マスタ.扱者名(漢字)<BR>
     * <BR>
     * PTS口座開設区分 =<BR>
     * 　@－（引数.顧客.PTS口座開設区分 == NULL）の場合、0：　@口座なし。<BR>
     * 　@－（引数.顧客.PTS口座開設区分 == 0：未開設）の場合、0：　@口座なし。<BR>
     * 　@－（引数.顧客.PTS口座開設区分 == 1：開設）の場合、1：　@口座開設。<BR>
     * 　@－（引数.顧客.PTS口座開設区分の値が上記以外）の場合、<BR>
     * 　@　@　@「パラメータ値不正」の例外をthrowする。<BR>
     * 　@class : WEB3BaseRuntimeException<BR>
     * 　@tag   : SYSTEM_ERROR_80017<BR>
     * <BR>
     * 証券会社プリファ@レンステーブルから、口座開設区分情報取込実施を取得する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社ID = 引数.顧客.証券会社ID<BR>
     * 　@And　@プリファ@レンス名 = プリファ@レンス名.口座開設区分情報取込実施<BR>
     * 　@And　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * 口座開設区分一覧 =<BR>
     * －（口座開設区分情報取込実施データが取得できなかった）の場合、NULL。<BR>
     * －（口座開設区分情報取込実施データが取得できた）の場合、this.get口座開設区分()の戻り値。<BR>
     * 　@　@　@[get口座開設区分の引数]<BR>
     * 　@　@　@口座ID：引数.顧客.口座ID<BR>
     * <BR>
     * 電子交付申込チェックフラグ = 電子交付管理テーブル.電子交付申込チェックフラグ<BR>
     * （※電子交付管理テーブルにレコードが存在しない場合、nullをセットする。）<BR>
     * 電子交付管理テーブルから、電子交付申込チェックフラグを取得する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.顧客.getInstitution().getInstitutionCode() And<BR>
     * 　@部店コード = 引数.顧客.getBranch().getBranchCode() And<BR>
     * 　@口座コード = 引数.顧客.getAccountCode()<BR>
     * <BR>
     * ※4　@顧客オブジェクト<BR>
     * 拡張アカウントマネージャ.get顧客(引数.顧客.口座ＩＤ：long)にて取得する。<BR>
     *  <BR>
     * ※5　@停止フラグ<BR>
     * 注文チェック.validate取引可能顧客()がエラーになる場合はtrue，以外false。<BR>
     *  <BR>
     * [validate取引可能顧客()に指定する引数]<BR>
     * 顧客：　@※4<BR>
     *  <BR>
     * ※6　@顧客余力条件<BR>
     * TradingpowerCalcConditionDao.findRowByAccountId(引数.顧客.口座ＩＤ)にて取得する。<BR>
     *  <BR>
     * @@param l_acceptCode - 入力された６桁の顧客コード<BR>
     * @@param l_mainAccountRow - 顧客<BR>
     * @@param l_loginInfo - ログイン情報<BR>
     * @@param l_orderRootDiv - 経路区分<BR>
     * @@return webbroker3.login.message.WEB3AcceptInfo
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B55E02DE
     */
    protected WEB3AcceptInfo getAcceptInfo(
        String l_acceptCode,
        MainAccountRow l_mainAccountRow,
        LoginInfo l_loginInfo,
        String l_orderRootDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAcceptInfo(String)";
        log.entering(STR_METHOD_NAME);

        WEB3AcceptInfo l_acceptInfo = new WEB3AcceptInfo();
        try
        {
            //get 顧客オブジェクト
            WEB3GentradeMainAccount l_genMainAccount =
                new WEB3GentradeMainAccount(l_mainAccountRow.getAccountId());

            //口座ＩＤ = mainAccountRow.口座ＩＤ
            l_acceptInfo.accountID = l_mainAccountRow.getAccountId();

            //顧客コード = 引数の６桁顧客コード
            l_acceptInfo.acceptCode = l_acceptCode;

            //ＣＤ付顧客コード = mainAccountRow.口座コード
            l_acceptInfo.acceptCodeCD = l_mainAccountRow.getAccountCode();

            l_acceptInfo.xTradeUsername = l_loginInfo.getUsername();

            //名前（漢字） = mainAccountRow.名前（苗字） ※1 顧客名（漢字）として使用
            l_acceptInfo.nameKanji = l_mainAccountRow.getFamilyName();

            //名前（カナ） = mainAccountRow.名前（苗字１） ※2 顧客名（カナ）として使用
            l_acceptInfo.nameKana = l_mainAccountRow.getFamilyNameAlt1();

            //性別 = mainAccountRow.性別
            l_acceptInfo.sex = l_mainAccountRow.getSex();

            //最終ログイン時刻 = loginInfo.getAttributes().get(ログイン属性名.最終ログイン時刻)
            String l_lastLogin =
                (String) l_loginInfo.getAttributes().get(
                    WEB3LoginAttributeKeyDef.LAST_LOGIN);
            l_acceptInfo.lastLoginTime = null;
            if (l_lastLogin != null)
            {
                try
                {
                    l_acceptInfo.lastLoginTime =
                        WEB3PasswordUtility.loginAttributeDateFormat.parse(
                            l_lastLogin);
                }
                catch (ParseException pex)
                {
                }
            }
            log.debug(STR_METHOD_NAME + " .... lastLogin = " + l_acceptInfo.lastLoginTime);

            //信用取引登録フラグ
            if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv())
                || WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
            {
                l_acceptInfo.marginRegistFlag = true;
            }
            else
            {
                l_acceptInfo.marginRegistFlag = false;
            }

            //先物OP取引登録
            if ((l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES) == false)
                && (l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION) == true))
            {
                l_acceptInfo.futOpTradeRegist =
                    WEB3FutOpTradeRegistDef.OP_REGIST;
            }
            else if ((l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES) == true)
                    && (l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION) == false))
            {
                l_acceptInfo.futOpTradeRegist =
                    WEB3FutOpTradeRegistDef.FUT_REGIST;
            }
            else if ((l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES) == true)
                    && (l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION) == true))
            {
                l_acceptInfo.futOpTradeRegist =
                    WEB3FutOpTradeRegistDef.FUT_OP_REGIST;
            }
            else
            {
                l_acceptInfo.futOpTradeRegist = WEB3FutOpTradeRegistDef.NOT_REGIST;
            }

            //累投登録フラグ
            //（mainAccountRow.累投口座開設区分 == 累投口座開設）の場合true，以外false
            if (WEB3CumulativeAccountDivDef.ESTABLISH.equals(l_mainAccountRow.getRuitoAccOpenDiv()))
            {
                l_acceptInfo.ruitoRegistFlag = true;
            }
            else
            {
                l_acceptInfo.ruitoRegistFlag = false;
            }

            //為替保証金口座開設区分
            String l_strFxAccOpenDiv = l_mainAccountRow.getFxAccOpenDiv();
            if (WEB3AccountOpenDef.OPEN.equals(l_strFxAccOpenDiv)
                || WEB3AccountOpenDef.DELETED.equals(l_strFxAccOpenDiv)
                || WEB3AccountOpenDef.NOT_OPEN.equals(l_strFxAccOpenDiv)
                || WEB3AccountOpenDef.TRANSFER_STOP.equals(l_strFxAccOpenDiv))
            {
                l_acceptInfo.fxAccOpenDiv = l_strFxAccOpenDiv;
            }
            else if(l_strFxAccOpenDiv == null)
            {
                l_acceptInfo.fxAccOpenDiv = WEB3AccountOpenDef.NOT_OPEN;
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "mainAccountRow.為替保証金口座開設区分 = " + l_strFxAccOpenDiv);
            }

            //外国株式連携口座開設区分
            String l_strFeqConAccOpenDiv = l_mainAccountRow.getFeqConAccOpenDiv();
            if (WEB3AccountOpenDef.OPEN.equals(l_strFeqConAccOpenDiv)
                || WEB3AccountOpenDef.DELETED.equals(l_strFeqConAccOpenDiv)
                || WEB3AccountOpenDef.NOT_OPEN.equals(l_strFeqConAccOpenDiv))
            {
                l_acceptInfo.fstkCooperateAccOpenDiv = l_strFeqConAccOpenDiv;
            }
            else if(l_strFeqConAccOpenDiv == null)
            {
                l_acceptInfo.fstkCooperateAccOpenDiv = WEB3AccountOpenDef.NOT_OPEN;
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "mainAccountRow.外国株式連携口座開設区分 = " + l_strFeqConAccOpenDiv);
            }

            //リアル時価取得フラグ = 顧客※4.isリアル客()
            l_acceptInfo.quotoFlag = l_genMainAccount.isRealCustomer();

            //停止フラグ
            //注文チェック.validate取引可能顧客()がエラーになる場合はtrue，以外false
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeOrderValidator l_genOrderValidator =
                (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();
            OrderValidationResult l_orderValidationResult =
                l_genOrderValidator.validateAccountForTrading(l_genMainAccount);
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                l_acceptInfo.stopFlag = true;
            }
            else
            {
                l_acceptInfo.stopFlag = false;
            }

            //Ｙ客フラグ = （mainAccountRow.Ｙ客区分 == Ｙ客）の場合true，以外false
            if (WEB3YellowCustomerDef.YELLOW_CUSTOMER.equals(l_mainAccountRow.getYellowCustomer()))
            {
                l_acceptInfo.yellowCustomerFlag = true;
            }
            else
            {
                l_acceptInfo.yellowCustomerFlag = false;
            }
            
            //外国証券口座開設区分
            String l_strFeAccOpenDiv = l_mainAccountRow.getForeignSecAccOpenDiv();
            if (WEB3ForeignSecAccOpenDiv.NOT_OPEN.equals(l_strFeAccOpenDiv)
                || WEB3ForeignSecAccOpenDiv.OPEN.equals(l_strFeAccOpenDiv))
            {
                l_acceptInfo.feAccOpenDiv = l_strFeAccOpenDiv;
            }
            else if (l_strFeAccOpenDiv == null)
            {
                l_acceptInfo.feAccOpenDiv = WEB3ForeignSecAccOpenDiv.NOT_OPEN;
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "mainAccountRow.外国証券口座開設区分 = " + l_strFeAccOpenDiv);
            }
            
            //預り証券評価制区分
            TradingpowerCalcConditionRow l_tradingpowerCalcConditionRow =
                TradingpowerCalcConditionDao.findRowByAccountId(
                    l_mainAccountRow.getAccountId());
            if (l_tradingpowerCalcConditionRow == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客余力条件テーブルに、口座ID[" + l_mainAccountRow.getAccountId() + "]のレコードが存在しません。");
            }
            String l_strAssetEvaluationDiv =
                l_tradingpowerCalcConditionRow.getAssetEvaluationDiv();
            if (WEB3EnforcementDef.NOT_ENFORCEMENT.equals(l_strAssetEvaluationDiv)
                || WEB3EnforcementDef.ENFORCEMENT.equals(l_strAssetEvaluationDiv))
            {
                l_acceptInfo.assetEvaluationDiv = l_strAssetEvaluationDiv;
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "TradingpowerCalcConditionRow.預り証券評価制区分 = " + l_strAssetEvaluationDiv);
            }

            //ストックオプション口座開設区分
            String l_strStockOptionAccOpenDiv = l_mainAccountRow.getStockOptionAccOpenDiv();
            if (l_strStockOptionAccOpenDiv == null)
            {
                l_acceptInfo.stockOpAccOpenDiv = WEB3StockOptionAccOpenDivDef.NOT_OPEN;
            }
            else if (WEB3StockOptionAccOpenDivDef.NOT_OPEN.equals(l_strStockOptionAccOpenDiv) 
                || WEB3StockOptionAccOpenDivDef.OPEN.equals(l_strStockOptionAccOpenDiv))
            {
                l_acceptInfo.stockOpAccOpenDiv = l_strStockOptionAccOpenDiv;
            }
            else
            {
                log.error("ストックオプション口座開設区分：" + l_strStockOptionAccOpenDiv);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "MainAccountRow.ストックオプション口座開設区分 = " + l_strStockOptionAccOpenDiv);
            }

            //モバイル専用口座開設区分
            String l_strOnlyMobileOpenDiv = l_mainAccountRow.getOnlyMobileOpenDiv();
            if (l_strOnlyMobileOpenDiv == null)
            {
                l_acceptInfo.mobileAccOpenDiv = WEB3OnlyMobileOpenDivDef.DEFAULT;
            }
            else if (WEB3OnlyMobileOpenDivDef.DEFAULT.equals(l_strOnlyMobileOpenDiv)
                || WEB3OnlyMobileOpenDivDef.OPEN.equals(l_strOnlyMobileOpenDiv))
            {
                l_acceptInfo.mobileAccOpenDiv = l_strOnlyMobileOpenDiv;
            }
            else
            {
                log.error("モバイル専用口座開設区分：" + l_strOnlyMobileOpenDiv);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "MainAccountRow.モバイル専用口座開設区分 = " + l_strOnlyMobileOpenDiv);
            }

            //担保ローン口座開設区分 =
            String l_strSecuredLoanAccOpenDiv = l_mainAccountRow.getSecuredLoanSecAccOpenDiv();
            if (l_strSecuredLoanAccOpenDiv == null)
            {
                //  －（引数.顧客.証券担保ローン区分 == NULL）の場合、0：　@口座なし。
                l_acceptInfo.securedLoanAccOpenDiv = WEB3SecuredLoanAccOpenDivDef.SECURED_LOAN_ACCOUNT_NOT_OPEN;
            }
            else if (WEB3SecuredLoanSecAccOpenDivDef.NOT_OPEN.equals(l_strSecuredLoanAccOpenDiv))
            {
                //  －（引数.顧客.証券担保ローン区分 == 0：未開設）の場合、0：　@口座なし。
                l_acceptInfo.securedLoanAccOpenDiv = WEB3SecuredLoanAccOpenDivDef.SECURED_LOAN_ACCOUNT_NOT_OPEN;
            }
            else if (WEB3SecuredLoanSecAccOpenDivDef.OPEN.equals(l_strSecuredLoanAccOpenDiv))
            {
                //  －（引数.顧客.証券担保ローン区分 == 1：開設）の場合、
                //  1)株券担保ローン口座テーブルから該当顧客のデータを取得する。
                //        [条件]
                //        口座ID = 引数.顧客.口座ID and 開設状況 = 2：成約
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                StringBuffer l_sbQueryString = new StringBuffer();
                l_sbQueryString.append(" account_id = ? ");
                l_sbQueryString.append(" and account_open_status = ? ");

                List l_lisValues = new ArrayList();
                l_lisValues.add(new Long(l_mainAccountRow.getAccountId()));
                l_lisValues.add(WEB3AccountOpenStatusDef.PROMISE);

                Object[] l_queryDataContainers = new Object[l_lisValues.size()];

                l_lisValues.toArray(l_queryDataContainers);

                try
                {
                    List l_lisStockSecuredLoanRecords =
                        l_queryProcessor.doFindAllQuery(
                            StockSecuredLoanRow.TYPE,
                            l_sbQueryString.toString(),
                            l_queryDataContainers);

                    if (l_lisStockSecuredLoanRecords.isEmpty())
                    {
                        //  2)データが取得できなかった場合、0：　@口座なし。
                        l_acceptInfo.securedLoanAccOpenDiv = WEB3SecuredLoanAccOpenDivDef.SECURED_LOAN_ACCOUNT_NOT_OPEN;
                    }
                    else
                    {
                        //  3)データが取得できた場合、1：　@口座開設。
                        l_acceptInfo.securedLoanAccOpenDiv = WEB3SecuredLoanAccOpenDivDef.SECURED_LOAN_ACCOUNT_OPEN;
                    }
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            else
            {
                //  －（引数.顧客.証券担保ローン区分の値が上記以外）の場合、
                //　@　@　@「パラメータ値不正」の例外をthrowする。
                log.error("担保ローン口座開設区分：" + l_strSecuredLoanAccOpenDiv);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "MainAccountRow.担保ローン口座開設区分 = " + l_strSecuredLoanAccOpenDiv);
            }

            // 書面交付管理情報
            //  [get書面交付管理情報の引数]
            //  顧客：引数.顧客
            //  注文経路区分：引数.注文経路区分
            //  信用取引登録フラグ：上の処理で取得した信用取引登録フラグ
            //  先物OP取引登録：上の処理で取得した先物OP取引登録
            l_acceptInfo.documentDeliverList = this.getDocumentDeliverInfoUnit(
                l_mainAccountRow,
                l_orderRootDiv,
                l_acceptInfo.marginRegistFlag,
                l_acceptInfo.futOpTradeRegist);

            //SONAR扱者情報
            if (l_mainAccountRow.getSonarTraderCode() != null)
            {
                StringBuffer l_sbSql = new StringBuffer();
                l_sbSql.append(" institution_code = ? ");
                l_sbSql.append(" and branch_code = ? ");
                l_sbSql.append(" and sonar_trader_code = ? ");

                Object[] l_sbValues = new Object[]{
                    l_mainAccountRow.getInstitutionCode(),
                    l_mainAccountRow.getBranchCode(),
                    l_mainAccountRow.getSonarTraderCode()};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisResults = null;
                try
                {
                    l_lisResults = l_queryProcessor.doFindAllQuery(
                        SonarTraderRow.TYPE,
                        l_sbSql.toString(),
                        l_sbValues);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                if (l_lisResults.size() != 0)
                {
                    //データが取得できた場合、以下の通り設定する。
                    SonarTraderRow l_sonarTraderRow =
                        (SonarTraderRow)l_lisResults.get(0);
                    WEB3SonarOperatorInfo l_sonarOperatorInfo =
                        new WEB3SonarOperatorInfo();
                    //SONAR扱者情報.扱者コード　@　@　@= SONAR扱者マスタ.扱者コード
                    l_sonarOperatorInfo.operatorCode = l_sonarTraderRow.getSonarTraderCode();

                    //SONAR扱者情報.扱者名(カナ) 　@= SONAR扱者マスタ.扱者名(カナ)
                    l_sonarOperatorInfo.nameKana = l_sonarTraderRow.getFamilyNameAlt1();

                    //SONAR扱者情報.扱者名(漢字)　@= SONAR扱者マスタ.扱者名(漢字)
                    l_sonarOperatorInfo.nameKanji = l_sonarTraderRow.getFamilyName();

                    l_acceptInfo.sonarOperatorInfo = l_sonarOperatorInfo;
                }
            }

            //PTS口座開設区分 =
            //－（引数.顧客.PTS口座開設区分 == NULL）の場合、0：　@口座なし。
            if (l_mainAccountRow.getPtsAccOpenDiv() == null)
            {
                l_acceptInfo.ptsAccOpenDiv = WEB3PTSAccOpenDivDef.DEFAULT;
            }
            //－（引数.顧客.PTS口座開設区分 == 0：未開設）の場合、0：　@口座なし。
            else if (WEB3PTSAccOpenDivDef.DEFAULT.equals(l_mainAccountRow.getPtsAccOpenDiv()))
            {
                l_acceptInfo.ptsAccOpenDiv = WEB3PTSAccOpenDivDef.DEFAULT;
            }
            //－（引数.顧客.PTS口座開設区分 == 1：開設）の場合、1：　@口座開設。
            else if (WEB3PTSAccOpenDivDef.ACCOUNT_OPEN.equals(l_mainAccountRow.getPtsAccOpenDiv()))
            {
                l_acceptInfo.ptsAccOpenDiv = WEB3PTSAccOpenDivDef.ACCOUNT_OPEN;
            }
            else
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータ値不正。");
            }

            //証券会社プリファ@レンステーブルから、口座開設区分情報取込実施を取得する。
            //条件:
            //証券会社ID = 引数.顧客.証券会社ID
            //プリファ@レンス名 = プリファ@レンス名.口座開設区分情報取込実施
            //プリファ@レンス名の連番 = 1
            InstitutionPreferencesRow l_institutionPreferencesRow = null;
            try
            {
                l_institutionPreferencesRow =
                    InstitutionPreferencesDao.findRowByInstitutionIdNameNameSerialNo(
                    l_mainAccountRow.getInstitutionId(),
                    WEB3InstitutionPreferencesNameDef.ACCOUNTOPEN_INFO_TAKING_DIV,
                    1);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);

                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //口座開設区分一覧 =
            //－（口座開設区分情報取込実施データが取得できなかった）の場合、NULL。
            //－（口座開設区分情報取込実施データが取得できた）の場合、this.get口座開設区分()の戻り値。
            //[get口座開設区分の引数]
            //口座ID：引数.顧客.口座ID
            if (l_institutionPreferencesRow != null)
            {
            	l_acceptInfo.accOpenDivList = this.getAccOpenDivUnit(l_mainAccountRow.getAccountId());
            }
            else
            {
            	l_acceptInfo.accOpenDivList = null;
            }

            //電子交付申込チェックフラグ = 電子交付管理テーブル.電子交付申込チェックフラグ
            //（※電子交付管理テーブルにレコードが存在しない場合、nullをセットする。）
            //電子交付管理テーブルから、電子交付申込チェックフラグを取得する。
            //　@[条件]
            //　@証券会社コード = 引数.顧客.getInstitution().getInstitutionCode() And
            //　@部店コード = 引数.顧客.getBranch().getBranchCode() And
            //　@口座コード = 引数.顧客.getAccountCode()
            EleDeliveryManagementRow l_eleDeliveryManagementRow = null;

            try
            {
                l_eleDeliveryManagementRow =
                    EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                        l_mainAccountRow.getInstitutionCode(),
                        l_mainAccountRow.getBranchCode(),
                        l_mainAccountRow.getAccountCode());
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            if (l_eleDeliveryManagementRow != null)
            {
                //電子交付申込チェックフラグ = 電子交付管理テーブル.電子交付申込チェックフラグ
                l_acceptInfo.edCheckFlag =
                    String.valueOf(l_eleDeliveryManagementRow.getEleDelRegiFlag());
            }
            else
            {
                //電子交付管理テーブルにレコードが存在しない場合、nullをセットする。
                l_acceptInfo.edCheckFlag = null;
            }
        }
        catch (Exception ex)
        {
        	// catchしたオブジェクトが業務エラーの場合、例外オブジェクトをそのままスロー。
        	// 以外、システムエラーをスローする。
        	if (ex instanceof WEB3BaseException)
        	{
        		throw (WEB3BaseException)ex;
        	}
        	else
        	{
				log.error(STR_METHOD_NAME, ex);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80002,
					getClass().getName() + "." + STR_METHOD_NAME,
					ex.getMessage() + " .... ここで例外が発生する事は有り得ない筈",
					ex);
        	}
        }

        log.exiting(STR_METHOD_NAME);
        return l_acceptInfo;
    }

    /**
     * (get管理者情報)<BR>
     *  <BR>
     * 管理者（DB:管理者マスタ，管理者タイプ）のデータをコピーしてレスポンス用<BR>
     * の管理者情報を作成する。<BR>
     *  <BR>
     * [編集内容] <BR>
     * 管理者ＩＤ = 引数.管理者.管理者ＩＤ<BR>
     * 管理者コード = 引数.管理者.管理者コード<BR>
     * xTradeユーザ名 = 引数.ログイン.getUserName()<BR>
     * 管理者名 = 引数.管理者.管理者名<BR>
     * 権限レベル = 引数.管理者.権限レベル<BR>
     * 最終ログイン時刻 = 引数.ログイン.getAttributes().get(ログイン属性名.最終ログイン時刻)<BR>
     * 全部店許可フラグ = (*1)管理者タイプ行.全部店許可フラグ<BR>
     * DIR管理者フラグ = (*1)管理者タイプ行.DIR管理者フラグ<BR>
     * <BR>
     * (*1) 管理者タイプ行の取得<BR>
     * 管理者タイプテーブルを以下の条件で検索し、該当行を取得する。<BR>
     * <BR>
     * [条件]<BR>
     * 管理者タイプテーブル.証券会社コード = 引数.管理者.証券会社コード<BR>
     * 管理者タイプテーブル.権限レベル = 引数.管理者.権限レベル<BR>
     * <BR>
     * コピーの過程で何らかの例外が発生した場合、<BR>
     * 致命的なシステムエラーをthrowする。<BR>
     * <BR>
     * @@param l_administratorRow - 管理者<BR>
     * @@param l_loginInfo - ログイン情報<BR>
     * @@return webbroker3.login.message.WEB3AdministratorInfo
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2F0B20019
     */
    protected WEB3AdministratorInfo getAdministratorInfo(
        AdministratorRow l_administratorRow,
        LoginInfo l_loginInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdministratorInfo()";
        log.entering(STR_METHOD_NAME);

        WEB3AdministratorInfo l_administratorInfo = new WEB3AdministratorInfo();
        try
        {
            //管理者タイプ行の取得
            AdministratorTypeRow l_administratorTypeRow =
                AdministratorTypeDao.findRowByPk(
                    l_administratorRow.getInstitutionCode(),
                    l_administratorRow.getPermissionLevel());

            //管理者ＩＤ = administratorRow.管理者ＩＤ
            l_administratorInfo.administratorID =
                l_administratorRow.getAdministratorId();
            //管理者コード = administratorRow.管理者コード
            l_administratorInfo.administratorCode =
                l_administratorRow.getAdministratorCode();
            //xTradeユーザ名 = loginInfo.getUserName()
            l_administratorInfo.xTradeUsername = l_loginInfo.getUsername();
            //管理者名 = administratorRow.管理者名
            l_administratorInfo.administratorName = l_administratorRow.getName();
            //権限レベル = administratorRow.権限レベル
            l_administratorInfo.permissionLevel =
                l_administratorRow.getPermissionLevel();
            //最終ログイン時刻 = loginInfo.getAttributes().get(ログイン属性名.最終ログイン時刻)
            String l_lastLogin =
                (String) l_loginInfo.getAttributes().get(
                    WEB3LoginAttributeKeyDef.LAST_LOGIN);
            l_administratorInfo.lastLoginTime = null;
            if (l_lastLogin != null)
            {
                try
                {
                    l_administratorInfo.lastLoginTime =
                        WEB3PasswordUtility.loginAttributeDateFormat.parse(
                            l_lastLogin);
                }
                catch (ParseException pex)
                {
                }
            }
            log.debug(STR_METHOD_NAME + " .... lastLogin = " + l_administratorInfo.lastLoginTime);

            //全部店許可フラグ = (*1)管理者タイプ行.全部店許可フラグ
            if (BooleanEnum.TRUE.equals(l_administratorTypeRow.getAllBranchPermissionFlag()))
            {
                l_administratorInfo.allBranchPermissionFlag = true;
            }
            else
            {
                l_administratorInfo.allBranchPermissionFlag = false;
            }
            // DIR管理者フラグ = (*1)管理者タイプ行.DIR管理者フラグ
            l_administratorInfo.dirAdminFlag = 
                String.valueOf(l_administratorTypeRow.getDirAdminFlag());

        }
        catch (Exception ex)
        {
            log.error(STR_METHOD_NAME, ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage() + " .... ここで例外が発生する事は有り得ない筈",
                ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_administratorInfo;
    }

    /**
     * (get会社情報)<BR>
     * 会社（DB:証券会社）のデータをコピーして<BR>
     * レスポンス用の会社情報を作成する。<BR>
     * コピーの過程で何らかの例外が発生した場合<BR>
     * 　@　@致命的なシステムエラーをthrowする。<BR>
     * @@param l_institutionRow - 証券会社<BR>
     * @@return webbroker3.login.message.WEB3InstitutionInfo
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B5A1033C
     */
    protected WEB3InstitutionInfo getInstitutionInfo(
        InstitutionRow l_institutionRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInstitutionInfo()";
        log.entering(STR_METHOD_NAME);

        WEB3InstitutionInfo l_instInfo = new WEB3InstitutionInfo();
        try
        {
            l_instInfo.institutionID = l_institutionRow.getInstitutionId();
            l_instInfo.institutionCode = l_institutionRow.getInstitutionCode();
            l_instInfo.institutionName = l_institutionRow.getInstitutionName();
        }
        catch (Exception ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage() + " .... ここで例外が発生する事は有り得ない筈",
                ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_instInfo;
    }

    /**
     * (get部店情報)<BR>
     * 部店（DB:部店）のデータをコピーして<BR>
     * レスポンス用の部店情報を作成する。<BR>
     * コピーの過程で何らかの例外が発生した場合<BR>
     * 　@　@致命的なシステムエラーをthrowする。<BR>
     * @@param l_branchRow - 部店<BR>
     * @@return webbroker3.login.message.WEB3BranchInfo
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40762E4F02B1
     */
    protected WEB3BranchInfo getBranchInfo(
        BranchRow l_branchRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchInfo()";
        log.entering(STR_METHOD_NAME);

        WEB3BranchInfo l_branchInfo = new WEB3BranchInfo();
        try
        {
            l_branchInfo.branchID = l_branchRow.getBranchId();
            l_branchInfo.branchCode = l_branchRow.getBranchCode();
            l_branchInfo.branchName = l_branchRow.getBranchName();
        }
        catch (Exception ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage() + " .... ここで例外が発生する事は有り得ない筈",
                ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_branchInfo;
    }

    /**
     * (getサービス実施状態)<BR>
     * 会社ID、部店ID、口座IDでサービス実施状態を生成する。<BR><BR>
     * @@param l_institutionRow - 証券会社<BR>
     * @@param l_branchRow - 部店<BR>
     * @@param l_mainAccountRow - 顧客<BR>
     * @@return WEB3ServiceImpState
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4045B47A00D6
     */
    protected WEB3ServiceImpState getServiceImpState(
        InstitutionRow l_institutionRow,
        BranchRow l_branchRow,
        MainAccountRow l_mainAccountRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getServiceImpState()";
        log.entering(STR_METHOD_NAME);

        WEB3ServiceImpState l_serviceImp;
        try
        {
            l_serviceImp =
                new WEB3ServiceImpState(
                    l_institutionRow.getInstitutionId(),
                    l_branchRow.getBranchId(),
                    l_mainAccountRow.getAccountId());
        }
        catch (WEB3BaseException ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw ex;
        }
        catch (Exception ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage() + " .... ここで例外が発生する事は有り得ない筈",
                ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_serviceImp;
    }

    /**
     * (setセッション属性)<BR>
     * セッション・リクエストを生成する。<BR>
     * 引数のセッションID、セッション属性設定リクエストインスタンスを<BR>
     * セッション・リクエストにセットする。<BR>
     * <BR>
     * セッション属性設定ハンドラにディスパッチする。<BR>
     * <BR>
     * セキュリティ・エラーによって失敗した場合<BR>
     * 　@　@返されたエラー情報でWEB3SystemLayerExceptionをthrowする。<BR>
     * それ以外のエラーによって失敗した場合<BR>
     * 　@　@返されたエラー情報でWEB3BusinessLayerExceptionをthrowする。<BR>
     * @@param l_sessionID - xTradeセッションID
     * @@param l_request - セッション属性設定リクエスト
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4045B4EF02CA
     */
    protected void setSessionAttribute(
        String l_sessionID,
        WEB3SetSessionRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setSessionAttribute(String, WEB3SetSessionRequest)";
        log.entering(STR_METHOD_NAME);

        SessionRequest l_sessionReq = new SessionRequest();
        l_sessionReq.session_id = l_sessionID;
        l_sessionReq.request = new Request[1];
        l_sessionReq.request[0] = l_request;

        SessionResponse l_sessionRes =
            (SessionResponse) MessageHandlerDispatcher.Handle(l_sessionReq);
        if (l_sessionRes.getSecurityError() != null)
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... exception: "
                    + l_sessionRes.getSecurityError().toString());
            throw new WEB3SystemLayerException(
                l_sessionRes.getSecurityError(),
                getClass().getName() + "." + STR_METHOD_NAME,
                "セッション属性設定ハンドラへのディスパッチでセキュリティ・エラーが発生");
        }

        WEB3SetSessionResponse l_setSessionRes =
            (WEB3SetSessionResponse) l_sessionRes.response[0];
        if (l_setSessionRes.errorInfo != null)
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... exception: "
                    + l_setSessionRes.errorInfo.toString());
            throw new WEB3BusinessLayerException(
                l_setSessionRes.errorInfo,
                getClass().getName() + "." + STR_METHOD_NAME,
                "セッション属性設定ハンドラでExceptionが発生");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （dispatchログアウト）<BR>
     * ログアウト・リクエストを生成する。<BR>
     * 引数：xTradeセッションIDがnullの場合<BR>
     * 　@　@ログアウト・リクエストをディスパッチに使用するリクエストとする。<BR>
     * そうでない場合<BR>
     * 　@　@セッション・リクエストを生成する。<BR>
     * 　@　@セッション・リクエストに引数：xTradeセッションIDをセットする。<BR>
     * 　@　@セッション・リクエストにログアウト・リクエストをセットする。<BR>
     * 　@　@セッション・リクエストをディスパッチに使用するリクエストとする。<BR>
     * ログアウト・ハンドラにディスパッチする。<BR>
     * <BR>
     * 引数：xTradeセッションIDがnullの場合<BR>
     * 　@　@結果をログアウト・レスポンスとして取得する。<BR>
     * そうでない場合<BR>
     * 　@　@結果をセッション・レスポンスとして取得する。<BR>
     * 　@　@セキュリティ・エラーが発生している場合<BR>
     * 　@　@　@　@返されたエラー情報でWEB3SystemLayerExceptionをthrowする。<BR>
     * 　@　@セッション・レスポンスからログアウト・レスポンスを取得する。<BR>
     * <BR>
     * ログアウト・レスポンスを確認し、失敗している場合は<BR>
     * 　@　@返されたエラー情報でWEB3BusinessLayerExceptionをthrowする。<BR>
     * @@param l_sessionID - xTradeセッションID
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40640EA902ED
     */
    protected void dispatchLogout(String l_sessionID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "dispatchLogout(String)";
        log.entering(STR_METHOD_NAME);

        Request l_request = new WEB3LogoutRequest();
        if (l_sessionID != null)
        {
            SessionRequest l_sessionReq = new SessionRequest();
            l_sessionReq.session_id = l_sessionID;
            l_sessionReq.request = new Request[1];
            l_sessionReq.request[0] = l_request;
            l_request = l_sessionReq;
        }

        WEB3LogoutResponse l_logoutRes = null;
        Response l_response = MessageHandlerDispatcher.Handle(l_request);
        if (l_sessionID == null)
        {
            l_logoutRes = (WEB3LogoutResponse) l_response;
        }
        else
        {
            SessionResponse l_sessionRes = (SessionResponse) l_response;
            if (l_sessionRes.getSecurityError() != null)
            {
                log.debug(
                    STR_METHOD_NAME
                        + " .... exception: "
                        + l_sessionRes.getSecurityError().toString());
                throw new WEB3SystemLayerException(
                    l_sessionRes.getSecurityError(),
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "ログアウト・ハンドラへのディスパッチでセキュリティ・エラーが発生");
            }
            l_logoutRes = (WEB3LogoutResponse) l_sessionRes.response[0];
        }

        if (l_logoutRes.errorInfo != null)
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... exception: "
                    + l_logoutRes.errorInfo.toString());
            throw new WEB3BusinessLayerException(
                l_logoutRes.errorInfo,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ログアウト・ハンドラでExceptionが発生");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 引数で与えられたリクエストを基に業務処理を行い、処理結果をレスポンスに設定<BR>
     * してを返す。<BR>
     * @@param l_request リクエスト
     * @@return 処理結果が設定されたレスポンス
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4057ED630195
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        return null;
    }

    /**
     * (get書面交付管理情)<BR>
     * 書面が交付済か交付未済かの情報を取得する <BR>
     * <BR>
     * １）書面種類管理テーブルを下記条件で検索する。 <BR>
     * 　@　@[条件] <BR>
     * 　@　@証券会社コード = 引数.顧客.証券会社コード <BR>
     * 　@　@部店コード = 引数.顧客.部店コード <BR>
     * <BR>
     * ２）書面種類管理テーブルの件数分ループする。 <BR>
     * <BR>
     * 　@２－１） 書面区分管理テーブルから書面区分を取得する。  <BR>
     * 　@　@　@　@　@[条件] <BR>
     * 　@　@　@　@　@証券会社コード = 引数.顧客.証券会社コード <BR>
     * 　@　@　@　@　@部店コード = 引数.顧客.部店コード <BR>
     * 　@　@　@　@　@書面チェック区分 = ”金商法@” <BR>
     * 　@　@　@　@　@書面通番 = 0 <BR>
     * 　@　@　@　@　@書面種類コード = １）で取得した書面種類 <BR>
     * <BR>
     * 　@　@　@　@　@※レコードが取得できなかった場合は、ループの始めに戻る。 <BR>
     * <BR>
     *   ２－２） 電子鳩銘柄コード管理テーブルから電子鳩銘柄コードを取得する。  <BR>
     * 　@　@　@　@　@[条件] <BR>
     * 　@　@　@　@　@証券会社コード = 引数.顧客.証券会社コード <BR>
     * 　@　@　@　@　@部店コード = 引数.顧客.部店コード <BR>
     * 　@　@　@　@　@書面区分 = ２－１）で取得した書面区分 <BR>
     * 　@　@　@　@　@電子鳩銘柄コードの1～3文字目 = １）で取得した書面種類<BR>
     * 　@　@　@　@　@有効区分 = 「0：valid」 <BR>
     * <BR>
     * 　@　@　@　@　@※レコードが取得できなかった場合は、ループの始めに戻る。 <BR>
     * 　@　@　@　@　@※複数件レコードが取得できた場合は、業務エラー「有効な電子鳩銘<BR>
     * 　@　@　@　@　@柄コードが複数存在します。」の例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  : BUSINESS_ERROR_03005<BR>
     * <BR>
     *   ２－３）以下の何れかに該当する場合、書面交付チェックを行う。  <BR>
     * 　@　@　@　@　@２）で取得した書面種類管理レコードの交付対象が「0：全顧客」  <BR>
     * 　@　@　@　@　@２）で取得した書面種類管理レコードの交付対象が<BR>
     * 　@　@　@　@　@「1：信用開設済顧客」かつ引数.信用取引登録フラグがture <BR>
     * 　@　@　@　@　@２）で取得した書面種類管理レコードの交付対象が<BR>
     * 　@　@　@　@　@「2：先物・オプション開設済顧客」かつ引数.先物OP取引登録が「0：登録なし」以外 <BR>
     * <BR>
     *     ２－３－１） 引数.顧客.is書面交付をコールする。 <BR>
     * 　@　@　@　@　@　@　@　@　@[引数] <BR>
     * 　@　@　@　@　@　@　@　@　@書面区分 = ２－１）で取得した書面区分 <BR>
     * 　@　@　@　@　@　@　@　@　@銘柄コード = ２－２）で取得した電子鳩銘柄コード <BR>
     * <BR>
     *     ２－３－２） （引数.注文経路区分が”リッチ”もしくは”IVR”の場合）かつ <BR>
     * 　@　@　@　@　@　@　@　@　@is書面交付メソッドの戻り値がfalseの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@「書面未交付エラー（ログイン時）。」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@　@tag  : BUSINESS_ERROR_02939<BR>
     * <BR>
     *   ２－４） プロパティセット <BR>
     * 　@　@　@　@　@書面区分 ＝ ２－１）で取得した書面区分 <BR>
     * 　@　@　@　@　@書面種類 ＝ １）で取得した書面種類 <BR>
     * 　@　@　@　@　@電子鳩銘柄コード ＝ ２－２）で取得した電子鳩銘柄コード <BR>
     * 　@　@　@　@　@交付済フラグ <BR>
     * 　@　@　@　@　@is書面交付メソッドの戻り値がfalseの場合、「0：交付未済」 <BR>
     * 　@　@　@　@　@is書面交付メソッドの戻り値がtrueの場合、 「1：交付済」 <BR>
     * 　@　@　@　@　@is書面交付メソッドがコールされなかった場合、「2：交付不要」 <BR>
     * <BR>
     *   ２－５） 書面交付管理情報を一件追加する。 <BR>
     * <BR>
     * ３）生成した書面交付管理情報をリターンする。<BR>
     * @@param l_mainAccountRow - (顧客)<BR>
     * 顧客<BR>
     * @@param l_strOrderRootDiv - (注文経路区分)<BR>
     * 注文経路区分<BR>
     * @@param l_blnMarginRegistFlag - (信用取引登録フラグ)<BR>
     * 信用取引登録フラグ<BR>
     * true：　@登録済み　@false：　@未登録<BR>
     * @@param l_strFutOpTradeRegist - (先物OP取引登録)<BR>
     * 先物OP取引登録<BR>
     * 0：　@登録なし<BR>
     * 1：　@登録済み（OP買建取引）<BR>
     * 2：　@登録済み（先物取引）<BR>
     * 3：　@登録済み（先物／OP取引）<BR>
     * @@return WEB3DocumentDeliverInfoUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3DocumentDeliverInfoUnit[] getDocumentDeliverInfoUnit(
        MainAccountRow l_mainAccountRow,
        String l_strOrderRootDiv,
        boolean l_blnMarginRegistFlag,
        String l_strFutOpTradeRegist) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocumentDeliverInfoUnit(MainAccountRow, String, boolean, String)";
        log.entering(STR_METHOD_NAME);

        //１）書面種類管理テーブルを下記条件で検索する。
        //[条件]
        //証券会社コード = 引数.顧客.証券会社コード
        //部店コード = 引数.顧客.部店コード
        List l_lisDocCategoryManagements = null;

        String l_strQueryDocCategoryManagement = " institution_code = ? and branch_code = ? ";

        Object[] l_queryValueDocCategoryManagements = {
            l_mainAccountRow.getInstitutionCode(), l_mainAccountRow.getBranchCode()};

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisDocCategoryManagements = l_queryProcessor.doFindAllQuery(
                DocCategoryManagementRow.TYPE,
                l_strQueryDocCategoryManagement,
                l_queryValueDocCategoryManagements);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）書面種類管理テーブルの件数分ループする。
        int l_intDocCategoryManagementsLength = l_lisDocCategoryManagements.size();
        //書面交付管理情報
        List l_lisDocumentDeliverInfoUnits = new ArrayList();
        for (int i = 0; i < l_intDocCategoryManagementsLength; i++)
        {
            //２－１） 書面区分管理テーブルから書面区分を取得する。
            //[条件]
            //証券会社コード = 引数.顧客.証券会社コード
            //部店コード = 引数.顧客.部店コード
            //書面チェック区分 = ”金商法@”
            //書面通番 = 0
            //書面種類コード = １）で取得した書面種類
            //※レコードが取得できなかった場合は、ループの始めに戻る。
            StringBuffer l_sbWhereDocDivManagementRow = new StringBuffer();
            l_sbWhereDocDivManagementRow.append(" institution_code = ?");
            l_sbWhereDocDivManagementRow.append(" and branch_code = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_check_div = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_number = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_category = ? ");

            Object[] l_valueDocDivManagementRows = new Object[5];
            DocCategoryManagementRow l_docCategoryManagementRow =
                (DocCategoryManagementRow)l_lisDocCategoryManagements.get(i);
            l_valueDocDivManagementRows[0] = l_mainAccountRow.getInstitutionCode();
            l_valueDocDivManagementRows[1] = l_mainAccountRow.getBranchCode();
            l_valueDocDivManagementRows[2] = WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW;
            l_valueDocDivManagementRows[3] = "0";
            l_valueDocDivManagementRows[4] = l_docCategoryManagementRow.getDocumentCategory();

            List l_lisDocDivManagementRows = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisDocDivManagementRows = l_queryProcessor.doFindAllQuery(
                    DocDivManagementRow.TYPE,
                    l_sbWhereDocDivManagementRow.toString(),
                    l_valueDocDivManagementRows);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //※レコードが取得できなかった場合は、ループの始めに戻る。
            if (l_lisDocDivManagementRows.isEmpty())
            {
                continue;
            }

            //２－２） 電子鳩銘柄コード管理テーブルから電子鳩銘柄コードを取得する。
            //[条件]
            //証券会社コード = 引数.顧客.証券会社コード
            //部店コード = 引数.顧客.部店コード
            //書面区分 = ２－１）で取得した書面区分
            //電子鳩銘柄コードの1～3文字目 = １）で取得した書面種類
            //有効区分 = 「0：valid」
            //※レコードが取得できなかった場合は、ループの始めに戻る。
            StringBuffer l_sbWhereBatoProductManagement = new StringBuffer();
            l_sbWhereBatoProductManagement.append(" institution_code = ? ");
            l_sbWhereBatoProductManagement.append(" and branch_code = ? ");
            l_sbWhereBatoProductManagement.append(" and document_div = ? ");
            l_sbWhereBatoProductManagement.append(" and substr(bato_product_code, 1, 3) = ? ");
            l_sbWhereBatoProductManagement.append(" and valid_flag = ? ");

            Object[] l_valueBatoProductManagements = new Object[5];
            DocDivManagementRow l_docDivManagementRow =
                (DocDivManagementRow)l_lisDocDivManagementRows.get(0);
            l_valueBatoProductManagements[0] = l_mainAccountRow.getInstitutionCode();
            l_valueBatoProductManagements[1] = l_mainAccountRow.getBranchCode();
            l_valueBatoProductManagements[2] = l_docDivManagementRow.getDocumentDiv();
            l_valueBatoProductManagements[3] = l_docCategoryManagementRow.getDocumentCategory();
            l_valueBatoProductManagements[4] = WEB3ValidFlagDef.VALID;

            List l_lisBatoProductManagements = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisBatoProductManagements = l_queryProcessor.doFindAllQuery(
                    BatoProductManagementRow.TYPE,
                    l_sbWhereBatoProductManagement.toString(),
                    l_valueBatoProductManagements);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //※レコードが取得できなかった場合は、ループの始めに戻る。
            if (l_lisBatoProductManagements.isEmpty())
            {
                continue;
            }

            //※複数件レコードが取得できた場合は、
            //業務エラー「有効な電子鳩銘柄コードが複数存在します。」の例外をスローする。
            if (l_lisBatoProductManagements.size() > 1)
            {
                log.debug("有効な電子鳩銘柄コードが複数存在します。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "有効な電子鳩銘柄コードが複数存在します。");
            }

            BatoProductManagementRow l_batoProductManagementRow =
                (BatoProductManagementRow)l_lisBatoProductManagements.get(0);

            boolean l_blnIsDocumentDelivery = false;
            //is書面交付メソッドがコールされなかった
            boolean l_blnIsDocumentDeliveryCallFlag = false;
            //２－３）以下の何れかに該当する場合、書面交付チェックを行う。
            //  ２）で取得した書面種類管理レコードの交付対象が「0：全顧客」
            //  ２）で取得した書面種類管理レコードの交付対象が「1：信用開設済顧客」
            //    かつ引数.信用取引登録フラグがture
            //  ２）で取得した書面種類管理レコードの交付対象が「2：先物・オプション開設済顧客」
            //    かつ引数.先物OP取引登録が「0：登録なし」以外
            if (WEB3DeliveryTargetDef.ALL_ACCOUNT.equals(l_docCategoryManagementRow.getDeliveryTarget())
                || (WEB3DeliveryTargetDef.MARGIN_OPENED_ACCOUNT.equals(l_docCategoryManagementRow.getDeliveryTarget())
                    && l_blnMarginRegistFlag)
                || (WEB3DeliveryTargetDef.FUTURE_OPTION_OPENED_ACCOUNT.equals(
                    l_docCategoryManagementRow.getDeliveryTarget())
                    && !WEB3FutOpTradeRegistDef.NOT_REGIST.equals(l_strFutOpTradeRegist)))
            {
                try
                {
                    //get 顧客オブジェクト
                    WEB3GentradeMainAccount l_genMainAccount =
                        new WEB3GentradeMainAccount(l_mainAccountRow.getAccountId());

                    //２－３－１） 引数.顧客.is書面交付をコールする。
                    //   [引数]
                    //      書面区分 = ２－１）で取得した書面区分
                    //      銘柄コード = ２－２）で取得した電子鳩銘柄コード
                    l_blnIsDocumentDelivery = l_genMainAccount.isDocumentDelivery(
                        l_docDivManagementRow.getDocumentDiv(),
                        l_batoProductManagementRow.getBatoProductCode());

                    l_blnIsDocumentDeliveryCallFlag = true;
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //２－３－２） （引数.注文経路区分が”リッチ”もしくは”IVR”の場合）かつ
                // is書面交付メソッドの戻り値がfalseの場合、「書面未交付エラー（ログイン時）。」の例外をスローする。
                if ((WEB3OrderRootDivDef.RICH_CLIENT.equals(l_strOrderRootDiv)
                    || WEB3OrderRootDivDef.IVR.equals(l_strOrderRootDiv))
                    && !l_blnIsDocumentDelivery)
                {
                    log.debug("書面の交付がされてないため、ログインできません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02939,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "書面の交付がされてないため、ログインできません。");
                }
            }

            //２－４） プロパティセット
            WEB3DocumentDeliverInfoUnit l_documentDeliverInfoUnit = new WEB3DocumentDeliverInfoUnit();
            //書面区分 ＝ ２－１）で取得した書面区分
            l_documentDeliverInfoUnit.documentDiv = l_docDivManagementRow.getDocumentDiv();
            //書面種類 ＝ １）で取得した書面種類
            l_documentDeliverInfoUnit.documentCategory = l_docCategoryManagementRow.getDocumentCategory();
            //電子鳩銘柄コード ＝ ２－２）で取得した電子鳩銘柄コード
            l_documentDeliverInfoUnit.batoProductCode = l_batoProductManagementRow.getBatoProductCode();
            //交付済フラグ
            if (l_blnIsDocumentDeliveryCallFlag)
            {
                if (l_blnIsDocumentDelivery)
                {
                    //  is書面交付メソッドの戻り値がtrueの場合、 「1：交付済」
                    l_documentDeliverInfoUnit.deliverFlag = WEB3DeliverFlagDivDef.DELIVERY;
                }
                else
                {
                    //  is書面交付メソッドの戻り値がfalseの場合、「0：交付未済」
                    l_documentDeliverInfoUnit.deliverFlag = WEB3DeliverFlagDivDef.UNDELIVERY;
                }
            }
            else
            {
                //  is書面交付メソッドがコールされなかった場合、「2：交付不要」
                l_documentDeliverInfoUnit.deliverFlag = WEB3DeliverFlagDivDef.NO_DELIVERY;
            }

            //２－５） 書面交付管理情報を一件追加する。
            l_lisDocumentDeliverInfoUnits.add(l_documentDeliverInfoUnit);
        }
        //３）生成した書面交付管理情報をリターンする。
        WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnits =
            new WEB3DocumentDeliverInfoUnit[l_lisDocumentDeliverInfoUnits.size()];
        l_lisDocumentDeliverInfoUnits.toArray(l_documentDeliverInfoUnits);

        log.exiting(STR_METHOD_NAME);
        return l_documentDeliverInfoUnits;
    }

    /**
     * (get口座開設区分)<BR>
     * 口座開設区分情報を取得する。<BR>
     * <BR>
     * １）　@口座開設区分情報取得<BR>
     * 　@１－１）　@口座開設区分テーブルを以下の条件で検索する。<BR>
     * 　@　@　@　@　@　@[条件]<BR>
     * 　@　@　@　@　@　@口座ID = 引数.口座ID<BR>
     * <BR>
     * 　@１－２）　@検索結果が取得できなかった場合、nullを返却する。<BR> 
     * <BR>
     * ２）　@ArrayListを生成する。<BR> 
     * <BR>
     * ３）　@１）の戻り値について、以下の処理を繰り返す。<BR> 
     * <BR>
     * 　@３－１）　@口座開設区分オブジェクトを生成する。<BR>
     * <BR>
     * 　@３－２）　@プロパティセット<BR>
     * 　@　@　@　@　@　@口座開設区分.口座種別 ＝ １）で取得した口座開設区分行. 口座種別<BR>
     * 　@　@　@　@　@　@口座開設区分.口座開設区分 ＝ １）で取得した口座開設区分行. 口座開設区分<BR>
     * <BR>
     * 　@３－３）　@生成した口座開設区分をArrayListに追加する。<BR> 
     * <BR>
     * ４）　@生成したArrayList.toArray()の戻り値を返却する。<BR> 
     * @@param l_lngAccountID - (口座ID)<BR>
     * 口座ID<BR>
     * @@return WEB3AccOpenDivUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccOpenDivUnit[] getAccOpenDivUnit(
        long l_lngAccountID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccOpenDivUnit(long)";
        log.entering(STR_METHOD_NAME);

        WEB3AccOpenDivUnit[] l_accOpenDivUnits = null;

        //口座開設区分テーブルを以下の条件で検索する。
        //[条件]:口座ID = 引数.口座ID
        String l_strQueryString = "account_id = ?";
        Object[] l_objWhereValues = new Object[]{new Long(l_lngAccountID)};
        List l_lisAccOpeDivRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpeDivRecords =
                l_queryProcessor.doFindAllQuery(
                    AccOpenDivRow.TYPE,
                    l_strQueryString.toString(),
                    l_objWhereValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //検索結果が取得できなかった場合、nullを返却する。
        if (l_lisAccOpeDivRecords.size() != 0)
        {
            //ArrayListを生成する。
            List l_lisAccOpenDivUnitList = new ArrayList();
            
            //１）の戻り値について、以下の処理を繰り返す。
            Iterator l_AccOpeDivIterator = l_lisAccOpeDivRecords.iterator();
            WEB3AccOpenDivUnit l_accOpenDivUnit = null;
            AccOpenDivRow l_accOpDivRow = null;
            while (l_AccOpeDivIterator.hasNext())
            {
                 l_accOpDivRow = (AccOpenDivRow)l_AccOpeDivIterator.next();

                //口座開設区分オブジェクトを生成する。
                l_accOpenDivUnit = new WEB3AccOpenDivUnit();

                //プロパティセット
                //口座開設区分.口座種別 ＝ １）で取得した口座開設区分行. 口座種別
                //口座開設区分.口座開設区分 ＝ １）で取得した口座開設区分行. 口座開設区分
                l_accOpenDivUnit.accType = l_accOpDivRow.getAccType();
                l_accOpenDivUnit.accOpenDiv = l_accOpDivRow.getAccOpenDiv();

                //生成した口座開設区分をArrayListに追加する。
            	l_lisAccOpenDivUnitList.add(l_accOpenDivUnit);
            }

            //生成したArrayList.toArray()の戻り値を返却する。
            l_accOpenDivUnits = new WEB3AccOpenDivUnit[l_lisAccOpenDivUnitList.size()];
            l_lisAccOpenDivUnitList.toArray(l_accOpenDivUnits);
        }

        //生成した口座開設区分をArrayListに追加する。
        //検索結果が取得できなかった場合、nullを返却する。
        return l_accOpenDivUnits;
    }
}@


1.1
log
@*** empty log message ***
@
text
@d2 1
a2 1
Copyright           : (株)大和総研 証券ソリューションシステム第二部
d4 1
a4 1
Author Name         : Daiwa Institute of Research
d22 1
d71 2
d1027 9
d1544 50
@

