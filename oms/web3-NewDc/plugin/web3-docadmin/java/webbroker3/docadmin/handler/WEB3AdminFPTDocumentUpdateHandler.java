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
filename	WEB3AdminFPTDocumentUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t���ʍX�V�n���h��(WEB3AdminFPTDocumentUpdateHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 �g�C�� (���u) �V�K�쐬 ���f��No.039
*/
package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҋ����@@��t���ʍX�V�n���h��)<BR>
 * �Ǘ��ҋ����@@��t���ʍX�V�n���h��<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentUpdateHandler implements MessageHandler
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentUpdateHandler.class);

    /**
     * @@roseuid 47CBC5AD0213
     */
    public WEB3AdminFPTDocumentUpdateHandler()
    {

    }

    /**
     * (get�����@@��t���ʍX�V����)<BR>
     * �����@@��t���ʍX�V���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTDocumentUpdateInputResponse
     * @@roseuid 47C25CED028F
     */
    public WEB3AdminFPTDocumentUpdateInputResponse getDocumentUpdateInput(
        WEB3AdminFPTDocumentUpdateInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getDocumentUpdateInput(WEB3AdminFPTDocumentUpdateInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentUpdateInputResponse l_response;
        WEB3AdminFPTDocumentUpdateService l_service;

        //�Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X���擾��
        try
        {
            l_service =
                (WEB3AdminFPTDocumentUpdateService)Services.getService(
                        WEB3AdminFPTDocumentUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������B
        try
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����@@��t���ʍX�V���͉�ʕ\�������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����@@��t���ʍX�V���͉�ʕ\�������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�����@@��t���ʍX�V�m�F)<BR>
     * �����@@��t���ʍX�V�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTDocumentUpdateConfirmResponse
     * @@roseuid 47C25E320156
     */
    public WEB3AdminFPTDocumentUpdateConfirmResponse getDocumentUpdateConfirm(
        WEB3AdminFPTDocumentUpdateConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "getDocumentUpdateConfirm(WEB3AdminFPTDocumentUpdateConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentUpdateConfirmResponse l_response;
        WEB3AdminFPTDocumentUpdateService l_service;

        //�Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X���擾��
        try
        {
            l_service =
                (WEB3AdminFPTDocumentUpdateService)Services.getService(
                        WEB3AdminFPTDocumentUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������B
        try
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����@@��t���ʍX�V�m�F��ʕ\�������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����@@��t���ʍX�V�m�F��ʕ\�������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�����@@��t���ʍX�V����)<BR>
     * �����@@��t���ʍX�V������ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTDocumentUpdateCompleteResponse
     * @@roseuid 47C25E32037B
     */
    public WEB3AdminFPTDocumentUpdateCompleteResponse getDocumentUpdateComplete(
        WEB3AdminFPTDocumentUpdateCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "getDocumentUpdateComplete(WEB3AdminFPTDocumentUpdateCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentUpdateCompleteResponse l_response;
        WEB3AdminFPTDocumentUpdateService l_service;

        //�Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X���擾��
        try
        {
            l_service =
                (WEB3AdminFPTDocumentUpdateService)Services.getService(
                        WEB3AdminFPTDocumentUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������B
        try
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����@@��t���ʍX�V������ʕ\�������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����@@��t���ʍX�V������ʕ\�������ɃG���[���������܂����B",
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
