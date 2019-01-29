head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorAccountListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータ対象顧客一覧レスポンス(WEB3CCOperatorAccountListResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 周墨洋 (中訊) 新規作成・モデルNo.039
*/

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (CCオペレータ対象顧客一覧レスポンス)<BR>
 * CCオペレータ対象顧客一覧レスポンスクラス
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3CCOperatorAccountListResponse extends WEB3GenResponse
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3CCOperatorAccountListResponse.class);

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231120L;

    /**
     * TAGNAME<BR>
     */
    public static final String TAGNAME = "response";

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "cc_operator_account_list";

    /**
     * (対象顧客一覧)<BR>
     * 対象顧客一覧
     */
    public WEB3TraderAccountInfo[] traderAccoutInfos;

    /**
     * (総ページ数)<BR>
     * 総ページ数
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 総レコード数
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号
     */
    public String pageIndex;

    /**
     * (CCオペレータ対象顧客一覧レスポンス)<BR>
     * コンストラクタ<BR>
     */
    public WEB3CCOperatorAccountListResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - (リクエストオブジェクト)
     */
    protected WEB3CCOperatorAccountListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
