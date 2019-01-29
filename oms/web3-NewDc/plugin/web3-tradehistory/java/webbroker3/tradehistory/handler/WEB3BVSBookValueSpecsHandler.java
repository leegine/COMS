head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3BVSBookValueSpecsHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : �뉿�P�����׏Ɖ�n���h��(WEB3BVSBookValueSpecsHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���q (���u) �V�K�쐬
*/

package webbroker3.tradehistory.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsRequest;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsResponse;
import webbroker3.tradehistory.service.delegate.WEB3BVSBookValueSpecsService;
import webbroker3.util.WEB3LogUtility;


/**
 * (�뉿�P�����׏Ɖ�n���h��)<BR>
 * �뉿�P�����׏Ɖ�n���h���N���X<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3BVSBookValueSpecsHandler implements MessageHandler
{
     /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BVSBookValueSpecsHandler.class);

    /**
     * @@roseuid 418877BD0290
     */
    public WEB3BVSBookValueSpecsHandler()
    {

    }

    /**
     * (get�뉿�P�����׏Ɖ�)<BR>
     * �뉿�P�����׏Ɖ�����s���B<BR>
     * <BR>
     * �뉿�P�����׏Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �뉿�P�����׏Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.plsbvs.message.WEB3BVSBookValueSpecsResponse
     * @@roseuid 416E4D9B0063
     */
    public WEB3BVSBookValueSpecsResponse getBookValueSpecs(WEB3BVSBookValueSpecsRequest l_request)
    {
        final String STR_METHOD_NAME = " getBookValueSpecs(WEB3BVSBookValueSpecsRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3BVSBookValueSpecsResponse  l_response = null;
        WEB3BVSBookValueSpecsService  l_service = null;

        try
        {
            l_service = (WEB3BVSBookValueSpecsService) Services.getService(
                                 WEB3BVSBookValueSpecsService.class);
        }
        catch (Exception e)
        {
            l_response = (WEB3BVSBookValueSpecsResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�뉿�P�����׏Ɖ�T�[�r�XImpl�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3BVSBookValueSpecsResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.error(l_request, "�뉿�P�����׏Ɖ��ʕ\�����s���܂����B", e);
            l_response = (WEB3BVSBookValueSpecsResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
