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
filename	WEB3OptionOpenContractOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : OP�V�K�������n���h��(WEB3OptionOpenContractOrderHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/06/19 ������ (���u) �V�K�쐬
                 001: 2004/07/31 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000097
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP�V�K�������n���h��)<BR>
 * �����w���I�v�V�����V�K�������n���h���N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionOpenContractOrderHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOpenContractOrderHandler.class);

    /**
     * @@roseuid 40C0AF3300EA
     */
    public WEB3OptionOpenContractOrderHandler()
    {

    }

    /**
     * (confirm����)<BR>
     * <BR>
     * �����w���I�v�V�����̐V�K�������R�����s���B<BR>
     * <BR>
     * �����w���I�v�V�����V�K�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���I�v�V�����V�K�������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmResponse
     * @@roseuid 404EF34403D7
     */
    public WEB3OptionsOpenMarginConfirmResponse confirmOrder(WEB3OptionsOpenMarginConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = ".confirmOrder(WEB3OptionsOpenMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3OptionsOpenMarginConfirmResponse l_response = null;
        WEB3OptionOpenContractOrderService l_service = null;

        //�����w���I�v�V�����̐V�K�������R�����s���B
        try
        {
            l_service = (WEB3OptionOpenContractOrderService)Services.getService(WEB3OptionOpenContractOrderService.class);

            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3OptionsOpenMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���I�v�V�����V�K�������T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);

            return l_response;
        }
        
        //�����w���I�v�V�����V�K�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3OptionsOpenMarginConfirmResponse) l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsOpenMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���I�v�V�����V�K�������̔����R���Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3OptionsOpenMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���I�v�V�����V�K�������̔����R���Ɏ��s���܂����B", l_rex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (complete����)<BR>
     * <BR>
     * �����w���I�v�V�����̐V�K��������o�^����B<BR>
     * <BR>
     * �����w���I�v�V�����V�K�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���I�v�V�����V�K�������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3OptionsOpenMarginCompleteResponse
     * @@roseuid 404EF345000F
     */
    public WEB3OptionsOpenMarginCompleteResponse completeOrder(WEB3OptionsOpenMarginCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeOrder(WEB3OptionsOpenMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3OptionsOpenMarginCompleteResponse l_response = null;
        WEB3OptionOpenContractOrderService l_service = null;

        //�����w���I�v�V�����̐V�K��������o�^����
        try
        {
            l_service = (WEB3OptionOpenContractOrderService)Services.getService(WEB3OptionOpenContractOrderService.class);
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3OptionsOpenMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���I�v�V�����V�K�������T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);

            return l_response;
        }
        
        //�����w���I�v�V�����V�K�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3OptionsOpenMarginCompleteResponse)l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsOpenMarginCompleteResponse)l_request.createResponse();        
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���I�v�V�����V�K�������̓o�^�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3OptionsOpenMarginCompleteResponse)l_request.createResponse();        
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "�����w���I�v�V�����V�K�������̓o�^�Ɏ��s���܂����B", l_rex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
