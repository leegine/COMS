head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesSettleContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍϓ��̓n���h��(WEB3FuturesSettleContractInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractInputService;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginListRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginListResponse;

/**
 * (�����w���敨�ԍϓ��̓n���h��)<BR>
 * �����w���敨�ԍϓ��̓n���h���N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesSettleContractInputHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractInputHandler.class);

    /**
     * @@roseuid 40F7B07302CE
     */
    public WEB3FuturesSettleContractInputHandler()
    {

    }

    /**
     * (get�ԍψꗗ)<BR>
     * �����w���敨�ԍψꗗ�\���������s���B<BR>
     * <BR>
     * �敨�ԍϓ��̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * 
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginListResponse
     * @@roseuid 40A967C602C0
     */
    public WEB3FuturesCloseMarginListResponse getCloseMarginList(WEB3FuturesCloseMarginListRequest l_request)
    {
        final String STR_METHOD_NAME = "getCloseMarginList(WEB3FuturesCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginListResponse l_response = null;
        WEB3FuturesSettleContractInputService l_service = null;

        try
        {
            l_service = (WEB3FuturesSettleContractInputService) Services.getService(WEB3FuturesSettleContractInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesCloseMarginListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���敨�ԍϓ��̓T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesCloseMarginListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesCloseMarginListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�ԍψꗗ�\�������Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get�ԍϓ��͉��)<BR>
     * �����w���敨�ԍϒ������͕\���������s���B<BR>
     * <BR>
     * �敨�ԍϓ��̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginInputResponse
     * @@roseuid 40A967C602DF
     */
    public WEB3FuturesCloseMarginInputResponse getCloseMarginInput(WEB3FuturesCloseMarginInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getCloseMarginInput(WEB3FuturesCloseMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginInputResponse l_response = null;
        WEB3FuturesSettleContractInputService l_service = null;

        try
        {
            l_service = (WEB3FuturesSettleContractInputService) Services.getService(WEB3FuturesSettleContractInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesCloseMarginInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���敨�ԍϓ��̓T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesCloseMarginInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesCloseMarginInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�ԍϒ������͕\�������Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
