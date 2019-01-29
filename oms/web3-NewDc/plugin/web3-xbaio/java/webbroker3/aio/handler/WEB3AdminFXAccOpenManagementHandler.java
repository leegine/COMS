head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenManagementHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���FX�����J�݊Ǘ��n���h���N���X(WEB3AdminFXAccOpenManagementHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 ������ (���u) �V�K�쐬
                 : 2006/02/08 杊��] (���u) �d�l�ύX�E���f��466
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminFXAccOpenApplyDownloadRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyDownloadResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListConditionRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListConditionResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminFXAccOpenManagementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���FX�����J�݊Ǘ��n���h��) <BR>
 * �Ǘ���FX�����J�݊Ǘ��n���h���N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenManagementHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenManagementHandler.class);

    /**
     * (get�������͉��) <BR>
     * (�Ǘ���FX�����J�݊Ǘ�)�����J�ݐ\�������������� <BR>
     * ��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ���FX�����J�݊Ǘ��T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B
     * 
     * @@param l_request - �Ǘ��ҁEFX�����J�ݐ\���ꗗ�������̓��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccOpenApplyListConditionResponse
     * @@roseuid 41BD52F20183
     */
    public WEB3AdminFXAccOpenApplyListConditionResponse getCondInputScreen(
        WEB3AdminFXAccOpenApplyListConditionRequest l_request)
    {
        final String STR_METHOD_NAME = "getCondInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ���FX�����J�݊Ǘ��T�[�r�X���擾��
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenApplyListConditionResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccOpenManagementService) 
                Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenApplyListConditionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���FX�����J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXAccOpenApplyListConditionResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenApplyListConditionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(�Ǘ���FX�����J�݊Ǘ�)���͉�ʕ\�����������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ꗗ���) <BR>
     * (�Ǘ���FX�����J�݊Ǘ�)�����J�ݐ\���������� <BR>
     * �ꗗ��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ���FX�����J�݊Ǘ��T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B
     * @@param l_request - �Ǘ��ҁEFX�����J�ݐ\���ꗗ���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccOpenApplyListResponse
     * @@roseuid 41BD534F025D
     */
    public WEB3AdminFXAccOpenApplyListResponse getListScreen(
        WEB3AdminFXAccOpenApplyListRequest l_request)
    {
        final String STR_METHOD_NAME = "getListScreen()";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ���FX�����J�݊Ǘ��T�[�r�X���擾��
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenApplyListResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccOpenManagementService) 
                Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenApplyListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���FX�����J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXAccOpenApplyListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenApplyListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(�Ǘ���FX�����J�݊Ǘ�)�����J�ݐ\���������ʈꗗ��ʕ\�����������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�X�e�[�^�X�X�V���͉��) <BR>
     * (�Ǘ���FX�����J�݊Ǘ�)�����J�݃X�e�[�^�X�X�V���͉�ʕ\���������s���B <BR>
     * �Ǘ���FX�����J�݊Ǘ��T�[�r�XImpl���擾�� �A execute()���\�b�h���R�[������B<BR>
     * 
     * @@param l_request - �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���̓��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccOpenStatusUpdInputResponse
     * @@roseuid 41BD53B00106
     */
    public WEB3AdminFXAccOpenStatusUpdInputResponse getStatusUpdInputScreen(
        WEB3AdminFXAccOpenStatusUpdInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getStatusUpdInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ���FX�����J�݊Ǘ��T�[�r�X���擾��
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenStatusUpdInputResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccOpenManagementService) 
                Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���FX�����J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXAccOpenStatusUpdInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(�Ǘ���FX�����J�݊Ǘ�)�X�V���͉�ʕ\�����������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirm�X�e�[�^�X�X�V) <BR>
     * (�Ǘ���FX�����J�݊Ǘ�)�����J�݃X�e�[�^�X�X�V�m�F�������s���B <BR>
     * <BR>
     * �Ǘ���FX�����J�݊Ǘ��T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * 
     * @@param l_request - �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�m�F���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccOpenStatusUpdConfirmResponse
     * @@roseuid 41BD53E902EA
     */
    public WEB3AdminFXAccOpenStatusUpdConfirmResponse confirmStatusUpd(
        WEB3AdminFXAccOpenStatusUpdConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmStatusUpd()";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ���FX�����J�݊Ǘ��T�[�r�X���擾��
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenStatusUpdConfirmResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccOpenManagementService) 
                Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���FX�����J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXAccOpenStatusUpdConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(�Ǘ���FX�����J�݊Ǘ�)�����J�݃X�e�[�^�X�X�V�m�F���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete�X�e�[�^�X�X�V) <BR>
     * (�Ǘ���FX�����J�݊Ǘ�)�����J�݃X�e�[�^�X�X�V�����������s���B <BR>
     * <BR>
     * �Ǘ���FX�����J�݊Ǘ��T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B
     * 
     * @@param l_request - �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccOpenStatusUpdCompleteResponse
     * @@roseuid 41BD543F023E
     */
    public WEB3AdminFXAccOpenStatusUpdCompleteResponse completeStatusUpd(
        WEB3AdminFXAccOpenStatusUpdCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeStatusUpd()";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ���FX�����J�݊Ǘ��T�[�r�X���擾��
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenStatusUpdCompleteResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccOpenManagementService) 
                Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���FX�����J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXAccOpenStatusUpdCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(�Ǘ���FX�����J�݊Ǘ�)�����J�݃X�e�[�^�X�X�V�������������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �Ǘ���FX�����J�ݐ\���_�E�����[�h�t�@@�C���f�[�^�擾�������s���B<BR>
     * <BR>
     * �Ǘ���FX�����J�݊Ǘ��T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param  l_request - �Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���N�G�X�g<BR>
     * @@return WEB3AdminFXAccOpenApplyDownloadResponse
     */
    public WEB3AdminFXAccOpenApplyDownloadResponse getDownloadFile(
        WEB3AdminFXAccOpenApplyDownloadRequest l_request)
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminFXAccOpenApplyDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        //�Ǘ���FX�����J�݊Ǘ��T�[�r�XImpl���擾��
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenApplyDownloadResponse l_response = null;
        
        try
        {
            l_service = 
                (WEB3AdminFXAccOpenManagementService) Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXAccOpenApplyDownloadResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���FX�����J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXAccOpenApplyDownloadResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenApplyDownloadResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ���FX�����J�ݐ\���_�E�����[�h�t�@@�C���f�[�^�擾���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenApplyDownloadResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ���FX�����J�ݐ\���_�E�����[�h�t�@@�C���f�[�^�擾���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
