head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : üàA®¹NGXgNX(WEB3AioCashinNoticeCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 üz (u) VKì¬
                   2004/10/22 © (u) r[    
*/


package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (üàA®¹NGXg)<BR>
 * üàA®¹NGXgNX
 * 
 * @@author üz(u)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeCompleteRequest extends WEB3AioCashinNoticeCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_notice_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410111111L;
    
    /**
     * (ÃØÔ)<BR>
     * æÊÉÄüÍ³ê½ÃØÔ
     */
    public String password;
    
    /**
     * @@roseuid 4158E9B80036
     */
    public WEB3AioCashinNoticeCompleteRequest() 
    {
     
    }
    
    /**
     * icreateResponseÌÀj<BR>
     * <BR>
     * üàA®¹X|XIuWFNgð¶¬µÄÔ·B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinNoticeCompleteResponse(this);
    }

}
@
