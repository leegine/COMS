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
filename	WEB3AdminPMProductCondConfInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒE®Á¿ðÝèüÍX|X(WEB3AdminPMProductCondConfInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (ÇÒE®Á¿ðÝèüÍX|X) <BR>
 * <BR>
 * ÇÒE®Á¿ðÝèüÍX|XNX<BR>
 * <BR>
 * WEB3AdminPMProductCondConfInputResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_conf_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * i»Ýúj<BR>
     * <BR>
     * »Ýú
     * <BR>
     * currentDate<BR>
     * <BR>
     */
    public Date currentDate;

    /**
     * icÆúj<BR>
     * <BR>
     * {úÌcÆú<BR>
     * <BR>
     * bizDate<BR>
     * <BR>
     */
    public Date bizDate;

    /**
     * icÆúj<BR>
     * <BR>
     * úÌcÆú<BR>
     * <BR>
     * nextBizDate<BR>
     * <BR>
     */
    public Date nextBizDate;

    /**
     * iXcÆúj<BR>
     * <BR>
     * XúÌcÆú<BR>
     * <BR>
     * next2BizDate<BR>
     * <BR>
     */
    public Date next2BizDate;

    /**
     * iÁ¿R[hj<BR>
     * <BR>
     * Á¿R[h<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * iÁ¿¼j<BR>
     * <BR>
     * Á¿¼<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * iãêsêR[hêj<BR>
     * <BR>
     * Á¿ªãêµÄ¢ésêÌsêR[hÌzñ<BR>
     * <BR>
     * The array of marketCode of markets in which products list<BR>
     * <BR>
     */
    public String[] listingCodeList;

    /**
     * i§xMpÀ{tOj<BR>
     * <BR>
     * §xMpÀ{tO<BR>
     * <BR>
     * falseF@@¢À{<BR>
     * trueF@@À{<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * marketMarginFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean marketMarginFlag;

    /**
     * iêÊMpÀ{tOj<BR>
     * <BR>
     * êÊMpÀ{tO<BR>
     * <BR>
     * falseF@@¢À{<BR>
     * trueF@@À{<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * institutionMarginFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean institutionMarginFlag;

    /**
     * i~jÀ{tOj<BR>
     * <BR>
     * ~jÀ{tO<BR>
     * <BR>
     * falseF@@¢À{<BR>
     * trueF@@À{<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * miniFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean miniFlag;

    /**
     * iæøK§êj<BR>
     * <BR>
     * ®Á¿æøK§Ìê<BR>
     * <BR>
     * tradingRegulation list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] tradingRegulationList;

    /**
     * iî{îñêj<BR>
     * <BR>
     * ®Á¿î{îñÌê<BR>
     * <BR>
     * basicInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] basicInfoList;

    /**
     * iMpÁ¿îñêj<BR>
     * <BR>
     * ®Á¿MpÁ¿îñÌê<BR>
     * <BR>
     * stockMarginInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] stockMarginInfoList;

    /**
     * iÏõÛØà¦êj<BR>
     * <BR>
     * ®Á¿ÏõÛØà¦Ìê<BR>
     * <BR>
     * depositRate list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] depositRateList;

    /**
     * iãpL¿Øîñêj<BR>
     * <BR>
     * ®Á¿ãpL¿ØîñÌê<BR>
     * <BR>
     * substituteSecurityInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] substituteSecurityInfoList;

    /**
     * iliîñêj<BR>
     * <BR>
     * ®Á¿liîñÌê<BR>
     * <BR>
     * priceInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] priceInfoList;

    /**
     * @@roseuid 41FA2A3501A6
     */
    public WEB3AdminPMProductCondConfInputResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMProductCondConfInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
