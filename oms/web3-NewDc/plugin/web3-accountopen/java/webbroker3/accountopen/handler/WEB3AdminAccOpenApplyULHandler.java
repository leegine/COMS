head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyULHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\��UL�n���h��(WEB3AdminAccOpenApplyULHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 ���g (���u) �V�K�쐬 ���f�� No.148
*/

package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyULService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����J�ݐ\��UL�n���h��)<BR>
 * �Ǘ��Ҍ����J�ݐ\��UL�n���h��<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyULHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyULHandler.class);

    /**
     * @@roseuid 4743EF520065
     */
    public WEB3AdminAccOpenApplyULHandler()
    {

    }

    /**
     * (�A�b�v���[�h��ʕ\��)<BR>
     * �����J�ݐ\���A�b�v���[�h���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * �Ǘ��Ҍ����J�ݐ\��UL���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * <BR>
     * @@return WEB3AdminAccOpenApplyUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 472993A10184
     */
    public WEB3AdminAccOpenApplyUploadInputResponse uploadScreenDisplay(
        WEB3AdminAccOpenApplyUploadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "uploadScreenDisplay(WEB3AdminAccOpenApplyUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadInputResponse l_response = null;
        WEB3AdminAccOpenApplyULService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminAccOpenApplyULService)Services.getService(
                    WEB3AdminAccOpenApplyULService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminAccOpenApplyUploadInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�ݐ\��UL���͏����ɃG���[���������܂����B",
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
     * �����J�ݐ\���A�b�v���[�h�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * �Ǘ��Ҍ����J�ݐ\��UL�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * <BR>
     * @@return WEB3AdminAccOpenApplyUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4729947A01E9
     */
    public WEB3AdminAccOpenApplyUploadConfirmResponse uploadFileConfirm(
        WEB3AdminAccOpenApplyUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "uploadFileConfirm(WEB3AdminAccOpenApplyUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
        WEB3AdminAccOpenApplyULService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminAccOpenApplyULService)Services.getService(
                    WEB3AdminAccOpenApplyULService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminAccOpenApplyUploadConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUploadConfirmResponse)l_request.createResponse();
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
            l_response = (WEB3AdminAccOpenApplyUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�ݐ\��UL�m�F�����ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�����J�ݐ\���A�b�v���[�h)<BR>
     * �����J�ݐ\���A�b�v���[�h�����������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * �Ǘ��Ҍ����J�ݐ\��UL�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * <BR>
     * @@return WEB3AdminAccOpenApplyUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4729947B018C
     */
    public WEB3AdminAccOpenApplyUploadCompleteResponse accOpenApplyUpload(
        WEB3AdminAccOpenApplyUploadCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "accOpenApplyUpload(WEB3AdminAccOpenApplyUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadCompleteResponse l_response = null;
        WEB3AdminAccOpenApplyULService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminAccOpenApplyULService)Services.getService(
                    WEB3AdminAccOpenApplyULService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminAccOpenApplyUploadCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{���폜�����ɃG���[���������܂����B",
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
     * �����J�ݐ\���A�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * �Ǘ��Ҍ����J�ݐ\��UL���~���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return WEB3AdminAccOpenApplyUploadCancelResponse
     * @@throws WEB3BaseException
     * @@roseuid 4729947C00E0
     */
    public WEB3AdminAccOpenApplyUploadCancelResponse uploadCancel(
        WEB3AdminAccOpenApplyUploadCancelRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "uploadCancel(WEB3AdminAccOpenApplyUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadCancelResponse l_response = null;
        WEB3AdminAccOpenApplyULService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminAccOpenApplyULService)Services.getService(
                    WEB3AdminAccOpenApplyULService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminAccOpenApplyUploadCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�ݐ\��UL���~�����ɃG���[���������܂����B",
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
