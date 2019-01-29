head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���jOP�����V�K�����̓n���h���iWEB3ToSuccOptionChangeOpenContractInputHandler.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/11 �g�E�N�| (���u) �V�K�쐬 ���f��267
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���jOP�����V�K�����̓n���h��)<BR>
 * �i�A���jOP�����V�K�����̓n���h���N���X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeOpenContractInputHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeOpenContractInputHandler.class);

    /**
     * @@roseuid 47FDBE3D037A
     */
    public WEB3ToSuccOptionChangeOpenContractInputHandler()
    {

    }

    /**
     * (�����V�K�����̓��N�G�X�g)<BR>
     * �i�A���jOP�����V�K�����͕\���������s���B  <BR>
     * <BR>
     * �i�A���jOP�����V�K�����̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccOptionsOpenChangeInputResponse
     * @@roseuid 47A920FE002D
     */
    public WEB3SuccOptionsOpenChangeInputResponse changeOpenContractInputRequest(
        WEB3SuccOptionsOpenChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "changeOpenContractInputRequest(WEB3SuccOptionsOpenChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsOpenChangeInputResponse l_response = null;
        WEB3ToSuccOptionChangeOpenContractInputService l_service = null;
        try
        {
            // �i�A���jOP�����V�K�����̓T�[�r�X���擾
            l_service = (WEB3ToSuccOptionChangeOpenContractInputService)Services.getService(
                WEB3ToSuccOptionChangeOpenContractInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���jOP�����V�K�����̓T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()���\�b�h���R�[������
            l_response = (WEB3SuccOptionsOpenChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���jOP�����V�K�����͕\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���jOP�����V�K�����͕\�����������s���܂����B",
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
