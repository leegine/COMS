head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingDemandMapRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOÌÞ¯¸ËÞÙÃÞ¨Ý¸Þ\ªz}Ø¸´½Ä(WEB3AdminIPOBookBuildingDemandMapRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ÄÙ (u) VKì¬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * ÇÒIPOÌÞ¯¸ËÞÙÃÞ¨Ý¸Þ\ªz}Ø¸´½ÄNX
 *                                                               
 * @@author ÄÙ
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingDemandMapRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_bookBuildingDemandMap";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121059L;
    
    /**
     * IPOÁ¿hc
     */
    public String id;
    
    /**
     * @@roseuid 4112DF8C02EE
     */
    public WEB3AdminIPOBookBuildingDemandMapRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8C0302
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOBookBuildingDemandMapResponse(this);
    }
}
@
