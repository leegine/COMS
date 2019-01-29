head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֐����ʒm�n���h��(WEB3AccTransChangeRequestNotifyHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/26 ���E(���u) ���r���[
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AccTransChangeRequestNotifyRequest;
import webbroker3.aio.message.WEB3AccTransChangeRequestNotifyResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�U�֐����ʒm�n���h��)<BR>
 * �U�֐����ʒm�n���h���N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestNotifyHandler implements MessageHandler
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestNotifyHandler.class);

    /**
     * (�U�֐����ʒm���N�G�X�g)<BR>
     * �U�֐����ʒm�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AccTransChangeRequestNotifyResponse
     * @@roseuid 413C2DF301A7
     */
    public WEB3AccTransChangeRequestNotifyResponse handleAccTransChangeRequestNotifyRequest(
        WEB3AccTransChangeRequestNotifyRequest l_request)
    {
        final String STR_METHOD_NAME =
            "handleAccTransChangeRequestNotifyRequest(" +
                "WEB3AccTransChangeRequestNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeRequestNotifyResponse l_response = null;
        WEB3AccTransChangeRequestNotifyService l_accTransChangeRequestNotifyService =
            null;

        try
        {
            l_accTransChangeRequestNotifyService =
                (WEB3AccTransChangeRequestNotifyService) Services.getService(
                    WEB3AccTransChangeRequestNotifyService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeRequestNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�U�֐����ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //�U�֐����ʒm�T�[�r�X���擾���Aexecute()���\�b�h���R�[������
            l_response =
                (WEB3AccTransChangeRequestNotifyResponse) l_accTransChangeRequestNotifyService.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeRequestNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�U�֐����ʒm���N�G�X�g�����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
