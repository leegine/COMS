head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferFileDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOIÊwü\óµÌ§²ÙÀÞ³ÝÛ°ÄÞØ¸´½Ä(WEB3AdminIPOLotResultOfferFileDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ÄÙ (u) VKì¬
                 : 2006/11/09 êÏ (u) dlÏXEf160
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * ÇÒIPOIÊwü\óµÌ§²ÙÀÞ³ÝÛ°ÄÞØ¸´½ÄNX
 *                                                               
 * @@author ÄÙ
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferFileDownloadRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOfferFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121116L;
    
    /**
     * IPOÁ¿hc
     */
    public String id;
    
    /**
     * (CSVæª) <BR>
     * CSVæª <BR>
     * <BR>
     * 0FÇÁÚ³<BR>
     * 1FÇÁÚLiµÒR[hAöJ¿iAMpæªAIÔj<BR>
     */
    public String csvDiv = "0";
    
    /**
     * @@roseuid 4112DAD501D0
     */
    public WEB3AdminIPOLotResultOfferFileDownloadRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD501E4
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotResultOfferFileDownloadResponse(this);
    }
}
@
