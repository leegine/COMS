head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : MõÁ¿ðo^®¹NGXg(WEB3AdminMutualConditionsCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 © (u) VKì¬
                   2004/08/23 °üí (u) r[ 
                   2004/12/10 °üí (u) cÎ
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * MõÁ¿ðo^®¹NGXg<BR>
 * 
 * @@author ©(u)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsCompleteRequest 
    extends WEB3MutualProductConditionsCommonRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131115L;
    
    /**
     * ÃØÔ<BR>
     */
    public String password;
       
    /**
     * (MÁ¿ðo^®¹NGXg)<BR>
     * ftHgRXgN^<BR>
     * @@roseuid 40DF815400B2
     */
    public WEB3AdminMutualConditionsCompleteRequest() 
    {
     
    }
        
    /**
     * icreateResponseÌÀj<BR>
     * <BR>
     * MÁ¿ðo^®¹X|XIuWFNgð¶¬µÄÔ·B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF816102A6
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualConditionsCompleteResponse(this);
    }
    

}@
