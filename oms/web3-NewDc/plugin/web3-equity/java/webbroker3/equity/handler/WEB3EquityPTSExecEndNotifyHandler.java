head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSExecEndNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�����o���I���ʒm�n���h��(WEB3EquityPTSExecEndNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 ��іQ(���u) �V�K�쐬 ���f��No.1285
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityPTSExecEndNotifyRequest;
import webbroker3.equity.message.WEB3EquityPTSExecEndNotifyResponse;
import webbroker3.equity.service.delegate.WEB3EquityPTSExecEndNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * ((PTS)�����o���I���ʒm�n���h��)<BR>
 * (PTS)�����o���I���ʒm�n���h��<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3EquityPTSExecEndNotifyHandler implements MessageHandler
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSExecEndNotifyHandler.class);

    /**
     * @@roseuid 40B28FD9022A
     */
    public WEB3EquityPTSExecEndNotifyHandler()
    {

    }

    /**
     * (complete�ʒm)<BR>
     * (PTS)�����o���I���ʒm�������s���B<BR>
     * <BR>
     * (PTS)�����o���I���ʒm�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * (PTS)�����o���I���ʒm���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3EquityPTSExecEndNotifyResponse
     * @@roseuid 4056E82202D6
     */
    public WEB3EquityPTSExecEndNotifyResponse completeNotify(WEB3EquityPTSExecEndNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = "completeNotify(WEB3EquityPTSExecEndNotifyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityPTSExecEndNotifyResponse l_response = null;
        WEB3EquityPTSExecEndNotifyService l_service = null;

        try
        {
            l_service = (WEB3EquityPTSExecEndNotifyService)Services.getService(
                WEB3EquityPTSExecEndNotifyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityPTSExecEndNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "(PTS)�����o���I���ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3EquityPTSExecEndNotifyResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityPTSExecEndNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(PTS)�����o���I���ʒm���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityPTSExecEndNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
               "(PTS)�����o���I���ʒm���������s���܂����B",
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
