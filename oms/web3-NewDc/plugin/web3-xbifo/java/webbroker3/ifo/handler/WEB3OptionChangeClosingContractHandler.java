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
filename	WEB3OptionChangeClosingContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������ԍσn���h���N���X(WEB3OptionChangeClosingContractHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteRequest;
import webbroker3.ifo.service.delegate.WEB3OptionChangeClosingContractService;

import webbroker3.util.WEB3LogUtility;

/**
 * (OP�����ԍσn���h��)<BR>
 * �����w���I�v�V���������ԍσn���h���N���X<BR>
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3OptionChangeClosingContractHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeClosingContractHandler.class);

    /**
     * @@roseuid 40C075590222
     */
    public WEB3OptionChangeClosingContractHandler()
    {

    }

    /**
     * (confirm�����ԍ�)<BR>
     * �����w���I�v�V�����̒����ԍϔ����R�����s���B<BR>
     * <BR>
     * �����w���I�v�V���������ԍσT�[�r�X���擾���A<BR>execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V���������ԍϊm�F���N�G�X�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmResponse
     * @@roseuid 4056D0FE004B
     */
    public WEB3OptionsCloseMarginChangeConfirmResponse confirmChangeClosingContract(
        WEB3OptionsCloseMarginChangeConfirmRequest l_request)
    {

        final String STR_METHOD_NAME =
            getClass().getName() +
            ".confirmChangeClosingContract(WEB3OptionsCloseMarginChangeConfirmRequest l_request)";

        log.entering(STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeConfirmResponse l_response = null;
        WEB3OptionChangeClosingContractService l_service = null;
        log.debug("l_response = " + l_response);
        log.debug("l_service = " + l_service);
        //�����w���I�v�V���������V�K���T�[�r�X���擾����
        try
        {

            l_service =
                (WEB3OptionChangeClosingContractService)Services.getService(
                WEB3OptionChangeClosingContractService.class);

        }

        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {

            l_response = (WEB3OptionsCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���I�v�V���������ԍσT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            return l_response;

        }

        //�����w���I�v�V���������V�K���T�[�r�X.execute()���\�b�h���R�[������
        try
        {

            l_response =
                (WEB3OptionsCloseMarginChangeConfirmResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {

            l_response = (WEB3OptionsCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V���������ԍςɎ��s���܂����B", l_ex);
            return l_response;

        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3OptionsCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V���������ԍςɎ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }

    /**
     * (complete�����ԍ�)<BR>
     * �����w���I�v�V�����̒����ԍϒ�����o�^����B<BR>
     * <BR>
     * �����w���I�v�V���������ԍσT�[�r�X���擾���A<BR>execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V���������ԍϊ������N�G�X�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteResponse
     * @@roseuid 4056D0FE006B
     */
    public WEB3OptionsCloseMarginChangeCompleteResponse completeChangeClosingContract(
        WEB3OptionsCloseMarginChangeCompleteRequest l_request)
    {

        final String STR_METHOD_NAME =
            getClass().getName() +
            ".completeChangeClosingContract(WEB3OptionsCloseMarginChangeCompleteRequest l_request)";


        log.entering(STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeCompleteResponse l_response = null;
        WEB3OptionChangeClosingContractService l_service = null;

        //�����w���I�v�V���������V�K���T�[�r�X���擾����
        try
        {

            l_service =
                (WEB3OptionChangeClosingContractService)Services.getService(
                WEB3OptionChangeClosingContractService.class);

        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {

            l_response = (WEB3OptionsCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���I�v�V���������ԍσT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            return l_response;

        }

        //�����w���I�v�V���������V�K���T�[�r�X.execute()���\�b�h���R�[������
        //�����w���I�v�V���������V�K���T�[�r�X.execute()���\�b�h���R�[������
        try
        {

            l_response =
                (WEB3OptionsCloseMarginChangeCompleteResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {

            l_response = (WEB3OptionsCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V���������ԍςɎ��s���܂����B", l_ex);
            return l_response;

        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3OptionsCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V���������ԍςɎ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }
}
@
