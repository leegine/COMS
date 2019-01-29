head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP一覧レスポンス(WEB3AdminTraderAdminIPControlListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・ログイン拒否IP一覧レスポンス)<BR>
 * 管理者・ログイン拒否IP一覧レスポンスクラス。<BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221751L;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号。<BR>
     */
    public String pageIndex;

    /**
     * (総ページ数)<BR>
     * 総ページ数。<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 総レコード数。<BR>
     */
    public String totalRecords;

    /**
     * (ログイン拒否IP一覧)<BR>
     */
    public WEB3AdminTraderAdminIPControlReferenceUnit[] ipControlList;

    /**
     * @@roseuid 48D75CD603D6
     */
    public WEB3AdminTraderAdminIPControlListResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTraderAdminIPControlListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
