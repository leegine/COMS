head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : ฎ~jtถฎนX|XNX(WEB3MstkSellCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 Cg (u) VK์ฌ
                   2004/12/09 Kด (SAR) cฤฮ No.281
                   2005/01/05 ชบaพ (SRA) JavaDocCณ
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * iฎ~jtถฎนX|XjB<BR>
 * <BR>
 * ฎ~jtถฎนX|XNX
 * @@author Cg
 * @@version 1.0
 */
public class WEB3MstkSellCompleteResponse extends WEB3GenResponse 
{
    /**
     * iPTYPEjB
     */
    public static final String PTYPE = "mstk_sellComplete";

    /**
     * iSerialVersionUIDjB
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * iXVิjB
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * iฏสิjB
     */
    public String orderActionId;
    
	/**
	 * iCTC_[x\ฆtOjB<BR>
     * <BR>
	 * trueFx\ฆv<BR>
	 * falseFx\ฆsv
	 */
	public boolean insiderWarningFlag;
    
    /**
     * iฎ~jtถฎนX|XjB<BR>
     * <BR>
     * RXgN^
     */
	public WEB3MstkSellCompleteResponse() 
	{

	}

    /**
     * iฎ~jtถฎนX|XjB<BR>
     * <BR>
     * RXgN^
     * @@param l_request ฎ~jtถฎนNGXg
     */
    public WEB3MstkSellCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
