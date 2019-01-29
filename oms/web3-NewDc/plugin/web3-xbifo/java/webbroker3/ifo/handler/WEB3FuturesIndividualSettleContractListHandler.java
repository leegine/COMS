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
filename	WEB3FuturesIndividualSettleContractListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ʕԍψꗗ�\���n���h���N���X(WEB3FuturesIndividualSettleContractListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.service.delegate.WEB3FuturesIndividualSettleContractListService;
import webbroker3.ifo.message.WEB3FuturesIndividualCloseMarginListRequest;
import webbroker3.ifo.message.WEB3FuturesIndividualCloseMarginListResponse;

/**
 * (�����w���敨�ʕԍψꗗ�\���n���h��)<BR>
 * �����w���敨�ʕԍψꗗ�\���n���h���N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesIndividualSettleContractListHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesIndividualSettleContractListHandler.class);

    /**
     * @@roseuid 40F7B07200DA
     */
    public WEB3FuturesIndividualSettleContractListHandler()
    {

    }

    /**
     * (get�ʕԍψꗗ)<BR>
     * �����w���敨�ʕԍψꗗ�\���������s���B<BR>
     * <BR>
     * �敨�ʕԍψꗗ��ʕ\���T�[�r�X���擾���Aexecute()<BR>
     * ���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * 
     * @@return webbroker3.ifo.message.WEB3FuturesIndividualCloseMarginListResponse
     * @@roseuid 40A9923C023B
     */
    public WEB3FuturesIndividualCloseMarginListResponse getIndividualCloseMarginList(WEB3FuturesIndividualCloseMarginListRequest l_request)
    {
        final String STR_METHOD_NAME = " getIndividualSettleContractList(WEB3FuturesIndividualCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesIndividualCloseMarginListResponse l_response = null;
        WEB3FuturesIndividualSettleContractListService l_service = null;

        try
        {
            l_service = (WEB3FuturesIndividualSettleContractListService) Services.getService(WEB3FuturesIndividualSettleContractListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesIndividualCloseMarginListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�敨�ʕԍψꗗ�\���T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesIndividualCloseMarginListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesIndividualCloseMarginListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�ʕԍψꗗ�\�������Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
