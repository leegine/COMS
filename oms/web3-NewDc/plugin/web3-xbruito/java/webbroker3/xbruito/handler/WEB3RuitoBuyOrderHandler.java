head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoBuyOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������t�����n���h���N���X(WEB3RuitoBuyOrderHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.handler;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
/**
 * �ݐϓ������t�����n���h���N���X<BR>
 */
public class WEB3RuitoBuyOrderHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoBuyOrderHandler.class);
    /**
     * �ݐϓ������t�����R�����s���B<BR>
     * <BR>
     * �ݐϓ������t�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �ݐϓ������t�����m�F���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.xbruito.message.WEB3RuitoBuyConfirmResponse
     * @@roseuid 4058292B039C
     */
    public WEB3RuitoBuyConfirmResponse confirmBuyOrder(WEB3RuitoBuyConfirmRequest request)
    {
        final String STR_METHOD_NAME =
            "confirmBuyOrder(WEB3RuitoBuyConfirmRequest request)";
        log.entering(STR_METHOD_NAME);

        if (request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        WEB3RuitoBuyOrderService l_service = null;
        WEB3RuitoBuyConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3RuitoBuyOrderService) Services.getService(
                    WEB3RuitoBuyOrderService.class);
        }
        catch (Exception l_exp)
        {
            l_response = (WEB3RuitoBuyConfirmResponse) request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                request,
                "�ݐϓ����������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_exp);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3RuitoBuyConfirmResponse) l_service.execute(request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3RuitoBuyConfirmResponse) request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(request, "�ݐϓ����ݓ����t�����Ɏ��s���܂���", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * �ݐϓ������t�����o�^���s���B<BR>
     * <BR>
     * �ݐϓ������t�����T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �ݐϓ������t�����������N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.xbruito.message.WEB3RuitoBuyCompleteResponse
     * @@roseuid 40582931035D
     */
    public WEB3RuitoBuyCompleteResponse submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        WEB3RuitoBuyOrderService l_service = null;
        WEB3RuitoBuyCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3RuitoBuyOrderService) Services.getService(
                    WEB3RuitoBuyOrderService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3RuitoBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�ݐϓ������t�����T�[�r�X�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3RuitoBuyCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3RuitoBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ݐϓ������t�����Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
