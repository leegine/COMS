head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOREquityOrderExecutionRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒE®¶ñèÆïüÍX|X (WEB3AdminOREquityOrderExecutionRefInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/09 ½¶q(u) dlÏX fNo.106
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (ÇÒE®¶ñèÆïüÍX|X)<BR>
 * <BR>
 * ÇÒE®¶ñèÆïüÍX|XNX<BR>
 * <BR>
 * WEB3AdminOREquityOrderExecutionRefInputResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOREquityOrderExecutionRefInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_equity_order_execution_ref_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (­úê)<BR>
     * <BR>
     * ­úÌzñ<BR>
     * <BR>
     * An array of orderBizDate<BR>
     * <BR>
     */
    public Date[] orderBizDateList;

    /**
     * (sêR[hê)<BR>
     * <BR>
     * sêR[hÌzñ<BR>
     * <BR>
     * An array of marketCode<BR>
     * <BR>
     */
    public String[] marketCodeList;

    /**
     * (ÙÏê)<BR>
     * <BR>
     * ÙÏæªÌzñ<BR>
     * <BR>
     * An array of repayment<BR>
     * <BR>
     */
    public String[] repaymentList = null;

    /**
     * (liðê)<BR>
     * <BR>
     * liðÌzñ<BR>
     * <BR>
     * An array of priceCond<BR>
     * <BR>
     */
    public String[] priceCondList = null;

    /**
     * (·sðê)<BR>
     * <BR>
     * ·sðÌzñ<BR>
     * <BR>
     * An array of execCondList<BR>
     * <BR>
     */
    public String[] execCondList = null;

    /**
     * (¶úÀæªê)<BR>
     * <BR>
     * ¶úÀæªÌzñ<BR>
     * <BR>
     * An array of expirationDateType<BR>
     * <BR>
     */
    public String[] expirationDateTypeList = null;

    /**
     * (­ðê)<BR>
     * <BR>
     * ­ðÌzñ<BR>
     * <BR>
     * An array of orderCondType<BR>
     * <BR>
     */
    public String[] orderCondTypeList = null;

    /**
     * (¶oHæªê)<BR>
     * <BR>
     * ¶oHæªÌê<BR>
     */
    public String[] orderRootList = null;
    
    /**
     * iæµ¤iêj<BR>
     * <BR>
     */
    public WEB3AdminORTradingProductUnit[] tradingProductList;

    /**
     * (­§ÏÀ{tO)<BR>
     * <BR>
     * false:¢À{<BR>
     * true:À{<BR>
     */
    public boolean forcedSettleEnforcementFlag;

    /**
     * @@roseuid 4212FB420315
     */
    public WEB3AdminOREquityOrderExecutionRefInputResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOREquityOrderExecutionRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
