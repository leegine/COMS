head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultUploadInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOIÊ±¯ÌßÛ°ÄÞüÍØ¸´½ÄNX(WEB3AdminIPOLotResultUploadInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 Cg (u) VKì¬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * ÇÒIPOIÊ±¯ÌßÛ°ÄÞüÍØ¸´½ÄNX
 * 
 * @@author Cg
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultUploadInputRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotResultUploadInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131148L;
    
    /**
     * IPOÁ¿hc
     */
    public String id;
    
    /**
     * @@roseuid 4112DAD4003E
     */
    public WEB3AdminIPOLotResultUploadInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD40048
     */
    public WEB3GenResponse createResponse() 
    {

        return new WEB3AdminIPOLotResultUploadInputResponse(this);

    }
}
@
