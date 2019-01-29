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
filename	WEB3OptionIndividualSettleContractListServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�ʕԍψꗗ�\���T�[�r�X�n���h��(WEB3OptionIndividualSettleContractListServiceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/24 ����� (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3OptionIndividualSettleContractListService;
import webbroker3.ifo.message.WEB3OptionsIndividualCloseMarginListResponse;
import webbroker3.ifo.message.WEB3OptionsIndividualCloseMarginListRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP�ʕԍψꗗ�\���T�[�r�X�n���h��)<BR>
 * �����w���I�v�V�����ʕԍψꗗ�\���T�[�r�X�n���h���N���X<BR>
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3OptionIndividualSettleContractListServiceHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionIndividualSettleContractListServiceHandler.class);

    /**
     * (get�ʕԍψꗗ)<BR>
     * <BR>
     * �����w���I�v�V�����ʕԍψꗗ�\���������s���B<BR>
     * <BR>
     * OP�ʕԍψꗗ��ʕ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3Options�hndividualCloseMarginListResponse
     * @@roseuid 408F67900057
     */
    public WEB3OptionsIndividualCloseMarginListResponse getIndividualSettleContractList(WEB3OptionsIndividualCloseMarginListRequest l_request)
    {
        final String STR_METHOD_NAME = " getIndividualSettleContractList(WEB3OptionsIndividualCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsIndividualCloseMarginListResponse l_response = null;
        WEB3OptionIndividualSettleContractListService l_service = null;

        try
        {
            l_service =
                 (WEB3OptionIndividualSettleContractListService)Services.getService(
                    WEB3OptionIndividualSettleContractListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsIndividualCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP�ʕԍψꗗ�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsIndividualCloseMarginListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsIndividualCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V�����ʕԍψꗗ�\�������Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
