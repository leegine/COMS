head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�n���h��(WEB3AdminAccOpenApplyDataDelHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 ���m�a(���u) �V�K�쐬 ���f��No.159
*/

package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyDataDelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�n���h��)<BR>
 * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�n���h��<BR>
 * <BR>
 * @@author ���m�a
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminAccOpenApplyDataDelHandler.class);

    /**
     * @@roseuid 4940F22B0290
     */
    public WEB3AdminAccOpenApplyDataDelHandler()
    {

    }

    /**
     * (������ʕ\��)<BR>
     * �����J�ݎ��������f�[�^�폜������ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenApplyDataDelSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 493388650351
     */
    public WEB3AdminAccOpenApplyDataDelSearchResponse displaySearchScreen(
        WEB3AdminAccOpenApplyDataDelSearchRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "displaySearchScreen(WEB3AdminAccOpenApplyDataDelSearchRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyDataDelService l_service;
        WEB3AdminAccOpenApplyDataDelSearchResponse l_response;

        try
        {
            l_service = (WEB3AdminAccOpenApplyDataDelService)Services.getService(
                WEB3AdminAccOpenApplyDataDelService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelSearchResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccOpenApplyDataDelSearchResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����J�ݎ��������f�[�^�폜������ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����J�ݎ��������f�[�^�폜������ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�폜�m�F)<BR>
     * �����J�ݎ��������f�[�^�폜�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenApplyDataDelCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 493389DA03E4
     */
    public WEB3AdminAccOpenApplyDataDelCnfResponse deleteConfirm(
        WEB3AdminAccOpenApplyDataDelCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteConfirm(WEB3AdminAccOpenApplyDataDelCnfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyDataDelService l_service;
        WEB3AdminAccOpenApplyDataDelCnfResponse l_response;

        try
        {
            l_service = (WEB3AdminAccOpenApplyDataDelService)Services.getService(
                WEB3AdminAccOpenApplyDataDelService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCnfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCnfResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����J�ݎ��������f�[�^�폜�m�F�����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����J�ݎ��������f�[�^�폜�m�F�����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�폜����)<BR>
     * �����J�ݎ��������f�[�^�폜�����������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenApplyDataDelCmpResponse
     * @@roseuid 493389EB027E
     */
    public WEB3AdminAccOpenApplyDataDelCmpResponse deleteComplete(
        WEB3AdminAccOpenApplyDataDelCmpRequest l_request)
    {
        final String STR_METHOD_NAME = "deleteComplete(WEB3AdminAccOpenApplyDataDelCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyDataDelService l_service;
        WEB3AdminAccOpenApplyDataDelCmpResponse l_response;

        try
        {
            l_service = (WEB3AdminAccOpenApplyDataDelService)Services.getService(
                WEB3AdminAccOpenApplyDataDelService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCmpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����J�ݎ��������f�[�^�폜���������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����J�ݎ��������f�[�^�폜���������Ɏ��s���܂����B",
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
