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
filename	WEB3RuitoOrderReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ��������Ɖ�n���h���N���X(WEB3RuitoOrderReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/07 �m�X (���u) �V�K�쐬
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderReferenceService;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceResponse;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceRequest;

/**
 * �ݐϓ��������Ɖ�n���h���N���X
 */
public class WEB3RuitoOrderReferenceHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderReferenceHandler.class);

    /**
     * �ݐϓ����̒����Ɖ���s���B<BR>
     * <BR>
     * �ݓ������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �ݐϓ��������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.xbruito.message.WEB3RuitoOrderReferenceResponse
     * @@roseuid 405A4C21001E
     */
    public WEB3RuitoOrderReferenceResponse ruitoOrderReferenceRequest(
            WEB3RuitoOrderReferenceRequest l_request)
    {        
        final String STR_METHOD_NAME =
         "ruitoOrderReferenceRequest(WEB3RuitoOrderReferenceRequest)";

        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");            
        }
        WEB3RuitoOrderReferenceService l_service = null;
        WEB3RuitoOrderReferenceResponse l_response = null;

        try
        {
            l_service =
                (WEB3RuitoOrderReferenceService) Services.getService(
                    WEB3RuitoOrderReferenceService.class);
            //WEB3BaseException
            l_response = (WEB3RuitoOrderReferenceResponse) 
                    l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3RuitoOrderReferenceResponse) 
                    l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "�ݐϓ����̒����Ɖ�������s���܂����B",
                     e.getErrorInfo(), e);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
