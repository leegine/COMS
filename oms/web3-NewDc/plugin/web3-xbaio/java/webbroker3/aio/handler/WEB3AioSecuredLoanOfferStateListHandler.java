head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecuredLoanOfferStateListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۃ��[���\���󋵈ꗗ�n���h��(WEB3AioSecuredLoanOfferStateListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.756,No.790
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListRequest;
import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListResponse;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanOfferStateListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�S�ۃ��[���\���󋵈ꗗ�n���h��)<BR>
 * �S�ۃ��[���\���󋵈ꗗ�n���h���N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AioSecuredLoanOfferStateListHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecuredLoanOfferStateListHandler.class);

    /**
     * @@roseuid 46E0BE4702AF
     */
    public WEB3AioSecuredLoanOfferStateListHandler()
    {

    }

    /**
     * (get�ꗗ���)<BR>
     * �S�ۃ��[���\���󋵈ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSLAccountOpenApplyListResponse
     * @@roseuid 46D287BB0366
     */
    public WEB3AdminSLAccountOpenApplyListResponse getListScreen(
        WEB3AdminSLAccountOpenApplyListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getListScreen(WEB3AdminSLAccountOpenApplyListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AioSecuredLoanOfferStateListService l_service;
        WEB3AdminSLAccountOpenApplyListResponse l_response;

        try
        {
            //�S�ۃ��[���\���󋵈ꗗ�T�[�r�X
            l_service =
                (WEB3AioSecuredLoanOfferStateListService)Services.getService(
                    WEB3AioSecuredLoanOfferStateListService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLAccountOpenApplyListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�S�ۃ��[���\���󋵈ꗗ�T�[�r�X�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSLAccountOpenApplyListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLAccountOpenApplyListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�S�ۃ��[���\���󋵈ꗗ�T�[�r�X.execute()",
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_excRuntime)
        {
            l_response =
                (WEB3AdminSLAccountOpenApplyListResponse)l_request.createResponse();
            l_response.errorInfo = l_excRuntime.getErrorInfo();
            log.error(
                l_request,
                "�S�ۃ��[���\���󋵈ꗗ�T�[�r�X.execute()",
                l_excRuntime);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
