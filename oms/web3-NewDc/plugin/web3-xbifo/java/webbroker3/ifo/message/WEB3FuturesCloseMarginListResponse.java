head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済一覧画面表示レスポンス(WEB3FuturesCloseMarginListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (株価指数先物返済一覧画面表示レスポンス)<BR>
 * 株価指数先物返済一覧画面表示レスポンスクラス
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginListResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191511L;

    /**
     * (株価指数先物返済一覧行)<BR>
     */
    public WEB3FuturesCloseMarginGroup[] closeMarginGroups;

    /**
     * (表示ページ番号)<BR>
     * 実際に表示するページ位置を指定　@※先頭ページを"1"とする
     */
    public String pageIndex;

    /**
     * (総ページ数)<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     */
    public String totalRecords;

    /**
     * (取引終了警告文言)<BR>
     * 閉局間近の市場があれば、そのコードを格納
     */
    public String[] messageSuspension;

    /**
     * (株価指数先物オプション銘柄コード名称)<BR>
     * 株価指数先物オプション銘柄コード名称<BR>
     * (検索条件表示に使用)
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;

    /**
     * @@roseuid 40F7AE16003E
     */
    public WEB3FuturesCloseMarginListResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FuturesCloseMarginListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
