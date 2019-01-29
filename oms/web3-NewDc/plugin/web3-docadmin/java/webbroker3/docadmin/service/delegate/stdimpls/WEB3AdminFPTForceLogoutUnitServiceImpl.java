head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者 書面未承諾 強制ログアウト一件サービスImpl(WEB3AdminFPTForceLogoutUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 孫(FLJ) 新規作成
*/
package webbroker3.docadmin.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.define.WEB3AdminFPTForceLogoutValidityDef;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutUnitService;

import webbroker3.util.WEB3LogUtility;


/**
 * 管理者 書面未承諾 強制ログアウト一件サービスImpl
 * 
 * @@author 孫
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutUnitServiceImpl implements WEB3AdminFPTForceLogoutUnitService 
{
	
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutUnitServiceImpl.class);
    
    /**
     * @@roseuid 47DF46770017
     */
    public WEB3AdminFPTForceLogoutUnitServiceImpl() 
    {
     
    }
    
    /**
     * this.ログインセッションRowのセッションを無効にする。
     * 
     * シーケンス図 
     * 「（管理者 書面未承諾 強制ログアウト一件サービス）logout」参照。
     * @@param l_loginSessionRow - ログインセッションRow
     * @@roseuid 47D664AD026D
     */
    public void logout(LoginSessionRow l_loginSessionRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "logout(LoginSessionRow)";
        log.entering(STR_METHOD_NAME);
    	
    	//ログインセッションParamsを生成する。
    	//[引数]
    	//arg0：　@引数.ログインセッションRow
    	LoginSessionParams l_params = new LoginSessionParams(l_loginSessionRow);
     
    	//有効フラグを設定する。
    	//[引数]
    	//arg0：　@無効
    	l_params.setValidity(WEB3AdminFPTForceLogoutValidityDef.INT_VALIDITY_INVALID_USER_LOGOUT.intValue());
    	
    	//更新時間を設定する。
    	//[引数]
    	//arg0：　@new Date()
    	l_params.setLastUpdate(new Date());
    	
    	try {
			
    		final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

			//ロックする。
			//[引数]
			//arg0：　@ログインセッション.ログインId
    		//arg1:   true  
			l_queryProcesser.lockAccount(l_loginSessionRow.getLoginId(),true);
			
	    	//ログインを無効にする。
	    	//[引数]
	    	//arg0：　@生成したLoginSessionParams
			l_queryProcesser.doUpdateQuery(l_params);
			
		} catch (Exception l_ex) {
	           log.exiting(STR_METHOD_NAME);
	           throw new WEB3BaseException(
	               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
	               this.getClass().getName() + "." + STR_METHOD_NAME,
	               l_ex.getMessage(), l_ex);
		}
    	
		log.exiting(STR_METHOD_NAME);
    }
}
@
