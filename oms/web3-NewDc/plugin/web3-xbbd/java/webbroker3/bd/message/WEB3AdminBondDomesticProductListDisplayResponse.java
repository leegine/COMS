head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductListDisplayResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄一覧画面表示レスポンス(WEB3AdminBondDomesticProductListDisplayResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 周墨洋 (中訊) 新規作成 モデル192
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者国内債券銘柄一覧画面表示レスポンス)<BR>
 * 管理者国内債券銘柄一覧画面表示レスポンス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListDisplayResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_product_list_display";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20070709100000L;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;

    /**
     * (銘柄照会情報一覧)<BR>
     * 銘柄照会情報一覧<BR>
     */
    public WEB3BondDomesticProductRefInfo[] productRefInfoList;

    /**
     * (管理者国内債券銘柄一覧画面表示レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 465F6E940382
     */
    public WEB3AdminBondDomesticProductListDisplayResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondDomesticProductListDisplayResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
