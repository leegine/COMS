head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductListSearchDisplayResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄一覧検索画面表示レスポンス(WEB3AdminBondDomesticProductListSearchDisplayResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 周墨洋 (中訊) 新規作成 モデル192
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者国内債券銘柄一覧検索画面表示レスポンス)<BR>
 * 管理者国内債券銘柄一覧検索画面表示レスポンス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListSearchDisplayResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_product_list_search_display";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20070709100000L;

    /**
     * (債券タイプ一覧)<BR>
     * 債券タイプ一覧<BR>
     * <BR>
     * 11:個人向け国債<BR>
     * 12:社債<BR>
     */
    public String[] bondTypeList;

    /**
     * (取扱区分一覧)<BR>
     * 取扱区分一覧<BR>
     * <BR>
     * 0：不可<BR>
     * 2：顧客<BR>
     */
    public String[] tradeHandleDivList;

    /**
     * (管理者国内債券銘柄一覧検索画面表示レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 46838A9B0274
     */
    public WEB3AdminBondDomesticProductListSearchDisplayResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondDomesticProductListSearchDisplayResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
