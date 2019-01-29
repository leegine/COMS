head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������Ɖ�n���h���N���X(WEB3MutualOrderReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ���E (���u) �V�K�쐬
                   2004/08/20 ���� (���u) ���r���[  
*/
package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualOrderReferenceRequest;
import webbroker3.mf.message.WEB3MutualOrderReferenceResponse;
import webbroker3.mf.service.delegate.WEB3MutualOrderReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M�������Ɖ�n���h���N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualOrderReferenceHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderReferenceHandler.class);
    /**
     * (search����)<BR>
     * �����M�������Ɖ�������{����B<BR>
     * <BR>
     * �����M�������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualOrderReferenceResponse
     * @@roseuid 40566A82033E
     */
    public WEB3MutualOrderReferenceResponse searchOrder(WEB3MutualOrderReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "searchOrder(WEB3MutualOrderReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.error(" the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        WEB3MutualOrderReferenceService l_service = null;
        WEB3MutualOrderReferenceResponse l_response = null;
        try
        {
            l_service =
                (WEB3MutualOrderReferenceService) Services.getService(
                    WEB3MutualOrderReferenceService.class);

        }
        catch (Exception l_ex)
        {
            l_response = (WEB3MutualOrderReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�����Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3MutualOrderReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MutualOrderReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����M�������Ɖ�̎擾�Ɏ��s���܂���", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
