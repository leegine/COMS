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
filename	WEB3FuturesOrderExecNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�o���ʒm�n���h���N���X(WEB3FuturesOrderExecNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/23 䈋� (���u) �V�K�쐬
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOrderExecNotifyRequest;
import webbroker3.ifo.message.WEB3FuturesOrderExecNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����w���敨�o���ʒm�n���h��)<BR>
 * �����w���敨�o���ʒm�n���h���N���X<BR>
 */
public class WEB3FuturesOrderExecNotifyHandler implements MessageHandler
{
    /**
         * ���O�o�̓��[�e�B���e�B�B<BR>
         */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderExecNotifyHandler.class);

    /**
     * @@roseuid 40F7B071003E
     */
    public WEB3FuturesOrderExecNotifyHandler()
    {

    }

    /**
     * (�敨�o���ʒm���N�G�X�g)<BR>
     * �����w���敨�o���ʒm�������s���B<BR>
     * <BR>
     * �����w���敨�o���ʒm�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨�o���ʒm���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.message.WEB3FuturesOrderExecNotifyResponse
     * @@roseuid 40A8411403DA
     */
    public WEB3FuturesOrderExecNotifyResponse futuresOrderExecNotifyRequest(WEB3FuturesOrderExecNotifyRequest l_request)
    {

        final String STR_METHOD_NAME =
            getClass().getName()
                + ".futuresOrderExecNotifyRequest(WEB3FuturesOrderExecNotifyRequest l_request)";

        log.debug(STR_METHOD_NAME);

        WEB3FuturesOrderExecNotifyResponse l_response = null;
        WEB3FuturesOrderExecNotifyService l_service = null;

        try
        {
            l_service =
                (WEB3FuturesOrderExecNotifyService)Services.getService(
                    WEB3FuturesOrderExecNotifyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOrderExecNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���敨�o���ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FuturesOrderExecNotifyResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOrderExecNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�o���ʒm�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.debug(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
