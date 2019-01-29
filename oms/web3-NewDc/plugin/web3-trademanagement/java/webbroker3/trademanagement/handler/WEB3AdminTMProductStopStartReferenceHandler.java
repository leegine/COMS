head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMProductStopStartReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�n���h��)(WEB3AdminTMProductStopStartReferenceHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trademanagement.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartReferenceRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartReferenceResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMProductStopStartReferenceService;

/**
 *
 * (�Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�n���h��)<BR>
 * <BR>
 * �Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�n���h���N���X<BR>
 * <BR>
 * WEB3AdminTMProductStopStartReferenceHandler<BR>
 * <BR>
 * WEB3AdminTMProductStopStartReferenceHandler class<BR>
 * <BR>
 * @@author Sudhindra Kinnal
 * @@version 1.0
 */
public class WEB3AdminTMProductStopStartReferenceHandler implements MessageHandler
{
    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMProductStopStartChangeServiceHandler.class);
    /**
     * @@roseuid 41DD424F02A1
     */
    public WEB3AdminTMProductStopStartReferenceHandler()
    {

    }

    /**
     * (get���i�ʎ戵�󋵏Ɖ�)<BR>
     * <BR>
     * ���i�ʎ戵��~�ĊJ�Ɖ�����s���B<BR>
     * <BR>
     * ���i�ʎ戵��~�ĊJ�Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * ----<English>----------------<BR>
     *  <BR>
     * getProductHandlingStatusReference<BR>
     * <BR>
     * Execute WEB3AdminTMProductStopStartReferenceService process<BR>
     * <BR>
     * Acquire WEB3AdminTMProductStopStartReferenceServiceImpl, and call execute()
     * method. <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE���i�ʎ戵��~�ĊJ�Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMPStopStartInputRequest�@@object<BR>
     * <BR>
     * @@return webbroker3.trademnagement.message.WEB3AdminTMPStopStartReferenceResponse
     * @@roseuid 417482060080
     */
    public WEB3AdminTMPStopStartReferenceResponse getProductHandlingStatusReference
        (WEB3AdminTMPStopStartReferenceRequest l_request)
    {
        final String STR_METHOD_NAME =
            "WEB3AdminTMProductStopStartReferenceHandler.getProductHandlingStatusReference()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTMPStopStartReferenceResponse l_response = null;
        WEB3AdminTMProductStopStartReferenceService l_service = null;

        try
        {
            // call  WEB3AdminTMProductStopStartReferenceService.
            l_service =
                (WEB3AdminTMProductStopStartReferenceService) Services.getService(
                    WEB3AdminTMProductStopStartReferenceService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminTMPStopStartReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminTMProductStopStartReferenceService ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //  Call l_service.execute(l_request)
            l_response = (WEB3AdminTMPStopStartReferenceResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request,
                     "Failed to access WEB3AdminTMProductStopStartReferenceServiceImpl()", l_exp);
            l_response = (WEB3AdminTMPStopStartReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
