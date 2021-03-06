head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellPossibleListReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÝÏðñênh(WEB3RuitoSellPossibleListReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 u­ (u) VKì¬
*/

package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellPossibleListReferenceService;
import webbroker3.xbruito.message.WEB3RuitoSellListResponse;
import webbroker3.xbruito.message.WEB3RuitoSellListRequest;

/**
 * ÝÏðñênh<BR>
 */
public class WEB3RuitoSellPossibleListReferenceHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellPossibleListReferenceHandler.class);

    /**
     * ÝÏÌðñêæÊ\¦ðs¤B<BR>
     * <BR>
     * ÝÏðñêT[rXðæ¾µA<BR>
     * execute()\bhðR[·éB<BR>
     * @@param l_request - ÝÏðñêNGXgIuWFNg<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoSellListResponse
     * @@roseuid 40693AD3031C
     */
    public WEB3RuitoSellListResponse searchRuitoSellPossibleListReference(
            WEB3RuitoSellListRequest l_request)
    {
		final String STR_METHOD_NAME = 
            "searchRuitoSellPossibleListReference(WEB3RuitoSellListRequest l_request)";
		log.entering(STR_METHOD_NAME);
		
		if (l_request == null)
		{
            log.debug(" p[^lªNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "p[^lªNULL");            
		}
		
		WEB3RuitoSellPossibleListReferenceService l_service = null;
        WEB3RuitoSellListResponse l_response = null;

        l_service =
            (WEB3RuitoSellPossibleListReferenceService) 
            Services.getService(WEB3RuitoSellPossibleListReferenceService.class);

        try
        {
            l_response = (WEB3RuitoSellListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3RuitoSellListResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "ÝÏÌðñêæÊ\¦ª¸sµÜµ½B", e.getErrorInfo(), e);
        }
		log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
