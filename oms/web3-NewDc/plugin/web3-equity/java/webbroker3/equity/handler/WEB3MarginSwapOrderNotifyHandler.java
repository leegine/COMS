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
filename	WEB3MarginSwapOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�����ʒm�n���h��(WEB3MarginSwapOrderNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 �X�� (SRA) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.message.WEB3MarginSwapOrderNotifyRequest;
import webbroker3.equity.message.WEB3MarginSwapOrderNotifyResponse;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * �i�M�p����������n�����ʒm�n���h���j�B<BR>
 * <BR>
 * �M�p����������n�����ʒm�n���h��
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyHandler implements MessageHandler
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapOrderNotifyHandler.class);

    /**
     * (�R���X�g���N�^)�B<BR>
     */
    public WEB3MarginSwapOrderNotifyHandler()
    {
    }

    /**
     * (�M�p����������n�����ʒm���N�G�X�g)�B<BR>
     * <BR>
     * �M�p����������n�����ʒm�������s���B <BR>
     * <BR>
     * �M�p����������n�����ʒm�T�[�r�X���擾���A <BR>
     * execute()���\�b�h���R�[������B <BR>
     * <BR>
     * @@param l_request �M�p����������n�����ʒm���N�G�X�g�I�u�W�F�N�g <BR>
     * @@return WEB3MarginSwapOrderNotifyResponse<BR>
     */
    public WEB3MarginSwapOrderNotifyResponse swapOrderNotify(
        WEB3MarginSwapOrderNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = "swapOrderNotify(WEB3MarginSwapOrderNotifyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginSwapOrderNotifyResponse   l_response = null;
        WEB3MarginSwapOrderNotifyService    l_service = null;

        //--------------------
        //�M�p����������n�����ʒm�T�[�r�X�̎擾
        //--------------------
        try
        {
            l_service = (WEB3MarginSwapOrderNotifyService) Services
                .getService(WEB3MarginSwapOrderNotifyService.class);
        }
        catch (Exception l_e)
        {
            l_response = (WEB3MarginSwapOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, "�M�p����������n�����ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //--------------------
        //�M�p����������n�����ʒm�T�[�r�X�̎��s
        //--------------------
        try
        {
            l_response = (WEB3MarginSwapOrderNotifyResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(l_request, "�M�p����������n�����ʒm�Ɏ��s���܂����B", l_wbe);
            l_response = (WEB3MarginSwapOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_wbe.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_request, "�M�p����������n�����ʒm�Ɏ��s���܂����B", l_bre);
            l_response = (WEB3MarginSwapOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
