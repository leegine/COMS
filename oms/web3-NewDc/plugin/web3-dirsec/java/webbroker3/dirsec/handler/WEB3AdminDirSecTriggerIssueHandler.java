head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[���s�����n���h��(WEB3AdminDirSecTriggerIssueHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 ���O(���u) �V�K�쐬 ���f��No.116�ANo.118�ANo.119
*/
package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecTriggerIssueService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[���s�����n���h��)<BR>
 * �g���K�[���s���n���h���N���X�B<BR>
 * <BR>
 * @@author ���O
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueHandler.class);

    /**
     * @@roseuid 4806E05401B7
     */
    public WEB3AdminDirSecTriggerIssueHandler()
    {

    }

    /**
     * (get�g���K�[���s�����ꗗ��ʕ\��)<BR>
     * �g���K�[���s�����ꗗ��ʕ\�����s���B<BR>
     * <BR>
     * �g���K�[���s�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�g���K�[���s�����ꗗ���N�G�X�g�N���X<BR>
     * @@return WEB3AdminDirSecTriggerIssueListResponse
     * @@roseuid 47C27C2D004F
     */
    public WEB3AdminDirSecTriggerIssueListResponse getTriggerIssueListScreenDisplay(
        WEB3AdminDirSecTriggerIssueListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getTriggerIssueListScreenDisplay(WEB3AdminDirSecTriggerIssueListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueListResponse l_response = null;
        WEB3AdminDirSecTriggerIssueService l_service = null;

        try
        {
            l_service = (WEB3AdminDirSecTriggerIssueService)Services.getService(
                WEB3AdminDirSecTriggerIssueService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�g���K�[���s�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecTriggerIssueListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�g���K�[���s�����ꗗ��ʂɎ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminDirSecTriggerIssueListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�g���K�[���s�����ꗗ��ʂɎ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�g���K�[���s�������͉�ʕ\��)<BR>
     * �g���K�[���s�������͉�ʕ\�����s���B<BR>
     * <BR>
     * �g���K�[���s�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�g���K�[���s�������̓��N�G�X�g�N���X<BR>
     * @@return WEB3AdminDirSecTriggerIssueInputResponse
     * @@roseuid 47C27C2D005F
     */
    public WEB3AdminDirSecTriggerIssueInputResponse getTriggerIssueInputScreenDisplay(
        WEB3AdminDirSecTriggerIssueInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getTriggerIssueInputScreenDisplay(WEB3AdminDirSecTriggerIssueInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminDirSecTriggerIssueInputResponse l_response = null;
        WEB3AdminDirSecTriggerIssueService l_service = null;

        try
        {
            l_service = (WEB3AdminDirSecTriggerIssueService)Services.getService(
                WEB3AdminDirSecTriggerIssueService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�g���K�[���s�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecTriggerIssueInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�g���K�[���s�������͉�ʂɎ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminDirSecTriggerIssueInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�g���K�[���s�������͉�ʂɎ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�g���K�[���s�����m�F��ʕ\��)<BR>
     * �g���K�[���s�����m�F��ʕ\�����s���B<BR>
     * <BR>
     * �g���K�[���s�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�g���K�[���s�����m�F���N�G�X�g�N���X<BR>
     * @@return WEB3AdminDirSecTriggerIssueConfirmResponse
     * @@roseuid 47C27C2D0060
     */
    public WEB3AdminDirSecTriggerIssueConfirmResponse validateTriggerIssueConfirmScreenDisplay(
        WEB3AdminDirSecTriggerIssueConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateTriggerIssueConfirmScreenDisplay(WEB3AdminDirSecTriggerIssueConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminDirSecTriggerIssueConfirmResponse l_response = null;
        WEB3AdminDirSecTriggerIssueService l_service = null;

        try
        {
            l_service = (WEB3AdminDirSecTriggerIssueService)Services.getService(
                WEB3AdminDirSecTriggerIssueService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�g���K�[���s�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecTriggerIssueConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�g���K�[���s�����m�F��ʂɎ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminDirSecTriggerIssueConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�g���K�[���s�����m�F��ʂɎ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�g���K�[���s����������ʕ\��)<BR>
     * �g���K�[���s����������ʕ\���������s���B<BR>
     * <BR>
     * �g���K�[���s�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�g���K�[���s�����������N�G�X�g�N���X<BR>
     * @@return WEB3AdminDirSecTriggerIssueCompleteResponse
     * @@roseuid 47C27C2D006F
     */
    public WEB3AdminDirSecTriggerIssueCompleteResponse submitTriggerIssueCompleteScreenDisplay(
        WEB3AdminDirSecTriggerIssueCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitTriggerIssueCompleteScreenDisplay(WEB3AdminDirSecTriggerIssueCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminDirSecTriggerIssueCompleteResponse l_response = null;
        WEB3AdminDirSecTriggerIssueService l_service = null;

        try
        {
            l_service = (WEB3AdminDirSecTriggerIssueService)Services.getService(
                WEB3AdminDirSecTriggerIssueService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�g���K�[���s�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecTriggerIssueCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�g���K�[���s����������ʂɎ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminDirSecTriggerIssueCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�g���K�[���s����������ʂɎ��s���܂����B",
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
