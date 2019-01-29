head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者 書面未承諾 強制ログアウト一件サービス(WEB3AdminFPTForceLogoutUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 孫(FLJ) 新規作成
*/
package webbroker3.docadmin.service.delegate;

import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionRow;


/**
 * 管理者 書面未承諾 強制ログアウト一件サービス
 * 
 * @@author 孫
 * @@version 1.0
 */
public interface WEB3AdminFPTForceLogoutUnitService extends Service
{
    
    /**
     * this.ログインセッションRowのセッションを無効にする。
     * 
     * シーケンス図 
     * 「（管理者 書面未承諾 強制ログアウト一件サービス）logout」参照。
     * @@param l_loginSessionRow - ログインセッションRow
     * @@roseuid 47DB263C00BC
     */
    public void logout(LoginSessionRow l_loginSessionRow) throws WEB3BaseException;
}
@
