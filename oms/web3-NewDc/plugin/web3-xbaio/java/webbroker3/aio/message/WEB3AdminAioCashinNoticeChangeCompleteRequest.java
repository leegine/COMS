head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : üàÊmù³®¹NGXgNX(WEB3AdminAioCashinNoticeChangeCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/21 èèOàù (u) VKì¬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (üàÊmù³®¹NGXg)<BR>
 * üàÊmù³®¹NGXgNX
 * 
 * @@author èèOàù(u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinNoticeChangeCompleteRequest extends WEB3AdminAioCashinChangeCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_notice_change_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211145L;  
    
    /**
     * (ÃØÔ)
     */
    public String password;
        
    /**
     * icreateResponseÌÀj<BR>
     * <BR>
     * üàÊmù³®¹X|XIuWFNgð¶¬µÄÔ·B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashinNoticeChangeCompleteResponse(this);
    }
}
@
