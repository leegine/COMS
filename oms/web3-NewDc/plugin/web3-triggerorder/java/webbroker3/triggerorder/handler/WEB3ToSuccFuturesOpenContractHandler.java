head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesOpenContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�V�K�������n���h��(WEB3ToSuccFuturesOpenContractHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 ���n(���u) �V�K�쐬���f��257
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�敨�V�K�������n���h��)<BR>
 * �i�A���j�敨�V�K�������n���h���N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ToSuccFuturesOpenContractHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesOpenContractHandler.class);

    /**
     * @@roseuid 47D659350261
     */
    public WEB3ToSuccFuturesOpenContractHandler()
    {

    }

    /**
     * (confirm����)<BR>
     * �i�A���j�敨�V�K�������R�����s���B <BR>
     * <BR>
     * �i�A���j�敨�V�K���T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesOpenConfirmResponse
     * @@roseuid 47A93D040228
     */
    public WEB3SuccFuturesOpenConfirmResponse confirmOrder(WEB3SuccFuturesOpenConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmOrder(WEB3SuccFuturesOpenConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenConfirmResponse l_response = null;
        WEB3ToSuccFuturesOpenContractService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesOpenContractService)Services.getService(
                WEB3ToSuccFuturesOpenContractService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�敨�V�K���T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccFuturesOpenConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�V�K�������R���̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "�i�A���j�敨�V�K�������R���̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete����)<BR>
     * �i�A���j�敨�V�K�������o�^���s���B <BR>
     * <BR>
     * �i�A���j�敨�V�K���T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesOpenCompleteResponse
     * @@roseuid 47A93D1500B1
     */
    public WEB3SuccFuturesOpenCompleteResponse completeOrder(WEB3SuccFuturesOpenCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeOrder(WEB3SuccFuturesOpenCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenCompleteResponse l_response = null;
        WEB3ToSuccFuturesOpenContractService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesOpenContractService)Services.getService(
                WEB3ToSuccFuturesOpenContractService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�敨�V�K���T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccFuturesOpenCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�V�K�������o�^���s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "�i�A���j�敨�V�K�������o�^���s���܂����B",
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
