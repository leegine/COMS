head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqUploadErrCancelInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ĺa¤ Ř\[VVXećń
File Name        : ÇŇOŽŻąŻĚßŰ°ÄŢ´×°đüÍNGXg(WEB3AdminFeqUploadErrCancelInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 sp (u) VKěŹ
                 : 2005/08/02 ¤űU (u) r[
*/
package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇŇOŽŻąŻĚßŰ°ÄŢ´×°đüÍNGXg)<BR>
 * ÇŇOŽŻąŻĚßŰ°ÄŢ´×°đüÍNGXg
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3AdminFeqUploadErrCancelInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_uploadErrCancelInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * @@roseuid 42CE3A02037A
     */
    public WEB3AdminFeqUploadErrCancelInputRequest() 
    {
     
    }

    /**
     * YNGXgÉÎˇéX|XIuWFNgđÔˇB<BR>
     *<BR>
     * @@return X|XIuWFNg
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqUploadErrCancelInputResponse(this);
    }
}
@
