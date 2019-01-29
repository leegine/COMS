head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeClosingContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�i�A���jOP�����ԍϓ��̓n���h���iWEB3ToSuccOptionChangeClosingContractInputHandler.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 �И���(���u) �V�K�쐬 ���f��No.306
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeClosingContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���jOP�����ԍϓ��̓n���h��)<BR>
 * �i�A���j�����w���I�v�V���������ԍϓ��̓n���h���N���X<BR>
 *
 * @@author �И���(���u)
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeClosingContractInputHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeClosingContractInputHandler.class);

    /**
     * @@roseuid 47FDBE3E005D
     */
    public WEB3ToSuccOptionChangeClosingContractInputHandler()
    {

    }

    /**
     * (�����ԍϓ��̓��N�G�X�g)<BR>
     * �i�A���j�����w���I�v�V���������ԍϓ��͕\���������s���B <BR>
     * <BR>
     * �i�A���j�����w���I�v�V���������ԍϓ��̓T�[�r�X���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccOptionsCloseChangeInputResponse
     * @@roseuid 47A92BCD017C
     */
    public WEB3SuccOptionsCloseChangeInputResponse changeCloseInputRequest(
        WEB3SuccOptionsCloseChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "changeCloseInputRequest(WEB3SuccOptionsCloseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseChangeInputResponse l_response = null;
        WEB3ToSuccOptionChangeClosingContractInputService l_service = null;

        //�i�A���j�����w���I�v�V���������ԍϓ��̓T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3ToSuccOptionChangeClosingContractInputService)Services.getService(
                        WEB3ToSuccOptionChangeClosingContractInputService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�����w���I�v�V���������ԍϓ��̓T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�i�A���j�����w���I�v�V���������ԍϓ��̓T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SuccOptionsCloseChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���I�v�V���������ԍϓ��͕\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���I�v�V���������ԍϓ��͕\�����������s���܂����B",
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
