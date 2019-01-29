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
filename	WEB3RuitoCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ�����n���h�� (WEB3RuitoCancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ���u�� (���u) �V�K�쐬
*/

package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelService;

/**
 * �ݐϓ�������n���h���N���X<BR>
 */
public class WEB3RuitoCancelHandler implements MessageHandler 
{
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(
	    WEB3RuitoCancelHandler.class);
   
   /**
    * �ݐϓ�������R�����s���B<BR>
    * <BR>
    * �ݐϓ�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
    * @@param l_request - �ݐϓ�������m�F���N�G�X�g�I�u�W�F�N�g
    * @@return webbroker3.xbruito.message.WEB3RuitoCancelConfirmResponse
    * @@roseuid 40581D7500EC
    */
    public WEB3RuitoCancelConfirmResponse confirmCancel(
            WEB3RuitoCancelConfirmRequest l_request)
    {
		final String STR_METHOD_NAME = 
            "confirmCancel(WEB3RuitoCancelConfirmRequest l_request)";
		log.entering(STR_METHOD_NAME);
		
		if (l_request == null)
		{
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
		}

        WEB3RuitoCancelService l_service = null;
        WEB3RuitoCancelConfirmResponse l_response = null;
    
        l_service = (WEB3RuitoCancelService)Services.getService(
            WEB3RuitoCancelService.class);

        try 
        {
            l_response =
                (WEB3RuitoCancelConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
            (WEB3RuitoCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�ݐϓ�������R�����������s���܂����B", l_ex.getErrorInfo(), l_ex);
        }
        
		log.exiting(STR_METHOD_NAME);               
        return l_response;
    }
   
   /**
    * �ݐϓ�������o�^���s���B<BR>
    * <BR>
    * �ݐϓ�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
    * @@param l_request - �ݐϓ�������������N�G�X�g�I�u�W�F�N�g<BR>
    * @@return webbroker3.xbruito.message.WEB3RuitoCancelCompleteResponse
    * @@throws WEB3BaseException
    * @@roseuid 40581D7E0021
    */
    public WEB3RuitoCancelCompleteResponse completeCancel(
        WEB3RuitoCancelCompleteRequest l_request)
    {
		final String STR_METHOD_NAME = 
			"confirmCancel(WEB3RuitoCancelConfirmRequest l_request)";
		log.entering(STR_METHOD_NAME);
		
		if (l_request == null)
		{
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
		}

		WEB3RuitoCancelService l_service = null;
        WEB3RuitoCancelCompleteResponse l_response = null;
 
        l_service = (WEB3RuitoCancelService)Services.getService(
            WEB3RuitoCancelService.class);

        try 
        {
            l_response =
            (WEB3RuitoCancelCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
            (WEB3RuitoCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�ݐϓ�������o�^���������s���܂����B", l_ex.getErrorInfo(), l_ex);
        }
        
		log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
