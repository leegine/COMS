head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������n���h��(WEB3BondCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/22 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3BondCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondCancelCompleteResponse;
import webbroker3.bd.message.WEB3BondCancelConfirmRequest;
import webbroker3.bd.message.WEB3BondCancelConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (������n���h��)<BR>
 * ������n���h��
 * 
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3BondCancelHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondCancelHandler.class);
    
    /**
     * @@roseuid 44E3362F0399
     */
    public WEB3BondCancelHandler() 
    {
     
    }
    
    /**
     * (������m�F)<BR>
     * ������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BondCancelConfirmResponse
     * @@roseuid 44B61AB403B8
     */
    public WEB3BondCancelConfirmResponse confirmCancel(WEB3BondCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "confirmCancel(WEB3BondCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondCancelConfirmResponse l_response = null;
        WEB3BondCancelService l_service = null;
        try
        {
            l_service =
                (WEB3BondCancelService)Services.getService(
                    WEB3BondCancelService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "������T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()���\�b�h���R�[������B
        try
        {
            l_response =
                (WEB3BondCancelConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * (���������)<BR>
     * ������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BondCancelCompleteResponse
     * @@roseuid 44B61AB403B8
     */
    public WEB3BondCancelCompleteResponse completeCancel(
        WEB3BondCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeCancel(WEB3BondCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondCancelCompleteResponse l_response = null;
        WEB3BondCancelService l_service = null;
        try
        {
            l_service =
                (WEB3BondCancelService)Services.getService(
                        WEB3BondCancelService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "������T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()���\�b�h���R�[������B
        try
        {
            l_response =
                (WEB3BondCancelCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
 
}
@
