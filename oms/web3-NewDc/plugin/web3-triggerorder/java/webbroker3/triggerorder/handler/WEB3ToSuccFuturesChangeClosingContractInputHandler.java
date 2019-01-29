head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeClosingContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�敨�����ԍϓ��̓n���h���iWEB3ToSuccFuturesChangeClosingContractInputHandler.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/14 �И���(���u) �V�K�쐬���f��No.264
 */

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�敨�����ԍϓ��̓n���h��)<BR>
 * �i�A���j�����w���敨�����ԍϓ��̓n���h���N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3ToSuccFuturesChangeClosingContractInputHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeClosingContractInputHandler.class);

    /**
     * @@roseuid 47D65936008D
     */
    public WEB3ToSuccFuturesChangeClosingContractInputHandler()
    {

    }

    /**
     * (�����ԍϓ��̓��N�G�X�g)<BR>
     * �i�A���j�����w���敨�����ԍϓ��͕\���������s���B <BR>
     * <BR>
     * �i�A���j�����w���敨�����ԍϓ��̓T�[�r�X���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesCloseChangeInputResponse
     * @@roseuid 47A94F2D03DD
     */
    public WEB3SuccFuturesCloseChangeInputResponse closeChangeInputRequest(
        WEB3SuccFuturesCloseChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "closeChangeInputRequest(WEB3SuccFuturesCloseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseChangeInputResponse l_response = null;
        WEB3ToSuccFuturesChangeClosingContractInputService l_service = null;

        //�i�A���j�����w���敨�����ԍϓ��̓T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3ToSuccFuturesChangeClosingContractInputService)Services.getService(
                    WEB3ToSuccFuturesChangeClosingContractInputService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�����w���敨�����ԍϓ��̓T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�i�A���j�����w���敨�����ԍϓ��̓T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SuccFuturesCloseChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���敨�����ԍϓ��͕\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���敨�����ԍϓ��͕\�����������s���܂����B",
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
