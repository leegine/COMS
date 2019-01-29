head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlRegistCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP登録完了レスポンス(WEB3AdminTraderAdminIPControlRegistCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・ログイン拒否IP登録完了レスポンス)<BR>
 * 管理者・ログイン拒否IP登録完了レスポンスクラス。<BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlRegistCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_regist_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221805L;

    /**
     * @@roseuid 48D75CD70117
     */
    public WEB3AdminTraderAdminIPControlRegistCompleteResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTraderAdminIPControlRegistCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
