head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTListReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���Ɖ�n���h��(WEB3AdminFPTListReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 ���g (���u) �V�K�쐬
*/

package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTListReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҋ����@@��t�{���Ɖ�n���h��)<BR>
 * �Ǘ��ҋ����@@��t�{���Ɖ�n���h���N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTListReferenceHandler implements MessageHandler
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTListReferenceHandler.class);

    /**
     * @@roseuid 46FDDD3E0242
     */
    public WEB3AdminFPTListReferenceHandler()
    {

    }

    /**
     * (get��t�{���ꗗ�������)<BR>
     * �����@@��t�{���ꗗ������ʂ�\�����鏈�����R�[������B<BR>
     * <BR>
     * �P�j�@@�Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�X���擾���Aexecute���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTSearchInputResponse
     * @@roseuid 46F1D077023E
     */
    public WEB3AdminFPTSearchInputResponse getListSearchScreen(WEB3AdminFPTSearchInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getListSearchScreen(WEB3AdminFPTSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTSearchInputResponse l_response;
        WEB3AdminFPTListReferenceService l_service;

        //�Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�XImpl���擾��
        try
        {
            l_service = (WEB3AdminFPTListReferenceService)Services.getService(
                WEB3AdminFPTListReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminFPTSearchInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{����ʕ\�������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get��t�{���ꗗ�Ɖ���)<BR>
     * �����@@��t�{���ꗗ��ʂ�\�����鏈�����R�[������B<BR>
     * <BR>
     * �P�j�@@�Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�X���擾���Aexecute���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTListReferenceResponse
     * @@roseuid 46F1D267023A
     */
    public WEB3AdminFPTListReferenceResponse getListReferenceScreen(WEB3AdminFPTListReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = " getSearchInputScreen(WEB3AdminFPTSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTListReferenceResponse l_response;
        WEB3AdminFPTListReferenceService l_service;

        //�Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�XImpl���擾��
        try
        {
            l_service =
                (WEB3AdminFPTListReferenceService)Services.getService(
                    WEB3AdminFPTListReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminFPTListReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҋ����@@��t�{����ʕ\�������ɃG���[���������܂����B",
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
