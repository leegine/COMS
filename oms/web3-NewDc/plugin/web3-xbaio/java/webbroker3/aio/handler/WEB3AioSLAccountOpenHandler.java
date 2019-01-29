head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLAccountOpenHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SL�����J�݃n���h��(WEB3AioSLAccountOpenHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 �Ӑ� (���u) �V�K�쐬 ���f��No.763
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3SLAccountOpenApplyRequest;
import webbroker3.aio.message.WEB3SLAccountOpenApplyResponse;
import webbroker3.aio.message.WEB3SLAccountOpenRequest;
import webbroker3.aio.message.WEB3SLAccountOpenResponse;
import webbroker3.aio.service.delegate.WEB3AioSLAccountOpenUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (SL�����J�݃n���h��)<BR>
 * SL�����J�݃n���h���N���X<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AioSLAccountOpenHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLAccountOpenHandler.class);

    /**
     * @@roseuid 46F0D58001E4
     */
    public WEB3AioSLAccountOpenHandler()
    {

    }

    /**
     * (SL�����J�݊m�F)<BR>
     * SL�����J��Unit�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * <BR>
     * @@return WEB3SLAccountOpenApplyResponse
     * @@roseuid 46BBF45E0187
     */
    public WEB3SLAccountOpenApplyResponse slAccountOpenConfirm(WEB3SLAccountOpenApplyRequest l_request)
    {
        final String STR_METHOD_NAME = " slAccountOpenConfirm(WEB3SLAccountOpenApplyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SLAccountOpenApplyResponse l_response = null;
        WEB3AioSLAccountOpenUnitService l_aioSLAccountOpenUnitService = null;

        try
        {
            l_aioSLAccountOpenUnitService =
                (WEB3AioSLAccountOpenUnitService)Services.getService(
                    WEB3AioSLAccountOpenUnitService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SLAccountOpenApplyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "SL�����J��UnitService�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //SL�����J��UnitService���擾���Aexecute()���\�b�h���R�[������
            l_response =
                (WEB3SLAccountOpenApplyResponse)l_aioSLAccountOpenUnitService.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SLAccountOpenApplyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "SL�����J�ݐ\�����N�G�X�g�����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLAccountOpenApplyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "SL�����J��UnitService���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (SL�����J��)<BR>
     * SL�����J��Unit�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * <BR>
     * @@return WEB3SLAccountOpenResponse
     * @@roseuid 46CE402C018B
     */
    public WEB3SLAccountOpenResponse slAccountOpen(WEB3SLAccountOpenRequest l_request)
    {
        final String STR_METHOD_NAME = " slAccountOpen(WEB3SLAccountOpenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SLAccountOpenResponse l_response = null;
        WEB3AioSLAccountOpenUnitService l_aioSLAccountOpenUnitService = null;

        try
        {
            l_aioSLAccountOpenUnitService =
                (WEB3AioSLAccountOpenUnitService)Services.getService(
                    WEB3AioSLAccountOpenUnitService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SLAccountOpenResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "SL�����J��UnitService�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //�،��U�֒ʒm�T�[�r�X���擾���Aexecute()���\�b�h���R�[������
            l_response =
                (WEB3SLAccountOpenResponse)l_aioSLAccountOpenUnitService.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SLAccountOpenResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "SL�����J�݃��N�G�X�g�����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLAccountOpenResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "SL�����J��UnitService���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
