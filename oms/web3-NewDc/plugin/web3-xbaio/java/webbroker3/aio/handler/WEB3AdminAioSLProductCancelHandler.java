head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ�������n���h���N���X(WEB3AdminAioSLProductCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��760
Revision History : 2007/09/28 ���� (���u) �d�l�ύX���f��797
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�S�ۖ�������n���h��)<BR>
 * �S�ۖ�������n���h���N���X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3AdminAioSLProductCancelHandler implements MessageHandler
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductCancelHandler.class);

    /**
     * @@roseuid 46E8EE2A0375
     */
    public WEB3AdminAioSLProductCancelHandler()
    {

    }

    /**
     *�ivalidate�S�ۖ�������j<BR>
     * �S�ۖ�������m�F��ʕ\���������s���B<BR>
     * <BR>
     * �S�ۖ�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminSLProductCancelConfirmResponse
     */
    public WEB3AdminSLProductCancelConfirmResponse validateSLProductCancel(
        WEB3AdminSLProductCancelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateSLProductCancel(WEB3AdminSLProductCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductCancelConfirmResponse l_response = null;
        WEB3AdminAioSLProductCancelService l_service = null;

        // �S�ۖ�������T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAioSLProductCancelService)Services.getService(
                WEB3AdminAioSLProductCancelService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "�S�ۖ�������T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�S�ۖ�������T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminSLProductCancelConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request, "�S�ۖ�������m�F��ʕ\�������̎��{�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
               "�S�ۖ�������m�F��ʕ\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     *�isubmit�S�ۖ�������j<BR>
     * �S�ۖ������������ʕ\���������s���B<BR>
     * <BR>
     * �S�ۖ�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminSLProductCancelCompleteResponse
     */
    public WEB3AdminSLProductCancelCompleteResponse submitSLProductCancel(
        WEB3AdminSLProductCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitSLProductCancel(WEB3AdminSLProductCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductCancelCompleteResponse l_response = null;
        WEB3AdminAioSLProductCancelService l_service = null;

        // �S�ۖ�������T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAioSLProductCancelService)Services.getService(
                WEB3AdminAioSLProductCancelService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "�S�ۖ�������T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�S�ۖ�������T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminSLProductCancelCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request, "�S�ۖ������������ʕ\�������̎��{�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
               "�S�ۖ������������ʕ\�������̎��{�Ɏ��s���܂����B",
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
