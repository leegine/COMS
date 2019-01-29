head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlDeleteCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP登録情報削除完了レスポンス(WEB3AdminTraderAdminIPControlDeleteCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (管理者・ログイン拒否IP登録情報削除完了レスポンス)<BR>
 * 管理者・ログイン拒否IP登録情報削除完了レスポンスクラス。<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlDeleteCompleteResponse
    extends WEB3AdminTraderAdminIPControlUpdateCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_delete_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221736L;

    /**
     * @@roseuid 48D75CD7032B
     */
    public WEB3AdminTraderAdminIPControlDeleteCompleteResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTraderAdminIPControlDeleteCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
