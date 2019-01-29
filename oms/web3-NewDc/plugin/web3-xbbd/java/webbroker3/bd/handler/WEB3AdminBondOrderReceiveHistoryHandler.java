head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderReceiveHistoryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғ�����t�����Ɖ�n���h��(WEB3AdminBondOrderReceiveHistoryHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.217
*/
package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryResponse;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryRequest;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderReceiveHistoryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ғ�����t�����Ɖ�n���h��)<BR>
 * �Ǘ��Ғ�����t�����Ɖ�n���h���N���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminBondOrderReceiveHistoryHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderReceiveHistoryHandler.class);

    /**
     * @@roseuid 46A473FD00DA
     */
    public WEB3AdminBondOrderReceiveHistoryHandler()
    {

    }

    /**
     * (������t�����Ɖ�)<BR>
     * �Ǘ��Ғ�����t�����Ɖ�T�[�r�X���擾���Aexecute()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ғ�����t�����Ɖ�N�G�X�g<BR>
     * @@return WEB3AdminBondOrderReceiveHistoryResponse
     * @@roseuid 4688AB8801D3
     */
    public WEB3AdminBondOrderReceiveHistoryResponse orderReceiveHistory(
        WEB3AdminBondOrderReceiveHistoryRequest l_request)
    {
        final String STR_METHOD_NAME = " orderReceiveHistory(WEB3AdminBondOrderReceiveHistoryRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminBondOrderReceiveHistoryService l_service = null;
        WEB3AdminBondOrderReceiveHistoryResponse l_response = null;

        try
        {
            l_service =
                (WEB3AdminBondOrderReceiveHistoryService)Services.getService(
                    WEB3AdminBondOrderReceiveHistoryService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondOrderReceiveHistoryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "�Ǘ��Ғ�����t�����Ɖ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminBondOrderReceiveHistoryResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondOrderReceiveHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�Ǘ��Ғ�����t�����Ɖ�T�[�r�X�����s�����s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
