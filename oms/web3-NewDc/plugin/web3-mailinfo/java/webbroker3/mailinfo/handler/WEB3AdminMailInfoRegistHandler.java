head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.11.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����o�^�n���h��(WEB3AdminMailInfoRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���[�����o�^�n���h��)<BR>
 * ���[�����o�^�n���h���N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoRegistHandler implements MessageHandler 
{    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoRegistHandler.class);    
    
    /**
     * @@roseuid 416F1DD203D8
     */
    public WEB3AdminMailInfoRegistHandler() 
    {
     
    }
    
    /**
     * (confirm���[�����o�^)<BR>
     * ���[�����o�^�R���������s���B<BR>
     * <BR>
     * ���[�����o�^�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param  l_request - �i���N�G�X�g�f�[�^�j<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmResponse
     * @@roseuid 413C2BBE0232
     */
    public WEB3AdminMailInfoRegistConfirmResponse confirmMailInfoRegist(WEB3AdminMailInfoRegistConfirmRequest  l_request) 
    {
        final String STR_METHOD_NAME = " confirmMailInfoRegist(WEB3AdminMailInfoRegistConfirmRequest  l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoRegistConfirmResponse l_response = null;
        WEB3AdminMailInfoRegistService l_service = null;
        try
        {
            l_service = (WEB3AdminMailInfoRegistService)Services.getService(WEB3AdminMailInfoRegistService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request, "���[�����o�^�n���h���T�[�r�X�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;        
        }
        
        try
        {
            l_response = (WEB3AdminMailInfoRegistConfirmResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();   
            log.error(l_request, "���[�����o�^�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete���[�����o�^)<BR>
     * ���[�����o�^�������s���B<BR>
     * <BR>
     * ���[�����o�^�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param  l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteResponse
     * @@roseuid 413C2C3A0203
     */
    public WEB3AdminMailInfoRegistCompleteResponse completeMailInfoRegist(WEB3AdminMailInfoRegistCompleteRequest  l_request) 
    {
        final String STR_METHOD_NAME = " confirmMailInfoRegist(WEB3AdminMailInfoRegistConfirmRequest  l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoRegistCompleteResponse l_response = null;
        WEB3AdminMailInfoRegistService l_service = null;
        
        try
        {
            l_service = (WEB3AdminMailInfoRegistService)Services.getService(WEB3AdminMailInfoRegistService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request, "���[�����o�^�n���h���T�[�r�X�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;        
        }
        
        try
        {
            l_response = (WEB3AdminMailInfoRegistCompleteResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();   
            log.error(l_request, "���[�����o�^�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
}
@
