head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒO®ú¶`[êX|X(WEB3AdminFeqOrderVoucherListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 sp (u) VKì¬
                 : 2005/08/02 ¤ûU (u) r[
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒO®ú¶`[êX|X)<BR>
 * ÇÒO®ú¶`[êX|XNX
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderVoucherList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (ú¶`[)<BR>
     * O®ú¶`[iCSVjÌzñ
     */
    public String[] orderVoucherList;
    
    /**
     * @@roseuid 42CE39FB0203
     */
    public WEB3AdminFeqOrderVoucherListResponse() 
    {
     
    }

    /**
     * RXgN^B<BR>
     * wè³ê½NGXgIuWFNgÉÎ·éX|XIuWFNgð¶¬·éB<BR>
     * @@param l_request - NGXgIuWFNg
     */
    public WEB3AdminFeqOrderVoucherListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
