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
filename	WEB3RuitoOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ���������t�n���h���N���X (WEB3RuitoOrderAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.handler;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptService;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.xbruito.message.WEB3RuitoOrderAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoOrderAcceptRequest;
/**
 * �ݐϓ���������t�n���h���N���X<BR>
 */
public class WEB3RuitoOrderAcceptHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderAcceptHandler.class);
    /**
     * �ݐϓ���������t�������s���B<BR>
     * <BR>
     * �ݐϓ���������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �ݐϓ���������t���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.xbruito.message.WEB3RuitoOrderAcceptResponse
     * @@roseuid 405A4A08027F
     */
    public WEB3RuitoOrderAcceptResponse orderAcceptRequest(
            WEB3RuitoOrderAcceptRequest request)        
    {
        final String STR_METHOD_NAME =
            "orderAcceptRequest(WEB3RuitoOrderAcceptRequest request)";
        log.entering(STR_METHOD_NAME);
        WEB3RuitoOrderAcceptResponse l_response = null;
        WEB3RuitoOrderAcceptService l_service = null;
        log.debug("entry if (request == null)");
        if (request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        log.debug("end if (request == null)");
        try
        {
            l_service =
                (WEB3RuitoOrderAcceptService) Services.getService(
                    WEB3RuitoOrderAcceptService.class);
            log.debug("l_service = " + l_service);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3RuitoOrderAcceptResponse) request.createResponse();
            log.debug("l_ruitoOrderAcceptResponse =" + l_response);
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                request,
                "�ݐϓ���������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3RuitoOrderAcceptResponse) l_service.execute(request);
            log.debug("l_response = " + l_response);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3RuitoOrderAcceptResponse) request.createResponse();
            log.debug("l_ruitoOrderAcceptResponse =" + l_response);
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(request, "�ݐϓ���������t�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
