head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeToIfoDepositHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����ւ̐U�փn���h��(WEB3AccTransChangeToIfoDepositHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/26 ���E(���u) ���r���[
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteRequest;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteResponse;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmRequest;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeToIfoDepositService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�؋����ւ̐U�փn���h��)<BR>
 * �؋����ւ̐U�փn���h���N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeToIfoDepositHandler implements MessageHandler
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeToIfoDepositHandler.class);

    /**
     * (confirm����)<BR>
     * �U�֏����̔����R�����s���B <BR>
     * <BR>
     * �؋����ւ̐U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AccTransChangeToIfoDepositConfirmResponse
     * @@roseuid 4135AE7F02A6
     */
    public WEB3AccTransChangeToIfoDepositConfirmResponse confirmOrder(
        WEB3AccTransChangeToIfoDepositConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "confirmOrder(" +
                "WEB3AccTransChangeToIfoDepositConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositService l_service = null;
        WEB3AccTransChangeToIfoDepositConfirmResponse l_response = null;

        try
        {
            l_service =
                (WEB3AccTransChangeToIfoDepositService) Services.getService(
                    WEB3AccTransChangeToIfoDepositService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�؋����ւ̐U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�U�֏����̔����R�������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (complete����)<BR>
     * �U�֏����̓o�^���s���B <BR>
     * <BR>
     * �؋����ւ̐U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AccTransChangeToIfoDepositCompleteResponse
     * @@roseuid 4135AE7F02A8
     */
    public WEB3AccTransChangeToIfoDepositCompleteResponse completeOrder(
        WEB3AccTransChangeToIfoDepositCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeOrder(" +
                "WEB3AccTransChangeToIfoDepositCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositService l_service = null;
        WEB3AccTransChangeToIfoDepositCompleteResponse l_response = null;

        try
        {
            l_service =
                (WEB3AccTransChangeToIfoDepositService) Services.getService(
                    WEB3AccTransChangeToIfoDepositService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�؋����ւ̐U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�U�֏����̓o�^�����s���܂����B", l_ex.getErrorInfo(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
