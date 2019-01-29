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
filename	WEB3AdminPMProductCondConfConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式銘柄条件設定確認レスポンスクラス (WEB3AdminPMProductCondConfConfirmResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 管理者・株式銘柄条件設定確認レスポンスクラス<BR>
 * <BR>
 * 管理者・株式銘柄条件設定完了レスポンスクラス<BR>
 * <BR>
 * WEB3AdminPMProductCondConfConfirmResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfConfirmResponse extends WEB3GenResponse
{
    /**
    * PTYPE<BR>
    */
    public static final String PTYPE = "admin_pm_product_cond_conf_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502011606L;

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
     * 株式銘柄取引規制の一覧<BR>
     * <BR>
     * tradingRegulation list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] tradingRegulationList;

    /**
     * （基本情報一覧）<BR>
     * <BR>
     * 株式銘柄基本情報の一覧<BR>
     * <BR>
     * basicInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] basicInfoList;

    /**
     * （信用銘柄情報一覧）<BR>
     * <BR>
     * 株式銘柄信用銘柄情報の一覧<BR>
     * <BR>
     * stockMarginInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] stockMarginInfoList;

    /**
     * （委託保証金率一覧）<BR>
     * <BR>
     * 株式銘柄委託保証金率の一覧
     * <BR>
     * depositRate list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] depositRateList;

    /**
     * （代用有価証券情報一覧）<BR>
     * <BR>
     * 株式銘柄代用有価証券情報の一覧<BR>
     * <BR>
     * substituteSecurityInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] substituteSecurityInfoList;

    /**
     * （値段情報一覧）<BR>
     * <BR>
     * 株式銘柄値段情報の一覧<BR>
     * <BR>
     * priceInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] priceInfoList;

    /**
     * @@roseuid 41FA2A2C039A
     */
    public WEB3AdminPMProductCondConfConfirmResponse()
    {

    }

    /**
     * @@roseuid 41FD94070000
     * @@param l_request l_request
     */
    public WEB3AdminPMProductCondConfConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
