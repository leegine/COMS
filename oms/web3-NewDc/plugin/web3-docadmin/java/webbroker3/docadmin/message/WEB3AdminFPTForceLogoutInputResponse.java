head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・書面未承諾 強制ログアウト入力レスポンス(WEB3AdminFPTForceLogoutInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 許(FLJ) 新規作成
*/
package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者 書面未承諾 強制ログアウト入力レスポンス
 * <BR>
 * @@author Kyo
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_fpt_force_logout_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200803181606L;
    
    /**
     * 書面区分管理情報一覧
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;
    
    /**
     * @@roseuid 47DF467700A4
     */
    public WEB3AdminFPTForceLogoutInputResponse() 
    {
     
    }
    
    /**
    *
    * @@param l_request WEB3GenRequest
    */
    public WEB3AdminFPTForceLogoutInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
