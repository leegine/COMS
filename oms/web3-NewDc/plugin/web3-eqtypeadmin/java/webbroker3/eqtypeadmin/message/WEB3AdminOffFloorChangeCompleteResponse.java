head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄更新完了レスポンス (WEB3AdminOffFloorChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者立会外分売銘柄更新完了レスポンス)<BR>
 * <BR>
 * 管理者立会外分売銘柄更新サービス（完了）のレスポンスデータ。<BR>
 * <BR>
 * --------<English>---------------<BR>
 * <BR>
 * WEB3AdminOffFloorChangeCompleteResponse<BR>
 * <BR>
 * response data of WEB3AdminOffFloorChangeService(submit)<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOffFloorChangeCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_change_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (更新時間)<BR>
     * <BR>
     * 更新時間。<BR>
     * <BR>
     * lastUpdatedTimestamp<BR>
     * <BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * @@roseuid 421AE3A10310
     */
    public WEB3AdminOffFloorChangeCompleteResponse()
    {

    }

    /**
     * @@roseuid 41FD94160000
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOffFloorChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
