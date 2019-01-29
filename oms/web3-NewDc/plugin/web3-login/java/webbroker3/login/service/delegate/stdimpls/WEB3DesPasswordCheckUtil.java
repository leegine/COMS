head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3DesPasswordCheckUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright : (株)大和総研 証券ソリューションシステム第二部 
 * File Name : 新パスワードチェックUTIL(WEB3DesPasswordCheckUtil.java) 
 * Author Name : Daiwa Institute of Research 
 * Revesion History : 2007/07/25 孫家全(FLJ) 新規作成
 */

package webbroker3.login.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginPlugin;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.*;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 復号化可能な方法@で暗号化されたパスワードで認証
 * 
 * @@author  孫
 * @@version 1.0
 */
public class WEB3DesPasswordCheckUtil
{

    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3DesPasswordCheckUtil.class);

    public static final String DES_PWD_CHECK_KEY = "despwdcheck.";

    private static WEB3DesPasswordCheckUtil _instance = new WEB3DesPasswordCheckUtil();

    private WEB3DesPasswordCheckUtil()
    {
    }

    public static WEB3DesPasswordCheckUtil getInstance()
    {
        return _instance;
    }

    /**
     * 復号化可能な方法@で暗号化されたパスワードで認証 
     * ①@新パスワードチェック実施会社属性をチェック、実施しない場合、メソッド正常終了。
     * ②暗号化されたパスワード（LOGIN_ATTRIBUTE.WEB3_ENCRYPTED_PASSWORD）を取得、取得できない場合(Null)、エラーとする。
     * ③入力パスワードを暗号化して、②で取得したパスワードと比較する。 比較一致でない場合、エラーとする。
     * 
     * 以上の操作でエラーがある場合、ログインエラー情報を更新して（エラー回数更新）、ログイン認証異常をスローする
     * エラーなしの場合、メソッド正常終了。
     * 
     * @@param iCode
     *            証券会社コード
     * @@param l_pwd
     *            入力パスワード
     * @@param l_loginInfo
     *            ログイン情報
     * @@throws WEB3BusinessLayerException
     *             暗号化パスワード取得できない場合、或いはパスワード不一致の場合、スローする
     */
    public void checkDesPassword(String l_iCode,String l_pwd, LoginInfo l_loginInfo)
            throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "checkDesPassword(String,String,Map)";
        log.entering(STR_METHOD_NAME);
        
        //①@新パスワードチェック実施会社属性をチェック、実施しない場合、メソッド正常終了。
        String l_checkFlg = GtlUtils.getTradingSystem().getPreference(DES_PWD_CHECK_KEY + l_iCode);
        log.debug(l_iCode+" checkFlg:"+l_checkFlg);
        
        if ("true".equals(l_checkFlg))
        {
            //②暗号化されたパスワード（LOGIN_ATTRIBUTE.WEB3_ENCRYPTED_PASSWORD）を取得、取得できない場合(Null)、エラーとする。
            Object l_objEncryptedPassword = l_loginInfo.getAttributes().get(WEB3LoginAttributeKeyDef.PASSWORD);
            log.debug("pwd of attributes:"+l_objEncryptedPassword);

            //③入力パスワードを暗号化して、②で取得したパスワードと比較する。 比較一致でない場合、エラーとする。
            String l_encryptPwd = WEB3AcceptPasswordCheckUtil.getInstance().encrypt(l_pwd);
            log.debug("pwd encrypted:"+l_encryptPwd);
            
            //エラーがある場合
            if (l_objEncryptedPassword == null || !l_objEncryptedPassword.equals(l_encryptPwd))
            {
                //ログインエラー情報を更新して（エラー回数更新）
                try
                {
                    updateBadPasswordInfo(l_loginInfo);
                }
                catch (DataException e)
                {
                    log.error("Error while updating bad password info.", e);
                }
                
                //ログイン認証異常をスローする
                log.debug(STR_METHOD_NAME + " .... login failed(authenticate).");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_90203, getClass().getName() + "."
                        + STR_METHOD_NAME, "顧客(xNAME: " + l_loginInfo.getUsername() + ") 認証エラー.");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ユーザのログイン情報のログインエラー回数を更新する。
     * 
     * ①@ログイン情報からログインレコードを取得 
     * ②既存エラー回数に１をプラスして、エラー回数項目に設定。（既存エラー回数はnullの場合、１を設定）
     * ③DBを更新する
     * 
     * @@param l_loginInfo
     *            ログイン情報
     * @@return int 更新行数
     * @@throws DataException
     *             DBエラーが発生する場合
     */
    private void updateBadPasswordInfo(LoginInfo l_loginInfo) throws DataException
    {
        final String STR_METHOD_NAME = "updateBadPasswordInfo(String)";
        log.entering(STR_METHOD_NAME);

        final QueryProcessor qp = Processors.getDefaultProcessor();

        //①@ユーザ名からログイン情報を取得
//        LoginUsernameRow l_usr = LoginUsernameDao.findRowByPk(l_uname);
//        LoginRow loginRow = LoginDao.findRowByPk(l_usr.getLoginId());
        LoginRow loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());
        
        final LoginParams loginParams = new LoginParams(loginRow);

        //②既存エラー回数に１をプラスして、エラー回数項目に設定。（既存エラー回数はnullの場合、１を設定）
        Date l_now = new Date();
        Integer failureCount = null;
        if (loginRow.getFailureCountIsNull())
        {
            failureCount = new Integer(1);
        }
        else
        {
            if(loginRow.getLastFailureTimestamp()==null || l_now.getTime() > loginRow.getLastFailureTimestamp().getTime() + getLockoutIntervalMillis(loginRow.getTypeId()))
            {
                failureCount = new Integer(1);               
            }
            else
            {
                failureCount = new Integer(loginRow.getFailureCount() + 1);
            }
        }
        loginParams.setFailureCount(failureCount);
        loginParams.setLastFailureTimestamp(l_now);

        //③DBを更新する
        qp.doTransaction(1, new TransactionCallback()
        {
            public Object process() throws DataQueryException, DataNetworkException
            {
                qp.lockAccount(loginParams.getLoginId(), true);
                int updated = qp.doUpdateQuery(loginParams);
                return new Integer(updated);
            }

        });

        log.exiting(STR_METHOD_NAME);
    }
    
    private long getLockoutIntervalMillis(long loginTypeId)
        throws DataException
    {
        LoginTypeRow loginTypeRow = LoginTypeDao.findRowByPk(loginTypeId);
        
        return OpLoginPlugin.getLockoutIntervalMillis(loginTypeRow.getTypeName());
    }


}@
