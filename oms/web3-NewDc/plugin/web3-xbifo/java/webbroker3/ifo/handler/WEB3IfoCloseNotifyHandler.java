head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCloseNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����ʒm�n���h���N���X(WEB3IfoCloseNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/17 Ḗ@@�� (���u) �V�K�쐬
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.message.WEB3IfoCloseOrderRequest;
import webbroker3.ifo.message.WEB3IfoCloseOrderResponse;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyService;


/**
 * (�敨OP�����ʒm�n���h��)<BR>
 * �敨OP�����ʒm�n���h���N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3IfoCloseNotifyHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoCloseNotifyHandler.class);        

    /**
     * @@roseuid 40C0755102BF
     */
    public WEB3IfoCloseNotifyHandler() 
    {
     
    }
    
    /**
     * (�敨OP�����ʒm�������N�G�X�g)<BR>
     * �敨OP�����ʒm���������{����B<BR>
     * <BR>
     * �敨OP�����ʒm�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���I�v�V���������ʒm���N�G�X�g�I�u�W�F�N�g
     * @@throws WEB3BaseException 
     * @@return webbroker3.ifo.message.WEB3IfoCloseOrderResponse
     * @@roseuid 4057B7C9039C
     */
    public WEB3IfoCloseOrderResponse ifoCloseNotifyRequest(WEB3IfoCloseOrderRequest l_request) throws WEB3BaseException 
    {
        log.debug("Enter the method(ifoCloseNotifyRequest).");
        final String STR_METHOD_NAME = "ifoCloseNotifyRequest(WEB3IfoCloseOrderRequest l_request)";  
        
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoCloseOrderResponse l_response = null;
        WEB3IfoCloseNotifyService l_closeNotify = null;
        try
        {
            log.debug("Get the   �敨OP�����ʒm�T�[�r�X");
            l_closeNotify = 
                (WEB3IfoCloseNotifyService)Services.getService(WEB3IfoCloseNotifyService.class);
            log.debug("Succeeded to get the  �敨OP�����ʒm�T�[�r�X");
        }
        catch(Exception l_ex)
        {
            log.debug("Enter the Exception and create the exception response object.");
            l_response = (WEB3IfoCloseOrderResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002; 
            log.error(
                l_request,
                "�敨OP�����ʒm�T�[�r�X���擾�����s",
                l_response.errorInfo,
                l_ex);
            log.debug("Exit the Exception and create the exception response object.");
            return l_response;
        }
        try
        {
            log.debug(" get the WEB3IfoCloseOrderResponse Object");
            l_response = (WEB3IfoCloseOrderResponse)l_closeNotify.execute(l_request);
            log.debug(" Succeeded to get the WEB3IfoCloseOrderResponse Object");
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("Enter the Exception and create the exception response object.");
            l_response = (WEB3IfoCloseOrderResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(l_request,
                "�敨OP�����ʒmResponse Object�擾���s",
                l_ex); 
            log.debug("Exit the Exception and create the exception response object."); 
            return l_response;                     
        }
        return l_response;
    }
}
@
