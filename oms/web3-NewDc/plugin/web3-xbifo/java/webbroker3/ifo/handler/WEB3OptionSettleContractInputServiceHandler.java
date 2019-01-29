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
filename	WEB3OptionSettleContractInputServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�ԍϓ��̓n���h��(WEB3OptionSettleContractInputServiceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/23 ����� (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.ifo.message.WEB3OptionsCloseMarginListResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginListRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginInputResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginInputRequest;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractInputService;

/**
 * (OP�ԍϓ��̓n���h��)<BR>
 * �����w���I�v�V�����ԍϓ��̓n���h���N���X<BR>
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3OptionSettleContractInputServiceHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionSettleContractInputServiceHandler.class);

    /**
     * (get�ԍψꗗ)<BR>
     * <BR>
     * �����w���I�v�V�����ԍψꗗ�\���������s���B<BR>
     * <BR>
     * OP�ԍϓ��̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginListResponse
     * @@roseuid 4083A166014F
     */
    public WEB3OptionsCloseMarginListResponse getSettleContractList(WEB3OptionsCloseMarginListRequest l_request)
    {
        final String STR_METHOD_NAME = " getSettleContractList(WEB3OptionsCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsCloseMarginListResponse l_response = null;
        WEB3OptionSettleContractInputService l_service = null;

        try
        {
            l_service =
                (WEB3OptionSettleContractInputService)Services.getService(
                    WEB3OptionSettleContractInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���I�v�V�����ԍϓ��̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsCloseMarginListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V�����ԍψꗗ�\�������Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get�ԍϓ��͉��)<BR>
     * <BR>
     * �����w���I�v�V�����ԍϒ������͕\���������s���B<BR>
     * <BR>
     * OP�ԍϓ��̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginInputResponse
     * @@roseuid 4083A1E8017E
     */
    public WEB3OptionsCloseMarginInputResponse getSettleContractInputScreen(WEB3OptionsCloseMarginInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getSettleContractInputScreen(WEB3OptionsCloseMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsCloseMarginInputResponse l_response = null;
        WEB3OptionSettleContractInputService l_service = null;

        try
        {
            l_service =
                (WEB3OptionSettleContractInputService)Services.getService(
                    WEB3OptionSettleContractInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���I�v�V�����ԍϓ��̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsCloseMarginInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V�����ԍϒ������͕\�������Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
