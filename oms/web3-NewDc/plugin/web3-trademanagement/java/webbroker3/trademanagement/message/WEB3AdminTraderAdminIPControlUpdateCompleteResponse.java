head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.19.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlUpdateCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP登録情報変更完了レスポンス(WEB3AdminTraderAdminIPControlUpdateCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.004
*/
package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (管理者・ログイン拒否IP登録情報変更完了レスポンス)<BR>
 * 管理者・ログイン拒否IP登録情報変更完了レスポンスクラス。<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlUpdateCompleteResponse
    extends WEB3AdminTraderAdminIPControlUpdateCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_update_complete";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221827L;

    /**
     * @@roseuid 48D75CD7026F
     */
    public WEB3AdminTraderAdminIPControlUpdateCompleteResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTraderAdminIPControlUpdateCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
