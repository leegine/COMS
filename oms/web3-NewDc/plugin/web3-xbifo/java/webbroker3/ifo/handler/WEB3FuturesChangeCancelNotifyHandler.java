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
filename	WEB3FuturesChangeCancelNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨��������ʒm�n���h��(WEB3FuturesChangeCancelNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/21 Ḗ@@�� (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.message.WEB3FuturesChangeCancelNotifyRequest;
import webbroker3.ifo.message.WEB3FuturesChangeCancelNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyService;

/**
 * (�敨��������ʒm�n���h��)<BR>
 * �敨��������ʒm�n���h���N���X<BR>
 */
public class WEB3FuturesChangeCancelNotifyHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeCancelNotifyHandler.class);        
    /**
     * @@roseuid 40F7B072034B
     */
    public WEB3FuturesChangeCancelNotifyHandler() 
    {
     
    }
    
    /**
     * (�敨��������ʒm���N�G�X�g)<BR>
     * �����w���敨��������ʒm���������{����B<BR>
     * <BR>
     * �����w���敨��������ʒm�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨��������ʒm���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3FuturesChangeCancelNotifyResponse
     * @@roseuid 40A89D710113
     */
    public WEB3FuturesChangeCancelNotifyResponse WEB3FuturesChangeCancelNotifyRequest(WEB3FuturesChangeCancelNotifyRequest l_request) throws WEB3SystemLayerException 
    {
        log.debug("Start to test the method :FuturesChangeCancelNotifyRequest");
        final String STR_METHOD_NAME = "FuturesChangeCancelNotifyRequest(WEB3FuturessChangeCancelNotifyRequest l_request)";
        WEB3FuturesChangeCancelNotifyResponse l_response = null;
        WEB3FuturesChangeCancelNotifyService l_changeCancelNotify = null;
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
            log.debug("Enter the if path that the l_request parameter is null.");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        try
        {
            l_changeCancelNotify = 
                (WEB3FuturesChangeCancelNotifyService)Services.getService(WEB3FuturesChangeCancelNotifyService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3FuturesChangeCancelNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002; 
            log.error(l_request,
                "�����w���敨��������ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex); 
            return l_response;  
        }
        try
        {
            l_response = 
                (WEB3FuturesChangeCancelNotifyResponse)l_changeCancelNotify.execute(l_request);

        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesChangeCancelNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(l_request,
                "�����w���敨��������ʒm�Ɏ��s���܂����B",
                l_ex); 
            return l_response;          
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
}
@
