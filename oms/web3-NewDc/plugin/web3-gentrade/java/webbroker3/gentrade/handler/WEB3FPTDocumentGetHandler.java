head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.30.05.57.36;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d92c6286688;
filename	WEB3FPTDocumentGetHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (��)��a�����r�W�l�X�E�C�m�x�[�V����
 File Name           : �����@@���ʏ��擾�n���h���N���X(WEB3FPTDocumentGetHandler.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/17 �����C(�k�����u) �V�K�쐬 �d�l�ύX���f��No.354
 */
package webbroker3.gentrade.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.message.WEB3FPTDocumentGetRequest;
import webbroker3.gentrade.message.WEB3FPTDocumentGetResponse;
import webbroker3.gentrade.service.delegate.WEB3FPTDocumentGetService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����@@���ʏ��擾�n���h��)<BR>
 * �����@@���ʏ��擾�n���h���N���X<BR>
 * <BR>
 * @@author �����C(�k�����u)
 * @@version 1.0
 */
public class WEB3FPTDocumentGetHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FPTDocumentGetHandler.class);

    /**
     * (�����@@���ʏ��擾)<BR>
     * �����@@���ʏ��擾���������{����B<BR>
     * <BR>
     * �����@@���ʏ��擾�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     */
    public WEB3FPTDocumentGetResponse fptDocumentGet(
        WEB3FPTDocumentGetRequest l_request)
    {
        final String STR_METHOD_NAME = "fptDocumentGet(WEB3FPTDocumentGetRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FPTDocumentGetResponse l_response = null;

        WEB3FPTDocumentGetService  l_service = null;

        //�����@@���ʏ��擾�T�[�r�X���擾
        try
        {
            l_service =
                (WEB3FPTDocumentGetService)Services.getService(
                    WEB3FPTDocumentGetService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3FPTDocumentGetResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����@@���ʏ��擾�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3FPTDocumentGetResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FPTDocumentGetResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����@@���ʏ��擾�����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3FPTDocumentGetResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����@@���ʏ��擾�����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
