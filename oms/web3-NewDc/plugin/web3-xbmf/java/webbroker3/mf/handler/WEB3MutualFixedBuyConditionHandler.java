head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t���������o�^�n���h��(WEB3MutualFixedBuyConditionHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/12 ���z(���u) �V�K�쐬 ���f��608
*/

package webbroker3.mf.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (���M�莞��z���t���������o�^�n���h��)<BR>
 * ���M�莞��z���t���������o�^�n���h��<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionHandler.class);

    /**
     * @@roseuid 487597AA037A
     */
    public WEB3MutualFixedBuyConditionHandler()
    {

    }

    /**
     * (�莞��z���t���������o�^����)<BR>
     * ���M�莞��z���t���������o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MutualFixedBuyConditionInputResponse
     * @@roseuid 4851CBF70304
     */
    public WEB3MutualFixedBuyConditionInputResponse mutualFixedBuyConditionInput(
        WEB3MutualFixedBuyConditionInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "mutualFixedBuyConditionInput(WEB3MutualFixedBuyConditionInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionService l_service;
        WEB3MutualFixedBuyConditionInputResponse l_response;
        try
        {
            //���M�莞��z���t���������o�^�T�[�r�X���擾
            l_service =
                (WEB3MutualFixedBuyConditionService)Services.getService(
                    WEB3MutualFixedBuyConditionService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyConditionInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "���M�莞��z���t���������o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3MutualFixedBuyConditionInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyConditionInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�莞��z���t���������o�^���͂Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyConditionInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�莞��z���t���������o�^���͂Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�莞��z���t���������o�^�m�F)<BR>
     * ���M�莞��z���t���������o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MutualFixedBuyConditionConfirmResponse
     * @@roseuid 4851CCDC0215
     */
    public WEB3MutualFixedBuyConditionConfirmResponse mutualFixedBuyConditionConfirm(
        WEB3MutualFixedBuyConditionConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "mutualFixedBuyConditionConfirm(WEB3MutualFixedBuyConditionConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionService l_service = null;
        WEB3MutualFixedBuyConditionConfirmResponse l_response = null;
        try
        {
            //���M�莞��z���t���������o�^�T�[�r�X���擾
            l_service =
                (WEB3MutualFixedBuyConditionService)Services.getService(
                    WEB3MutualFixedBuyConditionService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyConditionConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�莞��z���t���������o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3MutualFixedBuyConditionConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyConditionConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (l_ex.getErrorMessage() != null && !"".equals(l_ex.getErrorMessage()))
            {
                if (!(l_ex instanceof WEB3SystemLayerException || l_ex instanceof WEB3BusinessLayerException))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }
            log.error(l_request, "���M�莞��z���t���������o�^�m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyConditionConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���M�莞��z���t���������o�^�m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�莞��z���t���������o�^����)<BR>
     * ���M�莞��z���t���������o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MutualFixedBuyConditionCompleteResponse
     * @@roseuid 4851CDC80038
     */
    public WEB3MutualFixedBuyConditionCompleteResponse mutualFixedBuyConditionComplete(
        WEB3MutualFixedBuyConditionCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "mutualFixedBuyConditionComplete(WEB3MutualFixedBuyConditionCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionService l_service = null;
        WEB3MutualFixedBuyConditionCompleteResponse l_response = null;
        try
        {
            //���M�莞��z���t���������o�^�T�[�r�X���擾
            l_service =
                (WEB3MutualFixedBuyConditionService)Services.getService(
                    WEB3MutualFixedBuyConditionService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyConditionCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�莞��z���t���������o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3MutualFixedBuyConditionCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyConditionCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (l_ex.getErrorMessage() != null && !"".equals(l_ex.getErrorMessage()))
            {
                if (!(l_ex instanceof WEB3SystemLayerException || l_ex instanceof WEB3BusinessLayerException))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }
            log.error(l_request, "���M�莞��z���t���������o�^�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyConditionCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���M�莞��z���t���������o�^�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
