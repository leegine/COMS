head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOIÊwü\óµÀÞ³ÝÛ°ÄÞØ¸´½Ä(WEB3AdminIPOLotResultOfferDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ÄÙ (u) VKì¬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * ÇÒIPOIÊwü\óµÀÞ³ÝÛ°ÄÞØ¸´½ÄNX
 *                                                               
 * @@author ÄÙ
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferDownloadRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOfferDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121114L;
    
    /**
     * IPOÁ¿hc
     */
    public String id;
    
    /**
     * @@roseuid 4112DAD50338
     */
    public WEB3AdminIPOLotResultOfferDownloadRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD5034C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotResultOfferDownloadResponse(this);
    }
}
@
