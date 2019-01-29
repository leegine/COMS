head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionSettleContractInputServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���jOP�ԍϓ��̓n���h��(WEB3ToSuccOptionSettleContractInputServiceHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/04/09 �k�v�u(���u) �V�K�쐬 ���f��297
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���jOP�ԍϓ��̓n���h��)<BR>
 * �i�A���jOP�ԍϓ��̓n���h���N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3ToSuccOptionSettleContractInputServiceHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractInputServiceHandler.class);

    /**
     * @@roseuid 47FDBE3E0119
     */
    public WEB3ToSuccOptionSettleContractInputServiceHandler()
    {

    }

    /**
     * (get�ԍϓ��͉��)<BR>
     * �i�A���j�����w���I�v�V�����ԍϓ��͉�ʕ\���������s���B<BR>
     * <BR>
     * �i�A���jOP�ԍϓ��̓T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccOptionsCloseInputResponse
     * @@roseuid 47A9279C02C4
     */
    public WEB3SuccOptionsCloseInputResponse getSettleContractInputScreen(
        WEB3SuccOptionsCloseInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getSettleContractInputScreen(WEB3SuccOptionsCloseInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseInputResponse l_response = null;
        WEB3ToSuccOptionSettleContractInputService l_service = null;

        //�i�A���jOP�ԍϓ��̓T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3ToSuccOptionSettleContractInputService)Services.getService(
                    WEB3ToSuccOptionSettleContractInputService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���jOP�ԍϓ��̓T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�i�A���jOP�ԍϓ��̓T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SuccOptionsCloseInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w��OP�ԍϓ��͉�ʕ\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w��OP�ԍϓ��͉�ʕ\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
