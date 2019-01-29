head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminLoginHistoryListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン処理一覧検索結果レスポンス(WEB3AdminTraderAdminLoginHistoryListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.005,007
*/

package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・ログイン処理一覧検索結果レスポンス)<BR>
 * 管理者・ログイン処理一覧検索結果レスポンスクラス。<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTraderAdminLoginHistoryListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_login_history_list";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221740L;

    /**
     * (総ページ数)<BR>
     * 総ページ数。<BR>
     */
    public String totalPages;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号。<BR>
     */
    public String pageIndex;

    /**
     * (総レコード数)<BR>
     * 総レコード数。<BR>
     */
    public String totalRecords;

    /**
     * (ログイン履歴情報一覧)<BR>
     */
    public WEB3AdminTraderAdminLoginHistoryReferenceUnit[] loginHistoryList;

    /**
     * @@roseuid 48D75CD60194
     */
    public WEB3AdminTraderAdminLoginHistoryListResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTraderAdminLoginHistoryListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
