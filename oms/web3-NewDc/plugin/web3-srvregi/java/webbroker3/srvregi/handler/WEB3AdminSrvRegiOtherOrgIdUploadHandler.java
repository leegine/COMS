head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�������(WEB3AdminSrvRegiOtherOrgIdUploadHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 �đo�g(���u) �V�K�쐬 ���f��No.337
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�������)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰������׃N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdUploadHandler.class);

    /**
     * @@roseuid 47D111370225
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadHandler()
    {

    }

    /**
     * (�O���A�gID�Ɖﱯ��۰�މ�ʕ\��)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�މ�ʏ������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�މ�ʃT�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޓ��̓��N�G�X�g<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadInputResponse
     * @@roseuid 47B940CA021F
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadInputResponse otherOrgIdUploadScreenDisplay(
        WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdUploadScreenDisplay(WEB3AdminSrvRegiOtherOrgIdUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdUploadInputResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdUploadService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdUploadService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�މ�ʕ\�������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�މ�ʕ\�������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�O���A�gID�Ɖﱯ��۰�ރt�@@�C���m�F)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ރt�@@�C���m�F�������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ރt�@@�C���m�F�T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ރt�@@�C���m�F���N�G�X�g<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse
     * @@roseuid 47B940CA0223
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse otherOrgIdUploadConfirm(
        WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdUploadConfirm(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdUploadService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdUploadService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                if (!(l_ex instanceof WEB3SystemLayerException || l_ex instanceof WEB3BusinessLayerException))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }

            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�F�����ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�F�����ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�O���A�gID�Ɖﱯ��۰��)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޏ������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ރT�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ރ��N�G�X�g<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse
     * @@roseuid 47B940CA022F
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse otherOrgIdUpload(
        WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdUpload(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdUploadService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdUploadService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                if (!(l_ex instanceof WEB3SystemLayerException || l_ex instanceof WEB3BusinessLayerException))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }

            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ��������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ��������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�O���A�gID�Ɖﱯ��۰�ޒ��~)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~�������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~�T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~���N�G�X�g<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse
     * @@roseuid 47B940CA0221
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse otherOrgIdUploadCancel(
        WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdUploadComplete(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdUploadService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdUploadService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~�����ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~�����ɃG���[���������܂����B",
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
