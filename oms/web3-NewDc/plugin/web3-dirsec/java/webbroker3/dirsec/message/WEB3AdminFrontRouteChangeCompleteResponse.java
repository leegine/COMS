head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontRouteChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒ­oHØÖ®¹X|X (WEB3AdminFrontRouteChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  Óù (u) dlÏXfNo.116
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒ­oHØÖ®¹X|X)<BR>
 * <BR>
 * ÇÒ­oHØÖT[rXi®¹jÌX|Xf[^B<BR>
 * <BR>
 * --------<English>---------------<BR>
 * <BR>
 * WEB3AdminFrontRouteChangeCompleteResponse<BR>
 * <BR>
 * response data of WEB3AdminFrontRouteChangeService(submit)<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontRouteChangeCompleteResponse extends WEB3GenResponse {
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_front_route_change_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * @@roseuid 42FFFED60032
     */
    public WEB3AdminFrontRouteChangeCompleteResponse()
    {

    }

    /**
     * @@roseuid 
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminFrontRouteChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }


}
@
