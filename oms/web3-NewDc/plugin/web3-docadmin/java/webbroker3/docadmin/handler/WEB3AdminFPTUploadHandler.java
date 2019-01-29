head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���A�b�v���[�h�n���h��(WEB3AdminFPTUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 ���g (���u) �V�K�쐬 ���f�� No.012,No.014
*/

package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTUploadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҋ����@@��t�{���A�b�v���[�h�n���h��)<BR>
 * �Ǘ��ҋ����@@��t�{���A�b�v���[�h�n���h��<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTUploadHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadHandler.class);

    /**
     * @@roseuid 4758B2790128
     */
    public WEB3AdminFPTUploadHandler()
    {

    }

    /**
     * (�A�b�v���[�h��ʕ\��)<BR>
     * �����@@��t�{���A�b�v���[�h���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTUploadInputResponse
     * @@roseuid 4737F458021C
     */
    public WEB3AdminFPTUploadInputResponse uploadScreenDisplay(WEB3AdminFPTUploadInputRequest l_request)
    {
        final String STR_METHOD_NAME = "uploadScreenDisplay(WEB3AdminAccOpenApplyUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTUploadInputResponse l_response = null;
        WEB3AdminFPTUploadService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminFPTUploadService)Services.getService(
                    WEB3AdminFPTUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminFPTUploadInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{���A�b�v���[�h���͏����ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�A�b�v���[�h�t�@@�C���m�F)<BR>
     * �����@@��t�{���A�b�v���[�hj�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTUploadConfirmResponse
     * @@roseuid 4737F46B0112
     */
    public WEB3AdminFPTUploadConfirmResponse uploadFileConfirm(WEB3AdminFPTUploadConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "uploadFileConfirm(WEB3AdminFPTUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTUploadConfirmResponse l_response = null;
        WEB3AdminFPTUploadService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminFPTUploadService)Services.getService(
                    WEB3AdminFPTUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminFPTUploadConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (l_ex.getErrorMessage() != null && !"".equals(l_ex.getErrorMessage()))
            {
                if (!(l_ex instanceof WEB3SystemLayerException || l_ex instanceof WEB3BusinessLayerException))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{���A�b�v���[�h�m�F�����ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�����@@��t�{���A�b�v���[�h)<BR>
     * �����@@��t�{���A�b�v���[�h������ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTUploadCompleteResponse
     * @@roseuid 4737F47E0343
     */
    public WEB3AdminFPTUploadCompleteResponse adminFPTUpload(WEB3AdminFPTUploadCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "adminFPTUpload(WEB3AdminFPTUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTUploadCompleteResponse l_response = null;
        WEB3AdminFPTUploadService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminFPTUploadService)Services.getService(
                    WEB3AdminFPTUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminFPTUploadCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (l_ex.getErrorMessage() != null && !"".equals(l_ex.getErrorMessage()))
            {
                if (!(l_ex instanceof WEB3SystemLayerException || l_ex instanceof WEB3BusinessLayerException))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{���A�b�v���[�h���������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�A�b�v���[�h���~)<BR>
     * �����@@��t�{���A�b�v���[�h���~��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTUploadCancelResponse
     * @@roseuid 4737F49200B2
     */
    public WEB3AdminFPTUploadCancelResponse uploadCancel(WEB3AdminFPTUploadCancelRequest l_request)
    {
        final String STR_METHOD_NAME = "uploadCancel(WEB3AdminFPTUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTUploadCancelResponse l_response = null;
        WEB3AdminFPTUploadService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminFPTUploadService)Services.getService(
                    WEB3AdminFPTUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminFPTUploadCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{���A�b�v���[�h���~�����ɃG���[���������܂����B",
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
