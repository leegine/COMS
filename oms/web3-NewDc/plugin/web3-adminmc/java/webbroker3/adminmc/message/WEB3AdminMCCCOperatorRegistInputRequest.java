head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.55.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒj[§äCCµÍßÚ°Ào^üÍØ¸´½Ä(WEB3AdminMCCCOperatorRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 ÍdÕ (u) VKì¬
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (ÇÒj[§äCCµÍßÚ°Ào^üÍØ¸´½Ä)<BR>
 * ÇÒj[§äCCµÍßÚ°Ào^üÍØ¸´½Ä<BR>
 * 
 * @@author ÍdÕ
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorRegistInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorRegistInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * @@roseuid 419864290138
     */
    public WEB3AdminMCCCOperatorRegistInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 419864290148
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCCCOperatorRegistInputResponse(this);
    }
}
@
