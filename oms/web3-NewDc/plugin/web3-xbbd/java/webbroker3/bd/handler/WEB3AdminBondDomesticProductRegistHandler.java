head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ����������o�^�n���h��(WEB3AdminBondDomesticProductRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 �����q(���u) �V�K�쐬 �d�l�ύX�E���f��No.193
Revision History : 2007/07/13 �đo�g(���u) �d�l�ύX�E���f��No.210
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҍ����������o�^�n���h��)<BR>
 * �Ǘ��ҍ����������o�^�n���h��<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductRegistHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductRegistHandler.class);

    /**
     * @@roseuid 4691D3AE0305
     */
    public WEB3AdminBondDomesticProductRegistHandler()
    {

    }

    /**
     * (input�����o�^)<BR>
     * �Ǘ��ҍ����������o�^�T�[�r�X���擾���Aexecute()���R�[������B<BR>
     * <BR>
     * @@param l_request (���N�G�X�g�I�u�W�F�N�g)
     * @@return WEB3AdminBondDomesticProductRegistInputResponse
     * @@roseuid 4667608700AB
     */
    public WEB3AdminBondDomesticProductRegistInputResponse inputProductRegist(
        WEB3AdminBondDomesticProductRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME = " inputProductRegist(WEB3AdminBondProductRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminBondDomesticProductRegistInputResponse l_response = null;
        WEB3AdminBondDomesticProductRegistService l_service = null;

        try
        {
            l_service =
                (WEB3AdminBondDomesticProductRegistService)Services.getService(
                    WEB3AdminBondDomesticProductRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҍ����������o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminBondDomesticProductRegistInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�Ǘ��ҍ����������o�^���͏��������s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�����o�^)<BR>
     * �Ǘ��ҍ����������o�^�T�[�r�X���擾���Aexecute()���R�[������B<BR>
     * <BR>
     * @@param l_request (���N�G�X�g�I�u�W�F�N�g)
     * @@return WEB3AdminBondDomesticProductRegistConfirmResponse
     * @@roseuid 4667617F031C
     */
    public WEB3AdminBondDomesticProductRegistConfirmResponse validateProductRegist(
        WEB3AdminBondDomesticProductRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " validateProductRegist(WEB3AdminBondDomesticProductRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminBondDomesticProductRegistConfirmResponse l_response = null;
        WEB3AdminBondDomesticProductRegistService l_service = null;

        try
        {
            l_service =
                (WEB3AdminBondDomesticProductRegistService)Services.getService(
                    WEB3AdminBondDomesticProductRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҍ����������o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminBondDomesticProductRegistConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�Ǘ��ҍ����������o�^�m�F���������s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�����o�^)<BR>
     * �Ǘ��ҍ����������o�^�T�[�r�X���擾���Aexecute()���R�[������B<BR>
     * <BR>
     * @@param l_request (���N�G�X�g�I�u�W�F�N�g)
     * @@return WEB3AdminBondDomesticProductRegistCompleteResponse
     * @@roseuid 46676193001F
     */
    public WEB3AdminBondDomesticProductRegistCompleteResponse submitProductRegist(
        WEB3AdminBondDomesticProductRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " submitProductRegist(WEB3AdminBondDomesticProductRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminBondDomesticProductRegistCompleteResponse l_response = null;
        WEB3AdminBondDomesticProductRegistService l_service = null;

        try
        {
            l_service =
                (WEB3AdminBondDomesticProductRegistService)Services.getService(
                    WEB3AdminBondDomesticProductRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҍ����������o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminBondDomesticProductRegistCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�Ǘ��ҍ����������o�^�������������s���܂����B",
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
