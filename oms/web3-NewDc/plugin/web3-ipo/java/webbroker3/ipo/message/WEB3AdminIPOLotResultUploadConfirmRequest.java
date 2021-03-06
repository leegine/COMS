head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOIÊ±¯ÌßÛ°ÄÞmFØ¸´½ÄNX(WEB3AdminIPOLotResultUploadConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 Cg (u) VKì¬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * ÇÒIPOIÊ±¯ÌßÛ°ÄÞmFØ¸´½ÄNX
 * 
 * @@author Cg
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultUploadConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotResultUploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131117L;
    
    /**
     * IPOÁ¿hc
     */
    public String id;
    
    /**
     * Abv[ht@@C<BR>
     * <BR>
     * ¦ CSVt@@CsÌzñ<BR>
     */
    public String[] uploadFile;
    
    /**
     * Iæª<BR>
     * <BR>
     * 1F@@VKI<BR>
     * 2F@@JãI<BR>
     */
    public String lotDiv;
    
    /**
     * @@roseuid 4112DAD400CA
     */
    public WEB3AdminIPOLotResultUploadConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD400E8
     */
    public WEB3GenResponse createResponse() 
    {

        return new WEB3AdminIPOLotResultUploadConfirmResponse(this);
        
    }
}
@
