head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ų\[VVXeęń
File Name        : oą\ā¹oą®¹NGXgNX(WEB3AdminAioCashoutInqCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ččOął (u) VKģ¬
                   2004/10/27 üE(u) r[
                   2005/01/07 üE (u) cĪ 
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (oą\ā¹oą®¹NGXg)<BR>
 * (oą\ā¹oą®¹NGXgNX)
 * 
 * @@author ččOął(u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqCompleteRequest extends WEB3AdminAioCashoutInqCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;    
    /**
     * (ĆŲŌ)<BR>
     * ęŹÉÄüĶ³ź½ĆŲŌ
     */
    public String password;
    
    /**
     * @@roseuid 4158EB6500C9
     */
    public WEB3AdminAioCashoutInqCompleteRequest() 
    {
     
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB660189
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashoutInqCompleteResponse(this);
    }
}
@
