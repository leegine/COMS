head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : MวาJeS[o^mFNGXg(WEB3AdminMutualCategoryRegistConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 ฉ (u) VK์ฌ 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (MวาJeS[o^mFNGXg)<BR>
 * M๕วาJeS[o^mFNGXgNX
 * 
 * @@author ฉ(u)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistConfirmRequest 
    extends WEB3MutualCategoryRegistCommonRequest 
{   
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_category_regist_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412030951L;
    
    /**
     * icreateResponseฬภj<BR>
     * <BR>
     * MวาJeS[o^mFX|XIuWFNg๐ิpท้B
     * @@return WEB3GenResponse
     * @@roseuid 4153B8260150
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualCategoryRegistConfirmResponse(this);
    }
}
@
