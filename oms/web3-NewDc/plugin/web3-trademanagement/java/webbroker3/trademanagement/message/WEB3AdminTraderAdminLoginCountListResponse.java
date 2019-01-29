head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminLoginCountListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・IP別ログイン回数一覧検索結果レスポンス(WEB3AdminTraderAdminLoginCountListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.005,007
*/
package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・IP別ログイン回数一覧検索結果レスポンス)<BR>
 * 管理者・IP別ログイン回数一覧検索結果レスポンスクラス。<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTraderAdminLoginCountListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_login_count_list";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221757L;

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
     * （IP別ログイン回数情報一覧）<BR>
     * IP別ログイン回数情報一覧。<BR>
     */
    public WEB3AdminTraderAdminLoginCountReferenceUnit[] loginCountList;

    /**
     * @@roseuid 48D75CD602DC
     */
    public WEB3AdminTraderAdminLoginCountListResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTraderAdminLoginCountListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
