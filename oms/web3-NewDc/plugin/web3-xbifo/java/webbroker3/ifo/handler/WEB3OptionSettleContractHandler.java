head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionSettleContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�ԍϒ����n���h��(WEB3OptionSettleContractHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/23 ����� (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequest;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderService;


/**
 * (OP�ԍϒ����n���h��)<BR>
 * �����w���I�v�V�����ԍϒ����n���h���N���X<BR>
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3OptionSettleContractHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionSettleContractHandler.class);

    /**
     * (confirm�ԍ�)<BR>
     * <BR>
     * �����w���I�v�V�����̕ԍϔ����R�����s���B<BR>
     * <BR>
     * �I�v�V�����ԍϒ����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmResponse
     * @@roseuid 405511FF0128
     */
    public WEB3OptionsCloseMarginConfirmResponse confirmSettleContract(WEB3OptionsCloseMarginConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmSettleContract(WEB3OptionsCloseMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsCloseMarginConfirmResponse l_response = null;
        WEB3OptionSettleContractOrderService l_service = null;

        try
        {
            l_service =
                 (WEB3OptionSettleContractOrderService)Services.getService(
                    WEB3OptionSettleContractOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�I�v�V�����ԍϒ����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsCloseMarginConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���I�v�V�����̕ԍϔ����R���Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsCloseMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���I�v�V�����̕ԍϔ����R���Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (complete�ԍ�)<BR>
     * <BR>
     * �����w���I�v�V�����̕ԍϒ�����o�^����B<BR>
     * <BR>
     * �I�v�V�����ԍϒ����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteResponse
     * @@roseuid 4055120C027F
     */
    public WEB3OptionsCloseMarginCompleteResponse completeSettleContract(WEB3OptionsCloseMarginCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeSettleContract(WEB3OptionsCloseMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsCloseMarginCompleteResponse l_response = null;
        WEB3OptionSettleContractOrderService l_service = null;

        try
        {
            l_service =
                 (WEB3OptionSettleContractOrderService)Services.getService(
                    WEB3OptionSettleContractOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�I�v�V�����ԍϒ����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsCloseMarginCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���I�v�V�����̕ԍϒ����Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsCloseMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���I�v�V�����̕ԍϒ����Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;

    }
}
@
