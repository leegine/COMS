head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����w���敨�V�K�������n���h���N���X(WEB3FuturesOpenMarginHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/21 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����w���敨�V�K�������n���h��)<BR>
 * �����w���敨�V�K�������n���h���N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3FuturesOpenContractHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOpenContractHandler.class);

    /**
     * @@roseuid 40F7B07100FA
     */
    public WEB3FuturesOpenContractHandler()
    {

    }

    /**
     * (confirm����)<BR>
     * �����w���敨�̐V�K�������R�����s���B<BR>
     * <BR>
     * �����w���敨�V�K�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���敨�V�K�������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3FuturesOpenMarginConfirmResponse
     * @@roseuid 40A84A4701E6
     */
    public WEB3FuturesOpenMarginConfirmResponse confirmOrder(WEB3FuturesOpenMarginConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmOrder(WEB3FuturesOpenMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesOpenMarginConfirmResponse l_response = null;
        WEB3FuturesOpenContractService l_service = null;

        //�����w���敨�̐V�K�������R�����s��
        try
        {
            l_service = (WEB3FuturesOpenContractService) Services.getService(WEB3FuturesOpenContractService.class);

            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3FuturesOpenMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���敨�V�K�������T�[�r�X�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            return l_response;
        }
        //�����w���敨�V�K�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3FuturesOpenMarginConfirmResponse) l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOpenMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���敨�V�K�������̔����R�������Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesOpenMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���敨�V�K�������̔����R�������Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (complete����)<BR>
     * �����w���敨�̐V�K��������o�^����B<BR>
     * <BR>
     * �����w���敨�V�K�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���敨�V�K�������������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3FuturesOpenMarginCompleteResponse
     * @@roseuid 40A84A470206
     */
    public WEB3FuturesOpenMarginCompleteResponse completeOrder(WEB3FuturesOpenMarginCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeOrder(WEB3FuturesOpenMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesOpenMarginCompleteResponse l_response = null;
        WEB3FuturesOpenContractService l_service = null;

        //�����w���敨�̐V�K��������o�^����
        try
        {
            l_service = (WEB3FuturesOpenContractService) Services.getService(WEB3FuturesOpenContractService.class);
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3FuturesOpenMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���敨�V�K�������T�[�r�X�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            return l_response;
        }
        //�����w���敨�̐V�K��������o�^����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3FuturesOpenMarginCompleteResponse) l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOpenMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���敨�V�K�������̓o�^�����Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesOpenMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���敨�V�K�������̓o�^�����Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
