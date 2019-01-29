head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者 書面未承諾 強制ログアウトメインレスポンス(WEB3AdminFPTForceLogoutMainResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 孫(FLJ) 新規作成
*/
package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * 管理者 書面未承諾 強制ログアウトメインレスポンス<BR>
 * 
 * @@author 孫
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutMainResponse extends WEB3BackResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_fpt_force_logout_main";
	
    /**
     * コンストラクタ
     * 
     * @@roseuid 47DF4677017E
     */
    public WEB3AdminFPTForceLogoutMainResponse() 
    {
     
    }
}
@
