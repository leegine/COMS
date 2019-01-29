head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlUpdateInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP登録情報変更レスポンス(WEB3AdminTraderAdminIPControlUpdateInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (管理者・ログイン拒否IP登録情報変更レスポンス)<BR>
 * 管理者・ログイン拒否IP登録情報変更レスポンスクラス。<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlUpdateInputResponse
    extends WEB3AdminTraderAdminIPControlUpdateCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_update_input";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221814L;

    /**
     * @@roseuid 48D75CD70175
     */
    public WEB3AdminTraderAdminIPControlUpdateInputResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTraderAdminIPControlUpdateInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
