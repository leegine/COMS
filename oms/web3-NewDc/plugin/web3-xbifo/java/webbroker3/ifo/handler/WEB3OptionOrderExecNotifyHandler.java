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
filename	WEB3OptionOrderExecNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�o���ʒm�n���h��(WEB3OptionOrderExecNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 羉s(���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.message.WEB3OptionOrderExecNotifyRequest;
import webbroker3.ifo.message.WEB3OptionOrderExecNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecNotifyService;

/**
 * (OP�o���ʒm�n���h��)<BR>
 * �����w���I�v�V�����o���ʒm�n���h���N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionOrderExecNotifyHandler implements MessageHandler
{

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOrderExecNotifyHandler.class);

    /**
     * @@roseuid 40C0755403D8
     */
    public WEB3OptionOrderExecNotifyHandler()
    {

    }

    /**
     * (�I�v�V�����o���ʒm���N�G�X�g)<BR>
     * <BR>
     * �����w���I�v�V�����o���ʒm�������s���B<BR>
     * <BR>
     * �����w���I�v�V�����o���ʒm�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �����w���I�v�V�����o���ʒm���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionOrderExecNotifyResponse
     * @@roseuid 4057BEAE03AC
     */
    public WEB3OptionOrderExecNotifyResponse optionOrderExecNotifyRequest(WEB3OptionOrderExecNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = "optionOrderExecNotifyRequest(WEB3OptionOrderExecNotifyRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3OptionOrderExecNotifyResponse l_response = null;
        WEB3OptionOrderExecNotifyService l_service = null;

        try
        {
            l_service = (WEB3OptionOrderExecNotifyService) Services.getService(WEB3OptionOrderExecNotifyService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_exception)
        {
            l_response = (WEB3OptionOrderExecNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�o���ʒm���s���܂����B", l_response.errorInfo, l_exception);
            return l_response;
        }

        try
        {
            l_response = (WEB3OptionOrderExecNotifyResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_baseException)
        {
            l_response = (WEB3OptionOrderExecNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_baseException.getErrorInfo();
            log.error(l_request, "�o���ʒm���s���܂����B", l_baseException);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
