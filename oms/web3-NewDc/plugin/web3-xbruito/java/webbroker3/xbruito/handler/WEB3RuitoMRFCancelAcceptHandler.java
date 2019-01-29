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
filename	WEB3RuitoMRFCancelAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ���MRF�����t�n���h���N���X(WEB3RuitoMRFCancelAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/13 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFCancelAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * �ݐϓ���MRF�����t�n���h���N���X<BR>
 */
public class WEB3RuitoMRFCancelAcceptHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFCancelAcceptHandler.class);

    /**
     * �ݐϓ���MRF�����t�������s���B<BR>
     * <BR>
     * �ݐϓ���MRF�����t�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �ݐϓ���MRF�����t���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptResponse
     * @@roseuid 405812600235
     */
    public WEB3RuitoMRFCancelAcceptResponse mrfCancelAcceptRequest(
            WEB3RuitoMRFCancelAcceptRequest l_request)
    {
        String STR_METHOD_NAME = "mrfCancelAcceptRequest(" +
            "WEB3RuitoMRFCancelAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        /**
         * ���O�o�̓��[�e�B���e�B�B<BR>
         */

        final WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3RuitoMRFCancelAcceptHandler.class);

        // �ݐϓ���MRF�����t�T�[�r�X�I�u�W�F�N�g���擾����

        WEB3RuitoMRFCancelAcceptResponse l_response = null;
        WEB3RuitoMRFCancelAcceptService l_service = null;

        l_service =
                (WEB3RuitoMRFCancelAcceptService) Services.getService(
                    WEB3RuitoMRFCancelAcceptService.class);

        try
        {
            l_response = (WEB3RuitoMRFCancelAcceptResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3RuitoMRFCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ݐϓ���MRF�����t�T�[�r�X�̎擾�Ɏ��s���܂����B", l_ex);
            return l_response;           
        }
        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����     
        return l_response;
    }
}
@
