head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : ()åa¤ Ø\[VVXeæñ
 File Name        : ÇÒEFXUÖæÁ®¹NGXg(WEB3AdminFXTransferCancelCompleteRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ©(u) VKì¬
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (ÇÒEFXUÖæÁ®¹NGXg) <BR>
 * ÇÒEFXUÖæÁ®¹NGXgNX
 * 
 * @@author ©(u)
 * @@version 1.0
 */

public class WEB3AdminFXTransferCancelCompleteRequest extends
    WEB3AdminFXTransferCancelCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_transfer_cancel_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (ÃØÔ) <BR>
     * æÊ©çüÍ³ê½ÃØÔ
     */
    public String password;

    /**
     * @@roseuid 41E7902001C5
     */
    public WEB3AdminFXTransferCancelCompleteRequest()
    {
    }
    
    /**
     * icreateResponseÌÀj<BR>
     * <BR>
     * ÇÒEFXUÖæÁ®¹X|XIuWFNgðÔp·éB
     * @@return WEB3GenResponse
     * @@roseuid 41E7904B029F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXTransferCancelCompleteResponse(this);
    }
}@
