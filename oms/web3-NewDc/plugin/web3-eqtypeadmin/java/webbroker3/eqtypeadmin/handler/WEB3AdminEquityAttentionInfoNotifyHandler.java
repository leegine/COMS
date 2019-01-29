head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӏ��ʒm�n���h��(WEB3AdminEquityAttentionInfoNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/04 ������(���u) �V�K�쐬 ���f��No.219
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ӏ��ʒm�n���h��)<BR>
 * ���ӏ��ʒm�n���h��<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoNotifyHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoNotifyHandler.class);

    /**
     * @@roseuid 49588AEC00AB
     */
    public WEB3AdminEquityAttentionInfoNotifyHandler()
    {

    }

    /**
     * (���ӏ��ʒm���N�G�X�g)<BR>
     * ���ӏ��ʒm���������{����B<BR>
     * <BR>
     * ���ӏ��ʒm�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminEquityAttentionInfoNotifyResponse
     * @@roseuid 493DF4EB032D
     */
    public WEB3AdminEquityAttentionInfoNotifyResponse attentionInfoNotifyRequest(
        WEB3AdminEquityAttentionInfoNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = "attentionInfoNotifyRequest(WEB3AdminEquityAttentionInfoNotifyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityAttentionInfoNotifyService l_service = null;
        WEB3AdminEquityAttentionInfoNotifyResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminEquityAttentionInfoNotifyService)Services.getService(
                    WEB3AdminEquityAttentionInfoNotifyService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityAttentionInfoNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���ӏ��ʒm�T�[�r�X �̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminEquityAttentionInfoNotifyResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminEquityAttentionInfoNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���ӏ��ʒm�����̎��{�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminEquityAttentionInfoNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���ӏ��ʒm�����̎��{�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
