head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOIÊwü\óµêØ¸´½Ä(WEB3AdminIPOLotResultOfferListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ÄÙ (u) VKì¬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * ÇÒIPOIÊwü\óµêØ¸´½ÄNX
 *                                                               
 * @@author ÄÙ
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferListRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOfferList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121118L;
    
    /**
     * IPOÁ¿hc
     */
    public String id;
    
    /**
     * vy[WÔ
     */
    public String pageIndex;
    
    /**
     * y[Wà\¦s
     */
    public String pageSize;
    
    /**
     * (\[gL[)
     */
    public WEB3IPOSortKey[] sortKeys;
    
    /**
     * @@roseuid 4112DAD500CB
     */
    public WEB3AdminIPOLotResultOfferListRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD500DF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotResultOfferListResponse(this);
    }
}
@
