head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyProductListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募銘柄一覧レスポンス(WEB3BondDomesticApplyProductListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 モデルNo.227
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (国内債券応募銘柄一覧レスポンス)<BR>
 * 国内債券応募銘柄一覧レスポンス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyProductListResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_domestic_apply_product_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

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
     * (国内債券応募銘柄一覧)<BR>
     * 国内債券応募銘柄一覧<BR>
     */
    public WEB3BondDomesticApplyProductInfo[] bondDomesticApplyProductList;

    /**
     * (国内債券応募銘柄一覧レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 466643810174
     */
    public WEB3BondDomesticApplyProductListResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondDomesticApplyProductListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
