head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式銘柄条件照会銘柄入力レスポンス(WEB3AdminPMProductCondRefInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式銘柄条件照会銘柄入力レスポンス)<BR>
 * <BR>
 * 管理者・株式銘柄条件照会銘柄入力レスポンスクラス<BR>
 * <BR>
 * WEB3AdminPMProductCondRefInputResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondRefInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_ref_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * （営業日一覧）<BR>
     * <BR>
     * 営業日の一覧<BR>
     * <BR>
     * 当日営業日、翌営業日、翌々営業日の順で格納<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * bizDateList<BR>
     * <BR>
     * Store in order of bizDate, nextBizDate and next2BizDate<BR>
     * <BR>
     */
    public Date[] bizDateList;

    /**
     * @@roseuid 41FA2E0E00FA
     */
    public WEB3AdminPMProductCondRefInputResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMProductCondRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
