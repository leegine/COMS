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
filename	WEB3AdminEquityForcedSettleReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ����Ɖ�n���h��(WEB3AdminEquityForcedSettleReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 ��іQ (���u) �V�K�쐬 ���f��No.129
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ϒ����Ɖ�n���h��)<BR>
 * �Ǘ��ҁE�������ϒ����Ɖ�n���h���N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleReferenceHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleReferenceHandler.class);

    /**
     * @@roseuid 462CA416039F
     */
    public WEB3AdminEquityForcedSettleReferenceHandler()
    {

    }

    /**
     * (get���͉��)<BR>
     * �������ϒ����Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�������ϒ����Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ϒ����Ɖ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputResponse
     * @@roseuid 4601EA71006C
     */
    public WEB3AdminForcedSettleRefInputResponse getInputScreen(WEB3AdminForcedSettleRefInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminForcedSettleRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleRefInputResponse l_response = null;
        WEB3AdminEquityForcedSettleReferenceService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleReferenceService)Services.getService(
                    WEB3AdminEquityForcedSettleReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleRefInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�������ϒ����Ɖ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()���\�b�h���R�[������B
        try
        {
            l_response =
                (WEB3AdminForcedSettleRefInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleRefInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ɖ���)<BR>
     * �������ϒ����Ɖ�����s���B<BR>
     * <BR>
     * �Ǘ��ҁE�������ϒ����Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ϒ����Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceResponse
     * @@roseuid 4601EA71008B
     */
    public WEB3AdminForcedSettleReferenceResponse getReferenceScreen(WEB3AdminForcedSettleReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = "getReferenceScreen(WEB3AdminForcedSettleReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceResponse l_response = null;
        WEB3AdminEquityForcedSettleReferenceService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleReferenceService)Services.getService(
                    WEB3AdminEquityForcedSettleReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�������ϒ����Ɖ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()���\�b�h���R�[������B
        try
        {
            l_response =
                (WEB3AdminForcedSettleReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
