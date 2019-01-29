head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecNotifyMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����o���ʒm���C���n���h��(WEB3EquityOrderExecNotifyMainHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 �������F(SRA) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityExecNotifyMainRequest;
import webbroker3.equity.message.WEB3EquityExecNotifyMainResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����o���ʒm���C���n���h���j�B
 * @@author �������F
 * @@version 1.0
 */
public class WEB3EquityOrderExecNotifyMainHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderExecNotifyMainHandler.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3EquityOrderExecNotifyMainHandler()
    {
    }

    /**
     * (�o���ʒm���C�����N�G�X�g)<BR>
     * <BR>
     * �����E�M�p����̏o���ʒm�T�[�r�X�U�蕪�����������{����B<BR>
     * <BR>
     * �����o���ʒm���C���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_execNotifyMainRequest - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3EquityExecNotifyMainResponse<BR>
     */
    public WEB3EquityExecNotifyMainResponse equityOrderExecNotifyMainRequest(WEB3EquityExecNotifyMainRequest l_execNotifyMainRequest)
    {
        final String STR_METHOD_NAME =
            "equityOrderExecNotifyMainRequest(WEB3EquityExecNotifyMainRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityExecNotifyMainResponse l_response = null;
        WEB3EquityOrderExecNotifyMainService l_service = null;

        try
        {
            l_service =
                (WEB3EquityOrderExecNotifyMainService) Services.getService(
                    WEB3EquityOrderExecNotifyMainService.class);
        }
        catch (Exception l_exp)
        {
            l_response =
                (WEB3EquityExecNotifyMainResponse) l_execNotifyMainRequest.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_execNotifyMainRequest,
                "�����o���ʒm���C���T�[�r�X�擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityExecNotifyMainResponse)l_service.execute(
                    l_execNotifyMainRequest);
        }
        catch (WEB3BaseException l_exp)
        {
            log.error(l_execNotifyMainRequest, "�����o���ʒm���C�������Ɏ��s���܂����B", l_exp);
            l_response =
                (WEB3EquityExecNotifyMainResponse)l_execNotifyMainRequest.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_execNotifyMainRequest, "�����o���ʒm���C�������Ɏ��s���܂����B", l_bre);
            l_response =
                (WEB3EquityExecNotifyMainResponse)l_execNotifyMainRequest.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
