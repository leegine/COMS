head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBuyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : ฎ~jtถฎนX|X(WEB3MstkBuyCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ซ] (u) VK์ฌ
                   2004/12/09 KดiSARjcฤฮ No.281
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * iฎ~jtถฎนX|XjB<BR>
 * <BR>
 * ฎ~jtถฎนX|XNX
 * @@author ซ]
 * @@version 1.0
 */
public class WEB3MstkBuyCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_buyComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101059L;     
    /**
     * (XVิ)
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (ฏสิ)
     */
    public String orderActionId;
    
	/**
	 * (CTC_[x\ฆtO)<BR>
	 * trueFx\ฆv<BR>
	 * falseFx\ฆsv<BR>
	 */
	public boolean insiderWarningFlag;
	
    /**
     * @@roseuid 4167B04C0352
     */
	public WEB3MstkBuyCompleteResponse() 
	{

	}
	
    public WEB3MstkBuyCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
