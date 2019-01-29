head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorDeleteConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄削除確認レスポンス (WEB3AdminOffFloorDeleteConfirmResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者立会外分売銘柄削除確認レスポンス)<BR>
 * <BR>
 * 管理者立会外分売銘柄削除サービス（確認）のレスポンスデータ。<BR>
 * <BR>
 * -----<English>-----------<BR>
 * <BR>
 * WEB3AdminOffFloorDeleteConfirmResponse<BR>
 * <BR>
 * response data of WEB3AdminOffFloorDeleteService(validate)<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOffFloorDeleteConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_delete_confirm";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (銘柄名)<BR>
     * <BR>
     * 銘柄名。<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * (受付開始日時)<BR>
     * <BR>
     * 受付開始日時。<BR>
     * <BR>
     * orderStartDatetime<BR>
     * <BR>
     */
    public Date orderStartDatetime;

    /**
     * (分売価格)<BR>
     * <BR>
     * 分売価格。<BR>
     * <BR>
     * offFloorOrderPrice<BR>
     * <BR>
     */
    public String offFloorOrderPrice;

    /**
     * (申込株数上限)<BR>
     * <BR>
     * 申込株数上限。<BR>
     * （一人あたりの注文可能株数の上限値）<BR>
     * <BR>
     * ----<English>-------<BR>
     * <BR>
     * maxApplyQuantity<BR>
     * （maximum value of applyQuantity per person）<BR>
     * <BR>
     */
    public String maxApplyQuantity;

    /**
     * @@roseuid 421AE40E011C
     */
    public WEB3AdminOffFloorDeleteConfirmResponse()
    {

    }

    /**
     * @@roseuid 41FD94160000
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOffFloorDeleteConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
