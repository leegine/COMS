head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptPasswordCheckServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客パスワードチェックサービス(WEB3AcceptPasswordCheckServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/10/28 齋藤　@栄三(FLJ) 新規作成
Revesion History    : 2006/03/24 劉斌(FLJ)　@IVR認証初期パスワード対応
*/
package webbroker3.login.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.PasswordUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.login.message.WEB3AcceptPasswordCheckRequest;
import webbroker3.login.message.WEB3AcceptPasswordCheckResponse;
import webbroker3.login.service.delegate.WEB3AcceptPasswordCheckService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;


/**
 * (顧客パスワードチェックサービス)<BR>
 * <BR>
 * @@author      Eizo Saito(FLJ)
 * @@version     1.00
 */
public class WEB3AcceptPasswordCheckServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3AcceptPasswordCheckService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AcceptPasswordCheckServiceImpl.class);

    /**
     * コンストラクタ
     */
    public WEB3AcceptPasswordCheckServiceImpl()
    {
        super();
    }

    /**
     * 初期化をする。<BR>
     * <BR>
     * リクエストからxTradeユーザ名を取得する。<BR>
     * 　@　@※nullの場合、エラーとする。<BR>
     * <BR>
     * リクエストから顧客パスワードを取得する。<BR>
     * 　@　@※nullの場合、エラーとする。<BR>
     * <BR>
     * login_usernameテーブルからレコードを取得する。<BR>
     * 　@検索条件<BR>
     * 　@　@・username ＝ xTradeユーザ名
     * 　@　@※レコードを取得できない場合、エラーとする。<BR>
     * <BR>
     * loginAttributeテーブルからレコードを取得する。<BR>
     * 　@検索条件<BR>
     * 　@　@・LOGIN_ID ＝ login_username.login_id
     * 　@　@・ATTRIBUTE_NAME＝WEB3LoginAttributeKeyDef.INITIAL_PASSWORD
     * 　@　@※レコードを取得できない場合、エラーとする。<BR>
     * <BR>
     * 顧客パスワードとDBのパスワードが一致するか判定する。<BR>
     * 　@顧客パスワードを暗号化した内容とLOGIN_ATTRIBUTE.ATTRIBUTE_VALUEを比較する。<BR>
     * <BR>
     * 判定結果でレスポンスを作成し、処理を終了する。<BR>
     * <BR>
     * 処理の過程で例外が発生した場合、次の様に振舞う。<BR>
     * WEB3BaseExceptionが発生した場合<BR>
     * 　@　@catchした例外をそのままthrowする。<BR>
     * DataExceptionが発生した場合<BR>
     * 　@　@DBアクセス失敗としてシステムエラーをthrowする。<BR>
     * それ以外の例外が発生した場合<BR>
     * 　@　@有り得ない異常として致命的なシステムエラーをthrowする。<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AcceptPasswordCheckRequest l_passwordCheckReq = (WEB3AcceptPasswordCheckRequest) l_request;
        WEB3AcceptPasswordCheckResponse l_passwordCheckRes = null;
        try
        {
            //1)リクエスト情報を取得する。
            String l_strInstitutionCode = l_passwordCheckReq.institutionCode;
            String l_strBranchCode = l_passwordCheckReq.branchCode;
            String l_strAcceptCode = l_passwordCheckReq.acceptCode;
            String l_strAccountId = l_passwordCheckReq.account_id;
            //2)xTradeユーザ名を取得する。
            String l_strUserName = l_passwordCheckReq.xTradeUsername;
            //3)顧客パスワードを取得する。
            String l_strPassword = l_passwordCheckReq.acceptPassword;

            log.debug(
                STR_METHOD_NAME
                    + " .... institution = "
                    + l_strInstitutionCode
                    + ", branch = "
                    + l_strBranchCode
                    + ", accept = "
                    + l_strAcceptCode
                    + ", accountid(affinity) = "
                    + l_strAccountId
                    + ", xTradeUsername = "
                    + l_strUserName
                    + ", password = "
                    + l_strPassword);

            //xTradeユーザ名チェック
            if(l_strUserName == null || l_strUserName.length() == 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "WEB3AcceptPasswordCheckRequest.xTradeUsername = " + l_strUserName);
            }

            //顧客パスワードチェック
            if(l_strPassword == null || l_strPassword.length() == 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "WEB3AcceptPasswordCheckRequest.acceptPassword = " + l_strPassword);
            }


            //4)login_usernameから１レコードを取得する。
            LoginUsernameRow l_userNameRow = LoginUsernameDao.findRowByPk(l_strUserName);

            //5)loginAttributeから１レコードを取得する。
            LoginAttributeRow l_loginAttributeRow = LoginAttributeDao.findRowByPk(
                l_userNameRow.getLoginId(), WEB3LoginAttributeKeyDef.INITIAL_PASSWORD);

            //6)パスワードを比較する。
            String l_strHashedInitPasswordFromDb = l_loginAttributeRow.getAttributeValue();

            boolean l_isCertified = false;


            //イニシャルパスワードと比較する
            String l_encryptedPwd = WEB3AcceptPasswordCheckUtil.getInstance().encrypt(
                l_strPassword);
            l_isCertified = l_encryptedPwd.equals(l_strHashedInitPasswordFromDb);

            //レスポンス生成
            l_passwordCheckRes = (WEB3AcceptPasswordCheckResponse)l_passwordCheckReq.createResponse();

            //7)判定結果をレスポンスに入れる
            l_passwordCheckRes.certifiedPasswordFlg = l_isCertified;

        }
        catch (WEB3BaseException ex)
        {
            throw ex;
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
        return l_passwordCheckRes;
    }

}
@
