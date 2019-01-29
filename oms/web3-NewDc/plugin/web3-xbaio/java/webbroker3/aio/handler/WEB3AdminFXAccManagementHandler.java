head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccManagementHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���FX�����Ǘ��n���h���N���X(WEB3AdminFXAccManagementHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/26 �����(���u)�V�K�쐬
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeInputResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchConditionRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchConditionResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchResponse;
import webbroker3.aio.service.delegate.WEB3AdminFXAccManagementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���FX�����Ǘ��n���h��) <BR>
 * �Ǘ���FX�����Ǘ��n���h���N���X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminFXAccManagementHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccManagementHandler.class);

    /**
     * @@roseuid 41E7934A01D4
     */
    public WEB3AdminFXAccManagementHandler()
    {
    }

    /**
     * (get�������͉��) <BR>
     * (�Ǘ���FX�����Ǘ�)������񌟍��������͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ���FX�����Ǘ��T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B
     * 
     * @@param l_request - �Ǘ��ҁEFX������񌟍��������̓��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccInfoSearchConditionResponse
     * @@roseuid 41BD352201F0
     */
    public WEB3AdminFXAccInfoSearchConditionResponse getCondInputScreen(
        WEB3AdminFXAccInfoSearchConditionRequest l_request)
    {
        final String STR_METHOD_NAME = "getCondInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ���FX�����Ǘ��T�[�r�X���擾��
        WEB3AdminFXAccManagementService l_service = null;
        WEB3AdminFXAccInfoSearchConditionResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccManagementService) 
                Services.getService(WEB3AdminFXAccManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoSearchConditionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���FX�����Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXAccInfoSearchConditionResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoSearchConditionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(�Ǘ���FX�����Ǘ�)������񌟍��������͉�ʕ\�����������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get��������) <BR> 
     * (�Ǘ���FX�����Ǘ�)������񌟍��������s���B <BR>
     * <BR>
     * �Ǘ���FX�����Ǘ��T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * 
     * @@param l_request - �Ǘ��ҁEFX������񌟍����N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccInfoSearchResponse
     * @@roseuid 41BD3607020F
     */
    public WEB3AdminFXAccInfoSearchResponse getQueryResult(
        WEB3AdminFXAccInfoSearchRequest l_request)
    {
        final String STR_METHOD_NAME = "getQueryResult()";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ���FX�����Ǘ��T�[�r�X���擾��
        WEB3AdminFXAccManagementService l_service = null;
        WEB3AdminFXAccInfoSearchResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccManagementService) 
                Services.getService(WEB3AdminFXAccManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoSearchResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���FX�����Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXAccInfoSearchResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(�Ǘ���FX�����Ǘ�)������񌟍����������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ύX���͉��) <BR>
     * (�Ǘ���FX�����Ǘ�)�������ύX���͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ���FX�����Ǘ��T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * 
     * @@param l_request - �Ǘ��ҁEFX�������ύX���̓��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccInfoChangeInputResponse
     * @@roseuid 41BD363E0329
     */
    public WEB3AdminFXAccInfoChangeInputResponse getUpdInputScreen(
        WEB3AdminFXAccInfoChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getUpdInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ���FX�����Ǘ��T�[�r�X���擾��
        WEB3AdminFXAccManagementService l_service = null;
        WEB3AdminFXAccInfoChangeInputResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccManagementService) 
                Services.getService(WEB3AdminFXAccManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���FX�����Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXAccInfoChangeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(�Ǘ���FX�����Ǘ�)�������ύX���͉�ʕ\�����������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirm�ύX) <BR>
     * (�Ǘ���FX�����Ǘ�)�������ύX�m�F�������s���B <BR>
     * <BR>
     * �Ǘ���FX�����Ǘ��T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * 
     * @@param l_request - �Ǘ��ҁEFX�������ύX�m�F���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccInfoChangeConfirmResponse
     * @@roseuid 41BD36830357
     */
    public WEB3AdminFXAccInfoChangeConfirmResponse confirmChanger(
        WEB3AdminFXAccInfoChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmChanger()";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ���FX�����Ǘ��T�[�r�X���擾��
        WEB3AdminFXAccManagementService l_service = null;
        WEB3AdminFXAccInfoChangeConfirmResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccManagementService) 
                Services.getService(WEB3AdminFXAccManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���FX�����Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXAccInfoChangeConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(�Ǘ���FX�����Ǘ�)�������ύX�m�F���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete�ύX) <BR>
     * (�Ǘ���FX�����Ǘ�)�������ύX�����������s���B <BR>
     * <BR>
     * �Ǘ���FX�����Ǘ��T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * 
     * @@param l_request - �Ǘ��ҁEFX�������ύX�������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccInfoChangeCompleteResponse
     * @@roseuid 41BD36AA003B
     */
    public WEB3AdminFXAccInfoChangeCompleteResponse completeChange(
        WEB3AdminFXAccInfoChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeChange()";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ���FX�����Ǘ��T�[�r�X���擾��
        WEB3AdminFXAccManagementService l_service = null;
        WEB3AdminFXAccInfoChangeCompleteResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccManagementService) 
                Services.getService(WEB3AdminFXAccManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���FX�����Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXAccInfoChangeCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(�Ǘ���FX�����Ǘ�)�������ύX�������������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
