head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgncalHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���C�O�s��J�����_�[�o�^�n���h��(WEB3MutualFrgncalHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �����(���u) �V�K�쐬
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualFrgncalService;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M���C�O�s��J�����_�[�o�^�n���h���@@�N���X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3MutualFrgncalHandler implements MessageHandler 
{
    
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MutualFrgncalHandler.class);

    /**
     * (�C�O�s��J�����_�[�o�^���̓��N�G�X�g)<BR>
     * �����M���C�O�s��J�����_�[�o�^���͏������s���B<BR>
     * <BR>
     * ���M�C�O�s��J�����_�[�o�^�T�[�r�X���擾���Aexecute()���\�b�h<BR>
     * ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse
     * @@roseuid 40D80F5B00B9
     */
    public WEB3AdminMutualFrgncalInputResponse frgncalInputRequest(
        WEB3AdminMutualFrgncalInputRequest l_request) 
    {
        final String l_strMethodName = "frgncalInputRequest("
            + "WEB3AdminMutualFrgncalInputRequest l_request ";
        log.entering(l_strMethodName);
        WEB3AdminMutualFrgncalService l_service;
        WEB3AdminMutualFrgncalInputResponse l_response;
        try
        {
            l_service = 
                (WEB3AdminMutualFrgncalService)Services.getService(
                    WEB3AdminMutualFrgncalService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�C�O�s��J�����_�[�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualFrgncalInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���M�C�O�s��J�����_�[�o�^���͂Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;
    }
    
    /**
     * (search�C�O�s��J�����_�[�o�^)<BR>
     * �����M���C�O�s��J�����_�[�o�^�Ɖ�����s���B<BR>
     * <BR>
     * ���M�C�O�s��J�����_�[�o�^�T�[�r�X���擾���Aexecute()���\�b�h<BR>
     * ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse
     * @@roseuid 40D80F5B00C9
     */
    public WEB3AdminMutualFrgncalReferenceResponse searchFrgncalReg(
        WEB3AdminMutualFrgncalReferenceRequest l_request) 
    {
        final String l_strMethodName = "searchFrgncalReg("
            + "WEB3AdminMutualFrgncalReferenceRequest l_request ";
        log.entering(l_strMethodName);
        WEB3AdminMutualFrgncalService l_service;
        WEB3AdminMutualFrgncalReferenceResponse l_response;
        try
        {
            l_service = 
                (WEB3AdminMutualFrgncalService)Services.getService(
                    WEB3AdminMutualFrgncalService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�C�O�s��J�����_�[�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualFrgncalReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����M���C�O�s��J�����_�[�o�^�Ɖ���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;
    }
    
    /**
     * (complete�C�O�s��J�����_�[�o�^)<BR>
     * �����M���C�O�s��J�����_�[�o�^�����������s���B<BR>
     * <BR>
     * ���M�C�O�s��J�����_�[�o�^�T�[�r�X���擾���Aexecute()���\�b�h<BR>
     * ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteResponse
     * @@roseuid 40D80F5B00F8
     */
    public WEB3AdminMutualFrgncalCompleteResponse completeFrgncalReg(
        WEB3AdminMutualFrgncalCompleteRequest l_request) 
    {
        final String l_strMethodName = "completeFrgncalReg("
            + "WEB3AdminMutualFrgncalCompleteRequest l_request ";
        log.entering(l_strMethodName);
        WEB3AdminMutualFrgncalService l_service;
        WEB3AdminMutualFrgncalCompleteResponse l_response;
        try
        {
            l_service = 
                (WEB3AdminMutualFrgncalService)Services.getService(
                    WEB3AdminMutualFrgncalService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�C�O�s��J�����_�[�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualFrgncalCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����M���C�O�s��J�����_�[�o�^���������Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;
    }
}
@
