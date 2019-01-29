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
filename	WEB3AdminEquityForcedSettleOrderApproveMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F���C���n���h��(WEB3AdminEquityForcedSettleOrderApproveMainHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����F (���u) �V�K�쐬 �d�l�ύX���f��No.129
Revision History : 2007/04/28 �����F ���f��No.148
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F���C���n���h��)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F���C���n���h���N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleOrderApproveMainHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveMainHandler.class);

    /**
     * @@roseuid 462CA41801AB
     */
    public WEB3AdminEquityForcedSettleOrderApproveMainHandler()
    {

    }

    /**
     * (���F�^�񏳔F���N�G�X�g)<BR>
     * �i�񓯊��j�������ω��������F�^�񏳔F�������N������B<BR>
     * <BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F���C�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainResponse
     * @@roseuid 460325730213
     */
    public WEB3AdminEquityForcedSettleOrderApproveMainResponse approveRequest(
        WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request)
    {
        final String STR_METHOD_NAME = "approveRequest(WEB3AdminEquityForcedSettleOrderApproveMainRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityForcedSettleOrderApproveMainResponse l_response = null;
        WEB3AdminEquityForcedSettleOrderApproveMainService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleOrderApproveMainService)Services.getService(
                    WEB3AdminEquityForcedSettleOrderApproveMainService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityForcedSettleOrderApproveMainResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminEquityForcedSettleOrderApproveMainResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request, "�i�񓯊��j�������ω��������F�^�񏳔F�����̏����X�e�[�^�X�̎��{�Ɏ��s���܂����B", l_ex);
            l_response =
                (WEB3AdminEquityForcedSettleOrderApproveMainResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
