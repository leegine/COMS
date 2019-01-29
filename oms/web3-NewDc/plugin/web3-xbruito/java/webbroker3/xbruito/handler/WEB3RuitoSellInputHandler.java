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
filename	WEB3RuitoSellInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������̓n���h��(WEB3RuitoSellInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 ���u�� (���u) �V�K�쐬
*/

package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3RuitoSellInputResponse;
import webbroker3.xbruito.message.WEB3RuitoSellInputRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellInputService;


/**
 * �ݓ������̓n���h��<BR>
 */
public class WEB3RuitoSellInputHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellInputHandler.class);

    /**
     * �ݐϓ����̉����͉�ʕ\���������s���B<BR>
     * �ݓ������̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �ݓ������̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoSellInputResponse
     * @@roseuid 406A907C01C2
     */
    public WEB3RuitoSellInputResponse sellInputRequest(
            WEB3RuitoSellInputRequest l_request)
    {
		final String STR_METHOD_NAME = 
            "sellInputRequest(WEB3RuitoSellInputRequest l_request)";
		log.entering(STR_METHOD_NAME);
		
        if (l_request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");            
        }

		WEB3RuitoSellInputService l_service = null;
        WEB3RuitoSellInputResponse l_response = null;

        l_service =
            (WEB3RuitoSellInputService) Services.getService(WEB3RuitoSellInputService.class);

        try
        {
            l_response = (WEB3RuitoSellInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3RuitoSellInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ݐϓ����̉����͉�ʕ\�����������s���܂����B", l_ex.getErrorInfo(), l_ex);
        }
		log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
