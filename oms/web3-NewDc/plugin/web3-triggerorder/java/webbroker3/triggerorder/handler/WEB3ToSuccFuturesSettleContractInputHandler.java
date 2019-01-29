head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesSettleContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�敨�ԍϓ��̓n���h���iWEB3ToSuccFuturesSettleContractInputHandler.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/17 �И���(���u) �V�K�쐬���f��No.255 No.298
 */
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�敨�ԍϓ��̓n���h��)<BR>
 * �i�A���j�敨�ԍϓ��̓n���h���N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3ToSuccFuturesSettleContractInputHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesSettleContractInputHandler.class);

    /**
     * @@roseuid 47D659360158
     */
    public WEB3ToSuccFuturesSettleContractInputHandler()
    {

    }

    /**
     * (get�ԍϓ��͉��)<BR>
     * �i�A���j�����w���敨�ԍϓ��͉�ʕ\���������s���B<BR>
     * <BR>
     * �i�A���j�敨�ԍϓ��̓T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesCloseInputResponse
     * @@roseuid 47A94B3001E8
     */
    public WEB3SuccFuturesCloseInputResponse getSettleContractInputScreen(WEB3SuccFuturesCloseInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getSettleContractInputScreen(WEB3SuccFuturesCloseInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseInputResponse l_response = null;
        WEB3ToSuccFuturesSettleContractInputService l_service = null;

        //�i�A���j�敨�ԍϒ����T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3ToSuccFuturesSettleContractInputService)Services.getService(
                    WEB3ToSuccFuturesSettleContractInputService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�敨�ԍϓ��̓T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�i�A���j�敨�ԍϓ��̓T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SuccFuturesCloseInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���敨�ԍϓ��͉�ʕ\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���敨�ԍϓ��͉�ʕ\�����������s���܂����B",
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
