head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.44.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄新規登録確認レスポンス(WEB3AdminOffFloorRegistConfirmResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者立会外分売銘柄新規登録確認レスポンス)<BR>
 * <BR>
 * 管理者立会外分売銘柄新規登録サービス（確認）のレスポンスデータ。<BR>
 * <BR>
 * -----<English>--------<BR>
 * <BR>
 * WEB3AdminOffFloorRegistConfirmResponse<BR>
 * <BR>
 * response data of WEB3AdminOffFloorRegistService(validate)<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorRegistConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_regist_confirm";

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
     * (受付終了日時)<BR>
     * <BR>
     * 受付終了日時。<BR>
     * <BR>
     * orderEndDatetime<BR>
     * <BR>
     */
    public Date orderEndDatetime;

    /**
     * @@roseuid 421AE47F0012
     */
    public WEB3AdminOffFloorRegistConfirmResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOffFloorRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
