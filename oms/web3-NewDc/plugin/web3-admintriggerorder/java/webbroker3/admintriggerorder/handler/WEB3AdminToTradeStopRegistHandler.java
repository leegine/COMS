head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�o�^�n���h��(WEB3AdminToTradeStopRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04�@@�A����(���u) �V�K�쐬
*/
package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�o�^�n���h��)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�o�^�n���h��<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminToTradeStopRegistHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopRegistHandler.class);
   
    /**
     * @@roseuid 4430DC7702EE
     */
    public WEB3AdminToTradeStopRegistHandler() 
    {
     
    }
    
    /**
     * (confirm�o�^)<BR>
     * �戵��~�o�^�m�F�������s���B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�o�^�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopRegConfirmResponse
     * @@roseuid 4410D57B01F2
     */
    public WEB3AdminToTradeStopRegConfirmResponse confirmRegist(WEB3AdminToTradeStopRegConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmRegist(WEB3AdminToTradeStopRegConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopRegConfirmResponse l_response = null;
        WEB3AdminToTradeStopRegistService l_service = null;
        
        try
        {            
            //get�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X
            l_service = (WEB3AdminToTradeStopRegistService) Services.getService(WEB3AdminToTradeStopRegistService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopRegConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ��ҁE�戵��~�o�^)�戵��~�o�^�m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ��ҁE�戵��~�o�^)�戵��~�o�^�m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete�o�^)<BR>
     * �戵��~�o�^�����������s���B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�o�^�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopRegCompleteResponse
     * @@roseuid 4410D6E103E6
     */
    public WEB3AdminToTradeStopRegCompleteResponse completeRegist(WEB3AdminToTradeStopRegCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeRegist(WEB3AdminToTradeStopRegCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopRegCompleteResponse l_response = null;
        WEB3AdminToTradeStopRegistService l_service = null;
        
        try
        {            
            //get�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X
            l_service = (WEB3AdminToTradeStopRegistService) Services.getService(WEB3AdminToTradeStopRegistService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopRegCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ��ҁE�戵��~�o�^)�戵��~�o�^���������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ��ҁE�戵��~�o�^)�戵��~�o�^���������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
