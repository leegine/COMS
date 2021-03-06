head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : »¨®cÆïcvX|X(WEB3EquityBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 òº@@mm(SRA) VKì¬
				   2006/08/29 r@@(u)@@dlÏX fNo.971
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * i»¨®cÆïcvX|XjB<BR>
 * <BR>
 * »¨®cÆïcvX|XNX<BR>
 */
public class WEB3EquityBalanceReferenceTotalResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_balance_reference_total";
    
    /**
     * (ÁèûÀ]¿zv)<BR>
     *<BR>
     * ÁèûÀ]¿zv<BR>
     */
    public String capitalGainTotalAsset;
    
    /**
     * (ÁèûÀ]¿¹vv)<BR>
     *<BR>
     * ÁèûÀ]¿¹vv<BR>
     */
    public String capitalGainTotalAppraisalProfitLoss;
    
    /**
     * (êÊûÀ]¿zv)<BR>
     *<BR>
     * êÊûÀ]¿zv<BR>
     */
    public String normalAccountTotalAsset;
    
    /**
     * (êÊûÀ]¿¹vv)<BR>
     *<BR>
     * êÊûÀ]¿¹vv<BR>
     */
    public String normalAccountTotalAppraisalProfitLoss;
    
    /**
     * (XgbNIvVûÀ]¿zv)<BR>
     *<BR>
     *XgbNIvVûÀ]¿zv<BR>
     */
    public String stockOptionTotalAsset;
    
    /**
     * (XgbNIvVûÀ]¿¹vv)<BR>
     *<BR>
     *XgbNIvVûÀ]¿¹vv<BR>
     */
    public String stockOptionTotalAppraisalProfitLoss;
    
    /**
     * @@roseuid 4206C8A903C5<BR>
     */
    public WEB3EquityBalanceReferenceTotalResponse() 
    {
     
    }
    
    public WEB3EquityBalanceReferenceTotalResponse(WEB3EquityBalanceReferenceTotalRequest l_request) 
    {
        super(l_request); 
    }
}
@
