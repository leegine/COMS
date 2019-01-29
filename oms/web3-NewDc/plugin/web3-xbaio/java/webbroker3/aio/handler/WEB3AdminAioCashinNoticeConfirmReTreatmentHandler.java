head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeConfirmReTreatmentHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm�m�F�ď����n���h���N���X(WEB3AdminAioCashinNoticeConfirmReTreatmentHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/20 ���r (���u) �V�K�쐬
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeInputResponse;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeSearchRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeSearchResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinNoticeConfirmReTreatmentService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����ʒm�m�F�ď����n���h��)<BR>
 * �����ʒm�m�F�ď����n���h���N���X<BR>
 * 
 * @@author ���r(���u)
 * @@version 1.0
 */

public class WEB3AdminAioCashinNoticeConfirmReTreatmentHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeConfirmReTreatmentHandler.class);    
            
    
    /**
     * (�����ʒm�������N�G�X�g)<BR>
     * �����ʒm�����������s���B<BR> 
     * <BR>
     * �����ʒm�m�F�ď����T�[�r�X���擾��<BR> 
     * execute()���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashinNoticeSearchResponse
     * @@roseuid 4108777A037B
     */
    public WEB3AdminAioCashinNoticeSearchResponse cashinNoticeSearchRequest(
        WEB3AdminAioCashinNoticeSearchRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "cashinNoticeSearchRequest(" +
            "WEB3AdminAioCashinNoticeSearchRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        //�����ʒm�m�F�ď����T�[�r�X���擾��
        WEB3AdminAioCashinNoticeConfirmReTreatmentService l_service = null;
        WEB3AdminAioCashinNoticeSearchResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioCashinNoticeConfirmReTreatmentService) 
                    Services.getService(
                        WEB3AdminAioCashinNoticeConfirmReTreatmentService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashinNoticeSearchResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����ʒm�m�F�ď����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashinNoticeSearchResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashinNoticeSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�����ʒm�m�F�ď��������s���܂����B",                 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    

    /**
     * (�����ʒm�������͉�ʃ��N�G�X�g)<BR>
     * �����ʒm�������͉�ʏ������s���B<BR> 
     * <BR>
     * �����ʒm�m�F�ď����T�[�r�X���擾��<BR> 
     * execute()���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashinNoticeChangeInputResponses
     * @@roseuid 4108777A037B
     */
    public WEB3AdminAioCashinNoticeChangeInputResponse cashinNoticeChangeInputScreenRequest(
        WEB3AdminAioCashinNoticeChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "cashinNoticeChangeInputScreenRequest(" +
            "WEB3AdminAioCashinNoticeChangeInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        //�����ʒm�m�F�ď����T�[�r�X���擾��
        WEB3AdminAioCashinNoticeConfirmReTreatmentService l_service = null;
        WEB3AdminAioCashinNoticeChangeInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioCashinNoticeConfirmReTreatmentService) 
                Services.getService(WEB3AdminAioCashinNoticeConfirmReTreatmentService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashinNoticeChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����ʒm�m�F�ď����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashinNoticeChangeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashinNoticeChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�����ʒm�m�F�ď��������s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�����ʒm�����m�F���N�G�X�g)<BR>
     * �����ʒm�����m�F�������s���B<BR> 
     * <BR>
     * �����ʒm�m�F�ď����T�[�r�X���擾��<BR> 
     * execute()���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashinNoticeChangeConfirmResponses
     * @@roseuid 4108777A037B
     */
    public WEB3AdminAioCashinNoticeChangeConfirmResponse cashinNoticeChangeConfirmRequest(
        WEB3AdminAioCashinNoticeChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "cashinNoticeChangeConfirmRequest(" +
            "WEB3AdminAioCashinNoticeChangeConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        //�����ʒm�m�F�ď����T�[�r�X���擾��
        WEB3AdminAioCashinNoticeConfirmReTreatmentService l_service = null;
        WEB3AdminAioCashinNoticeChangeConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioCashinNoticeConfirmReTreatmentService) 
                Services.getService(WEB3AdminAioCashinNoticeConfirmReTreatmentService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashinNoticeChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����ʒm�m�F�ď����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashinNoticeChangeConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashinNoticeChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            
            log.error(l_request, 
                "�����ʒm�m�F�ď��������s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }  
    
    /**
     * (�����ʒm�����������N�G�X�g )<BR>
     * �����ʒm���������������s���B<BR> 
     * <BR>
     * �����ʒm�m�F�ď����T�[�r�X���擾��<BR> 
     * execute()���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashinNoticeChangeCompleteResponse
     * @@roseuid 4108777A037B
     */
    public WEB3AdminAioCashinNoticeChangeCompleteResponse cashinNoticeChangeCompleteRequest(
        WEB3AdminAioCashinNoticeChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "cashinNoticeChangeCompleteRequest(" +
            "WEB3AdminAioCashinNoticeChangeCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        //�����ʒm�m�F�ď����T�[�r�X���擾��
        WEB3AdminAioCashinNoticeConfirmReTreatmentService l_service = null;
        WEB3AdminAioCashinNoticeChangeCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioCashinNoticeConfirmReTreatmentService) 
                Services.getService(WEB3AdminAioCashinNoticeConfirmReTreatmentService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashinNoticeChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;            
            log.error(
                l_request,
                "�����ʒm�m�F�ď����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashinNoticeChangeCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashinNoticeChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�����ʒm�m�F�ď��������s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }    
}
@
