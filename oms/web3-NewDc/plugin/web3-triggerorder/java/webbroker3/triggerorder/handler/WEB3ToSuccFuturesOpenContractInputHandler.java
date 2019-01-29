head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�V�K�����̓n���h��(WEB3ToSuccFuturesOpenContractInputHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 ���n(���u) �V�K�쐬���f��256
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�敨�V�K�����̓n���h��)<BR>
 * �i�A���j�����w���敨�V�K�����̓n���h���N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ToSuccFuturesOpenContractInputHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesOpenContractInputHandler.class);

    /**
     * @@roseuid 47D6593502CF
     */
    public WEB3ToSuccFuturesOpenContractInputHandler()
    {

    }

    /**
     * (�V�K�����̓��N�G�X�g)<BR>
     * �i�A���j�����w���敨�V�K�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �i�A���j�����w���敨�V�K�����̓T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesOpenInputResponse
     * @@roseuid 47A939C401AB
     */
    public WEB3SuccFuturesOpenInputResponse openContractInputRequest(WEB3SuccFuturesOpenInputRequest l_request)
    {
        final String STR_METHOD_NAME = "openContractInputRequest(WEB3SuccFuturesOpenInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenInputResponse l_response = null;
        WEB3ToSuccFuturesOpenContractInputService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesOpenContractInputService)Services.getService(
                WEB3ToSuccFuturesOpenContractInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�����w���敨�V�K�����̓T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccFuturesOpenInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���敨�V�K�����͉�ʕ\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���敨�V�K�����͉�ʕ\�������̎��{�Ɏ��s���܂����B",
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
