head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : M๕วา]อฒฎฎนNGXgNX(WEB3AdminMutualTPAdjustCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 ่่Oเ๙ (u) VK์ฌ
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * M๕วา]อฒฎฎนNGXgNX
 * 
 * @@author ่่Oเ๙(u)
 * @@version 1.0
 */
public class WEB3AdminMutualTPAdjustCompleteRequest extends WEB3AdminMutualTPAdjustCommonRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_tp_adjust_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512191029L;

    /**
     * รุิ<BR>
     */
    public String password;

    /**
     * (M๕วา]อฒฎฎนNGXg)<BR>
     * ftHgRXgN^
     * @@roseuid 40A8A7E600E7
     */
    public WEB3AdminMutualTPAdjustCompleteRequest()
    {

    }

    /**
     * icreateResponseฬภj<BR>
     * <BR>
     * Mวา]อฒฎฎนX|XIuWFNg๐ถฌตฤิทB<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8A80001D1
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualTPAdjustCompleteResponse(this);
    }
}
@
