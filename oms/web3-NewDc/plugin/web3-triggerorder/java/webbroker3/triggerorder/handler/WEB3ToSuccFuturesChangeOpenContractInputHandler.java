head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�����V�K�����̓n���h���iWEB3ToSuccFuturesChangeOpenContractInputHandler.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 �g�E�N�| (���u) �V�K�쐬 ���f��No.261
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�敨�����V�K�����̓n���h��)<BR>
 * �i�A���j�敨�����V�K�����̓n���h���N���X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3ToSuccFuturesChangeOpenContractInputHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeOpenContractInputHandler.class);

    /**
     * @@roseuid 47D6593503A9
     */
    public WEB3ToSuccFuturesChangeOpenContractInputHandler()
    {

    }

    /**
     * (�����V�K�����̓��N�G�X�g)<BR>
     * �i�A���j�����w���敨�����V�K�����͕\���������s���B <BR>
     * <BR>
     * �i�A���j�����w���敨�����V�K�����̓T�[�r�X���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccFuturesOpenChangeInputResponse
     * @@roseuid 47A942CF0210
     */
    public WEB3SuccFuturesOpenChangeInputResponse changeOpenContractInputRequest(
        WEB3SuccFuturesOpenChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "changeOpenContractInputRequest(WEB3SuccFuturesOpenChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenChangeInputResponse l_response = null;
        WEB3ToSuccFuturesChangeOpenContractInputService l_service = null;
        try
        {
            // �i�A���j�����w���敨�����V�K�����̓T�[�r�X���擾
            l_service = (WEB3ToSuccFuturesChangeOpenContractInputService)Services.getService(
                    WEB3ToSuccFuturesChangeOpenContractInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�����w���敨�����V�K�����̓T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()���\�b�h���R�[������
            l_response = (WEB3SuccFuturesOpenChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���敨�����V�K�����͕\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���敨�����V�K�����͕\�����������s���܂����B",
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
