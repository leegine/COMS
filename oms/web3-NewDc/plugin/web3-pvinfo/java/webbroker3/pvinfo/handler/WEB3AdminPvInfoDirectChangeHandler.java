head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃_�C���N�g�w��ύX�n���h��(WEB3AdminPvInfoDirectChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/26 ���O�B(���u) �쐬
Revesion History : 2007/06/28 ���G��(���u) �����̖��003
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃_�C���N�g�w��ύX�n���h��)<BR>
 * �Ǘ��҃_�C���N�g�w��ύX�n���h���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectChangeHandler implements MessageHandler
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectChangeHandler.class);

    /**
     * (get�_�C���N�g�w��ύX���͉��)<BR>
     * �_�C���N�g�w��ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҃_�C���N�g�w��ύX�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��ύX���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputResponse
     * @@roseuid 416101C501E0
     */
    public WEB3AdminPvInfoDirectChangeInputResponse getDirectChangeInputScreen(WEB3AdminPvInfoDirectChangeInputRequest l_request)
    {
        
        final String STR_METHOD_NAME = " getDirectChangeInputScreen(WEB3AdminPvInfoDirectChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectChangeInputResponse l_response = null;
        WEB3AdminPvInfoDirectChangeService l_service = null;
        
        //�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X�C���^�t�F�C�X      
        try
        {
            l_service =
                (WEB3AdminPvInfoDirectChangeService)Services.getService(
                WEB3AdminPvInfoDirectChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminPvInfoDirectChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҃_�C���N�g�w��ύX�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response; 
    }

    /**
     * (confirm�_�C���N�g�w��ύX)<BR>
     * �_�C���N�g�w��ύX�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��҃_�C���N�g�w��ύX�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��ύX�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmResponse
     * @@roseuid 416101C50200
     */
    public WEB3AdminPvInfoDirectChangeConfirmResponse confirmDirectChange(WEB3AdminPvInfoDirectChangeConfirmRequest l_request)
    {
        
        final String STR_METHOD_NAME = " confirmDirectChange(WEB3AdminPvInfoDirectChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectChangeConfirmResponse l_response = null;
        WEB3AdminPvInfoDirectChangeService l_service = null;
        
        //�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminPvInfoDirectChangeService)Services.getService(WEB3AdminPvInfoDirectChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //�Ǘ��҃_�C���N�g�w��ύX�T�[�r�XImpl.execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoDirectChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�Ǘ��҃_�C���N�g�w��ύX�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;         
    }

    /**
     * (complete�_�C���N�g�w��ύX)<BR>
     * �_�C���N�g�w��ύX�����������s���B<BR>
     * <BR>
     * �Ǘ��҃_�C���N�g�w��ύX�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��ύX�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteResponse
     * @@roseuid 416101C5020F
     */
    public WEB3AdminPvInfoDirectChangeCompleteResponse completeDirectChange(WEB3AdminPvInfoDirectChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeDirectChange(WEB3AdminPvInfoDirectChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectChangeCompleteResponse l_response = null;
        WEB3AdminPvInfoDirectChangeService l_service = null;
        
        //�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminPvInfoDirectChangeService)Services.getService(WEB3AdminPvInfoDirectChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //�Ǘ��҃_�C���N�g�w��ύX�T�[�r�XImpl.execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoDirectChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�Ǘ��҃_�C���N�g�w��ύX�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
        
    }

    /**
     * (undo�_�C���N�g�w��ύX�A�b�v���[�h)<BR>
     * �_�C���N�g�w��ύX�A�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �Ǘ��҃_�C���N�g�w��ύX�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��ύX�A�b�v���[�h���~���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelResponse
     * @@roseuid 416101C5022E
     */
    public WEB3AdminPvInfoDirectChangeCancelResponse undoDirectChangeUpload(WEB3AdminPvInfoDirectChangeCancelRequest l_request)
    {
        final String STR_METHOD_NAME = " undoDirectChangeUpload(WEB3AdminPvInfoDirectChangeCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectChangeCancelResponse l_response = null;
        WEB3AdminPvInfoDirectChangeService l_service = null;
        
        //�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminPvInfoDirectChangeService)Services.getService(WEB3AdminPvInfoDirectChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X.execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoDirectChangeCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҃_�C���N�g�w��ύX�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
        
    }
}
@
