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
filename	WEB3AdminToTradeStopDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�폜�n���h��(WEB3AdminToTradeStopDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04�@@�A����(���u) �V�K�쐬
*/
package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopDeleteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�폜�n���h��)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�폜�n���h��<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminToTradeStopDeleteHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopDeleteHandler.class);
    
    /**
     * @@roseuid 4430DB3F006D
     */
    public WEB3AdminToTradeStopDeleteHandler() 
    {
     
    }
    
    /**
     * (confirm�폜)<BR>
     * �戵��~�폜�m�F�������s���B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�XImpl.<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�폜�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopDelConfirmResponse
     * @@roseuid 4410D20B025F
     */
    public WEB3AdminToTradeStopDelConfirmResponse confirmDelete(WEB3AdminToTradeStopDelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmDelete(WEB3AdminToTradeStopDelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopDelConfirmResponse l_response = null;
        WEB3AdminToTradeStopDeleteService l_service = null;
        
        try
        {            
            //get�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X
            l_service = (WEB3AdminToTradeStopDeleteService) Services.getService(WEB3AdminToTradeStopDeleteService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopDelConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ��ҁE�戵��~�폜)�戵��~�폜�m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ��ҁE�戵��~�폜)�戵��~�폜�m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete�폜)<BR>
     * �戵��~�폜�����������s���B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�XImpl.<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�폜�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopDelCompleteResponse
     * @@roseuid 4410D29302EC
     */
    public WEB3AdminToTradeStopDelCompleteResponse completeDelete(WEB3AdminToTradeStopDelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeDelete(WEB3AdminToTradeStopDelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopDelCompleteResponse l_response = null;
        WEB3AdminToTradeStopDeleteService l_service = null;
        
        try
        {            
            //get�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X
            l_service = (WEB3AdminToTradeStopDeleteService) Services.getService(WEB3AdminToTradeStopDeleteService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopDelCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ��ҁE�戵��~�폜)�戵��~�폜���������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ��ҁE�戵��~�폜)�戵��~�폜���������̎��{�Ɏ��s���܂����B",
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
