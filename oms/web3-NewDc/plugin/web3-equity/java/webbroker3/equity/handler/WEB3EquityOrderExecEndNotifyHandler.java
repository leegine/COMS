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
filename	WEB3EquityOrderExecEndNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���I���ʒm�n���h��(WEB3EquityOrderExecEndNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �A�C��(���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityExecEndNotifyRequest;
import webbroker3.equity.message.WEB3EquityExecEndNotifyResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecEndNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�o���I���ʒm�n���h���j�B
 * @@version 1.0
 */
public class WEB3EquityOrderExecEndNotifyHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderExecEndNotifyHandler.class);

    /**
     * @@roseuid 40B28FD9022A
     */
    public WEB3EquityOrderExecEndNotifyHandler()
    {

    }

    /**
     * (complete�ʒm)<BR>
     * �o���I���ʒm�������s���B<BR>
     * <BR>
     * �o���I���ʒm�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_requestData - 
     * �o���I���ʒm���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3EquityExecEndNotifyResponse
     * @@roseuid 4056E82202D6
     */
    public WEB3EquityExecEndNotifyResponse completeNotify(WEB3EquityExecEndNotifyRequest l_requestData)
    {
        final String STR_METHOD_NAME =
            "completeNotify(WEB3EquityExecEndNotifyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityExecEndNotifyResponse l_response = null;
        WEB3EquityOrderExecEndNotifyService l_service = null;

        try
        {
            l_service =
                (WEB3EquityOrderExecEndNotifyService) Services.getService(
                    WEB3EquityOrderExecEndNotifyService.class);
        }
        catch (Exception l_e)
        {
            l_response =
                (WEB3EquityExecEndNotifyResponse) l_requestData.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_requestData,
                "�o���I���ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3EquityExecEndNotifyResponse) l_service.execute(
                    l_requestData);
        }
        catch (WEB3BaseException l_e)
        {
            log.error(l_requestData, "�o���I���ʒm�Ɏ��s���܂����B", l_e);
            l_response =
                (WEB3EquityExecEndNotifyResponse) l_requestData.createResponse();
            l_response.errorInfo = l_e.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_requestData, "�o���I���ʒm�Ɏ��s���܂����B", l_bre);
            l_response =
                (WEB3EquityExecEndNotifyResponse) l_requestData.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
