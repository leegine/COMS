head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : MÇÒJeS[o^üÍæÊNGXg(WEB3AdminMutualCategoryRegistInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 © (u) VKì¬ 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (MÇÒJeS[o^üÍæÊNGXg)<BR>
 * MõÇÒJeS[o^üÍæÊNGXgNX
 * 
 * @@author ©(u)
 * @@version 1.0  
 */

public class WEB3AdminMutualCategoryRegistInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_category_regist_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412030925L;
    
    /**
     * icreateResponseÌÀj<BR>
     * <BR>
     * MÇÒJeS[o^üÍæÊX|XIuWFNgðÔp·éB
     * @@return WEB3GenResponse
     * @@roseuid 4153B6160363
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualCategoryRegistInputResponse(this);
    }
}
@
