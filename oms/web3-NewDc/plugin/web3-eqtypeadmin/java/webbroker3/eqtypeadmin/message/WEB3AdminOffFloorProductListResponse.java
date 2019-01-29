head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.44.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorProductListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄一覧レスポンス(WEB3AdminOffFloorProductListResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者立会外分売銘柄一覧レスポンス)<BR>
 * <BR>
 * 管理者立会外分売銘柄一覧サービスのレスポンスデータ。<BR>
 * <BR>
 * -----<English>-------------<BR>
 * <BR>
 * WEB3AdminOffFloorProductListResponse<BR>
 * <BR>
 * response data of WEB3AdminOffFloorProductListService<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorProductListResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_product_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (銘柄一覧)<BR>
     * <BR>
     * 立会外分売銘柄一覧。<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * productList<BR>
     * <BR>
     * A list of off floor product<BR>
     * <BR>
     */
    public webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductGroup[] productList;

    /**
     * @@roseuid 421AE31B00FD
     */
    public WEB3AdminOffFloorProductListResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOffFloorProductListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
