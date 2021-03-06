head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingHistoryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOÌÞ¯¸ËÞÙÃÞ¨Ý¸Þ\ðØ¸´½Ä(WEB3AdminIPOBookBuildingHistoryRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ÄÙ (u) VKì¬
Revesion History : 2005/01/06 âã(SRA) cÎ>>>056
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * ÇÒIPOÌÞ¯¸ËÞÙÃÞ¨Ý¸Þ\ðØ¸´½ÄNX
 *                                                               
 * @@author ÄÙ
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingHistoryRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_bookBuildingHistory";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121108L;
    
    /**
     * IPOÁ¿hc
     */
    public String id;
    
    /**
     * XR[h
     */
    public String branchCode;
    
    /**
     * ÚqR[h
     */
    public String accountCode;
    
    /**
     * @@roseuid 4112DF8C01DF
     */
    public WEB3AdminIPOBookBuildingHistoryRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8C01F3
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOBookBuildingHistoryResponse(this);
    }
}
@
