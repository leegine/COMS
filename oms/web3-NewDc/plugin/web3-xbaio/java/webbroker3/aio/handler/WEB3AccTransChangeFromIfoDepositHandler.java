head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeFromIfoDepositHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋�������U�փn���h��(WEB3AccTransChangeFromIfoDepositHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/22 ���z (���u) ���r���[
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositCompleteRequest;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositCompleteResponse;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositConfirmRequest;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeFromIfoDepositService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�؋�������U�փn���h��)<BR>
 * �؋�������U�փn���h���N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeFromIfoDepositHandler implements MessageHandler
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeFromIfoDepositHandler.class);

    /**
     * (confirm����)<BR>
     * �U�֏����̔����R�����s���B <BR>
     * <BR>
     * �؋�������U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * 
     * @@return WEB3AccTransChangeFromIfoDepositConfirmResponse
     * @@roseuid 4135317001B7
     */
    public WEB3AccTransChangeFromIfoDepositConfirmResponse handleConfirmOrder(
        WEB3AccTransChangeFromIfoDepositConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "handleConfirmOrder(" 
            + "WEB3AccTransChangeFromIfoDepositConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeFromIfoDepositService l_service = null;
        WEB3AccTransChangeFromIfoDepositConfirmResponse l_response = null;

        try
        {
            l_service =
                (WEB3AccTransChangeFromIfoDepositService) Services.getService(
                    WEB3AccTransChangeFromIfoDepositService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�؋�������U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�U�֏����̔����R�����������s���܂����B",
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
     * �؋�������U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AccTransChangeFromIfoDepositCompleteResponse
     * @@roseuid 4135317001D6
     */
    public WEB3AccTransChangeFromIfoDepositCompleteResponse handleCompleteOrder(
        WEB3AccTransChangeFromIfoDepositCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "handleCompleteOrder(" +
                "WEB3AccTransChangeFromIfoDepositCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeFromIfoDepositService l_service = null;
        WEB3AccTransChangeFromIfoDepositCompleteResponse l_response = null;

        try
        {
            l_service =
                (WEB3AccTransChangeFromIfoDepositService) Services.getService(
                    WEB3AccTransChangeFromIfoDepositService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�؋�������U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�U�֏����̓o�^�T�[�r�X�����s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
