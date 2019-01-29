head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �⍇���Ǘ����⍇�����̓T�[�r�X(WEB3FaqInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.faq.message.WEB3FaqCompleteRequest;
import webbroker3.faq.message.WEB3FaqCompleteResponse;
import webbroker3.faq.message.WEB3FaqConfirmRequest;
import webbroker3.faq.message.WEB3FaqConfirmResponse;
import webbroker3.faq.message.WEB3FaqInputRequest;
import webbroker3.faq.message.WEB3FaqInputResponse;
import webbroker3.faq.service.delegate.WEB3FaqInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�⍇���Ǘ����⍇�����̓T�[�r�X)<BR>
 * �⍇���Ǘ����⍇�����̓T�[�r�X<BR>
 */
public class WEB3FaqInputHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqInputHandler.class);

    /**
     * @@roseuid 41C25BF300BB
     */
    public WEB3FaqInputHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �⍇���Ǘ����⍇�����̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �⍇���Ǘ����⍇�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * 
     * @@return WEB3FaqInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41ABFB70011E
     */
    public WEB3FaqInputResponse inputScreenDisplay(WEB3FaqInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3FaqInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FaqInputResponse l_response = null;
        WEB3FaqInputService l_service = null;

        try
        {
            l_service = (WEB3FaqInputService)Services.getService(WEB3FaqInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FaqInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�⍇���Ǘ����⍇�����̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FaqInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FaqInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�⍇���Ǘ����⍇�����͂̓��͉�ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (�⍇�����͊m�F)<BR>
     * �⍇�����͊m�F�������s���B <BR>
     * <BR>
     * �⍇���Ǘ����⍇�����̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �⍇���Ǘ����⍇���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3FaqConfirmResponse
     * @@roseuid 41ABFB700276
     */
    public WEB3FaqConfirmResponse faqInputConfirm(WEB3FaqConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " faqInputConfirm(WEB3FaqConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FaqConfirmResponse l_response = null;
        WEB3FaqInputService l_service = null;

        try
        {
            l_service = (WEB3FaqInputService)Services.getService(WEB3FaqInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FaqConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�⍇���Ǘ����⍇�����̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FaqConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FaqConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�⍇���Ǘ����⍇�����̖͂⍇�����͊m�F�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (�⍇�����͊���)<BR>
     * �⍇�����͊����������s���B <BR>
     * <BR>
     * �⍇���Ǘ����⍇�����̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �⍇���Ǘ����⍇���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3FaqCompleteResponse
     * @@roseuid 41ABFB700332
     */
    public WEB3FaqCompleteResponse faqInputComplete(WEB3FaqCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " faqInputComplete(WEB3FaqCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FaqCompleteResponse l_response = null;
        WEB3FaqInputService l_service = null;

        try
        {
            l_service = (WEB3FaqInputService)Services.getService(WEB3FaqInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FaqCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�⍇���Ǘ����⍇�����̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FaqCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FaqCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�⍇���Ǘ����⍇�����̖͂⍇�����͊����Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
