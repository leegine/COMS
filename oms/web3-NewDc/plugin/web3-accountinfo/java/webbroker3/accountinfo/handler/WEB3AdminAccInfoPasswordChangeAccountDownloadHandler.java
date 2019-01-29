head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordChangeAccountDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�������(WEB3AdminAccInfoPasswordChangeAccountDownloadHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�������)<BR>
 * �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�������<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordChangeAccountDownloadHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoPasswordChangeAccountDownloadHandler.class);   
    
    /**
     * @@roseuid 418F3A11001F
     */
    public WEB3AdminAccInfoPasswordChangeAccountDownloadHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �Ïؔԍ��ύX�ڋq�_�E�����[�h�⍇����ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8C6201C1
     */
    public WEB3AdminAccInfoPasswordChangeAccountInquiryResponse inputScreenDisplay(WEB3AdminAccInfoPasswordChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoPasswordChangeAccountInquiryResponse l_response = null;
        WEB3AdminAccInfoPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoPasswordChangeAccountDownloadService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountInquiryResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�ނ̓��͉�ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (�_�E�����[�h��ʕ\��)<BR>
     * �Ïؔԍ��ύX�ڋq�_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8C6201E0
     */
    public WEB3AdminAccInfoPasswordChangeAccountDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoPasswordChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " downloadScreenDisplay(WEB3AdminAccInfoPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoPasswordChangeAccountDownloadResponse l_response = null;
        WEB3AdminAccInfoPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoPasswordChangeAccountDownloadService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�ނ̃_�E�����[�h��ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (�Ïؔԍ��ύX�ڋq�_�E�����[�h)<BR>
     * �Ïؔԍ��ύX�ڋq�_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8C6201EF
     */
    public WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse passwordChangeAccountDownload(WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " passwordChangeAccountDownload(WEB3AdminAccInfoPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse l_response = null;
        WEB3AdminAccInfoPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoPasswordChangeAccountDownloadService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�ނ̈Ïؔԍ��ύX�ڋq�_�E�����[�h�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
