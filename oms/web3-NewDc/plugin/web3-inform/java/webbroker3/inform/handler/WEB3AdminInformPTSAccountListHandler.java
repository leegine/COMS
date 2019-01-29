head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.57.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ���PTS�\���q�ꗗ�⍇���n���h��(WEB3AdminInformPTSAccountListHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 �Ӑ�(���u) �V�K�쐬 ���f��No.130
*/

package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccountListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���PTS�\���q�ꗗ�⍇���n���h��)<BR>
 * �Ǘ���PTS�\���q�ꗗ�⍇���n���h��<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListHandler implements MessageHandler
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListHandler.class);

    /**
     * @@roseuid 47C6665102C9
     */
    public WEB3AdminInformPTSAccountListHandler()
    {

    }

    /**
     * (������ʕ\��)<BR>
     * �Ǘ���PTS�\���q�ꗗ�⍇��������ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformPTSAccountListInquiryResponse
     * @@roseuid 47B538B101B9
     */
    public WEB3AdminInformPTSAccountListInquiryResponse displaySearchScreen(
        WEB3AdminInformPTSAccountListInquiryRequest l_request)
    {
        final String STR_METHOD_NAME = "displaySearchScreen(WEB3AdminInformPTSAccountListInquiryRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInquiryResponse l_response = null;
        WEB3AdminInformPTSAccountListService l_service = null;

        //�Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformPTSAccountListService)Services.getService(
                WEB3AdminInformPTSAccountListService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminInformPTSAccountListInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformPTSAccountListInquiryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccountListInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ���PTS�\���q�ꗗ�⍇��������ʂ̎擾�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccountListInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ���PTS�\���q�ꗗ�⍇��������ʂ̎擾�ɃG���[���������܂����B",
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
     * (�������ʕ\��)<BR>
     * �Ǘ���PTS�\���q�ꗗ�⍇���������ʉ�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformPTSAccountListResultResponse
     * @@roseuid 47B539350381
     */
    public WEB3AdminInformPTSAccountListResultResponse displaySearchResultScreen(
        WEB3AdminInformPTSAccountListResultRequest l_request)
    {
        final String STR_METHOD_NAME = "displaySearchResultScreen(WEB3AdminInformPTSAccountListResultRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListResultResponse l_response = null;
        WEB3AdminInformPTSAccountListService l_service = null;

        //�Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformPTSAccountListService)Services.getService(
                WEB3AdminInformPTSAccountListService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminInformPTSAccountListResultResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformPTSAccountListResultResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccountListResultResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ���PTS�\���q�ꗗ�⍇���������ʉ�ʂ̎擾�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccountListResultResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ���PTS�\���q�ꗗ�⍇���������ʉ�ʂ̎擾�ɃG���[���������܂����B",
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
