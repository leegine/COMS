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
filename	WEB3RuitoCancelAcceptedHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������t�n���h�� �N���X (WEB3RuitoCancelAcceptedHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15  �����F (���u) �V�K�쐬
*/

package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedService;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptRequest;


/**
 * �ݐϓ��������t�n���h���N���X<BR>
 */
public class WEB3RuitoCancelAcceptedHandler implements MessageHandler
{
	private static WEB3LogUtility log =
			WEB3LogUtility.getInstance(
			WEB3RuitoCancelAcceptResponse.class);
    /**
     * �ݐϓ��������t���s���B<BR>
     * <BR>
     * �ݐϓ��������t�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �ݐϓ��������t���N�G�X�g�I�u�W�F�N�g <BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoCancelAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 40582120014A
     */
    public WEB3RuitoCancelAcceptResponse cancelAcceptedRequest
        (WEB3RuitoCancelAcceptRequest l_request)
    {
        String STR_METHOD_NAME = "cancelAcceptedRequest(" +
            "WEB3RuitoCancelAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");            
        }
        
        WEB3RuitoCancelAcceptResponse l_response = null;
        WEB3RuitoCancelAcceptedService l_ruitoCancelAcceptService =
            (WEB3RuitoCancelAcceptedService)Services.getService
        (WEB3RuitoCancelAcceptedService.class);     

        try
        {
            l_response = (WEB3RuitoCancelAcceptResponse)l_ruitoCancelAcceptService.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3RuitoCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ݐϓ��������t�T�[�r�X�̎擾�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
