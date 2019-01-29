head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutValidityDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログインセッション　@有効フラグ定数定義インタフェイス(WEB3AdminFPTForceLogoutValidityDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 孫(FLJ) 新規作成
*/
package webbroker3.docadmin.define;

/**
 * ログインセッション　@有効フラグ定数定義インタフェイス<BR>
 * 
 * @@author 孫
 * @@version 1.0
 */
public interface WEB3AdminFPTForceLogoutValidityDef 
{
	//ログインセッション　@有効フラグ　@1:無効
    public static final Integer INT_VALIDITY_INVALID_USER_LOGOUT = new Integer(1);
	//ログインセッション　@有効フラグ　@0:有効
    public static final Integer INT_VALIDITY_VALID_USER_LOGOUT = new Integer(0);
}
@
