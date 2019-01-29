head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddressRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݃��[���A�h���X�o�^�n���h��(WEB3AccOpenMailAddressRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 �И��� (���u) �V�K�쐬 ���f�� No.166
*/
package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenMailAddressRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�݃��[���A�h���X�o�^�n���h��)<BR>
 * �����J�݃��[���A�h���X�o�^�n���h��<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AccOpenMailAddressRegistHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AccOpenMailAddressRegistHandler.class);

    /**
     * (���͉�ʕ\��)<BR>
     * �����J�݃��[���A�h���X�o�^���͉�ʕ\���������s���B <BR>
     * <BR>
     * �����J�݃��[���A�h���X�o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����J�݃��[���A�h���X�o�^���̓��N�G�X�g
     * @@return �����J�݃��[���A�h���X�o�^���̓��X�|���X
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenMailAddrRegInputResponse inputScreenDisplay(WEB3AccOpenMailAddrRegInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AccOpenMailAddrRegInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AccOpenMailAddressRegistService l_service;
        WEB3AccOpenMailAddrRegInputResponse l_response;

        try
        {
            l_service = (WEB3AccOpenMailAddressRegistService)Services.getService(
                    WEB3AccOpenMailAddressRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�����J�݃��[���A�h���X�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AccOpenMailAddrRegInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����J�݃��[���A�h���X�o�^���͉�ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����J�݃��[���A�h���X�o�^���͉�ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�o�^����)<BR>
     * �����J�݃��[���A�h���X�o�^�����������s���B <BR>
     * <BR>
     * �����J�݃��[���A�h���X�o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����J�݃��[���A�h���X�o�^�������N�G�X�g
     * @@return �����J�݃��[���A�h���X�o�^�������X�|���X
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenMailAddrRegCompleteResponse registComplete(WEB3AccOpenMailAddrRegCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "registComplete(WEB3AccOpenMailAddrRegCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AccOpenMailAddressRegistService l_service;
        WEB3AccOpenMailAddrRegCompleteResponse l_response;

        try
        {
            l_service = (WEB3AccOpenMailAddressRegistService)Services.getService(
                    WEB3AccOpenMailAddressRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�����J�݃��[���A�h���X�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AccOpenMailAddrRegCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����J�݃��[���A�h���X�o�^���������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����J�݃��[���A�h���X�o�^���������Ɏ��s���܂����B",
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
