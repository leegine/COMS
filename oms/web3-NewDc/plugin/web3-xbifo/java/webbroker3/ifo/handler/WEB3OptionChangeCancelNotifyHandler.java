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
filename	WEB3OptionChangeCancelNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V������������ʒm�n���h���N���X(WEB3OptionChangeCancelNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/17 Ḗ@@�� (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;

import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.message.WEB3OptionsChangeCancelNotifyRequest;
import webbroker3.ifo.message.WEB3OptionsChangeCancelNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyService;

/**
 * (OP��������ʒm�n���h��)<BR>
 * �����w���I�v�V������������ʒm�n���h���N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3OptionChangeCancelNotifyHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeCancelNotifyHandler.class);    
    /**
     * @@roseuid 40C075500203
     */
    public WEB3OptionChangeCancelNotifyHandler() 
    {
     
    }
    
    /**
     * (�I�v�V������������ʒm���N�G�X�g)<BR>
     * �����w���I�v�V������������ʒm���������{����B<BR>
     * <BR>
     * �����w���I�v�V������������ʒm�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V������������ʒm���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3OptionsChangeCancelNotifyResponse
     * @@roseuid 4057D2FE00AE
     */
    public WEB3OptionsChangeCancelNotifyResponse optionChangeCancelNotifyRequest(WEB3OptionsChangeCancelNotifyRequest l_request) throws WEB3BaseException 
    {

        final String STR_METHOD_NAME = "optionChangeCancelNotifyRequest(WEB3OptionsChangeCancelNotifyRequest l_request)";
        WEB3OptionsChangeCancelNotifyResponse l_response = null;
        WEB3OptionChangeCancelNotifyService l_changeCancelNotify = null;
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
            log.debug("Enter the try path and get the WEB3OptionChangeCancelNotifyServiceImpl object.");
            l_changeCancelNotify = 
                (WEB3OptionChangeCancelNotifyService)Services.getService(WEB3OptionChangeCancelNotifyService.class);
            log.debug("Exit the try path and succeed to get the WEB3OptionChangeCancelNotifyServiceImpl object.");
        }
        catch(Exception l_ex)
        {
            log.debug("Enter the catch path and create the response about exception."); 
            l_response = (WEB3OptionsChangeCancelNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002; 
            log.error(l_request,
                "�����w���I�v�V������������ʒm�T�[�r�X�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex); 
            log.debug("Exit the catch path and create the response about exception."); 
            return l_response;  
        }
        try
        {
            log.debug("Enter the try path and get the WEB3OptionsChangeCancelNotifyResponse object.");
            l_response = 
                (WEB3OptionsChangeCancelNotifyResponse)l_changeCancelNotify.execute(l_request);
            log.debug("Exit the try path and get the WEB3OptionsChangeCancelNotifyResponse object.");

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("Enter the catch path and create the response to deal with the exception."); 
            l_response = (WEB3OptionsChangeCancelNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(l_request,
                "�����w���I�v�V������������ʒm�Ɏ��s���܂���",
                l_ex); 
            log.debug("Exit the catch path and create the response to deal with the exception."); 
            return l_response;          
        }
        log.exiting(STR_METHOD_NAME);
        log.debug("End to test the method :OptionChangeCancelNotifyRequest");
        return l_response;
        }

    }

@
