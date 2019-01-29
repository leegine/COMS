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
filename	WEB3RuitoSellPossibleListReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������ꗗ�n���h��(WEB3RuitoSellPossibleListReferenceHandler)
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
import webbroker3.xbruito.service.delegate.WEB3RuitoSellPossibleListReferenceService;
import webbroker3.xbruito.message.WEB3RuitoSellListResponse;
import webbroker3.xbruito.message.WEB3RuitoSellListRequest;

/**
 * �ݐϓ������ꗗ�n���h��<BR>
 */
public class WEB3RuitoSellPossibleListReferenceHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellPossibleListReferenceHandler.class);

    /**
     * �ݐϓ����̉��ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �ݐϓ������ꗗ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �ݐϓ������ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoSellListResponse
     * @@roseuid 40693AD3031C
     */
    public WEB3RuitoSellListResponse searchRuitoSellPossibleListReference(
            WEB3RuitoSellListRequest l_request)
    {
		final String STR_METHOD_NAME = 
            "searchRuitoSellPossibleListReference(WEB3RuitoSellListRequest l_request)";
		log.entering(STR_METHOD_NAME);
		
		if (l_request == null)
		{
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");            
		}
		
		WEB3RuitoSellPossibleListReferenceService l_service = null;
        WEB3RuitoSellListResponse l_response = null;

        l_service =
            (WEB3RuitoSellPossibleListReferenceService) 
            Services.getService(WEB3RuitoSellPossibleListReferenceService.class);

        try
        {
            l_response = (WEB3RuitoSellListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3RuitoSellListResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "�ݐϓ����̉��ꗗ��ʕ\�����������s���܂����B", e.getErrorInfo(), e);
        }
		log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
