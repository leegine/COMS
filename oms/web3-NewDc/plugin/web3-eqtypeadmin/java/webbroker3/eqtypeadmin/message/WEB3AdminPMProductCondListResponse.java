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
filename	WEB3AdminPMProductCondListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式銘柄条件予定一覧レスポンス(WEB3AdminPMProductCondListResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式銘柄条件予定一覧レスポンス)<BR>
 * <BR>
 * 管理者・株式銘柄条件予定一覧レスポンスクラス<BR>
 * <BR>
 * WEB3AdminPMProductCondListResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * （現在日時）<BR>
     * <BR>
     * 現在日時<BR>
     * <BR>
     * currentDate<BR>
     * <BR>
     */
    public Date currentDate;

    /**
     * （取引規制一覧）<BR>
     * <BR>
     * 取引規制の一覧<BR>
     * <BR>
     * tradingRegulation list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] tradingRegulationList = null;

    /**
     * （基本情報一覧）<BR>
     * <BR>
     * 基本情報の一覧<BR>
     * <BR>
     * basicInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] basicInfoList = null;

    /**
     * （信用銘柄情報一覧）<BR>
     * <BR>
     * 信用銘柄情報の一覧<BR>
     * <BR>
     * stockMarginInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] stockMarginInfoList = null;

    /**
     * （委託保証金率一覧）<BR>
     * <BR>
     * 委託保証金率の一覧<BR>
     * <BR>
     * depositRate list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] depositRateList = null;

    /**
     * （代用有価証券情報一覧）<BR>
     * <BR>
     * 代用有価証券情報の一覧<BR>
     * <BR>
     * substituteSecurityInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] substituteSecurityInfoList = null;

    /**
     * （値段情報一覧）<BR>
     * <BR>
     * 値段情報の一覧<BR>
     * <BR>
     * priceInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] priceInfoList = null;

    /**
     * @@roseuid 41FD92020119
     */
    public WEB3AdminPMProductCondListResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMProductCondListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
