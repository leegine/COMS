head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferManagementHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�U�֊Ǘ��n���h���N���X(WEB3AdminFXTransferManagementHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 ������ (���u) �V�K�쐬
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminFXTransferCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXTransferCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXTransferCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXTransferCancelConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXTransferListConditionRequest;
import webbroker3.aio.message.WEB3AdminFXTransferListConditionResponse;
import webbroker3.aio.message.WEB3AdminFXTransferListRequest;
import webbroker3.aio.message.WEB3AdminFXTransferListResponse;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferManagementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX�U�֊Ǘ��n���h��) <BR>
 * FX�U�֊Ǘ��n���h���N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AdminFXTransferManagementHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferManagementHandler.class);

    /**
     * (�������͉�ʕ\��) <BR>
     * �U�֏������͉�ʕ\���������s���B <BR>
     * <BR>
     * FX�U�֊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminFXTransferListConditionResponse
     * @@roseuid 41C0FA3303B7
     */
    public WEB3AdminFXTransferListConditionResponse condInputScreenDisplay(
        WEB3AdminFXTransferListConditionRequest l_request)
    {
        final String STR_METHOD_NAME = "condInputScreenDisplay()";
        log.entering(STR_METHOD_NAME);
        
        //FX�U�֊Ǘ��T�[�r�X���擾��
        WEB3AdminFXTransferManagementService l_service = null;
        WEB3AdminFXTransferListConditionResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXTransferManagementService) 
                Services.getService(WEB3AdminFXTransferManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXTransferListConditionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX�U�֊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXTransferListConditionResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXTransferListConditionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�U�֊Ǘ��̏������͉�ʕ\���f�[�^�̎擾���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�ꗗ��ʕ\��) <BR>
     * �U�ֈꗗ��ʕ\���������s���B <BR>
     * <BR>
     * FX�U�֊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminFXTransferListResponse
     * @@roseuid 41C0FA3E0118
     */
    public WEB3AdminFXTransferListResponse listScreenDisplay(
        WEB3AdminFXTransferListRequest l_request)
    {
        final String STR_METHOD_NAME = "listScreenDisplay()";
        log.entering(STR_METHOD_NAME);
        
        //FX�U�֊Ǘ��T�[�r�X���擾��
        WEB3AdminFXTransferManagementService l_service = null;
        WEB3AdminFXTransferListResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXTransferManagementService) 
                Services.getService(WEB3AdminFXTransferManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXTransferListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX�U�֊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXTransferListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXTransferListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�U�֊Ǘ��̈ꗗ��ʕ\���f�[�^�̎擾���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (����m�F) <BR>
     * �U�֎���m�F�������s���B <BR>
     * <BR>
     * FX�U�֊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminFXTransferCancelConfirmResponse
     * @@roseuid 41C0FA440379
     */
    public WEB3AdminFXTransferCancelConfirmResponse cancelConfirm(
        WEB3AdminFXTransferCancelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "cancelConfirm()";
        log.entering(STR_METHOD_NAME);

        //FX�U�֊Ǘ��T�[�r�X���擾��
        WEB3AdminFXTransferManagementService l_service = null;
        WEB3AdminFXTransferCancelConfirmResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXTransferManagementService) 
                Services.getService(WEB3AdminFXTransferManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXTransferCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX�U�֊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXTransferCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXTransferCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�U�֊Ǘ��̎�������̊m�F�����s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�������) <BR>
     * �U�֎�������������s���B <BR>
     * <BR>
     * FX�U�֊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminFXTransferCancelCompleteResponse
     * @@roseuid 41C0FA480231
     */
    public WEB3AdminFXTransferCancelCompleteResponse cancelComplete(
        WEB3AdminFXTransferCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "cancelComplete()";
        log.entering(STR_METHOD_NAME);

        //FX�U�֊Ǘ��T�[�r�X���擾��
        WEB3AdminFXTransferManagementService l_service = null;
        WEB3AdminFXTransferCancelCompleteResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXTransferManagementService) 
                Services.getService(WEB3AdminFXTransferManagementService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXTransferCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX�U�֊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminFXTransferCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXTransferCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�U�֊Ǘ��̒�������̓o�^�����s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
