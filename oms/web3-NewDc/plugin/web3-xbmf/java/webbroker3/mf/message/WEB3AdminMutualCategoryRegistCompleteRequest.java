head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : MวาJeS[o^ฎนNGXg(WEB3AdminMutualCategoryRegistCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ฉ (u) VK์ฌ 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (MวาJeS[o^ฎนNGXg)<BR>
 * M๕วาJeS[o^ฎนNGXgNX
 * 
 * @@author ฉ(u)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistCompleteRequest 
    extends WEB3MutualCategoryRegistCommonRequest 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_category_regist_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412031046L;
    
    /**
     * (รุิ)<BR>
     *  รุิ
     */
    public String password;
    
    /**
     * icreateResponseฬภj<BR>
     * <BR>
     * MวาJeS[o^ฎนX|XIuWFNg๐ิpท้B
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4153B8400056
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualCategoryRegistCompleteResponse(this);
    }
}
@
