head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇���n���h���N���X(WEB3AdminAioCashoutInqHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/25 �����(���u) ���r���[       
                   2006/07/31 �����(���u) ����̍X���f��604                                                 
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqDownloadRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqDownloadResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqListRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioCashoutInqService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���\���⍇���n���h��)<BR>
 * �o���\���⍇���n���h���N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */

public class WEB3AdminAioCashoutInqHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqHandler.class);
        
    /**
     * (�o���\���⍇�����N�G�X�g)<BR>
     * �o���\���⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminAioCashoutInqListResponse<BR>
     * @@roseuid 4101062D0000
     */
    public WEB3AdminAioCashoutInqListResponse handlecashoutInqRequest(
        WEB3AdminAioCashoutInqListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handlecashoutInqRequest(WEB3AdminAioCashoutInqListRequest l_request)";
        log.entering(STR_METHOD_NAME);       
        
        //�o���\���⍇���T�[�r�X���擾��
        WEB3AdminAioCashoutInqService l_service = null;
        WEB3AdminAioCashoutInqListResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioCashoutInqService) 
                Services.getService(WEB3AdminAioCashoutInqService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o���\���⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashoutInqListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�o���\���⍇�����������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (confirm�o��)<BR>
     * �o�������̔����R�����s���B <BR>
     * <BR>
     * �o���\���⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashoutInqConfirmResponse
     * @@roseuid 413531E0037C
     */
    public WEB3AdminAioCashoutInqConfirmResponse confirmCashout(
        WEB3AdminAioCashoutInqConfirmRequest l_request) 
    {
        String STR_METHOD_NAME = "confirmCashout(WEB3AdminAioCashoutInqConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);       

        //�o���\���⍇���T�[�r�X���擾��
        WEB3AdminAioCashoutInqService l_service = null;
        WEB3AdminAioCashoutInqConfirmResponse l_response = null;
        try
        {
            l_service = (WEB3AdminAioCashoutInqService) 
                Services.getService(WEB3AdminAioCashoutInqService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o���\���⍇���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashoutInqConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�o�������̔����R�����������s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete�o��)<BR>
     * �o�������̓o�^���s���B <BR>
     * <BR>
     * �o���\���⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashoutInqCompleteResponse
     * @@roseuid 413531EF01A7
     */
    public WEB3AdminAioCashoutInqCompleteResponse completeCashout(
        WEB3AdminAioCashoutInqCompleteRequest l_request) 
    {
        String STR_METHOD_NAME = "completeCashout(WEB3AdminAioCashoutInqCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        //�o���\���⍇���T�[�r�X���擾��
        WEB3AdminAioCashoutInqService l_service = null;
        WEB3AdminAioCashoutInqCompleteResponse l_response = null;

        try
        {
            l_service = (WEB3AdminAioCashoutInqService) 
                Services.getService(WEB3AdminAioCashoutInqService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o���\���⍇���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashoutInqCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqCompleteResponse)l_request.createResponse();
            
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�o�������̓o�^�����s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (confirm���)<BR>
     * �o������̔����R�����s���B <BR>
     * <BR>
     * �o���\���⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashoutInqCancelConfirmResponse
     * @@roseuid 413532C802FF
     */
    public WEB3AdminAioCashoutInqCancelConfirmResponse confirmCancel(
        WEB3AdminAioCashoutInqCancelConfirmRequest l_request) 
    {
        String STR_METHOD_NAME = "confirmCancel(WEB3AdminAioCashoutInqCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);       

        //�o���\���⍇���T�[�r�X���擾��
        WEB3AdminAioCashoutInqService l_service = null;
        WEB3AdminAioCashoutInqCancelConfirmResponse l_response = null;
        try
        {
            l_service = (WEB3AdminAioCashoutInqService) 
                Services.getService(WEB3AdminAioCashoutInqService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o���\���⍇���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashoutInqCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�o������̔����R�����������s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete���)<BR>
     * �o������̓o�^���s���B <BR>
     * <BR>
     * �o���\���⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashoutInqCancelCompleteResponse
     * @@roseuid 413532C8031E
     */
    public WEB3AdminAioCashoutInqCancelCompleteResponse completeCancel(
        WEB3AdminAioCashoutInqCancelCompleteRequest l_request) 
    {
        String STR_METHOD_NAME = "completeCancel(WEB3AdminAioCashoutInqCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //�o���\���⍇���T�[�r�X���擾��
        WEB3AdminAioCashoutInqService l_service = null;
        WEB3AdminAioCashoutInqCancelCompleteResponse l_response = null;

        try
        {
            l_service = (WEB3AdminAioCashoutInqService) 
                Services.getService(WEB3AdminAioCashoutInqService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o���\���⍇���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashoutInqCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�o������̓o�^�����s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �o���\���⍇���_�E�����[�h�t�@@�C���f�[�^�擾�������s���B<BR>
     * <BR>
     * �o���\���⍇���T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashoutInqDownloadResponse
     * @@roseuid 413532C8031E
     */
    public WEB3AdminAioCashoutInqDownloadResponse getDownloadFile(
    	WEB3AdminAioCashoutInqDownloadRequest l_request) 
    {
        String STR_METHOD_NAME = "getDownloadFile(WEB3AdminAioCashoutInqDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //�o���\���⍇���T�[�r�X���擾��
        WEB3AdminAioCashoutInqService l_service = null;
        WEB3AdminAioCashoutInqDownloadResponse l_response = null;

        try
        {
            l_service = (WEB3AdminAioCashoutInqService) 
                Services.getService(WEB3AdminAioCashoutInqService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o���\���⍇���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashoutInqDownloadResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashoutInqDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�o���\���⍇���_�E�����[�h�t�@@�C���f�[�^�擾���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
