head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.19.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP登録リクエスト(WEB3AdminTraderAdminIPControlRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・ログイン拒否IP登録リクエスト)<BR>
 * 管理者・ログイン拒否IP登録リクエストクラス。<BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlRegistInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_regist_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221808L;

    /**
     * @@roseuid 48D75CD7002D
     */
    public WEB3AdminTraderAdminIPControlRegistInputRequest()
    {

    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminIPControlRegistInputResponse(this);
    }
}
@
