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
filename	WEB3AdminFPTForceLogoutCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・書面未承諾 強制ログアウト完了レスポンス(WEB3AdminFPTForceLogoutCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 許(FLJ) 新規作成
*/
package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者 書面未承諾 強制ログアウト完了レスポンス
 * <BR>
 * @@author Kyo
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_fpt_force_logout_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200803181606L;
    
    /**
     * @@roseuid 47DF4677022A
     */
    public WEB3AdminFPTForceLogoutCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    protected WEB3AdminFPTForceLogoutCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
