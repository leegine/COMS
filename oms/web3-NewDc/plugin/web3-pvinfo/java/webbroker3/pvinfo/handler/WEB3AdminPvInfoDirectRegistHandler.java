head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃_�C���N�g�w��o�^�n���h��(WEB3AdminPvInfoDirectRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/27 ������(���u) �쐬
Revesion History : 2007/06/28 ���G��(���u) �����̖��003
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃_�C���N�g�w��o�^�n���h��)<BR>
 * �Ǘ��҃_�C���N�g�w��o�^�n���h���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectRegistHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectRegistHandler.class);
  
    /**
     * (get�_�C���N�g�w��o�^���͉��)<BR>
     * �_�C���N�g�w��o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҃_�C���N�g�w��o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��o�^���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputResponse
     * @@roseuid 415D3F810002
     */
    public WEB3AdminPvInfoDirectRegistInputResponse getDirectRegistInputScreen(WEB3AdminPvInfoDirectRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getDirectRegistInputScreen(WEB3AdminPvInfoDirectRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistInputResponse l_response = null;
        WEB3AdminPvInfoDirectRegistService l_service = null;
        
        //�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X���擾��
        try
        {        
            l_service = (WEB3AdminPvInfoDirectRegistService)Services.getService(WEB3AdminPvInfoDirectRegistService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X���擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoDirectRegistInputResponse)l_service.execute(l_request);           
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirm�_�C���N�g�w��o�^)<BR>
     * �_�C���N�g�w��o�^�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��҃_�C���N�g�w��o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��o�^�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmResponse
     * @@roseuid 415D3FE9014A
     */
    public WEB3AdminPvInfoDirectRegistConfirmResponse confirmDirectRegist(WEB3AdminPvInfoDirectRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmDirectRegist(WEB3AdminPvInfoDirectRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistConfirmResponse l_response = null;
        WEB3AdminPvInfoDirectRegistService l_service = null;
        
        //�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoDirectRegistService)Services.getService(WEB3AdminPvInfoDirectRegistService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X���擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminPvInfoDirectRegistConfirmResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return  l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete�_�C���N�g�w��o�^)<BR>
     * �_�C���N�g�w��o�^�����������s���B<BR>
     * <BR>
     * �Ǘ��҃_�C���N�g�w��o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��o�^�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteResponse
     * @@roseuid 415D4046037C
     */
    public WEB3AdminPvInfoDirectRegistCompleteResponse completeDirectRegist(WEB3AdminPvInfoDirectRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeDirectRegist(WEB3AdminPvInfoDirectRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistCompleteResponse l_response = null;
        WEB3AdminPvInfoDirectRegistService l_service = null;
        
        //�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoDirectRegistService)Services.getService(WEB3AdminPvInfoDirectRegistService.class);            
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X���擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoDirectRegistCompleteResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return  l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undo�_�C���N�g�w��o�^�A�b�v���[�h)<BR>
     * �_�C���N�g�w��o�^�A�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �Ǘ��҃_�C���N�g�w��o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��o�^�A�b�v���[�h���~���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelResponse
     * @@roseuid 415D416600E9
     */
    public WEB3AdminPvInfoDirectRegistCancelResponse undoDirectRegistUpload(WEB3AdminPvInfoDirectRegistCancelRequest l_request)
    {
        final String STR_METHOD_NAME = " undoDirectRegistUpload(WEB3AdminPvInfoDirectRegistCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistCancelResponse l_response = null;
        WEB3AdminPvInfoDirectRegistService l_service = null;
        //�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoDirectRegistService)Services.getService(WEB3AdminPvInfoDirectRegistService.class);            
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X���擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoDirectRegistCancelResponse)l_service.execute(l_request);                      
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
