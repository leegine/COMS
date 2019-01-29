head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�������(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�������)<BR>
 * �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�������<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler.class);   
    
    /**
     * @@roseuid 418F3A0D0138
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �p�X���[�h�ύX�ڋq�_�E�����[�h�⍇����ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5B51032B
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse inputScreenDisplay(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse l_response = null;
        WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰Ă̓��͉�ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (�_�E�����[�h��ʕ\��)<BR>
     * �p�X���[�h�ύX�ڋq�_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147BBDB02E8
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse l_response = null;
        WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰Ẵ_�E�����[�h��ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (�p�X���[�h�ύX�ڋq�_�E�����[�h)<BR>
     * �p�X���[�h�ύX�ڋq�_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147BBDB02F8
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse loginPasswordChangeAccountDownload(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " loginPasswordChangeAccountDownload(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse l_response = null;
        WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰Ẵp�X���[�h�ύX�ڋq�_�E�����[�h�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
