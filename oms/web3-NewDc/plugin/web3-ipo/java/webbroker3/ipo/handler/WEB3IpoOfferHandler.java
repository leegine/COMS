head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOfferHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        :  IPOwü\nh(WEB3IpoOfferHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 mûa VKì¬
*/
package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOOfferCompleteRequest;
import webbroker3.ipo.message.WEB3IPOOfferConfirmRequest;
import webbroker3.ipo.message.WEB3IPOOfferConfirmResponse;
import webbroker3.ipo.message.WEB3IPOOfferInputRequest;
import webbroker3.ipo.message.WEB3IPOOfferInputResponse;
import webbroker3.ipo.message.WEB3IPOOfferCompleteResponse;
import webbroker3.ipo.service.delegate.WEB3IpoOfferService;
import webbroker3.util.WEB3LogUtility;

/**
 * ( IPOwü\nh)<BR>
 * 
 * @@author mûa
 * @@version 1.0
 */
public class WEB3IpoOfferHandler implements MessageHandler 
{
    
    /**
     * OoÍ[eBeBB
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoOfferHandler.class);
        
            
    /**
     * @@roseuid 4112EEA702C6
     */
    public WEB3IpoOfferHandler() 
    {
     
    }
    
    /**
     * (üÍæÊ\¦)<BR>
     * IPOwü\üÍæÊ\¦<BR>
     * <BR>
     * IPOwü\T[rXðæ¾µAexecute()\bhðR[·éB
     * @@param l_request - (NGXgf[^)<BR>
     * IPOwü\üÍNGXgf[^IuWFNg
     * @@return webbroker3.ipo.message.WEB3IpoOfferInputResponse
     * @@roseuid 40DB91EA03B4
     */
    public WEB3IPOOfferInputResponse offerInput(WEB3IPOOfferInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
                " offerInput(WEB3IPOOfferInputRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOOfferInputResponse l_response = null;
        WEB3IpoOfferService l_service = null;
        
        //IPOwü\T[rXðæ¾·é
        try
        {
            l_service =
                (WEB3IpoOfferService)Services.getService(
            WEB3IpoOfferService.class);
        }
        //T[rXÅáOª­¶µ½êÍAG[îñðX|XbZ[WÉÝè·é
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOOfferInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOwü\T[rXÌæ¾É¸sµÜµ½B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPOwü\T[rX.execute()\bhðR[·é
        try
        {
            l_response =
                (WEB3IPOOfferInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOOfferInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPOwü\É¸sµÜµ½B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //X|XIuWFNgðÔp·éB
        return l_response;
}
    
    /**
     * (wü\mF)<BR>
     * IPOwü\mF<BR>
     * <BR>
     * IPOwü\T[rXðæ¾µAexecute()\bhðR[·éB<BR>
     * @@param l_request - (NGXgf[^)<BR>
     * IPOwü\mFNGXgf[^IuWFNg
     * @@return webbroker3.ipo.message.WEB3IpoOfferConfirmResponse
     * @@roseuid 40DB91EA03C4
     */
    public WEB3IPOOfferConfirmResponse offerConfirm(WEB3IPOOfferConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 " offerConfirm(WEB3IPOOfferConfirmRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOOfferConfirmResponse l_response = null;
        WEB3IpoOfferService l_service = null;
        
        //IPOwü\T[rXðæ¾·é
        try
        {
            l_service =
                (WEB3IpoOfferService)Services.getService(
            WEB3IpoOfferService.class);
        }
        //T[rXÅáOª­¶µ½êÍAG[îñðX|XbZ[WÉÝè·é
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOOfferConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOwü\T[rXÌæ¾É¸sµÜµ½B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPOwü\T[rX.execute()\bhðR[·é
        try
        {
            l_response =
                (WEB3IPOOfferConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOOfferConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPOwü\É¸sµÜµ½B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //X|XIuWFNgðÔp·éB
        return l_response;
    }
    
    /**
     * (wü\®¹)<BR>
     * IPOwü\®¹<BR>
     * <BR>
     * IPOwü\T[rXðæ¾µAexecute()\bhðR[·éB<BR>
     * @@param l_request - (NGXgf[^)<BR>
     * IPOwü\®¹NGXgf[^IuWFNg
     * @@return webbroker3.ipo.message.WEB3IpoOfferCompleteResponse
     * @@roseuid 40DB91EA03D4
     */
    public WEB3IPOOfferCompleteResponse offerComplete(WEB3IPOOfferCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                " offerComplete(WEB3IPOOfferCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOOfferCompleteResponse l_response = null;
        WEB3IpoOfferService l_service = null;
        
        //IPOwü\T[rXðæ¾·é
        try
        {
            l_service =
                (WEB3IpoOfferService)Services.getService(
            WEB3IpoOfferService.class);
        }
        //T[rXÅáOª­¶µ½êÍAG[îñðX|XbZ[WÉÝè·é
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOOfferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOwü\T[rXÌæ¾É¸sµÜµ½B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPOwü\T[rX.execute()\bhðR[·é
        try
        {
            l_response =
                (WEB3IPOOfferCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOOfferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPOwü\É¸sµÜµ½B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //X|XIuWFNgðÔp·éB
        return l_response;
    }
}
@
