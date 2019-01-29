head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍψꗗ�n���h��(WEB3AioSLRepayListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.757
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3SLRepayCancelListRequest;
import webbroker3.aio.message.WEB3SLRepayCancelListResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��S�ۃ��[���ԍψꗗ�n���h��)<BR>
 * �،��S�ۃ��[���ԍψꗗ�n���h���N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AioSLRepayListHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayListHandler.class);

    /**
     * @@roseuid 46E890860387
     */
    public WEB3AioSLRepayListHandler()
    {

    }

    /**
     * (�،��S�ۃ��[���ԍψꗗ���N�G�X�g)<BR>
     * �،��S�ۃ��[���ԍψꗗ�\���������s���B<BR>
     * <BR>
     * �،��S�ۃ��[���ԍψꗗ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SLRepayCancelListResponse
     * @@roseuid 46DE35DA0020
     */
    public WEB3SLRepayCancelListResponse repayCancelListRequest(
        WEB3SLRepayCancelListRequest l_request)
    {
        final String STR_METHOD_NAME = "repayCancelListRequest(WEB3SLRepayCancelListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SLRepayCancelListResponse l_response = null;
        WEB3AioSLRepayListService l_slRepayCancelListService = null;

        try
        {
            l_slRepayCancelListService =
                (WEB3AioSLRepayListService)Services.getService(
                        WEB3AioSLRepayListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SLRepayCancelListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�،��S�ۃ��[���ԍψꗗ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //�،��S�ۃ��[���ԍψꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������
            l_response =
                (WEB3SLRepayCancelListResponse)l_slRepayCancelListService.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SLRepayCancelListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�،��S�ۃ��[���ԍψꗗ���N�G�X�g�����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLRepayCancelListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�،��S�ۃ��[���ԍψꗗ�T�[�r�X���������s���܂����B",
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
