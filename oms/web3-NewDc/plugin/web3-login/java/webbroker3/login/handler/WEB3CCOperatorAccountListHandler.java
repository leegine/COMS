head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.28.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorAccountListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CC�I�y���[�^�Ώیڋq�ꗗ�n���h��(WEB3CCOperatorAccountListHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 ���n�m (���u) �V�K�쐬 ���f��No.039
*/

package webbroker3.login.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.message.Response;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.login.message.WEB3CCOperatorAccountListRequest;
import webbroker3.login.message.WEB3CCOperatorAccountListResponse;
import webbroker3.login.service.delegate.WEB3CCOperatorAccountListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (CC�I�y���[�^�Ώیڋq�ꗗ�n���h��)<BR>
 * CC�I�y���[�^�Ώیڋq�ꗗ�n���h��<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3CCOperatorAccountListHandler implements MessageHandler
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3CCOperatorAccountListHandler.class);

    /**
     * (CC�I�y���[�^�Ώیڋq�ꗗ�n���h��)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 46A45A21004E
     */
    public WEB3CCOperatorAccountListHandler()
    {

    }

    /**
     * (CC�I�y���[�^�Ώیڋq�ꗗ���N�G�X�g)<BR>
     * CC�I�y���[�^�Ώیڋq�ꗗ���N�G�X�g<BR>
     * @@param l_request - (���N�G�X�g)
     * ���N�G�X�g
     * @@return Response
     * @@roseuid 46949C3C0207
     */
    public Response ccOperatorAccountListRequest(WEB3CCOperatorAccountListRequest l_request)
    {

        final String STR_METHOD_NAME =
            " ccOperatorAccountListRequest(WEB3CCOperatorAccountListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3CCOperatorAccountListResponse l_response = null;
        WEB3CCOperatorAccountListService l_service = null;

        try
        {
            l_service =
                (WEB3CCOperatorAccountListService)Services.getService(
                    WEB3CCOperatorAccountListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3CCOperatorAccountListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "CC�I�y���[�^�Ώیڋq�ꗗ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3CCOperatorAccountListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3CCOperatorAccountListResponse)l_request.createResponse();
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
