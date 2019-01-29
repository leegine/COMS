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
filename	WEB3AdminDirSecBatoTroubleFlagUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ғd�q����Q�t���O�X�V�n���h��(WEB3AdminDirSecBatoTroubleFlagUpdateHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/28 �đo�g(���u) �V�K�쐬 ���f��No.117
*/

package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecBatoTroubleFlagUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ғd�q����Q�t���O�X�V�n���h��)<BR>
 * �Ǘ��ғd�q����Q�t���O�X�V�n���h���N���X�B<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminDirSecBatoTroubleFlagUpdateHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecBatoTroubleFlagUpdateHandler.class);

    /**
     * @@roseuid 481155FD0353
     */
    public WEB3AdminDirSecBatoTroubleFlagUpdateHandler()
    {

    }

    /**
     * (get�ꗗ���)<BR>
     * �ғ��󋵈ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ғd�q����Q�t���O�X�VImpl���擾���A<BR>
     * �@@execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �d�q���V�X�e����Е��X�v���t�@@�����X�e�[�u�����R�[�h�������ʃ��N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecWorkingListResponse
     * @@roseuid 47C3700A0002
     */
    public WEB3AdminDirSecWorkingListResponse getListScreen(WEB3AdminDirSecWorkingListRequest l_request)
    {
        final String STR_METHOD_NAME = "getListScreen(WEB3AdminDirSecWorkingListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecBatoTroubleFlagUpdateService l_service;
        WEB3AdminDirSecWorkingListResponse l_response;

        try
        {
            l_service = (WEB3AdminDirSecBatoTroubleFlagUpdateService)Services.getService(
                WEB3AdminDirSecBatoTroubleFlagUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�Ǘ��ғd�q����Q�t���O�X�V�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecWorkingListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�ғ��󋵈ꗗ��ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�ғ��󋵈ꗗ��ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ύX�m�F���)<BR>
     * �ғ��󋵕ύX�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ғd�q����Q�t���O�X�VImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�ғ��󋵍X�V�m�F���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecWorkingConfirmResponse
     * @@roseuid 47C37045007B
     */
    public WEB3AdminDirSecWorkingConfirmResponse validateChangeConfirmScreen(
        WEB3AdminDirSecWorkingConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateChangeConfirmScreen(WEB3AdminDirSecWorkingConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecBatoTroubleFlagUpdateService l_service;
        WEB3AdminDirSecWorkingConfirmResponse l_response;

        try
        {
            l_service = (WEB3AdminDirSecBatoTroubleFlagUpdateService)Services.getService(
                WEB3AdminDirSecBatoTroubleFlagUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�Ǘ��ғd�q����Q�t���O�X�V�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecWorkingConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�ғ��󋵕ύX�m�F��ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�ғ��󋵕ύX�m�F��ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ύX�������)<BR>
     * �ғ��󋵍X�V������ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ғd�q����Q�t���O�X�VImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�ғ��󋵍X�V�������N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecWorkingCompleteResponse
     * @@roseuid 47C3705A005A
     */
    public WEB3AdminDirSecWorkingCompleteResponse submitChangeCompleteScreen(
        WEB3AdminDirSecWorkingCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitChangeCompleteScreen(WEB3AdminDirSecWorkingCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecBatoTroubleFlagUpdateService l_service;
        WEB3AdminDirSecWorkingCompleteResponse l_response;

        try
        {
            l_service = (WEB3AdminDirSecBatoTroubleFlagUpdateService)Services.getService(
                WEB3AdminDirSecBatoTroubleFlagUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�Ǘ��ғd�q����Q�t���O�X�V�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecWorkingCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�ғ��󋵍X�V������ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�ғ��󋵍X�V������ʕ\�������Ɏ��s���܂����B",
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
