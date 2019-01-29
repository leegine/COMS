head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄更新確認レスポンス (WEB3AdminOffFloorChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者立会外分売銘柄更新確認レスポンス)<BR>
 * <BR>
 * 管理者立会外分売銘柄更新サービス（確認）のレスポンスデータ。<BR>
 * <BR>
 * ----<English>------------------<BR>
 * <BR>
 * WEB3AdminOffFloorChangeConfirmResponse<BR>
 * <BR>
 * response data of WEB3AdminOffFloorChangeService(validate)<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOffFloorChangeConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_change_confirm";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * @@roseuid 421AE3A90264
     */
    public WEB3AdminOffFloorChangeConfirmResponse()
    {

    }

    /**
     * @@roseuid 41FD94160000
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOffFloorChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
