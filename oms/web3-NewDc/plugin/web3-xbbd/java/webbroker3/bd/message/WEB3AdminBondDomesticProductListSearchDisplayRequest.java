head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductListSearchDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄一覧検索画面表示リクエスト(WEB3AdminBondDomesticProductListSearchDisplayRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 周墨洋 (中訊) 新規作成 モデル192
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者国内債券銘柄一覧検索画面表示リクエスト)<BR>
 * 管理者国内債券銘柄一覧検索画面表示リクエスト<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListSearchDisplayRequest extends WEB3GenRequest
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
     * @@roseuid 4691C5EC01EC
     */
    public WEB3AdminBondDomesticProductListSearchDisplayRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
         return new WEB3AdminBondDomesticProductListSearchDisplayResponse(this);
    }

}
@
