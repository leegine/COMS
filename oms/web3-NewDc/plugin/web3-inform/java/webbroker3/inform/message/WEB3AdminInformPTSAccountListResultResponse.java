head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListResultResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・PTS申込客一覧問合せ検索結果レスポンスクラス(WEB3AdminInformPTSAccountListResultResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 謝旋(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・PTS申込客一覧問合せ検索結果レスポンスクラス)<BR>
 * 管理者・PTS申込客一覧問合せ検索結果レスポンスクラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListResultResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_pts_account_list_result";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802281638L;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号
     */
    public String pageIndex;

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
     * (PTS申込客情報一覧)<BR>
     * PTS申込客情報一覧
     */
    public WEB3AdminInformPTSAccountInfoUnit[] ptsAccountInfoList;

    /**
     * @@roseuid 47C522D40377
     */
    public WEB3AdminInformPTSAccountListResultResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminInformPTSAccountListResultResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
