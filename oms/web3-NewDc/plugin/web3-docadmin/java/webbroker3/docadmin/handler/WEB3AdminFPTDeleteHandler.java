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
filename	WEB3AdminFPTDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���폜�n���h��(WEB3AdminFPTDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/06 ���g (���u) �V�K�쐬 �d�l�ύX�E���f�� No.010
*/

package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDeleteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҋ����@@��t�{���폜�n���h��)<BR>
 * �Ǘ��ҋ����@@��t�{���폜�n���h���N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTDeleteHandler implements MessageHandler
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDeleteHandler.class);

    /**
     * @@roseuid 472FC5B60363
     */
    public WEB3AdminFPTDeleteHandler()
    {

    }

    /**
     * (get�����@@��t�{���폜�m�F)<BR>
     * �����@@��t�{���폜�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҋ����@@��t�{���폜�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTDeleteConfirmResponse
     * @@roseuid 4726CF3A0216
     */
    public WEB3AdminFPTDeleteConfirmResponse getDeleteConfirm(
        WEB3AdminFPTDeleteConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " getDeleteConfirm(WEB3AdminFPTDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDeleteConfirmResponse l_response;
        WEB3AdminFPTDeleteService l_service;

        //�Ǘ��ҋ����@@��t�{���폜�T�[�r�XImpl���擾��
        try
        {
            l_service = (WEB3AdminFPTDeleteService)Services.getService(
                WEB3AdminFPTDeleteService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�Ǘ��ҋ����@@��t�{���폜�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminFPTDeleteConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{���폜�m�F�����ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�����@@��t�{���폜����)<BR>
     * �����@@��t�{���폜������ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҋ����@@��t�{���폜�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTDeleteCompleteResponse
     * @@roseuid 4726CF3C0169
     */
    public WEB3AdminFPTDeleteCompleteResponse getDeleteComplete(
        WEB3AdminFPTDeleteCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " getDeleteComplete(WEB3AdminFPTDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDeleteCompleteResponse l_response;
        WEB3AdminFPTDeleteService l_service;

        //�Ǘ��ҋ����@@��t�{���폜�T�[�r�XImpl���擾��
        try
        {
            l_service = (WEB3AdminFPTDeleteService)Services.getService(
                WEB3AdminFPTDeleteService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�Ǘ��ҋ����@@��t�{���폜�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminFPTDeleteCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{���폜���������ɃG���[���������܂����B",
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
