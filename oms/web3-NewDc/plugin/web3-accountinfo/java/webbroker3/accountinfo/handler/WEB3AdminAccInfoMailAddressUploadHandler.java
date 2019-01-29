head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X����۰�������(WEB3AdminAccInfoMailAddressUploadHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/14 ������ (���u) �V�K�쐬
 */

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCancelRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCancelResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X����۰�������)<BR>
 * �Ǘ��҂��q�l��񃁁[���A�h���X����۰�������<BR>
 * 
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoMailAddressUploadHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressUploadHandler.class);

    public WEB3AdminAccInfoMailAddressUploadHandler()
    {

    }

    /**
     * (�A�b�v���[�h��ʕ\��)<BR>
     * ���[���A�h���X�A�b�v���[�h��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���̓��N�G�X�g<BR>
     * <BR>
     * @@return WEB3AdminAccInfoMailAddressUploadInputResponse<BR>
     */
    public WEB3AdminAccInfoMailAddressUploadInputResponse uploadScreenDisplay(
            WEB3AdminAccInfoMailAddressUploadInputRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "uploadScreenDisplay(WEB3AdminAccInfoMailAddressUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressUploadInputResponse l_response = null;
        WEB3AdminAccInfoMailAddressUploadService l_service = null;

        // �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressUploadService) Services.getService(
                WEB3AdminAccInfoMailAddressUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            return l_response;
        }

        // �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�މ�ʕ\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
        
    }

    /**
     * (�A�b�v���[�h�t�@@�C���m�F)<BR>
     * ���[���A�h���X�A�b�v���[�h�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�m�F���N�G�X�g<BR>
     * <BR>
     * @@return WEB3AdminAccInfoMailAddressUploadConfirmResponse<BR>
     */
    public WEB3AdminAccInfoMailAddressUploadConfirmResponse uploadFileConfirm(
        WEB3AdminAccInfoMailAddressUploadConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "uploadFileConfirm(WEB3AdminAccInfoMailAddressUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoMailAddressUploadConfirmResponse l_response = null;
        WEB3AdminAccInfoMailAddressUploadService l_service = null;

        // �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressUploadService) Services.getService(
                WEB3AdminAccInfoMailAddressUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            return l_response;
        }

        // �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�ފm�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }

    /**
     * (���[���A�h���X�A�b�v���[�h)<BR>
     * ���[���A�h���X�A�b�v���[�h�����������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������<BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�������N�G�X�g<BR>
     * <BR>
     * @@return WEB3AdminAccInfoMailAddressUploadCompleteResponse<BR>
     */
    public WEB3AdminAccInfoMailAddressUploadCompleteResponse mailAddressUpload(
        WEB3AdminAccInfoMailAddressUploadCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "mailAddressUpload(WEB3AdminAccInfoMailAddressUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoMailAddressUploadCompleteResponse l_response = null;
        WEB3AdminAccInfoMailAddressUploadService l_service = null;
        
        // �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressUploadService) Services.getService(
                WEB3AdminAccInfoMailAddressUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            return l_response;
        }

        // �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�ފ��������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }

    /**
     * (�A�b�v���[�h���~)<BR>
     * ���[���A�h���X�A�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���~���N�G�X�g<BR>
     * <BR>
     * @@return WEB3AdminAccInfoMailAddressUploadCancelResponse<BR>
     */
    public WEB3AdminAccInfoMailAddressUploadCancelResponse uploadCancel(
        WEB3AdminAccInfoMailAddressUploadCancelRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "uploadCancel(WEB3AdminAccInfoMailAddressUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoMailAddressUploadCancelResponse l_response = null;
        WEB3AdminAccInfoMailAddressUploadService l_service = null;

        // �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressUploadService) Services.getService(
                WEB3AdminAccInfoMailAddressUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            return l_response;
        }

        // �Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCancelResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�޻��޽���擾�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�Ǘ��҂��q�l��񃁁[���A�h���X����۰�ޏ����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
            
    }
}
@
