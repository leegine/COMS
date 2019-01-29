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
filename	WEB3FuturesSettleContractOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍϒ����n���h��(WEB3FuturesSettleContractOrderHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderService;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmResponse;

/**
 * (�����w���敨�ԍϒ����n���h��)<BR>
 * �����w���敨�ԍϒ����n���h���N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesSettleContractOrderHandler implements MessageHandler
{

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractOrderHandler.class);

    /**
     * @@roseuid 40F7B0720196
     */
    public WEB3FuturesSettleContractOrderHandler()
    {

    }

    /**
     * (confirm�ԍ�)<BR>
     * �����w���敨�̕ԍϔ����R�����s���B<BR>
     * <BR>
     * �敨�ԍϒ����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmResponse
     * @@roseuid 40A8C17C03D5
     */
    public WEB3FuturesCloseMarginConfirmResponse confirmCloseMargin(WEB3FuturesCloseMarginConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmCloseMargin(WEB3FuturesCloseMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginConfirmResponse l_response = null;
        WEB3FuturesSettleContractOrderService l_service = null;

        try
        {
            l_service = (WEB3FuturesSettleContractOrderService) Services.getService(WEB3FuturesSettleContractOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesCloseMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�敨�ԍϒ����T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesCloseMarginConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesCloseMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().error_debug_info;
            log.error(l_request, "�����w���敨�̕ԍϔ����R���Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesCloseMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().error_debug_info;
            log.error(l_request, "�����w���敨�̕ԍϔ����R���Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (complete�ԍ�)<BR>
     * �����w���敨�̕ԍϒ�����o�^����B<BR>
     * <BR>
     * �敨�ԍϒ����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteResponse
     * @@roseuid 40A8C17D000D
     */
    public WEB3FuturesCloseMarginCompleteResponse completeCloseMargin(WEB3FuturesCloseMarginCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeCloseMargin(WEB3FuturesCloseMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginCompleteResponse l_response = null;
        WEB3FuturesSettleContractOrderService l_service = null;

        try
        {
            l_service = (WEB3FuturesSettleContractOrderService) Services.getService(WEB3FuturesSettleContractOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesCloseMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�敨�ԍϒ����T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesCloseMarginCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesCloseMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().error_debug_info;
            log.error(l_request, "�����w���敨�̕ԍϒ����Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesCloseMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().error_debug_info;
            log.error(l_request, "�����w���敨�̕ԍϒ����Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;

    }
}
@
